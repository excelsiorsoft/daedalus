package com.excelsiorsoft.daedalus.dominion.builder;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sleyzerzon
 *
 */
public class OptionSymbolBuilder {
	
	
    public String buildSymbol(String underlyingSymbol, Date expirationDate, String optionType, double strike)  {

    	String timeString = new SimpleDateFormat("yyMMdd").format(expirationDate);
		
		String paddedPrice = String.format("%08d", (int) (strike * 1000));

		return underlyingSymbol + timeString	+ optionType + paddedPrice;

    }

}
