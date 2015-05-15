package com.excelsiorsoft.gatherer.tradeking.connector.api;

import org.scribe.model.Verb;

/**
 * A builder for different sorts of TradeKing API calls - replacement for {@link ApiCall}
 * 
 * @author sleyzerzon
 *
 */
public class ApiCalls {
	
	public enum MARKET
	{
		CLOCK("https://api.tradeking.com/v1/market/clock", "."), 
		EXT_QUOTES("https://api.tradeking.com/v1/market/ext/quotes", "."), 
		STREAM_EXT_QUOTES("https://stream.tradeking.com/v1/market/quotes", "."), 
		NEWS_SEARCH("https://api.tradeking.com/v1/market/news/search", "."), 
		NEWS_ID("https://api.tradeking.com/v1/market/news/", "", "."), 
		OPTIONS_SEARCH("https://api.tradeking.com/v1/market/options/search", "."), 
		OPTIONS_STRIKES("https://api.tradeking.com/v1/market/options/strikes", "."), 
		OPTIONS_EXPIRATIONS("https://api.tradeking.com/v1/market/options/expirations", "."), 
		TIMESALES("https://api.tradeking.com/v1/market/timesales", "."), 
		TOPLISTS_VOLUME("https://api.tradeking.com/v1/market/toplists/topvolume", "."), 
		TOPLISTS_LOSERS_DOLLAR("https://api.tradeking.com/v1/market/toplists/toplosers", "."), 
		TOPLISTS_LOSERS_PERCENTAGE("https://api.tradeking.com/v1/market/toplists/toppctlosers", "."), 
		TOPLISTS_ACTIVE("https://api.tradeking.com/v1/market/toplists/topactive", "."), 
		TOPLISTS_GAINERS_DOLLAR_AMT("https://api.tradeking.com/v1/market/toplists/topgainers", "."), 
		TOPLISTS_GAINERS_PERCENTAGE("https://api.tradeking.com/v1/market/toplists/toppctgainers", "."), 
		TOPLISTS_GAINERS_ACTIVE_DOLLAR_AMT("https://api.tradeking.com/v1/market/toplists/topactivegainersbydollarvalue", ".");

		private String[] urlStrings;

		MARKET(String... urlStrings)
		{
			this.urlStrings = urlStrings;
		}

		public String resolveString(String... params)
		{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < urlStrings.length; i++)
			{
				sb.append(urlStrings[i]);
				if (params.length > i)
				{
					sb.append(params[i]);
				}
			}
			return sb.toString();
		}

		public Verb getVerb()
		{
			return Verb.GET;
		}
	}	

}
