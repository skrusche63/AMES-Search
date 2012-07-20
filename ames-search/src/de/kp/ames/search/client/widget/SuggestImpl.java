package de.kp.ames.search.client.widget;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.model.SuggestObject;
import de.kp.ames.search.client.widget.grid.SuggestGridImpl;

public class SuggestImpl extends VLayout {
	
	private SuggestGridImpl grid;
	
	/**
	 * Constructor depends on search query 
	 * 
	 * @param query
	 */
	public SuggestImpl(String query) {

		this.setWidth(GuiGlobals.SUGGEST_WIDTH);
		// height will be set dynamic in relation to rootpanel height
		this.setHeight(GuiGlobals.SUGGEST_HEIGHT);

		/*
		 * This is an essential feature to ensure
		 * proper scrollbars, i.e vertical ones only
		 */
		this.setOverflow(Overflow.HIDDEN);

		grid = new SuggestGridImpl(query);
	
		this.addMember(grid);
		
	};

	public SuggestImpl(JSONValue jValue) {

		this.setWidth(GuiGlobals.SUGGEST_WIDTH);
		// height will be set dynamic in relation to rootpanel height
		this.setHeight(GuiGlobals.SUGGEST_HEIGHT);

		/*
		 * This is an essential feature to ensure
		 * proper scrollbars, i.e vertical ones only
		 */
		this.setOverflow(Overflow.HIDDEN);
		
		ListGridRecord[] records = new SuggestObject().createListGridRecords(jValue);
		grid = new SuggestGridImpl(records);

		this.addMember(grid);

	}

	/*
	 * focus moves from SearchBox to SuggestGrid
	 */
	public void focusToSuggestGrid() {
		grid.focusToSuggestGrid();
	}


	private int getDrawnHeight() {
		Integer[] visibleRows = grid.getVisibleRows();
		
		SC.logWarn("====> grid visibleRows: " + visibleRows[0] + " : " + visibleRows[1]);
		
		int visibleHeight = 0; 
		for (int visibleRow = visibleRows[0]; visibleRow <= visibleRows[1]; visibleRow++) {
			visibleHeight += grid.getDrawnRowHeight(visibleRow);
		}
		return visibleHeight;
	}
	/*
	 * dynamic calculated new height
	 */
	public void setHeightTo(int h) {

//		grid.setMaxHeight(h);
		int drawnHeight = getDrawnHeight();
		SC.logWarn("====> grid drawnHeight: " + drawnHeight);
		if (drawnHeight < GuiGlobals.SUGGEST_HEIGHT) {
			this.setHeight(drawnHeight);
		}
	}

}
