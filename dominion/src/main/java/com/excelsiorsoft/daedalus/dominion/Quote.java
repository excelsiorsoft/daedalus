package com.excelsiorsoft.daedalus.dominion;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.Assert;


public class Quote extends AbstractDomain {

	private Instrument underlying;

	private final Map<String /*name TODO: or id?*/, ExpirationCycle /*respective cycle*/ > expirationCycles = new LinkedHashMap<>();
	
	private Quote(){}
	
	public Quote(Instrument underlying){
		this.underlying = underlying;
		this.setTimestamp(new Timestamp(new Date().getTime()));
	}

	public Instrument getUnderlying() {
		return underlying;
	}

	public Map<String, ExpirationCycle> getExpirationCycles() {
		return expirationCycles;
	}

	public final static class QuoteBuilder<T> {
		
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
			Instrument underlying  = new Instrument().setType(Instrument.InstrumentType.STOCK).setSymbol(ticker);
			QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
			return builder;
		}
		
		public static <T> QuoteBuilder<T> withInstrument(String symbol, Instrument.InstrumentType typeOfUnderlying) {
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
}
