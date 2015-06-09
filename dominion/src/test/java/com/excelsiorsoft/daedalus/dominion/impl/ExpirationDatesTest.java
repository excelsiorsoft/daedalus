package com.excelsiorsoft.daedalus.dominion.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;




import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDates.ExpirationDatesBuilder;

public class ExpirationDatesTest {

	

	private static ExpirationDatesBuilder expDatesBuilder;
	
	@BeforeClass
	public static void setUp(){
		
		expDatesBuilder = ExpirationDatesBuilder.builder();
	}
	
	
	@Test
	public void toStringTesting() {
		
		List<String> dates =  Arrays.asList(new String[]{"2015-06-05","2015-06-12","2015-06-19","2015-06-26","2015-07-02","2015-07-10","2015-07-17","2015-08-21","2015-09-18","2015-12-18","2016-01-15","2017-01-20"});
		
		ExpirationDates expirationDates = expDatesBuilder

				.forSymbol("SLW")
				.withExpirationDates(dates)
				.build();
		System.out.println(expirationDates);
		
	}

}
