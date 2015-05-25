/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.connect;


import com.excelsiorsoft.api.consumer.tradeking.api.TradeKingOperations;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.TradeKingTemplate;

import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Template;

public class TradeKingServiceProvider extends AbstractOAuth1ServiceProvider<TradeKingOperations> {

    public final static String PROVIDER_ID = "tradeking";

    public TradeKingServiceProvider(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret, new OAuth1Template(consumerKey, consumerSecret,
                "https://developers.tradeking.com/oauth/request_token",
                "https://developers.tradeking.com/oauth/authorize",
                "https://developers.tradeking.com/oauth/access_token"
        ));
    }

    @Override
    public TradeKingOperations getApi(String accessToken, String secret) {
        return new TradeKingTemplate(getConsumerKey(), getConsumerSecret(), accessToken, secret);
    }
}
