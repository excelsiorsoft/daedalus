package com.excelsiorsoft.daedalus.dominion.impl;

//import java.math.BigDecimal;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.TradeableListable;
import com.excelsiorsoft.daedalus.dominion.WithSpread;

import static org.apache.commons.lang3.math.NumberUtils.*;

public abstract class AbstractTradeableInstrument extends AbstractDomain implements WithSpread, TradeableListable {

	//private Exchange exchange;
	
	protected /*BigDecimal*/ double bid;
	protected /*BigDecimal*/double ask;
	
	/**
	 * Time of latest ask
	 */
	protected Date askTime;
	
	/**
	 * Time of latest bid
	 */
	protected LocalDateTime bidTime;
	
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
	public abstract TradeableListable setExchange(Exchange exchange);

	@Override
	public abstract Exchange getExchange();

	@Override
	public WithSpread setAskTime(String askTime) {
		this.askTime = Date.from(LocalDateTime
				.parse(askTime, DateTimeFormatter.ofPattern("HH:mm"))
				.atZone(ZoneId.systemDefault()).toInstant());
		return this;
	}

	@Override
	public Date getAskTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WithSpread setAskSize(String askSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAskSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WithSpread setBidTime(String bidTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getBidTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WithSpread setBidSize(String bidSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBidSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public static enum InstrumentType {
		OPTION, STOCK, ETF, FUTURE;


	}
	
}
