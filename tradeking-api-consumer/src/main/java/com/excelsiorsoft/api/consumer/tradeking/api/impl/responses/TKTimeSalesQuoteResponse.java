/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.TimeSalesQuote;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

public class TKTimeSalesQuoteResponse extends TKResponse {

    private String error;

    private TimeSalesQuote[] quotes;

    @JsonSetter("quotes")
    public void setQuote(Map quotesResponse) throws Exception {
        this.quotes = (TimeSalesQuote[]) this.extractArray(TimeSalesQuote[].class, quotesResponse, "quote");
    }

    public String getError() {
        return error;
    }

    public TimeSalesQuote[] getQuotes() {
        return quotes;
    }


}
