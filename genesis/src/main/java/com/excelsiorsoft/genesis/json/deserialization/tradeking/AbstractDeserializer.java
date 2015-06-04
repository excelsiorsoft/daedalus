/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.*;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Simeon
 * @param <T>
 *
 */
public abstract class AbstractDeserializer<T> implements SimpleDeserializer<T> {

	protected Logger logger = LoggerFactory.getLogger(AbstractDeserializer.class);
	
	
	private final Class<T> clazz;
	protected String symbol;
	
	protected AbstractDeserializer(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public List<T> deserialize(final JsonNode node, final Map<String, Object> context) throws Throwable {

		symbol =( context != null)?(String) context.get(SYMBOL):"";
		
		List<T> result = new LinkedList<>();

		if (node.isContainerNode()) {

			return deserializeNodeCollection(node, context);

		} else {

			result.add(deserializeSingleNode(node , context));
			return result;
		}

	}

	/**
	 * @param node
	 * @return
	 * @throws Throwable
	 */
	protected abstract T deserializeSingleNode(JsonNode node, final Map<String, Object> context) throws Throwable;

	/**
	 * @param elements
	 * @return
	 * @throws Throwable
	 */
	protected List<T> deserializeNodeCollection(JsonNode elements, Map<String, Object> context) throws Throwable {
		
		final List<T> result = new LinkedList<>();
		logger.debug("Deserializing a collection of json nodes of size {} into a collection of {}s:", elements.size(), clazz.getSimpleName());
		
		//int counter = 0;
		for(JsonNode element : elements){
			
			logger.debug("deserializing node: {}", element);
			result.add(deserializeSingleNode(element, context));
			
		}
		
		logger.debug("Done deserializing.\nResulting collection: {}", result);
		return result;
	}

}
