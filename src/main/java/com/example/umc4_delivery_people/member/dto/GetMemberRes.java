package com.example.umc4_delivery_people.member.dto;

import com.example.umc4_delivery_people.member.mrank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMemberRes {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phoneNumber;
    private mrank mrank;
}
