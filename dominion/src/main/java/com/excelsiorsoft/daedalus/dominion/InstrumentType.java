package com.excelsiorsoft.daedalus.dominion;


/**
 * @author Simeon
 *
 */

public enum InstrumentType {
	OPTION, STOCK, ETF, FUTURE;

	public static enum OptionType{
		PUT, CALL;
	}
}