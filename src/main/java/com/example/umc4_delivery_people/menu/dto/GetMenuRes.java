package com.example.umc4_delivery_people.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMenuRes {
    private Long menuId;
    private String name;
    private String ingredient;
}
