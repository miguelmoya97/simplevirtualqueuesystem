package com.miguel.virtualqueue.services;

import com.miguel.virtualqueue.models.ClientRequestModel;
import com.miguel.virtualqueue.models.ClientResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final ConcurrentLinkedQueue<ClientResponseModel> queue;

    public void enqueue(ClientRequestModel item) {
        ClientResponseModel responseModel = ClientResponseModel.builder()
                .name(item.getName())
                .position(queue.size())
                .build();
        queue.add(responseModel);

    }
    public ClientResponseModel dequeue() {
        return queue.poll();
    }

    public List<ClientResponseModel> getQueueList() {
        return queue.stream().toList();
    }

    public int getQueueSize() {
        return queue.size();
    }
}
