/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder.builder;

import java.util.Map;

import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Simeon
 *
 */
public class StrikesDeserializer extends AbstractDeserializer<Strike> {

	public StrikesDeserializer() {
		super(Strike.class);
		
	}

	@Override
	protected Strike deserializeSingleNode(final JsonNode price, final Map<String, Object> context) throws Throwable {
		
		
		Strike strike = null;
		final StrikeBuilder builder = builder();
		
		try {

			strike = builder.withValue(price.asText()).build();

		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", price,
					e.getMessage());
		}
		
		return strike;
	}

}
