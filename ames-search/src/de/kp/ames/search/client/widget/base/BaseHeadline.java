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

import com.smartgwt.client.widgets.HTMLPane;

import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.style.GuiStyles;

public class BaseHeadline extends HTMLPane {

	private String title;
	private String slogan;

	/**
	 * Constructor requires title and slogan
	 * 
	 * @param title
	 * @param slogan
	 */
	public BaseHeadline(String title, String slogan) {

		this.title = title;
		this.slogan = slogan;

		this.setStyleName(GuiStyles.X_TOPLINE);

		this.setShowEdges(false);

		this.setWidth100();
		this.setHeight(64);

		this.setHeadline(title, slogan);

	}

	/**
	 * Method to initially set title and slogan
	 * 
	 * @param title
	 * @param slogan
	 */
	public void setHeadline(String title, String slogan) {

		this.title = title;
		this.slogan = slogan;

		this.setContents(getHtml());

	}

	/**
	 * Method to change the title and keeping the slogan
	 * 
	 * @param title
	 */
	public void setHeading(String title) {

		this.title = title;

		this.setContents(getHtml());

	}

	/**
	 * A helper method to create the HTML content of the headline
	 * 
	 * @return
	 */
	private String getHtml() {

		// TODO: why does CoreGlobal get an undefined?!?
		// but same code in GUIGlobals not
		// SC.logWarn("======> BaselineHeadline.getHtml: " + CoreGlobals.SHOWCASE_FLAG);
		// SC.logWarn("======> BaselineHeadline.getHtml: " + GUIGlobals.SHOWCASE_FLAG);

		String html = "";

		// if (CoreGlobals.SHOWCASE_FLAG) { // does get undefined for flag?!?
		if (GUIGlobals.SHOWCASE_FLAG) {

			/*
			 * Showcase
			 */
			html = "<div class='x-topline'>";
			html += "<div style='padding:8px 0px 0px 8px;font-size:20px;vertical-align:top;'><b>" + this.title
					+ "</b><br/>";
			html += "<span style='padding:8px 0px 0px 2px;font-size:11px;'>" + this.slogan + "</span></div>";
			html += "</div";

		} else {

			/*
			 * Operational use case
			 */
			html = "<div class='x-topline'>";
			html += "<img src='" + GuiStyles.APP_ICON
					+ "' height='48' width='48' style='display:block;float:left;margin:8px 4px 4px 4px;'>";
			html += "<div style='padding:8px 0px 0px 8px;font-size:20px;vertical-align:top;'><b>" + this.title
					+ "</b><br/>";
			html += "<span style='padding:8px 0px 0px 2px;font-size:11px;'>" + this.slogan + "</span></div>";
			html += "</div";

		}

		return html;

	}
}
