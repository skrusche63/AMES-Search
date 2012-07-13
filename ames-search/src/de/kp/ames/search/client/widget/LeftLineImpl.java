package de.kp.ames.search.client.widget;

import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiStyles;

public class LeftLineImpl extends VLayout {

	public LeftLineImpl() {
		
		this.setShowEdges(false);
		this.setStyleName(GuiStyles.X_BD_STYLE_3);
		
		this.setWidth(GuiStyles.LEFT_LINE_WIDTH);
		this.setHeight100();
		this.setBackgroundColor(GuiStyles.LEFT_LINE_BG);

		
		
	};
	
}
