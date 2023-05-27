package com.example.umc4_delivery_people.order_menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostOrderMenuReq {
    private Long basketId;
    private Long menuId;
}
