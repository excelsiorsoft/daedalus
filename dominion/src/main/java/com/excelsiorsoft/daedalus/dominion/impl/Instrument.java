package com.excelsiorsoft.daedalus.dominion.impl;

import java.util.ArrayList;
import java.util.List;

import com.excelsiorsoft.daedalus.dominion.Phenomenon;
import com.excelsiorsoft.daedalus.dominion.WithDescription;
import com.excelsiorsoft.daedalus.dominion.WithSymbol;
import com.excelsiorsoft.daedalus.dominion.WithTimestamp;
import com.excelsiorsoft.daedalus.dominion.impl.AbstractTradeableInstrument.InstrumentType;


/**
 * @author Simeon
 *
 */
public class Instrument implements WithDescription, WithTimestamp, WithSymbol {

	private InstrumentType type;
	
	//akin to a ticker for stocks but wider based on instrument type
	private String symbol;
	
	private String description;
	
	private List<Phenomenon> affectedBy = new ArrayList<Phenomenon>();
	
	protected List<Exchange> tradableOnExchanges = new ArrayList<>();
	
	protected long timestamp;
	
	public InstrumentType getType() {
		return type;
	}

	public Instrument setType(InstrumentType type) {
		this.type = type;
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public Instrument setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Instrument setDescription(String description) { 
		this.description = description;
		return this;
	}
	
	
	public List<Phenomenon> getAffectedBy() {
		return affectedBy;
	}

	public Instrument setAffectedBy(List<Phenomenon> affectedBy) {

		this.affectedBy = affectedBy;
		return this;
	}
	
	@Override
	public long getTimestamp() {
		return timestamp;
	}

	//@Override 
	@Deprecated //potentially remove and do the setting via an internal builder instance
	public WithTimestamp setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	/*@Override
	public TradeableListable setExchange(Exchange exchange) {
		//implement later
		//this.tradableOnExchanges.add(exchange);
		return this;
	}*/

	/*@Override
	public Exchange getExchange() {
		//will implement later - based on instrument type, go over all listings (strikes) for the instrument and build up the set of exhcnages where it's tradeable
		return null;
	}*/
	
	public String toString() {
		   //return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
		
			StringBuilder builder = new StringBuilder();
			builder.append("Instrument[timestamp=").append(timestamp).append(", symbol=").append(symbol).append(", affectedBy=").append(affectedBy).append(", tradableOnExchanges=").append(tradableOnExchanges).append("]");
			return builder.toString();
		
		 }	
	
	
	
	protected List<Exchange> getTradableOnExchanges() {
		return tradableOnExchanges;
	}




}
