package com.excelsiorsoft.daedalus.dominion;

import java.sql.Timestamp;

public class AbstractDomain implements Reportable {

	protected Long id;

	protected Timestamp timestamp;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Identifiable setId(Long id) {
		this.id = id;
		return this;
	}

	@Override
	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public TimeTrackable setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}

}
