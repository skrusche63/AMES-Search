package de.kp.ames.search.client.widget;

/**
 *	Copyright 2012 Dr. Krusche & Partner PartG
 *
 *	AMES-Web-GUI is free software: you can redistribute it and/or 
 *	modify it under the terms of the GNU General Public License 
 *	as published by the Free Software Foundation, either version 3 of 
 *	the License, or (at your option) any later version.
 *
 *	AMES- Web-GUI is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 *  See the GNU General Public License for more details. 
 *
 *	You should have received a copy of the GNU General Public License
 *	along with this software. If not, see <http://www.gnu.org/licenses/>.
 *
 */

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.handler.SearchHandler;
import de.kp.ames.search.client.widget.grid.SuggestGridImpl;

public class SearchWidget extends VLayout {

	private TextItem searchBox;

	/*
	 * The query string sent to the search service
	 */
	private String query;
	private SearchHandler searchHandler;
	
	private SuggestImpl suggest;

	/*
	 * The offset position of the search widget from the top right corner of the
	 * viewport
	 */
	private static int X_OFF = 392;
	private static int Y_OFF = 24;

	private boolean centered;
	private int x;
	private int y;

	private static int SEARCHBOX_WIDTH = 320;

	private static int WIDGET_WIDTH = 360;
	private static int WIDGET_HEIGHT = 28;

	public SearchWidget() {

		centered = true;
		reCenter();

		build(this.x, this.y);

	}

	public SearchWidget(int x, int y) {

		build(x, y);

	}

	private void build(int x, int y) {
		this.x = x;
		this.y = y;

		/*
		 * Appearance & Dimensions
		 */
		this.setWidth(WIDGET_WIDTH);
		this.setHeight(WIDGET_HEIGHT);

		this.setShowShadow(true);
		this.setShadowSoftness(2);

		this.setShadowOffset(1);
		this.addMember(createToolStrip());

		RootPanel root = RootPanel.get();
		root.add(this);

		// int cx = (int) (0.5 * root.getOffsetWidth() - WIDGET_WIDTH);
		// int cy = (int) (0.5 * root.getOffsetHeight() - WIDGET_HEIGHT);

		this.moveTo(x, y);
		this.draw();
	}

	private void reCenter() {
		/*
		 * The search widget is applied to the root panel, i.e. it is position
		 * on the viewport with respect to the offset position defined
		 */
		RootPanel root = RootPanel.get();

		x = (int) (0.5 * (root.getOffsetWidth() - WIDGET_WIDTH));
		// y = (int) (0.5 * (root.getOffsetHeight() - WIDGET_HEIGHT));
		y = 40; // for better debugging with FireBug

	}

	/**
	 * Set the search query
	 * 
	 * @param query
	 */
	public void setQuery(String query) {

		this.query = query;
		/*
		 * This invoke a change event
		 */
		this.searchBox.setValue(this.query);

	}

	/**
	 * Register search handler for search widget
	 * 
	 * @param searchHandler
	 */
	public void addSearchHandler(SearchHandler searchHandler) {
		this.searchHandler = searchHandler;
	}

	/**
	 * @param event
	 */
	public void afterResized(ResizedEvent event) {
		if (isCentered())
			reCenter();

		this.moveTo(x, y);
		this.draw();

		// reposition grid
		// TODO: positioning in a way that change of browser zoom, will not destroy layout?
		int gx = this.getAbsoluteLeft() + 39;
		int gy = this.getAbsoluteTop() - 14;
		
		suggest.moveTo(gx, gy);
		
		// dynamic height (does not work in Firefox 3.6 yet)
//		suggest.setHeight(RootPanel.get().getOffsetHeight() - y - 100);
		
	}

	private boolean isCentered() {
		// TODO Auto-generated method stub
		return this.centered;
	}

	private void afterChanged(ChangedEvent event) {
		TextItem item = (TextItem) event.getItem();
		String val = item.getValueAsString();
		RootPanel root = RootPanel.get();

		if (suggest != null) {
			root.remove(suggest);
			
			suggest.destroy();
			suggest = null;

		}
		
		if (val == null || val.length() == 0 )
			return;
		
		// rebuild grid for every suggest, to stabilize expanded grouping
		suggest = new SuggestImpl(val);
		
		root.add(suggest);
		
		// TODO: positioning in a way that change of browser zoom, will not destroy layout?
		int x = this.getAbsoluteLeft() + 39;
		int y = this.getAbsoluteTop() - 14;
		
		suggest.moveTo(x, y);
		
		// dynamic height (does not work in Firefox 3.6 yet)
//		suggest.setHeight(root.getOffsetHeight() - y - 100);

		/*
		 * The content of the searchbox has changed, so initiate another search
		 */
		// doSearch(rec);
	}

	/**
	 * @param url
	 * @param params
	 * @param fields
	 * @return
	 */
	private ToolStrip createToolStrip() {

		ToolStrip ts = new ToolStrip();
		ts.setStyleName("x-searchbox");

		ts.setWidth100();
		ts.setHeight(WIDGET_HEIGHT);

		/*
		 * The textbox is used to display the name field of the respective data
		 * source
		 */
		searchBox = new TextItem();

		// setValueField may be conflict with criteria on the same field name
		// doubled
		// searchBox.setValueField(JsonConstants.J_QUERY);

		searchBox.setTitle("<b>search</b>:");
		searchBox.setWidth(SEARCHBOX_WIDTH);

		searchBox.addChangedHandler(new ChangedHandler() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.smartgwt.client.widgets.form.fields.events.ChangedHandler
			 * #onChanged
			 * (com.smartgwt.client.widgets.form.fields.events.ChangedEvent)
			 */
			public void onChanged(ChangedEvent event) {
				afterChanged(event);
			}

		});

		/*
		 * A dynamic form is used as a wrapper to get the search box centered in
		 * height
		 */

		DynamicForm form = new DynamicForm();

		form.setTitleSuffix(""); // default ":"
		form.setWidth100();

		form.setMargin(3);
		form.setFields(searchBox);

		ts.addMember(form);
		return ts;

	}

	/**
	 * @param record
	 */
	private void doSearch(ListGridRecord record) {

		if (this.searchHandler == null) {
			/*
			 * The selected application is not searchable
			 */
			SC.say(GUIGlobals.APP_TITLE + ": Search Error", "The current application is not searchable.");

		} else {
			/*
			 * Initiate search request
			 */
			String query = record.getAttributeAsString(JsonConstants.J_TERM);
			this.searchHandler.doSearch(query);

		}

	}




}
