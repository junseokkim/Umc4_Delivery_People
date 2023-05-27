package com.example.umc4_delivery_people.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLoginReq {
    private String email;
    private String password;
}
