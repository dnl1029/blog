package com.example.blog.service;

import com.example.blog.dto.DataDto;
import com.example.blog.dto.PostReturnDto;
import com.example.blog.dto.PostTestDto;
import com.example.blog.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestAPIService {

    private final RestTemplate restTemplate;
    private final FeignClient feignClient;

    public ResponseEntity<UserDto> restTemplateMethod() {

//        HttpHeaders httpHeaders = new HttpHeaders();
//        Charset charset = Charset.forName("UTF-8");
//        MediaType mediaType = new MediaType("application", "json",charset);
//        httpHeaders.setContentType(mediaType);
//        HttpEntity<Object> request = new HttpEntity<Object>(httpHeaders);
        String url = "https://reqres.in/api/users/2";

        ResponseEntity<UserDto> userDtoResponseEntity = null;

        try{
            long startTime = System.currentTimeMillis();
            userDtoResponseEntity = restTemplate.getForEntity(url,UserDto.class);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            log.info("statusCode : {}", userDtoResponseEntity.getStatusCode());
            log.info("getBody : {}", userDtoResponseEntity.getBody());
            log.info("elapsedTime = {}",elapsedTime);
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return userDtoResponseEntity;
    }

    public ResponseEntity<UserDto> feignMethod() {

        ResponseEntity<UserDto> result = null;
        try{
            long startTime = System.currentTimeMillis();
            ResponseEntity<UserDto> userDtoResponseEntity = feignClient.getFeignData();
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            log.info("statusCode : {}", userDtoResponseEntity.getStatusCode());
            log.info("getBody : {}", userDtoResponseEntity.getBody());
            log.info("elapsedTime = {}",elapsedTime);
            result = userDtoResponseEntity;
        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public ResponseEntity<PostReturnDto> postRestTemplateMethod(PostTestDto postTestDto) {

        String url = "https://reqres.in/api/users";

        ResponseEntity<PostReturnDto> postReturnDtoResponseEntity = null;
        try {
            long startTime = System.currentTimeMillis();
            postReturnDtoResponseEntity = restTemplate.postForEntity(url, postTestDto, PostReturnDto.class);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            log.info("statusCode : {}", postReturnDtoResponseEntity.getStatusCode());
            log.info("getBody : {}", postReturnDtoResponseEntity.getBody());
            log.info("elapsedTime = {}", elapsedTime);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return postReturnDtoResponseEntity;
    }

    public ResponseEntity<PostReturnDto> postFeignMethod(PostTestDto postTestDto) {

        ResponseEntity<PostReturnDto> postReturnDtoResponseEntity = null;
        try {
            long startTime = System.currentTimeMillis();
            postReturnDtoResponseEntity = feignClient.postFeignData(postTestDto);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            log.info("statusCode : {}", postReturnDtoResponseEntity.getStatusCode());
            log.info("getBody : {}", postReturnDtoResponseEntity.getBody());
            log.info("elapsedTime = {}", elapsedTime);

        }
        catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return postReturnDtoResponseEntity;
    }
    
}
