package com.example.blog.config;

import com.example.blog.interceptor.JwtInterceptor;
import com.example.blog.interceptor.MyInterceptor;
import com.example.blog.service.JwtIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyInterceptor())
//                .order(1)
//                //.addPathPatterns("/api/*")
//                .addPathPatterns("/api/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }

}
