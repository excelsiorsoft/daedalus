/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.WithSymbol;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType;


/**
 * @author Simeon
 * 
 */
public final class Strike extends AbstractDomain implements WithSymbol {

	//private Exchange exchange;
	//TODO: need to have an Option field on which the optionType, etc. will be housed, this representation only specific to Yahoo's options listing page
	private OptionType type;
	private /*BigDecimal*/ double value;
	//private ExpirationDate expirationCycle;
	private String expirationCycle;
	private String symbol;
	

	
	
	public /*BigDecimal*/double getValue() {
		return value;
	}

	public Strike setValue(/*BigDecimal*/double value) {
		this.value = value;
		return this;
	}
	
	public Strike setValue(String value) {
		this.value=NumberUtils.isNumber(value)?/*new BigDecimal(*/toDouble(value)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		
		return this;
	}


	public OptionType getType() { 
		return type;
	}

	public Strike setType(OptionType type) {
		this.type = type;
		return this;
	}
	
	
	
	/*public ExpirationDate getExpirationCycle() {
		return expirationCycle;
	}

	public Strike setExpirationCycle(ExpirationDate expirationCycle) {
		this.expirationCycle = expirationCycle;
		return this;
	}*/
	
	
	public String getExpirationCycle() {
		return expirationCycle;
	}

	public Strike setExpirationCycle(String expirationCycle) {
		this.expirationCycle = expirationCycle;
		return this;
	}

	
	@Override
	public AbstractDomain setTimestamp(long timestamp) {
		super.setTimestamp(timestamp);
		return this;
	}
	

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public WithSymbol setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Strike[timestamp=").append(timestamp).append(", symbol=").append(symbol)
				.append(", expirationCycle=").append(expirationCycle).append(", value=").append(value)
				.append("]");
		return builder.toString();
	}
	
	
	/**
	 * @author Simeon
	 *
	 */
	public final static class StrikeBuilder {

		
		private final Strike strike = new Strike();
		
		private StrikeBuilder(){};
		
		public static StrikeBuilder builder() {
			
			StrikeBuilder result = new StrikeBuilder();
			return result;
		}
		
		public Strike build(){
			return strike;
		}
		
		public StrikeBuilder forSymbol(String symbol){
			strike.symbol = symbol;
			return this;
		}
		
		public StrikeBuilder asOf(long timestamp){
			strike.timestamp = timestamp;
			return this;
		}
		
		public StrikeBuilder forExpirationCycle(String expirationCycle){
			
			/*strike.expirationCycle = ExpirationDateBuilder.builder()
				.asOf(nowstrike.timestamp)
				.forSymbol(strike.symbol)
				.forCycle(expirationCycle)
				.build();
			strike.expirationCycle.setCycle(expirationCycle);*/
			strike.setExpirationCycle(expirationCycle);
			return this;
		}
		
		public StrikeBuilder withValue(String value){
			strike.setValue(value);
			return this;
		}
		
		
	}





	













}
