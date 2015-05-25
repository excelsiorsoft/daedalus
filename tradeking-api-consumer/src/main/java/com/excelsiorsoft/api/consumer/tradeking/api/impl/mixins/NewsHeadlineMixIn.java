/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;


public class NewsHeadlineMixIn {

    @JsonProperty("id")
    private double id;

    @JsonProperty("headline")
    private String headline;

    @JsonProperty("date")
    private String date;
}
