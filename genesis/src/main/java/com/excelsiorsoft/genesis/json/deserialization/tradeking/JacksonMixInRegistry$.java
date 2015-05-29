/**
 * 
 */
package com.excelsiorsoft.genesis.json.deserialization.tradeking;

import com.excelsiorsoft.daedalus.dominion.Option;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * @author sleyzerzon
 *
 */
@Deprecated
public class JacksonMixInRegistry$ extends SimpleModule {
	
	public JacksonMixInRegistry$(){
		addDeserializer(Option.class, new OptionDeserializer$());
	}

}
