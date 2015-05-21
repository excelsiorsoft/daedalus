package com.excelsiorsoft.daedalus.dominion;

import java.sql.Timestamp;


public interface TimeTrackable {

	Timestamp getTimestamp();

	TimeTrackable setTimestamp(Timestamp timestamp);
}
