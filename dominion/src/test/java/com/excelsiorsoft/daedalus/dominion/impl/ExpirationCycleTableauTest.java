package com.excelsiorsoft.daedalus.dominion.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau.ExpirationCycleTableauBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType;

import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType.CALL;
import static com.excelsiorsoft.daedalus.dominion.impl.Option.OptionType.PUT;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

public class ExpirationCycleTableauTest {

	ExpirationCycleTableau cut;
	
	@Test
	public void populateSingleTableau() throws Throwable {
		
		List<String> strikesStr =  asList(new String[]{"3","5","8","9","10","11","11.5","12","12.5","13","13.5","14","14.5","15","15.5","16","16.5","17","17.5","18","18.5","19","19.5","20","20.5","21","21.5","22","22.5","23","23.5","24","24.5","25","25.5","26","26.5","27","27.5","28","28.5","29","30","31","32","33","34","35","36","37","38","40"});
		
		long now = nowFromEpoch();
		String underlying = "SLW";
		String expirationCycle = "2017-01-20";
		
		cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now).forExpirationCycle(expirationCycle).withStrikes(strikesStr).build();
		System.out.println("Empty tableau: " + cut);
		
		
		//String singleStrikeName = "13.5";
		String singleStrikeName = "3";
		
		String putType = "P";
		Map<String, Option> _3_strike = cut.getStrikes().get(singleStrikeName);
		OptionBuilder putBuilder = OptionBuilder.builder();
		Option put = putBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(putType).build();
		_3_strike.put(putType, put);
		System.out.println("\nput: " +put);
		
		
		String callType ="C";
		OptionBuilder callBuilder = OptionBuilder.builder();
		Option call = callBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(callType).build();
		_3_strike.put(callType, call);
		System.out.println("\ncall: " +call);
		
		System.out.println("\nTableau with options: " +cut);
	}
	
	
	
	@Test
	public void populateTableauWithSeveralStrikes() throws Throwable {
		
		List<String> strikesStr =  asList(new String[]{"3","5","8","9","10","11","11.5","12","12.5","13","13.5","14","14.5","15","15.5","16","16.5","17","17.5","18","18.5","19","19.5","20","20.5","21","21.5","22","22.5","23","23.5","24","24.5","25","25.5","26","26.5","27","27.5","28","28.5","29","30","31","32","33","34","35","36","37","38","40"});
		
		long now = nowFromEpoch();
		String underlying = "SLW";
		String expirationCycle = "2017-01-20";
		
		cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now).forExpirationCycle(expirationCycle).withStrikes(strikesStr).build();
		System.out.println("Empty tableau: " + cut);
		
		
		//String singleStrikeName = "13.5";
		String threeStrikeName = "3";
		
		String putType = PUT.abbreviation();
		Map<String, Option> _3_strike = cut.getStrikes().get(threeStrikeName);
		OptionBuilder putBuilder = OptionBuilder.builder();
		Option put = putBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(putType).withStrike(threeStrikeName).build();
		_3_strike.put(putType, put);
		System.out.println("\nput: " +put);
		
		
		String callType = CALL.abbreviation();
		OptionBuilder callBuilder = OptionBuilder.builder();
		Option call = callBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(callType).withStrike(threeStrikeName).build();
		_3_strike.put(callType, call);
		System.out.println("\ncall: " +call);
		
		
		
		String eightStrikeName = "8";
		
		//String putType = "P";
		Map<String, Option> _8_strike = cut.getStrikes().get(eightStrikeName);
		putBuilder = OptionBuilder.builder();
		put = putBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(putType).withStrike(eightStrikeName).build();
		_8_strike.put(putType, put);
		System.out.println("\nput: " +put);
		
		
		//String callType ="C";
		callBuilder = OptionBuilder.builder();
		call = callBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(callType).withStrike(eightStrikeName).build();
		_8_strike.put(callType, call);
		System.out.println("\ncall: " +call);
		
		
		System.out.println("\nTableau with options: " +cut);
	}
	
	
	@Test
	public void toStringTestsWithVaryingLevelOfEmptyness() throws Throwable {

		try{
			long now = nowFromEpoch();
			String underlying = "SLW";
			String expirationCycle = "2017-01-20";
			
			cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now).forExpirationCycle(expirationCycle).withStrikes(null).build();
			System.out.println(cut);
		}catch(Throwable t){
			assertTrue(t instanceof RuntimeException);
			System.out.println("Expected: " + t.getMessage());
		}
		
		
		long now = nowFromEpoch();
		String underlying = "SLW";
		String expirationCycle = "2017-01-20";
		
		cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now).forExpirationCycle(expirationCycle).withStrikes(new ArrayList<>()).build();
		System.out.println("\nWith an empty strikes list: " + cut);
		
		List<String> strikes =  asList(new String[]{"3","5","8","9","10","11","11.5","12","12.5","13","13.5","14","14.5","15","15.5","16","16.5","17","17.5","18","18.5","19","19.5","20","20.5","21","21.5","22","22.5","23","23.5","24","24.5","25","25.5","26","26.5","27","27.5","28","28.5","29","30","31","32","33","34","35","36","37","38","40"});
		
		cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now).forExpirationCycle(expirationCycle).withStrikes(strikes).build();
		System.out.println("\nWith a bunch of empty strikes: " + cut);
		
	}

}
