package com.weather.betterworld.api;

import com.weather.betterworld.api.request.DonateApiRequest;
import com.weather.betterworld.api.response.AccountValidationApiResponse;
import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;

public interface MockBank {

    AccountValidationApiResponse accountValidation(AccountRegistrationRequest request);

    void donate(DonateApiRequest request);
}
