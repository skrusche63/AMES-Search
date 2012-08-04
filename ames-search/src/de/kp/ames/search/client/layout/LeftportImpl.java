package de.kp.ames.search.client.layout;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.style.GuiStyles;


public class LeftportImpl extends VLayout {

	/**
	 * Constructor
	 */
	public LeftportImpl() {
		
		/*
		 * Dimensions
		 */
		this.setWidth(GuiStyles.LEFT_LINE_WIDTH);
		this.setHeight100();
		
		this.setShowEdges(false);

		/*
		 * Style
		 */
		this.setStyleName(GuiStyles.X_BD_STYLE_3);
		this.setBackgroundColor(GuiStyles.LEFT_LINE_BG);
		
	};
	
}
