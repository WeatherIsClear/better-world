package com.weather.betterworld.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DonateResultResponse {

    private boolean isSuccess;

    private BigDecimal amount;

}
