/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.AccountBalance;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.BuyingPower;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.CashData;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.balance.Securities;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TradeKingTransaction;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TransactionDetails;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.history.TransactionSecurity;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.holdings.*;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.account.summary.AccountsSummary;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.*;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.UserAccount;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins.*;
import com.fasterxml.jackson.databind.module.SimpleModule;


/**
 * The module to register all mix ins, so they can be registered in places where the object mapper is created from scratch
 * easily as well as the main JsonMessageConverter
 */
public class TradeKingModule extends SimpleModule {

    public TradeKingModule() {
        super("TradeKingModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(TradeKingObject.class, TradeKingKObjectMixIn.class);
        context.setMixInAnnotations(AccountBalance.class, AccountBalanceMixIn.class);
        context.setMixInAnnotations(AccountHoldingEntry.class, AccountHoldingEntryMixIn.class);
        context.setMixInAnnotations(AccountHoldings.class, AccountHoldingsMixIn.class);
        context.setMixInAnnotations(BuyingPower.class, BuyingPowerMixIn.class);
        context.setMixInAnnotations(FinancialInstrument.class, FinancialInstrumentMixIn.class);
        context.setMixInAnnotations(HoldingDisplayData.class, HoldingDisplayDataMixIn.class);
        context.setMixInAnnotations(OptionQuote.class, OptionQuoteMixIn.class);
        context.setMixInAnnotations(HoldingsQuote.class, HoldingQuoteMixIn.class);
        context.setMixInAnnotations(Securities.class, SecuritiesMixIn.class);
        context.setMixInAnnotations(StockQuote.class, StockQuoteMixin.class);
        context.setMixInAnnotations(AccountsSummary.class, TKAllAccountsSummaryMixIn.class);
        context.setMixInAnnotations(TradeKingTransaction.class, TradeKingTransactionMixIn.class);
        context.setMixInAnnotations(TransactionDetails.class, TransactionDetailsMixIn.class);
        context.setMixInAnnotations(TransactionSecurity.class, TransactionSecurityMixIn.class);
        context.setMixInAnnotations(UserAccount.class, UserAccountMixIn.class);
        context.setMixInAnnotations(CashData.class, CashMixIn.class);
        context.setMixInAnnotations(NewsHeadline.class, NewsHeadlineMixIn.class);
        context.setMixInAnnotations(NewsStory.class, NewsStoryMixIn.class);
        context.setMixInAnnotations(TopListEntry.class, TopListEntryMixIn.class);
        context.setMixInAnnotations(TimeSalesQuote.class, TimeSalesQuoteMixIn.class);
    }


}
