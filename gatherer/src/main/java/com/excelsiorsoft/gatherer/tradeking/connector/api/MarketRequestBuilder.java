package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.extQuotes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.marketClock;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.optionsExpirations;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.optionsStrikes;
import static org.scribe.model.Verb.GET;
import static org.scribe.model.Verb.POST;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.TKRequest.*;

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

	@SuppressWarnings("serial")
	public static MarketRequestBuilder getClock(final ResponseFormat format)
			throws Throwable {

		Map<String, String> context = new HashMap<String, String>() {
			{
				put(FORMAT, format.toString());
			}
		};

		return (MarketRequestBuilder) new MarketRequestBuilder(GET, context)
					.setResourceURL(marketClock((context/*.get(FORMAT)*/)));

	}

	public static MarketRequestBuilder getExtQuotes(ResponseFormat format,
			String symbols, String fields) throws Throwable {

		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(POST,
				null);
		mktReqBuilder.setResourceURL(extQuotes(format.toString(), symbols));
		return mktReqBuilder;
	}

	public static MarketRequestBuilder getOptionsExpirations(
			ResponseFormat format, String symbol) throws Throwable {

		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET, null);
		mktReqBuilder.setResourceURL(optionsExpirations(format.toString(),
				symbol));
		return mktReqBuilder;
	}

	public static MarketRequestBuilder getOptionsStrikes(ResponseFormat format,
			String symbol) throws Throwable {

		MarketRequestBuilder mktReqBuilder = new MarketRequestBuilder(GET, null);
		mktReqBuilder.setResourceURL(optionsStrikes(format.toString(), symbol));
		return mktReqBuilder;
	}

}
