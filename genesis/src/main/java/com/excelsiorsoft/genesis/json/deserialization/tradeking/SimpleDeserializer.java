/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Behavior used by Daedalus converters to deserialize from incoming Json structures into application domain structures
 * 
 * @author sleyzerzon
 *
 */
public interface SimpleDeserializer<T> {
	
	Collection<T> deserialize(JsonNode node) throws Throwable; 
	
	
		
			

}
