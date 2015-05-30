/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;


import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.InstrumentType.OptionType;

/**
 * @author Simeon
 * 
 */

public final class Strike extends AbstractTradableInstrument/* implements WithSpread*/ {
	
	
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

	/*public BigDecimal getBid() {
		return bid;
	}*/

	/*public WithSpread setBid(BigDecimal bid) {
		this.bid = bid;
		return this;
	}*/
	
	/*public WithSpread setBid(String bid) {
		this.bid = NumberUtils.isNumber(bid)?new BigDecimal(bid):new BigDecimal(ZERO);
		return this;
	}*/

	/*public BigDecimal getAsk() {
		return ask;
	}*/

	/*public WithSpread setAsk(BigDecimal ask) {
		this.ask = ask;
		return this;
	}*/
	 
	/*public WithSpread setAsk(String ask) {
		this.ask = NumberUtils.isNumber(ask)?new BigDecimal(ask):new BigDecimal(ZERO);
		return this;
	}*/


	public OptionType getType() {
		return type;
	}

	public WithSpread setType(OptionType type) {
		this.type = type;
		return this;
	}
}
