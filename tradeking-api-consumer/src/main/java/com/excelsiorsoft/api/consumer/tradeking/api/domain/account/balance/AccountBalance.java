/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * Detailed balance information for the account
 */
public class AccountBalance extends TradeKingObject {

    private String accountId;
    private double accountValue;
    private double backOfficeHouseExcess;
    private BuyingPower buyingPowerSummary;
    private double fedCall;
    private double houseCall;
    private double maintenanceExcess;
    private CashData cashSummary;
    private Securities securitiesSummary;

    public AccountBalance() {
    }

    public AccountBalance(String accountId, double accountValue, double backOfficeHouseExcess, BuyingPower buyingPowerSummary, double fedCall, double houseCall, double maintenanceExcess, CashData cashSummary, Securities securitiesSummary) {
        this.accountId = accountId;
        this.accountValue = accountValue;
        this.backOfficeHouseExcess = backOfficeHouseExcess;
        this.buyingPowerSummary = buyingPowerSummary;
        this.fedCall = fedCall;
        this.houseCall = houseCall;
        this.maintenanceExcess = maintenanceExcess;
        this.cashSummary = cashSummary;
        this.securitiesSummary = securitiesSummary;
    }

    /**
     * Account Id
     *
     * @return String account Id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Account value
     *
     * @return a figure in dollars
     */
    public double getAccountValue() {
        return accountValue;
    }

    /**
     * Back Office House Excess. TK does not specify what it is in api docs.
     *
     * @return a figure in dollars
     */
    public double getBackOfficeHouseExcess() {
        return backOfficeHouseExcess;
    }

    /**
     * Buying Power.
     *
     * @return Buying Power Object
     */
    public BuyingPower getBuyingPowerSummary() {
        return buyingPowerSummary;
    }

    /**
     * Value of any fed call on account
     *
     * @return a figure in dollars
     */
    public double getFedCall() {
        return fedCall;
    }

    /**
     * Value of any house call
     *
     * @return a figure in dollars
     */
    public double getHouseCall() {
        return houseCall;
    }

    /**
     * Maintenance Excess. The difference between your equity and margin requirement is the maintenance excess, and
     * represents the equity available to support new trades. Please note that any open orders reduce maintenance excess.
     * (http://www.questrade.com/pricing/margin_centre)
     *
     * @return a figure in dollars
     */
    public double getMaintenanceExcess() {
        return maintenanceExcess;
    }

    /**
     * Data about cash in the account
     *
     * @return Cash Object
     */
    public CashData getCashSummary() {
        return cashSummary;
    }

    /**
     * Detailed data about securities in the account
     *
     * @return Securities Object
     */
    public Securities getSecuritiesSummary() {
        return securitiesSummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountBalance)) return false;

        AccountBalance that = (AccountBalance) o;

        if (Double.compare(that.accountValue, accountValue) != 0) return false;
        if (Double.compare(that.backOfficeHouseExcess, backOfficeHouseExcess) != 0) return false;
        if (Double.compare(that.fedCall, fedCall) != 0) return false;
        if (Double.compare(that.houseCall, houseCall) != 0) return false;
        if (Double.compare(that.maintenanceExcess, maintenanceExcess) != 0) return false;
        if (!accountId.equals(that.accountId)) return false;
        if (!buyingPowerSummary.equals(that.buyingPowerSummary)) return false;
        if (!cashSummary.equals(that.cashSummary)) return false;
        if (!securitiesSummary.equals(that.securitiesSummary)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountId.hashCode();
        temp = Double.doubleToLongBits(accountValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(backOfficeHouseExcess);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + buyingPowerSummary.hashCode();
        temp = Double.doubleToLongBits(fedCall);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(houseCall);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maintenanceExcess);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cashSummary.hashCode();
        result = 31 * result + securitiesSummary.hashCode();
        return result;
    }
}
