package de.kp.ames.search.client.http;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;
import java.util.Set;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.RequestTimeoutException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.method.RequestMethodImpl;

public class ConnectionManager {

	private static final int STATUS_CODE_OK = 200;
	
	private static ConnectionManager instance = new ConnectionManager();
	
	public static ConnectionManager getInstance() {
		if(instance==null) instance = new ConnectionManager();
		return instance;
	}
	
	/**
	 * @param baseUrl
	 * @param method
	 * @param callback
	 */
	public void sendGetRequest(final String baseUrl, final RequestMethodImpl method, final HashMap<String, String>headers, final ConnectionCallback callback) {
		String url = baseUrl + method.toQuery();
		sendGetRequest(url, headers, callback);
	}
	
	/**
	 * @param url
	 * @param callback
	 */
	public void sendGetRequest(final String url, final HashMap<String, String>headers, final ConnectionCallback callback) {
	    		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		builder.setTimeoutMillis(GuiGlobals.CONNECTION_TIMEOUT);
		
		/*
		 * Set header parameters
		 */
		if (headers.isEmpty() == false) {
			Set<String> keys = headers.keySet();
			for (String key:keys) {
				builder.setHeader(key, headers.get(key));				
			}
		}

		/*
    	 * Set request callback
    	 */
    	builder.setCallback(new RequestCallback() {

	        public void onResponseReceived(Request request, Response response) {

				if (STATUS_CODE_OK == response.getStatusCode()) {						
					handleSuccess(response, callback);
				
				} else {						
					handleFailure(response, callback);
				}

	        }

	        public void onError(Request request, Throwable exception) {
				
				if (exception instanceof RequestTimeoutException) {						
					handleTimeout(exception, callback);
				
				} else {						
					handleError(exception, callback);
			    }

	        }
    	
    	});

	    try {
	    	builder.send();
	    	
	    } catch (RequestException e) {
	      handleError(e, callback);
	    	
	    }
	    
	}

	/**
	 * @param response
	 * @param callback
	 */
	private void handleSuccess(Response response, ConnectionCallback callback) {

		String responseText = response.getText();
		if (callback != null) callback.onSuccess(responseText);
		
	}
	
	/**
	 * @param response
	 * @param callback
	 */
	private void handleFailure(Response response, ConnectionCallback callback) {

		String responseText = response.getText();
		if (callback != null) callback.onFailure(responseText);

	}
	
	/**
	 * @param throwable
	 * @param callback
	 */
	private void handleError(Throwable throwable, ConnectionCallback callback) {
		if (callback != null) callback.onError(throwable);
	}

	/**
	 * @param exception
	 * @param callback
	 */
	private void handleError(RequestException exception, ConnectionCallback callback) {
		if (callback != null) callback.onError(exception);
	}

	/**
	 * @param throwable
	 * @param callback
	 */
	private void handleTimeout(Throwable throwable, ConnectionCallback callback) {

		RequestTimeoutException exception = (RequestTimeoutException)throwable;
		if (callback != null) callback.onTimeout(exception.getMessage());
	
	}
  
}


