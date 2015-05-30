/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.excelsiorsoft.daedalus.dominion.builder.NullSupressingStyle;


/**
 * @author Simeon
 *
 */
public class Instrument implements /*Listable*/ Tradable {

private InstrumentType type;
	
	//akin to a ticker for stocks but wider based on instrument type
	private String symbol;
	
	private String description;
	
	private List<Phenomenon> affectedBy = new ArrayList<Phenomenon>();
	
	private String [] tradableOnExchanges = new String[]{};
	
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
	public Tradable setExchange(String[] exchanges) {
		
		this.tradableOnExchanges = exchanges;
		return this;
	}

	@Override
	public String[] getExchange() {
		
		return tradableOnExchanges;
	}
	
	public String toString() {
		   return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
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
