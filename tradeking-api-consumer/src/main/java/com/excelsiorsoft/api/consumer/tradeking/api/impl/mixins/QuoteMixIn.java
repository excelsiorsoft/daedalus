/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.ChangeSign;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.Quote;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

abstract class QuoteMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("ask_time")
    LocalTime askTime;

    @JsonProperty("asksz")
    int askLatestSize;

    @JsonProperty("bid_time")
    LocalTime bidTime;

    @JsonProperty("bidsz")
    int bidLatestSize;

    @JsonProperty("chg")
    double change;

    @JsonProperty("chg_sign")
    ChangeSign changeSign;

    @JsonProperty("chg_t")
    String changeText;

    @JsonProperty("cl")
    double previousClose;

    @JsonProperty("date")
    LocalDate dateLastTrade;

    @JsonProperty("exch")
    String exchangeCode;

    @JsonProperty("exch_desc")
    String exchangeDescription;

    @JsonProperty("hi")
    double dayHigh;

    @JsonProperty("incr_vl")
    int volumeLastTrade;

    @JsonProperty("last")
    double lastPrice;

    @JsonProperty("lo")
    double dayLow;

    @JsonProperty("opn")
    double openPrice;

    @JsonProperty("opn")
    double openTradePrice;

    @JsonProperty("pchg")
    String changePercentage;

    @JsonProperty("pchg_sign")
    ChangeSign changePercentageSign;

    @JsonProperty("pcls")
    double priorDayClose;

    @JsonProperty("phi")
    double priorDayHigh;

    @JsonProperty("plo")
    double priorDayLow;

    @JsonProperty("pr_date")
    LocalDate dateOfPriorTradeDay;

    @JsonProperty("popn")
    double priorDayOpen;

    @JsonProperty("prchg")
    double priorDayChange;

    @JsonProperty("pvol")
    int priorDayVolume;

    @JsonProperty("secclass")
    Quote.SecurityClass securityClass;

    @JsonProperty("sesn")
    Quote.TradingSession tradingSession;

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("tcond")
    Quote.TradeCondition tradeCondition;

    @JsonProperty("timestamp")
    String timeStamp;

    @JsonProperty("tr_num")
    int tradeCountSinceOpen;

    @JsonProperty("tradetick")
    char tradeDirection;

    @JsonProperty("trend")
    String trendOf10LastTicks;

    @JsonProperty("vl")
    int cumulativeVolume;

    @JsonProperty("vwap")
    double volumeWeightedAveragePrice;

    @JsonProperty("wk52hi")
    double week52high;

    @JsonProperty("wk52hidate")
    @JsonDeserialize(using = SquishedDateDeserializer.class)
    LocalDate week52highDate;

    @JsonProperty("wk52lodate")
    @JsonDeserialize(using = SquishedDateDeserializer.class)
    LocalDate week52lowDate;

    @JsonProperty("wk52lo")
    double week52low;

    @JsonProperty("datetime")
    DateTime dateTime;

    @JsonProperty("sesn_vl")
    int sessionVolume;

    @JsonProperty("qcond")
    String quoteConditionCode;

}
