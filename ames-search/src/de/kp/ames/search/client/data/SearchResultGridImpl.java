package de.kp.ames.search.client.data;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;

import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.handler.SearchRecordHandlerImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.ResultObject;
import de.kp.ames.search.client.style.GuiStyles;

public class SearchResultGridImpl extends RemoteGridImpl {

	public SearchResultGridImpl(Record record) {
		super(GUIGlobals.SEARCH_URL, "search");
		initialize();
		setQueryAttributeParam(record);

	}

	public SearchResultGridImpl(String query) {
		super(GUIGlobals.SEARCH_URL, "search");
		initialize();
		setQueryAttributeParam(query);

	}

	private void initialize() {
		
		SC.logWarn("======> SearchResultGridImpl.initialize");

		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_0);

		this.setHeight100();
		this.setWidth100();

		this.setFixedRecordHeights(false);
		this.setWrapCells(true);


		/*
		 * Register data
		 */
		attributes = new HashMap<String,String>();
		attributes.put(MethodConstants.ATTR_TYPE, "search");

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
		
		/*
		 * Add Record Handler
		 */
		SearchRecordHandlerImpl recordHandler = new SearchRecordHandlerImpl(this);
		this.addRecordHandler(recordHandler);

	}

	/**
	 * Prepare search data from suggest result
	 * 
	 * @param record
	 */
	public void setQueryAttributeParam(Record record) {

		String queryString = record.getAttributeAsString(JsonConstants.J_QUERYSTRING);
		setQueryAttributeParam(queryString);
	
	}

	/**
	 * Prepare search data from suggest result
	 * 
	 * @param String
	 */
	public void setQueryAttributeParam(String query) {

		attributes.put(MethodConstants.ATTR_QUERY, query);
	
	}


	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new ResultObject();
	}

	@Override
	public void afterCellClick(CellClickEvent event) {
		/*
		 * do nothing, instead of trigger another recordHandler.doSelect()
		 */
	}

	@Override
	public void afterRecordDoubleClick(RecordDoubleClickEvent event) {
		
		/*
		 * Retrieve affected grid record
		 */
		Record record = event.getRecord();
		SearchEventManager.getInstance().doAfterSearchResultConfirmed(record);
	}
	
}
