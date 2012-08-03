package de.kp.ames.search.client.handler;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;

import de.kp.ames.search.client.data.Grid;
import de.kp.ames.search.client.event.SearchEventManager;

public class SearchRecordHandlerImpl extends GridRecordHandlerImpl {

	/**
	 * Constructor
	 * 
	 * @param grid
	 */
	public SearchRecordHandlerImpl(Grid grid) {
		super(grid);
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.handler.GridRecordHandlerImpl#doSelect(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	public void doSelect(Record record) {
		SC.logWarn("======> SearchRecordHandlerImpl.doSelect");
		SearchEventManager.getInstance().doAfterSearchResultSelected(record);
	}
	
}
