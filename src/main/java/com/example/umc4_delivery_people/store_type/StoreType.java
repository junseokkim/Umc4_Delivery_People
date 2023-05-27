package com.example.umc4_delivery_people.store_type;

import com.example.umc4_delivery_people.store.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class StoreType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public StoreType createStoreType(type type, Store store) {
        this.type = type;
        this.store = store;
        return this;
    }
}
