package com.example.umc4_delivery_people.store_type;

import com.example.umc4_delivery_people.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreTypeRepository extends JpaRepository<StoreType, Long> {

    @Query("select st.store from StoreType st where st.type = :type")
    List<Store> findStoresByType(@Param("type") type type);


}
