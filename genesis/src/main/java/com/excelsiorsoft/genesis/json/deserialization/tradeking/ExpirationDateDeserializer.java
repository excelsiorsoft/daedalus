/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.time.Instant;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder;

import static com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder.*;
import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.*;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class ExpirationDateDeserializer extends AbstractDeserializer<ExpirationDate> {

	private Logger logger = LoggerFactory.getLogger(ExpirationDateDeserializer.class);
	
	public ExpirationDateDeserializer() {
		super(ExpirationDate.class);
		
	}

	
	protected ExpirationDate deserializeSingleNode(final JsonNode date, final Map<String, Object> context) throws Throwable {
		

		
		ExpirationDate expDate = null;
		final long now = Instant.now().getEpochSecond();
		final ExpirationDateBuilder builder = builder();
		
		try{
			
			expDate = builder.forSymbol((String) context.get(SYMBOL)) //TODO: pass the symbol through
						.asOf(now)
						.forCycle(date.asText())
						.build();
			

		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", date, e.getMessage());
		}

		return expDate;
	}	
	
	
	


}
