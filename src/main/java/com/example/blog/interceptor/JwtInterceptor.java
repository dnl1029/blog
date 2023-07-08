package com.example.blog.interceptor;

import com.example.blog.service.JwtIssueService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtIssueService jwtIssueService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("JwtInterceptor preHandle");
        String jwtToken = request.getHeader("jwtToken");
        log.info("jwtToken : {}",jwtToken);
        if(jwtIssueService.tokenValidCheck(jwtToken)) {
            Claims body = jwtIssueService.getClaims(jwtToken).getBody();
            MDC.put("userID",body.get("userId").toString());
            MDC.put("role",body.get("role").toString());
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        else {
            throw new RuntimeException("jwt token is wrong");
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("MyInterceptor postHandle");
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("userID : {}",MDC.get("userID"));
        log.info("role : {}",MDC.get("role"));
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
