/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

/**
 * Enum used for querying time sales data.
 * Tick means that it queries for every point of sale.
 */
public enum TimeSalesInterval {
    MINUTES_1("1min"),
    MINUTES_5("5min"),
    TICK("tick");
    private final String name;

    TimeSalesInterval(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

