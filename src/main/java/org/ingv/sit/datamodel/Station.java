/**
 *
 *
 * Andrea Bono 
 * I.N.G.V. Isituto Nazionale di Geofisica e Vulcanologia
 * C.N.T. Centro Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 * andrea.bono@ingv.it
 *
 */

package org.ingv.sit.datamodel;
import java.util.ArrayList;
import java.util.Comparator;
import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.PickEmersio;
import org.ingv.dante.model.PickFirstmotion;
import org.ingv.sit.App;
import org.ingv.sit.sigpro._StationResponse_Channel;

public class Station {
    private String Network;
    private String Code;
    private String Name;
    private double lat, lon, elev;
    private ArrayList Phases = new ArrayList();
    private ArrayList<Waveform> Waves = new ArrayList();
    private ArrayList<Waveform> Waves_WA = new ArrayList();
    private float Coda_duration;
    private double max_latency_in_seconds = -9.9;   
    private int EarthWorm_WaveServer_Client_INDEX=-1;
    private int FDSN_Service_index=-1;     
    private ArrayList<_StationResponse_Channel> Channels;
    
    public Station(){
    }
//------------------------------------------------------------------------------       
    public Station(String STAZ_CODE, String STAZ_NAME, float latitude, float longitude, float elevation,
            String in_net) {
        Code=STAZ_CODE;
        Name=STAZ_NAME;
        lat=latitude;
        lon=longitude;
        elev = elevation;
        Network = in_net;
    }
//------------------------------------------------------------------------------        
    public Station(String STAZ_CODE, ArrayList<Station> arrStationsInNetwork) {
        Code=STAZ_CODE;
        RecoverStationData(arrStationsInNetwork);
    }
//------------------------------------------------------------------------------    
     public String FindFirstAvailableChannel(){
        try {
            if (Channels==null) {
                Channels = App.G.SeismicNet.FindChannels(Code);
            }
            if (Channels==null) return null;
            if (Channels.isEmpty()) return null;
                       
            int id=0;
            while (id < Channels.size()){
                if (Channels.get(id).getCode().startsWith("HH") || Channels.get(id).getCode().startsWith("EH"))
                    return Channels.get(id).getCode();
                    id++;
            }
            
            // Qui ci arriva solo se non ha trovato HH e EH           
            return Channels.get(0).getCode();
            
        } catch (Exception ex){
            return null;
        }
    }
//------------------------------------------------------------------------------        
    public boolean HasAnUsedPhase(){
        try{
            if (Phases==null) return false;
            if (Phases.isEmpty()) return false;
            
            for (int phId=0; phId < Phases.size(); phId++){
                if (((ObjectArrival)Phases.get(phId)).getArrTimeIsUsed()==null){
                    if (((ObjectArrival)Phases.get(phId)).getWeight()>0.0) 
                        return true;
                    else return false;
                } else {
                    return ((ObjectArrival)Phases.get(phId)).getArrTimeIsUsed();
                } 
            }
            
            return false;
            
        } catch (Exception ex){
            return false;
        }
    }
    
    
    public boolean phase_is_used(int phaseId){
        try{
            if (Phases==null) return false;
            if (Phases.isEmpty()) return false;
            
            
            if (((ObjectArrival)Phases.get(phaseId)).getArrTimeIsUsed()==null){
                if (((ObjectArrival)Phases.get(phaseId)).getWeight()>0.0) 
                    return true;
                else return false;
            } else {
                return ((ObjectArrival)Phases.get(phaseId)).getArrTimeIsUsed();
            } 
            

            
        } catch (Exception ex){
            return false;
        }
    }
//------------------------------------------------------------------------------    
    public int FindPhaseID(ObjectArrival ph){
        try {
            if (Phases==null) return -1;
            if (Phases.isEmpty()) return -1;
            
            for (int phId=0; phId < Phases.size(); phId++){
                   if (ph.getIscCode().equalsIgnoreCase(((ObjectArrival)Phases.get(phId)).getIscCode()))
                        return phId;
            }
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }    
//------------------------------------------------------------------------------    
    public int FindFirstPhase(String phaseName){
        try {
            if (Phases==null) return -1;
            if (Phases.isEmpty()) return -1;
            
            for (int phId=0; phId < Phases.size(); phId++){
                if (((ObjectArrival)Phases.get(phId)).getIscCode().toUpperCase().contains(phaseName) ) return phId;
            }
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
    
    public ObjectArrival FindFirstPhase_fase(String phaseName){
        try {
            if (Phases==null) return null;
            if (Phases.isEmpty()) return null;
            
            for (int phId=0; phId < Phases.size(); phId++){
                if (((ObjectArrival)Phases.get(phId)).getIscCode().toUpperCase().contains(phaseName) ) return (ObjectArrival)Phases.get(phId);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
//------------------------------------------------------------------------------    
    public void deletePhase(int idPhase){
        if (getNPhases()>= idPhase)
            Phases.remove(idPhase);
    }
//------------------------------------------------------------------------------    
    public void setPhasePolarity(int idPhase, String firstmotion){
        if (getNPhases()>= idPhase){
            if (firstmotion.toUpperCase().contains("U"))
                ((ObjectArrival)Phases.get(idPhase)).getPick().setFirstmotion(PickFirstmotion.U);
            else if (firstmotion.toUpperCase().contains("D"))
                ((ObjectArrival)Phases.get(idPhase)).getPick().setFirstmotion(PickFirstmotion.D);
            else
                ((ObjectArrival)Phases.get(idPhase)).getPick().setFirstmotion(null);
                //((ObjectArrival)Phases.get(idPhase)).setFirstmotion(PickFirstmotion.NULL);
        }
    }
//------------------------------------------------------------------------------    
    public void setPhaseQuality(int idPhase, String quality){
        if (getNPhases()>= idPhase){
            //((ObjectArrival)Phases.get(idPhase)).setEmersio(quality);
            if (quality.toUpperCase().contains("I"))
                    ((ObjectArrival)Phases.get(idPhase)).getPick().setEmersio(PickEmersio.I); 
                else if (quality.toUpperCase().contains("E"))
                    ((ObjectArrival)Phases.get(idPhase)).getPick().setEmersio(PickEmersio.E); 
                else
                    ((ObjectArrival)Phases.get(idPhase)).getPick().setEmersio(null); 
        }
    }
//------------------------------------------------------------------------------
    public void RecoverDuration(ObjectMagnitude Md){
        if (Md.getStationmagnitudes()==null) return;
        if (Md.getStationmagnitudes().isEmpty()) return;
        for (int i=0; i< Md.getStationmagnitudes().size(); i++){
            if (Md.getStationmagnitudes().get(i).getAmplitude().getSta().equalsIgnoreCase(Code)){
                Coda_duration = Md.getStationmagnitudes().get(i).getAmplitude().getAmp1().floatValue();
            }
        }
    }
    
    
//------------------------------------------------------------------------------
    private void RecoverStationData(ArrayList<Station> arrStations){
        try {
            int i=0;
            boolean fnd=false;         
            while ((i<arrStations.size()) && (!fnd))   {
                if (Code.equalsIgnoreCase(((Station)arrStations.get(i)).getCode())) {
                    fnd=true;
                    lat = ((Station)arrStations.get(i)).getLat();
                    lon = ((Station)arrStations.get(i)).getLon();
                    elev = ((Station)arrStations.get(i)).getElev();
                    Network= ((Station)arrStations.get(i)).getNetwork();
                    Name = ((Station)arrStations.get(i)).getName();
                    //Location = ((Station)arrStations.get(i)).getLocation();
                    EarthWorm_WaveServer_Client_INDEX= ((Station)arrStations.get(i)).getEarthWorm_WaveServer_Client_INDEX();
                    Channels = ((Station)arrStations.get(i)).getChannels();
                } else i+=1;
            }
            
        }catch (Exception ex) {
        }
    } 
//------------------------------------------------------------------------------    
/**
     * @return the Coda_duration
     */
    public float getCoda_duration() {
        return Coda_duration;
    }
//------------------------------------------------------------------------------    
    /**
     * @param Coda_duration the Coda_duration to set
     */
    public void setCoda_duration(float in_Coda_duration) {
        Coda_duration = in_Coda_duration;
    }    
//------------------------------------------------------------------------------    
   /**
     * @return the Location
     * @param channelcode the channel code that owns a location
     */
    public String getLocation(String channelcode) {      
        if (channelcode==null) return "--";   // Questi return sono pericolosi!!
        if (Channels==null) return "--";
        if (Channels.isEmpty()) return "--";
       
        for (int i=0; i<Channels.size(); i++){
            if (Channels.get(i).getCode().equalsIgnoreCase(channelcode))
                return Channels.get(i).getLocationCode();
        }
        
        return "--";
       
    }
//------------------------------------------------------------------------------    
    /**
     * @return the Network
     */
    public String getNetwork() {
        return Network;
    }
//------------------------------------------------------------------------------    
    /**
     * @param Network the Network to set
     */
    public void setNetwork(String in_Network) {
        Network = in_Network;
    }
//--------------------------------------------------------------------------
    /**
     * @return the waves
     */
    public  ArrayList<Waveform> getWaves() {
        return Waves;
    }
//------------------------------------------------------------------------------    
    public  Waveform getWave(int id) {
        return Waves.get(id);
    }
//------------------------------------------------------------------------------    
    public  Waveform getWave_WA(int id) {
        return Waves_WA.get(id);
    }    
//------------------------------------------------------------------------------ 
    /**
     * @param aWaves the waves to set
     */
    public void setWaves(ArrayList<Waveform> aWaves) {
        Waves = aWaves;
    }
//------------------------------------------------------------------------------    
    /**
     * @return the Code
     */
    public String getCode() {
        return Code;
    }
//------------------------------------------------------------------------------
    /**
     * @param Code the Code to set
     */
    public void setCode(String in_Code) {
        Code = in_Code;
    }
//------------------------------------------------------------------------------
    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }
//------------------------------------------------------------------------------
    /**
     * @param lat the lat to set
     */
    public void setLat(float in_lat) {
        lat = in_lat;
    }
//------------------------------------------------------------------------------
    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }
//------------------------------------------------------------------------------
    /**
     * @param lon the lon to set
     */
    public void setLon(float in_lon) {
        lon = in_lon;
    }
//------------------------------------------------------------------------------
    /**
     * @return the elev
     */
    public double getElev() {
        return elev;
    }
//------------------------------------------------------------------------------
    /**
     * @param elev the elev to set
     */
    public void setElev(float in_elev) {
        elev = in_elev;
    }
//------------------------------------------------------------------------------
    /**
     * @return the Phases
     */
    public ArrayList getPhases() {
        return Phases;
    }
//------------------------------------------------------------------------------    
    public int getNPhases(){
        if (Phases==null) return 0;
        if (Phases.isEmpty()) return 0;
    
        return Phases.size();
        
    }
//------------------------------------------------------------------------------   
    /**
     * @param Phases the Phases to set
     */
    public void setPhases(ArrayList in_Phases) {
        Phases = in_Phases;
    }
//------------------------------------------------------------------------------    
    /**
     * @return the Phases
     */
    public ObjectArrival getPhase(int index) {
        return (ObjectArrival)Phases.get(index);
    }
    
//------------------------------------------------------------------------------    
//// 20210118
//    /**
//     * @return the specified Station Magnitude
//     */
//    public StationMagnitude getMagnitude(int index) {
//        return (StationMagnitude)Station_Magnitudes.get(index);
//    }
//------------------------------------------------------------------------------    
    public void addPhase(ObjectArrival newPhase) {
        Phases.add(newPhase);
    }
//------------------------------------------------------------------------------    
    public void addWave(Waveform newWave) {
        Waves.add(newWave);
    }
//------------------------------------------------------------------------------    
    public void addWave_WA(Waveform newWave) {
        Waves_WA.add(newWave);
    }
//------------------------------------------------------------------------------    
    public int getNWaves(){
        try {
            return Waves.size();
        } catch (Exception ex) {
            return 0;
        }
    }  
    
    public int getNWaves_WA(){
        try {
            return Waves_WA.size();
        } catch (Exception ex) {
            return 0;
        }
    }  

//    /**
//     * @return the plot_box_id
//     */
//    public int getPlot_box_id() {
//        return plot_box_id;
//    }
//
//    /**
//     * @param plot_box_id the plot_box_id to set
//     */
//    public void setPlot_box_id(int in_plot_box_id) {
//        plot_box_id = in_plot_box_id;
//    }
//------------------------------------------------------------------------------
    public int phase_index(String phRemark) {
        try {
            boolean phase_found = false;
            int i=0, ph_id =-1;
            while ((i<getNPhases()) && !phase_found) {
                //if (getPhase(i).getIscCode().equalsIgnoreCase(phRemark)) {
                if (getPhase(i).getIscCode().toUpperCase().contains(phRemark.toUpperCase())) {
                    ph_id=i;
                    phase_found=true;
                } else i+=1;
            }
            return ph_id;
        }
        catch (Exception ex){
            return -1;
        }
    }
//--------------------------------------------------------------------------------
    /**
     * Picks a new phase for a station or updates an old one
     * @param ph the Phase object to be picked
     * 
     */
    public void Pick_a_phase(ObjectArrival ph) {
        if (ph.getPick().getLocalspace()==null)
            ph.getPick().setLocalspace(new ObjectLocalspace());
        ph.getPick().getLocalspace().setName("sit arrival");
        ph.getPick().getLocalspace().setDescription("sit arrival localspace description");
        ph.getPick().setIdLocalspace(null);
        
        ph.getPick().setSta(getCode());
        ph.getPick().setNet(getNetwork());
        
        ph.getPick().setLoc(getLocation(ph.getPick().getCha()));
        
        int phId = phase_index(ph.getIscCode());
        if (phId == -1) {
            addPhase(ph);
            SortPhases();
        } else {
            getPhases().set(phId, ph);
            // Set the old phase tu Used=true
            ((ObjectArrival)Phases.get(phId)).setArrTimeIsUsed(true); //.setUsedForLocation(true); 
        }
    }
//------------------------------------------------------------------------------
    public void SortPhases(){
        if (Phases==null) return;
        if (Phases.size()<=1) return;
               
        Phases.sort(new Comparator<ObjectArrival>() {
            @Override
            public int compare(ObjectArrival a1, ObjectArrival a2) {
                //return p1.getAge() - p2.getAge();
                return (int)(a1.getPick().getArrivalTime().toEpochSecond() - a2.getPick().getArrivalTime().toEpochSecond());
            }
        });    
    }
    
    
    
//------------------------------------------------------------------------------
    public void Exclude_from_location(){
        if (Phases==null) return;
        if (Phases.isEmpty()) return;
        
        for (int i =0; i<Phases.size(); i++){
            ((ObjectArrival)Phases.get(i)).setArrTimeIsUsed(false);   
            //((ObjectArrival)Phases.get(i)).getPick().setQualityClass(4);
        }
    }
    
    public void Include_in_location(){
        if (Phases==null) return;
        if (Phases.isEmpty()) return;
        
        for (int i =0; i<Phases.size(); i++){
            ((ObjectArrival)Phases.get(i)).setArrTimeIsUsed(true);   
        }
    }
//------------------------------------------------------------------------------    

    /**
     * @return the max_latency_in_seconds
     */
    public double getMax_latency_in_seconds() {
        return max_latency_in_seconds;
    }

    /**
     * @param in_max_latency_in_seconds the max_latency_in_seconds to set
     */
    public void setMax_latency_in_seconds(double in_max_latency_in_seconds) {
        max_latency_in_seconds = in_max_latency_in_seconds;
    }

    /**
     * @return the EarthWorm_WaveServer_Client_INDEX
     */
    public int getEarthWorm_WaveServer_Client_INDEX() {
        return EarthWorm_WaveServer_Client_INDEX;
    }

    /**
     * @param in_EarthWorm_WaveServer_Client_INDEX the EarthWorm_WaveServer_Client_INDEX to set
     */
    public void setEarthWorm_WaveServer_Client_INDEX(int in_EarthWorm_WaveServer_Client_INDEX) {
        EarthWorm_WaveServer_Client_INDEX = in_EarthWorm_WaveServer_Client_INDEX;
    }
    
    /**
    * Calculate distance between two points in latitude and longitude taking
    * into account height difference. If you are not interested in height
    * difference pass 0.0. Uses Haversine method as its base.
    * 
    * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
    * el2 End altitude in meters
    * @returns Distance in KMeters
    */
    public double distanceFrom(double latitude,  double longitude, double elevation) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat - latitude);
        double lonDistance = Math.toRadians(lon - longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // * 1000; // convert to meters

        double height = 0; //elev - elevation;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

//    /**
//     * @return the plot_box_id_WA
//     */
//    public int getPlot_box_id_WA() {
//        //return plot_box_id_WA;
//        return -1;
//    }
//
//    /**
//     * @param plot_box_id_WA the plot_box_id_WA to set
//     */
//    public void setPlot_box_id_WA(int plot_box_id_WA, String channel_code) {
//        boolean fnd=false;
//        int i =0;
//        while ((!fnd) && (i< getNWaves_WA())){
//            if (getWave_WA(i).getChannelCode().equalsIgnoreCase(channel_code)) {
//                fnd = true;
//                
//            }
//        }
//        
//        //plot_box_id_WA = plot_box_id_WA;
//    }

    /**
     * @return the Waves_WA
     */
    public ArrayList<Waveform> getWaves_WA() {
        return Waves_WA;
    }

    /**
     * @param Waves_WA the Waves_WA to set
     */
    public void setWaves_WA(ArrayList<Waveform> in_Waves_WA) {
        Waves_WA = in_Waves_WA;
    }

    /**
     * @return the FDSN_Service_index
     */
    public int getFDSN_Service_index() {
        return FDSN_Service_index;
    }

    /**
     * @param FDSN_Service_index the FDSN_Service_index to set
     */
    public void setFDSN_Service_index(int in_FDSN_Service_index) {
        FDSN_Service_index = in_FDSN_Service_index;
    }

    /**
     * @return the Channels
     */
    public ArrayList<String> getChannelsAsStrings() {
        ArrayList<String> res = new ArrayList();
        if ((Channels!= null) && !Channels.isEmpty()){
            for (int i=0; i< Channels.size(); i++) {
                res.add(Channels.get(i).getCode());
            }
        }
        return res;
    }

    /**
     * @param Channels the Channels to set
     */
//    public void setChannels(ArrayList<String> Channels) {
//        Channels = Channels;
//    }

//    /**
//     * @return the idDataSource
//     */
//    public int getIdDataSource() {
//        return idDataSource;
//    }
//
//    /**
//     * @param idDataSource the idDataSource to set
//     */
//    public void setIdDataSource(int in_idDataSource) {
//        idDataSource = in_idDataSource;
//    }

    /**
     * @return the Channels
     */
    public ArrayList<_StationResponse_Channel> getChannels() {
        return Channels;
    }
    public int getChannelID(String channel_code) {
        if (Channels==null) return -1;
        if (Channels.isEmpty()) return -1;
        
        for (int i=0; i < Channels.size(); i++){
            if (channel_code.equalsIgnoreCase(Channels.get(i).getCode()))
                return i;
        }
        
        
        return -1;
    }

    /**
     * @param Channels the Channels to set
     */
    public void setChannels(ArrayList<_StationResponse_Channel> in_Channels) {
        Channels = in_Channels;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String in_Name) {
        Name = in_Name;
    }
}
