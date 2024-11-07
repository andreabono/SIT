
package org.ingv.sit.sigpro;


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
public class _StationResponse_Channel {
    private String Code;
    public String LocationCode;
    private _SAC_ResponseStruct SAC_Response_Structure;
//--------------------------------------------------------------------------------    
    public _StationResponse_Channel(String in_Code){
        this.Code = in_Code;
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

    /**
     * @return the SAC_Response_Structure
     */
    public _SAC_ResponseStruct getSAC_Response_Structure() {
        return SAC_Response_Structure;
    }
//--------------------------------------------------------------------------------
    /**
     * @param SAC_Response_Structure the SAC_Response_Structure to set
     */
    public void setSAC_Response_Structure(_SAC_ResponseStruct SAC_Response_Structure) {
        this.SAC_Response_Structure = SAC_Response_Structure;
    }

    /**
     * @return the LocationCode
     */
    public String getLocationCode() {
        return LocationCode;
    }

    /**
     * @param LocationCode the LocationCode to set
     */
    public void setLocationCode(String inLocationCode) {
        this.LocationCode = inLocationCode;
    }
}
