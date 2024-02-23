package com.miguel.virtualqueue.services;

import com.miguel.virtualqueue.models.ClientResponseModel;
import com.miguel.virtualqueue.models.NextGuestModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@AllArgsConstructor
public class SseService {
    private final ConcurrentLinkedQueue<SseEmitter> emitters;
//    private final Gson gson;
    public void sendEventToClients(List<ClientResponseModel> newQueue) {
        List<SseEmitter> deadEmitters = new ArrayList<>();

        SseEmitter nextGuest = emitters.poll();
        try {
            if (nextGuest != null) {
                // perform a logic. In this simple example, sending a response that they are next is sufficient for the
                // front end to perform its logic
                nextGuest.send(NextGuestModel.builder().next(true).build());
            }
        } catch (IOException e) {
            deadEmitters.add(nextGuest);
        }


        for (SseEmitter emitter : emitters.stream().toList()) {
            try {
                emitter.send(newQueue);
            } catch (IOException e) {
                // Handle exceptions
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }

    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
    }

    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }
}
