/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

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
