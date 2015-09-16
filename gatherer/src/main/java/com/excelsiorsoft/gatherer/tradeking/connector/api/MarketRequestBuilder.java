package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.extQuotes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.marketClock;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.optionsExpirations;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.optionsStrikes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.optionsStrikesForSymbolPerExpCycle;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.topLosers;
import static org.scribe.model.Verb.GET;
import static org.scribe.model.Verb.POST;

import java.util.HashMap;
import java.util.Map;

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

	private final static Logger LOGGER = LoggerFactory
			.getLogger(MarketRequestBuilder.class);

	private static final long serialVersionUID = -7542591696724178699L;

	private MarketRequestBuilder(Verb v, Map<String, String> context) {
		verb = v;
		params = context;
	}

	
	public static MarketRequestBuilder getClock(final ResponseFormat format)
			throws Throwable {

		@SuppressWarnings("serial")
		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
			}
		};

		return (MarketRequestBuilder) new MarketRequestBuilder(GET, context)
					.setResourceURL(marketClock((context)));

	}

	public static MarketRequestBuilder getExtQuotes(ResponseFormat format,
			String symbols, String fields) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOLS, symbols);
			}
		};

		return (MarketRequestBuilder) new MarketRequestBuilder(POST, context)
					.setResourceURL(extQuotes((context)));

	}

	public static MarketRequestBuilder getOptionsExpirations(
			ResponseFormat format, String symbol) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOL, symbol);
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET, context);
		mktReqBuilder.setResourceURL(optionsExpirations(context));
		return mktReqBuilder;
	}

	public static MarketRequestBuilder getOptionsStrikes(ResponseFormat format,
			String symbol) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOL, symbol);
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET, context);
		mktReqBuilder.setResourceURL(optionsStrikes(context));
		return mktReqBuilder;
	}
	
	public static MarketRequestBuilder getOptionsStrikesForSymbolPerExpCycle(ResponseFormat format, String symbol) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOL, symbol);
				put("xdate","xdate-eq:20160115 AND put_call-eq:call");
				put("fields","fids=exch,strikeprice");
				//put("xdate","xdate-eq:20160115 AND put_call-eq:call");
				//put("xdate","xyear-eq:2016 AND xmonth-eq:01 AND xday-eq:01");
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(POST, context);
		//MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET, context);
		mktReqBuilder.setResourceURL(optionsStrikesForSymbolPerExpCycle(context));
		return mktReqBuilder;
	}	
	
	
	public static MarketRequestBuilder getTopLosers(TopType typeOfLoser, ResponseFormat format/*,
			String symbol*/) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
				put(LOSER_TYPE, typeOfLoser.toString());
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET, context);
		mktReqBuilder.setResourceURL(topLosers(context));
		return mktReqBuilder;
	}

}
