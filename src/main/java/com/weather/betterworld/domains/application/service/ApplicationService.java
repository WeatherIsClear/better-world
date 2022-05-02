package com.weather.betterworld.domains.application.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.request.DonateApiRequest;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.application.domain.Application;
import com.weather.betterworld.domains.application.repository.ApplicationRepository;
import com.weather.betterworld.domains.application.request.TemporaryApplicationRequest;
import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
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
    private final ReceiveAccountRepository receiveAccountRepository;

    public void temporaryApplication(TemporaryApplicationRequest request) {

        Account account = validationAccount(request);

        ReceiveAccount receiveAccount = getReceiveAccount(request);

        mockBank.donate(new DonateApiRequest(account.getBillKey(), request.getAmount(), receiveAccount));
    }

    private Account validationAccount(TemporaryApplicationRequest request) {
        return accountRepository.findByMemberIdAndAccountNumber(request.getMemberId(), request.getAccountNumber())
                .orElseThrow(() -> new IllegalStateException("등록되지 않은 계좌입니다."));
    }

    private ReceiveAccount getReceiveAccount(TemporaryApplicationRequest request) {
        return receiveAccountRepository
                .findByOrganizationAndStatus(request.getOrganizationId(), REGISTRATION)
                .orElseThrow(() -> new IllegalStateException("계좌가 등록되지 않았습니다."));
    }
}
