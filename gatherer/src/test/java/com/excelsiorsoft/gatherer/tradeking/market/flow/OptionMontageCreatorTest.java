package com.excelsiorsoft.gatherer.tradeking.market.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage;

public class OptionMontageCreatorTest {

	
	private List<String> symbols = Arrays.asList(new String[]{"SLW", "NLY"});
	
	@Test
	public void testMontageCreation() throws Throwable {
		
		List<OptionMontage> results = new ArrayList<>();
		
		for(String underlying:symbols) {
			results.add(new OptionMontageCreator().create(underlying));
		}
		
		assertTrue("", results.size()==symbols.size());
	}

}
