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

public class CoreGlobals {

	public static String HISTORY_TOKEN = "historyToken";
	
	/*
	 * Base Url for the AMES Web Service(s)
	 */
	public static String BASE_URL = "http://localhost:8080";

	/*
	 * Connection Parameters
	 */
	public static int CONNECTION_TIMEOUT = 180000;

	/*
	 * Definition of Guest parameters
	 */
	public static String GUEST_ID   = "urn:de:kp:guest:user";
	public static String GUEST_NAME = "Anonymous";

	public static String GUEST_ROLE = "Registry Guest";
	
	/* 
	 * Property (in index.html) that describes the URL to the
	 * AMES Web Service
	 */

	public static String REG_ID  = "urn:de:kp:reg:url";	
	public static String REG_URL = DOM.getElementById(REG_ID).getAttribute("content");

	/*
	 * Property (in index.html) that describes the URL to the WMS Service
	 */
	public static String WMS_ID  = "urn:de:kp:wms:url";
	public static String WMS_URL = DOM.getElementById(WMS_ID).getAttribute("content");

	/*
	 * Property (in index.html) to distinguish between showcase or operational use
	 */
	public static String SHOWCASE_FLAG_ID  = "urn:de:kp:showcase";
	public static boolean SHOWCASE_FLAG = Boolean.valueOf(DOM.getElementById(SHOWCASE_FLAG_ID).getAttribute("content"));

}
