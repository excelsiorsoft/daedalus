/**
 * 
 */
package com.excelsiorsoft.daedalus.util.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author sleyzerzon
 *
 */
public final class DateTimeUtils {
	
	public static String fromUnixTimestampToLocalDateTime(long unixTimestamp){
		
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return Instant.ofEpochSecond(unixTimestamp).atZone(ZoneId.systemDefault()).format(formatter);
		
	}
	
	/**
	 * Should be equivalent to 'date +%s' on Unix
	 * 
	 * @return
	 */
	public static long nowFromEpoch(){
		return Instant.now().getEpochSecond();
	}

}
