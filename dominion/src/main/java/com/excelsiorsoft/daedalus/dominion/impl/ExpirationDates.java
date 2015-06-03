/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;


import java.util.LinkedList;
import java.util.List;

import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;

/**
 * Represent a sorted sequence of an option expiration cycles for a particular underlying in YYYY-MM-DD format.<br>
 * Each expiration cycle in turn would have a set of {@link Strike}s
 * 
 * @author sleyzerzon
 *
 */
public class ExpirationDates {

	private long timestamp;
	
	private String symbol;
	
	private List<String> expirationCycles = new LinkedList<>();
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpirationDates[timestamp=")
				.append(timestamp)
				.append(", symbol=")
				.append(symbol)
				.append(", expirationCycles=")
				.append(expirationCycles)
				
				.append("]");
		return builder.toString();
	}
	
	
	/**
	 * Builder for the ExpirationDates domain object
	 * 
	 * @author sleyzerzon
	 *
	 */
	public final static class ExpirationDatesBuilder {
			
			private final ExpirationDates expirationDates = new ExpirationDates();
			
			private ExpirationDatesBuilder(){};
			
			public static ExpirationDatesBuilder builder() {
				
				ExpirationDatesBuilder result = new ExpirationDatesBuilder();
				return result;
			}
			
			public ExpirationDates build(){
				
				return expirationDates;
			}
			
			public ExpirationDatesBuilder forSymbol(String symbol){
				expirationDates.symbol = symbol;
				return this;
			}
			
			public ExpirationDatesBuilder withExpirationDates(List<String> dates){
				expirationDates.expirationCycles = new LinkedList<String>(dates);
				return this;
			}
			
	}
	
}
