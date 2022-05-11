package com.weather.betterworld.domains.donation.repository;

import com.weather.betterworld.domains.donation.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
