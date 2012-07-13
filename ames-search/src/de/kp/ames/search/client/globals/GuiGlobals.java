package de.kp.ames.search.client.globals;

import com.google.gwt.user.client.DOM;

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

public class GuiGlobals {

	/*
	 * Name of the anonymous user
	 */
	public static String ANONYMOUS_USER = "Guest";

	/*
	 * Main title of the application
	 */
	public static String APP_TITLE  = "AMES Web";
	
	public static int APP_TITLE_WIDTH = 80;

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
	public static int SUGESST_HEIGHT = 600;
	
}
