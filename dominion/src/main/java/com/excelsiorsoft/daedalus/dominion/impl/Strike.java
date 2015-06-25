/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;

import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;
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
	private int volume;
	private ExpirationDate expirationCycle;
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
	


	/*@Override
	public Exchange getAsQuotedOn() {
		
		return exchange;
	}

	@Override
	public Strike setAsQuotedOn(Exchange exchange) {
		this.exchange = exchange;
		return this;
	}*/
	
	
	public ExpirationDate getExpirationCycle() {
		return expirationCycle;
	}

	public Strike setExpirationCycle(ExpirationDate expirationCycle) {
		this.expirationCycle = expirationCycle;
		return this;
	}
	
	/*@Override
	public WithSpread setVolume(String volume) {
		this.volume=Integer.parseInt(volume);
		return this;
	}

	@Override
	public int getVolume() {
		return volume;
	}*/
	
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
				/*.append(", bid=").append(bid).append(", bidSize=").append(bidSize).append(", bidTime=").append(bidTime)
				.append(", ask=").append(ask).append(", askSize=").append(askSize).append(", askTime=").append(askTime)
				.append(", exchange=").append(exchange)
				.append(", volume=").append(volume)*/
				.append("]");
		return builder.toString();
	}
	
	
	/**
	 * @author Simeon
	 *
	 */
	public final static class StrikeBuilder {
		
		long now = nowFromEpoch();
		
		private final Strike strike = new Strike();
		
		private StrikeBuilder(){};
		
		public static StrikeBuilder builder() {
			
			StrikeBuilder result = new StrikeBuilder();
			return result;
		}
		
		public Strike build(){
			strike.timestamp = now;
			return strike;
		}
		
		public StrikeBuilder forSymbol(String symbol){
			strike.symbol = symbol;
			return this;
		}
		
		public StrikeBuilder forExpirationCycle(String expirationCycle){
			
			strike.expirationCycle = ExpirationDateBuilder.builder()
			.forSymbol(strike.symbol)
			.forCycle(expirationCycle)
			.asOf(now)
			.build();
			strike.expirationCycle.setCycle(expirationCycle);
			return this;
		}
		
		public StrikeBuilder withValue(String value){
			strike.setValue(value);
			return this;
		}
		
		/*public StrikeBuilder withBid(String bid){
			strike.setBid(bid);
			return this;
		}
		
		public StrikeBuilder withBidSize(String bidSize){
			strike.setBidSize(bidSize);
			return this;
		}	
		
		public StrikeBuilder withBidTime(String bidTime){
			strike.setBidTime(bidTime);
			return this;
		}
		
		public StrikeBuilder withAsk(String ask){
			strike.setAsk(ask);
			return this;
		}
		
		public StrikeBuilder withAskSize(String askSize){
			strike.setAskSize(askSize);
			return this;
		}
		
		public StrikeBuilder withAskTime(String askTime){
			strike.setAskTime(askTime);
			return this;
		}
		
		public StrikeBuilder asQuotedOne(String askTime){
			strike.setAskTime(askTime);
			return this;
		}		*/
	}





	













}
