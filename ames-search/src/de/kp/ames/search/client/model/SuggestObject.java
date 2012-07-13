package de.kp.ames.search.client.model;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

import de.kp.ames.search.client.globals.JsonConstants;

public class SuggestObject implements DataObject {

	/**
	 * Constructor
	 */
	public SuggestObject() {

	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.model.DataObject#createDataFields()
	 */
	public DataSourceField[] createDataFields() {

		ArrayList<DataSourceField> fields = new ArrayList<DataSourceField>();

		fields.add(new DataSourceTextField(JsonConstants.J_TERM));
		fields.add(new DataSourceTextField(JsonConstants.J_HYPERNYM));

		fields.add(new DataSourceTextField(JsonConstants.J_SYNONYMS));
		fields.add(new DataSourceTextField(JsonConstants.J_DESC));
		fields.add(new DataSourceTextField(JsonConstants.J_QUERYSTRING));

		return (DataSourceField[]) fields.toArray(new DataSourceField[fields.size()]);
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.model.DataObject#createGridFields()
	 */
	public ListGridField[] createGridFields() {

		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

		ListGridField hypernymField = new ListGridField(JsonConstants.J_HYPERNYM);
		hypernymField.setHidden(true);
		
		ListGridField synonymsField = new ListGridField(JsonConstants.J_SYNONYMS);
		synonymsField.setWidth(380); // same as parent Grid 
		
		// http://www.smartclient.com/smartgwt/showcase/#grid_autofit_values
		synonymsField.setAlign(Alignment.LEFT);  

		fields.add(hypernymField);
		fields.add(synonymsField);
		
		return (ListGridField[]) fields.toArray(new ListGridField[fields.size()]);
		
	}

}
