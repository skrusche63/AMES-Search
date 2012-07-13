package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiStyles;

public class CenterLineImpl extends VLayout {

	private SuggestFeedbackImpl suggestFeedback;
	private SearchResultImpl searchResult;

	public CenterLineImpl(Record record) {
		
		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_4);
		
		this.setWidth(GuiStyles.CENTER_LINE_WIDTH);
		this.setHeight100();
		
		this.setOverflow(Overflow.HIDDEN);

		suggestFeedback = new SuggestFeedbackImpl(record);
		suggestFeedback.setHeight("15%");
		
		searchResult = new SearchResultImpl(record);
		searchResult.setHeight("85%");
		
		suggestFeedback.setShowResizeBar(true);
		
		this.setMembers(suggestFeedback, searchResult);
		
	};
	
}
