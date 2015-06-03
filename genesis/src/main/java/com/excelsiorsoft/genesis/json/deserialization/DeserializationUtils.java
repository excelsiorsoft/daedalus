/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Utility for deserializing Json documents using concepts of Jackson Tree Model outlined {@link http://wiki.fasterxml.com/JacksonTreeModel}
 * 
 * @author sleyzerzon
 *
 */
public class DeserializationUtils {
	
	/**
	 * @param node
	 * @param path
	 * @return
	 */
	public static String asText(final JsonNode node, final String path){
		  
		String result = node.path(path).asText();
		return result;
	}
	
	
	/**
	 * @param node
	 * @param path
	 * @return
	 */
	public static Long asLong(final JsonNode node, final String path){
		  
		Long result = node.path(path).asLong();
		return result;
	}

}
