/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;

/**
 * @author Simeon
 *
 */
public interface Tradable extends Listable {
	
	Tradable setExchange(Exchange exchange);
	Exchange getExchange();

}
