/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Information about current market status
 */
public class MarketStatus extends TradeKingObject {

    private LocalDateTime date;
    private MarketCondition currentStatus;
    private MarketCondition nextStatus;
    private LocalTime nextMarketStatusTime;
    private String statusMessage;
    private int serverUnixTimeStamp;


    public MarketStatus() {

    }

    public MarketStatus(LocalDateTime date, MarketCondition currentStatus, MarketCondition nextStatus, LocalTime nextMarketStatusTime, String statusMessage, int serverUnixTimeStamp) {
        this.date = date;
        this.currentStatus = currentStatus;
        this.nextStatus = nextStatus;
        this.nextMarketStatusTime = nextMarketStatusTime;
        this.statusMessage = statusMessage;
        this.serverUnixTimeStamp = serverUnixTimeStamp;
    }

    /**
     * Current market date
     *
     * @return LocalDateTime Object
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Current market status as “pre”, “open”, “after”, or “closed”.
     *
     * @return MarketCondition enum value
     */
    public MarketCondition getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Next market status as “pre”, “open”, “after”, or “closed”.
     *
     * @return MarketCondition enum value
     */
    public MarketCondition getNextStatus() {
        return nextStatus;
    }

    /**
     * Timestamp of next market session change.
     *
     * @return String time, like "16:00:00"
     */
    public LocalTime getNextMarketStatusTime() {
        return nextMarketStatusTime;
    }

    /**
     * Message of market status as either open or closed.
     *
     * @return String message, like "Market is open"
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Unix Time Stamp. Could be good for calibrating your server with TK
     *
     * @return integer timestamp
     */
    public int getServerUnixTimeStamp() {
        return serverUnixTimeStamp;
    }

    /**
     * Market condition
     */
    public enum MarketCondition {

        PRE_OPEN("pre"), OPEN("open"), AFTER_MARKET("after"), CLOSED("closed");

        private final String name;

        private static final Map<String, MarketCondition> map = new HashMap<>();

        static {
            for (MarketCondition type : MarketCondition.values()) {
                map.put(type.name, type);
            }
        }

        MarketCondition(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static MarketCondition fromString(String name) {
            if (map.containsKey(name)) {
                return map.get(name);
            }
            throw new NoSuchElementException(name + "not found");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MarketStatus)) return false;

        MarketStatus that = (MarketStatus) o;

        if (serverUnixTimeStamp != that.serverUnixTimeStamp) return false;
        if (currentStatus != that.currentStatus) return false;
        if (!date.equals(that.date)) return false;
        if (!nextMarketStatusTime.equals(that.nextMarketStatusTime)) return false;
        if (nextStatus != that.nextStatus) return false;
        if (!statusMessage.equals(that.statusMessage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + currentStatus.hashCode();
        result = 31 * result + nextStatus.hashCode();
        result = 31 * result + nextMarketStatusTime.hashCode();
        result = 31 * result + statusMessage.hashCode();
        result = 31 * result + serverUnixTimeStamp;
        return result;
    }
}
