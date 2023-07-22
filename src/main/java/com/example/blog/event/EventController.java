package com.example.blog.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class EventController {

    private final EventPubService eventPubService;

    @PostMapping("eventpubsub")
    public void sendMessage(String message) {
        eventPubService.pub(message);
    }

}
