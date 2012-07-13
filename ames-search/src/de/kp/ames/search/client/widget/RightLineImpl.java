package de.kp.ames.search.client.widget;

import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiStyles;

public class RightLineImpl extends VLayout {

	public RightLineImpl() {

		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_4);

		this.setWidth(GuiStyles.RIGHT_LINE_WIDTH);
		this.setHeight100();
		this.setBackgroundColor(GuiStyles.RIGHT_LINE_BG);
		
	};
	
}
