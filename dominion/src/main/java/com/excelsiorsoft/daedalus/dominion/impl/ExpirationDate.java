
package com.excelsiorsoft.daedalus.dominion.impl;



/**
 * 
 * 
 * @author sleyzerzon
 *
 */
public class ExpirationDate {
	
	
	private long timestamp;
	
	private String symbol;
	
	private String cycle;
	
	private ExpirationDate() {}
	
	

	public long getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}



	public String getSymbol() {
		return symbol;
	}



	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}



	public String getCycle() {
		return cycle;
	}



	public void setCycle(String cycle) {
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
