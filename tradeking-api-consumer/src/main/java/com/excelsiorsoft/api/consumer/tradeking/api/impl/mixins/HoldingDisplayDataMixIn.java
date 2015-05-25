/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HoldingDisplayDataMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("accounttype")
    String accountType;

    @JsonProperty("assetclass")
    String assetClass;

    @JsonProperty("change")
    String change;

    @JsonProperty("costbasis")
    String costBasis;

    @JsonProperty("desc")
    String description;

    @JsonProperty("lastprice")
    String lastPrice;

    @JsonProperty("marketvalue")
    String marketValue;

    @JsonProperty("marketvaluechange")
    String marketValueChange;

    @JsonProperty("qty")
    int quantity;

    @JsonProperty("symbol")
    String symbol;

}
