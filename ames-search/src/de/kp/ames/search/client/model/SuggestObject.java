package de.kp.ames.search.client.model;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.ArrayList;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.model.external.ExternalObject;

public class SuggestObject extends ExternalObject {

	/**
	 * Constructor
	 */
	public SuggestObject() {

	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.model.DataObject#createDataFields()
	 */
	@Override
	public ArrayList<DataSourceField> createDataFieldsAsList() {

		ArrayList<DataSourceField> fields = new ArrayList<DataSourceField>();

		/*
		 * Primary key
		 */
		DataSourceTextField primaryKey = new DataSourceTextField(JsonConstants.J_ID);
		primaryKey.setHidden(true);
		primaryKey.setPrimaryKey(true);
		fields.add(primaryKey);
		
		fields.add(new DataSourceTextField(JsonConstants.J_TERM));
		fields.add(new DataSourceTextField(JsonConstants.J_HYPERNYM));

		fields.add(new DataSourceTextField(JsonConstants.J_RESULT));
		fields.add(new DataSourceTextField(JsonConstants.J_DESC));
		fields.add(new DataSourceTextField(JsonConstants.J_QUERYSTRING));

		return fields;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.model.DataObject#createGridFields()
	 */
	@Override
	public ArrayList<ListGridField> createListGridFieldsAsList() {

		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

		ListGridField hypernymField = new ListGridField(JsonConstants.J_HYPERNYM);
		hypernymField.setHidden(true);
		
		ListGridField resultField = new ListGridField(JsonConstants.J_RESULT);
		resultField.setAlign(Alignment.LEFT);  

		fields.add(hypernymField);
		fields.add(resultField);
		
		return fields;
		
	}

	/*
	 * create records from JSON (for async pattern)
	 */
	public ListGridRecord[] createListGridRecords(JSONValue jValue) {

		ArrayList<ListGridRecord> records = new ArrayList<ListGridRecord>();

		JSONArray jArray = jValue.isArray();
		for (int i=0; i < jArray.size(); i++) {
			
			JSONObject jRecord = jArray.get(i).isObject();
			ListGridRecord record = new ListGridRecord();
			
			Set<String> keys = jRecord.keySet();
			for (String key:keys) {
				
				String val = jRecord.get(key).isString().stringValue();
				record.setAttribute(key, val);
				
				records.add(record);
				
			}
			
		}
		
		return (ListGridRecord[]) records.toArray(new ListGridRecord[records.size()]);
		
	}


}
