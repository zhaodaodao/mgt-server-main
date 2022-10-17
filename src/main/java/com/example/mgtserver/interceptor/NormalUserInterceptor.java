package com.example.mgtserver.interceptor;

import com.example.mgtserver.mapper.version2.UserLoginStateMapper;
import com.example.mgtserver.mapper.version2.UserMapper;
import com.example.mgtserver.model.version2.User;
import com.example.mgtserver.model.version2.UserLoginState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 13:55
 */
@Service("normalUserInterceptor")
public class NormalUserInterceptor extends UserAuthInterceptor {
    @Override
    public boolean validRole(int role) {
        return true;
    }
}
