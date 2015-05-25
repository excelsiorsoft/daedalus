/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.TradeKingUser;

/**
 * Sub-api that proves data about API itself
 */
public interface APIOperations {

    /**
     * Gets API version for the api connected to
     *
     * @return a String with api version
     */
    String getAPIVersion();

    /**
     * Gets the connected user that is authorized to use the api
     *
     * @return User object
     */
    TradeKingUser getCurrentUser();

}
