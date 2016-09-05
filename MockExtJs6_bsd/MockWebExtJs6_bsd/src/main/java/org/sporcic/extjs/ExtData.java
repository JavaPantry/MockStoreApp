package org.sporcic.extjs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

    @ JsonProperty("data")
    private final List<Object> data = new ArrayList<Object>();

    @JsonProperty("total")
    private long total = 0;

    /**
     * Summary field to support remote Summary feature see:QuotaGrid.js referred as 'Ext.grid.feature.RemoteSummary'
     * TODO - <AP> Meta 'grid.feature.RemoteSummary'
     */
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
