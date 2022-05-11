package com.weather.betterworld.domains.account.domain;

import com.weather.betterworld.api.response.AccountValidationApiResponse;
import com.weather.betterworld.domains.member.domain.Member;
import com.weather.betterworld.domains.member.request.MemberRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.weather.betterworld.domains.account.domain.AccountStatus.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Test
    void accountReleaseTest() {
        Member member = Member.of(new MemberRequest());
        AccountValidationApiResponse apiResponse = new AccountValidationApiResponse();
        Account account = Account.of(member, apiResponse);
        assertThat(account.getStatus()).isEqualTo(REGISTRATION);

        account.accountRelease();
        assertThat(account.getStatus()).isEqualTo(RELEASE);
    }

}
