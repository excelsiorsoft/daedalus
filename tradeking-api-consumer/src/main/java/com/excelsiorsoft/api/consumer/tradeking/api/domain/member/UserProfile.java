/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.member;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;

public class UserProfile extends TradeKingObject {

    private String UUID;
    private String accountSummaryDashboardCollapsed;
    private String firstName;
    private String lasName;
    private boolean hasMewMessage;
    private boolean realTimeStockQuote;
    private String emailAddress;
    private String FDICPaper;
    private boolean isGainsKeeper;
    private boolean isTradingPasswordUsed;
    private String FDICAgreement;

    public UserProfile() {

    }

    public UserProfile(String UUID, String accountSummaryDashboardCollapsed, String firstName, String lasName, boolean hasMewMessage, boolean realTimeStockQuote, String emailAddress, String FDICPaper, boolean isGainsKeeper, boolean isTradingPasswordUsed, String FDICAgreement) {
        this.UUID = UUID;
        this.accountSummaryDashboardCollapsed = accountSummaryDashboardCollapsed;
        this.firstName = firstName;
        this.lasName = lasName;
        this.hasMewMessage = hasMewMessage;
        this.realTimeStockQuote = realTimeStockQuote;
        this.emailAddress = emailAddress;
        this.FDICPaper = FDICPaper;
        this.isGainsKeeper = isGainsKeeper;
        this.isTradingPasswordUsed = isTradingPasswordUsed;
        this.FDICAgreement = FDICAgreement;
    }

    /**
     * Unique User ID. It could be an int, however, just to be future proof, in case TK decided to add a letter somewhere
     * it is String.
     *
     * @return returns String User Id.
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @return {@code True} if Summary dashboard is collapsed , otherwise{@code False}
     */
    public String getAccountSummaryDashboardCollapsed() {
        return accountSummaryDashboardCollapsed;
    }

    /**
     * @return String First Name of a user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return String Last Name of a user
     */
    public String getLasName() {
        return lasName;
    }

    /**
     * @return {@code True} if user has an unread message, otherwise {@code False}
     */
    public boolean isHasMewMessage() {
        return hasMewMessage;
    }

    /**
     * @return {@code True} if user has real time stock quotes, otherwise {@code False}
     */
    public boolean isRealTimeStockQuote() {
        return realTimeStockQuote;
    }

    /**
     * @return String user's e-mail address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Federal Deposit Insurance Corporation Paper.
     *
     * @return good question. In some cases even when it is signed, it returns "0"
     */
    public String getFDICPaper() {
        return FDICPaper;
    }

    /**
     * @return {@code True} if user is gains keeper, otherwise {@code False}
     */
    public boolean isGainsKeeper() {
        return isGainsKeeper;
    }

    /**
     * @return {@code True} if user a special password for trading, otherwise {@code False}
     */
    public boolean isTradingPasswordUsed() {
        return isTradingPasswordUsed;
    }

    /**
     * Federal Deposit Insurance Corporation Agreement. It consists of account number and date signed, i.e.  4ZXXXXX=01/01/2013
     *
     * @return String agreement "signature", i.e. "4ZXXXXX=01/01/2013"
     */
    public String getFDICAgreement() {
        return FDICAgreement;
    }


    /*
        SETTERS
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public void setAccountSummaryDashboardCollapsed(String accountSummaryDashboardCollapsed) {
        this.accountSummaryDashboardCollapsed = accountSummaryDashboardCollapsed;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public void setHasMewMessage(boolean hasMewMessage) {
        this.hasMewMessage = hasMewMessage;
    }

    public void setRealTimeStockQuote(boolean realTimeStockQuote) {
        this.realTimeStockQuote = realTimeStockQuote;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFDICPaper(String FDICPaper) {
        this.FDICPaper = FDICPaper;
    }

    public void setGainsKeeper(boolean isGainsKeeper) {
        this.isGainsKeeper = isGainsKeeper;
    }

    public void setTradingPasswordUsed(boolean isTradingPasswordUsed) {
        this.isTradingPasswordUsed = isTradingPasswordUsed;
    }

    public void setFDICAgreement(String FDICAgreement) {
        this.FDICAgreement = FDICAgreement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;

        UserProfile that = (UserProfile) o;

        if (hasMewMessage != that.hasMewMessage) return false;
        if (isGainsKeeper != that.isGainsKeeper) return false;
        if (isTradingPasswordUsed != that.isTradingPasswordUsed) return false;
        if (realTimeStockQuote != that.realTimeStockQuote) return false;
        if (!FDICAgreement.equals(that.FDICAgreement)) return false;
        if (!FDICPaper.equals(that.FDICPaper)) return false;
        if (!UUID.equals(that.UUID)) return false;
        if (!accountSummaryDashboardCollapsed.equals(that.accountSummaryDashboardCollapsed)) return false;
        if (!emailAddress.equals(that.emailAddress)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lasName.equals(that.lasName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = UUID.hashCode();
        result = 31 * result + accountSummaryDashboardCollapsed.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lasName.hashCode();
        result = 31 * result + (hasMewMessage ? 1 : 0);
        result = 31 * result + (realTimeStockQuote ? 1 : 0);
        result = 31 * result + emailAddress.hashCode();
        result = 31 * result + FDICPaper.hashCode();
        result = 31 * result + (isGainsKeeper ? 1 : 0);
        result = 31 * result + (isTradingPasswordUsed ? 1 : 0);
        result = 31 * result + FDICAgreement.hashCode();
        return result;
    }
}
