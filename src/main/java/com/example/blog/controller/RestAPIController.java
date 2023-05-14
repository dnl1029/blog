package com.example.blog.controller;

import com.example.blog.dto.PostReturnDto;
import com.example.blog.dto.PostTestDto;
import com.example.blog.dto.UserDto;
import com.example.blog.service.RestAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("postrestTemplate")
    public ResponseEntity<PostReturnDto> postapiTestRestTemplate(@RequestBody PostTestDto postTestDto){
        ResponseEntity<PostReturnDto> postReturnDtoResponseEntity = restAPIService.postRestTemplateMethod(postTestDto);
        return postReturnDtoResponseEntity;
    }

    @PostMapping("postfeign")
    public ResponseEntity<PostReturnDto> postapiTestFeign(@RequestBody PostTestDto postTestDto) {
        ResponseEntity<PostReturnDto> postReturnDtoResponseEntity = restAPIService.postFeignMethod(postTestDto);
        return postReturnDtoResponseEntity;
    }

}
