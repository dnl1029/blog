package com.example.blog.controller;


import com.example.blog.dto.BeanValidationDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class BeanValidationController {

    @PostMapping("test")
    public String beanValidationTest(@RequestBody @Validated BeanValidationDto beanValidationDto){
        log.info("beanValidationDto : {}",beanValidationDto);
        return "ok";
    }

}
