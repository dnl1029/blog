package com.example.blog.config;

import com.example.blog.service.BeanTestService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanTestConfig {
    @Qualifier("manualService")
//    @Bean(name = "manualService",initMethod = "init",destroyMethod = "destory")
    @Bean(name = "manualService")
    public BeanTestService beanTestService() {
        return new BeanTestService();
    }

    @Primary
    @Bean("beanTestService")
    public BeanTestService anotherBeanTestService() {
        return new BeanTestService();
    }
}
