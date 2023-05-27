package com.example.umc4_delivery_people.store;

import com.example.umc4_delivery_people.store_category.StoreCategory;
import com.example.umc4_delivery_people.store_type.StoreType;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Store extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String content;
    private Integer minOrderAmount;
    private String minDeliveryTime;
    private String maxDeliveryTime;
    @Column(nullable = false)
    private Integer deliveryTip;

    @OneToMany(mappedBy = "store")
    private List<StoreCategory> storeCategories;

    @OneToMany(mappedBy = "store")
    private List<StoreType> storeTypes;

    public Store createStore(String name, String content, Integer minOrderAmount, String minDeliveryTime, String maxDeliveryTime, Integer deliveryTip) {
        this.name = name;
        this.content = content;
        this.maxDeliveryTime = maxDeliveryTime;
        this.minDeliveryTime = minDeliveryTime;
        this.minOrderAmount = minOrderAmount;
        this.deliveryTip = deliveryTip;
        return this;
    }
}
