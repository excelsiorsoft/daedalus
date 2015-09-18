package com.excelsiorsoft.gatherer.tradeking.connector.api;


import static com.excelsiorsoft.gatherer.tradeking.connector.api.TKRequest.*;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.UriBuilder.MARKET.*;
import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.gatherer.tradeking.connector.api.TKRequest.TopType;

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
	
	public interface UriType {
		String getTemplate(); 
	}

	/**
	 * This call will return the current state of the market, the time of the next state change (if the market is open), and the current server timestamp.
	 */	
	public static String marketClock(Map<String, Object> params) throws Throwable	{

		return buildUri( CLOCK, params);
		
	}
	
	/**
	 * This call will return quotes for a symbol or list of symbols passed as a query parameter (see query parameters below). While this request type is GET, POST can also be used and is recommended
	 * for larger lists of symbols.
	 * @param symbolsLst 
	 * @throws Throwable 
	 */
	public static String extQuotes(Map<String, Object> params) throws Throwable {

		return buildUri(EXT_QUOTES, params);
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
	public static String optionsExpirations(Map<String, Object> params) throws Throwable {

		return buildUri(OPTIONS_EXPIRATIONS, params);
	}
	
	/**
	 * This call will return the full list of available option strikes for a given symbol.
	 * 
	 * @param format
	 * @param symbol a single symbol for which options are traded
	 * @return
	 * @throws Throwable
	 */
	public static String optionsStrikes(Map<String, Object> params) throws Throwable {

		return buildUri(OPTIONS_STRIKES, params);
	}	
	
	
	/**
	 * @param params
	 * @return
	 * @throws Throwable
	 */
	public static String optionsStrikesForSymbolPerExpCycle(Map<String, Object> params) throws Throwable {

		return buildUri(OPTIONS_SEARCH_STRIKES_PER_SYMBOL_FOR_EXP_CYCLE, params);
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
	public static String topLosers(Map<String, Object> params) throws Throwable {
	
		String defaultLosersUri = buildUri(MARKET.TOPLISTS_LOSERS_DOLLAR, params);
		String typeOfTop = (String) params.get(LOSER_TYPE);
		
		String result = (typeOfTop.equals(TopType.BY_DOLLAR_AMOUNT)) ? defaultLosersUri 	: 
						(typeOfTop.equals(TopType.BY_PERCENTAGE_AMOUNT)) ? buildUri(MARKET.TOPLISTS_LOSERS_PERCENTAGE, params)
						: defaultLosersUri;
			
		return result;
			
		}	
	
	
	public static String buildUri(UriType type, Map<String, Object> params) throws Throwable {


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

		CLOCK("https://api.tradeking.com/v1/market/clock.${"+FORMAT+"}"), 
		EXT_QUOTES("https://api.tradeking.com/v1/market/ext/quotes.${"+FORMAT+"}?symbols=${"+SYMBOLS+"?url}"), 
		STREAM_EXT_QUOTES("https://stream.tradeking.com/v1/market/quotes"), 
		NEWS_SEARCH("https://api.tradeking.com/v1/market/news/search"), 
		NEWS_ID("https://api.tradeking.com/v1/market/news/"), 
		
		//fields should not be URL encoded, date - should
		OPTIONS_SEARCH_STRIKES_PER_SYMBOL_FOR_EXP_CYCLE("https://api.tradeking.com/v1/market/options/search.${"+FORMAT+"}?symbol=${"+SYMBOL+"?url}&query=${"+EXPIRATION_DATE+"?url}&${"+FIELDS+"}"), 
		OPTIONS_STRIKES("https://api.tradeking.com/v1/market/options/strikes.${"+FORMAT+"}?symbol=${"+SYMBOL+"?url}"), 
		OPTIONS_EXPIRATIONS("https://api.tradeking.com/v1/market/options/expirations.${"+FORMAT+"}?symbol=${"+SYMBOL+"?url}"), 
		TIMESALES("https://api.tradeking.com/v1/market/timesales"), 
		TOPLISTS_VOLUME("https://api.tradeking.com/v1/market/toplists/topvolume"), 
		TOPLISTS_LOSERS_DOLLAR("https://api.tradeking.com/v1/market/toplists/toplosers.${"+FORMAT+"}"), 
		TOPLISTS_LOSERS_PERCENTAGE("https://api.tradeking.com/v1/market/toplists/toppctlosers.${"+FORMAT+"}"), 
		TOPLISTS_ACTIVE("https://api.tradeking.com/v1/market/toplists/topactive"), 
		TOPLISTS_GAINERS_DOLLAR_AMT("https://api.tradeking.com/v1/market/toplists/topgainers"), 
		TOPLISTS_GAINERS_PERCENTAGE("https://api.tradeking.com/v1/market/toplists/toppctgainers"), 
		TOPLISTS_GAINERS_ACTIVE_DOLLAR_AMT("https://api.tradeking.com/v1/market/toplists/topactivegainersbydollarvalue");

		private String template;

		MARKET(String template) {

			this.template = template;
		
		}
		
		
		public String getTemplate() {
			return template;
		}

	}
}
		



