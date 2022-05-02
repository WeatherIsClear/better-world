package com.weather.betterworld.domains.receiveaccount.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.weather.betterworld.domains.receiveaccount.domain.QReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.weather.betterworld.domains.receiveaccount.domain.QReceiveAccount.*;
import static com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus.*;

public class ReceiveAccountRepositoryImpl implements ReceiveAccountRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ReceiveAccountRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<ReceiveAccount> findByOrganizationAndStatus(Long organizationId, ReceiveAccountStatus status) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(receiveAccount)
                        .where(receiveAccount.organization.id.eq(organizationId)
                                .and(receiveAccount.status.eq(status)))
                        .fetchOne());
    }
}
