package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;

import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;


public class ExpirationDateDeserializerTest {

	@SuppressWarnings("serial")
	@Test
	public void parsingJsonIntoObjects() throws Throwable {
		
		String optionJsonStr = "{\"response\":{\"@id\":\"-53fd9e88:14dbee75e77:-1d2e\",\"expirationdates\":{\"date\":[\"2015-06-05\",\"2015-06-12\",\"2015-06-19\",\"2015-06-26\",\"2015-07-02\",\"2015-07-10\",\"2015-07-17\",\"2015-07-24\",\"2015-08-21\",\"2015-09-18\",\"2015-12-18\",\"2016-01-15\",\"2017-01-20\"]}}}";		

		
		//create context
		Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,"SLW");put(TIMESTAMP,nowFromEpoch());}};

		Collection<ExpirationDate> result = new ExpirationDateDeserializer().deserialize(/*dates*/optionJsonStr, context/*new HashMap<String,Object>(){{put(SYMBOL,"SLW");}}*/);
		//assertEquals("Expecting different # of deserialized objects", dates.size(), result.size());
		System.out.println("Deserialized: "+result);
		
	}
	
	
	

}
