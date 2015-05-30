/**
 * 
 */
package com.excelsiorsoft.daedalus.dominion.impl;

import java.io.Serializable;

/**
 * Representation of financial exchange
 * 
 * @author Simeon
 *
 */
public class Exchange implements Serializable {
	

	private static final long serialVersionUID = -341132477094925756L;

	private String code;
	
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Exchange[code=").append(code).append(":").append(", description=").append(description).append("]");
		return builder.toString();
	}	

}
