/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

/**
 * Top List Type enum is used for querying api's top list endpoint
 */
public enum TopListType {

    LOSERS_DOLLARS("toplosers"), LOSERS_PERCENTAGE("toppctlosers"), VOLUME("topvolume"), ACTIVE("topactive"), GAINERS_DOLLARS("topgainers"),
    GAINERS_PERCENTAGE("toppctgainers"), GAINERS_ACTIVE_DOLLARS("topactivegainersbydollarvalue");

    private final String name;

    TopListType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
