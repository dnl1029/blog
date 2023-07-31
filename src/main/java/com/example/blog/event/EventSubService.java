package com.example.blog.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventSubService {

    @Qualifier("eventListenerThreadExecutor")
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Async("eventListenerThreadExecutor")
    @EventListener
    public void subscribe(EventDto eventDto) {
        log.info("EventSubService. event: {}", eventDto);
        log.info("thread / corePool : {}, maxPool : {}, QueueCapacity : {}, ActiveCount : {}",
                threadPoolTaskExecutor.getCorePoolSize(),
                threadPoolTaskExecutor.getMaxPoolSize(),
                threadPoolTaskExecutor.getQueueCapacity(),
                threadPoolTaskExecutor.getActiveCount());

    }

}
