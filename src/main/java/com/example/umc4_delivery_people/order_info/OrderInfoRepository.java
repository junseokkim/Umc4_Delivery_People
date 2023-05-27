package com.example.umc4_delivery_people.order_info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

    @Query("select of from OrderInfo of where of.basket.member.id = :memberId")
    public List<OrderInfo> findOrderInfoByMemberId(Long memberId);
}
