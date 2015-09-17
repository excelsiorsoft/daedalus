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

	private MarketRequestBuilder(Map<String, Object> context) {

		params = context;
	}

	
	public static MarketRequestBuilder getClock(final ResponseFormat format)
			throws Throwable {

		@SuppressWarnings("serial")
		Map<String, Object> context = new HashMap<String, Object>() {
			{
				put(FORMAT, format.toString());
				put(HTTP_METHOD, GET);
			}
		};

		return (MarketRequestBuilder) new MarketRequestBuilder(/*GET,*/ context)
					.setResourceURL(marketClock((context)));

	}

	public static MarketRequestBuilder getExtQuotes(ResponseFormat format,
			String symbols, String fields) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, Object> context = new HashMap<String, Object>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOLS, symbols);
				put(HTTP_METHOD, POST);
			}
		};

		return (MarketRequestBuilder) new MarketRequestBuilder(/*POST,*/ context)
					.setResourceURL(extQuotes((context)));

	}

	public static MarketRequestBuilder getOptionsExpirations(
			ResponseFormat format, String symbol) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, Object> context = new HashMap<String, Object>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOL, symbol);
				put(HTTP_METHOD, GET);
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(/*GET,*/ context);
		mktReqBuilder.setResourceURL(optionsExpirations(context));
		return mktReqBuilder;
	}

	public static MarketRequestBuilder getOptionsStrikes(ResponseFormat format,
			String symbol) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, Object> context = new HashMap<String, Object>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOL, symbol);
				put(HTTP_METHOD, GET);
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(/*GET, */context);
		mktReqBuilder.setResourceURL(optionsStrikes(context));
		return mktReqBuilder;
	}
	
	public static MarketRequestBuilder getOptionsStrikesForSymbolPerExpCycle(ResponseFormat format, String symbol) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, Object> context = new HashMap<String, Object>() {
			{
				put(FORMAT, format.toString());
				put(SYMBOL, symbol);
				put(EXPIRATION_DATE,"xdate-eq:20160115 AND put_call-eq:call");
				put(FIELDS,"fids=exch,strikeprice");
				//put("xdate","xdate-eq:20160115 AND put_call-eq:call");
				//put("xdate","xyear-eq:2016 AND xmonth-eq:01 AND xday-eq:01");
				put(HTTP_METHOD, POST);
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(context);
		mktReqBuilder.setResourceURL(optionsStrikesForSymbolPerExpCycle(context));
		return mktReqBuilder;
	}	
	
	
	public static MarketRequestBuilder getTopLosers(TopType typeOfLoser, ResponseFormat format) throws Throwable {

		@SuppressWarnings("serial")
		Map<String, Object> context = new HashMap<String, Object>() {
			{
				put(FORMAT, format.toString());
				put(LOSER_TYPE, typeOfLoser.toString());
				put(HTTP_METHOD, GET);
			}
		};
		
		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder( context);
		mktReqBuilder.setResourceURL(topLosers(context));
		return mktReqBuilder;
	}

}
