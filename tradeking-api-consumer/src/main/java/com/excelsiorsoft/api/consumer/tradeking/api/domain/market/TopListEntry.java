/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * Top list entry. Top List are "biggest volumes today", "biggest gainers, losers", etc.
 */
public class TopListEntry extends TradeKingObject {

    private double change;
    private ChangeSign changeDirection;
    private double lastTradePrice;
    private String companyName;
    private String percentageChange;
    private double priorDayClosePrice;
    private int rankInTheList;
    private String ticker;
    private int volume;

    public TopListEntry() {
    }

    public TopListEntry(double change, ChangeSign changeDirection, double lastTradePrice, String companyName, String percentageChange, double priorDayClosePrice, int rankInTheList, String ticker, int volume) {
        this.change = change;
        this.changeDirection = changeDirection;
        this.lastTradePrice = lastTradePrice;
        this.companyName = companyName;
        this.percentageChange = percentageChange;
        this.priorDayClosePrice = priorDayClosePrice;
        this.rankInTheList = rankInTheList;
        this.ticker = ticker;
        this.volume = volume;
    }

    /**
     * Change since prior day close (cl)
     *
     * @return dollar figure
     */
    public double getChange() {
        return change;
    }

    /**
     * Change sign: up, down, did not change.
     *
     * @return enum with a change value
     */
    public ChangeSign getChangeDirection() {
        return changeDirection;
    }

    /**
     * Last trade price
     *
     * @return dollar figure
     */
    public double getLastTradePrice() {
        return lastTradePrice;
    }

    /**
     * Company name
     *
     * @return String company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Percentage change from prior day close
     *
     * @return percent String
     */
    public String getPercentageChange() {
        return percentageChange;
    }

    /**
     * Prior day close
     *
     * @return dollar figure
     */
    public double getPriorDayClosePrice() {
        return priorDayClosePrice;
    }

    /**
     * Rank within list returned
     *
     * @return integer rank number
     */
    public int getRankInTheList() {
        return rankInTheList;
    }

    /**
     * Ticker symbol
     *
     * @return String ticker symbol
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Today's cumulative volume
     *
     * @return integer volume number
     */
    public int getVolume() {
        return volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopListEntry)) return false;

        TopListEntry that = (TopListEntry) o;

        if (Double.compare(that.change, change) != 0) return false;
        if (Double.compare(that.lastTradePrice, lastTradePrice) != 0) return false;
        if (Double.compare(that.priorDayClosePrice, priorDayClosePrice) != 0) return false;
        if (rankInTheList != that.rankInTheList) return false;
        if (volume != that.volume) return false;
        if (changeDirection != that.changeDirection) return false;
        if (!companyName.equals(that.companyName)) return false;
        if (!percentageChange.equals(that.percentageChange)) return false;
        if (!ticker.equals(that.ticker)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(change);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + changeDirection.hashCode();
        temp = Double.doubleToLongBits(lastTradePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + companyName.hashCode();
        result = 31 * result + percentageChange.hashCode();
        temp = Double.doubleToLongBits(priorDayClosePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + rankInTheList;
        result = 31 * result + ticker.hashCode();
        result = 31 * result + volume;
        return result;
    }
}
