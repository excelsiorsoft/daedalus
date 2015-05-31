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
public interface Listable {
	
	String getDescription();
	Instrument setDescription(String description);
	
	String getSymbol();
	Instrument setSymbol(String symbol);

}
