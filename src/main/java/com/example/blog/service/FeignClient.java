package com.example.blog.service;


import com.example.blog.dto.PostReturnDto;
import com.example.blog.dto.PostTestDto;
import com.example.blog.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.cloud.openfeign.FeignClient(value = "regres.in", url = "https://reqres.in/api")
public interface FeignClient {
    @GetMapping(value = "users/2")
    ResponseEntity<UserDto> getFeignData();

    @PostMapping(value = "users")
    ResponseEntity<PostReturnDto> postFeignData(@RequestBody PostTestDto postTestDto);

}
