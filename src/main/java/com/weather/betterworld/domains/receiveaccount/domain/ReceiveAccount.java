package com.weather.betterworld.domains.receiveaccount.domain;

import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.organization.request.OrganizationRegistrationRequest;
import lombok.Builder;
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
    @Column(name = "receive_account_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private String number;

    private ReceiveAccountStatus status;

    @Builder
    private ReceiveAccount(Organization organization, String number, ReceiveAccountStatus status) {
        this.organization = organization;
        this.number = number;
        this.status = status;
    }

    public static ReceiveAccount of(Organization organization, OrganizationRegistrationRequest request) {
        return ReceiveAccount.builder()
                .organization(organization)
                .number(request.getAccountNumber())
                .status(ReceiveAccountStatus.REGISTRATION)
                .build();
    }


}
