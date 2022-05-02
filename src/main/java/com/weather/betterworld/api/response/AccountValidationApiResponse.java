package com.weather.betterworld.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountValidationApiResponse {

    private boolean isSuccess;

    private String accountNumber;

    private String billKey;
}