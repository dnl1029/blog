package com.example.blog.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        log.info("MyInterceptor preHandle");
        String apiKey = request.getHeader("apikey");
        if(!"testkey123".equals(apiKey)) {
            throw new RuntimeException("intercepter invalid api key");
        }
        request.setAttribute("startTime",startTime);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor postHandle");
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = (long) request.getAttribute("startTime");
        long gap = endTime - startTime;
        log.info("MyInterceptor afterCompletion");
        log.info("MyInterceptor ElaspedTime : {} ms",gap);
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
