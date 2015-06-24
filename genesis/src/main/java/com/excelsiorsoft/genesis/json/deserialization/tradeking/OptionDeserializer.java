/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder.builder;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.*;
import static com.excelsiorsoft.genesis.json.deserialization.DeserializationUtils.asLong;
import static com.excelsiorsoft.genesis.json.deserialization.DeserializationUtils.asText;

import java.time.Instant;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;
import com.excelsiorsoft.daedalus.dominion.impl.Exchange.ExchangeBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author sleyzerzon
 *
 */
public class OptionDeserializer  extends AbstractDeserializer<Option> {

	public OptionDeserializer() {
		super(Option.class);
		
	}

	private Logger logger = LoggerFactory.getLogger(OptionDeserializer.class);
	
	/**
	 * @param quote
	 * @return
	 * @throws Throwable
	 */
	protected Option deserializeSingleNode(final JsonNode quote, final Map<String, Object> context) throws Throwable {
		
		Option option = null;
		final long now = nowFromEpoch();
		final OptionBuilder builder = builder();
		
		try{
			
				builder.withUnderlying(asText(quote, "undersymbol"));
				builder.withExpiration(asText(quote, "xdate"));
				builder.withStrike(Double.parseDouble(asText(quote, "strikeprice")));
				builder.ofType(asText(quote, "put_call"));
				
				Exchange exchange = ExchangeBuilder.builder().withCode(asText(quote, "exch")).withDescription(asText(quote, "exch_desc")).build();
				/*Exchange exchange = new Exchange(); exchange.setCode(asText(quote, "exch")); exchange.setDescription(asText(quote, "exch_desc"));*/ 
				builder.tradeableOn(exchange);
				
				option = builder.build();
				option.setTimestamp(now);
				
				//make sure it's invoked after option is built!!
				Strike strike = (Strike) option.getStrike()
						//.setBid(asText(quote, "bid")).setAsk(asText(quote, "ask"))
						//.setBidSize(asText(quote, "bidsz")).setAskSize(asText(quote, "asksz"))
						//.setBidTime(asText(quote, "bid_time")).setAskTime(asText(quote, "ask_time"))
						//.setVolume(asText(quote, "vl"))
						//.setTimestamp(asLong(quote, "timestamp"))
						.setTimestamp(now) //disregarding what's on the response
						
						;


			logger.debug(
					"\n\ttimestamp: {}, "
					+ "\n\ttimestamp as instant: {}, "
					+ "\n\ttimestamp @ local zone: {}, "
					+ "\n\tnow as instant: {}, "
					+ "\n\tnow @ local zone: {}",
					asLong(quote, "timestamp"),
					Instant.ofEpochSecond(asLong(quote, "timestamp")),
					fromUnixTimestampToLocalDateTime(asLong(quote, "timestamp")),
					nowFromEpoch(),
					fromUnixTimestampToLocalDateTime(Instant.now()
							.getEpochSecond()));


		} catch (Throwable e) {
			logger.error("Error while deserializing {}: {}", quote, e.getMessage());
		}

		return option;
	}


}
