package de.kp.ames.search.client.handler;

import de.kp.ames.search.client.activity.Activity;


public interface FormHandler {

	/**
	 * @param activity
	 */
	public void addSendActivity(Activity activity);
	
	/**
	 * Send form to server
	 */
	public void doSend();
	
}
