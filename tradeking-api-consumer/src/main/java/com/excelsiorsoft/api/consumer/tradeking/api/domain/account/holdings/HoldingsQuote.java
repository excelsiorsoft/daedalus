/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * A very brief quote object that is returned within Holdings entry
 */
public class HoldingsQuote extends TradeKingObject {

    private double change;
    private double lastPrice;

    public HoldingsQuote() {
    }

    public HoldingsQuote(double change, double lastPrice) {
        this.change = change;
        this.lastPrice = lastPrice;
    }

    /**
     * Holding asset change for the day
     *
     * @return dollar amount change
     */
    public double getChange() {
        return change;
    }

    /**
     * Last Price of instrument
     *
     * @return dollar amount of last price
     */
    public double getLastPrice() {
        return lastPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoldingsQuote)) return false;

        HoldingsQuote that = (HoldingsQuote) o;

        if (Double.compare(that.change, change) != 0) return false;
        if (Double.compare(that.lastPrice, lastPrice) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(change);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lastPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
