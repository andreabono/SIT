
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
public class _StationResponse_Station {
    private String Code;
    private ArrayList<_StationResponse_Channel> Channels;
//--------------------------------------------------------------------------------    
    public _StationResponse_Station(String in_Code){
        this.Code = in_Code;
    }
//--------------------------------------------------------------------------------    
    public void AddChannel(_StationResponse_Channel in_channel){     
        
        if (this.Channels == null) {
            this.Channels = new ArrayList<_StationResponse_Channel>();
        }     
        this.Channels.add(in_channel);
    }
//--------------------------------------------------------------------------------    
    public int FindChannel(String in_ChannelCode){
        try{
            
            int id=0;
            boolean fnd=false;
                  
            while ((id < this.Channels.size() && (!fnd))){
                
                if (this.Channels.get(id).getCode().equalsIgnoreCase(in_ChannelCode)){
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

    /**
     * @return the Code
     */
    public String getCode() {
        return Code;
    }
//--------------------------------------------------------------------------------
    /**
     * @param Code the Code to set
     */
    public void setCode(String Code) {
        this.Code = Code;
    }
//--------------------------------------------------------------------------------
    public _StationResponse_Channel getChannel(int id_channel) {
        try {
            if (this.Channels == null) return null;
            if (this.Channels.size() <= id_channel) return null;

            return this.Channels.get(id_channel);
        } catch (Exception ex) {
            return null;
        }      
    }
//--------------------------------------------------------------------------------
    public int get_nChannels(){
        try {
            return this.Channels.size();
        } catch (Exception ex) {
            return -1;
        }
    }
}
