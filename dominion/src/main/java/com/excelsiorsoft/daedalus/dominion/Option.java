package com.excelsiorsoft.daedalus.dominion;

import static com.excelsiorsoft.daedalus.dominion.Option.OptionsSymbologyType.OCC;

import java.util.Date;




import com.excelsiorsoft.daedalus.dominion.InstrumentType.OptionType;

public class Option {

	Underlying underlying;
	InstrumentType optionType;
	// expiration type - european or american

	Date expiration;
	Strike strike;

	OptionsSymbol symbol; // OCC by default, need to create a hierarchy for symbols of different types of underlying (stocks & etf would have their own, especially futures)

	
	public static class OptionsSymbolBuilder{
		
		public String symbolFor(OptionsSymbologyType symbolType, Date expirationDate, OptionType optionType, double strikePrice){
			
			
			return null;
		}
	}
	
	
	
	
	public static enum OptionsSymbologyType {
		OCC;
	}

	public static class OptionsSymbol {

		String value;
		OptionsSymbologyType type;

		OptionsSymbol(String value) {
			this.value = value;
			this.type = OCC;
		}

		OptionsSymbol(String value, OptionsSymbologyType type) {
			this.value = value;
			this.type = type;
		}
	}
}
