package de.kp.ames.search.client.data;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;

import de.kp.ames.search.client.control.SimilarityController;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.handler.ResultRecordHandlerImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.ResultObject;
import de.kp.ames.search.client.style.GuiStyles;

public class ResultGridImpl extends RemoteGridImpl {

	public ResultGridImpl(Record record) {
		super(GUIGlobals.SEARCH_URL, "search");
		initialize();
		setQueryAttributeParam(record);

	}

	public ResultGridImpl(String query) {
		super(GUIGlobals.SEARCH_URL, "search");
		initialize();
		setQueryAttributeParam(query);

	}

	private void initialize() {
		
		SC.logWarn("======> ResultGridImpl.initialize");

		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_0);

		this.setHeight100();
		this.setWidth100();

		this.setFixedRecordHeights(false);
		this.setWrapCells(true);
		this.setSelectionType(SelectionStyle.SINGLE);
		this.setShowHeader(true);

		/*
		 * Register data
		 */
		attributes = new HashMap<String,String>();
		attributes.put(MethodConstants.ATTR_TYPE, "search");
		attributes.put(MethodConstants.ATTR_SOURCE, GUIGlobals.SEARCH_SOURCE);

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

		
		/*
		 * Add Record Handler
		 */
		ResultRecordHandlerImpl recordHandler = new ResultRecordHandlerImpl(this);
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
		SearchEventManager.getInstance().doAfterResultRecordConfirmed(record);
	}

	/**
	 * @param event
	 */
	@Override
	public void afterDataArrived(DataArrivedEvent event) {

		SC.logWarn("====> ResultGridImpl.afterDataArrived -> focus");
		
		this.focus();
		
		if ((this.getRecords().length>0) && (this.getSelectedRecords().length == 0)) {
			/*
			 * nothing selected yet, select first in row if records are
			 * available there must be a group header first because of this we
			 * select second record, which contains first suggestion
			 */
			SC.logWarn("======> ResultGridImpl.afterDataArrived 2");
			this.selectSingleRecord(0);
			// force similarity
			new SimilarityController().doGetSimilarity(this.getSelectedRecord());

			
		}
	}

	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new ResultObject();
	}

	
}
