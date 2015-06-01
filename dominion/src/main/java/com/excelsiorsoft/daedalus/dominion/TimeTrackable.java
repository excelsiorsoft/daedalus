package com.excelsiorsoft.daedalus.dominion;

import java.sql.Timestamp;


public interface TimeTrackable {

	long getTimestamp();

	TimeTrackable setTimestamp(long timestamp);
}
