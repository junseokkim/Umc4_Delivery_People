package com.example.umc4_delivery_people.store_category.dto;

import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.store_category.category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStoreCategoryRes {
    private Long storeId;
    private String name;
}
