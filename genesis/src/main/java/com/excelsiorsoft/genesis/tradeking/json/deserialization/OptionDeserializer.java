/**
 * 
 */
package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import java.io.IOException;

//import net.sf.json.JSONArray;


import java.util.Iterator;

import net.minidev.json.JSONArray;

import com.excelsiorsoft.daedalus.dominion.Option; 
import com.excelsiorsoft.daedalus.dominion.Option.OptionBuilder;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * @author sleyzerzon
 *
 */
public class OptionDeserializer extends JsonDeserializer<Option> {

	@SuppressWarnings("unused")
	@Override
	public Option deserialize(JsonParser jsonParser,
	            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {		
		
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        
        String response = node.get("response").toString();
        
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(response);
		//String symbol = JsonPath.read(document, "$.quotes.quote[0].symbol");
		JSONArray quotes = JsonPath.read(document, "$.quotes.quote[*]");
		
		Option result = null;
		OptionBuilder builder = OptionBuilder.init();
		
		try{
			
			for (Object quote : quotes){
				
				builder.withUnderlying((String)JsonPath.read(quote, "$.undersymbol"));
				builder.withExpiration((String)JsonPath.read(quote, "$.xdate"));
				builder.withStrike(Double.parseDouble(JsonPath.read(quote, "$.strikeprice")));
				builder.ofType((String)JsonPath.read(quote, "$.put_call"));
				
				result = builder.build();
				
			}

		} catch (Throwable e) {
			//TODO: proper error handling 
		}

		return result;

	}

}
