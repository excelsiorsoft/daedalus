/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;

import java.io.Serializable;


/**
 * Representation of financial exchange
 * 
 * @author Simeon
 *
 */
public class Exchange implements Serializable {
	

	private static final long serialVersionUID = -341132477094925756L;
	
	private Exchange(){};

	private String code;
	
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exchange[code=").append(code).append(", description=").append(description).append("]");
		return builder.toString();
	}
	
	
	/**
	 * A utility to fluidly build {@link Exchange} instances
	 * @author Simeon
	 *
	 */
	public static final class ExchangeBuilder{
		
		private final Exchange exchange = new Exchange();
		
		private ExchangeBuilder(){};
		
		public static ExchangeBuilder builder() {
			ExchangeBuilder result = new ExchangeBuilder();
			return result;
		}
		
		public ExchangeBuilder withCode(String code){
			exchange.code = code;
			return this;
		}
		
		public ExchangeBuilder withDescription(String description){
			exchange.description = description;
			return this;
		}
		
		public Exchange build(){
			
			return exchange;
		}
		
	}

}
