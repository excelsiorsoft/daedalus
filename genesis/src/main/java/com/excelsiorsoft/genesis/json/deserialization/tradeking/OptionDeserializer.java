/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder.builder;
import static com.excelsiorsoft.genesis.json.deserialization.DeserializationUtils.asText;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;
import com.excelsiorsoft.daedalus.dominion.impl.Exchange.ExchangeBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class OptionDeserializer implements SimpleDeserializer<Option> {

	private Logger logger = LoggerFactory.getLogger(OptionDeserializer.class);
	
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
		
		Option option = null;
		OptionBuilder builder = builder();
		
		try{
			
				builder.withUnderlying(asText(quote, "undersymbol"));
				builder.withExpiration(asText(quote, "xdate"));
				builder.withStrike(Double.parseDouble(asText(quote, "strikeprice")));
				builder.ofType(asText(quote, "put_call"));
				
				Exchange exchange = ExchangeBuilder.builder().withCode(asText(quote, "exch")).withDescription(asText(quote, "exch_desc")).build();
				/*Exchange exchange = new Exchange(); exchange.setCode(asText(quote, "exch")); exchange.setDescription(asText(quote, "exch_desc"));*/ 
				builder.tradeableOn(exchange);
				
				option = builder.build();
				
				//make sure it's invoked after option is built!!
				Strike strike = (Strike) option.getStrike()
						.setBid(asText(quote, "bid")).setAsk(asText(quote, "ask"))
						.setBidSize(asText(quote, "bidsz")).setAskSize(asText(quote, "asksz"))
						.setBidTime(asText(quote, "bid_time")).setAskTime(asText(quote, "ask_time"));


		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", quote, e.getMessage());
		}

		return option;
	}
	
	

	private List<Option> deserializeNodeCollection(JsonNode quotes) throws Throwable {
		
		List<Option> result = new LinkedList<>();
		logger.debug("Deserializing a collection of json nodes of size {} into a collection of {}s", quotes.size(), Option.class.getSimpleName());
		
		//int counter = 0;
		for(JsonNode quote : quotes){
			
			logger.debug("deserializing node: {}", quote);
			result.add(deserializeSingleNode(quote));
			
		}
		
		logger.debug("Done deserializing.\nResulting collection: {}", result);
		return result;
	}

}
