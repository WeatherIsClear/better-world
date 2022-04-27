package com.weather.betterworld.domains.organization.service;

import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.organization.dto.OrganizationRegistrationRequest;
import com.weather.betterworld.domains.organization.repository.OrganizationRepository;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.repository.ReceiveAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ReceiveAccountRepository receiveAccountRepository;

    public void registration(OrganizationRegistrationRequest request) {
        Organization organization = Organization.of(request);
        Organization savedOrganization = organizationRepository.save(organization);
        log.debug("Organization registration={}", savedOrganization.getId());
        
        receiveAccountRegistration(savedOrganization, request);
    }

    private void receiveAccountRegistration(Organization savedOrganization, OrganizationRegistrationRequest request) {
        ReceiveAccount receiveAccount = ReceiveAccount.of(savedOrganization, request);
        ReceiveAccount savedReceiveAccountRepository = receiveAccountRepository.save(receiveAccount);
        log.debug("ReceiveAccount registration={}", savedReceiveAccountRepository.getId());
    }
}
