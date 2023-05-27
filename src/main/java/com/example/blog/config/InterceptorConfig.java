package com.example.blog.config;

import com.example.blog.interceptor.MyInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .order(1)
                //.addPathPatterns("/api/*")
                .addPathPatterns("/api/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }

}
