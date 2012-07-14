package de.kp.ames.search.client.widget.grid;

import java.util.Map;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;

import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.globals.GuiStyles;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.handler.SuggestRecordHandlerImpl;
import de.kp.ames.search.client.method.RequestMethod;
import de.kp.ames.search.client.method.RequestMethodImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.SuggestObject;

public class SuggestGridImpl extends GridImpl {

	private String query;

	/**
	 * @param query
	 */
	public SuggestGridImpl(String query) {
		super(GuiGlobals.SEARCH_URL, "search");

		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_0);

		this.setHeight(1);
		this.setWidth100();
		
		this.setBodyOverflow(Overflow.VISIBLE);
		this.setOverflow(Overflow.VISIBLE);
		this.setLeaveScrollbarGap(false);

		setQuery(query);

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

		this.setGroupStartOpen(GroupStartOpen.ALL);
		this.setGroupByField(JsonConstants.J_HYPERNYM);

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

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#getRequestParams()
	 */
	public Map<String, String> getRequestParams() {

		/*
		 * Update attributes
		 */
		attributes.put(JsonConstants.J_QUERY, this.query);

		RequestMethod requestMethod = createMethod();
		return requestMethod.toParams();
		
	}

	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new SuggestObject();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.core.grid.Grid#afterRecordClick(com.smartgwt
	 * .client.widgets.grid.events.RecordClickEvent)
	 */
	public void afterRecordClick(RecordClickEvent event) {
		
		event.cancel();
		
		if (this.recordHandler == null) {
			SC.say("afterRecordClick: 1"); 
			return;
		}
			
		Record record = event.getRecord();
		this.recordHandler.doSelect(record);
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#createMethod()
	 */
	public RequestMethod createMethod() {

		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SUGGEST);

		requestMethod.setAttributes(attributes);
		return requestMethod;

	}

}
