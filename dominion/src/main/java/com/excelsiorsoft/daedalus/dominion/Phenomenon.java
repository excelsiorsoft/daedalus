package com.excelsiorsoft.daedalus.dominion;

/**
 * @author Simeon
 *
 */
public interface Phenomenon {

	String getManifestation();

	String getName();

	Phenomenon measuredBy();

}
