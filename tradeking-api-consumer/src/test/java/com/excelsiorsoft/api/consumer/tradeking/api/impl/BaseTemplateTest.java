package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

import com.excelsiorsoft.api.consumer.tradeking.api.impl.TradeKingTemplate;

public class BaseTemplateTest {

    MockRestServiceServer mockServer;
    TradeKingTemplate tradeKing;
    TestObjectsRepository mockData;

    @Before
    public void setup() {
        tradeKing = new TradeKingTemplate("API_KEY", "API_SECRET", "ACCESS_TOKEN", "ACCESS_TOKEN_SECRET");
        mockServer = MockRestServiceServer.createServer(tradeKing.getRestTemplate());
        mockData = new TestObjectsRepository();
    }

    Resource jsonResource(String filename) {
        return new ClassPathResource(filename + ".json", getClass());
    }

}
