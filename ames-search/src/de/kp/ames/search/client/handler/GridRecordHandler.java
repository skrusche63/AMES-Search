package de.kp.ames.search.client.handler;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;

import com.smartgwt.client.widgets.grid.ListGridRecord;

import de.kp.ames.search.client.widget.grid.Grid;

public interface GridRecordHandler {

	/**
	 * @param record
	 */
	public void doSelect(ListGridRecord record);

	/**
	 * @param key
	 * @return
	 */
	public String getParam(String key);

	/**
	 * @return
	 */
	public HashMap<String,String> getParams();
	
	/**
	 * @param grid
	 */
	public void setGrid(Grid grid);
	
	/**
	 * @param key
	 * @param value
	 */
	public void setParam(String key, String value);

}
