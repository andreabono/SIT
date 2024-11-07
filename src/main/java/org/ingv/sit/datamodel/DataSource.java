
package org.ingv.sit.datamodel;

import java.util.ArrayList;

/**
 *
 * @author andreabono
 */
public class DataSource {
    
    public enum dsType{
         FDSN,
         INGV,
         EARTHWORMWS,
         SL,
         LOCALHOST_EVENTS,
         LOCALHOST_NETWORKS,
         UNKNOWN
    };
    
    //--------------------------------------------------------------------------
    private dsType datasourcetype;  // Can be: STATIONS, WAVES, EVENTS
    private boolean used;       
    private String description;
    private String hostname;        // Earthworm hostname for waveservers 
    private ArrayList<Integer> portslist;       // Array: "16021,16022,16023,16024,16025"
    private String url;             // url for a webservice type data source
    public int httpport;
    
    private boolean still_trying_to_read=true;
    //--------------------------------------------------------------------------
    
    /**
     * @return the datasourcetype
     */
    public dsType getDatasourcetype() {
        return datasourcetype;
    }

    /**
     * @param datasourcetype the datasourcetype to set
     */
    public void setDatasourcetype(dsType datasourcetype) {
        this.datasourcetype = datasourcetype;
    }

    /**
     * @return the used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the portslist
     */
    public ArrayList<Integer> getPortslist() {
        return portslist;
    }

    /**
     * @param portslist the portslist to set
     */
    public void setPortslist(ArrayList<Integer> portslist) {
        this.portslist = portslist;
    }

    /**
     * @return the still_trying_to_read
     */
    public boolean isStill_trying_to_read() {
        return still_trying_to_read;
    }

    /**
     * @param still_trying_to_read the still_trying_to_read to set
     */
    public void setStill_trying_to_read(boolean still_trying_to_read) {
        this.still_trying_to_read = still_trying_to_read;
    }

    /**
     * @return the httpport
     */
    public int getHttpport() {
        return httpport;
    }

    /**
     * @param httpport the httpport to set
     */
    public void setHttpport(int httpport) {
        this.httpport = httpport;
    }
    
}
