package com.weather.betterworld.api.request;

import com.weather.betterworld.domains.receiveaccount.domain.ReceiveAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DonateApiRequest {

    private String billKey;

    private BigDecimal amount;

    private ReceiveAccount receiveAccount;
}
