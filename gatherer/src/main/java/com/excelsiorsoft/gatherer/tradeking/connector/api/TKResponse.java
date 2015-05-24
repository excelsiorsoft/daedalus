package com.excelsiorsoft.gatherer.tradeking.connector.api;

import java.io.Serializable;

import org.scribe.model.Request;
import org.scribe.model.Response;

/**
 * 
 * Encapsulates TradeKing response that comes back through each REST call.<br>
 * The important parts are the rate limits, which help you control how many
 * calls of that type you can use again.
 * 
 * <pre>

<h1>Rate Limiting</h1>
<h2>Overview</h2>

We have implemented a rate limiting system for all authenticated brokerage calls through the API. It is designed to protect accounts from abuse and servers from load. We aim to put limits in place that provide protection but do not hinder the usability of the API.
Limits

These are the current limits in place:

    20 requests per minute for trade submission calls (ex: POST /v1/account/12345678/orders.xml)
    60 requests per minute for market/quotes calls
    180 requests per minute for all other authenticated calls (balances, summary, etc)

<H1>Rate Limit Headers</H1>

You can find rate limit information in the response headers of all calls that are limited. These headers can help you throttle your application as well as provide information about your current limit quota.

    X-RateLimit-Used: Number of requests sent against the current limit
    X-RateLimit-Expire: When the current limit will expire (Unix timestamp)
    X-RateLimit-Limit: Total number of requests allowed in the call limit
    X-RateLimit-Remaining: Number of requests allowed against the current limit

Here is an example of the response headers for a trade/quote call:

HTTP/1.1 200 OK
Content-Length: 6024
Content-Type: application/xml; charset=UTF-8
Date: Wed, 16 Mar 2011 14:51:44 GMT
Server: Apache-Coyote/1.1
X-RateLimit-Used: 8
X-RateLimit-Expire: 1300286940
X-RateLimit-Limit: 60
X-RateLimit-Remaining: 52

<H1>Exceeding Limits</H1>

If you exceed these limits you will receive a rate limit fault along with the same rate limiting headers you would recieve in the response. You will receive this as a response until the limit has expired, at which point you will receive regular responses.
 * </pre>
 * 
 * 
 * @author sleyzerzon
 *
 */
public class TKResponse implements Serializable {
	private static final long serialVersionUID = 1626169989303557890L;
	private String response = "";
	private int rateLimitUsed = 0;
	private long rateLimitExpire = 0;
	private int rateLimitTotal = 0;
	private int rateLimitRemaining = 0;

	public TKResponse(Response oAuthResponse) {
		
		
		String limitUsed = oAuthResponse.getHeader("X-RateLimit-Used");
		String limitExpire = oAuthResponse.getHeader("X-RateLimit-Expire");
		String limitTotal = oAuthResponse.getHeader("X-RateLimit-Limit");
		String limitRemain = oAuthResponse.getHeader("X-RateLimit-Remaining");
		
		
		rateLimitUsed = limitUsed != null?Integer.parseInt(limitUsed):0;
		
		
		rateLimitExpire = limitExpire != null?Long.parseLong(limitExpire):0;
		
		
		rateLimitTotal = limitTotal != null?Integer.parseInt(limitTotal):0;
		
		
		rateLimitRemaining = limitRemain != null?Integer.parseInt(limitRemain):0;
		
		this.response = oAuthResponse.getBody();
	}

	public TKResponse(String req, Integer... limits) {
		this.response = req;
	}

	public TKResponse() {}

	public int getCallsUsed() {
		return rateLimitUsed;
	}

	public long getRateLimitExpiration() {
		return rateLimitExpire;
	}

	public int getTotalCallsAllowed() {
		return rateLimitTotal;
	}

	public int getCallsRemaining() {
		return rateLimitRemaining;
	}
	
	public String getResponse(){
		return response;
	}

	@Override
	public String toString() {
		return String
				.format("TKResponse [response=%s, rateLimitUsed=%s, rateLimitExpire=%s, rateLimitTotal=%s, rateLimitRemaining=%s]",
						response, rateLimitUsed, rateLimitExpire,
						rateLimitTotal, rateLimitRemaining);
	}

	
}

