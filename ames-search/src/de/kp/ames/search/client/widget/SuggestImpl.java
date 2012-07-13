package de.kp.ames.search.client.widget;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.widget.grid.SuggestGridImpl;

public class SuggestImpl extends VLayout {

	public SuggestImpl(String query) {
		this.setWidth(416);
		this.setHeight(600); // dynamic set

		SuggestGridImpl grid = new SuggestGridImpl(query);
		
		this.addMember(grid);
		this.setOverflow(Overflow.AUTO);
		
	};
}
