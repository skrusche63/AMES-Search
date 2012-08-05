package de.kp.ames.search.client.model;

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridField;

import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.model.external.ExternalObject;

public class ResultObject extends ExternalObject {

	public ArrayList<DataSourceField> createDataFieldsAsList() {
		ArrayList<DataSourceField> fields = new ArrayList<DataSourceField>();

		fields.add(new DataSourceTextField(JsonConstants.J_ID));
		fields.add(new DataSourceTextField(JsonConstants.J_RESULT));
		fields.add(new DataSourceTextField(JsonConstants.J_TITLE));
		fields.add(new DataSourceTextField(JsonConstants.J_NAME));
		fields.add(new DataSourceTextField(JsonConstants.J_DESC));

		return fields; 
}

	public ArrayList<ListGridField> createListGridFieldsAsList() {

		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

		fields.add(new ListGridField(JsonConstants.J_RESULT));
		ListGridField descField = new ListGridField(JsonConstants.J_DESC);
		descField.setHidden(true);
		fields.add(descField);
		
		return fields;
	}

}
