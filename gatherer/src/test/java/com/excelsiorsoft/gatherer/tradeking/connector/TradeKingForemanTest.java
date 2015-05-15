package com.excelsiorsoft.gatherer.tradeking.connector;


import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketBuilder.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.XML;
import static org.junit.Assert.*;

import org.junit.Test;

import com.excelsiorsoft.gatherer.tradeking.connector.api.MarketBuilder;
import com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat;



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
		

		System.out.println(foreman.makeAPICall(getClock(XML)).toString()); 

		assertTrue("Foreman didn't recognize API reponse",foreman.makeAPICall(MarketBuilder.getClock(XML)).toString().contains("<message>"));
	}	
	
	@Test
	public void marketExtQuotesApiCallTest() throws Throwable {
		
		TradeKingForeman foreman = new TradeKingForeman();
		System.out.println(foreman.makeAPICall(getExtQuotes(XML, "", "")).toString());
		
	}	

}
