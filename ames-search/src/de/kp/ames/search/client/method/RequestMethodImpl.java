package de.kp.ames.search.client.method;
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
import java.util.Map;
import java.util.Set;

/**
 * @author Stefan Krusche (krusche@dr-kruscheundpartner.de)
 *
 */

public class RequestMethodImpl implements RequestMethod {

	// method name
	private String name;

	private HashMap<String, String> attributes;
	
	/**
	 * Constructor
	 */
	public RequestMethodImpl() {	
		// initialize method attributes
		this.attributes = new HashMap<String, String>();
	}

	/**
	 * Constructor with method name
	 * @param name
	 */
	public RequestMethodImpl(String name) {
		// register method name
		this.name = name;
		// initialize method attributes
		this.attributes = new HashMap<String, String>();
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.method.RequestMethod#getName()
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.method.RequestMethod#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.method.RequestMethod#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String key, String value) {
		this.attributes.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.method.RequestMethod#toQuery()
	 */
	public String toQuery() {
		
		StringBuffer sb = new StringBuffer();
		
		// add method
		sb.append("?method=" + this.name);
		
		// add attributes
		Set<String> keys = attributes.keySet();
		for (String key:keys) {
			sb.append("&" + key + "=" + attributes.get(key));
		}
		
		return sb.toString();

	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.method.RequestMethod#setAttributes(java.util.HashMap)
	 */
	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes.putAll(attributes);		
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.method.RequestMethod#toParams()
	 */
	public Map<String,String> toParams() {
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("method", this.name);
		
		params.putAll(this.attributes);
		return params;
		
	}
	
}
