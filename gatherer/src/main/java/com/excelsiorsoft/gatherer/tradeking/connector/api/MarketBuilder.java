package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static org.scribe.model.Verb.GET;
import static org.scribe.model.Verb.POST;

import org.scribe.model.Verb;



/**
 * An APIBuilder to handle TradeKing Market calls
 * 
 * @author sleyzerzon
 *
 */
public class MarketBuilder extends ApiBuilder
{
	private static final long serialVersionUID = -7542591696724178699L;

	private MarketBuilder(Verb v)
	{
		verb = v;
	}

	public static MarketBuilder getClock(ResponseFormat format) throws Throwable {
		
		MarketBuilder marketBuilder = new MarketBuilder(GET);
		//b.resourceURL = ApiCall.getMarketClock(format);
		marketBuilder.resourceURL = ApiCalls.getMarketClock(format.toString());
		return marketBuilder;
	}
	
	
	public static MarketBuilder getExtQuotes(ResponseFormat format, String symbols, String fields) throws Throwable { 
		
		MarketBuilder marketBuilder = new MarketBuilder(POST);
		marketBuilder.resourceURL = ApiCalls.getExtQuotes(format.toString(), symbols);
		return marketBuilder;
	}

/*	public static MarketBuilder getQuotes(ResponseFormat format, String... symbols) {
		return getQuotes(format, symbols, new MarketQuotesResponseField[] {});
	}

	public static MarketBuilder getQuotes(ResponseFormat format, String[] symbols, MarketQuotesResponseField[] fields)
	{
		StringBuilder sb = new StringBuilder();
		for (String sym : symbols)
		{
			sb.append(sym + " ");
		}
		StringBuilder fids = new StringBuilder();
		for (MarketQuotesResponseField f : fields)
		{
			fids.append(f.toString() + " ");
		}
		MarketBuilder b = new MarketBuilder(POST);
		b.params.put(SYMBOLS.toString(), sb.toString().trim().replace(" ", ","));
		b.resourceURL = ApiCall.getQuote(XML);
		if (!fids.toString().isEmpty())
		{
			b.params.put(FIDS.toString(), fids.toString().trim().replace(" ", ","));
		}
		return b;
	}*/

}

