package com.weather.betterworld.domains.donation;

import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.organization.domain.Organization;
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
    @Column(name = "donation_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private BigDecimal amount;

}
