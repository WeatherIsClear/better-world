package com.weather.betterworld.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountValidationApiResponse {

    private boolean isSuccess;

    private String accountNumber;

    private String billKey;
}
