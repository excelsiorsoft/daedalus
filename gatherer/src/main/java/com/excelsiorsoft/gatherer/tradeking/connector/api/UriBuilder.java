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
import static com.excelsiorsoft.gatherer.tradeking.connector.api.TKRequest.*;





/**
 * A builder for different sorts of TradeKing API calls - replacement for {@link ApiCall}
 * 
 * @author sleyzerzon
 *
 */
public class UriBuilder {
	

	private final static Logger LOGGER = LoggerFactory.getLogger(UriBuilder.class);
	
	public interface UriType {
		Verb getHttpMethod();
		String getTemplate(); 
	}

	/**
	 * This call will return the current state of the market, the time of the next state change (if the market is open), and the current server timestamp.
	 */	
	public static String marketClock(Map<String, String> params) throws Throwable	{
		
		/*Map<String, String> params = new HashMap<>();
		params.put("format", format);*/
		return buildUri( MARKET.CLOCK, params/*.get(FORMAT)*/);
		
	}
	
	/**
	 * This call will return quotes for a symbol or list of symbols passed as a query parameter (see query parameters below). While this request type is GET, POST can also be used and is recommended
	 * for larger lists of symbols.
	 * @param symbolsLst 
	 * @throws Throwable 
	 */
	public static String extQuotes(Map<String, String> params) throws Throwable {
		
		/*Map<String, String> params = new HashMap<>();
		params.put("symbols", symbolsLst);
		params.put("format", format);*/

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
	public static String optionsExpirations(Map<String, String> params) throws Throwable {
		
		/*Map<String, String> params = new HashMap<>();
		params.put("symbol", symbol);
		params.put("format", format);*/

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
	public static String optionsStrikes(Map<String, String> params) throws Throwable {
		
		/*Map<String, String> params = new HashMap<>();
		params.put("symbol", symbol);
		params.put("format", format);*/

		return buildUri(MARKET.OPTIONS_STRIKES, params);
	}	
	
	
	/**
	 * @param params
	 * @return
	 * @throws Throwable
	 */
	public static String optionsStrikesForSymbolPerExpCycle(Map<String, String> params) throws Throwable {
		
		/*Map<String, String> params = new HashMap<>();
		params.put("symbol", symbol);
		params.put("format", format);*/

		return buildUri(MARKET.OPTIONS_SEARCH, params);
	}	
	
	
	/**
	 * This call will return a ranked list based on the list type specified.
	 * 
	 * 			<li>toplosers	Top losers by dollar amount, corresponds to {@link TopType.BY_DOLLAR_AMOUNT}
	 *			<li>toppctlosers	Top percentage losers, corresponds to {@link TopType.BY_PERCENTAGE_AMOUNT}
	 * 
	 * @param typeOfTop 
	 * @param params
	 * @return
	 * @throws Throwable
	 */
	public static String topLosers(/*TopType typeOfTop, */Map<String, String> params) throws Throwable {
	
		String defaultLosersUri = buildUri(MARKET.TOPLISTS_LOSERS_DOLLAR, params);
		String typeOfTop = params.get(LOSER_TYPE);
		
		String result = (typeOfTop.equals(TopType.BY_DOLLAR_AMOUNT)) ? defaultLosersUri 	: 
						(typeOfTop.equals(TopType.BY_PERCENTAGE_AMOUNT)) ? buildUri(MARKET.TOPLISTS_LOSERS_PERCENTAGE, params)
						: defaultLosersUri;
			
		return result;
			
		}	
	
	
	public static String buildUri(UriType type, Map<String, String> params) throws Throwable {


		LOGGER.debug("Building a template with call of type {} and parameters {}\n", type, params);

		Configuration cfg = new Configuration();
		cfg.setObjectWrapper(BEANS_WRAPPER);
		cfg.setURLEscapingCharset("UTF-8");

		Template template = new Template("URLProducingTemplate",
				new StringReader(type.getTemplate()), cfg);

		Writer out = new StringWriter();
		template.process(params, out);

		String result = out.toString();
		LOGGER.debug("Done: {}", result);
		return result;
	}
	
	public enum MARKET implements UriType {

		CLOCK(GET, "https://api.tradeking.com/v1/market/clock.${"+FORMAT+"}"), 
		EXT_QUOTES(GET, "https://api.tradeking.com/v1/market/ext/quotes.${"+FORMAT+"}?symbols=${"+SYMBOLS+"?url}"), 
		STREAM_EXT_QUOTES(GET, "https://stream.tradeking.com/v1/market/quotes"), 
		NEWS_SEARCH(GET, "https://api.tradeking.com/v1/market/news/search"), 
		NEWS_ID(GET, "https://api.tradeking.com/v1/market/news/"), 
		
		//fields should not be URL encoded, date - should
		OPTIONS_SEARCH(GET, "https://api.tradeking.com/v1/market/options/search.${"+FORMAT+"}?symbol=${"+SYMBOL+"?url}&query=${xdate?url}&${fields}"), 
		OPTIONS_STRIKES(GET, "https://api.tradeking.com/v1/market/options/strikes.${"+FORMAT+"}?symbol=${"+SYMBOL+"?url}"), 
		OPTIONS_EXPIRATIONS(GET, "https://api.tradeking.com/v1/market/options/expirations.${"+FORMAT+"}?symbol=${"+SYMBOL+"?url}"), 
		TIMESALES(GET, "https://api.tradeking.com/v1/market/timesales"), 
		TOPLISTS_VOLUME(GET, "https://api.tradeking.com/v1/market/toplists/topvolume"), 
		TOPLISTS_LOSERS_DOLLAR(GET, "https://api.tradeking.com/v1/market/toplists/toplosers.${"+FORMAT+"}"), 
		TOPLISTS_LOSERS_PERCENTAGE(GET, "https://api.tradeking.com/v1/market/toplists/toppctlosers.${"+FORMAT+"}"), 
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
		



