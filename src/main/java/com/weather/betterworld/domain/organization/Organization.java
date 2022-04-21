package com.weather.betterworld.domain.organization;

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
    @Column("organization_id")
    private Long id;

    private String president;

    private String name;

    private String tel;

    private String email;

    @Embedded
    private Address address;
}
