package com.weather.betterworld.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    @Column("account_id")
    private Long id;

    private String billKey;

    private String number;

    private AccountStatus status;
}
