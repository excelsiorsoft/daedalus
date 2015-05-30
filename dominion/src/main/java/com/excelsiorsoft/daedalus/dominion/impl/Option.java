package com.excelsiorsoft.daedalus.dominion.impl;


import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionSymbologyType.OCC;
import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType.*;

import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * Representation of an option financial instrument
 * 
 * 
 * @author sleyzerzon
 *
 */
//@JsonDeserialize(using = OptionDeserializer.class)
public class Option extends /*AbstractTradable*/Instrument {

	private Instrument underlying = new Instrument(); 
	private OptionType optionType; //put or call

	// expiration type - european or american

	private Date expirationDate;
	private Strike strike = new Strike();

	private OptionSymbol symbol; // OCC by default, need to create a hierarchy
									// for symbols of different types of
									// underlying (stocks & etf would have their
									// own, especially futures)
	private OptionSymbolBuilder symbolBuilder = new OptionSymbolBuilder();

	private Option(){}
	
	
	/**
	 * @return
	 */
	public String getSymbol(){
		
		return symbolBuilder.buildSymbol(underlying.getSymbol(), expirationDate, optionType.abbreviation(), strike.getValue());
	}
	
	/**
	 * @param symbolType
	 * @return
	 */
	public String getSymbol(OptionSymbologyType symbolType){

		return symbolBuilder.buildSymbol(symbolType, underlying.getSymbol(), expirationDate, optionType.abbreviation(), strike.getValue());
	}

	
	
	
	
	
	/**
	 * Encapsulates different existing specifications of options symbology, defaulting to the OCC
	 * 
	 * @author sleyzerzon
	 *
	 */
	public static enum OptionSymbologyType {
		OCC;
	}

	/**
	 * Representation of an option symbol 
	 * 
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
	 * Utility to create a symbol for an option from its constituent parts </br>
	 * Conforms to a specific option symbology specification. </br>
	 * Defaults to the OCC.
	 * 
	 * @author sleyzerzon
	 *
	 */
	public static class OptionSymbolBuilder {
		
	    	
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
	    
		
	    private String occSpecification(OptionSymbologyType symbolType, String underlyingSymbol, Date expirationDate, String optionType, double strike){
					
					String timeString = new SimpleDateFormat("yyMMdd").format(expirationDate);
					
					String paddedPrice = String.format("%08d", (int) (strike * 1000));
		
					return new StringBuilder(underlyingSymbol).append(timeString).append(optionType).append(paddedPrice).toString().toUpperCase();
				}
		

	}
	
	/**
	 * A utility to fluidly build option instances
	 * 
	 * @author sleyzerzon
	 *
	 */
	public static class OptionBuilder {
		
		private Option option = new Option();
		
		private OptionBuilder(){}
		

		public OptionBuilder withStrike(double strike){
			
			option.strike.setValue(strike);
			return this;
		}
		
		public OptionBuilder ofType(OptionType type){
			
			option.optionType = type;
			return this;
		}
		
		public OptionBuilder ofType(String type){
			
			if (type.equalsIgnoreCase("PUT") || type.equalsIgnoreCase("P")){
				
				option.optionType = PUT;
			
			} else if (type.equalsIgnoreCase("CALL") || type.equalsIgnoreCase("C")){
				
				option.optionType = CALL;
			}
			
			return this;
		}
		
		public OptionBuilder withExpiration(Date expirDate){
			
			option.expirationDate = expirDate;
			return this;
		}
		
		public OptionBuilder withExpiration(String expiration) throws Throwable{
			
			option.expirationDate = new SimpleDateFormat("yyyyMMdd").parse(expiration);
			return this;
			
		}

		public static OptionBuilder init() {
			OptionBuilder result = new OptionBuilder();
			return result;
		}
		
		public OptionBuilder withUnderlying(Instrument underlying){
			
			option.underlying = underlying;
			return this;
		}
		
		public  OptionBuilder withUnderlying(String symbol){
			
			option.underlying.setSymbol(symbol);
			return this;
		}
		
		public Option build(){
			
			return option;
		}
	}
	
	
	/**
	 * @author Simeon
	 *
	 */
	public static enum OptionType{
		PUT("P"), CALL("C");
		
		String abbreviation;
		
		private OptionType(String value){
			this.abbreviation=value;}

		public String abbreviation() {
			return abbreviation;
		}
	}
	
}
