package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static org.scribe.model.Verb.GET;
import static org.scribe.model.Verb.POST;



import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.gatherer.tradeking.connector.TradeKingForemanTest;



/**
 * An APIBuilder to handle TradeKing Market calls
 * 
 * @author sleyzerzon
 *
 */
public class MarketBuilder extends ApiBuilder {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MarketBuilder.class);
	
	private static final long serialVersionUID = -7542591696724178699L;

	private MarketBuilder(Verb v) {
		verb = v;
	}

	public static MarketBuilder getClock(ResponseFormat format) throws Throwable {
		
		MarketBuilder marketBuilder = new MarketBuilder(GET);
		//b.resourceURL = ApiCall.getMarketClock(format);
		marketBuilder.setResourceURL(ApiCalls.getMarketClock(format.toString()));;
		return marketBuilder;
	}
	
	
	public static MarketBuilder getExtQuotes(ResponseFormat format, String symbols, String fields) throws Throwable { 
		
		MarketBuilder marketBuilder = new MarketBuilder(POST);
		marketBuilder.setResourceURL(ApiCalls.getExtQuotes(format.toString(), symbols));
		return marketBuilder;
	}
	

	public static MarketBuilder getOptionsExpirations (ResponseFormat format, String symbol) throws Throwable { 
		
		MarketBuilder marketBuilder = new MarketBuilder(GET);
		marketBuilder.setResourceURL(ApiCalls.optionsExpirations(format.toString(), symbol));
		return marketBuilder;
	}
	
	public static MarketBuilder getOptionsStrikes (ResponseFormat format, String symbol) throws Throwable { 
		
		MarketBuilder marketBuilder = new MarketBuilder(GET);
		marketBuilder.setResourceURL(ApiCalls.optionsStrikes(format.toString(), symbol));
		return marketBuilder;
	}	



}

