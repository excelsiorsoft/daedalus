package com.excelsiorsoft.daedalus.dominion.impl;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.excelsiorsoft.daedalus.dominion.WithId;
//import com.excelsiorsoft.daedalus.dominion.TimeTrackable;
import com.excelsiorsoft.daedalus.dominion.WithTimestamp;
import com.excelsiorsoft.daedalus.dominion.impl.builder.NullSupressingStyle;



public abstract class AbstractDomain implements WithId, WithTimestamp {

	protected Long id;

	protected long timestamp;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public WithId setId(Long id) {
		this.id = id;
		return this;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public AbstractDomain setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	public String toString() {
		   return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
		 }	
	

}
