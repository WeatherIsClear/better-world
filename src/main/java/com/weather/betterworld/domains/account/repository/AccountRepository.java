package com.weather.betterworld.domains.account.repository;

import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.domain.AccountStatus;
import com.weather.betterworld.domains.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

    Optional<Account> findByMemberAndStatus(Member member, AccountStatus status);
}
