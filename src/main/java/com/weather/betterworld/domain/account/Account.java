package com.weather.betterworld.domain.account;

import com.weather.betterworld.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    @Column("account_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("member_id")
    private Member member;

    private String billKey;

    private String number;

    private AccountStatus status;
}
