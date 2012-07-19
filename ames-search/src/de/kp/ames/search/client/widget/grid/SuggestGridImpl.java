package de.kp.ames.search.client.widget.grid;

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.EventHandler;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.KeyDownEvent;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.MouseDownEvent;
import com.smartgwt.client.widgets.events.RightMouseDownEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.BodyKeyPressEvent;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

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

		this.setHeight(1);
		this.setWidth100();

		this.setBodyOverflow(Overflow.VISIBLE);
		this.setOverflow(Overflow.VISIBLE);

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

		// this.setGroupStartOpen(GroupStartOpen.ALL);
		// this.setGroupByField(JsonConstants.J_HYPERNYM);

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
			
		} else if (key.equals("Arrow_Down") || key.equals("Arrow_Up")) {
			
//			Integer focusRow = this.getFocusRow();
//			Integer selectedRow = this.getRecordIndex(this.getSelectedRecord());
//			
//			SC.logWarn("======> KeyDown focusRow:" + focusRow + " selectedRow:" + selectedRow);
//			if (focusRow == null) focusRow = 1;
			
//			this.selectSingleRecord(focusRow);
		}

		


	}

	public void afterSelectionChanged(SelectionEvent event) {
		
		/*
		 * react on state=select only
		 */
		if (!event.getState()) return;

		Record selected = event.getSelectedRecord();
		Record record = this.getSelectedRecord();
		String key = EventHandler.getKey();

		SC.logWarn("====> SelectionChanged ev.selected " +
				"record: <" + selected.getAttributeAsString("qsraw") + "> " + 
				" id> " + selected.getAttributeAsString(JsonConstants.J_ID) + 
				" focus> " + this.getFocusRow() 
				);
		
		SC.logWarn("====> SelectionChanged ts.selected " +
				"record: <" + record.getAttributeAsString("qsraw") + "> " + 
				" id> " + record.getAttributeAsString(JsonConstants.J_ID) + 
				" #>" + this.getRecordIndex(record) + 
				" key> " + key
				);
		
		int index = this.getRecordIndex(selected);

		if (selected.getAttributeAsString("type").equals("group")) {
			SC.logWarn("======> SelectionChanged stepped on group header");
			/*
			 * pseudo record group header detected 
			 * Assertions: 
			 * - there must be a next record 
			 * - next record cannot be a group header
			 */

			if (key == "Arrow_Down") {
				// way down
				if (index + 1 < this.getRecords().length) {
					// not the last one
					this.selectSingleRecord(index + 1);
										
//					SC.logWarn("======> SelectionChanged Arrow_Down move down to> " + (index + 1));
					return;

				}
			} else if (key == "Arrow_Up") {
				// way up
				if (index != 0) {
					// not the first one
					this.selectSingleRecord(index - 1);
					
//					SC.logWarn("======> SelectionChanged Arrow_Up move up to> " + (index - 1));
					return;
					
				} else {
					// move selection back to first suggest record, 
					// don't select group header
					this.selectSingleRecord(1);

					// move focus up to text widget
					SuggestController.getInstance().focusToSearchBox();
					SC.logWarn("======> SelectionChanged Arrow_Up move upto TextWidget> " + key);

				}
			} else {
				SC.logWarn("======> RecordClick mover ??> " + key);
			}

//			/*
//			 * check result selected record
//			 */
//			Record selectedCheck = this.getSelectedRecord();
//			SC.logWarn("======> SelectionChanged selected " +
//					"record: <" + selectedCheck.getAttributeAsString("qsraw") + "> " + 
//					" #>" + this.getRecordIndex(selectedCheck) + 
//					" id> " + selectedCheck.getAttributeAsString(JsonConstants.J_ID)  + 
//					" focus> " + this.getFocusRow());
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
	 * KeyPress is called after a KeyDown KeyPress.getKeyName() results in a
	 * possible combination of keys, such as Shift + a => A KeyPress is not
	 * called for Shift, Ctrl, Alt, F1 - F12, Arrow keys
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.widget.grid.GridImpl#afterKeyPress(com.smartgwt
	 * .client.widgets.events.KeyPressEvent)
	 */
	public void afterKeyPress(KeyPressEvent event) {

		// has: event.cancel();
		String key = event.getKeyName();

		SC.logWarn("====> KeyPress pressed: " + key);

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
		Record selected = this.getSelectedRecord();

		SC.logWarn("====> RecordDoubleClick: event record : <" + record.getAttributeAsString("qsraw") + "> " + " id> "
				+ record.getAttributeAsString(JsonConstants.J_ID));

		SC.logWarn("======> RecordDoubleClick: selected record : <" + selected.getAttributeAsString("qsraw") + "> "
				+ " id> " + selected.getAttributeAsString(JsonConstants.J_ID));

		if (this.recordHandler == null) {
			SC.say("afterRecordClick: 1");
			return;
		}

		this.recordHandler.doSelect(record);
		SC.logWarn("======> RecordDoubleClick: event record search triggered");

	}

	/*
	 * CellClick is called after mouse click into cell and resolution is on
	 * row/col level CellClick is followed by a RecordClick which can process
	 * the result on record level
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.kp.ames.search.client.widget.grid.GridImpl#afterCellClick(com.smartgwt
	 * .client.widgets.grid.events.CellClickEvent)
	 */
	public void afterCellClick(CellClickEvent event) {
		// has: event.cancel();

		Record record = event.getRecord();
		Record selected = this.getSelectedRecord();		

		SC.logWarn("====> CellClick event col: " + event.getColNum() + " row:" + event.getRowNum());
		SC.logWarn("======> CellClick  event recid> " + record.getAttributeAsString(JsonConstants.J_ID));
		SC.logWarn("======> CellClick  selected recid> " + selected.getAttributeAsString(JsonConstants.J_ID));
	}

	public void afterBodyKeyPress(BodyKeyPressEvent event) {
		String key = EventHandler.getKey();
		SC.logWarn("====> BodyKeyPress: " + key);

		event.cancel();
	}

	public void afterRightMouseDown(RightMouseDownEvent event) {
		SC.logWarn("====> RightMouseDown: ");
	}
	
	public void afterRowContextClick(RowContextClickEvent event) {
		Record record = event.getRecord();
		int row = event.getRowNum();
		SC.logWarn("====> RowContextClick: event recid> " + record.getAttributeAsString(JsonConstants.J_ID) + " row: " + row);
	}

	public void afterMouseDown(MouseDownEvent event) {
		SC.logWarn("====> MouseDown: ");
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

	public native static void setStartRow(int row, JavaScriptObject self) /*-{
		self.selection.startRow = row;
		self.selection.lastRow = row;
		self.selection.test = "hallo";
	}-*/;
	
}
