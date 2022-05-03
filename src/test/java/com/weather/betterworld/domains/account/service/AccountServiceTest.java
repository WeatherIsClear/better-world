package com.weather.betterworld.domains.account.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.response.AccountValidationApiResponse;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.domain.AccountStatus;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.member.repository.MemberRepository;
import com.weather.betterworld.domains.member.request.MemberRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.weather.betterworld.domains.account.domain.AccountStatus.*;
import static java.util.Optional.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private MockBank mockBank;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService;

    AccountRegistrationRequest request;

    @BeforeEach
    void init() {
        this.request = new AccountRegistrationRequest();
    }

    @Test
    void previousAccountReleaseTest() {

        given(mockBank.accountValidation(request))
                .willReturn(new AccountValidationApiResponse(true, "1234", "1234"));

        AccountValidationApiResponse apiResponse = mockBank.accountValidation(request);

        Member member = findMember();
        Account account = saveAccount(apiResponse, member);

        given(accountRepository.findByMemberAndStatus(member, REGISTRATION)).willReturn(of(account));
        Optional<Account> currentAccount = accountRepository.findByMemberAndStatus(member, REGISTRATION);
        currentAccount.ifPresent(Account::accountRelease);

        assertThat(currentAccount.get().getStatus()).isEqualTo(RELEASE);
    }

    private Account saveAccount(AccountValidationApiResponse apiResponse, Member findMember) {
        Account account = Account.of(findMember, apiResponse);
        given(accountRepository.save(account)).willReturn(account);
        return accountRepository.save(account);
    }

    private Member findMember() {
        MemberRequest memberRequest = new MemberRequest("name", "email", "phone");
        Member member = Member.of(memberRequest);
        given(memberRepository.findById(request.getMemberId())).willReturn(ofNullable(member));
        Member findMember = memberRepository.findById(request.getMemberId()).get();
        return findMember;
    }

    @Test
    void accountValidationFail() {
        assertThatThrownBy(this::failLogic).isInstanceOf(IllegalStateException.class);
    }

    private void failLogic() {
        given(mockBank.accountValidation(request))
                .willReturn(new AccountValidationApiResponse(false, "1234", "1234"));

        accountService.accountRegistration(request);
    }
}
