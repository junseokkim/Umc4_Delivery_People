package com.example.umc4_delivery_people.menu;

import com.example.umc4_delivery_people.config.BaseResponseStatus;
import com.example.umc4_delivery_people.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Integer countByStoreAndName(Store store, String name);

    @Query("select m from Menu m where m.store.id = :storeId")
    List<Menu> findByStore(Long storeId);
}
