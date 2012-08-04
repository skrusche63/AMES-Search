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

import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.style.GuiStyles;


/**
 * This window is used to give user feedback in case
 * of longer lasting actions, i.e. reading from the
 * OASIS ebXML RegRep
 */

public class ActionMessage extends Window {
	
	/**
	 * Constructor requires message text
	 * 
	 * @param text
	 */
	public ActionMessage(String text) {

		VLayout vLayout = new VLayout();
		vLayout.setShowEdges(false);
		
		vLayout.setWidth100();
		vLayout.setHeight100();
		
		vLayout.setMembersMargin(0);
		vLayout.setLayoutMargin(0);

		StringBuffer sb = new StringBuffer();
		
		sb.append("<img style=\"margin:16px;margin-top:24px;margin-left:auto;margin-right:auto;display:block;\" ");
		sb.append("src=\"" + GuiStyles.LOADING_IMAGE + "\" width=\"64\" height=\"64\" />");
		
		sb.append("<p style=\"font:normal 11px tahoma, arial, helvetica, sans-serif;color:" + GuiStyles.FONT_COLOR + ";text-align:center;\">" + text + "</p>");

		HTMLFlow flow = new HTMLFlow();
		flow.setContents(sb.toString());
		
		vLayout.addMember(flow);
		this.addItem(vLayout);
		
		this.setTitle(GUIGlobals.APP_TITLE);
		
		this.setShowCloseButton(false);
		this.setShowMinimizeButton(false);

		this.setBodyColor(GuiStyles.BG_COLOR);

		this.setWidth(240);
		this.setHeight(160);
		
		this.centerInPage();
		this.draw();
		
	}
	
}
