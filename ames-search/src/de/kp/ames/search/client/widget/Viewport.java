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

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

import de.kp.ames.search.client.control.MainController;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.globals.GuiStyles;

public class Viewport extends VLayout {
	
	private static String TITLE = GUIGlobals.APP_TITLE;
	
	/* 
	 * A reference to the user label to enable change 
	 * and also provisioning of additional information
	 */
	
	private Label user;
	
	private static int TOP_HEIGHT = 32;
	
	public Viewport() {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_0);
		
		this.setWidth100();
		this.setHeight100();

		this.setBackgroundColor(GuiStyles.BG_COLOR);
		this.setOverflow(Overflow.HIDDEN);
		
		/*
		 * Create toolstrip
		 */
		this.addMember(createToolStrip());
		
		/*
		 * Create place holder
		 */
		this.addMember(createPlaceHolder());
		
		/*
		 * Resized events
		 */
		this.addResizedHandler(new ResizedHandler() {
			public void onResized(ResizedEvent event) {
				MainController.getInstance().afterResized(event);
			}
			
		});

	}
	
	/**
	 * Create Placeholder
	 * 
	 * @return
	 */
	private VLayout createPlaceHolder() {
		
		VLayout placeHolder = new VLayout();

		placeHolder.setShowEdges(false);
		placeHolder.setWidth100();
		placeHolder.setHeight100();
		
		return placeHolder;

	}

	/**
	 * Create Toolstrip
	 * 
	 * @return
	 */
	private ToolStrip createToolStrip() {
		
		ToolStrip ts = new ToolStrip();
		ts.setStyleName(GuiStyles.X_HEADER);
		
		ts.setWidth100();
		ts.setHeight(TOP_HEIGHT);
		
		ts.addSpacer(5);
		
		/* 
		 * Application Logo
		 */
		
		ImgButton logo = new ImgButton();
		logo.setSrc(GuiStyles.LOGO_IMAGE);
		
		logo.setWidth(16);
		logo.setHeight(16);

	    logo.setPrompt(TITLE);
	    logo.setHoverStyle(GuiStyles.X_IMAGEHOVER);
	    
	    logo.setShowRollOver(false);
	    logo.setShowDownIcon(false);

	    logo.setShowDown(false);

		logo.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				doLogo(event);
			}
		});
		
		ts.addMember(logo);
		ts.addSpacer(5);

		/*
		 * Application title
		 */

	    Label title = new Label(TITLE);
	    title.setStyleName(GuiStyles.X_HEADLINE);

	    title.setWidth(GUIGlobals.APP_TITLE_WIDTH);
	    ts.addMember(title);

	    ts.addFill();

	    /*
	     * User label
	     */

	    user = new Label(GUIGlobals.ANONYMOUS_USER);
	    user.setStyleName(GuiStyles.X_USER);

	    user.setWidth(160);
	    user.setOverflow(Overflow.HIDDEN);
	    
	    ts.addMember(user);
	    ts.addSpacer(10);
	    
	    
//	    /*
//	     * Search image button
//	     */
//
//	    ImgButton search = new ImgButton();
//	    search.setSrc(GuiStyles.SEARCH_IMAGE);
//	    
//	    search.setWidth(16);
//	    search.setHeight(16);
//	    
//	    search.setShowRollOver(false);
//	    search.setShowDownIcon(false);
//
//	    search.setShowDown(false);
//
//	    search.setShowFocused(false);
//	    search.setShowFocusedIcon(false);
//
//	    search.addClickHandler(new ClickHandler() {
//	        public void onClick(ClickEvent event) {
//	        	doSearch(event);
//	        }
//	    });
//
//	    ts.addMember(search);
//	    ts.addSpacer(15);

		return ts;
		
	}

	public void setUser(String text) {		
		user.setContents(text);
		user.redraw();
	}

	/**
	 * Reload desktop 
	 * 
	 * @param e
	 */
	private void doLogo(ClickEvent e) {
//		MainController.getInstance().createApp(FncGlobals.FNC_APP_ID_Desktop);		
	}
	
	private void doSearch(ClickEvent e) {
		MainController.getInstance().toggleSearch();
	}


}
