package com.example.blog.controller;

import com.example.blog.dto.AirbnbDto;
import com.example.blog.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class MongoController {

    private final MongoService mongoService;

    @GetMapping("mongo/test")
    public String apiKeyTest(@RequestParam String name) {
        AirbnbDto airbnbDto = mongoService.getAirbnbData(name);
        log.info("MongoController. airbnbDto : {}",airbnbDto);
        return "ok";
    }

}
