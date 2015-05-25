/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TradeKingTransaction;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

public class TKHistoryResponse extends TKResponse {

    private String error;

    private TradeKingTransaction[] transactionHistory;

    public String getError() {
        return error;
    }

    public TradeKingTransaction[] getTransactionHistory() {
        return transactionHistory;
    }

    @JsonSetter("transactions")
    public void setTransactionHistory(Map historyResponse) throws Exception {
        this.transactionHistory = (TradeKingTransaction[]) this.extractArray(TradeKingTransaction[].class, historyResponse, "transaction");
    }
}
