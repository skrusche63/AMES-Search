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

public interface Action {

	/**
	 * Execute controlled action
	 */
	public void execute();

	/**
	 * Request parameters to be sent
	 * to the server side
	 * 
	 * @param params
	 */
	public void setParams(HashMap<String,String> params);
	
	/**
	 * Retrieve request parameters
	 * 
	 * @return
	 */
	public HashMap<String,String> getParams();
	
}
