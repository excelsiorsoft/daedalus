/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.OptionQuote;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.joda.time.LocalDate;

public class OptionQuoteMixIn extends QuoteMixIn {

    @JsonProperty("contract_size")
    int contractSize;

    @JsonProperty("days_to_expiration")
    int daysToExpiration;

    @JsonProperty("imp_volatility")
    double impliedVolatility;

    @JsonProperty("idelta")
    double iDelta;

    @JsonProperty("igamma")
    double iGamma;

    @JsonProperty("irho")
    double iRho;

    @JsonProperty("itheta")
    double iTheta;

    @JsonProperty("ivega")
    double iVega;

    @JsonProperty("issue_desc")
    String fullDescription;

    @JsonProperty("op_delivery")
    OptionQuote.OptionDelivery optionDelivery;

    @JsonProperty("op_style")
    OptionQuote.OptionStyle optionStyle;

    @JsonProperty("op_subclass")
    OptionQuote.OptionClass optionClass;

    @JsonProperty("openinterest")
    String openInterest;

    @JsonProperty("pr_openinterest")
    String priorDayOpenInterest;

    @JsonProperty("opt_val")
    double optionValueCalculation;

    @JsonProperty("prem_mult")
    double optionPremiumMultiplier;

    @JsonProperty("put_call")
    OptionQuote.OptionType optionType;

    @JsonProperty("rootsymbol")
    String optionRootSymbol;

    @JsonProperty("strikeprice")
    double strikePrice;

    @JsonProperty("under_cusip")
    String underlyingCUSIP;

    @JsonProperty("undersymbol")
    String underlyingSymbol;

    @JsonProperty("xdate")
    @JsonDeserialize(using = SquishedDateDeserializer.class)
    LocalDate expirationDate;

    @JsonProperty("xday")
    String expirationDay;

    @JsonProperty("xmonth")
    String expirationMonth;

    @JsonProperty("xyear")
    String expirationYear;
}
