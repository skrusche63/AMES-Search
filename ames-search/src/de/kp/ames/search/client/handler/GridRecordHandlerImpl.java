package de.kp.ames.search.client.handler;
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

import com.smartgwt.client.widgets.grid.ListGridRecord;

import de.kp.ames.search.client.widget.grid.Grid;

public class GridRecordHandlerImpl implements GridRecordHandler {
	/*
	 * Reference to Grid
	 */
	protected Grid grid;
	
	/*
	 * Reference to parameters
	 */
	protected HashMap<String,String> params;
	
	/**
	 * Constructor
	 */
	public GridRecordHandlerImpl() {		
	}
	
	/**
	 * Constructor
	 * 
	 * @param grid
	 */
	public GridRecordHandlerImpl(Grid grid) {
		this.grid = grid;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#doSelect(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	public void doSelect(ListGridRecord record) {
		/*
		 * Must be overridden
		 */
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#setGrid(de.kp.ames.web.client.core.grid.Grid)
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#setParam(java.lang.String, java.lang.String)
	 */
	public void setParam(String key, String value) {
		if (this.params == null) this.params = new HashMap<String,String>();
		this.params.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#getParam(java.lang.String)
	 */
	public String getParam(String key) {
		if ((this.params == null) || (this.params.containsKey(key) == false)) return null;
		return this.params.get(key);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#getParams()
	 */
	public HashMap<String,String> getParams() {
		return this.params;
	}

}
