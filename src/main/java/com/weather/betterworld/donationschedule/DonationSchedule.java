package com.weather.betterworld.donationschedule;

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
public class DonationSchedule {

    @Id
    @GeneratedValue
    @Column("donation_schedule_id")
    private Long id;


}
