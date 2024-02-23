package com.miguel.virtualqueue.controllers;

import com.miguel.virtualqueue.services.SseService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SSEController {
    private final SseService sseService;

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter sse() {
        SseEmitter emitter = new SseEmitter(-1L);

        emitter.onTimeout(emitter::complete);
        emitter.onCompletion(() -> sseService.removeEmitter(emitter));
        sseService.addEmitter(emitter);
        return emitter;
    }
}
