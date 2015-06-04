package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.SYMBOL;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StrikesDeserializerTest {

	@Test
	public void parsingJsonIntoObjects() throws Throwable {
		
		String optionJsonStr = "{\"response\":{\"@id\":\"-53fd9e88:14dbee75e77:-1d2e\",\"expirationdates\":{\"date\":[\"2015-06-05\",\"2015-06-12\",\"2015-06-19\",\"2015-06-26\",\"2015-07-02\",\"2015-07-10\",\"2015-07-17\",\"2015-07-24\",\"2015-08-21\",\"2015-09-18\",\"2015-12-18\",\"2016-01-15\",\"2017-01-20\"]}}}";		
		ObjectMapper mapper = new ObjectMapper();
		
		
		JsonNode response = mapper.readTree(optionJsonStr).get("response");

		System.out.println("response/expirationdates: " +response.path("expirationdates"));
		System.out.println("response/expirationdates/date: " + response.path("expirationdates").path("date"));
		
		SimpleDeserializer<ExpirationDate> deserializer = new ExpirationDateDeserializer();
		
		JsonNode dates = response.path("expirationdates").path("date"); 
		
		System.out.println("isContainer: "+dates.isContainerNode());
		System.out.println("isArray: "+dates.isArray());
		

		Collection<ExpirationDate> result = deserializer.deserialize(dates, new HashMap<String,Object>(){{put(SYMBOL,"SLW");}});
		assertEquals("Expecting different # of deserialized objects", dates.size(), result.size());
		
		
	}

}
