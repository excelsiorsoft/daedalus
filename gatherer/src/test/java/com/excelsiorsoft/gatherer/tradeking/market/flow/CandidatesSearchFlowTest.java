package com.excelsiorsoft.gatherer.tradeking.market.flow;

import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.SYMBOL;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.xml;
import static java.lang.String.format;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.excelsiorsoft.gatherer.tradeking.connector.TradeKingForeman;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.ExpirationDateDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.SimpleDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.StrikesDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("unused")
public class CandidatesSearchFlowTest {

	private final static Logger logger = LoggerFactory.getLogger(CandidatesSearchFlowTest.class);
	
	private static List<String> symbols;
	
	private static TradeKingForeman foreman;
	
	@BeforeClass
	public static void setUp(){
		symbols = Arrays.asList(new String[]{"SLW"});
		foreman = new TradeKingForeman();
	}
	
	
	@SuppressWarnings("serial")
	@Test
	public void buildingOptionMatrix() throws Throwable {
		
		for(String symbol: symbols) {
			
			logger.info("Building matrix for {}",symbol);

			String expirations = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirations);
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode expDatesResponse = mapper.readTree(expirations).get("response");
			
			SimpleDeserializer<ExpirationDate> expDatesDeserializer = new ExpirationDateDeserializer();
			
			JsonNode dateNodes = expDatesResponse.path("expirationdates").path("date"); 
			
			Collection<ExpirationDate> expirDates = expDatesDeserializer.deserialize(dateNodes, new HashMap<String,Object>(){{put(SYMBOL,symbol);}});
			assertEquals("Expecting different # of deserialized objects", dateNodes.size(), expirDates.size());
			logger.info("Expiration dates for {}: {}", symbol, expirDates);
			
			
				for(ExpirationDate expDate : expirDates) {
					
					String strikesJson = foreman.makeApiCall(getOptionsStrikes(json, symbol)).getResponse();
					logger.debug("Strikes for {} for expiration date of {}: {}", symbol, expDate.getCycle(), strikesJson);
					JsonNode strikesResponse = mapper.readTree(strikesJson).get("response");
					
					SimpleDeserializer<Strike> strikeDeserializer = new StrikesDeserializer();
					
					JsonNode strikesNodes = strikesResponse.path("prices").path("price"); 

					Collection<Strike> strikes = strikeDeserializer.deserialize(strikesNodes, new HashMap<String,Object>(){{put(SYMBOL,symbol);}});
					assertEquals("Expecting different # of deserialized objects", strikesNodes.size(), strikes.size());
					logger.info("Strikes for {} for {} expiration cycle: {}", symbol, expDate.getCycle(), strikes);

				}
		}
		
	}

}
