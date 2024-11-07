
package org.ingv.sit.datamodel;


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
public class Event_type {
    private Integer id;
    private String Description;
//--------------------------------------------------------------------------------
    public Event_type(Integer in_id, String in_description){
        id = in_id;
        Description = in_description;
    }
//--------------------------------------------------------------------------------    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
//--------------------------------------------------------------------------------
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
//--------------------------------------------------------------------------------
    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }
//--------------------------------------------------------------------------------
    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }
//--------------------------------------------------------------------------------
}
