package com.excelsiorsoft.daedalus.dominion.impl.builder;

import org.springframework.util.Assert;

import com.excelsiorsoft.daedalus.dominion.impl.AbstractTradeableInstrument;
import com.excelsiorsoft.daedalus.dominion.impl.Instrument;
import com.excelsiorsoft.daedalus.dominion.impl.Quote;
import com.excelsiorsoft.daedalus.dominion.impl.AbstractTradeableInstrument.InstrumentType;


public final class QuoteBuilder<T> {
	
	private final Instrument underlying;

	
	/**
	 * Private constructor to be invoked from the static factory methods only.
	 */
	private QuoteBuilder(Instrument underlying) {
		Assert.notNull(underlying, "underlying must not be null");
		this.underlying = underlying;


	}
	
	public static <T> QuoteBuilder<T> withUnderlying(Instrument underlying) {
		Assert.notNull(underlying, "underlying must be present");
		Assert.notNull(underlying.getSymbol(), "ticker must be present");
		QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
		return builder;
	}
	
	public static <T> QuoteBuilder<T> withTicker(String ticker) {
		Assert.notNull(ticker, "ticker must be present");
		Instrument underlying  = new Instrument().setType(AbstractTradeableInstrument.InstrumentType.STOCK).setSymbol(ticker);
		QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
		return builder;
	}
	
	public static <T> QuoteBuilder<T> withInstrument(String symbol, AbstractTradeableInstrument.InstrumentType typeOfUnderlying) {
		Assert.notNull(symbol, "symbol must be present");
		Assert.notNull(typeOfUnderlying, "type of Instrument instrument must be present");
		Instrument underlying  = new Instrument().setType(typeOfUnderlying).setSymbol(symbol);
		QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
		return builder;
	}
	
	public Quote build() {
		
		return new Quote(underlying);
	}
}