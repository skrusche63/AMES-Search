package de.kp.ames.search.client.widget;
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

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.smartgwt.client.types.HeaderControls;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.HeaderControl;
import com.smartgwt.client.widgets.HeaderControl.HeaderIcon;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.event.CheckoutListener;
import de.kp.ames.search.client.event.SearchEventManager;
import de.kp.ames.search.client.globals.GUIGlobals;
import de.kp.ames.search.client.style.GuiStyles;
import de.kp.ames.search.client.widget.base.GUIBaseFactory;


public class CheckoutViewerImpl extends Window implements CheckoutListener {

	/*
	 * Default dimensions
	 */
	private static int DIM = 700;
	private HTMLPane htmlPane;

	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 */
	public CheckoutViewerImpl(String title, String slogan) {
		/*
		 * Set overall title for viewer
		 */
		this.setTitle(GUIGlobals.ADF_VIEWER);
		
		VLayout vLayout = new VLayout();
		vLayout.setShowEdges(false);
		
		vLayout.setWidth100();
		vLayout.setHeight100();
		
		vLayout.setMembersMargin(0);
		vLayout.setLayoutMargin(0);
		


		/*
		 * Build html pane
		 */
		htmlPane = new HTMLPane();
	    htmlPane.setShowEdges(false);
	    
	    htmlPane.setWidth100();
	    htmlPane.setHeight100();
	    
	    htmlPane.setPadding(15);

	    //htmlPane.setContents(html);
	    
        HeaderControl downloadControl = new HeaderControl(new HeaderIcon("silk/page_white_compressed.png"), new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SC.logWarn("====> downloadButton.onClick");

				SearchEventManager.getInstance().doTriggerDownload();

			}
		});
        
        downloadControl.setShowRollOver(false);  
        downloadControl.setShowFocused(false);  
        downloadControl.setShowDown(false); 
        downloadControl.setTooltip("Checkout your semantic research");
        downloadControl.setHoverWidth(180);
        downloadControl.setAltText("Checkout your semantic research");
        
        this.setHeaderControls(HeaderControls.HEADER_LABEL, downloadControl, HeaderControls.CLOSE_BUTTON);
        
        vLayout.setMembers(createHeadline(title, slogan), createBody(htmlPane));
		
		this.addItem(vLayout);
		
		this.setWidth(DIM);
		this.setHeight(DIM);

		this.setCanDragReposition(true);  
        this.setCanDragResize(true);  

		this.setShowShadow(true);
		this.setShadowOffset(3);

		this.setShadowSoftness(10);				
		this.setBodyColor(GuiStyles.VWR_BG_COLOR);

		final CheckoutViewerImpl self = this;		

		this.addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				self.destroy();				
			}			
		});

		this.centerInPage();
		this.draw();
		
		/*
		 * register listener
		 */
		SearchEventManager.getInstance().addCheckoutListener(this);

		
	}

	/**
	 * Create body element
	 * 
	 * @param canvas
	 * @return
	 */
	protected VLayout createBody(Canvas canvas) {

		VLayout vLayout = new VLayout();
		vLayout.setStyleName(GuiStyles.X_BD_STYLE_2);

		vLayout.setWidth100();		
		vLayout.setHeight100();
		
		vLayout.addMember(canvas);
		return vLayout;
		
	}

	/**
	 * Create headline element
	 * 
	 * @param title
	 * @param slogan
	 * @return
	 */
	protected VLayout createHeadline(String title, String slogan) {
		return GUIBaseFactory.createHeadline(title, slogan);
	}

	/*
	 * (non-Javadoc)
	 * @see de.kp.ames.search.client.event.CheckoutListener#doAfterUpdate(com.google.gwt.json.client.JSONObject)
	 */
	@Override
	public void doAfterUpdate(JSONObject jObject) {
		SC.logWarn("======> CheckoutViewerImpl.doAfterCheckoutUpdate");
		/*
		 * update HTML pane
		 */
		htmlPane.setContents(((JSONString) jObject.get("data")).stringValue());
	}

	
}
