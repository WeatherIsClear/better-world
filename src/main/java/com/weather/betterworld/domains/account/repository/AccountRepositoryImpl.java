package com.weather.betterworld.domains.account.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weather.betterworld.domains.account.domain.Account;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.weather.betterworld.domains.account.domain.QAccount.*;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public AccountRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Optional<Account> findByMemberIdAndAccountNumber(Long memberId, String accountNumber) {
        return Optional.ofNullable(
                queryFactory
                        .select(account)
                        .from(account)
                        .where(account.member.id.eq(memberId)
                                .and(account.accountNumber.eq(accountNumber)))
                        .fetchOne());
    }
}
