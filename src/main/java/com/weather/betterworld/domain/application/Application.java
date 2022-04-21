package com.weather.betterworld.domain.application;

import com.weather.betterworld.domain.donation.Donation;
import com.weather.betterworld.domain.donationschedule.DonationSchedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Application {

    @Id
    @GeneratedValue
    @Column("application_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn("donation_id")
    private Donation donation;

    @OneToOne(fetch = LAZY)
    @JoinColumn("donation_schedule_id")
    private DonationSchedule donationSchedule;

    private BigDecimal amount;

    private DonationType donationType;

    private LocalDateTime withdrawDate;
}
