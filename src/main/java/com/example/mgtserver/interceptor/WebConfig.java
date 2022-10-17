package com.example.mgtserver.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Hexrt
 * @description
 * @create 2022-05-06 14:10
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Resource(name = "normalUserInterceptor")
    private NormalUserInterceptor normalInterceptor;
    @Resource(name = "adminInterceptor")
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对普通用户操作进行核验
        registry.addInterceptor(normalInterceptor).addPathPatterns("/api/v2/user/password/change").excludePathPatterns("");
        // 对管理员进行权限核验
        registry.addInterceptor(adminInterceptor).addPathPatterns("/api/v2/user/register",
                                                                "/api/v2/user/modify",
                                                                "/api/v2/user/password/reset",
                                                                "/api/v2/user/list").excludePathPatterns("");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
