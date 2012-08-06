package de.kp.ames.search.client.form;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.TabSet;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.handler.FormHandler;
import de.kp.ames.search.client.style.GuiStyles;

public class FormImpl extends VLayout implements Form {

	protected static String LABEL_STYLE = "x-sc-label";

	/*
	 * Reference to dynamic form
	 */
	protected DynamicForm scForm;
	
	/*
	 * Reference to Form Handler
	 */
	protected FormHandler formHandler;

	/*
	 * Request specific parameters
	 */
	protected HashMap<String,String> params;
	
	/*
	 * Reference to Form Data
	 */
	protected JSONValue jData;
	
	/*
	 * Indicate read onyl form
	 */
	protected boolean readOnly;

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#setParam(java.lang.String, java.lang.String)
	 */
	public void setParam(String key, String value) {
		if (this.params == null) this.params = new HashMap<String,String>();
		this.params.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#getParam(java.lang.String)
	 */
	public String getParam(String key) {
		if ((this.params == null) || (this.params.containsKey(key) == false)) return null;
		return this.params.get(key);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#setParams(java.util.HashMap)
	 */
	public void setParams(HashMap<String,String> params) {
		if (this.params == null) this.params = new HashMap<String,String>();
		this.params.putAll(params);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#addFormData(com.google.gwt.json.client.JSONValue)
	 */
	public void addFormData(JSONValue jValue) {
		this.jData = jValue;
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#addFormData(com.smartgwt.client.data.Record)
	 */
	public void addFormData(Record record) {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#getFormData()
	 */
	public String getFormData() {
		/*
		 * Must be overridden
		 */
		return null;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#addFormHandler(de.kp.ames.web.client.core.form.FormHandler)
	 */
	public void addFormHandler(FormHandler formHandler) {
		this.formHandler = formHandler;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#createFormItemsAsArray()
	 */
	public FormItem[] createFormItemsAsArray() {
		
		ArrayList<FormItem> items = createFormItemsAsList();
		return (FormItem[])items.toArray(new FormItem [items.size()]);
		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#createFormItemsAsList()
	 */
	public ArrayList<FormItem> createFormItemsAsList() {
		/*
		 * Must be overridden
		 */
		return null;
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#doSubmit(de.kp.ames.web.client.core.activity.Activity)
	 */
	public void doSubmit(Activity afterSubmitActivity) {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.Form#doEnter()
	 */
	public void doEnter() {
		/*
		 * Must be overridden
		 */
		
	}
	
	/**
	 * A helper method to create a tabset for additional
	 * information rendering in a form
	 * 
	 * @return
	 */
	public TabSet createTabSet() {
		
		TabSet tabSet = new TabSet();
		
		/*
		 * TabSet layout settings
		 */
	    Layout tabProperties = new Layout();

	    tabProperties.setLayoutMargin(0);
	    tabProperties.setLayoutTopMargin(1);
	    
	    tabSet.setPaneContainerProperties(tabProperties);
	    return tabSet;
	    
	}
 
	/**
	 * A helper method to create a vertical layout
	 * container either bordered or unbordered
	 * 
	 * @param border
	 * @return
	 */
	public static VLayout createVLayout(boolean border) {

		VLayout layout = new VLayout();
		layout.setShowEdges(false);
		
        /*
         * Style
         */
        if (border == true) 
        	layout.setStyleName(GuiStyles.X_BD_STYLE_4);
        
        else
        	layout.setStyleName(GuiStyles.X_BD_STYLE_0);
        	
		return layout;
		
	}
	
}
