package de.kp.ames.search.client.model;

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.grid.ListGridField;

public class ResultObject implements DataObject {

	@Override
	public DataSourceField[] createDataFields() {
		ArrayList<DataSourceField> fields = new ArrayList<DataSourceField>();

		return (DataSourceField[]) fields.toArray(new DataSourceField[fields.size()]);	}

	@Override
	public ListGridField[] createGridFields() {

		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

//		ListGridField synonymsField = new ListGridField(JsonConstants.J_SYNONYMS);
//		synonymsField.setWidth(380); // same as parent Grid 
//		
//		// http://www.smartclient.com/smartgwt/showcase/#grid_autofit_values
//		synonymsField.setAlign(Alignment.LEFT);  

//		fields.add(synonymsField);
		
		return (ListGridField[]) fields.toArray(new ListGridField[fields.size()]);
	}

}
