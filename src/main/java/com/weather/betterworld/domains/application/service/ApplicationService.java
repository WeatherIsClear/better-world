package com.weather.betterworld.domains.application.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.request.DonateApiRequest;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.application.domain.Application;
import com.weather.betterworld.domains.application.repository.ApplicationRepository;
import com.weather.betterworld.domains.application.request.TemporaryApplicationRequest;
import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.organization.repository.OrganizationRepository;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus;
import com.weather.betterworld.domains.receiveaccount.repository.ReceiveAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccountStatus.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final MockBank mockBank;
    private final AccountRepository accountRepository;
    private final ApplicationRepository applicationRepository;
    private final OrganizationRepository organizationRepository;
    private final ReceiveAccountRepository receiveAccountRepository;

    public void temporaryApplication(TemporaryApplicationRequest request) {

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalStateException("등록되지 않은 계좌입니다."));

        ReceiveAccount receiveAccount = getReceiveAccount(request);

        mockBank.donate(new DonateApiRequest(account.getBillKey(), request.getAmount(), receiveAccount));

        applicationRepository.save(Application.of(request));
    }

    private ReceiveAccount getReceiveAccount(TemporaryApplicationRequest request) {
        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 단체입니다."));

        return receiveAccountRepository.findByOrganizationAndStatus(organization, REGISTRATION)
                .orElseThrow(() -> new IllegalStateException("단체의 수취 계좌가 등록되지 않았습니다."));
    }
}
