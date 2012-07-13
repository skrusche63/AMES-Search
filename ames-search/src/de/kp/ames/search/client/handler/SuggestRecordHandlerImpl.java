package de.kp.ames.search.client.handler;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.widgets.grid.ListGridRecord;

import de.kp.ames.search.client.control.MainController;
import de.kp.ames.search.client.widget.grid.Grid;

public class SuggestRecordHandlerImpl extends GridRecordHandlerImpl {

	/**
	 * Constructor
	 * 
	 * @param grid
	 */
	public SuggestRecordHandlerImpl(Grid grid) {
		super(grid);
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.handler.GridRecordHandlerImpl#doSelect(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	public void doSelect(ListGridRecord record) {
		MainController.getInstance().doAfterSuggest(record);
	}
	
}
