/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldingEntry;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.FinancialInstrument;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.HoldingDisplayData;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.HoldingsQuote;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountHoldingEntryMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("accounttype")
    AccountHoldingEntry.HoldingType holdingType;

    @JsonProperty("costbasis")
    double costBasis;

    @JsonProperty("displaydata")
    HoldingDisplayData displayData;

    @JsonProperty("gainloss")
    double gainLoss;

    @JsonProperty("instrument")
    FinancialInstrument instrument;

    @JsonProperty("marketvalue")
    double marketValue;

    @JsonProperty("marketvaluechange")
    double marketValueChange;

    @JsonProperty("price")
    double price;

    @JsonProperty("purchaseprice")
    double purchasePrice;

    @JsonProperty("qty")
    double quantity;

    @JsonProperty("quote")
    HoldingsQuote holdingsQuote;

    @JsonProperty("sodcostbasis")
    double SODCostBasis;

}
