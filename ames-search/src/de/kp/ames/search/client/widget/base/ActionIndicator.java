package de.kp.ames.search.client.widget.base;
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

public class ActionIndicator {

	/*
	 * Reference to Message Window
	 */
	private ActionMessage loading;
	private static ActionIndicator instance = new ActionIndicator();
	
	/**
	 * Constuctor
	 */
	private ActionIndicator() {}
	
	/**
	 * @return
	 */
	public static ActionIndicator getInstance() {
		if (instance == null) instance = new ActionIndicator();
		return instance;
	}
	
	/**
	 * Open message window
	 * 
	 * @param text
	 */
	public void open(String text) {
		if (loading != null) loading.destroy();
		loading = new ActionMessage(text);
	}
	
	/**
	 * Reset message window
	 */
	public void reset() {
		if (loading != null) loading.destroy();
		loading = null;
	}
}
