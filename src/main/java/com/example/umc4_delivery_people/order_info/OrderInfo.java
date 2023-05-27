package com.example.umc4_delivery_people.order_info;

import com.example.umc4_delivery_people.basket.Basket;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class OrderInfo extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer waitingTime;
    @Column(nullable = false)
    private Integer totalPrice;
    private String requestStore;
    private String requestDeliver;
    @Column(nullable = false)
    private Boolean status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASKET_ID")
    private Basket basket;

    public OrderInfo createOrderInfo(Integer waitingTime, Integer totalPrice, String requestStore, String requestDeliver, Basket basket) {
        this.waitingTime = waitingTime;
        this.totalPrice = totalPrice;
        this.requestStore = requestStore;
        this.requestDeliver = requestDeliver;
        this.status = true;
        this.basket = basket;
        this.basket.updateStatus();
        return this;
    }
}
