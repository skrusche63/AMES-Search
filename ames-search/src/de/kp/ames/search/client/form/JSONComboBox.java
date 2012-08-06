package de.kp.ames.search.client.form;
/**
 *	Copyright 2012 Dr. Krusche & Partner PartG
 *
 *	AMES-Web-GUI is free software: you can redistribute it and/or 
 *	modify it under the terms of the GNU General Public License 
 *	as published by the Free Software Foundation, either version 3 of 
 *	the License, or (at your option) any later version.
 *
 *	AMES- Web-GUI is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * 
 *  See the GNU General Public License for more details. 
 *
 *	You should have received a copy of the GNU General Public License
 *	along with this software. If not, see <http://www.gnu.org/licenses/>.
 *
 */

import java.util.Map;

import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;

public class JSONComboBox extends ComboBoxItem {

	private RestDataSource ds;
	
	public JSONComboBox(String name) {
		super(name);
	}
	
	public void configureDS(final String url, final Map<String, String> params, final DataSourceField[] fields) {
		
		/* 
		 * This data source is loaded on demand, i.e. on the first user click
		 */
		
		ds = new RestDataSource() {
			  
			protected Object transformRequest(DSRequest dsRequest) {  
				dsRequest.setParams(params);				
				return super.transformRequest(dsRequest);  
			}  

			protected void transformResponse(DSResponse response, DSRequest request, Object data) {  
				super.transformResponse(response, request, data);  
			}  
			
		};
		
		ds.setDataFormat(DSDataFormat.JSON);
		ds.setDataProtocol(DSProtocol.GETPARAMS);  
		
		ds.setFetchDataURL(url);		
		ds.setFields(fields);

		this.setOptionDataSource(ds);
		
	}
	
}
