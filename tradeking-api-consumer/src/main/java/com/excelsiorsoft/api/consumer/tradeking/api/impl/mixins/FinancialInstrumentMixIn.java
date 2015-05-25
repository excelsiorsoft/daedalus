/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialInstrumentMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("cusip")
    String CUSIP;

    @JsonProperty("desc")
    String description;

    @JsonProperty("factor")
    double factor;

    @JsonProperty("sectyp")
    String securityType;

    @JsonProperty("sym")
    String symbol;
}
