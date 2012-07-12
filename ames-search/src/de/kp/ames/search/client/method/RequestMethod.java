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

public interface RequestMethod {

	/**
	 * This method returns the name of a method
	 * @return
	 */
	public String getName();
	
	/**
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, String value);
	
	/**
	 * @param attributes
	 */
	public void setAttributes(HashMap<String, String> attributes);
	
	/**
	 * This method returns a method and its attributes as
	 * a query for an URI
	 * @return
	 */
	public String toQuery();

	/**
	 * This method returns a method and its attributes as
	 * a Map<String,String> of url parameters
	 * 
	 * @return
	 */
	public Map<String,String> toParams();
	
}