package de.kp.ames.search.client.widget.grid;

import java.util.Map;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;

import de.kp.ames.search.client.control.SuggestController;
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

	public SuggestGridImpl(ListGridRecord[] records) {
		super(GuiGlobals.SEARCH_URL, "search");

		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_0);

//		this.setHeight(1);
		this.setHeight100();
		this.setWidth100();

//		this.setBodyOverflow(Overflow.VISIBLE);
//		this.setOverflow(Overflow.VISIBLE);

		this.setLeaveScrollbarGap(false);

		/*
		 * Create data object
		 */
		this.dataObject = createDataObject();

		/*
		 * Create data
		 */
		this.setData(records);

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

	/**
	 * @param query
	 */
	public SuggestGridImpl(String query) {
		super(GuiGlobals.SEARCH_URL, "search");

		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_0);

//		this.setHeight(1);
		this.setHeight100();
		this.setWidth100();

//		this.setBodyOverflow(Overflow.VISIBLE);
//		this.setOverflow(Overflow.AUTO);

//		this.setLeaveScrollbarGap(false);

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

		// this.setGroupStartOpen(GroupStartOpen.ALL);
		// this.setGroupByField(JsonConstants.J_HYPERNYM);

		this.setShowHeader(false);
//		this.setCellHeight(32);

		/*
		 * Add Record Handler
		 */
		SuggestRecordHandlerImpl recordHandler = new SuggestRecordHandlerImpl(this);
		this.addRecordHandler(recordHandler);

	}

	public void setQuery(String query) {
		this.query = query;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	 * Grid get focus from TextWidget with Arrow_Down
	 */
	public void focusToSuggestGrid() {

		if (this.getRecords().length == 0)
			return;

		this.focus();
		
		SC.logWarn("====> focus"); 

		if (this.getSelectedRecords().length == 0) {
			/*
			 * nothing selected yet, select first in row if records are
			 * available there must be a group header first because of this we
			 * select second record, which contains first suggestion
			 */
			this.selectSingleRecord(this.getRecords()[1]);
		}

	}
	
	/*
	 * KeyDown is called for each single Key
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.widget.grid.GridImpl#afterKeyDown(com.smartgwt
	 * .client.widgets.events.KeyDownEvent)
	 */
	public void afterKeyDown(KeyDownEvent event) {
		// has: event.cancel();
		String key = EventHandler.getKey();
		SC.logWarn("====> KeyDown key> " + key);

		/*
		 * KeyDown is called before navigation to next record finished
		 * selectedRecord is in that case the last or start position
		 */
		if (key.equals("Arrow_Left") || key.equals("Arrow_Right")) {
			// move focus up to text widget
			SuggestController.getInstance().focusToSearchBox();
			SC.logWarn("======> KeyDown left/right move focus to TextWidget: " + key);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.core.grid.Grid#afterRecordClick(com.smartgwt
	 * .client.widgets.grid.events.RecordClickEvent)
	 */
	public void afterRecordClick(RecordClickEvent event) {

		// group headers will not trigger that even
		Record record = event.getRecord();

		SC.logWarn("====> RecordClick event record: <" + record.getAttributeAsString("qsraw") + "> " + " id> "
				+ record.getAttributeAsString(JsonConstants.J_ID));

		int index = this.getRecordIndex(record);
		SC.logWarn("======> RecordClick index: " + index);

		/*
		 * check selected record
		 */
		Record selected = this.getSelectedRecord();
		SC.logWarn("======> RecordClick selected record: <" + selected.getAttributeAsString("qsraw") + "> " + " #>"
				+ this.getRecordIndex(selected) + " id> " + selected.getAttributeAsString(JsonConstants.J_ID));

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.core.grid.Grid#afterRecordDoubleClick(com.smartgwt
	 * .client.widgets.grid.events.RecordDoubleClickEvent)
	 */
	public void afterRecordDoubleClick(RecordDoubleClickEvent event) {
		// does not have event.cancel();
		Record record = event.getRecord();

		SC.logWarn("====> RecordDoubleClick: event record : <" + record.getAttributeAsString("qsraw") + "> " + " id> "
				+ record.getAttributeAsString(JsonConstants.J_ID));

		if (this.recordHandler == null) {
			SC.say("======> RecordDoubleClick: no recordHandler registered");
			return;
		}

		this.recordHandler.doSelect(record);
		SC.logWarn("======> RecordDoubleClick: event record search triggered");
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see de.kp.ames.search.client.widget.grid.GridImpl#createMethod()
	 */
	public RequestMethod createMethod() {

		RequestMethodImpl requestMethod = new RequestMethodImpl();
		requestMethod.setName(MethodConstants.METH_SUGGEST);

		requestMethod.setAttributes(attributes);
		return requestMethod;

	}

}
