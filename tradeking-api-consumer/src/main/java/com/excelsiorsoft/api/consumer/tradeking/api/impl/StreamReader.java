/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

interface StreamReader {

    /**
     * iterate to the next item in the stream. This is usually used in the while loop to keep going till conditions are correct.
     */
    void next();

    /**
     * Closes the stream and takes care of closing all other objects serving the streaming process
     */
    void close();

}