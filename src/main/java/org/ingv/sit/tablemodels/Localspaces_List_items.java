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

public class Localspaces_List_items {
    private String environment; 
    private SimpleStringProperty name;   
    private SimpleStringProperty used;
    
//------------------------------------------------------------------------------
    public Localspaces_List_items(String in_environment, String in_name){

        //environment=new SimpleStringProperty(in_environment);  
        environment=in_environment;  
        name=new SimpleStringProperty(in_name);        
        used = new SimpleStringProperty("TRUE");
    }    
//------------------------------------------------------------------------------
    /**
     * @return the environment
     */
    public String getEnvironment() {
        return environment; //.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_env the environment to set
     */
    public void setEnvironment(String in_env) {
        //environment.set(in_env);
        environment=in_env;
    }    
//------------------------------------------------------------------------------
    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }
//------------------------------------------------------------------------------
    /**
     * @param in_name the name to set
     */
    public void setName(String in_name) {
        name.set(in_name);
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
