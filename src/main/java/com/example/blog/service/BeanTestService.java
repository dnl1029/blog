package com.example.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BeanTestService {
    public void log() {
        log.info("log test...");
    }
}
