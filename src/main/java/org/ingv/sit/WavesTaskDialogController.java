
package org.ingv.sit;

import edu.iris.dmc.seedcodec.CodecException;
import edu.sc.seis.seisFile.ChannelTimeWindow;
import edu.sc.seis.seisFile.mseed.DataRecord;
import edu.sc.seis.seisFile.mseed.SeedFormatException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.TaskProgressView;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.FontAwesome.Glyph;
import org.ingv.sit.WavesTaskDialogController.wavesTaskType;
import static org.ingv.sit.WavesTaskDialogController.wavesTaskType.EW;
import static org.ingv.sit.WavesTaskDialogController.wavesTaskType.SEEDLINK;
import org.ingv.sit.datamodel.DataSource;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.datamodel.Waveform;
import org.ingv.sit.earthworm.EW_Wave_Server_Client;
import org.ingv.sit.mapping.MapHandler;
import org.ingv.sit.utils.MiniSeedToFloatArray;
import org.ingv.sit.utils.pfxDialog;

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
public class WavesTaskDialogController implements Initializable {
    
    @FXML
    private AnchorPane anchor_tasks;
    
    enum wavesTaskType {
        EW, FDSN, SEEDLINK, PLOT;
    }
    
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private TaskProgressView<WavesRecoveryTask> taskProgressView;
    private final FontAwesome fontAwesome = new FontAwesome();
    private Callback<WavesRecoveryTask, Node> factory;
    int taskCounter=0;   
    private boolean another_task_running;
       
    private Event Event_on_Map = ((MapFormController)App.G.getMainFormLoader().getController()).getEvent_on_Map();
    final ArrayList<String> Selected_Stations = ((MapFormController)App.G.getMainFormLoader().getController()).getMH().getSelected_Stations();
    final String local_QUAKEML_file=((MapFormController)App.G.getMainFormLoader().getController()).getLocal_QUAKEML_file();
    
    private List<ChannelTimeWindow> Stazioni_canali_tempi_selezionati;
    
    //--------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
        taskProgressView = new TaskProgressView<WavesRecoveryTask>();
        //
        factory = task -> {
            org.controlsfx.glyphfont.Glyph result = null;
            switch (task.getType()) {
            case EW:
                result = fontAwesome.create(Glyph.MOBILE_PHONE).size(24)
                        .color(Color.RED);
                break;
            case FDSN:
                result = fontAwesome.create(Glyph.COMPASS).size(24)
                        .color(Color.GREEN);
                break;
            case SEEDLINK:
                result = fontAwesome.create(Glyph.APPLE).size(24)
                        .color(Color.RED);
                break;
            case PLOT:
                result = fontAwesome.create(Glyph.SUPERSCRIPT).size(24)
                        .color(Color.GREEN);
                break;    
            default:
            }

            if (result != null) {
                result.setEffect(new DropShadow(8, Color.GRAY));
                result.setAlignment(Pos.CENTER);

                /*
                 * We have to make sure all glyps have the same size. Otherwise
                 * the progress cells will not be aligned properly.
                 */
                result.setPrefSize(24, 24);
            }

            return result;
        };
        
        taskProgressView.setGraphicFactory(factory);
        
        anchor_tasks.getChildren().add(taskProgressView);
        
        AnchorPane.setBottomAnchor(taskProgressView, 0.0);
        AnchorPane.setTopAnchor(taskProgressView, 0.0);
        AnchorPane.setLeftAnchor(taskProgressView, 0.0);
        AnchorPane.setRightAnchor(taskProgressView, 0.0); 
        
        loop_on_datasources();
        
        startTask("PLOT",0);
        
        tskChiusura task_chiusura=new tskChiusura();
        executorService.submit(task_chiusura);          
    } 
    

    /**
     * @param Event_on_Map the Event_on_Map to set
     */
    public void setEvent_on_Map(Event Event_on_Map) {
        this.Event_on_Map = Event_on_Map;
    }
    
    
    
    private void loop_on_datasources() {
            int i;           
            //------------------------------------------------------------------
            //Event_on_Map.ADDWAVES(Selected_Stations, PrimaryStage);
            //------------------------------------------------------------------
            try {
                LocalDateTime DATE_start=null, DATE_end=null;
                //List<ChannelTimeWindow> Stazioni_canali_tempi_selezionati = new ArrayList<>();
                Stazioni_canali_tempi_selezionati = new ArrayList<>();
                
                if (Event_on_Map.innerObjectEvent.getOrigins().get(0).getOt()!=null){
                    String  net, sta, cha, loc; //, in_date_start, in_date_end;
                    // Processa tutti i datasources in ordine per cercare le stazioni
                    // selezionate sulla mappa "SelectedStations"        
                    //------------------------------------------------------------------------------------------
                    // Prepara la lista di stazioni da richiedere partendo dalle stazioni associate nell'evento 
                    //------------------------------------------------------------------------------------------
                    //updateMessage("Building stations list...");
                    for (i=0; i < Event_on_Map.getNStations(); i++){
                        if (Event_on_Map.getStation(i).getNPhases()>0) {
                            sta = Event_on_Map.getStation(i).getCode();  
                            if ((Selected_Stations==null) || (Selected_Stations.contains(sta))) {
                                net = Event_on_Map.getStation(i).getNetwork();   
                                cha = Event_on_Map.getStation(i).getPhase(0).getPick().getCha();
                                loc = Event_on_Map.getStation(i).getLocation(cha);
//                                if ((loc.equalsIgnoreCase("--")) || (loc.trim().length()==0)) 
//                                    loc="*";
                                // START TIME for waves request = arrival time of the first phase - 5 seconds
                                if (DATE_start==null){
                                    DATE_start =Event_on_Map.getStation(i).getPhase(0).getPick().getArrivalTime().minus(java.time.Duration.ofSeconds(5)).toLocalDateTime();
                                }
                                
                                // END TIME for waves request = arrival time of the last phase +60 seconds
                                DATE_end =Event_on_Map.getStation(i).getPhase(0).getPick().getArrivalTime().plus(java.time.Duration.ofSeconds(60)).toLocalDateTime();

                                Stazioni_canali_tempi_selezionati.add(new ChannelTimeWindow(net, sta, loc, cha, 
                                        Event_on_Map.getStation(i).getPhase(0).getPick().getArrivalTime().minus(java.time.Duration.ofSeconds(5)).toInstant(), 
                                        Event_on_Map.getStation(i).getPhase(0).getPick().getArrivalTime().plus(java.time.Duration.ofSeconds(60)).toInstant()));
                            }   

                            if ((Selected_Stations!=null) && !(Selected_Stations.contains(sta))) {
                                int idSta = Event_on_Map.StationCode_to_StationId(sta);
                                if (idSta!=-1)
                                    Event_on_Map.getStations().get(idSta).Exclude_from_location();
                            }                       
                        }
                    } 
                    //----------------------------------------------------------------------
            //      Costruisce la SCNL_list a partire dalle selected stations 
                    if ((DATE_start==null) && (Event_on_Map.innerObjectEvent.getOrigins().get(0).getOt()!=null))
                        DATE_start = Event_on_Map.innerObjectEvent.getOrigins().get(0).getOt().toLocalDateTime().minus(5,ChronoUnit.SECONDS);
                    if ((DATE_end==null) && (Event_on_Map.innerObjectEvent.getOrigins().get(0).getOt()!=null))
                        DATE_end = Event_on_Map.innerObjectEvent.getOrigins().get(0).getOt().toLocalDateTime().plus(60,ChronoUnit.SECONDS);
                    List<ChannelTimeWindow> SCNL_list = Event_on_Map.BuildChannelsList(DATE_start.toInstant(ZoneOffset.UTC), DATE_end.toInstant(ZoneOffset.UTC), Selected_Stations);
            //            
                    if (((SCNL_list==null)||SCNL_list.isEmpty()) && (Selected_Stations==null || Selected_Stations.isEmpty())) return;
            //                     
                    //--------------------------------------------------------------------
                    // Merge of the two lists
                    //--------------------------------------------------------------------  
                    if (SCNL_list != null && !SCNL_list.isEmpty()){
                        for (i=0; i< SCNL_list.size(); i++){
                            if (Event_on_Map.find_for_merge(SCNL_list.get(i), Stazioni_canali_tempi_selezionati)==-1) {
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
                    App.G.MakeDirectory((App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + Event_on_Map.innerObjectEvent.getOrigins().get(0).getId()+ File.separator));

                    if (!App.G.IsDirEmpty(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + Event_on_Map.innerObjectEvent.getOrigins().get(0).getId()+ File.separator)) {
                        App.G.ClearDirectory(App.G.WAVES_BASKET_PATH + System.getProperty("user.name") + File.separator + Event_on_Map.innerObjectEvent.getOrigins().get(0).getId()+ File.separator);  
                    }
                }
            
                
                
            //------------------------------------------------------------------
            // Fino a quando ci sono datasource abilitati e tracce da cercare...
            // Elabora tutti i datasources per le waves in sequenza
            // Ordine: LOCALHOST_EVENTS, EW, SL, FDSN
            //------------------------------------------------------------------
            
            // LOCALHOST_EVENTS
            // Parte da un datasource LOCALHOST_EVENTS perchè è il più veloce (se c'è)
            // IN REALTà HA GIà FATTO TUTTO NEL PRE-READ
            
            Stazioni_canali_tempi_selezionati = Event_on_Map.Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false); 
            
            // QUAKEML ON LOCALHOST
            if (!local_QUAKEML_file.trim().isEmpty()){
                // We assume that waves are in miniseed format and are stored in
                // the same folder of the QuakeML file.
                // ex.
                //  Quakeml file: /Users/john/Desktop/Events/001/filename.xml
                //  Waves folder: /Users/john/Desktop/Events/001/mseed/
                String mseedpath = local_QUAKEML_file.substring(0, local_QUAKEML_file.lastIndexOf(App.G.SystemProperties.file_Separator)+1)+ "mseed/";
                
                //Event_on_Map.RecoverWaves_LOCAL_MINISEED(mseedpath, Stazioni_canali_tempi_selezionati);
                if (new File(mseedpath).exists()){       
                    Event_on_Map.Arrange_Waves_to_Stations(tmpBuildWavesList(mseedpath, Stazioni_canali_tempi_selezionati), DataSource.dsType.LOCALHOST_EVENTS,-1);
                    Stazioni_canali_tempi_selezionati = Event_on_Map.Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false);
                } else {
                    System.out.println("WARNING: missing miniseed directory\n" + mseedpath);
                }
            }
                        
            // EW
            for (int idEW=0; idEW<App.G.options.getDatasources_EW().size(); idEW++){
                if (App.G.options.getDatasources_EW().get(idEW).isUsed())
                    startTask("EW", idEW);
            }
                       
            // SL
//            if ((Stazioni_canali_tempi_selezionati!=null)  && (!Stazioni_canali_tempi_selezionati.isEmpty())){
//                for (int idSL=0; idSL<App.G.options.getDatasources_SL().size(); idSL++){
//                    //
//                }
//            }
            
            // FDSN           
            for (int idFDSN=0; idFDSN<App.G.options.getDatasources_FDSN().size(); idFDSN++){
                if (App.G.options.getDatasources_FDSN().get(idFDSN).isUsed())
                    startTask("FDSN", idFDSN);
            }
                                 
        } catch (Exception ex){
            App.logger.error(ex.getMessage());
        }
    }    
//------------------------------------------------------------------------------    
    public ArrayList<Waveform> tmpBuildWavesList(String basket_path, List<ChannelTimeWindow> parametri) throws IOException {
        ArrayList<List<DataRecord>> drLists; 
        try {
            String filename;
            MiniSeedToFloatArray msf= new MiniSeedToFloatArray();

            // lista dei file mseed nel basket
            File f = new File(basket_path);
                                  
            String[] miniseed_files = f.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    boolean res=name.toLowerCase().endsWith("seed");
                    boolean res2=false;
                    int id=0;
                    while ((id < parametri.size()) &&(!res2)){
                        res2 = name.contains(parametri.get(id).getStation()) & name.contains(parametri.get(id).getChannel());
                        id++;
                    }
                    res = res && res2;
                    return res ; 
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
    private void startTask(String tipo, int id) {
        
        if (Stazioni_canali_tempi_selezionati==null || Stazioni_canali_tempi_selezionati.isEmpty()) return;
        
        taskCounter++;
        WavesRecoveryTask task=null;
        switch (tipo){
                case "EW":
                    task = new WavesRecoveryTask_EW(App.G.options.getDatasources_EW().get(id).getDescription(), tipo, id);
                    break;
                case "FDSN":
                    task = new WavesRecoveryTask_FDSN(App.G.options.getDatasources_FDSN().get(id).getDescription(), tipo, id);
                    break;
                case "SEEDLINK":
                    //task = new WavesRecoveryTask_EW(tipo + " task #" + taskCounter, tipo, id);
                    break;
                case "PLOT":
                    task = new WavesPlotTask("Final plot...", tipo, id);
                    break;    
                default:
                    //task = new WavesRecoveryTask_EW(tipo + " task #" + taskCounter, tipo, id);
               
        }
       
        if (task!=null){
            // Add task to the UI
            taskProgressView.getTasks().add(task);
            // Execute task
            executorService.submit(task);
        }
        
    }   
//------------------------------------------------------------------------------    
    class WavesRecoveryTask extends Task<Void> {
        final wavesTaskType type;
        int idSource;
        

        public WavesRecoveryTask(String title, String tipo, int IDSOURCE) {
            updateTitle(title);
            idSource  = IDSOURCE;
            switch (tipo){
                case "EW":
                    type=wavesTaskType.values()[0];
                    break;
                case "FDSN":
                    type=wavesTaskType.values()[1];
                    break;
                case "SEEDLINK":
                    type=wavesTaskType.values()[2];
                    break;
                default:
                    type=wavesTaskType.values()[1];
            }   
        }
        //---------------------------------------------------------
        @Override
        protected Void call() throws Exception {
            while (another_task_running){
                Thread.sleep(500);
            }
             
            done();
            return null;
        }
        //---------------------------------------------------------
        public wavesTaskType getType() {
            return type;
        }
    }
//------------------------------------------------------------------------------    
    class WavesRecoveryTask_EW extends WavesRecoveryTask {
        
        public WavesRecoveryTask_EW(String title, String tipo, int IDSOURCE) {
            super(title, tipo, IDSOURCE);               
        }

        //---------------------------------------------------------
        @Override
        protected Void call() throws Exception {
            
            while (another_task_running){
                Thread.sleep(500);
            }

            another_task_running=true;
 

            updateMessage("Downloading waves from " + App.G.options.getDatasources_EW().get(idSource).getHostname()); 
//                        System.out.println("*****************************************************************+*+");
//                        App.logger.info("Downloading waves from datasource " + App.G.options.getDatasources_EW().get(idEW).getDescription());
//                        App.logger.info("Cerco " +Stazioni_canali_tempi_selezionati.size()+  " stazioni");
//                        for (int k=0; k< Stazioni_canali_tempi_selezionati.size(); k++){
//                            System.out.println(Stazioni_canali_tempi_selezionati.get(k).getStation());
//                        }   
//                        System.out.println("*****************************************************************+*+");
//
//                        // Download data from the Earthworm wave server #idEW

System.out.println(">>>>>>>>>>> Scarico da EW " + Stazioni_canali_tempi_selezionati.size() + " stazioni");
                        Event_on_Map.RecoverWaves_EW(idSource,  Stazioni_canali_tempi_selezionati);
//
                        Stazioni_canali_tempi_selezionati = Event_on_Map.Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false); 

       //     updateProgress(0, 0);

            done();
            
            
            another_task_running=false;
            return null;
        }
    }
    
//------------------------------------------------------------------------------    
    class WavesRecoveryTask_FDSN extends WavesRecoveryTask {
        
        public WavesRecoveryTask_FDSN(String title, String tipo, int IDSOURCE) {
            super(title, tipo, IDSOURCE);               
        }

        //---------------------------------------------------------
        @Override
        protected Void call() throws Exception {
            
            while (another_task_running){
                Thread.sleep(500);
            }

            another_task_running=true;
 
            updateMessage("Downloading waves from " + App.G.options.getDatasources_FDSN().get(idSource).getUrl()); 
System.out.println(">>>>>>>>>>> Scarico da FDSN " + Stazioni_canali_tempi_selezionati.size() + " stazioni");
            Event_on_Map.RecoverWaves_FDSN(idSource,  Stazioni_canali_tempi_selezionati);
            

            Stazioni_canali_tempi_selezionati = Event_on_Map.Ancora_da_cercare(Stazioni_canali_tempi_selezionati, false); 

            done();
                        
            another_task_running=false;
            return null;
        }
    }
//------------------------------------------------------------------------------    
    class WavesPlotTask extends WavesRecoveryTask {
        
        public WavesPlotTask(String title, String tipo, int IDSOURCE) {
            super(title, tipo, IDSOURCE);               
        }

        //---------------------------------------------------------
        @Override
        protected Void call() throws Exception {
            
            while (another_task_running){
                Thread.sleep(500);
            }

            another_task_running=true;
 
            updateMessage("Plotting when ready..."); // + title.getValue());
            
            if (!Event_on_Map.Event_has_waves()){ // NO!! Sostituire con "se ci sono stazioni della SeismicNetowrk (letta all'inizio) non trovate sui server EW"
                    pfxDialog.ShowMessage("No waves found.", "ALERT", Alert.AlertType.WARNING,null);  
            } else {
                Platform.runLater(() -> {
                    if (!Event_on_Map.isAddingStations())
                        Event_on_Map.ShowWavesWindow();
                    else {
                        Event_on_Map.setAddingStations(false);
                        App.G.WavesControllers.get(Event_on_Map.getIdController()).DisplayOnlyWaves(0);
                        App.G.WavesControllers.get(Event_on_Map.getIdController()).populate_stations_combo();
                    }
                });
            }  
                       
            done();            
            another_task_running=false;
            return null;
        }
    }   
//------------------------------------------------------------------------------    
    class tskChiusura extends Task<Void> {
        
//        public tskChiusura(String title, String tipo, int IDSOURCE) {
//            super(title, tipo, IDSOURCE);               
//        }

        //---------------------------------------------------------
        @Override
        protected Void call() throws Exception {
            
            while (another_task_running){
                Thread.sleep(1500);
            }

            another_task_running=true;
 
            try {       
                Stage stage = (Stage) anchor_tasks.getScene().getWindow();
                Platform.runLater(() -> {
                    stage.close();  
                    });
            } catch (Exception ex) {
                int k=0;
                k++;
                
            }

            done();
                        
            another_task_running=false;
            return null;
        }
    }    

    

}
    
