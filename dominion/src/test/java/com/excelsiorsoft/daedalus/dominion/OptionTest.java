/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;


import static org.junit.Assert.*;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;
import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.dominion.impl.Exchange.ExchangeBuilder;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;

/**
 * @author sleyzerzon
 *
 */
public class OptionTest {

	@Test
	public void createAMinimalOptionContract() throws Throwable {
		
		Option option = OptionBuilder.builder().withUnderlying("SLW").ofType("C").withExpiration("2017-01-20").withStrike(18.00).build();
		System.out.println(option);
		assertNotNull("expect a non-null option",option);
		
	}
	
	@Test
	public void createAnOptionContractTradeableOn() throws Throwable {
		
		Exchange exchange = ExchangeBuilder.builder().withCode("exch").withDescription("exch_desc").build();
		
		Option option = OptionBuilder.builder().withUnderlying("SLW").ofType("C").withExpiration("2017-01-20").withStrike(18.00)
				.tradeableOn(exchange)
				.build();
		System.out.println(option);
		assertNotNull("expect a non-null option",option);
		
	}	
	
	@Test
	public void createAnOptionContractWithBidAskTimings() throws Throwable {
		
		Option option = OptionBuilder.builder().withUnderlying("SLW").ofType("C").withExpiration("2017-01-20").withStrike(18.00).build();
		System.out.println(option);
		assertNotNull("expect a non-null option",option);
		
	}

}
