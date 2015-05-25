/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl.responses;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TKApiVersionResponse extends TKResponse {

    @JsonProperty("version")
    private String version;

    @JsonProperty("error")
    private String error;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
