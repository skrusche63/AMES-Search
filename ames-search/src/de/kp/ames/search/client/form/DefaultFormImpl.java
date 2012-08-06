package de.kp.ames.search.client.form;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;

import de.kp.ames.search.client.model.DataObject;

public class DefaultFormImpl extends DynamicForm {

	/**
	 * Constructor
	 */
	public DefaultFormImpl(DataObject dataObject) {
		super();
		
		this.setTitleSuffix(""); // default ":"
		
		this.setColWidths(100, 320);
		this.setFixedColWidths(true);
		
		this.setPadding(16);
		this.setTitleOrientation(TitleOrientation.LEFT);
		
		this.setAutoFocus(true);
		this.setLayoutAlign(Alignment.CENTER);
		
		FormItem[] formItems = dataObject.createFormItemsAsArray();
		this.setFields(formItems);
		
	}	

}
