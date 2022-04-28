package com.weather.betterworld.domains.account.domain;

import com.weather.betterworld.api.account.AccountValidationApiResponse;
import com.weather.betterworld.domains.member.domain.Member;
import lombok.Builder;
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
    @Column(name = "account_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String accountNumber;

    private String billKey;

    private AccountStatus status;

    @Builder
    private Account(Member member, String accountNumber, String billKey, AccountStatus status) {
        this.member = member;
        this.accountNumber = accountNumber;
        this.billKey = billKey;
        this.status = status;
    }

    public static Account of(Member member, AccountValidationApiResponse apiResponse) {
        return Account.builder()
                .member(member)
                .accountNumber(apiResponse.getAccountNumber())
                .billKey(apiResponse.getBillKey())
                .status(AccountStatus.REGISTRATION)
                .build();
    }

    public void accountRelease() {
        this.status = AccountStatus.RELEASE;
    }
}
