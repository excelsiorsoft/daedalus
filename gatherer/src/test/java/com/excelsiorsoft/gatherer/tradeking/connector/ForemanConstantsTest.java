package com.excelsiorsoft.gatherer.tradeking.connector;

import static org.junit.Assert.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.*;
import org.junit.Test;



public class ForemanConstantsTest {

	@Test
	public void test()
	{
		assertTrue(!API_KEY.toString().isEmpty());
		assertTrue(!API_SECRET.toString().isEmpty());
		assertTrue(!ACCESS_TOKEN.toString().isEmpty());
		assertTrue(!ACCESS_TOKEN_SECRET.toString().isEmpty());
	}

}
