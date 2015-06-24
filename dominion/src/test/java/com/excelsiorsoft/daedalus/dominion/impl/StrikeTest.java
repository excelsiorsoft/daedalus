package com.excelsiorsoft.daedalus.dominion.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.excelsiorsoft.daedalus.dominion.impl.Strike.StrikeBuilder;

public class StrikeTest {

	@Test
	public void creatingWithBuilder() {
		Strike strike = StrikeBuilder.builder().forSymbol("SLW").withValue("28.0").forExpirationCycle("2015-06-19").withBid("3.3500").withBidSize("70").withBidTime("15:54").withAsk("3.4").withAskSize("80").withAskTime("15:53").build();
		System.out.println(strike);
	}

}
