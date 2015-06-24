/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import com.excelsiorsoft.daedalus.dominion.impl.Instrument;

/**
 * 
 * 
 * @author Simeon
 *
 */
@Deprecated
public interface Listable$ {
	
	String getDescription();
	Instrument setDescription(String description);
	
	String getSymbol();
	TradeableListable setSymbol(String symbol);

}
