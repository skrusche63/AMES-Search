package de.kp.ames.search.client.model;

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.grid.ListGridField;

import de.kp.ames.search.client.globals.JsonConstants;

public class SuggestObject implements DataObject {

	public SuggestObject() {

	}

	@Override
	public DataSourceField[] createDataFields() {
		ArrayList<DataSourceField> fields = new ArrayList<DataSourceField>();

		/*
		 * Term
		 */
		fields.add(new DataSourceTextField(JsonConstants.J_TERM, "Term"));
		fields.add(new DataSourceTextField("synonyms", "Synonyms"));
		fields.add(new DataSourceTextField("hypernym", "Hypernym"));
		fields.add(new DataSourceTextField("desc", "Description"));

		return (DataSourceField[]) fields.toArray(new DataSourceField[fields.size()]);
	}

	@Override
	public ListGridField[] createGridFields() {
		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

		ListGridField termField = new ListGridField(JsonConstants.J_TERM);
		termField.setWidth(50);

		ListGridField hypernymField = new ListGridField("hypernym");
		hypernymField.setHidden(true);
		
		ListGridField synonymsField = new ListGridField("synonyms");
		synonymsField.setWidth(280);
		
		// http://www.smartclient.com/smartgwt/showcase/#grid_autofit_values
		synonymsField.setAlign(Alignment.LEFT);  
				
		fields.add(hypernymField);
		fields.add(synonymsField);
		
		return (ListGridField[]) fields.toArray(new ListGridField[fields.size()]);
	}

}
