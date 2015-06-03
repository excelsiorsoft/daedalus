/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



/**
 * @author Simeon
 * 
 */
@Deprecated
public class ExpirationCycle extends AbstractDomain {

	private Long id;
	public ExpirationCycle(){}
	
	private String name;
	private String url;
	//private Map<String, Strike> strikeSeries = new LinkedHashMap<String, Strike>();
	//@SuppressWarnings({ "unchecked", "deprecation" })
	//private Map<String, Strike> strikeSeries = new LinkedMap(new MultiHashMap());
	
	/**
	 * just to init, will be rewritten later in {@link BuildSinglePutStrikeSeries#process()}
	 */
	private Map<String, Collection<Strike>> strikeSeries = new HashMap<>();

	public String getName() {
		return name;
	}

	public ExpirationCycle setName(String name) {
		this.name = name;
		return this;
	}

	public Map<String, Collection<Strike>> getStrikeSeries() {
		return strikeSeries;
	}

	public ExpirationCycle setStrikeSeries(Map<String, Collection<Strike>> map) {
		this.strikeSeries = map;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public ExpirationCycle setUrl(String url) {
		this.url = url;
		return this;
	}

}
