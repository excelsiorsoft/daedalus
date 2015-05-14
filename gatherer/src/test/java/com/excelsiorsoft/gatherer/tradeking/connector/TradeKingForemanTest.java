package com.excelsiorsoft.gatherer.tradeking.connector;

import static org.junit.Assert.*;

import org.junit.Test;


public class TradeKingForemanTest {

	@Test
	public void connectionTest()
	{
		TradeKingForeman foreman = new TradeKingForeman();
		assertTrue("Foreman does not have OAuth Service", !foreman.isConnected());
		
	}

}
