package com.cgfy.mybatis.base.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截请求
 */
@Slf4j
public class LogcostInterceptor implements HandlerInterceptor {

    /**
     * preHandle是在请求执行前执行的,返回true,postHandler和afterCompletion方法才能执行,否则false为拒绝执行，起到拦截器控制作用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("拦截请求URI:"+requestURI);
        return true;
    }

    /**
     * afterCompletion是视图渲染完成之后才执行,同样需要preHandle返回true，该方法通常用于清理资源等工作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }





    /**
     * postHandler是在请求结束之后,视图渲染之前执行的,但只有preHandle方法返回true的时候才会执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
}
