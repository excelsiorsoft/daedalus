package com.excelsiorsoft.daedalus.dominion.impl;


import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType.CALL;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

//import com.excelsiorsoft.daedalus.dominion.impl.builder.OptionSymbolBuilder$;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionSymbolBuilder;
import com.excelsiorsoft.daedalus.util.Significant;

@Significant
public class OptionSymbolBuilderTest {

	
	private static OptionSymbolBuilder builder = null;
	
	@BeforeClass
	public static void setUp(){
		builder = new OptionSymbolBuilder();
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void creatingSymbol() throws Throwable {
		
		String underlying = "slw";
		//Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-20");
		String expirationDate = "2017-01-20";
		String optionType = CALL.abbreviation();
		double strike = 18.00;
		
		String result = builder.buildSymbol(underlying, expirationDate, optionType, strike);
		assertEquals(result, "SLW170120C00018000");
		System.out.println(result);
		
	}

}
