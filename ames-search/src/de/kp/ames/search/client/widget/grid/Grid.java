package de.kp.ames.search.client.widget.grid;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.widgets.grid.ListGridField;
import de.kp.ames.search.client.handler.GridRecordHandler;
import de.kp.ames.search.client.method.RequestMethod;


public interface Grid {
	
	
	/**
	 * Register Grid RecordHandler
	 * 
	 * @param recordHandler
	 */
	public void addRecordHandler(GridRecordHandler recordHandler);
	
	/**
	 * Data Handling after grid content has changed
	 */
	public void reload();

	/**
	 * Data Handling after grid content has changed
	 * 
	 * @param attributes
	 */
	public void reload(HashMap<String,String> attributes);

	/**
	 * @param attributes
	 * @return
	 */
	public DataSourceField[] createDataFields();

	/**
	 * @param attributes
	 * @return
	 */
	public ListGridField[] createGridFields();

	/**
	 * Create request method from attributes
	 * 
	 * @param attributes
	 * @return
	 */
	public RequestMethod createMethod();

	/**
	 * Create Data source
	 * 
	 * @param attributes
	 */
	public void createScGridDS();

	/**
	 * Create request parameters
	 * 
	 * @return
	 */
	public Map<String,String> getRequestParams();
	
	/**
	 * @return
	 */
	public String getRequestUrl();
	
}
