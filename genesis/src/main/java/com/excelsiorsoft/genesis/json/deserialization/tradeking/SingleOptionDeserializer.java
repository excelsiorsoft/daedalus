/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.genesis.json.deserialization.DeserializationUtils.asText;

import java.util.LinkedList;
import java.util.List;

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
	public List<Option> deserialize(JsonNode node) throws Throwable {
		
		List<Option> result = new LinkedList<>();
		if (node.isContainerNode()) {
			return deserializeNodeCollection(node);
		}else {
			result.add(deserializeSingleNode(node));
			return result;
		}
		
		/*Option result = null;
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

		return result;*/
	}

	
	private Option deserializeSingleNode(JsonNode quote) throws Throwable {
		
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
	
	

	private List<Option> deserializeNodeCollection(JsonNode quotes) throws Throwable {
		
		List<Option> result = new LinkedList<>();
		logger.debug("Deserializing a collection of json nodes of size {} into a collection of Options", quotes.size());
		
		int counter = 0;
		for(JsonNode quote : quotes){
			
			logger.debug("deserializing node: {}", quote);
			result.add(deserializeSingleNode(quote));
			
		}
		
		logger.debug("Done deserializing.\nResulting collection: {}", result);
		return result;
	}

}
