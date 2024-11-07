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

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreabono
 */
public class Versions_List_items {
    private SimpleStringProperty version_value;   
    private SimpleStringProperty version_description;   
    private SimpleStringProperty used;
    
//------------------------------------------------------------------------------
    public Versions_List_items(String in_value, String in_description){
        version_value=new SimpleStringProperty(in_value);      
        version_description=new SimpleStringProperty(in_description); 
        used = new SimpleStringProperty("TRUE");
    }    
//------------------------------------------------------------------------------
    /**
     * @return the version_value
     */
    public String getVersion_value() {
        return version_value.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_name the version_value to set
     */
    public void setVersion_value(String in_name) {
        version_value.set(in_name);
    }
//------------------------------------------------------------------------------
    /**
     * @return the version_value
     */
    public String getVersion_description() {
        return version_description.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_name the version_value to set
     */
    public void setVersion_description(String in_desc) {
        version_description.set(in_desc);
    }
//------------------------------------------------------------------------------
    /**
     * @return the used
     */
    public SimpleStringProperty getUsed() {
        return used;
    }
//------------------------------------------------------------------------------
    /**
     * @param used the used to set
     */
    public void setUsed(SimpleStringProperty used) {
        this.used = used;
    }
//------------------------------------------------------------------------------  
}
