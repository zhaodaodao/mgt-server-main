package com.example.mgtserver.controller.version2;

import com.example.mgtserver.dto.*;
import com.example.mgtserver.dto.util.PagedQueryDTO;
import com.example.mgtserver.enums.ResponseCode;
import com.example.mgtserver.model.version2.User;
import com.example.mgtserver.model.version2.UserLoginState;
import com.example.mgtserver.service.version2.UserService;
import com.example.mgtserver.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 11:02
 */
@Controller("userController")
@RequestMapping("/api/v2/user")
@ResponseBody
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    @Value("${project-config.admin-login-router}")
    private String adminLoginRouter;
    @Value("${project-config.time-to-alive}")
    private Long TTL;

    /**
     * 普通用户登录
     * @param userLoginDTO 登录信息
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResultVO<UserLoginStateDTO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) {
        ResultVO<UserLoginStateDTO> resultVO = verifyCode(userLoginDTO, request);
        // 检验验证码
        if (null != resultVO) {
            return resultVO;
        }
        // 普通用户，管理员都能登录
        UserLoginStateDTO loginState = userService.login(userLoginDTO, -1, 0, 1);
        if (null != loginState) {
            Cookie tokenCookie = new Cookie("token", loginState.getToken());
            tokenCookie.setPath("/");
            tokenCookie.setMaxAge((int)(TTL/1000));
            response.addCookie(tokenCookie);
        }
        return new ResultVO<>(ResponseCode.auto(loginState), loginState);
    }
    /**
     * 管理员登录
     * @param path         管理员登录指定路由
     * @param userLoginDTO 登录信息
     * @param request      请求
     * @param response     返回
     * @return
     * @throws IOException
     */
    @PostMapping("/admin/{path}")
    public ResultVO<UserLoginStateDTO> adminLogin(@PathVariable("path") String path, @RequestBody UserLoginDTO userLoginDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (null == path || !path.equals(adminLoginRouter)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        ResultVO<UserLoginStateDTO> resultVO = verifyCode(userLoginDTO, request);
        // 检验验证码
        if (null != resultVO) {
            return resultVO;
        }
        UserLoginStateDTO loginState = userService.loginForAdmin(userLoginDTO);
        if (null != loginState) {
            Cookie tokenCookie = new Cookie("token", loginState.getToken());
            tokenCookie.setPath("/");
            tokenCookie.setMaxAge((int)(TTL/1000));
            response.addCookie(tokenCookie);
        }
        return new ResultVO<>(ResponseCode.auto(loginState), loginState);
    }

    /**
     * 用户注册
     * @param user 注册用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public ResultVO<?> registry(@RequestBody User user) {
        int code = userService.register(user);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    /**
     * 修改密码
     * @param passwordChangeDTO
     * @param request
     * @return
     */
    @PostMapping("/password/change")
    public ResultVO<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO, HttpServletRequest request) {
        String token = getToken(request);
        if (null == token || null == passwordChangeDTO || null == passwordChangeDTO.getOldPassword() || null == passwordChangeDTO.getNewPassword()) {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
        passwordChangeDTO.setToken(token);
        int code = userService.changePassword(passwordChangeDTO);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    /**
     * 修改用户信息（对管理员开放）
     * @param userModifyDTO 用户修改信息
     * @param request       请求
     * @return
     */
    @PostMapping("/modify")
    public ResultVO<?> disable(@RequestBody UserModifyDTO userModifyDTO, HttpServletRequest request) {
        String token = getToken(request);
        if (null == token || null == userModifyDTO.getUsername()) {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
        userModifyDTO.setToken(token);
        int code = userService.modify(userModifyDTO);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    @PostMapping("/password/reset")
    public ResultVO<User> resetPassword(@RequestParam("username") String username, HttpServletRequest request) {
        String token = getToken(request);
        if (null == token || null == username) {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
        User user = userService.resetPassword(username, token);
        return new ResultVO<>(ResponseCode.auto(user), user);
    }

    @PostMapping("/list")
    public ResultVO<PageInfo<UserInfoDTO>> list(@RequestBody PagedQueryDTO<UserInfoDTO> userInfo, HttpServletRequest request) {
        String token = getToken(request);
        if (null == token || null == userInfo) {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
        List<UserInfoDTO> list = userService.list(userInfo, token);
        return new ResultVO<>(ResponseCode.auto(list), new PageInfo<UserInfoDTO>(list));
    }

    /**
     * 更新是否可用
     * @param id      id
     * @param disable 可用状态
     * @return
     */
    @PostMapping("/modify/disable")
    public ResultVO<?> modifyDisable(@RequestParam("id") Long id, @RequestParam("disable") Integer disable, HttpServletRequest request) {
        String token = getToken(request);
        if (null == token || null == id || null == disable) {
            return new ResultVO<>(ResponseCode.FAILED, null);
        }
        Integer code = userService.updateAble(id, disable, token);
        return new ResultVO<>(ResponseCode.auto(code), code);
    }

    /**
     * 主动查询用户登录状态
     * @param request 请求体
     * @return
     */
    @PostMapping("/state")
    public ResultVO<UserLoginStateDTO> userLoginState(HttpServletRequest request) {
        String token = getToken(request);
        if (null == token) {
            return new ResultVO<>(ResponseCode.errorOf("未登录状态"), null);
        }
        UserLoginStateDTO userLoginStateDTO = userService.getLoginStateByToken(token);
        return new ResultVO<>(ResponseCode.auto(userLoginStateDTO), userLoginStateDTO);
    }

    /**
     * 获取token
     * @param request 请求参数
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        // 没有携带登录参数
        if (null == cookies || cookies.length == 0) {
            return null;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        return token;
    }

    /**
     * 验证验证码正确性
     * @param userLoginDTO 用户输入验证码
     * @param request      系统生成验证码
     * @return
     */
    private ResultVO<UserLoginStateDTO> verifyCode(UserLoginDTO userLoginDTO, HttpServletRequest request) {
        //todo 当前需求删除验证码校验
        if (1024L == 1024L) {
            return null;
        }
        String code = (String)request.getSession().getAttribute("verifyCode");
        // 验证一次需要删除
        request.getSession().removeAttribute("verifyCode");
        if (null == code || null == userLoginDTO.getVerifyCode()) {
            return new ResultVO<>(ResponseCode.errorOf("无验证码"), null);
        }
        // 不区分大小写
        if (!code.equalsIgnoreCase(userLoginDTO.getVerifyCode())) {
            return new ResultVO<>(ResponseCode.errorOf("验证码错误"), null);
        }
        return null;
    }
}
