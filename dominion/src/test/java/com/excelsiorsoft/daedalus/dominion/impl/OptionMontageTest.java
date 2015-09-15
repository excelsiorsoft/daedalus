package com.excelsiorsoft.daedalus.dominion.impl;


import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;
import static java.util.Arrays.asList;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excelsiorsoft.daedalus.dominion.impl.ExpirationCycleTableau.ExpirationCycleTableauBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage.OptionMontageBuilder;
import com.excelsiorsoft.daedalus.util.Significant;

@Significant
public class OptionMontageTest {

	private final static Logger logger = LoggerFactory.getLogger(OptionMontageTest.class);
	
	
	
	

	

	
	/**
	 * Raw & unfinished, see {@link CandidatesSearchFlowTest#buildingOptionMontage} in gatherer instead 
	 */
	@Test
	@Deprecated
	public void toStringTest() {
		
		long now = nowFromEpoch();
		String underlying = "SLW";
		
		List<String> expirationsStr = asList(new String[]{"2015-09-11","2015-09-18","2015-09-25","2015-10-02","2015-10-09","2015-10-16","2015-10-23","2015-10-30","2015-11-20","2015-12-18","2016-01-15","2016-03-18","2017-01-20"});
		List<String> strikesStr =  asList(new String[]{"3","5","8","9","10","11","11.5","12","12.5","13","13.5","14","14.5","15","15.5","16","16.5","17","17.5","18","18.5","19","19.5","20","20.5","21","21.5","22","22.5","23","23.5","24","24.5","25","25.5","26","26.5","27","27.5","28","28.5","29","30","31","32","33","34","35","36","37","38","40"});
		
		
		String _2017_01_20_expirationCycle = "2017-01-20";
		
		ExpirationCycleTableau tableau = ExpirationCycleTableauBuilder.builder().forSymbol(underlying).asOf(now).forExpirationCycle(_2017_01_20_expirationCycle).withStrikes(strikesStr).build();
		System.out.println("Empty tableau: " + tableau);
		
		OptionMontageBuilder builder = OptionMontageBuilder.builder();
		OptionMontage montage = builder.asOf(now).forSymbol(underlying).build();
		logger.debug("Resulting montage: {}", montage);
	}

}
