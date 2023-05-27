package com.example.umc4_delivery_people.member;

import com.example.umc4_delivery_people.utils.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private mrank mrank;

    public Member createMember(String email, String password, String name, String nickName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.mrank = com.example.umc4_delivery_people.member.mrank.고마운분;
        return this;
    }

    public void updateNickName(String nickName) { this.nickName = nickName;}
}
