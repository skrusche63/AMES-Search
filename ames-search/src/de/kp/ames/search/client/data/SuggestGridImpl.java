package de.kp.ames.search.client.data;

import java.util.HashMap;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyDownHandler;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

import de.kp.ames.search.client.control.SuggestController;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.globals.MethodConstants;
import de.kp.ames.search.client.handler.SuggestRecordHandlerImpl;
import de.kp.ames.search.client.model.DataObject;
import de.kp.ames.search.client.model.SuggestObject;
import de.kp.ames.search.client.style.GuiStyles;

public class SuggestGridImpl extends RemoteGridImpl {

	private String query;


	/**
	 * @param query
	 */
	public SuggestGridImpl(String query) {
		super(GUIGlobals.SEARCH_URL, "search");

		/*
		 * Set query
		 */
		this.query = query;

		initialize();

	}

	// this is a LocalGridImpl variant for async service calls
//	public SuggestGridImpl(ListGridRecord[] records) {
//		super(GUIGlobals.SEARCH_URL, "search");
//
//		initialize();
//
//		/*
//		 * Create data
//		 */
//		this.setData(records);
//
//	}

	private void initialize() {
		/*
		 * No border style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_4);

		this.setHeight100();
		this.setWidth100();

		this.setLeaveScrollbarGap(false);
		this.setSelectionType(SelectionStyle.SINGLE);
		this.setShowHeader(false);
		
		this.setFixedRecordHeights(false);
		this.setWrapCells(true);

		/*
		 * Register data
		 */
		attributes = new HashMap<String,String>();
		attributes.put(MethodConstants.ATTR_TYPE, "suggest");
		attributes.put(JsonConstants.J_QUERY, this.query);


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
		
		/*
		 * add keydown handler
		 */
		final SuggestGridImpl self = this;
		this.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				self.afterKeyDown(event);
				
			}
		});

		/*
		 * Add Record Handler
		 */
		SuggestRecordHandlerImpl recordHandler = new SuggestRecordHandlerImpl(this);
		this.addRecordHandler(recordHandler);
	}
	
	@Override
	public void afterSelectionChanged(SelectionEvent event) {
		// do nothing on single select
	}


	@Override
	public String getDetailFieldName() {
		// suppress detail field expansion option
		return null;
	}


	/*
	 * Grid get focus from TextWidget with Arrow_Down
	 */
	public void focusToSuggestGrid() {

		SC.logWarn("====> SuggestGridImpl.focusToSuggestGrid 1");
		SC.logWarn("====> SuggestGridImpl.focusToSuggestGrid empty?: " + this.getResultSet().isEmpty());

//		if (this.getRecords().length == 0)
		if (this.getResultSet().isEmpty())
			return;

		SC.logWarn("====> SuggestGridImpl.focusToSuggestGrid 2");

		this.focus();

		SC.logWarn("====> SuggestGridImpl.focusToSuggestGrid 3: selected: " + this.getSelectedRecords().length);

		if (this.getSelectedRecords().length == 0) {
			/*
			 * nothing selected yet, select first in row if records are
			 * available there must be a group header first because of this we
			 * select second record, which contains first suggestion
			 */
			SC.logWarn("====> SuggestGridImpl.focusToSuggestGrid 4");
			this.selectSingleRecord(1);
//			this.selectSingleRecord(this.getRecords()[1]);
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
	 * de.kp.ames.search.client.core.grid.Grid#afterRecordDoubleClick(com.smartgwt
	 * .client.widgets.grid.events.RecordDoubleClickEvent)
	 */
	@Override
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

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see de.kp.ames.search.client.widget.grid.GridImpl#createMethod()
//	 */
//	@Override
//	public RequestMethod createMethod() {
//
//		RequestMethodImpl requestMethod = new RequestMethodImpl();
//		requestMethod.setName(MethodConstants.METH_SUGGEST);
//
//		requestMethod.setAttributes(attributes);
//		return requestMethod;
//
//	}

	/**
	 * @return
	 */
	private DataObject createDataObject() {
		return new SuggestObject();
	}

}
