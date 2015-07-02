package com.excelsiorsoft.daedalus.dominion.impl;

import static org.junit.Assert.*;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage.OptionMontageBuilder;


public class OptionMontageTest {

	private final static Logger logger = LoggerFactory.getLogger(OptionMontageTest.class);
	
	@Test
	public void toStringTest() {
		
		long now = nowFromEpoch();
		String underlying = "SLW";
		
		OptionMontageBuilder builder = OptionMontageBuilder.builder();
		OptionMontage montage = builder.asOf(now).forSymbol(underlying).build();
		logger.debug("Resulting montage: {}", montage);
	}

}
