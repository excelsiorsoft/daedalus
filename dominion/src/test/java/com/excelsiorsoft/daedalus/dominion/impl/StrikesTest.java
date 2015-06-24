package com.excelsiorsoft.daedalus.dominion.impl;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;






import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDates.ExpirationDatesBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Strikes.StrikesBuilder;

public class StrikesTest {

	

	private static StrikesBuilder strikesBuilder;
	
	@BeforeClass
	public static void setUp(){
		
		strikesBuilder = StrikesBuilder.builder();
	}
	
	
	@Test
	public void toStringTesting() {
		
				
		List<String> strikesStr =  asList(new String[]{"3","5","8","9","10","11","11.5","12","12.5","13","13.5","14","14.5","15","15.5","16","16.5","17","17.5","18","18.5","19","19.5","20","20.5","21","21.5","22","22.5","23","23.5","24","24.5","25","25.5","26","26.5","27","27.5","28","28.5","29","30","31","32","33","34","35","36","37","38","40"});
		
		Strikes strikes = strikesBuilder

				.forSymbol("SLW")
				.forExpirationCycle("2016-01-15")
				.withValues(strikesStr)
				.build();
		System.out.println(strikes);
		
	}

}
