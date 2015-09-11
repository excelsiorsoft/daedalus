package com.excelsiorsoft.daedalus.util.time;

import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.fromUnixTimestampToLocalDateTime;
import static com.excelsiorsoft.daedalus.util.time.DateTimeUtils.nowFromEpoch;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DateTimeUtilsTest {

	
	private final static Logger logger = LoggerFactory.getLogger(DateTimeUtilsTest.class);
	
	@Test
	public void test() {
		logger.debug(
				"\n\ttimestamp: {}, "
				+ "\n\ttimestamp as instant: {}, "
				+ "\n\ttimestamp @ local zone: {}, "
				+ "\n\tnow as instant: {}, "
				+ "\n\tnow @ local zone: {}",
				1432785600L,
				Instant.ofEpochSecond(1432785600L),
				fromUnixTimestampToLocalDateTime(1432785600L),
				nowFromEpoch(),
				fromUnixTimestampToLocalDateTime(Instant.now()
						.getEpochSecond()));
	}

}
