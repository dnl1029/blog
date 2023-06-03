package com.example.blog.controller;

import com.example.blog.dto.*;
import com.example.blog.service.RestAPIService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("RequestParam/test")
    public String getRequestParam(@RequestParam String first, String second) {
        log.info("RequestParam log / first param : {}, second param : {}",first,second);
        return "ok";
    }

    @GetMapping("PathVariable/test/{first}/{second}")
    public String getPathVariable(@PathVariable String first, String second) {
        log.info("PathVariable log / first param : {}, second param : {}",first,second);
        return "ok";
    }

    @GetMapping("ModelAttribute/test1")
    public String getModelAttribute(@ModelAttribute RestAPITestDto restAPITestDto) {
        log.info("getModelAttibute log / first param : {}, second param : {}",restAPITestDto.getFirst(), restAPITestDto.getSecond());
        return "ok";
    }

    @PostMapping("ModelAtrribute/test2")
    public String postModelAttribute(@ModelAttribute RestAPITestDto restAPITestDto) {
        log.info("postModelAttribute log / first param : {}, second param : {}",restAPITestDto.getFirst(), restAPITestDto.getSecond());
        return "ok";
    }

    @GetMapping("RequestBody/test1")
    public String getRequestBody(@RequestBody RestAPITestDto restAPITestDto) {
        log.info("getRequestBody log / first param : {}, second param : {}",restAPITestDto.getFirst(), restAPITestDto.getSecond());
        return "ok";
    }

//    @PostMapping("RequestBody/test2")
//    public String postRequestBody(@RequestBody RestAPITestDto restAPITestDto, HttpServletRequest httpServletRequest) {
//        log.info("postRequestBody log / first param : {}, second param : {}",restAPITestDto.getFirst(), restAPITestDto.getSecond());
//        log.info("url : {}",httpServletRequest.getRequestURL());
//        return "ok";
//    }
    @PostMapping("RequestBody/test2")
    public String postRequestBody(@RequestBody RestAPITestDto restAPITestDto) {
        log.info("postRequestBody log / first param : {}, second param : {}",restAPITestDto.getFirst(), restAPITestDto.getSecond());

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("log : {}",servletRequest.getRequestURL());
        return "ok";
    }

    @PostMapping("apikey/test")
    public String apiKeyTest(@RequestBody ApiKeyDto apiKeyDto) {
        log.info("RestAPIController. apiKeyDto : {}",apiKeyDto);
        return "ok";
    }

}
