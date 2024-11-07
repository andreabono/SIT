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


package org.ingv.sit.quakeml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import org.quakeml.xmlns.quakeml._1.Quakeml;
import java.io.File;
import java.math.BigInteger;
import java.time.Instant;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.Marshaller;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.XMLGregorianCalendar;

import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.sit.utils.sitDialog;
import org.quakeml.xmlns.bed._1.Amplitude;
import org.quakeml.xmlns.bed._1.Arrival;
import org.quakeml.xmlns.bed._1.CreationInfo;
import org.quakeml.xmlns.bed._1.EvaluationMode;
import org.quakeml.xmlns.bed._1.EvaluationStatus;
import org.quakeml.xmlns.bed._1.EventDescription;
import org.quakeml.xmlns.bed._1.EventDescriptionType;
import org.quakeml.xmlns.bed._1.EventParameters;
import org.quakeml.xmlns.bed._1.EventType;
import org.quakeml.xmlns.bed._1.Magnitude;
import org.quakeml.xmlns.bed._1.OriginDepthType;
import org.quakeml.xmlns.bed._1.OriginQuality;
import org.quakeml.xmlns.bed._1.OriginType;
import org.quakeml.xmlns.bed._1.OriginUncertainty;
import org.quakeml.xmlns.bed._1.OriginUncertaintyDescription;
import org.quakeml.xmlns.bed._1.Phase;
import org.quakeml.xmlns.bed._1.Pick;
import org.quakeml.xmlns.bed._1.PickOnset;
import org.quakeml.xmlns.bed._1.PickPolarity;
import org.quakeml.xmlns.bed._1.RealQuantity;
import org.quakeml.xmlns.bed._1.TimeQuantity;
import org.quakeml.xmlns.bed._1.WaveformStreamID;

public class QuakeMLWriter {
//    
    private org.ingv.sit.datamodel.Event source_event;
////--------------------------------------------------------------------------------     
//    /**
//     * Saves the current  data to the specified file.
//     * 
//     * @param file
//     */
    public void saveEventDataToQMLFile_1_2(File file) {
        try {
            org.quakeml.xmlns.quakeml._1.ObjectFactory ROOT_OF = new  org.quakeml.xmlns.quakeml._1.ObjectFactory();
            org.quakeml.xmlns.bed._1.ObjectFactory BED_OF = new org.quakeml.xmlns.bed._1.ObjectFactory();
//
            // Create the quakeml root object
            Quakeml qmlbody = ROOT_OF.createQuakeml();
//
            // Create the EVENTPARAMETERS:         
            EventParameters event_parameters = BED_OF.createEventParameters(); 
            event_parameters.setPublicID("smi:www.ingv.it/event_parameters/event-parameters-public-id");
            
            // EventParameters >> EVENT
            org.quakeml.xmlns.bed._1.Event evento =  BED_OF.createEvent();
            //--------------------------------------------------------------------
            // EVENT
            //--------------------------------------------------------------------
                //--------------------------------------------------------
                // EventID
                //--------------------------------------------------------
                evento.setPublicID("smi:www.ingv.it/events/eventId=" + String.valueOf(source_event.getInnerObjectEvent().getId()));
                evento.getAny().add(EventType.EARTHQUAKE);
//
                //--------------------------------------------------------
                // Event description
                //--------------------------------------------------------
                EventDescription ed = BED_OF.createEventDescription();          
                ed.getTextOrType().add(EventDescriptionType.REGION_NAME);            
                String area = "*Unknown area*";
                if (source_event.getInnerObjectEvent().getOrigins().get(0).getRegion() != null) area = source_event.getInnerObjectEvent().getOrigins().get(0).getRegion();
                ed.getTextOrType().add(area);
                JAXBElement<EventDescription> event_description = BED_OF.createEventDescription(ed);
                
                evento.getAny().add(event_description);
//            
                //--------------------------------------------------------
//              // Preferred Magnitude ID
//              //--------------------------------------------------------  
                if (source_event.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()!=null){ 
                    // This must be reviewed:
                    JAXBElement<String> pref_mag_id = BED_OF.createEventPreferredMagnitudeID("smi:www.ingv.it/magnitudes/Preferred_magnitude_id_must_generate");    
                    evento.getAny().add(pref_mag_id);
                }
//
                //--------------------------------------------------------
                // Preferred Origin ID
                //--------------------------------------------------------
                String origin_id = "smi:www.ingv.it/originId=" + this.source_event.getInnerObjectEvent().getOrigins().get(0).getId();
                JAXBElement<String> pref_origin_id = BED_OF.createEventPreferredOriginID(origin_id);    
                evento.getAny().add(pref_origin_id);
                               
                //--------------------------------------------------------
                // Event Creation Info
                //--------------------------------------------------------   
                CreationInfo ci =  BED_OF.createCreationInfo(); 
                JAXBElement<String> agency_id = BED_OF.createCreationInfoAgencyID("INGV");
                String prov = "*Unknown software*";
                if (this.source_event.getInnerObjectEvent().getOrigins().get(0).getProvenance().getSoftwarename()!=null) prov = this.source_event.getInnerObjectEvent().getOrigins().get(0).getProvenance().getSoftwarename();
                JAXBElement<String> author = BED_OF.createCreationInfoAuthor(prov);       
        //     
                String dateTimeString = Instant.now().toString();
                XMLGregorianCalendar ora
                    = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
        //       
                JAXBElement<XMLGregorianCalendar> creationTime = BED_OF.createCreationInfoCreationTime(ora);
        //          
                JAXBElement<CreationInfo> creation_info = BED_OF.createEventCreationInfo(ci);
                ci.getAny().add(agency_id);
                ci.getAny().add(author);
                ci.getAny().add(creationTime);
        //        
                evento.getAny().add(creation_info);
                
                //--------------------------------------------------------
                // EVENT ORIGIN
                //--------------------------------------------------------
                org.quakeml.xmlns.bed._1.Origin origin = BED_OF.createOrigin(); //ORIGIN IS CREATED HERE!!   
                origin.setPublicID(origin_id);
                
                    // Origin >> EvaluationMode
                    JAXBElement<EvaluationMode> evaluation_mode = BED_OF.createOriginEvaluationMode(EvaluationMode.MANUAL);
                    origin.getAny().add(evaluation_mode);
                    // Origin >> Type
                    JAXBElement<OriginType> origin_type = BED_OF.createOriginType(OriginType.HYPOCENTER);
                    origin.getAny().add(origin_type);
                    // Origin >> Time    
                    TimeQuantity tq = BED_OF.createTimeQuantity();
                    XMLGregorianCalendar event_origin_time  = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
//          
                    event_origin_time.setYear(source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getYear());
                    event_origin_time.setMonth(source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getMonthValue());
                    event_origin_time.setDay(source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getDayOfMonth());                
                    event_origin_time.setTime(source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getHour(), 
                        source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getMinute(), 
                        source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getSecond(), 
                        (int)(source_event.getInnerObjectEvent().getOrigins().get(0).getOt().getNano()/1000000)); 
                    JAXBElement<XMLGregorianCalendar> tqv =  BED_OF.createTimeQuantityValue(event_origin_time);
//
                    tq.getAny().add(tqv);    
                    JAXBElement<TimeQuantity> origin_time = BED_OF.createOriginTime(tq);
                    origin.getAny().add(origin_time);
//
                    // Origin >> Latitude
                    RealQuantity lat_value =  BED_OF.createRealQuantity();
                    JAXBElement<Double> rqv = BED_OF.createRealQuantityValue(source_event.getInnerObjectEvent().getOrigins().get(0).getLat());
                    lat_value.getAny().add(rqv);
//
                    JAXBElement<RealQuantity> lat = BED_OF.createOriginLatitude(lat_value);
                    lat.setValue(lat_value);
                    origin.getAny().add(lat);
//
                    // Origin >> Longitude
                    RealQuantity lon_value =  BED_OF.createRealQuantity();
                    rqv = BED_OF.createRealQuantityValue(source_event.getInnerObjectEvent().getOrigins().get(0).getLon());
                    lon_value.getAny().add(rqv);
                    JAXBElement<RealQuantity> lon = BED_OF.createOriginLongitude(lon_value);
                    lon.setValue(lon_value);
                    origin.getAny().add(lon);
//
                    // Origin >> Depth
                    RealQuantity dep_value =  BED_OF.createRealQuantity();
                    rqv = BED_OF.createRealQuantityValue(source_event.getInnerObjectEvent().getOrigins().get(0).getDepth());
                    dep_value.getAny().add(rqv);
                    JAXBElement<RealQuantity> dep = BED_OF.createOriginDepth(dep_value);
                    dep.setValue(dep_value);
                    origin.getAny().add(dep);
//
                    // Origin >> Depth type       
                    JAXBElement<OriginDepthType> dep_type = BED_OF.createOriginDepthType(OriginDepthType.FROM_LOCATION);
                    origin.getAny().add(dep_type);
//
                    // Origin >> Origin Uncertainty
                    OriginUncertainty origin_uncert_value = BED_OF.createOriginUncertainty();
//
                    // >> preferredDescription
                    JAXBElement<OriginUncertaintyDescription> origin_uncert_desc = BED_OF.createOriginUncertaintyPreferredDescription(OriginUncertaintyDescription.CONFIDENCE_ELLIPSOID);
                    origin_uncert_value.getAny().add(origin_uncert_desc);
//
                    // << horizontalUncertainty        
                    JAXBElement<Double> origin_hor_uncert = BED_OF.createOriginUncertaintyHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    origin_uncert_value.getAny().add(origin_hor_uncert);
//
                    // << minHorizontalUncertainty        
                    JAXBElement<Double> origin_MIN_hor_uncert =  BED_OF.createOriginUncertaintyMinHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    origin_uncert_value.getAny().add(origin_MIN_hor_uncert);
//
                    // << maxHorizontalUncertainty        
                    JAXBElement<Double> origin_MAX_hor_uncert =  BED_OF.createOriginUncertaintyMaxHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    origin_uncert_value.getAny().add(origin_MAX_hor_uncert);
//
                    // << azimutMaxHorizontalUncertainty        
                    JAXBElement<Double> origin_azimuthMax_hor_uncert =  BED_OF.createOriginUncertaintyMinHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    origin_uncert_value.getAny().add(origin_azimuthMax_hor_uncert);
//
                    // << confidenceLevel   
                    JAXBElement<Double> origin_uncert_confidence_level = BED_OF.createOriginUncertaintyConfidenceLevel(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    origin_uncert_value.getAny().add(origin_uncert_confidence_level);
//
                    JAXBElement<OriginUncertainty> origin_uncert = BED_OF.createOriginOriginUncertainty(origin_uncert_value);
                    origin.getAny().add(origin_uncert);    
//           
                    // Origin >> Origin Quality       
                    OriginQuality origin_quality_value = BED_OF.createOriginQuality(); 
                    //origin_quality_value.getAny().add(BED_OF.createOriginQualityAzimuthalGap(source_event.getInnerObjectEvent().getHypocenter().getAzimut_gap())); // 20210927
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityAssociatedPhaseCount(BigInteger.valueOf(source_event.getInnerObjectEvent().getOrigins().get(0).getNphTot()))); 
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityUsedPhaseCount(BigInteger.valueOf(0)));    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityStandardError(source_event.getInnerObjectEvent().getOrigins().get(0).getRms()));
                    Double min_dist =0.0;
                    Double max_dist = 0.0;
                    if (source_event.getInnerObjectEvent().getOrigins().get(0).getMinDistance()!=null) min_dist = source_event.getInnerObjectEvent().getOrigins().get(0).getMinDistance();
                    if (source_event.getInnerObjectEvent().getOrigins().get(0).getMaxDistance()!=null) max_dist = source_event.getInnerObjectEvent().getOrigins().get(0).getMaxDistance();
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityMinimumDistance(min_dist)); 
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityMaximumDistance(max_dist)); 
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityAssociatedStationCount(BigInteger.valueOf(source_event.getNStations()))); 
                    origin_quality_value.getAny().add(BED_OF.createOriginQualityUsedStationCount(BigInteger.valueOf(source_event.getNStations()))); 
//
                    JAXBElement<OriginQuality> origin_quality = BED_OF.createOriginQuality(origin_quality_value);
                    origin.getAny().add(origin_quality); 

                    // Origin >> evaluationStatus 
                    JAXBElement<EvaluationStatus> origin_eval_stat = BED_OF.createOriginEvaluationStatus(EvaluationStatus.REVIEWED);
                    origin.getAny().add(origin_eval_stat); 
//
                    // Origin >> ARRIVALS[]
                    // LOOP OVER EVENT ARRIVALS
                    JAXBElement<Arrival> arrivo = null;
                    JAXBElement<Phase> ph;
                    JAXBElement<Double> az, dist, res, wgt;
                    JAXBElement<String> pid, earth_model_id;
                    JAXBElement<RealQuantity> takeoff;
                    Arrival arrival; 
                    Phase phName=BED_OF.createPhase();
                    int staID, phID;
                    if (source_event.getNStations() > 0) {
                        for (staID=0; staID<source_event.getNStations(); staID++){
                            if (source_event.getStation(staID).getNPhases() > 0) {
                                for (phID=0; phID < source_event.getStation(staID).getNPhases(); phID++){
                                    arrival= BED_OF.createArrival();
                                    arrival.setPublicID("smi:www.ingv.it/arrivals/arrival_publicId_" + String.valueOf(staID) + "_" + String.valueOf(phID));
                                    // Phase name
                                    phName.setValue(source_event.getStation(staID).getPhase(phID).getIscCode());
                                    ph =  BED_OF.createArrivalPhase(phName);   
                                    arrival.getAny().add(ph);
                                    // Azimuth   
                                    if (source_event.getStation(staID).getPhase(phID).getAzimut()!=null){
                                        az = BED_OF.createArrivalAzimuth(Double.valueOf(source_event.getStation(staID).getPhase(phID).getAzimut()));
                                        arrival.getAny().add(az);
                                    }
                                    // Distance
                                    dist = BED_OF.createArrivalDistance(this.round(source_event.getStation(staID).getPhase(phID).getEpDistanceKm(),2));
                                    arrival.getAny().add(dist);
                                    // Time residual
                                    res = BED_OF.createArrivalTimeResidual(this.round(source_event.getStation(staID).getPhase(phID).getResidual(), 2));       
                                    arrival.getAny().add(res);
                                    // Time Weight
                                    wgt = BED_OF.createArrivalTimeWeight(this.round(source_event.getStation(staID).getPhase(phID).getWeight(), 2));
                                    arrival.getAny().add(wgt);
                                    //PickID
                                    pid = BED_OF.createArrivalPickID("smi:www.ingv.it/picks/pick_publicId_" + String.valueOf(staID) + "_" + String.valueOf(phID));
                                    arrival.getAny().add(pid);
                                    //EarthModelID
                                    earth_model_id = BED_OF.createArrivalEarthModelID("smi:www.ingv.it/earth_models/earth_model_id_have_to_generate");
                                    arrival.getAny().add(earth_model_id);
                                    // Takeoff Angle
                                    RealQuantity rq2 = BED_OF.createRealQuantity();
                                    rq2.getAny().add(BED_OF.createRealQuantityValue(90.0)); 
                                    takeoff = BED_OF.createArrivalTakeoffAngle(rq2);
                                    arrival.getAny().add(takeoff);

                                    arrival.getAny().add(creation_info);

                                    arrivo = BED_OF.createOriginArrival(arrival);
                                    origin.getAny().add(arrivo);
                                }
                            } // else station staID has no phases
                        }
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Could not save data");
                        alert.setContentText("Current event has no station data.");

                        alert.showAndWait();    
                    }
                                 
    //       
            // Put the ORIGIN into the EVENT
            JAXBElement<org.quakeml.xmlns.bed._1.Origin> event_origin = BED_OF.createEventOrigin(origin);
            evento.getAny().add(event_origin);

    //      ----------------------------------------------------------------------
    //      END OF ORIGIN    
    //      ----------------------------------------------------------------------
    
    //      ------------------------ 
    //      Magnitude
    //      ------------------------ 
            if (!this.source_event.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty()) {
                
                JAXBElement<Magnitude> mag_element = null;
                for (int idMag=0; idMag < this.source_event.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); idMag++){
                    mag_element = write_magnitude(origin_id, this.source_event.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag));
                    if (mag_element!=null) evento.getAny().add(mag_element);
                }
            }
            
    //      ----------------------------------- 
    //      Amplitudes (if an Ml is available)
    //      ----------------------------------- 
            int idML = this.source_event.findMLid();
            if (idML >-1) {
                int nAmpls=0;
                JAXBElement<Amplitude> amp_element;
                ObjectMagnitude refML = this.source_event.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idML);
                
                // 20210927 SISTEMARE LE AMPIEZZE
                // refML.getAmplitudes()
//                if (refML.getAmplitudes()!= null) {
//                    nAmpls = refML.getAmplitudes().size();
//                }
//                for (int idAmpl=0; idAmpl < nAmpls; idAmpl++) {
//                    amp_element = this.write_amplitude(refML.getAmplitudes().get(idAmpl));
//                    if (amp_element!=null) evento.getAny().add(amp_element);
//                }
            }
             
//
    //      ------------------------        
    //      Picks list
    //      ------------------------   
            Pick pick ;    
            JAXBElement<Pick> pick_element;
            JAXBElement<Double> tqu=null; 
            
            CreationInfo pick_CI_obj ; 
            
            XMLGregorianCalendar pick_t  = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
            if (source_event.getNStations() > 0) {
                for (staID=0; staID<source_event.getNStations(); staID++){
                    if (source_event.getStation(staID).getNPhases() > 0) {
                        for (phID=0; phID < source_event.getStation(staID).getNPhases(); phID++){
                            pick = BED_OF.createPick();      
                            pick.setPublicID("smi:www.ingv.it/picks/pick_publicId_" + String.valueOf(staID) + "_" + String.valueOf(phID));
//
                            // Pick >> time (and uncertainty)
                            tq = BED_OF.createTimeQuantity();
//                            
                            pick_t.setYear(source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getYear());
                            pick_t.setMonth(source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getMonthValue());
                            pick_t.setDay(source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getDayOfMonth());                       
                            pick_t.setTime(source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getHour(), 
                                source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getMinute(), 
                                source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getSecond(), 
                                (int)(source_event.getStation(staID).getPhase(phID).getPick().getArrivalTime().getNano()/1000000));            
                            tqv =  BED_OF.createTimeQuantityValue(pick_t);     
                            if (source_event.getStation(staID).getPhase(phID).getPick().getUpperUncertainty()!=null)
                                tqu = BED_OF.createTimeQuantityUncertainty(Double.valueOf(source_event.getStation(staID).getPhase(phID).getPick().getUpperUncertainty()));     // 20211004: must be fixed!!!!!!                       
                            tq.getAny().add(tqv); 
                            tq.getAny().add(tqu);
//
                            JAXBElement<TimeQuantity> pick_time = BED_OF.createPickTime(tq);
//
                            pick_time.setValue(tq);
                            pick.getAny().add(pick_time);
                            //
                            // Pick >> method id
                            JAXBElement<String> method_id = BED_OF.createPickMethodID("smi:www.ingv.it/PickFX_manual_methodId");
                            pick.getAny().add(method_id);
//
                            // Pick >> onset
                            if (source_event.getStation(staID).getPhase(phID).getPick().getEmersio()!=null) {
                                JAXBElement<PickOnset> po;
                                if (source_event.getStation(staID).getPhase(phID).getPick().getEmersio().getValue().equalsIgnoreCase("E")) {
                                    po = BED_OF.createPickOnset(PickOnset.EMERGENT);
                                } else if (source_event.getStation(staID).getPhase(phID).getPick().getEmersio().getValue().equalsIgnoreCase("I")) {
                                    po = BED_OF.createPickOnset(PickOnset.IMPULSIVE);
                                } else po = BED_OF.createPickOnset(PickOnset.QUESTIONABLE);
                                pick.getAny().add(po);
                            }
                            // Pick >> phase hint
                            Phase fase = BED_OF.createPhase();
                            fase.setValue(source_event.getStation(staID).getPhase(phID).getIscCode());
                            JAXBElement<Phase> jph = BED_OF.createPickPhaseHint(fase);
                            pick.getAny().add(jph);
//
                            // Pick >> polarity
                            if (source_event.getStation(staID).getPhase(phID).getPick().getFirstmotion()!=null) {
                                JAXBElement<PickPolarity> pp;
                                if ((source_event.getStation(staID).getPhase(phID).getPick().getFirstmotion().getValue().equalsIgnoreCase("D")) || (source_event.getStation(staID).getPhase(phID).getPick().getFirstmotion().getValue().equalsIgnoreCase("-"))) {
                                    pp = BED_OF.createPickPolarity(PickPolarity.NEGATIVE);
                                } else if ((source_event.getStation(staID).getPhase(phID).getPick().getFirstmotion().getValue().equalsIgnoreCase("U")) || (source_event.getStation(staID).getPhase(phID).getPick().getFirstmotion().getValue().equalsIgnoreCase("+"))) {
                                    pp = BED_OF.createPickPolarity(PickPolarity.POSITIVE);                 
                                } else pp = BED_OF.createPickPolarity(PickPolarity.UNDECIDABLE);
                                pick.getAny().add(pp);
                            }
//                            // Pick >> evaluation mode
//                            // .........
//
//                            // Pick >> creation info
//                            
                            pick_CI_obj = BED_OF.createCreationInfo();
                            dateTimeString = Instant.now().toString();
                            ora = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
                            JAXBElement<XMLGregorianCalendar> pick_obj_CI_CreationTime = BED_OF.createCreationInfoCreationTime(ora);                           
                            pick_CI_obj.getAny().add(pick_obj_CI_CreationTime);
                            JAXBElement<CreationInfo> pick_CI = BED_OF.createPickCreationInfo(pick_CI_obj);
                            pick.getAny().add(pick_CI);
//                            
//                          // Pick >> Waveform ID
                            WaveformStreamID wsid = BED_OF.createWaveformStreamID();
                            wsid.setChannelCode(source_event.getStation(staID).getPhase(phID).getPick().getCha());
                            wsid.setLocationCode(source_event.getStation(staID).getLocation(source_event.getStation(staID).getPhase(phID).getPick().getCha()));
                            wsid.setNetworkCode(source_event.getStation(staID).getNetwork());
                            wsid.setStationCode(source_event.getStation(staID).getCode());
                     
                            JAXBElement<WaveformStreamID> wid = BED_OF.createPickWaveformID(wsid);
                            
                            pick.getAny().add(wid);
//
                            pick_element = BED_OF.createEventPick(pick);
                            evento.getAny().add(pick_element);
                        }
                    } // else station(staID) has no phases
                }
            }
//           
            //--------------------------------------------------------------------
            // Put the EVENT into the EVENT PARAMETERS...
            //--------------------------------------------------------------------
            event_parameters.getAny().add(evento);  
            // ... and the EVENT PARAMETERS int the QUAKEML main body
            qmlbody.setEventParameters(event_parameters);
            JAXBElement<Quakeml> qml_root = ROOT_OF.createQuakeml(qmlbody);
            //-----------------------------------------
            // FINAL MARSHALING
            //-----------------------------------------
            JAXBContext context = JAXBContext.newInstance(org.quakeml.xmlns.quakeml._1.Quakeml.class); 
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);          
            // Run the marshaler
            m.marshal(qml_root, file);
            sitDialog.ShowInformationMessage("Your file is ready!!",null);
            //
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            
            ex.printStackTrace();

            alert.showAndWait();
        }       
    }
////--------------------------------------------------------------------------------
    private JAXBElement<Magnitude> write_magnitude(String in_origin_id, ObjectMagnitude inMag){ 
        try {
            org.quakeml.xmlns.bed._1.ObjectFactory BED_OF = new org.quakeml.xmlns.bed._1.ObjectFactory();
        
            // MAGNITUDE root object
            Magnitude m = BED_OF.createMagnitude();

            // publicID
            m.setPublicID("smi:www.ingv.it/magnitudes/mag_publicId_");
            // Station Count
            JAXBElement<BigInteger> station_count =  BED_OF.createMagnitudeStationCount(BigInteger.valueOf(inMag.getNstaUsed())); 
            m.getAny().add(station_count);
            // originID
            JAXBElement<String> origin_id = BED_OF.createMagnitudeOriginID(in_origin_id);
            m.getAny().add(origin_id);
            // type (mangnitude type)
            JAXBElement<String> mag_type = BED_OF.createMagnitudeType(inMag.getTypeMagnitude()); 
            m.getAny().add(mag_type);
            // mag
            RealQuantity mag_RQ = BED_OF.createRealQuantity();
            
            JAXBElement<Double> mag_RQ_value = BED_OF.createRealQuantityValue(this.round(inMag.getMag(), 1));
            mag_RQ.getAny().add(mag_RQ_value);
            JAXBElement<Double> mag_RQ_uncertainty = BED_OF.createRealQuantityUncertainty(this.round(inMag.getUpperUncertainty(), 1));
            mag_RQ.getAny().add(mag_RQ_uncertainty);  
            JAXBElement<RealQuantity>mag_MAG = BED_OF.createMagnitudeMag(mag_RQ);
            m.getAny().add(mag_MAG);
            // methodID
            JAXBElement<String> method_id = BED_OF.createMagnitudeMethodID("smi:www.ingv.it/magnitudes/methodId/0123456789");
            m.getAny().add(method_id);
            // creationInfo
            CreationInfo mag_CI_obj = BED_OF.createCreationInfo();
            JAXBElement<String> mag_CI_obj_AgencyID = BED_OF.createCreationInfoAgencyID("INGV");
           // JAXBElement<String> mag_CI_obj_AgencyURI = BED_OF.createCreationInfoAgencyURI("smi:www.ingv.it");
            JAXBElement<String> mag_CI_obj_Author = BED_OF.createCreationInfoAuthor("INGV PickFX");
            String dateTimeString = Instant.now().toString();
            XMLGregorianCalendar ora = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
            JAXBElement<XMLGregorianCalendar> mag_CI_obj_CreationTime = BED_OF.createCreationInfoCreationTime(ora); 
            mag_CI_obj.getAny().add(mag_CI_obj_AgencyID);
           // mag_CI_obj.getAny().add(mag_CI_obj_AgencyURI);
            mag_CI_obj.getAny().add(mag_CI_obj_Author);
            mag_CI_obj.getAny().add(mag_CI_obj_CreationTime);
            JAXBElement<CreationInfo> mag_CI = BED_OF.createMagnitudeCreationInfo(mag_CI_obj);
            m.getAny().add(mag_CI);
            //
            // FINAL OUTPUT JAXB Element
            JAXBElement<Magnitude> res = BED_OF.createEventMagnitude(m);
            //
            return res;
        } catch (Exception ex) {
            return null;
        }
        
        
        
    }
//    
////--------------------------------------------------------------------------------
//    private java.time.LocalDateTime LocalDateTimeConversion(org.threeten.bp.LocalDateTime in_datetime){
//        java.time.LocalDateTime res=null;
//
//        res = java.time.LocalDateTime.of(in_datetime.getYear(), in_datetime.getMonthValue(), in_datetime.getDayOfMonth(), 
//                in_datetime.getHour(), in_datetime.getMinute(), in_datetime.getSecond(), in_datetime.getNano());
//        return res;
//        
//    }
//    
//    private JAXBElement<Amplitude> write_amplitude(io.swagger.client.model.ObjectStationmagnitude inAmp){
//        try{
//            org.quakeml.xmlns.bed._1.ObjectFactory BED_OF = new org.quakeml.xmlns.bed._1.ObjectFactory();
//
//            // AMPLITUDE root object
//            Amplitude a = BED_OF.createAmplitude();
//            a.setPublicID("smi:www.ingv.it/amplitudes/mag_publicId_");
//     
//            // generic amplitude
//            RealQuantity amp_GenericAmplitude = BED_OF.createRealQuantity();
//            JAXBElement<Double> amp_RQ_value = BED_OF.createRealQuantityValue(this.round(Math.abs((inAmp.getAmp1() - inAmp.getAmp2())/(2 * Math.pow(10.0, 3.0))),5));
//            amp_GenericAmplitude.getAny().add(amp_RQ_value);
//            JAXBElement<RealQuantity> generic_amplitude = BED_OF.createAmplitudeGenericAmplitude(amp_GenericAmplitude);
//            a.getAny().add(generic_amplitude);
//            
//            // period
//            Double period_value;
//            RealQuantity amp_Period= BED_OF.createRealQuantity();
//            
//            period_value = Math.abs(LocalDateTimeConversion(inAmp.getTime1().toLocalDateTime()).getLong( ChronoField.MILLI_OF_DAY) - LocalDateTimeConversion(inAmp.getTime2().toLocalDateTime()).getLong(ChronoField.MILLI_OF_DAY))/1000.0;
//            TemporalField prova;
//            
//            
//           
//            
//            //inAmp.getTime1().getLong(ChronoField.MILLI_OF_DAY)
//            
//            JAXBElement<Double> amp_Period_value = BED_OF.createRealQuantityValue(period_value);
//            amp_Period.getAny().add(amp_Period_value);
//            JAXBElement<RealQuantity> period = BED_OF.createAmplitudePeriod(amp_Period);
//            a.getAny().add(period);
//            
//            // unit
//            JAXBElement<String> unit = BED_OF.createAmplitudeUnit("m");
//            a.getAny().add(unit);
//            
//            // category
//            JAXBElement<AmplitudeCategory> category = BED_OF.createAmplitudeCategory(AmplitudeCategory.OTHER);
//            a.getAny().add(category);
//            
//            // type
//            JAXBElement<String> amp_type =  BED_OF.createAmplitudeType("AML");
//            a.getAny().add(amp_type);
//            
//            // comment
//            Comment comm = BED_OF.createComment();
//            JAXBElement<Comment> comment_val = BED_OF.createAmplitudeComment(comm);
//            a.getAny().add(comment_val);
//            
//            // time window
//            TimeWindow TW = BED_OF.createTimeWindow();
//            JAXBElement<Double> TW_begin = BED_OF.createTimeWindowBegin(0.0);
//            TW.getAny().add(TW_begin);
//            
//            JAXBElement<Double> TW_end = BED_OF.createTimeWindowEnd(period_value/2.0);
//            TW.getAny().add(TW_end);
//            
//            XMLGregorianCalendar amp_pick_time  = DatatypeFactory.newInstance().newXMLGregorianCalendar(inAmp.getTime1().format(org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(org.threeten.bp.ZoneId.of("UTC"))).replace(" ", "T"));
//            
//            
//            
//            
//             JAXBElement<XMLGregorianCalendar> TW_ref = BED_OF.createTimeWindowReference(amp_pick_time);
//            TW.getAny().add(TW_ref);
//            
//            JAXBElement<TimeWindow> tw_element = BED_OF.createAmplitudeTimeWindow(TW);
//            a.getAny().add(tw_element);
//     
//             // creationInfo
//            CreationInfo amp_CI_obj = BED_OF.createCreationInfo();
//            JAXBElement<String> amp_CI_obj_AgencyID = BED_OF.createCreationInfoAgencyID("INGV");
//           // JAXBElement<String> mag_CI_obj_AgencyURI = BED_OF.createCreationInfoAgencyURI("smi:www.ingv.it");
//            JAXBElement<String> amp_CI_obj_Author = BED_OF.createCreationInfoAuthor("INGV PickFX");
//            String dateTimeString = Instant.now().toString();
//            XMLGregorianCalendar ora = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
//            JAXBElement<XMLGregorianCalendar> amp_CI_obj_CreationTime = BED_OF.createCreationInfoCreationTime(ora); 
//            amp_CI_obj.getAny().add(amp_CI_obj_AgencyID);
//           // mag_CI_obj.getAny().add(mag_CI_obj_AgencyURI);
//            amp_CI_obj.getAny().add(amp_CI_obj_Author);
//            amp_CI_obj.getAny().add(amp_CI_obj_CreationTime);
//            JAXBElement<CreationInfo> amp_CI = BED_OF.createMagnitudeCreationInfo(amp_CI_obj);
//            a.getAny().add(amp_CI);
//
//            org.ingv.sit.datamodel.Station s;
//                        
//            WaveformStreamID wsid = BED_OF.createWaveformStreamID();
//                            wsid.setChannelCode(inAmp.getCha());
//                            wsid.setLocationCode(inAmp.getLoc());
//                            wsid.setNetworkCode(inAmp.getNet());
//                            wsid.setStationCode(inAmp.getSta());
//                     
//                            JAXBElement<WaveformStreamID> wid = BED_OF.createPickWaveformID(wsid);
//                                                       
//             a.getAny().add(wid);
//                            
//            JAXBElement<Amplitude> res = BED_OF.createEventAmplitude(a);
//            return res;
//        } catch (Exception ex) {
//            return null;
//        }
//        
//    }   
////-------------------------------------------------------------------------------- 
//    /**
//     * Saves the current  data to the specified file.
//     * 
//     * @param file
//     */
//    public void saveEventDataToQMLFile_2_0(File file) {
////        try {
////            org.quakeml.xmlns.quakeml.ObjectFactory ROOT_OF = new org.quakeml.xmlns.quakeml.ObjectFactory(); 
////            org.quakeml.xmlns.bed._1.ObjectFactory BED_OF = new org.quakeml.xmlns.bed._1.ObjectFactory();
////            org.quakeml.xmlns.bed_rt._1.ObjectFactory BEDRT_OF = new org.quakeml.xmlns.bed_rt._1.ObjectFactory();
////            org.quakeml.xmlns.bedt._1.ObjectFactory BEDT_OF = new org.quakeml.xmlns.bedt._1.ObjectFactory();
////            org.quakeml.xmlns.resourcemetadata._1.ObjectFactory META_OF = new org.quakeml.xmlns.resourcemetadata._1.ObjectFactory();
////            org.quakeml.xmlns.common._1.ObjectFactory COMMON_OF = new org.quakeml.xmlns.common._1.ObjectFactory();
////            
////            String origin_id = "originId=" + this.source_event.getInnerObjectEvent().getHYPOCENTER_ID();
////
////            JAXBContext context = JAXBContext.newInstance(org.quakeml.xmlns.quakeml.Quakeml.class);
////            Marshaller m = context.createMarshaller();
////
////            // This should handle namespace prefixes
////            m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapperImpl());
////                       
////            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
////
////            // Create the QuakeML root object:
////            Quakeml quakeml_root =  ROOT_OF.createQuakeml();
////
////            // Create the EventParameters:         
////            EventParameters event_parameters = BED_OF.createEventParameters(); 
////
////            event_parameters.setPublicID("event-parameters-public-id");
////
////            org.quakeml.xmlns.bed._1.Event evento =  BED_OF.createEvent(); 
////            evento.setPublicID("eventId=" + String.valueOf(this.source_event.getInnerObjectEvent().getID()));
////
////            evento.getAny().add(EventType.EARTHQUAKE);
////
////            EventDescription ed = BEDT_OF.createEventDescription(); 
////
////            ed.getTextOrType().add(EventDescriptionType.REGION_NAME);
////            
////            String area = "*Unknown area*";
////            if (this.source_event.getInnerObjectEvent().getHypocenter().getRegion() != null) area = this.source_event.getInnerObjectEvent().getHypocenter().getRegion();
////            ed.getTextOrType().add(area);
////
////            JAXBElement<EventDescription> event_description = BED_OF.createEventDescription(ed);;
////
////            evento.getAny().add(event_description);
////
////            //--------------------------------------------------------
////            // Preferred Magnitude ID
////            //--------------------------------------------------------  
////            JAXBElement<String> pref_mag_id = BED_OF.createEventPreferredMagnitudeID("ID DELLA MAGNITUDO PREFERITA");    
////            evento.getAny().add(pref_mag_id);
////
////            //--------------------------------------------------------
////            // Preferred Origin ID
////            //--------------------------------------------------------
////            JAXBElement<String> pref_origin_id = BED_OF.createEventPreferredOriginID(origin_id);    
////            evento.getAny().add(pref_origin_id);
////
////            //--------------------------------------------------------
////            // Event Creation Info
////            //--------------------------------------------------------   
////            CreationInfo ci =  META_OF.createCreationInfo(); //  new CreationInfo();
////            JAXBElement<String> agency_id = META_OF.createCreationInfoAgencyID("INGV");
////            String prov = "*Unknown software*";
////            if (this.source_event.getInnerObjectEvent().getHypocenter().getProvenance_softwarename()!=null) prov = this.source_event.getInnerObjectEvent().getHypocenter().getProvenance_softwarename();
////            JAXBElement<String> author = META_OF.createCreationInfoAuthor(prov);       
////    //     
////            String dateTimeString = Instant.now().toString();
////            XMLGregorianCalendar ora
////                = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
////    //       
////            JAXBElement<XMLGregorianCalendar> creationTime = META_OF.createCreationInfoCreationTime(ora);
////    //          
////            JAXBElement<CreationInfo> creation_info = BED_OF.createEventCreationInfo(ci);
////            ci.getAny().add(agency_id);
////            ci.getAny().add(author);
////            ci.getAny().add(creationTime);
////    //        
////            evento.getAny().add(creation_info);
////
////            //--------------------------------------------------------
////            // EVENT ORIGIN
////            //--------------------------------------------------------
////            org.quakeml.xmlns.bed._1.Origin origin = BED_OF.createOrigin(); //ORIGIN IS CREATED HERE!!   
//////
////            origin.setPublicID(origin_id);
////            // Origin >> EvaluationMode
////            JAXBElement<EvaluationMode> evaluation_mode = BED_OF.createOriginEvaluationMode(EvaluationMode.MANUAL);
////            origin.getAny().add(evaluation_mode);
////            // Origin >> Type
////            JAXBElement<OriginType> origin_type = BED_OF.createOriginType(OriginType.HYPOCENTER);
////            origin.getAny().add(origin_type);
////            // Origin >> Time    
////            org.quakeml.xmlns.common._1.TimeQuantity tq = COMMON_OF.createTimeQuantity();
////            XMLGregorianCalendar event_origin_time  = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
////          
////            event_origin_time.setYear(source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getYear());
////            event_origin_time.setMonth(source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getMonthValue());
////            event_origin_time.setDay(source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getDayOfMonth());
////            
////            event_origin_time.setTime(source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getHour(), 
////                    source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getMinute(), 
////                    source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getSecond(), 
////                    (int)(source_event.getInnerObjectEvent().getHypocenter().getOriginTime().getNano()/1000000)); 
////            JAXBElement<XMLGregorianCalendar> tqv =  COMMON_OF.createTimeQuantityValue(event_origin_time);
////
////            tq.getAny().add(tqv);    
////            JAXBElement<TimeQuantity> origin_time = BED_OF.createOriginTime(tq);
////            origin.getAny().add(origin_time);
////
////            // Origin >> Latitude
////            RealQuantity lat_value =  COMMON_OF.createRealQuantity();
////            JAXBElement<Double> rqv = COMMON_OF.createRealQuantityValue(source_event.getInnerObjectEvent().getHypocenter().getLat());
////            lat_value.getAny().add(rqv);
////
////            JAXBElement<RealQuantity> lat = BED_OF.createOriginLatitude(lat_value);
////            lat.setValue(lat_value);
////            origin.getAny().add(lat);
////
////            // Origin >> Longitude
////            RealQuantity lon_value =  COMMON_OF.createRealQuantity();
////            rqv = COMMON_OF.createRealQuantityValue(source_event.getInnerObjectEvent().getHypocenter().getLon());
////            lon_value.getAny().add(rqv);
////            JAXBElement<RealQuantity> lon = BED_OF.createOriginLongitude(lon_value);
////            lon.setValue(lon_value);
////            origin.getAny().add(lon);
////
////            // Origin >> Depth
////            RealQuantity dep_value =  COMMON_OF.createRealQuantity();
////            rqv = COMMON_OF.createRealQuantityValue(source_event.getInnerObjectEvent().getHypocenter().getDepth());
////            dep_value.getAny().add(rqv);
////            JAXBElement<RealQuantity> dep = BED_OF.createOriginDepth(dep_value);
////            dep.setValue(dep_value);
////            origin.getAny().add(dep);
////
////            // Origin >> Depth type       
////            JAXBElement<OriginDepthType> dep_type = BED_OF.createOriginDepthType(OriginDepthType.FROM_LOCATION);
////            origin.getAny().add(dep_type);
////
////            // Origin >> Origin Uncertainty
////            OriginUncertainty origin_uncert_value = BED_OF.createOriginUncertainty();
////
////                // >> preferredDescription
////                JAXBElement<OriginUncertaintyDescription> origin_uncert_desc = BED_OF.createOriginUncertaintyPreferredDescription(OriginUncertaintyDescription.CONFIDENCE_ELLIPSOID);
////                origin_uncert_value.getAny().add(origin_uncert_desc);
////
////                // << horizontalUncertainty        
////                JAXBElement<Double> origin_hor_uncert = BED_OF.createOriginUncertaintyHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////                origin_uncert_value.getAny().add(origin_hor_uncert);
////
////                // << minHorizontalUncertainty        
////                JAXBElement<Double> origin_MIN_hor_uncert =  BED_OF.createOriginUncertaintyMinHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////                origin_uncert_value.getAny().add(origin_MIN_hor_uncert);
////
////                // << maxHorizontalUncertainty        
////                JAXBElement<Double> origin_MAX_hor_uncert =  BED_OF.createOriginUncertaintyMaxHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////                origin_uncert_value.getAny().add(origin_MAX_hor_uncert);
////
////                // << azimutMaxHorizontalUncertainty        
////                JAXBElement<Double> origin_azimuthMax_hor_uncert =  BED_OF.createOriginUncertaintyMinHorizontalUncertainty(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////                origin_uncert_value.getAny().add(origin_azimuthMax_hor_uncert);
////
////                // << confidenceLevel   
////                JAXBElement<Double> origin_uncert_confidence_level = BED_OF.createOriginUncertaintyConfidenceLevel(123.456); // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////                origin_uncert_value.getAny().add(origin_uncert_confidence_level);
////
////            JAXBElement<OriginUncertainty> origin_uncert = BED_OF.createOriginOriginUncertainty(origin_uncert_value);
////            origin.getAny().add(origin_uncert);    
////           
////            // Origin >> Origin Quality       
////            OriginQuality origin_quality_value = BEDT_OF.createOriginQuality(); 
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityAzimuthalGap(source_event.getInnerObjectEvent().getHypocenter().getAzimut_gap()));
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityAssociatedPhaseCount(BigInteger.valueOf(source_event.getInnerObjectEvent().getHypocenter().getNph_tot()))); 
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityUsedPhaseCount(BigInteger.valueOf(0)));    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityStandardError(source_event.getInnerObjectEvent().getHypocenter().getRms()));
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityMinimumDistance(source_event.getInnerObjectEvent().getHypocenter().getMinDistance())); 
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityMaximumDistance(source_event.getInnerObjectEvent().getHypocenter().getMaxDistance())); 
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityAssociatedStationCount(BigInteger.valueOf(source_event.getNStations()))); 
////            origin_quality_value.getAny().add(BEDT_OF.createOriginQualityUsedStationCount(BigInteger.valueOf(source_event.getInnerObjectEvent().getHypocenter().getnStationsUsed()))); 
////
////            JAXBElement<OriginQuality> origin_quality = BED_OF.createOriginQuality(origin_quality_value);
////            origin.getAny().add(origin_quality); 
////
////            // Origin >> evaluationStatus 
////            JAXBElement<EvaluationStatus> origin_eval_stat = BED_OF.createOriginEvaluationStatus(EvaluationStatus.REVIEWED);
////            origin.getAny().add(origin_eval_stat); 
////
////            // Origin >> ARRIVALS[]
////            // LOOP OVER EVENT ARRIVALS
////            JAXBElement<Arrival> arrivo = null;
////            JAXBElement<Phase> ph;
////            JAXBElement<Double> az, dist, res, wgt;
////            JAXBElement<String> pid, earth_model_id;
////            JAXBElement<RealQuantity> takeoff;
////            Arrival arrival ; 
////            Phase phName=BEDT_OF.createPhase();
////            int staID, phID;
////            
////            if (source_event.getNStations() > 0) {
////                for (staID=0; staID<source_event.getNStations(); staID++){
////                    if (source_event.getStation(staID).getNPhases() > 0) {
////                        for (phID=0; phID < source_event.getStation(staID).getNPhases(); phID++){
////                            arrival= BED_OF.createArrival();
////                            arrival.setPublicID("arrival_publicId_" + String.valueOf(staID) + "_" + String.valueOf(phID));
////                            // Phase name
////                            phName.setValue(source_event.getStation(staID).getPhase(phID).getPhaseRemark());
////                            ph =  BED_OF.createArrivalPhase(phName);   
////                            arrival.getAny().add(ph);
////                            // Azimuth     
////                            az = BED_OF.createArrivalAzimuth(Double.valueOf(source_event.getStation(staID).getPhase(phID).getAzimut()));
////                            arrival.getAny().add(az);
////                            // Distance
////                            dist = BED_OF.createArrivalDistance(Double.valueOf(this.round(source_event.getStation(staID).getPhase(phID).getDelta(), 2).toString()));
////                            arrival.getAny().add(dist);
////                            // Time residual
////                            res = BED_OF.createArrivalTimeResidual(Double.valueOf(this.round(source_event.getStation(staID).getPhase(phID).getResidual(), 2).toString()));       
////                            arrival.getAny().add(res);
////                            // Time Weight
////                            wgt = BED_OF.createArrivalTimeWeight(Double.valueOf(this.round(source_event.getStation(staID).getPhase(phID).getWeight_post(), 2).toString()));
////                            arrival.getAny().add(wgt);
////                            //PickID
////                            pid = BED_OF.createArrivalPickID("pick_publicId_" + String.valueOf(staID) + "_" + String.valueOf(phID));
////                            arrival.getAny().add(pid);
////                            //EarthModelID
////                            earth_model_id = BED_OF.createArrivalEarthModelID("earth model id... have to generate...");
////                            arrival.getAny().add(earth_model_id);
////                            // Takeoff Angle
////                            RealQuantity rq2 = COMMON_OF.createRealQuantity();
////                            rq2.getAny().add(COMMON_OF.createRealQuantityValue(90.0)); 
////                            takeoff = BED_OF.createArrivalTakeoffAngle(rq2);
////                            arrival.getAny().add(takeoff);
////
////                            arrival.getAny().add(creation_info);
////
////                            arrivo = BED_OF.createOriginArrival(arrival);
////                            origin.getAny().add(arrivo);
////                        }
////                    } // else station staID has no phases
////                }
////            } else {
////                Alert alert = new Alert(AlertType.ERROR);
////                alert.setTitle("Error");
////                alert.setHeaderText("Could not save data");
////                alert.setContentText("Current event has no station data.");
////
////                alert.showAndWait();    
////            }
////    //       
////            // Put the ORIGIN into the EVENT
////            JAXBElement<org.quakeml.xmlns.bed._1.Origin> event_origin = BED_OF.createEventOrigin(origin);
////            evento.getAny().add(event_origin);
////
////    //      --------------------------------------------------------------------------
////    //      END OF ORIGIN    
////    //      --------------------------------------------------------------------------
////
////    //      ------------------------        
////    //      Picks list
////    //      ------------------------   
////            Pick pick ;    
////            JAXBElement<Pick> pick_element;
////            JAXBElement<Double> tqu; 
////            XMLGregorianCalendar pick_t  = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
////            if (source_event.getNStations() > 0) {
////                for (staID=0; staID<source_event.getNStations(); staID++){
////                    if (source_event.getStation(staID).getNPhases() > 0) {
////                        for (phID=0; phID < source_event.getStation(staID).getNPhases(); phID++){
////                            pick = BED_OF.createPick();      
////                            pick.setPublicID("pick_publicId_" + String.valueOf(staID) + "_" + String.valueOf(phID));
////
////                            // Pick >> time (and uncertainty)
////                            tq = COMMON_OF.createTimeQuantity();
////                            
////                            pick_t.setYear(source_event.getStation(staID).getPhase(phID).getDateTime().getYear());
////                            pick_t.setMonth(source_event.getStation(staID).getPhase(phID).getDateTime().getMonthValue());
////                            pick_t.setDay(source_event.getStation(staID).getPhase(phID).getDateTime().getDayOfMonth());
////                            
////                            pick_t.setTime(source_event.getStation(staID).getPhase(phID).getDateTime().getHour(), 
////                                source_event.getStation(staID).getPhase(phID).getDateTime().getMinute(), 
////                                source_event.getStation(staID).getPhase(phID).getDateTime().getSecond(), 
////                                (int)(source_event.getStation(staID).getPhase(phID).getDateTime().getNano()/1000000)); 
////               
////                            tqv =  COMMON_OF.createTimeQuantityValue(pick_t);
////                            
////                            tqu = COMMON_OF.createTimeQuantityUncertainty(Double.valueOf(source_event.getStation(staID).getPhase(phID).getTimeUncertainty()));
////                            
////                            tq.getAny().add(tqv); 
////                            tq.getAny().add(tqu);
////
////                            JAXBElement<TimeQuantity> pick_time = BED_OF.createPickTime(tq);
////
////                            pick_time.setValue(tq);
////                            pick.getAny().add(pick_time);
////                            //
////                            // Pick >> method id
////                            JAXBElement<String> method_id = BED_OF.createPickMethodID("PickFX_manual_methodId");
////                            pick.getAny().add(method_id);
////
////                            // Pick >> onset
////                            if (source_event.getStation(staID).getPhase(phID).getQuality()!=null) {
////                                JAXBElement<PickOnset> po;
////                                if (source_event.getStation(staID).getPhase(phID).getQuality().equalsIgnoreCase("E")) {
////                                    po = BED_OF.createPickOnset(PickOnset.EMERGENT);
////                                } else if (source_event.getStation(staID).getPhase(phID).getQuality().equalsIgnoreCase("I")) {
////                                    po = BED_OF.createPickOnset(PickOnset.IMPULSIVE);
////                                } else po = BED_OF.createPickOnset(PickOnset.QUESTIONABLE);
////                                pick.getAny().add(po);
////                            }
////                            // Pick >> phase hint
////                            Phase fase = BEDT_OF.createPhase();
////                            fase.setValue(source_event.getStation(staID).getPhase(phID).getPhaseRemark());
////                            JAXBElement<Phase> jph = BED_OF.createPickPhaseHint(fase);
////                            pick.getAny().add(jph);
////
////                            // Pick >> polarity
////                            if (source_event.getStation(staID).getPhase(phID).getFirstMotion()!=null) {
////                                JAXBElement<PickPolarity> pp;
////                                if ((source_event.getStation(staID).getPhase(phID).getFirstMotion().equalsIgnoreCase("D")) || (source_event.getStation(staID).getPhase(phID).getFirstMotion().equalsIgnoreCase("-"))) {
////                                    pp = BED_OF.createPickPolarity(PickPolarity.NEGATIVE);
////                                } else pp = BED_OF.createPickPolarity(PickPolarity.UNDECIDABLE);
////                                pick.getAny().add(pp);
////                            }
////                            // Pick >> evaluation mode
////                            // .........
////
////                            // Pick >> creation info
////                            // .........
////                            
////                            // <waveformID networkCode="IV" stationCode="ARRO" channelCode="EHZ" locationCode=""/>
////                            // Pick >> Waveform ID
////                            //
////                            org.quakeml.xmlns.waveform._1.ObjectFactory  tmpOF = new org.quakeml.xmlns.waveform._1.ObjectFactory();                            
////                            WaveformStreamID wsid = tmpOF.createWaveformStreamID();
////                            wsid.setChannelCode(source_event.getStation(staID).getPhase(phID).getChannelCode());
////                            wsid.setLocationCode(source_event.getStation(staID).getLocation());
////                            wsid.setNetworkCode(source_event.getStation(staID).getNetwork());
////                            wsid.setStationCode(source_event.getStation(staID).getCode());
////                     
////                            JAXBElement<WaveformStreamID> wid = BEDRT_OF.createPickWaveformID(wsid);
////                            
////                            pick.getAny().add(wid);
////
////                            pick_element = BED_OF.createEventPick(pick);
////                            evento.getAny().add(pick_element);
////                        }
////                    } // else station staID has no phases
////                }
////            }
//////
////            //       
////            // Put the EVENT into the EVENT PARAMETERS
////            event_parameters.getAny().add(evento);      
////            //
////            // Put the EVENT PARAMETERS into the QUAKEML (root)
////            quakeml_root.setEventParameters(event_parameters);
////            //
////            // Run the marshaler
////            m.marshal(quakeml_root, file);
////    //      
////        } catch (Exception e) { // catches ANY exception
////            Alert alert = new Alert(AlertType.ERROR);
////            alert.setTitle("Error");
////            alert.setHeaderText("Could not save data");
////            alert.setContentText("Could not save data to file:\n" + file.getPath());
////
////            alert.showAndWait();
////        }
//    }
//--------------------------------------------------------------------------------
    /**
     * @return the source_event
     */
    public org.ingv.sit.datamodel.Event getSource_event() {
        return source_event;
    }
//--------------------------------------------------------------------------------
    /**
     * @param source_event the source_event to set
     */
    public void setSource_event(org.ingv.sit.datamodel.Event source_event) {
        this.source_event = source_event;
    }
////
//    private  BigDecimal round(float d, int decimalPlace) {
//        try {
//            BigDecimal bd = new BigDecimal(Float.toString(d));
//            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
//            return bd;
//        } catch (Exception ex) { 
//            return BigDecimal.valueOf(0);
//        }
//        
//    }
    
    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
}
