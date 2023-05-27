package com.example.umc4_delivery_people.menu;

import com.example.umc4_delivery_people.menuOption.MenuOption;
import com.example.umc4_delivery_people.store.Store;
import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Menu extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String ingredient;
    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @OneToMany(mappedBy = "menu")
    private List<MenuOption> menuOptions;

    public Menu createMenu(String name, String ingredient, Integer price, Store store) {
        this.name = name;
        this.ingredient = ingredient;
        this.price = price;
        this.store = store;
        return this;
    }
}
