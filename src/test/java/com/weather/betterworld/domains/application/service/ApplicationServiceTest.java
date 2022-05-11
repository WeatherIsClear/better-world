package com.weather.betterworld.domains.application.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.response.AccountValidationApiResponse;
import com.weather.betterworld.api.response.DonateResultResponse;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.application.repository.ApplicationRepository;
import com.weather.betterworld.domains.application.request.TemporaryApplicationRequest;
import com.weather.betterworld.domains.donation.repository.DonationRepository;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.member.request.MemberRequest;
import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.organization.repository.OrganizationRepository;
import com.weather.betterworld.domains.organization.request.OrganizationRegistrationRequest;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.repository.ReceiveAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus.*;
import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceTest {

    @Mock
    MockBank mockBank;
    @Mock
    AccountRepository accountRepository;
    @Mock
    DonationRepository donationRepository;
    @Mock
    ApplicationRepository applicationRepository;
    @Mock
    OrganizationRepository organizationRepository;
    @Mock
    ReceiveAccountRepository receiveAccountRepository;
    @InjectMocks
    ApplicationService applicationService;

    TemporaryApplicationRequest request = new TemporaryApplicationRequest();

    @Test
    void applicationTest() {

        createAccount();

        createReceiveAccount();

        applicationServiceCall(true);
    }

    @Test
    void donationFailTest() {

        createAccount();

        createReceiveAccount();

        assertThatThrownBy(() -> applicationServiceCall(false)).isInstanceOf(IllegalStateException.class);
    }

    private void createAccount() {
        Member member = Member.of(new MemberRequest());
        AccountValidationApiResponse apiResponse = new AccountValidationApiResponse();
        given(accountRepository.findByAccountNumber(request.getAccountNumber()))
                .willReturn(ofNullable(Account.of(member, apiResponse)));
    }

    private void createReceiveAccount() {
        OrganizationRegistrationRequest organizationRequest = new OrganizationRegistrationRequest();
        given(organizationRepository.findById(request.getOrganizationId())).willReturn(ofNullable(Organization.of(organizationRequest)));
        Organization organization = organizationRepository.findById(request.getOrganizationId()).get();
        given(receiveAccountRepository.findByOrganizationAndStatus(organization, REGISTRATION)).willReturn(ofNullable(ReceiveAccount.of(organization, organizationRequest)));
    }

    private void applicationServiceCall(boolean isSuccess) {
        DonateResultResponse donateResultResponse = new DonateResultResponse(isSuccess, BigDecimal.ZERO);
        given(mockBank.donate(any())).willReturn(donateResultResponse);
        applicationService.temporaryApplication(request);
    }
}