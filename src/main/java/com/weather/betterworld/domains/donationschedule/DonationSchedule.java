package com.weather.betterworld.domains.donationschedule;

import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.organization.domain.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class DonationSchedule {

    @Id
    @GeneratedValue
    @Column(name = "donation_schedule_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
