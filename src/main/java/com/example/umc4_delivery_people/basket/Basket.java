package com.example.umc4_delivery_people.basket;

import com.example.umc4_delivery_people.member.Member;
import com.example.umc4_delivery_people.order_info.OrderInfo;
import com.example.umc4_delivery_people.order_menu.OrderMenu;
import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Basket extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer menuPrice;
    @Column(nullable = false)
    private Integer deliveryTip;
    @Column(nullable = false)
    private boolean deliveryType;
    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @OneToMany(mappedBy = "basket")
    private List<OrderMenu> orderMenus = new ArrayList<>();

    // 세웅이 형한테 파라미터 객체 Basket을 받을 방법 물어보기
    public Basket createBasket(Integer deliveryTip, boolean deliveryType, Member member, Store store) {
        this.deliveryTip = deliveryTip;
        this.deliveryType = deliveryType;
        this.menuPrice = 0;
        this.member = member;
        this.store = store;
        this.status = true; // 생성 시 현재 장바구니
        return this;
    }

    public void updateStatus() {
        this.status = false;
    }

    public void updateMenuPrice(Integer price) {
        this.menuPrice += price;
    }
}
