/**
 * 
 */
package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import java.io.IOException;

import net.sf.json.JSONArray;

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
        
        String toDeserialize = node.get("response").toString();
        
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(toDeserialize);
		String symbol = JsonPath.read(document, "$.quotes.quote[0].symbol");
		JSONArray quotes = JsonPath.read(document, "$.quotes.quote[*]");
		
		
		String bid = JsonPath.read(document, "$.quotes.quote.bid");
		String ask = JsonPath.read(document, "$.quotes.quote.ask");
		String optionType = JsonPath.read(document, "$.quotes.quote.put_call");
		String strike = JsonPath.read(document, "$.quotes.quote.strikeprice");
		String expiration = JsonPath.read(document, "$.quotes.quote.xdate");
		String underlying = JsonPath.read(document, "$.quotes.quote.undersymbol");

		Option result = null;
		try {
			result = OptionBuilder.withUnderlying(underlying).ofType(optionType)
					.withExpiration(expiration)
					.withStrike(Double.parseDouble(strike)).build();
		} catch (Throwable e) {
			//TODO: proper error handling 
		}
		return result;
        
		
		
		
	}

}
