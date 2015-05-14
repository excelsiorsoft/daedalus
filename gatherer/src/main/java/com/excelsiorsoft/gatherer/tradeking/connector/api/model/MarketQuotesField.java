package com.excelsiorsoft.gatherer.tradeking.connector.api.model;

import java.io.Serializable;

public enum MarketQuotesField implements Serializable
{
	SYMBOLS("symbols"), FIDS("fids");
	
	private String tag;
	MarketQuotesField(String tag)
	{
		this.tag = tag;
	}
	
	@Override
	public String toString()
	{
		return tag;
	}
}
