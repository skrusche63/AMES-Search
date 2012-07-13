package de.kp.ames.search.client.widget;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class SearchWidget extends VLayout {

	private TextItem searchBox;

	/*
	 * The query string sent to the search service
	 */
	private String query;
	
	/*
	 * Reference to SuggestImpl
	 */
	private SuggestImpl suggestor;

	/*
	 * Indicates centered positioning
	 */
	private boolean top = false;

	/*
	 * Absolute position of search widget
	 */
	private int x;
	private int y;

	/*
	 * Predefined dimensions
	 */
	private static int SEARCHBOX_WIDTH = 320;

	private static int WIDGET_WIDTH = 360;
	private static int WIDGET_HEIGHT = 28;

	/**
	 * Constructor
	 */
	public SearchWidget() {

		calculateCenterPosition();

		setupWidget(this.x, this.y);

	}

	private void setupWidget(int x, int y) {
		
		/*
		 * Register coordinates
		 */
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

		/*
		 * Locate widget
		 */
		setWidget();
		
	}

	/**
	 * Calculate center position
	 */
	private void calculateCenterPosition() {
		/*
		 * The search widget is applied to the root panel, i.e. it is position
		 * on the viewport with respect to the offset position defined
		 */
		RootPanel root = RootPanel.get();

		x = (int) (0.5 * (root.getOffsetWidth() - WIDGET_WIDTH));
		// y = (int) (0.5 * (root.getOffsetHeight() - WIDGET_HEIGHT));
		y = 80; // for better debugging with FireBug

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
	 * @param event
	 */
	public void afterResized(ResizedEvent event) {
		
		/*
		 * Locate widget
		 */
		setWidget();

		/*
		 * Locate suggestor
		 */
		setSuggestor();
		
	}

	/**
	 * A helper method to create suggestor from TextItem value
	 * 
	 * @param event
	 */
	private void afterChanged(ChangedEvent event) {
		
		/*
		 * Retrieve search query from text item
		 */
		TextItem item = (TextItem) event.getItem();
		String val = item.getValueAsString();

		/*
		 * Do no term suggest in case of empty value
		 */
		if (val == null || val.length() == 0 )
			return;

		/*
		 * build suggestor
		 */
		buildSuggestor(val);
		
		/*
		 * position suggestor
		 */
		setSuggestor();

	}

	/**
	 * A helper method to remove the suggestor
	 */
	private void buildSuggestor(String val) {

		RootPanel rp = RootPanel.get();
		
		if (suggestor != null) {
			rp.remove(suggestor);
			
			suggestor.destroy();
			suggestor = null;

		}

		suggestor = new SuggestImpl(val);		
		rp.add(suggestor);

	}
	
	public void removeSuggestor() {

		RootPanel rp = RootPanel.get();
		
		if (suggestor != null) {
			rp.remove(suggestor);
			
			suggestor.destroy();
			suggestor = null;

		}
	}
	
	public void moveToTop() {
		
		this.top = true;
		this.y = 3;
		this.setWidget();
	}
	
	/**
	 * Locate suggestor
	 */
	private void setSuggestor() {
		
		// TODO: positioning in a way that change of browser zoom, will not destroy layout?

		int x = this.getAbsoluteLeft() + 39;
		int y = this.getAbsoluteTop()  - 14;
		
		suggestor.moveTo(x, y);

	}
	
	/**
	 * Locate widget
	 */
	private void setWidget() {

		calculateCenterPosition();
		
		if (top == true) this.y = 3; 

		this.moveTo(x, y);
		this.draw();

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

		searchBox = new TextItem();

		searchBox.setTitle("<b>search</b>:");
		searchBox.setWidth(SEARCHBOX_WIDTH);

		searchBox.addChangedHandler(new ChangedHandler() {
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

}
