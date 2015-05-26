package com.excelsiorsoft.daedalus.dominion;


/**
 * @author Simeon
 *
 */

public enum InstrumentType {
	OPTION, STOCK, ETF, FUTURE;

	public static enum OptionType{
		PUT("P"), CALL("C");
		
		String abbreviation;
		
		OptionType(String value){
			this.abbreviation=value;}

		public String abbreviation() {
			return abbreviation;
		}
	}
}