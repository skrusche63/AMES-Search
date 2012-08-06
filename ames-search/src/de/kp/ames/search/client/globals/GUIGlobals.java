package de.kp.ames.search.client.globals;
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

public class GUIGlobals {

	/*
	 * AMES semantic search specific
	 */
	public static String SEARCH_URL_ID  = "urn:de:kp:search:url";	
	public static String SEARCH_URL = DOM.getElementById(SEARCH_URL_ID).getAttribute("content");
	// set search use case by source: wn or scm 
	public static String SEARCH_SOURCE_ID  = "urn:de:kp:search:source";	
	public static String SEARCH_SOURCE = DOM.getElementById(SEARCH_SOURCE_ID).getAttribute("content");

	public static String BROADER_LABEL = SEARCH_SOURCE=="wn" ? "Hypernym" : "Context";

	public static final String CHECKOUT_C_TITLE = "Checkout";
	public static final String CHECKOUT_C_SLOGAN = "Add a comment for your checkout";

	/*
	 * AMES FncGlobals imported
	 */
	public static String ADF_EDITOR = "ADF - Editor";
	public static String ADF_VIEWER = "ADF - Viewer";

	
	
	/*
	 * Widget Dimensions
	 */
	public static int SUGGEST_WIDTH  = 420;
	public static int SUGGEST_HEIGHT = 600;
	
	/*
	 * Name of the anonymous user
	 */
	public static String ANONYMOUS_USER = "Guest";

	/*
	 * Main title of the application
	 */
	public static String APP_SLOGAN = "Share and visualize knowledge through the web.";
	public static String APP_TITLE  = "AMES SCM";
	
	public static int APP_TITLE_WIDTH = 80;

	/*
	 * Button labels
	 */	
	public static String BTN_APPLY_LABEL  = "Apply";
	public static String BTN_CAN_LABEL    = "Cancel";
	public static String BTN_CREATE_LABEL = "Create";
	public static String BTN_LOGIN_LABEL  = "Login";
	public static String BTN_OK_LABEL     = "Ok";
	public static String BTN_SAVE_LABEL   = "Save";

	/*
	 * DOM identifier
	 */
	public static String SPLASH_ID = "x-splash";
		
	/*
	 * Default dimensions of button
	 */
	public static int BTN_WIDTH  = 80;
	public static int BTN_HEIGHT = 24;

	/*
	 * Default dimensions of a dialog
	 */
	public static int DIALOG_DIM = 360;
	/*
	 * Default dimensions of a viewer
	 */
	public static int VIEWER_DIM = 640;
	
	/*
	 * Icon Directory & suffix
	 */
	public static String ICON_DIR    = "icons/16/";
	public static String ICON_SUFFIX = ".png";
	
}
