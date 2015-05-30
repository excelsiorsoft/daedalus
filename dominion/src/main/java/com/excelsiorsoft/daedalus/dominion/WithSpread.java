/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion;

//import java.math.BigDecimal;

/**
 * @author Simeon
 *
 */
public interface WithSpread {
	
	//public static final String ZERO = "0";
	//public static final double ZERO = 0.0;

	/*BigDecimal*/ double getBid() ;
	
	WithSpread setBid(/*BigDecimal*/double  bid);
	
	WithSpread setBid(String bid);

	/*BigDecimal*/double getAsk();

	WithSpread setAsk(/*BigDecimal*/double ask);
	
	WithSpread setAsk(String ask);

}
