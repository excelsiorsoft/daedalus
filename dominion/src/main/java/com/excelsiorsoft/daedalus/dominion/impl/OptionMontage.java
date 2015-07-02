package com.excelsiorsoft.daedalus.dominion.impl;


import java.util.LinkedHashMap;
import java.util.Map;

import com.excelsiorsoft.daedalus.dominion.WithSymbol;
import com.excelsiorsoft.daedalus.dominion.WithTimestamp;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau.ExpirationCycleTableauBuilder;

/**
 * Full representation of all option activity for a given underlying symbol, including the activity for the latter
 * 
 * @author sleyzerzon
 *
 */
public class OptionMontage implements WithSymbol, WithTimestamp{

	/*
	    Tableau for SLW at 2015-08-21: ExpirationCycleTableau[timestamp=1435780126, symbol=SLW, strikes={3.0={}, 5.0={}, 8.0={}, 9.0={}, 10.0={}, 11.0={}, 11.5={}, 12.0={}, 12.5={}, 13.0={}, 13.5={}, 14.0={}, 14.5={}, 15.0={}, 15.5={}, 16.0={}, 16.5={}, 17.0={}, 17.5={}, 18.0={}, 18.5={}, 19.0={}, 19.5={}, 20.0={}, 20.5={}, 21.0={}, 21.5={}, 22.0={}, 22.5={}, 23.0={}, 23.5={}, 24.0={}, 24.5={}, 25.0={}, 25.5={}, 26.0={}, 26.5={}, 27.0={}, 27.5={}, 28.0={}, 28.5={}, 29.0={}, 30.0={}, 31.0={}, 32.0={}, 33.0={}, 34.0={}, 35.0={}, 36.0={}, 37.0={}, 38.0={}, 40.0={}}]
	  
	   ...
	   
		Tableau for SLW at 2017-01-20: ExpirationCycleTableau[timestamp=1435780126, symbol=SLW, strikes={3.0={}, 5.0={}, 8.0={}, 9.0={}, 10.0={}, 11.0={}, 11.5={}, 12.0={}, 12.5={}, 13.0={}, 13.5={}, 14.0={}, 14.5={}, 15.0={}, 15.5={}, 16.0={}, 16.5={}, 17.0={}, 17.5={}, 18.0={}, 18.5={}, 19.0={}, 19.5={}, 20.0={}, 20.5={}, 21.0={}, 21.5={}, 22.0={}, 22.5={}, 23.0={}, 23.5={}, 24.0={}, 24.5={}, 25.0={}, 25.5={}, 26.0={}, 26.5={}, 27.0={}, 27.5={}, 28.0={}, 28.5={}, 29.0={}, 30.0={}, 31.0={}, 32.0={}, 33.0={}, 34.0={}, 35.0={}, 36.0={}, 37.0={}, 38.0={}, 40.0={}}]
	   
	   ...
	*/
	
	private Map<String, ExpirationCycleTableau> tableus = new LinkedHashMap<>(); //expCycle[] -> Tableau for that cycle.
	
	private long timestamp;
	private String symbol;

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public String getSymbol() throws Throwable {
		return symbol;
	}

	@Override
	public WithSymbol setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("OptionMontage[timestamp=")
				.append(timestamp)
				.append(", symbol=")
				.append(symbol)
				.append(", tableus=")
				.append(tableus)
				
				.append("]");
		return builder.toString();

	}


	public final static class OptionMontageBuilder {
		
		private final OptionMontage montage = new OptionMontage();
		//private final OptionBuilder optionBuilder = OptionBuilder.builder();
		
		private OptionMontageBuilder(){};
		
		public static OptionMontageBuilder builder() {
			
			OptionMontageBuilder result = new OptionMontageBuilder();
			return result;
		}
		
		
		public OptionMontageBuilder asOf(long timestamp){
			montage.timestamp = timestamp;
			return this;
		}
		
		
		public OptionMontage build(){
			
			return montage;
		}
		
		public OptionMontageBuilder forSymbol(String symbol){
			montage.symbol = symbol;
			return this;
		}
	}
	
}
