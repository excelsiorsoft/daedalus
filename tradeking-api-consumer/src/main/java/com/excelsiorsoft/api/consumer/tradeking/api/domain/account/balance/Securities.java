/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * Detailed info about securities hold in the account
 */
public class Securities extends TradeKingObject {

    private double longOptions;
    private double longStocks;
    private double shortOptions;
    private double shortStocks;
    private double options;
    private double stocks;
    private double total;

    public Securities() {
    }

    public Securities(double longOptions, double longStocks, double shortOptions, double shortStocks, double options, double stocks, double total) {
        this.longOptions = longOptions;
        this.longStocks = longStocks;
        this.shortOptions = shortOptions;
        this.shortStocks = shortStocks;
        this.options = options;
        this.stocks = stocks;
        this.total = total;
    }

    /**
     * Total long options market value.
     *
     * @return dollar figure
     */
    public double getLongOptions() {
        return longOptions;
    }

    /**
     * Long stock market value
     *
     * @return dollar figure
     */
    public double getLongStocks() {
        return longStocks;
    }

    /**
     * Short option market value
     *
     * @return dollar figure
     */
    public double getShortOptions() {
        return shortOptions;
    }

    /**
     * Short stock market value
     *
     * @return dollar figure
     */
    public double getShortStocks() {
        return shortStocks;
    }

    /**
     * Total options market value
     *
     * @return dollar figure
     */
    public double getOptions() {
        return options;
    }

    /**
     * Total stock market value
     *
     * @return dollar figure
     */
    public double getStocks() {
        return stocks;
    }

    /**
     * Total market value (stock and option)
     *
     * @return dollar figure
     */
    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Securities)) return false;

        Securities that = (Securities) o;

        if (Double.compare(that.longOptions, longOptions) != 0) return false;
        if (Double.compare(that.longStocks, longStocks) != 0) return false;
        if (Double.compare(that.options, options) != 0) return false;
        if (Double.compare(that.shortOptions, shortOptions) != 0) return false;
        if (Double.compare(that.shortStocks, shortStocks) != 0) return false;
        if (Double.compare(that.stocks, stocks) != 0) return false;
        if (Double.compare(that.total, total) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(longOptions);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longStocks);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(shortOptions);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(shortStocks);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(options);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stocks);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
