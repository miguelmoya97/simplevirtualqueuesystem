package com.miguel.virtualqueue.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ClientRequestModel {
    private final String name;
    private final int numGuests;
    private final boolean status;
}
