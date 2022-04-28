package com.weather.betterworld.domains.organization.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrganizationRegistrationRequest {

    private String president;

    private String name;

    private String tel;

    private String email;

    private String street;

    private String zipcode;

    private String accountNumber;
}
