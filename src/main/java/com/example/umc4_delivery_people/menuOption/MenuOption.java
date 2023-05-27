package com.example.umc4_delivery_people.menuOption;

import com.example.umc4_delivery_people.menu.Menu;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MenuOption extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    public MenuOption createMenuOption(String name, Integer price, Menu menu) {
        this.name = name;
        this.price = price;
        this.menu = menu;
        this.menu.getMenuOptions().add(this);
        return this;
    }
}
