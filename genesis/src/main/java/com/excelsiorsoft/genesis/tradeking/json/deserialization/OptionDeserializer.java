/**
 * 
 */
package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import java.io.IOException;

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

	@Override
	public Option deserialize(JsonParser jsonParser,
	            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {		
		
		ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        
        String toDeserialize = node.get("response").toString();
        
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(toDeserialize);
		String symbol = JsonPath.read(document, "$.quotes.quote[0].symbol");
		String bid = JsonPath.read(document, "$.quotes.quote[0].bid");
		String ask = JsonPath.read(document, "$.quotes.quote[0].ask");
		String optionType = JsonPath.read(document, "$.quotes.quote[0].put_call");
		String strike = JsonPath.read(document, "$.quotes.quote[0].strikeprice");
		String expiration = JsonPath.read(document, "$.quotes.quote[0].xdate");

		Option result = null;
		try {
			result = OptionBuilder.withUnderlying(symbol).ofType(optionType)
					.withExpiration(expiration)
					.withStrike(Double.parseDouble(strike)).build();
		} catch (Throwable e) {
			//TODO: proper error handling 
		}
		return result;
        
		
		
		
	}

}
