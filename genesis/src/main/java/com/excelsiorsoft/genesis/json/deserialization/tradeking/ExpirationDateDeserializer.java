/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;


import static com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder.builder;
import static com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateFormat.XDATE_FORMAT;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateFormat;
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
		
		Assert.notNull(context, "Missing a deserialization context.");
		ExpirationDateFormat expDateFormat = (ExpirationDateFormat) context
				.get(XDATE_FORMAT) == null ? ExpirationDateFormat.SQUIZZED
				: (ExpirationDateFormat) context.get(XDATE_FORMAT);

		logger.info("will deserialize dates in a "+expDateFormat +" format.");
		
		ExpirationDate expDate = null;
		final ExpirationDateBuilder builder = builder();
		
		String dateStr = expDateFormat == ExpirationDateFormat.SQUIZZED?date.asText().replace("-", ""):date.asText();
		
		
		try{
			
			expDate = builder.asOf(timestamp)
						.forSymbol(symbol) 
						.forCycle(dateStr)
						.build();
			

		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", date, e.getMessage());
		}

		return expDate;
	}


	@Override
	public JsonNode cursor(final JsonNode root) {
		JsonNode dateNodes = root.path("expirationdates").path("date"); 
		return dateNodes;
	}


	
	
	


}
