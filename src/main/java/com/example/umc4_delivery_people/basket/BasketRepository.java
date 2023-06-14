package com.example.umc4_delivery_people.basket;

import com.example.umc4_delivery_people.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Integer countByMemberAndStatus(Member member, Boolean status);

    Basket findBasketByMemberAndStatus(Member member, Boolean status);

    Basket findBasketByMember(Member member);
}
