/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class DeserializationUtils {
	
	public static String asText(JsonNode node, String path){
		  
		String result = node.path(path).asText();
		return result;
	}
	
	
	public static Long asLong(JsonNode node, String path){
		  
		Long result = node.path(path).asLong();
		return result;
	}

}
