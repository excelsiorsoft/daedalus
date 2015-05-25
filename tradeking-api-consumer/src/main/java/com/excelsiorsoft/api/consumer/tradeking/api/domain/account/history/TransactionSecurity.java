/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

/**
 * Brief details about transaction security itself
 */
public class TransactionSecurity extends TradeKingObject {

    private String CUSIP;
    private String id;
    private String securityType;
    private String symbol;

    public TransactionSecurity() {
    }

    public TransactionSecurity(String CUSIP, String id, String securityType, String symbol) {
        this.CUSIP = CUSIP;
        this.id = id;
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
     * Transaction Id. Seems to be the same as CUSIP without the last digit.
     *
     * @return String transaction ID
     */
    public String getId() {
        return id;
    }


    /**
     * Security Type
     *
     * @return String Security Type
     */
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Instrument Symbol
     *
     * @return String symbol
     */
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionSecurity)) return false;

        TransactionSecurity that = (TransactionSecurity) o;

        if (!CUSIP.equals(that.CUSIP)) return false;
        if (!id.equals(that.id)) return false;
        if (!securityType.equals(that.securityType)) return false;
        if (!symbol.equals(that.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = CUSIP.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + securityType.hashCode();
        result = 31 * result + symbol.hashCode();
        return result;
    }
}
