/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ingv.sit.tablemodels;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreabono
 */
public class Towns_List_items {
    private SimpleStringProperty name;
    //private SimpleStringProperty delta;
    private SimpleFloatProperty delta;
    private SimpleStringProperty prov;
    private SimpleLongProperty population;
 
    
    //------------------------------------------------------------------------------
    public Towns_List_items(String in_name, String in_prov, Float in_delta, Long in_population){
        name =new SimpleStringProperty(in_name);
        delta =new SimpleFloatProperty(in_delta);
        prov =new SimpleStringProperty(in_prov);
        population =new SimpleLongProperty(in_population);
    }    

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    /**
     * @return the delta
     */
    public Float getDelta() {
        return delta.get();
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(SimpleFloatProperty delta) {
        this.delta = delta;
    }

    /**
     * @return the prov
     */
    public String getProv() {
        return prov.get();
    }

    /**
     * @param prov the prov to set
     */
    public void setProv(SimpleStringProperty prov) {
        this.prov = prov;
    }

    /**
     * @return the population
     */
    public Long getPopulation() {
        return population.get();
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(SimpleLongProperty population) {
        this.population = population;
    }
}
