package com.excelsiorsoft.gatherer.tradeking.connector;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Request;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * A Helper to interact with the TradeKing API
 * 
 * @author sleyzerzon
 *
 */
public class TradeKingForeman implements Serializable {

	private static final long serialVersionUID = -8650965609080965601L;
	
	private Token accessToken;
	private OAuthService srv;
	private Logger log = LoggerFactory.getLogger(TradeKingForeman.class);
	
	
	public TKResponse makeAPICall(ApiBuilder b) throws ForemanException
	{
		if (!isConnected())
		{
			connect();
		}
		log.trace("Making an API Call");
		log.trace("\t ... Verb:" + b.getVerb());
		log.trace("\t ... Resource URL:" + b.getResourceURL());
		log.trace("\t ... Body:" + b.getBody());
		log.trace("\t ... Parameters:" + !b.getParameters().isEmpty());
		return sendRequest(makeRequest(b.getVerb(), b.getResourceURL(), b.getParameters(), b.getBody()));
	}
	
	private TKResponse sendRequest(Request request)
	{
		TKResponse response = new TKResponse(request);
		return response;
	}	
	

	
	private Request makeRequest(Verb verb, String resourceURL, Map<String, String> parameters, String payload)
	{
		OAuthRequest request = new OAuthRequest(verb, resourceURL);
		for (Entry<String, String> entry : parameters.entrySet())
		{
			request.addBodyParameter(entry.getKey(), entry.getValue());
		}
		if (payload != null)
		{
			request.addHeader("Content-Length", Integer.toString(payload.length()));
			request.addHeader("Content-Type", "text/xml");
			request.addPayload(payload);
		}
		srv.signRequest(accessToken, request);
		return request;
	}
	
	private void connect() throws ForemanException
	{
		log.trace("Connecting to Tradeking");
		srv = new ServiceBuilder().provider(TradekingApi.class).apiKey(ForemanConstants.API_KEY.toString()).apiSecret(ForemanConstants.API_SECRET.toString()).build();
		log.trace("\t ... Service built!");
		accessToken = new Token(ForemanConstants.ACCESS_TOKEN.toString(), ForemanConstants.ACCESS_TOKEN_SECRET.toString());
		log.trace("\t ... Access Token built!");
		log.trace("Connection Established");
	}	
	
	private boolean hasOAuth()
	{
		return srv != null;
	}

	private boolean hasAccessToken()
	{
		return accessToken != null;
	}
	
	protected boolean isConnected()
	{
		return hasOAuth() && hasAccessToken();
	}

}
