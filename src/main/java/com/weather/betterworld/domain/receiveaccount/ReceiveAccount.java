package com.weather.betterworld.domain.receiveaccount;

import com.weather.betterworld.domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ReceiveAccount {

    @Id
    @GeneratedValue
    @Column("receive_account_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("organization_id")
    private Organization organization;

    private String number;

    private ReceiveAccountStatus status;
}
