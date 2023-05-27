package com.example.umc4_delivery_people.store_category;

import com.example.umc4_delivery_people.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

    @Query("select sc.store from StoreCategory sc where sc.category = :category")
    List<Store> findStoresByCategory(@Param("category") category category);
}
