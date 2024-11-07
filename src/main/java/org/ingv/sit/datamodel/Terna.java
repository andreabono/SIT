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
import edu.sc.seis.seisFile.mseed.DataRecord;
import edu.sc.seis.seisFile.mseed.DataRecordIterator;
import edu.sc.seis.seisFile.mseed.SeedFormatException;
import edu.sc.seis.seisFile.sac.SacTimeSeries;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectPick;
import org.ingv.sit.App;
import org.ingv.sit.earthworm.EW_Wave_Server_Client;
import org.ingv.sit.earthworm.EW_Host;
import org.ingv.sit.utils.MiniSeedToFloatArray;


public class Terna {
    private int StationIndex;
    private String StationCode;
    private String StationName;
    private String ChannelCode; // HH, EH, BH, ecc... WITHOUT COMPONENT!!
    private String StationLocation;
    private String NetworkCode;
    private ArrayList<Waveform> Waves;
    private String waves_path;
    
    private int ID_earthworm_waveserver=-1;
    private int ID_FDSN_webservice=-1;
//    
//------------------------------------------------------------------------------ 
    public Terna(String Station_Code, String Station_Name, String Channel_Code, String Network, 
            String Location, int Station_id_in_event, 
            String event_data_path, 
            int in_ID_EW_wave_server,
            int in_ID_FDSN_webservice 
            ) {

        StationCode = Station_Code;
        StationName = Station_Name;
        ChannelCode = Channel_Code; 
        NetworkCode=Network;
        StationLocation = Location;
        StationIndex = Station_id_in_event;
        
        ID_earthworm_waveserver=in_ID_EW_wave_server;
        ID_FDSN_webservice=in_ID_FDSN_webservice;
        
        if ((ID_earthworm_waveserver!=-1)||(ID_FDSN_webservice!=-1))
            waves_path = event_data_path + File.separator + "terna" + File.separator;
        else
            waves_path = event_data_path + File.separator;
        
    }
//------------------------------------------------------------------------------    
    public boolean WavesAlreadyAvailable(){
        try{
            File fZ = new File(waves_path +  StationCode + "." + ChannelCode + "Z.mseed");
            File fN = new File(waves_path +  StationCode + "." + ChannelCode + "N.mseed");
            File fE = new File(waves_path +  StationCode + "." + ChannelCode + "E.mseed");
            
            if ((!fZ.exists()) && (!fN.exists()) && (!fE.exists()))
                return false;
            else
                return true;
        } 
        catch (Exception ex)
        {
            return false;
        }
    } 
//------------------------------------------------------------------------------
    public boolean RecoverWaves(OffsetDateTime offset_event_OT){
        /*
            offset_event_OT can be NULL for events in LOCALHOST folders
        */
        try {
            LocalDateTime event_OT;
            
            if (Waves != null) 
                Waves.clear();
            else
                Waves = new ArrayList<>();
            
            //-------------------------------------------------------------
            // SEARCHING WAVES IN LOCALHOST FILESYSTEM (SAC FILES)
            //-------------------------------------------------------------
            if ((ID_earthworm_waveserver==-1) && (ID_FDSN_webservice==-1)){
                // Datasource is LOCALHOST
                //String pt = this.
                if (!RecoverWaves_LOCALHOST_SAC())
                    return RecoverWaves_LOCALHOST_MINISEED();
            }
                       
            //-------------------------------------------------------------
            // SEARCHING WAVES ON PRE CONFIGURED WEB-SERVICES
            //-------------------------------------------------------------            
            if (offset_event_OT!=null){
                event_OT=offset_event_OT.toLocalDateTime();
                List<ChannelTimeWindow> Stazioni_canali_tempi_selezionati = new ArrayList<>();
 
                LocalDateTime DATE_start, DATE_end;
                DATE_start = event_OT.minus(5,ChronoUnit.SECONDS);
                DATE_end = event_OT.plus(100,ChronoUnit.SECONDS);

                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(NetworkCode, StationCode, StationLocation, ChannelCode + "Z", DATE_start.toInstant(ZoneOffset.UTC), DATE_end.toInstant(ZoneOffset.UTC))); 
                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(NetworkCode, StationCode, StationLocation, ChannelCode + "N", DATE_start.toInstant(ZoneOffset.UTC), DATE_end.toInstant(ZoneOffset.UTC))); 
                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(NetworkCode, StationCode, StationLocation, ChannelCode + "E", DATE_start.toInstant(ZoneOffset.UTC), DATE_end.toInstant(ZoneOffset.UTC)));                
                // 
                if (ID_earthworm_waveserver!=-1){
                    // Datasource is Earhtworm
                    App.logger.info("Downloading TERNA waves from datasource " + App.G.options.getDatasources_EW().get(ID_earthworm_waveserver).getDescription());
                    return RecoverWaves_EW(App.G.options.getDatasources_EW().get(ID_earthworm_waveserver).getHostname(), 
                                App.G.options.getDatasources_EW().get(ID_earthworm_waveserver).getPortslist(), 
                                Stazioni_canali_tempi_selezionati);
                } else if (this.ID_FDSN_webservice!=-1){
                    // Datasource is FDSN
                    return RecoverWaves_FDSN(App.G.options.getDatasources_FDSN().get(ID_FDSN_webservice).getHostname(),
                                App.G.options.getDatasources_FDSN().get(ID_FDSN_webservice).getHttpport(),
                                Stazioni_canali_tempi_selezionati);
                }
            }
               
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.WARNING, "Could not recover terna waves because no data source seems to be available.");
            return false;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
    }  
//------------------------------------------------------------------------------    
    public boolean RecoverWaves_LOCALHOST_SAC(){
        try {
            LocalDateTime absolute_starttime;
            Waveform tmpW; 
        
            if (waves_path==null) return false;
            if (waves_path.isBlank()) return false;
            
            // List files in waves_path
            File SACrepository = new File (waves_path);
            
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
            
            
            SacTimeSeries tempSACTimeSeries;
            for (File sacFile : sacfileList) {
                
                if (sacFile.exists() && sacFile.isFile()) {
                    tempSACTimeSeries =  edu.sc.seis.seisFile.sac.SacTimeSeries.read(sacFile);
                    if (tempSACTimeSeries.getHeader().getKstnm().trim().equalsIgnoreCase(StationCode)){
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
                        
                        // Prende il tempo del primo campione
                        int dayOfYear = tempSACTimeSeries.getHeader().getNzjday() ;
                        Year y = Year.of(tempSACTimeSeries.getHeader().getNzyear());
                        LocalDate ld = y.atDay(dayOfYear);

                        LocalTime lt = LocalTime.of(tempSACTimeSeries.getHeader().getNzhour(), 
                                tempSACTimeSeries.getHeader().getNzmin(), 
                                tempSACTimeSeries.getHeader().getNzsec(), 
                                tempSACTimeSeries.getHeader().getNzmsec()*1000000);
                        absolute_starttime = ld.atTime(lt);    

                        // Legge i campioni e li aggiunge come Waveform alla terna
                        if (tempSACTimeSeries.getHeader().getNpts()>0){
                            tmpW = new Waveform();
                            tmpW.setDataProvider("LOCALHOST");
                            tmpW.setChannelCode(tempSACTimeSeries.getHeader().getKcmpnm().trim());
                            tmpW.setFilename(sacFile.getAbsolutePath());
                            tmpW.setLocationCode("--");
                            tmpW.setNetworkCode( tempSACTimeSeries.getHeader().getKnetwk().trim());
                            tmpW.setStationCode(tempSACTimeSeries.getHeader().getKstnm().toUpperCase());
                            tmpW.setStartTime(absolute_starttime);
                            tmpW.setSamplingRate((float)1.0/tempSACTimeSeries.getHeader().getDelta());
                            tmpW.setnSamples(tempSACTimeSeries.getHeader().getNpts());

                            tmpW.setY(tempSACTimeSeries.getY());


                            if (this.getWaves()==null) this.setWaves(new ArrayList<Waveform>());
                            this.getWaves().add(tmpW);
                        }
                      
                    }
                                       
                } else {
                    out.println("Cannot load, "+sacFile.getPath()+" exists="+sacFile.exists()+" isFile="+sacFile.isFile());
                }
            }
            
            if (!Waves.isEmpty())
                return true;
            else
                return false;
        } catch (Exception ex) {
           Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
        
        
    }
    
    public boolean RecoverWaves_LOCALHOST_MINISEED(){
        ArrayList<List<DataRecord>> drLists; 
        try {
            if (waves_path==null) return false;
            if (waves_path.isBlank()) return false;
            
            String filename;
            MiniSeedToFloatArray msf= new MiniSeedToFloatArray();

            // lista dei file mseed nel basket
            File f = new File(waves_path);
                           
            String[] miniseed_files = f.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    boolean res=name.toLowerCase().endsWith("seed");
                    boolean res2=false;

                    res2 = name.contains(NetworkCode) & name.contains(StationCode) & name.contains(ChannelCode);
                                         
                    res = res && res2;
                    return res ; 
                }
            });
           
            if (miniseed_files.length==0) return false;
//
            int j, k;
            ArrayList<Waveform> Warr = new ArrayList<Waveform>();
            List<DataRecord> lista;
            float[] samps;
            Waveform swpW;
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy,D,HH:mm:ss.SSS");                    
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            for (int i=0; i<miniseed_files.length; i++) {
                filename = waves_path + miniseed_files[i];
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
                            Waves.add(swpW);
                        }    
                    }   
                } 
            }   
            
            if (!Waves.isEmpty())
                return true;
            else
                return false;
        } catch (Exception ex) {
           Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
        
        
    }
//------------------------------------------------------------------------------
    public boolean RecoverWaves_FDSN(String FDSNHostname, int httpport, List<ChannelTimeWindow> Selected_Stations){ 
        try {      
            if ((Selected_Stations==null)||(Selected_Stations.isEmpty())) return false;

            String res;

            res = Query_FDSN_Node(FDSNHostname,  httpport,
                    waves_path,   
                    Selected_Stations, ID_FDSN_webservice,
                    false);
            if (res.equalsIgnoreCase("OK")){
//                Waves=BuildWavesList(waves_path);               
                return true;
            } else return false;
               
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
    }   
//------------------------------------------------------------------------------
    public boolean RecoverWaves_EW(String ewHostName, ArrayList<Integer> ports_list, List<ChannelTimeWindow> Selected_Stations){
        try {        
            Logger.getLogger("org.ingv.pfx.Event ").log(Level.INFO, 
                            "Searching waves on earthworm server " + ewHostName); 
//            
            Query_EWWaveServer(ewHostName, ports_list,Selected_Stations);
// 
            return true;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.pfx ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
    }  
//------------------------------------------------------------------------------
     public void SortWaves() {
         //------------------------------------------------------------
         // Esegue il sort delle waves per componente (Ordine Z, N, E)
         //------------------------------------------------------------
         try {
//            ArrayList<Waveform> W; // = new ArrayList<Waveform>();
//            int i, j;
            
            
            if (Waves==null) return;
            if (Waves.isEmpty()) return;
            
            Waves.sort(new Comparator<Waveform>() {
                @Override
                public int compare(Waveform w1, Waveform w2) {
                    //return p1.getAge() - p2.getAge();
                    return (int)(w2.getChannelCode().charAt(2) - w1.getChannelCode().charAt(2));
                }
            });
                       
         } catch (Exception ex) {
         
         }
     }
//------------------------------------------------------------------------------
     private  String Query_EWWaveServer(String Host, ArrayList<Integer> ports_list, List<ChannelTimeWindow> parametri) {
        EW_Host EW_SERVER_terna = new EW_Host(Host, ports_list);
        EW_Wave_Server_Client wsc = null;
               
        try {          
            //String[] dati;
            int idWaveServer;
            int idStaz=-1;
            for (int i=0; i<parametri.size(); i++) {
                //dati=parametri.get(i).split(" ");
                try {
                    idStaz = StationIndex;
                    if (idStaz!=-1) {
                        idWaveServer = ID_earthworm_waveserver; //getStation(idStaz).getEarthWorm_WaveServer_Client_INDEX();
                    } else {   
                        idStaz = App.G.SeismicNet.StationCodeToStationId(parametri.get(i).getStation());
                        idWaveServer = ((Station)App.G.SeismicNet.getStations().get(idStaz)).getEarthWorm_WaveServer_Client_INDEX();
                    }
//                  
                    if (idWaveServer!=-1 ) {  
                        wsc = EW_SERVER_terna.getWave_server_client_list().get(idWaveServer);   
                                               
                        // wsc.readData("STA", "CHAN", "NET", "LOC", date_start, date_end);
                        wsc.readData(parametri.get(i).getStation(), parametri.get(i).getChannel(), parametri.get(i).getNetwork(), parametri.get(i).getLocation(), 
                                new Date(parametri.get(i).getBeginTime().toEpochMilli()),
                                new Date(parametri.get(i).getEndTime().toEpochMilli()), 
                                waves_path);

                    } else {
                        Logger.getLogger("org.ingv.pfx ").log(Level.WARNING, 
                                "Skipping data search for " + parametri.get(i).getStation() + 
                                        " " + parametri.get(i).getChannel() + 
                                        " because it is not mapped on any earthworm wave-server...");
                    }
                }catch (Exception ex) {
                    Logger.getLogger("org.ingv.pfx ").log(Level.WARNING, 
                            "NODATA for  " +  parametri.get(i).getStation() + "."+
                                        parametri.get(i).getChannel() + ": " + ex.getMessage()); 
                }                  
            }             
//            
            // Se ci sono file.mseed nella directory di output
            if (!App.G.IsDirEmpty(waves_path)) {
                setWaves(wsc.BuildWavesList(waves_path, StationCode));
                
                return "OK";
            } else return "NODATA for " + StationCode +  " on " + Host;   
        } catch (Exception ex) {
            return null;
        }
    }     
//------------------------------------------------------------------------------
     private String Query_FDSN_Node(String FDSNHostname,int httpport,  String data_path,   
            List<ChannelTimeWindow> parametri, int idDataSource,
            boolean searching_wa) {   
//    private String Query_FDSN_Node(String NodeURL, String data_path,   
//            List<ChannelTimeWindow> parametri, int ID_FDSN_webservice,
//            boolean searching_wa) {        
        try { 
            File outputFile = new File(data_path + FDSNHostname +"_data.mseed");
            FDSNDataSelectQueryParams queryParams = new FDSNDataSelectQueryParams();
  
            queryParams.setFdsnwsPath("fdsnws");
            queryParams.setHost(FDSNHostname);
            queryParams.setPort(httpport);
            
            FDSNDataSelectQuerier Q = new FDSNDataSelectQuerier(queryParams, parametri);
                        
            DataRecordIterator it = Q.getDataRecordIterator();
            try {
                if (handleFDSNResults(it, outputFile)){
                    //ArrayList<Waveform> WAVES=BuildWavesList(outputFile.getAbsolutePath()); //(data_path);
                    
                    Waves =BuildWavesList(outputFile.getAbsolutePath());
                    //if (WAVES==null || WAVES.isEmpty()) return "NODATA for " + FDSNHostname; 
                    if (Waves==null || Waves.isEmpty()) return "NODATA for " + FDSNHostname; 
          
                    //
                    return "OK";
                } else return "NODATA for " + FDSNHostname; 
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
    private ArrayList<Waveform> BuildWavesList(String filename) throws ParseException {
        
        try {
            ArrayList<List<DataRecord>> drLists; 
            
//            File dir = new File(path);
//            if (!dir.exists())  dir.mkdir();
//  
//            String filename= path + "data.mseed";

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
    
    private ArrayList<Waveform> BuildWavesList_LOCALHOST_MINISEED(String folderpath){
        try {
            return null;
        } catch (Exception ex){
            return null;
        }
}
//------------------------------------------------------------------------------
    /**
     * @return the Waves
     */
    public ArrayList<Waveform> getWaves() {
        return Waves;
    }
//------------------------------------------------------------------------------
    /**
     * @param Waves the Waves to set
     */
    public void setWaves(ArrayList<Waveform> in_Waves) {
        Waves = in_Waves;
    }
//------------------------------------------------------------------------------    

    /**
     * @return the StationCode
     */
    public String getStationCode() {
        return StationCode;
    }
//------------------------------------------------------------------------------
    /**
     * @param StationCode the StationCode to set
     */
    public void setStationCode(String in_StationCode) {
        StationCode = in_StationCode;
    }
//------------------------------------------------------------------------------
    /**
     * @return the ChannelCode
     */
    public String getChannelCode() {
        return ChannelCode;
    }
//------------------------------------------------------------------------------
    /**
     * @param ChannelCode the ChannelCode to set
     */
    public void setChannelCode(String in_ChannelCode) {
        ChannelCode = in_ChannelCode;
    }
//------------------------------------------------------------------------------    

    /**
     * @return the StationLocation
     */
    public String getStationLocation() {
        return StationLocation;
    }

    /**
     * @param StationLocation the StationLocation to set
     */
    public void setStationLocation(String in_StationLocation) {
        StationLocation = in_StationLocation;
    }

    /**
     * @return the Networkcode
     */
    public String getNetworkcode() {
        return NetworkCode;
    }

    /**
     * @param Networkcode the Networkcode to set
     */
    public void setNetworkcode(String in_Networkcode) {
        NetworkCode = in_Networkcode;
    }

    /**
     * @return the StationIndex
     */
    public int getStationIndex() {
        return StationIndex;
    }

    /**
     * @param StationIndex the StationIndex to set
     */
    public void setStationIndex(int in_StationIndex) {
        StationIndex = in_StationIndex;
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
    public void setWaves_path(String in_waves_path) {
        waves_path = in_waves_path;
    }
    
//------------------------------------------------------------------------------    

    /**
     * @return the ID_earthworm_waveserver
     */
    public int getID_earthworm_waveserver() {
        return ID_earthworm_waveserver;
    }

    /**
     * @param ID_earthworm_waveserver the ID_earthworm_waveserver to set
     */
    public void setID_earthworm_waveserver(int in_ID_earthworm_waveserver) {
        ID_earthworm_waveserver = in_ID_earthworm_waveserver;
    }

    /**
     * @return the ID_FDSN_webservice
     */
    public int getIdDataSource() {
        return ID_FDSN_webservice;
    }

    /**
     * @param idDataSource the ID_FDSN_webservice to set
     */
    public void setIdDataSource(int in_idDataSource) {
        ID_FDSN_webservice = in_idDataSource;
    }

    /**
     * @return the StationName
     */
    public String getStationName() {
        return StationName;
    }

    /**
     * @param StationName the StationName to set
     */
    public void setStationName(String StationName) {
        this.StationName = StationName;
    }
    
}
