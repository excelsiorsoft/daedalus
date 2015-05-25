/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * A detailed data about a single holding line
 */
public class AccountHoldingEntry extends TradeKingObject {

    private HoldingType holdingType;
    private double costBasis;
    private HoldingDisplayData displayData;
    private double gainLoss;
    private FinancialInstrument instrument;
    private double marketValue;
    private double marketValueChange;
    private double price;
    private double purchasePrice;
    private double quantity;
    private HoldingsQuote holdingsQuote;
    private double SODCostBasis;

    public AccountHoldingEntry() {
    }

    public AccountHoldingEntry(HoldingType holdingType, double costBasis, HoldingDisplayData displayData, double gainLoss, FinancialInstrument instrument, double marketValue, double marketValueChange, double price, double purchasePrice, double quantity, HoldingsQuote holdingsQuote, double SODCostBasis) {
        this.holdingType = holdingType;
        this.costBasis = costBasis;
        this.displayData = displayData;
        this.gainLoss = gainLoss;
        this.instrument = instrument;
        this.marketValue = marketValue;
        this.marketValueChange = marketValueChange;
        this.price = price;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.holdingsQuote = holdingsQuote;
        this.SODCostBasis = SODCostBasis;
    }

    /**
     * Holdings attribute for where asset as held
     *
     * @return enum value, like short, long, cash...
     */
    public HoldingType getHoldingType() {
        return holdingType;
    }

    /**
     * Holding cost basis.
     *
     * @return a dollar figure
     */
    public double getCostBasis() {
        return costBasis;
    }

    /**
     * Holding display data has all the necessary fields about holding (like $ figures, symbol, description)
     * formatted display friendly, with $ signs etc.
     *
     * @return Display Data Object
     */
    public HoldingDisplayData getDisplayData() {
        return displayData;
    }

    /**
     * Holding gain/loss overall
     *
     * @return a dollar figure
     */
    public double getGainLoss() {
        return gainLoss;
    }

    /**
     * Financial instrument meta data object that includes values like security type, CUSIP, etc.
     *
     * @return Financial Instrument Object
     */
    public FinancialInstrument getInstrument() {
        return instrument;
    }

    /**
     * Holding market value
     *
     * @return a dollar figure
     */
    public double getMarketValue() {
        return marketValue;
    }

    /**
     * Holding market value change
     *
     * @return a dollar figure
     */
    public double getMarketValueChange() {
        return marketValueChange;
    }

    /**
     * Instrument price
     *
     * @return a dollar figure
     */
    public double getPrice() {
        return price;
    }

    /**
     * Holding purchase price
     *
     * @return a dollar figure
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Holding quantity
     *
     * @return quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * A very brief quote object that is returned within Holdings entry
     *
     * @return HoldingsQuote object
     */
    public HoldingsQuote getQuote() {
        return holdingsQuote;
    }

    /**
     * Start of Day Cost Basis
     *
     * @return start of day cost basis in dollars
     */
    public double getSODCostBasis() {
        return SODCostBasis;
    }

    public enum HoldingType {
        CASH(1), MARGIN_LONG(2), MARGIN_SHORT(5);
        private final Integer value;

        HoldingType(int i) {
            this.value = i;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHoldingEntry)) return false;

        AccountHoldingEntry that = (AccountHoldingEntry) o;

        if (Double.compare(that.SODCostBasis, SODCostBasis) != 0) return false;
        if (Double.compare(that.costBasis, costBasis) != 0) return false;
        if (Double.compare(that.gainLoss, gainLoss) != 0) return false;
        if (Double.compare(that.marketValue, marketValue) != 0) return false;
        if (Double.compare(that.marketValueChange, marketValueChange) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.purchasePrice, purchasePrice) != 0) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (!displayData.equals(that.displayData)) return false;
        if (holdingType != that.holdingType) return false;
        if (!holdingsQuote.equals(that.holdingsQuote)) return false;
        if (!instrument.equals(that.instrument)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = holdingType.hashCode();
        temp = Double.doubleToLongBits(costBasis);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + displayData.hashCode();
        temp = Double.doubleToLongBits(gainLoss);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + instrument.hashCode();
        temp = Double.doubleToLongBits(marketValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(marketValueChange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(purchasePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + holdingsQuote.hashCode();
        temp = Double.doubleToLongBits(SODCostBasis);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
