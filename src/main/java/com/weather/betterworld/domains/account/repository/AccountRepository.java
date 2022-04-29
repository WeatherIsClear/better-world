package com.weather.betterworld.domains.account.repository;

import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.domain.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIdAndStatus(Long memberId, AccountStatus status);
}
