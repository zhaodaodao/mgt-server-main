package com.example.mgtserver.service.version2;

import com.example.mgtserver.dto.*;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.mapper.version2.UserLoginStateMapper;
import com.example.mgtserver.mapper.version2.UserMapper;
import com.example.mgtserver.model.version2.User;
import com.example.mgtserver.model.version2.UserLoginState;
import com.example.mgtserver.utils.CryptUtil;
import com.example.mgtserver.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:43
 */
@Service("userService")
public class UserService {
    @Resource(name = "userLoginStateMapper")
    private UserLoginStateMapper loginStateMapper;
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Value("${project-config.time-to-alive}")
    private Long TTL;

    /**
     * 登录
     * @param userLoginDTO 登录信息
     * @return 登录结果
     */
    public UserLoginStateDTO login(UserLoginDTO userLoginDTO, int... role) {
        // 无主要登录参数
        if (null == userLoginDTO.getUsername() || null == userLoginDTO.getPassword()) {
            return null;
        }
        User user = userMapper.queryByUsername(userLoginDTO.getUsername());
        // 用户不存在
        if (null == user) {
            return null;
        }
        // 用户被禁用
        if (user.getIsDisable().equals(1)) {
            return null;
        }
        String password = CryptUtil.md5(userLoginDTO.getPassword());
        // 密码错误，登录失败
        assert password != null;
        if (!password.equals(user.getPassword())) {
            return null;
        }
        // 角色不匹配，管理员和普通用户
        if (Arrays.stream(role).noneMatch(x -> user.getRole().equals(x))) {
            return null;
        }
        // 登录成功流程
        // todo 对所有之前过期的token进行删除
        UserLoginState loginState = new UserLoginState(StringUtil.getUuidKey(), user.getId(), System.currentTimeMillis() + TTL);
        // 写入token，登录状态表
        loginStateMapper.insert(loginState);
        UserLoginStateDTO userLoginStateDTO = new UserLoginStateDTO();
        userLoginStateDTO.setToken(loginState.getToken());
        userLoginStateDTO.setUsername(user.getUsername());
        userLoginStateDTO.setRole(user.getRole());
        return userLoginStateDTO;
    }

    /**
     * 管理员登录
     * @param userLoginDTO
     * @return
     */
    public UserLoginStateDTO loginForAdmin(UserLoginDTO userLoginDTO) {
        return login(userLoginDTO, -1, 0);
    }

    /**
     * 注册
     * @param user 注册用户信息
     */
    public int register(User user) {
        // 必填参数为空
        if (null == user.getRole() || null == user.getUsername() || null == user.getPassword()) {
            return 0;
        }
        // 注册角色不允许范围
        if (user.getRole() < 0 || user.getRole() > 1) {
            return 0;
        }
        String username = user.getUsername();
        String password = user.getPassword();
        // 账号密码 长度为 [5, 16]
        // 账号只能有大小写字母和数字，且大小写字母开头
        // 密码只能包含大小写字母数字，^%&',;=?$ 这几个特殊字符
        if (!Pattern.matches("^[a-zA-Z][a-zA-Z0-9_]{4,15}$", username) || !Pattern.matches("^[a-zA-Z0-9^%&',;=?$]{5,16}$", password)) {
            return 0;
        }
        // 已经存在此用户名
        if (userMapper.queryByUsername(username) != null) {
            return 0;
        }
        // 对密码进行加密
        user.setPassword(CryptUtil.md5(password));
        //todo 这里对禁用与否需求进行分析判断
        // 初始为正常使用状态
        user.setIsDisable(0);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtLastLogin(null);
        return userMapper.insert(user);
    }

    /**
     * 修改密码
     * @param passwordChangeDTO 修改密码信息
     * @return
     */
    public int changePassword(PasswordChangeDTO passwordChangeDTO) {
        // 这里默认已经经过拦截器校验了，是有效的token
        UserLoginState loginState = loginStateMapper.queryByToken(passwordChangeDTO.getToken());
        assert loginState != null;
        User user = userMapper.queryById(loginState.getUserId());
        assert user != null;
        // 与旧密码不匹配
        if (!user.getPassword().equals(CryptUtil.md5(passwordChangeDTO.getOldPassword()))) {
            return 0;
        }
        List<UserLoginState> stateList = loginStateMapper.queryByUserId(user.getId());
        // 除自己外，所有已经登录状态的需要删除，设为失效
        for (UserLoginState state : stateList) {
            if (!state.getToken().equals(passwordChangeDTO.getToken())) {
                loginStateMapper.deleteByToken(state.getToken());
            }
        }
        // 更新密码
        user.setPassword(CryptUtil.md5(passwordChangeDTO.getNewPassword()));
        user.setGmtLastLogin(System.currentTimeMillis());
        return userMapper.update(user);
    }

    /**
     * 修改
     * @param userModifyDTO 用户修改信息
     * @return
     */
    public int modify(UserModifyDTO userModifyDTO) {
        UserLoginState loginState = loginStateMapper.queryByToken(userModifyDTO.getToken());
        assert loginState != null;
        User user = userMapper.queryById(loginState.getUserId());
        assert user != null;
        // 未授权的角色操作
        if (user.getRole() > 0) {
            return 0;
        }
        //todo 可能涉及超级管理员
        // 进行了两次查找
        User modifyUser = userMapper.queryById(userModifyDTO.getId());
        if (null == modifyUser) {
            modifyUser = userMapper.queryByUsername(userModifyDTO.getUsername());
        }
        // 不存在此修改的用户
        if (null == modifyUser) {
            return 0;
        }
        // 无法修改权限与自己同级或者高于自己的角色信息
        if (modifyUser.getRole() <= user.getRole()) {
            return 0;
        }
        // 无法修改为权限高于等于自身,或者非法权限
        if (null != userModifyDTO.getRole()) {
            if (userModifyDTO.getRole() <= user.getRole()) {
                return 0;
            }
            if (userModifyDTO.getRole() > 1) {
                return 0;
            }
        }

        // 禁止修改为超级管理员或者非法状态
        if (null != userModifyDTO.getIsDisable()) {
            if (userModifyDTO.getIsDisable() > 1 || userModifyDTO.getIsDisable() <= -1) {
                return 0;
            }
            // 修改用户为被禁用
            if (userModifyDTO.getIsDisable().equals(1)) {
                loginStateMapper.deleteByUserId(modifyUser.getId());
            }
        }

        // 清除过期的登录信息
        if (null != userModifyDTO.getRole() && !userModifyDTO.getRole().equals(modifyUser.getRole())) {
            loginStateMapper.deleteByUserId(modifyUser.getId());
        }

        modifyUser.setRole(userModifyDTO.getRole());
        modifyUser.setIsDisable(userModifyDTO.getIsDisable());
        modifyUser.setComment(userModifyDTO.getComment());
        System.out.println(userModifyDTO.getComment());
        return userMapper.update(modifyUser);
    }

    /**
     * 重置密码
     * @param username 用户名
     * @param token    token
     * @return
     */
    public User resetPassword(String username, String token) {
        UserLoginState loginState = loginStateMapper.queryByToken(token);
        assert loginState != null;
        User user = userMapper.queryById(loginState.getUserId());
        assert user != null;
        // 未授权的角色操作
        if (user.getRole() > 0) {
            return null;
        }
        //todo 可能涉及超级管理员
        User modifyUser = userMapper.queryByUsername(username);
        // 不存在此修改的用户
        if (null == modifyUser) {
            return null;
        }
        // 无法修改权限与自己同级或者高于自己的角色信息
        if (modifyUser.getRole() <= user.getRole()) {
            return null;
        }
        // 随机密码6位字符
        String password = StringUtil.getUuidKey(6);
        modifyUser.setPassword(CryptUtil.md5(password));
        // 清除过期的登录信息
        loginStateMapper.deleteByUserId(modifyUser.getId());
        // 更新
        userMapper.update(modifyUser);
        modifyUser.setPassword(password);
        return modifyUser;
    }

    /**
     * 查询用户
     * @param queryDTO  查询
     * @param token 查询者的token
     * @return
     */
    public List<UserInfoDTO> list(PagedQueryDTO<UserInfoDTO> queryDTO, String token) {
        UserInfoDTO userInfo = queryDTO.getQuery();
        UserLoginState loginState = loginStateMapper.queryByToken(token);
        assert loginState != null;
        User user = userMapper.queryById(loginState.getUserId());
        assert user != null;
        // 未授权的角色操作
        if (user.getRole() > 0) {
            return null;
        }
        User userQuery = new User();
        userQuery.setRole(userInfo.getRole());
        userQuery.setComment(userInfo.getComment());
        userQuery.setUsername(userInfo.getUsername());
        userQuery.setIsDisable(userInfo.getIsDisable());
        userQuery.setGmtCreate(userInfo.getGmtCreate());
        userQuery.setGmtLastLogin(userInfo.getGmtLastLogin());
        // 这里才进行分页操作
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<User> users = userMapper.query(userQuery);
        List<UserInfoDTO> list = new ArrayList<>(users.size());
        for (User userTemp : users) {
            list.add(new UserInfoDTO(userTemp));
        }
        return list;
    }

    /**
     * 更新用户是否可用
     * @param id      更新id
     * @param disable 可用状态
     * @param token   更新者的token
     * @return
     */
    public int updateAble(Long id, Integer disable, String token) {
        User user = userMapper.queryById(id);
        if (null == user) {
            return 0;
        }
        UserModifyDTO userModifyDTO = new UserModifyDTO();
        userModifyDTO.setId(user.getId());
        userModifyDTO.setIsDisable(disable);
        userModifyDTO.setToken(token);
        return modify(userModifyDTO);
    }

    /**
     * 通过token获取登录状态
     * @param token token
     * @return 登录状态
     */
    public UserLoginStateDTO getLoginStateByToken(String token) {
        UserLoginState loginState = loginStateMapper.queryByToken(token);
        // 不存在此token
        if (null == loginState) {
            return null;
        }
        // 过期了
        if (System.currentTimeMillis() >= loginState.getExpire()) {
            loginStateMapper.deleteByToken(token);
            return null;
        }
        User user = userMapper.queryById(loginState.getUserId());
        // 这种情况在数据库强制删除用户时可能发生(token 存在，但是用户不存在，这是一个严重错误)
        if (null == user) {
            loginStateMapper.deleteByToken(token);
            return null;
        }
        // 账户被禁用
        if (user.getIsDisable().equals(1)) {
            loginStateMapper.deleteByToken(token);
            return null;
        }
        return new UserLoginStateDTO(loginState.getToken(), user.getUsername(), user.getRole());
    }
}
