package com.excelsiorsoft.daedalus.dominion.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.excelsiorsoft.daedalus.dominion.Phenomenon;
import com.excelsiorsoft.daedalus.dominion.TradeableListable;
import com.excelsiorsoft.daedalus.dominion.impl.builder.NullSupressingStyle;


/**
 * @author Simeon
 *
 */
public class Instrument implements /*Listable$*/ TradeableListable {

private InstrumentType type;
	
	//akin to a ticker for stocks but wider based on instrument type
	private String symbol;
	
	private String description;
	
	private List<Phenomenon> affectedBy = new ArrayList<Phenomenon>();
	
	protected List<Exchange> tradableOnExchanges = new ArrayList<>();
	
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
	public TradeableListable setExchange(Exchange exchange) {
		//implement later
		//this.tradableOnExchanges.add(exchange);
		return this;
	}

	@Override
	public Exchange getExchange() {
		//will implement later - based on instrument type, go over all listings (strikes) for the instrument and build up the set of exhcnages where it's tradeable
		return null;
	}
	
	public String toString() {
		   //return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
		
			StringBuilder builder = new StringBuilder();
			builder.append("Instrument[symbol=").append(symbol).append(", affectedBy=").append(affectedBy).append(", tradableOnExchanges=").append(tradableOnExchanges).append("]");
			return builder.toString();
		
		 }	
	
	
	
	/**
	 * @author Simeon
	 *
	 */
	public static enum InstrumentType {
		OPTION, STOCK, ETF, FUTURE, INDEX, COMMODITY;

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



	protected List<Exchange> getTradableOnExchanges() {
		return tradableOnExchanges;
	}




}
