package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import com.excelsiorsoft.api.consumer.tradeking.api.StreamListener;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.StreamQuoteEvent;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.StreamTradeEvent;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.Stream;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.StreamingTemplate;

import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class StreamingTemplateTest extends BaseTemplateTest {

    private Boolean quoteAssertionPassed = null;
    private Boolean tradeAssertionPassed = null;

    @Test
    public void quotesAndTradesStream_testQuote() throws InterruptedException {

        mockServer.expect(requestTo(StreamingTemplate.URL_STREAM_QUOTES_TRADES))
                .andExpect(method(POST))
                .andExpect(content().string("symbols=CMPN1"))
                .andRespond(withSuccess(jsonResource("market/stream_quote"), MediaType.APPLICATION_JSON));

        List<StreamListener> listeners = new ArrayList<>();
        listeners.add(new TestListener());

        Stream stream = tradeKing.getStreamingOperations().quotesAndTradesStream(listeners, new String[]{"CMPN1"});

        Thread.sleep(2000); // delay while stream is read

        mockServer.verify();

        queryForStatus("Quote", quoteAssertionPassed);

        stream.close();

    }

    @Test
    public void quotesAndTradesStream_testTrade() throws InterruptedException {

        mockServer.expect(requestTo(StreamingTemplate.URL_STREAM_QUOTES_TRADES))
                .andExpect(method(POST))
                .andExpect(content().string("symbols=CMPN1"))
                .andRespond(withSuccess(jsonResource("market/stream_trade"), MediaType.APPLICATION_JSON));

        List<StreamListener> listeners = new ArrayList<>();
        listeners.add(new TestListener());

        tradeKing.getStreamingOperations().quotesAndTradesStream(listeners, new String[]{"CMPN1"});

        Thread.sleep(2000); // delay while stream is read

        mockServer.verify();

        queryForStatus("Trade", tradeAssertionPassed);

    }

    private void queryForStatus(String description, Boolean varToCheck) throws InterruptedException {
        int timesQueried = 0;

        while (true) {

            if (timesQueried > 100) {
                assertFalse("Timeout checking the " + description + " match", true);
                return;
            }

            if (null == varToCheck) {
                timesQueried++;
                Thread.sleep(20);
            } else if (varToCheck) {
                return;
            } else {
                assertFalse("Stream " + description + " do not match", true);
                return;
            }

        }
    }


    class TestListener implements StreamListener {

        @Override
        public void onTrade(StreamTradeEvent tradeEvent) {
            tradeAssertionPassed = tradeEvent.equals(mockData.streamTrade);
        }

        @Override
        public void onQuote(StreamQuoteEvent quoteEvent) {
            quoteAssertionPassed = quoteEvent.equals(mockData.streamQuote);
        }
    }
}
