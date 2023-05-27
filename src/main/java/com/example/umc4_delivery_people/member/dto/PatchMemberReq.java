package com.example.umc4_delivery_people.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchMemberReq {
    private Long memberId;
    private String nickName;
}
