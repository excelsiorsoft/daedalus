package com.excelsiorsoft.daedalus.dominion.impl.builder;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sleyzerzon
 *
 */
@Deprecated
public final class OptionSymbolBuilder$ {
	
	@Deprecated
    public static String buildSymbol(final String underlyingSymbol, final Date expirationDate, final String optionType, final double strike)  {

    	String timeString = new SimpleDateFormat("yyMMdd").format(expirationDate);
		
		String paddedPrice = String.format("%08d", (int) (strike * 1000));

		return new StringBuilder(underlyingSymbol).append(timeString).append(optionType).append(paddedPrice).toString().toUpperCase();

    }

}
