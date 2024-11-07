
package org.ingv.sit.sigpro;

import java.util.ArrayList;


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
public class _StationResponses {
    private ArrayList<_StationResponse_Station> StationResponses;
    
//--------------------------------------------------------------------------------        
    public _StationResponses() {
        StationResponses = new ArrayList<>();
    }
//--------------------------------------------------------------------------------
    public int get_nStations(){
        try{
            return this.getStationResponses().size();
        } catch (Exception ex) {
            return -1;
        }
    }
//--------------------------------------------------------------------------------        
    public void AddStation(_StationResponse_Station in_station){
        if (this.getStationResponses()==null) {
            this.setStationResponses(new ArrayList<_StationResponse_Station>());
        }
        this.getStationResponses().add(in_station);
    }
//-------------------------------------------------------------------------------- 
    public _StationResponse_Station getStation(int in_id) {
        try{            
            return this.getStationResponses().get(in_id);
        } catch (Exception ex) {
            return null;
        }
    }
//--------------------------------------------------------------------------------    
    public int FindStation(String in_StationCode){
        try{
            int id=-1;
            boolean fnd=false;
            
            while ((id < this.getStationResponses().size() && (!fnd))){
                if (this.getStationResponses().get(id).getCode().equalsIgnoreCase(in_StationCode)){
                    fnd = true;
                
                } else id++;
            }
            if (fnd)
                return id;
            else
                return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//--------------------------------------------------------------------------------        
    public void AddChannelToStation(String in_StationCode, _StationResponse_Channel in_channel){
        //
        int StationIndex = this.FindStation(in_StationCode);
        //
        if (StationIndex > -1){ // Found a station to add the channel
            this.getStationResponses().get(StationIndex).AddChannel(in_channel); 
        // else STATION IS NOT IN THE ARRAY... SHOULD BE ADDED?
        } else {
            this.getStationResponses().add(new _StationResponse_Station(in_StationCode));
            this.getStationResponses().get(this.getStationResponses().size()-1).AddChannel(in_channel);
        }
    }
    
    
//--------------------------------------------------------------------------------    

    /**
     * @return the StationResponses
     */
    public ArrayList<_StationResponse_Station> getStationResponses() {
        return StationResponses;
    }

    /**
     * @param StationResponses the StationResponses to set
     */
    public void setStationResponses(ArrayList<_StationResponse_Station> StationResponses) {
        this.StationResponses = StationResponses;
    }
}
