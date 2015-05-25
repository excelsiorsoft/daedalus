/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

import com.excelsiorsoft.api.consumer.tradeking.api.StreamListener;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.TradeKingObject;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.StreamQuoteEvent;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.market.StreamTradeEvent;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins.StreamQuoteMixIn;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins.StreamTradeMixIn;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins.TradeKingKObjectMixIn;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Dispatcher polls queue in a loop and dispatches it to listeners once a recognizable entry is found
 * It uses a pool of threads to run process paralleled
 */
class StreamDispatcher implements Runnable {

    private final List<StreamListener> listeners;

    private final ObjectMapper objectMapper;

    private final AtomicBoolean active;

    private final Queue<String> queue;

    private final ExecutorService pool;


    public StreamDispatcher(Queue<String> queue, List<StreamListener> listeners) {
        this.queue = queue;
        this.listeners = listeners;
        pool = Executors.newCachedThreadPool();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());

        objectMapper.addMixInAnnotations(TradeKingObject.class, TradeKingKObjectMixIn.class);
        objectMapper.addMixInAnnotations(StreamQuoteEvent.class, StreamQuoteMixIn.class);
        objectMapper.addMixInAnnotations(StreamTradeEvent.class, StreamTradeMixIn.class);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        active = new AtomicBoolean(true);
    }

    /**
     * Keeps polling the queue. If it finds something,
     * it tries to figure out the type of the entry and dispatches it for processing
     */
    @Override
    public void run() {
        while (active.get()) {

            String jsonEntry = queue.poll();
            if (jsonEntry == null || jsonEntry.length() == 0) return;

            try {
                if (jsonEntry.contains("{\"quote")) {
                    handleQuote(jsonEntry);
                } else if (jsonEntry.startsWith("{\"trade")) {
                    handleTrade(jsonEntry);
                }
            } catch (IOException e) {
                // could not map the line, will skip for now
            }
        }
    }

    /**
     * Stops the dispatcher.
     */
    public void stop() {
        active.set(false);
        pool.shutdown();
    }

    /**
     * Takes a String json entry, converts it to a stream quote object and passes it to listeners provided to execute in
     * thread pool
     *
     * @param jsonEntry json format quote
     * @throws IOException exception if could not map json to object
     */
    private void handleQuote(String jsonEntry) throws IOException {
        StreamQuoteEvent quoteEvent = objectMapper.readValue(jsonEntry, StreamQuoteEvent.class);
        for (final StreamListener listener : listeners) pool.submit((() -> listener.onQuote(quoteEvent)));
    }

    /**
     * Takes a String json entry, converts it to a stream trade object and passes it to listeners provided to execute in
     * thread pool
     *
     * @param jsonEntry json format trade entry
     * @throws IOException exception if could not map json to object
     */
    private void handleTrade(String jsonEntry) throws IOException {
        StreamTradeEvent tradeEvent = objectMapper.readValue(jsonEntry, StreamTradeEvent.class);
        for (StreamListener listener : listeners) pool.submit((() -> listener.onTrade(tradeEvent)));
    }


}
