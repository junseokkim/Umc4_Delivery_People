package com.example.umc4_delivery_people.order_menu;

import com.example.umc4_delivery_people.basket.Basket;
import com.example.umc4_delivery_people.menu.Menu;
import com.example.umc4_delivery_people.order_option.OrderOption;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class OrderMenu extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BASKET_ID")
    private Basket basket;

    @OneToMany(mappedBy = "orderMenu")
    private List<OrderOption> orderOptions = new ArrayList<>();

    public OrderMenu createOrderMenu(Integer price, Menu menu, Basket basket) {
        this.price = price;
        this.menu = menu;
        this.basket = basket;
        this.status = true;
        // 현재 장바구니에서 추가된 메뉴 추가
        this.basket.getOrderMenus().add(this);
        this.basket.updateMenuPrice(price);
        return this;
    }

    public void updateStatus() {
        this.status = false;
        // 현재 장바구니에서 삭제된 메뉴 제거
        this.basket.getOrderMenus().remove(this);
        this.basket.updateMenuPrice(price * -1);
    }

    public void addOptionPrice(Integer price) {
        this.price += price;
        this.basket.updateMenuPrice(price);
    }
}
