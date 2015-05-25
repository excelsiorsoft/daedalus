/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.joda.time.DateTime;

@JsonRootName("quote")
public class StreamQuoteMixIn {

    @JsonProperty("ask")
    double askPrice;

    @JsonProperty("asksz")
    long askLatestSizeHundreds;

    @JsonProperty("bid")
    double bidPrice;

    @JsonProperty("bidsz")
    long bidLatestSizeHundreds;

    @JsonProperty("bidtick")
    Integer bidTick;

    @JsonProperty("datetime")
    DateTime dateTime;

    @JsonProperty("exch")
    String exchangeCode;

    @JsonProperty("qcond")
    String conditionCode;

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("timestamp")
    int timeStamp;
}
