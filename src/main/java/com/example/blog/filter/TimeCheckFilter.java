package com.example.blog.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class TimeCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String method = req.getMethod();
        StringBuffer requestURL = req.getRequestURL();

        long startTime = System.currentTimeMillis();
        try {
            log.info("TimeCheckFilter Method : {}, url : {},",method,requestURL);
            //핵심 코드 doFilter
            filterChain.doFilter(servletRequest,servletResponse);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            long endTime = System.currentTimeMillis();
            long gap = endTime - startTime;
            log.info("FilterElaspedTime : {} ms",gap);
        }

    }
}
