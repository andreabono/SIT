
package org.ingv.sit.utils;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class myStationxml extends edu.sc.seis.seisFile.fdsnws.stationxml.Station {
    
    public myStationxml(){
        
    }
    
    public myStationxml(edu.sc.seis.seisFile.fdsnws.stationxml.Station inS){
        this.setCode(inS.getCode());
        this.setName(inS.getName());
        this.setChannelList(inS.getChannelList());
        this.setAlternateCode(inS.getAlternateCode());
        this.setCommentList(inS.getCommentList());
        this.setCreationDate(inS.getCreationDate());
        this.setDataAvailability(inS.getDataAvailability());
        this.setDescription(inS.getDescription());
        this.setElevation(inS.getElevation());
        this.setEndDate(inS.getEndDate());
        this.setEquipmentList(inS.getEquipmentList());
        this.setExternalReferenceList(inS.getExternalReferenceList());
        this.setGeology(inS.getGeology());
        this.setHistoricalCode(inS.getHistoricalCode());
        this.setLatitude(inS.getLatitude());
        this.setLongitude(inS.getLongitude());
        this.setNetwork(inS.getNetwork());
        this.setOperatorList(inS.getOperatorList());
        this.setRestrictedStatus(inS.getRestrictedStatus());
        this.setSelectedNumChannels(inS.getSelectedNumChannels());
    }
}
