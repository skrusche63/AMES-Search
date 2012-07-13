package de.kp.ames.search.client.control;
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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.events.DrawEvent;
import com.smartgwt.client.widgets.events.DrawHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.widget.SearchWidget;
import de.kp.ames.search.client.widget.Viewport;


/**
 * @author Stefan Krusche (krusche@dr-kruscheundpartner.de)
 *
 */
public class MainController {

	private static MainController instance = new MainController();
	
	/* 
	 * The viewport for the application
	 */
	private VLayout container;
	private Viewport viewport;
	
//	/*
//	 * Reference to the selected application
//	 */
//	private BaseApp selectedApp;
	
	/*
	 * Reference to the SearchWidget
	 */
	private SearchWidget searchWidget;
		
	/*
	 * Reference to the search query
	 */
	private String searchQuery;
	
//	/*
//	 * Search (result) handler
//	 */
//	private ISearch searchHandler;
	
	/**
	 * Constructor
	 */
	private MainController() {}
	
	public static MainController getInstance() {		
		if (instance == null) instance = new MainController();
		return instance;
	}
	
	/**
	 * Create introduction viewport when
	 * starting the AMES Web application
	 */
	public void createWelcome() {
		
		/* 
		 * Remove the initial splash screen
		 */
		Element splash = DOM.getElementById(GuiGlobals.SPLASH_ID);
		DOM.removeChild(RootPanel.getBodyElement(), splash);
		
		/*
		 * Create viewport
		 */
		createViewport();
		
		
		/*
		 * Create search widget
		 */
		// delayed
//		createSearchWidget();
			
	}
	
	public void createSearchWidget() {
		
		openSearch();
//		SC.say("SearchWidget left: " + searchWidget.getAbsoluteLeft() + " root width: " + RootPanel.get().getOffsetWidth());
		
	}
	
	/**
	 * A helper method to append a selected application
	 * to the viewport, depending on the specific profile
	 * 
	 * @param profile
	 */
	public void createApp(String profile) {
		
//		/*
//		 * All apps are derived from an HLayout
//		 */
//		BaseApp app = null;		
//		if (profile.equals(FncGlobals.FNC_APP_ID_Bulletin)) {
//			app = new BulletinImpl();
//
//		} 
//
//		if (app == null) return;
//
//		/*
//		 * Append selected app
//		 */
//		replaceApp(app);
		
	}
	
	
	/**
	 * A helper method to create the viewport
	 */
	public void createViewport() {

		/*
		 * Build view port of the application
		 */
		container = new VLayout();
		container.setShowEdges(false);
		
		container.setWidth100();
		container.setHeight100();

		container.setOverflow(Overflow.HIDDEN);
				
		container.addDrawHandler(new DrawHandler() {
			
			@Override
			public void onDraw(DrawEvent event) {
				createSearchWidget();
			}
		});
		
		
		viewport = new Viewport();
		container.addMember(viewport);
		
		container.draw();
		

	}

//	/**
//	 * A helper method to replace the actual
//	 * app with a new app
//	 * 
//	 * @param newApp
//	 */
//	public void replaceApp(BaseApp newApp) {
//
//		/*
//		 * Remove existing application from viewport;
//		 * take into account that apps are always
//		 * wrapped by vertical layouts
//		 */
//		
//		/*
//		 * Call application specific functionality
//		 * before it is removed from the viewport
//		 */
//		if (selectedApp != null) selectedApp.beforeRemove();
//		viewport.removeMember(viewport.getMember(1));
//		
//		/*
//		 * Register new application as selected app
//		 */
//		selectedApp = newApp;
//		
//		/* 
//		 * The wrapper is essential to get the vertical 
//		 * scrollbar right in case of many portlets
//		 */
//		VLayout newWrapper = new VLayout();
//		newWrapper.setOverflow(Overflow.AUTO);
//		
//		newWrapper.addMember(newApp);		
//		viewport.addMember(newWrapper);
//
//		container.draw();
//
//	}
	
	/**
	 * This method controls all actions that have to be
	 * taken after the main application as changed its size
	 * 
	 * @param event
	 */
	public void afterResized(ResizedEvent event) {		
		if (searchWidget != null) searchWidget.afterResized(event);		
	}
	
//	/**
//	 * Set current application that acts as a
//	 * response handler for search result
//	 * 
//	 * @param handler
//	 */
//	public void setSearchHandler(ISearch handler) {
//		this.searchHandler = handler;
//	}
	
	/**
	 * Search query term for 'suggest' search
	 * 
	 * @param query
	 */
	public void setSearchQuery(String query) {
		this.searchQuery = query;
	}
	
	/**
	 * Close search widget and remove from
	 * root panel
	 */
	public void closeSearch() {
		
		RootPanel.get().remove(searchWidget);
		
		searchWidget.destroy();
		searchWidget = null;
		
	}
	
	/**
	 * Method to open (and show) the search widget
	 */
	public void openSearch() {
		
		if (searchWidget != null) closeSearch();
		
		/*
		 * Setup search widget
		 */
		searchWidget = new SearchWidget();
		
//		searchWidget.setQuery(this.searchQuery);
//		searchWidget.setHandler(this.searchHandler);

	}
	
	/**
	 * Toggle visibility of search widget
	 */
	public void toggleSearch() {

		if (searchWidget == null) 
			openSearch();
		
		else
			closeSearch();
		
	}
}
