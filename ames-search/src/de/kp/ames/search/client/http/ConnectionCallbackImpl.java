package de.kp.ames.search.client.http;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.http.client.RequestException;


/**
 * @author Stefan Krusche (krusche@dr-kruscheundpartner.de)
 *
 */
public class ConnectionCallbackImpl implements ConnectionCallback {

	@Override
	public void onSuccess(String response) {
		// put your code in here
	}

	@Override
	public void onError(Throwable throwable) {
		
		if (throwable instanceof NullPointerException) {
			
			// the HTTP request request returned a response text NULL
			
		} else if (throwable instanceof RequestException) {
			
		}
		
	}

	@Override
	public void onTimeout(String message) {
		// put your code in here
	}

	@Override
	public void onFailure(String message) {
		// put your code in here
	}
	
}
