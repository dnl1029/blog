package com.example.blog.service;


import com.example.blog.dto.DataDto;
import com.example.blog.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient(value = "regres.in", url = "https://reqres.in/api")
public interface FeignClient {
    @GetMapping(value = "users/2")
    ResponseEntity<UserDto> getFeignData();
}
