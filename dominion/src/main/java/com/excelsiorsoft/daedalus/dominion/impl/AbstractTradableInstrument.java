package com.excelsiorsoft.daedalus.dominion.impl;

//import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.Tradeable;
import com.excelsiorsoft.daedalus.dominion.WithSpread;

import static org.apache.commons.lang3.math.NumberUtils.*;

public abstract class AbstractTradableInstrument extends AbstractDomain implements WithSpread, Tradeable {

	private Exchange exchange;
	
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


	}


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrument setDescription(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instrument setSymbol(String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public abstract Tradeable setExchange(Exchange exchange);

	@Override
	public abstract Exchange getExchange();

	
}
