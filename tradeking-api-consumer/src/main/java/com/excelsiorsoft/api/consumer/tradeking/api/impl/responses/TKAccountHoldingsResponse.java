/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldings;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TKAccountHoldingsResponse extends TKResponse {

    private String error;

    @JsonProperty("accountholdings")
    private AccountHoldings accountHoldings;

    public String getError() {
        return error;
    }

    public AccountHoldings getAccountHoldings() {
        return accountHoldings;
    }
}
