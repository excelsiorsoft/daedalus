/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import java.util.Arrays;

/**
 * This class contains a list of the holding entries and a field that has a total
 */
public class AccountHoldings extends TradeKingObject {

    public AccountHoldings() {
    }

    public AccountHoldings(AccountHoldingEntry[] holdingsList, double totalSecurities) {
        this.holdingsList = holdingsList;
        this.totalSecurities = totalSecurities;
    }

    private AccountHoldingEntry[] holdingsList;
    private double totalSecurities;

    /**
     * @return list of holding entry objects
     */
    public AccountHoldingEntry[] getHoldingsList() {
        return holdingsList;
    }

    /**
     * Total of all securities. This is calculated by TK, not this library
     *
     * @return dollar figure
     */
    public double getTotalSecurities() {
        return totalSecurities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHoldings)) return false;

        AccountHoldings that = (AccountHoldings) o;

        if (Double.compare(that.totalSecurities, totalSecurities) != 0) return false;
        if (!Arrays.equals(holdingsList, that.holdingsList)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = Arrays.hashCode(holdingsList);
        temp = Double.doubleToLongBits(totalSecurities);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
