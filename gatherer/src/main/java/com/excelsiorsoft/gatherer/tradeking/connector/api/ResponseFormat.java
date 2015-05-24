package com.excelsiorsoft.gatherer.tradeking.connector.api;

import java.io.Serializable;

/**
 * TradeKing support XML and JSON responses
 * 
 * @author sleyzerzon
 *
 */
public enum ResponseFormat implements Serializable
{
	xml, json, jsonp;

	@Override
	public String toString()
	{
		return name().toLowerCase();
	}
}
