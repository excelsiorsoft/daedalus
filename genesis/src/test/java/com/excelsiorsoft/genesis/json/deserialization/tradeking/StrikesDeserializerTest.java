package com.excelsiorsoft.genesis.json.deserialization.tradeking;

//import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.SYMBOL;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;

public class StrikesDeserializerTest {

	@Test
	public void parsingJsonIntoObjects() throws Throwable {
		
		String optionJsonStr = "{\"response\":{\"@id\":\"46d1d54c:14dc122964c:-5091\",\"prices\":{\"price\":[\"3\",\"5\",\"8\",\"9\",\"10\",\"11\",\"11.5\",\"12\",\"12.5\",\"13\",\"13.5\",\"14\",\"14.5\",\"15\",\"15.5\",\"16\",\"16.5\",\"17\",\"17.5\",\"18\",\"18.5\",\"19\",\"19.5\",\"20\",\"20.5\",\"21\",\"21.5\",\"22\",\"22.5\",\"23\",\"23.5\",\"24\",\"24.5\",\"25\",\"25.5\",\"26\",\"26.5\",\"27\",\"27.5\",\"28\",\"28.5\",\"29\",\"29.5\",\"30\",\"31\",\"32\",\"33\",\"34\",\"35\",\"36\",\"37\",\"38\",\"40\"]}}}";		
		ObjectMapper mapper = new ObjectMapper();
		
		
		JsonNode response = mapper.readTree(optionJsonStr).get("response");

		System.out.println("response/prices: " +response.path("prices"));
		System.out.println("response/prices/price: " + response.path("prices").path("price"));
		
		SimpleDeserializer<Strike> deserializer = new StrikesDeserializer();
		
		JsonNode prices = response.path("prices").path("price"); 
		
		System.out.println("isContainer: "+prices.isContainerNode());
		System.out.println("isArray: "+prices.isArray());
		

		Collection<Strike> result = deserializer.deserialize(prices, new HashMap<String,Object>(){{put(SYMBOL,"SLW");}});
		assertEquals("Expecting different # of deserialized objects", prices.size(), result.size());
		
		
	}

}
