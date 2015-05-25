/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyingPowerMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("cashavailableforwithdrawal")
    double cashAvailableForWithdrawal;

    @JsonProperty("equitypercentage")
    double equityPercentage;

    @JsonProperty("options")
    double options;

    @JsonProperty("sodoptions")
    double sodOptions;

    @JsonProperty("sodstock")
    double sodStock;

    @JsonProperty("stock")
    double stock;

}
