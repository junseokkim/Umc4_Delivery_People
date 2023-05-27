package com.example.umc4_delivery_people.basket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostBasketReq {
    private Long memberId;
    private Long storeId;
    private Boolean deliveryType;
}
