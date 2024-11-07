/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 * andrea.bono@ingv.itff
 * +39 0651860290
 * 
 */
package org.ingv.sit.tablemodels;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataSource_List_items {
    final private SimpleBooleanProperty used; 
    final private SimpleStringProperty type;
    final private SimpleStringProperty description;
    final private SimpleStringProperty host;
    final private SimpleStringProperty ports;
    final private SimpleIntegerProperty httpport;
    final private SimpleStringProperty url;
 
//------------------------------------------------------------------------------
    public DataSource_List_items(Boolean in_used, String in_type, String in_desc,  String in_host, String in_ports, String in_url, int in_httpport){
        used = new SimpleBooleanProperty(in_used);
        type =new SimpleStringProperty(in_type);
        description =new SimpleStringProperty(in_desc);
        ports=new SimpleStringProperty(in_ports);
        httpport=new SimpleIntegerProperty(in_httpport);
        host =new SimpleStringProperty(in_host);
        url =new SimpleStringProperty(in_url);
    }    
    //------------------------------------------------------------------------------ 
    public Boolean getUsed() {
        return used.get();
    }
//------------------------------------------------------------------------------     
    public void setUsed(Boolean in_used) {
        this.used.set(in_used);
    }    
//------------------------------------------------------------------------------ 
    public String getType() {
        return type.get();
    }
//------------------------------------------------------------------------------     
    public void setType(String in_type) {
        this.type.set(in_type);
    }
//------------------------------------------------------------------------------ 
    public String getDescription() {
        return description.get();
    }
//------------------------------------------------------------------------------     
    public void setDescription(String in_desc) {
        this.description.set(in_desc);
    }    
    
    public String getUrl() {
        return url.get();
    }
//------------------------------------------------------------------------------     
    public void setUrl(String in_url) {
        this.url.set(in_url);
    }        
//------------------------------------------------------------------------------ 
    /**
     * @return the hostname
     */
    public String getHost() {
        return host.get();
    }
//------------------------------------------------------------------------------     
    /**
     * @param in_host the hostname to set
     */
    public void setHost(String in_host) {
        this.host.set(in_host);
    }
//------------------------------------------------------------------------------   
    /**
     * @return the ports list
     */
    public String getPorts() {
        return ports.get();
    }
//------------------------------------------------------------------------------     
    /**
     * @param in_ports the ports list to set
     */
    public void setPorts(String in_ports) {
        this.ports.set(in_ports);
    }
//------------------------------------------------------------------------------   
    /**
     * @return the ports list
     */
    public int getHttpport() {
        return httpport.get();
    }
//------------------------------------------------------------------------------     
    /**
     * @param in_ports the ports list to set
     */
    public void setHttpport(int in_port) {
        this.httpport.set(in_port);
    }
//------------------------------------------------------------------------------ 
   
    
}
