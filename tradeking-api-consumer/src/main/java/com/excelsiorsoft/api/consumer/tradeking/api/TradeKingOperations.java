/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

/**
 * The api is divided into sub-api. Pretty much one sub-api per URI fragment.
 */
package com.excelsiorsoft.api.consumer.tradeking.api;

public interface TradeKingOperations {

    /**
     * Sub api for getting various info about account, holdings and transaction history
     *
     * @return Object that holds methods to access api data
     */
    AccountOperations getAccountOperations();

    /**
     * Sub api to get, create, edit and delete watch lists
     *
     * @return Object that has methods to write and read watchlist api data
     */
    WatchlistOperations getWatchlistOperations();

    /**
     * Sub-api for market operations. This is the core of api that provides financial data.
     *
     * @return Object that has methods to read market data
     */
    MarketOperations getMarketOperations();

    /**
     * Sub-api for streaming market data
     * Streaming data from the API consists of making an Authenticated HTTP request and leaving the HTTP socket open to continually receive data.
     *
     * @return Object hat has methods to initiate data streaming
     */
    StreamingOperations getStreamingOperations();

    /**
     * Sub-api that proves data about API itself, like current user, api time, etc
     *
     * @return Object hat has methods query data about api itself
     */
    APIOperations getAPIOperations();
}
