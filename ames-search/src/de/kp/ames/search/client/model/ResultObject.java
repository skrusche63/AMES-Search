package de.kp.ames.search.client.model;

import java.util.ArrayList;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
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
		fields.add(new DataSourceTextField(JsonConstants.J_SOURCE));
		fields.add(new DataSourceTextField(JsonConstants.J_ICON));

		return fields; 
}

	public ArrayList<ListGridField> createListGridFieldsAsList() {

		ArrayList<ListGridField> fields = new ArrayList<ListGridField>();

		ListGridField icon = new ListGridField(JsonConstants.J_ICON, "LoC", 50);  
		icon.setAlign(Alignment.CENTER);  
		icon.setType(ListGridFieldType.IMAGE);  
		icon.setImageURLPrefix("silk/");  
		icon.setImageURLSuffix(".png");  
		
		fields.add(icon);
		
		ListGridField title = new ListGridField(JsonConstants.J_TITLE, "Module");
		title.setWidth("*");
		fields.add(title);
		
//		ListGridField descField = new ListGridField(JsonConstants.J_DESC);
//		descField.setHidden(true);
//		fields.add(descField);
		
		return fields;
	}

}
