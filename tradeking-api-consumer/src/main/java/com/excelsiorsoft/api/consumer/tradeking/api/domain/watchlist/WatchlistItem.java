/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.domain.watchlist;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * A list of WatchlistItem is received once we pull a specific watchlist. It has very brief data.
 */
public class WatchlistItem extends TradeKingObject {

    private double costBasis;
    private double quantity;
    private String symbol;

    public WatchlistItem() {
    }

    public WatchlistItem(double costBasis, double quantity, String symbol) {
        this.costBasis = costBasis;
        this.quantity = quantity;
        this.symbol = symbol;
    }

    /**
     * Gets cost basis of instrument added to watchlist. For now we cannot specify cost basis when adding the item to watchlist
     * so this will be 0.0.
     *
     * @return cost basis of the instrument added to watchlist.
     */
    public double getCostBasis() {
        return costBasis;
    }

    /**
     * Gets quantity of instrument added to watchlist. For now we cannot specify quantity when adding the item to watchlist
     * so this will be 0.
     *
     * @return quantity of the instrument added to watchlist.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @return String instrument symbol
     */
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WatchlistItem)) return false;

        WatchlistItem that = (WatchlistItem) o;

        if (Double.compare(that.costBasis, costBasis) != 0) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (!symbol.equals(that.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(costBasis);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + symbol.hashCode();
        return result;
    }
}
