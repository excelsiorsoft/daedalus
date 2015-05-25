/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldingEntry;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class AccountHoldingsMixIn extends TradeKingKObjectMixIn {
    @JsonProperty("holding")
    @JsonDeserialize(using = AccountHoldingsEntryDeserializer.class)
    AccountHoldingEntry[] holdingsList;

    @JsonProperty("totalsecurities")
    double totalSecurities;

}
