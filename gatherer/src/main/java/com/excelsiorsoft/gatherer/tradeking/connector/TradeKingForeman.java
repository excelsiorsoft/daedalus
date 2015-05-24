package com.excelsiorsoft.gatherer.tradeking.connector;

import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.OAUTH_TOKEN;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.OAUTH_TOKEN_SECRET;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.CONSUMER_KEY;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.CONSUMER_SECRET;

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

import com.excelsiorsoft.gatherer.tradeking.connector.api.TKRequest;
import com.excelsiorsoft.gatherer.tradeking.connector.api.TKResponse;
import com.excelsiorsoft.gatherer.tradeking.connector.api.TradekingApi;



/**
 * An entry point service into the TradeKing API
 * 
 * @author sleyzerzon
 *
 */
public class TradeKingForeman implements Serializable {

	private static final long serialVersionUID = -8650965609080965601L;
	
	private Token accessToken;
	private OAuthService oauthService;
	private final static Logger log = LoggerFactory.getLogger(TradeKingForeman.class);
	
	

	public TKResponse makeApiCall(final TKRequest tkRequest) throws ForemanException {
		
		if (!isConnected()) {
			connect();
		}
		
		log.trace("Sending an API Request");
		log.trace("\t ... Verb:" + tkRequest.getVerb());
		log.trace("\t ... Resource URL:" + tkRequest.getResourceURL());
		log.trace("\t ... Body:" + tkRequest.getBody());
		log.trace("\t ... Parameters:" + !tkRequest.getParameters().isEmpty());
		
		return sendOAuthRequest(makeOAuthRequest(
										tkRequest.getVerb(),
										tkRequest.getResourceURL(), 
										tkRequest.getParameters(),
										tkRequest.getBody()));
	}
	
	private TKResponse sendOAuthRequest(final Request request) {
		
		TKResponse response = new TKResponse(request);
		return response;
	}	
	

	

	private Request makeOAuthRequest(
										final Verb verb, 
										final String resourceURL, 
										final Map<String, String> parameters, 
										final String payload
									) {
		

		OAuthRequest request = new OAuthRequest(verb, resourceURL);

		for (Entry<String, String> entry : parameters.entrySet()) {
			request.addBodyParameter(entry.getKey(), entry.getValue());
		}
		
		if (payload != null) {
			request.addHeader("Content-Length", Integer.toString(payload.length()));
			request.addHeader("Content-Type", "text/xml");
			request.addPayload(payload);
		}
		
		oauthService.signRequest(accessToken, request);
		return request;
	}
	
	private void connect() throws ForemanException 	{
		
		log.trace("Connecting to Tradeking");

		oauthService = new ServiceBuilder().provider(TradekingApi.class).apiKey(CONSUMER_KEY.toString()).apiSecret(CONSUMER_SECRET.toString()).build();

		log.trace("\t ... Service built!");
		accessToken = new Token(OAUTH_TOKEN.toString(), OAUTH_TOKEN_SECRET.toString());
		log.trace("\t ... Access Token built!");
		log.trace("Connection Established");
	}	
	
	private boolean hasOAuth()	{
		return oauthService != null;
	}

	private boolean hasAccessToken() {
		return accessToken != null;
	}
	
	protected boolean isConnected() {
		return hasOAuth() && hasAccessToken();
	}

}
