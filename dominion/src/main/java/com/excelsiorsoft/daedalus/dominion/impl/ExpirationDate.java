
package com.excelsiorsoft.daedalus.dominion.impl;

import com.excelsiorsoft.daedalus.dominion.WithSymbol;
import com.excelsiorsoft.daedalus.dominion.WithTimestamp;



/**
 * 
 * 
 * @author sleyzerzon
 *
 */
public class ExpirationDate implements WithTimestamp, WithSymbol {
	
	
	private long timestamp;
	
	private String symbol;
	
	private String cycle;
	
	private ExpirationDate() {}
	
	
	//public static final String XDATE_FORMAT = "ExpirationDateFormat";

	public static enum ExpirationDateFormat {
		
		HIPHENATED, //2016-01-15 
		SQUIZZED;	//20160115
		
		public static final String XDATE_FORMAT = "ExpirationDateFormat";
	}
	
	

	public long getTimestamp() {
		return timestamp;
	}



	public  ExpirationDate setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}



	public String getSymbol() {
		return symbol;
	}



	public ExpirationDate setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}



	public String getCycle() {
		return cycle;
	}



	protected void setCycle(String cycle) {
		this.cycle = cycle;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpirationDate[timestamp=")
				.append(timestamp)
				.append(", symbol=")
				.append(symbol)
				.append(", cycle=")
				.append(cycle)
				
				.append("]");
		return builder.toString();
	}
	

	public final static class ExpirationDateBuilder {
		
		private final ExpirationDate expirationDate = new ExpirationDate();
		
		private ExpirationDateBuilder(){};
		
		public static ExpirationDateBuilder builder() {
			
			ExpirationDateBuilder result = new ExpirationDateBuilder();
			return result;
		}
		
		public ExpirationDate build(){
			
			return expirationDate;
		}
		
		public ExpirationDateBuilder forSymbol(String symbol){
			expirationDate.symbol = symbol;
			return this;
		}
		
		public ExpirationDateBuilder forCycle(String date){
			expirationDate.cycle = date;
			return this;
		}
		
		public ExpirationDateBuilder asOf(long now){
			expirationDate.timestamp = now;
			return this;
		}
		
}

}
