package com.excelsiorsoft.daedalus.dominion.impl;

import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.excelsiorsoft.daedalus.dominion.WithTimestamp;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;

/**
 * Representation of cacheable structure to house option strikes for a particular symbol on a particular expiration date.
 * Resembles thinkorswim strike views on their trade screen. 
 * 
 * @author sleyzerzon
 *
 */
public class ExpirationCycleTableau implements WithTimestamp {
	
	private String symbol;
	private long timestamp; 
	/*private ExpirationDate expirationCycle;*/
	private String expirationCycle;
	private Map<String, Map<String,Option>> strikes = new LinkedHashMap<>(); // strikeName[15.00] -> OptionType[Put/Call] -> Option[market data]
	
	
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public ExpirationCycleTableau setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public /*ExpirationDate*/String getExpirationCycle() {
		return expirationCycle;
	}

	public ExpirationCycleTableau setExpirationCycle(/*ExpirationDate*/String expirationCycle) {
		this.expirationCycle = expirationCycle;
		return this;
	}

	public Map<String, Map<String,Option>> getStrikes() {
		return strikes;
	}

	public ExpirationCycleTableau setStrikes(Map<String, Map<String,Option>> strikes) {
		this.strikes = strikes;
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nExpirationCycleTableau[timestamp=")
				.append(timestamp)
				.append(", symbol=")
				.append(symbol)
				.append(", nstrikes=")
				/*.append(expirationCycles)*/
				.append(strikes)
				
				.append("]");
		return builder.toString();
	}
	
	
	
	public final static class ExpirationCycleTableauBuilder {
		
		private final ExpirationCycleTableau tableau = new ExpirationCycleTableau();
		//private final OptionBuilder optionBuilder = OptionBuilder.builder();
		
		private ExpirationCycleTableauBuilder(){};
		
		public static ExpirationCycleTableauBuilder builder() {
			
			ExpirationCycleTableauBuilder result = new ExpirationCycleTableauBuilder();
			return result;
		}
		
		public ExpirationCycleTableau build(){

			
			long now = nowFromEpoch();
			tableau.timestamp = now;
			
			/*for(Strike strike : tableau.values){
				strike.setTimestamp(now);
			}*/
			
			return tableau;
		}
		
		public ExpirationCycleTableauBuilder forSymbol(String symbol){
			tableau.symbol = symbol;
			return this;
		}
		
		public ExpirationCycleTableauBuilder forExpirationCycle(String expirationCycle){
			//tableau.expirationCycle = ExpirationDateBuilder.builder().forCycle(expirationCycle).build();
			tableau.expirationCycle = expirationCycle;
			return this;
		}
		
		public ExpirationCycleTableauBuilder withStrikes(List<String> strikesStr){

			for(String strikeStr : strikesStr){
				tableau.strikes.put(strikeStr, new HashMap<String,Option>());
			}

			return this;
		}

	}

}
