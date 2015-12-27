package com.excelsiorsoft.daedalus.dominion.builder;

import static org.junit.Assert.*;

import org.junit.Test;




import com.excelsiorsoft.daedalus.dominion.impl.Quote;
import com.excelsiorsoft.daedalus.dominion.impl.builder.QuoteBuilder;

public class QuoteBuilderTest {

	@Test
	public void test() {
		String ticker = "SLW";
		Quote quote = QuoteBuilder.withTicker(ticker).build();
		assertNotNull("expect an instance of quote", quote);
		System.out.println("result: "+quote);
	}

}
