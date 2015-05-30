/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Option;
import com.excelsiorsoft.daedalus.dominion.impl.Option.OptionBuilder;

/**
 * @author sleyzerzon
 *
 */
public class OptionTest {

	@Test
	public void createAnOptionContract() throws Throwable {
		
		Option option = OptionBuilder.builder().withUnderlying("SLW").ofType("C").withExpiration("2017-01-20").withStrike(18.00).build();
		System.out.println(option);
		assertNotNull("expect a non-null option",option);
		
	}

}
