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

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.service.Service;
import de.kp.ames.search.client.widget.base.ActionIndicator;

public class ExtractCallbackImpl implements ConnectionCallback {

	/*
	 * Reference to After Request Activity
	 */
	private Activity activity;
	
	/*
	 * Reference to Service
	 */
	private Service service;
	
	/**
	 * Constructor
	 * 
	 * @param activity
	 * @param service
	 */
	public ExtractCallbackImpl(Activity activity, Service service) {
		this.activity = activity;
		this.service  = service;
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
			doExtractFailure();
			
		}

	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onError(java.lang.Throwable)
	 */
	public void onError(Throwable throwable) {
		doExtractFailure();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onTimeout(java.lang.String)
	 */
	public void onTimeout(String message) {
		doExtractFailure();
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.callback.ConnectionCallback#onFailure(java.lang.String)
	 */
	public void onFailure(String message) {
		doExtractFailure();
	}
	
	/**
	 * Submit request failure
	 */
	protected void doExtractFailure() {
		/*
		 * Reset any action indicator
		 */
		ActionIndicator.getInstance().reset();	
	
		String message = "Extract request failed due to server error.";
		service.doRequestError(message);		
	
	}

}

