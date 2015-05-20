package com.excelsiorsoft.gatherer.tradeking.connector.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.scribe.model.Verb;

/**
 * The abstract backend to all APIBuilders
 * 
 * @author sleyzerzon
 *
 */
public abstract class ApiBuilder implements Serializable {
	
	private static final long serialVersionUID = 5364934694744835663L;
	
	protected Verb verb;
	protected Map<String, String> params = new HashMap<>();
	protected String resourceURL;
	protected String body;
	protected boolean streaming;

	public Verb getVerb() {
		return verb;
	}

	public Map<String, String> getParameters() {
		return params;
	}

	public String getResourceURL() {
		return resourceURL;
	}
	
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public String getBody() {
		return body;
	}

	public boolean isStreaming() {
		return streaming;
	}

}
