package com.miguel.virtualqueue.controllers;

import com.miguel.virtualqueue.models.ClientRequestModel;
import com.miguel.virtualqueue.models.ClientResponseModel;
import com.miguel.virtualqueue.services.QueueService;
import com.miguel.virtualqueue.services.SseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class QueueController {
    private final QueueService qs;
    private final SseService ss;
    @PostMapping(value = "/joinQueue", produces = "application/json")
    public List<ClientResponseModel> joinQueue(@RequestBody ClientRequestModel request) {
        // process the client
        qs.enqueue(request);

        return qs.getQueueList();
    }

    @GetMapping(value = "/getQueue")
    public List<ClientResponseModel> getQueue() {
        return qs.getQueueList();
    }

    // Do I need bidirectionality?
    // SSE, no need for bidirectionality
    // i.e., when I pop a guest, I need to update their list
    @GetMapping(value = "/getNextGuest", produces = "application/json")
    public ClientResponseModel getNextGuest() {
        ClientResponseModel item = qs.dequeue();
        ss.sendEventToClients(qs.getQueueList());
        return item;

    }
}
