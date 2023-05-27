package com.example.umc4_delivery_people.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostMenuReq {
    private String name;
    private String ingredient;
    private Integer price;
    private Long storeId;
}
