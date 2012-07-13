package de.kp.ames.search.client.widget;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.layout.HLayout;

public class ResultImpl extends HLayout {
	
	private LeftLineImpl leftLine;
	private CenterLineImpl centerLine;
	private RightLineImpl rightLine;

	public ResultImpl(Record record) {
		this.setWidth100();
		this.setHeight100();
		
		leftLine = new LeftLineImpl();
		centerLine = new CenterLineImpl(record);
		rightLine = new RightLineImpl();
		this.setMembers(leftLine, centerLine, rightLine);
		
	};
	
	

}
