package com.example.mgtserver.interceptor;

import com.example.mgtserver.mapper.version2.UserLoginStateMapper;
import com.example.mgtserver.mapper.version2.UserMapper;
import com.example.mgtserver.model.version2.User;
import com.example.mgtserver.model.version2.UserLoginState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 22:00
 */
public class UserAuthInterceptor implements HandlerInterceptor {
    @Resource(name = "userLoginStateMapper")
    private UserLoginStateMapper loginStateMapper;
    @Resource(name = "userMapper")
    private UserMapper userMapper;
    @Value("${project-config.time-to-alive}")
    private Long TTL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //todo 这里可以通过session缓存短时间的存活状态，避免多次查表（优化时，需要注意，注销，禁用，等操作需要同步session数据）
        Cookie[] cookies = request.getCookies();
        // 没有携带登录参数
        if (null == cookies || cookies.length == 0) {
            return false;
        }

        // 获取token值
        String token = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        // 空值
        if (null == token) {
            return false;
        }
        UserLoginState loginState = null;
        loginState =loginStateMapper.queryByToken(token);
        // 或者值不存在
        if (null == loginState) {
            return false;
        }
        // 登录过期
        if (System.currentTimeMillis() >= loginState.getExpire()) {
            loginStateMapper.deleteByToken(token);
            return false;
        }
        User user = userMapper.queryById(loginState.getUserId());
        // 角色不匹配(管理员用户角色类型为0)
        if (null == user || !validRole(user.getRole())) {
            return false;
        }
        // 用户被禁用了
        if (user.getIsDisable() == 1) {
            // 删除其缓存的登录状态
            loginStateMapper.deleteByToken(token);
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public boolean validRole(int role) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}