package de.kp.ames.search.client.util;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.smartgwt.client.data.Record;

public class JsonConverter {

	/**
	 * @param record
	 * @param keys
	 * @return
	 */
	public static JSONObject recordToJson(Record record, String[] keys) {
		
		JSONObject jRecord = new JSONObject();
		
		for (String key:keys) {
			jRecord.put(key, new JSONString(record.getAttributeAsString(key)));
		}
		
		return jRecord;
		
	}
		
}
