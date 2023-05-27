package com.example.blog.controller;

import com.example.blog.service.ExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class ExceptionController {

    @Autowired
    ExceptionService exceptionService;

    @GetMapping("exception/test")
    public String test(int a) {
        int result = exceptionService.exceptionTest(a);
        log.info("result : {}",result);

        return "ok";
    }

    @GetMapping("exception/throw/test")
    public String throwtest(int a) throws Exception {
        int result = exceptionService.exceptionThrowTest(a);
        log.info("result : {}",result);

        return "ok";
    }

}
