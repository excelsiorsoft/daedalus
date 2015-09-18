package com.excelsiorsoft.gatherer.tradeking.market.flow;


import static com.excelsiorsoft.daedalus.dominion.WithExpirationDate.EXPIRATION_DATE;
import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;
import static com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateFormat.XDATE_FORMAT;
import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType.CALL;
import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType.PUT;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getExtQuotes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsExpirations;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsStrikes;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsStrikesForSymbolPerExpCycle;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.json;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.xml;
import static com.excelsiorsoft.gatherer.tradeking.market.flow.util.RandomPick.randomFrom;

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
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateFormat;
import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionSymbolBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage.OptionMontageBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.excelsiorsoft.daedalus.util.Significant;
import com.excelsiorsoft.gatherer.tradeking.connector.TradeKingForeman;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.ExpirationDateDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.OptionDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.SimpleDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.StrikesDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.StrikesPerExpirationDateDeserializer;

@Significant
@SuppressWarnings("unused")
public class CandidatesSearchFlowTest {

	private final static Logger logger = LoggerFactory.getLogger(CandidatesSearchFlowTest.class);
	
	private static List<String> symbols;
	
	private static TradeKingForeman foreman;
	
	@BeforeClass
	public static void setUp(){
		symbols = Arrays.asList(new String[]{"SLW", "NLY"});
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
			
			SimpleDeserializer<ExpirationDate> expDatesDeserializer = new ExpirationDateDeserializer();
			
			Collection<ExpirationDate> expirDates = expDatesDeserializer.deserialize(/*dateNodes*/expirationsJson, new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}});
			logger.info("Expiration dates for {}: {}", symbol, expirDates);
			
			
				for(ExpirationDate expDate : expirDates) {
					
					String strikesJson = foreman.makeApiCall(getOptionsStrikes(json, symbol)).getResponse();
					logger.debug("Strikes for {} for expiration date of {}: {}", symbol, expDate.getCycle(), strikesJson);
					
					SimpleDeserializer<Strike> strikeDeserializer = new StrikesDeserializer();
					
					Collection<Strike> strikes = strikeDeserializer.deserialize(/*strikesNodes*/strikesJson, new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}});
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
	
	/*@SuppressWarnings("serial")
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
		
		
	}*/
	
	@SuppressWarnings("serial")
	@Test
	public void pickingRandomExpirationCycle() throws Throwable {
		
		long now = nowFromEpoch(); //define first - this is an anchor timestamp for the whole underlying structure
		
		for(String symbol: symbols) {
			
			//create context
			Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);}};			
			
			String expirations = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirations);
			
			Collection<ExpirationDate> expirDates = new ExpirationDateDeserializer().deserialize(expirations, context);
			logger.info("{} expiration dates for {}: {}", expirDates.size(), symbol, expirDates);
			
			ExpirationDate randomExpDate = randomFrom((List<ExpirationDate>) expirDates);
			logger.info("randomExpDate: {}:{}", randomExpDate, randomExpDate.getCycle());
		}
	}

	
	@SuppressWarnings("serial")
	@Test
	public void buildingOptionMontage() throws Throwable {
		
		long now = nowFromEpoch(); //define first - this is an anchor timestamp for the whole underlying structure
		
		for(String symbol: symbols) {
			
			OptionMontageBuilder montageBuilder = OptionMontageBuilder.builder();
			montageBuilder.forSymbol(symbol).asOf(now);
			
			//create context
			Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,symbol);put(TIMESTAMP,now);/*put(XDATE_FORMAT,ExpirationDateFormat.SQUIZZED);*/}};
			
			logger.info("Building matrix for {} as of {}",symbol, now);
			
			String expirations = foreman.makeApiCall(getOptionsExpirations(json, symbol)).getResponse();
			logger.debug("Expiration dates for {}: {}", symbol, expirations);
			
			Collection<ExpirationDate> expirDates = new ExpirationDateDeserializer().deserialize(expirations, context);
			logger.info("{} expiration dates for {}: {}", expirDates.size(), symbol, expirDates);
			
			Collection<Strike> strikes  = null;

			for(ExpirationDate expDate : expirDates) {
				
				ExpirationCycleTableauBuilder tableauBuilder = ExpirationCycleTableauBuilder.builder().forSymbol(symbol).asOf(now);
				
				String expDateStr = expDate.getCycle();
				context.put(EXPIRATION_DATE, expDateStr);
				tableauBuilder.forExpirationCycle(expDateStr);
				
				String strikesJson = foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(json, symbol, /*requires squeezed format*/expDateStr)).getResponse();
				logger.debug("Strikes for {} for expiration date of {}: {}", symbol, expDateStr, strikesJson);
				
				strikes = new StrikesPerExpirationDateDeserializer().deserialize(strikesJson, context);
				//strikes = new StrikesDeserializer().deserialize(strikesJson, context);
				logger.info("{} strikes for {} for {} expiration cycle: {}", strikes.size(), symbol, expDateStr, strikes);
				
				tableauBuilder.withStrikes((List<Strike>) strikes);
				ExpirationCycleTableau tableau = tableauBuilder.build();

				logger.info("Tableau for {} at {}: {}", symbol, expDateStr, tableau);
				montageBuilder.add(tableau);
				logger.info("Added tableau {} onto option montage", tableau);

			}
			
			OptionMontage montage = montageBuilder.build();

			logger.info("Built {} option montage:\n\n{}", symbol, montage);
			
			/*for(int i = 0; i<2; i++) {
				ExpirationDate randomExpDate = randomFrom((List<ExpirationDate>) expirDates);
				logger.info("randomExpDate: {}:{}", randomExpDate, randomExpDate.getCycle());

				Strike randomStrike = randomFrom((List<Strike>) strikes);
				logger.info("randomStrike: {}:{}", randomStrike, randomStrike.getValue());

				buildOption(montage, randomExpDate.getCycle(), Double.toString(randomStrike.getValue()), CALL.abbreviation());
				buildOption(montage, randomExpDate.getCycle(), Double.toString(randomStrike.getValue()), PUT.abbreviation());
				
				logger.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			
			logger.info("Resulting montage: {}", montage);*/

		}
		
		
	}
	
	
	@SuppressWarnings({ "serial", "static-access" })
	private Option buildOption(OptionMontage montage, String expirationDate, String strike, String optionType) throws Throwable {
		
		String underlying = montage.getSymbol();
		ExpirationCycleTableau chosenTableau = montage.getTableau(expirationDate);
		Map<String, Option> chosenStrike = chosenTableau.getStrikes().get(strike);
		
		OptionBuilder optionBuilder = OptionBuilder.builder();
		
		String optionSymbol = new OptionSymbolBuilder().buildSymbol(underlying, expirationDate, optionType, Double.parseDouble(strike));
		
		
		String quote = foreman.makeApiCall(getExtQuotes(json, optionSymbol, "")).getResponse();
		
		//create context
		Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,underlying);put(TIMESTAMP,nowFromEpoch());}};
				
		Collection<Option> result = new OptionDeserializer().deserialize(quote, context);
		logger.info("Deserialized: {}",result);
		
		Option option = optionBuilder.withUnderlying(underlying).withExpiration(chosenTableau.getExpirationDate()).ofType(optionType).withStrike(strike).build();
		
		chosenStrike.put(optionType, option);
		
		return option;
		
	}
	
}
