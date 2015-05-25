/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.account.summary;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.AccountBalance;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldings;

/**
 * Account Summary. It contains Account Holdings and Account Balance for the account. Both of these also could be retrieved
 * separately, so this is just an "umbrella" class when it is retrieved in one go.
 */
public class AccountsSummary extends TradeKingObject {

    private String accountId;
    private AccountBalance accountBalance;
    private AccountHoldings accountHoldings;

    public AccountsSummary() {
    }

    public AccountsSummary(String accountId, AccountBalance accountBalance, AccountHoldings accountHoldings) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.accountHoldings = accountHoldings;
    }

    /**
     * @return String account id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Account Balance, data about account's cash, securities etc..
     *
     * @return Account Balance Object
     */
    public AccountBalance getAccountBalance() {
        return accountBalance;
    }

    /**
     * Account Holdings that includes a list of the holdings and a total securities figure.
     *
     * @return Account Holdings Object
     */
    public AccountHoldings getAccountHoldings() {
        return accountHoldings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountsSummary)) return false;

        AccountsSummary that = (AccountsSummary) o;

        if (!accountBalance.equals(that.accountBalance)) return false;
        if (!accountHoldings.equals(that.accountHoldings)) return false;
        if (!accountId.equals(that.accountId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountId.hashCode();
        result = 31 * result + accountBalance.hashCode();
        result = 31 * result + accountHoldings.hashCode();
        return result;
    }
}
