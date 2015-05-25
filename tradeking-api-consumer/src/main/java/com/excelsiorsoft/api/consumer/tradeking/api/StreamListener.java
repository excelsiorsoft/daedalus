/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.StreamQuoteEvent;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.StreamTradeEvent;

/**
 * Interface for listeners that get executed once entry from streaming api is received.
 */
public interface StreamListener {

    /**
     * Method that gets executed once Trade event happens
     *
     * @param tradeEvent Trade Event object.
     */
    void onTrade(StreamTradeEvent tradeEvent);

    /**
     * Method that gets executed once Quote event happens
     *
     * @param quoteEvent Quote Event object. It is a very brief version of a detailed quote.
     *                   If more information is needed, a full quote should be retrieved via market sub-api
     */
    void onQuote(StreamQuoteEvent quoteEvent);
}