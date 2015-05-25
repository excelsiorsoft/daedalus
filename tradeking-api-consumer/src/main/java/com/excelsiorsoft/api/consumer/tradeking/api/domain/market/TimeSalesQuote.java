/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Time and sales quote data. This is a point at a time that shows momentum of the stock.
 */
public class TimeSalesQuote extends TradeKingObject {

    private LocalDate date;
    private DateTime intervalStartTime;
    private double highPrice;
    private double lowPrice;
    private int volume;
    private double lastPrice;
    private double openTradePrice;
    private int timestamp;
    private int cumulativeVolume;

    public TimeSalesQuote() {
    }


    public TimeSalesQuote(LocalDate date, DateTime intervalStartTime, double highPrice, double lowPrice, int volume, double lastPrice, double openTradePrice, int timestamp, int cumulativeVolume) {
        this.date = date;
        this.intervalStartTime = intervalStartTime;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.volume = volume;
        this.lastPrice = lastPrice;
        this.openTradePrice = openTradePrice;
        this.timestamp = timestamp;
        this.cumulativeVolume = cumulativeVolume;
    }

    /**
     * Date and time
     *
     * @return DateTime object
     */
    public DateTime getIntervalStartTime() {
        return intervalStartTime;
    }

    /**
     * High Trade Price
     *
     * @return dollar figure
     */
    public double getHighPrice() {
        return highPrice;
    }

    /**
     * Low Trade Price
     *
     * @return dollar figure
     */
    public double getLowPrice() {
        return lowPrice;
    }

    /**
     * Volume of trades
     *
     * @return volume number
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Last trade price
     *
     * @return dollar figure
     */
    public double getLastPrice() {
        return lastPrice;
    }

    /**
     * Open trade price
     *
     * @return dollar figure
     */
    public double getOpenTradePrice() {
        return openTradePrice;
    }

    /**
     * Timestamp
     *
     * @return integer timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Cumulative volume
     *
     * @return volume number
     */
    public int getCumulativeVolume() {
        return cumulativeVolume;
    }

    /**
     * Date
     *
     * @return Date object
     */
    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSalesQuote)) return false;

        TimeSalesQuote that = (TimeSalesQuote) o;

        if (cumulativeVolume != that.cumulativeVolume) return false;
        if (Double.compare(that.highPrice, highPrice) != 0) return false;
        if (Double.compare(that.lastPrice, lastPrice) != 0) return false;
        if (Double.compare(that.lowPrice, lowPrice) != 0) return false;
        if (Double.compare(that.openTradePrice, openTradePrice) != 0) return false;
        if (timestamp != that.timestamp) return false;
        if (volume != that.volume) return false;
        if (!date.equals(that.date)) return false;
        if (!intervalStartTime.equals(that.intervalStartTime)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date.hashCode();
        result = 31 * result + intervalStartTime.hashCode();
        temp = Double.doubleToLongBits(highPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lowPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + volume;
        temp = Double.doubleToLongBits(lastPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(openTradePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + timestamp;
        result = 31 * result + cumulativeVolume;
        return result;
    }
}
