package com.weather.betterworld.domains.receiveaccount.repository;

import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus;

import java.util.Optional;

public interface ReceiveAccountRepositoryCustom {

    Optional<ReceiveAccount> findByOrganizationAndStatus(Long organizationId, ReceiveAccountStatus status);
}
