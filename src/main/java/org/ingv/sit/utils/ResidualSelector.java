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
package org.ingv.sit.utils;

import java.util.ArrayList;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.AnchorPane;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.datamodel.Station;

/**
 *
 * @author andreabono
 */
public class ResidualSelector {
    private double DeltaMin, DeltaMax;
    private double ResMin, ResMax;
    
   private Selector selector;
   
    public void InitSelector(ScatterChart<?,?> scatter_chart, AnchorPane anchor_pane){
        selector= new Selector(scatter_chart, anchor_pane);
    }  
 
    private void CheckBounds(){
        double appo;
        if (DeltaMax < DeltaMin){
            appo= DeltaMax;
            DeltaMax=DeltaMin;
            DeltaMin=appo;
        }
        if (ResMax < ResMin) {
            appo= ResMax;
            ResMax=ResMin;
            ResMin=appo;
        }
    }
//------------------------------------------------------------------------------        
    public ArrayList FireStationsSelection(Event e){
        ArrayList Selected_Stations=new ArrayList();
        //Selected_Stations=null;
        Station s=null;
        if (e==null) return null;
        if (e.getNStations()==0) return null;
        
        String[] element; 
        
        CheckBounds();
                               
        for (int i=0; i< e.getNStations(); i++) {
            s = e.getStation(i);
            
            if (s.getNPhases()>0) {
                //---------------------------------------------------------------
                // If there is a Phase ==> Residuals and Distance must be checked
                //---------------------------------------------------------------
                for (int j=0; j<s.getNPhases(); j++){
                    if ((this.DeltaMin <= s.getPhase(j).getEpDistanceKm()) && (s.getPhase(j).getEpDistanceKm() <= this.DeltaMax)) {
                        if ((this.ResMin <= s.getPhase(j).getResidual()) && (s.getPhase(j).getResidual() <= this.ResMax)) {
                            //Selected_Stations.add(s.getCode());
                            element = new String[4];
                            element[0] = s.getCode();
                            element[1] = String.valueOf(s.getLat());
                            element[2] = String.valueOf(s.getLon());
                            element[3] ="T";
                            
                            Selected_Stations.add((String[])element); //.clone());
                        }
                    }
                }
                
                
            } else {
                //---------------------------------------------------------------
                // If there is NO Phase ==> Only Distance must be checked
                //---------------------------------------------------------------
                // ......
            }
        }
        
        return Selected_Stations;
    }
//------------------------------------------------------------------------------    
    /**
     * @return the DeltaMin
     */
    public double getDeltaMin() {
        return DeltaMin;
    }

    /**
     * @param DeltaMin the DeltaMin to set
     */
    public void setDeltaMin(double DeltaMin) {
        this.DeltaMin = DeltaMin;
    }

    /**
     * @return the DeltaMax
     */
    public double getDeltaMax() {
        return DeltaMax;
    }

    /**
     * @param DeltaMax the DeltaMax to set
     */
    public void setDeltaMax(double DeltaMax) {
        this.DeltaMax = DeltaMax;
    }

    /**
     * @return the ResMin
     */
    public double getResMin() {
        return ResMin;
    }

    /**
     * @param ResMin the ResMin to set
     */
    public void setResMin(double ResMin) {
        this.ResMin = ResMin;
    }

    /**
     * @return the ResMax
     */
    public double getResMax() {
        return ResMax;
    }

    /**
     * @param ResMax the ResMax to set
     */
    public void setResMax(double ResMax) {
        this.ResMax = ResMax;
    }
  
}
