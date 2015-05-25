/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;


public interface Stream {

    /**
     * Opens the stream. Usually the stream is in other thread, so this starts the thread.
     */
    void open();

    /**
     * Close the stream and all the processes around it
     */
    void close();
}
