package com.example.umc4_delivery_people.basket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetBasketRes {
    private Long basketId;
    private String nickName;
    private String storeName;
}
