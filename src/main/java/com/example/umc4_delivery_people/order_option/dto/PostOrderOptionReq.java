package com.example.umc4_delivery_people.order_option.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostOrderOptionReq {
    private Long menuOptionId;
    private Long orderMenuId;
}
