package com.excelsiorsoft.gatherer.tradeking.connector;


import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketBuilder.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.xml;
import static org.junit.Assert.*;

import org.junit.Test;


import com.excelsiorsoft.gatherer.tradeking.connector.api.MarketBuilder;
import com.excelsiorsoft.gatherer.tradeking.parser.XmlHandler;




public class TradeKingForemanTest {

	@Test
	public void connectionTest()
	{
		TradeKingForeman foreman = new TradeKingForeman();
		assertTrue("Foreman does not have OAuth Service", !foreman.isConnected());
		
	}
	
	@Test
	public void marketClockApiCallTest() throws Throwable {
		
		TradeKingForeman foreman = new TradeKingForeman();
		

		System.out.println(foreman.makeAPICall(getClock(xml)).toString()); 

		String response = foreman.makeAPICall(MarketBuilder.getClock(xml)).toString();
		assertTrue("Foreman didn't recognize API reponse",response.contains("<message>"));
		
		XmlHandler handler = new XmlHandler();
		handler.parseMarketClock(response);
	}	
	
	@Test
	public void marketExtQuotesApiCallTest() throws Throwable {
		
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).toString());
		//System.out.println(foreman.makeAPICall(getExtQuotes(XML, "gasl, gasl150918C00004000 ", "")).toString());
		
	}
	
	@Test
	public void exploringResponse() throws Throwable {
		
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println("Calls remaining: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsRemaining());
		System.out.println("Calls used: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsUsed());
		System.out.println("Rate limit expiration: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getRateLimitExpiration());
		System.out.println("Total calls allowed: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getTotalCallsAllowed());
		
		System.out.println("\n..... repeating .....\n");
		
		System.out.println("Calls remaining: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsRemaining());
		System.out.println("Calls used: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getCallsUsed());
		System.out.println("Rate limit expiration: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getRateLimitExpiration());
		System.out.println("Total calls allowed: " + foreman.makeAPICall(getExtQuotes(xml, "slw, slw160115P00020000, slw160115P00021000 ", "")).getTotalCallsAllowed());
	}	
	
	
	@Test
	public void marketOptionsExpirationsApiCallTest() throws Throwable {
		
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeAPICall(getOptionsExpirations(xml, "slw ")).toString());
		
	}

}
