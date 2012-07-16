package de.kp.ames.search.client.service;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;

import de.kp.ames.search.client.http.ConnectionCallback;
import de.kp.ames.search.client.method.RequestMethodImpl;

public interface Service {

	/**
	 * Unique Service Identifier
	 * @param sid
	 */
	public void setId(String sid);
	
	/**
	 * Base Url of associated web service
	 * @param base
	 */
	public void setBase(String base);

	/**
	 * Headers of the associated HTTP request
	 * @return
	 */
	public HashMap<String,String> getHeaders();

	/**
	 * Request error handling
	 * 
	 * @param message
	 */
	public void doRequestError(String message);

	/**
	 * Send GET request to web service
	 * @param method
	 */
	public void sendGetRequest(RequestMethodImpl method, ConnectionCallback callback);
	
}
