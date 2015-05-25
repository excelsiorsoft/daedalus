/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.AccountBalance;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TradeKingTransaction;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.AccountHoldings;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.summary.AccountsSummary;

/**
 * A Sub-api that provides data about user account.
 */
public interface AccountOperations {

    /**
     * This call will return detailed balance and holding information for each account associated with a user.
     *
     * @return summary object
     */
    AccountsSummary[] getAccounts();

    /**
     * This call will return detailed balance information for the accountId
     *
     * @param accountId id of an account
     * @return full account balance, including Securities, Cash, Buying Power etc..
     */
    AccountBalance getAccountBalance(String accountId);

    /**
     * This call will return detail information about the holdings for an accountId
     *
     * @param accountId id of an account
     * @return an object that contains the holding list and total securities value
     */
    AccountHoldings getAccountHoldings(String accountId);

    /**
     * This call will return account activity for the accountId. This call supports optional date range or transaction type filters.
     *
     * @param accountId id of the account the history is retrieved for
     * @param range     a range of time to get history for
     * @param type      kind of transaction
     * @return a list of transaction objects
     */
    TradeKingTransaction[] getTransactionsHistory(String accountId, TradeKingTransaction.Range range, TradeKingTransaction.Type type);

    /**
     * Spring Social requires updateStatus(). In this case this is a no-op method
     * and does not do anything.
     *
     * @see org.springframework.social.connect.Connection#updateStatus(String)
     */
    void updateStatus();

}
