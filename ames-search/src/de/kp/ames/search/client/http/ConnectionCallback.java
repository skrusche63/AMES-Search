package de.kp.ames.search.client.http;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

public interface ConnectionCallback {

	/**
	 * @param jValue
	 */
	public void onSuccess(String response);

	/**
	 * @param throwable
	 */
	public void onError(Throwable throwable);
	
	/**
	 * @param message
	 */
	public void onTimeout(String message);
	
	/**
	 * @param message
	 */
	public void onFailure(String message);
	
}
