package com.weather.betterworld.domains.receiveaccount.repository;

import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiveAccountRepository extends JpaRepository<ReceiveAccount, Long> {
}
