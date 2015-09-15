package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;

/**
 * Encapsulation of the base version of domain deserialization logic
 * 
 * @author Simeon
 * @param <T>
 *
 */
public abstract class AbstractDeserializer<T> implements SimpleDeserializer<T> {

	protected Logger logger = LoggerFactory.getLogger(AbstractDeserializer.class);
	
	
	private final Class<T> clazz;
	protected final ObjectMapper jacksonMapper = new ObjectMapper();
	protected JsonNode rootNode;
	
	protected String symbol;
	protected long timestamp;
	
	
	protected AbstractDeserializer(Class<T> clazz){
		this.clazz = clazz;
	}
	
	public List<T> deserialize(/*final JsonNode node*/final String json, final Map<String, Object> context) throws Throwable {
		
		Assert.notNull(json, "Json string must be non-empty.");
		rootNode = jacksonMapper.readTree(json).get("response");
		JsonNode node = cursor(rootNode);

		Assert.notNull(context, "Context was not passed in.");
		symbol = (String) context.get(SYMBOL);
		timestamp = context.get(TIMESTAMP)!= null?(long) context.get(TIMESTAMP):0L;
		logger.debug("For symbol {} @ timestamp={}...", symbol, timestamp);
		
			
		List<T> result = new LinkedList<>();

		if(node.getNodeType() != JsonNodeType.MISSING) {
			if (node.isContainerNode()/*.isArray()*/) {
	
				return deserializeNodeCollection(node, context);
	
			} else {
	
				result.add(deserializeSingleNode(node , context));
				return result;
			}
		}

		return Collections.EMPTY_LIST/*result*/;
	}


	/**
	 * @param elements
	 * @return
	 * @throws Throwable
	 */
	protected List<T> deserializeNodeCollection(final JsonNode elements, final Map<String, Object> context) throws Throwable {
		
		final List<T> result = new LinkedList<>();
		logger.debug("Deserializing a collection of json nodes of size {} into a collection of {}s:", elements.size(), clazz.getSimpleName());
		
	
		for(JsonNode element : elements){
			
			logger.debug("deserializing node: {}", element);
			result.add(deserializeSingleNode(element, context));
			
		}
		
		logger.debug("Done deserializing.\nResulting collection: {}", result);
		return result;
	}
	
	
	/**
	 * @param node
	 * @return
	 * @throws Throwable
	 */
	protected abstract T deserializeSingleNode(final JsonNode node, final Map<String, Object> context) throws Throwable;
	
	public abstract JsonNode cursor(JsonNode root);


}
