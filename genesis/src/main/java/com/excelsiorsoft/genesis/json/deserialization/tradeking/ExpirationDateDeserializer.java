/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder;
import static com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder.*;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class ExpirationDateDeserializer extends AbstractDeserializer<ExpirationDate> {

	private Logger logger = LoggerFactory.getLogger(ExpirationDateDeserializer.class);
	
	protected ExpirationDateDeserializer() {
		super(ExpirationDate.class);
		
	}

	
	protected ExpirationDate deserializeSingleNode(final JsonNode date) throws Throwable {
		

		
		ExpirationDate expDate = null;
		final long now = Instant.now().getEpochSecond();
		final ExpirationDateBuilder builder = builder();
		
		try{
			
			expDate = builder.forSymbol("SLW") //TODO: pass the symbol through
						.asOf(now)
						.forCycle(date.asText())
						.build();
			

		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", date, e.getMessage());
		}

		return expDate;
	}	
	
	
	


}
