/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
package org.ingv.sit.datamodel;

import edu.iris.dmc.seedcodec.CodecException;
import edu.sc.seis.seisFile.ChannelTimeWindow;
import edu.sc.seis.seisFile.fdsnws.FDSNDataSelectQuerier;
import edu.sc.seis.seisFile.fdsnws.FDSNDataSelectQueryParams;
import edu.sc.seis.seisFile.fdsnws.quakeml.EventIterator;
import edu.sc.seis.seisFile.fdsnws.quakeml.Origin;
import edu.sc.seis.seisFile.fdsnws.quakeml.Pick;
import edu.sc.seis.seisFile.fdsnws.quakeml.Quakeml;
import edu.sc.seis.seisFile.mseed.DataRecord;
import edu.sc.seis.seisFile.mseed.DataRecordIterator;
import edu.sc.seis.seisFile.mseed.SeedFormatException;
import edu.sc.seis.seisFile.sac.SacTimeSeries;
//import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import org.ingv.dante.api.GetApi;
import org.ingv.dante.api.UpdateApi;
import org.ingv.dante.model.GetEvent200Response;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200ResponseDataInner;
import org.ingv.dante.model.ObjectAmplitude;
import org.ingv.dante.model.ObjectAmplitudeTypeAmplitude;

import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectEvent;
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectOrigin;
import org.ingv.dante.model.ObjectPick;
import org.ingv.dante.model.ObjectProvenance;
import org.ingv.dante.model.ObjectStationmagnitude;

import org.ingv.dante.model.PickEmersio;
import org.ingv.dante.model.ProvenanceEvaluationmode;
import org.ingv.dante.model.UpdateEventRequest;
import org.ingv.dante.model.UpdateEventRequestData;
import org.ingv.dante.model.UpdateEventRequestDataEvent;

import org.ingv.sit.App;
import org.ingv.sit.WavesFormController;
import org.ingv.sit.datamodel.DataSource.dsType;
import org.ingv.sit.earthworm.EW_Host;
import org.ingv.sit.earthworm.EW_Wave_Server_Client;
import org.ingv.sit.fdsn.QuakeML_Utils;
import org.ingv.sit.utils.MiniSeedToFloatArray;
import org.ingv.sit.utils.StationsComparator;
import org.ingv.sit.utils.duration_buffer_item;
import org.ingv.sit.utils.sitDialog;

public class Event { 
    private EW_Host EW_SERVER;
    private ArrayList<Station>  Stations;
    private List<GetMunicipiDistanceKmPopolazione200ResponseDataInner> TownsInfo;
    private int nOpenWaves;
    private int ActiveStationID;
    private Terna ActiveTerna; 
    private String waves_path;
    private String waves_path_WA;
    private String original_hostname;   
    //private String work_event_ID;
    private Long work_event_ID;
    private Long work_origin_ID;  
    public ObjectEvent innerObjectEvent; 
    ObjectLocalspace sitLocalSpace;
    private ObjectProvenance sitProvenance;        
       
    private boolean AddingStations=false;
    public int idController;   
    private boolean another_task=false;
//------------------------------------------------------------------------------     
    public Event(int in_idController){
        innerObjectEvent = new ObjectEvent();
        idController=in_idController;
    }
//------------------------------------------------------------------------------ 
    private void init_ew_server(){
        int first_host_id = App.G.options.getFirstUsedEwHostIndex();
        //---------------------------
        // SET-UP THE EARTHWORM HOST
        //---------------------------
        if (first_host_id!=-1)
            EW_SERVER=new EW_Host(App.G.options.getDatasources_EW().get(first_host_id).getHostname(), App.G.options.getDatasources_EW().get(first_host_id).getPortslist());   
        else
            EW_SERVER=null;
    }
//------------------------------------------------------------------------------    
    public boolean read(Long originId) {
        sitLocalSpace = new ObjectLocalspace(); 
        sitLocalSpace.setName("sit");
        sitLocalSpace.setDescription("Manually reviewed");    // Differenziare per bollettino

        setSitProvenance(new ObjectProvenance()); 
        getSitProvenance().setName("INGV");
        getSitProvenance().setDescription("SIT interactive revision");
        getSitProvenance().setHostname(App.G.sitHostname);
        getSitProvenance().setMethod("ToDoSIT");
        getSitProvenance().setModel("ToDoSIT");
        getSitProvenance().setParameters("ToDoSIT");
        getSitProvenance().setProgram("sit");
        getSitProvenance().setSoftwarename("SIT");
        getSitProvenance().setUsername(App.G.sitUsername);
        getSitProvenance().setVersion("1.0");
        getSitProvenance().setEvaluationmode(ProvenanceEvaluationmode.MANUAL);
        if ((App.G.User!=null)&&(App.G.User.isLoggedIn()))
            getSitProvenance().setUsername(App.G.User.getName());
        
        /*
        Reads an event via the DANTE web-service starting from the originId of 
        the hypocenter (origin) 
        */  
        GetApi ReadClient;
        try {
            String STAZ;
            ReadClient = new GetApi();
            ReadClient.getApiClient().setReadTimeout(30000);
            
            App.logger.debug("WS-LOG: Event.read()--> ReadClient.getEvent reading");
            GetEvent200Response myResp= ReadClient.getEvent(originId, null, null,null,null);
           
            if (myResp!=null){     
                App.logger.debug("WS-LOG: Event.read()--> ReadClient.getEvent received response");
                //java.util.logging.Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.INFO, myResp.getData().toString());
                if (myResp.getData().getEvent()==null) {
                    // No origin found
                    return false;
                } else {   
                    // 
                    innerObjectEvent = myResp.getData().getEvent();
                                        
                    work_event_ID=myResp.getData().getEvent().getId(); 
                    setWork_origin_ID(myResp.getData().getEvent().getOrigins().get(0).getId());
                                                           
                    if (myResp.getData().getEvent().getLocalspace()!=null && myResp.getData().getEvent().getLocalspace().getName()!=null){
                        String nome;
                        if (myResp.getData().getEvent().getLocalspace().getName().contains("_"))
                            nome = myResp.getData().getEvent().getLocalspace().getName().substring(0, myResp.getData().getEvent().getLocalspace().getName().indexOf("_")).toUpperCase();
                        else 
                            nome = myResp.getData().getEvent().getLocalspace().getName().toUpperCase();
                        switch (nome){
                            case "HEW10":
                                original_hostname = "hew1.int.ingv.it";
                                break;
                            case "HEW20":
                                original_hostname = "hew2.int.ingv.it";
                                break;    
                            case "HEW3":
                                original_hostname = "hew3.int.ingv.it";
                                break;  
                            case "HEW19":
                                original_hostname = "hew19.int.ingv.it";
                                break;  
                            case "HEW29":
                                original_hostname = "hew29.int.ingv.it";
                                break;    
                            default:
                                original_hostname = "hew1.int.ingv.it";
                                break;
                        }
                    } else this.original_hostname = "hew1.int.ingv.it";
                    //
                    //----------------------------------------------------------
                    //
                    if (myResp.getData().getEvent().getOrigins()==null) return false;   // Event has no origins
                    //
                    ObjectMagnitude Md = searchMd();
                    
                    // Search for an Md Magnitude
                   // ObjectMagnitude Md=null; innerObjectEvent.getOrigins().get(0).getMagnitudes()
                    // ------------------------------------------------------------
                    // Stations and Phases and picks...    
                    // ------------------------------------------------------------ 
                    if (innerObjectEvent.getOrigins().get(0).getArrivals()!=null) {
                        if (!innerObjectEvent.getOrigins().get(0).getArrivals().isEmpty()) {
                            Stations = new ArrayList();
                            // PARSE DEL JsonArray e lettura delle fasi
                            for (ObjectArrival tmpPh1 : innerObjectEvent.getOrigins().get(0).getArrivals()) {                        
                                //
                                STAZ = ((ObjectArrival)tmpPh1).getPick().getSta();
                                                            
                                //             
                                Station s;
                                int idStazione = StationCode_to_StationId(STAZ);   
                                ObjectArrival p;                                
                                p = tmpPh1; // 20211004
                                
                                // patch for legacy pick uncertainties and deltakm coming from old INGV database
                                p = PatchUncert(p);
                                p = PatchDelta(p);

                                if (idStazione>-1) {
                                    // La stazione è gia nell'evento
                                    // Quindi aggiungo solo una nuova fase
                                    s = (Station)(Stations.get(idStazione));
                                    //p.setIdStation(idStazione);
                                    s.addPhase(p);
                                } else {
                                    // La stazione non è ancora nell'evento
                                    s = new Station(STAZ, App.G.SeismicNet.getStations());
                                    //                                                                 
                                    s.setNetwork(((ObjectArrival)tmpPh1).getPick().getNet());
                           //         s.setLocation(((ObjectArrival)tmpPh1).getLoc());  // 20230413
                           
                                    //s.setChannels(App.G.SeismicNet.FindChannels(STAZ));
                                    
                                    // Se l'evento ha una MD aggiunge la DURATA
                                    if (Md!=null){
                                        s.RecoverDuration(Md);
                                    }
                                    
                                    s.addPhase(p);
                                    this.Stations.add(s);  
                                }
                            }
                            
                            Collections.sort(Stations, new StationsComparator());
                        } //else {
                            // No phases: rise an exception 
                          //  return false;
                        //}
                        
                        // Sort stations by Distance
                        //Collections.sort(Stations, new StationsComparator());
//                    } else {
//                        // No phases: rise an exception 
//                        return false;
                    }
 
                }          
            }   
            //--------------------------------------------------------------------------
            // When available and configured in datasources_waveforms.xml, adds 
            // a "first level" Earthworm server to read latencies and recover waves
            //--------------------------------------------------------------------------
            init_ew_server();
            //--------------------------------------------------------------------------
            //
//            this.setTowns(new TownsListReader());            
//            this.getTowns().Read(50, this.getOrigins().get(0).getLat(), this.getOrigins().get(0).getLon()); 
            
            TownsInfo = new TownsListReader(0.0, 50.0, Long.valueOf("0"), innerObjectEvent.getOrigins().get(0).getLat(), innerObjectEvent.getOrigins().get(0).getLon()).Read();
                      
            //
            return true;
        } catch (Exception ex) {
            //pfxDialog.ShowErrorMessage("Error reading event!", null);
            return false;
        } finally {
            ReadClient = null;
        }
    }
    
    
    public void RefreshTownsInfo(){
        try{
            TownsInfo = new TownsListReader(0.0, 50.0, Long.valueOf("0"), innerObjectEvent.getOrigins().get(0).getLat(), innerObjectEvent.getOrigins().get(0).getLon()).Read();
        } catch (Exception ex){
        }
    }
    
    private ObjectArrival PatchUncert(ObjectArrival inP){
        if (inP.getPick()==null) return inP;
        
        if ((inP.getPick().getUpperUncertainty()==null) && (inP.getPick().getLowerUncertainty()!=null)){
            inP.getPick().setUpperUncertainty(inP.getPick().getLowerUncertainty());
        }
        if ((inP.getPick().getUpperUncertainty()!=null) && (inP.getPick().getLowerUncertainty()==null)){
            inP.getPick().setLowerUncertainty(inP.getPick().getUpperUncertainty());
        }
        
        return inP;
    }
    
    private ObjectArrival PatchDelta(ObjectArrival inP){
        
        if ((inP.getEpDistanceKm()!=null) && (inP.getEpDistanceKm()==0))
            inP.setEpDistanceKm((float)9999.99);
        
        
        return inP;
    }
    private ObjectMagnitude searchMd(){
        try{
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes()==null) return null;
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty()) return null;
            for (int i=0; i<innerObjectEvent.getOrigins().get(0).getMagnitudes().size(); i++){
                if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("MD"))
                        return innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i);
            }
            return null;
        } catch (Exception ex){
            return null;
        }
    }
//------------------------------------------------------------------------------  
    public boolean read(edu.sc.seis.seisFile.fdsnws.quakeml.Event QMLevent) {      
        /*
        Reads an event parsing an FDSN quakeml.event object.
        quakeml.event il described in edu.sc.seis.seisFile.fdsnws.quakeml.*
        */  
        
        try { 
            String STAZ="";
            QuakeML_Utils QML_UTILS = new QuakeML_Utils(QMLevent);
              
            sitLocalSpace = new ObjectLocalspace(); 
            sitLocalSpace.setName("SIT");
            sitLocalSpace.setDescription("Manually reviewed");    // Differenziare per bollettino
            innerObjectEvent.setLocalspace(sitLocalSpace);

            setSitProvenance(new ObjectProvenance()); 
            getSitProvenance().setName("INGV");
            getSitProvenance().setDescription("SIT interactive revision");
            getSitProvenance().setHostname(App.G.sitHostname);
            getSitProvenance().setMethod("ToDoSIT");
            getSitProvenance().setModel("ToDoSIT");
            getSitProvenance().setParameters("ToDoSIT");
            getSitProvenance().setProgram("SIT");
            getSitProvenance().setSoftwarename("SIT");
            getSitProvenance().setUsername(App.G.sitUsername);
            getSitProvenance().setVersion("1.0");
            getSitProvenance().setEvaluationmode(ProvenanceEvaluationmode.MANUAL);
                            
            //-------------
            // Origin
            //-------------
            int id_origin = FindBestAvailableOrigin(QMLevent.getOriginList());
            
            //edu.sc.seis.seisFile.fdsnws.quakeml.Origin o = QMLevent.getOriginList().get(QMLevent.getOriginList().size()-2);
            edu.sc.seis.seisFile.fdsnws.quakeml.Origin o = QMLevent.getOriginList().get(id_origin);
            if (QMLevent.getPublicId().contains("=")) {
                work_event_ID = Long.valueOf(QMLevent.getPublicId().toString().substring(QMLevent.getPublicId().toString().indexOf("=")+1));
            } else if (QMLevent.getPublicId().contains("/")) {
                work_event_ID = Long.valueOf(QMLevent.getPublicId().substring(QMLevent.getPublicId().lastIndexOf("/")+1));
            } else {
                work_event_ID = Long.valueOf(QMLevent.getPublicId());
            }
            //
            innerObjectEvent.setTypeEvent(QMLevent.getType()); 
        
            innerObjectEvent.setOrigins(new ArrayList<>());
            ObjectOrigin tmpOrigin = new ObjectOrigin();
            tmpOrigin.setLat(Double.valueOf(o.getLatitude().getValue()));
            tmpOrigin.setLon(Double.valueOf(o.getLongitude().getValue()));
            tmpOrigin.setDepth(o.getDepth().getValue()/1000.0);         
            //
            if (o.getQuality()!=null) {
                tmpOrigin.setRms(Double.valueOf(o.getQuality().getStandardError().toString()));      
                tmpOrigin.setAzimGap(o.getQuality().getAzimuthalGap());       
            }
                 
            java.time.LocalDateTime appotime = java.time.LocalDateTime.parse(o.getTime().getValue().replace("T", " ").substring(0, 22), java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS").withZone(ZoneId.of("UTC")));
            tmpOrigin.setOt(appotime.atOffset(ZoneOffset.UTC));
            
            tmpOrigin.setProvenance(null);

            if (QMLevent.getDescriptionList().get(0).getText() != null){
                tmpOrigin.setRegion(QMLevent.getDescriptionList().get(0).getText());               
            } else {tmpOrigin.setRegion("Unknown area");}
//            
            //-------------
            // Magnitudes
            //-------------
            List<edu.sc.seis.seisFile.fdsnws.quakeml.Magnitude> tmpMagArray = QMLevent.getMagnitudeList() ;
            if (tmpMagArray != null) {
                if (!tmpMagArray.isEmpty()) {
                    if (tmpOrigin.getMagnitudes()==null){
                        tmpOrigin.setMagnitudes(new ArrayList<>());
                    }
                    Long tmpID=Long.valueOf("0");
                    for (edu.sc.seis.seisFile.fdsnws.quakeml.Magnitude tmpM1 : tmpMagArray) {
                        //ObjectMagnitude m = new ObjectMagnitude(); 
                        //Fills in some fields for the new magnitude object
                        
                        ObjectMagnitude m=null;
                        String ID = tmpM1.getPublicId();
                        
                        if (ID.contains("=")) {
                            ID=ID.substring(ID.lastIndexOf("=")+1);
                        } else if (ID.contains("/")) {
                            ID=ID.substring(ID.lastIndexOf("/")+1);
                        }
                        
//                        if (ID.toUpperCase().contains("INGV") || ID.toUpperCase().contains("IRIS")){
//                            ID=ID.substring(ID.lastIndexOf("=")+1);
//                            m = new ObjectMagnitude(Long.valueOf(ID),null, null);
//                        } else {
//                            //m = new ObjectMagnitude(ID,null, null);  <<<<<<<<<<<<<< SHOULD BE THIS ONE!!!!
//                            m = new ObjectMagnitude(Long.valueOf(ID),null, null);
//                        }
                        
                        if (!App.G.IsNumeric(ID)){
                            tmpID++;
                            ID = tmpID.toString();
                        }


                        m = new ObjectMagnitude(Long.valueOf(ID),null, null,null);
                        if (m!=null) {
                            m.setMag(Double.valueOf(tmpM1.getMag().getValue()));
                            if (tmpM1.getStationCount()!=null)
                                m.setNsta(Long.valueOf(tmpM1.getStationCount()));


                            m.setTypeMagnitude(tmpM1.getType());
                            if (tmpM1.getMag().getConfidenceLevel()!=null)
                                m.setQuality(Double.valueOf(tmpM1.getMag().getConfidenceLevel()));

                            // Deve leggere le station magnitudes
                            if (!tmpM1.getStationMagnitudeContributionList().isEmpty()){
                                m.setNcha(Long.valueOf(tmpM1.getStationMagnitudeContributionList().size()));
                                m.setStationmagnitudes(new ArrayList<>());
                            }
                            //
                            tmpOrigin.getMagnitudes().add(m);
                        }  
                    }
                }
            }
//            
            innerObjectEvent.getOrigins().add(tmpOrigin);
//                       
            TownsInfo = new TownsListReader(0.0, 50.0, Long.valueOf("0"), innerObjectEvent.getOrigins().get(0).getLat(), innerObjectEvent.getOrigins().get(0).getLon()).Read();
//          
            // ------------------------------------------------------------
            // Stations and Arrivals   
            // ------------------------------------------------------------          
            List<edu.sc.seis.seisFile.fdsnws.quakeml.Arrival> tmpPhArray = o.getArrivalList();
            LocalDateTime time_ph;
            if (tmpPhArray != null) {
                if (tmpPhArray.size()>0) {    
                    this.Stations = new ArrayList();
                    //
                    //for (Pick tmpArr1 : tmpPhArray) { 
                    for (edu.sc.seis.seisFile.fdsnws.quakeml.Arrival tmpArr1 : tmpPhArray) { 
                       // STAZ = tmpArr1.getWaveformID().getStationCode(); 
                       
                        Pick corresp_pick = QML_UTILS.Arrival_2_Pick(tmpArr1);
                        if (corresp_pick != null) {

                            STAZ = corresp_pick.getWaveformID().getStationCode(); //  QML_UTILS.pickID_2_StationCode(tmpArr1.getPickID());
                            //
                            Station s;
                            int idStazione = this.StationCode_to_StationId(STAZ); //this.StationIndex(STAZ);

                            //Phase p = new Phase(tmpArr1, corresp_pick);   
                            ObjectArrival p = new ObjectArrival(); 
                            p.setPick(new ObjectPick());
                            // p.set(....)
                            p.getPick().setSta(corresp_pick.getWaveformID().getStationCode());
                            p.getPick().setCha(corresp_pick.getWaveformID().getChannelCode());
                            p.getPick().setNet(corresp_pick.getWaveformID().getNetworkCode());
                            p.getPick().setLoc(corresp_pick.getWaveformID().getLocationCode());
                            p.setEpDistanceKm(tmpArr1.getDistance());
                            try {
                                time_ph = LocalDateTime.parse(corresp_pick.getTime().getValue().replace("T", " ").substring(0, 22), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS").withZone(ZoneId.of("UTC")));            
                            } catch (Exception e) {
                                time_ph = LocalDateTime.parse(corresp_pick.getTime().getValue().replace("T", " ").substring(0, 21), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S").withZone(ZoneId.of("UTC")));            
                            }
                            p.getPick().setArrivalTime(time_ph.atOffset(ZoneOffset.UTC));
                            p.setAzimut(tmpArr1.getAzimuth());
                            p.getPick().setEmersio(this.findOnset(corresp_pick.getOnset())); //  corresp_pick.getOnset());
                            p.setIscCode(tmpArr1.getPhase());
                            p.setResidual(Double.valueOf(tmpArr1.getTimeResidual()));
                            p.setWeight(Double.valueOf(tmpArr1.getTimeWeight()));
                            // Controllare
                            p.getPick().setQualityClass(0);

                            if (idStazione>-1) {
                                //-------------------------------------------
                                // STAZ is already part of the Event:
                                // only a phase add is needed
                                //-------------------------------------------
                                s = (Station)(this.Stations.get(idStazione));
                              //  p.setIdStation(idStazione);
                                s.addPhase(p);
                            } else {
                                //-------------------------------------------
                                // STAZ is not yet in the Event:
                                // a Station.add and a Phase.add are needed
                                //-------------------------------------------
                                s = new Station(STAZ, App.G.SeismicNet.getStations());
                                s.setNetwork(corresp_pick.getWaveformID().getNetworkCode());
                               // s.setLocation(corresp_pick.getWaveformID().getLocationCode());               // 20230413                                   
                              
    //                                    
                                s.addPhase(p);
                                this.Stations.add(s);  
                            }
                        }
                    }
                } else {
                    // No phases: "true" if an event can have no phase
                    //
                    
                    return true; // false;
                }
            } else {
                // if QMLevent.getPickList() is null return false 
                return false;
            }
//         
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    private PickEmersio findOnset(String in_onset){
        PickEmersio res = null; //PickEmersio.NULL;
        if (in_onset==null) return null;
        if (in_onset.toUpperCase().contains("I"))
            res  = PickEmersio.I;
        else
            if (in_onset.toUpperCase().contains("E"))
                res = PickEmersio.E;               
        //
        return res;
    }
    
    
    
    private int FindBestAvailableOrigin(List<Origin> origins){ 
        //--------------------------------------------------
        // Trova la origin AUTOMATICA più recente
        //--------------------------------------------------
        try{
            if (origins==null) return -1;
            if (origins.isEmpty()) return -1;
            
            if (origins.size()==1) return 0;
            
            int outId=0;
            
            if (origins.get(0).getCreationInfo()==null) return 0;
            
            LocalDateTime most_recent_time = parseLocalDateTime(origins.get(0).getCreationInfo().getCreationTime());
            
            for (int i=1; i< origins.size(); i++){
                
                if(parseLocalDateTime(origins.get(i).getCreationInfo().getCreationTime()).isAfter(most_recent_time) && origins.get(i).getEvaluationMode().equalsIgnoreCase("AUTOMATIC")){
                    outId = i;
                    most_recent_time = parseLocalDateTime(origins.get(i).getCreationInfo().getCreationTime());
                }
            }
            
            
            return outId;
        } catch (Exception ex){
            return -1;
        }
    }
    
    public static LocalDateTime parseLocalDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(input, formatter);
    }
//--------------------------------------------------------------------------------       
    public boolean read(edu.sc.seis.seisFile.fdsnws.quakeml.Event QMLevent, int hypocenterID) {
    return false;
////////        /*
////////        Reads an event parsing an FDSN quakeml.event object.
////////        quakeml.event il described in edu.sc.seis.seisFile.fdsnws.quakeml.*
////////        */  
////////        try { 
////////            Origin o=null;
////////            String STAZ="";
////////            QuakeML_Utils QML_UTILS = new QuakeML_Utils(QMLevent);
////////            //-------------
////////            // Origin
////////            //-------------
////////            boolean fnd=false;
////////            int idx=0;
////////            String val;
////////            while ((idx < QMLevent.getOriginList().size()) && !fnd){
////////                val=QMLevent.getOriginList().get(idx).getPublicId();
////////                if (val.contains("=")) val = val.substring(val.indexOf("=")+1); 
////////                
////////                if (hypocenterID==Integer.valueOf(val)){
////////                    o = QMLevent.getOriginList().get(idx);
////////                    fnd = true;
////////                } else idx+=1;
////////            }
////////            
////////            if ((fnd) && (o!=null)) {
////////                this.HYPOCENTER_ID = hypocenterID;
////////            
////////                this.ID = this.HYPOCENTER_ID; //Long.valueOf(QMLevent.getPublicId());
////////                this.setHypocenter(new Hypocenter());
////////                //
////////                this.getHypocenter().setLat(o.getLatitude());
////////                //    
////////                this.getHypocenter().setLon(o.getLongitude());
////////                this.getHypocenter().setDepth(o.getDepth().getValue()/1000.0);
////////                
////////                
////////                if (o.getQuality()!=null) {
////////                    this.getHypocenter().setRms(Double.valueOf(o.getQuality().getStandardError().toString()));      
////////                    this.getHypocenter().setAzimut_gap(o.getQuality().getAzimuthalGap());
////////                }
////////                      
////////                this.getHypocenter().setOriginTime_from_formatted_string(o.getTime().getValue().replace("T", " "));
////////                //        
////////               
////////                this.getHypocenter().setProvenance(null);
////////            } else return false;   
//////////
////////            if (QMLevent.getDescriptionList().get(0).getText() != null){
////////                this.getHypocenter().setRegion(QMLevent.getDescriptionList().get(0).getText());               
////////            } else {this.getHypocenter().setRegion("Unknown area");}
//////////            
////////            //-------------
////////            // Magnitudes
////////            //-------------
////////            List<edu.sc.seis.seisFile.fdsnws.quakeml.Magnitude> tmpMagArray = QMLevent.getMagnitudeList() ;
////////            
////////            if (tmpMagArray != null) {
////////                if (tmpMagArray.size()>0) {
////////                    for (edu.sc.seis.seisFile.fdsnws.quakeml.Magnitude tmpM1 : tmpMagArray) {
////////                        // 20210927 SISTEMARE!!!!!!!
//////////                        Magnitude m = new Magnitude(tmpM1);
//////////                        this.Hypocenter.getMagnitudes().add(m);
////////                    }
////////                }
////////            }
//////////          
////////            // ------------------------------------------------------------
////////            // Stations and Phases and picks    
////////            // ------------------------------------------------------------              
////////            List<Arrival> tmpPhArray = o.getArrivalList();
////////      
////////            if (tmpPhArray != null) {
////////                if (tmpPhArray.size()>0) {    
////////                    this.Stations = new ArrayList();
////////                    //
////////                    //for (Pick tmpArr1 : tmpPhArray) { 
////////                    for (Arrival tmpArr1 : tmpPhArray) { 
////////                       // STAZ = tmpArr1.getWaveformID().getStationCode(); 
////////                       
////////                        Pick corresp_pick = QML_UTILS.Arrival_2_Pick(tmpArr1);
////////                        if (corresp_pick != null) {
////////
////////                            STAZ = corresp_pick.getWaveformID().getStationCode(); //  QML_UTILS.pickID_2_StationCode(tmpArr1.getPickID());
////////                            //
////////                            Station s;
////////                            int idStazione = this.StationIndex(STAZ);
////////
////////                            Phase p = new Phase(tmpArr1, corresp_pick);   
////////
////////                            if (idStazione>-1) {
////////                                //-------------------------------------------
////////                                // STAZ is already part of the Event:
////////                                // only a phase add is needed
////////                                //-------------------------------------------
////////                                s = (Station)(this.Stations.get(idStazione));
////////                                p.setIdStation(idStazione);
////////                                s.addPhase(p);
////////                            } else {
////////                                //-------------------------------------------
////////                                // STAZ is not yet in the Event:
////////                                // a Station.add and a Phase.add are needed
////////                                //-------------------------------------------
////////                                s = new Station(STAZ, App.G.SeismicNet.getStations());
////////                                s.setNetwork(corresp_pick.getWaveformID().getNetworkCode());
////////                                s.setLocation(corresp_pick.getWaveformID().getLocationCode());
////////    //                                    
////////                                p.setIdStation(this.Stations.size());
////////    //                                    
////////                                s.addPhase(p);
////////                                this.Stations.add(s);  
////////                            }
////////                        }
////////                    }
////////                } else {
////////                    // No phases: "true" if an event can have no phase
////////                    return true; // false;
////////                }
////////            } else {
////////                // if QMLevent.getPickList() is null return false 
////////                return false;
////////            }
//////////         
////////            return true;
////////        } catch (Exception ex) {
////////            return false;
////////        }
    }    
    
    
    
    public edu.sc.seis.seisFile.fdsnws.quakeml.Event read_single_quakeml_from_file(String xmlFileName) {
        try {
            edu.sc.seis.seisFile.fdsnws.quakeml.Event ev=null;
            BufferedReader buf = new BufferedReader(new FileReader(xmlFileName));
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader r = factory.createXMLEventReader(buf);
            Quakeml qml = new Quakeml(r);
            EventIterator eIt = qml.getEventParameters().getEvents();
            while (eIt.hasNext()) {
                ev = eIt.next();
                break;
            }
            buf.close();
            return ev;
        } catch (Exception e) {
            System.err.println("Errore durante il caricamento o la lettura del file QuakeML: " + e.getMessage());
            return null;
        }
    }
//------------------------------------------------------------------------------    
    private void AddStation (Station staz) {
        if (Stations==null) Stations = new ArrayList<Station>();
        Stations.add(staz);
    }    
//------------------------------------------------------------------------------    
    public float SearchAnAmplitude(String Staz, String Chan){
        /*
        This is used to find an amplitude on Channel "Chan" for Station "Staz"
        First find an ML magnitude.
        If an ML is avalable, search in its amplitudes 
        (a MAGNITUDE has many AMPLITUDES)
        */
        try {
            int idMag=0;
            int idAmp;
            boolean found_mag = false;
            ObjectMagnitude m=null;
            ObjectStationmagnitude A;
            //
            //if (this.getHypocenter().getnMagnitudes()==0) return 0;
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty()) return 0;
            //
            while ((idMag < innerObjectEvent.getOrigins().get(0).getMagnitudes().size()) && (!found_mag)){
                m = innerObjectEvent.getOrigins().get(0).getMagnitudes().get(idMag);
                
                if (m==null) return 0;
                if (m.getTypeMagnitude().toUpperCase().contains("ML")) {
                    found_mag=true;
                } else {
                    idMag+=1;
                }
            }
            //
            if (!found_mag) return 0;
            //
            if (m==null) return 0;
            //
            
            
            // In this case m is the actual ML magnitude
            if (m.getStationmagnitudes()==null) return 0;
            if (m.getStationmagnitudes().isEmpty()) return 0;
            //
            for (idAmp=0; idAmp < m.getStationmagnitudes().size(); idAmp++){
                A = m.getStationmagnitudes().get(idAmp);
                if (A.getAmplitude().getSta().equalsIgnoreCase(Staz) && 
                        (A.getAmplitude().getCha().equalsIgnoreCase(Chan)) && 
                        (Math.abs(A.getAmplitude().getAmp1()-A.getAmplitude().getAmp2())>0.0) &&
                        (A.getIsUsed())) 
                    
                    return (float) Math.abs(A.getAmplitude().getAmp1()-A.getAmplitude().getAmp2());
            }
            return 0;
        } catch (Exception ex) {
            return 0;
        }
    }
//------------------------------------------------------------------------------
    public int StationCode_to_StationId(String StationCode){
        int res = -1;
        try {
            boolean fnd=false;
            int i=0;
            while ((!fnd) && (i<this.Stations.size())) {
                if (StationCode.trim().equalsIgnoreCase(this.Stations.get(i).getCode())) {
                    res=i;
                    fnd=true;
                } else {
                    i+=1;
                }
            }
            return res;
        } catch (Exception ex) {
            return res;
        }
    }
//------------------------------------------------------------------------------
    public Waveform SubplotIndex_to_Waveform(int plot_index){
        try {
            int idSta=0;
            while (idSta<Stations.size()) {
                if (Stations.get(idSta).getNWaves()>0){
                    for (int idW=0; idW<Stations.get(idSta).getNWaves(); idW++){
                        if (Stations.get(idSta).getWave(idW).getPlot_box_id()==plot_index){
                            return Stations.get(idSta).getWave(idW);
                        }
                    }
                } 
                    
                idSta++;
                
            }
            return null;
        } catch (Exception ex) {
            return null;
        }  
    }
//------------------------------------------------------------------------------ 
    public Waveform SubplotIndex_to_Waveform_WA(int WAplot_index){
        try {
            int idSta=0;
            while (idSta<Stations.size()) {
                if (Stations.get(idSta).getNWaves_WA()>0){
                    for (int idW=0; idW<Stations.get(idSta).getNWaves_WA(); idW++){
                        if (Stations.get(idSta).getWave_WA(idW).getPlot_box_id_WA()==WAplot_index){
                            return Stations.get(idSta).getWave_WA(idW);
                        }
                    }
                } 
                
                idSta++;
                
            }
            return null;
        } catch (Exception ex) {
            return null;
        }  
    } 
//------------------------------------------------------------------------------
    public int getNStations(){
        try {
            if (this.Stations!=null) {
                return this.Stations.size();
            } else return 0;
        } catch(Exception ex) {
            return 0;
        }
    }
//------------------------------------------------------------------------------
    public Station getStation(int i) {
        try {
            if ((this.Stations.isEmpty()) || (this.Stations.size()<=i))
                return null;
            else
                return this.Stations.get(i);
        } catch (Exception ex) {
            return null;
        }
    }
//------------------------------------------------------------------------------
    public boolean RecoverWaves_FDSN(int idDataSource, List<ChannelTimeWindow> Selected_Stations){ //ArrayList<String> Selected_Stations){
        try {     
            App.logger.debug("Searching waves on FDSN node " + 
                    App.G.options.getDatasources_FDSN().get(idDataSource).getUrl());

            String res;
            waves_path=App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + 
                    File.separator + innerObjectEvent.getOrigins().get(0).getId()+ File.separator;

            res = Query_FDSN_Node(App.G.options.getDatasources_FDSN().get(idDataSource).getHostname(), 
                    App.G.options.getDatasources_FDSN().get(idDataSource).getHttpport(),
                    waves_path,   
                    Selected_Stations, idDataSource,
                    false);
// 
            return true;
        } catch (Exception ex) {
            return false;
        }
    } 
//------------------------------------------------------------------------------    
    public boolean RecoverWaves_FDSN_WA(int idDataSource, List<ChannelTimeWindow> Selected_Stations){ //ArrayList<String> Selected_Stations){
        try {    
            String NodeURL = App.G.options.getDatasources_FDSN().get(idDataSource).getUrl();
            
            Logger.getLogger("org.ingv.sit.Event ").log(Level.INFO, 
                            "Searching waves on FDSN node " + NodeURL); 

            String res;
            waves_path_WA = App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + 
                    File.separator +  getWork_origin_ID()  + File.separator +"WA" + File.separator;

            res = Query_FDSN_Node(App.G.options.getDatasources_FDSN().get(idDataSource).getHostname(), App.G.options.getDatasources_FDSN().get(idDataSource).getHttpport(),
                    waves_path_WA,   
                    Selected_Stations, idDataSource,
                    true);
// 
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------
    public boolean RecoverWaves_EW(int idDataSource, List<ChannelTimeWindow> Selected_Stations){
        EW_Host EWHOST=null;
        try {    
            String ewHostName = App.G.options.getDatasources_EW().get(idDataSource).getHostname();
            ArrayList<Integer> ports_list = App.G.options.getDatasources_EW().get(idDataSource).getPortslist();
            
            Logger.getLogger("org.ingv.sit.Event ").log(Level.INFO, 
                            "Searching waves on earthworm server " + ewHostName); 
          
            Long tmpID = innerObjectEvent.getOrigins().get(0).getId();
            if (tmpID==null) tmpID = this.getWork_origin_ID();
            waves_path = App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + tmpID+ File.separator;

            EWHOST = new EW_Host(ewHostName, ports_list);
            
            Query_EWWaveServer(EWHOST,
                    waves_path, 
                    Selected_Stations, idDataSource,
                    false);
// 
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if (EWHOST!=null) EWHOST.CloseConnection();
            EWHOST=null;
            System.gc();
        }
    }  

//    public Task<Void> task_RecoverWaves_FDSN(int idDataSource, List<ChannelTimeWindow> Selected_Stations){
//        return new Task<Void>() {
//            @Override
//            protected Void call()
//                    throws InterruptedException {
//                try {
//                    Thread.sleep(ThreadLocalRandom.current().nextInt(200,2000));
//                } catch (InterruptedException e) {
//                    //e.printStackTrace();
//                }
//                updateMessage("Finding waves on " + App.G.options.getDatasources_FDSN().get(idDataSource).getDescription());
//                updateProgress(0, 100);
//                
//                RecoverWaves_FDSN(idDataSource,  Selected_Stations);
//                
//                try {
//                    Thread.sleep(ThreadLocalRandom.current().nextInt(200,2000));
//                } catch (InterruptedException e) {
//                    //e.printStackTrace();
//                }
//
//                updateProgress(100, 100);
//                updateMessage("Search finished.");
//                another_task=false;
//                return null;
//            }
//        };
//    }
    
//------------------------------------------------------------------------------    
    public boolean RecoverWaves_EW_WA(int idDataSource, List<ChannelTimeWindow> Selected_Stations){
        EW_Host EWHOST=null;
        try {
            
            String ewHostName = App.G.options.getDatasources_EW().get(idDataSource).getHostname();
            ArrayList<Integer> ports_list = App.G.options.getDatasources_EW().get(idDataSource).getPortslist();
            
            Logger.getLogger("org.ingv.sit.Event ").log(Level.INFO, 
                            "Searching horizontal components on earthworm server " + ewHostName); 

            String res;
                                    
            waves_path_WA = App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + 
                    File.separator + getWork_origin_ID() + File.separator +"WA" + File.separator;

            EWHOST = new EW_Host(ewHostName, ports_list);
            
            res=Query_EWWaveServer(EWHOST,
                    waves_path_WA, 
                    Selected_Stations, idDataSource,
                    true);
// 
            another_task=false;
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if (EWHOST!=null) EWHOST.CloseConnection();
            EWHOST=null;
            System.gc();
        }
    }   
//------------------------------------------------------------------------------    
    public boolean RecoverWaves_LOCAL_MINISEED(String waves_path, List<ChannelTimeWindow> Selected_Stations){
        try{
            if (Selected_Stations.isEmpty()) return false;
            
            File MSEEDrepository = new File (waves_path);
            
            FilenameFilter filtro = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                        boolean value;
                        // return files only that begins with test
                        if(name.toUpperCase().endsWith("SEED")){
                                value=true;
                        }
                        else{
                                value=false;
                        }
                        return value;
                }
            };
            
            // List of MINISEED files as Files
            List<File> mseedfileList = new ArrayList<File>();
            File[] lista = MSEEDrepository.listFiles(filtro);            
            mseedfileList.addAll(Arrays.asList(lista));
            
            for (int i=0; i<Selected_Stations.size(); i++){
                String file = Selected_Stations.get(i).getNetwork() + "." +
                        Selected_Stations.get(i).getStation() + "." + 
                        Selected_Stations.get(i).getLocation().replace("--", "") + "." +
                        Selected_Stations.get(i).getChannel() + ".miniseed";
                
                
            }
                 
            return true;
        } catch (Exception ex){
            return false;
        }
    }
//------------------------------------------------------------------------------        
    public void CleanupWAWaves(){
        try {
            for (int i=0; i<this.Stations.size(); i++){
                if (this.Stations.get(i).getNWaves_WA()>0)
                    this.Stations.get(i).getWaves_WA().clear();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }    
    }    
//------------------------------------------------------------------------------    
    public boolean InitTerna(String Staz, String staName, String Chan, String Net, String Loc, int id_waveserver, int id_FDSNsource) {
    try {       
        int idStaz = this.StationCode_to_StationId(Staz);    
        String terna_waves_path;
        if ((id_waveserver==-1) && (id_FDSNsource==-1)){
            // WAVES ON LOCALHOST_EVENTS: local SAC or miniseed files
            terna_waves_path=new File(Stations.get(idStaz).getWaves().get(0).getFilename()).getAbsolutePath();
            terna_waves_path = terna_waves_path.substring(0, terna_waves_path.lastIndexOf(App.G.SystemProperties.file_Separator));
        } else {
            // WAVES ON WEB-SERVICES
            terna_waves_path=waves_path;
        }
        
                
        ActiveTerna = new Terna(Staz, staName, Chan, Net, Loc, idStaz,  terna_waves_path, id_waveserver, id_FDSNsource);
        App.G.MakeDirectory(this.ActiveTerna.getWaves_path());
//        
        if (this.ActiveTerna.WavesAlreadyAvailable()) {
            EW_Wave_Server_Client wsc;
            //wsc = App.G.EW_SERVER.getWave_server_client_list().get(this.ActiveTerna.getID_earthworm_waveserver());  
            wsc = EW_SERVER.getWave_server_client_list().get(this.ActiveTerna.getID_earthworm_waveserver());  
            this.ActiveTerna.setWaves(wsc.BuildWavesList(this.ActiveTerna.getWaves_path(), Staz));
            return true;
        }
//        
        if (ActiveTerna.RecoverWaves(innerObjectEvent.getOrigins().get(0).getOt())) 
            return true;
        else 
            Logger.getLogger("org.ingv.sit ").log(Level.SEVERE, 
                            "Could NOT init terna for " + Staz + Chan + Net + Loc); 
            return false;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }    
    }
//------------------------------------------------------------------------------    
    public ArrayList ToStationArray() {
        try {
            ;
            ArrayList out_arr = new ArrayList(); 
            String[] element; 
            if (this.Stations.isEmpty()) return null;
            for (int i=0; i<this.Stations.size(); i++){
                element = new String[4];
                element[0] = ((Station)this.Stations.get(i)).getCode();
                element[1] = String.valueOf(((Station)this.Stations.get(i)).getLat());
                element[2] = String.valueOf(((Station)this.Stations.get(i)).getLon());

                if (Stations.get(i).HasAnUsedPhase())
                    element[3] ="T";
                else
                    element[3] ="F";
                out_arr.add((String[])element); //.clone());
            }

            return out_arr;
        } catch (Exception ex) {
            return null;
        }
        
    } 
//------------------------------------------------------------------------------    
    public void Update_with_location_results(Event location_result) {            
        /* 
            Updates the main EVENT object with the "location_result" 
            event coming form last location 
        */
        //----------------------------------------------------------------------
        // Origin (Hypocenter) with magnitudes
        //----------------------------------------------------------------------  
//        final ObjectOrigin original_origin = getOrigins().get(0);
//        Long swapId = innerObjectEvent.getOrigins().get(0).getId();     // Serve a conservare l'ID della localizzazionne che altrimenti andrebbe perso
        
        // Mette da parte gli arrivals prima della localizzazione
        List<ObjectArrival> TEMP = innerObjectEvent.getOrigins().get(0).getArrivals();
        if (TEMP!=null) {
            for (int k=0; k< TEMP.size(); k++){
                TEMP.get(k).setArrTimeIsUsed(Boolean.FALSE);    // Li pone tutti come NON usati
            }
        }
        
        // Swap della nuova localizzazione ==> CAMBIANO GLI ARRIVALS
        innerObjectEvent.getOrigins().set(0, location_result.getInnerObjectEvent().getOrigins().get(0));  
               
        //innerObjectEvent.getOrigins().get(0).setId(swapId);  // Serve (?) a conservare l'ID della localizzazionne che altrimenti andrebbe perso
//        this.setWork_origin_ID(swapId);
//                
//        // Reinserisce nella origin le fasi originarie NON usate in localizzazione
        if (TEMP!=null) {
            for (int aId=0; aId<TEMP.size(); aId++){
                if (find_old_phase(innerObjectEvent.getOrigins().get(0).getArrivals(), TEMP.get(aId)) == -1){
                    innerObjectEvent.getOrigins().get(0).getArrivals().add(TEMP.get(aId));
                } 
            }
        }
                    
        // 20220503
        // Sostituisce le vecchie ML con quelle calcolate da PYML
        innerObjectEvent.getOrigins().get(0).setMagnitudes(location_result.getInnerObjectEvent().getOrigins().get(0).getMagnitudes());
        
        //----------------------------------------------------------------------
        // PHASES in "Station" objects
        //----------------------------------------------------------------------
        int i;
        Station tmpStaz;
        int station_id_in_main_event;
        ArrayList fasi_pre_localizzazione=null;
        int indice_vecchia_fase=-1;
        for (i = 0; i < location_result.Stations.size(); i++) {
            tmpStaz = location_result.getStation(i);
            station_id_in_main_event = StationCode_to_StationId(tmpStaz.getCode()); 
            if (station_id_in_main_event!=-1) {
                fasi_pre_localizzazione = Stations.get(station_id_in_main_event).getPhases();
                
                Stations.get(station_id_in_main_event).setPhases(tmpStaz.getPhases()); 
                
                if (fasi_pre_localizzazione!=null){
                    for (int phId=0; phId<this.Stations.get(station_id_in_main_event).getNPhases(); phId++){
                        indice_vecchia_fase = find_old_phase(fasi_pre_localizzazione,(ObjectArrival)Stations.get(station_id_in_main_event).getPhase(phId));
                        if (indice_vecchia_fase!=-1){
                            ((ObjectArrival)(Stations.get(station_id_in_main_event).getPhase(phId))).getPick().setIdLocalspace(((ObjectArrival)fasi_pre_localizzazione.get(indice_vecchia_fase)).getPick().getIdLocalspace());
                            ((ObjectArrival)(Stations.get(station_id_in_main_event).getPhase(phId))).getPick().setLocalspace(((ObjectArrival)fasi_pre_localizzazione.get(indice_vecchia_fase)).getPick().getLocalspace());
                        }
                    }
                }
                               
            } else {
                this.AddStation(tmpStaz);
            }
        }    
//        
        if (Stations.size() != location_result.Stations.size()){
            for (i=0; i<Stations.size(); i++){
                if (location_result.StationCode_to_StationId(this.getStation(i).getCode())==-1){
                    this.getStation(i).Exclude_from_location();
                }
            }
        }
    }
    
    
    
    private int find_old_phase(ArrayList vecchie_fasi, ObjectArrival fase){
        try {
            for (int k=0; k< vecchie_fasi.size(); k++){
                if (((ObjectArrival)vecchie_fasi.get(k)).getIscCode().equalsIgnoreCase(fase.getIscCode())) return k;
            }
            
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
    
    private int find_old_phase(List vecchie_fasi, ObjectArrival fase){
        try {
            for (int k=0; k< vecchie_fasi.size(); k++){
                
//                if ((((ObjectArrival)vecchie_fasi.get(k)).getSta().equalsIgnoreCase(fase.getSta())) && 
//                  (((ObjectArrival)vecchie_fasi.get(k)).getIscCode().equalsIgnoreCase(fase.getIscCode()))) 
//                    return k;
                if ((((ObjectArrival)vecchie_fasi.get(k)).getPick().getSta().equalsIgnoreCase(fase.getPick().getSta())) && 
                  ((((ObjectArrival)vecchie_fasi.get(k)).getIscCode().toUpperCase().contains(fase.getIscCode().toUpperCase()))) ||(
                    fase.getIscCode().toUpperCase().contains(((ObjectArrival)vecchie_fasi.get(k)).getIscCode().toUpperCase())))
                    return k;
            }
            
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------ 
    
//------------------------------------------------------------------------------
    public void Remove_Unselected_Stations(ArrayList<String> selected_stations){
        try {
            ArrayList<Station>  ClearedStations = new ArrayList<Station>();
            
            if (selected_stations==null) return;
            if (selected_stations.size() <= 0) return;
            //
            if (this.Stations==null) return;
            if (this.getNStations()==0) return;
            //
            String sigla;
            for (int i=0; i< this.getNStations(); i++) {
                sigla = this.getStation(i).getCode();
                if (selected_stations.indexOf(sigla) != -1) {
                    //
                    ClearedStations.add(this.Stations.get(i));
                } 
            }
//      
            this.Stations = ClearedStations;
        } catch (Exception ex){
            //
        }
    
    }
//------------------------------------------------------------------------------    
    public void Clear_data_basket(){
        try {
            new File(this.waves_path).delete();
        } catch (Exception ex) {
        }
    }    
//------------------------------------------------------------------------------  
    public ObjectStationmagnitude FindAmplitude(String STAZ, String CHAN){
        try {
            boolean hasML=false;
            int i=0;
            int mlId=-1;
            
            if (innerObjectEvent.getOrigins().get(0)==null) return null;
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes()==null || innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty()) return null;

            while ((!hasML) && (i<innerObjectEvent.getOrigins().get(0).getMagnitudes().size())){
                if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("ML")) {
                    mlId=i;
                    hasML=true;
                } else i+=1;

            }
            
            if ((hasML) && (mlId != -1)) {
                ObjectMagnitude tmpM = innerObjectEvent.getOrigins().get(0).getMagnitudes().get(mlId);
                return this.local_findAmp(tmpM, STAZ, CHAN);  
            } else {
                Logger.getLogger("org.ingv.sit ").log(Level.WARNING, "EVENT HAS NO ML");
                return null;
            } 
        } catch (Exception ex) {
            return null;
        }      
    }
//------------------------------------------------------------------------------    
    private ObjectStationmagnitude local_findAmp(ObjectMagnitude M, String STAZ, String CHAN){
        try{
            boolean fnd =false;
            int id=0;
            if (M.getStationmagnitudes()==null) return null;
            if (M.getStationmagnitudes().isEmpty()) return null;
            while ((!fnd) && (id<M.getStationmagnitudes().size())){
                if ((M.getStationmagnitudes().get(id).getAmplitude().getSta().equalsIgnoreCase(STAZ)) && (M.getStationmagnitudes().get(id).getAmplitude().getCha().equalsIgnoreCase(CHAN))){
                    fnd=true;
                    return M.getStationmagnitudes().get(id);
                } else
                    id++;
            }
            
          
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
//------------------------------------------------------------------------------    
    private int local_findAmpIndex(ObjectMagnitude M, String STAZ, String CHAN){
        try{
            boolean fnd =false;
            int id=0;
            if (M.getStationmagnitudes()==null) return -1;
            if (M.getStationmagnitudes().isEmpty()) return -1;
            while ((!fnd) && (id<M.getStationmagnitudes().size())){
                if ((M.getStationmagnitudes().get(id).getAmplitude().getSta().equalsIgnoreCase(STAZ)) && (M.getStationmagnitudes().get(id).getAmplitude().getCha().equalsIgnoreCase(CHAN))){
                    fnd=true;
                    return id;
                } else
                    id++;
            }
            
          
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    public int FindAmplitudeIndex(String type_magnitude, String STAZ, String CHAN){
        try {
            boolean hasML=false;
            int i=0;
            int mlId=-1;
            if (innerObjectEvent.getOrigins().get(0)==null) return -1;
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty()) return -1;

            while ((!hasML) && (i<innerObjectEvent.getOrigins().get(0).getMagnitudes().size())){
                //if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("ML")) {
                if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().equalsIgnoreCase(type_magnitude)) {
                    mlId=i;
                    hasML=true;
                } else i+=1;

            }
            
            if ((hasML) && (mlId != -1)) {
                ObjectMagnitude tmpM = innerObjectEvent.getOrigins().get(0).getMagnitudes().get(mlId);
                return this.local_findAmpIndex(tmpM, STAZ, CHAN);
            } else {
                Logger.getLogger("org.ingv.sit ").log(Level.WARNING, 
                            "EVENT HAS NO ML and mlId = " + String.valueOf(mlId));
                return -1;
            }
       
        } catch (Exception ex) {
            return -1;
        }
            
    }
//------------------------------------------------------------------------------
    public void AmplitudeUpdate(ObjectStationmagnitude inAmp){
        int idAmp;
        idAmp = FindAmplitudeIndex(inAmp.getTypeMagnitude(), inAmp.getAmplitude().getSta(), inAmp.getAmplitude().getCha());
        if (idAmp==-1) {
            AmplitudeAdd(inAmp);
        } else {           
            inAmp.getAmplitude().setLocalspace(sitLocalSpace);
            inAmp.getAmplitude().setProvenance(getSitProvenance());
            innerObjectEvent.getOrigins().get(0).getMagnitudes().get(this.findMLid(inAmp.getTypeMagnitude())).getStationmagnitudes().remove(idAmp);
            innerObjectEvent.getOrigins().get(0).getMagnitudes().get(this.findMLid(inAmp.getTypeMagnitude())).getStationmagnitudes().add(inAmp);
        }
    }
//--------------------------------------------------------------------------------    
    public void AmplitudeDelete(ObjectStationmagnitude inAmp){
        
        int idAmp;
        idAmp = FindAmplitudeIndex(inAmp.getTypeMagnitude(),inAmp.getAmplitude().getSta(), inAmp.getAmplitude().getCha());
        if (idAmp!=-1) {
            innerObjectEvent.getOrigins().get(0).getMagnitudes().get(this.findMLid(inAmp.getTypeMagnitude())).getStationmagnitudes().remove(idAmp);
        }
    }
//--------------------------------------------------------------------------------   
    public void AmplitudeAdd(ObjectStationmagnitude inAmp){

        inAmp.getAmplitude().setLocalspace(sitLocalSpace);
        inAmp.getAmplitude().setProvenance(getSitProvenance());

        for (int i=0; i< innerObjectEvent.getOrigins().get(0).getMagnitudes().size(); i++){
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("ML")){
                if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getStationmagnitudes()==null){
                    innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).setStationmagnitudes(new ArrayList<ObjectStationmagnitude>());
                }
                innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getStationmagnitudes().add(inAmp);
            }
        }
        
    }
//------------------------------------------------------------------------------    
    public int findMLid(String ml_type){
        try {
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty()) return -1;
            int id=0;
            while (id < innerObjectEvent.getOrigins().get(0).getMagnitudes().size()){
                //if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(id).getTypeMagnitude().toUpperCase().contains("ML")) 
                if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(id).getTypeMagnitude().toUpperCase().equalsIgnoreCase(ml_type)) 
                    return id;
                else id+=1;
            }
            
            // Ml not found
            return -1;
            
        } catch (Exception ex) {
            return -1;
        }
    }  
    public int findMLid(){
        try {
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty()) return -1;
            int id=0;
            while (id < innerObjectEvent.getOrigins().get(0).getMagnitudes().size()){
                if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(id).getTypeMagnitude().toUpperCase().contains("ML")) 
                    return id;
                else id+=1;
            }
            
            // Ml not found
            return -1;
            
        } catch (Exception ex) {
            return -1;
        }
    }  
//------------------------------------------------------------------------------
    public boolean ChangeEventType(String newEventType, ArrayList<Event_type> event_types){
        UpdateApi eventUpdater = new UpdateApi();         
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        UpdateEventRequestDataEvent ev;
        UpdateEventRequestData data;
        try {  
            if ((App.G.User!=null) && (App.G.User.isLoggedIn()))
                eventUpdater.getApiClient().setBearerToken(App.G.User.getToken());
            else
                eventUpdater.getApiClient().setBearerToken("");
            
            ev = new UpdateEventRequestDataEvent();
            ev.setEventGroupId(innerObjectEvent.getEventGroupId());
            ev.setTypeEvent(newEventType);
            
            data = new UpdateEventRequestData();
            data.setEvent(ev);

            
            updateEventRequest.setData(data);
            
            App.logger.debug("WS-LOG: ChangeEventType()--> eventUpdater.updateEvent posting");
            eventUpdater.updateEvent(innerObjectEvent.getId(), updateEventRequest);
            App.logger.debug("WS-LOG: ChangeEventType()--> eventUpdater.updateEvent finished");
            return true;
        } catch (Exception ex) {
            sitDialog.ShowErrorMessage("Error:\n" + ex.getMessage() , null);
            return false;
        } finally {
            eventUpdater=null;
            
            ev=null;
            data=null;
            
        }
    } 
//------------------------------------------------------------------------------   
    private ArrayList<Waveform> BuildWavesList(String filename) throws ParseException {
        
        try {
            ArrayList<List<DataRecord>> drLists; 
            //            
            MiniSeedToFloatArray msf= new MiniSeedToFloatArray();
//
            drLists = msf.readseedfile(filename, false);         
      
            if (drLists == null) return null;
            int j, k;
            ArrayList<Waveform> Warr = new ArrayList<Waveform>();
            Waveform swpW;
            List<DataRecord> lista;
            float[] samps;
            
            
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy,D,HH:mm:ss.SS");                    
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            
            for (k=0; k<drLists.size(); k++) {
                swpW = new Waveform();
                lista = drLists.get(k);
                swpW.setDataProvider("FDSN");
                swpW.setStationCode(lista.get(0).getHeader().getStationIdentifier());
                swpW.setChannelCode(lista.get(0).getHeader().getChannelIdentifier());
                swpW.setSamplingRate(lista.get(lista.size()-1).getHeader().calcSampleRateFromMultipilerFactor());
                swpW.setNetworkCode(lista.get(lista.size()-1).getHeader().getNetworkCode());
                swpW.setLocationCode(lista.get(lista.size()-1).getHeader().getLocationIdentifier());
                if (swpW.getLocationCode().trim().length()==0) swpW.setLocationCode("--");
              
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy,D,HH:mm:ss.SSSS");
                swpW.setStartTime(LocalDateTime.parse(lista.get(0).getHeader().getStartTime(), format));
                //swpW.setEndTime(LocalDateTime.parse(lista.get(lista.size()-1).getHeader().getEndTime(), format));
                
                samps = msf.extract(lista);
                if (samps != null) {
                    swpW.setY(samps);
                    swpW.setnSamples(samps.length);
                    Warr.add(swpW);
                }    
            }            
//            
            return Warr;
//            
            } catch (CodecException | SeedFormatException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }           
//------------------------------------------------------------------------------
    public void Arrange_Waves_to_Stations(ArrayList<Waveform> waves, dsType datasourcetype, int idDataSource) {
        // ------------------------------------------------------
        // Distribuisce le varie waves alle rispettive stazioni
        // ------------------------------------------------------
        if (waves == null) return;
        if (waves.isEmpty()) return;
//
        String stCode;
        int stId=-1;
        Station newStaz;
        for (int i = 0; i < waves.size(); i++) {
            stCode = waves.get(i).getStationCode();
            stId = this.StationCode_to_StationId(stCode);
            if (stId != -1) {
                // La stazione esiste: le associamo il segnale
                Stations.get(stId).addWave(waves.get(i));
                if (datasourcetype==dsType.EARTHWORMWS){
                    Stations.get(stId).setEarthWorm_WaveServer_Client_INDEX(idDataSource);
                    Stations.get(stId).setFDSN_Service_index(-1);
                } else {
                    Stations.get(stId).setEarthWorm_WaveServer_Client_INDEX(-1);
                    Stations.get(stId).setFDSN_Service_index(idDataSource);
                }
                
                //Stations.get(stId).setIdDataSource(idDataSource);
            } else {
                // La stazione non esiste: bisogna crearla...
                //newStazId = this.Stations.size();
                newStaz = new Station(stCode, App.G.SeismicNet.getStations());
                //
                // ... e associarle il segnale
                newStaz.addWave(waves.get(i));
                
                if (datasourcetype==dsType.EARTHWORMWS){
                    newStaz.setEarthWorm_WaveServer_Client_INDEX(idDataSource);
                    newStaz.setFDSN_Service_index(-1);
                } else {
                    newStaz.setEarthWorm_WaveServer_Client_INDEX(-1);
                    newStaz.setFDSN_Service_index(idDataSource);
                }
                
                //newStaz.setIdDataSource(idDataSource);
                //
                // ... e inserire la stazione nell'evento
                AddStation(newStaz);
                
            }
        }
    }
    
    private void Arrange_Waves_to_Stations_WA(ArrayList<Waveform> waves, dsType datasourcetype, int idDataSource) {
        // ------------------------------------------------------
        // Distribuisce le varie waves alle rispettive stazioni
        // ------------------------------------------------------
        if (waves == null) return;
        if (waves.isEmpty()) return;
//
        String stCode;
        int stId=-1;
        Station newStaz;
        for (int i = 0; i < waves.size(); i++) {
            stCode = waves.get(i).getStationCode();
            stId = this.StationCode_to_StationId(stCode);
            if (stId != -1) {
                // La stazione esiste: le associamo il segnale
                Stations.get(stId).addWave_WA(waves.get(i));
               // Stations.get(stId).setIdDataSource(idDataSource);
                
                if (datasourcetype==dsType.EARTHWORMWS){
                    Stations.get(stId).setEarthWorm_WaveServer_Client_INDEX(idDataSource);
                    Stations.get(stId).setFDSN_Service_index(-1);
                } else {
                    Stations.get(stId).setEarthWorm_WaveServer_Client_INDEX(-1);
                    Stations.get(stId).setFDSN_Service_index(idDataSource);
                }
            } else {
                // La stazione non esiste: bisogna crearla...
                //newStazId = this.Stations.size();
                newStaz = new Station(stCode, App.G.SeismicNet.getStations());
                //
                // ... e associarle il segnale
                newStaz.addWave_WA(waves.get(i));
                
                if (datasourcetype==dsType.EARTHWORMWS){
                    newStaz.setEarthWorm_WaveServer_Client_INDEX(idDataSource);
                    newStaz.setFDSN_Service_index(-1);
                } else {
                    newStaz.setEarthWorm_WaveServer_Client_INDEX(-1);
                    newStaz.setFDSN_Service_index(idDataSource);
                }
                
              //  newStaz.setIdDataSource(idDataSource);
                //
                // ... e inserire la stazione nell'evento
                AddStation(newStaz);
                
            }
        }
    }
//------------------------------------------------------------------------------ 
    public int find_for_merge(ChannelTimeWindow element,List<ChannelTimeWindow> vect){  
        
        if (element.getStation().trim().equalsIgnoreCase("SIB3")){
            int k=0;
            k++;
        }
        try {
            for (int i=0; i<vect.size(); i++){
                if (element.getNetwork().equalsIgnoreCase(vect.get(i).getNetwork()) && 
                        element.getStation().equalsIgnoreCase(vect.get(i).getStation())  &&
                        //element.getLocation().equalsIgnoreCase(vect.get(i).getLocation())  &&
                        element.getChannel().equalsIgnoreCase(vect.get(i).getChannel()) 
                        )
                    return i;
            }
// 
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------ 
    public void ADDWAVES(ArrayList<String> selected_stations, Stage primaryStage){ 
        /*
        ATTENZIONE: Questa routine si usa solo da AddStations
        */
        if (innerObjectEvent==null) return;
        if (innerObjectEvent.getOrigins()==null) return;
        if (innerObjectEvent.getOrigins().isEmpty()) return;
        try {
                LocalDateTime DATE_start=null, DATE_end=null;
                List<ChannelTimeWindow> Stazioni_canali_tempi_selezionati = new ArrayList<>();

                if (innerObjectEvent.getOrigins().get(0).getOt()!=null){
                    String  net, sta, cha, loc; //, in_date_start, in_date_end;
                    // Processa tutti i datasources in ordine per cercare le stazioni
                    // selezionate sulla mappa "SelectedStations"        
                    //------------------------------------------------------------------------------------------
                    // Prepara la lista di stazioni da richiedere partendo dalle stazioni associate nell'evento 
                    //------------------------------------------------------------------------------------------
                    
                    for (int i=0; i < getNStations(); i++){
                        if (getStation(i).getNPhases()>0) {
                            sta = getStation(i).getCode();  
                            if ((selected_stations==null) || (selected_stations.contains(sta))) {
                                net = getStation(i).getNetwork();   
                                cha = getStation(i).getPhase(0).getPick().getCha();
                                loc = getStation(i).getLocation(cha);
//                                if ((loc.equalsIgnoreCase("--")) || (loc.trim().length()==0)) 
//                                    loc="*";
                                // START TIME for waves request = arrival time of the first phase - 5 seconds
                                if (DATE_start==null){
                                    DATE_start =getStation(i).getPhase(0).getPick().getArrivalTime().minus(java.time.Duration.ofSeconds(5)).toLocalDateTime();
                                }
                                
                                // END TIME for waves request = arrival time of the last phase +60 seconds
                                DATE_end =getStation(i).getPhase(0).getPick().getArrivalTime().plus(java.time.Duration.ofSeconds(60)).toLocalDateTime();

                                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(net, sta, loc, cha, 
                                        getStation(i).getPhase(0).getPick().getArrivalTime().minus(java.time.Duration.ofSeconds(5)).toInstant(), 
                                        getStation(i).getPhase(0).getPick().getArrivalTime().plus(java.time.Duration.ofSeconds(60)).toInstant()));
                            }   

//                            if ((selected_stations!=null) && !(selected_stations.contains(sta))) {
//                                int idSta = StationCode_to_StationId(sta);
//                                if (idSta!=-1)
//                                    getStations().get(idSta).Exclude_from_location();
//                            }                       
                        }
                    } 
                    //----------------------------------------------------------------------
            //      Costruisce la SCNL_list a partire dalle selected stations 
                    if ((DATE_start==null) && (innerObjectEvent.getOrigins().get(0).getOt()!=null))
                        DATE_start = innerObjectEvent.getOrigins().get(0).getOt().toLocalDateTime().minus(5,ChronoUnit.SECONDS);
                    if ((DATE_end==null) && (innerObjectEvent.getOrigins().get(0).getOt()!=null))
                        DATE_end = innerObjectEvent.getOrigins().get(0).getOt().toLocalDateTime().plus(60,ChronoUnit.SECONDS);
                    List<ChannelTimeWindow> SCNL_list = BuildChannelsList(DATE_start.toInstant(ZoneOffset.UTC), DATE_end.toInstant(ZoneOffset.UTC), selected_stations);
            //            
                    if (((SCNL_list==null)||SCNL_list.isEmpty()) && (selected_stations==null || selected_stations.isEmpty())) return;
            //                     
                    //--------------------------------------------------------------------
                    // Merge of the two lists
                    //--------------------------------------------------------------------  
                    if (SCNL_list != null && !SCNL_list.isEmpty()){
                        for (int i=0; i< SCNL_list.size(); i++){
                            if (find_for_merge(SCNL_list.get(i), Stazioni_canali_tempi_selezionati)==-1) {
                                Stazioni_canali_tempi_selezionati.add(SCNL_list.get(i));    
                            }   
                        }
                    }

                    //----------------------------------------------------------------------
                    // A questo punto in params_from_picks c'è tutto quello che vogliamo vedere
                    // Iniziamo a processare i datasources_waves in ordine (l'orinde 
                    // nell'array conicide con la priorità)
                    //----------------------------------------------------------------------
                    //if (!App.G.FileExists(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + this.getOrigins().get(0).getId()+ File.separator))
//                    App.G.MakeDirectory((App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + innerObjectEvent.getOrigins().get(0).getId()+ File.separator));

//                    if (!App.G.IsDirEmpty(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + innerObjectEvent.getOrigins().get(0).getId()+ File.separator)) {
//                        App.G.ClearDirectory(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + innerObjectEvent.getOrigins().get(0).getId()+ File.separator);  
//                    }
                }
             
            //------------------------------------------------------------------
            // Fino a quando ci sono datasource abilitati e tracce da cercare...
            // Elabora tutti i datasources per le waves in sequenza
            // Ordine: LOCALHOST_EVENTS, EW, SL, FDSN
            //------------------------------------------------------------------
            
            // LOCALHOST_EVENTS
            // Parte da un datasource LOCALHOST_EVENTS perchè è il più veloce (se c'è)
            // IN REALTà HA GIà FATTO TUTTO NEL PRE-READ
            
            Stazioni_canali_tempi_selezionati = Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false); 
                      
            // EW
            if (App.G.options.getDatasources_EW()!=null && !App.G.options.getDatasources_EW().isEmpty() && Stazioni_canali_tempi_selezionati!=null  && !Stazioni_canali_tempi_selezionati.isEmpty()){
                for (int idEW=0; idEW<App.G.options.getDatasources_EW().size(); idEW++){
                    if (App.G.options.getDatasources_EW().get(idEW).isUsed()){      
                        // Download data from the Earthworm wave server #idEW
                        RecoverWaves_EW(idEW,  Stazioni_canali_tempi_selezionati);

                        Stazioni_canali_tempi_selezionati = Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false); 
                    }
                }
            }
            
            // SL
//            if ((Stazioni_canali_tempi_selezionati!=null)  && (!Stazioni_canali_tempi_selezionati.isEmpty())){
//                for (int idSL=0; idSL<App.G.options.getDatasources_SL().size(); idSL++){
//                    //
//                }
//            }
            
            // FDSN
            if ((Stazioni_canali_tempi_selezionati != null) && (!Stazioni_canali_tempi_selezionati.isEmpty())) {
                for (int idFDSN=0; idFDSN<App.G.options.getDatasources_FDSN().size(); idFDSN++){
                    if (App.G.options.getDatasources_FDSN().get(idFDSN).isUsed()){                   
                        RecoverWaves_FDSN(idFDSN,  Stazioni_canali_tempi_selezionati);
                        Stazioni_canali_tempi_selezionati = Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false); 
                    }
                }
            }           
          
        } catch (Exception ex){
            App.logger.error(ex.getMessage());
        }
        
    }
//------------------------------------------------------------------------------     
    public boolean GETWAVES_WA(ArrayList<String> selected_stations){
        //
        try {
            List<ChannelTimeWindow> Stazioni_canali_tempi_selezionati = new ArrayList<>();
            int i;
            String  net, sta, cha, loc;       
            LocalDateTime DATE_start, DATE_end;
            DATE_start = innerObjectEvent.getOrigins().get(0).getOt().toLocalDateTime().minus(5,ChronoUnit.SECONDS);
            DATE_end = innerObjectEvent.getOrigins().get(0).getOt().toLocalDateTime().plus(60,ChronoUnit.SECONDS);

            //------------------------------------------------------------------------------------------
            // Prepara la lista di stazioni da richiedere partendo dalle stazioni associate nell'evento 
            //------------------------------------------------------------------------------------------
            for (i=0; i < getNStations(); i++){
                //if (this.getStation(idSta).getNPhases()>0) {
                    sta = Stations.get(i).getCode();  
                  
                    if ((selected_stations==null) || (selected_stations.contains(sta))) {
                        net = Stations.get(i).getNetwork();
              
                        if (Stations.get(i).getNPhases()>0)
                            cha = Stations.get(i).getPhase(0).getPick().getCha();
                        else {
//                                if (Stations.get(i).getNWaves()>0)
//                                    cha = Stations.get(i).getWave(0).getChannelCode();
//                                else
                                    cha = Stations.get(i).FindFirstAvailableChannel();
                        }
                        
                        loc = Stations.get(i).getLocation(cha);
                        //if (loc.equalsIgnoreCase("--")) loc="";
                        
                        if (cha!=null) {
                            cha = cha.substring(0, 2);

                            if (Stations.get(i).getNPhases()>0){
                                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(net,sta ,loc,cha + "N", Stations.get(i).getPhase(0).getPick().getArrivalTime().minus(Duration.ofSeconds(5)).toInstant(), Stations.get(i).getPhase(0).getPick().getArrivalTime().plus(Duration.ofSeconds(60)).toInstant()));
                                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(net,sta ,loc,cha + "E", Stations.get(i).getPhase(0).getPick().getArrivalTime().minus(Duration.ofSeconds(5)).toInstant(), Stations.get(i).getPhase(0).getPick().getArrivalTime().plus(Duration.ofSeconds(60)).toInstant()));
                            } else {
                                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(net,sta ,loc,cha + "N", this.getInnerObjectEvent().getOrigins().get(0).getOt().minus(Duration.ofSeconds(5)).toInstant(), this.getInnerObjectEvent().getOrigins().get(0).getOt().plus(Duration.ofSeconds(60)).toInstant()));
                                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(net,sta ,loc,cha + "E", this.getInnerObjectEvent().getOrigins().get(0).getOt().minus(Duration.ofSeconds(5)).toInstant(), this.getInnerObjectEvent().getOrigins().get(0).getOt().plus(Duration.ofSeconds(60)).toInstant()));
                            }
                        }
                    }     
                //}
            } 
            
//            System.out.println("PRIMA ----------------------------------------------");
//             for (int j=0; j< Stazioni_canali_tempi_selezionati.size(); j++){
//                System.out.println(Stazioni_canali_tempi_selezionati.get(j).getStation() + "   " + Stazioni_canali_tempi_selezionati.get(j).getChannel());
//            }
//            System.out.println("PRIMA ----------------------------------------------");
            
            //----------------------------------------------------------------------
    //      Costruisce la SCNL_list a partire dalle selected stations 
    //      (cercando idSta canali in SeismicNetwork)
            List<ChannelTimeWindow> SCNL_list = BuildChannelsList_WA(DATE_start.toInstant(ZoneOffset.UTC), DATE_end.toInstant(ZoneOffset.UTC), selected_stations);
    //            
            if (((SCNL_list==null)||SCNL_list.isEmpty()) && (Stazioni_canali_tempi_selezionati==null || Stazioni_canali_tempi_selezionati.isEmpty())) return false;
    //                     
            //--------------------------------------------------------------------
            // Merge of the two lists
            //--------------------------------------------------------------------  
            if (SCNL_list != null && !SCNL_list.isEmpty()){
                for (i=0; i< SCNL_list.size(); i++){
                    if (find_for_merge(SCNL_list.get(i), Stazioni_canali_tempi_selezionati)==-1) {
                        if (!Stazioni_canali_tempi_selezionati.contains(SCNL_list.get(i)))
                            Stazioni_canali_tempi_selezionati.add(SCNL_list.get(i));
                    }   
                }
            }
            
//            for (int j=0; j< Stazioni_canali_tempi_selezionati.size(); j++){
//                System.out.println(Stazioni_canali_tempi_selezionati.get(j).getStation() + "   " + Stazioni_canali_tempi_selezionati.get(j).getChannel());
//            }
            
            //----------------------------------------------------------------------
            // A questo punto in params_from_picks c'è tutto quello che vogliamo vedere
            // Iniziamo a processare idSta datasources_waves in ordine (l'orinde 
            // nell'array conicide con la priorità)
            //----------------------------------------------------------------------
            ///int idDataSource=0;
            if (!App.G.IsDirEmpty(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + work_origin_ID  + File.separator +"WA" + File.separator)) {
                App.G.ClearDirectory(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + getWork_origin_ID() + File.separator +"WA" + File.separator);  
            }
            //-------------------------------------------------------------------
            // Fino a quando ci sono datasource abilitati e tracce da cercare...
            //-------------------------------------------------------------------

            if (App.G.options.getDatasources_EW()!=null && !App.G.options.getDatasources_EW().isEmpty()) {
                for (int idEW=0; idEW<App.G.options.getDatasources_EW().size(); idEW++){
                    if (App.G.options.getDatasources_EW().get(idEW).isUsed()){                   
    //                    System.out.println("*****************************************************************+*+");
                        App.logger.info("Downloading horizontal waves from datasource " + App.G.options.getDatasources_EW().get(idEW).getDescription());
    //                    System.out.println("*****************************************************************+*+");

                        // Download data from the Earthworm wave server #idEW
                        RecoverWaves_EW_WA(idEW, Stazioni_canali_tempi_selezionati);

                        Stazioni_canali_tempi_selezionati = Ancora_da_cercare(Stazioni_canali_tempi_selezionati, true); 
                    }
                }
            }
            if (App.G.options.getDatasources_SL()!=null && !App.G.options.getDatasources_SL().isEmpty()) {
                for (int idSL=0; idSL<App.G.options.getDatasources_SL().size(); idSL++){
                    //
                }
            }
            if (App.G.options.getDatasources_FDSN()!=null && !App.G.options.getDatasources_FDSN().isEmpty()) {
                for (int idFDSN=0; idFDSN<App.G.options.getDatasources_FDSN().size(); idFDSN++){
                    if (App.G.options.getDatasources_FDSN().get(idFDSN).isUsed()){                   
    //                    System.out.println("*****************************************************************+*+");
                        App.logger.info("Downloading horizontal waves from datasource " + App.G.options.getDatasources_FDSN().get(idFDSN).getDescription());
    //                    System.out.println("*****************************************************************+*+");

                        // Download data from the Earthworm wave server #idEW
                        RecoverWaves_FDSN_WA(idFDSN, Stazioni_canali_tempi_selezionati);

                        Stazioni_canali_tempi_selezionati = Ancora_da_cercare(Stazioni_canali_tempi_selezionati, true); 
                    }
                }
            }

            return true;
        } catch (Exception ex) {
            return false;
        }    
        
    }
//------------------------------------------------------------------------------           
    public List<ChannelTimeWindow> BuildChannelsList(  
            Instant date_start, Instant date_end,
            ArrayList<String> stations_list 
            ) {
        try {
            List<ChannelTimeWindow> res = new ArrayList<>();
            String sta;   
            if ((stations_list!=null) && (!stations_list.isEmpty())) {
                int idSta;
                for (int i=0; i<stations_list.size(); i++){
                    sta = stations_list.get(i);
                    idSta=App.G.SeismicNet.StationCodeToStationId(sta);
                    
                    String tmp;
                    if (idSta!=-1){
                        tmp = ((Station)App.G.SeismicNet.getStations().get(idSta)).getLocation(((Station)App.G.SeismicNet.getStations().get(idSta)).getChannelsAsStrings().get(0).substring(0,2) + "Z");
                        //if (tmp.equalsIgnoreCase("--")) tmp="??";
                        res.add(new ChannelTimeWindow(((Station)App.G.SeismicNet.getStations().get(idSta)).getNetwork(),
                                ((Station)App.G.SeismicNet.getStations().get(idSta)).getCode(),
                                tmp,
                                ((Station)App.G.SeismicNet.getStations().get(idSta)).FindFirstAvailableChannel().substring(0,2) + "Z",
                                date_start, date_end
                        ));
                    }
                }
            } else {
                double min_lat = innerObjectEvent.getOrigins().get(0).getLat()-0.5;
                double max_lat = innerObjectEvent.getOrigins().get(0).getLat()+0.5;
                double min_lon = innerObjectEvent.getOrigins().get(0).getLon()-0.5;
                double max_lon = innerObjectEvent.getOrigins().get(0).getLon()+0.5;
                res = App.G.SeismicNet.Stations_in_box(min_lat, min_lon, max_lat, max_lon, date_start, date_end);
            }
            //            
            if (res==null) return null;
            if (res.isEmpty()) return null;
            
            return res;
        } catch (Exception ex) {
            return null;
        }
    } 
//------------------------------------------------------------------------------    
    private List<ChannelTimeWindow> BuildChannelsList_WA( 
           Instant date_start, Instant date_end, 
            ArrayList<String> stations_list 
            ) {
        try {
            List<ChannelTimeWindow> res = new ArrayList<>();
            
            String sta, cha;   
            if ((stations_list!=null) && (!stations_list.isEmpty())) {
                int idSta;
                for (int i=0; i<stations_list.size(); i++){
                    sta = stations_list.get(i);
                    idSta=App.G.SeismicNet.StationCodeToStationId(sta);
                    if (idSta!=-1){   
                        cha = ((Station)App.G.SeismicNet.getStations().get(idSta)).FindFirstAvailableChannel().substring(0,2);
                        if (!cha.startsWith("BH")){
                            res.add(new ChannelTimeWindow(((Station)App.G.SeismicNet.getStations().get(idSta)).getNetwork(),
                                ((Station)App.G.SeismicNet.getStations().get(idSta)).getCode(),
                                ((Station)App.G.SeismicNet.getStations().get(idSta)).getLocation(((Station)App.G.SeismicNet.getStations().get(idSta)).getChannelsAsStrings().get(0).substring(0,2) + "N"),
                                cha + "N",
                                date_start, date_end));
                            res.add(new ChannelTimeWindow(((Station)App.G.SeismicNet.getStations().get(idSta)).getNetwork(),
                                    ((Station)App.G.SeismicNet.getStations().get(idSta)).getCode(),
                                    ((Station)App.G.SeismicNet.getStations().get(idSta)).getLocation(((Station)App.G.SeismicNet.getStations().get(idSta)).getChannelsAsStrings().get(0).substring(0,2) + "E"),
                                    cha + "E",
                                    date_start, date_end));
                        }
                    }
                }
            }
            //
            if (res==null) return null;
            if (res.isEmpty()) return null;
            
            return res;
        } catch (Exception ex) {
            return null;
        }
    }    
//------------------------------------------------------------------------------
    public int getNWavesWA(){
        try {
            int res=0;
            if (Stations==null) return 0;
            if (Stations.isEmpty()) return 0;
            for (int i=0; i<Stations.size(); i++){
                res+=Stations.get(i).getNWaves_WA();
            }
            return res;
        } catch (Exception ex) {
            return 0;
        }
    }
//------------------------------------------------------------------------------
    
    
    
//------------------------------------------------------------------------------
    public List<ChannelTimeWindow> Ancora_da_cercare(List<ChannelTimeWindow> inList, boolean makingWA){
    //private ArrayList<String> Ancora_da_cercare(ArrayList<String> inList, boolean makingWA){ // List<ChannelTimeWindow> Selected_Stations){ //ArrayList<String> Selected_Stations){
        try {
            //ArrayList<String> res = new ArrayList<>(inList);
            List<ChannelTimeWindow> res = new ArrayList<>(inList);
            int id=0, staId;
            //String staCode;
            for (id =0; id < inList.size(); id++){
                //staCode =  inList.get(id).getStation(); //   inList.get(id).split(" ")[1];
                staId = StationCode_to_StationId(inList.get(id).getStation());
                if (staId!=-1){
                    if (!makingWA){
                        if (Stations.get(staId).getNWaves()>0)                        
                            res.remove(inList.get(id));  
                    } else {
                        if (Stations.get(staId).getNWaves_WA()>0)                        
                            res.remove(inList.get(id));
                    }
                }    
            }
            
            if (res.isEmpty()) 
                return null;
            else
                return res;
        } catch (Exception ex) {
            return null;
        }
    }

//------------------------------------------------------------------------------    
    private String Query_EWWaveServer(EW_Host EW_SERVER, String data_path, List<ChannelTimeWindow> parametri, int idDataSource, boolean making_WA) {
        EW_Wave_Server_Client wsc=null;
        try {               
            //String[] dati;
            int idStaz;
            int idWaveServer;
            for (int i=0; i<parametri.size(); i++) {
                //dati=parametri.get(idSta).split(" ");
                try {
                    idStaz = this.StationCode_to_StationId(parametri.get(i).getStation()); //this.StationIndex(dati[1]);
                    if (idStaz!=-1) {
                        idWaveServer = this.getStation(idStaz).getEarthWorm_WaveServer_Client_INDEX();
                    } else {   
                        idStaz = App.G.SeismicNet.StationCodeToStationId(parametri.get(i).getStation());
                        idWaveServer = ((Station)App.G.SeismicNet.getStations().get(idStaz)).getEarthWorm_WaveServer_Client_INDEX();
                    }
//                  
                    if (idWaveServer!=-1 ) {
                        //wsc = App.G.EW_SERVER.getWave_server_client_list().get(idWaveServer);   
                        wsc = EW_SERVER.getWave_server_client_list().get(idWaveServer);   
                                               
                        // wsc.readData("STA", "CHAN", "NET", "LOC", date_start, date_end);
                        wsc.readData(parametri.get(i).getStation(), parametri.get(i).getChannel(), parametri.get(i).getNetwork(), parametri.get(i).getLocation(), 
                                new Date(parametri.get(i).getBeginTime().toEpochMilli()),
                                new Date(parametri.get(i).getEndTime().toEpochMilli()), 
                                data_path);


                    } else {
                        App.logger.warn("Skipping data search for " + parametri.get(i).getStation() + " " + parametri.get(i).getChannel() + " because it is not mapped on any earthworm wave-server...");
                    }
                }catch (Exception ex) {
                    App.logger.warn("NODATA for  " + parametri.get(i).getStation()+"."+ parametri.get(i).getChannel() +": " + ex.getMessage()); 
                }                  
            }         
//            
            // If some mseed file is available in the output directory:
            if (!App.G.IsDirEmpty(data_path) && (wsc!=null)) {
                if (making_WA)
                    Arrange_Waves_to_Stations_WA(wsc.BuildWavesList(data_path,parametri), dsType.EARTHWORMWS, idDataSource);
                else
                    Arrange_Waves_to_Stations(wsc.BuildWavesList(data_path, parametri), dsType.EARTHWORMWS,idDataSource);
                return "OK";
            } else return "NODATA for ..."; // + NodeURL;   
        } catch (Exception ex) {
            return null;
        }
    } 
//------------------------------------------------------------------------------
    private String Query_FDSN_Node(String FDSNHostname,int httpport,  String data_path,   
            List<ChannelTimeWindow> parametri, int idDataSource,
            boolean searching_wa) {               
        try { 
            File outputFile = new File(data_path + FDSNHostname +"_data.mseed");
            FDSNDataSelectQueryParams queryParams = new FDSNDataSelectQueryParams();
  
            queryParams.setFdsnwsPath("fdsnws");
            queryParams.setHost(FDSNHostname);
            queryParams.setPort(httpport);
            
            FDSNDataSelectQuerier Q = new FDSNDataSelectQuerier(queryParams, parametri);
                       // Q
            DataRecordIterator it = Q.getDataRecordIterator();
            try {
                if (handleFDSNResults(it, outputFile)){
                    ArrayList<Waveform> WAVES=BuildWavesList(outputFile.getAbsolutePath()); 
                    
                    if (WAVES==null || WAVES.isEmpty()) return "NODATA for " + FDSNHostname; // NodeURL; 
                   
                    if (searching_wa) {
                        Arrange_Waves_to_Stations_WA(WAVES, dsType.FDSN, idDataSource);
                    } else Arrange_Waves_to_Stations(WAVES, dsType.FDSN, idDataSource);
                    //
                    return "OK";
                } else return "NODATA for " + FDSNHostname; //NodeURL; 
            } finally {
                it.close();
            } 
        } catch (Exception ex) {
            return null;
        }
    } 
//------------------------------------------------------------------------------
    public boolean handleFDSNResults(DataRecordIterator drIter, File outputFile) throws IOException, SeedFormatException {
        if (!drIter.hasNext()) {
            App.logger.info("No Data");
            return false;
        }
        DataOutputStream out = null;
        try {
            if (outputFile != null) {
                out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
            }
            while (drIter.hasNext()) {
                DataRecord dr = drIter.next(); 
                if (out != null ) {
                    dr.write(out);
                }  
            }
            return true;
        } catch (Exception ex){
            return false;
        
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
//------------------------------------------------------------------------------
// public void update_ew_waveserver_indexes(){
//     if (this.Stations.isEmpty()) return;
//     int StationID_in_network=-1;
//     for (int i=0; i<this.Stations.size(); i++) {
//         StationID_in_network = App.G.SeismicNet.StationCodeToStationId(this.Stations.get(i).getCode());
//         if (StationID_in_network != -1)
//            this.Stations.get(i).setEarthWorm_WaveServer_Client_INDEX(((Station)App.G.SeismicNet.getStations().get(StationID_in_network)).getEarthWorm_WaveServer_Client_INDEX()); // Deve andare a cercare l'indice giusto!!
//        }
// }
//------------------------------------------------------------------------------
    public boolean Event_has_waves() {
        try {
            int k=0;
            while (k<this.Stations.size()) {
                if (this.Stations.get(k).getNWaves()>0) {
                    return true;
                } else k+=1;
            }
//
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------        
    public boolean Event_has_waves_WA() {
        try {
            int k=0;
            while (k<this.Stations.size()) {
                if (this.Stations.get(k).getNWaves_WA()>0) {
                    return true;
                } else k+=1;
            }
//
            return false;
        } catch (Exception ex) {
            return false;
        }
    }        
//------------------------------------------------------------------------------    
    /**
     * @return the Stations
     */
    public ArrayList<Station> getStations() {
        return Stations;
    }

    /**
     * @param Stations the Stations to set
     */
    public void setStations(ArrayList<Station> Stations) {
        this.Stations = Stations;
    }

    /**
     * @return the TownsReader
     */
//    public TownsListReader getTowns() {
//        return TownsReader;
//    }
//
//    /**
//     * @param Towns the TownsReader to set
//     */
//    public void setTowns(TownsListReader Towns) {
//        this.TownsReader = Towns;
//    }

    /**
     * @return the nOpenWaves
     */
    public int getnOpenWaves() {
        return nOpenWaves;
    }

    /**
     * @param nOpenWaves the nOpenWaves to set
     */
    public void setnOpenWaves(int nOpenWaves) {
        this.nOpenWaves = nOpenWaves;
    }

    /**
     * @return the ActiveStationID
     */
    public int getActiveStationID() {
        return ActiveStationID;
    }

    /**
     * @param ActiveStationID the ActiveStationID to set
     */
    public void setActiveStationID(int ActiveStationID) {
        this.ActiveStationID = ActiveStationID;
    }

    /**
     * @return the ActiveTerna
     */
    public Terna getActiveTerna() {
        return ActiveTerna;
    }

    /**
     * @param ActiveTerna the ActiveTerna to set
     */
    public void setActiveTerna(Terna ActiveTerna) {
        this.ActiveTerna = ActiveTerna;
    }

    
    /**
     * @return the waves_path
     */
    public String getWaves_path() {
        return waves_path;
    }

    /**
     * @param waves_path the waves_path to set
     */
    public void setWaves_path(String waves_path) {
        this.waves_path = waves_path;
    }

    /**
     * @return the waves_path_WA
     */
    public String getWaves_path_WA() {
        return waves_path_WA;
    }

    /**
     * @param waves_path_WA the waves_path_WA to set
     */
    public void setWaves_path_WA(String waves_path_WA) {
        this.waves_path_WA = waves_path_WA;
    }
//------------------------------------------------------------------------------    
    public void ResetLocalspacesAndProvenances(){
        innerObjectEvent.setIdLocalspace(null);
        innerObjectEvent.getLocalspace().setName("sit-rev-event");
        innerObjectEvent.getLocalspace().setDescription("SIT manually reviewed event");
        
        ObjectLocalspace ls = new ObjectLocalspace();
        ls.setName("sit-rev-origin");
        ls.setDescription("sit manually reviewed origin");
        
        ObjectLocalspace ls_mag = new ObjectLocalspace();
        ls_mag.setName("sit-rev-magnitude");
        ls_mag.setDescription("sit manually reviewed magnitude");
        
        if (innerObjectEvent.getOrigins()!=null){
            for (int id_origin=0; id_origin<innerObjectEvent.getOrigins().size(); id_origin++){
                innerObjectEvent.getOrigins().get(id_origin).setIdLocalspace(null);
                innerObjectEvent.getOrigins().get(id_origin).setLocalspace(ls);   
                innerObjectEvent.getOrigins().get(id_origin).setProvenance(sitProvenance);

                if (innerObjectEvent.getOrigins().get(id_origin).getMagnitudes()!=null){
                    for (int id_mags=0; id_mags < innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().size(); id_mags++){
                        innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).setIdLocalspace(null);
                        innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).setLocalspace(ls_mag);  
                        innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).setProvenance(sitProvenance);
                        if (innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).getTypeMagnitude().toUpperCase().contains("MD")){
                            
                            for (int k=0; k < innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).getStationmagnitudes().size(); k++){
                                innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).getStationmagnitudes().get(k).getAmplitude().setLocalspace(ls_mag);
                                innerObjectEvent.getOrigins().get(id_origin).getMagnitudes().get(id_mags).getStationmagnitudes().get(k).getAmplitude().setProvenance(sitProvenance);
                            }

                        }
                    }
                }
            }
        }
    }

    /**
     * @return the TownsInfo
     */
    public List<GetMunicipiDistanceKmPopolazione200ResponseDataInner> getTownsInfo() {
        return TownsInfo;
    }

    /**
     * @param TownsInfo the TownsInfo to set
     */
    public void setTownsInfo(List<GetMunicipiDistanceKmPopolazione200ResponseDataInner> TownsInfo) {
        this.TownsInfo = TownsInfo;
    }

    /**
     * @return the work_event_ID
     */
    public Long getWork_event_ID() {
        return work_event_ID;
    }

    /**
     * @param local_event_ID the work_event_ID to set
     */
    public void setWork_event_ID(Long in_work_event_ID) {
        this.work_event_ID = in_work_event_ID;
    }

    /**
     * @return the original_hostname
     */
    public String getOriginal_hostname() {
        return original_hostname;
    }

    /**
     * @param original_hostname the original_hostname to set
     */
    public void setOriginal_hostname(String original_hostname) {
        this.original_hostname = original_hostname;
    }
    
//------------------------------------------------------------------------------    
    public boolean SetSemaphoreOnOrigin(String ON_or_OFF){
        return true;
//        try{
//            if (ON_or_OFF.equalsIgnoreCase("ON")) {
//                StoreApi writer=new StoreApi();
//                ObjectOriginFlag originFlagItem = new ObjectOriginFlag();
//                originFlagItem.setName("SEMAPHORE");
//                writer.getApiClient().setReadTimeout(30000);
//                AddOriginFlagRequest addOriginFlagRequest= new AddOriginFlagRequest();
//                AddOriginFlagRequestData data = new AddOriginFlagRequestData();
//            
//                originFlagItem.setValue(Float.valueOf("1")); 
//                //originFlagItem.setNote(App.G.sitUsername + " on " + App.G.sitHostname);
//                originFlagItem.setNote(App.G.sitUsername + " on " + App.G.sitHostname);
//                 data.setOriginid(innerObjectEvent.getOrigins().get(0).getId());
//                data.addOriginFlagItem(originFlagItem);
//
//                addOriginFlagRequest.setData(data);
//
//                try {   
//                    writer.addOriginFlag(addOriginFlagRequest);
//                    // Semaphore properly set ON/OFF
//                } catch (ApiException aex){
//                    sitDialog.ShowErrorMessage("Could not switch semaphore " + ON_or_OFF +  "!!");
//                    return false;
//                }
//                writer=null;
//            } else {
//                Long origin_flag_id = Long.valueOf("-1");
//                DeleteApi d = new DeleteApi();
//                GetApi g = new GetApi();
//                g.getApiClient().setReadTimeout(30000);
//                d.getApiClient().setWriteTimeout(30000);
//                // First we have to retrieve the origin flag  id
//                GetOriginFlag200Response resp = g.getOriginFlag(innerObjectEvent.getOrigins().get(0).getId(), null, null, App.G.sitUsername + " on " + App.G.sitHostname);
//                if ((resp!=null) && (resp.getData()!=null)){
//                    if (!resp.getData().isEmpty()){
//                        int idSta=0;
//                        boolean fnd=false;
//                        while ((idSta<resp.getData().size()) && !fnd){
//                            if (resp.getData().get(idSta).getName().equalsIgnoreCase("SEMAPHORE")){
//                                origin_flag_id=resp.getData().get(idSta).getId();
//                                fnd =true;
//                            } else idSta++;
//                        }       
//                    }    
//                }
//                
//                // If we found a SEMAPHORE (origin_flag_id!=-1) then we can remove it
//                if (origin_flag_id > -1)
//                    d.deleteOriginFlag(origin_flag_id);      
//                
//                d=null;
//                g=null;
//            }
//
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
    }
//------------------------------------------------------------------------------    
    public String getSemaphoreOrOtherFlags(){
        try {
            if (innerObjectEvent.getOrigins().get(0).getFlags()!=null)
                return innerObjectEvent.getOrigins().get(0).getFlags();
            else
                return "";
        } catch (Exception ex) {
            return "";
        }
    }
//------------------------------------------------------------------------------    
    public boolean RemoveStation(int StationIndex){
        try{
            if (Stations==null) return false;
            if (Stations.size()<=1) return false;
            //int idSta=StationCode_to_StationId(StationCode);
            //if (idSta==-1) return false;
            //
            
            Stations.remove(StationIndex);
            
            
            
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * @return the innerObjectEvent
     */
    public ObjectEvent getInnerObjectEvent() {
        return innerObjectEvent;
    }

    /**
     * @param innerObjectEvent the innerObjectEvent to set
     */
    public void setInnerObjectEvent(ObjectEvent innerObjectEvent) {
        this.innerObjectEvent = innerObjectEvent;
    }

    /**
     * @return the work_origin_ID
     */
    public Long getWork_origin_ID() {
        return work_origin_ID;
    }

    /**
     * @param work_origin_ID the work_origin_ID to set
     */
    public void setWork_origin_ID(Long work_origin_ID) {
        this.work_origin_ID = work_origin_ID;
    }
    
    
    public boolean HasAmplitudes(){
        if ((innerObjectEvent.getOrigins()==null)||(innerObjectEvent.getOrigins().isEmpty())) return false;
        if ((innerObjectEvent.getOrigins().get(0).getMagnitudes()==null)||(innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty())) return false;
        
        for (int i=0; i< innerObjectEvent.getOrigins().get(0).getMagnitudes().size(); i++){
            if ((innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getStationmagnitudes()!=null) && (!innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getStationmagnitudes().isEmpty())) 
                return true;
        }
        
        return false;
    }
    
    public boolean HasMD(){
        if ((innerObjectEvent.getOrigins()==null)||(innerObjectEvent.getOrigins().isEmpty())) return false;
        if ((innerObjectEvent.getOrigins().get(0).getMagnitudes()==null)||(innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty())) return false;
        
        for (int i=0; i< innerObjectEvent.getOrigins().get(0).getMagnitudes().size(); i++){
            if  (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("MD"))
                return true;
        }
        
        return false;
    }
    
//    public void RemoveMLs(){
//        if ((innerObjectEvent.getOrigins()==null)||(innerObjectEvent.getOrigins().isEmpty())) return;
//        if ((innerObjectEvent.getOrigins().get(0).getMagnitudes()==null)||(innerObjectEvent.getOrigins().get(0).getMagnitudes().isEmpty())) return;
//        
//        for (int i=0; i< innerObjectEvent.getOrigins().get(0).getMagnitudes().size(); i++){
//            if (innerObjectEvent.getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("ML")){
//                innerObjectEvent.getOrigins().get(0).getMagnitudes().remove(i);
//                RemoveMLs();
//            }
//        }
//    }
    
    public boolean reReadMagnitudes(){
        // Returns TRUE if some amplitude is found
        try {
            Long ID = innerObjectEvent.getOrigins().get(0).getId();
            if (ID==null) ID = this.getWork_origin_ID();
            // Attempt to re-read this event
            if (!read_only_magnitudes(ID)) {
                return false;
            } else {
                int idML = findMLid();
                if (idML!=-1){
                    if ((innerObjectEvent.getOrigins().get(0).getMagnitudes().get(idML).getStationmagnitudes()!=null) && !(innerObjectEvent.getOrigins().get(0).getMagnitudes().get(idML).getStationmagnitudes().isEmpty()))
                        return true;
                } else return false;
            
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
// 
    private boolean read_only_magnitudes(Long originId) {
        /*
        Reads the event magnitudes array starting from the originId of 
        the hypocenter (origin) 
        */  
        GetApi ReadClient;
        try {

            ReadClient = new GetApi();
            ReadClient.getApiClient().setReadTimeout(30000);
            
            App.logger.debug("WS-LOG: read_only_magnitudes--> ReadClient.getEvent reading");
            GetEvent200Response myResp= ReadClient.getEvent(originId, null, null,null,null);

            if (myResp!=null){  
                App.logger.debug("WS-LOG: read_only_magnitudes--> ReadClient.getEvent received response");
                if (myResp.getData().getEvent()==null) {
                    // No origin found
                    return false;
                } else {   
                    if (myResp.getData().getEvent().getOrigins()==null) return false;   // Event has no origins
                    //
                    if ((myResp.getData().getEvent().getOrigins().get(0).getMagnitudes()!=null)&& !(myResp.getData().getEvent().getOrigins().get(0).getMagnitudes().isEmpty())){
                        innerObjectEvent.getOrigins().get(0).setMagnitudes(myResp.getData().getEvent().getOrigins().get(0).getMagnitudes());
                        return true;
                    } else {
                        return false;
                    }
                } 
            }
//
            return false;
        } catch (Exception ex) {
            //pfxDialog.ShowErrorMessage("Error reading only magnitudes!",null);
            return false;
        } finally {
            ReadClient = null;
        }
    }
//------------------------------------------------------------------------------    
    public void ShowWavesWindow(){
        try { 
            //Event_on_Map.SetSemaphoreOnOrigin("ON");
//            
            if (App.G.WavesControllers==null) App.G.WavesControllers = new ArrayList<>();
//                                
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("WavesForm.fxml"));    
            Parent root1 = (Parent) fxmlLoader1.load();
                       
            Stage waves_primarystage = new Stage();
            List<Screen> screens = Screen.getScreens();
            if(screens.size()>1){
                
                int wavesScreen=1;
                double offsetX = 0;
                double offsetY = 0;
                
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    
                  Rectangle2D rec = screens.get(wavesScreen).getVisualBounds();
                  // upper left corner of the extended screen
                  offsetX = rec.getMinX();
                  offsetY = rec.getMinY();
                  // set relative to this screen
                  waves_primarystage.setX(primaryScreenBounds.getMinX() + offsetX);
                  waves_primarystage.setY(primaryScreenBounds.getMinY() + offsetY); 
                  //waves_primarystage.setFullScreen(true);
                  waves_primarystage.setMaximized(true);
            }
  
            waves_primarystage.getIcons().add(new Image(("images/waves.png")));                       
            waves_primarystage.setScene(new Scene(root1));              
            waves_primarystage.setTitle("SIT - Event (Origin ID): " + this.getInnerObjectEvent().getOrigins().get(0).getId());
           
            waves_primarystage.setMaximized(true); 
            waves_primarystage.requestFocus();
            //waves_primarystage.setAlwaysOnTop(true);
           
            App.G.WavesControllers.add((WavesFormController)fxmlLoader1.getController());
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).setIdControllers(App.G.WavesControllers.size()-1);
            
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).setPrimaryStage(waves_primarystage);
//            
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).setMyEvent(this);            
//            
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).getMyEvent().setWaves_path(App.G.WAVES_BASKET_PATH +  App.G.sitUsername + File.separator + String.valueOf(this.getInnerObjectEvent().getOrigins().get(0).getId()));
//            
            waves_primarystage.setOnShown(event -> {
                event.consume();
                App.G.WavesControllers.get(App.G.WavesControllers.size()-1).DisplayAll(); 
                App.G.WavesControllers.get(App.G.WavesControllers.size()-1).populate_stations_combo();
                App.G.WavesControllers.get(App.G.WavesControllers.size()-1).setM_stageShowing(true);
                App.G.WavesControllers.get(App.G.WavesControllers.size()-1).initPhasesSwapThread();                 
            });  
                       
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).getPrimaryStage().show();
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).getPrimaryStage().setMaximized(true);
            App.G.WavesControllers.get(App.G.WavesControllers.size()-1).resizemap();
          
        }  catch (Exception ex) {
            //Event_on_Map.SetSemaphoreOnOrigin("OFF");
            sitDialog.ShowErrorMessage("Error while loading waves.\n" + ex.getMessage(),null);
        }     
    }
    
    
    
    public boolean HasCodas(){
        try {
            for (int i =0; i< Stations.size(); i++){
                if (Stations.get(i).getCoda_duration()>0.0){
                    return true;
                }
            }         
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
      
    public ArrayList<duration_buffer_item> BackupCodas(){
        try {
            ArrayList<duration_buffer_item> res = new ArrayList();
            duration_buffer_item buf;
            if (Stations==null) return null;
            if (Stations.isEmpty()) return null;
            
            for (int i =0; i< Stations.size(); i++){
                if (Stations.get(i).getCoda_duration()>0.0){
                    buf = new duration_buffer_item();
                    buf.setStation_code(Stations.get(i).getCode());
                    buf.setDuration(Stations.get(i).getCoda_duration());
                    
                    res.add(buf);
                }
            }
//
            return res;
        } catch (Exception ex) {
            
            return null;
        }   
    }
    
    public void RestoreCodas(ArrayList<duration_buffer_item> buf){
        if (buf==null) return;
        if (buf.isEmpty()) return;
        
        int id;
        
        for (int i=0; i<buf.size(); i++){
            id = StationCode_to_StationId(buf.get(i).getStation_code());
            if (id > -1)
                Stations.get(id).setCoda_duration(buf.get(i).getDuration());
        }
    }
    
    public void CalculateMD(){
        try {
            ObjectStationmagnitude sm=null;
            ObjectMagnitude MD = new ObjectMagnitude();
           
            MD.setTypeMagnitude("Md");
            MD.setProvenance(getSitProvenance());
            MD.setLocalspace(sitLocalSpace);
            
            double somma=0;
            int n=0;
            ObjectArrival P;
//            DecimalFormat df = new DecimalFormat("#.##");
//            df.setRoundingMode(RoundingMode.CEILING);
            for (int i = 0; i< Stations.size(); i++){
                if (Stations.get(i).getCoda_duration()>0.0){
                    P = Stations.get(i).FindFirstPhase_fase("P");
                    sm = new ObjectStationmagnitude();
                    sm.setAmplitude(new ObjectAmplitude());
                    sm.getAmplitude().setSta(Stations.get(i).getCode());
                    sm.getAmplitude().setCha(P.getPick().getCha());
                    sm.getAmplitude().setNet(P.getPick().getNet());
                    sm.getAmplitude().setLoc(P.getPick().getLoc());
                    
                    sm.getAmplitude().setTypeAmplitude(new ObjectAmplitudeTypeAmplitude());
                    sm.getAmplitude().getTypeAmplitude().setName("Duration");
                    
                    sm.setEpDistanceKm(P.getEpDistanceKm());
                    
                    sm.setTypeMagnitude("Md");
                    
                    sm.getAmplitude().setAmp1((double)Stations.get(i).getCoda_duration());
                    sm.getAmplitude().setTime1(P.getPick().getArrivalTime());
                    
                    sm.getAmplitude().setLocalspace(sitLocalSpace);
                    sm.getAmplitude().setProvenance(getSitProvenance());
 
                    double D=P.getEpDistanceKm();   // Distanza in Km
                     
                    sm.setMag(2*Math.log10((double)Stations.get(i).getCoda_duration() + 0.082*D) - 0.87);
                    //  Md=2.0*log((T) + 0.082*d) -0.87 + MCORsta(n)
                                
                    somma+=sm.getMag();
                    n+=1;
                    
                    MD.addStationmagnitudesItem(sm);
                }
            }
                
            MD.setMag(somma/n);
            MD.setUpperUncertainty((double)MD.getMag()/2.0);
            
            if (innerObjectEvent.getOrigins().get(0).getMagnitudes()==null) innerObjectEvent.getOrigins().get(0).setMagnitudes(new ArrayList<ObjectMagnitude>());
            innerObjectEvent.getOrigins().get(0).getMagnitudes().add(MD);
            
        } catch (Exception ex) {
            //
        }
    }
//------------------------------------------------------------------------------
    public void ApplyRangeLimitation(float rangekm){
        try {
            if (Stations==null) return;
            if (Stations.isEmpty()) return;
            
//            for (int idSta=0; idSta<Stations.size(); idSta++){
//                if ((Stations.get(idSta).getPhases()!=null) & (Stations.get(idSta).getPhase(0).getEpDistanceKm()> rangekm))
//                    Stations.get(idSta).Exclude_from_location();
//                
//                // else LA INCLUDE?????
//                
//            }
            for (int i=0; i<Stations.size(); i++){
                if (Stations.get(i).getPhases()!=null){
                    if(Stations.get(i).getPhase(0).getEpDistanceKm()> rangekm)
                        Stations.get(i).Exclude_from_location();
                    else
                        Stations.get(i).Include_in_location();
                } 
                
                
                // else LA INCLUDE?????
                
            }
           
            
        } catch (Exception ex){
        }
    }

    /**
     * @return the AddingStations
     */
    public boolean isAddingStations() {
        return AddingStations;
    }

    /**
     * @param AddingStations the AddingStations to set
     */
    public void setAddingStations(boolean AddingStations) {
        this.AddingStations = AddingStations;
    }

    /**
     * @return the idController
     */
    public int getIdController() {
        return idController;
    }

    /**
     * @param idController the idController to set
     */
    public void setIdController(int idController) {
        this.idController = idController;
    }
    
    
//------------------------------------------------------------------------------    
    public ArrayList<ObjectArrival> BackupUnusedArrivals(){
        try {
            ArrayList<ObjectArrival> res = new ArrayList();
            if (innerObjectEvent.getOrigins().get(0).getArrivals()==null) return null;
            if (innerObjectEvent.getOrigins().get(0).getArrivals().isEmpty()) return null;
            
            for (int i=0; i< innerObjectEvent.getOrigins().get(0).getArrivals().size(); i++){
                if (!innerObjectEvent.getOrigins().get(0).getArrivals().get(i).getArrTimeIsUsed()){
                    innerObjectEvent.getOrigins().get(0).getArrivals().get(i).getPick().setQualityClass(8);
                    innerObjectEvent.getOrigins().get(0).getArrivals().get(i).setWeight(0.0);
                    res.add(innerObjectEvent.getOrigins().get(0).getArrivals().get(i));
                }
            }            
            if (!res.isEmpty())
                return res;
            else 
                return null;
        } catch (Exception ex) {
            return null;
        }
    }
//------------------------------------------------------------------------------    
    public void RestoreUnusedArrivals(ArrayList<ObjectArrival> unused_arrivals){
        try {
            if (unused_arrivals==null) return;
            if (unused_arrivals.isEmpty()) return;
            if (innerObjectEvent.getOrigins()==null) return;
            if (innerObjectEvent.getOrigins().isEmpty()) return;
            if (innerObjectEvent.getOrigins().get(0).getArrivals()==null) {
                innerObjectEvent.getOrigins().get(0).setArrivals(unused_arrivals);
            } else {
                innerObjectEvent.getOrigins().get(0).getArrivals().addAll(unused_arrivals);
            }
            
        } catch (Exception ex){
        }
    } 
//------------------------------------------------------------------------------
    public boolean read_from_SAC_folder(String folderPath){
        // Reads all SAC file headers in a specified folder to build an Event
        Station s;
        ObjectArrival tmpP, tmpS;
        LocalDateTime absolute_starttime;
        Waveform tmpW;    
        // Create a dummy ObjectOrigin
        ObjectOrigin Orig = new ObjectOrigin();
        Orig.setLat(0.0);
        Orig.setLon(0.0);
        Orig.setDepth(0.0);
            
        try{
            File SACrepository = new File (folderPath);
            
            FilenameFilter filtro = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                        boolean value;
                        // return files only that begins with test
                        if(name.toUpperCase().endsWith(".SAC")){
                                value=true;
                        }
                        else{
                                value=false;
                        }
                        return value;
                }
            };
            
            // List of SAC files as Files
            List<File> sacfileList = new ArrayList<File>();
            File[] lista = SACrepository.listFiles(filtro);            
            sacfileList.addAll(Arrays.asList(lista));
                           
            PrintWriter out = new PrintWriter(System.out, true);
            
            int stId;
            SacTimeSeries tempSACTimeSeries;
            for (File sacFile : sacfileList) {
                s=null;
                if (sacFile.exists() && sacFile.isFile()) {
                    tempSACTimeSeries =  edu.sc.seis.seisFile.sac.SacTimeSeries.read(sacFile);
                    if ((Orig.getOt()==null) && (tempSACTimeSeries.getHeader().getO()!=-12345)){
                        // Calcola e imposta il T0 dell'evento
                        // todo...
                    }
                                   
                    //SacIncrementalloader loader = new SacIncrementalloader(sacFile);
                    //SacHeader header = loader.getHeader();
                    String filename = sacFile.getName();
                    boolean byteOrder = tempSACTimeSeries.getHeader().getByteOrder();
                    String byteOrderStr = byteOrder ? "big endian" : "little endian";
                    
                    out.println();
                    out.println(filename+" ("+byteOrderStr+")");
                    String dashLine = "";
                    for (int j = 0; j < filename.length(); j++) {
                        dashLine += "-";
                    }
                    out.println(dashLine);
                    out.println();
                    tempSACTimeSeries.getHeader().printHeader(out);
               
                    if (tempSACTimeSeries.getHeader().getKcmpnm().trim().endsWith("Z")){
                        stId = StationCode_to_StationId(tempSACTimeSeries.getHeader().getKstnm().trim().toUpperCase());
                        if (stId==-1){
                            s = new Station(tempSACTimeSeries.getHeader().getKstnm().trim().toUpperCase(), 
                                    "", tempSACTimeSeries.getHeader().getStla(), tempSACTimeSeries.getHeader().getStlo(), 
                                    tempSACTimeSeries.getHeader().getStel(), tempSACTimeSeries.getHeader().getKnetwk().trim());
                            if (Stations==null) Stations = new ArrayList<Station>();
                            Stations.add(s);
                        } else {
                            s = Stations.get(stId);
                        } 

                        // Prende il tempo del primo campione
                        int dayOfYear = tempSACTimeSeries.getHeader().getNzjday() ;
                        Year y = Year.of(tempSACTimeSeries.getHeader().getNzyear());
                        LocalDate ld = y.atDay(dayOfYear);

                        LocalTime lt = LocalTime.of(tempSACTimeSeries.getHeader().getNzhour(), 
                                tempSACTimeSeries.getHeader().getNzmin(), 
                                tempSACTimeSeries.getHeader().getNzsec(), 
                                tempSACTimeSeries.getHeader().getNzmsec()*1000000);
                        absolute_starttime = ld.atTime(lt);    

                        //--------------------------------------------------------------
                        // Recupera la fasi 
                        //--------------------------------------------------------------
                        if (tempSACTimeSeries.getHeader().getA()!=-12345){     // There's a P phase                    
                            tmpP = new ObjectArrival();
                            tmpP.setPick(new ObjectPick());  
                            if ((tempSACTimeSeries.getHeader().getKa()!=null) && (!tempSACTimeSeries.getHeader().getKa().isBlank()))
                                tmpP.setIscCode(tempSACTimeSeries.getHeader().getKa().trim());
                            else
                                tmpP.setIscCode("P");
                            tmpP.getPick().setSta(s.getCode());
                            tmpP.getPick().setCha(tempSACTimeSeries.getHeader().getKcmpnm().trim());
                            tmpP.getPick().setNet(tempSACTimeSeries.getHeader().getKnetwk().trim());
                            tmpP.getPick().setLoc("--"); // Station or sensor location code
                            tmpP.setEpDistanceKm(tempSACTimeSeries.getHeader().getDist());
                            tmpP.setAzimut(tempSACTimeSeries.getHeader().getAz());
                            long nanosP = (long)(tempSACTimeSeries.getHeader().getA() * 1000000000);
                            tmpP.getPick().setArrivalTime(absolute_starttime.plusNanos(nanosP).atOffset(ZoneOffset.UTC));
                            
                            tmpP.setArrTimeIsUsed(true);

                            if (s.getPhases()==null) s.setPhases(new ArrayList<ObjectArrival>());
                            s.getPhases().add(tmpP);
                        }
                        if (tempSACTimeSeries.getHeader().getT0()!=-12345){     // There's an S phase

                            tmpS = new ObjectArrival();
                            tmpS.setPick(new ObjectPick()); 
                            if ((tempSACTimeSeries.getHeader().getKa()!=null) && (! tempSACTimeSeries.getHeader().getKa().isBlank()))
                                tmpS.setIscCode(tempSACTimeSeries.getHeader().getKt0().trim());
                            else
                                tmpS.setIscCode("S");
                            tmpS.getPick().setSta(s.getCode());
                            tmpS.getPick().setCha(tempSACTimeSeries.getHeader().getKcmpnm().trim());
                            tmpS.getPick().setNet(tempSACTimeSeries.getHeader().getKnetwk().trim());
                            tmpS.getPick().setLoc("--"); // Station or sensor location code
                            tmpS.setEpDistanceKm(tempSACTimeSeries.getHeader().getDist());
                            tmpS.setAzimut(tempSACTimeSeries.getHeader().getAz());

                            long nanosS = (long)(tempSACTimeSeries.getHeader().getT0() * 1000000000);
                            tmpS.getPick().setArrivalTime(absolute_starttime.plusNanos(nanosS).atOffset(ZoneOffset.UTC));
                            
                            tmpS.setArrTimeIsUsed(true);

                            if (s.getPhases()==null) s.setPhases(new ArrayList<ObjectArrival>());
                            s.getPhases().add(tmpS);
                        }  
                      
                        // Legge i campioni e li aggiunge come Waveform alla stazione
                        if (tempSACTimeSeries.getHeader().getNpts()>0){
                            tmpW = new Waveform();
                            tmpW.setDataProvider("LOCALHOST");
                            tmpW.setChannelCode(tempSACTimeSeries.getHeader().getKcmpnm().trim());
                            tmpW.setFilename(sacFile.getAbsolutePath());
                            tmpW.setLocationCode("--");
                            tmpW.setNetworkCode( tempSACTimeSeries.getHeader().getKnetwk().trim());
                            tmpW.setStationCode(s.getCode());
                            tmpW.setStartTime(absolute_starttime);
                            tmpW.setSamplingRate((float)1.0/tempSACTimeSeries.getHeader().getDelta());
                            tmpW.setnSamples(tempSACTimeSeries.getHeader().getNpts());
                            
                            tmpW.setY(tempSACTimeSeries.getY());
                            
                            
                            if (s.getWaves()==null) s.setWaves(new ArrayList<Waveform>());
                            s.getWaves().add(tmpW);
                        }
                    }
                } else {
                    out.println("Cannot load, "+sacFile.getPath()+" exists="+sacFile.exists()+" isFile="+sacFile.isFile());
                }
            }
            
            sitLocalSpace = new ObjectLocalspace(); 
            sitLocalSpace.setName("sit");
            sitLocalSpace.setDescription("Manually reviewed");    // Differenziare per bollettino

            setSitProvenance(new ObjectProvenance()); 
            getSitProvenance().setName("INGV");
            getSitProvenance().setDescription("SIT interactive revision");
            getSitProvenance().setHostname(App.G.sitHostname);
            getSitProvenance().setMethod("ToDoSIT");
            getSitProvenance().setModel("ToDoSIT");
            getSitProvenance().setParameters("ToDoSIT");
            getSitProvenance().setProgram("sit");
            getSitProvenance().setSoftwarename("SIT");
            getSitProvenance().setUsername(App.G.sitUsername);
            getSitProvenance().setVersion("1.0");
            getSitProvenance().setEvaluationmode(ProvenanceEvaluationmode.MANUAL);
            if ((App.G.User!=null)&&(App.G.User.isLoggedIn()))
                getSitProvenance().setUsername(App.G.User.getName());
            
            
            Orig.setProvenance(getSitProvenance());
            //O.setOt();
            
            // Copy the arrivals from the picks stored in stations
            for (int i=0; i<this.getNStations(); i++){
                if (Stations.get(i).getNPhases()>0){
                    for (int idPh=0; idPh<Stations.get(i).getNPhases(); idPh++){
                        ObjectArrival tmpA = new ObjectArrival();
                        tmpA = Stations.get(i).getPhase(idPh);
                        if (Orig.getArrivals()==null)
                            Orig.setArrivals(new ArrayList<ObjectArrival>());
                        Orig.getArrivals().add(tmpA);
                    }
                }
            }

            if (innerObjectEvent.getOrigins()==null)
                innerObjectEvent.setOrigins(new ArrayList<ObjectOrigin>());
           
            innerObjectEvent.getOrigins().add(Orig);
            
            TownsInfo = new TownsListReader(0.0, 50.0, Long.valueOf("0"), innerObjectEvent.getOrigins().get(0).getLat(), innerObjectEvent.getOrigins().get(0).getLon()).Read();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            tmpP=null;
            tmpS=null;
            s=null;
            absolute_starttime=null;
        }
    }
//------------------------------------------------------------------------------    
    public Float AzimutPerStation(String Staz){
        try{
            int idStaz=StationCode_to_StationId(Staz);
            if (idStaz==-1) return Float.valueOf(-1);
            if (Stations.get(idStaz).getPhases() == null) return Float.valueOf(-1);
            if (Stations.get(idStaz).getPhases().isEmpty()) return Float.valueOf(-1);
            
            if (((ObjectArrival)Stations.get(idStaz).getPhases().get(0)).getAzimut() != null)
                return ((ObjectArrival)Stations.get(idStaz).getPhases().get(0)).getAzimut();
            else 
                return Float.valueOf(-1);
                
        } catch (Exception ex){
            return Float.valueOf(-1);
        }
    }
//------------------------------------------------------------------------------
    public void SortStationsByP(){
        try {
            if (Stations==null) return;
            if (Stations.isEmpty()) return;
            Station swap;
            for (int i=0; i< Stations.size()-1; i++){
                for (int j=i+1; j < Stations.size(); j++){
                   if (Stations.get(i).getNPhases()>0 && Stations.get(j).getNPhases()>0){
                       if (((ObjectArrival)(Stations.get(i).getPhases().get(0))).getPick().getArrivalTime().isAfter(((ObjectArrival)(Stations.get(j).getPhases().get(0))).getPick().getArrivalTime())){
                           swap = new Station();
                           swap = Stations.get(i);
                           Stations.set(i, Stations.get(j));
                           Stations.set(j, swap);
                       }              
                   }
                }
            }
        } catch (Exception ex){
        }
    }

    /**
     * @return the sitProvenance
     */
    public ObjectProvenance getSitProvenance() {
        return sitProvenance;
    }

    /**
     * @param sitProvenance the sitProvenance to set
     */
    public void setSitProvenance(ObjectProvenance sitProvenance) {
        this.sitProvenance = sitProvenance;
    }
    
    
}