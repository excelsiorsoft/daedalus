package com.excelsiorsoft.daedalus.dominion;

public interface WithTimestamp {
	
	final String TIMESTAMP = "timestamp";

	long getTimestamp();

	WithTimestamp setTimestamp(long timestamp);
	
}
