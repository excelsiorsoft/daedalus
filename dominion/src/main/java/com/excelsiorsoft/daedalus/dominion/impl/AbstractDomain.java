package com.excelsiorsoft.daedalus.dominion.impl;

import java.sql.Timestamp;

import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.excelsiorsoft.daedalus.dominion.Identifiable;
import com.excelsiorsoft.daedalus.dominion.IdentifiableTimeTrackable;
import com.excelsiorsoft.daedalus.dominion.TimeTrackable;
import com.excelsiorsoft.daedalus.dominion.impl.builder.NullSupressingStyle;



public class AbstractDomain implements IdentifiableTimeTrackable {

	protected Long id;

	protected long timestamp;

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
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public TimeTrackable setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	public String toString() {
		   return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
		 }	
	

}
