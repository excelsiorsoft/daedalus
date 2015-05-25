/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api;

import com.excelsiorsoft.api.consumer.tradeking.api.impl.Stream;

import java.util.List;

/**
 * Streaming data from the API consists of making an Authenticated HTTP request and leaving the HTTP socket open to continually receive data.
 * You can request streaming data for all standard equity and option symbols. The format of option symbols must be sent in OCC symbology.
 * At this time, it only permits 256 symbols to be streamed per request.
 */
public interface StreamingOperations {

    /**
     * This call will begin streaming quote and trade payloads for a symbol or list of symbols passed as a  parameter
     *
     * @param listeners Listeners that will pick up the result of the stream
     * @param quotes    symbols of stocks or options to start streaming. Max limit is 255. For option symbols,
     *                  the symbols need to follow that of the OCC.
     * @return A stream object. It starts streaming in other threads and listeners pick it up, so there is no result in return statement
     */
    Stream quotesAndTradesStream(final List<StreamListener> listeners, String[] quotes);

}
