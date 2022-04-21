package com.weather.betterworld.domain.donation;

import com.weather.betterworld.domain.member.Member;
import com.weather.betterworld.domain.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Donation {

    @Id
    @GeneratedValue
    @Column("donation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("organization_id")
    private Organization organization;

    @ManyToOne(fetch = LAZY)
    @JoinColumn("member_id")
    private Member member;

    private BigDecimal amount;

}
