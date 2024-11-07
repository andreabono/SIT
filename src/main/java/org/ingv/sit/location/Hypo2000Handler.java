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
package org.ingv.sit.location;

import java.net.InetAddress;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ingv.apollo.ApiException;
import org.ingv.apollo.api.LocationApi;
import org.ingv.apollo.model.Hyp2000arcPhasesInner;
import org.ingv.apollo.model.V2PostHyp2000Request;
import org.ingv.apollo.model.V2PostHyp2000RequestData;
import org.ingv.apollo.model.Hyp2000arcSchema;
import org.ingv.apollo.model.ObjectHyp2000Output;
import org.ingv.apollo.model.ObjectHyp2000Phase;
import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectOrigin;
import org.ingv.dante.model.ObjectPick;
import org.ingv.dante.model.ObjectProvenance;
import org.ingv.dante.model.ObjectTypeOrigin;
import org.ingv.dante.model.PickFirstmotion;
import org.ingv.dante.model.TypeOriginName;
import org.ingv.sit.App;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.datamodel.Station;
import org.ingv.sit.utils.pfxDialog;
import org.ingv.sit.webservices.Hypo2000_configuration;

public class Hypo2000Handler {
    private String hypo2000URL;
    private Hypo2000_configuration Hypo2000_CONFIG;
//------------------------------------------------------------------------------    
    public Hypo2000Handler(String in_service_url){
        hypo2000URL = in_service_url;
        Hypo2000_CONFIG = new Hypo2000_configuration("configuration/Hypo2000_configuration.txt",
                "configuration/Hypo2000_MODEL_configuration.txt");
    } 
//------------------------------------------------------------------------------        
    public Event PostRequest(Event EV){
        ObjectLocalspace pfxLocalSpace;
        ObjectProvenance pfxProvenance;
        String pfxHostname, pfxUsername;
        
        try {
            pfxHostname=InetAddress.getLocalHost().getHostName();
        } catch (Exception ex){
            pfxHostname="Unknown pfx host";
        }
        try {
            pfxUsername=System.getProperty("user.name");
        } catch (Exception ex){
            pfxUsername="Unknown pfx user";
        }
        
//        Package pack = getClass().getPackage();
//        String pfxVersion = pack.getImplementationVersion();
           
        pfxLocalSpace = new ObjectLocalspace(); 
        pfxLocalSpace.setName("pfx");
        pfxLocalSpace.setDescription("Manually reviewed");    // Differenziare per bollettino

        pfxProvenance = new ObjectProvenance(); 
        pfxProvenance.setName("INGV");
        pfxProvenance.setDescription("PFX interactive revision");
        pfxProvenance.setHostname(pfxHostname);
        pfxProvenance.setMethod("ToDoPFX");
        pfxProvenance.setModel("ToDoPFX");
        pfxProvenance.setParameters("ToDoPFX");
        pfxProvenance.setProgram("ToDoPFX");
        pfxProvenance.setSoftwarename("PFX");
        pfxProvenance.setUsername(pfxUsername);
        pfxProvenance.setVersion("1.0");
        
        try {
            Event res=new Event(EV.getIdController());
                                    
            LocationApi locApi = new LocationApi();
            locApi.getApiClient().setBasePath(hypo2000URL);
            locApi.getApiClient().setReadTimeout(30000);

            V2PostHyp2000Request req = new V2PostHyp2000Request();
            V2PostHyp2000RequestData data = new V2PostHyp2000RequestData();
            //--------------------------------
            // Hypo2000 Settings configuration
            //--------------------------------
            List<String> hyp2000Conf = build_HYP2000_CONF();
            
            data.setHyp2000Conf(hyp2000Conf); 
            
            //--------------------------------
            // Hypo2000 model configuration
            //--------------------------------
            List<String> model = build_MODEL();
            data.setModel(model);
                 
            //--------------------------------
            // Hypo2000 phases for location
            //--------------------------------
            List<ObjectHyp2000Phase> phases= build_PHASES(EV);
            data.setPhases(phases);

            //--------------------------------
            // Setting the output format
            //--------------------------------
            data.setOutput(ObjectHyp2000Output.JSON);
            
            //--------------------------------
            // Setting the data for the POST
            //--------------------------------
            req.setData(data);
            
            //--------------------------------
            // Posting the request
            //--------------------------------
            
            App.logger.debug(req.toJson());
            
            System.out.println(req.toJson());
                       
            App.logger.debug("WS-LOG: LocationApi.v2PostHyp2000 posting");
            Hyp2000arcSchema ARC = locApi.v2PostHyp2000(req);
            
            App.logger.debug("WS-LOG: LocationApi.v2PostHyp2000 received response");
            //------------------------------------------------------------------
            // Swap the resulting ARC event into our pfx class Event
            //------------------------------------------------------------------
            res.setOriginal_hostname(EV.getOriginal_hostname());
            res.getInnerObjectEvent().setProvenance(pfxProvenance);
            res.getInnerObjectEvent().setLocalspace(pfxLocalSpace);
            
            //res.setOrigins(EV.getOrigins());
            ArrayList<ObjectOrigin> arr = new ArrayList();
            arr.add(new ObjectOrigin());
            res.getInnerObjectEvent().setOrigins(arr);
            res.getInnerObjectEvent().getOrigins().get(0).setLat(ARC.getEwMessage().getLatitude());
            res.getInnerObjectEvent().getOrigins().get(0).setLon(ARC.getEwMessage().getLongitude());
            res.getInnerObjectEvent().getOrigins().get(0).setDepth(ARC.getEwMessage().getDepth());
            res.getInnerObjectEvent().getOrigins().get(0).setOt(ARC.getEwMessage().getOriginTime());
            
            res.getInnerObjectEvent().getOrigins().get(0).setAzimGap(ARC.getEwMessage().getGap());
            res.getInnerObjectEvent().getOrigins().get(0).setErrDepth(ARC.getEwMessage().getErz());
            res.getInnerObjectEvent().getOrigins().get(0).setErrH(ARC.getEwMessage().getErh());
            
            res.getInnerObjectEvent().getOrigins().get(0).setErrOt((double)0);  //Hypo2000 non calcola l'errore sul T0
            
            res.getInnerObjectEvent().getOrigins().get(0).setErrZ(ARC.getEwMessage().getErz());
            
            res.getInnerObjectEvent().getOrigins().get(0).setFixDepth(Hypo2000_CONFIG.isFixDepth());
            
            
            res.getInnerObjectEvent().getOrigins().get(0).setIdLocalspace(null);
            res.getInnerObjectEvent().getOrigins().get(0).setProvenance(pfxProvenance);   
            res.getInnerObjectEvent().getOrigins().get(0).setLocalspace(pfxLocalSpace);
            res.getInnerObjectEvent().getOrigins().get(0).setNph(ARC.getEwMessage().getNph());
            res.getInnerObjectEvent().getOrigins().get(0).setNphS(ARC.getEwMessage().getNphS());
            res.getInnerObjectEvent().getOrigins().get(0).setNphTot(ARC.getEwMessage().getNphtot());
            res.getInnerObjectEvent().getOrigins().get(0).setNphFm(ARC.getEwMessage().getnPfm());
            res.getInnerObjectEvent().getOrigins().get(0).setQuality(ARC.getEwMessage().getIngvQuality());
            res.getInnerObjectEvent().getOrigins().get(0).setRms(ARC.getEwMessage().getRms());
            if (ARC.getEwMessage().getReg()!=null)
                res.getInnerObjectEvent().getOrigins().get(0).setRegion(ARC.getEwMessage().getReg());
            else
                res.getInnerObjectEvent().getOrigins().get(0).setRegion("Unspecified region name");
            
            ObjectTypeOrigin to = new ObjectTypeOrigin();
            to.setName(TypeOriginName.HYPOCENTER);
            
            long hours = ChronoUnit.HOURS.between(res.getInnerObjectEvent().getOrigins().get(0).getOt() , OffsetDateTime.now());           
            if (hours > 24){
                to.setVersionName("bulletin preliminary");
            } else {
                to.setVersionName("ingv survey review");
            }
            res.getInnerObjectEvent().getOrigins().get(0).setTypeOrigin(to);
               
            // Magnitudes and amplitudes are ignored!! So we get the original ones.
            res.getInnerObjectEvent().getOrigins().get(0).setMagnitudes(EV.getInnerObjectEvent().getOrigins().get(0).getMagnitudes());
            
            // Phases reparse
            if (res.getInnerObjectEvent().getOrigins().get(0).getArrivals()!=null)
                res.getInnerObjectEvent().getOrigins().get(0).getArrivals().clear();
            String STAZ;
            if ((ARC.getEwMessage().getPhases()!=null) && (!ARC.getEwMessage().getPhases().isEmpty())){
                res.getInnerObjectEvent().getOrigins().get(0).setArrivals(new ArrayList<>());
                ArrayList<ObjectArrival> out_phases;
                
                res.setStations(new ArrayList());
                   
                Station s;
                int idStazione;
                int idStazioneInOldEvent;
                for (int i=0; i< ARC.getEwMessage().getPhases().size(); i++){
                    STAZ =  ARC.getEwMessage().getPhases().get(i).getSta(); 
//
                    //idStazione = res.StationCode_to_StationId(STAZ); 
                    //
                    out_phases = this.Parse_Hypo2000_Line(ARC.getEwMessage().getPhases().get(i));
                    int idPhaseinOldEvent=-1;
                    if ((out_phases!=null) && (!out_phases.isEmpty())){
                        for (ObjectArrival p : out_phases) {
                            if (p.getIscCode().toUpperCase().contains("P") || p.getIscCode().toUpperCase().contains("S")){
                                //------------------------------------------------------------------------
                                // Ripristina i valori di incertezza sulla letture altrimenti vanno persi
                                //------------------------------------------------------------------------
                                idStazioneInOldEvent = EV.StationCode_to_StationId(STAZ);
                                if (idStazioneInOldEvent>-1) {
                                    idPhaseinOldEvent = EV.getStation(idStazioneInOldEvent).FindPhaseID(p);
                                    if (idPhaseinOldEvent>-1){
                                        p.getPick().setUpperUncertainty(EV.getStation(idStazioneInOldEvent).getPhase(idPhaseinOldEvent).getPick().getUpperUncertainty());
                                        p.getPick().setLowerUncertainty(EV.getStation(idStazioneInOldEvent).getPhase(idPhaseinOldEvent).getPick().getLowerUncertainty());
                                    }
                                }
                                //------------------------------------------------------------------------
                                
                                p.setArrTimeIsUsed(true);

                                p.getPick().setLocalspace(pfxLocalSpace);
                                p.getPick().setProvenance(pfxProvenance);
                                
                                res.getInnerObjectEvent().getOrigins().get(0).getArrivals().add(p);
                                
                                idStazione = res.StationCode_to_StationId(STAZ); 

                                if (idStazione>-1) {
                                        //--------------------------------------
                                        // La stazione è gia nell'evento
                                        // Quindi aggiungo solo una nuova fase
                                        //--------------------------------------
                                        s = (Station)(res.getStations().get(idStazione));                                        
                                        s.addPhase(p);                                          
                                } else {
                                    //--------------------------------------
                                    // La stazione non è ancora nell'evento
                                    //--------------------------------------
                                    s = new Station(STAZ, App.G.SeismicNet.getStations());
                                    //idStazione=res.getStations().size();
                                    s.addPhase(p);   
                                    res.getStations().add(s);  
                                }  
                            }
                       }
                    }
                }    
            }  else {
                // No phases: rise an exception
                Logger.getLogger("org.ingv.pfx").log(Level.SEVERE, "Error in location output parse");
                return null;
            }
            
            return res;
        } catch (ApiException aex) {
            String msg = "...";
            if (aex.getResponseHeaders()!=null) msg = aex.getMessage();
            pfxDialog.ShowErrorMessage("ERROR: " + msg  + "\n" + aex.getResponseBody(), null);
            return null;
        } catch (Exception ex){
            return null;
        }   
    }    
//------------------------------------------------------------------------------        
    private ArrayList build_HYP2000_CONF() {
        ArrayList tmp = new ArrayList();
        tmp.add(this.Hypo2000_CONFIG.getBasic_item());
        tmp.add("LET " +  this.Hypo2000_CONFIG.getLET());
        tmp.add("H71 " +  this.Hypo2000_CONFIG.getH71());
        tmp.add("STA ’./all_stations.hinv’");
        tmp.add("CRH 1 ’./italy.crh’");
        tmp.add("MAG " +  this.Hypo2000_CONFIG.getMAG());
        tmp.add("DUR " +  this.Hypo2000_CONFIG.getDUR());
        tmp.add("FC1 " +  this.Hypo2000_CONFIG.getFC1());
        tmp.add("PRE " +  this.Hypo2000_CONFIG.getPRE());
        tmp.add("RMS " +  this.Hypo2000_CONFIG.getRMS());
        tmp.add("ERR " +  this.Hypo2000_CONFIG.getERR());
        tmp.add("POS " +  this.Hypo2000_CONFIG.getPOS());
        tmp.add("REP " +  this.Hypo2000_CONFIG.getREP());
        tmp.add("JUN " +  this.Hypo2000_CONFIG.getJUN());
        tmp.add("MIN " +  this.Hypo2000_CONFIG.getMIN());
        tmp.add("NET " +  this.Hypo2000_CONFIG.getNET());
        tmp.add("ZTR " +  this.Hypo2000_CONFIG.getZTR());
        tmp.add("DIS " +  this.Hypo2000_CONFIG.getDIS());
        tmp.add("DAM " +  this.Hypo2000_CONFIG.getDAM());
        tmp.add("WET " +  this.Hypo2000_CONFIG.getWET());
        tmp.add("ERF " +  this.Hypo2000_CONFIG.getERF());
        tmp.add("TOP " +  this.Hypo2000_CONFIG.getTOP());
        tmp.add("LST " +  this.Hypo2000_CONFIG.getLST());
        tmp.add("KPR " +  this.Hypo2000_CONFIG.getKPR());
        tmp.add("COP " +  this.Hypo2000_CONFIG.getCOP());
        tmp.add( "CAR " +  this.Hypo2000_CONFIG.getCAR());
        tmp.add("PRT  ’../output/hypo.prt’");
        tmp.add("SUM ’../output/hypo.sum’");
        tmp.add("ARC ’../output/hypo.arc’");
        tmp.add("APP " +  this.Hypo2000_CONFIG.getAPP());
        tmp.add("CON " +  this.Hypo2000_CONFIG.getCON());
        tmp.add( "PHS ’./input.arc’");
        tmp.add("LOC");

        return tmp;
    }
//------------------------------------------------------------------------------    
    private ArrayList<String> build_MODEL() {
        ArrayList<String> tmp = new ArrayList();
        
        DecimalFormat df2 = new DecimalFormat( "##0.00" );
        
        tmp.add(Hypo2000_CONFIG.getMODEL_Name());
        
        tmp.add( df2.format(this.Hypo2000_CONFIG.getMODEL_V0()).replace(",", ".") + " " + df2.format(this.Hypo2000_CONFIG.getMODEL_H0()).replace(",", "."));
        tmp.add( df2.format(this.Hypo2000_CONFIG.getMODEL_V1()).replace(",", ".") + " " + df2.format(this.Hypo2000_CONFIG.getMODEL_H1()).replace(",", "."));
        tmp.add(df2.format(this.Hypo2000_CONFIG.getMODEL_V2()).replace(",", ".") + " " + df2.format(this.Hypo2000_CONFIG.getMODEL_H2()).replace(",", "."));
  
        return tmp;
    }
//------------------------------------------------------------------------------
    private  List<ObjectHyp2000Phase> build_PHASES(Event myEvent){
        try {
            if (myEvent == null) return null;
            if (myEvent.getStations() == null) return null;
            if (myEvent.getStations().isEmpty()) return null;
            
            List<ObjectHyp2000Phase> res = new ArrayList<>();
            ObjectHyp2000Phase P;
            //float ampiezza;
            for (int i=0; i< myEvent.getStations().size(); i++){
                for (int idP=0; idP < myEvent.getStations().get(i).getNPhases(); idP++){
                    if ((myEvent.getStations().get(i).getPhase(idP).getArrTimeIsUsed()==null) ||(myEvent.getStations().get(i).getPhase(idP).getArrTimeIsUsed())){
                        P = new ObjectHyp2000Phase();
                        P.setAmpType(ObjectHyp2000Phase.AmpTypeEnum.NUMBER_1); // Wood-Anderson
                        P.setArrivalTime(myEvent.getStations().get(i).getPhase(idP).getPick().getArrivalTime());
                        P.setCha(myEvent.getStations().get(i).getPhase(idP).getPick().getCha());
                        if (myEvent.getStations().get(i).getPhase(idP).getPick().getEmersio()!=null) {
                            switch (myEvent.getStations().get(i).getPhase(idP).getPick().getEmersio()) {
                                case E:
                                    P.setEmersio(org.ingv.apollo.model.PickEmersio.E);
                                    break;
                                case I:
                                    P.setEmersio(org.ingv.apollo.model.PickEmersio.I);
                                    break;
                                default:
                                    //P.setEmersio(org.ingv.apollo.model.PickEmersio.NULL);
                                    P.setEmersio(null);
                                    break;
                            }
                        } else P.setEmersio(null);
                        if (myEvent.getStations().get(i).getPhase(idP).getPick().getFirstmotion()!=null){
                            switch (myEvent.getStations().get(i).getPhase(idP).getPick().getFirstmotion()) {
                                case U:
                                    P.setFirstmotion(org.ingv.apollo.model.PickFirstmotion.U);
                                    break;
                                case D:
                                    P.setFirstmotion(org.ingv.apollo.model.PickFirstmotion.D);
                                    break;
                                default:
                                    //P.setFirstmotion(org.ingv.apollo.model.PickFirstmotion.NULL);
                                    P.setFirstmotion(null);
                                    break;
                            }
                        } else P.setFirstmotion(null);

                        P.setIscCode(myEvent.getStations().get(i).getPhase(idP).getIscCode());
                        P.setLoc(myEvent.getStations().get(i).getPhase(idP).getPick().getLoc());
                        P.setNet(myEvent.getStations().get(i).getPhase(idP).getPick().getNet());
                        P.setSta(myEvent.getStations().get(i).getPhase(idP).getPick().getSta());
                        if (myEvent.getStations().get(i).getPhase(idP).getPick().getQualityClass()!=null) {                  
                            switch (myEvent.getStations().get(i).getPhase(idP).getPick().getQualityClass()){
                                case 0:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_0);
                                    break;
                                case 1:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_1);
                                    break;
                                case 2:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_2);
                                    break;
                                case 3:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_3);
                                    break;
                                case 4:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_4);
                                    break;
                                case 5:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_5);
                                    break;
                                case 6:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_6);
                                    break;
                                case 7:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_7);
                                    break;
                                case 8:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_8);
                                    break;

                                default:
                                    P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_9);
                                    break;
                            }
                        } else P.setWeight(ObjectHyp2000Phase.WeightEnum.NUMBER_0);

//                        ampiezza= myEvent.SearchAnAmplitude(P.getSta(), P.getCha());
//                        if (ampiezza>0.0) {
//                            P.setAmplitude(Double.valueOf(ampiezza));
//                        } else {
//                            P.setAmplitude(null);
//                        }

                        res.add(P);  
                    }       
                }
            }
            if (!res.isEmpty())
                return res;
            else return null;
        } catch (Exception ex){
            return null;
        }
    }
//------------------------------------------------------------------------------
    private ArrayList<ObjectArrival> Parse_Hypo2000_Line(Hyp2000arcPhasesInner phase_line){
        // "tmpP" è il Json Object che contiene le info sulla fase
        ObjectArrival outPh_P = null; 
        ObjectArrival outPh_S = null;
        ObjectArrival outPh_A = null;
        boolean isP, isS;
    
        try {             
            //
            String p_onset = phase_line.getPonset();
            String s_onset = phase_line.getSonset();
            String p_label = phase_line.getPlabel();
            String s_label = phase_line.getSlabel();
            
            //------------------------------------------------------------
            // ATTENZIONE: L'oggetto tmpP può contenere i dati di una P, 
            // di una S oppure di una P E di una S
            //------------------------------------------------------------
            if (p_onset.trim().length()>0) 
                isP = true; 
            else isP=false;
            
            if (s_onset.trim().length()>0) 
                isS = true; 
            else isS=false;
            //------------------------------------------------------------           
            if (isP) {
                outPh_P = new ObjectArrival();
                outPh_P.setPick(new ObjectPick());
                
                outPh_P.getPick().setCha(phase_line.getComp());
                outPh_P.getPick().setSta(phase_line.getSta());
                outPh_P.getPick().setNet(phase_line.getNet());
                outPh_P.getPick().setLoc(phase_line.getLoc());
                
                //----------
                // P PHASES
                //----------
                // Phase remark
                if (p_label.trim().length()>0) outPh_P.setIscCode(p_label); // Pg, Pn
                else outPh_P.setIscCode(p_onset); // simply P
                //
                // Arrival time
               // outPh_P.setArrivalTime_from_formatted_string(((JsonObject)tmpP).get("Pat").getAsString(), "yyyy-MM-dd HH:mm:ss.SSS");
                outPh_P.getPick().setArrivalTime(phase_line.getPat());
                // First motion  
                if ((phase_line.getPfm()!=null) && (!phase_line.getPfm().isBlank())){
                    if (phase_line.getPfm().toUpperCase().contains("U"))
                        outPh_P.getPick().setFirstmotion(PickFirstmotion.U);
                    else if (phase_line.getPfm().toUpperCase().contains("D"))
                        outPh_P.getPick().setFirstmotion(PickFirstmotion.D);
                    else
                        outPh_P.getPick().setFirstmotion(null);
                }

                // Weight post localization
                if (phase_line.getPwt()!=null) {
                    outPh_P.setWeight(phase_line.getPwt());
                }
                // Residual
                if (phase_line.getPres()!=null) {
                    outPh_P.setResidual(phase_line.getPres());
                }
                
                // Delta
                if (phase_line.getDist()!=null) {
                    outPh_P.setEpDistanceKm(phase_line.getDist().floatValue());
                }
                // Azmiut
                if (phase_line.getAzm()!=null) {
                    outPh_P.setAzimut(phase_line.getAzm().floatValue());
                } 
                // Quality class
                if (phase_line.getPqual()!=null) {
                    outPh_P.getPick().setQualityClass(phase_line.getPqual().intValue());
                } 
            }
            //} else if (isS) { 
            if (isS) { 
                outPh_S = new ObjectArrival();
                outPh_S.setPick(new ObjectPick());
                
                outPh_S.getPick().setCha(phase_line.getComp());
                outPh_S.getPick().setSta(phase_line.getSta());
                outPh_S.getPick().setNet(phase_line.getNet());
                outPh_S.getPick().setLoc(phase_line.getLoc());
                //----------
                // S PHASES
                //----------
                // Phase remark
                if (s_label.trim().length()>0) outPh_S.setIscCode(s_label); // Sg, Sn
                else outPh_S.setIscCode(s_onset); // simply S
                // Arrival time
                //OffsetDateTime o = OffsetDateTime.parse(((JsonObject)tmpP).get("Sat").getAsString());
                //outPh_S.setArrivalTime_from_formatted_string(((JsonObject)tmpP).get("Sat").getAsString(), "yyyy-MM-dd HH:mm:ss.SSS");
                outPh_S.getPick().setArrivalTime(phase_line.getSat());
                
                // First motion
                if ((phase_line.getSfm()!=null) && (!phase_line.getSfm().isBlank())){
                    if (phase_line.getSfm().toUpperCase().contains("U"))
                        outPh_S.getPick().setFirstmotion(PickFirstmotion.U);
                    else if (phase_line.getSfm().toUpperCase().contains("D"))
                        outPh_S.getPick().setFirstmotion(PickFirstmotion.D);
                    else
                        outPh_S.getPick().setFirstmotion(null);
                }
                // Weight post localization
                if (phase_line.getSwt()!=null) {
                    outPh_S.setWeight(phase_line.getSwt());
                }
                // Residual
                if (phase_line.getSres()!=null) {
                    outPh_S.setResidual(phase_line.getSres());
                }
                
                // Delta
                if (phase_line.getDist()!=null) {
                    outPh_S.setEpDistanceKm(phase_line.getDist().floatValue());
                }
                // Azmiut
                if (phase_line.getAzm()!=null) {
                    outPh_S.setAzimut(phase_line.getAzm().floatValue());
                } 
                // Quality class
                if (phase_line.getSqual()!=null) {
                    outPh_S.getPick().setQualityClass(phase_line.getSqual().intValue());
                } 
            }  //else outPh_P.setPhaseRemark("A");  
            //
//            if ((!isP) && (!isS)) {
//                // Phase is not a P neither an S, we (suppose)impose that it's an amplitude
//                outPh_A = new ObjectArrival();
////                 outPh_A.setLocalspace(pfxLocalSpace);
////                outPh_A.setProvenance(pfxProvenance);
//                outPh_A.setIscCode("A");
//                // Channel Code
//                outPh_A.setCha(((JsonObject)tmpP).get("comp").getAsString());
//                outPh_A.setSta(((JsonObject)tmpP).get("sta").getAsString());
//                outPh_A.setNet(((JsonObject)tmpP).get("net").getAsString());
//                outPh_A.setLoc(((JsonObject)tmpP).get("loc").getAsString());
//            }
            //
            ArrayList<ObjectArrival> result = new ArrayList<ObjectArrival>(); 
            if (outPh_P != null) result.add(outPh_P);
            if (outPh_S != null) result.add(outPh_S);
 //           if (outPh_A != null) result.add(outPh_A);
            //
            return result;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return null;
        }
        
    }

    /**
     * @return the Hypo2000_CONFIG
     */
    public Hypo2000_configuration getHypo2000_CONFIG() {
        return Hypo2000_CONFIG;
    }

    /**
     * @param Hypo2000_CONFIG the Hypo2000_CONFIG to set
     */
    public void setHypo2000_CONFIG(Hypo2000_configuration Hypo2000_CONFIG) {
        this.Hypo2000_CONFIG = Hypo2000_CONFIG;
    }


}
