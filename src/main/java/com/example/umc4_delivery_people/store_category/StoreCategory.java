package com.example.umc4_delivery_people.store_category;

import com.example.umc4_delivery_people.store.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class StoreCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public StoreCategory createStoreCategory(category category, Store store) {
        this.category = category;
        this.store = store;
        return this;
    }
}
