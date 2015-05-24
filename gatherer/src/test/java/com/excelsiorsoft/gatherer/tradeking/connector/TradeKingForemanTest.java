package com.excelsiorsoft.gatherer.tradeking.connector;


import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.xml;
import static org.junit.Assert.*;

import org.junit.Test;

import com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder;
import com.excelsiorsoft.gatherer.tradeking.parser.XmlHandler;




public class TradeKingForemanTest {

	
	
	/*@Test
	public void connectionTest()
	{
		System.out.println("testing connection via TK OAuth...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		assertTrue("Foreman does not have OAuth Service", !foreman.isConnected());
		System.out.println("connected fine.");
		System.out.println("==============================");
		
	}*/
	
	@Test
	public void marketClockApiCall() throws Throwable {
		
		System.out.println("market/clock call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		

		System.out.println(foreman.makeApiCall(getClock(xml)).toString()); 

		String response = foreman.makeApiCall(MarketRequestBuilder.getClock(xml)).toString();
		assertTrue("Foreman didn't recognize API reponse",response.contains("<message>"));
		
		XmlHandler handler = new XmlHandler();
		handler.parseMarketClock(response);
		System.out.println("==============================");
	}	
	
	/*@Test
	public void marketExtQuotesApiCall() throws Throwable {
		
		System.out.println("market/ext/quotes call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).toString());
		//System.out.println(foreman.makeAPICall(getExtQuotes(XML, "gasl, gasl150918C00004000 ", "")).toString());
		System.out.println("==============================");
		
	}
	
	@Test
	public void exploringResponse() throws Throwable {
		
		System.out.println("exploring TK API response ...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println("Calls remaining: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsRemaining());
		System.out.println("Calls used: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsUsed());
		System.out.println("Rate limit expiration: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getRateLimitExpiration());
		System.out.println("Total calls allowed: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getTotalCallsAllowed());
		
		System.out.println("\n..... repeating .....\n");
		
		System.out.println("Calls remaining: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsRemaining());
		System.out.println("Calls used: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsUsed());
		System.out.println("Rate limit expiration: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getRateLimitExpiration());
		System.out.println("Total calls allowed: " + foreman.makeApiCall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getTotalCallsAllowed());
		System.out.println("==============================");
	}	
	
	
	@Test
	public void marketOptionsExpirationsApiCall() throws Throwable {
		
		System.out.println("market/options/expirations call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getOptionsExpirations(xml, "slw ")).toString());
		System.out.println("==============================");
		
	}
	
	@Test
	public void marketOptionsStrikesApiCall() throws Throwable {
		
		System.out.println("market/options/strikes call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeApiCall(getOptionsStrikes(xml, "slw ")).toString());
		System.out.println("==============================");
		
	}
*/
}
