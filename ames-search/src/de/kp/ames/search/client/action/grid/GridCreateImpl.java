package de.kp.ames.search.client.action.grid;
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

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.action.ActionImpl;
import de.kp.ames.search.client.data.Grid;
import de.kp.ames.search.client.globals.GUIGlobals;

public class GridCreateImpl extends ActionImpl {

	/*
	 * Reference to Grid
	 */
	protected Grid grid;
	
	/**
	 * Constructor
	 * 
	 * @param grid
	 */
	public GridCreateImpl(Grid grid) {	
		this.grid = grid;
	}
	
	/**
	 * A typical after create activity (may be overridden)
	 * 
	 * @param jValue (server response)
	 */
	public void doAfterCreate(JSONValue jValue) {

		this.registerResponse(jValue);
		if (this.isSuccess()) {					
			/*
			 * Update grid
			 */
			grid.reload();
		
		} else {
			/*
			 * Fail message
			 */
			String message = this.getMessage();
			SC.say(GUIGlobals.APP_TITLE + ": Request Error", message);		

		}

	}
	
}
