package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.data.ResultCardGridImpl;
import de.kp.ames.search.client.style.GuiStyles;

public class ResultCartImpl extends VLayout {
	private ResultCardGridImpl grid;

	public ResultCartImpl() {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_3);
		
		this.setWidth100();
		this.setHeight100();
		
		SC.logWarn("========> ResultCartImpl.CTOR 1");

		grid = new ResultCardGridImpl();

		SC.logWarn("========> ResultCartImpl.CTOR 2");

		this.setMembers(grid);
	}

	public void addChoice(Record suggestFeedbackRecord, Record resultRecord) {
		
		String combinedId = suggestFeedbackRecord.getAttribute("id") + "::" + resultRecord.getAttribute("id");
		
		// TODO: check if id is already added
		if (this.hasCombinedId()) return;
		
		Record record = new Record();
		record.setAttribute("id", combinedId);
		
		SC.logWarn("========> ResultCartImpl.addChoice combId: " + record.getAttribute("id"));

		record.setAttribute("suggest", suggestFeedbackRecord.getAttribute("term"));
		record.setAttribute("choice", resultRecord.getAttribute("title"));
		grid.addData(record);
	}

	private boolean hasCombinedId() {
		return grid.hasCombinedId();
	}

	public int getCartCount() {
//		return grid.getRecord().length;
		return grid.getTotalRows();
	}


}
