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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.ingv.dante.model.Environment;
import org.ingv.dante.model.ObjectLocalspaceForVw;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup;
import org.ingv.dante.model.ObjectOrigin;
import org.ingv.dante.model.ObjectOriginForMagnitudesOriginsEventsAndEventsGroup;
import org.ingv.dante.model.ObjectMagnitudesOriginsEventsAndEventsGroup;

public class LocationTreeItems_Lev2_new {
    private ObjectMagnitudesOriginsEventsAndEventsGroup data;
    private List<ObjectOrigin> children_level3;
    final private Long preferred_magnitude_id;
    //--------------------------------------------------------------------------
    public LocationTreeItems_Lev2_new(ObjectMagnitudesOriginsEventsAndEventsGroup in_data_in_a_row, 
            List<ObjectOrigin> in_child_locations, Long in_preferred_magnitude_id){
        data= in_data_in_a_row;
        children_level3=in_child_locations;
        preferred_magnitude_id=in_preferred_magnitude_id;
    }
    //--------------------------------------------------------------------------
    /**
     * @return the data
     */
    public ObjectMagnitudesOriginsEventsAndEventsGroup getData() {
        return data;
    }
    //--------------------------------------------------------------------------
    /**
     * @param data the data to set
     */
    public void setData(ObjectMagnitudesOriginsEventsAndEventsGroup data) {
        this.data = data;
    }
    //--------------------------------------------------------------------------
    /**
     * @return the children_level3
     */
    public List<ObjectOrigin> getChildren_level3() {
        return children_level3;
    }
    //--------------------------------------------------------------------------
    /**
     * @param children_level3 the children_level3 to set
     */
    public void setChildren_level3(ArrayList<ObjectOrigin> children_level3) {
        this.children_level3 = children_level3;
    }
    //--------------------------------------------------------------------------
    public ObjectMagnitudesOriginsEventsAndEventsGroup getChildren_ObjectOrigin_to_ObjectMagnitudesOriginsEventsAndEventsGroup(int child_id){
        try {
            ObjectMagnitudesOriginsEventsAndEventsGroup res=new ObjectMagnitudesOriginsEventsAndEventsGroup();
            res.setLocalspace(new ObjectLocalspaceForVw());
            res.getLocalspace().setName(children_level3.get(child_id).getLocalspace().getName());
            res.getLocalspace().setEnvironment(Environment.PRODUCTION);
            res.setId(this.children_level3.get(child_id).getId());
            res.setIdLocalspace(this.children_level3.get(child_id).getIdLocalspace());
                           
            res.setOrigin(new ObjectOriginForMagnitudesOriginsEventsAndEventsGroup());
            res.getOrigin().setId(this.children_level3.get(child_id).getId());
            res.getOrigin().setOt(this.children_level3.get(child_id).getOt());
            res.getOrigin().setRegion(this.children_level3.get(child_id).getRegion());
            res.getOrigin().setTypeOrigin(this.children_level3.get(child_id).getTypeOrigin());
            
            res.getOrigin().setQuality(this.children_level3.get(child_id).getQuality());

            if ((this.children_level3.get(child_id).getMagnitudes()!=null) && (!this.children_level3.get(child_id).getMagnitudes().isEmpty())){
                res.setMagnitude(new ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup());
                //-----------------------------------------------
                // Non è elegante, ma per ora funziona:
                res.getMagnitude().setMag(this.children_level3.get(child_id).getMagnitudes().get(FindPreferredMagnitudeId(children_level3.get(child_id).getMagnitudes())).getMag());
                //-----------------------------------------------
                res.getMagnitude().setTypeMagnitude(this.children_level3.get(child_id).getMagnitudes().get(0).getTypeMagnitude());
            }
    
            return res;
        } catch (Exception ex){
            return null;
        }
    }
    //--------------------------------------------------------------------------
    // Trova l'indice della magnitudo preferita tra quelle disponibili
    //--------------------------------------------------------------------------
    private int FindPreferredMagnitudeId(List<ObjectMagnitude> ms){
        try {
            for (int i=0; i<ms.size(); i++){
                if (Objects.equals(ms.get(i).getId(), this.preferred_magnitude_id))  
                    return i;
            }
            return 0;
        } catch (Exception ex){
            return 0;
        }
    }
    //--------------------------------------------------------------------------  
}
