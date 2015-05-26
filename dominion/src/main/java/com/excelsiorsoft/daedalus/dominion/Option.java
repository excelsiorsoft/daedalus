package com.excelsiorsoft.daedalus.dominion;

import static com.excelsiorsoft.daedalus.dominion.Option.OptionsSymbologyType.OCC;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.excelsiorsoft.daedalus.dominion.InstrumentType.OptionType;

public class Option {

	Underlying underlying;
	OptionType optionType;
	// expiration type - european or american

	Date expirationDate;
	Strike strike;

	String symbol; // OCC by default, need to create a hierarchy for symbols of different types of underlying (stocks & etf would have their own, especially futures)

	
		public String getSymbol(OptionsSymbologyType... symbolType){
			
			String optionSymbol = "";
			
			OptionsSymbologyType optSymbolType = symbolType[0] != null?symbolType[0]:OCC;

				switch (optSymbolType) {
				
				default:
				{
					String timeString = new SimpleDateFormat("yyMMdd").format(expirationDate);
	
					String optnType = optionType.abbreviation();
					
					String paddedPrice = String.format("%08d", (int) (strike.getValue() * 1000));
	
					optionSymbol = underlying.getSymbol() + timeString	+ optnType + paddedPrice;
					
					break;
				}
			}
			
			return optionSymbol;
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
