package com.miguel.virtualqueue.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseModel {
    private final String name;
    private final int position;
}
