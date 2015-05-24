package com.excelsiorsoft.gatherer.tradeking.connector.api;


import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;
import static org.scribe.model.Verb.GET;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.scribe.model.Verb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;




/**
 * A builder for different sorts of TradeKing API calls - replacement for {@link ApiCall}
 * 
 * @author sleyzerzon
 *
 */
public class UriBuilder {
	

	private final static Logger LOGGER = LoggerFactory.getLogger(UriBuilder.class);
	
	public interface CallType {
		Verb getHttpMethod();
		String getTemplate(); 
	}

	/**
	 * This call will return the current state of the market, the time of the next state change (if the market is open), and the current server timestamp.
	 */	
	public static String marketClock(String format) throws Throwable	{
		
		Map<String, Object> params = new HashMap<>();
		params.put("format", format);
		return buildUri( MARKET.CLOCK, params);
		
	}
	
	/**
	 * This call will return quotes for a symbol or list of symbols passed as a query parameter (see query parameters below). While this request type is GET, POST can also be used and is recommended
	 * for larger lists of symbols.
	 * @param symbolsLst 
	 * @throws Throwable 
	 */
	public static String extQuotes(String format, String symbolsLst) throws Throwable {
		
		Map<String, Object> params = new HashMap<>();
		params.put("symbols", symbolsLst);
		params.put("format", format);

		return buildUri(MARKET.EXT_QUOTES, params);
	}
	
	
	/**
	 * This call will return the full list of available option expiration dates for a given symbol.</br>
	 * Expiration dates are returned in the format of YYYY-MM-DD
	 * 
	 * @param format
	 * @param symbol a single symbol for which options are traded
	 * @return
	 * @throws Throwable
	 */
	public static String optionsExpirations(String format, String symbol) throws Throwable {
		
		Map<String, Object> params = new HashMap<>();
		params.put("symbol", symbol);
		params.put("format", format);

		return buildUri(MARKET.OPTIONS_EXPIRATIONS, params);
	}
	
	/**
	 * This call will return the full list of available option strikes for a given symbol.
	 * 
	 * @param format
	 * @param symbol a single symbol for which options are traded
	 * @return
	 * @throws Throwable
	 */
	public static String optionsStrikes(String format, String symbol) throws Throwable {
		
		Map<String, Object> params = new HashMap<>();
		params.put("symbol", symbol);
		params.put("format", format);

		return buildUri(MARKET.OPTIONS_STRIKES, params);
	}		
	
	
	public static String buildUri(CallType type, Map<String, Object> params) throws Throwable {


		LOGGER.trace("Building a template with call of type {} and parameters {}\n", type, params);

		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(BEANS_WRAPPER);
		cfg.setURLEscapingCharset("UTF-8");

		Template template = new Template("URLProducingTemplate",
				new StringReader(type.getTemplate()), cfg);

		Writer out = new StringWriter();
		template.process(params, out);

		String result = out.toString();
		LOGGER.trace("Done: {}", result);
		return result;
	}
	
	public enum MARKET implements CallType {
		CLOCK(GET, "https://api.tradeking.com/v1/market/clock.${format}"), 
		EXT_QUOTES(GET, "https://api.tradeking.com/v1/market/ext/quotes.${format}?symbols=${symbols?url}"), 
		STREAM_EXT_QUOTES(GET, "https://stream.tradeking.com/v1/market/quotes"), 
		NEWS_SEARCH(GET, "https://api.tradeking.com/v1/market/news/search"), 
		NEWS_ID(GET, "https://api.tradeking.com/v1/market/news/"), 
		OPTIONS_SEARCH(GET, "https://api.tradeking.com/v1/market/options/search"), 
		OPTIONS_STRIKES(GET, "https://api.tradeking.com/v1/market/options/strikes.${format}?symbol=${symbol?url}"), 
		OPTIONS_EXPIRATIONS(GET, "https://api.tradeking.com/v1/market/options/expirations.${format}?symbol=${symbol?url}"), 
		TIMESALES(GET, "https://api.tradeking.com/v1/market/timesales"), 
		TOPLISTS_VOLUME(GET, "https://api.tradeking.com/v1/market/toplists/topvolume"), 
		TOPLISTS_LOSERS_DOLLAR(GET, "https://api.tradeking.com/v1/market/toplists/toplosers"), 
		TOPLISTS_LOSERS_PERCENTAGE(GET, "https://api.tradeking.com/v1/market/toplists/toppctlosers"), 
		TOPLISTS_ACTIVE(GET, "https://api.tradeking.com/v1/market/toplists/topactive"), 
		TOPLISTS_GAINERS_DOLLAR_AMT(GET, "https://api.tradeking.com/v1/market/toplists/topgainers"), 
		TOPLISTS_GAINERS_PERCENTAGE(GET, "https://api.tradeking.com/v1/market/toplists/toppctgainers"), 
		TOPLISTS_GAINERS_ACTIVE_DOLLAR_AMT(GET, "https://api.tradeking.com/v1/market/toplists/topactivegainersbydollarvalue");

		private Verb httpMethod;
		private String template;

		MARKET(Verb httpMethod, String template) {
			this.httpMethod = httpMethod;
			this.template = template;
		
		}
		
		public Verb getHttpMethod() {
			return httpMethod;
		}

		public String getTemplate() {
			return template;
		}

	}
}
		



