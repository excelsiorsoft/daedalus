package com.excelsiorsoft.daedalus.dominion;

import java.util.LinkedHashMap;
import java.util.Map;



public class Quote {

	private Underlying underlying;

	private final Map<String /*name TODO: or id?*/, ExpirationCycle /*respective cycle*/ > expirationCycles = new LinkedHashMap<>();
	
}
