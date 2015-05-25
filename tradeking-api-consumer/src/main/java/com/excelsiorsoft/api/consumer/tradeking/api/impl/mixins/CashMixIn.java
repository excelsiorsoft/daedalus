/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CashMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("accruedinterest")
    double accruedInterest;

    @JsonProperty("cash")
    double cash;

    @JsonProperty("marginbalance")
    double marginBalance;

    @JsonProperty("cashavailable")
    double cashAvailable;

    @JsonProperty("mmf")
    double moneyMarketFund;

    @JsonProperty("total")
    double total;

    @JsonProperty("uncleareddeposits")
    double unclearedDeposits;

    @JsonProperty("unsettledfunds")
    double unsettledFunds;

    @JsonProperty("yield")
    double yield;

}
