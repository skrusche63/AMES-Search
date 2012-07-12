package de.kp.ames.search.client.widget.grid;

import java.util.HashMap;
import java.util.Map;

import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;

import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.handler.SuggestRecordHandlerImpl;
import de.kp.ames.search.client.method.RequestMethod;
import de.kp.ames.search.client.method.RequestMethodImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.SuggestObject;

public class SuggestGridImpl extends GridImpl {

	private String query;

	public SuggestGridImpl(String query) {
		super(GUIGlobals.SEARCH_URL, "search");
		
		this.setWidth(320);
		//this.setHeight(240);

		setQuery(query);

		/*
		 * Register data
		 */
		HashMap<String, String> attributes = new HashMap<String, String>();

		/*
		 * Create data object
		 */
		this.dataObject = createDataObject(attributes);

		/*
		 * Create data source
		 */
		this.createScGridDS(attributes);

		/*
		 * Create grid fields
		 */
		this.setFields(createGridFields(attributes));
		
		this.setGroupStartOpen(GroupStartOpen.ALL);
		this.setGroupByField("hypernym");
		
		this.setShowHeader(false);
		this.setCellHeight(32);
		
		/*
		 * Add Record Handler
		 */
		SuggestRecordHandlerImpl recordHandler = new SuggestRecordHandlerImpl(this);

		this.addRecordHandler(recordHandler);

	}
	
	public void setQuery(String query) {
		this.query = query;
	}


	public Map<String,String> getRequestParams() {
		
		HashMap<String,String> attributes = new HashMap<String,String>();
		attributes.put(JsonConstants.J_QUERY, this.query);
		/*
		 * Retrieve request method for attributes
		 */
		RequestMethod requestMethod = createMethod(attributes);
		
		return requestMethod.toParams();
	}

	
	/**
	 * @param attributes
	 * @return
	 */
	private DataObject createDataObject(HashMap<String, String> attributes) {
		/*
		 * Distinguish between accessor and remote object
		 */

		return new SuggestObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kp.ames.web.client.core.grid.GridImpl#getDetailFieldName()
	 */
	public String getDetailFieldName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.core.grid.Grid#afterRecordClick(com.smartgwt
	 * .client.widgets.grid.events.RecordClickEvent)
	 */
	public void afterRecordClick(RecordClickEvent event) {
		// TODO: to override
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.core.grid.BaseGrid#createMethod(java.util.HashMap)
	 */
	public RequestMethod createMethod(HashMap<String,String> attributes) {

		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SUGGEST);
		
		requestMethod.setAttributes(attributes);
		return requestMethod;

	}

	
}
