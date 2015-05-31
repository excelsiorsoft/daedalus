/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;

/**
 * Representation of relevant characreristics of products to be 
 * 
 * @author Simeon
 *
 */
public interface Tradeable extends Listable {
	
	Tradeable setExchange(Exchange exchange);
	Exchange getExchange();

}
