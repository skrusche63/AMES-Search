package de.kp.ames.search.client.model.external;
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

import de.kp.ames.search.client.model.DataObject;

public class ExternalObject implements DataObject {

	/**
	 * Constructor
	 */
	public ExternalObject() {
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createFormItemsAsArray()
	 */
	public FormItem[] createFormItemsAsArray() {
		
		ArrayList<FormItem> items = createFormItemsAsList();
		return (FormItem[])items.toArray(new FormItem [items.size()]);
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createFormItemsAsList()
	 */
	public ArrayList<FormItem> createFormItemsAsList() {
		/*
		 * Must be overridden
		 */
		return null;		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createDataFieldsAsArray()
	 */
	public DataSourceField[] createDataFieldsAsArray() {

		ArrayList<DataSourceField> fields = createDataFieldsAsList();
		return (DataSourceField[])fields.toArray(new DataSourceField [fields.size()]);
	
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createGridFieldsAsArray()
	 */
	public ListGridField[] createListGridFieldsAsArray() {
		
		ArrayList<ListGridField> fields = createListGridFieldsAsList();
		return (ListGridField[])fields.toArray(new ListGridField [fields.size()]);
				
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createDataFieldsAsList()
	 */
	public ArrayList<DataSourceField> createDataFieldsAsList() {
		/*
		 * Must be overridden
		 */
		return null;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createListGridRecordsAsArray()
	 */
	public ListGridRecord[] createListGridRecordsAsArray() {
		
		ArrayList<ListGridRecord> records = createListGridRecordsAsList();
		return (ListGridRecord[])records.toArray(new ListGridRecord [records.size()]);
		
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createListGridRecordsAsList()
	 */
	public ArrayList<ListGridRecord> createListGridRecordsAsList() {
		/*
		 * Must be overridden
		 */
		return null;		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createGridFieldsAsList()
	 */
	public ArrayList<ListGridField> createListGridFieldsAsList() {
		/*
		 * Must be overridden
		 */
		return null;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createTreeGridFieldsAsArray()
	 */
	public TreeGridField[] createTreeGridFieldsAsArray() {
		
		ArrayList<TreeGridField> fields = createTreeGridFieldsAsList();
		return (TreeGridField[])fields.toArray(new TreeGridField [fields.size()]);
				
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createTreeGridFieldsAsList()
	 */
	public ArrayList<TreeGridField> createTreeGridFieldsAsList() {
		/*
		 * Must be overridden
		 */
		return null;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createViewerFieldsAsArray()
	 */
	public DetailViewerField[] createViewerFieldsAsArray() {
		
		ArrayList<DetailViewerField> fields = createViewerFieldsAsList();
		return (DetailViewerField[])fields.toArray(new DetailViewerField [fields.size()]);
				
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.model.core.DataObject#createViewerFieldsAsList()
	 */
	public ArrayList<DetailViewerField> createViewerFieldsAsList() {
		/*
		 * Must be overridden
		 */
		return null;
	}

}
