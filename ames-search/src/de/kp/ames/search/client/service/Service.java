package de.kp.ames.search.client.service;

import java.util.HashMap;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.http.ConnectionCallback;
import de.kp.ames.search.client.method.RequestMethodImpl;

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



/**
 * @author Stefan Krusche (krusche@dr-kruscheundpartner.de)
 *
 */

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
	 * APPLY request
	 * 
	 * @param attributes
	 * @param activity
	 */
	public void doApply(HashMap<String,String> attributes, Activity activity);

	/**
	 * APPLY request
	 * 
	 * @param attributes
	 * @param data
	 * @param activity
	 */
	public void doApply(HashMap<String,String> attributes, String data, Activity activity);

	/**
	 * EXTRACT request
	 * 
	 * @param attributes
	 * @param activity
	 */
	public void doExtract(HashMap<String,String> attributes, Activity activity);

	/**
	 * A JSON based non-widget GET request
	 * 
	 * @param attributes
	 * @param activity
	 */
	public void doGetJson(HashMap<String,String> attributes, Activity activity);

	/**
	 * DELETE request
	 * 
	 * @param attributes
	 * @param data
	 * @param activity
	 */
	public void doDelete(HashMap<String,String> attributes, Activity activity);

	/**
	 * DELETE request
	 * 
	 * @param attributes
	 * @param data
	 * @param activity
	 */
	public void doDelete(HashMap<String,String> attributes, String data, Activity activity);

	/**
	 * Submit request
	 * 
	 * @param attributes
	 * @param data
	 * @param activity
	 */
	public void doSubmit(HashMap<String,String> attributes, String data, Activity activity);

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

	/**
	 * Send POST request to web service
	 * @param method
	 * @param data
	 */
	public void sendPostRequest(RequestMethodImpl method, String data, ConnectionCallback callback);
	
}
