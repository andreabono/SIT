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
package org.ingv.sit.datamodel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;
import org.ingv.dante.api.GetApi;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200Response;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200ResponseDataInner;
import org.ingv.sit.App;

public class TownsListReader {
    private Double Lat;
    private Double Lon;
    private Double minDist;
    private Double maxDist;
    private Long minPopulation;
//    
    //private List<InlineResponse2004Data> TownsInfo;
//--------------------------------------------------------------------------------        
    public TownsListReader(Double in_minDist, Double in_maxDist, Long in_minPopulation, Double in_Lat, Double in_Lon){
        minDist=in_minDist;
        maxDist = in_maxDist;
        minPopulation = in_minPopulation;
        Lat = in_Lat;
        Lon = in_Lon;
    }
//--------------------------------------------------------------------------------      
    public List<GetMunicipiDistanceKmPopolazione200ResponseDataInner> Read() {
        // Legge tramite web-services le informazioni sulle città più vicine a lat, lon
        GetApi ReadClient;
        try {  
            ReadClient = new GetApi();
            ReadClient.getApiClient().setReadTimeout(30000);
            App.logger.debug("WS-LOG: ReadClient.getMunicipiDistanceKmPopolazione reading");
            GetMunicipiDistanceKmPopolazione200Response request_output;
            
            request_output=ReadClient.getMunicipiDistanceKmPopolazione(minDist, maxDist, minPopulation, Lat, Lon);
            if (request_output.getData()!=null){
                App.logger.debug("WS-LOG: ReadClient.getMunicipiDistanceKmPopolazione received response");
                return request_output.getData();
            } else {
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.WARNING, 
                    "Error reading towns list from service");
            }
                        
            return null;
        } catch (Exception ex) {
            return null;
        } finally {
            ReadClient=null;
        }
    }


    /**
     * @return the Lat
     */
    public Double getLat() {
        return Lat;
    }

    /**
     * @param Lat the Lat to set
     */
    public void setLat(Double Lat) {
        this.Lat = Lat;
    }

    /**
     * @return the Lon
     */
    public Double getLon() {
        return Lon;
    }

    /**
     * @param Lon the Lon to set
     */
    public void setLon(Double Lon) {
        this.Lon = Lon;
    }

    /**
     * @return the minDist
     */
    public Double getMinDist() {
        return minDist;
    }

    /**
     * @param minDist the minDist to set
     */
    public void setMinDist(Double minDist) {
        this.minDist = minDist;
    }

    /**
     * @return the maxDist
     */
    public Double getMaxDist() {
        return maxDist;
    }

    /**
     * @param maxDist the maxDist to set
     */
    public void setMaxDist(Double maxDist) {
        this.maxDist = maxDist;
    }

    /**
     * @return the minPopulation
     */
    public Long getMinPopulation() {
        return minPopulation;
    }

    /**
     * @param minPopulation the minPopulation to set
     */
    public void setMinPopulation(Long minPopulation) {
        this.minPopulation = minPopulation;
    }  
}
