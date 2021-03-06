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
import com.smartgwt.client.util.SC;

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

			SC.logWarn("======> GetJsonCallbackImpl.onSuccess" + this.activity);

			this.activity.execute(jValue);
			
		} catch (NullPointerException e) {
			doGetFailure();
			
		}

	}

}
