/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.util.Map;

import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Simeon
 *
 */
public class StrikesDeserializer extends AbstractDeserializer<Strike> {

	protected StrikesDeserializer() {
		super(Strike.class);
		
	}

	@Override
	protected Strike deserializeSingleNode(final JsonNode node, final Map<String, Object> context) throws Throwable {
		
		
		Strike strike = null;
		
		return strike;
	}

}
