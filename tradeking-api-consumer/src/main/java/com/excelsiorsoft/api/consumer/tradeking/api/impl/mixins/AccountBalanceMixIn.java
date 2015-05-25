/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.BuyingPower;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.CashData;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.Securities;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBalanceMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("account")
    int accountId;

    @JsonProperty("accountvalue")
    double accountValue;

    @JsonProperty("backofficehouseexcess")
    double backOfficeHouseExcess;

    @JsonProperty("buyingpower")
    BuyingPower buyingPowerSummary;

    @JsonProperty("fedcall")
    double fedCall;

    @JsonProperty("housecall")
    double houseCall;

    @JsonProperty("maintenanceexcess")
    double maintenanceExcess;

    @JsonProperty("money")
    CashData cashSummary;

    @JsonProperty("securities")
    Securities securitiesSummary;

}
