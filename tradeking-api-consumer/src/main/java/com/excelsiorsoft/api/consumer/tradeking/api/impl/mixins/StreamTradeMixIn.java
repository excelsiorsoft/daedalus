package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.DateTime;


/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

@JsonRootName("trade")
public class StreamTradeMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("exch")
    String exchangeCode;

    @JsonProperty("last")
    double lastPrice;

    @JsonProperty("vl")
    int volume;

    @JsonProperty("cvol")
    int cumulativeVolume;

    @JsonProperty("vwap")
    double volumeWeightedAveragePrice;

    @JsonProperty("datetime")
    DateTime dateTime;

    @JsonProperty("tcond")
    String tradeCondition;

    @JsonProperty("timestamp")
    int timeStamp;


}
