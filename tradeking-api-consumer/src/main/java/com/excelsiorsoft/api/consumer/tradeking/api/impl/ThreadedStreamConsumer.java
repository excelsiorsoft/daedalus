/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

import com.excelsiorsoft.api.consumer.tradeking.api.StreamingException;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract Stream Consumer class that has unimplemented getStreamReader()
 * The class runs in its own thread and runs stream reader in a loop to read data from stream
 */
abstract class ThreadedStreamConsumer extends Thread implements Stream {

    private final AtomicBoolean open;

    private StreamReader streamReader;

    public ThreadedStreamConsumer() {
        this.open = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        long timeToSleep = 250;
        this.streamReader = null;

        while (this.open.get()) {
            try {
                if (this.streamReader == null) {      //initial connection or reconnect on disconnect
                    this.streamReader = this.getStreamReader();
                    timeToSleep = MIN_WAIT;
                }
                this.streamReader.next();
            } catch (StreamingException e) {        //connection error
                this.streamReader = null;             //nullify so it reconnects in the next iteration
            } catch (StreamCreationException e) {   //connection initialization error
                if (e.getHttpStatus() != null) {
                    if (timeToSleep == MIN_WAIT) {
                        timeToSleep = 5000;
                    }
                    this.sleepBeforeRetry(timeToSleep);
                    timeToSleep = timeToSleep * 2;
                    if (timeToSleep > HTTP_ERROR_SLEEP_MAX) {
                        this.close();
                    }
                } else {
                    if (this.open.get()) {
                        if (timeToSleep == MIN_WAIT) {
                            timeToSleep = 250;
                        }
                        this.sleepBeforeRetry(timeToSleep);
                        timeToSleep = Math.min(timeToSleep + 250, NETWORK_ERROR_SLEEP_MAX);
                    }
                }
            }
        }
    }

    @Override
    public void open() {
        this.start();
    }

    @Override
    public void close() {
        open.set(false);
        if (streamReader != null) {
            streamReader.close();
        }
    }

    protected abstract StreamReader getStreamReader() throws StreamCreationException;

    void sleepBeforeRetry(long timeToSleep) {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            //thread got interrupted
        }
    }

    private static final long HTTP_ERROR_SLEEP_MAX = 320000;

    private static final long NETWORK_ERROR_SLEEP_MAX = 16000;

    private static final long MIN_WAIT = 250;

}
