package de.kp.ames.search.client.widget;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiGlobals;
import de.kp.ames.search.client.widget.grid.SuggestGridImpl;

public class SuggestImpl extends VLayout {
	
	/**
	 * Constructor depends on search query 
	 * 
	 * @param query
	 */
	public SuggestImpl(String query) {

		this.setWidth(GuiGlobals.SUGGEST_WIDTH);
		this.setHeight(GuiGlobals.SUGESST_HEIGHT);

		/*
		 * This is an essential feature to ensure
		 * proper scrollbars, i.e vertical ones only
		 */
		this.setOverflow(Overflow.AUTO);

		SuggestGridImpl grid = new SuggestGridImpl(query);		
		this.addMember(grid);
		
	};

}
