package com.weather.betterworld.domains.account.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.response.AccountValidationApiResponse;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.weather.betterworld.domains.account.domain.AccountStatus.*;

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

        previousAccountRelease(member);

        Account savedAccount = accountRepository.save(Account.of(member, apiResponse));
        log.debug("Account SAVE={}", savedAccount.getId());
    }

    private void previousAccountRelease(Member member) {
        Optional<Account> findOptionalAccount = accountRepository.findByMemberAndStatus(member, REGISTRATION);
        findOptionalAccount.ifPresent(Account::accountRelease);
    }
}
