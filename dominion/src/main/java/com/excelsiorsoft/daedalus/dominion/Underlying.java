/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Simeon
 *
 */
public class Underlying extends AbstractTradableDomain /*implements Tradable*/ {
	
	private InstrumentType type;
	
	//akin to a ticker for stocks but wider based on instrument type
	private String symbol;
	
	private String description;
	
	/*private BigDecimal bid;
	private BigDecimal ask;*/
	

	private List<Phenomenon> affectedBy = new ArrayList<Phenomenon>();

	public InstrumentType getType() {
		return type;
	}

	public Underlying setType(InstrumentType type) {
		this.type = type;
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public Underlying setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Phenomenon> getAffectedBy() {
		return affectedBy;
	}

	public void setAffectedBy(List<Phenomenon> affectedBy) {

		this.affectedBy = affectedBy;
	}


}
