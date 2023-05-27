package com.example.umc4_delivery_people.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Integer countByEmail(String email);

    Member findByEmail(String email);

    @Query("select m from Member m")
    List<Member> findMembers();

    List<Member> findMemberByNickName(String nickName);
}
