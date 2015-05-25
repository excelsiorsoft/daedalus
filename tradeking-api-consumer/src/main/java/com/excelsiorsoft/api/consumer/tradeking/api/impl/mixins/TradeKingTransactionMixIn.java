/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TradeKingTransaction;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TransactionDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

public class TradeKingTransactionMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("activity")
    TradeKingTransaction.Type activityType;

    @JsonProperty("amount")
    double amount;

    @JsonProperty("date")
    DateTime date;

    @JsonProperty("desc")
    String description;

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("transaction")
    TransactionDetails transactionDetails;


}
