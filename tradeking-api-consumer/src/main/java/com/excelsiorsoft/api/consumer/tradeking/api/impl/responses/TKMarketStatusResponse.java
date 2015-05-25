/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.MarketStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Map;

public class TKMarketStatusResponse extends TKResponse {

    private String error;

    @JsonIgnore
    private MarketStatus marketStatus;

    @JsonIgnore
    private String currentStatus;

    @JsonIgnore
    private String nextStatus;

    @JsonIgnore
    private LocalTime changeAt;

    @JsonIgnore
    private LocalDateTime date;

    @JsonProperty("message")
    private String message;

    @JsonProperty("unixtime")
    private int unixTime;

    /**
     * Deserializer does not understand the milliseconds, need to do it manually here
     *
     * @param dateResponse a String date from Json
     */
    @JsonSetter("date")
    public void setDate(String dateResponse) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        this.date = LocalDateTime.parse(dateResponse, formatter);
    }

    /**
     * This needs an explicit setter to unwrap the values from inside of "status" object
     *
     * @param statusResponse value from deserializer, in this case object "status" as a HashMap
     */
    @JsonSetter("status")
    public void setAccounts(Map statusResponse) {
        this.currentStatus = (String) statusResponse.get("current");
        this.nextStatus = (String) statusResponse.get("next");
        this.changeAt = LocalTime.parse((String) statusResponse.get("change_at"));
    }

    public String getError() {
        return error;
    }

    public MarketStatus getMarketStatus() {
        if (null == this.marketStatus) {
            this.marketStatus = new MarketStatus(this.date
                    , MarketStatus.MarketCondition.fromString(this.currentStatus)
                    , MarketStatus.MarketCondition.fromString(this.nextStatus)
                    , this.changeAt
                    , this.message
                    , this.unixTime);
        }

        return this.marketStatus;
    }
}
