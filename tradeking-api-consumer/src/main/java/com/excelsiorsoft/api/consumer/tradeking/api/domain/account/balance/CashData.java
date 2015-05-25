/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * Holds data about cash in the account
 */
public class CashData extends TradeKingObject {

    private double accruedInterest;
    private double cash;
    private double marginBalance;
    private double cashAvailable;
    private double moneyMarketFund;
    private double total;
    private double unclearedDeposits;
    private double unsettledFunds;
    private double yield;

    public CashData() {
    }

    public CashData(double accruedInterest, double cash, double marginBalance, double cashAvailable, double moneyMarketFund, double total, double unclearedDeposits, double unsettledFunds, double yield) {
        this.accruedInterest = accruedInterest;
        this.cash = cash;
        this.marginBalance = marginBalance;
        this.cashAvailable = cashAvailable;
        this.moneyMarketFund = moneyMarketFund;
        this.total = total;
        this.unclearedDeposits = unclearedDeposits;
        this.unsettledFunds = unsettledFunds;
        this.yield = yield;
    }

    /**
     * Amount of any accrued interest on the account
     *
     * @return dollar figure
     */
    public double getAccruedInterest() {
        return accruedInterest;
    }

    /**
     * Cash
     *
     * @return dollar figure
     */
    public double getCash() {
        return cash;
    }

    /**
     * Margin balance (- indicates debit balance, + indicates credit balance)
     *
     * @return dollar figure
     */
    public double getMarginBalance() {
        return marginBalance;
    }

    /**
     * Cash available
     *
     * @return dollar figure
     */
    public double getCashAvailable() {
        return cashAvailable;
    }

    /**
     * Money market fund (mmf)
     *
     * @return dollar figure
     */
    public double getMoneyMarketFund() {
        return moneyMarketFund;
    }

    /**
     * Total cash balance
     *
     * @return dollar figure
     */
    public double getTotal() {
        return total;
    }

    /**
     * Uncleared deposits.
     *
     * @return dollar figure
     */
    public double getUnclearedDeposits() {
        return unclearedDeposits;
    }

    /**
     * Unsettled Funds
     *
     * @return dollar figure
     */
    public double getUnsettledFunds() {
        return unsettledFunds;
    }

    /**
     * Yield
     *
     * @return dollar figure (?)
     */
    public double getYield() {
        return yield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CashData)) return false;

        CashData cash1 = (CashData) o;

        if (Double.compare(cash1.accruedInterest, accruedInterest) != 0) return false;
        if (Double.compare(cash1.cash, cash) != 0) return false;
        if (Double.compare(cash1.cashAvailable, cashAvailable) != 0) return false;
        if (Double.compare(cash1.marginBalance, marginBalance) != 0) return false;
        if (Double.compare(cash1.moneyMarketFund, moneyMarketFund) != 0) return false;
        if (Double.compare(cash1.total, total) != 0) return false;
        if (Double.compare(cash1.unclearedDeposits, unclearedDeposits) != 0) return false;
        if (Double.compare(cash1.unsettledFunds, unsettledFunds) != 0) return false;
        if (Double.compare(cash1.yield, yield) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(accruedInterest);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cash);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(marginBalance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cashAvailable);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(moneyMarketFund);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(unclearedDeposits);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(unsettledFunds);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yield);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
