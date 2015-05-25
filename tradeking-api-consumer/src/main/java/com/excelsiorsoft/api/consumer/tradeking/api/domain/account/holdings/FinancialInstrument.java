/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * This is part of holding entry, that holds metadata about security
 */
public class FinancialInstrument extends TradeKingObject {

    private String CUSIP;
    private String description;
    private double factor;
    private String securityType;
    private String symbol;

    public FinancialInstrument() {
    }

    public FinancialInstrument(String CUSIP, String description, double factor, String securityType, String symbol) {
        this.CUSIP = CUSIP;
        this.description = description;
        this.factor = factor;
        this.securityType = securityType;
        this.symbol = symbol;
    }

    /**
     * A CUSIP is a nine-character alphanumeric code that identifies a North American financial security for the purposes
     * of facilitating clearing and settlement of trades. (Wikipedia)
     *
     * @return String CUSIP
     */
    public String getCUSIP() {
        return CUSIP;
    }

    /**
     * Description, Usually the company name of underlying security
     *
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Instrument factor
     *
     * @return instrument factor
     */
    public double getFactor() {
        return factor;
    }

    /**
     * Instrument security type (FIXML)
     *
     * @return security type
     */
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Instrument Symbol
     *
     * @return string symbol
     */
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinancialInstrument)) return false;

        FinancialInstrument that = (FinancialInstrument) o;

        if (Double.compare(that.factor, factor) != 0) return false;
        if (!CUSIP.equals(that.CUSIP)) return false;
        if (!description.equals(that.description)) return false;
        if (!securityType.equals(that.securityType)) return false;
        if (!symbol.equals(that.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = CUSIP.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(factor);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + securityType.hashCode();
        result = 31 * result + symbol.hashCode();
        return result;
    }
}
