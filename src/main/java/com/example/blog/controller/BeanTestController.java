package com.example.blog.controller;


import com.example.blog.service.BeanTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class BeanTestController {
    private final BeanTestService beanTestService;

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/beanList")
    public void getBeanList() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : beanDefinitionNames) {
            log.info("beanName: {}",beanName);
        }
    }

    public void beanTest() {
        beanTestService.log();
    }
}
