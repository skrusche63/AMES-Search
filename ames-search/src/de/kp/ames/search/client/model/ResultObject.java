package de.kp.ames.search.client.model;

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridField;

import de.kp.ames.search.client.globals.JsonConstants;

public class ResultObject implements DataObject {

	@Override
	public DataSourceField[] createDataFields() {
		ArrayList<DataSourceField> fields = new ArrayList<DataSourceField>();

		fields.add(new DataSourceTextField(JsonConstants.J_ID));
		fields.add(new DataSourceTextField(JsonConstants.J_RESULT));
		fields.add(new DataSourceTextField(JsonConstants.J_TITLE));

		return (DataSourceField[]) fields.toArray(new DataSourceField[fields.size()]);	}

	@Override
	public ListGridField[] createGridFields() {

		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

		ListGridField resultField = new ListGridField(JsonConstants.J_RESULT);
//		synonymsField.setWidth(380); // same as parent Grid 
//		
//		// http://www.smartclient.com/smartgwt/showcase/#grid_autofit_values
//		synonymsField.setAlign(Alignment.LEFT);  

		fields.add(resultField);
		
		return (ListGridField[]) fields.toArray(new ListGridField[fields.size()]);
	}

}
