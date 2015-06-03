package com.excelsiorsoft.daedalus.dominion.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDate.ExpirationDateBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.ExpirationDates.ExpirationDatesBuilder;

public class ExpirationDateTest {

private static ExpirationDateBuilder expDateBuilder;
	
	@BeforeClass
	public static void setUp(){
		
		expDateBuilder = ExpirationDateBuilder.builder();
	}
	
	
	@Test
	public void toStringTesting() {
		
		//List<String> dates =  Arrays.asList(new String[]{"2015-06-05","2015-06-12","2015-06-19","2015-06-26","2015-07-02","2015-07-10","2015-07-17","2015-08-21","2015-09-18","2015-12-18","2016-01-15","2017-01-20"});
		
		ExpirationDate expirationDates = expDateBuilder

				.forSymbol("SLW")
				.forCycle("2015-06-05")
				.build();
		System.out.println(expirationDates);
		
	}

}
