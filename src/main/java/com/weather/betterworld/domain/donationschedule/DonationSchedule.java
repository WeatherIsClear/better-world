package com.weather.betterworld.domain.donationschedule;

import com.weather.betterworld.domain.account.Account;
import com.weather.betterworld.domain.member.Member;
import com.weather.betterworld.domain.organization.Organization;
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
    @Column("donation_schedule_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("account_id")
    private Account account;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("organization_id")
    private Organization organization;
}
