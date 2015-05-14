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
public enum ForemanConstants implements Serializable
{
	/**
	 * TradeKing's OAuth API Key
	 */
	API_KEY(System.getenv("API_KEY")),
	/**
	 * TradeKing's OAuth API Secret Key
	 */
	API_SECRET(System.getenv("API_SECRET")),
	/**
	 * TradeKing's OAuth Access Token Key
	 */
	ACCESS_TOKEN(System.getenv("ACCESS_TOKEN")),
	/**
	 * TradeKing's OAuth Access Token Secret Key
	 */
	ACCESS_TOKEN_SECRET(System.getenv("ACCESS_TOKEN_SECRET"));

	private String value;

	ForemanConstants(String value)
	{
		if (value == null)
		{
			this.value = "";
		}
		else
		{
			this.value = value;
		}
	}

	@Override
	public String toString()
	{
		return value;
	}
}
