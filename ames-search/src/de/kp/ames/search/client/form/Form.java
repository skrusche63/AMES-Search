package de.kp.ames.search.client.form;
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

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.fields.FormItem;

import de.kp.ames.search.client.activity.Activity;
import de.kp.ames.search.client.handler.FormHandler;

public interface Form {

	/**
	 * Add form data in terms of a JSONValue
	 * 
	 * @param jValue
	 */
	public void addFormData(JSONValue jValue);

	/**
	 * Add form data in terms of a record
	 * 
	 * @param record
	 */
	public void addFormData(Record record);
	
	/**
	 * @return
	 */
	public String getFormData();
	
	/**
	 * Assign form handler to process (submit)
	 * form data
	 */
	public void addFormHandler(FormHandler formHandler);

	/**
	 * @return
	 */
	public FormItem[] createFormItemsAsArray();

	/**
	 * @return
	 */
	public ArrayList<FormItem> createFormItemsAsList();

	/**
	 * Activity after 'ENTER' has been pressed on a
	 * form item
	 */
	public void doEnter();
	
	/**
	 * @param afterSubmitActivity
	 */
	public void doSubmit(Activity afterSubmitActivity);

	/**
	 * @param key
	 * @return
	 */
	public String getParam(String key);

	/**
	 * @param key
	 * @param value
	 */
	public void setParam(String key, String value);
	
	/**
	 * @param params
	 */
	public void setParams(HashMap<String,String> params);
	
	/**
	 * Indicate form to be read only or not
	 * 
	 * @param readOnly
	 */
	public void setReadOnly(boolean readOnly);

}
