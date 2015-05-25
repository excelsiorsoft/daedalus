/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.LocalDate;

public class StockQuoteMixin extends QuoteMixIn {

    @JsonProperty("adp_100")
    double averageDailyPrice100;

    @JsonProperty("name")
    String companyName;

    @JsonProperty("adp_200")
    double averageDailyPrice200;

    @JsonProperty("adp_50")
    double averageDailyPrice50;

    @JsonProperty("adv_21")
    long averageDailyVolume21;

    @JsonProperty("adv_30")
    long averageDailyVolume30;

    @JsonProperty("adv_90")
    long averageDailyVolume90;

    @JsonProperty("beta")
    double beta;

    @JsonProperty("bidtick")
    Integer tickDirectionSinceLastBid;

    @JsonProperty("cusip")
    private String CUSIP;

    @JsonProperty("div")
    double lastDividend;

    @JsonProperty("divexdate")
    @JsonDeserialize(using = SquishedDateDeserializer.class)
    LocalDate exDividendDate;

    @JsonProperty("divfreq")
    char dividendFrequency;

    @JsonProperty("divpaydt")
    @JsonDeserialize(using = SquishedDateDeserializer.class)
    LocalDate lastDividendPayDate;

    @JsonProperty("eps")
    double earningsPerShare;

    @JsonProperty("iad")
    double indicatedAnnualDividend;

    @JsonProperty("op_flag")
    int hasOptions;

    @JsonProperty("pr_adp_100")
    double priorAverageDailyPrice100;

    @JsonProperty("pr_adp_200")
    double priorAverageDailyPrice200;

    @JsonProperty("pr_adp_50")
    double priorAverageDailyPrice50;

    @JsonProperty("prbook")
    double bookValuePrice;

    @JsonProperty("sho")
    String sharesOutstanding;

    @JsonProperty("volatility12")
    double oneYearVolatility;

    @JsonProperty("yield")
    Double dividendYield;

    @JsonProperty("pe")
    double priceEarning;

    @JsonProperty("sessionVolume")
    long sessionVolume;

    @JsonProperty("dollar_value")
    double dollarValue;

}
