/**
 * 
 */
package com.excelsiorsoft.gatherer.tradeking.connector;

import java.io.Serializable;

/**
 * TradeKing Foreman's Constants that come from System Environment Properties
 * 
 * @author sleyzerzon
 *
 */
public enum ForemanConstants implements Serializable {
	/**
	 * TradeKing's OAuth API Key
	 */
	CONSUMER_KEY(System.getenv("CONSUMER_KEY")),
	/**
	 * TradeKing's OAuth API Secret Key
	 */
	CONSUMER_SECRET(System.getenv("CONSUMER_SECRET")),
	/**
	 * TradeKing's OAuth Access Token Key
	 */
	OAUTH_TOKEN(System.getenv("OAUTH_TOKEN")),
	/**
	 * TradeKing's OAuth Access Token Secret Key
	 */
	OAUTH_TOKEN_SECRET(System.getenv("OAUTH_TOKEN_SECRET"));

	private String value;

	ForemanConstants(String value) {
		
		this.value = value == null?"":value;

	}

	@Override
	public String toString() {
		return value;
	}
}
