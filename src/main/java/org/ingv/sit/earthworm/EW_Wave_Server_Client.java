package org.ingv.sit.earthworm;

import edu.iris.dmc.seedcodec.CodecException;
import edu.sc.seis.seisFile.ChannelTimeWindow;

//import edu.sc.seis.seisFile.MSeedQueryClient;
//import edu.sc.seis.seisFile.QueryParams;
import edu.sc.seis.seisFile.SeisFileException;
import edu.sc.seis.seisFile.mseed.Blockette;
import edu.sc.seis.seisFile.mseed.Blockette1000;
import edu.sc.seis.seisFile.mseed.DataRecord;
import edu.sc.seis.seisFile.mseed.SeedFormatException;
import edu.sc.seis.seisFile.waveserver.MenuItem;
import edu.sc.seis.seisFile.waveserver.WaveServer;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ingv.sit.App;
import org.ingv.sit.datamodel.Station;
import org.ingv.sit.datamodel.Waveform;
import org.ingv.sit.utils.MiniSeedToFloatArray;
import org.ingv.sit.utils.pfxDialog;

public class EW_Wave_Server_Client { //extends MSeedQueryClient{
//
    private String host = "";

    private int port = 0; //16222 for Winston

//    int recordSize = 12;

    //boolean doSteim1 = true;

    boolean doMenu = false;
    
    public List<MenuItem> menu_items;
    
    private ArrayList<List<DataRecord>> drLists; 
    private int index_in_list=-1;
    
    private String query;
    
    WaveServer reader;
//------------------------------------------------------------------------------  
    public EW_Wave_Server_Client(String in_host, int in_port, int in_recordSize, boolean in_domenu, boolean in_doSteim1, int local_id){
     //public EW_Wave_Server_Client(String[] args, int local_id) throws SeisFileException  {
         
        //super(args); 
             
        try {
            //----------------------------------
            this.host=in_host;
            this.port =in_port;
            this.doMenu = in_domenu;
            this.index_in_list = local_id;
            //----------------------------------
//            List<String> unknownArgs = params.getUnknownArgs();
//            List<String> reallyUnknownArgs = new ArrayList<String>();
//            for (int i = 0; i < unknownArgs.size(); i++) {
//                if (unknownArgs.get(i).equals("--steim1")) {
//                    doSteim1 = true;
//                } else if (unknownArgs.get(i).equals("--menu")) {
//                    doMenu = true;
//                } else if (i < unknownArgs.size() - 1) {
//                    // arg with value
//                    if (unknownArgs.get(i).equals("--recLen")) {
//                        recordSize = Integer.parseInt(unknownArgs.get(i + 1));
//                        i++;
//                    } else if (unknownArgs.get(i).equals("-h") || unknownArgs.get(i).equals("--help")) {
//                        host = unknownArgs.get(i + 1);
//                        i++;
//                    } else if (unknownArgs.get(i).equals("-p")) {
//                        port = Integer.parseInt(unknownArgs.get(i + 1));
//                        i++;
//                    } else {
//                        reallyUnknownArgs.add(unknownArgs.get(i));
//                    }
//                } else {
//                    reallyUnknownArgs.add(unknownArgs.get(i));
//                }
//            }
//            if (reallyUnknownArgs.size() != 0) {
//                String s = "";
//                for (String a : reallyUnknownArgs) {
//                    s += " "+a;
//                }
//                Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, "Unknown args: " + s);
//                Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.INFO, getHelp());
//                System.exit(-1);
//            }
//            if (params.getOutFile() == null) {
//                params.setOutFile("output.mseed");
//            }
                       
            reader = new WaveServer(host, port);
            
//            ((WaveServer)reader).setDoSteim1(doSteim1);
//            ((WaveServer)reader).setRecordSize(recordSize);
            
            reader.setDoSteim1(in_doSteim1);
            reader.setRecordSize(in_recordSize);
            
            this.setup_waveserver_per_stations();
            
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
        }  
    }
//--------------------------------------------------------------------------------
    public void readData() throws IOException, SeisFileException {       
        try {
            
//            if (!doMenu
//                && (params.getNetwork() == null || params.getStation() == null || params.getChannel() == null)) {
//                Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, BuildVersion.getDetailedVersion() + " one of scnl is null: n=" + params.getNetwork()
//                        + " s=" + params.getStation() + " l=" + params.getLocation() + " c=" + params.getChannel() + 
//                        "\n" + "LocId null is ok for scn, but needed for scnl");            
//                return;
//            }
            //
            if (doMenu)  {
                this.UpdateLatencies();
            } else {
                
                //super.readData();
   
            }
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            
            pfxDialog.ShowErrorMessage("Can't read data: \n" + ex.getMessage(), null);
            
            ex.printStackTrace();
        }   
    }
//--------------------------------------------------------------------------------    
    public String  readData(String station , String channel,  String network, 
            String location, Date begin, Date end, String output_path) throws IOException{
        
        String outputFile = output_path +station+"."+channel+".mseed";
        DataOutputStream out = null;
        try {     
            query = reader.createQuery(network, station, location, channel, begin.toInstant(), end.toInstant());
                       
            List<DataRecord> dati = reader.read(query);
            if (dati!=null){      
                ListIterator drIter = dati.listIterator();

                if (!drIter.hasNext()) {
                    App.logger.info("No data for " + query);
                    return null;
                } else {

                    if (outputFile != null) {
                        out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
                    }
                    DataRecord dr;
                    while (drIter.hasNext()) {
                        dr = (DataRecord)drIter.next();

                        dr.write(out);
                    }
                    dr=null;

                    return outputFile;
                }
            } else 
                return null;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (Exception ex) {}
        }
    } 
//------------------------------------------------------------------------------
    private boolean setup_waveserver_per_stations(){
        try {
            int idStaz;

            if (App.G==null) return false;
            if (App.G.SeismicNet==null) return false;
            
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.INFO, "Matching stations with servers...");
            
            menu_items = ((WaveServer)this.reader).getMenu();
            //
           
            for (MenuItem item : menu_items) { 
                idStaz = App.G.SeismicNet.StationCodeToStationId(item.getStation());
                if (idStaz > -1) {
                    ((Station)App.G.SeismicNet.getStations().get(idStaz)).setEarthWorm_WaveServer_Client_INDEX(this.index_in_list);
                }            
            }
                       
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.INFO, "Done!!");
            return true;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
    }
//------------------------------------------------------------------------------    
    public boolean UpdateLatencies(){
        try {
            Date now = new Date();
            String last_staz="";
            long last_latency=-9;
            int idStaz=-1;
//
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.INFO, "Getting menu...");
            menu_items = ((WaveServer)this.reader).getMenu();  
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.INFO, "Done!!");
//
            int nNotMapped=0;
            long latency;
            for (MenuItem item : menu_items) {   
               
                idStaz = App.G.SeismicNet.StationCodeToStationId(item.getStation());     
                if (idStaz > -1) {
                    //latency = (now.getTime() - item.getEndDate()   .getTime()) / 1000;  
                    latency =  Instant.now().getEpochSecond() - item.getEndDate().getEpochSecond();
                    if (!item.getStation().equalsIgnoreCase(last_staz)){
                        last_staz = item.getStation();
                        last_latency = latency;                       
                    } else {
                        if (latency > last_latency) {
                            last_latency = latency;
                            //((Station)App.G.SeismicNet.getStations().get(idStaz)).setMax_latency_in_seconds(last_latency);
                        }
                    }
                    ((Station)App.G.SeismicNet.getStations().get(idStaz)).setMax_latency_in_seconds(last_latency);
                } else {
//                    System.out.println(">>>>>>>>>>>>>>>> " + item.getStation()  + " " + item.getChannel() + " is in Earthworm, but not in my Seismic Network");
//                    nNotMapped+=1;
                }    
            }
//            
            return true;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.WARNING, "get menu FAILED!! Latencies are unreliable!! Check for EW server connectivity.");
            //
            //pfxDialog.ShowErrorMessage("get menu FAILED: Station latencies are unreliable!! \nCheck for EW server connectivity.");
            return false;
        }
    }
//------------------------------------------------------------------------------    
//    public String getHelp() {
//        //return "";
//        return "java "
//                +  EW_Wave_Server_Client.class.getName()
//                + " "+QueryParams.getStandardHelpOptions()+"[-h host][-p port][--menu][--steim1][--recLen len(8-12)]";
//    }
//------------------------------------------------------------------------------
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }
//------------------------------------------------------------------------------
    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }
//------------------------------------------------------------------------------       
    public ArrayList<Waveform> BuildWavesList(String basket_path, String StationCode) throws IOException {
        
        try {
            String filename;
            MiniSeedToFloatArray msf= new MiniSeedToFloatArray();

            // lista dei file mseed nel basket
            File f = new File(basket_path);
            
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File f, String name) {
                    return name.startsWith(StationCode);
                }
            };
                       
            String[] miniseed_files = f.list(filter);
           
            if (miniseed_files.length==0) return null;
//
            int j, k;
            ArrayList<Waveform> Warr = new ArrayList<Waveform>();
            List<DataRecord> lista;
            float[] samps;
            Waveform swpW;
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy,D,HH:mm:ss.SSS");                    
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            for (int i=0; i<miniseed_files.length; i++) {
                filename = basket_path + miniseed_files[i];
                drLists = msf.readseedfile(filename, true); 
                if (drLists != null) {   
                    for (k=0; k<drLists.size(); k++) {
                        swpW = new Waveform();
                        swpW.setDataProvider("EW");
                        lista = drLists.get(k);
                        swpW.setStationCode(lista.get(0).getHeader().getStationIdentifier());
                        swpW.setChannelCode(lista.get(0).getHeader().getChannelIdentifier());
                        
                        //swpW.setSamplingRate(lista.get(0).getHeader().getSampleRate());                       
                        swpW.setSamplingRate(Math.abs(lista.get(0).getHeader().getSampleRateFactor()/lista.get(0).getHeader().getSampleRateMultiplier()));
                        
                        swpW.setNetworkCode(lista.get(0).getHeader().getNetworkCode());
                        swpW.setLocationCode(lista.get(0).getHeader().getLocationIdentifier());
                        if (swpW.getLocationCode().trim().length()==0) swpW.setLocationCode("--");
                        try { 
                            //swpW.setStartTime(formatter.parse(lista.get(0).getHeader().getStartTime()));
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy,D,HH:mm:ss.SSSS");
                            swpW.setStartTime(LocalDateTime.parse(lista.get(0).getHeader().getStartTime(), format));
                            //swpW.setEndTime(LocalDateTime.parse(lista.get(lista.size()-1).getHeader().getEndTime(), format));
                        } catch (Exception ex) {
                            Logger.getLogger(EW_Wave_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
                        }                     
//
                        samps = msf.extract(lista);
                        if (samps != null) {
                            swpW.setY(samps);
                            swpW.setnSamples(samps.length);
                            swpW.setFilename(filename);
                            Warr.add(swpW);
                        }    
                    }   
                } 
            }                   
//            
            return Warr;
//            
            } catch (CodecException ex) {
                Logger.getLogger(EW_Wave_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (SeedFormatException ex) {
                Logger.getLogger(EW_Wave_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }   
    
    
    public ArrayList<Waveform> BuildWavesList(String basket_path, List<ChannelTimeWindow> parametri) throws IOException {
        
        try {
            String filename;
            MiniSeedToFloatArray msf= new MiniSeedToFloatArray();

            // lista dei file mseed nel basket
            File f = new File(basket_path);
                                  
            String[] miniseed_files = f.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    boolean res=name.toLowerCase().endsWith(".mseed");
                    boolean res2=false;
                    int id=0;
                    while ((id < parametri.size()) &&(!res2)){
                        res2 = name.contains(parametri.get(id).getStation()) & name.contains(parametri.get(id).getChannel());
                        id++;
                    }
                    res = res && res2;
                    return res ; //name.toLowerCase().endsWith(".mseed");
                    //return name.toLowerCase().endsWith(".mseed");
                }
            });
           
            if (miniseed_files.length==0) return null;
//
            int j, k;
            ArrayList<Waveform> Warr = new ArrayList<Waveform>();
            List<DataRecord> lista;
            float[] samps;
            Waveform swpW;
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy,D,HH:mm:ss.SSS");                    
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            for (int i=0; i<miniseed_files.length; i++) {
                filename = basket_path + miniseed_files[i];
                drLists = msf.readseedfile(filename, true);                         
                if (drLists != null) {   
                    for (k=0; k<drLists.size(); k++) {
                        swpW = new Waveform();
                        swpW.setDataProvider("EW");
                        lista = drLists.get(k);
                        swpW.setStationCode(lista.get(0).getHeader().getStationIdentifier());
                        swpW.setChannelCode(lista.get(0).getHeader().getChannelIdentifier());
                        
                        //swpW.setSamplingRate(lista.get(0).getHeader().getSampleRate());
                        swpW.setSamplingRate(Math.abs(lista.get(0).getHeader().getSampleRateFactor()/lista.get(0).getHeader().getSampleRateMultiplier()));
                        
                        swpW.setNetworkCode(lista.get(0).getHeader().getNetworkCode());
                        swpW.setLocationCode(lista.get(0).getHeader().getLocationIdentifier());
                        if (swpW.getLocationCode().trim().length()==0) swpW.setLocationCode("--");
                        try { 
                            //swpW.setStartTime(formatter.parse(lista.get(0).getHeader().getStartTime()));
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy,D,HH:mm:ss.SSSS");
                            swpW.setStartTime(LocalDateTime.parse(lista.get(0).getHeader().getStartTime(), format));
                            //swpW.setEndTime(LocalDateTime.parse(lista.get(lista.size()-1).getHeader().getEndTime(), format));
                        } catch (Exception ex) {
                            Logger.getLogger(EW_Wave_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
                        }                     
//
                        samps = msf.extract(lista);
                        if (samps != null) {
                            swpW.setY(samps);
                            swpW.setnSamples(samps.length);
                            swpW.setFilename(filename);
                            Warr.add(swpW);
                        }    
                    }   
                } 
            }                   
//            
            return Warr;
//            
            } catch (CodecException ex) {
                Logger.getLogger(EW_Wave_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (SeedFormatException ex) {
                Logger.getLogger(EW_Wave_Server_Client.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }   
//------------------------------------------------------------------------------           
    /**
     * @return the index_in_list
     */
    public int getIndex_in_list() {
        return index_in_list;
    }
//------------------------------------------------------------------------------       
    /**
     * @param index_in_list the index_in_list to set
     */
    public void setIndex_in_list(int index_in_list) {
        this.index_in_list = index_in_list;
    }
//------------------------------------------------------------------------------       

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }
}
