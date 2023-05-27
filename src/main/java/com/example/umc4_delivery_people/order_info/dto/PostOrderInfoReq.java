package com.example.umc4_delivery_people.order_info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostOrderInfoReq {
    private Integer waitingTime;
    private String requestStore;
    private String requestDeliver;
    private Long basketId;
}
