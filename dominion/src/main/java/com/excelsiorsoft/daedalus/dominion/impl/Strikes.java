package com.excelsiorsoft.daedalus.dominion.impl;


import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import java.util.LinkedList;
import java.util.List;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;


/**
 * Represent a sorted sequence of {@link Strike}s for a specific {@link ExpirationDate} of a particular underlying instrument.<br>
 * 
 * 
 * @author sleyzerzon
 *
 */
public class Strikes {

	private long timestamp;
	
	private String symbol;
	private String expirationCycle;
	
	//private List<String> expirationCycles = new LinkedList<>();
	private List<Strike> values = new LinkedList<>();
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Strikes[timestamp=")
				.append(timestamp)
				.append(", symbol=")
				.append(symbol)
				.append(", values=")
				/*.append(expirationCycles)*/
				.append(values)
				
				.append("]");
		return builder.toString();
	}
	
	
	/**
	 * Builder for the ExpirationDates domain object
	 * 
	 * @author sleyzerzon
	 *
	 */
	public final static class StrikesBuilder {
			
			private final Strikes strikes = new Strikes();
			
			private StrikesBuilder(){};
			
			public static StrikesBuilder builder() {
				
				StrikesBuilder result = new StrikesBuilder();
				return result;
			}
			
			public Strikes build(){

				
				long now = nowFromEpoch();
				strikes.timestamp = now;
				
				for(Strike strike : strikes.values){
					strike.setTimestamp(now);
				}
				
				return strikes;
			}
			
			public StrikesBuilder forSymbol(String symbol){
				strikes.symbol = symbol;
				return this;
			}
			
			public StrikesBuilder forExpirationCycle(String expirationCycle){
				strikes.expirationCycle = expirationCycle;
				return this;
			}
			
			public StrikesBuilder withValues(List<String> strikesStr){
				//expirationDates.expirationCycles = new LinkedList<String>(datesStr);
				for(String strikeStr : strikesStr){
					
					Strike strike = StrikeBuilder.builder()

							.forSymbol(strikes.symbol)
							.forExpirationCycle(strikes.expirationCycle)
							.withValue(strikeStr)
							.build();
					
					strikes.values.add(strike);
				}
				return this;
			}
			
	}
	
}
