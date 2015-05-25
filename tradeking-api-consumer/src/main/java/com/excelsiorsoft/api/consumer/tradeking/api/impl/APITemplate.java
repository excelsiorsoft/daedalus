/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;

import com.excelsiorsoft.api.consumer.tradeking.api.APIOperations;
import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.TradeKingUser;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.responses.TKApiVersionResponse;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.responses.TKUserResponse;
import com.excelsiorsoft.api.consumer.tradeking.connect.TradeKingServiceProvider;

import org.springframework.http.ResponseEntity;
import org.springframework.social.ApiException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class APITemplate extends BaseTemplate implements APIOperations {

    private static final String URL_PROFILE = "member/profile.json";
    private static final String URL_API_VERSION = "utility/version.json";

    APITemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public String getAPIVersion() {
        URI url = this.buildUri(URL_API_VERSION);

        ResponseEntity<TKApiVersionResponse> response = this.getRestTemplate().getForEntity(url, TKApiVersionResponse.class);

        if (response.getBody().getError() != null)
            throw new ApiException(TradeKingServiceProvider.PROVIDER_ID, response.getBody().getError());

        return response.getBody().getVersion();
    }

    @Override
    public TradeKingUser getCurrentUser() {
        URI url = this.buildUri(URL_PROFILE);
        ResponseEntity<TKUserResponse> response = this.getRestTemplate().getForEntity(url, TKUserResponse.class);
        if (response.getBody().getError() == null || !"Success".equals(response.getBody().getError()))
            throw new ApiException(TradeKingServiceProvider.PROVIDER_ID, response.getBody().getError());

        return response.getBody().getUserData();

    }
}
