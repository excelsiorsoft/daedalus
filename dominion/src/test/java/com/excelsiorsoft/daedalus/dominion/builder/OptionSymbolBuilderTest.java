package com.excelsiorsoft.daedalus.dominion.builder;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.InstrumentType.OptionType;

public class OptionSymbolBuilderTest {

	
	private static OptionSymbolBuilder builder = null;
	
	@BeforeClass
	public static void setUp(){
		builder = new OptionSymbolBuilder();
	}
	
	@Test
	public void creatingSymbol() throws Throwable {
		
		String underlying = "slw";
		Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-20");
		String optionType = OptionType.CALL.abbreviation();
		double strike = 18.00;
		
		String result = builder.buildSymbol(underlying, expirationDate, optionType, strike);
		assertEquals(result, "SLW170120C00018000");
		System.out.println(result);
		
	}

}
