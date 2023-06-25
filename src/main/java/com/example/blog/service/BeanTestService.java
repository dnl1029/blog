package com.example.blog.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
public class BeanTestService {
    public void log() {
        log.info("log test...");
    }

    @PostConstruct
    public void init() {
        log.info("BeanTestService init...");
    }

    @PreDestroy
    public void destory() {
        log.info("BeanTestService destroy...");
    }
    
    public void gracefulTest() throws InterruptedException {
        log.info("graceful test 시작");
        Thread.sleep(10000);
        log.info("graceful test 종료");
    }
}
