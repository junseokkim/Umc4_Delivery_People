package com.example.umc4_delivery_people.store_type.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.umc4_delivery_people.store_type.type;

@Getter
@AllArgsConstructor
public class PostStoreTypeReq {
    private Long storeId;
    private type type;
}
