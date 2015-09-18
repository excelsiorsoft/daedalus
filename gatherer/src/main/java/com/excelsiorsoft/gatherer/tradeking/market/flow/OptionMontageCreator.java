/**
 * 
 */
package com.excelsiorsoft.gatherer.tradeking.market.flow;

import static com.excelsiorsoft.daedalus.dominion.WithExpirationDate.EXPIRATION_DATE;
import static com.excelsiorsoft.daedalus.dominion.WithSymbol.SYMBOL;
import static com.excelsiorsoft.daedalus.dominion.WithTimestamp.TIMESTAMP;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsExpirations;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.MarketRequestBuilder.getOptionsStrikesForSymbolPerExpCycle;
import static com.excelsiorsoft.gatherer.tradeking.connector.api.ResponseFormat.json;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau.ExpirationCycleTableauBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage.OptionMontageBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Strike;
import com.excelsiorsoft.gatherer.tradeking.connector.TradeKingForeman;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.ExpirationDateDeserializer;
import com.excelsiorsoft.genesis.json.deserialization.tradeking.StrikesPerExpirationDateDeserializer;

/**
 * @author sleyzerzon
 *
 */
@Service("optionMongateCreator")
public class OptionMontageCreator {
	
	
	private final static Logger logger = LoggerFactory.getLogger(OptionMontageCreator.class);
	
	@Autowired
	private TradeKingForeman foreman;
	//TODO: needs to be autowired
	//private TradeKingForeman foreman = new TradeKingForeman();
	
	@SuppressWarnings("serial")
	public OptionMontage create(String underlying) throws Throwable {
		
		long now = nowFromEpoch(); //define first - this is an anchor timestamp for the whole underlying structure
		
		OptionMontageBuilder montageBuilder = OptionMontageBuilder.builder();
		montageBuilder.forSymbol(underlying).asOf(now);
		
		//create context
		Map<String, Object> context = new HashMap<String,Object>(){{put(SYMBOL,underlying);put(TIMESTAMP,now);/*put(XDATE_FORMAT,ExpirationDateFormat.SQUIZZED);*/}};
		
		logger.info("Building matrix for {} as of {}",underlying, now);
		
		String expirations = foreman.makeApiCall(getOptionsExpirations(json, underlying)).getResponse();
		logger.debug("Expiration dates for {}: {}", underlying, expirations);
		
		Collection<ExpirationDate> expirDates = new ExpirationDateDeserializer().deserialize(expirations, context);
		logger.info("{} expiration dates for {}: {}", expirDates.size(), underlying, expirDates);
		
		Collection<Strike> strikes  = null;

		for(ExpirationDate expDate : expirDates) {
			
			ExpirationCycleTableauBuilder tableauBuilder = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now);
			
			String expDateStr = expDate.getCycle();
			context.put(EXPIRATION_DATE, expDateStr);
			tableauBuilder.forExpirationCycle(expDateStr);
			
			String strikesJson = foreman.makeApiCall(getOptionsStrikesForSymbolPerExpCycle(json, underlying, /*requires squeezed format*/expDateStr)).getResponse();
			logger.debug("Strikes for {} for expiration date of {}: {}", underlying, expDateStr, strikesJson);
			
			strikes = new StrikesPerExpirationDateDeserializer().deserialize(strikesJson, context);
			//strikes = new StrikesDeserializer().deserialize(strikesJson, context);
			logger.info("{} strikes for {} for {} expiration cycle: {}", strikes.size(), underlying, expDateStr, strikes);
			
			tableauBuilder.withStrikes((List<Strike>) strikes);
			ExpirationCycleTableau tableau = tableauBuilder.build();

			logger.info("Tableau for {} at {}: {}", underlying, expDateStr, tableau);
			montageBuilder.add(tableau);
			logger.info("Added tableau {} onto option montage", tableau);

		}
		
		OptionMontage montage = montageBuilder.build();

		logger.info("Built {} option montage:\n\n{}", underlying, montage);
		
		return montage;
	}

}
