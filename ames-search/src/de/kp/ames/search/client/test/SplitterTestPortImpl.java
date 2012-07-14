package de.kp.ames.search.client.test;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

import de.kp.ames.search.client.globals.GuiStyles;
import de.kp.ames.search.client.layout.CenterportImpl;

public class SplitterTestPortImpl extends CenterportImpl {

	public SplitterTestPortImpl() {
		super();
		
		this.setOverflow(Overflow.HIDDEN);

		VLayout top = createTop();
		top.setHeight("15%");
		
		VLayout bottom = createBottom();
		bottom.setHeight("85%");
		
		top.setShowResizeBar(true);
		
		this.setMembers(top, bottom);

	}
	
	public VLayout createTop() {
		
		VLayout layout = new VLayout();
		
		layout.setShowEdges(false);
		
		layout.setStyleName(GuiStyles.X_BD_STYLE_0);
		layout.setBackgroundColor("#f2f2f2");
		
		layout.setWidth100();
		layout.setHeight100();
		
		layout.setOverflow(Overflow.AUTO);

		HTMLPane pane = new HTMLPane();		
		pane.setContents(getHtml());
		
		layout.setMembers(pane);
		
		return layout;
	
	}
	
	public VLayout createBottom() {

		VLayout layout = new VLayout();

		layout.setShowEdges(false);
		layout.setStyleName(GuiStyles.X_BD_STYLE_3);
		
		layout.setWidth100();
		layout.setHeight100();
		
		layout.setOverflow(Overflow.AUTO);
		layout.setMembers(createGrid());
		
		return layout;

	}
	
	private ListGrid createGrid() {
		
		ListGrid grid = new ListGrid();
		grid.setShowEdges(false);
		/*
		 * No border style
		 */
		grid.setStyleName(GuiStyles.X_BD_STYLE_0);

		grid.setHeight100();
		grid.setWidth100();

		grid.setShowHeader(false);
		
		return grid;
	}
	
	private String getHtml() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<div class=\"sg-fb\">This is a test text.</div>");
		
		return sb.toString();
	}
}
