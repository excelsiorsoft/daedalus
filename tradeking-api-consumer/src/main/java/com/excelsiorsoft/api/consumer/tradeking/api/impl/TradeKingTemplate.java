
package com.excelsiorsoft.api.consumer.tradeking.api.impl;

import com.excelsiorsoft.api.consumer.tradeking.api.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;

public class TradeKingTemplate extends AbstractOAuth1ApiBinding implements TradeKingOperations {

    private AccountOperations accountOperations;
    private WatchlistOperations watchlistOperations;
    private MarketOperations marketOperations;
    private StreamingOperations streamingOperations;
    private APIOperations apiOperations;


    public TradeKingTemplate(String consumerKey, String consumerSecret, String accessToken, String secret) {
        super(consumerKey, consumerSecret, accessToken, secret);
    }

    @Override
    public AccountOperations getAccountOperations() {
        if (null == this.accountOperations) this.accountOperations = new AccountTemplate(this.getRestTemplate());
        return this.accountOperations;
    }

    @Override
    public WatchlistOperations getWatchlistOperations() {
        if (null == this.watchlistOperations) this.watchlistOperations = new WatchlistTemplate(this.getRestTemplate());
        return this.watchlistOperations;
    }

    @Override
    public MarketOperations getMarketOperations() {
        if (null == this.marketOperations) this.marketOperations = new MarketTemplate(this.getRestTemplate());
        return this.marketOperations;
    }

    @Override
    public StreamingOperations getStreamingOperations() {
        if (null == this.streamingOperations) this.streamingOperations = new StreamingTemplate(this.getRestTemplate());
        return this.streamingOperations;
    }

    @Override
    public APIOperations getAPIOperations() {
        if (null == this.apiOperations) this.apiOperations = new APITemplate(this.getRestTemplate());
        return this.apiOperations;
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        mapper.registerModule(new TradeKingModule());
        mapper.registerModule(new JodaModule());
        converter.setObjectMapper(mapper);
        return converter;
    }

}
