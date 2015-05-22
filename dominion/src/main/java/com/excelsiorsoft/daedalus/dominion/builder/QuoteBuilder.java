package com.excelsiorsoft.daedalus.dominion.builder;

import org.springframework.util.Assert;

import com.excelsiorsoft.daedalus.dominion.InstrumentType;
import com.excelsiorsoft.daedalus.dominion.Quote;
import com.excelsiorsoft.daedalus.dominion.Underlying;


public final class QuoteBuilder<T> {
	
	private final Underlying underlying;

	
	/**
	 * Private constructor to be invoked from the static factory methods only.
	 */
	private QuoteBuilder(Underlying underlying) {
		Assert.notNull(underlying, "underlying must not be null");
		this.underlying = underlying;


	}
	
	public static <T> QuoteBuilder<T> withUnderlying(Underlying underlying) {
		Assert.notNull(underlying, "underlying must be present");
		Assert.notNull(underlying.getSymbol(), "ticker must be present");
		QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
		return builder;
	}
	
	public static <T> QuoteBuilder<T> withTicker(String ticker) {
		Assert.notNull(ticker, "ticker must be present");
		Underlying underlying  = new Underlying().setType(InstrumentType.STOCK).setSymbol(ticker);
		QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
		return builder;
	}
	
	public static <T> QuoteBuilder<T> withInstrument(String symbol, InstrumentType typeOfUnderlying) {
		Assert.notNull(symbol, "symbol must be present");
		Assert.notNull(typeOfUnderlying, "type of Underlying instrument must be present");
		Underlying underlying  = new Underlying().setType(typeOfUnderlying).setSymbol(symbol);
		QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
		return builder;
	}
	
	public Quote build() {
		
		return new Quote(underlying);
	}
}