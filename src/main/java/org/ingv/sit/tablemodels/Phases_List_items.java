package org.ingv.sit.tablemodels;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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
public class Phases_List_items {
    final private SimpleStringProperty station_code;
    final private SimpleStringProperty channel_code;
    final private SimpleStringProperty pick_name;
    final private SimpleStringProperty pick_time;
    final private SimpleFloatProperty residual;
    final private SimpleFloatProperty delta;
    final private SimpleFloatProperty azimut;
    final private SimpleFloatProperty weight;
    private SimpleIntegerProperty qualityclass_weight;
    private SimpleStringProperty used;
   
    
//------------------------------------------------------------------------------
    public Phases_List_items(String in_station_code, String in_channel_code,
                String in_pick_name, String in_pick_time, Float in_residual,
                Float in_ph_weight, Float in_delta, Float in_azimut, Integer in_quality_class){
        station_code=new SimpleStringProperty(in_station_code);
        channel_code = new SimpleStringProperty(in_channel_code);
        pick_name =new SimpleStringProperty(in_pick_name);
        pick_time =new SimpleStringProperty(in_pick_time);
        residual =new SimpleFloatProperty(in_residual);
        weight =new SimpleFloatProperty(in_ph_weight);
        delta =new SimpleFloatProperty(in_delta);
        azimut=new SimpleFloatProperty(in_azimut);
        used = new SimpleStringProperty("TRUE");
        qualityclass_weight= new SimpleIntegerProperty(in_quality_class);
    }    
//------------------------------------------------------------------------------ 
    /**
     * @return the station_code
     */
    public String getStation_code() {
            return station_code.get();
        }
//------------------------------------------------------------------------------     
    /**
     * @param in_station_code the station_code to set
     */
    public void setStation_code(String in_station_code) {
            this.station_code.set(in_station_code);
        }
//------------------------------------------------------------------------------
    /**
     * @return the channel_code
     */
    public String getChannel_code() {
        return channel_code.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_channel_code the channel_code to set
     */
    public void setChannel_code(String in_channel_code) {
        this.channel_code.set(in_channel_code);
    }
//------------------------------------------------------------------------------
    /**
     * @return the pick_name
     */
    public String getPick_name() {
        return pick_name.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_pick_name the channel_code to set
     */
    public void setPick_name(String in_pick_name) {
        this.pick_name.set(in_pick_name);
    }
//------------------------------------------------------------------------------
    /**
     * @return the pick_time
     */
    public String getPick_time() {
        return pick_time.get().replace("Z","");
    }
//------------------------------------------------------------------------------
    /**
     * @param in_pick_time the channel_code to set
     */
    public void setPick_time(String in_pick_time) {
        this.pick_time.set(in_pick_time);
    }
//------------------------------------------------------------------------------
    /**
     * @return the residual
     */
    public Float getResidual() {
        return residual.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_residual the channel_code to set
     */
    public void setResidual(Float in_residual) {
        this.residual.set(in_residual);
    }
//------------------------------------------------------------------------------

    /**
     * @return the delta
     */
    public Float getDelta() {
        return delta.get();
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(Float in_delta) {
        this.delta.set(in_delta);
    }

    /**
     * @return the azimut
     */
    public Float getAzimut() {
        return azimut.get();
    }

    /**
     * @param azimut the azimut to set
     */
    public void setAzimut(Float in_azimut) {
        this.azimut.set(in_azimut);
    }

    /**
     * @return the weight
     */
    public Float getWeight() {
        return weight.get();
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Float in_weight) {
        this.weight.set(in_weight);
    }

    /**
     * @return the used
     */
    public SimpleStringProperty getUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(SimpleStringProperty used) {
        this.used = used;
    }
    
    /**
     * @return the hypo_weight
     */
    public int getQualityclass_weight() {
        return this.qualityclass_weight.get();
    }

    /**
     * @param hypo_weight the hypo_weight to set
     */
    public void setQualityclass_weight(int qc_weight) {
        this.qualityclass_weight.set(qc_weight);
    }
    
    
}
