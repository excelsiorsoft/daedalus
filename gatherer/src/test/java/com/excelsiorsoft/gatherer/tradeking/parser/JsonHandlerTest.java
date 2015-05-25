package com.excelsiorsoft.gatherer.tradeking.parser;

import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getExtQuotes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.json;

import org.junit.Test;

import com.excelsiorsoft.gatherer.tradeking.connector.TradeKingForeman;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class JsonHandlerTest {
	
	@Test
	public void marketExtQuotesApiCall() throws Throwable {
		
		System.out.println("market/ext/quotes call...");
		System.out.println("==============================");
		TradeKingForeman foreman = new TradeKingForeman();
		String jsonStr = foreman.makeApiCall(getExtQuotes(json, "slw, slw160115P00020000, slw160115P00021000 ", "")).getResponse();
		System.out.println(jsonStr);
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonStr);
		String firstAsk = JsonPath.read(document, "$.response.quotes.quote[0].ask");
		System.out.println(firstAsk);
		
		System.out.println("==============================");
		
	}

}
