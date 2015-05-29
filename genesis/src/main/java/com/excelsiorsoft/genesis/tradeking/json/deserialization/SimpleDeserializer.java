/**
 * 
 */
package com.excelsiorsoft.genesis.tradeking.json.deserialization;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Behavior used by Daedalus converters to deserialize from incoming Json structures into application domain structures
 * 
 * @author sleyzerzon
 *
 */
public interface SimpleDeserializer<T> {
	
	T deserialize(JsonNode node) throws Throwable; 
		
			

}
