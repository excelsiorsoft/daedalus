/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Details about transaction including price, fees, quantities
 */
public class TransactionDetails extends TradeKingObject {

    private double commission;
    private String description;
    private double fee;
    private double price;
    private double quantity;
    private String source;
    private int side;
    private int accountType;
    private double SECFee;
    private TransactionSecurity transactionSecurity;
    private DateTime tradeDate;
    private DateTime settlementDate;
    private int transactionId;
    private String transactionType;

    public TransactionDetails() {
    }

    public TransactionDetails(double commission, String description, double fee, double price, double quantity, String source, int side, int accountType, double SECFee, TransactionSecurity transactionSecurity, DateTime tradeDate, DateTime settlementDate, int transactionId, String transactionType) {
        this.commission = commission;
        this.description = description;
        this.fee = fee;
        this.price = price;
        this.quantity = quantity;
        this.source = source;
        this.side = side;
        this.accountType = accountType;
        this.SECFee = SECFee;
        this.transactionSecurity = transactionSecurity;
        this.tradeDate = tradeDate;
        this.settlementDate = settlementDate;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
    }

    /**
     * Commission paid for transaction
     *
     * @return dollar figure
     */
    public double getCommission() {
        return commission;
    }

    /**
     * Description of transaction
     *
     * @return String Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Transaction fee. This is not the commission for trade.
     *
     * @return dollar figure
     */
    public double getFee() {
        return fee;
    }

    /**
     * Price of the unit of security
     *
     * @return dollar figure
     */
    public double getPrice() {
        return price;
    }

    /**
     * Quantity of the securities in transaction.
     *
     * @return quantity of securities. Negative means sell, positive means buy.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Source of transaction i.e. XCH as exchange, DIV as dividends etc...
     *
     * @return String description of source
     */
    public String getSource() {
        return source;
    }

    /**
     * SEC fee for transaction
     *
     * @return dollar figure
     */
    public double getSECFee() {
        return SECFee;
    }

    /**
     * Transaction Security
     *
     * @return Object with Security details
     */
    public TransactionSecurity getTransactionSecurity() {
        return transactionSecurity;
    }

    /**
     * Date when trade happened
     *
     * @return Date object. Accurate to the day, which means hours are midnight -05:00
     */
    public DateTime getTradeDate() {
        return tradeDate;
    }

    /**
     * Date transaction was settled.
     *
     * @return Date object. Accurate to the day, which means hours are midnight -05:00
     */
    public DateTime getSettlementDate() {
        return settlementDate;
    }

    /**
     * Transaction Id (for some reason always seems to be 10)
     *
     * @return integer transaction Id
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Transaction type. Null for trades, description of transaction for different types
     *
     * @return transaction type description
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Side. This is not documented, but seems like:
     * Buy Transactions: Side 1,
     * Sell - Side 2
     * Others - Side 0
     *
     * @return integer side id
     */
    public int getSide() {
        return side;
    }

    /**
     * Account TYPE. Unfortunately this is not documented in api docs.
     *
     * @return integer account type
     */
    public int getAccountType() {
        return accountType;
    }

    /**
     * This method is needed as TK sometimes returns description as String, sometimes as Array
     *
     * @param description description fed by Jackson
     */
    @SuppressWarnings("unchecked")
    public void setDescription(Object description) {
        if (description.getClass() == String.class) {
            this.description = (String) description;
        } else {
            List<String> descriptionList = (List<String>) description;
            StringBuilder stringDescription = new StringBuilder();
            for (String descriptionPart : descriptionList) {
                stringDescription.append(descriptionPart).append(" ");
            }
            this.description = stringDescription.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDetails)) return false;

        TransactionDetails that = (TransactionDetails) o;

        if (Double.compare(that.SECFee, SECFee) != 0) return false;
        if (accountType != that.accountType) return false;
        if (Double.compare(that.commission, commission) != 0) return false;
        if (Double.compare(that.fee, fee) != 0) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.quantity, quantity) != 0) return false;
        if (side != that.side) return false;
        if (transactionId != that.transactionId) return false;
        if (!description.equals(that.description)) return false;
        if (settlementDate != null ? !settlementDate.equals(that.settlementDate) : that.settlementDate != null)
            return false;
        if (!source.equals(that.source)) return false;
        if (!tradeDate.equals(that.tradeDate)) return false;
        if (!transactionSecurity.equals(that.transactionSecurity)) return false;
        if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(commission);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(fee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quantity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + source.hashCode();
        result = 31 * result + side;
        result = 31 * result + accountType;
        temp = Double.doubleToLongBits(SECFee);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + transactionSecurity.hashCode();
        result = 31 * result + tradeDate.hashCode();
        result = 31 * result + (settlementDate != null ? settlementDate.hashCode() : 0);
        result = 31 * result + transactionId;
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        return result;
    }
}
