package de.kp.ames.search.client.globals;
/**
 * Copyright 2012. All rights reserved by Dr. Krusche & Partner PartG
 * Please contact: team@dr-kruscheundpartner.de
 */

import com.google.gwt.user.client.DOM;

public class GuiGlobals {

	/*
	 * Connection Parameters
	 */
	public static int CONNECTION_TIMEOUT = 180000;

	/*
	 * DOM identifier
	 */
	public static String SPLASH_ID = "x-splash";
	
	/* 
	 * Property (in index.html) that describes the URL to the
	 * AMES Web Service
	 */
	public static String SEARCH_ID  = "urn:de:kp:search:url";	
	public static String SEARCH_URL = DOM.getElementById(SEARCH_ID).getAttribute("content");

	/*
	 * Widget Dimensions
	 */
	public static int SUGGEST_WIDTH  = 420;
	public static int SUGGEST_HEIGHT = 600;
	
}
