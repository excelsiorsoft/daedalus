package com.excelsiorsoft.gatherer.tradeking.connector;

import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.OAUTH_TOKEN;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.OAUTH_TOKEN_SECRET;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.CONSUMER_KEY;
import static com.excelsiorsoft.gatherer.tradeking.connector.ForemanConstants.CONSUMER_SECRET;

import java.io.Serializable;
import java.util.Map;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Request;
import org.scribe.model.Response;
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
	private final static Logger logger = LoggerFactory.getLogger(TradeKingForeman.class);
	
	

	public TKResponse makeApiCall(final TKRequest tkRequest) throws ForemanException {
		
		if (!isConnected()) {
			prepareOAuthConnection();
		}
	
		return sendOAuthRequest(makeOAuthRequest(tkRequest));
	}
	
	private TKResponse sendOAuthRequest(final Request request) {
		

		logger.info("OAuth request {} is ready to be sent out to TK", request);
		logger.info("OAuth parameters: "+((OAuthRequest)request).getOauthParameters());
		logger.info("oauth_timestamp={}", ((OAuthRequest)request).getOauthParameters().get("oauth_timestamp"));

		Response oAuthResponse = request.send();
		logger.info("OAuth request was sent, received a response: {}", oAuthResponse);

		
		TKResponse response = new TKResponse(oAuthResponse);
		return response;
	}	
	

	

	private Request makeOAuthRequest(final TKRequest tkRequest) {

		final Verb verb = tkRequest.getVerb();
		final String resourceURL = tkRequest.getResourceURL(); 
		
		//parameters and payload get in the way - for now not using them
		final Map<String, Object> parameters = tkRequest.getParameters();
		final String payload = tkRequest.getBody();
		
		logger.info("Creating an OAuth request wrapper around {}", tkRequest);
		
		
		OAuthRequest request = new OAuthRequest(verb, resourceURL);
		
		
		/*
		 * TODO: Using params on the scribe request results in an authentication error, use templating solution until fully understood why
		 * 
		for (Entry<String, String> entry : parameters.entrySet()) {
			log.info("adding request body param: {}={}", entry.getKey(), entry.getValue() );
			request.addBodyParameter(entry.getKey(), entry.getValue());
		}
			
		if (payload != null) {
			request.addHeader("Content-Length", Integer.toString(payload.length()));
			request.addHeader("Content-Type", "text/xml");
			request.addPayload(payload);
		}*/
		
		logger.info("OAuth request wrapper created: {}", request);
		oauthService.signRequest(accessToken, request);
		logger.info("OAuth request wrapper has been singed.");
		return request;
	}
	
	private void prepareOAuthConnection() throws ForemanException 	{
		
		logger.info("Connecting to Tradeking");

		oauthService = new ServiceBuilder().provider(TradekingApi.class).apiKey(CONSUMER_KEY.toString()).apiSecret(CONSUMER_SECRET.toString()).build();

		logger.info("\t ... OAuth Service built!");
		
		accessToken = new Token(OAUTH_TOKEN.toString(), OAUTH_TOKEN_SECRET.toString());
		
		logger.info("\t ... Access Token built!");
		logger.info("Safe to connect");
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
