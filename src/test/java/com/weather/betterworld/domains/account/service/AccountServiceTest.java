package com.weather.betterworld.domains.account.service;

import com.weather.betterworld.api.MockBank;
import com.weather.betterworld.api.response.AccountValidationApiResponse;
import com.weather.betterworld.domains.account.domain.Account;
import com.weather.betterworld.domains.account.repository.AccountRepository;
import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.member.repository.MemberRepository;
import com.weather.betterworld.domains.member.request.MemberRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    AccountRegistrationRequest request = new AccountRegistrationRequest();;

    @Test
    void previousAccountReleaseTest() {

        mockBankMocking(true);

        AccountValidationApiResponse apiResponse = mockBank.accountValidation(request);

        Member member = findMember();
        Account account = Account.of(member, apiResponse);

        given(accountRepository.findByMemberAndStatus(member, REGISTRATION)).willReturn(of(account));

        accountService.accountRegistration(request);
    }

    private void mockBankMocking(boolean isSuccess) {
        given(mockBank.accountValidation(request))
                .willReturn(new AccountValidationApiResponse(isSuccess, "1234", "1234"));
    }

    private Member findMember() {
        MemberRequest memberRequest = new MemberRequest("name", "email", "phone");
        Member member = Member.of(memberRequest);
        given(memberRepository.findById(request.getMemberId())).willReturn(ofNullable(member));
        return memberRepository.findById(request.getMemberId()).get();
    }

    @Test
    void accountValidationFail()
    {
        assertThatThrownBy(this::failLogic).isInstanceOf(IllegalStateException.class);
    }

    private void failLogic() {
        mockBankMocking(false);

        accountService.accountRegistration(request);
    }
}
