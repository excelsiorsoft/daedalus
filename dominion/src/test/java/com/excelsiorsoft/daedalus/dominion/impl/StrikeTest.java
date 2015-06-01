package com.excelsiorsoft.daedalus.dominion.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;

public class StrikeTest {

	@Test
	public void creatingWithBuilder() {
		Strike strike = StrikeBuilder.builder().withBid("3.3500").withBidSize("70").withBidTime("15:54").build();
		System.out.println(strike);
	}

}
