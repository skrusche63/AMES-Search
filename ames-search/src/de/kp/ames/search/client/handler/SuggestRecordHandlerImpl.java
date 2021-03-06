package de.kp.ames.search.client.handler;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.data.Grid;
import de.kp.ames.search.client.event.SearchEventManager;

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
	public void doSelect(Record record) {
		SC.logWarn("======> SuggestRecordHandlerImpl.doSelect");
		SearchEventManager.getInstance().doAfterSuggest(record);
	}
	
}
