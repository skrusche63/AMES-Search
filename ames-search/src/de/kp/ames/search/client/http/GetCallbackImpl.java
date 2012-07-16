package de.kp.ames.search.client.http;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.service.Service;

/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

public class GetCallbackImpl implements ConnectionCallback {

	/*
	 * Reference to After Request Activity
	 */
	protected Activity activity;
	
	/*
	 * Reference to Service
	 */
	protected Service service;
	
	/**
	 * Constructor
	 * 
	 * @param activity
	 * @param service
	 */
	public GetCallbackImpl(Activity activity, Service service) {
		this.activity = activity;
		this.service  = service;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onSuccess(java.lang.String)
	 */
	public void onSuccess(String response) {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onError(java.lang.Throwable)
	 */
	public void onError(Throwable throwable) {
		doGetFailure();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onTimeout(java.lang.String)
	 */
	public void onTimeout(String message) {
		doGetFailure();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onFailure(java.lang.String)
	 */
	public void onFailure(String message) {
		doGetFailure();
	}
	
	/**
	 * Get request failure
	 */
	protected void doGetFailure() {
	
		String message = "Get request failed due to server error.";
		service.doRequestError(message);		
	
	}


}
