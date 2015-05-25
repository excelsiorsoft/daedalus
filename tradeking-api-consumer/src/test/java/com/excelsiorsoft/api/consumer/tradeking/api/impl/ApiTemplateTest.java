package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import com.excelsiorsoft.api.consumer.tradeking.api.domain.member.TradeKingUser;
import com.excelsiorsoft.api.consumer.tradeking.api.impl.BaseTemplate;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.ApiException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class ApiTemplateTest extends BaseTemplateTest {

    @Test
    public void getAPIVersion() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "utility/version.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("api/version"), MediaType.APPLICATION_JSON));

        String apiVersion = tradeKing.getAPIOperations().getAPIVersion();
        mockServer.verify();
        assertEquals("API Versions Differ", apiVersion, mockData.apiVersion);
    }

    @Test(expected = ApiException.class)
    public void getAPIVersion_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "utility/version.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));
        tradeKing.getAPIOperations().getAPIVersion();
    }

    @Test
    public void getCurrentUser() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "member/profile.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("api/user"), MediaType.APPLICATION_JSON));

        TradeKingUser user = tradeKing.getAPIOperations().getCurrentUser();
        mockServer.verify();

        assertEquals("User Profile Objects Do not match", user.getUserProfile(), mockData.user.getUserProfile());
        assertArrayEquals("User Accounts Objects Do not match", user.getAccounts(), mockData.user.getAccounts());
        assertEquals("User Objects Do not match", user, mockData.user);

    }

    @Test(expected = ApiException.class)
    public void getCurrentUser_errorResponse() {
        mockServer.expect(requestTo(BaseTemplate.URL_BASE + "member/profile.json"))
                .andExpect(method(GET))
                .andRespond(withSuccess(jsonResource("error_response"), MediaType.APPLICATION_JSON));

        tradeKing.getAPIOperations().getCurrentUser();
        mockServer.verify();
    }
}
