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
package org.ingv.sit.fdsn;


import edu.sc.seis.seisFile.fdsnws.FDSNEventQuerier;
import edu.sc.seis.seisFile.fdsnws.FDSNEventQueryParams;
import edu.sc.seis.seisFile.fdsnws.quakeml.EventIterator;
import edu.sc.seis.seisFile.fdsnws.quakeml.QuakeMLTagNames;
import edu.sc.seis.seisFile.fdsnws.quakeml.Quakeml;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ingv.sit.utils.pfxDialog;
/**
 *
 * @author andreabono
 */
public class FDSN_ws_reader {
//--------------------------------------------------------------------------------    
    public FDSN_ws_reader(){
        
       // Should do some basic input validation here...
      
    }
//--------------------------------------------------------------------------------    
    public EventIterator read_quakeml_list(String host, String time_start, String time_end, 
            float min_magnitude,
            float min_lat, float max_lat, float min_lon, float max_lon,
            float max_depth){
        try {
            FDSNEventQueryParams queryParams = new FDSNEventQueryParams();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            //    
            if (host.toUpperCase().contains("INGV"))
                queryParams.area(min_lat, max_lat, min_lon, max_lon);
            queryParams.setHost(host); //.replace("http://", ""));
            
            queryParams.setStartTime(sdf.parse(time_start).toInstant());
            queryParams.setEndTime(sdf.parse(time_end).toInstant());
            queryParams.setMaxDepth(max_depth);
            queryParams.setMinMagnitude(min_magnitude);
            queryParams.setOrderBy(FDSNEventQueryParams.ORDER_TIME_ASC);
        
            FDSNEventQuerier querier = new FDSNEventQuerier(queryParams);
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + querier.formURI().toURL().toString());
            Quakeml quakeml = querier.getQuakeML();
            //
            if (!quakeml.checkSchemaVersion()) {
                log_locally_for_bad_format(quakeml);
            }
            //          
            return quakeml.getEventParameters().getEvents();
            //
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(Level.SEVERE, 
                             ex.getMessage());
            pfxDialog.ShowErrorMessage("Error in QML list read.\n" + ex.getMessage(), null);
            return null;
        }
    }
//--------------------------------------------------------------------------------
    public edu.sc.seis.seisFile.fdsnws.quakeml.Event read_single_quakeml(String host, String event_id, boolean include_all_magnitudes, 
            boolean include_all_origins, boolean include_arrivals){
        try {
            FDSNEventQueryParams queryParams = new FDSNEventQueryParams();
            //
            queryParams.setHost(host.replace("http://", ""));
            queryParams.setEventid(event_id);
            queryParams.setIncludeAllMagnitudes(include_all_magnitudes);
            queryParams.setIncludeAllOrigins(include_all_origins);
            queryParams.setIncludeArrivals(include_arrivals);   
            
          //  queryParams.setParam("includeallstationsmagnitudes", true);
                       
            //queryParams.setOrderBy(FDSNEventQueryParams.ORDER_TIME_ASC);
            //
            FDSNEventQuerier querier = new FDSNEventQuerier(queryParams);
            Quakeml quakeml = querier.getQuakeML();
            if (!quakeml.checkSchemaVersion()) {
                log_locally_for_bad_format(quakeml);
            }
            //
            if (quakeml.getEventParameters().getEvents().hasNext())
                return quakeml.getEventParameters().getEvents().next();
            else
                return null;
            //
        } catch (Exception ex) {
            pfxDialog.ShowErrorMessage("Error in QML event read.\n" + ex.getMessage(),null);
            return null;
        }
    }
//--------------------------------------------------------------------------------
    private void log_locally_for_bad_format(Quakeml quakeml){
        Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, 
                "WARNING: XmlSchema of this document does not match this code, results may be incorrect." + "\n" + 
                        "XmlSchema (code): " + QuakeMLTagNames.CODE_MAIN_SCHEMA_VERSION + "\n" +
                        "XmlSchema (doc): " + quakeml.getSchemaVersion());
    }
//--------------------------------------------------------------------------------
    
    
}
