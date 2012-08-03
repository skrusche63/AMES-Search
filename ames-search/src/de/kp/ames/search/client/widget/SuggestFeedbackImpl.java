package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiStyles;
import de.kp.ames.search.client.globals.JsonConstants;

public class SuggestFeedbackImpl extends VLayout {
	private HTMLPane pane;

	public SuggestFeedbackImpl(Record record) {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_0);
		
		this.setWidth100();
		this.setHeight100();
		this.setBackgroundColor(GuiStyles.SUGGEST_FEEDBACK_BG);
//		this.setMembersMargin(10);
		
		this.setOverflow(Overflow.AUTO);

		pane = new HTMLPane();
//		pane.setWidth100();
//		pane.setHeight100();
		pane.setContents(recordToHtml(record));
		
		this.setMembers(pane);

	}
	
	private String recordToHtml(Record record) {
		// wrap search term context into an additional DIV for layout-control
		String result = "<div class=\"sg-fb\">" +
				 record.getAttributeAsString(JsonConstants.J_RESULT) +
				"<div class=\"sg\">" +
					 "<p class=\"sg-dg\">" + 
						"<span class=\"sg-dl\">Hypernym:</span>" + 
						"<span class=\"sg-d\"> " + record.getAttributeAsString(JsonConstants.J_HYPERNYM) + "</span>" + 
					"</p>" +
				 "</div>" + 
				 "</div>"; 
		return result;
	}

	public void update(Record record) {
		pane.setContents(recordToHtml(record));
	}
	
}
