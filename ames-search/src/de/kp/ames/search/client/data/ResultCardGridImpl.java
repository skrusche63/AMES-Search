package de.kp.ames.search.client.data;

import java.util.HashMap;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;

import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.ResultCartObject;


public class ResultCardGridImpl extends LocalGridImpl {

	public ResultCardGridImpl() {
		super();

		SC.logWarn("==========> ResultCardGridImpl.CTOR");

        /*
		 * Register data
		 */
		attributes = new HashMap<String,String>();

		/*
		 * Create data object
		 */
		this.dataObject = createDataObject();
				
		/*
		 * Create grid fields
		 */
		this.setFields(createGridFields());

	}

	@Override
	public String getDetailFieldName() {
		return null;
	}

	@Override
	public void afterRecordDoubleClick(RecordDoubleClickEvent event) {
		
		/*
		 * Retrieve affected grid record
		 */
		Record record = event.getRecord();
		// TODO: delete dialog
		
		// test getGridData
		getGridData();

	}

	
	@Override
	public JSONArray getGridData() {
		JSONArray jData = new JSONArray();
		String[] attributes = null;
		
		RecordList recordList = this.getDataAsRecordList();
		SC.logWarn("====> RCG.getGriddata: recordList.length: " + recordList.getLength());
		for (int i = 0; i < recordList.getLength(); i++) {
			Record record = recordList.get(i);
			JSONObject jRecord = new JSONObject();
			if (attributes == null)
				attributes = record.getAttributes();

			SC.logWarn("======> RCG.getGriddata: attributes.length: " + attributes.length);

			for (int j = 0; j < attributes.length; j++) {
				String attribute = attributes[j];
				SC.logWarn("====> RCG.getGriddata: attribute value: " + attribute +" :: "+ record.getAttributeAsString(attribute)); 
				jRecord.put(attribute, new JSONString(record.getAttributeAsString(attribute)));
			}
			jData.set(i, jRecord);
		}
		
		SC.logWarn("====> RCG.getGridData: " + jData.toString());
		return jData;
	}

	
	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new ResultCartObject();
	}

	public boolean hasCombinedId(String id) {
		RecordList recordList = this.getDataAsRecordList();
		for (int i = 0; i < recordList.getLength(); i++) {
			Record record = recordList.get(i);
			if (record.getAttribute("id").equals(id))
				return true;
		}
		return false;
	}
	
	
}
