package de.kp.ames.search.client.service;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;

import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.http.ConnectionCallback;
import de.kp.ames.search.client.http.ConnectionManager;
import de.kp.ames.search.client.http.GetJsonCallbackImpl;
import de.kp.ames.search.client.method.RequestMethodImpl;

public class ServiceImpl implements Service {

	/*
	 * The base url necessary to invoke the
	 * web service that refers to this service
	 */
	
	protected String base;
	
	/*
	 * The unique service identifier
	 */
	protected String sid;
	
	/*
	 * Reference to Connection Manager
	 */
	
	protected static ConnectionManager cm = ConnectionManager.getInstance();
	
	/**
	 * Constructor
	 */
	public ServiceImpl() {		
	}

	/**
	 * @param base
	 * @param sid
	 */
	public ServiceImpl(String base, String sid) {
		this.base = base;
		this.sid  = sid;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.service.Service#setId(java.lang.String)
	 */
	public void setId(String sid) {
		this.sid = sid;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.service.Service#setBase(java.lang.String)
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.service.Service#doGetJson(java.util.HashMap, de.kp.ames.web.client.core.activity.Activity)
	 */
	public void doSuggest(HashMap<String,String> attributes, Activity activity) {
	
		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SUGGEST);

		requestMethod.setAttributes(attributes);
		
		GetJsonCallbackImpl callback = new GetJsonCallbackImpl(activity, this);
		sendGetRequest(requestMethod, callback);
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.service.Service#sendGetRequest(de.kp.ames.web.client.core.method.RequestMethodImpl, de.kp.ames.web.client.core.callback.Callback)
	 */
	public void sendGetRequest(RequestMethodImpl method, ConnectionCallback callback) {
		
		String requestUrl = getRequestUrl();
		HashMap<String,String> requestHeaders = getHeaders();
		
		cm.sendGetRequest(requestUrl, method, requestHeaders, callback);
	
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.service.Service#getHeaders()
	 */
	public HashMap<String,String> getHeaders() {
		
		HashMap<String,String> headers = new HashMap<String,String>();
		return headers;
	}

	/**
	 * @param attributes
	 * @return
	 */
	public String getUri(HashMap<String,String> attributes) {

		/*
		 * Build method
		 */
		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SUGGEST);

		requestMethod.setAttributes(attributes);
		
		/*
		 * Build request uri
		 */
		return getRequestUrl() + requestMethod.toQuery();
		
	}
	
	/**
	 * @return
	 */
	protected String getRequestUrl() {
		
		if ((this.sid == null) || (this.base == null)) return null;
		return this.base + "/" + this.sid;
		
	}

	/**
	 * Message box to show the request error failure
	 * 
	 * @param message
	 */
	public void doRequestError(String message) {
		SC.say("Request Error", message);		
	}

}
