package com.excelsiorsoft.gatherer.tradeking.connector.api;

import java.io.Serializable;

import org.scribe.model.Request;
import org.scribe.model.Response;

/**
 * 
 * A holder for all the bits that come back through each REST call.<br>
 * The important parts are the rate limits, which help you control how many
 * calls of that type you can use again.
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

	public TKResponse(Request req) {
		Response response = req.send();
		String limitUsed = response.getHeader("X-RateLimit-Used");
		String limitExpire = response.getHeader("X-RateLimit-Expire");
		String limitTotal = response.getHeader("X-RateLimit-Limit");
		String limitRemain = response.getHeader("X-RateLimit-Remaining");
		if (limitUsed != null) {
			rateLimitUsed = Integer.parseInt(limitUsed);
		}
		if (limitExpire != null) {
			rateLimitExpire = Long.parseLong(limitExpire);
		}
		if (limitTotal != null) {
			rateLimitTotal = Integer.parseInt(limitTotal);
		}
		if (limitRemain != null) {
			rateLimitRemaining = Integer.parseInt(limitRemain);
		}
		this.response = response.getBody();
	}

	public TKResponse(String req, Integer... limits) {
		this.response = req;
	}

	public TKResponse() {
	}

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

	@Override
	public String toString() {
		return response;
	}
}

