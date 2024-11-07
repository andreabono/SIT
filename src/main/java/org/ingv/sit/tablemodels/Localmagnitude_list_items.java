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
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
package org.ingv.sit.tablemodels;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreabono
 */
public class Localmagnitude_list_items {
    private SimpleStringProperty station;
    private SimpleStringProperty channel;
    private SimpleFloatProperty delta;
    private SimpleFloatProperty ml;
    private SimpleFloatProperty a1;
    private SimpleStringProperty t1;
    private SimpleFloatProperty a2;
    private SimpleStringProperty t2;
    private SimpleStringProperty Used;
    
    private SimpleStringProperty Log;
    
    //------------------------------------------------------------------------------
    public Localmagnitude_list_items(String in_used, String in_station, String in_channel, Float in_delta, Float in_ml, Float in_a1, String in_t1,
            Float in_a2, String in_t2, String in_log){
        station =new SimpleStringProperty(in_station);
        channel = new SimpleStringProperty(in_channel);
        delta =new SimpleFloatProperty(in_delta);
        if (in_ml!=null)
            ml =new SimpleFloatProperty(in_ml);
        a1 =new SimpleFloatProperty(in_a1);
        t1 =new SimpleStringProperty(in_t1);
        a2 =new SimpleFloatProperty(in_a2);
        t2 =new SimpleStringProperty(in_t2);
        Used= new SimpleStringProperty(in_used);
        Log=new SimpleStringProperty(in_log);
    }    

    /**
     * @return the name
     */
    public String getStation() {
        return station.get();
    }

    /**
     * @param name the name to set
     */
    public void setStation(SimpleStringProperty in_sta) {
        station = in_sta;
    }

    public String getChannel() {
        return channel.get();
    }
    
    public void setChannel(SimpleStringProperty in_cha) {
        channel = in_cha;
    }
    
    public String getT1() {
        if (t1==null) return "";
        if (t1.get().length()==0) return "";
        
        if (t1.get().contains("."))
            return t1.get().substring(11,t1.get().indexOf(".")+2);
        else
            return t1.get().substring(11,t1.get().indexOf("Z"))+".0" ;    
    }
    
    public void setT1(SimpleStringProperty in_t1) {
        t1 = in_t1;
    }
    
    public String getT2() {
        if (t2==null) return "";
        if (t2.get().length()==0) return "";
        
        if (t2.get().contains("."))
            return t2.get().substring(11,t2.get().indexOf(".")+2);
        else
        return t2.get().substring(11,t2.get().indexOf("Z"))+".0" ; 
    }
    
    public void setT2(SimpleStringProperty in_t2) {
        t2 = in_t2;
    }
    
    public Float getA1() {
        return a1.get();
    }
    
    public void setA1(SimpleFloatProperty in_a1) {
        a1 = in_a1;
    }
    
    public Float getA2() {
        return a2.get();
    }
    
    public void setA2(SimpleFloatProperty in_a2) {
        a1 = in_a2;
    }

    /**
     * @return the Used
     */
    public SimpleStringProperty getUsed() {
        return Used;
    }

    /**
     * @param Used the Used to set
     */
    public void setUsed(SimpleStringProperty Used) {
        this.Used = Used;
    }
    
    /**
     * @return the Log
     */
    public SimpleStringProperty getLog() {
        return Log;
    }

    /**
     * @param Used the Used to set
     */
    public void setLog(SimpleStringProperty in_Log) {
        this.Log = in_Log;
    }

    public Float getMl() {
        return ml.get();
    }
    
    public void setMl(SimpleFloatProperty in_ml) {
        ml = in_ml;
    }
    
    public Float getDelta() {
        return delta.get();
    }
    
    public void setDelta(SimpleFloatProperty in_delta) {
        delta = in_delta;
    }
    
}







