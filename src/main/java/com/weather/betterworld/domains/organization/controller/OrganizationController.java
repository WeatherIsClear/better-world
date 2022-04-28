package com.weather.betterworld.domains.organization.controller;

import com.weather.betterworld.domains.organization.request.OrganizationRegistrationRequest;
import com.weather.betterworld.domains.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public void registration(@RequestBody OrganizationRegistrationRequest request) {
        organizationService.organizationRegistration(request);
    }
}
