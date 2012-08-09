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

import com.smartgwt.client.types.BkgndRepeat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.style.GuiStyles;


public class GUIBaseFactory {
	
	/**
	 * Create application headline
	 * 
	 * @param title
	 * @param slogan
	 * @return
	 */
	public static VLayout createHeadline(String title, String slogan) {

		VLayout vLayout = new VLayout();
		vLayout.setStyleName(GuiStyles.X_BD_STYLE_1);
		
		/*
		 * Headline dimension
		 */
		vLayout.setWidth100();
		vLayout.setHeight(64);
		
		vLayout.setMargin(0);
		
		vLayout.setBackgroundImage(GuiStyles.APP_BG_IMAGE);
		vLayout.setBackgroundRepeat(BkgndRepeat.REPEAT);
	
		SC.logWarn("======> GUIBaseFactory.createHeadline: "); 
		
		vLayout.addMember(new BaseHeadline(title, slogan));		
		return vLayout;
	
	}

}
