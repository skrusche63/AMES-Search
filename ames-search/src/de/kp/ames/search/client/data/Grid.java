package de.kp.ames.search.client.data;
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

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.menu.Menu;

import de.kp.ames.search.client.handler.GridRecordHandler;

public interface Grid {
	
	
	/**
	 * Register Grid RecordHandler
	 * 
	 * @param recordHandler
	 */
	public void addRecordHandler(GridRecordHandler recordHandler);
	
	/**
	 * Event handling after left mouse click
	 * on grid entry (cell)
	 * 
	 * @param event
	 */
	public void afterCellClick(CellClickEvent event);

	/**
	 * @param event
	 */
	public void afterRecordDoubleClick(RecordDoubleClickEvent event);

	/**
	 * @param event
	 */
	public void afterSelectionChanged(SelectionEvent event);

	/**
	 * @return
	 */
	public String getDetailFieldName();
	
	/**
	 * Data Handling after grid content has changed
	 */
	public void reload();

	/**
	 * Data Handling after grid content has changed
     *
	 * @param attributes
	 */
	public void reload(HashMap<String,String> attributes);
	
	/**
	 * @return
	 */
	public DataSourceField[] createDataFields();

	/**
	 * @return
	 */
	public ListGridField[] createGridFields();

	/**
	 * @return
	 */
	public ListGridRecord[] createGridRecords();
	
	/**
	 * @param menu
	 */
	public void setMenu(Menu menu);
	
	
}
