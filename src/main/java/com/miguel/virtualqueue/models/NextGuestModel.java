package com.miguel.virtualqueue.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NextGuestModel {
    public final boolean next;
}
