package de.kp.ames.search.client;

/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.core.client.EntryPoint;

import de.kp.ames.search.client.control.MainController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class main implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	 public void onModuleLoad() {
	 MainController.getInstance().createWelcome();
	 // MainController.getInstance().createTestCase();
	
	 }

//	public void onModuleLoad() {
//
////		RPCManager.setPromptStyle(PromptStyle.CURSOR);
//
//		VLayout topWrapper = new VLayout();
//		topWrapper.setOverflow(Overflow.AUTO);
//		topWrapper.setWidth100();
//		topWrapper.setHeight100();
//		
//		HLayout threecols = new HLayout();
//		threecols.setWidth100();
//		threecols.setHeight100();
//		
//		VLayout leftport = new VLayout();
//		leftport.setWidth(GuiStyles.LEFT_LINE_WIDTH);
//		leftport.setHeight100();
//		leftport.setShowEdges(false);
//		leftport.setStyleName(GuiStyles.X_BD_STYLE_3);
//		leftport.setBackgroundColor(GuiStyles.LEFT_LINE_BG);
//
//		VLayout rightport = new VLayout();
//		rightport.setWidth(GuiStyles.RIGHT_LINE_WIDTH);
//		rightport.setHeight100();
//		rightport.setShowEdges(false);
//		rightport.setStyleName(GuiStyles.X_BD_STYLE_4);
//		rightport.setBackgroundColor(GuiStyles.RIGHT_LINE_BG);
//
//		
//		VLayout centerport = new VLayout();
//		// from de.kp.ames.search.client.layout.CenterportImpl.CenterportImpl()
//		centerport.setWidth(GuiStyles.CENTER_LINE_WIDTH);
//		centerport.setHeight100();
//		centerport.setShowEdges(false);
//		centerport.setStyleName(GuiStyles.X_BD_STYLE_4);
//		// from de.kp.ames.search.client.widget.ResultPortImpl
//		centerport.setOverflow(Overflow.HIDDEN);
//
//		VLayout suggestFeedback = new VLayout();
//		suggestFeedback.setHeight("15%");
//
//		Canvas searchResult = new SuggestImpl("hy");
////		ListGrid searchResult = new SuggestGridImpl("hy");
////		ListGrid searchResult = new SearchResultGridImpl("paul");
//		searchResult.setHeight("85%");
//
//		suggestFeedback.setShowResizeBar(true);
//
//		centerport.setMembers(suggestFeedback, searchResult);
//
//		threecols.setMembers(leftport, centerport, rightport);
//		
//		topWrapper.addMember(threecols);
//
//		topWrapper.draw();
//	}
}
