package de.kp.ames.search.client.data;

import com.google.gwt.json.client.JSONArray;
import com.smartgwt.client.widgets.grid.ListGridRecord;

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

public class LocalGridImpl extends GridImpl {

	/**
	 * Constructor
	 */
	public LocalGridImpl() {
		super();
		
		/*
		 * Data handling
		 */

		this.setShowAllRecords(true);  
        this.setCanReorderRecords(true);
                
	}
	
	/**
	 * Add record to grid
	 * 
	 * @param record
	 */
	public void addRecord(ListGridRecord record) {
		this.addData(record);
	}
	
	/**
	 * Retrieve grid data in JSON representation
	 * 
	 * @return
	 */
	public JSONArray getGridData() {
		/*
		 * Must be overridden
		 */
		return null;
	}
}
