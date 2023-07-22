package com.example.blog.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventPubService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void pub(String message) {
        EventDto eventDto = new EventDto();
        eventDto.setMessage(message);
        applicationEventPublisher.publishEvent(eventDto);
        log.info("publish success");
    }

}
