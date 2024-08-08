package com.example.board.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id",nullable = false)
    private String memberId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "call", nullable = false)
    private String call;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "nick_name",nullable = false)
    private String nickName;

    @Column(name = "email",nullable = false)
    private String email;


    public Member(String memberId, String password, String name, String call, Gender gender, String nickName, String email) {
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.call = call;
        this.gender = gender;
        this.nickName = nickName;
        this.email = email;
    }
}
