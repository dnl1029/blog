package com.example.blog.controller;


import com.example.blog.service.BeanTestService;
import com.example.blog.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    // @Qualifier 테스트를 위해 생성자 주입 주석처리
    // private final BeanTestService beanTestService;

    // @Autowired
    // private BeanTestService beanTestService;

//    @Autowired
//    private BeanTestService service;

    @Qualifier("manualService")
    @Autowired
    private BeanTestService service;

    @Autowired
    private MyService myService;

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/beanList")
    public void getBeanList() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : beanDefinitionNames) {
            log.info("beanName: {}",beanName);
        }
    }
    @GetMapping("servicelog")
    public void beanTest() {
        //beanTestService.log();
        service.log();
    }

    @GetMapping("myservicelog")
    public void myServiceBeanTest() {
        myService.mylog();
    }


}
