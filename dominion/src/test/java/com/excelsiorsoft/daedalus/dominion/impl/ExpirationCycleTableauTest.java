package com.excelsiorsoft.daedalus.dominion.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
		
		cut = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).forExpirationCycle(expirationCycle).withStrikes(strikesStr).build();
		System.out.println(cut);
		
		String optionType = "P";
		
		OptionBuilder optionBuilder = OptionBuilder.builder();
		Option put = optionBuilder.withUnderlying(underlying).withExpiration(expirationCycle).ofType(optionType).build();
		cut.getStrikes().get("13.5").put(optionType, put);
		
		System.out.println(cut);
	}

}
