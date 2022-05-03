package com.weather.betterworld.domains.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private String name;

    private String email;

    private String phone;
}
