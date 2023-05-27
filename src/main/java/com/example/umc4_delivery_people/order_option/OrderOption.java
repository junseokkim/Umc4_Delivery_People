package com.example.umc4_delivery_people.order_option;

import com.example.umc4_delivery_people.menuOption.MenuOption;
import com.example.umc4_delivery_people.order_menu.OrderMenu;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class OrderOption extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENUOPTION_ID")
    private MenuOption menuOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERMENU_ID")
    private OrderMenu orderMenu;

    public OrderOption createOrderOption(MenuOption menuOption, OrderMenu orderMenu) {
        this.status = true;
        this.menuOption = menuOption;
        this.orderMenu = orderMenu;
        this.orderMenu.getOrderOptions().add(this);
        // 주문메뉴옵션 주가 시 해당 주문메뉴에 옵션 가격 추가
        this.orderMenu.addOptionPrice(menuOption.getPrice());
        return this;
    }
}
