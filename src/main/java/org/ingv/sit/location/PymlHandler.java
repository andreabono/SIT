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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.ingv.apollo.api.LocationApi;
import org.ingv.apollo.model.ObjectPyMLConf;
import org.ingv.apollo.model.ObjectPyMLConfEventMagnitude;
import org.ingv.apollo.model.ObjectPyMLConfPreconditions;
import org.ingv.apollo.model.ObjectPyMLConfStationMagnitude;
import org.ingv.apollo.model.V2PostPyML200Response;
import org.ingv.apollo.model.V2PostPyML200ResponseData;
import org.ingv.apollo.model.V2PostPyML200ResponseDataLog;
//import org.ingv.apollo.model.V2PostPyML200ResponseDataLogInner;
import org.ingv.apollo.model.V2PostPyML200ResponseDataMagnitudes;
import org.ingv.apollo.model.V2PostPyML200ResponseDataStationmagnitudesInner;
import org.ingv.apollo.model.V2PostPyMLRequest;
import org.ingv.apollo.model.V2PostPyMLRequestData;
import org.ingv.dante.model.ObjectAmplitude;
import org.ingv.dante.model.ObjectAmplitudeTypeAmplitude;
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectProvenance;
import org.ingv.dante.model.ObjectStationmagnitude;
import org.ingv.sit.App;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.utils.sitDialog;

public class PymlHandler {
    private String pymlURL;
    
    public PymlHandler(String in_service_url){
        pymlURL = in_service_url;
    } 
    
    public Event PostRequest(Event myEvent){
        try{
            if (myEvent.findMLid()==-1) return myEvent;
                      
            LocationApi locApi = new LocationApi();
            locApi.getApiClient().setBasePath(pymlURL);
            locApi.getApiClient().setReadTimeout(30000);
            locApi.getApiClient().setConnectTimeout(30000);
            locApi.getApiClient().setWriteTimeout(30000);
            
            V2PostPyMLRequest body = new V2PostPyMLRequest();
            V2PostPyMLRequestData data = new V2PostPyMLRequestData();
            
            
            //---------------------------
            // pyml Configuration 
            //---------------------------
            ObjectPyMLConf pymlConf = new ObjectPyMLConf();
            ObjectPyMLConfPreconditions preconditions = new ObjectPyMLConfPreconditions();
                preconditions.setDeltaCorner(Long.valueOf("5"));
                preconditions.setMaxLowcorner(Long.valueOf("15"));
                preconditions.setTheoreticalP(true);
                preconditions.setTheoreticalS(true);
            pymlConf.setPreconditions(preconditions);
            
            ObjectPyMLConfEventMagnitude eventMagnitude = new ObjectPyMLConfEventMagnitude();
                Float[] co = {(float)0.3, (float)1.0};
                eventMagnitude.setHmCutoff(co);
                eventMagnitude.setMaxdist((long)600);
                eventMagnitude.setMindist((long)10);
                eventMagnitude.setOutliersCutoff((float)0.1);
                eventMagnitude.setOutliersMaxIt((long)10);
                eventMagnitude.setOutliersNstd((long)1);
                eventMagnitude.setOutliersRedStop((float)0.05);
            
            pymlConf.setEventMagnitude(eventMagnitude);
            
            ObjectPyMLConfStationMagnitude stationMagnitude = new ObjectPyMLConfStationMagnitude();
                stationMagnitude.setDeltaPeaks((float)1);
                stationMagnitude.setUseStcorrDb(true);
                stationMagnitude.setUseStcorrHb(true);
                stationMagnitude.setWhenNoStcorrDb(true);
                stationMagnitude.setWhenNoStcorrHb(true);
            pymlConf.setStationMagnitude(stationMagnitude);
            
            data.setPymlConf(pymlConf);
            
            //---------------------------
            // pyml Origin
            //---------------------------
            
            org.ingv.apollo.model.ObjectOrigin origin = new org.ingv.apollo.model.ObjectOrigin();
            origin.setDepth(myEvent.getInnerObjectEvent().getOrigins().get(0).getDepth());
            origin.setLat(myEvent.getInnerObjectEvent().getOrigins().get(0).getLat());
            origin.setLon(myEvent.getInnerObjectEvent().getOrigins().get(0).getLon());
            
            data.setOrigin(origin);
                                  
            //---------------------------
            // pyml Amplitudes
            //---------------------------
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
            List<org.ingv.apollo.model.ObjectPyMLAmplitude> amplitudes = new ArrayList();
            int nAmps = myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().size();
            org.ingv.apollo.model.ObjectPyMLAmplitude tmpA;
            for (int i=0; i< nAmps; i++){
                if (((myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getIsUsed())==null) || 
                (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getIsUsed())){
                    tmpA=new org.ingv.apollo.model.ObjectPyMLAmplitude();
                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getAmp1()!=null)
                        tmpA.setAmp1(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getAmp1());
                    else
                        tmpA.setAmp1((double)0);

                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getAmp2()!=null)
                        tmpA.setAmp2(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getAmp2());
                    else
                        tmpA.setAmp2((double)0);
                    tmpA.setCha(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getCha());
                    tmpA.setSta(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getSta());
                    tmpA.setNet(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getNet());
                    tmpA.setLoc(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getLoc());

                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getTime1()!=null)
                        tmpA.setTime1(OffsetDateTime.parse(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getTime1().format(formatter)));
                    else
                        tmpA.setTime1(OffsetDateTime.parse(OffsetDateTime.now().format(formatter)));
                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getTime2()!=null)
                        tmpA.setTime2(OffsetDateTime.parse(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getStationmagnitudes().get(i).getAmplitude().getTime2().format(formatter))); 
                    else
                        tmpA.setTime2(OffsetDateTime.parse(OffsetDateTime.now().format(formatter)));

                    amplitudes.add(tmpA);
                    tmpA=null;               
                }           
            }
                           
            data.setAmplitudes(amplitudes);
            
            body.setData(data);
            
            App.logger.debug(body.toJson());
            System.out.println(body.toJson());
                       
            App.logger.debug("WS-LOG: LocationApi.v2PostPyML posting2");
            V2PostPyML200Response resp = locApi.v2PostPyML(body);
            App.logger.debug("WS-LOG: LocationApi.v2PostPyML received response");              
            V2PostPyML200ResponseData res = resp.getData();
            
            //------------------------------------------------------------------
            //List<V2PostPyML200ResponseDataLogInner> pyml_logs = res.getLog();
             V2PostPyML200ResponseDataLog pyml_log = res.getLog();
            // Analisi dei log....
            
            //------------------------------------------------------------------
     
            V2PostPyML200ResponseDataMagnitudes mags = res.getMagnitudes();
                                                        
            List<V2PostPyML200ResponseDataStationmagnitudesInner> sta_mags = res.getStationmagnitudes();
            ObjectMagnitude OriginalMagnitude = myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid());
            // Aggiorna le magnitudo e le amipezze sul myEvent di input
            if ((mags!=null) && (sta_mags!=null) && (!sta_mags.isEmpty())){
                myEvent.getInnerObjectEvent().getOrigins().get(0).setMagnitudes(null);
                myEvent.getInnerObjectEvent().getOrigins().get(0).setMagnitudes(new ArrayList<ObjectMagnitude>());
                
                ObjectProvenance mProv = new ObjectProvenance();
                mProv.setSoftwarename("pyml");
                
                ObjectProvenance mProv_staz;
                ObjectAmplitudeTypeAmplitude type_amp = new ObjectAmplitudeTypeAmplitude();
                type_amp.setUnit("mm");
                type_amp.setName("WoodAnderson");
                
                ObjectLocalspace ls= new ObjectLocalspace();              
                ls.setName("sit");
                int idStationMag=-1;
                if (mags.getDb()!=null){
                    ObjectMagnitude mDB = new ObjectMagnitude();
                    mDB.setMag(mags.getDb().getMl());
                    mDB.setLowerUncertainty(mags.getDb().getStd());
                    mDB.setUpperUncertainty(mags.getDb().getStd());
                    mDB.setNsta(mags.getDb().getTotsta());
                    mDB.setNstaUsed(mags.getDb().getUsedsta());
                    mDB.setNcha((long)sta_mags.size());
                    mDB.setNchaUsed(mDB.getNcha());
                    mDB.setTypeMagnitude("ML-DIBONA");
                    mDB.setProvenance(mProv);
                    mDB.setMagQuality(mags.getDb().getQuality());
                    
                    // Aggiungere le ampiezze come magnitudo di stazione
                    mDB.setStationmagnitudes(new ArrayList<ObjectStationmagnitude>());
                    for (int i=0; i< sta_mags.size(); i++){
                        ObjectStationmagnitude sm = new ObjectStationmagnitude();
                        sm.setAmplitude(new ObjectAmplitude());
                        sm.setTypeMagnitude("ML");
                        sm.getAmplitude().setTypeAmplitude(type_amp);
                                                                                  
                        sm.getAmplitude().setSta(sta_mags.get(i).getSta());
                        sm.getAmplitude().setCha(sta_mags.get(i).getCha());
                        sm.getAmplitude().setNet(sta_mags.get(i).getNet());
                        sm.getAmplitude().setLoc(sta_mags.get(i).getLoc());
                        
                        sm.getAmplitude().amp1(sta_mags.get(i).getAmp1());
                        sm.getAmplitude().amp2(sta_mags.get(i).getAmp2()); 
                        sm.getAmplitude().setTime1(sta_mags.get(i).getTime1());
                        sm.getAmplitude().setTime2(sta_mags.get(i).getTime2());
                        
                        sm.setEpDistanceKm(sta_mags.get(i).getEpDistanceKm());
                        sm.setOrigDistanceKm(sta_mags.get(i).getOrigDistance());
                        sm.setAzimut(sta_mags.get(i).getAzimuth());
                        
                        idStationMag = idStationMagnitudeBeforeLocation(OriginalMagnitude, sm);
                        if (idStationMag!=-1){
                            sm.getAmplitude().setIdLocalspace(OriginalMagnitude.getStationmagnitudes().get(idStationMag).getAmplitude().getIdLocalspace());
                            sm.getAmplitude().setLocalspace(OriginalMagnitude.getStationmagnitudes().get(idStationMag).getAmplitude().getLocalspace());
                            
                            sm.getAmplitude().setProvenance(OriginalMagnitude.getStationmagnitudes().get(idStationMag).getAmplitude().getProvenance());
                        } else {
                            sm.getAmplitude().setIdLocalspace(null);
                            sm.getAmplitude().setLocalspace(ls);
                            
                            mProv_staz = new ObjectProvenance();
                            mProv_staz.setSoftwarename("pyml");
                            sm.getAmplitude().setProvenance(mProv_staz);
                        }
                        // Aggiunge il Log di pyml alla fine della sigla della stazione
                        sm.getAmplitude().setSta(sta_mags.get(i).getSta() + "*" + process_pyml_log(pyml_log, sta_mags.get(i).getSta(), sta_mags.get(i).getCha()));
                                               
                        if ((sta_mags.get(i).getDb().getMl()!=null) && (App.G.IsNumeric(sta_mags.get(i).getDb().getMl().toString()))){
                            sm.setMag(sta_mags.get(i).getDb().getMl());
                            if ((App.G.IsNumeric(sta_mags.get(i).getDb().getW().toString())) && (sta_mags.get(i).getDb().getW()>=0.0))
                                sm.isUsed(true);
                            else
                                sm.isUsed(false);   
                            
                            mDB.getStationmagnitudes().add(sm);
                        }
//                        } else {
//                            //sm.setMag(-9.9);
//                            sm.setMag(null);
//                            sm.isUsed(false);
//                        }
                        
                        
                    }
                    
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().add(mDB);
                }
                if (mags.getHb()!=null){
                    ObjectMagnitude mHB = new ObjectMagnitude();
                    mHB.setMag(mags.getHb().getMl());
                    mHB.setLowerUncertainty(mags.getHb().getStd());
                    mHB.setUpperUncertainty(mags.getHb().getStd());
                    mHB.setNsta(mags.getHb().getTotsta());
                    mHB.setNstaUsed(mags.getHb().getUsedsta());
                    mHB.setNcha((long)sta_mags.size());
                    mHB.setNchaUsed(mHB.getNcha());
                    mHB.setTypeMagnitude("ML-HUTTONBOORE");
                    mHB.setProvenance(mProv);
                    mHB.setMagQuality(mags.getHb().getQuality());
                    
                    // Aggiungere le ampiezze come magnitudo di stazione
                    mHB.setStationmagnitudes(new ArrayList<ObjectStationmagnitude>());
                    for (int i=0; i< sta_mags.size(); i++){
                        ObjectStationmagnitude sm = new ObjectStationmagnitude();
                        sm.setAmplitude(new ObjectAmplitude());
                        sm.setTypeMagnitude("ML");
                        sm.getAmplitude().setTypeAmplitude(type_amp);
                                               
                        sm.getAmplitude().setSta(sta_mags.get(i).getSta());
                        
                        sm.getAmplitude().setCha(sta_mags.get(i).getCha());
                        sm.getAmplitude().setNet(sta_mags.get(i).getNet());
                        sm.getAmplitude().setLoc(sta_mags.get(i).getLoc());
                        sm.getAmplitude().amp1(sta_mags.get(i).getAmp1());
                        sm.getAmplitude().amp2(sta_mags.get(i).getAmp2()); 
                        sm.getAmplitude().setTime1(sta_mags.get(i).getTime1());
                        sm.getAmplitude().setTime2(sta_mags.get(i).getTime2());
                        
                        sm.setEpDistanceKm(sta_mags.get(i).getEpDistanceKm());
                        sm.setOrigDistanceKm(sta_mags.get(i).getOrigDistance());
                        
                        idStationMag = idStationMagnitudeBeforeLocation(OriginalMagnitude, sm);
                        if (idStationMag!=-1){
                            sm.getAmplitude().setIdLocalspace(OriginalMagnitude.getStationmagnitudes().get(idStationMag).getAmplitude().getIdLocalspace());
                            sm.getAmplitude().setLocalspace(OriginalMagnitude.getStationmagnitudes().get(idStationMag).getAmplitude().getLocalspace());
                            
                            sm.getAmplitude().setProvenance(OriginalMagnitude.getStationmagnitudes().get(idStationMag).getAmplitude().getProvenance());
                        } else {
                            sm.getAmplitude().setIdLocalspace(null);
                            sm.getAmplitude().setLocalspace(ls);
                            
                            mProv_staz = new ObjectProvenance();
                            mProv_staz.setSoftwarename("pyml");
                            sm.getAmplitude().setProvenance(mProv_staz);
                        }
                
                        sm.getAmplitude().setSta(sta_mags.get(i).getSta() + "*" + process_pyml_log(pyml_log, sta_mags.get(i).getSta(), sta_mags.get(i).getCha()));
                        
                        sm.getAmplitude().setCha(sta_mags.get(i).getCha());
                        sm.getAmplitude().setNet(sta_mags.get(i).getNet());
                        sm.getAmplitude().setLoc(sta_mags.get(i).getLoc());
                                                
                        sm.setEpDistanceKm(sta_mags.get(i).getEpDistanceKm());
                        sm.setOrigDistanceKm(sta_mags.get(i).getOrigDistance());
                        sm.setAzimut(sta_mags.get(i).getAzimuth());
                        
                        //if ((sta_mags.get(i).getHb().getMl()!=null) && (App.G.IsNumeric(String.valueOf(sta_mags.get(i).getHb().getMl())))){
                        if (sta_mags.get(i).getHb().getMl()!=null){
                            sm.setMag(sta_mags.get(i).getHb().getMl());
                            if ((App.G.IsNumeric(String.valueOf(sta_mags.get(i).getHb().getW()))) && (sta_mags.get(i).getHb().getW()>=0.0))
                                sm.isUsed(true);
                            else
                                sm.isUsed(false);  
                            
                            mHB.getStationmagnitudes().add(sm);
                        }
//                        } else {
//                            //sm.setMag(-9.9);
//                            sm.setMag(null);
//                            sm.isUsed(false);
//                        }
                                               
                        
                    }
                    
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().add(mHB);
                }
                               
                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size()>1){
                    // Ordina le Ml per evere la HB per prima
                    ObjectMagnitude appo = myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(0);
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().set(0, myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(1));
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().set(1, appo);
                }   
            } 
            
            locApi=null;
            return myEvent;
        } catch (Exception ex){
            sitDialog.ShowErrorMessage("Error while using PYML:\n" + ex.getMessage(), null);
            return null;
        }
    }
//------------------------------------------------------------------------------
    private String process_pyml_log(V2PostPyML200ResponseDataLog pyml_log, String sta, String cha){
        try {
            String res=null;
            if (pyml_log==null) return null;
            if ((pyml_log.getStationmagnitudes()==null)||(pyml_log.getStationmagnitudes().isEmpty())) return null;
            
            for (int i=0; i<pyml_log.getStationmagnitudes().size(); i++){
                if ((pyml_log.getStationmagnitudes().get(i).getSta().equalsIgnoreCase(sta)) &&
                        (pyml_log.getStationmagnitudes().get(i).getBandInst().equalsIgnoreCase(cha.substring(0,2))) )
                    return pyml_log.getStationmagnitudes().get(i).getStatus().toUpperCase() + ": " + 
                            pyml_log.getStationmagnitudes().get(i).getSummary() + "       ";
            }
//------------------------------------------------------------------------------            
            return res;
        } catch (Exception ex){
            return null;
        }
    }
        
    /**
     * @return the pymlURL
     */
    public String getPymlURL() {
        return pymlURL;
    }

    /**
     * @param pymlURL the pymlURL to set
     */
    public void setPymlURL(String pymlURL) {
        this.pymlURL = pymlURL;
    }
    
    
    private int idStationMagnitudeBeforeLocation(ObjectMagnitude OriginalMagnitude, ObjectStationmagnitude sm) {
        try {
            String sAmp1, sAmp2;
             DecimalFormat decForm = new DecimalFormat("#.###", new DecimalFormatSymbols());
             decForm.setRoundingMode(RoundingMode.CEILING);
            for (int i=0; i< OriginalMagnitude.getStationmagnitudes().size(); i++){
                sAmp1 = decForm.format(sm.getAmplitude().getAmp1());
                
                if ((sm.getAmplitude().getSta().equalsIgnoreCase(OriginalMagnitude.getStationmagnitudes().get(i).getAmplitude().getSta())) &&
                        (sm.getAmplitude().getCha().equalsIgnoreCase(OriginalMagnitude.getStationmagnitudes().get(i).getAmplitude().getCha())) &&
                        (sm.getAmplitude().getNet().equalsIgnoreCase(OriginalMagnitude.getStationmagnitudes().get(i).getAmplitude().getNet())) &&
                        (sm.getAmplitude().getLoc().equalsIgnoreCase(OriginalMagnitude.getStationmagnitudes().get(i).getAmplitude().getLoc())) &&
                        //(decForm.format(sm.getAmp1()).equals(decForm.format(OriginalMagnitude.getStationmagnitudes().get(i).getAmp1()))) && 
                        //(decForm.format(sm.getAmp2()).equals(decForm.format(OriginalMagnitude.getStationmagnitudes().get(i).getAmp2())))  &&
                        (sm.getAmplitude().getTime1().equals(OriginalMagnitude.getStationmagnitudes().get(i).getAmplitude().getTime1())) && 
                        (sm.getAmplitude().getTime2().equals(OriginalMagnitude.getStationmagnitudes().get(i).getAmplitude().getTime2()))) {
                    return i;
                }
            }
            
            return -1;
        } catch (Exception ex){
            return -1;
        } 
    }
    
}
