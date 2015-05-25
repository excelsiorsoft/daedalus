/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TransactionSecurity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import org.joda.time.DateTime;

public class TransactionDetailsMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("commission")
    double commission;

    @JsonProperty("fee")
    double fee;

    @JsonProperty("price")
    double price;

    @JsonProperty("quantity")
    double quantity;

    @JsonProperty("source")
    String source;

    @JsonProperty("side")
    int side;

    @JsonProperty("accounttype")
    int accountType;

    @JsonProperty("secfee")
    double SECFee;

    @JsonProperty("security")
    TransactionSecurity transactionSecurity;

    @JsonProperty("tradedate")
    DateTime tradeDate;

    @JsonProperty("settlementdate")
    DateTime settlementDate;

    @JsonProperty("transactionid")
    int transactionId;

    @JsonProperty("transactiontype")
    String transactionType;

    @JsonSetter("description")
    void setDescription(Object description) {
    }


}
