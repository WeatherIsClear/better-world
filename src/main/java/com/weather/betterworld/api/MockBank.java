package com.weather.betterworld.api;

import com.weather.betterworld.api.account.AccountValidationApiResponse;
import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;

public interface MockBank {

    AccountValidationApiResponse accountValidation(AccountRegistrationRequest request);
}
