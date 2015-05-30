package com.excelsiorsoft.daedalus.dominion.impl;

//import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.WithSpread;

import static org.apache.commons.lang3.math.NumberUtils.*;

public class AbstractTradableInstrument extends AbstractDomain implements WithSpread {

	protected /*BigDecimal*/ double bid;
	protected /*BigDecimal*/double ask;
	
	public /*BigDecimal*/ double getBid() {
		return bid;
	}

	public WithSpread setBid(/*BigDecimal*/double bid) {
		this.bid = bid;
		return this;
	}
	
	public WithSpread setBid(String bid) {
		this.bid = NumberUtils.isNumber(bid)?/*new BigDecimal(*/toDouble(bid)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		return this;
	}

	public /*BigDecimal*/double getAsk() {
		return ask;
	}

	public WithSpread setAsk(/*BigDecimal*/double ask) {
		this.ask = ask;
		return this;
	}
	 
	public WithSpread setAsk(String ask) {
		this.ask = NumberUtils.isNumber(ask)?/*new BigDecimal(*/toDouble(ask)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		return this;
	}
	
	
	/**
	 * @author Simeon
	 *
	 */

	public static enum InstrumentType {
		OPTION, STOCK, ETF, FUTURE;

		public static enum OptionType{
			PUT("P"), CALL("C");
			
			String abbreviation;
			
			private OptionType(String value){
				this.abbreviation=value;}

			public String abbreviation() {
				return abbreviation;
			}
		}
	}
}
