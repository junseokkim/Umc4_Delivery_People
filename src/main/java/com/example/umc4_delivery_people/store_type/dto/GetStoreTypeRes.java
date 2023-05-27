package com.example.umc4_delivery_people.store_type.dto;

import com.example.umc4_delivery_people.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStoreTypeRes {
    private Long storeId;
    private String name;
}
