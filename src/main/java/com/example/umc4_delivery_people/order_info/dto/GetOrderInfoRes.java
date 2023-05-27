package com.example.umc4_delivery_people.order_info.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class GetOrderInfoRes {
    private Long orderInfoId;
    private LocalDateTime createdTime;
    private String storeName;
    private String menuName;
    private Integer totalPrice;
}
