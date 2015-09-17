package com.excelsiorsoft.genesis.json.deserialization.tradeking;

//import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.SYMBOL;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

public class StrikesPerExpirationDateDeserializerTest {

	@Test
	public void parsingJsonIntoObjects() throws Throwable {
		
		String optionJsonStr = "{\"response\":{\"@id\":\"6b0450c2:14fe06fb988:207a\",\"quotes\":{\"quote\":[{\"exch\":\"OPRA\",\"strikeprice\":\"35.00\",\"symbol\":\"SLW150918C00035000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"34.00\",\"symbol\":\"SLW150918C00034000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"11.00\",\"symbol\":\"SLW150918C00011000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"32.00\",\"symbol\":\"SLW150918C00032000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"31.00\",\"symbol\":\"SLW150918C00031000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"14.00\",\"symbol\":\"SLW150918C00014000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"15.00\",\"symbol\":\"SLW150918C00015000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"16.00\",\"symbol\":\"SLW150918C00016000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"17.00\",\"symbol\":\"SLW150918C00017000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"18.00\",\"symbol\":\"SLW150918C00018000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"19.00\",\"symbol\":\"SLW150918C00019000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"20.00\",\"symbol\":\"SLW150918C00020000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"21.00\",\"symbol\":\"SLW150918C00021000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"22.00\",\"symbol\":\"SLW150918C00022000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"23.00\",\"symbol\":\"SLW150918C00023000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"24.00\",\"symbol\":\"SLW150918C00024000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"25.00\",\"symbol\":\"SLW150918C00025000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"26.00\",\"symbol\":\"SLW150918C00026000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"27.00\",\"symbol\":\"SLW150918C00027000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"28.00\",\"symbol\":\"SLW150918C00028000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"29.00\",\"symbol\":\"SLW150918C00029000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"30.00\",\"symbol\":\"SLW150918C00030000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"12.00\",\"symbol\":\"SLW150918C00012000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"13.00\",\"symbol\":\"SLW150918C00013000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"33.00\",\"symbol\":\"SLW150918C00033000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"10.00\",\"symbol\":\"SLW150918C00010000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"9.00\",\"symbol\":\"SLW150918C00009000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"8.00\",\"symbol\":\"SLW150918C00008000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"7.00\",\"symbol\":\"SLW150918C00007000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"6.00\",\"symbol\":\"SLW150918C00006000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"10.50\",\"symbol\":\"SLW150918C00010500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"11.50\",\"symbol\":\"SLW150918C00011500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"12.50\",\"symbol\":\"SLW150918C00012500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"13.50\",\"symbol\":\"SLW150918C00013500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"14.50\",\"symbol\":\"SLW150918C00014500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"15.50\",\"symbol\":\"SLW150918C00015500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"16.50\",\"symbol\":\"SLW150918C00016500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"17.50\",\"symbol\":\"SLW150918C00017500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"18.50\",\"symbol\":\"SLW150918C00018500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"19.50\",\"symbol\":\"SLW150918C00019500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"20.50\",\"symbol\":\"SLW150918C00020500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"21.50\",\"symbol\":\"SLW150918C00021500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"22.50\",\"symbol\":\"SLW150918C00022500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"23.50\",\"symbol\":\"SLW150918C00023500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"24.50\",\"symbol\":\"SLW150918C00024500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"5.00\",\"symbol\":\"SLW150918C00005000\"},{\"exch\":\"OPRA\",\"strikeprice\":\"6.50\",\"symbol\":\"SLW150918C00006500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"7.50\",\"symbol\":\"SLW150918C00007500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"8.50\",\"symbol\":\"SLW150918C00008500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"9.50\",\"symbol\":\"SLW150918C00009500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"4.50\",\"symbol\":\"SLW150918C00004500\"},{\"exch\":\"OPRA\",\"strikeprice\":\"5.50\",\"symbol\":\"SLW150918C00005500\"}]}}}";		

		
		//create context
		Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,"SLW");put(TIMESTAMP,nowFromEpoch());}};

		Collection<Strike> result = new StrikesPerExpirationDateDeserializer().deserialize(optionJsonStr, context);
		//assertEquals("Expecting different # of deserialized objects", prices.size(), result.size());
		System.out.println("Deserialized: "+result);
		
		
	}

}
