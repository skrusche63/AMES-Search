package de.kp.ames.search.client.widget;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.data.SuggestGridImpl;
import de.kp.ames.search.client.globals.GUIGlobals;

public class SuggestImpl extends VLayout {
	
	private SuggestGridImpl grid;
	
	/**
	 * Constructor depends on search query 
	 * 
	 * @param query
	 */
	public SuggestImpl(String query) {

		this.setWidth(GUIGlobals.SUGGEST_WIDTH);
		// height will be set dynamic in relation to rootpanel height
		this.setHeight(GUIGlobals.SUGGEST_HEIGHT);

		/*
		 * This is an essential feature to ensure
		 * proper scrollbars, i.e vertical ones only
		 */
		this.setOverflow(Overflow.HIDDEN);

		grid = new SuggestGridImpl(query);
	
		this.addMember(grid);
		
	};

	// this is a showcase variant for async service calls
	// will need a LocalGridImpl instead
//	public SuggestImpl(JSONValue jValue) {
//
//		this.setWidth(GUIGlobals.SUGGEST_WIDTH);
//		// height will be set dynamic in relation to rootpanel height
//		this.setHeight(GUIGlobals.SUGGEST_HEIGHT);
//
//		/*
//		 * This is an essential feature to ensure
//		 * proper scrollbars, i.e vertical ones only
//		 */
//		this.setOverflow(Overflow.HIDDEN);
//		
//		ListGridRecord[] records = new SuggestObject().createListGridRecords(jValue);
//		grid = new SuggestGridImpl(records);
//
//		this.addMember(grid);
//
//	}

	/*
	 * focus moves from SearchBox to SuggestGrid
	 */
	public void focusToSuggestGrid() {

		SC.logWarn("====> SuggestImpl.focusToSuggestGrid");

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
		if (drawnHeight < GUIGlobals.SUGGEST_HEIGHT) {
			this.setHeight(drawnHeight);
		}
	}

}
