
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
public class Phases_List_items_terna {
    private SimpleStringProperty pick_name;
    private SimpleStringProperty pick_time;
    private SimpleStringProperty error_plus;
    private SimpleStringProperty error_minus;
    private SimpleFloatProperty hypo_weight;
    private SimpleIntegerProperty qualityclass_weight;
//--------------------------------------------------------------------------------    
    public Phases_List_items_terna(String in_pick_name, String in_pick_time, 
            String in_error_plus, String in_error_minus, Float in_hypo_weight,
            Integer in_quality_class){
        pick_name=new SimpleStringProperty(in_pick_name);
        pick_time=new SimpleStringProperty(in_pick_time);
        error_plus =new SimpleStringProperty(in_error_plus);
        error_minus =new SimpleStringProperty(in_error_minus);
        hypo_weight = new SimpleFloatProperty(in_hypo_weight);
        qualityclass_weight = new SimpleIntegerProperty(in_quality_class);
    }
//--------------------------------------------------------------------------------

    /**
     * @return the pick_name
     */
    public String getPick_name() {
        return pick_name.get();
    }

    /**
     * @param pick_name the pick_name to set
     */
    public void setPick_name(String pick_name) {
        this.pick_name.set(pick_name); 
    }

    /**
     * @return the pick_time
     */
    public String getPick_time() {
        String res = pick_time.get().replace("Z", "");
        if (res.length()>11) res = res.substring(0,11);
        
        return res;
        //return pick_time.get();
    }

    /**
     * @param pick_time the pick_time to set
     */
    public void setPick_time(String pick_time) {
        this.pick_time.set(pick_time);
    }

    /**
     * @return the error_plus
     */
    public String getError_plus() {
        return error_plus.get();
    }

    /**
     * @param error_plus the error_plus to set
     */
    public void setError_plus(String error_plus) {
        this.error_plus.set(error_plus);
    }

    /**
     * @return the error_minus
     */
    public String getError_minus() {
        return error_minus.get();
    }

    /**
     * @param error_minus the error_minus to set
     */
    public void setError_minus(String error_minus) {
        this.error_minus.set(error_minus);
    }

    /**
     * @return the hypo_weight
     */
    public float getHypo_weight() {
        return hypo_weight.get();
    }

    /**
     * @param hypo_weight the hypo_weight to set
     */
    public void setHypo_weight(float hypo_weight) {
        this.hypo_weight.set(hypo_weight);
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
