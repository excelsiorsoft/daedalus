package com.excelsiorsoft.gatherer.tradeking.market.flow;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.dominion.impl.OptionMontage;
import com.excelsiorsoft.gatherer.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
public class OptionMontageCreatorTest {

	
	@Autowired
	private OptionMontageCreator cut;
	
	private List<String> symbols = Arrays.asList(new String[]{"SLW", "NLY"});
	
	@Test
	public void testMontageCreation() throws Throwable {
		
		List<OptionMontage> results = new ArrayList<>();
		
		for(String underlying:symbols) {
			results.add(cut.create(underlying));
		}
		
		assertTrue("sizes don't match", results.size()==symbols.size());
	}

}
