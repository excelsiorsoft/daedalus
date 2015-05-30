/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.Tradable;
import com.excelsiorsoft.daedalus.dominion.WithSpread;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType;


/**
 * @author Simeon
 * 
 */
public final class Strike extends AbstractTradableInstrument/* implements WithSpread*/ {

	private Exchange exchange;
	//TODO: need to have an Option field on which the optionType, etc. will be housed, this representation only specific to Yahoo's options listing page
	private OptionType type;
	private /*BigDecimal*/ double value;
	/*private BigDecimal bid;
	private BigDecimal ask;*/

	
	public /*BigDecimal*/double getValue() {
		return value;
	}

	public WithSpread setValue(/*BigDecimal*/double value) {
		this.value = value;
		return this;
	}
	
	public WithSpread setValue(String value) {
		this.value=NumberUtils.isNumber(value)?/*new BigDecimal(*/toDouble(value)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		
		return this;
	}


	public OptionType getType() {
		return type;
	}

	public WithSpread setType(OptionType type) {
		this.type = type;
		return this;
	}
	


	@Override
	public Exchange getExchange() {
		
		return exchange;
	}

	@Override
	public Tradable setExchange(Exchange exchange) {
		this.exchange = exchange;
		return this;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Strike[value=").append(value).append(", bid=").append(bid).append(", ask=").append(ask).append(", exchange=").append(exchange).append("]");
		return builder.toString();
	}
}
