package de.kp.ames.search.client.http;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.service.Service;

public class GetJsonCallbackImpl extends GetCallbackImpl {
	
	/**
	 * Constructor
	 * 
	 * @param activity
	 * @param service
	 */
	public GetJsonCallbackImpl(Activity activity, Service service) {
		super(activity, service);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onSuccess(java.lang.String)
	 */
	public void onSuccess(String response) {

		try {
			/*
			 * JSON response
			 */
			JSONValue jValue = JSONParser.parseStrict(response);
			this.activity.execute(jValue);
			
		} catch (NullPointerException e) {
			doGetFailure();
			
		}

	}

}
