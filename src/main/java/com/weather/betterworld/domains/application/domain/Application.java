package com.weather.betterworld.domains.application.domain;

import com.weather.betterworld.domains.application.request.TemporaryApplicationRequest;
import com.weather.betterworld.domains.donation.Donation;
import com.weather.betterworld.domains.donationschedule.DonationSchedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.weather.betterworld.domains.application.domain.DonationType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "donation_schedule_id")
    private DonationSchedule donationSchedule;

    private BigDecimal amount;

    private DonationType donationType;

    private LocalDateTime withdrawDate;

    @Builder
    private Application(Donation donation, DonationSchedule donationSchedule, BigDecimal amount, DonationType donationType, LocalDateTime withdrawDate) {
        this.donation = donation;
        this.donationSchedule = donationSchedule;
        this.amount = amount;
        this.donationType = donationType;
        this.withdrawDate = withdrawDate;
    }

    public static Application of(TemporaryApplicationRequest request) {
        return Application.builder()
                .amount(request.getAmount())
                .donationType(TEMPORARY)
                .build();
    }
}
