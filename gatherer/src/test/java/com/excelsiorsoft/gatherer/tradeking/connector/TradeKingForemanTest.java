package com.excelsiorsoft.gatherer.tradeking.connector;


import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getExtQuotes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsExpirations;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsStrikes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsStrikesForSymbolPerExpCycle;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getTopLosers;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.json;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.xml;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.util.Significant;
import com.excelsiorsoft.gatherer.Application;
import com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder;
import com.excelsiorsoft.gatherer.tradeking.connector.api.TKRequest.TopType;
import com.excelsiorsoft.gatherer.tradeking.parser.XmlHandler;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.OptionDeserializer;



@Significant
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)*/
public class TradeKingForemanTest {
	
	
	@Test
	public void connectionTest()
	{
		System.out.println("testing connection via TK OAuth...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		assertTrue("Foreman does not have OAuth Service", !foreman.isConnected());
		System.out.println("connected fine.");
		System.out.println("==============================");
		
	}
	
	@Test
	public void marketClockApiCall() throws Throwable {
		
		System.out.println("market/clock call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		
		String response = foreman.makeApiCall(MarketRequestBuilder.getClock(xml)).getResponse().toString();
		assertTrue("Foreman didn't recognize API reponse",response.contains("<message>"));
		System.out.println(response);
		
		XmlHandler handler = new XmlHandler();
		handler.parseMarketClock(response);
		System.out.println("==============================");
	}	
	
	@Test
	public void marketExtQuotesApiCall() throws Throwable {
		
		System.out.println("market/ext/quotes call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getResponse());
		System.out.println("single option: "+foreman.makeApiCall(getExtQuotes(json, "slw180119P00020000", "")).getResponse());
		System.out.println("several options: "+foreman.makeApiCall(getExtQuotes(json, "slw180119P00017000, slw180119P00020000", "")).getResponse());
		//System.out.println(foreman.makeAPICall(getExtQuotes(XML, "gasl, gasl150918C00004000 ", "")).toString());
		System.out.println("==============================");
		
	}
	
	@Test
	public void exploringResponse() throws Throwable {
		
		System.out.println("exploring TK API response ...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println("Calls remaining: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getCallsRemaining());
		System.out.println("Calls used: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getCallsUsed());
		System.out.println("Rate limit expiration: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getRateLimitExpiration());
		System.out.println("Total calls allowed: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getTotalCallsAllowed());
		
		System.out.println("\n..... repeating .....\n");
		
		System.out.println("Calls remaining: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getCallsRemaining());
		System.out.println("Calls used: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getCallsUsed());
		System.out.println("Rate limit expiration: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getRateLimitExpiration());
		System.out.println("Total calls allowed: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw180119P00017000, slw180119P00020000 ", "")).getTotalCallsAllowed());
		System.out.println("==============================");
	}	
	
	
	@Test
	public void marketOptionsExpirationsApiCall() throws Throwable {
		
		System.out.println("market/options/expirations call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		//System.out.println(foreman.makeApiCall(getOptionsExpirations(xml, "slw")).getResponse());
		
		
		String response = foreman.makeApiCall(getOptionsExpirations(xml, "slw ")).getResponse();
		System.out.println(response);
		assertTrue("TK response doesn't contains exp <date> tag",response.contains("<date>"));
		
		response = foreman.makeApiCall(getOptionsExpirations(json, "slw ")).getResponse();
		System.out.println(response);
		assertTrue("TK response doesn't contains exp <date> tag",response.contains("\"date\""));
		System.out.println("==============================");
		
	}

	@Test
	public void marketOptionsExpirationsWithMultitTableausApiCall() throws Throwable {
		
		System.out.println("market/options/expirations call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getOptionsExpirations(xml, "uvxy ")).getResponse());
		System.out.println(foreman.makeApiCall(getOptionsExpirations(json, "uvxy ")).getResponse());
		System.out.println("==============================");
		
	}
	
	@Test
	public void marketOptionsStrikesApiCall() throws Throwable {
		
		System.out.println("market/options/strikes call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		
		String response = foreman.makeApiCall(getOptionsStrikes(xml, "SLW ")).getResponse();
		System.out.println(response);
		assertTrue("TK response doesn't contains exp <price> tag",response.contains("<price>"));
		
		response = foreman.makeApiCall(getOptionsStrikes(json, "slw ")).getResponse();
		System.out.println(response);
		assertTrue("TK response doesn't contains exp <price> tag",response.contains("\"price\""));
		System.out.println("==============================");
		
	}
	
	
	@Test
	public void marketOptionsStrikesForSymbolPerExpirationCycleApiCall() throws Throwable {
		
		System.out.println("market/options/search call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();

		System.out.println(foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(xml, "slw", "20180119")).getResponse());
		System.out.println(foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(json, "SLW ", "20180119")).getResponse());
		System.out.println("==============================");
		
	}
	
	@Test
	public void marketOptionsStrikesForSymbolPerExpirationCycleApiCall_NLY() throws Throwable {
		
		//Experiencing strange behavior described here: http://community.tradeking.com/groups/tradeking-api/forum/topics/11355-missing-data-for-a-particular-query/forum_posts
		
		System.out.println("market/options/search call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();

		//System.out.println(foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(xml, "slw", "20180119")).getResponse());
		System.out.println(foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(json, "NLY ", "20160506")).getResponse());
		System.out.println(foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(json, "NLY ", "20160513")).getResponse()); //<-- missing data, why?
		System.out.println("==============================");
		
	}	
	
	
	@Test
	public void marketTopListsApiCall_xml() throws Throwable {
		
		System.out.println("market/toplists losers by $ call (xml)...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getTopLosers(TopType.BY_DOLLAR_AMOUNT, xml)).getResponse());
		System.out.println("==============================");
		
		
		System.out.println("market/toplists losers by % call (xml)...");
		System.out.println("==============================");
		System.out.println(foreman.makeApiCall(getTopLosers(TopType.BY_PERCENTAGE_AMOUNT, xml)).getResponse());
		System.out.println("==============================");
		
	}
	
	@Test
	public void marketTopListsApiCall_json() throws Throwable {
		
		System.out.println("market/toplists losers by $ call (json)...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getTopLosers(TopType.BY_DOLLAR_AMOUNT, json)).getResponse());
		System.out.println("==============================");
		
		
		System.out.println("market/toplists losers by % call (json)...");
		System.out.println("==============================");
		System.out.println(foreman.makeApiCall(getTopLosers(TopType.BY_PERCENTAGE_AMOUNT, json)).getResponse());
		System.out.println("==============================");
		
	}
	
	@Test
	public void marketExtQuotesApiCallMultiOptionDeserialization() throws Throwable {
		
		
		System.out.println("market/clock call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		
		String response = foreman.makeApiCall(MarketRequestBuilder.getClock(xml)).getResponse().toString();
		assertTrue("Foreman didn't recognize API reponse",response.contains("<message>"));
		System.out.println(response);
		
		XmlHandler handler = new XmlHandler();
		handler.parseMarketClock(response);
		System.out.println("==============================");
		
		
		
		System.out.println("market/ext/quotes call...");
		System.out.println("==============================");

		
		String optionJsonStr = foreman.makeApiCall(getExtQuotes(json, "slw180119P00017000, slw160115P00020000, slw160115P00025000 ", "")).getResponse();
		System.out.println("several options: "+optionJsonStr);

		Collection<Option> result = new OptionDeserializer().deserialize(optionJsonStr, new HashMap());
		//assertEquals("Expecting different # of deserialized objects", quotes.size(), result.size());
		System.out.println("==============================");
		
	}

}
