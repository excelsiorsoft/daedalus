/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import org.joda.time.DateTime;

/**
 * TK API allows to subscribe to streams, one of them is Quotes
 * This object represents quote event that happens in the market.
 */
public class StreamQuoteEvent extends TradeKingObject {

    private double askPrice;
    private long askLatestSizeHundreds;
    private double bidPrice;
    private long bidLatestSizeHundreds;
    private Integer bidTick;
    private DateTime dateTime;
    private String exchangeCode;
    private String conditionCode;
    private String symbol;
    private int timeStamp;

    public StreamQuoteEvent() {
    }

    public StreamQuoteEvent(double askPrice, long askLatestSizeHundreds, double bidPrice, long bidLatestSize, Integer bidTick, DateTime dateTime, String exchangeCode, String conditionCode, String symbol, int timeStamp) {
        this.askPrice = askPrice;
        this.askLatestSizeHundreds = askLatestSizeHundreds;
        this.bidPrice = bidPrice;
        this.bidLatestSizeHundreds = bidLatestSize;
        this.bidTick = bidTick;
        this.dateTime = dateTime;
        this.exchangeCode = exchangeCode;
        this.conditionCode = conditionCode;
        this.symbol = symbol;
        this.timeStamp = timeStamp;
    }

    /**
     * Ask Price
     *
     * @return dollar figure
     */
    public double getAskPrice() {
        return askPrice;
    }

    /**
     * Size of latest ask (in 100's)
     *
     * @return integer size in hundreds
     */
    public long getAskLatestSizeHundreds() {
        return askLatestSizeHundreds;
    }

    /**
     * Bid Price
     *
     * @return dollar figure
     */
    public double getBidPrice() {
        return bidPrice;
    }

    /**
     * Size of latest bid (in 100's)
     *
     * @return integer size in hundreds
     */
    public long getBidLatestSizeHundreds() {
        return bidLatestSizeHundreds;
    }

    /**
     * "An indication of whether the latest bid price is higher, lower or the same as the previous bid"
     * TK returns 1, 3 or null. No documentation provided. Seems like 1 is down, 3 is up
     *
     * @return bid tick id if provided, otherwise {@code null}
     */
    public Integer getBidTick() {
        return bidTick;
    }

    /**
     * Date and time of the quote
     *
     * @return DateTime object
     */
    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Exchange code
     *
     * @return String exchange code description
     */
    public String getExchangeCode() {
        return exchangeCode;
    }

    /**
     * Condition of quote, i.e. "Regular, two-sided open quote automated"
     *
     * @return String condition description
     */
    public String getConditionCode() {
        return conditionCode;
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
     * Timestamp
     *
     * @return integer timestamp
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StreamQuoteEvent)) return false;

        StreamQuoteEvent that = (StreamQuoteEvent) o;

        if (askLatestSizeHundreds != that.askLatestSizeHundreds) return false;
        if (Double.compare(that.askPrice, askPrice) != 0) return false;
        if (bidLatestSizeHundreds != that.bidLatestSizeHundreds) return false;
        if (Double.compare(that.bidPrice, bidPrice) != 0) return false;
        if (timeStamp != that.timeStamp) return false;
        if (bidTick != null ? !bidTick.equals(that.bidTick) : that.bidTick != null) return false;
        if (!conditionCode.equals(that.conditionCode)) return false;
        if (!dateTime.equals(that.dateTime)) return false;
        if (!exchangeCode.equals(that.exchangeCode)) return false;
        if (!symbol.equals(that.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(askPrice);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (askLatestSizeHundreds ^ (askLatestSizeHundreds >>> 32));
        temp = Double.doubleToLongBits(bidPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (bidLatestSizeHundreds ^ (bidLatestSizeHundreds >>> 32));
        result = 31 * result + (bidTick != null ? bidTick.hashCode() : 0);
        result = 31 * result + dateTime.hashCode();
        result = 31 * result + exchangeCode.hashCode();
        result = 31 * result + conditionCode.hashCode();
        result = 31 * result + symbol.hashCode();
        result = 31 * result + timeStamp;
        return result;
    }
}
