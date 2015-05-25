/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.domain.market;

/**
 * Change Sign for the quote
 */
public enum ChangeSign {
    UP("u"), DOWN("d"), EQUAL("e");
    private final String value;

    ChangeSign(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
