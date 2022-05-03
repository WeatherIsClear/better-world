package com.weather.betterworld.domains.application.controller;

import com.weather.betterworld.domains.application.request.TemporaryApplicationRequest;
import com.weather.betterworld.domains.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public void temporaryApplication(@RequestBody TemporaryApplicationRequest request) {
        applicationService.temporaryApplication(request);
    }
}
