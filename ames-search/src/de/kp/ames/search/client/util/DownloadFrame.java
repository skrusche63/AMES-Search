package de.kp.ames.search.client.util;

import com.google.gwt.event.dom.client.HasLoadHandlers;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.RootPanel;

import de.kp.ames.search.client.activity.Activity;


public class DownloadFrame extends Frame implements LoadHandler, HasLoadHandlers {

    public static final String DOWNLOAD_FRAME = "__gwt_downloadFrame";
    
    /*
     * Reference to after download activity
     */
	private Activity afterDownloadActivity;

    public DownloadFrame(String requestUrl, Activity afterDownloadActivity) {
        super();

        /*
         * Register activity
         */
        this.afterDownloadActivity = afterDownloadActivity;
        
        /*
         * Dimensions
         */
        this.setSize("0px", "0px");
        this.setVisible(false);
            
        RootPanel rp = RootPanel.get(DOWNLOAD_FRAME);
        if (rp != null) {
            
        	this.addLoadHandler(this);
            rp.add(this);
            this.setUrl(requestUrl);
        
        } else {
          openURLInNewWindow(requestUrl);
          
        }
        
    }

	/**
	 * @param url
	 */
	private native void openURLInNewWindow(String url) /*-{
    	$wnd.open(url);
    }-*/;

    /* (non-Javadoc)
     * @see com.google.gwt.user.client.ui.Frame#addLoadHandler(com.google.gwt.event.dom.client.LoadHandler)
     */
    public HandlerRegistration addLoadHandler(LoadHandler handler) {
        return this.addHandler(handler, LoadEvent.getType());
    }

    /* (non-Javadoc)
     * @see com.google.gwt.event.dom.client.LoadHandler#onLoad(com.google.gwt.event.dom.client.LoadEvent)
     */
    public void onLoad(LoadEvent event) {
    	if (this.afterDownloadActivity != null) this.afterDownloadActivity.execute();
    }
    
}
