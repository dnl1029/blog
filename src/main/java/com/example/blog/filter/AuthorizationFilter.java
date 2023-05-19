package com.example.blog.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String apiKey = req.getHeader("apikey");

        try{
            if(!"testkey123".equals(apiKey)) {
                throw new RuntimeException("filter invalid api key");
            }
            //핵심 코드 doFilter
            filterChain.doFilter(servletRequest,servletResponse);
        }
        catch (Exception e){
            throw e;
        }
        finally {
            log.info("AuthorizationFilter is applied");
        }
    }

}
