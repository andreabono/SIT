/**
 *
 *
 * Andrea Bono
 * I.N.G.V. Isituto Nazionale di Geofisica e Vulcanologia
 * C.N.T. Centro Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 */
package org.ingv.sit.utils;

import java.util.ArrayList;

/**
 *
 * @author andreabono
 */
public class earthworm_hostslist_element {
    private String hostname;
    private ArrayList<Integer> portslist;
    private int priority=-1;

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
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
}
