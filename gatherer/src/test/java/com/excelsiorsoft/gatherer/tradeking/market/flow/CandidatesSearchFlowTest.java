package com.excelsiorsoft.gatherer.tradeking.market.flow;


import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;
import static com.excelsiorsoft.daedalus.dominion.WithExpirationDate.EXPIRATION_DATE;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.*;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.xml;
import static java.lang.String.format;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.*;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau.ExpirationCycleTableauBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage.OptionMontageBuilder;
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
	@Test @Deprecated
	public void buildingOptionMatrix() throws Throwable {
		
		for(String symbol: symbols) {
			
			long now = nowFromEpoch(); //define first - this is an anchor timestamp for the whole underlying structure
			
			logger.info("Building matrix for {} as of {}",symbol, now);

			String expirationsJson = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirationsJson);
			
			/*ObjectMapper mapper = new ObjectMapper();
			JsonNode expDatesResponse = mapper.readTree(expirationsJson).get("response");*/
			
			SimpleDeserializer<ExpirationDate> expDatesDeserializer = new ExpirationDateDeserializer();
			
			/*JsonNode dateNodes = expDatesResponse.path("expirationdates").path("date"); */
			
			Collection<ExpirationDate> expirDates = expDatesDeserializer.deserialize(/*dateNodes*/expirationsJson, new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}});
			//assertEquals("Expecting different # of deserialized objects", dateNodes.size(), expirDates.size());
			logger.info("Expiration dates for {}: {}", symbol, expirDates);
			
			
				for(ExpirationDate expDate : expirDates) {
					
					String strikesJson = foreman.makeApiCall(getOptionsStrikes(json, symbol)).getResponse();
					logger.debug("Strikes for {} for expiration date of {}: {}", symbol, expDate.getCycle(), strikesJson);
					//JsonNode strikesResponse = mapper.readTree(strikesJson).get("response");
					
					SimpleDeserializer<Strike> strikeDeserializer = new StrikesDeserializer();
					
					//JsonNode strikesNodes = strikesResponse.path("prices").path("price"); 

					Collection<Strike> strikes = strikeDeserializer.deserialize(/*strikesNodes*/strikesJson, new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}});
					//assertEquals("Expecting different # of deserialized objects", strikesNodes.size(), strikes.size());
					logger.info("Strikes for {} for {} expiration cycle: {}", symbol, expDate.getCycle(), strikes);

				}
		}
		
	}
	
	@SuppressWarnings("serial")
	@Test 
	public void buildingCacheableTableaus() throws Throwable {
		
		for(String symbol: symbols) {
			
			long now = nowFromEpoch(); //define first - this is an anchor timestamp for the whole underlying structure
			
			
			//create context
			Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}};
			
			logger.info("Building matrix for {} as of {}",symbol, now);
			
			String expirations = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirations);
			
			Collection<ExpirationDate> expirDates = new ExpirationDateDeserializer().deserialize(expirations, context);
			logger.info("{} expiration dates for {}: {}", expirDates.size(), symbol, expirDates);
			
			for(ExpirationDate expDate : expirDates) {
				
				ExpirationCycleTableauBuilder tableauBuilder = ExpirationCycleTableauBuilder.builder().forSymbol(symbol).asOf(now);
				
				String expDateStr = expDate.getCycle();
				context.put(EXPIRATION_DATE, expDateStr);
				tableauBuilder.forExpirationCycle(expDateStr);
				
				String strikesJson = foreman.makeApiCall(getOptionsStrikes(json, symbol)).getResponse();
				logger.debug("Strikes for {} for expiration date of {}: {}", symbol, expDateStr, strikesJson);
				
				Collection<Strike> strikes = new StrikesDeserializer().deserialize(strikesJson, context);
				logger.info("{} strikes for {} for {} expiration cycle: {}", strikes.size(), symbol, expDateStr, strikes);
				
				tableauBuilder.withStrikes((List<Strike>) strikes);
				ExpirationCycleTableau tableau = tableauBuilder.build();
				logger.debug("Tableau for {} at {}: {}", symbol, expDateStr, tableau);
			}
			
			
		}
		

	}
	
	@SuppressWarnings("serial")
	@Test
	public void buildingOptionMontage() throws Throwable {
		
		for(String symbol: symbols) {
			
			long now = nowFromEpoch(); //define first - this is an anchor timestamp for the whole underlying structure
			OptionMontageBuilder montageBuilder = OptionMontageBuilder.builder();
			montageBuilder.forSymbol(symbol).asOf(now);
			
			//create context
			Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}};
			
			logger.info("Building matrix for {} as of {}",symbol, now);
			
			String expirations = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirations);
			
			Collection<ExpirationDate> expirDates = new ExpirationDateDeserializer().deserialize(expirations, context);
			logger.info("{} expiration dates for {}: {}", expirDates.size(), symbol, expirDates);
			
			for(ExpirationDate expDate : expirDates) {
				
				ExpirationCycleTableauBuilder tableauBuilder = ExpirationCycleTableauBuilder.builder().forSymbol(symbol).asOf(now);
				
				String expDateStr = expDate.getCycle();
				context.put(EXPIRATION_DATE, expDateStr);
				tableauBuilder.forExpirationCycle(expDateStr);
				
				String strikesJson = foreman.makeApiCall(getOptionsStrikes(json, symbol)).getResponse();
				logger.debug("Strikes for {} for expiration date of {}: {}", symbol, expDateStr, strikesJson);
				
				Collection<Strike> strikes = new StrikesDeserializer().deserialize(strikesJson, context);
				logger.info("{} strikes for {} for {} expiration cycle: {}", strikes.size(), symbol, expDateStr, strikes);
				
				tableauBuilder.withStrikes((List<Strike>) strikes);
				ExpirationCycleTableau tableau = tableauBuilder.build();
				logger.info("Tableau for {} at {}: {}", symbol, expDateStr, tableau);
				montageBuilder.add(tableau);
				logger.info("Added tableau {} onto option montage", tableau);
			}
			
			OptionMontage montage = montageBuilder.build();
			logger.info("Built {} option montage:\n\n{}", symbol, montage);
		}
		
		
	}

}
