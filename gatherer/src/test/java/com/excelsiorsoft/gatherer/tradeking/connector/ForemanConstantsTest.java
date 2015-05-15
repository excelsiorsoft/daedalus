package com.excelsiorsoft.gatherer.tradeking.connector;

import static org.junit.Assert.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.*;
import org.junit.Test;



public class ForemanConstantsTest {

	@Test
	public void test()
	{
		assertTrue(!CONSUMER_KEY.toString().isEmpty());
		assertTrue(!CONSUMER_SECRET.toString().isEmpty());
		assertTrue(!OAUTH_TOKEN.toString().isEmpty());
		assertTrue(!OAUTH_TOKEN_SECRET.toString().isEmpty());
	}

}
