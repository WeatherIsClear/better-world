package com.weather.betterworld.domains.receiveaccount.repository;

import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReceiveAccountRepository extends JpaRepository<ReceiveAccount, Long> {

    Optional<ReceiveAccount> findByOrganizationAndStatus(Organization organization, ReceiveAccountStatus status);
}
