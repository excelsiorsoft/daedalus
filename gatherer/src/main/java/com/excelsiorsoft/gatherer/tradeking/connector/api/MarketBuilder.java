package com.excelsiorsoft.gatherer.tradeking.connector.api;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.XML;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.model.MarketQuotesField.FIDS;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.model.MarketQuotesField.SYMBOLS;
import static org.scribe.model.Verb.POST;

import org.scribe.model.Verb;


import com.excelsiorsoft.gatherer.tradeking.connector.api.model.MarketQuotesResponseField;



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

	public static MarketBuilder getClock(ResponseFormat format)
	{
		MarketBuilder b = new MarketBuilder(Verb.GET);
		//b.resourceURL = ApiCall.getMarketClock(format);
		b.resourceURL = ApiCalls.getMarketClock(format.toString());
		return b;
	}

	public static MarketBuilder getQuotes(ResponseFormat format, String... symbols)
	{
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
	}

}

