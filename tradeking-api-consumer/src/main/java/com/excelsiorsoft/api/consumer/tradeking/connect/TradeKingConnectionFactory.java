/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.connect;


import com.excelsiorsoft.api.consumer.tradeking.api.TradeKingOperations;

import org.springframework.social.connect.support.OAuth1ConnectionFactory;

public class TradeKingConnectionFactory extends OAuth1ConnectionFactory<TradeKingOperations> {

    public TradeKingConnectionFactory(String consumerKey, String consumerSecret) {
        super(TradeKingServiceProvider.PROVIDER_ID, new TradeKingServiceProvider(consumerKey, consumerSecret), new TradeKingApiAdapter());
    }


}
