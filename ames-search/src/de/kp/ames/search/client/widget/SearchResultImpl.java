package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiStyles;
import de.kp.ames.search.client.widget.grid.SearchResultGridImpl;

public class SearchResultImpl extends VLayout {
	private SearchResultGridImpl grid;

	public SearchResultImpl(Record record) {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_3);
		
		this.setWidth100();
		this.setHeight100();
		
//		this.setOverflow(Overflow.AUTO);
		
		grid = new SearchResultGridImpl(record);

		this.setMembers(grid);
	}


}
