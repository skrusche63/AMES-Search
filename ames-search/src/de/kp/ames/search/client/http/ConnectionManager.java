package de.kp.ames.search.client.http;
/**
 *	Copyright 2012 Dr. Krusche & Partner PartG
 *
 *	AMES-Web-GUI is free software: you can redistribute it and/or 
 *	modify it under the terms of the GNU General Public License 
 *	as published by the Free Software Foundation, either version 3 of 
 *	the License, or (at your option) any later version.
 *
 *	AMES- Web-GUI is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 *  See the GNU General Public License for more details. 
 *
 *	You should have received a copy of the GNU General Public License
 *	along with this software. If not, see <http://www.gnu.org/licenses/>.
 *
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
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.globals.CoreGlobals;
import de.kp.ames.search.client.method.RequestMethodImpl;

/**
 * @author Stefan Krusche (krusche@dr-kruscheundpartner.de)
 *
 */
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
		builder.setTimeoutMillis(CoreGlobals.CONNECTION_TIMEOUT);
		
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

	        	SC.logWarn("======> cm.RequestCallback.onResponseReceived");

				if (STATUS_CODE_OK == response.getStatusCode()) {						
					handleSuccess(response, callback);
				
				} else {						
					handleFailure(response, callback);
				}

	        }

	        public void onError(Request request, Throwable exception) {

	        	SC.logWarn("======> cm.RequestCallback.onError");
				
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
	 * @param baseUrl
	 * @param method
	 * @param requestData
	 * @param callback
	 */
	public void sendPostRequest(final String baseUrl, final RequestMethodImpl method, final HashMap<String, String>headers, final String requestData, final ConnectionCallback callback) {
		String url = baseUrl + method.toQuery();
		sendPostRequest(url, headers, requestData, callback);
	}
		
	/**
	 * @param url
	 * @param requestData
	 * @param callback
	 */
	public void sendPostRequest(final String url, final HashMap<String, String>headers, final String requestData, final ConnectionCallback callback) {
	    
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		builder.setTimeoutMillis(CoreGlobals.CONNECTION_TIMEOUT);
				
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
		 * Set request data
		 */
		if (requestData != null) builder.setRequestData(requestData);
		
    	/*
    	 * Set request callback
    	 */
    	builder.setCallback(new RequestCallback() {

	        public void onResponseReceived(Request request, Response response) {

				if (STATUS_CODE_OK == response.getStatusCode()) {

					SC.logWarn("====> cm.onResponseReceived");
					
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


