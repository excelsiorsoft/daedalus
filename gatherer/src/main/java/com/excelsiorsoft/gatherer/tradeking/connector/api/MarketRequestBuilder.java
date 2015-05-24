package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.*;
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

		return (MarketRequestBuilder) new MarketRequestBuilder(GET)
				.setResourceURL(marketClock(format.toString()));


	}
	
	
	public static MarketRequestBuilder getExtQuotes(ResponseFormat format, String symbols, String fields) throws Throwable { 
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(POST);
		mktReqBuilder.setResourceURL(extQuotes(format.toString(), symbols));
		return mktReqBuilder;
	}
	

	public static MarketRequestBuilder getOptionsExpirations (ResponseFormat format, String symbol) throws Throwable { 
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET);
		mktReqBuilder.setResourceURL(optionsExpirations(format.toString(), symbol));
		return mktReqBuilder;
	}
	
	public static MarketRequestBuilder getOptionsStrikes (ResponseFormat format, String symbol) throws Throwable { 
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET);
		mktReqBuilder.setResourceURL(optionsStrikes(format.toString(), symbol));
		return mktReqBuilder;
	}	



}

