package com.excelsiorsoft.gatherer.tradeking.connector.api;


import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;
import static org.scribe.model.Verb.GET;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.scribe.model.Verb;

import freemarker.template.Configuration;
import freemarker.template.Template;




/**
 * A builder for different sorts of TradeKing API calls - replacement for {@link ApiCall}
 * 
 * @author sleyzerzon
 *
 */
public class ApiCalls {
	

	public interface CallType {
		Verb getHttpMethod();
		String getTemplate(); 
	}

	/**
	 * This call will return the current state of the market, the time of the next state change (if the market is open), and the current server timestamp.
	 */	
	public static String getMarketClock(String format) throws Throwable	{
		return resolveString( MARKET.CLOCK, null,format);
	}
	
	/**
	 * This call will return quotes for a symbol or list of symbols passed as a query parameter (see query parameters below). While this request type is GET, POST can also be used and is recommended
	 * for larger lists of symbols.
	 * @throws Throwable 
	 */
	public static String getQuote(ResponseFormat format) throws Throwable 	{
		
		Map<String, String> params = new HashMap<>();
		params.put("symbols", "SLW");
		return resolveString(MARKET.EXT_QUOTES, params, format.toString());
	}
	
	
	public static String resolveString(CallType type, Map<String, String> params, String format) throws Throwable {

		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(BEANS_WRAPPER);

		Template template = new Template("URLProducingTemplate",
				new StringReader(type.getTemplate()), cfg);

		Writer out = new StringWriter();
		template.process(params, out);

		return out.toString();
	}
	
	public enum MARKET implements CallType	{
		CLOCK(GET, "https://api.tradeking.com/v1/market/clock"), 
		EXT_QUOTES(GET, "https://api.tradeking.com/v1/market/ext/quotes"), 
		STREAM_EXT_QUOTES(GET, "https://stream.tradeking.com/v1/market/quotes"), 
		NEWS_SEARCH(GET, "https://api.tradeking.com/v1/market/news/search"), 
		NEWS_ID(GET, "https://api.tradeking.com/v1/market/news/"), 
		OPTIONS_SEARCH(GET, "https://api.tradeking.com/v1/market/options/search"), 
		OPTIONS_STRIKES(GET, "https://api.tradeking.com/v1/market/options/strikes"), 
		OPTIONS_EXPIRATIONS(GET, "https://api.tradeking.com/v1/market/options/expirations"), 
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

		MARKET(Verb httpMethod, String template){
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
		



