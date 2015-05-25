/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */


package com.excelsiorsoft.api.consumer.tradeking.api.impl.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccountMixIn extends TradeKingKObjectMixIn {

    @JsonProperty("account")
    private String accountId;

    @JsonProperty("fundtrading")
    private boolean fundTrading;

    @JsonProperty("ira")
    private boolean ira;

    @JsonProperty("margintrading")
    private boolean marginTrading;

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("optionlevel")
    private int optionLevel;

    @JsonProperty("shared")
    private boolean shared;

    @JsonProperty("stocktrading")
    private boolean stockTrading;

}
