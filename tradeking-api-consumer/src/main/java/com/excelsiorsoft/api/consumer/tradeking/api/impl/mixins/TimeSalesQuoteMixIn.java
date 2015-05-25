/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class TimeSalesQuoteMixIn {

    @JsonProperty("date")
    LocalDate date;

    @JsonProperty("datetime")
    DateTime intervalStartTime;

    @JsonProperty("hi")
    double highPrice;

    @JsonProperty("lo")
    double lowPrice;

    @JsonProperty("incr_vl")
    int volume;

    @JsonProperty("last")
    double lastPrice;

    @JsonProperty("opn")
    double openTradePrice;

    @JsonProperty("timestamp")
    int timestamp;

    @JsonProperty("vl")
    int cumulativeVolume;

}
