package org.sporcic.extjs;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An ExtResponse subclass which contains a list of data, along with
 * a total count. This is usefully for working with ExtJS Stores so that
 * you don't need to build your own Map to return. The fields will be
 * serialized by Jackson JSON in the JSON format as long as you annotate
 * the controller method return value as @ResponseBody.
 */
public class ExtData extends ExtResponse {

	//@ JsonProperty("data")
	@Expose
    private final List<Object> data = new ArrayList<Object>();

	//@JsonProperty("total")
	@Expose
    private long total = 0;

    /**
     * Summary field to support remote Summary feature see:QuotaGrid.js referred as 'Ext.grid.feature.RemoteSummary'
     * TODO - <AP> Meta 'grid.feature.RemoteSummary'
     */
	@Expose
    @ JsonProperty("summary")
    Object summary	= null; 
    
    public Object getSummary() {return summary;}
	public void setSummary(Object summary) {this.summary = summary;}
	
	public long getTotal() {return total;}
	public void setTotal(long total) {this.total = total;}

	/**
     * Add a single Object to the data array
     * @param item the Object to add to the array
     */
    public void add(Object item) {

        if(item == null) return;

        if(item instanceof Collection) {

            for(Object object : (Collection) item) {
                data.add(object);
                total++;
            }

        } else {
            data.add(item);
            total++;
        }
    }
}
