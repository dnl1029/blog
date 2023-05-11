package com.example.blog.controller;

import com.example.blog.dto.UserDto;
import com.example.blog.service.RestAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RestAPIController {

    private final RestAPIService restAPIService;

    @GetMapping("restTemplate")
    public ResponseEntity<UserDto> apiTestRestTemplate() {
        ResponseEntity<UserDto> userDtoResponseEntity = restAPIService.restTemplateMethod();
        return userDtoResponseEntity;
    }

    @GetMapping("feign")
    public ResponseEntity<UserDto> apiTestFeign() {
        ResponseEntity<UserDto> userDtoResponseEntity = restAPIService.feignMethod();
        return userDtoResponseEntity;
    }
}
