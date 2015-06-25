package com.excelsiorsoft.daedalus.dominion.impl;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.Map;
import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau.ExpirationCycleTableauBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;

public class ExpirationCycleTableauTest {

	ExpirationCycleTableau cut;
	
	@Test
	public void tryingToString() throws Throwable {
		
		List<String> strikesStr =  asList(new String[]{"3","5","8","9","10","11","11.5","12","12.5","13","13.5","14","14.5","15","15.5","16","16.5","17","17.5","18","18.5","19","19.5","20","20.5","21","21.5","22","22.5","23","23.5","24","24.5","25","25.5","26","26.5","27","27.5","28","28.5","29","30","31","32","33","34","35","36","37","38","40"});
		
		String underlying = "SLW";
		String expirationCycle = "2017-01-20";
		
		//OptionBuilder optionBuilder = OptionBuilder.builder();
		
		cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).forExpirationCycle(expirationCycle).withStrikes(strikesStr).build();
		System.out.println(cut);
		
		
		String singleStrikeName = "13.5";
		
		String putType = "P";
		Map<String, Option> _135_strike = cut.getStrikes().get(singleStrikeName);
		OptionBuilder putBuilder = OptionBuilder.builder();
		Option put = putBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(putType).build();
		_135_strike.put(putType, put);
		System.out.println("\nput: " +put);
		
		
		String callType ="C";
		OptionBuilder callBuilder = OptionBuilder.builder();
		Option call = callBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(callType).build();
		_135_strike.put(callType, call);
		System.out.println("\ncall: " +call);
		
		System.out.println("\ntableau: " +cut);
	}

}
