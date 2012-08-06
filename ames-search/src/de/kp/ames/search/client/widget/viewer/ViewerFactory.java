package de.kp.ames.search.client.widget.viewer;

import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.widgets.HTMLPane;

public class ViewerFactory {

	/**
	 * Files are loaded view an <IFrame>, i.e. using
	 * the <HTMLPane> component
	 * 
	 * @param title
	 * @param slogan
	 * @param uri
	 */
	public static void createFrameViewer(String title, String slogan, String uri) {
		
		/*
		 * Build html pane
		 */
		HTMLPane htmlPane = new HTMLPane();
	    htmlPane.setShowEdges(false);
	    
	    htmlPane.setWidth100();
	    htmlPane.setHeight100();
	    
	    htmlPane.setContentsURL(uri);
	    htmlPane.setContentsType(ContentsType.PAGE);
		
	    /*
	     * Instantiate viewer
	     */
	    new ViewerImpl(title, slogan, htmlPane);
	    
	}
	
	/**
	 * @param title
	 * @param slogan
	 * @param html
	 */
	public static void createHtmlViewer(String title, String slogan, String html) {

		/*
		 * Build html pane
		 */
		HTMLPane htmlPane = new HTMLPane();
	    htmlPane.setShowEdges(false);
	    
	    htmlPane.setWidth100();
	    htmlPane.setHeight100();

	    htmlPane.setContents(html);

	    /*
	     * Instantiate viewer
	     */
	    new ViewerImpl(title, slogan, htmlPane);
	    
	}
	
}
