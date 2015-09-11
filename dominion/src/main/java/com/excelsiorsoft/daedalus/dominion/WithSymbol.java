package com.excelsiorsoft.daedalus.dominion;

public interface WithSymbol {

	final String SYMBOL = "symbol";
	
	String getSymbol() throws Throwable;
	WithSymbol setSymbol(String symbol);
	
}
