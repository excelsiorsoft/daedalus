/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;
import com.excelsiorsoft.daedalus.dominion.impl.Instrument;

/**
 * Representation of relevant characreristics of products to be 
 * 
 * @author Simeon
 *
 */
public interface TradeableListable extends Listable$ {
	
	TradeableListable setExchange(Exchange exchange);
	Exchange getExchange();

	
	String getDescription();
	Instrument setDescription(String description);
	
	String getSymbol();
	Instrument setSymbol(String symbol);
	
}
