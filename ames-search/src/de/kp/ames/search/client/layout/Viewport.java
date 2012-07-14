package de.kp.ames.search.client.layout;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

import de.kp.ames.search.client.control.MainController;
import de.kp.ames.search.client.globals.GuiStyles;

public class Viewport extends VLayout {
	
	private static int TOP_HEIGHT = 56;
	
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
		
		ts.addFill();
		
		ts.addSpacer(5);
				
		ImgButton logo = new ImgButton();
		logo.setSrc(GuiStyles.LOGO_IMAGE);
		
		logo.setWidth(256);
		logo.setHeight(56);

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

		return ts;
		
	}

	/**
	 * Reload desktop 
	 * 
	 * @param e
	 */
	private void doLogo(ClickEvent e) {
		// Do nothing
	}

}
