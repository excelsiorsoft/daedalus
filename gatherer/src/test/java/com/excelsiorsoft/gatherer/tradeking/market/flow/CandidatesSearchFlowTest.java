package com.excelsiorsoft.gatherer.tradeking.market.flow;

import static com.excelsiorsoft.daedalus.dominion.impl.Quote.QuoteBuilder.SYMBOL;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsExpirations;
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
import com.excelsiorsoft.gatherer.tradeking.connector.TradeKingForeman;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.ExpirationDateDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.SimpleDeserializer;
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
		
		for(String symbol: symbols){
			logger.info("Building matrix for {}",symbol);

			String expirations = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirations);
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode response = mapper.readTree(expirations).get("response");
			
			SimpleDeserializer<ExpirationDate> deserializer = new ExpirationDateDeserializer();
			
			JsonNode dates = response.path("expirationdates").path("date"); 
			
			System.out.println("isContainer: "+dates.isContainerNode());
			System.out.println("isArray: "+dates.isArray());
			
			Collection<ExpirationDate> expirDates = deserializer.deserialize(dates, new HashMap<String,Object>(){{put(SYMBOL,symbol);}});
			assertEquals("Expecting different # of deserialized objects", dates.size(), expirDates.size());
			logger.info("Expiration dates for {}: {}", symbol, expirDates);
			
		}
		
	}

}
