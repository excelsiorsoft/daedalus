/**
 * 
 */
package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import java.io.IOException;

import com.excelsiorsoft.daedalus.dominion.Option; 
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author sleyzerzon
 *
 */
public class OptionDeserializer extends JsonDeserializer<Option> {

	@Override
	public Option deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		
		
		return null;
	}

}
