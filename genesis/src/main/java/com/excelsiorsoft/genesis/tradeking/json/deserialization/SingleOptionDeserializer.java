/**
 * 
 */
package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import com.excelsiorsoft.daedalus.dominion.Option;
import com.excelsiorsoft.daedalus.dominion.Option.OptionBuilder;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class SingleOptionDeserializer implements SimpleDeserializer<Option> {

	@Override
	public Option deserialize(JsonNode quote) throws Throwable {
		
		Option result = null;
		OptionBuilder builder = OptionBuilder.init();
		
		try{
			
				builder.withUnderlying(quote.path("undersymbol").asText());
				builder.withExpiration(quote.path("xdate").asText());
				builder.withStrike(Double.parseDouble(quote.path("strikeprice").asText()));
				builder.ofType(quote.path("put_call").asText());
				
				result = builder.build();

		} catch (Throwable e) {
			System.out.println(e);
		}

		return result;
	}

}
