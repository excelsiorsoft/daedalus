package com.excelsior.gatherer;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;

public class TKAdapterTest {
	
	
	
	public static void main(String[] args) throws Exception {

        String CONSUMER_KEY = "<CONSUMER_KEY>";
        String CONSUMER_SECRET = "<CONSUMER_SECRET>";
        String ACCESS_TOKEN = "<ACCESS_TOKEN>";
        String ACCESS_TOKEN_SECRET = "<ACCESS_TOKEN_SECRET>";
		
		
		HttpClient client = new HttpClient();
		
		Request request = client.newRequest(
				"https://stream.tradeking.com/v1/market/quotes.xml?symbols=F")
				.method(HttpMethod.GET)
				.onResponseContent((response, buffer) -> { System.out.println("onResponseContent: " + buffer); });
				
			request.send((result) -> { 
					
					System.out.println("CompleteListener...\n " );
						
						int status = result.getResponse().getStatus();
						
						if (status == 200)
		                    System.out.println("Successfully connected");
		                else
		                    System.out.println("Error Code Received: " + status);
				
				});
		
		
        
        client.start();
        
		
	}	
}
