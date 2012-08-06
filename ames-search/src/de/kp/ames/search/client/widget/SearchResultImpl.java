package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.data.ResultGridImpl;
import de.kp.ames.search.client.style.GuiStyles;

public class SearchResultImpl extends VLayout {
	private ResultGridImpl grid;

	public SearchResultImpl(Record record) {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_3);
		
		this.setWidth100();
		this.setHeight100();
		
		grid = new ResultGridImpl(record);

		this.setMembers(grid);
	}

	public void update(Record record) {
		replacePlaceHolderByGrid(record);		
	}

	private void replacePlaceHolderByGrid(Record record) {
		
		ResultGridImpl grid = new ResultGridImpl(record);


		this.removeMember(this.getMember(0));
		this.addMember(grid);
		
	}

}
