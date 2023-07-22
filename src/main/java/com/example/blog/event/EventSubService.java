package com.example.blog.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventSubService {

    @EventListener
    public void subscribe(EventDto eventDto) {
        log.info("EvnetSubService. event: {}", eventDto);
    }

}
