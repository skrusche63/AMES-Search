package de.kp.ames.search.client.control;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.event.SuggestListener;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.JsonConstants;
import de.kp.ames.search.client.layout.Viewport;
import de.kp.ames.search.client.widget.ResultImpl;
import de.kp.ames.search.client.widget.SearchWidget;

public class MainController implements SuggestListener {

	private static MainController instance = new MainController();

	/*
	 * The viewport for the application
	 */
	private VLayout container;
	private Viewport viewport;

	/*
	 * Reference to the SearchWidget
	 */
	private SearchWidget searchWidget;

	private boolean isResultLayoutCreated;

	/**
	 * Constructor
	 */
	private MainController() {
		/*
		 * register listener
		 */
		SearchEventManager.getInstance().addSuggestListener(this);
		
	}

	public static MainController getInstance() {
		if (instance == null)
			instance = new MainController();
		return instance;
	}

	/**
	 * Create introduction viewport when starting the AMES Web application
	 */
	public void createWelcome() {

		/*
		 * Remove the initial splash screen
		 */
		Element splash = DOM.getElementById(GUIGlobals.SPLASH_ID);
		DOM.removeChild(RootPanel.getBodyElement(), splash);

		/*
		 * Create viewport
		 */
		createViewport();
		
		/*
		 * Create search widget
		 */
		createSearchWidget();
		
	}

	private void createSearchWidget() {
		openSearch();
	}

	/**
	 * A helper method to create the viewport
	 */
	public void createViewport() {

		container = new VLayout();
		container.setShowEdges(false);

		container.setWidth100();
		container.setHeight100();

		container.setOverflow(Overflow.HIDDEN);

		viewport = new Viewport();
		container.addMember(viewport);

		container.draw();

	}

	private void updateResult(Record suggestRecord) {
		SC.logWarn("======> MainController.updateResult");

		// send message to all listeners for update
		SearchEventManager.getInstance().doAfterSearchUpdate(suggestRecord);
	}
	
	private void createFirstTimeResult(Record suggestRecord) {

		SC.logWarn("======> MainController.createFirstTimeResult");

		/* 
		 * Remove placeholder
		 */
		viewport.removeMember(viewport.getMember(1));
		
		VLayout newWrapper = new VLayout();
		newWrapper.setOverflow(Overflow.AUTO);

		newWrapper.addMember(new ResultImpl(suggestRecord));
		viewport.addMember(newWrapper);

		container.draw();

		SC.logWarn("======> MainController.createFirstTimeResult END");
	}

	
	/**
	 * This method controls all actions that have to be taken after the main
	 * application as changed its size
	 * 
	 * @param event
	 */
	public void afterResized(ResizedEvent event) {
		if (getSearchWidget() != null)
			getSearchWidget().afterResized(event);
	}

	/**
	 * Close search widget and remove from root panel
	 */
	public void closeSearch() {

		RootPanel.get().remove(getSearchWidget());

		getSearchWidget().destroy();
		searchWidget = null;

	}

	/**
	 * Method to open (and show) the search widget
	 */
	public void openSearch() {

		if (getSearchWidget() != null)
			closeSearch();

		/*
		 * Setup search widget
		 */
		searchWidget = new SearchWidget();

	}

	/**
	 * Toggle visibility of search widget
	 */
	public void toggleSearch() {

		if (getSearchWidget() == null)
			openSearch();

		else
			closeSearch();

	}

	/**
	 * SuggestListener 
	 * Main method to establish after suggest handling
	 * 
	 * @param record
	 */
	@Override
	public void doAfterSuggest(Record record) {

		SC.logWarn("======> MainController.doAfterSuggest");

		SuggestController.getInstance().removeSuggestor();
		getSearchWidget().moveToTop();
		getSearchWidget().setQuery(record.getAttributeAsString(JsonConstants.J_QUERYRAWSTRING));
		
		if (!isResultLayoutCreated) {
			isResultLayoutCreated = true;
			createFirstTimeResult(record);
		} else {
			updateResult(record);
		}
	}

	/**
	 * @return the searchWidget
	 */
	public SearchWidget getSearchWidget() {
		return searchWidget;
	}


}
