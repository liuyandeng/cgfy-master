package com.cgfy.mybatis.base.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogcostInterceptor())
                //添加需要验证登录用户操作权限的请求
                .addPathPatterns("/**"); //这里可以用registry.addInterceptor添加多个拦截器实例，后面加上匹配模式


    }
}