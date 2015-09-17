/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.daedalus.dominion.WithExpirationDate.EXPIRATION_DATE;
import static com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder.builder;

import java.util.Map;

import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Simeon
 *
 */
public class StrikesPerExpirationDateDeserializer extends AbstractDeserializer<Strike> {

	public StrikesPerExpirationDateDeserializer() {
		super(Strike.class);
		
	}

	@Override
	protected Strike deserializeSingleNode(final JsonNode node, final Map<String, Object> context) throws Throwable {
		
		
		
		Strike strike = null;
		final StrikeBuilder builder = builder();
		
		try {

			strike = builder.asOf(timestamp).forSymbol(symbol).withValue(node.get("strikeprice").asText()).forExpirationCycle((String)context.get(EXPIRATION_DATE)).build();

		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", node,
					e.getMessage());
		}
		
		return strike;
	}
	
	@Override
	public JsonNode cursor(JsonNode root) {
		//JsonNode dateNodes = root.path("prices").path("price"); 
		JsonNode dateNodes = root.path("quotes").path("quote"); 
		return dateNodes;
	}	

}
