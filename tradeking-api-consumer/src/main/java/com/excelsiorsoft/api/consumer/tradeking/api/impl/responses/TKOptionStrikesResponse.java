/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Map;

public class TKOptionStrikesResponse extends TKResponse {

    private String error;

    private Double[] prices;

    @JsonSetter("prices")
    public void setPrices(Map pricesResponse) throws Exception {
        this.prices = (Double[]) this.extractArray(Double[].class, pricesResponse, "price");
    }

    public String getError() {
        return error;
    }

    public Double[] getPrices() {
        return prices;
    }

}
