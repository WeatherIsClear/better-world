package com.weather.betterworld.domains.account.controller;

import com.weather.betterworld.domains.account.request.AccountRegistrationRequest;
import com.weather.betterworld.domains.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void accountRegistration(@RequestBody AccountRegistrationRequest request) {
        accountService.accountRegistration(request);
    }
}
