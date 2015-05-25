/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api;

import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.*;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.OptionQuoteNotFoundException;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TimeSalesInterval;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TopListType;

import org.joda.time.LocalDate;


/**
 * Sub-api for market operations. This is the core of api that provides financial data.
 */
public interface MarketOperations {

    /**
     * This call will return quote for a symbol passed
     *
     * @param ticker String symbol of the quote
     * @return StockQuote object
     */
    StockQuote getQuoteForStock(String ticker);

    /**
     * This call will return quotes for  multiple  symbols.
     *
     * @param tickers String array of tickers to get quotes for
     * @return list of stock quotes
     */

    StockQuote[] getQuoteForStocks(String[] tickers);

    /**
     * Gets Option quote for the stock with a provided expiration date and strike price
     *
     * @param ticker         symbol of the stock
     * @param expirationDate option expiration date
     * @param type           option type, put or call
     * @param strikePrice    option strike price
     * @return Option Quote if found
     * @throws OptionQuoteNotFoundException thrown if no option with the parameters provided was found
     */
    OptionQuote getQuoteForOption(String ticker, LocalDate expirationDate, OptionQuote.OptionType type, double strikePrice) throws OptionQuoteNotFoundException;

    /**
     * Search for options by parameters given
     *
     * @param ticker         symbol of the stock
     * @param minStrikePrice minimum strike price in dollars or null if any
     * @param maxStrikePrice maximum strike price in dollars or null if any
     * @param type           option type, put or call
     * @param startDate      Date range start or null if any
     * @param endDate        Date range end or null if any
     * @return a list of option quotes
     */
    OptionQuote[] searchOptions(String ticker, Double minStrikePrice, Double maxStrikePrice, OptionQuote.OptionType type, LocalDate startDate, LocalDate endDate);

    /**
     * This call will return the full list of available option strikes for a given symbol
     *
     * @param ticker stock symbol
     * @return returns a list of strike prices or empty list if none found
     */
    Double[] getStrikePrices(String ticker);

    /**
     * This call will return the full list of available option expiration dates for a given symbol.
     *
     * @param ticker stock symbol
     * @return a list of expiration dates or empty list if none found
     */
    LocalDate[] getOptionExpirationDates(String ticker);

    /**
     * This call will return a listing of the newest news headlines based on the current symbol search.
     *
     * @param ticker stock symbol
     * @param limit  limit how many to return
     * @return a list of news article objects, it will not have the story in it. For story see MarketOperations#getNewsById
     */
    NewsHeadline[] getNewsList(String ticker, int limit);

    /**
     * This call will return a listing of the newest news headlines based on the current keyword search.
     *
     * @param keywords string list of keywords to search by
     * @param limit    limit how many to return
     * @return a list of news article objects, it will not have the story in it. For story see MarketOperations#getNewsById
     */
    NewsHeadline[] getNewsList(String[] keywords, int limit);

    /**
     * Gets a news article by newsId with a full story in it
     *
     * @param newsId id of the news article retrieved by news search methods above
     * @return news article object with full story populated in them
     */
    NewsStory getNewsById(String newsId);

    /**
     * This call will return the current state of the market, the time of the next state change (if the market is open),
     * and the current server timestamp
     *
     * @return Market Status object
     */
    MarketStatus getMarketStatus();

    /**
     * This call will return a ranked list based on the list type specified.
     *
     * @param listType List type
     * @return list of top stocks for the type
     */
    TopListEntry[] getTopList(TopListType listType);

    /**
     * This call will return time and sales quote data based on a symbol passed as a  parameter. It assumes the interval
     * is "tick" and the time is today starting from market opening
     *
     * @param ticker     stock symbol
     * @param pageNumber number of a page
     * @param perPage    entries per page
     * @return data points for the stock
     */
    TimeSalesQuote[] getDataPoints(String ticker, int pageNumber, int perPage);

    /**
     * This call will return time and sales quote data based on a symbol passed as a  parameter.
     * Time and sales data is available on a rolling 5 day basis.
     *
     * @param ticker    stock symbol
     * @param startDate start date to get the points for
     * @param endDate   end date to get the points for. If null, now will be assumed.
     * @param interval  time interval between 2 data points
     * @return data points for the stock
     */
    TimeSalesQuote[] getDataPoints(String ticker, LocalDate startDate, LocalDate endDate, TimeSalesInterval interval);

}
