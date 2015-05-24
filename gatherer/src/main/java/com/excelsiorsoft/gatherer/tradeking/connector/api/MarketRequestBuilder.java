package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static org.scribe.model.Verb.GET;
import static org.scribe.model.Verb.POST;



import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * An APIBuilder to handle TradeKing Market calls
 * 
 * @author sleyzerzon
 *
 */
public class MarketRequestBuilder extends TKRequest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MarketRequestBuilder.class);
	
	private static final long serialVersionUID = -7542591696724178699L;

	private MarketRequestBuilder(Verb v) {
		verb = v;
	}

	public static MarketRequestBuilder getClock(ResponseFormat format) throws Throwable {
		
		MarketRequestBuilder marketBuilder = new MarketRequestBuilder(GET);
		//b.resourceURL = ApiCall.getMarketClock(format);
		marketBuilder.setResourceURL(ApiCalls.getMarketClock(format.toString()));;
		return marketBuilder;
	}
	
	
	public static MarketRequestBuilder getExtQuotes(ResponseFormat format, String symbols, String fields) throws Throwable { 
		
		MarketRequestBuilder marketBuilder = new MarketRequestBuilder(POST);
		marketBuilder.setResourceURL(ApiCalls.getExtQuotes(format.toString(), symbols));
		return marketBuilder;
	}
	

	public static MarketRequestBuilder getOptionsExpirations (ResponseFormat format, String symbol) throws Throwable { 
		
		MarketRequestBuilder marketBuilder = new MarketRequestBuilder(GET);
		marketBuilder.setResourceURL(ApiCalls.optionsExpirations(format.toString(), symbol));
		return marketBuilder;
	}
	
	public static MarketRequestBuilder getOptionsStrikes (ResponseFormat format, String symbol) throws Throwable { 
		
		MarketRequestBuilder marketBuilder = new MarketRequestBuilder(GET);
		marketBuilder.setResourceURL(ApiCalls.optionsStrikes(format.toString(), symbol));
		return marketBuilder;
	}	



}

