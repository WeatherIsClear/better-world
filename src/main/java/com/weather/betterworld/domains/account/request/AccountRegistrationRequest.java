package com.weather.betterworld.domains.account.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRegistrationRequest {

    private Long memberId;

    private String accountNumber;

    private String accountPassword;
}
