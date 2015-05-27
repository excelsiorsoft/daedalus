package com.excelsiorsoft.daedalus.dominion;

import static com.excelsiorsoft.daedalus.dominion.Option.OptionSymbologyType.OCC;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.excelsiorsoft.daedalus.dominion.InstrumentType.OptionType;

public class Option extends AbstractTradableInstrument {

	private Underlying underlying;
	private OptionType optionType; //put or call

	// expiration type - european or american

	private Date expirationDate;
	private Strike strike;

	private OptionSymbol symbol; // OCC by default, need to create a hierarchy
									// for symbols of different types of
									// underlying (stocks & etf would have their
									// own, especially futures)
	private OptionSymbolBuilder symbolBuilder = new OptionSymbolBuilder();


	/**
	 * @return
	 */
	public String getSymbol(){
		
		return symbolBuilder.buildSymbol(underlying.getSymbol(), expirationDate, optionType.abbreviation, strike.getValue());
	}
	
	/**
	 * @param symbolType
	 * @return
	 */
	public String getSymbol(OptionSymbologyType symbolType){

		return symbolBuilder.buildSymbol(symbolType, underlying.getSymbol(), expirationDate, optionType.abbreviation, strike.getValue());
	}

	
	
	
	
	
	/**
	 * @author sleyzerzon
	 *
	 */
	public static enum OptionSymbologyType {
		OCC;
	}

	/**
	 * @author sleyzerzon
	 *
	 */
	public static class OptionSymbol {

		String value;
		OptionSymbologyType type;

		OptionSymbol(String value) {
			this.value = value;
			this.type = OCC;
		}

		OptionSymbol(String value, OptionSymbologyType type) {
			this.value = value;
			this.type = type;
		}
	}

	
	/**
	 * @author sleyzerzon
	 *
	 */
	public static class OptionSymbolBuilder {
		

		private String occSpecification(OptionSymbologyType symbolType, String underlyingSymbol, Date expirationDate, String optionType, double strike){
			
			String timeString = new SimpleDateFormat("yyMMdd").format(expirationDate);
			
			String paddedPrice = String.format("%08d", (int) (strike * 1000));

			return new StringBuilder(underlyingSymbol).append(timeString).append(optionType).append(paddedPrice).toString().toUpperCase();
		}
		
	    	
		public String buildSymbol(OptionSymbologyType symbolType, String underlyingSymbol, Date expirationDate, String optionType, double strike){
			
			String result = "";
			
			switch(symbolType){
			
			case OCC: 
				
				occSpecification(symbolType, underlyingSymbol, expirationDate, optionType, strike); break;
			
			default:
				
				occSpecification(symbolType, underlyingSymbol, expirationDate, optionType, strike); break;
				
			}
			
			return result;
			
		}
		
	    public String buildSymbol(String underlyingSymbol, Date expirationDate, String optionType, double strike)  {

	    	return buildSymbol(OCC, underlyingSymbol, expirationDate, optionType, strike);


	    }

	}
	
	
}
