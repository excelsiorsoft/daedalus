package com.excelsiorsoft.daedalus.dominion.impl;

//import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.Assert;

import com.excelsiorsoft.daedalus.dominion.WithExpirationDate;
import com.excelsiorsoft.daedalus.dominion.WithTimestamp;
//import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;
//import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;

/**
 * Representation of cacheable structure to house option strikes for a particular symbol on a particular expiration date.
 * Resembles thinkorswim strike views on their trade screen. 
 * 
 * @author sleyzerzon
 *
 */
public class ExpirationCycleTableau implements WithTimestamp, WithExpirationDate {
	
	private String symbol;
	private long timestamp; 
	/*private ExpirationDate expirationCycle;*/
	private String expirationCycle;
	private Map<String, Map<String,Option>> strikes = new LinkedHashMap<>(); // strikeName[15.00] -> OptionType[Put/Call] -> Option[market data]
	
	
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	//@Override
	@Deprecated //don't need a setter - timestamp should be set via the builder
	public ExpirationCycleTableau setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public /*ExpirationDate*/String getExpirationDate/*Cycle*/() {
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
		builder.append("ExpirationCycleTableau[timestamp=")
				.append(timestamp)
				.append(", symbol=")
				.append(symbol)
				.append(", expirationDate=")
				.append(expirationCycle)
				.append(", strikes=");
				
				builder.append("{");
				
				int noOfStrikes = strikes.size();
				if(noOfStrikes > 0){
					
					builder.append("\n");

					int strikesCntr = noOfStrikes-1;
					for(Iterator<Entry<String, Map<String, Option>>> strikesIterator = strikes.entrySet().iterator(); strikesIterator.hasNext() ; strikesCntr--){

						Entry<String, Map<String, Option>> strikeEntry = strikesIterator.next();
						
						String strikeKey = strikeEntry.getKey();
						Map<String, Option> putOrCall = strikeEntry.getValue();
						
						if(putOrCall.size() >0) builder.append ("\n");
						builder.append(strikeKey)
							.append(" -> ")
							.append("{");							
							
							int optionCntr = putOrCall.size()-1;
							for(Iterator<Entry<String, Option>> optionIterator = putOrCall.entrySet().iterator(); optionIterator.hasNext(); optionCntr--){

								Entry<String, Option> optionEntry = optionIterator.next();
								builder.append("\n\t");
								builder.append(optionEntry.getKey())
								.append(" = ")
								.append(optionEntry.getValue());
								/*.append("\n");*/
								if (optionCntr >0) builder.append(",");
							}
						
							builder.append("}");

						if (strikesCntr > 0) builder.append(", ");
					}
				
				}
				builder.append("}");
				builder.append("]");
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
		
		
		public ExpirationCycleTableauBuilder asOf(long timestamp){
			tableau.timestamp = timestamp;
			return this;
		}
		
		
		public ExpirationCycleTableau build(){

		/*	Assert.notNull(tableau.strikes, "Cannot build tableau until strikes are initialized.");
			Assert.notNull(tableau.symbol, "Cannot build tableau until symbol is known.");*/
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
		
	/*	public ExpirationCycleTableauBuilder withStrikes(List<String> strikesStr){

			for(String strikeStr : strikesStr){
				tableau.strikes.put(strikeStr, new HashMap<String,Option>());
			}

			return this;
		}*/
		
		public ExpirationCycleTableauBuilder withStrikes(List<?> strikes){

			  Assert.notNull(strikes, "Strikes must not be null");
			
			for(Object strike : strikes){
				
				if(strike instanceof String){
					tableau.strikes.put(strike.toString(), new HashMap<String,Option>());
				}else if(strike instanceof Strike){
					tableau.strikes.put(Double.toString(((Strike) strike).getValue()), new HashMap<String,Option>());
				}
			}

			return this;
		}		

	}

}
