package com.excelsiorsoft.api.consumer.tradeking.api.impl;


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
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.TradeKingUser;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.UserAccount;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.UserProfile;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.watchlist.WatchlistItem;

import org.joda.time.*;

/**
 * This is the repository for the objects that are mapped from json files in test
 * resources
 */
public class TestObjectsRepository {

    /*
      Account Template Data
     */
    final static String accountId = "TestAccount1";
    final CashData cash = new CashData(0.0, 9.83, 10.98, 0.0, 0.0, 10.98, 0.0, 0.0, 0.0);
    final BuyingPower buyingPower = new BuyingPower(26.63, 100, 26.63, 26.63, 53.26, 53.26);
    final Securities securities = new Securities(0.0, 26.48, 0.0, 0.0, 0.0, 26.48, 26.48);

    private final AccountHoldingEntry accountHoldingEntry1 = new AccountHoldingEntry(AccountHoldingEntry.HoldingType.MARGIN_LONG, 906.87,
            new HoldingDisplayData("Margin", "Stock", "$.00", "$906.87", "SOMETHING 1 CO", "$28.34", "$113,3.00", "-$9.00", 40, "SMTH1"),
            2267.129999999999,
            new FinancialInstrument("300000000", "SOMETHING 1 CO", 0, "CS", "SMTH1"),
            1133.0, -92, 28.34, 22.672175000000003, 40,
            new HoldingsQuote(0, 28.34), 0
    );

    private final AccountHoldingEntry accountHoldingEntry2 = new AccountHoldingEntry(AccountHoldingEntry.HoldingType.MARGIN_LONG, 580.71,
            new HoldingDisplayData("Margin", "Stock", "$.00", "$580.71", "SOMETHING2 CO", "$348.94", "$593.98", "$124.61", 2, "SMTH2"),
            12.26999999999953,
            new FinancialInstrument("6000L0000", "SOMETHING2 CO", 0, "CS", "SMTH2"),
            593.98, 12.60999999999967, 348.94, 341.5711764705882, 17,
            new HoldingsQuote(0, 348.94), 0
    );

    AccountHoldingEntry[] holdingsList = new AccountHoldingEntry[]{accountHoldingEntry1, accountHoldingEntry2};
    AccountBalance balance = new AccountBalance(accountId, 36.46, 0.0, buyingPower, 0.0, 0.0, 26.63, cash, securities);
    AccountHoldings holdings = new AccountHoldings(holdingsList, 26.48);
    AccountHoldings holdingSingle = new AccountHoldings(new AccountHoldingEntry[]{accountHoldingEntry1}, 26.48);
    AccountsSummary accountsSummary1 = new AccountsSummary(accountId, balance, holdings);

    // Need additional account for testing pulling multiple accounts
    String accountId2 = "TestAccount2";
    AccountBalance balance2 = new AccountBalance(accountId2, 36.46, 0.0, buyingPower, 0.0, 0.0, 26.63, cash, securities);
    AccountsSummary accountsSummary2 = new AccountsSummary(accountId2, balance2, holdingSingle);

    //Transaction1
    DateTime transactionDate1 = new DateTime(2014, 12, 15, 5, 0, 0, 0, DateTimeZone.UTC);
    DateTime settlementDate1 = new DateTime(2014, 12, 18, 5, 0, 0, 0, DateTimeZone.UTC);
    TransactionSecurity security1 = new TransactionSecurity("800000000", "80000000", "CS", "SMTH2");
    TransactionDetails transactionDetails1 = new TransactionDetails(4.95, "SOMETHING INC-DEL", 0, 2.671, -10, "XCH", 2, 2, 0.08, security1, transactionDate1, settlementDate1, 10, null);
    TradeKingTransaction transaction1 = new TradeKingTransaction(TradeKingTransaction.Type.TRADE, 286.07, transactionDate1, "SOMETHING INC", "SMTH2", transactionDetails1);

    //Transaction2
    DateTime transactionDate2 = new DateTime(2014, 12, 1, 5, 0, 0, 0, DateTimeZone.UTC);
    TransactionSecurity security2 = new TransactionSecurity("300000000", "30000000", "CS", "SMTH1");
    TransactionDetails transactionDetails2 = new TransactionDetails(0, "SOMETHING CO CASH DIV  ON     40 SHS ", 0, 0, 0, "DIV", 0, 2, 0, security2, transactionDate2, null, 10, "Cash Dividend");
    TradeKingTransaction transaction2 = new TradeKingTransaction(TradeKingTransaction.Type.DIVIDEND, 24, transactionDate2, "SOMETHING CO,CASH DIV ON 40 SHS", "SMTH1", transactionDetails2);


    /*
    WATCH LISTS
     */
    String[] watchLists = new String[]{"DEFAULT", "watch2"};
    String[] watchListsSingle = new String[]{"DEFAULT"};
    WatchlistItem watchlistItem1 = new WatchlistItem(0, 0, "TCKR1");
    WatchlistItem watchlistItem2 = new WatchlistItem(0, 0, "TCKR2");

    /*
   API Utilities
     */
    String apiVersion = "1.0-RC1";
    UserProfile userProfile = new UserProfile("1000001", "quickQuote", "NAME", "SURNAME", true, true, "name.surname@example.com", "0", true, true, "TestAccount1=05/06/2013");
    UserAccount userAccount = new UserAccount("TestAccount1", true, false, false, "NAME SURNAME", 2, false, true);
    TradeKingUser user = new TradeKingUser(false, false, false, new UserAccount[]{userAccount}, userProfile);

    /*
    Market
     */
    LocalDateTime marketStatusDate = new LocalDateTime(2015, 1, 4, 18, 11, 0, 359);
    LocalTime nextTime = new LocalTime(8, 0, 0);
    MarketStatus marketStatus = new MarketStatus(marketStatusDate, MarketStatus.MarketCondition.CLOSED, MarketStatus.MarketCondition.PRE_OPEN, nextTime, "Market is open Monday through Friday 9:30AM to 4:00PM EST", 1420413060);


    DateTime startSalesQuoteTime = new DateTime(2015, 1, 2, 9, 37, 0, 0, DateTimeZone.UTC);
    LocalDate quoteDate = new LocalDate(2015, 1, 2);
    TimeSalesQuote timeSalesQuote1 = new TimeSalesQuote(quoteDate, startSalesQuoteTime, 111.05, 111.05, 53, 111.05, 111.05, 1420191420, 53);

    DateTime startSalesQuoteTime2 = new DateTime(2015, 1, 2, 9, 44, 0, 0, DateTimeZone.UTC);
    TimeSalesQuote timeSalesQuote2 = new TimeSalesQuote(quoteDate, startSalesQuoteTime2, 110.95, 110.95, 75, 110.95, 110.95, 1420191840, 75);

    TopListEntry topListEntry1 = new TopListEntry(68.6600, ChangeSign.UP, 300.0100, "CORP 1", "29.6780 %", 231.3500, 1, "C1", 36744);
    TopListEntry topListEntry2 = new TopListEntry(18.47, ChangeSign.UP, 215.52, "CORP 2", "9.3733 %", 197.05, 2, "C2", 251548);
    TopListEntry[] topList = new TopListEntry[]{topListEntry1, topListEntry2};


    NewsStory newsStory = new NewsStory("4cacd265647adb9a46977d5d2f8ff559", "Eye on Apple and Amazon", "04/09 14:02", "This is some very long story");
    NewsHeadline newsHeadline1 = new NewsHeadline("4cacd265647adb9a46977d5d2f8ff559", "Eye on Apple and Amazon", "04/09 14:02");
    NewsHeadline newsHeadline2 = new NewsHeadline("4wefed265647adb9a46977d5d2df", "Second Story", "04/09 14:12");
    NewsHeadline[] newsList = new NewsHeadline[]{newsHeadline1, newsHeadline2};

    LocalDate optionExpiration1 = new LocalDate(2015, 1, 9);
    LocalDate optionExpiration2 = new LocalDate(2015, 1, 17);

    LocalDate[] optionExpirationDates = new LocalDate[]{optionExpiration1, optionExpiration2};

    Double optionStrikePrice1 = 27.86;
    Double optionStrikePrice2 = 28.57;
    Double[] optionStrikePrices = new Double[]{optionStrikePrice1, optionStrikePrice2};

    // Quotes
    LocalDate dateLastTrade = new LocalDate(2015, 1, 2);
    LocalDate dateOfPriorTradeDay = new LocalDate(2014, 12, 31);
    LocalDate week52HighDate = new LocalDate(2014, 11, 25);
    LocalDate week52LowDate = new LocalDate(2014, 1, 31);
    DateTime dateTime = new DateTime(2015, 1, 2, 21, 14, DateTimeZone.UTC);
    LocalDate lastDividendPayDate = new LocalDate(2014, 11, 13);
    LocalDate exDividendDate = new LocalDate(2014, 11, 6);
    LocalTime askTime = new LocalTime(19, 59);
    LocalTime bidTime = new LocalTime(0, 0);


    StockQuote stockQuote1 = new StockQuote(109.8, askTime, 1, 4, 0, bidTime, 0, 1.05, ChangeSign.DOWN, "1.0500", 110.38,
            dateLastTrade, 5327055164.00, "NASD", "NASDAQ", 111.44, 50, 109.33, 107.35, "SOMETHING CO",
            111.39, "0.951 %", ChangeSign.DOWN, 110.38, 113.13, 110.21, 112.82, dateOfPriorTradeDay, -2.14, 41403351,
            Quote.SecurityClass.STOCK, Quote.TradingSession.MARKET, "SMTH", Quote.TradeCondition.RESUMED, "1420233240",
            286657, ChangeSign.EQUAL, "edueduduee", 53204626, 109.7549, 119.75, week52HighDate, 70.51, week52LowDate, dateTime,
            "89", 106.0105, 97.1111, 111.8682, 46848450, 48378740, 50878760, 0.907, 0, "300000000", 0.47, exDividendDate, StockQuote.DividendFrequency.QUARTERLY,
            lastDividendPayDate, 6.45, 1.88, 1, 105.9210, 97.0085, 111.6870, 5.75, "5,864,840,000", 0.216042, 1.72, 16.96, 52442641);

    StockQuote stockQuote2 = new StockQuote(109.8, askTime, 1, 4, 0, bidTime, 0, 1.05, ChangeSign.DOWN, "1.0500", 110.38,
            dateLastTrade, 5327055164.00, "NASD", "NASDAQ", 111.44, 50, 109.33, 107.35, "SOMETHING CO2",
            111.39, "0.951 %", ChangeSign.DOWN, 110.38, 113.13, 110.21, 112.82, dateOfPriorTradeDay, -2.14, 41403351,
            Quote.SecurityClass.STOCK, Quote.TradingSession.MARKET, "SMTH2", Quote.TradeCondition.RESUMED, "1420233240",
            286657, ChangeSign.EQUAL, "edueduduee", 53204626, 109.7549, 119.75, week52HighDate, 70.51, week52LowDate, dateTime,
            "89", 106.0105, 97.1111, 111.8682, 46848450, 48378740, 50878760, 0.907, 0, "300000000", 0.47, exDividendDate, StockQuote.DividendFrequency.QUARTERLY,
            lastDividendPayDate, 6.45, 1.88, 1, 105.9210, 97.0085, 111.6870, 5.75, "5,864,840,000", 0.216042, 1.72, 16.96, 52442641);

    StockQuote[] stockQuotes = new StockQuote[]{stockQuote1, stockQuote2};

    OptionQuote optionQuote1 = new OptionQuote(11.85, new LocalTime(15, 57), 50, 4, 11.60, new LocalTime(15, 57), 492,
            0.66, ChangeSign.DOWN, "0.6600", 12.45, new LocalDate(2015, 1, 2), "OPRA", "OPRA - Composite", 12.7, 1, 11.79,
            10.54, 12.70, "5.301 %", ChangeSign.DOWN, 12.45, 14.25, 12.45, 14.20, new LocalDate(2014, 12, 31), -1.59, 496,
            Quote.SecurityClass.OPTION, Quote.TradingSession.MARKET, "CMPN1150320C00100000", Quote.TradeCondition.RESUMED, "1420232100", 57,
            ChangeSign.UP, "eeedduuuuu", 589, 0, 20.35, new LocalDate(2014, 11, 25), 10.50, new LocalDate(2014, 12, 16),
            new DateTime(2015, 1, 2, 20, 55, 0, DateTimeZone.UTC), "0", 100, 77, "CALL MAR-15 $ 100.00", OptionQuote.OptionDelivery.STANDARD,
            OptionQuote.OptionStyle.AMERICAN, 0.326106, OptionQuote.OptionClass.STANDARD, "2,458", 10.164716, "2,251", 100, OptionQuote.OptionType.CALL,
            "CMPN1", 100, "057738170", "CMPN1", new LocalDate(2015, 3, 20), "20", "03", "2015", 0.147170,
            0.743770, -0.030385, 0.019614, 0.163379);

    OptionQuote optionQuote2 = new OptionQuote(10.85, new LocalTime(15, 50), 50, 4, 10.60, new LocalTime(15, 57), 492,
            0.66, ChangeSign.DOWN, "0.6600", 12.45, new LocalDate(2015, 1, 2), "OPRA", "OPRA - Composite", 12.7, 1, 11.79,
            10.54, 12.7, "5.301 %", ChangeSign.DOWN, 12.45, 14.25, 12.45, 14.20, new LocalDate(2014, 12, 31), -1.59, 496,
            Quote.SecurityClass.OPTION, Quote.TradingSession.MARKET, "CMPN1150320C00095000", Quote.TradeCondition.RESUMED, "1420232100", 57,
            ChangeSign.UP, "eeedduuuuu", 589, 0, 20.35, new LocalDate(2014, 11, 25), 10.50, new LocalDate(2014, 12, 16),
            new DateTime(2015, 1, 2, 20, 55, 0, DateTimeZone.UTC), "0", 100, 77, "CALL MAR-15 $ 95.00", OptionQuote.OptionDelivery.STANDARD,
            OptionQuote.OptionStyle.AMERICAN, 0.326106, OptionQuote.OptionClass.STANDARD, "2,458", 10.164716, "2,251", 100, OptionQuote.OptionType.CALL,
            "CMPN1", 95, "057738170", "CMPN1", new LocalDate(2015, 3, 20), "20", "03", "2015", 0.147170,
            0.743770, -0.030385, 0.019614, 0.163379);

    OptionQuote[] searchOptionQuotes = new OptionQuote[]{optionQuote1, optionQuote2};

    //Streaming
    StreamQuoteEvent streamQuote = new StreamQuoteEvent(528.5, 5, 528.22, 3, null, new DateTime(2015, 2, 3, 22, 13, 43, 0, DateTimeZone.UTC), "Direct Edge X Exchange", "Regular, two-sided open quote automated", "CMPN1", 1423001623);
    StreamTradeEvent streamTrade = new StreamTradeEvent("CMPN1", "Direct Edge X Exchange", 528.5, 300, 2032727, 528.351, new DateTime(2015, 2, 3, 22, 13, 32, 0, DateTimeZone.UTC), 1423001612, "57,16,10");
}
