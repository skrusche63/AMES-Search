package de.kp.ames.search.client.action;
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

import java.util.HashMap;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

import de.kp.ames.search.client.globals.JsonConstants;



public class ActionImpl implements Action {

	/*
	 * Request specific parameters
	 */
	protected HashMap<String,String> params;
	
	/*
	 * Reference to action (server) response
	 */
	private JSONObject jResponse;
	
	public ActionImpl() {
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.action.Action#execute()
	 */
	public void execute() {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.action.Action#getParams()
	 */
	public HashMap<String,String> getParams() {
		if (this.params == null) this.params = new HashMap<String,String>();
		return this.params;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.action.Action#setParams(java.util.HashMap)
	 */
	public void setParams(HashMap<String,String> params) {
		this.params = params;
	}

	/**
	 * @param jValue
	 */
	public void registerResponse(JSONValue jValue) {
		this.jResponse = jValue.isObject();
	}
	
	/**
	 * @return
	 */
	public boolean isSuccess() {
		return jResponse.get(JsonConstants.J_SUCCESS).isBoolean().booleanValue();
	}
	
	/**
	 * @return
	 */
	public String getMessage() {
		return jResponse.get(JsonConstants.J_MESSAGE).isString().stringValue();
	}

}
