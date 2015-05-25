/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.OptionQuote;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

public class TKOptionQuoteResponse extends TKResponse {

    private String error;

    private OptionQuote[] quotes;

    @JsonSetter("quotes")
    public void setQuote(Map quotesResponse) throws Exception {
        this.quotes = (OptionQuote[]) this.extractArray(OptionQuote[].class, quotesResponse, "quote");
    }

    public String getError() {
        return error;
    }

    public OptionQuote[] getQuotes() {
        return quotes;
    }

}
