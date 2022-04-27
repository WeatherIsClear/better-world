package com.weather.betterworld.domains.organization.domain;

import com.weather.betterworld.domains.organization.dto.OrganizationRegistrationRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "organization_id")
    private Long id;

    private String president;

    private String name;

    private String tel;

    private String email;

    @Embedded
    private Address address;

    @Builder
    private Organization(String president, String name, String tel, String email, Address address) {
        this.president = president;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    public static Organization of(OrganizationRegistrationRequest request) {
        return Organization.builder()
                .president(request.getPresident())
                .name(request.getName())
                .tel(request.getTel())
                .email(request.getEmail())
                .address(new Address(request.getStreet(), request.getZipcode()))
                .build();
    }
}
