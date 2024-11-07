
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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreabono
 */
public class EWHosts_List_items {
    final private SimpleStringProperty host;
    final private SimpleStringProperty ports;
    final private SimpleIntegerProperty priority;
    
   
    
//------------------------------------------------------------------------------
    public EWHosts_List_items(String in_host, Integer in_priority, String in_ports){
        host=new SimpleStringProperty(in_host);
        priority = new SimpleIntegerProperty(in_priority);
        ports=new SimpleStringProperty(in_ports);
    }    
//------------------------------------------------------------------------------ 
    /**
     * @return the station_code
     */
    public String getHost() {
        return host.get();
    }
//------------------------------------------------------------------------------     
    /**
     * @param in_station_code the station_code to set
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
     * @return the priority
     */
    public Integer getPriority() {
        return priority.get();
    }
//------------------------------------------------------------------------------     
    /**
     * @param in_priority the priority to set
     */
    public void setPriority(Integer in_priority) {
        this.priority.set(in_priority);
    }
    
}

