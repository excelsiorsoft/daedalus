/*
 * Miserable Mind
 * http://www.miserablemind.com | Twitter: @lrimkus
 * The MIT License (MIT)
 */

package com.excelsiorsoft.api.consumer.tradeking.api.impl;


import com.excelsiorsoft.api.consumer.tradeking.api.StreamListener;
import com.excelsiorsoft.api.consumer.tradeking.api.StreamingOperations;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StreamingTemplate extends BaseTemplate implements StreamingOperations {

    public static final String URL_STREAM_QUOTES_TRADES = "https://stream.tradeking.com/v1/market/quotes.json";

    StreamingTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Stream quotesAndTradesStream(final List<StreamListener> listeners, String[] quotes) {
        String quotesString = this.buildCommaSeparatedParameterValue(quotes);
        Stream stream = new ThreadedStreamConsumer() {
            protected StreamReader getStreamReader() throws StreamCreationException {
                MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(1);
                parameters.set("symbols", String.valueOf(quotesString));
                return createStream(HttpMethod.POST, URL_STREAM_QUOTES_TRADES, parameters, listeners);
            }
        };
        stream.open();
        return stream;
    }

    private StreamReader createStream(HttpMethod method, String streamUrl, MultiValueMap<String, String> body, List<StreamListener> listeners) throws StreamCreationException {
        try {
            ClientHttpResponse response = executeRequest(method, streamUrl, body);
            if (response.getStatusCode().value() > 200) {
                throw new StreamCreationException("Unable to create stream", response.getStatusCode());
            }
            return new StreamReaderImpl(response.getBody(), listeners);
        } catch (IOException e) {
            throw new StreamCreationException("Unable to create stream.", e);
        }
    }

    private ClientHttpResponse executeRequest(HttpMethod method, String url, MultiValueMap<String, String> body) throws IOException {
        ClientHttpRequestFactory requestFactory = this.getRestTemplate().getRequestFactory();
        ClientHttpRequest request = requestFactory.createRequest(URI.create(url), method);
        OutputStreamWriter writer = new OutputStreamWriter(request.getBody());
        writer.write(createFormUrlEncodedBodyString(body));
        writer.flush();
        return request.execute();
    }

    private String createFormUrlEncodedBodyString(MultiValueMap<String, String> body) {
        StringBuilder bodyBuffer = new StringBuilder();
        for (Iterator<Map.Entry<String, List<String>>> bodyIt = body.entrySet().iterator(); bodyIt.hasNext(); ) {
            Map.Entry<String, List<String>> entry = bodyIt.next();
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (Iterator<String> valuesIt = values.iterator(); valuesIt.hasNext(); ) {
                bodyBuffer.append(key).append("=").append(formEncode(valuesIt.next()));
                if (valuesIt.hasNext()) {
                    bodyBuffer.append("&");
                }
            }
            if (bodyIt.hasNext()) {
                bodyBuffer.append("&");
            }
        }
        return bodyBuffer.toString();
    }

    private String formEncode(String data) {
        try {
            return URLEncoder.encode(data, "UTF-8");
        } catch (UnsupportedEncodingException wontHappen) {
            throw new IllegalStateException(wontHappen);
        }
    }

}
