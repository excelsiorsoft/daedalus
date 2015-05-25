/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import org.joda.time.DateTime;

/**
 * TK API allows to subscribe to streams, one of them is Trades
 * This object represents  trade event that happens in the market.
 */
public class StreamTradeEvent extends TradeKingObject {

    private String symbol;
    private String exchangeCode;
    private double lastPrice;
    private int volume;
    private int cumulativeVolume;
    private double volumeWeightedAveragePrice;
    private DateTime dateTime;
    private int timeStamp;
    private String tradeCondition;

    public StreamTradeEvent() {
    }

    public StreamTradeEvent(String symbol, String exchangeCode, double lastPrice, int volume, int cumulativeVolume, double volumeWeightedAveragePrice, DateTime dateTime, int timeStamp, String tradeCondition) {
        this.symbol = symbol;
        this.exchangeCode = exchangeCode;
        this.lastPrice = lastPrice;
        this.volume = volume;
        this.cumulativeVolume = cumulativeVolume;
        this.volumeWeightedAveragePrice = volumeWeightedAveragePrice;
        this.dateTime = dateTime;
        this.timeStamp = timeStamp;
        this.tradeCondition = tradeCondition;
    }

    /**
     * Symbol from data provider
     *
     * @return string instrument symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * eExchange code
     *
     * @return String exchange code description
     */
    public String getExchangeCode() {
        return exchangeCode;
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
     * Volume
     *
     * @return volume number
     */
    public int getVolume() {
        return volume;
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
     * Volume weighted average price
     *
     * @return dollar figure
     */
    public double getVolumeWeightedAveragePrice() {
        return volumeWeightedAveragePrice;
    }

    /**
     * Date and time
     *
     * @return DateTime object
     */
    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Timestamp
     *
     * @return integer timestamp
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    /**
     * Trade condition code – (H) halted or (R) resumed. (docs)
     * However, returns integers for now that are not documented
     *
     * @return String trade condition
     */
    public String getTradeCondition() {
        return tradeCondition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StreamTradeEvent)) return false;

        StreamTradeEvent that = (StreamTradeEvent) o;

        if (cumulativeVolume != that.cumulativeVolume) return false;
        if (Double.compare(that.lastPrice, lastPrice) != 0) return false;
        if (timeStamp != that.timeStamp) return false;
        if (volume != that.volume) return false;
        if (Double.compare(that.volumeWeightedAveragePrice, volumeWeightedAveragePrice) != 0) return false;
        if (!dateTime.equals(that.dateTime)) return false;
        if (!exchangeCode.equals(that.exchangeCode)) return false;
        if (!symbol.equals(that.symbol)) return false;
        if (!tradeCondition.equals(that.tradeCondition)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol.hashCode();
        result = 31 * result + exchangeCode.hashCode();
        temp = Double.doubleToLongBits(lastPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + volume;
        result = 31 * result + cumulativeVolume;
        temp = Double.doubleToLongBits(volumeWeightedAveragePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + timeStamp;
        result = 31 * result + tradeCondition.hashCode();
        return result;
    }
}
