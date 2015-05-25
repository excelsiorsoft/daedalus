/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
class StreamCreationException extends Exception {

    private HttpStatus httpStatus;

    public StreamCreationException(String message, HttpStatus httpStatus) {
        super(message + "; HTTP status: " + httpStatus);
        this.httpStatus = httpStatus;
    }

    public StreamCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
