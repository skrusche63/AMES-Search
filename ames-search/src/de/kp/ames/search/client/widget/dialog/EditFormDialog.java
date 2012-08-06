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
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.handler.FormHandler;

public class EditFormDialog extends FormDialog implements FormHandler {
	/*
	 * Buttons labels
	 */
	private static String LABEL1 = GUIGlobals.BTN_SAVE_LABEL;
	private static String LABEL2 = GUIGlobals.BTN_CAN_LABEL;

	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 */
	public EditFormDialog(String title, String slogan) {
		super(title, slogan);
		/*
		 * Set overall title for editor
		 */
		this.setTitle(GUIGlobals.ADF_EDITOR);

	}

	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 * @param jValue
	 */
	public EditFormDialog(String title, String slogan, JSONValue jValue) {
		super(title, slogan, jValue);
		/*
		 * Set overall title for editor
		 */
		this.setTitle(GUIGlobals.ADF_EDITOR);
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.widget.base.BaseDialog#createButtons()
	 */
	public VLayout createButtons() {
		return createButtons(LABEL1, LABEL2);
	}
	
}
