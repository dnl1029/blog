package com.example.blog.controller;

import com.example.blog.service.StreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class StreamController {

    @Autowired
    StreamService streamService;

    @GetMapping("stream/test")
    public void streamServiceCall() {
        log.info("StreamController is called");
        streamService.executeStream();
    }

}
