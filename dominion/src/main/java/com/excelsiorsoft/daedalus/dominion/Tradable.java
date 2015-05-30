/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

/**
 * @author Simeon
 *
 */
public interface Tradable extends Listable {
	
	Tradable setExchange(String[] exchange);
	String[] getExchange();

}
