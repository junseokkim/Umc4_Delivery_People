package com.example.umc4_delivery_people.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStoreRes {
    private Long storeId;
    private String name;
    private String content;
    private Integer minOrderAmount;
    private String minDeliveryTime;
    private String maxDeliveryTime;
    private Integer deliveryTip;
}
