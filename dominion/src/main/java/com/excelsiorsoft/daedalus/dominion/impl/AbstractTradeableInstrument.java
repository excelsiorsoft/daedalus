package com.excelsiorsoft.daedalus.dominion.impl;

//import java.math.BigDecimal;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.dominion.TradeableListable;
import com.excelsiorsoft.daedalus.dominion.WithSpread;

import static org.apache.commons.lang3.math.NumberUtils.*;

public abstract class AbstractTradeableInstrument extends AbstractDomain implements WithSpread, TradeableListable {

	//private Exchange exchange;
	
	protected /*BigDecimal*/ double bid;
	protected /*BigDecimal*/double ask;
	
	protected int bidSize;
	protected int askSize;
	
	/**
	 * Time of latest ask
	 */
	protected Date askTime;
	
	/**
	 * Time of latest bid
	 */
	protected Date bidTime;
	
	protected String symbol;
	
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
		return symbol;
	}

	@Override
	public TradeableListable setSymbol(String symbol) {
		return this;
	}

	/*@Override
	public abstract TradeableListable setExchange(Exchange exchange);*/

	/*@Override
	public abstract Exchange getExchange();*/

	@Override
	public WithSpread setAskTime(String askTime) {
		/*this.askTime = Date.from(LocalDateTime
				.parse(askTime, DateTimeFormatter.ofPattern("HH:mm"))
				.atZone(ZoneId.systemDefault()).toInstant());
		return this;*/
		
		LocalTime localAskTime = LocalTime.parse(askTime);
		Instant instant = localAskTime.atDate(LocalDate.now()).
		        atZone(ZoneId.systemDefault()).toInstant();
		this.askTime = Date.from(instant);
        System.out.println(this.askTime);
		return this;
	}

	@Override
	public Date getAskTime() {
		
		return this.bidTime;
	}

	@Override
	public WithSpread setAskSize(String askSize) {
		this.askSize = Integer.parseInt(askSize);
		return this;
	}

	@Override
	public int getAskSize() {
		
		return this.askSize;
	}

	/* 
	 * http://blog.progs.be/542/date-to-java-time
	 * 
	 * (non-Javadoc)
	 * @see com.excelsiorsoft.daedalus.dominion.WithSpread#setBidTime(java.lang.String)
	 */
	@Override
	public WithSpread setBidTime(String bidTime) {
		/*this.bidTime = Date.from(LocalDateTime
				.parse(bidTime, DateTimeFormatter.ofPattern("HH:mm"))
				.atZone(ZoneId.systemDefault()).toInstant());*/
		
		//LocalDateTime.parse(bidTime, DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
		
		
		LocalTime localBidTime = LocalTime.parse(bidTime);
		Instant instant = localBidTime.atDate(LocalDate.now()).
		        atZone(ZoneId.systemDefault()).toInstant();
		this.bidTime = Date.from(instant);
        System.out.println(this.bidTime);
		return this;
	}

	@Override
	public Date getBidTime() {
		
		return this.bidTime;
	}

	@Override
	public WithSpread setBidSize(String bidSize) {
		this.bidSize = Integer.parseInt(bidSize);
		return this;
	}

	@Override
	public int getBidSize() {
		
		return this.bidSize;
	}

	

	/*public static class AbstractTradeableInstrumentBuilder {
		
		
	}*/


	/**
	 * @author Simeon
	 *
	 */
	public static enum InstrumentType {
		OPTION, STOCK, ETF, FUTURE, INDEX, COMMODITY;
	
		/*public static enum OptionType{
			PUT("P"), CALL("C");
			
			String abbreviation;
			
			private OptionType(String value){
				this.abbreviation=value;}
	
			public String abbreviation() {
				return abbreviation;
			}
		}*/
	}


	@Override
	public abstract WithSpread setAsQuotedOn(Exchange exchange);

	@Override
	public abstract Exchange getAsQuotedOn();

	@Override
	public abstract WithSpread setVolume(String volume);

	@Override
	public abstract int getVolume();
	
}
