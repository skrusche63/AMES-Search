package de.kp.ames.search.client.widget.grid;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.Map;

import com.smartgwt.client.data.Record;
import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.globals.GuiStyles;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.method.RequestMethod;
import de.kp.ames.search.client.method.RequestMethodImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.ResultObject;

public class SearchResultGridImpl extends GridImpl {

	public SearchResultGridImpl(Record record) {
		super(GuiGlobals.SEARCH_URL, "search");
		init();
		setSearchData(record);

	}

	public SearchResultGridImpl(String query) {
		super(GuiGlobals.SEARCH_URL, "search");
		init();
		setSearchData(query);

	}

	private void init() {
		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_0);

		this.setHeight100();
		this.setWidth100();


		/*
		 * Create data object
		 */
		this.dataObject = createDataObject();

		/*
		 * Create data source
		 */
		this.createScGridDS();

		/*
		 * Create grid fields
		 */
		this.setFields(createGridFields());

		this.setShowHeader(false);
	}

	/**
	 * Prepare search data from suggest result
	 * 
	 * @param record
	 */
	public void setSearchData(Record record) {

		String queryString = record.getAttributeAsString(JsonConstants.J_QUERYSTRING);
		setSearchData(queryString);
	
	}

	/**
	 * Prepare search data from suggest result
	 * 
	 * @param String
	 */
	public void setSearchData(String query) {

		attributes.put(MethodConstants.ATTR_QUERY, query);
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#getRequestParams()
	 */
	public Map<String, String> getRequestParams() {

		RequestMethod requestMethod = createMethod();
		return requestMethod.toParams();

	}

	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new ResultObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#createMethod()
	 */
	public RequestMethod createMethod() {

		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SEARCH);

		requestMethod.setAttributes(attributes);
		return requestMethod;

	}
	
}
