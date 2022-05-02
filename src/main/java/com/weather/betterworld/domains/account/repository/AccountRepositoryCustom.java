package com.weather.betterworld.domains.account.repository;

import com.weather.betterworld.domains.account.domain.Account;

import java.util.Optional;

public interface AccountRepositoryCustom {

    Optional<Account> findByMemberIdAndAccountNumber(Long memberId, String accountNumber);
}
