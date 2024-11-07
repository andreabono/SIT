
package org.ingv.sit.tablemodels;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author andreabono
 */
public class FDSN_Events_List_items {
     public SimpleStringProperty id; 
     public SimpleStringProperty ot;
     public SimpleStringProperty mag; 
     public SimpleStringProperty qual;
     public SimpleStringProperty region; 
     public SimpleStringProperty version;
     public SimpleStringProperty localspace;
     
     // e un MODIFIED???
     
     
     public FDSN_Events_List_items(String in_id, String in_ot, String in_mag, 
             String in_qual, String in_region, String in_version, 
             String in_localspace){
        id =new SimpleStringProperty(in_id);
        ot =new SimpleStringProperty(in_ot);
        mag=new SimpleStringProperty(in_mag);
        qual=new SimpleStringProperty(in_qual);
        region=new SimpleStringProperty(in_region);
        version=new SimpleStringProperty(in_version);
        localspace=new SimpleStringProperty(in_localspace);
     }

    /**
     * @return the id
     */
    public String getId() {
        return id.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    /**
     * @return the ot
     */
    public String getOt() {
        return ot.get();
    }

    /**
     * @param ot the ot to set
     */
    public void setOt(SimpleStringProperty ot) {
        this.ot = ot;
    }

    /**
     * @return the mag
     */
    public String getMag() {
        return mag.get();
    }

    /**
     * @param mag the mag to set
     */
    public void setMag(SimpleStringProperty mag) {
        this.mag = mag;
    }

    /**
     * @return the qual
     */
    public String getQual() {
        return qual.get();
    }

    /**
     * @param qual the qual to set
     */
    public void setQual(SimpleStringProperty qual) {
        this.qual = qual;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region.get();
    }

    /**
     * @param region the region to set
     */
    public void setRegion(SimpleStringProperty region) {
        this.region = region;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version.get();
    }

    /**
     * @param version the version to set
     */
    public void setVersion(SimpleStringProperty version) {
        this.version = version;
    }

    /**
     * @return the localspace
     */
    public String getLocalspace() {
        return localspace.get();
    }

    /**
     * @param localspace the localspace to set
     */
    public void setLocalspace(SimpleStringProperty localspace) {
        this.localspace = localspace;
    }
}
