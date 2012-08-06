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

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.RichTextEditor;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class GuiFormFactory {

	/**
	 * A GUI helper to create a label
	 * 
	 * @param seqno
	 * @param label
	 * @param style
	 * @return
	 */
	public static CanvasItem createScLabelItem(int seqno, String label, String style) {

		String title = "" + seqno + ". " + label;

		HTMLPane pane = new HTMLPane();
		pane.setHeight(2);
		
		CanvasItem canvasField = new CanvasItem();
		
		canvasField.setTitle(title);
		canvasField.setTitleStyle(style);
		
		canvasField.setCanvas(pane);		
		return canvasField;
		
	}

	/**
	 * A GUI helper to create a password item
	 * 
	 * @param label
	 * @param name
	 * @param style
	 * @param width
	 * @return
	 */
	public static PasswordItem createScPasswordItem(String label, String name, String style, int width) {
		
		PasswordItem textField = new PasswordItem(name, label);
		
		if (label == null) textField.setShowTitle(false);
		textField.setTitleStyle(style);

		textField.setVAlign(VerticalAlignment.TOP);
		
		textField.setWidth(width);
		return textField;
		
	}

	/**
	 * A GUI helper to create a text area
	 * 
	 * @param label
	 * @param name
	 * @param style
	 * @param width
	 * @return
	 */
	public static TextAreaItem createScTextAreaItem(String label, String name, String style, int width) {
		
		TextAreaItem textAreaField = new TextAreaItem(name, label);
		
		if (label == null) textAreaField.setShowTitle(false);
		textAreaField.setTitleStyle(style);

		textAreaField.setVAlign(VerticalAlignment.TOP);

		textAreaField.setWidth(width);
		textAreaField.setHeight(100);
		
		return textAreaField;
		
	}

	/**
	 * A GUI helper to create a text area
	 * 
	 * @param label
	 * @param name
	 * @param style
	 * @param width
	 * @return
	 */
	public static TextAreaItem createScTextAreaItem(String label, String name, String style, String width) {
		
		TextAreaItem textAreaField = new TextAreaItem(name, label);
		
		if (label == null) textAreaField.setShowTitle(false);
		textAreaField.setTitleStyle(style);

		textAreaField.setVAlign(VerticalAlignment.TOP);

		textAreaField.setWidth(width);
		textAreaField.setHeight(100);
		
		return textAreaField;
		
	}

	/**
	 * A GUI helper to create a text item
	 * 
	 * @param label
	 * @param name
	 * @param style
	 * @param width
	 * @return
	 */
	public static TextItem createScTextItem(String label, String name, String style, int width) {
		
		TextItem textField = new TextItem(name, label);
		
		if (label == null) textField.setShowTitle(false);
		textField.setTitleStyle(style);

		textField.setVAlign(VerticalAlignment.TOP);

		textField.setWidth(width);
		return textField;
		
	}

	/**
	 * A GUI helper to create a text item
	 * 
	 * @param label
	 * @param name
	 * @param style
	 * @param width
	 * @return
	 */
	public static TextItem createScTextItem(String label, String name, String style, String width) {
		
		TextItem textField = new TextItem(name, label);
		
		if (label == null) textField.setShowTitle(false);
		textField.setTitleStyle(style);

		textField.setVAlign(VerticalAlignment.TOP);

		textField.setWidth(width);
		return textField;
		
	}

	/**
	 * A GUI helper to create an upload item
	 * 
	 * @param label
	 * @param name
	 * @param style
	 * @param width
	 * @return
	 */
	public static FileUploadItem createScFileUploadItem(String name, int width) {
		
		FileUploadItem uploadField = new FileUploadItem(name, "", width);
		uploadField.wrapHtmlElement();

		return uploadField;
		
	}
	
	/**
	 * A helper method to create a rich text editor
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public static RichTextEditor createScRichTextEditor(int width, int height) {
		
		RichTextEditor richTextEditor = new RichTextEditor();
		
		/*
		 * Dimensions
		 */
		richTextEditor.setWidth(width);
		richTextEditor.setHeight(height);
		
		/*
		 * Rendering
		 */
	    richTextEditor.setShowEdges(false);  

	    richTextEditor.setOverflow(Overflow.HIDDEN);  
	    richTextEditor.setCanDragResize(false);  
		
		return richTextEditor;
		
	}

}
