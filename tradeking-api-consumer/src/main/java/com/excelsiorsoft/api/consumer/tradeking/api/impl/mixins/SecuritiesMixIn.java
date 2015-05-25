/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecuritiesMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("longoptions")
    double longOptions;

    @JsonProperty("longstocks")
    double longStocks;

    @JsonProperty("shortoptions")
    double shortOptions;

    @JsonProperty("shortstocks")
    double shortStocks;

    @JsonProperty("options")
    double options;

    @JsonProperty("stocks")
    private double stocks;

    @JsonProperty("total")
    private double total;
}
