package com.excelsiorsoft.daedalus.dominion;

import java.sql.Timestamp;

import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.excelsiorsoft.daedalus.dominion.builder.NullSupressingStyle;



public class AbstractDomain implements IdentifiableTimeTrackable {

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
	
	public String toString() {
		   return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
		 }	
	

}
