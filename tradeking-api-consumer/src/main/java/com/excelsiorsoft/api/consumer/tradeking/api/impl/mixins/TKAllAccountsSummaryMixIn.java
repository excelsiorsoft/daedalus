/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.AccountBalance;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldings;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TKAllAccountsSummaryMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("account")
    String accountId;

    @JsonProperty("accountbalance")
    AccountBalance accountBalance;

    @JsonProperty("accountholdings")
    AccountHoldings accountHoldings;
}
