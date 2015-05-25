/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * Holds data about buying power for stocks and options
 */
public class BuyingPower extends TradeKingObject {

    private double cashAvailableForWithdrawal;
    private double equityPercentage;
    private double options;
    private double sodOptions;
    private double sodStock;
    private double stock;

    public BuyingPower() {
    }

    public BuyingPower(double cashAvailableForWithdrawal, double equityPercentage, double options, double sodOptions, double sodStock, double stock) {
        this.cashAvailableForWithdrawal = cashAvailableForWithdrawal;
        this.equityPercentage = equityPercentage;
        this.options = options;
        this.sodOptions = sodOptions;
        this.sodStock = sodStock;
        this.stock = stock;
    }

    /**
     * Cash available for withdrawal (cash and margin accounts only, n/a for retirement accounts)
     *
     * @return dollar figure of cash available for withdrawal
     */
    public double getCashAvailableForWithdrawal() {
        return cashAvailableForWithdrawal;
    }

    /**
     * Percentage of equity (margin accounts only)
     *
     * @return percentage figure
     */
    public double getEquityPercentage() {
        return equityPercentage;
    }

    /**
     * Options buying power.
     *
     * @return a dollar figure
     */
    public double getOptions() {
        return options;
    }

    /**
     * Start of day options buying power
     *
     * @return a dollar figure
     */
    public double getSodOptions() {
        return sodOptions;
    }

    /**
     * Start of day stock buying power
     *
     * @return a dollar figure
     */
    public double getSodStock() {
        return sodStock;
    }

    /**
     * Stock buying power
     *
     * @return a dollar figure
     */
    public double getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuyingPower)) return false;

        BuyingPower that = (BuyingPower) o;

        if (Double.compare(that.cashAvailableForWithdrawal, cashAvailableForWithdrawal) != 0) return false;
        if (Double.compare(that.equityPercentage, equityPercentage) != 0) return false;
        if (Double.compare(that.options, options) != 0) return false;
        if (Double.compare(that.sodOptions, sodOptions) != 0) return false;
        if (Double.compare(that.sodStock, sodStock) != 0) return false;
        if (Double.compare(that.stock, stock) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(cashAvailableForWithdrawal);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(equityPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(options);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sodOptions);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sodStock);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stock);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
