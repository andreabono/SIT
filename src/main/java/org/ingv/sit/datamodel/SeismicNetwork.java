
package org.ingv.sit.datamodel;


import edu.iris.dmc.IrisUtil;
import edu.sc.seis.seisFile.ChannelTimeWindow;
import java.util.ArrayList;
import java.util.List;
import org.ingv.sit.App;
import edu.sc.seis.seisFile.fdsnws.FDSNStationQuerier;
import edu.sc.seis.seisFile.fdsnws.FDSNStationQueryParams;
import edu.sc.seis.seisFile.fdsnws.stationxml.Channel;
import edu.sc.seis.seisFile.fdsnws.stationxml.FDSNStationXML;
import edu.sc.seis.seisFile.fdsnws.stationxml.Network;
import edu.sc.seis.seisFile.fdsnws.stationxml.NetworkIterator;
import edu.sc.seis.seisFile.fdsnws.stationxml.PolesZeros;
import edu.sc.seis.seisFile.fdsnws.stationxml.Response;
import edu.sc.seis.seisFile.fdsnws.stationxml.ResponseStage;
import edu.sc.seis.seisFile.fdsnws.stationxml.StationIterator;
import edu.sc.seis.seisFile.fdsnws.stationxml.Unit;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
//import java.net.URI;
import java.time.Instant;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.ingv.sit.utils.pfxDialog;
import org.ingv.sit.sigpro._SAC_PZNum;
import org.ingv.sit.sigpro._SAC_ResponseStruct;
import org.ingv.sit.sigpro._StationResponse_Channel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author andreabono
 */
public class SeismicNetwork {
    // E' semplicemente un array di oggetti di tipo 'Station'
    private ArrayList<Station> Stations = new ArrayList();

    
    public Station getStation(String StationCode){
        try {
            if (Stations==null || Stations.isEmpty()) return null;
            int id=0;
            while (id < Stations.size()){
                if (Stations.get(id).getCode().equalsIgnoreCase(StationCode)){
                    return Stations.get(id);
                } else id++;
            }
            
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     * @return the Stations
     */
    public ArrayList getStations() {
        return Stations;
    }

    /**
     * @param Stations the Stations to set
     */
    public void setStations(ArrayList Stations) {
        this.Stations = Stations;
    }
    
    public void RemoveDuplicates(){
        Stations = remove_duplicates(Stations);
    }
    
    private ArrayList remove_duplicates(ArrayList inArr){
        try {
            ArrayList res = new ArrayList();
            for (int i=0; i< inArr.size(); i++){
                if (!unefficent_search(res, ((Station)inArr.get(i)).getCode())) res.add(inArr.get(i)); 
            }
            return res;
        } catch (Exception ex){
            return null;
        }
    }
    
    
    public ArrayList FindChannels(String StationCode){
        try {
            int id = this.StationCodeToStationId(StationCode);
            if (id > -1) {
                return Stations.get(id).getChannels();
            } else
            
            return null;
        } catch (Exception ex){
            return null;
        }
    }
    
    private boolean unefficent_search(ArrayList<Station> inArr, String station_code){
        
        Comparator c = new Comparator<Station>() {
            @Override
            public int compare(Station s1, Station s2) {
                return s1.getCode().compareTo(s2.getCode());
            }
        };
        inArr.sort(c);

        try {
            
            int k=0;
            while ((k<inArr.size())){
                if (((Station)inArr.get(k)).getCode().equalsIgnoreCase(station_code))
                    return true;
                else if (((((Station)inArr.get(k)).getCode().compareTo(station_code) ))>0)
                    return false;
                else k++;
            }
            return false;
        } catch (Exception ex){
            return false;
        }
    }
//------------------------------------------------------------------------------    
    public String Stations_in_box(Double min_lat, Double min_lon,
            Double max_lat, Double max_lon){
        try {
            String res="";   
            for (int i=0; i<Stations.size(); i++){
                 if ((min_lat <= ((Station)Stations.get(i)).getLat()) &&
                    (max_lat >= ((Station)Stations.get(i)).getLat()) &&  
                    (min_lon <= ((Station)Stations.get(i)).getLon()) &&
                    (max_lon >= ((Station)Stations.get(i)).getLon()) ){
                    //
                        res +=  ((Station)Stations.get(i)).getNetwork() + " " +
                                 ((Station)Stations.get(i)).getCode() + " " +
                                 ((Station)Stations.get(i)).getLocation(((Station)Stations.get(i)).getChannelsAsStrings().get(0).substring(0,2) + "Z") +  " " +
                                 ((Station)Stations.get(i)).getChannelsAsStrings().get(0).substring(0,2) + "Z"  + " <DATES_PLACEHOLDER>\n";
                    }
            }
// 
            return res;         
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<ChannelTimeWindow> Stations_in_box(Double min_lat, Double min_lon,
            Double max_lat, Double max_lon, Instant date_start, Instant date_end){
        try {
            //String res=""; 
            List<ChannelTimeWindow> res = new ArrayList<>();
            for (int i=0; i<Stations.size(); i++){
                 if ((min_lat <= ((Station)Stations.get(i)).getLat()) &&
                    (max_lat >= ((Station)Stations.get(i)).getLat()) &&  
                    (min_lon <= ((Station)Stations.get(i)).getLon()) &&
                    (max_lon >= ((Station)Stations.get(i)).getLon()) ){
                    //
                        res.add(new ChannelTimeWindow(((Station)Stations.get(i)).getNetwork(),
                                ((Station)Stations.get(i)).getCode(),
                                 ((Station)Stations.get(i)).getLocation(((Station)Stations.get(i)).getChannelsAsStrings().get(0).substring(0,2) + "Z"),
                                ((Station)Stations.get(i)).getChannelsAsStrings().get(0).substring(0,2) + "Z",
                                date_start, date_end
                                
                        ));
                    }
            }
// 
            return res;         
        } catch (Exception ex) {
            return null;
        }
    }
    
//--------------------------------------------------------------------------------  
    public int StationCodeToStationId(String code){
        try{
            if (this.Stations==null) return -1;
            if (this.Stations.isEmpty()) return -1;
            
            int id = 0;
            while  (id < this.Stations.size()){
                //if (((Station)this.Stations.get(id)).getCode() > code) return -1;
                
                if (((Station)this.Stations.get(id)).getCode().equalsIgnoreCase(code)){
                    return id;
                } else id +=1;
        
            }
            
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------
    /*
        Calculate distance between two points in latitude and longitude taking
        into account height difference.If you are not interested in height
        difference pass 0.0. Uses Haversine method as its base.
 
        lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
        el2 End altitude in meters 
        @return Distance in KMeters
    */
    public double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
//------------------------------------------------------------------------------      
    public boolean read(boolean ForceUpdate){
        boolean res;
        try {     
            if (App.G.IsDirEmpty("networks") || ForceUpdate){
                pfxDialog.ShowInformationMessage("No network information available in local cache.\n Going to read from services.\n This could take some minutes...", null);
                Logger.getLogger("org.ingv.pfx.datamodel.SeismicNetwork").log(java.util.logging.Level.INFO, "No network information available in local cache.\n Going to read from services.\n This could take some minutes...");
                
                // Read the networks via FDSN web-services (level=RESPONSE ==> SLOW)
                res = readFDSN_StationXml_multithread(); 
            } else {
                res = readXMLLocalCache(false);
            }
            
            if ((App.G.FileExists("networks_stationxml")) && (!App.G.IsDirEmpty("networks_stationxml"))){
                res = readXMLLocalCache(true);
            }
                     
            return res;
        } catch (Exception ex) {
            return false;
        }   
    }
//------------------------------------------------------------------------------
    private boolean readXMLLocalCache(boolean isStationXML){
        try {
            // List subdirectories
            File[] directories;
            if (!isStationXML) {
                directories = new File("networks").listFiles((File file) -> file.isDirectory());
            } else {
                directories = new File("networks_stationxml").listFiles((File file) -> file.isDirectory());
            }
            
            if (directories.length<=0) return false;
            
            // For each directory (network)
            //int n_stations_found=0;
            for (int i=0; i< directories.length; i++){
                File[] xmlfiles = new File(directories[i].getAbsolutePath()).listFiles(new FileFilter() {
                    @Override
                        public boolean accept(File file) {
                            return file.getName().endsWith(".xml");
                        }
                    });    
                if (xmlfiles.length<=0) return false;
                
                // For each file... (Station)
                Station tmpSta;
                ArrayList<Station> tmpStaList;
                for (File xmlfile : xmlfiles) {
                    try {
                        if (!isStationXML) {
                            tmpSta = read_station_from_xml_pfxcustom_file(directories[i].getName(), xmlfile.getPath());
                            
                            if (tmpSta!=null){
                                Stations.add(tmpSta);
                                //n_stations_found++;
                            }
                        } else {
                            tmpStaList = read_station_from_FdSNStationxml_file(directories[i].getName(), xmlfile.getPath());
                            if (tmpStaList!=null){
                                int idStationInNet;
                                for (Station newS : tmpStaList){
                                    idStationInNet=StationCodeToStationId(newS.getCode());
                                    if (idStationInNet==-1){
                                        Stations.add(newS);
                                        //n_stations_found++
                                    } else {
                                        // the local StationXML station REPLACES THE OLD ONE
                                        Stations.set(idStationInNet, newS);
                                    }
                                }
                            }
                        }
                        //
                        
                    } catch (Exception ex) {
                        System.out.println("Corrupted station xml file: " + xmlfile.getPath());
                    }
                }
            }
          
            return true;
        }catch (Exception ex) {
            return false;
        } 
         
    }
    
    private ArrayList<Station> read_station_from_FdSNStationxml_file(String netCode, String fName){
        /*
        Reads a list of stations from a stationXML file in the filesystem
        */
        try {
            Station newPFXstation;
            ArrayList<Station> res = new ArrayList<>();
            ArrayList<_StationResponse_Channel> channel_responses;
            // Specifica il percorso del tuo file StationXML
            String stationXMLFilePath = fName;
            FileInputStream fileInputStream = new FileInputStream(new File(stationXMLFilePath));
            //InputStream inputStream = new FileInputStream(source);
            edu.iris.dmc.fdsn.station.model.FDSNStationXML document = IrisUtil.readXml(fileInputStream);
            edu.iris.dmc.fdsn.station.model.ResponseStage stage0;
            edu.iris.dmc.fdsn.station.model.PolesZeros PaZ;
            _SAC_ResponseStruct response_structure;
            for (edu.iris.dmc.fdsn.station.model.Network network : document.getNetwork()) {
                for (edu.iris.dmc.fdsn.station.model.Station station : network.getStations()) {
                    newPFXstation = new Station(station.getCode(), 
                            station.getSite().getName(), 
                             station.getLatitude().getValue().floatValue(), 
                            station.getLongitude().getValue().floatValue(), 
                            station.getElevation().getValue().floatValue(), 
                              station.getNetwork().getCode());
                    if (newPFXstation.getNetwork().isBlank())
                        newPFXstation.setNetwork(netCode);
                    
                    if (station.getChannels() != null) {
                        channel_responses = new ArrayList();
                        for (edu.iris.dmc.fdsn.station.model.Channel channel : station.getChannels()) {
                            channel.getAny().clear();
                            
                            _StationResponse_Channel tmpCh = new _StationResponse_Channel(channel.getCode());
                            if (channel.getResponse() != null) {
                                tmpCh.setSAC_Response_Structure(new _SAC_ResponseStruct());
                                
                                stage0 = channel.getResponse().getStage().get(0);
                                
                                PaZ = stage0.getPolesZeros();
                                edu.iris.dmc.fdsn.station.model.Units input_units = PaZ.getInputUnits();
                                edu.iris.dmc.fdsn.station.model.Units  output_units = PaZ.getOutputUnits();
//
                                // Compone la struttura di poli e zeri per il canale
                                response_structure = new _SAC_ResponseStruct();
                                response_structure.iNumPoles = PaZ.getPole().size();
                                response_structure.iNumZeroes = PaZ.getZero().size();
                                response_structure.Poles = new _SAC_PZNum[response_structure.iNumPoles];
                                response_structure.Zeroes = new _SAC_PZNum[response_structure.iNumZeroes];
                                
                                response_structure.dGain = ((stage0.getPolesZeros().getNormalizationFactor() *
                                                        channel.getResponse().getInstrumentSensitivity().getValue()))/1000000000.0;
                                
                                _SAC_PZNum tmpP; 
                                for (int i=0; i< response_structure.iNumPoles; i++ ){
                                    tmpP = new _SAC_PZNum();
                                    tmpP.dReal = PaZ.getPole().get(i).getReal().getValue();
                                    tmpP.dImag = PaZ.getPole().get(i).getImaginary().getValue();
                                    response_structure.Poles[i]=tmpP;
                                }
                                
                                 _SAC_PZNum tmpZ; 
                                for (int i=0; i< response_structure.iNumZeroes; i++ ){
                                    tmpZ = new _SAC_PZNum();
                                    tmpZ.dReal = PaZ.getZero().get(i).getReal().getValue();
                                    tmpZ.dImag = PaZ.getZero().get(i).getImaginary().getValue();
                                    response_structure.Zeroes[i]=tmpZ;
                                }
                                //***************************************
                                int nZeroesplus = response_structure.need_a_zero();
                                if (nZeroesplus>0) {
                                    response_structure.Zeroes = response_structure.Add_n_zeroes(nZeroesplus);
                                    
                                }

                                tmpCh.setSAC_Response_Structure(response_structure);
                                tmpCh.setLocationCode(channel.getLocationCode());
                                if (tmpCh.getLocationCode().trim().length()==0) tmpCh.setLocationCode("--");
                                
                                if (newPFXstation.getChannels()==null) newPFXstation.setChannels(new ArrayList());
                                newPFXstation.getChannels().add(tmpCh);
                            }
                        }
                    }                   
                    res.add(newPFXstation);       
                }  
            }
            return res;
        } catch (Exception ex){
            
            return null;
        }
    }
//------------------------------------------------------------------------------    
    private Station read_station_from_xml_pfxcustom_file(String netCode, String fName){
        String code, name, loc;
        float lat, lon, elev;
            
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(fName));

            doc.getDocumentElement().normalize();

            code = doc.getDocumentElement().getAttribute("CODE");
            name = doc.getDocumentElement().getAttribute("NAME");
            //loc = doc.getDocumentElement().getAttribute("--");
            lat  = Float.valueOf(doc.getDocumentElement().getAttribute("LATITUDE"));
            lon  = Float.valueOf(doc.getDocumentElement().getAttribute("LONGITUDE"));
            elev  = Float.valueOf(doc.getDocumentElement().getAttribute("ELEVATION"));
         // name  = Float.valueOf(doc.getDocumentElement().getAttribute("NAME"));

            NodeList channels_list = doc.getElementsByTagName("channel");
//
            ArrayList<_StationResponse_Channel> channel_responses = new ArrayList();
            for (int temp = 0; temp < channels_list.getLength(); temp++) {
//
                Node cha = channels_list.item(temp);
//
                if (cha.getNodeType() == Node.ELEMENT_NODE) {
                    cha.normalize();             
//
                    Element channel_element = (Element) cha;
                    channel_element.normalize();
                    String chacode = channel_element.getAttribute("ChannelCode");
                    float gain = Float.valueOf(channel_element.getAttribute("Gain"));
                    String chaloc = channel_element.getAttribute("LocationCode");
                    String chanet = channel_element.getAttribute("Network");

                    _StationResponse_Channel tmpCh = new _StationResponse_Channel(chacode);
                    tmpCh.setSAC_Response_Structure(new _SAC_ResponseStruct());
                    tmpCh.getSAC_Response_Structure().dGain = gain;

                    NodeList poli = channel_element.getElementsByTagName("poles");
                    Element element_poli = (Element)poli.item(0);
                    NodeList lista_poli = element_poli.getElementsByTagName("pole");
                    String v[];
                    
                    tmpCh.setLocationCode(channel_element.getAttribute("LocationCode"));
                    if (tmpCh.getLocationCode().trim().length()==0) tmpCh.setLocationCode("--");
                    
                    tmpCh.getSAC_Response_Structure().iNumPoles=lista_poli.getLength();
                    tmpCh.getSAC_Response_Structure().Poles = new _SAC_PZNum[ tmpCh.getSAC_Response_Structure().iNumPoles];                   
                    for (int p=0; p<lista_poli.getLength(); p++) {
                        v = lista_poli.item(p).getTextContent().split(" ");
                        
                        tmpCh.getSAC_Response_Structure().Poles[p] = new _SAC_PZNum();
                        tmpCh.getSAC_Response_Structure().Poles[p].dReal = Double.valueOf(v[0]);
                        tmpCh.getSAC_Response_Structure().Poles[p].dImag = Double.valueOf(v[1]);
                    }

                    NodeList zeri = channel_element.getElementsByTagName("zeroes");
                    Element element_zeri = (Element)zeri.item(0);
                    NodeList lista_zeri = element_zeri.getElementsByTagName("zero");
                    String z[];
                    
                    tmpCh.getSAC_Response_Structure().iNumZeroes=lista_zeri.getLength();
                    tmpCh.getSAC_Response_Structure().Zeroes = new _SAC_PZNum[ tmpCh.getSAC_Response_Structure().iNumZeroes];                   
                    for (int p=0; p<lista_zeri.getLength(); p++) {
                        z = lista_zeri.item(p).getTextContent().split(" ");
                        
                        tmpCh.getSAC_Response_Structure().Zeroes[p] = new _SAC_PZNum();
                        tmpCh.getSAC_Response_Structure().Zeroes[p].dReal = Double.valueOf(z[0]);
                        tmpCh.getSAC_Response_Structure().Zeroes[p].dImag = Double.valueOf(z[1]);
                    }
                    
                    
                    channel_responses.add(tmpCh);
//
              }
          }
                   
          //Station res = new Station(code, lat, lon, elev, netCode, loc);
          Station res = new Station(code, name, lat, lon, elev, netCode);
          res.setChannels(channel_responses);
          
          return res;
      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
          return null;
      }
    }
//-------------------------------------------------------------------------------- 
    private boolean readFDSN_StationXml_multithread(){
        try {
            
            for (int i=0; i<App.G.options.getDatasources_FDSN().size(); i++) { 
                if (App.G.options.getDatasources_FDSN().get(i).isUsed()) {
                    App.G.options.getDatasources_FDSN().get(i).setStill_trying_to_read(true);
                    
                    StationWebService service = new StationWebService();
                    service.setName(App.G.options.getDatasources_FDSN().get(i).getDescription());
                    service.setHost(App.G.options.getDatasources_FDSN().get(i).getHostname());
                    service.setPort(App.G.options.getDatasources_FDSN().get(i).getHttpport());
                    service.setNode_id(i);

                    service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent t) {
                            App.logger.info("Correctly read stations information form: " + service.name.toString() + "\n" +
                                    "Number of sites found: " + t.getSource().getValue());
                        }
                    });

                    // Avvia il service che avvia il task
                    service.start();
                }
            }
            
            return true;
        } catch (Exception ex) {
            return false;
        } 
    }
//------------------------------------------------------------------------------   
    public ArrayList ToStationArray_Geotools() {
        try {
            ArrayList out_arr = new ArrayList(); 
            String[] element; // = new String[3];
            if (this.Stations.isEmpty()) return null;
            for (int i=0; i<this.Stations.size(); i++){
                element = new String[4];
                element[0] = ((Station)this.Stations.get(i)).getCode();
                element[1] = String.valueOf(((Station)this.Stations.get(i)).getLat());
                element[2] = String.valueOf(((Station)this.Stations.get(i)).getLon());
                element[3] = String.valueOf(((Station)this.Stations.get(i)).getMax_latency_in_seconds());

                out_arr.add((String[])element); //.clone());
            }

            return out_arr;
        } catch (Exception ex) {
            return null;
        }
        
    }
//------------------------------------------------------------------------------
    private class StationWebService extends Service<Integer> {
        private String name;
        private String host;
        public int port;
        private int node_id;
        
        public final void setHost(String value) {
            host=value;
        }

        public final String getHost() {
            return host; 
        }

        /**
         * @return the name
         */
        public String getName() {
            return name; 
        }

        /**
         * @param name the name to set
         */
        public void setName(String value) {
            name = value; 
        }
        
        /**
         * @return the node_id
         */
        public int getNode_id() {
            return node_id;
        }

        /**
         * @param node_id the node_id to set
         */
        public void setNode_id(int node_id) {
            this.node_id = node_id;
        }
               
        @Override
        protected Task<Integer> createTask() {
            //final String _url = getUrl();
            return new Task<Integer>() {
                protected Integer call() throws IOException, MalformedURLException, ParserConfigurationException {
                   
                    int n_stations_found = 0;
                                       
                    try {  
                        // Network folder
                        File net_folder=new File("networks");
                        if (!net_folder.exists()){
                            net_folder.mkdir();
                        } 
//
                        FileOutputStream output_stream, stationxml_stream; 
                        
                        String station_code="", station_name="";
                        float station_lat;
                        float station_lon;
                        float station_elev;

                        String station_net;
//           
                        FDSNStationQueryParams queryParams = new FDSNStationQueryParams();
                                                                       
                        queryParams.setHost(host); 
//                        queryParams.setMinLongitude(App.G.options.get_box_minLon());
//                        queryParams.setMaxLongitude(App.G.options.get_box_maxLon());
//                        queryParams.setMinLatitude(App.G.options.get_box_minLat());
//                        queryParams.setMaxLatitude(App.G.options.get_box_maxLat());
                       
                        queryParams.appendToChannel("EH?,HH?,HN?,BH?,HG?");           
                        queryParams.setIncludeRestricted(true); 
                        
                        queryParams.setPort(port);

                        java.time.Instant instant = java.time.LocalDateTime.now().atZone(java.time.ZoneId.of("UTC")).toInstant();
                        queryParams.setEndAfter(instant);

//                        if (name.toUpperCase().contains("INGV")){
//                            queryParams.getParams().put("authoritative", "any"); 
//                        }

                        if (App.G.options.getFDSNSwsStationNetwork().trim().length()>0) {
                            queryParams.appendToNetwork(App.G.options.getFDSNSwsStationNetwork().trim());
                        }    
                        queryParams.setLevel(FDSNStationQueryParams.LEVEL_RESPONSE);
                        FDSNStationQuerier querier = new FDSNStationQuerier(queryParams);
                        querier.setConnectTimeout(60000);
                        querier.setReadTimeout(300000);
                       URI mio = querier.formURI();
                        FDSNStationXML xml = querier.getFDSNStationXML();
                        //
                        NetworkIterator nIt = xml.getNetworks();           
                        String currentNetCode;
                        Response cur_resp;
                        ResponseStage stage0;
                        PolesZeros PaZ;

                        int idStationInResponses=-1;
                        int idChannelInStation=-1;
                        //_StationResponse_Station SR;
                        _SAC_ResponseStruct response_structure;
                        _StationResponse_Channel tmpCh;
                        while (nIt.hasNext()) {
                            Network n =null;
                            try {
                                n= nIt.next();
                            } catch (Exception ex) {
                                //System.out.println("ERROR:" + ex.getMessage());
                            }
                            if (n!=null){
                                currentNetCode = n.getCode();

                                StationIterator sIt = n.getStations();
                                while (sIt.hasNext()) {
                                    edu.sc.seis.seisFile.fdsnws.stationxml.Station s = sIt.next();
                                    org.ingv.sit.datamodel.Station myStation;
 
                                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                                    //
                                    station_code = s.getCode();     
                                    station_name = s.getSite().getName();
                                    station_lat = s.getLatitude().getValue();
                                    station_lon = s.getLongitude().getValue();
                                    station_elev = s.getElevation().getValue();

                                    station_net = currentNetCode;

                                    App.G.MakeDirectory("networks/" +station_net + "/");
                                    //App.G.MakeDirectory("networks_stationxml/" +station_net + "/");

                                    output_stream = new FileOutputStream("networks/" + station_net + "/" + station_code + ".xml");
                                                                        
                                    // root elements
                                    Document doc = docBuilder.newDocument();
                                    Element rootElement = doc.createElement("station");
                                    rootElement.setAttribute("CODE", station_code);
                                    rootElement.setAttribute("NAME", station_name);
                                    rootElement.setAttribute("LATITUDE", String.valueOf(station_lat));
                                    rootElement.setAttribute("LONGITUDE", String.valueOf(station_lon));
                                    rootElement.setAttribute("ELEVATION", String.valueOf(station_elev));
                                    doc.appendChild(rootElement);                                                               
                                    //            
                                    myStation = new org.ingv.sit.datamodel.Station(station_code, station_name,station_lat, 
                                            station_lon, station_elev, station_net);

                                    myStation.setFDSN_Service_index(node_id);

                                    //------------------------------------------
                                    // Lettura dei canali...
                                    //------------------------------------------
                                    myStation.setChannels(new ArrayList<>());
                                    for (int chId=0; chId < s.getChannelList().size(); chId++){
                                        if (!myStation.getChannelsAsStrings().contains(s.getChannelList().get(chId).getChannelCode().substring(0, 2)))
                                               myStation.getChannelsAsStrings().add(s.getChannelList().get(chId).getChannelCode().substring(0, 2));
                                    }

                                    
                                    //----------------------------------------------  
                                    List<Channel> chanList = s.getChannelList();
                                    for (Channel chan : chanList) {   
                                        
                                        //------------------------------------------
                                        // Lettura delle response per canale
                                        //------------------------------------------
                                        cur_resp = chan.getResponse();

                                        // Add the channel to the station response
                                        idChannelInStation= -1; //*************************************************************************SR.FindChannel(chan.getCode());
                                        if (idChannelInStation != -1) {
                                            // THIS SHOULD NEVER HAPPEN (or maybe can happen because of different location codes)
                                            Logger.getLogger("org.ingv.pfx").log(Level.WARNING, 
                                                    "DUPLICATE CHANNEL IN STATIONXML for " + s.getCode() + " " + chan.getCode());
                                        } else {
                                            tmpCh = new _StationResponse_Channel(chan.getCode());

                                            // add xml elements
                                            Element channel = doc.createElement("channel");
                                            // add staff to root
                                            rootElement.appendChild(channel);

                                            if ((cur_resp!=null)&&(cur_resp.getResponseStageList()!=null) && !cur_resp.getResponseStageList().isEmpty()) {
                                                stage0 = (ResponseStage)(cur_resp.getResponseStageList().get(0));
                                                PaZ = (PolesZeros)stage0.getResponseItem();
                                                Unit input_units = stage0.getResponseItem().getInputUnits();
                                                Unit output_units = stage0.getResponseItem().getOutputUnits();

                                                // Compone la struttura di poli e zeri per il canale
                                                response_structure = new _SAC_ResponseStruct();
                                                response_structure.iNumPoles = PaZ.getPoleList().size();
                                                response_structure.iNumZeroes = PaZ.getZeroList().size();
                                                response_structure.Poles = new _SAC_PZNum[response_structure.iNumPoles];
                                                response_structure.Zeroes = new _SAC_PZNum[response_structure.iNumZeroes];

                                                //***************************************
                                                response_structure.dGain = (((PolesZeros)stage0.getResponseItem()).getNormalizationFactor() *
                                                        cur_resp.getInstrumentSensitivity().getSensitivityValue())/1000000000.0;
                                                //***************************************

                                                // add xml attribute
                                                channel.setAttribute("Network", chan.getNetworkCode());
                                                channel.setAttribute("ChannelCode", chan.getChannelCode());
                                                channel.setAttribute("LocationCode", chan.getLocCode());
                                                channel.setAttribute("Gain", String.valueOf(response_structure.dGain));


                                                Element _poles = doc.createElement("poles");                                           
                                                Element _pole;
                                                _SAC_PZNum tmpP; 
                                                for (int i=0; i< response_structure.iNumPoles; i++ ){
                                                    tmpP = new _SAC_PZNum();
                                                    tmpP.dReal = PaZ.getPoleList().get(i).getReal();
                                                    tmpP.dImag = PaZ.getPoleList().get(i).getImaginary();
                                                    response_structure.Poles[i]=tmpP;

                                                    _pole  = doc.createElement("pole");   
                                                    _pole.setTextContent(response_structure.Poles[i].dReal + " " + response_structure.Poles[i].dImag);      
                                                    _poles.appendChild(_pole);
                                                }

                                                channel.appendChild(_poles);

                                                Element _zeroes = doc.createElement("zeroes");
                                                Element _zero;
                                                _SAC_PZNum tmpZ; 
                                                for (int i=0; i< response_structure.iNumZeroes; i++ ){
                                                    tmpZ = new _SAC_PZNum();
                                                    tmpZ.dReal = PaZ.getZeroList().get(i).getReal();
                                                    tmpZ.dImag = PaZ.getZeroList().get(i).getImaginary();
                                                    response_structure.Zeroes[i]=tmpZ;

                                                    _zero  = doc.createElement("zero");                               
                                                    _zero.setTextContent(response_structure.Zeroes[i].dReal + " " + response_structure.Zeroes[i].dImag);
                                                    _zeroes.appendChild(_zero);
                                                }

                                                //***************************************
                                                int nZeroesplus = response_structure.need_a_zero();
                                                if (nZeroesplus>0) {
                                                    response_structure.Zeroes = response_structure.Add_n_zeroes(nZeroesplus);
                                                    for (int j=0; j< nZeroesplus; j++) {
                                                       // writer.write(response_structure.Zeroes[response_structure.Zeroes.length-nZeroesplus].dReal + " " + response_structure.Zeroes[response_structure.Zeroes.length-nZeroesplus].dImag + "\n");
                                                        _zero  = doc.createElement("zero");
                                                        _zero.setTextContent(response_structure.Zeroes[response_structure.Zeroes.length-nZeroesplus].dReal + " " + response_structure.Zeroes[response_structure.Zeroes.length-nZeroesplus].dImag);
                                                        _zeroes.appendChild(_zero);
                                                    }
                                                }

                                                channel.appendChild(_zeroes);

                                                tmpCh.setSAC_Response_Structure(response_structure);
                                                tmpCh.setLocationCode(channel.getAttribute("LocationCode"));
                                                if (tmpCh.getLocationCode().trim().length()==0) tmpCh.setLocationCode("--");

                                                // Aggiunge il canale alla stazione
                                                if (myStation.getChannels()==null) myStation.setChannels(new ArrayList());

                                                myStation.getChannels().add(tmpCh);

                                            }

                                        }
                                    }  

                                    //----------------------------------------------
                                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                    Transformer transformer = transformerFactory.newTransformer();
                                    // 
                                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                                    DOMSource source = new DOMSource(doc);
                                    StreamResult result = new StreamResult(output_stream);

                                    transformer.transform(source, result);

                                    if (!Stations.contains(myStation)) {
                                        Stations.add(myStation);
                                        n_stations_found++;
                                    } else myStation=null;
                                }

                            
                            }
                        }             
                        
                        xml.closeReader();
//                                               
                    } catch (Exception ex) {
                        // Exception handling...
                        App.logger.error(ex.getMessage());

                    } finally {
                        App.G.options.getDatasources_FDSN().get(node_id).setStill_trying_to_read(false);
                        
                    }
                    return n_stations_found;
   
                }
              
                @Override protected void succeeded() {
                    super.succeeded();
                    App.G.options.getDatasources_FDSN().get(node_id).setStill_trying_to_read(false);
                }

                @Override protected void cancelled() {
                    super.cancelled();
                    App.G.options.getDatasources_FDSN().get(node_id).setStill_trying_to_read(false);
                    App.logger.info("Reading network: Cancelled!");
                }

                @Override protected void failed() {
                    super.failed();
                    App.G.options.getDatasources_FDSN().get(node_id).setStill_trying_to_read(false);
                    App.logger.error("Reading network: Failed!");
                }
            };
        }

        /**
         * @return the port
         */
        public int getPort() {
            return port;
        }

        /**
         * @param port the port to set
         */
        public void setPort(int port) {
            this.port = port;
        }
    }   
}
