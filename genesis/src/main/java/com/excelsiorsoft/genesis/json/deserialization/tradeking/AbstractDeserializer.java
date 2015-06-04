/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Simeon
 * @param <T>
 *
 */
public abstract class AbstractDeserializer<T> implements SimpleDeserializer<T> {

	protected Logger logger = LoggerFactory.getLogger(AbstractDeserializer.class);
	
	
	private final Class<T> clazz;
	
	protected AbstractDeserializer(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public List<T> deserialize(final JsonNode node) throws Throwable {

		List<T> result = new LinkedList<>();

		if (node.isContainerNode()) {

			return deserializeNodeCollection(node);

		} else {

			result.add(deserializeSingleNode(node));
			return result;
		}

	}

	/**
	 * @param node
	 * @return
	 * @throws Throwable
	 */
	protected abstract T deserializeSingleNode(JsonNode node) throws Throwable;

	/**
	 * @param elements
	 * @return
	 * @throws Throwable
	 */
	protected List<T> deserializeNodeCollection(JsonNode elements) throws Throwable {
		
		final List<T> result = new LinkedList<>();
		logger.debug("Deserializing a collection of json nodes of size {} into a collection of {}s:", elements.size(), clazz.getSimpleName());
		
		//int counter = 0;
		for(JsonNode element : elements){
			
			logger.debug("deserializing node: {}", element);
			result.add(deserializeSingleNode(element));
			
		}
		
		logger.debug("Done deserializing.\nResulting collection: {}", result);
		return result;
	}

}
