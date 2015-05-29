/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.genesis.json.deserialization.DeserializationUtils.asText;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.Option;
import com.excelsiorsoft.daedalus.dominion.Option.OptionBuilder;
import com.excelsiorsoft.genesis.json.deserialization.DeserializationUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class SingleOptionDeserializer implements SimpleDeserializer<Option> {

	private Logger logger = LoggerFactory.getLogger(SingleOptionDeserializer.class);
	
	@Override
	public Option deserialize(JsonNode quote) throws Throwable {
		
		Option result = null;
		OptionBuilder builder = OptionBuilder.init();
		
		try{
			
				builder.withUnderlying(asText(quote, "undersymbol"));
				builder.withExpiration(asText(quote, "xdate"));
				builder.withStrike(Double.parseDouble(asText(quote, "strikeprice")));
				builder.ofType(asText(quote, "put_call"));
				
				result = builder.build();

		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", quote, e.getMessage());
		}

		return result;
	}

}
