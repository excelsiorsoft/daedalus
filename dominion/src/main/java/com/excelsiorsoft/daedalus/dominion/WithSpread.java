/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

import java.util.Date;

import com.excelsiorsoft.daedalus.dominion.impl.Exchange;

//import java.math.BigDecimal;

/**
 * @author Simeon
 *
 */
public interface WithSpread {
	
	WithSpread setAsQuotedOn(Exchange exchange);
	
	Exchange getAsQuotedOn();
	
	//public static final String ZERO = "0";
	//public static final double ZERO = 0.0;

	/*BigDecimal*/ double getBid() ;
	
	WithSpread setBid(/*BigDecimal*/double  bid);
	
	WithSpread setBid(String bid);

	/*BigDecimal*/double getAsk();

	WithSpread setAsk(/*BigDecimal*/double ask);
	
	WithSpread setAsk(String ask);
	
	WithSpread setAskTime(String askTime);
	
	Date getAskTime();
	
	WithSpread setAskSize(String askSize);
	
	int getAskSize();
	
	WithSpread setBidTime(String bidTime);
	
	Date getBidTime();
	
	WithSpread setBidSize(String bidSize);
	
	int getBidSize();

}
