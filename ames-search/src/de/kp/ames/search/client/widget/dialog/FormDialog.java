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

import java.util.HashMap;

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.form.FormImpl;
import de.kp.ames.search.client.handler.FormHandler;

public class FormDialog extends BaseDialog implements FormHandler {

	/*
	 * Reference to form
	 */
	protected FormImpl form;

	/*
	 * Request specific parameters
	 */
	protected HashMap<String,String> params;
	
	/*
	 * Reference to after send activity
	 */
	protected Activity sendActivity;
	
	/*
	 * Form based label style
	 */
	protected static String STYLE = "x-sc-label";
	
	/*
	 * An indicator to determine whether a dialog is
	 * automatically closed after any button is clicked 
	 */
	protected boolean autoClose = true;
	
	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 */
	public FormDialog(String title, String slogan) {
		super(title, slogan);				
	}
	
	/**
	 * Constructor
	 * 
	 * @param title
	 * @param slogan
	 * @param jValue
	 */
	public FormDialog(String title, String slogan, JSONValue jValue) {
		super(title, slogan, jValue);
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.widget.dialog.BaseDialog#createContent()
	 */
	public Canvas createContent() {

		/*
		 * Register form and assign form handler
		 */
		this.form = new FormImpl();
		this.form.addFormHandler(this);

		return this.form;
		
	}

	/**
	 * Get request specific parameters
	 * 
	 * @return
	 */
	public HashMap<String,String> getParams() {
		if (this.params == null) this.params = new HashMap<String,String>();
		return this.params;
	}
	
	/**
	 * Set request specific parameters
	 * 
	 * @param params
	 */
	public void setParams(HashMap<String,String> params) {
		if (this.params == null) this.params = new HashMap<String,String>();
		this.params.putAll(params);

		/*
		 * Synchronize form
		 */
		this.form.setParams(this.params);

	}

	/**
	 * Set a certain request specific parameter
	 * 
	 * @param key
	 * @param value
	 */
	public void setParam(String key, String value) {
		if (this.params == null) this.params = new HashMap<String,String>();
		this.params.put(key, value);
		
		/*
		 * Synchronize form
		 */
		this.form.setParams(this.params);
		
	}
	
	/**
	 * Get a certain request specific parameter
	 * 
	 * @param key
	 * @return
	 */
	public String getParam(String key) {
		if ((this.params == null) || (this.params.containsKey(key) == false)) return null;
		return this.params.get(key);
	}

	/**
	 * @param autoClose
	 */
	public void setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.FormHandler#doSend()
	 */
	public void doSend() {
		/*
		 * Must be overridden
		 */
	}

	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.form.FormHandler#addSendActivity(de.kp.ames.web.client.core.activity.Activity)
	 */
	public void addSendActivity(Activity activity) {
		this.sendActivity = activity;
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.widget.dialog.BaseDialog#createB1ClickHandler()
	 */
	public ClickHandler createB1ClickHandler() {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				doB1Click();				
			}			
		};
	}
	
	/* (non-Javadoc)
	 * @see de.kp.ames.web.client.core.widget.dialog.BaseDialog#createB2ClickHandler()
	 */
	public ClickHandler createB2ClickHandler() {
		return new ClickHandler() {
			public void onClick(ClickEvent event) {
				doB2Click();				
			}			
		};
	}

	/**
	 * Dialog handling after second button (B1) clicked
	 */
	public void doB1Click() {		
		/*
		 * Send respective form content
		 */
		this.doSend();				

		if (this.autoClose == false) return;
		
		/*
		 * Initiate before remove processing
		 */
		this.beforeRemove();
		
		/*
		 * Destroy window
		 */
		this.destroy();
		
	}
	
	/**
	 * Dialog handling after second button (B2) clicked
	 */
	public void doB2Click() {
		/*
		 * Initiate before remove processing
		 */
		this.beforeRemove();
		
		/*
		 * Destroy window
		 */
		this.destroy();

	}
}
