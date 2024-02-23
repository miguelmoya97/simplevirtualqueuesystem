package com.miguel.virtualqueue.configurations;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.miguel.virtualqueue.models.ClientResponseModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Configuration
public class QueueConfiguration {

    @Bean
    public ConcurrentLinkedQueue<ClientResponseModel> crmQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    @Bean
    public ConcurrentLinkedQueue<SseEmitter> sseQueue() {
        return new ConcurrentLinkedQueue<>();
    }
}