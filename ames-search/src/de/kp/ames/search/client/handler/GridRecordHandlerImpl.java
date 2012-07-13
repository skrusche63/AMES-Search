package de.kp.ames.search.client.handler;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;

import com.smartgwt.client.data.Record;

import de.kp.ames.search.client.widget.grid.Grid;

public class GridRecordHandlerImpl implements GridRecordHandler {
	/*
	 * Reference to Grid
	 */
	protected Grid grid;
	
	/*
	 * Reference to parameters
	 */
	protected HashMap<String,String> params;
	
	/**
	 * Constructor
	 */
	public GridRecordHandlerImpl() {		
	}
	
	/**
	 * Constructor
	 * 
	 * @param grid
	 */
	public GridRecordHandlerImpl(Grid grid) {
		this.grid = grid;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#doSelect(com.smartgwt.client.widgets.grid.ListGridRecord)
	 */
	public void doSelect(Record record) {
		/*
		 * Must be overridden
		 */
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#setGrid(de.kp.ames.web.client.core.grid.Grid)
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#setParam(java.lang.String, java.lang.String)
	 */
	public void setParam(String key, String value) {
		if (this.params == null) this.params = new HashMap<String,String>();
		this.params.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#getParam(java.lang.String)
	 */
	public String getParam(String key) {
		if ((this.params == null) || (this.params.containsKey(key) == false)) return null;
		return this.params.get(key);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.GridRecordHandler#getParams()
	 */
	public HashMap<String,String> getParams() {
		return this.params;
	}

}
