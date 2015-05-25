/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public abstract class TradeKingKObjectMixIn {

    @JsonAnySetter
    protected void add(String key, Object value) {
    }

}
