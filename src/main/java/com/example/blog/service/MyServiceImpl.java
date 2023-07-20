package com.example.blog.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConfigurationProperties(prefix = "custom.value")
@Getter
@Setter
public class MyServiceImpl implements MyService{

    private String x;
    private String y;

    @Value("${custom.value_v2.z}")
    private String z;

    @Override
    public void mylog() {
        log.info("mylog test...");
        log.info("x : {}",x);
        log.info("y : {}",y);
        log.info("z : {}",z);

    }
}
