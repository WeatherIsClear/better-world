package com.weather.betterworld.domains.application.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.request.DonateApiRequest;
import com.weather.betterworld.api.response.DonateResultResponse;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.application.domain.Application;
import com.weather.betterworld.domains.application.repository.ApplicationRepository;
import com.weather.betterworld.domains.application.request.TemporaryApplicationRequest;
import com.weather.betterworld.domains.donation.Donation;
import com.weather.betterworld.domains.donation.repository.DonationRepository;
import com.weather.betterworld.domains.organization.domain.Organization;
import com.weather.betterworld.domains.organization.repository.OrganizationRepository;
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
    private final DonationRepository donationRepository;
    private final ApplicationRepository applicationRepository;
    private final OrganizationRepository organizationRepository;
    private final ReceiveAccountRepository receiveAccountRepository;

    public void temporaryApplication(TemporaryApplicationRequest request) {

        Account account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalStateException("등록되지 않은 계좌입니다."));

        ReceiveAccount receiveAccount = getReceiveAccount(request);

        Application application = Application.of(request);
        applicationRepository.save(application);

        DonateResultResponse donateResult = mockBank.donate(new DonateApiRequest(account.getBillKey(), request.getAmount(), receiveAccount));

        donationSave(account, receiveAccount, donateResult);
    }

    private ReceiveAccount getReceiveAccount(TemporaryApplicationRequest request) {
        Organization organization = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 단체입니다."));

        return receiveAccountRepository.findByOrganizationAndStatus(organization, REGISTRATION)
                .orElseThrow(() -> new IllegalStateException("단체의 수취 계좌가 등록되지 않았습니다."));
    }

    private void donationSave(Account account, ReceiveAccount receiveAccount, DonateResultResponse donateResult) {
        if (donateResult.isSuccess()) {
            Donation donation = Donation.of(receiveAccount.getOrganization(), account.getMember(), donateResult.getAmount());
            donationRepository.save(donation);
        } else {
            throw new IllegalStateException("후원에 실패하셨습니다.");
        }
    }
}
