/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import com.excelsiorsoft.daedalus.dominion.impl.Instrument;

/**
 * Representation of relevant characteristics of products to be 
 * 
 * @author Simeon
 *
 */
public interface TradeableListable extends Listable$ {
	
	/*TradeableListable setExchange(Exchange exchange);
	Exchange getExchange();*/

	
	String getDescription();
	Instrument setDescription(String description);
	
	String getSymbol();
	TradeableListable setSymbol(String symbol);
	
}
