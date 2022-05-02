package com.weather.betterworld.domains.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class TemporaryApplicationRequest {

    private Long memberId;

    private Long organizationId;

    private String organizationName;

    private BigDecimal amount;

    private String memberName;

    private String accountNumber;
}
