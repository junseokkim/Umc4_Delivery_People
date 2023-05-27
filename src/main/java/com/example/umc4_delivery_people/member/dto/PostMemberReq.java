package com.example.umc4_delivery_people.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostMemberReq {
    private String email;
    private String name;
    private String nickName;
    private String password;
    private String phoneNumber;
}
