package com.example.umc4_delivery_people.menuOption.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostMenuOptionReq {
    private String name;
    private Integer price;
    private Long menuId;
}
