package de.kp.ames.search.client.widget.dialog;
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

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.handler.RemoveHandler;
import de.kp.ames.search.client.style.GuiStyles;
import de.kp.ames.search.client.widget.base.ActionIndicator;
import de.kp.ames.search.client.widget.base.GUIBaseFactory;

/**
 * This class is the base window for a modal dialog;
 * it supports the default buttons (ok, cancel)
 */
public class BaseDialog extends Window implements RemoveHandler {

	protected String title;
	protected String slogan;
	
	/*
	 * Reference to JSON based data that
	 * are essential for building a dialog
	 */
	protected JSONValue jValue;

	private IButton b1;
	private IButton b2;
	
	/*
	 * Button dimensions
	 */
	private static int BTN_WIDTH  = GUIGlobals.BTN_WIDTH;
	private static int BTN_HEIGHT = GUIGlobals.BTN_HEIGHT;

	/*
	 * Default labels of dialog window
	 */
	private static String LABEL1 = GUIGlobals.BTN_OK_LABEL;
	private static String LABEL2 = GUIGlobals.BTN_CAN_LABEL;
	
	/*
	 * Border styles
	 */
	private static String BORDER1 = GuiStyles.X_BD_STYLE_1;

	/*
	 * Default dimensions
	 */
	private static int DIM = GUIGlobals.DIALOG_DIM;
	
	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 */
	public BaseDialog(String title, String slogan) {
		
		/*
		 * Register title & slogan
		 */
		this.title = title;
		this.slogan = slogan;

		this.setupDialog();
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 * @param jValue
	 */
	public BaseDialog(String title, String slogan, JSONValue jValue) {

		/*
		 * Register title & slogan
		 */
		this.title = title;
		this.slogan = slogan;

		/*
		 * Register data
		 */
		this.jValue = jValue;
		this.setupDialog();
		
	}
	
	/**
	 * Setup dialog
	 */
	protected void setupDialog() {
		
		/*
		 * Layout & style
		 */
		this.setWidth(DIM);
		this.setHeight(DIM);
		
		this.setShowShadow(true);
		this.setShadowOffset(3);

		this.setShadowSoftness(10);				
		this.setBodyColor(GuiStyles.DIA_BG_COLOR);

		final BaseDialog self = this;		

		/*
		 * Close event
		 */
		this.addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				self.doBeforeClose(event);				
			}			
		});
		
		/*
		 * Create main structure
		 */
		VLayout body = this.createBody();
		this.addItem(body);

		this.centerInPage();
		this.draw();
		
	}
	
	/**
	 * Create main body of dialog with
	 * predefined layout structure
	 * 
	 * @return
	 */
	protected VLayout createBody() {

		VLayout vLayout = new VLayout();
		vLayout.setShowEdges(false);
		
		//vLayout.setWidth100();
		//vLayout.setHeight100();
		
		vLayout.setMembersMargin(0);
		vLayout.setLayoutMargin(0);
		
		/*
		 * Create headline
		 */
		VLayout headline = GUIBaseFactory.createHeadline(title, slogan);		
		vLayout.addMember(headline);
		
		/*
		 * Create content
		 */
		VLayout wrapper = new VLayout();
		
		wrapper.setLayoutMargin(0);
		wrapper.setStyleName(GuiStyles.X_BD_STYLE_2);

		//wrapper.setWidth100();		
		//wrapper.setHeight100();
		
		wrapper.addMember(createContent());
		vLayout.addMember(wrapper);
		
		/*
		 * Create buttons
		 */
		VLayout buttons = createButtons();
		vLayout.addMember(buttons);
		
		return vLayout;
		
	}
 	
	/**
	 * Method to assign content to dialog
	 * 
	 * @return
	 */
	protected Canvas createContent() {
		/*
		 * Must be overridden
		 */
		return null;
	}
	
	/**
	 * Method to create default dialog buttons
	 * 
	 * @return
	 */
	protected VLayout createButtons() {
		return createButtons(LABEL1, LABEL2);
	}
	
	/**
	 * Method to create customized dialog buttons
	 * 
	 * @param label1
	 * @param label2
	 * @return
	 */
	protected VLayout createButtons(String label1, String label2) {

		/*
		 * First button
		 */
		b1 = new IButton(label1);
		b1.setWidth(BTN_WIDTH);
		b1.setLayoutAlign(Alignment.CENTER);

		b1.addClickHandler(createB1ClickHandler());

		/*
		 * Second button
		 */
		b2 = new IButton(label2);
		b2.setWidth(BTN_WIDTH);
		b2.setLayoutAlign(Alignment.CENTER);

		/*
		 * Button rendering
		 */
		b2.addClickHandler(createB2ClickHandler());
		
		VLayout b1Layout = new VLayout();
		b1Layout.setWidth("50%");
		
		b1Layout.setLayoutAlign(Alignment.RIGHT);
		b1Layout.addMember(b1);

		VLayout b2Layout = new VLayout();
		b2Layout.setWidth("50%");
		b2Layout.addMember(b2);
		
		HLayout buttonLayout = new HLayout();
		buttonLayout.setHeight(BTN_HEIGHT + 16);

		buttonLayout.setWidth(180);
		buttonLayout.setMembersMargin(8);

		buttonLayout.setLayoutMargin(8);
		buttonLayout.setLayoutAlign(Alignment.CENTER);

		buttonLayout.addMember(b1Layout);
		buttonLayout.addMember(b2Layout);

		VLayout vLayout = new VLayout();

		vLayout.setStyleName(BORDER1);
		vLayout.setWidth("100%");
		
		vLayout.setHeight(40);
		vLayout.setBackgroundColor(GuiStyles.BTN_BG_COLOR);
		
		vLayout.addMember(buttonLayout);
		return vLayout;
		
	}
	
	/**
	 * @param event
	 */
	protected void doBeforeClose(CloseClickEvent event) {
		/*
		 * Initiate before remove processing
		 */
		this.beforeRemove();
		
		/*
		 * Destroy window
		 */
		this.destroy();
		
	}
	
	/**
	 * Click Handler for first button
	 * 
	 * @return
	 */
	protected ClickHandler createB1ClickHandler() {
		/*
		 * Must be overridden
		 */
		return null;
	}
	
	/**
	 * Click Handler for second button
	 * 
	 * @return
	 */
	protected ClickHandler createB2ClickHandler() {
		/*
		 * Must be overridden
		 */
		return null;
	}
	
	/**
	 * Set action indicator
	 * 
	 * @param mask
	 * @param text
	 */
	protected void setIndicator(String text) {
		ActionIndicator.getInstance().open(text);
	}

	/**
	 * Reset action indicator
	 */
	protected void resetIndicator() {
		ActionIndicator.getInstance().reset();		
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.handler.RemoveHandler#beforeRemove()
	 */
	public void beforeRemove() {
		/*
		 * Must be overridden
		 */		
	}
	
}
