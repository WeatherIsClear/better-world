package com.weather.betterworld.domains.account.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.account.AccountValidationApiResponse;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.domain.AccountStatus;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final MockBank mockBank;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    public void accountRegistration(AccountRegistrationRequest request) {

        AccountValidationApiResponse apiResponse = mockBank.accountValidation(request);

        if (apiResponse.isSuccess()) {

            registration(request, apiResponse);

        } else {
            throw new IllegalStateException("계좌 등록에 실패하였습니다.");
        }
    }

    private void registration(AccountRegistrationRequest request, AccountValidationApiResponse apiResponse) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        previousAccountRelease();

        Account savedAccount = accountRepository.save(Account.of(member, apiResponse));
        log.debug("Account SAVE={}", savedAccount.getId());
    }

    private void previousAccountRelease() {
        Optional<Account> findOptionalAccount = accountRepository.findByStatus(AccountStatus.REGISTRATION);
        findOptionalAccount.ifPresent(Account::accountRelease);
    }
}
