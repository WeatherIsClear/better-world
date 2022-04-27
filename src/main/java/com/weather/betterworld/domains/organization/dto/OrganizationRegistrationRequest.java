package com.weather.betterworld.domains.organization.dto;

import lombok.Getter;

@Getter
public class OrganizationRegistrationRequest {

    private String president;

    private String name;

    private String tel;

    private String email;

    private String street;

    private String zipcode;

    private String accountNumber;
}
