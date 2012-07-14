package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import de.kp.ames.search.client.layout.CenterportImpl;

public class ResultPortImpl extends CenterportImpl {

	private SuggestFeedbackImpl suggestFeedback;
	private SearchResultImpl searchResult;

	public ResultPortImpl(Record record) {
		super();
		
		this.setOverflow(Overflow.HIDDEN);

		suggestFeedback = new SuggestFeedbackImpl(record);
		suggestFeedback.setHeight("15%");
		
		searchResult = new SearchResultImpl(record);
		searchResult.setHeight("85%");
		
		suggestFeedback.setShowResizeBar(true);
		
		this.setMembers(suggestFeedback, searchResult);
		
	};
	
}
