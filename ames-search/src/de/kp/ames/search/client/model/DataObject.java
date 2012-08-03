package de.kp.ames.search.client.model;
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

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeGridField;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public interface DataObject {
	/**
	 * @return
	 */
	public FormItem[] createFormItemsAsArray();

	/**
	 * @return
	 */
	public ArrayList<FormItem> createFormItemsAsList();

	/**
	 * @return
	 */
	public DataSourceField[] createDataFieldsAsArray();

	/**
	 * @return
	 */
	public ArrayList<DataSourceField> createDataFieldsAsList();

	/**
	 * @return
	 */
	public ListGridRecord[] createListGridRecordsAsArray();
	
	/**
	 * @return
	 */
	public ArrayList<ListGridRecord> createListGridRecordsAsList();
		
	/**
	 * @return
	 */
	public ListGridField[] createListGridFieldsAsArray();

	/**
	 * @return
	 */
	public ArrayList<ListGridField> createListGridFieldsAsList();

	/**
	 * @return
	 */
	public TreeGridField[] createTreeGridFieldsAsArray();
	
	/**
	 * @return
	 */
	public ArrayList<TreeGridField> createTreeGridFieldsAsList();
	
	/**
	 * @return
	 */
	public DetailViewerField[] createViewerFieldsAsArray();

	/**
	 * @return
	 */
	public ArrayList<DetailViewerField> createViewerFieldsAsList();

}
