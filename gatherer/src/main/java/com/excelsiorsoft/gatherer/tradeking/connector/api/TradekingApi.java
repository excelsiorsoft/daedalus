package com.excelsiorsoft.gatherer.tradeking.connector.api;

import java.io.Serializable;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class TradekingApi extends DefaultApi10a implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7378772448374411605L;

	@Override
	public String getRequestTokenEndpoint() {
		
		return "https://developers.tradeking.com/oauth/request_token";
		
	}

	@Override
	public String getAccessTokenEndpoint() {
		
		return "https://developers.tradeking.com/oauth/access_token";
		
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		
		return "https://developers.tradeking.com/oauth/authorize?oauth_token=%s";
		
	}

	

}
