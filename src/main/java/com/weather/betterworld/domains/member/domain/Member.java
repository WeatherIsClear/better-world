package com.weather.betterworld.domains.member.domain;

import com.weather.betterworld.domains.member.request.MemberRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static com.weather.betterworld.domains.member.domain.MemberStatus.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String phone;

    private MemberStatus status;

    @Builder
    private Member(String name, String email, String phone, MemberStatus status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public static Member of(MemberRequest request) {
        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .status(USER)
                .build();
    }
}
