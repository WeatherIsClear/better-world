package com.weather.betterworld.domains.organization.service;

import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.organization.request.OrganizationRegistrationRequest;
import com.weather.betterworld.domains.organization.repository.OrganizationRepository;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.repository.ReceiveAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @Mock
    OrganizationRepository organizationRepository;

    @Mock
    ReceiveAccountRepository receiveAccountRepository;

    @Test
    void organizationRegistration() {

        OrganizationRegistrationRequest request = new OrganizationRegistrationRequest();
        Organization organization = Organization.of(request);
        given(organizationRepository.save(organization)).willReturn(organization);

        Organization savedOrganization = organizationRepository.save(organization);
        receiveAccountRegistration(organization, request);

        assertThat(organization).isEqualTo(savedOrganization);
    }

    private void receiveAccountRegistration(Organization savedOrganization, OrganizationRegistrationRequest request) {

        ReceiveAccount receiveAccount = ReceiveAccount.of(savedOrganization, request);
        given(receiveAccountRepository.save(receiveAccount)).willReturn(receiveAccount);

        ReceiveAccount savedReceiveAccount = receiveAccountRepository.save(receiveAccount);

        assertThat(receiveAccount).isEqualTo(savedReceiveAccount);
    }
}