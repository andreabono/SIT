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
package org.ingv.sit;

import edu.sc.seis.seisFile.fdsnws.quakeml.EventIterator;

import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.ingv.sit.utils.ResidualSelector;
import javafx.util.StringConverter;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.utils.sitDialog;
import org.ingv.sit.tablemodels.LocationTreeItems_Lev2_new;
import org.ingv.sit.tablemodels.Phases_List_items;
import org.ingv.sit.tablemodels.Towns_List_items;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import org.ingv.dante.api.GetApi;
import org.ingv.dante.model.GetEvent200Response;
import org.ingv.dante.model.GetEventsGroup200Response;

import org.ingv.dante.model.GetLocalspace200Response;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200ResponseDataInner;
import org.ingv.dante.model.GetTypeOrigin200Response;

import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectEvent;
import org.ingv.dante.model.ObjectMagnitude;

import org.ingv.dante.model.ObjectOrigin;

import org.ingv.dante.model.ObjectMagnitudesOriginsEventsAndEventsGroup;

import org.ingv.dante.model.ObjectStatus;
import org.ingv.dante.model.ObjectTableLocalspace;
import org.ingv.dante.model.ObjectTableTypeOrigin;
import org.ingv.sit.datamodel.DataSource.dsType;
import org.ingv.sit.fdsn.FDSN_ws_reader;
import org.ingv.sit.mapping.MapHandler;
import org.ingv.sit.quakeml.QuakeMLWriter;
import org.ingv.sit.tablemodels.Localspaces_List_items;
import org.ingv.sit.tablemodels.Versions_List_items;
import static org.ingv.sit.datamodel.DataSource.dsType.FDSN;
import static org.ingv.sit.datamodel.DataSource.dsType.INGV;
import org.ingv.sit.tablemodels.FDSN_Events_List_items;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.ToggleSwitch;
import org.eclipse.fx.ui.controls.filesystem.ResourceItem;
import org.eclipse.fx.ui.controls.filesystem.RootDirItem;

import javafx.beans.Observable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.shape.Circle;

import org.eclipse.fx.ui.controls.filesystem.DirectoryTreeView;
import org.eclipse.fx.ui.controls.filesystem.DirectoryView;
import org.eclipse.fx.ui.controls.filesystem.IconSize;
import org.ingv.dante.ApiResponse;

import org.ingv.sit.security.KeyCloakClient;
import org.ingv.sit.security.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class MapFormController implements Initializable {

    @FXML
    private AnchorPane main_anchor_pane;
    @FXML
    private MenuItem mnuFile_Export;
    @FXML
    private MenuItem mnuFile_Exit;
    @FXML
    private MenuItem mnuTools_Settings;
    @FXML
    private MenuItem mnuSwitchLayout;
    @FXML
    private MenuItem mnuHelp_About;
    @FXML
    private SplitPane split_map_updown;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnExport;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnRefresh;
    @FXML
    private DatePicker calendar;
    @FXML
    private Button btnInfo;
    @FXML
    public TreeTableView<ObjectMagnitudesOriginsEventsAndEventsGroup> treetableview_Events;
    @FXML
    private TreeTableColumn<String, String> ttc_id;
    @FXML
    private AnchorPane anchor_top_right;
    @FXML
    private AnchorPane anchor_summary;
    @FXML
    private SplitPane split_OriginInfo;
    @FXML
    private TableView<?> table_towns;
    @FXML
    private Label lblArea;
    @FXML
    private Label lblOT;
    @FXML
    private Label lblLon_L;
    @FXML
    private Label lblLat_L;
    @FXML
    private Label lblDep;
    @FXML
    private Label lblDep_L;
    @FXML
    private Label lblLat;
    @FXML
    private Label lblLon;
    @FXML
    private Label lblErrH;
    @FXML
    private Label lblErrZ;
    @FXML
    private Button btnOriginDetail;
    @FXML
    private TabPane tabMags;
    @FXML
    private SplitPane prova_split;
    
    @FXML
    private AnchorPane anchor_lower_right;
    @FXML
    private TableView<?> table_phases;
    @FXML
    private ScatterChart<?, ?> resChart;
    @FXML
    private ImageView imgDanteStatus;
    @FXML
    private Label lblDanteStatus;
    @FXML
    private Button btnWaves;

    private ResidualSelector myResidualSelector;
    
    boolean initializing=true;
    private String TodayString;
    private String selected_group_id; 
    private Event Event_on_Map; 
    //private ArrayList Selected_Stations;
    private PollingService polling_service;
    private Stage PrimaryStage;
    private ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> Locations_level1;
    private ArrayList<FDSN_Events_List_items> Locations_FDSN;
    //private ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> Locations_for_search_table;
    private FXMLLoader fXMLLoader = new FXMLLoader();
    private int id_current_used_ewhosts = 0;
    private Boolean finito_waves;  
//   
    private MapHandler MH;    
    
    @FXML
    private TextField txtMagMax;
    @FXML
    private TextField txtLatMin;
    @FXML
    private TextField txtCenterLat;
    @FXML
    private TextField txtMagMin;
    @FXML
    private TextField txtLatMax;
    @FXML
    private TextField txtCenterLon;
    @FXML
    private TextField txtLonMin;
    @FXML
    private TextField txtLonMax;
    @FXML
    private TextField txtRadiusMin;
    @FXML
    private TextField txtDepMin;
    @FXML
    private TextField txtDepMax;
    
    @FXML
    private TableView<ObjectMagnitudesOriginsEventsAndEventsGroup> tblOrigins;
    @FXML
    private TableView<?> tblSearch_Versions;
    @FXML
    private TextField txtRadiusMax;
    @FXML
    private Button btnSelectStations;
    @FXML
    private Button btnMapResetBounds;
    @FXML
    private ContextMenu mnuRight;
    @FXML
    private MenuItem mnuRight_SetPreferred;
    @FXML
    private AnchorPane anchor_bottom_left_ext;
    @FXML
    private Button btnMapZoomIn;
    @FXML
    private Button btnZoonOnIpo;
    @FXML
    private Button btnMapZoomOut;
    
    @FXML
    private Rating rating1;
    @FXML
    private Rating rating2;
    @FXML
    private SplitPane split_top_right_left;
    @FXML
    private AnchorPane anchor_top_right_int;
    
    private boolean limit_search=false;
    @FXML
    private CheckBox ckLimitSearch;
    @FXML
    private ComboBox<String> cmbSearchRoute;
    
    private String search_route;
    @FXML
    private TitledPane titled_pane_settings;
    
    private long selectedEventId=-1;
    
    private String polling_on = "GROUPS";
    @FXML
    private MenuItem mnuRight_ShowMembers;
    
    private int livello_click=1;
    TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> selected_treetable_item=null; 
    @FXML
    private TabPane tabPane_environments;
    @FXML
    private Button btnSaveSearchSettings;
    @FXML
    private Button btnApplySearchSettings1;
    @FXML
    private Label lblIDs;
    @FXML
    private Label lblQ1;
    @FXML
    private Label lblQ2;
    @FXML
    private Tab tabCARAVEL;
    @FXML
    private Tab tabFDSN;
    @FXML
    private TabPane tabs_available_FDSN;
    @FXML
    private TabPane tabMainEventsSources;
    @FXML
    private TabPane tabCARAVELGroupsAndOrigins;
    
    public boolean AnyDatasourcesAvailable;
    @FXML
    private Label lblErrLAT;
    @FXML
    private Label lblErrLON;
    @FXML
    private Label lblErrLON_plusminus;
    @FXML
    private Button btnRapaintMap;
    @FXML
    private TextField txtFilterLocalspace;
    @FXML
    private TextField txtFilterRegion;
    @FXML
    private Pane paneFilters;
    @FXML
    private ToggleSwitch tglCaravelGropusFilter;
    @FXML
    private ToggleSwitch tglCaravelLastToFirst;
    
    
    private String CaravelSearchOrder="origin_ot-desc";
    @FXML
    private Label lblLastPoll;    
    @FXML
    private AnchorPane anchor_localfolders;
    @FXML
    private Button btnPreRead;
    
    
    private String local_SAC_folder="";
    private String local_QUAKEML_file="";
    
    @FXML
    private Tab tabLOCALHOST;
    @FXML
    private TabPane tabs_available_LOCALHOST;
    @FXML
    private CheckMenuItem mnuView_ShowMagsByAzimuthChart;
    @FXML
    private Button btnOriginReport;
    @FXML
    private Button btnLogin;
    @FXML
    private ImageView imgAlias;
    
    private ApplicationContext Login_ApplicationContext;
    @FXML
    private Label lblLogin;
    
    private KeyCloakClient myKeyCloakClient;
    private String KeyCloakConfigURL; 
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnPreReadQML;
    
       
//    private void ReadKeyCloakConfigURL(){
////        UserApi api = new UserApi();
////        KeyCloakConfigURL = api.getCustomBaseUrl();
////        
////        int k=0;
////        k++;
//    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializing=true;  
        lblLastPoll.setVisible(false);
               
        AnchorPane.setTopAnchor(treetableview_Events, 0.0);
        treetableview_Events.setViewOrder(-1);
        
        //titled_pane_settings.setCollapsible(true);
        titled_pane_settings.setExpanded(false);
        titled_pane_settings.toBack();
        titled_pane_settings.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (!isNowExpanded) {
                //tblOrigins.toFront();
                titled_pane_settings.toBack();
            } else titled_pane_settings.toFront();
        });
         
        if (App.G.options.isCaravelAvailable()){
            imgDanteStatus.setVisible(true);
            lblDanteStatus.setVisible(true);
            if (!UpdateCaravelWebServicesStatus()){
                // Dante web services are not available
                sitDialog.ShowErrorMessage("Attention: Dante or Apollo web services are not responding!!", PrimaryStage);    
            }          
        } else {
            imgDanteStatus.setVisible(false);
            lblDanteStatus.setVisible(false);
        }

      //myResidualSelector=new ResidualSelector();      
//           
        if ((App.G.options.get_startup_date()!=null) && (App.G.options.get_startup_date().trim().length()<10)) {
            Date Today=new Date();
            TodayString = (new SimpleDateFormat("yyyy-MM-dd")).format(Today);
        } else TodayString = App.G.options.get_startup_date();
        
        selected_group_id="";
//      
        calendar.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });        
       
        calendar.setValue(LocalDate.now());
                
        rating1.setMax(4);
        rating2.setMax(4);
        rating1.setMouseTransparent(true);
        rating2.setMouseTransparent(true);
     
        tabCARAVELGroupsAndOrigins.selectionModelProperty().getValue().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) {    
            
                if ((oldValue.intValue()==(int)1) && (newValue.intValue()==(int)0)){
                    polling_on = "GROUPS";
                } else { 
                    polling_on = "ORIGINS";
                }
                btnRefresh.fire();
            }
        });
        //-----------------------------------------------------------        
        tabs_available_FDSN.selectionModelProperty().getValue().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {    
                    if (!initializing)
                        SearchAndShowLocations_FDSN((int)newValue);
                }
        });
        //-----------------------------------------------------------        
        tabMainEventsSources.selectionModelProperty().getValue().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {    
                    if (!initializing){
                        if ((oldValue.intValue()==(int)0) && (newValue.intValue()==(int)1)){
                            if (!tabs_available_FDSN.getSelectionModel().isEmpty()) {
                                tabs_available_FDSN.getSelectionModel().select(0);
                                SearchAndShowLocations_FDSN(0);
                            }
                        } else if ((oldValue.intValue()==(int)1) && (newValue.intValue()==(int)0)){ 
                            SearchAndShowLocations_CARAVEL();
                        }
                    }
                }
        });
        //-----------------------------------------------------------        
        tglCaravelGropusFilter.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                   AnchorPane.setTopAnchor(treetableview_Events, 40.0);
                } else {
                   AnchorPane.setTopAnchor(treetableview_Events, 0.0);
                }      
            }       
        });
        //-----------------------------------------------------------
        tglCaravelLastToFirst.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                    if (new_val) {
                       CaravelSearchOrder = "origin_ot-desc";
                    } else {
                        CaravelSearchOrder = "origin_ot-asc";
                    }      
                    btnRefresh.fire();
                }    
        });
        //----------------------------------------------------------------------
           
        //ReadKeyCloakConfigURL();
        btnLogout.setVisible(false);
        
         // Creazione di un cerchio per mascherare l'immagine
        Circle clip = new Circle();
        clip.setCenterX(16); // Imposta il centro X del cerchio
        clip.setCenterY(16); // Imposta il centro Y del cerchio
        clip.setRadius(16); // Imposta il raggio del cerchio

        // Applica la maschera circolare all'ImageView
        imgAlias.setClip(clip);
        
        btnPreReadQML.setDisable(true);
        KeyCloakConfigURL=App.G.options.get_KeyCloak_configurl();
    }
//------------------------------------------------------------------------------    
    public void FinalizeStartup(){
        try {
            prova_split.setDividerPosition(0, 0.6);
            split_map_updown.setDividerPosition(0, 0.68);
                      
            if (App.G.options.getDatasources_FDSN()!=null && !App.G.options.getDatasources_FDSN().isEmpty()){
                int nFDSNused=0;
                for (int i=0; i < App.G.options.getDatasources_FDSN().size(); i++){
                    if (App.G.options.getDatasources_FDSN().get(i).isUsed()){
                        nFDSNused++;
                        AddFDSNEventsTab(i, App.G.options.getDatasources_FDSN().get(i).getDescription());
                    }
                }
                if (nFDSNused==0){
                    // Removes the "FDSN" tab 
                    for (int k=0; k<this.tabMainEventsSources.getTabs().size(); k++){
                        if (tabMainEventsSources.getTabs().get(k).getText().equalsIgnoreCase("FDSN"))
                            tabMainEventsSources.getTabs().remove(k);     
                    }
                }
            } else {
                // Removes the "FDSN" tab 
                for (int k=0; k<this.tabMainEventsSources.getTabs().size(); k++){
                    if (tabMainEventsSources.getTabs().get(k).getText().equalsIgnoreCase("FDSN"))
                        tabMainEventsSources.getTabs().remove(k);     
                }
            }     
            
            if (App.G.options.getDatasources_CARAVEL()!=null && !App.G.options.getDatasources_CARAVEL().isEmpty()){
                //for (int i=0; i < App.G.options.getDatasources_CARAVEL().size(); i++){
                    if (App.G.options.getDatasources_CARAVEL().get(0).isUsed()){
                        tabCARAVELGroupsAndOrigins.setVisible(true);
                    } else {
                        // Removes the "CARAVEL" tab 
                        for (int k=0; k<this.tabMainEventsSources.getTabs().size(); k++){
                            if (tabMainEventsSources.getTabs().get(k).getText().equalsIgnoreCase("CARAVEL"))
                                tabMainEventsSources.getTabs().remove(k);     
                        }
                    }
                //}
            } else {
                // Removes the "CARAVEL" tab 
                for (int k=0; k<this.tabMainEventsSources.getTabs().size(); k++){
                    if (tabMainEventsSources.getTabs().get(k).getText().equalsIgnoreCase("CARAVEL"))
                        tabMainEventsSources.getTabs().remove(k);     
                }
            }
            
            if (App.G.options.getDatasources_LOCALHOST()!=null && !App.G.options.getDatasources_LOCALHOST().isEmpty()){
                int nLOCALHOSTused=0;
                for (int i=0; i < App.G.options.getDatasources_LOCALHOST().size(); i++){
                    if (App.G.options.getDatasources_LOCALHOST().get(i).isUsed()){
                        nLOCALHOSTused++;
                        AddLOCALHOSTEventsTab(i, App.G.options.getDatasources_LOCALHOST().get(i).getDescription()); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    }
                }
                if (nLOCALHOSTused==0){
                    // Removes the "FDSN" tab 
                    for (int k=0; k<this.tabMainEventsSources.getTabs().size(); k++){
                        if (tabMainEventsSources.getTabs().get(k).getText().equalsIgnoreCase("LOCAL FOLDERS"))
                            tabMainEventsSources.getTabs().remove(k);     
                    }
                }
            } else {
                // Removes the "LOCAL" tab 
                for (int k=0; k<this.tabMainEventsSources.getTabs().size(); k++){
                    if (tabMainEventsSources.getTabs().get(k).getText().equalsIgnoreCase("LOCAL FOLDERS"))
                        tabMainEventsSources.getTabs().remove(k);     
                }
            }
            
            if (tabMainEventsSources.getTabs()==null || tabMainEventsSources.getTabs().isEmpty()) {
                AnyDatasourcesAvailable=false;
                if (sitDialog.ShowConfirmationMessage("Missing events sources","No FDSN or CARAVEL datasources available or used.\nCheck files: \ndatasources_CARAVEL.xml\ndatasources_FDSN.xml", PrimaryStage)== ButtonType.OK){
                    ReleaseResources();
                    PrimaryStage.close();
                } 
                
                
            } else AnyDatasourcesAvailable=true;
            //------------------------------------------------------------------
            // TREE TABLE PROPERTIES:
            //------------------------------------------------------------------
            try {
                this.treetableview_Events.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup>>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup>> observable,
                            TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> old_val, TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> new_val) {
                        
                        UpdateEventIterface_CARAVEL(new_val);
                    }               
                });
//                
                treetableview_Events.getSelectionModel().selectLast(); 
                treetableview_Events.scrollTo(treetableview_Events.getSelectionModel().getSelectedIndex());
//                               
                tblOrigins.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ObjectMagnitudesOriginsEventsAndEventsGroup>() {
                    @Override
                    public void changed(ObservableValue<? extends ObjectMagnitudesOriginsEventsAndEventsGroup> observable, ObjectMagnitudesOriginsEventsAndEventsGroup old_val, ObjectMagnitudesOriginsEventsAndEventsGroup new_val) {
                        //      
                        ReadSelectedEvent_CARAVEL(new_val);
                       
                    }               
                });
                //------------------------
            } catch (Exception ex){
                ex.printStackTrace();
            }
                      
            //------------------------------------------------------------------
            
            // Map display
            drawMap(); 
            //           

            //------------------------------------------------------------------------
            // MESSAGING QUEUE CONNECTION (WHEN AVAILABLE)
            //if (App.G.options.isMessagingActive()) ConnectToRabbit();
            //------------------------------------------------------------------------      
                         
            myResidualSelector = createResidualSelector(resChart);           
            //                      
            if (App.G.options.isWebServicePollingActive()){
                StartPolling();
            }
            
            // 20220614
            populate_localspaces_list(search_localspaces());
            populate_versions_list(search_versions());
            
            populate_routes_combo();
            
            initializing=false;   
            
            tabMags.getScene().setUserData(this);   // This is useful in magnitudes display and refresh
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        } finally {
            //this.initializing=false;   
        }
        
    }   
    
    
    public void UpdateEventIterface_CARAVEL(TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> new_value){
        if ((new_value==null) && (selected_treetable_item!=null))
            new_value = selected_treetable_item;
        try {
            if (!initializing && new_value!=null){
                selected_treetable_item = new_value; 
                CARAVEL_UpdateMainInterface_with_treetable(new_value);
                //if ((treetableview_Events.getSelectionModel().getSelectedItem()!=null) && (App.G.options.isBulletinMode())){
                if (treetableview_Events.getSelectionModel().getSelectedItem()!=null) {
                    treetableview_Events.getSelectionModel().getSelectedItem().setExpanded(true);
                    if (treetableview_Events.getSelectionModel().getSelectedItem().getChildren()!=null){
                        for (int k=0; k<treetableview_Events.getSelectionModel().getSelectedItem().getChildren().size(); k++){
                            treetableview_Events.getSelectionModel().getSelectedItem().getChildren().get(k).setExpanded(true);
                        }
                    }
                }
            } 
        } catch (Exception ex){
        }
    }
//------------------------------------------------------------------------------        
    @FXML
    private void btnPreRead_click(ActionEvent event) {
        TS(null, null, local_SAC_folder, null);
    }
//------------------------------------------------------------------------------    
    private void populate_routes_combo(){
        cmbSearchRoute.getItems().add("/all");
        cmbSearchRoute.getItems().add("/events");
        cmbSearchRoute.getItems().add("/events-group");
        cmbSearchRoute.getItems().add("/magnitudes");
        cmbSearchRoute.getItems().add("/origins");  
        
        cmbSearchRoute.setPromptText("/magnitudes");
        search_route = "/magnitudes";
    }
//------------------------------------------------------------------------------        
    private void ShowQualityRatings(String quality_indexes){
        if (quality_indexes==null) return;
        
        if (quality_indexes.trim().length()>0) {
            lblQ1.setVisible(true);
            lblQ1.setText(quality_indexes.substring(0,1));            
           
            switch (quality_indexes.substring(0,1)){
                case "A":
                   rating1.setRating(4);
                   
                    break;
                case "B":
                    rating1.setRating(3);
                    
                    break;
                case "C":
                    rating1.setRating(2);
                    
                    break;
                case "D":
                    rating1.setRating(1);
                   
                    break;
                default:
                    rating1.setVisible(false);
                    break;
            }
        }
        if (quality_indexes.trim().length()>1) {
            lblQ2.setText(quality_indexes.substring(1));
            
            lblQ2.setVisible(true);
            switch (quality_indexes.substring(1)){
                case "A":
                   
                   rating2.setRating(4);
                  
                    break;
                case "B":
                    rating2.setRating(3);
                    
                    break;
                case "C":
                    rating2.setRating(2);
                    
                    break;
                case "D":
                    rating2.setRating(1);
                    
                    
                    break;   
                default:
                    rating1.setVisible(false);
                    break;
            }
        }
    }
//------------------------------------------------------------------------------    
    private void SelectRow(int id){
        try {
            MultipleSelectionModel msm = treetableview_Events.getSelectionModel();
                        
            msm.select(treetableview_Events.getRoot().getChildren().get(id));   
            
            selectedEventId = treetableview_Events.getRoot().getChildren().get(id).getValue().getId();

            if (treetableview_Events.getFocusModel()!=null){
                
                treetableview_Events.getFocusModel().focus(id);
            }
         
        } catch (Exception ex){
System.out.println("************************************************");
            System.out.println(ex.getMessage());
        }
    }
//------------------------------------------------------------------------------    
    @FXML
    private void mnuFile_Export_Click(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("QuakeML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
    
        //Show the save file dialog       
        File file = fileChooser.showSaveDialog(this.PrimaryStage);
 
        if (file != null) {
                //saveTextToFile(sampleText, file);
                QuakeMLWriter w = new QuakeMLWriter();
//        
                w.setSource_event(getEvent_on_Map());
                w.saveEventDataToQMLFile_1_2(file);
        }
        extFilter=null;
        fileChooser=null;
    }
//------------------------------------------------------------------------------
    @FXML
    private void mnuFile_Exit_Click(ActionEvent event) {
        btnExit.fire();
    }
//------------------------------------------------------------------------------
    @FXML
    private void mnuTools_Settings_Click(ActionEvent event) {
        try {         
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("Settings.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
      
            Stage stage = new Stage();
                        
            stage.setScene(new Scene(root1));  
            stage.setTitle("I.N.G.V. SIT - Settings dialog");
            stage.initModality(Modality.APPLICATION_MODAL);
                    
            stage.setMaximized(false);
            stage.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
        }
    }
//------------------------------------------------------------------------------
    @FXML
    private void mnuSwitchLayout_Click(ActionEvent event) {
        if ((anchor_bottom_left_ext.getChildren()==null) || (anchor_bottom_left_ext.getChildren().isEmpty())) return;
        Node node_bottom_left = anchor_bottom_left_ext.getChildren().get(0);
        Node Appo=node_bottom_left;
        anchor_bottom_left_ext.getChildren().remove(node_bottom_left);
        anchor_bottom_left_ext.getChildren().add(anchor_top_right.getChildren().get(0));
        anchor_top_right.getChildren().add(Appo);
    }
//------------------------------------------------------------------------------
    @FXML
    private void mnuHelp_About_Click(ActionEvent event) {
        try {                 
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("About.fxml"));
                       
            Parent root1 = (Parent) fxmlLoader1.load();
           
            Stage stage = new Stage();
                        
            stage.setScene(new Scene(root1));  
            stage.setTitle("I.N.G.V. SIT - Information dialog");
            stage.initModality(Modality.APPLICATION_MODAL);
                    
            stage.setMaximized(false);
           
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
        }
    }  
//------------------------------------------------------------------------------
    @FXML
    private void btnExit_Click(ActionEvent event) {
        if (sitDialog.ShowConfirmationMessage("Do you really want to quit SIT?", "All unsaved data will be lost!!", PrimaryStage)==ButtonType.OK) {
            ReleaseResources();
            PrimaryStage.close();
        }  
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnExport_Click(ActionEvent event) {
        mnuFile_Export.fire();
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnSettings_Click(ActionEvent event) {
        mnuTools_Settings.fire();
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnRefresh_Clicked(ActionEvent event) {
        titled_pane_settings.setExpanded(false);
        
        switch (polling_on){
            case "GROUPS":
                SearchAndShowLocations();
                break;
            case "ORIGINS":
                SearchOrigins();
                break;
            default:
                SearchAndShowLocations();
                break;
        }    
    }
//------------------------------------------------------------------------------
    @FXML
    private void date_changed(ActionEvent event) {
        if (!this.initializing){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            TodayString=formatter.format(this.calendar.getValue());
            //SearchAndShowLocations();
            btnRefresh.fire();
            
            if (calendar.getValue().isBefore(LocalDate.now())){
                if ((App.G.options.isWebServicePollingActive()) && sitDialog.ShowConfirmationMessage("Stop polling?", 
                        "You wish to stop events search for this session", PrimaryStage)== ButtonType.OK){
                    App.G.options.setWebServicePollingActive(false);
                    StopPolling();
                }
            }
            
        }
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnWaves_Clicked(ActionEvent event) {
        try {
            if (Event_already_open()){
                sitDialog.ShowInformationMessage("This event is already open in a waves window!!", PrimaryStage);
                return;
            }
            
            // Scan for datasources in this order:
            // - first search on Earhtworm waveservers (sorted by priority)
            // - if no wave is avalable then search on FDSN services (sorted by priority)
//            if (((App.G.options.getDatasources_EW()==null) && (App.G.options.getDatasources_FDSN()==null) && (App.G.options.getDatasources_SL()==null)) && (getLocal_QUAKEML_file().trim().isEmpty())) {
//                sitDialog.ShowInformationMessage("No datasource is configured.", PrimaryStage);
//                return;
//            }
            if ((App.G.options.getDatasources_EW()==null || App.G.options.getDatasources_EW().isEmpty()) && 
                    (App.G.options.getDatasources_FDSN()==null || App.G.options.getDatasources_FDSN().isEmpty()) && 
                    (App.G.options.getDatasources_SL()==null || App.G.options.getDatasources_SL().isEmpty()) && (getLocal_QUAKEML_file().trim().isEmpty())) {
                sitDialog.ShowInformationMessage("Datasource list is empty.", PrimaryStage);
                return;
            }
            
            if (getEvent_on_Map().getSemaphoreOrOtherFlags().toUpperCase().contains("SEMAPHORE")){
                if (sitDialog.ShowConfirmationMessage("Lock found!", "Someone is already watching this event.\n Continue anyway?", PrimaryStage).getButtonData().isCancelButton())
                    return; 
            }
            
            getEvent_on_Map().SetSemaphoreOnOrigin("ON");      
            
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("WavesTaskDialog.fxml"));
                     
            Parent root1 = (Parent) fxmlLoader1.load();
                     
            Stage stage = new Stage();
                        
            stage.setScene(new Scene(root1));  
            stage.setTitle("SIT - Recovering data");
            stage.initModality(Modality.APPLICATION_MODAL);
                    
            stage.setMaximized(false);
            
            stage.show();
            
            
        } catch (Exception ex){
            sitDialog.ShowErrorMessage("Error while loading waves.\n" + ex.getMessage(), PrimaryStage);
        }
    }
    
    
    private boolean Event_already_open(){
        try{
            if (App.G.WavesControllers==null) return false;
            if (App.G.WavesControllers.isEmpty()) return false;
            for (int i=0; i< App.G.WavesControllers.size(); i++){
                if (App.G.WavesControllers.get(i).getMyEvent().getInnerObjectEvent().getOrigins().get(0).getId()== Event_on_Map.getInnerObjectEvent().getOrigins().get(0).getId())
                    return true;
            }
            
            return false;
        } catch (Exception ex){
            return false;
        }
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnOriginReport_Click(ActionEvent event) {
         try {     
            if (getEvent_on_Map()==null) return;
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("ReportWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
      
            Stage stage = new Stage();
                        
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);

            ReportWindowController controller_details = (ReportWindowController)fxmlLoader1.getController();
            
             
            controller_details.ShowData(getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0), getEvent_on_Map().getTownsInfo());

            stage.setTitle("I.N.G.V. SIT - Event Report");
                    
            stage.setMaximized(false);
            stage.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
        }
    }

    @FXML
    private void btnLogin_Click(ActionEvent event) {
        String web_server_port="8585";
        Image alias_image;
        
        if (App.G.User!=null){
            if (!App.G.User.isLoggedIn()){
                   
                try {
                    if (Login_ApplicationContext==null){
                        Login_ApplicationContext = SpringApplication.run(org.ingv.sit.App.class, "--server.port="+web_server_port);
                    }
                        System.out.println("Launched web server on port " + web_server_port);
                } catch (Exception ex){
                    sitDialog.ShowErrorMessage("Unable to startup a web server for authentication!!", PrimaryStage);
                    return;
                }

                //KeyCloakClient KcC = new KeyCloakClient();
                if (myKeyCloakClient==null) myKeyCloakClient = new KeyCloakClient(KeyCloakConfigURL);
                
                if (myKeyCloakClient.handleLogin()) {
                    if (App.G.User==null)
                        App.G.User=new User();
      
                    //App.G.User.InitFromJson(myKeyCloakClient.getUserInfo_jsonstring(), myKeyCloakClient.getRefreshToken(), myKeyCloakClient.getToken_expiration());
                    App.G.User.InitFromJson(myKeyCloakClient.getUserInfo_jsonstring());
                   
                    App.G.User.setToken(myKeyCloakClient.getUserToken());
                    lblLogin.setText(App.G.User.getName());
                    if ((App.G.User.getGoogle_picture_url()!=null) && !(App.G.User.getGoogle_picture_url().isBlank())){
                        alias_image = new Image(App.G.User.getGoogle_picture_url());
                        imgAlias.setImage(alias_image);    
                    }  
                    
                    UpdateOpenEventsProvenances();
                    
                    btnLogout.setVisible(true);
                } else {
                    // Utente non autenticato
                    sitDialog.ShowInformationMessage("No user logged in", PrimaryStage);
                    reset_alias_image();
                    btnLogout.setVisible(false);
                }

                try {
                    int exitCode; 
                    if (Login_ApplicationContext!=null){
                        exitCode = SpringApplication.exit(Login_ApplicationContext); 
                        Login_ApplicationContext=null;
                        System.out.println("Stopped web server on port " + web_server_port + "(exit code: " + exitCode + ")");
                    }


                } catch (Exception ex){
                    sitDialog.ShowErrorMessage("Unable to stop the web server for authentication!!", PrimaryStage);
                }
            }       
        }  
    }
    
    private void reset_alias_image(){
        try {
            Image alias_image;
            alias_image = new Image("images/alias.png");
            imgAlias.setImage(alias_image);                
            lblLogin.setText("Login");
            App.G.User.Init();
        } catch (Exception ex) {
        }
        
    }

    private void UpdateOpenEventsProvenances(){
        try {
            if (App.G.User == null) return;
            if (Event_on_Map!=null)  {
                Event_on_Map.getInnerObjectEvent().getOrigins().get(0).getProvenance().setUsername(App.G.User.getName());
                Event_on_Map.getSitProvenance().setUsername(App.G.User.getName());
            }
            
            if (App.G.WavesControllers!=null && !App.G.WavesControllers.isEmpty()){
                for (int i=0; i< App.G.WavesControllers.size(); i++){
                    App.G.WavesControllers.get(i).getMyEvent().getInnerObjectEvent().getOrigins().get(0).getProvenance().setUsername(App.G.User.getName());
                    App.G.WavesControllers.get(i).getMyEvent().getSitProvenance().setUsername(App.G.User.getName());
                }
            }
            
        } catch (Exception ex) {
        }
    }
//------------------------------------------------------------------------------    
    @FXML
    private void btnLogout_Click(ActionEvent event) {     
        if ((App.G.User!=null) && (App.G.User.isLoggedIn())){
            String msg = "Do you want to logout?";
            if (sitDialog.ShowConfirmationMessage("Logout", msg, PrimaryStage)==ButtonType.OK) {

                if (myKeyCloakClient.handleLogout()){
                    App.G.User.Init();  
                    reset_alias_image();
                    btnLogout.setVisible(false);
                } 
            }     
        }
    }
    
//------------------------------------------------------------------------------
    @FXML
    private void btnPreReadQML_click(ActionEvent event) {
        if (!local_QUAKEML_file.trim().isEmpty())
            TS(null, null, null, getLocal_QUAKEML_file());
        else
            sitDialog.ShowErrorMessage("No QuakeML file selected", PrimaryStage);
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnInfo_Clicked(ActionEvent event) {
        mnuHelp_About.fire();           
    }


    @FXML
    private void btnOriginDetail_Click(ActionEvent event) {
        try {     
            if (getEvent_on_Map()==null) return;
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("DetailsWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
      
            Stage stage = new Stage();
                        
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);

            DetailsWindowController controller_details = (DetailsWindowController)fxmlLoader1.getController();
            
             
            controller_details.ShowData(getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0));

            stage.setTitle("I.N.G.V. SIT - Event details");
                    
            stage.setMaximized(false);
            stage.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
        }
    }
    
//    private void PlaySound(String fName){
//        try {
//            Media sound = new Media(new File(fName).toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(sound);
//            mediaPlayer.play();
//        } catch (Exception ex) {
//            // Log an error
//        }    
//    }
    
    public void SearchAndShowLocations(){
        if (App.G.options.isCaravelAvailable())
            SearchAndShowLocations_CARAVEL();
        else
            SearchAndShowLocations_FDSN(-1);
    }
    
    private void SearchAndShowLocations_CARAVEL(){
        try {  
            if (this.PrimaryStage!=null)
                this.PrimaryStage.getScene().setCursor(Cursor.WAIT);

            if (ReadLevel1_INGV()) {

                if (this.Locations_level1==null){
                    this.setEvent_on_Map(null);
                    sitDialog.ShowInformationMessage("No events found!", PrimaryStage);
                    ResetInterface();
                    return;
                }
    //                       
                MultipleSelectionModel<TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup>> sel = this.treetableview_Events.getSelectionModel();
                sel.setSelectionMode(SelectionMode.SINGLE);

                populate_AllLocations_TreeTableView();
                
                SelectLastModifiedTableRow();

            }  else {
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Could not update events list");
            }
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        } finally {
            if (this.PrimaryStage!=null)
                this.PrimaryStage.getScene().setCursor(Cursor.DEFAULT);
        }
    }
    
    private void SearchAndShowLocations_FDSN(int tabindex){
        try {  
            if (this.PrimaryStage!=null)
                this.PrimaryStage.getScene().setCursor(Cursor.WAIT);
            
            int idFDSNHost; // = findIdForSelectedFDSNEventsSource(); // find_id_for_Event_Source(dsType.FDSN); // In realtà trova l'id del tab corrente
            
            if (tabindex==-1)
                idFDSNHost = findIdForSelectedFDSNEventsSource(null);
            else
                idFDSNHost = findIdForSelectedFDSNEventsSource(tabs_available_FDSN.getTabs().get(tabindex).getText());
            
            if (Read_FDSN_events_list(idFDSNHost)) {

                if (Locations_FDSN==null){
                    setEvent_on_Map(null);
                    sitDialog.ShowInformationMessage("No events found!", PrimaryStage);
                    ResetInterface();
                    return;
                }
    //               
                int tabID = findIdForCurrentFDSNTab();
                if (tabID!=-1){
                    
                    TableView T = (TableView)((AnchorPane)tabs_available_FDSN.getTabs().get(tabID).getContent()).getChildren().get(0);
                    PopulateFDSNOriginsTable(T, Locations_FDSN, true);
                }
    
    
//                MultipleSelectionModel<TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup>> sel = this.treetableview_Events.getSelectionModel();
//                sel.setSelectionMode(SelectionMode.SINGLE);
//
//                populate_AllLocations_TreeTableView();
//                
//                SelectLastModifiedTableRow();

            }  else {
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Could not update events list");
            }
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        } finally {
            if (this.PrimaryStage!=null)
                this.PrimaryStage.getScene().setCursor(Cursor.DEFAULT);
        }
    }
    
    private int findIdForCurrentFDSNTab(){
        try {
            if (tabs_available_FDSN.getTabs() == null) return -1;
            if (tabs_available_FDSN.getTabs().isEmpty()) return -1;

            return tabs_available_FDSN.getSelectionModel().getSelectedIndex();

        } catch (Exception ex){
            return -1;
        }    
    }
    private int findIdForSelectedFDSNEventsSource(String src_title){
        try {
            if (tabs_available_FDSN.getTabs() == null) return -1;
            if (tabs_available_FDSN.getTabs().isEmpty()) return -1;
            if (App.G.options.getDatasources_FDSN()==null) return -1;
            if (App.G.options.getDatasources_FDSN().isEmpty()) return -1;
            
            String titolo=null;
//            for (int i=0; i < tabs_available_FDSN.getTabs().size(); i++){
//                if (tabs_available_FDSN.getTabs().get(i).isSelected())
//                    titolo = tabs_available_FDSN.getTabs().get(i).getText();
//            }
//            try {
//                titolo = tabs_available_FDSN.getSelectionModel().getSelectedItem().getText();
//            } catch (Exception ex){
//            }
            
            if (src_title!=null)
                titolo = src_title;
            else
                titolo = tabs_available_FDSN.getSelectionModel().getSelectedItem().getText();
          
            if (titolo==null) return -1;
            
            for (int i=0; i<App.G.options.getDatasources_FDSN().size(); i++){
                if (titolo.toUpperCase().contains(App.G.options.getDatasources_FDSN().get(i).getDescription().toUpperCase()))
                    return i;
            }
            return -1;
        } catch (Exception  ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------
    private void ResetInterface(){
        //-------------------------------------------
        this.table_phases.getColumns().clear();
        this.table_towns.getColumns().clear();
        //-------------------------------------------
        //TreeItem<String> dummyRoot = new TreeItem<>("No events available"); 
        this.treetableview_Events.setRoot(null); //(dummyRoot);
        //dummyRoot.setExpanded(false);
        //this.treeview_Events.setShowRoot(true); 
        //-------------------------------------------
        resChart.getData().clear();
        
        ShowEventSummary();
  }
//------------------------------------------------------------------------------    
private boolean Read_FDSN_events_list(int idFDSNHost){
    /*
    This routine searches for "groups of locations" through the FDSN QuakeML-based
    web polling_service.
    */
    FDSN_ws_reader FDSN_reader = new FDSN_ws_reader();
//
    EventIterator events_qml_list;
    String s_id_gruppo, s_id_evento, s_id_ipocentro, s_ot, s_area, s_magn, s_localspace, s_version;
    
    try {
        
        // Finds first available (set as "used") FDSN node
        //int idFDSNHost = find_id_for_Event_Source(dsType.FDSN);
        if (idFDSNHost==-1)return false;

        events_qml_list=FDSN_reader.read_quakeml_list(App.G.options.getDatasources_FDSN().get(idFDSNHost).getHostname(), 
                TodayString + " 00:00:00",
                TodayString + " 23:59:59",
                -2,             // Min Magnitude
                App.G.options.get_box_minLat(), App.G.options.get_box_maxLat(), // Coordinates box
                App.G.options.get_box_minLon(), App.G.options.get_box_maxLon(), // Coordinates box
                1000);          // Max depth
        
        if (events_qml_list != null) {
            //this.Locations_level1 = new ArrayList<>();
            Locations_FDSN = new ArrayList<>();
            //
            edu.sc.seis.seisFile.fdsnws.quakeml.Origin o;
            edu.sc.seis.seisFile.fdsnws.quakeml.Magnitude m;
            while (events_qml_list.hasNext()) {
                s_id_gruppo="";
                s_id_evento="";  
                s_id_ipocentro="";  
                s_ot="";
                s_magn="";
                s_localspace="";
                s_area="";
                s_version="";
                
                edu.sc.seis.seisFile.fdsnws.quakeml.Event qml_ev = events_qml_list.next();
                               
                o = qml_ev.getOriginList().get(0);               
                
                //
                //----------------------------------------------------------------
                // This ID search must PROBABLY be reviewed
//                s_id_gruppo = qml_ev.getPublicId(); //o.getPublicId();
//                if (s_id_gruppo.contains("=")) {
//                    s_id_gruppo = s_id_gruppo.substring(s_id_gruppo.lastIndexOf("=")+1);
//                }
//                if (s_id_gruppo.contains("/")) {
//                    s_id_gruppo = s_id_gruppo.substring(s_id_gruppo.lastIndexOf("/")+1);
//                }
                
                s_id_evento =  qml_ev.getPublicId();
//                 if (s_id_evento.contains("=")) {
//                    s_id_evento = s_id_evento.substring(s_id_evento.lastIndexOf("=")+1);
//                }
//                 if (s_id_evento.contains("/")) {
//                    s_id_evento = s_id_evento.substring(s_id_evento.lastIndexOf("/")+1);
//                }
                 
//                s_id_ipocentro = qml_ev.getPreferredOriginID(); 
//                 if (s_id_ipocentro.contains("=")) {
//                    s_id_ipocentro = s_id_ipocentro.substring(s_id_ipocentro.lastIndexOf("=")+1);
//                }
//                 if (s_id_ipocentro.contains("/")) {
//                    s_id_ipocentro = s_id_ipocentro.substring(s_id_ipocentro.lastIndexOf("/")+1);
//                }
                //---------------------------------------------------------------- 
                ObjectMagnitudesOriginsEventsAndEventsGroup tmpItem = new ObjectMagnitudesOriginsEventsAndEventsGroup();
                //ObjectOriginForMagnitudesOriginsEventsAndEventsGroup tmpO = new ObjectOriginForMagnitudesOriginsEventsAndEventsGroup();
                
                // ID
//                try {
//                    tmpO.setId(Long.valueOf(s_id_evento)); 
//                } catch (Exception ex){
//                    tmpO.setId((long)1234567);
//                }
                               
                // REGION
                if (!qml_ev.getDescriptionList().isEmpty()) {
                    if (qml_ev.getDescriptionList().get(0).getText() != null){
                        s_area = qml_ev.getDescriptionList().get(0).getText();               
                    } else s_area = "Unknown area";
                } else s_area = "Unknown area";
                
                // OT
                s_ot = o.getTime().getValue(); 
                s_version = o.getCreationInfo().getVersion();
                
                if (!qml_ev.getMagnitudeList().isEmpty())
                    s_magn = qml_ev.getMagnitudeList().get(0).getMag().getValue().toString();
                else
                    s_magn= null;

                s_localspace = qml_ev.getOriginList().get(0).getCreationInfo().getAuthor(); 
 
                Locations_FDSN.add(new FDSN_Events_List_items(s_id_evento,  s_ot, s_magn, 
                    "", s_area,  s_version, s_localspace));
            }
            return true;
        } else return false;     
    } catch (Exception ex) {
        return false;
    }
}
//------------------------------------------------------------------------------
    private int find_id_for_Event_Source(dsType source_type){ 
        try{
            switch (source_type){
                case FDSN:
                    for (int i=0; i<App.G.options.getDatasources_FDSN().size(); i++){
                        if (App.G.options.getDatasources_FDSN().get(i).isUsed()){ 
                            return i;
                        }
                    }
                case INGV:    
                    for (int i=0; i<App.G.options.getDatasources_CARAVEL().size(); i++){
                        if (App.G.options.getDatasources_CARAVEL().get(i).isUsed()){ 
                            return i;
                        }
                    }
            }
            //           
            return 0;
        } catch (Exception ex){
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    private boolean ReadLevel1_INGV(){
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss.DDD");
        //----------------------------------------------------------------------
        // QUESTA ROUTINE VA RIVISTA per gestire meglio i tipi di sorgenti dati
        //----------------------------------------------------------------------
        GetApi ReadClient;
        try {
            ReadClient = new GetApi();            
            int idCaravelHost = find_id_for_Event_Source(dsType.INGV);
            if (idCaravelHost==-1)return false;
          
            ReadClient.getApiClient().setBasePath(App.G.options.getDatasources_CARAVEL().get(idCaravelHost).getUrl());
            ReadClient.getApiClient().setReadTimeout(30000);
            
            OffsetDateTime start, end;
            GetEventsGroup200Response request_output;
            
            java.time.LocalDateTime NOW = java.time.LocalDateTime.now();
            if ((limit_search)&&(App.G.options.get_hours_before_now()>0))
                start = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), NOW.getHour()-App.G.options.get_hours_before_now(), NOW.getMinute(), NOW.getSecond(), 0, ZoneOffset.UTC);
             else
                start = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
            
            if ((NOW.getYear()==calendar.getValue().getYear()) && (NOW.getMonthValue()==calendar.getValue().getMonthValue()) && (NOW.getDayOfMonth()==calendar.getValue().getDayOfMonth()))
                end = null;
            else
                end = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), 23, 59, 59, 0, ZoneOffset.UTC);
//      
            App.logger.debug("WS-LOG: ReadLevel1_INGV reading by getEventsGroup");
                                  
            request_output = ReadClient.getEventsGroup(start, end,              // OffsetDateTime starttime, OffsetDateTime endtime,
                    null, null, null, null, null, null, null, null, null,       // Double minlat, Double maxlat, Double minlon, Double maxlon, orpolygon, notinpolygon, wherepolygonnamein, Double lat, Double lon,
                    null, null, null, null,                                     // Double minradius, Double maxradius, Double minradiuskm, Double maxradiuskm
                    null, null,null, null,                                      // Double minmag, maxmag, mindepth, maxdepth
                    null, null, null, null,
                    null, null,                                                 // mintypeoriginvalue, maxtypeoiriginvalue
                    null, null, null,                                           // origindirectlinktoevent, magnitudedirectlinktoorigin, magnitudedirectlinktoevent
                    null, null, null, null,                                                // wheretypeoriginvaluein, wheretypemagnitudenameregexp
                    App.G.options.getList_of_searched_environments(),           // whereeventlocalspaceenvironmentin
                    App.G.options.getList_of_searched_environments(),           // whereoriginlocalspaceenvironmentin
                    App.G.options.getList_of_searched_environments(),           // wheremagnitudelocalspaceenvironmentin
                    null, null, 
                    null, //App.G.options.getList_of_searched_localspaces_toString(),
                    null, // Long mintypeoriginvalue, Long maxtypeoriginvalue, String wheretypeoriginvaluein, String wherelocalspacenamein, String whereflagsin,
                    null, null, null,
                    null, 
                    CaravelSearchOrder,null, null,null, null, null, null, null);                     // String orderby, Long eventGroupId, Long idLocalspace, Integer limit, Integer page
            
        
            App.logger.debug("WS-LOG: ReadLevel1_INGV response received");

    //
            if (request_output.getData()!=null){
                // The web polling_service returned a non empty list 
                Locations_level1 = (ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup>)request_output.getData();
            } else {
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.WARNING, 
                                   "No hypocenter found in the selected time interval");
            }       
            return true;
        } catch (org.ingv.dante.ApiException aex){
            String msg = aex.getResponseBody();
            if (aex.getMessage()!=null) msg = aex.getMessage();
            Logger.getLogger("org.ingv.sit.MapFormController.ReadLevel1_INGV()").log(java.util.logging.Level.SEVERE, 
                                   msg);
            sitDialog.ShowErrorMessage(msg, PrimaryStage);
            return false;
        } catch (Exception ex){
            sitDialog.ShowErrorMessage("Can't read events:\n" + ex.getMessage(), PrimaryStage);
             
            return false;
        } finally {
            ReadClient = null;
        }
    }    
//------------------------------------------------------------------------------      
    private void drawMap(){   
        if (getMH()!=null){
            getMH().close();
            setMH(null);
        }
        
        setMH(new MapHandler((int)anchor_top_right_int.getWidth(), (int)anchor_top_right_int.getHeight()-45));
        if ((getEvent_on_Map()!=null) && (getEvent_on_Map().getInnerObjectEvent().getOrigins()!=null))
            getMH().CreateMapWithCanvas(getEvent_on_Map().ToStationArray(), 
                    getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getLat(), getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getLon(), 
                    App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
                    App.G.options.get_box_minLat(),App.G.options.get_box_maxLat(), 
                    false, false);
        else
            getMH().CreateMapWithCanvas(null, 0, 0, 
                    App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
                    App.G.options.get_box_minLat(),App.G.options.get_box_maxLat(), 
                    false, false);
        
        anchor_top_right_int.getChildren().add(getMH().getCanvas()); 
               
        AnchorPane.setBottomAnchor(getMH().getCanvas(), 0.0);
        AnchorPane.setTopAnchor(getMH().getCanvas(), 45.0);
        AnchorPane.setLeftAnchor(getMH().getCanvas(), 0.0);
        AnchorPane.setRightAnchor(getMH().getCanvas(), 0.0); 
    }
 //------------------------------------------------------------------------------    
    @FXML
    private void mnuView_ShowMagsByAzimuthChart_Click(ActionEvent event) {
        if (mnuView_ShowMagsByAzimuthChart.isSelected()){
            App.G.show_magnitudebyazimuth_chart=true;
            
        } else {
            App.G.show_magnitudebyazimuth_chart=false;
        }
        
        populate_magnitude_tabs();
    }
//------------------------------------------------------------------------------      
    public void populate_magnitude_tabs(){
        tabMags.getTabs().clear();
        if (getEvent_on_Map()==null) return;
        if ((getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes()==null) || (this.getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty())) {
            AddMAGTab(null);
        } else {
            for (int i =0; i< getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); i++){
                AddMAGTab(getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i)); 
                if (getEvent_on_Map().getInnerObjectEvent().getPreferredMagnitudeId()!=null && getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getId()!=null) {
                    if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getId().equals(getEvent_on_Map().getInnerObjectEvent().getPreferredMagnitudeId())){
                        tabMags.getSelectionModel().select(tabMags.getTabs().get(tabMags.getTabs().size()-1));  
                        setTabAsPreferred(tabMags.getTabs().get(tabMags.getTabs().size()-1), getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i));
                    }
                }
                  
            }
        }    
    }   
//------------------------------------------------------------------------------      
    private void AddMAGTab(ObjectMagnitude M)  {
        try {
            Tab tab = new Tab();
            
            fXMLLoader = new FXMLLoader();
            Parent tab_root;
            
            String Magnitude_tab_fxml_name = "Magnitude.fxml"; 
 
            tab_root = (Parent) fXMLLoader.load(this.getClass().getResource(Magnitude_tab_fxml_name).openStream());
             
            MagnitudeController controller_ML = (MagnitudeController)fXMLLoader.getController();
            
            //----------------------------------------------------------------------------
            // ATTENZIONE: Inserisce gli azimuth di stazione nelle station magnitudes
            // perchè di solito mancano (ma nelle fasi ci sono) e servono per un grafico
            //----------------------------------------------------------------------------
            if ((M!=null)&&(M.getStationmagnitudes()!=null) && (!M.getStationmagnitudes().isEmpty())){
                for (int i=0; i<M.getStationmagnitudes().size(); i++){
                    if (M.getStationmagnitudes().get(i).getAzimut()==null){
                        M.getStationmagnitudes().get(i).setAzimut(this.getEvent_on_Map().AzimutPerStation(M.getStationmagnitudes().get(i).getAmplitude().getSta()));
                    }
                }
            }
            //----------------------------------------------------------------------------
            
            controller_ML.setMAG(M);
            
            controller_ML.setEventSource("MAPFORM");
            controller_ML.setEventId(getEvent_on_Map().getInnerObjectEvent().getId());
            controller_ML.setEventPreferredMagnitudeId(getEvent_on_Map().getInnerObjectEvent().getPreferredMagnitudeId());
            controller_ML.ShowData(false);
            
            //Image iconImage = new Image("images/Accept-icon.png");

        // Creazione di un'etichetta con un'immagine
        String testo = "No Magnitude";
        if (M!=null) {
                testo = M.getTypeMagnitude();
        } 
        Label label = new Label(testo); //, new ImageView(iconImage));
        tab.setGraphic(label);
            
            tab.setContent(tab_root);

            tabMags.getTabs().add(tab);           
            fXMLLoader=null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }  
    }
    
    
    private void setTabAsPreferred(Tab tab, ObjectMagnitude M){
        Image iconImage = new Image("images/yellowstar_small.png");

        // Creazione di un'etichetta con un'immagine
        String testo = "No Magnitude";
        if (M!=null) {
                testo = M.getTypeMagnitude();
        } 
        Label label = new Label(testo, new ImageView(iconImage));
        tab.setGraphic(label);
        
    }
//------------------------------------------------------------------------------
    private ResidualSelector createResidualSelector( ScatterChart<?, ?> scatterChart) {
        final Axis<?> xAxis = scatterChart.getXAxis();
        final Axis<?> yAxis = scatterChart.getYAxis();
    
        ResidualSelector resSel= new ResidualSelector();

        final Node chartBackground = scatterChart.lookup(".chart-plot-background");

        for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
          if (n != chartBackground && n != xAxis && n != yAxis) {
            n.setMouseTransparent(true);
          }
        }
        chartBackground.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override public void handle(MouseEvent mouseEvent) {    
                 if (mouseEvent.getButton()==MouseButton.SECONDARY) getMH().TurnStationsOFF();
                 mouseEvent.consume();

           }
        });

        chartBackground.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {    

                if (mouseEvent.getButton()==MouseButton.SECONDARY) getMH().TurnStationsOFF();

                resSel.setDeltaMin((double)xAxis.getValueForDisplay(mouseEvent.getX()));
                resSel.setDeltaMax((double)xAxis.getValueForDisplay(mouseEvent.getX()));
                resSel.setResMin((double)yAxis.getValueForDisplay(mouseEvent.getY()));
                resSel.setResMax((double)yAxis.getValueForDisplay(mouseEvent.getY()));

                mouseEvent.consume();

          }
        });
        chartBackground.setOnMouseReleased(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
              resSel.setDeltaMax((double)xAxis.getValueForDisplay(mouseEvent.getX()));
              resSel.setResMax((double)yAxis.getValueForDisplay(mouseEvent.getY()));

              ArrayList res = resSel.FireStationsSelection(getEvent_on_Map());
              if (res != null) {
                   getMH().BlinkStationsSet(res);
              }
          }
        });

        return resSel; 
    }      
    //------------------------------------------------------------------------------
    private boolean UpdateCaravelWebServicesStatus(){
        return true;
//        GetApi ReadClient;
//        File file;
//        try {
//            boolean ok;
//            int n_services_up=0;
//            ReadClient = new GetApi();
//            ReadClient.getApiClient().setReadTimeout(30000);
//            
//            org.ingv.apollo.api.StatusApi apollo_status = new org.ingv.apollo.api.StatusApi();
//            org.ingv.dante.api.StatusApi dante_status = new org.ingv.dante.api.StatusApi();
//                             
//            App.logger.debug("WS-LOG: ReadClient.getStatus()");
//            ObjectStatus p = dante_status.getStatus();
//            App.logger.debug("WS-LOG: ReadClient.getStatus() received response");
//            
//            String separator = System.getProperty("file.separator");
//                  
//            if (p.getStatus()==200) {
//                n_services_up=1;
//                
//                ok= true;
//            } else {
//                ok=false;
//                
//            }
//            
//                    
//            String host_read = "Host(ro): ";
//            String  host_write  = "Host(rw): ";
//            for (int i=0; i< p.getDbHost().getRead().size(); i++){
//                host_read += p.getDbHost().getRead().get(i) + "/";
//            }
//            for (int i=0; i< p.getDbHost().getWrite().size(); i++){
//                host_write += p.getDbHost().getWrite().get(i) + "/";
//            }
//            
//            String apollo_info1;
//            apollo_status.setCustomBaseUrl(App.G.options.get_pyml_url());
//            try {
//                apollo_info1=  apollo_status.getStatus().getVersion() + " Status:" + apollo_status.getStatus().getDetail();
//                n_services_up=2;
//            } catch (Exception ex){
//                apollo_info1= "** NOT RESPONDING **";
//                ok=false;
//            }
//            
//            switch (n_services_up){
//                case 0:
//                    file = new File("src"+ separator + "main"+ separator + "resources"+ separator + "images"+ separator + "hand-ko-icon.png");
//                    break;
//                case 1:
//                    file = new File("src"+ separator + "main"+ separator + "resources"+ separator + "images"+ separator + "warning3.png");
//                    break;
//                case 2:
//                    file = new File("src"+ separator + "main"+ separator + "resources"+ separator + "images"+ separator + "hand-ok-icon.png");
//                    break;
//                default:
//                    file=null;
//            }
//            if (file!=null)
//                imgDanteStatus.setImage(new Image(file.toURI().toString()));
//
//            lblDanteStatus.setText("SIT is using Caravel web services (Dante & Apollo) - Dante: " + 
//                    p.getDetail() + " [" + p.getStatus() + "-" +p.getTitle() + "] " + 
//                    host_read + " " + host_write  + "  Port: " + p.getDbPort().toString()  + 
//                    "  Database name: " + p.getDbName() + " -- Apollo " + apollo_info1);
//            
//            return ok;
//                       
//        } catch (Exception ex) {
//            return false;
//        } finally {
//            ReadClient = null;
//            file=null;
//        }
    }    
//------------------------------------------------------------------------------
    public void StartPolling(){
        lblLastPoll.setVisible(true);
        OffsetDateTime startup;
        startup = OffsetDateTime.of(OffsetDateTime.now().getYear(), OffsetDateTime.now().getMonthValue(), OffsetDateTime.now().getDayOfMonth(), 
                    OffsetDateTime.now().getHour(), OffsetDateTime.now().getMinute(), OffsetDateTime.now().getSecond(), 0, ZoneOffset.UTC);
        if (polling_service == null)
            polling_service = new PollingService(App.G.options.getAlarm_filename(), App.G.options.getAlarm_originupdate_filename(),startup, startup, startup);
        polling_service.setPeriod(Duration.seconds(App.G.options.get_polling_interval())); // The interval between executions.
        
        if (polling_service.getState()==State.RUNNING || polling_service.getState()==State.CANCELLED ||polling_service.getState()==State.SCHEDULED)
            polling_service.restart(); 
        else
            polling_service.start(); 
    }
//------------------------------------------------------------------------------    
    public void StopPolling(){
        if ((polling_service!=null) && ((polling_service.getState()==State.RUNNING)||(polling_service.getState()==State.SCHEDULED)))
            polling_service.cancel();
        lblLastPoll.setVisible(false);
    }
  
//------------------------------------------------------------------------------
    private void CARAVEL_UpdateMainInterface_with_treetable(TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> valore){
//
        if (initializing) return;
        
        if (PrimaryStage!=null)
            PrimaryStage.getScene().setCursor(Cursor.WAIT);
        
        if (valore==null) {return;}
//   
        ObjectMagnitudesOriginsEventsAndEventsGroup oggetto_nella_riga=null;
    
        if (valore.getValue() instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) { 
            // Selected item is a ROOT node (Lev. 1) or a leaf node (Lev. 3)
            oggetto_nella_riga = valore.getValue();
            // Avvia un thread separato per la gestione dell'interfaccia asincrona rispetto alla lettura dell'evento

            TS(oggetto_nella_riga.getOrigin().getId(), null, null,null);
           
            if (valore.getParent().getParent()==null){
                livello_click=1;
                selected_group_id = String.valueOf(oggetto_nella_riga.getEventGroupId());
            }   else {
                    if (oggetto_nella_riga.getOrigin().getQuality()!=null) {
                        livello_click=2;
                        selected_group_id = String.valueOf(oggetto_nella_riga.getEventGroupId());
                    } else {
                        livello_click=3; 
                        selected_group_id = String.valueOf(valore.getParent().getParent().getValue().getId()); 
                }
            }
        } 
    }    
//------------------------------------------------------------------------------     
    private void show_group_members(TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> local_treetable_item){
        try {
            if (local_treetable_item.getValue() instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {             
                if (local_treetable_item.getParent().getParent()==null){           
                    if (local_treetable_item.getChildren().isEmpty()) {
                        // This expands a root node     
                        if (local_treetable_item.getValue().getEventGroupId()!=null) {
                                selected_group_id = String.valueOf(Double.valueOf(local_treetable_item.getValue().getEventGroupId()).longValue()); 
                                if (!selected_group_id.equals("0")) {
                                    if (!populate_group_members_INGV() ) { // read the elements in the group
                                        // If no element is found, try to read replicating the object in the parent
                                        if (!populate_group_members_INGV_replicating_parent(local_treetable_item.getValue())){
                                            sitDialog.ShowErrorMessage("Error while searching for child locations.\nTry again later.", PrimaryStage);
                                        }
                                    }
                                } else if (!populate_group_members_INGV_replicating_parent(local_treetable_item.getValue()) ) { // read the elements in the group
                                    sitDialog.ShowErrorMessage("Error while searching for child locations.\nTry again later.", PrimaryStage);
                                }
                           }
                    }                  
                }       
            }
        } catch (Exception ex) {
        }
    }
//------------------------------------------------------------------------------     
    private void ReadSelectedEvent_FDSN(FDSN_Events_List_items oggetto_nella_riga){
        if (this.PrimaryStage!=null)
            this.PrimaryStage.getScene().setCursor(Cursor.WAIT);
        if (oggetto_nella_riga==null) {return;}
        TS(null, oggetto_nella_riga.getId(), null,null);
    }   
//------------------------------------------------------------------------------    
    private void ReadSelectedEvent_CARAVEL(ObjectMagnitudesOriginsEventsAndEventsGroup oggetto_nella_riga){
        if (this.PrimaryStage!=null)
            this.PrimaryStage.getScene().setCursor(Cursor.WAIT);
        if (oggetto_nella_riga==null) {return;}
        TS(oggetto_nella_riga.getOrigin().getId(),null,null,null);
    }     
 //------------------------------------------------------------------------------   
    private void ReadSelectedEvent_LOCALHOST(String localhost_folder_path){
        if (this.PrimaryStage!=null)
            this.PrimaryStage.getScene().setCursor(Cursor.WAIT);
        
        TS(null, null, localhost_folder_path,null);
    }   
//--------------------------------------------------------------------------------    
    private void TS(Long l_eventID, String s_eventID, String localhost_folder_path, String qmlfilepath) {
        /*
        l_eventID != null ==> read using CARAVEL
        l_eventID is the hypocenter_id of a specific earthquake location in CARAVEL services
        
        s_eventID != null ==> read using FDSN
        s_eventID is the .....l_eventID in a quakeml (should be the favourite location??) for FDSN services
        
        localhost_folder_path!=null ==> read using a local folder as an event repository
        
        */
//        
        Task<Void> task = new Task<Void>() {
            public boolean finito=false;
            
            @Override
            protected Void call() throws Exception {
                boolean read_result = false;
                String read_discrim;
                if (l_eventID!=null)
                    read_discrim = "INGV";
                else if (s_eventID!=null){
                    read_discrim = "FDSN";
                } else if (localhost_folder_path!=null){
                    read_discrim = "LOCALHOST";
                } else 
                    read_discrim = "LOCALHOST_XML";
                // Lettura dell'evento tramite "web polling_service"
                if (App.G.WavesControllers!=null)
                    setEvent_on_Map(new Event(App.G.WavesControllers.size())); 
                else
                    setEvent_on_Map(new Event(0));
//               
                switch (read_discrim){
                    case "INGV":
                        read_result = getEvent_on_Map().read(l_eventID);
                        break;
                    case "FDSN":
                        int idFDSNHost = findIdForSelectedFDSNEventsSource(null); 
                        String ID = s_eventID;
                        FDSN_ws_reader FDSN_reader = new FDSN_ws_reader();
                        //------------------------------------------------------
                        // ID ADJUSTMENTS
                        // Numeric IDs have to be extracted
                        //------------------------------------------------------
                        if (ID.contains("=")){
                            ID = ID.substring(ID.lastIndexOf("=")+1);
                        } else if (ID.contains("/")){
                            ID = ID.substring(ID.lastIndexOf("/")+1);
                        }
    
                        //------------------------------------------------------
                        
                        edu.sc.seis.seisFile.fdsnws.quakeml.Event qml_ev  = FDSN_reader.read_single_quakeml(App.G.options.getDatasources_FDSN().get(idFDSNHost).getHostname(), 
                                ID, true, false, true); 
                        // POI LEGGE
                        if (qml_ev!=null)
                            read_result = getEvent_on_Map().read(qml_ev); 
                        else{
                            read_result=false;
                            sitDialog.ShowErrorMessage("Could not find event information for id " + s_eventID, PrimaryStage);
                        }
                        break;
                    case "LOCALHOST":
                        read_result = getEvent_on_Map().read_from_SAC_folder(local_SAC_folder);
                        break;
                    case "LOCALHOST_XML": 
                        edu.sc.seis.seisFile.fdsnws.quakeml.Event local_qml_ev = getEvent_on_Map().read_single_quakeml_from_file(qmlfilepath);
                        if (local_qml_ev!=null)
                            read_result = getEvent_on_Map().read(local_qml_ev); 
                        else{
                            read_result=false;
                            sitDialog.ShowErrorMessage("Could not find event information in \n" + getLocal_QUAKEML_file(), PrimaryStage);
                        }
                        break;       
                }                
//
                if (read_result) {         
                    updateProgress(100, 100);
                    return null; // va rivisto il tipo in uscita
                } else {
                    sitDialog.ShowErrorMessage("Something was wrong while reading this event.", PrimaryStage);
                    finito=false;
                    return null;
                }
            }
        };
//  
//
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                
                if (getEvent_on_Map()==null) return;
                if (getEvent_on_Map().getInnerObjectEvent()==null) return;
                if (getEvent_on_Map().getInnerObjectEvent().getOrigins()==null) return;
                if (getEvent_on_Map().getInnerObjectEvent().getOrigins().isEmpty()) return;
                
                getEvent_on_Map().SortStationsByP();
                               
                ShowEventSummary();          
                
                getMH().showLocationOnMap(getEvent_on_Map().ToStationArray(), getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getLat(), getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getLon(), false);
                                 
                make_residuals_chart();
               
                if (!populate_phases_list()) {
                    sitDialog.ShowErrorMessage("Error while building phases list.", PrimaryStage);
                }
                
                if (!populate_towns_list()) {
                    sitDialog.ShowErrorMessage("Error while building towns list.", PrimaryStage);
                }
                
                if (getPrimaryStage() != null) 
                    getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            }
        });
//
        new Thread(task).start();
    }  
//------------------------------------------------------------------------------    
    private boolean populate_group_members_INGV(){
        try {    
            ArrayList<LocationTreeItems_Lev2_new> res = ReadLevel2_INGV(selected_group_id);               
            MultipleSelectionModel<TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup>> sm = this.treetableview_Events.getSelectionModel();        
//        
            // Get the selected TreeItem
            TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> selectedItem =  sm.getSelectedItem();  
        
             if (res != null) {
                if (!res.isEmpty()) {
                    for (int i=0; i< res.size(); i++){          
                        TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> item_lev2 = new TreeItem(res.get(i).getData());

                        if (res.get(i).getChildren_level3()!=null){
                            for (int k=0; k<res.get(i).getChildren_level3().size(); k++){
                                TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> item_lev3 = new TreeItem(res.get(i).getChildren_ObjectOrigin_to_ObjectMagnitudesOriginsEventsAndEventsGroup(k));
                                item_lev2.getChildren().add(item_lev3);
                            }
                        }
                        item_lev2.setExpanded(true);
                        selectedItem.getChildren().add(item_lev2);                       
                    }
                    return true;
                }
            }         
//
            return false;   // no children in the group
        } catch (Exception ex) {
            return false;
        }  
    } 
//------------------------------------------------------------------------------    
    private boolean populate_group_members_INGV_replicating_parent(ObjectMagnitudesOriginsEventsAndEventsGroup parent){
        try {    
            ArrayList<LocationTreeItems_Lev2_new> res =  new ArrayList<>(); 
            String s_id_evento= parent.getId().toString();
            List<ObjectOrigin> myLevel3;
            myLevel3 = ReadLevel3_INGV(s_id_evento);
            
            // This creates a dummy element for level 2
            LocationTreeItems_Lev2_new DUMMY = new LocationTreeItems_Lev2_new(parent, myLevel3,getEvent_on_Map().getInnerObjectEvent().getPreferredMagnitudeId());

            res.add(DUMMY);
            
            MultipleSelectionModel<TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup>> sm = this.treetableview_Events.getSelectionModel();        
//        
            // Get the selected TreeItem
            TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> selectedItem =  sm.getSelectedItem();  
        
             if (res != null) {
                if (!res.isEmpty()) {
                    for (int i=0; i< res.size(); i++){
                        //this.addNewChildItem_Lev2(res.get(i));
                        TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> item_lev2 = new TreeItem(res.get(i).getData());
                        
                        if (res.get(i).getChildren_level3()!=null){
                            for (int k=0; k<res.get(i).getChildren_level3().size(); k++){
                                //TreeItem<ObjectOrigin> item_lev3 = new TreeItem(temp_level3.get(k));
                                TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> item_lev3 = new TreeItem(res.get(i).getChildren_ObjectOrigin_to_ObjectMagnitudesOriginsEventsAndEventsGroup(k));
                                item_lev2.getChildren().add(item_lev3);
                            }
                        }
                        
                        
                        selectedItem.getChildren().add(item_lev2);
                    }
                    return true;
                }
            }         
//
            return false;   // no children in the group
        } catch (Exception ex) {
            return false;
        }  
    } 
//------------------------------------------------------------------------------
    private ArrayList<LocationTreeItems_Lev2_new> ReadLevel2_INGV(String in_group_id){
        GetApi ReadClient;
        try {       
            ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> Locations_level2;      
            ArrayList<LocationTreeItems_Lev2_new> res = new ArrayList<>();
            String  s_id_evento;         
            ReadClient = new GetApi();
//           
            if (in_group_id==null) return null;
            if (in_group_id.equalsIgnoreCase("0")) return null;
            
            OffsetDateTime start, end;           
    //      
            start = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
            end = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), 23, 59, 59, 0, ZoneOffset.UTC);
    //                  
            ReadClient.getApiClient().setReadTimeout(30000);
//            
            App.logger.debug("WS-LOG: ReadClient.getEvents reading"); 
          
            GetEventsGroup200Response request_output  = ReadClient.getEvents(    
                    null, null,
                    null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null,  
                    null, null, null, 
                    null, null, null, null, null, null, null,
                    null, null, null, null, null, null,
                    App.G.options.getList_of_searched_environments(),
                    App.G.options.getList_of_searched_environments(), 
                    App.G.options.getList_of_searched_environments(),
                    null, null, null, null, null, null, null, null,
                    CaravelSearchOrder, 
                    Long.valueOf(in_group_id), null, null, null, null, null, null);
                       
            App.logger.debug("WS-LOG: ReadClient.getEvents received response"); 

            Locations_level2 = (ArrayList)request_output.getData();
            
            List<ObjectOrigin> myLevel3;
            for (int i=0; i< Locations_level2.size(); i++) {                     
                s_id_evento= Locations_level2.get(i).getId().toString(); 
//                                
                myLevel3 = this.ReadLevel3_INGV(s_id_evento);    
                res.add(new LocationTreeItems_Lev2_new(Locations_level2.get(i), myLevel3, getEvent_on_Map().getInnerObjectEvent().getPreferredMagnitudeId()));
//                    
            }
//            
            return res;
        } catch (Exception ex) {
            return null;
        }  finally {
            ReadClient = null;
        }
    }
//-------------------------------------------------------------------------------- 
    private List<ObjectOrigin> ReadLevel3_INGV(String event_id){
        GetApi ReadClient;
         try {
            ReadClient = new GetApi();
            ReadClient.getApiClient().setReadTimeout(10000);
           
            ObjectEvent evento;
            App.logger.debug("WS-LOG: ReadClient.getEvent reading");           
            
            ApiResponse<GetEvent200Response> ar = ReadClient.getEventWithHttpInfo(null, Long.valueOf(event_id), null, "type_origin_version_value-desc", null);
            int code = ar.getStatusCode();
            
            App.logger.debug("WS-LOG: ReadClient.getEvent received response");        
            //evento = request_output.getData().getEvent();
            evento = ar.getData().getData().getEvent();

            if ((evento !=null) && (evento.getOrigins()!=null) && (!evento.getOrigins().isEmpty())){  
                List<ObjectOrigin> res = evento.getOrigins();
                
//                res.sort(new Comparator<ObjectOrigin>() {
//                    @Override
//                    public int compare(ObjectOrigin o1, ObjectOrigin o2) {
//                        //return p1.getAge() - p2.getAge();
//                        return (int)(o2.getTypeOrigin().getVersionValue() - o1.getTypeOrigin().getVersionValue());
//                    }
//                });
                
                return res;
            } else {
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.WARNING, 
                                   "No hypocenter found in time interval " +  
                                   TodayString +  "T00:00:00.000+00:00" + " to " + 
                                   TodayString +  "T23:59:59.000+00:00");
                return null;
            }       
          
        } catch (Exception ex){
            return null;
        } finally {
            ReadClient = null;
        }
    }
    
    
//------------------------------------------------------------------------------    
    private void ShowEventSummary(){
    try {       
        if (getEvent_on_Map()==null) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Hypocenter is null");
            this.lblLat.setText("00.00");
            this.lblLon.setText("00.00");
            this.lblDep.setText("00.00" + " km");
            this.lblErrH.setText("00.00");
            this.lblErrZ.setText("00.00");
            
            this.lblArea.setText("Unknown area");
            
            //if (this.lblInstance!=null) this.lblInstance.setText("Unknown istance");
            this.lblOT.setText("ORIGIN TIME: HH:MM:SS");
            lblIDs.setText("Event id: " +    " Origin id:" );
            
            populate_magnitude_tabs();
                    
            this.anchor_summary.requestLayout();
            this.anchor_summary.layout();
        
            return;
        }
       
        this.lblLat.setText(String.format("%5.2f", getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getLat()));
        this.lblLon.setText(String.format("%5.2f", getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getLon()));
        this.lblDep.setText(getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getDepth() + " km");
               
        lblIDs.setText("Event id: " + getEvent_on_Map().getInnerObjectEvent().getId() +   "     Origin id:" + getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getId());
                
        if ((getEvent_on_Map()!=null) && (getEvent_on_Map().getInnerObjectEvent().getOrigins()!=null) && (!Event_on_Map.getInnerObjectEvent().getOrigins().isEmpty())){
            if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrH()!=null){
                lblErrH.setText(String.format("%5.2f",getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrH()) + " km");
                lblErrH.setVisible(true);
                lblErrLAT.setVisible(false);
                lblErrLON.setVisible(false);
                lblErrLON_plusminus.setVisible(false);
            } else {
                lblErrH.setText("??.??");
                lblErrH.setVisible(false);
                
                if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrLat()!=null){
                    lblErrLAT.setText(String.format("%5.2f",getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrLat()) + " km");
                    lblErrLAT.setVisible(true);
                } else {
                    lblErrLAT.setText("??.??");
                    lblErrLAT.setVisible(false);
                }
                
                if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrLon()!=null){
                    lblErrLON.setText(String.format("%5.2f",getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrLon()) + " km");
                    lblErrLON.setVisible(true);
                    lblErrLON_plusminus.setVisible(true);
                } else {
                    lblErrLON.setText("??.??");
                    lblErrLON.setVisible(false);
                    lblErrLON_plusminus.setVisible(false);
                } 
            }
            
            if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrZ()!=null){
                lblErrZ.setText(String.format("%5.2f",getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrZ()) + " km");
               
            } else {
                if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getErrDepth()!=null){
                    
                } else
                    lblErrZ.setText("??.??"); 
            }
            String qq = getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getQuality();
            if (qq!=null && !qq.equals("??")){      
                ShowQualityRatings(qq); 
            } else {
                lblQ1.setVisible(false);
                lblQ2.setVisible(false);
            }

            if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getRegion()!=null)
                this.lblArea.setText(getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getRegion());
            else
                this.lblArea.setText("Unknown area");

            //if (Event_on_Map.getInnerObjectEvent().getOrigins().get(0).getOt()!=null)
            if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getOt()!=null)
                this.lblOT.setText("ORIGIN TIME: " + getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getOt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS").withZone(ZoneId.of("UTC"))));
            else
                this.lblOT.setText("ORIGIN TIME: ** Unknown **");

            if (getEvent_on_Map().getInnerObjectEvent().getOrigins().get(0).getMagnitudes()!=null)
                populate_magnitude_tabs();
        }
        //
        this.anchor_summary.requestLayout();
        this.anchor_summary.layout();
        
    } catch (Exception ex){
        sitDialog.ShowErrorMessage("Error in event summary parameters display.\n" + ex.getMessage(), PrimaryStage);
    }
    
}
//------------------------------------------------------------------------------
    private void make_residuals_chart(){
        //final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//      
//        resChart.getXAxis().setLabel("Km");
//        resChart.getYAxis().setLabel("Sec.");

        resChart.getData().clear();
//
        this.resChart.setTitle("Residuals by distance");
//        xAxis.setLabel("Km");       
//        yAxis.setLabel("Sec.");
        
        
//               
        XYChart.Series seriesP_green = new XYChart.Series();
        seriesP_green.setName("Pg"); 
       // seriesP_green.setNode(rootIcon);
        
        XYChart.Series seriesP_orange = new XYChart.Series();
        seriesP_orange.setName("Po");
        XYChart.Series seriesP_red = new XYChart.Series();
        seriesP_red.setName("Pr");
        
        XYChart.Series seriesS_green = new XYChart.Series();
        seriesS_green.setName("Sg"); 
        XYChart.Series seriesS_orange = new XYChart.Series();
        seriesS_orange.setName("So");
        XYChart.Series seriesS_red = new XYChart.Series();
        seriesS_red.setName("Sr");   
//        
        float res;
        float d;
        for (int i = 0; i < getEvent_on_Map().getNStations(); i++) {
            if (getEvent_on_Map().getStation(i).getNPhases()>0) {
                for (int k=0; k < getEvent_on_Map().getStation(i).getNPhases(); k++) {
                    if ((!Event_on_Map.getStation(i).getPhase(k).getIscCode().equalsIgnoreCase("A")) && (Event_on_Map.getStation(i).getPhase(k).getEpDistanceKm()!=null) && (Event_on_Map.getStation(i).getPhase(k).getEpDistanceKm() < 9900.0)) {
                        if (getEvent_on_Map().getStation(i).getPhase(k).getResidual()!=null)
                            res=getEvent_on_Map().getStation(i).getPhase(k).getResidual().floatValue();
                        else
                            res =(float)-9.9;
                        if (getEvent_on_Map().getStation(i).getPhase(k).getEpDistanceKm() != null) 
                            d=getEvent_on_Map().getStation(i).getPhase(k).getEpDistanceKm();
                        else d=(float) -9.9;
                        if (getEvent_on_Map().getStation(i).getPhase(k).getIscCode().contains("P")) {
                            if ((0.0 <= Math.abs(res)) && (Math.abs(res) < 0.5)){
                                seriesP_green.getData().add(new XYChart.Data(d, res));
                                } else if ((0.5 <= Math.abs(res)) && (Math.abs(res) < 1.0)){
                                    seriesP_orange.getData().add(new XYChart.Data(d, res));
                                } else seriesP_red.getData().add(new XYChart.Data(d, res));  
                        }else{
                            if (getEvent_on_Map().getStation(i).getPhase(k).getIscCode().contains("S")) {
                                if ((0.0 <= Math.abs(res)) && (Math.abs(res) < 0.5)){
                                    seriesS_green.getData().add(new XYChart.Data(d, res));
                                    } else if ((0.5 <= Math.abs(res)) && (Math.abs(res) < 1.0)){
                                        seriesS_orange.getData().add(new XYChart.Data(d, res));
                                    } else seriesS_red.getData().add(new XYChart.Data(d, res));  
                            }  
                        }         
                    }
                }
            }       
        }
// 
        this.resChart.getData().addAll(seriesP_green, seriesP_orange, seriesP_red,
                seriesS_green, seriesS_orange, seriesS_red);
        
        this.resChart.setAlternativeRowFillVisible(true);
        this.resChart.setLegendVisible(true);
        
        if (PrimaryStage!=null) {
            try {
                String  path = new File(".").getCanonicalPath() + File.separator;
                PrimaryStage.getScene().getStylesheets().add("file://" + path +"style_selection_rectangle.css");
            } catch (IOException ex) {
                Logger.getLogger(MapFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
//        
        Set<Node> nodesP_green = this.resChart.lookupAll(".series" + 0);                
        for (Node n : nodesP_green) {
            n.setStyle("""
                       -fx-background-color: green, white;
                           -fx-background-insets: 0, 2;
                           -fx-shape: null;
                           -fx-background-radius: 3px;
                           -fx-padding: 3px;""");
            this.resChart.getData().get(0).setNode(n);
        }
//        
        Set<Node> nodesP_orange = this.resChart.lookupAll(".series" + 1);                
        for (Node n : nodesP_orange) {
            n.setStyle("""
                       -fx-background-color: orange, white;
                           -fx-background-insets: 0, 2;
                           -fx-background-radius: 3px;
                           -fx-shape: null;
                           -fx-padding: 3px;""");
            this.resChart.getData().get(1).setNode(n);
            
        }
//        
        Set<Node> nodesP_red = this.resChart.lookupAll(".series" + 2);                
        for (Node n : nodesP_red) {
            n.setStyle("""
                       -fx-background-color: red, white;
                           -fx-background-insets: 0, 2;
                           -fx-background-radius: 3px;
                           -fx-shape: null;
                           -fx-padding: 3px;""");
            this.resChart.getData().get(2).setNode(n);
        }             
//        
        Set<Node> nodesS_green = this.resChart.lookupAll(".series" + 3);                
        for (Node n : nodesS_green) {
            n.setStyle("""
                       -fx-background-color: green, white;
                           -fx-background-insets: 0, 2;
                           -fx-background-radius: 3px;
                           -fx-shape: null;
                           -fx-padding: 3px;""");
            this.resChart.getData().get(0).setNode(n);
        }
//        
        Set<Node> nodesS_orange = this.resChart.lookupAll(".series" + 4);                
        for (Node n : nodesS_orange) {
            n.setStyle("""
                       -fx-background-color: orange, white;
                           -fx-background-insets: 0, 2;
                           -fx-background-radius: 3px;
                           -fx-shape: null;
                           -fx-padding: 3px;""");
            this.resChart.getData().get(1).setNode(n);
            
        }
//        
        Set<Node> nodesS_red = this.resChart.lookupAll(".series" + 5);                
        for (Node n : nodesS_red) {
            n.setStyle("""
                       -fx-background-color: red, white;
                           -fx-background-insets: 0, 2;
                           -fx-background-radius: 3px;
                           -fx-shape: null;
                           -fx-padding: 3px;""");
            this.resChart.getData().get(2).setNode(n);
        }         
        
       resChart.setCursor(Cursor.CROSSHAIR);
       resChart.requestFocus();
       if (myResidualSelector!=null) myResidualSelector.InitSelector(resChart, anchor_lower_right);   
    }
//------------------------------------------------------------------------------    
    private boolean populate_phases_list(){
        try { 

            int i, j;
            Phases_List_items tmpItem;
            ObjectArrival tmpPh;
            float r,w,a, d;
            int qc;
            table_phases.getColumns().clear();
           //
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (getEvent_on_Map()==null) return true;
            if (getEvent_on_Map().getNStations()==0) return true;
            //---------------------------------------------
            for (i=0; i<getEvent_on_Map().getNStations(); i++) {
                if (getEvent_on_Map().getStation(i).getNPhases()>0) {
                    for (j=0; j<getEvent_on_Map().getStation(i).getNPhases(); j++) {
                        tmpPh = getEvent_on_Map().getStation(i).getPhase(j);

                        if (tmpPh.getResidual()==null) 
                            r=(float)-9.9;
                        else r= tmpPh.getResidual().floatValue();

                        if (tmpPh.getWeight()==null) 
                            w=(float)-9.9;
                        else w= tmpPh.getWeight().floatValue();    
                        if (tmpPh.getAzimut()==null) 
                            a=(float)-9.9;
                        else a= tmpPh.getAzimut().floatValue();

                        if (tmpPh.getEpDistanceKm()==null) 
                            d=(float)-9.9;
                        else d= tmpPh.getEpDistanceKm();
                        
                        if (tmpPh.getPick().getQualityClass()==null) 
                            qc=8;
                        else qc= tmpPh.getPick().getQualityClass();

                        tmpItem= new Phases_List_items(getEvent_on_Map().getStation(i).getCode(),
                                tmpPh.getPick().getCha(),tmpPh.getIscCode(),
                                tmpPh.getPick().getArrivalTime().format(DateTimeFormatter.ISO_TIME).replace("Z", ""), 
                                r,
                                w,
                                d,
                                a,
                                qc);
                        dati.add(tmpItem);
                    }
                }
            }
            //---------------------------------------------
            //
            TableColumn stationCol = new TableColumn("Sta");
            TableColumn channelCol = new TableColumn("Chan");
            TableColumn phaseCol = new TableColumn("Phase");
            TableColumn timeCol = new TableColumn("Time");
            TableColumn resCol = new TableColumn("Residual");
            TableColumn weightCol = new TableColumn("Weight");
            TableColumn qcCol = new TableColumn("QC");
            TableColumn deltaCol = new TableColumn("Delta");
            TableColumn azimutCol = new TableColumn("Azimut");

            stationCol.setCellValueFactory(
                new PropertyValueFactory<>("station_code")
            );
           // resCol.setStyle( "-fx-alignment: LEFT;");

            channelCol.setCellValueFactory(
                new PropertyValueFactory<>("channel_code")
            );
            channelCol.setStyle( "-fx-alignment: CENTER;");

            phaseCol.setCellValueFactory(
                new PropertyValueFactory<>("pick_name")
            );
            phaseCol.setStyle( "-fx-alignment: CENTER;");

            timeCol.setCellValueFactory(
                new PropertyValueFactory<>("pick_time")
            );
            timeCol.setStyle( "-fx-alignment: CENTER;");

            resCol.setCellValueFactory(
                new PropertyValueFactory<>("residual")
            );
            resCol.setStyle( "-fx-alignment: CENTER;");

            weightCol.setCellValueFactory(
                new PropertyValueFactory<>("weight")
            );
            weightCol.setStyle( "-fx-alignment: CENTER;");

            qcCol.setCellValueFactory(
                new PropertyValueFactory<>("qualityclass_weight")
            );
            qcCol.setStyle( "-fx-alignment: CENTER;");

//            weightCol.setCellFactory(column -> {
//                return new TableCell<Phases_List_items, Float>() {
//                    @Override
//                    protected void updateItem(Float item, boolean empty) {
//                        super.updateItem(item, empty);
//
//                        setText(empty ? "" : getItem().toString());
//                        setGraphic(null);
//
//                        TableRow<Phases_List_items> currentRow = getTableRow();
//
//                        if (currentRow!=null)  {  
//                            if (!isEmpty()){
//                                if(item<=0.0) 
//                                    currentRow.setStyle("-fx-background-color:rgb(103,115,122);");  
//                            } 
//                        }
//                    }
//                };
//            });


            deltaCol.setCellValueFactory(
                new PropertyValueFactory<>("delta")
            );
            deltaCol.setStyle( "-fx-alignment: CENTER;");

            azimutCol.setCellValueFactory(
                new PropertyValueFactory<>("azimut")
            );
            azimutCol.setStyle( "-fx-alignment: CENTER;");

            resCol.setCellFactory(column -> {
                //return new TableCell<Phase, Float>() {
                return new TableCell<ObjectArrival, Float>() {
                    @Override
                    protected void updateItem(Float item, boolean empty) {                        
                        super.updateItem(item, empty);
                        this.setAlignment(Pos.CENTER);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            // Format the residual
                            setText(item.toString());

                            //
                            if ((Math.abs(Double.valueOf(item)) >= 0.0) && (Math.abs(Double.valueOf(item)) < 0.5f)) {
                                setTextFill(javafx.scene.paint.Color.rgb(1, 148, 1));
                                setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                
                            } else  if ((Math.abs(Double.valueOf(item)) >= 0.5) && (Math.abs(Double.valueOf(item)) < 1.0)) { 
                                        setTextFill(Color.ORANGE); 
                                        setStyle("-fx-text-fill: rgb(255, 165, 0);");
                                
                                    } else {
                                            setTextFill(javafx.scene.paint.Color.rgb(200, 17, 17));
                                            setStyle("-fx-text-fill: rgb(200, 17, 17);");
                                        }
                            }
                        }
                    };
            });

            table_phases.setItems(dati);

            table_phases.getColumns().addAll(stationCol, channelCol, phaseCol, 
                timeCol, resCol, weightCol, qcCol, deltaCol, azimutCol);

             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------    
    private boolean populate_towns_list(){
        try {
            Towns_List_items tmpItem;
            //Town tmpTown;
            GetMunicipiDistanceKmPopolazione200ResponseDataInner tmpTown;
            table_towns.getColumns().clear();
            final ObservableList dati = FXCollections.observableArrayList();
    //        
            //---------------------------------------------------------
            if (getEvent_on_Map()==null) return true;
            if (getEvent_on_Map().getTownsInfo()==null) return true;
            //---------------------------------------------------------
            for (int i=0; i < getEvent_on_Map().getTownsInfo().size(); i++) {
                tmpTown = getEvent_on_Map().getTownsInfo().get(i);
                tmpItem= new Towns_List_items(tmpTown.getName(), tmpTown.getSiglaPro(), 
                        tmpTown.getDistKm(), tmpTown.getPopolazione());
                dati.add(tmpItem);
            }
    //  
            TableColumn nameCol = new TableColumn("City");
            TableColumn provCol = new TableColumn("Prov.");
            TableColumn deltaCol = new TableColumn("Distance");
            TableColumn populationCol = new TableColumn("Population");
    //       
            nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
            );
            //nameCol.setStyle( "-fx-alignment: LEFT;");

            provCol.setCellValueFactory(
                new PropertyValueFactory<>("prov")
            );
            provCol.setStyle( "-fx-alignment: CENTER;");

            deltaCol.setCellValueFactory(
                new PropertyValueFactory<>("delta")
            );
            deltaCol.setStyle( "-fx-alignment: CENTER;");

            populationCol.setCellValueFactory(
                new PropertyValueFactory<>("population")
            );
            populationCol.setStyle( "-fx-alignment: CENTER;");


            table_towns.setItems(dati);

            table_towns.getColumns().addAll(nameCol, provCol, deltaCol, populationCol);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }   
//------------------------------------------------------------------------------
    private void populate_AllLocations_TreeTableView(){
        TreeItem<ObjectMagnitudesOriginsEventsAndEventsGroup> dummyRoot = new TreeItem<>(null); 
        dummyRoot.setExpanded(true);
        treetableview_Events.setRoot(dummyRoot);
        treetableview_Events.setShowRoot(false);
        
        if (treetableview_Events.getColumns()!=null)
            treetableview_Events.getColumns().clear();
        
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, Long> treeTableColumn_id = new TreeTableColumn<>("ID");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_ot = new TreeTableColumn<>("OT");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_magnitude = new TreeTableColumn<>("Mag.");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_localspace = new TreeTableColumn<>("Localspace");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_region = new TreeTableColumn<>("Region");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_version = new TreeTableColumn<>("Version");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_quality = new TreeTableColumn<>("Quality");
        TreeTableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> treeTableColumn_typeevent = new TreeTableColumn<>("Type");
        
        treeTableColumn_id.setStyle( "-fx-alignment: baseline-center;");
        treeTableColumn_ot.setStyle( "-fx-alignment: baseline-center ;");
        treeTableColumn_magnitude.setStyle( "-fx-alignment: baseline-center ;");
        treeTableColumn_localspace.setStyle( "-fx-alignment: baseline-center ;");
        treeTableColumn_version.setStyle( "-fx-alignment: baseline-center ;");
        treeTableColumn_quality.setStyle( "-fx-alignment: baseline-center ;");
        treeTableColumn_typeevent.setStyle( "-fx-alignment: baseline-center ;");
        
        treeTableColumn_typeevent.setVisible(true);
        treeTableColumn_typeevent.setCellValueFactory(cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    if (ev.getTypeEvent()!= null){
                        return new SimpleStringProperty(ev.getTypeEvent());
                    }
                    else   
                        return new SimpleStringProperty("");
                        
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );
        
        // ID
        treeTableColumn_id.setCellValueFactory(new TreeItemPropertyValueFactory<>("id")); 
        treeTableColumn_id.setPrefWidth(40);
                  
        // version       
        treeTableColumn_version.setCellValueFactory(
            cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
//                Image green;
//                ImageView imageView=null;
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    if (ev.getOrigin().getTypeOrigin()!= null){
                        return new SimpleStringProperty(ev.getOrigin().getTypeOrigin().getVersionValue().toString()); // + "-" + ev.getOrigin().getTypeOrigin().getVersionName());
                    }
                    else   
                        return new SimpleStringProperty("");
                        
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );
        treeTableColumn_version.setPrefWidth(40);
        
        // localspace
        treeTableColumn_localspace.setCellValueFactory(new TreeItemPropertyValueFactory<>("localspace"));
        treeTableColumn_localspace.setPrefWidth(120);
        treeTableColumn_localspace.setCellValueFactory(
            cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    if (ev.getOrigin().getLocalspace()!= null){
                        return new SimpleStringProperty(ev.getOrigin().getLocalspace().getName()); // + "-" + ev.getOrigin().getTypeOrigin().getVersionName());
                    }
                    else if (ev.getLocalspace()!= null)  
                            return new SimpleStringProperty(ev.getLocalspace().getName());
                        else 
                        return new SimpleStringProperty("");
                        
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );

        // ot
        treeTableColumn_ot.setCellValueFactory(
            cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    //return new SimpleStringProperty(ev.getOrigin().getOt().toString().substring(11).replace("Z", ""));
                    //.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC")))
                    //LocalDateTime d = ev.getOrigin().getOt().toLocalDateTime();
                    
                    return new SimpleStringProperty(ev.getOrigin().getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"))));
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );
        // magnitude
        treeTableColumn_magnitude.setCellValueFactory(
            cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    if ((ev.getMagnitude()!=null) && (ev.getMagnitude().getMag()!=null))
                        return new SimpleStringProperty(String.valueOf(round(ev.getMagnitude().getMag(),2)));
                    else
                       return new SimpleStringProperty("");
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );
        
        // Quality
        treeTableColumn_quality.setCellValueFactory(
            cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    if (ev.getOrigin().getQuality()!= null){
                        return new SimpleStringProperty(ev.getOrigin().getQuality());
                    }
                    else   
                        return new SimpleStringProperty("");
                        
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );
        
        // region
        treeTableColumn_region.setCellValueFactory(
            cellData -> {
                TreeItem<?> item = cellData.getValue();
                Object data = item.getValue();
                if (data instanceof ObjectMagnitudesOriginsEventsAndEventsGroup) {
                    ObjectMagnitudesOriginsEventsAndEventsGroup ev = (ObjectMagnitudesOriginsEventsAndEventsGroup)data ;
                    return new SimpleStringProperty(ev.getOrigin().getRegion());
                } else {
                    return new SimpleStringProperty("");
                }
            }
        );
               
        treetableview_Events.getColumns().add(treeTableColumn_id);
        treetableview_Events.getColumns().add(treeTableColumn_ot);
        treetableview_Events.getColumns().add(treeTableColumn_magnitude);
        treetableview_Events.getColumns().add(treeTableColumn_localspace);
        treetableview_Events.getColumns().add(treeTableColumn_version);
        treetableview_Events.getColumns().add(treeTableColumn_typeevent);
        treetableview_Events.getColumns().add(treeTableColumn_quality);
        treetableview_Events.getColumns().add(treeTableColumn_region);
        
        final PseudoClass topNode = PseudoClass.getPseudoClass("top-node");
        final PseudoClass level1Node = PseudoClass.getPseudoClass("level1-node");
        final PseudoClass level2Node = PseudoClass.getPseudoClass("level2-node");
        final PseudoClass reviewedNode = PseudoClass.getPseudoClass("reviewed-node");
        final PseudoClass disabledNode = PseudoClass.getPseudoClass("disabled-node");
        final PseudoClass selectedNode = PseudoClass.getPseudoClass("selected");
        treetableview_Events.setRowFactory(t -> {
            final TreeTableRow<ObjectMagnitudesOriginsEventsAndEventsGroup> row = new TreeTableRow<>();

            // every time the TreeItem changes, check, if the new item is a
            // child of the root and set the pseudoclass accordingly
            row.treeItemProperty().addListener((o, oldValue, newValue) -> {
                boolean tn = false;
                boolean level1node=false;
                boolean level2node=false; 
                boolean reviewednode = false;
                boolean disablednode = false;
                boolean selectednode = false;

                if (newValue != null) {
                    tn = newValue.getParent() == dummyRoot;
                    //leafnode = newValue.getParent().getParent() == dummyRoot;
                    //realleafnode = newValue.getParent().getParent().getParent() == dummyRoot;
                    if ((newValue.getParent()!=null) && (newValue.getParent().getParent()!=null))
                            level1node=true;
                    
                    if ((newValue.getParent()!=null) && (newValue.getParent().getParent()!=null) && (newValue.getParent().getParent().getParent()!=null))
                            level2node=true;
                                       
                    if (newValue.getValue().getOrigin().getTypeOrigin().getVersionValue()>=100) reviewednode=true;
                    if ((newValue.getValue().getTypeEvent()!=null) && (newValue.getValue().getTypeEvent().toUpperCase().contains("NOT EXISTING"))) disablednode = true;
                    
                    if (newValue.getValue().getId()==selectedEventId) selectednode=true;
                }
                row.pseudoClassStateChanged(topNode, tn);
                row.pseudoClassStateChanged(level1Node, level1node);
                row.pseudoClassStateChanged(level2Node, level2node);
                row.pseudoClassStateChanged(reviewedNode, reviewednode);
                row.pseudoClassStateChanged(disabledNode, disablednode);
                row.pseudoClassStateChanged(selectedNode, selectednode);
            });
            return row;
        });    
//
        ObservableList<ObjectMagnitudesOriginsEventsAndEventsGroup> prova = FXCollections.observableArrayList(Locations_level1);

        FilteredList<ObjectMagnitudesOriginsEventsAndEventsGroup> filtered_level1=new FilteredList<>(prova, p -> true);
        txtFilterRegion.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered_level1.setPredicate(EV -> {
                    // If filter text is empty, display all locations
                    if (newValue == null || newValue.isEmpty()) {
                            return true;
                    }

                    if ((EV.getOrigin().getRegion()!=null) && (EV.getOrigin().getRegion().toUpperCase().contains(newValue.toUpperCase()))) {
                            return true; // Filter matches first name.
                    } else
                     return false; // Does not match.
            });

            dummyRoot.getChildren().clear();
            for (int i=0; i<filtered_level1.size(); i++){
                filtered_level1.get(i).setLocalspace(filtered_level1.get(i).getLocalspace()); //.getName() + "-" + filtered_level1.get(i).getIdLocalspace());
                dummyRoot.getChildren().add(new TreeItem(filtered_level1.get(i)));
            } 
        });
        txtFilterLocalspace.textProperty().addListener((observable, oldValue, newValue) -> {
            filtered_level1.setPredicate(EV -> {
                    // If filter text is empty, display all locations
                    if (newValue == null || newValue.isEmpty()) {
                            return true;
                    }

                    if ((EV.getOrigin().getLocalspace()!=null) && (EV.getOrigin().getLocalspace().getName().toUpperCase().contains(newValue.toUpperCase()))) {
                            return true; // Filter matches first name.
                    } else
                     return false; // Does not match.
            });

            dummyRoot.getChildren().clear();
            for (int i=0; i<filtered_level1.size(); i++){
                filtered_level1.get(i).setLocalspace(filtered_level1.get(i).getLocalspace()); // + "-" + filtered_level1.get(i).getIdLocalspace());
                dummyRoot.getChildren().add(new TreeItem(filtered_level1.get(i)));
            } 
        });
         
        // In case of not filters editing:
        for (int i=0; i<filtered_level1.size(); i++){
            filtered_level1.get(i).setLocalspace(filtered_level1.get(i).getLocalspace()); // + "-" + filtered_level1.get(i).getIdLocalspace());
            dummyRoot.getChildren().add(new TreeItem(filtered_level1.get(i)));
        } 
         
    }
    
    
    private void SelectLastModifiedTableRow(){
        try {
            Platform.runLater(() -> {           
                int id = find_last_modified();               
                SelectRow(id);
            });
        } catch (Exception ex) {
        }
    }
//--------------------------------------------------------------------------------    
public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}    
//--------------------------------------------------------------------------------    
    private int find_last_modified(){
        int res=-1;
        OffsetDateTime lu = OffsetDateTime.MIN;
        for (int i=0; i < Locations_level1.size(); i++){         
            if (Locations_level1.get(i).getModified().isAfter(lu)) {
                lu = Locations_level1.get(i).getModified();
                res = i;
            }
        }
        return res;
    }
//--------------------------------------------------------------------------------        
    private void ReleaseResources(){
        try{
            this.setEvent_on_Map(null);
            if (this.getMH()!=null){
                if (getMH().getMap()!=null) getMH().close(); // MH.getMap().dispose();
            }
            System.gc();
        } catch (Exception ex){}
    }    

    /**
     * @return the PrimaryStage
     */
    public Stage getPrimaryStage() {
        return PrimaryStage;
    }

    /**
     * @param PrimaryStage the primaryStage to set
     */
    public void setPrimaryStage(Stage primaryStage) {
        PrimaryStage = primaryStage;
        
        
    }

    /**
     * @return the MH
     */
    public MapHandler getMH() {
        return MH;
    }

    /**
     * @param MH the MH to set
     */
    public void setMH(MapHandler MH) {
        this.MH = MH;
    }

    @FXML
    private void mnuFile_UpdateNetwork(ActionEvent event) {
        File tmp;
        try {
            PrimaryStage.getScene().setCursor(Cursor.WAIT);
            
            tmp = new File( "networks" );
            if( tmp.exists() && tmp.isDirectory() ){
                tmp.renameTo(new File("networks_backup"));
            }

            boolean res = App.G.SeismicNet.read(true);
            while (App.G.StillReadingStations()) {
                Thread.sleep(1);
            }
            
            if (res) {  
                deleteDirectory(new File( "networks_backup" ));    
            }
            else
                (new File( "networks_backup" )).renameTo((new File("networks")));
        } catch (Exception ex){
            Logger.getLogger("org.ingv.sit.MapFormController.mnuFile_UpdateNetwork").log(java.util.logging.Level.SEVERE, "Error updating network!!");
        } finally {
            PrimaryStage.getScene().setCursor(Cursor.DEFAULT);
            tmp=null;
        }
  
    }
    
    
    public static void deleteDirectory(File directory) {
        if (!directory.exists()) {
            return;
        }
        
        // Verifica se è una directory
        if (directory.isDirectory()) {
            // Recupera tutti i file nella directory
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    // Chiamata ricorsiva per eliminare tutti i file/directory contenuti
                    deleteDirectory(file);
                }
            }
        }
        
        // Elimina la directory vuota o il file
        directory.delete();
    }

    
    
    private void SearchOrigins(){
        if (!SearchOriginsInputValidation()){
            sitDialog.ShowErrorMessage("Please check search settings.", PrimaryStage);
            return;
        }
       
        try {
            OffsetDateTime start, end;
                      
            java.time.LocalDateTime NOW = java.time.LocalDateTime.now();
            if ((limit_search)&&(App.G.options.get_hours_before_now()>0))
                start = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), NOW.getHour()-App.G.options.get_hours_before_now(), NOW.getMinute(), NOW.getSecond(), 0, ZoneOffset.UTC);
             else
                start = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
            
            if ((NOW.getYear()==calendar.getValue().getYear()) && (NOW.getMonthValue()==calendar.getValue().getMonthValue()) && (NOW.getDayOfMonth()==calendar.getValue().getDayOfMonth()))
                end = null;
            else
                end = OffsetDateTime.of(calendar.getValue().getYear(), calendar.getValue().getMonthValue(), calendar.getValue().getDayOfMonth(), 23, 59, 59, 0, ZoneOffset.UTC);
           
            boolean simple_date_format=false;           
            if ((end ==null) || (start.getDayOfYear()==end.getDayOfYear())) simple_date_format=true;

            GetEventsGroup200Response resp = read_origins_advanced(start, end, null, null, null);

            if (resp!=null){
                
                App.logger.debug("WS-LOG: reader.getMagnitudes received response");

                PopulateCARAVELOriginsTable(resp.getData(), simple_date_format);

                titled_pane_settings.setExpanded(false);
            }
        } catch (Exception ex) {
            //
            int k=0;
            k++;
        }   
    }
       
    private GetEventsGroup200Response read_origins_advanced(OffsetDateTime start, OffsetDateTime end, 
                    OffsetDateTime last_modified_datetime_event,
                    OffsetDateTime last_modified_datetime_origin,
                    OffsetDateTime last_modified_datetime_magnitude){
        GetApi reader;
        try {        
            reader = new GetApi();
                
                App.logger.debug("WS-LOG: reader.getMagnitudes reading");
                GetEventsGroup200Response resp=null;
                switch (search_route){
                    case "/magnitudes":
                        resp = reader.getMagnitudes(start, end, 
                            null, null, null, null, null, null, 
                            null, null, null, 
                            null, null,null, null, null, 
                            null,null, null,
                            null,null, null,
                            null, null, null, null,
                            null, null,null, null, null, null,
                            App.G.options.getList_of_searched_environments(), 
                            App.G.options.getList_of_searched_environments(),
                            App.G.options.getList_of_searched_environments(), 
                            null,
                            null, 
                            null,
                            last_modified_datetime_event,
                            last_modified_datetime_origin, 
                            last_modified_datetime_magnitude, 
                            null, null, 
                            CaravelSearchOrder,null, 
                            null,null, 
                            null, null, null, null); 
                        break;
                    case "/events":
                        resp = reader.getEvents(start, end, 
                            null, null, null, null, null, null, 
                            null, null, null, 
                            null, null,null, null, null, 
                            null,null, null,
                            null,null, null,
                            null, null,null, null,
                            null, null,null, null,null, null,null, null, null,
                            App.G.options.getList_of_searched_localspaces_toString(),
                            null, null,
                            last_modified_datetime_event,
                            last_modified_datetime_origin, 
                            last_modified_datetime_magnitude,  
                            null, null,
                            CaravelSearchOrder,null, 
                            null,null, 
                            null, null, null, null); 
                        break;
                    case "/all":
                        resp = reader.getAll(start, end, 
                            null, null, null, null, null, null, 
                            null, null, null, 
                            null, null,null, null, null, 
                            null,null, null,
                            null,null, null,
                            null, null,null, null,
                            null, null,null, null,null, null,
                            App.G.options.getList_of_searched_environments(), 
                            App.G.options.getList_of_searched_environments(),
                            App.G.options.getList_of_searched_environments(), 
                            null,
                            null, null,
                            last_modified_datetime_event,
                            last_modified_datetime_origin, 
                            last_modified_datetime_magnitude, 
                            null, null,
                            CaravelSearchOrder,null, 
                            null,null, 
                            null, null, null, null); 
                        break;
                    case "/origins":
                        resp = reader.getOrigins(start, end, 
                            null, null, null, null, null, null, 
                            null, null, null, 
                            null, null,null, null, null, 
                            null,null, null,
                            null,null, null,
                            null, null,null, null,
                            null, null,null, null,null, null,
                            App.G.options.getList_of_searched_environments(), 
                            App.G.options.getList_of_searched_environments(),
                            App.G.options.getList_of_searched_environments(), 
                            null,
                            null, null,
                            last_modified_datetime_event,
                            last_modified_datetime_origin, 
                            last_modified_datetime_magnitude, 
                            null, null,
                            CaravelSearchOrder,null, 
                            null,null, 
                            null, null, null,null); 
                        break;
                        
                }
                
            return resp;
        } catch (Exception ex) {
            return null;
        } finally {
            reader = null;
        }
    }
    
    private boolean SearchOriginsInputValidation(){
        try {
            if ((!txtCenterLat.getText().isBlank()) && ((Float.valueOf(txtCenterLat.getText()) > 90.0) || (Float.valueOf(txtCenterLat.getText()) < -90.0))) return false;
            if ((!txtCenterLon.getText().isBlank()) && ((Float.valueOf(txtCenterLon.getText()) > 180.0) || (Float.valueOf(txtCenterLon.getText()) < -180.0))) return false; 
            
            if ((!txtLatMin.getText().isBlank()) && ((Float.valueOf(txtLatMin.getText()) > 90.0) || (Float.valueOf(txtLatMin.getText()) < -90.0))) return false;
            if ((!txtLonMin.getText().isBlank()) && ((Float.valueOf(txtLonMin.getText()) > 180.0) || (Float.valueOf(txtLonMin.getText()) < -180.0))) return false; 
            
            if ((!txtRadiusMin.getText().isBlank()) && (Float.valueOf(txtRadiusMin.getText()) < 0.0)) return false;
            if ((!txtRadiusMax.getText().isBlank()) && (Float.valueOf(txtRadiusMax.getText()) < 0.0)) return false;
               
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
//------------------------------------------------------------------------------    
    private void PopulateFDSNOriginsTable(TableView<FDSN_Events_List_items> table, 
            List<FDSN_Events_List_items> lista_origins, 
            boolean simple_date_format){
        table.getColumns().clear();
        final ObservableList<FDSN_Events_List_items> dati = FXCollections.observableArrayList();
    //        
        //---------------------------------------------------------
        if (lista_origins==null) return;
        if (lista_origins.isEmpty()) return ;
        //---------------------------------------------------------
        for (int i=0; i < lista_origins.size(); i++) { 
            //if ((lista_origins.get(i).getOrigin().getTypeOrigin().getVersionValue()==null)||(App.G.options.list_of_searched_versions.contains(lista_origins.get(i).getOrigin().getTypeOrigin().getVersionValue().toString())))
            if ((lista_origins.get(i).getVersion()==null)||(App.G.options.list_of_searched_versions.contains(lista_origins.get(i).getVersion())))
                dati.add(lista_origins.get(i));       
        }
    //  
      //  TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> flagsCol = new TableColumn("FLAGS");
        TableColumn<FDSN_Events_List_items, String> idCol = new TableColumn("ID");
        TableColumn<FDSN_Events_List_items, String> otCol = new TableColumn("Time");
        TableColumn<FDSN_Events_List_items, String> magCol = new TableColumn("Mag");
        TableColumn<FDSN_Events_List_items, String> qualCol = new TableColumn("Qual");
        TableColumn<FDSN_Events_List_items, String> regionCol = new TableColumn<>("Region");
        TableColumn<FDSN_Events_List_items, String> versionCol = new TableColumn("Version");
        TableColumn<FDSN_Events_List_items, String> localspaceCol = new TableColumn("Localspace");
        
//       
//        flagsCol.setCellValueFactory(
//            cellData -> {
//                if (cellData.getValue().getFlags()!= null){
//                    return new SimpleStringProperty(cellData.getValue().getFlags());
//                }
//                else   
//                    return new SimpleStringProperty(""); 
//            }
//        );      

        idCol.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
    
        otCol.setCellValueFactory(
//            cellData -> {
//                if (simple_date_format)
//                    return new SimpleStringProperty(cellData.getValue().getOrigin().getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.of("UTC")))); 
//                else
//                    return new SimpleStringProperty(cellData.getValue().getOrigin().getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC")))); 
//            }
            cellData -> {
                
                return new SimpleStringProperty(cellData.getValue().getOt()); 
            }
        );
            
        otCol.setStyle( "-fx-alignment: CENTER;");
//
        // magnitude
        magCol.setCellValueFactory(
            cellData -> {
//                ObjectMagnitudesOriginsEventsAndEventsGroup item = cellData.getValue();    
//                if ((item.getMagnitude()!=null) && (item.getMagnitude().getMag()!=null)) 
//                    return new SimpleStringProperty(String.valueOf(round(item.getMagnitude().getMag(),2)));
//                else
                   return new SimpleStringProperty(cellData.getValue().getMag());
                
            }
        );
        magCol.setStyle( "-fx-alignment: CENTER;");
//
        // region
        regionCol.setCellValueFactory(
            cellData -> {
                return new SimpleStringProperty(cellData.getValue().getRegion());
            }
        );
//
        // version
        versionCol.setCellValueFactory(
            cellData -> {
//                if (cellData.getValue().getOrigin().getTypeOrigin()!= null){
//                    return new SimpleStringProperty(cellData.getValue().getOrigin().getTypeOrigin().getVersionValue() + "-" + cellData.getValue().getOrigin().getTypeOrigin().getVersionName());
//                }
//                else   
                    return new SimpleStringProperty(cellData.getValue().getVersion()); 
            }
        );      
        versionCol.setCellFactory(new Callback<TableColumn<FDSN_Events_List_items, String>, TableCell<FDSN_Events_List_items, String>>() {
            public TableCell call(TableColumn param) {
                return new TableCell<FDSN_Events_List_items, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            TableRow<FDSN_Events_List_items> currentRow = getTableRow();
                            int vNumber;
                            try{
                                vNumber = Integer.parseInt(item.split("-")[0]);
                            } catch (Exception ex){
                                vNumber = -9;
                            }
                            switch (vNumber){
                                case 0: {
                                    setTextFill(Color.GRAY);
                                    setStyle("-fx-text-fill: rgb(211, 211, 211);");
                                    break;
                                }
                                case 1:{
                                    setTextFill(Color.GRAY);
                                    setStyle("-fx-text-fill: rgb(211, 211, 211);");
                                    break;
                                }
                                case 2 : {
                                    setTextFill(Color.RED);
                                    setStyle("-fx-text-fill: rgb(200, 17, 17);");
                                    if (currentRow!=null)
                                        currentRow.setTextFill(Color.RED);                                        
                                        currentRow.setStyle("-fx-text-fill: rgb(200, 17, 17);");
                                    break;
                                }
                                case 100 : {
                                    
                                    setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                    
                                    if (currentRow!=null)
                                        currentRow.setTextFill(Color.GREEN);
                                        currentRow.setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                    break;
                                }
                                case 1000: {
                                    setTextFill(Color.GREEN);
                                    setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                    if (currentRow!=null)
                                        currentRow.setTextFill(Color.GREEN);
                                        currentRow.setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                    break;
                                }
                                default :{
                                    this.setTextFill(Color.ORANGE);
                                    setStyle("-fx-text-fill: rgb(255, 69, 0);");
                                } 
                                            
                            }
                                                       
                            setText(item);
                        }
                    }
                };
            }
        });
//        



        // quality
        qualCol.setCellValueFactory(
            cellData -> {
//                if (cellData.getValue().getOrigin().getQuality()!= null){
//                    return new SimpleStringProperty(cellData.getValue().getOrigin().getQuality());
//                }
//                else   
                    return new SimpleStringProperty(cellData.getValue().getQual()); 
            }
        );  
        qualCol.setStyle( "-fx-alignment: CENTER;");
        
        // Localspace
        localspaceCol.setCellValueFactory(
            cellData -> {
                if (cellData.getValue().getLocalspace()!= null){
                    return new SimpleStringProperty(cellData.getValue().getLocalspace());
                }
                else   
                    return new SimpleStringProperty(""); 
            }
        );
//
        table.setItems(dati);
        //tblOrigins.getColumns().addAll(firstColumn, flagsCol, idCol, otCol, magCol, regionCol, versionCol,qualCol, localspaceCol);        
        table.getColumns().addAll( idCol, otCol, magCol, regionCol, versionCol,qualCol, localspaceCol);        
//    
        table.setRowFactory(new Callback<TableView<FDSN_Events_List_items>, TableRow<FDSN_Events_List_items>>() {
            @Override
            public TableRow<FDSN_Events_List_items> call(TableView<FDSN_Events_List_items> tableView) {
                final TableRow<FDSN_Events_List_items> row = new TableRow<FDSN_Events_List_items>() {
                    @Override
                    protected void updateItem(FDSN_Events_List_items origin_item, boolean empty){
                        super.updateItem(origin_item, empty);
//                        if ((origin_item!=null) && (origin_item.getOrigin().getTypeOrigin()!=null) && (origin_item.getOrigin().getTypeOrigin().getVersionValue()==100))
//                            getStyleClass().add("highlightedRow");
//                        else
//                            getStyleClass().removeAll(Collections.singleton("highlightedRow"));
                    }
                };
                return row;
            }
        });
    }
    
    private void PopulateCARAVELOriginsTable(List<ObjectMagnitudesOriginsEventsAndEventsGroup> lista_origins, boolean simple_date_format){
        tblOrigins.getColumns().clear();
        final ObservableList<ObjectMagnitudesOriginsEventsAndEventsGroup> dati = FXCollections.observableArrayList();
    //        
        //---------------------------------------------------------
        if (lista_origins==null) return;
        if (lista_origins.isEmpty()) return ;
        //---------------------------------------------------------
        for (int i=0; i < lista_origins.size(); i++) { 
            if (App.G.options.list_of_searched_versions.contains(lista_origins.get(i).getOrigin().getTypeOrigin().getVersionValue().toString()))
                dati.add(lista_origins.get(i));       
        }
    //  
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> flagsCol = new TableColumn("FLAGS");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, Long> idCol = new TableColumn("ID");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> otCol = new TableColumn("Time");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> magCol = new TableColumn("Mag");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> qualCol = new TableColumn("Qual");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> regionCol = new TableColumn<>("Region");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> versionCol = new TableColumn("Version");
        TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String> localspaceCol = new TableColumn("Localspace");
        
//       
        flagsCol.setPrefWidth(80);
        flagsCol.setCellValueFactory(
            cellData -> {
                if (cellData.getValue().getFlags()!= null){
                    return new SimpleStringProperty(cellData.getValue().getFlags());
                }
                else   
                    return new SimpleStringProperty(""); 
            }
        );      

        idCol.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
    
        otCol.setCellValueFactory(
            cellData -> {
                if (simple_date_format)
                    return new SimpleStringProperty(cellData.getValue().getOrigin().getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.of("UTC")))); 
                else
                    return new SimpleStringProperty(cellData.getValue().getOrigin().getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC")))); 
            }
        );
            
        otCol.setStyle( "-fx-alignment: CENTER;");
//
        // magnitude
        magCol.setCellValueFactory(
            cellData -> {
                ObjectMagnitudesOriginsEventsAndEventsGroup item = cellData.getValue();    
                if ((item.getMagnitude()!=null) && (item.getMagnitude().getMag()!=null)) 
                    return new SimpleStringProperty(String.valueOf(round(item.getMagnitude().getMag(),2)));
                else
                   return new SimpleStringProperty("");
                
            }
        );
        magCol.setStyle( "-fx-alignment: CENTER;");
//
        // region
        regionCol.setCellValueFactory(
            cellData -> {
                return new SimpleStringProperty(cellData.getValue().getOrigin().getRegion());
            }
        );
//
        // version
        versionCol.setCellValueFactory(
            cellData -> {
                if (cellData.getValue().getOrigin().getTypeOrigin()!= null){
                    return new SimpleStringProperty(cellData.getValue().getOrigin().getTypeOrigin().getVersionValue() + "-" + cellData.getValue().getOrigin().getTypeOrigin().getVersionName());
                }
                else   
                    return new SimpleStringProperty(""); 
            }
        );      
        versionCol.setCellFactory(new Callback<TableColumn<ObjectMagnitudesOriginsEventsAndEventsGroup, String>, TableCell<ObjectMagnitudesOriginsEventsAndEventsGroup, String>>() {
            public TableCell call(TableColumn param) {
                return new TableCell<ObjectMagnitudesOriginsEventsAndEventsGroup, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            TableRow<ObjectMagnitudesOriginsEventsAndEventsGroup> currentRow = getTableRow();
                            int vNumber;
                            try{
                                vNumber = Integer.parseInt(item.split("-")[0]);
                            } catch (Exception ex){
                                vNumber = -9;
                            }
                            switch (vNumber){
                                case 0: {
                                    setTextFill(Color.GRAY);
                                    setStyle("-fx-text-fill: rgb(211, 211, 211);");
                                    break;
                                }
                                case 1:{
                                    setTextFill(Color.GRAY);
                                    setStyle("-fx-text-fill: rgb(211, 211, 211);");
                                    break;
                                }
                                case 2 : {
                                    setTextFill(Color.RED);
                                    setStyle("-fx-text-fill: rgb(255, 69, 0);");
                                    if (currentRow!=null)
                                        currentRow.setTextFill(Color.RED);                                        
                                        //currentRow.setStyle("-fx-background-color:lightcoral");
                                    break;
                                }
                                case 100 : { 
                                    setTextFill(Color.GREEN);
                                    setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                    
                                    if (currentRow!=null)
                                        currentRow.setTextFill(Color.GREEN);
                                    
                                    break;
                                }
                                case 1000: {
                                    setTextFill(Color.GREEN);
                                    setStyle("-fx-text-fill: rgb(1, 148, 1);");
                                    if (currentRow!=null)
                                        currentRow.setTextFill(Color.GREEN);
                                    
                                    
                                    break;
                                }
                                default :{
                                    this.setTextFill(Color.ORANGE);
                                    setStyle("-fx-text-fill: rgb(255, 165, 0);");
                                } 
                                            
                            }
                                                       
                            setText(item);
                        }
                    }
                };
            }
        });
//        



        // quality
        qualCol.setCellValueFactory(
            cellData -> {
                if (cellData.getValue().getOrigin().getQuality()!= null){
                    return new SimpleStringProperty(cellData.getValue().getOrigin().getQuality());
                }
                else   
                    return new SimpleStringProperty(""); 
            }
        );  
        qualCol.setStyle( "-fx-alignment: CENTER;");
        
        // Localspace
        localspaceCol.setCellValueFactory(
            cellData -> {
                if (cellData.getValue().getLocalspace()!= null){
                    return new SimpleStringProperty(cellData.getValue().getLocalspace().getName());
                }
                else   
                    return new SimpleStringProperty(""); 
            }
        );
//
        tblOrigins.setItems(dati);
        //tblOrigins.getColumns().addAll(firstColumn, flagsCol, idCol, otCol, magCol, regionCol, versionCol,qualCol, localspaceCol);        
        tblOrigins.getColumns().addAll(flagsCol, idCol, otCol, magCol, regionCol, versionCol,qualCol, localspaceCol);        
//    
        tblOrigins.setRowFactory(new Callback<TableView<ObjectMagnitudesOriginsEventsAndEventsGroup>, TableRow<ObjectMagnitudesOriginsEventsAndEventsGroup>>() {
            @Override
            public TableRow<ObjectMagnitudesOriginsEventsAndEventsGroup> call(TableView<ObjectMagnitudesOriginsEventsAndEventsGroup> tableView) {
                final TableRow<ObjectMagnitudesOriginsEventsAndEventsGroup> row = new TableRow<ObjectMagnitudesOriginsEventsAndEventsGroup>() {
                    @Override
                    protected void updateItem(ObjectMagnitudesOriginsEventsAndEventsGroup origin_item, boolean empty){
                        super.updateItem(origin_item, empty);
//                        if ((origin_item!=null) && (origin_item.getOrigin().getTypeOrigin()!=null) && (origin_item.getOrigin().getTypeOrigin().getVersionValue()==100))
//                            getStyleClass().add("highlightedRow");
//                        else
//                            getStyleClass().removeAll(Collections.singleton("highlightedRow"));
                    }
                };
                return row;
            }
        });
    }
//------------------------------------------------------------------------------    
    private boolean populate_localspaces_list(ArrayList<Localspaces_List_items> items){
        try { 
            int i, j;
           // tblSearch_Localspaces.getColumns().clear();
            
            ObservableList dati = FXCollections.observableArrayList();
//          ---------------------------------------------
//           Check per sicurezza
//          ---------------------------------------------
            if (items==null) return true;
            if (items.isEmpty()) return true;
            int current_tab_index;
            for (i=0; i < items.size(); i++){
                current_tab_index = environment_tab_index(items.get(i).getEnvironment());
                if (current_tab_index==-1){
                    dati.clear();
                    // Adds a new environment tab
                    Tab nt = new Tab(items.get(i).getEnvironment().toUpperCase());
                    nt.setId("tab_env_" + items.get(i).getEnvironment());
                    
                    //----------------------------------------------------------
                    // Adds a checkbox in tab title
                    CheckBox cBox = new CheckBox();
                    if ((App.G.options.getList_of_searched_environments()!=null) && (App.G.options.getList_of_searched_environments().contains(items.get(i).getEnvironment())))
                        cBox.selectedProperty().setValue(Boolean.TRUE);
                    else
                        cBox.selectedProperty().setValue(Boolean.FALSE);
                    cBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                            if (new_val) {
                                if (App.G.options.getList_of_searched_environments()==null)
                                    App.G.options.setList_of_searched_environments(new ArrayList<>());
                                App.G.options.getList_of_searched_environments().add(nt.getText().toLowerCase());
                            } else {
                                if (App.G.options.getList_of_searched_environments()!=null)
                                    App.G.options.getList_of_searched_environments().remove(nt.getText().toLowerCase());
                            }

                        }
                    });
 
                    nt.setGraphic(cBox);
                    //----------------------------------------------------------
                    
                    TableView tv = new TableView();
                    //TableColumn usedCol, nameCol;
                    TableColumn nameCol;
                    //usedCol=new TableColumn();
                    nameCol=new TableColumn();
                     nameCol.setText("Name");
                    nameCol.setCellValueFactory(
                        new PropertyValueFactory<>("name")
                    );
                    
                    tv.getColumns().add(nameCol);
                    
                    nt.setContent(tv);
                    tabPane_environments.getTabs().add(nt);
                }
                
                if (current_tab_index==-1) current_tab_index = tabPane_environments.getTabs().size()-1;
                
                dati.add(new Localspaces_List_items(items.get(i).getEnvironment(), items.get(i).getName()));
                ((TableView)tabPane_environments.getTabs().get(current_tab_index).getContent()).getItems().add(dati.get(dati.size()-1));
            }
    //   
             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    private int environment_tab_index(String tab_name){
        try {
            if (this.tabPane_environments.getTabs()==null) return -1;
            if (this.tabPane_environments.getTabs().isEmpty()) return -1;
            for (int i=0; i<this.tabPane_environments.getTabs().size(); i++){
                if (this.tabPane_environments.getTabs().get(i).getText().equalsIgnoreCase(tab_name))
                    return i;
            }
            
            return -1;
        } catch (Exception ex){
            return -1;
        }
    }
    
    
//------------------------------------------------------------------------------    
    private boolean populate_versions_list(ArrayList<Versions_List_items> items){
        try { 
            int i, j;
            
            ObjectArrival tmpPh;
            tblSearch_Versions.getColumns().clear();
            
            final ObservableList dati = FXCollections.observableArrayList();
//            ---------------------------------------------
//             Check per sicurezza
//            ---------------------------------------------
            if (items==null) return true;
            if (items.isEmpty()) return true;
//            ---------------------------------------------
//            ---------------------------------------------
            for (i=0; i<items.size(); i++) {          
                dati.add(new Versions_List_items(items.get(i).getVersion_value(), items.get(i).getVersion_description()));      
            }
//            ---------------------------------------------
            
            TableColumn usedCol = new TableColumn("Used");
            TableColumn valueCol = new TableColumn("Value");
            TableColumn descCol = new TableColumn("Version description");

            descCol.setCellValueFactory(
                new PropertyValueFactory<>("version_description")
            );
            valueCol.setCellValueFactory(
                new PropertyValueFactory<>("version_value")
            );

            usedCol.setMinWidth(20);
            usedCol.setStyle( "-fx-alignment: CENTER;");
            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Versions_List_items, CheckBox>, ObservableValue<CheckBox>>() {

                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<Versions_List_items, CheckBox> arg0) {
    //
                    Versions_List_items lsItem = arg0.getValue();
                    
                    CheckBox checkBox = new CheckBox();
                    int idV = App.G.options.search_used_version_index(lsItem.getVersion_value());
                    if (idV>=0)
                        checkBox.selectedProperty().setValue(Boolean.TRUE);
                    else
                        checkBox.selectedProperty().setValue(Boolean.FALSE);

                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                            if (new_val) {
                                lsItem.setUsed(new SimpleStringProperty("TRUE")); 
                                
                                App.G.options.list_of_searched_versions.add(lsItem.getVersion_value());
                            } else {
                                lsItem.setUsed(new SimpleStringProperty("FALSE"));
                                App.G.options.list_of_searched_versions.remove(lsItem.getVersion_value());
                            }

                        }
                    });
    //
                    return new SimpleObjectProperty<CheckBox>(checkBox);
    //
                }

            });
    //          
            tblSearch_Versions.setItems(dati);
    //        
            tblSearch_Versions.getColumns().addAll(usedCol, valueCol, descCol);
    //   
             return true;   
        } catch (Exception ex) {
            return false;
        }
    }    
//------------------------------------------------------------------------------  
    private ArrayList<Localspaces_List_items> search_localspaces(){
        GetApi GA;
        try {
            ArrayList<Localspaces_List_items> res = new ArrayList<>();            
            GA = new GetApi();
                        
            App.logger.debug("WS-LOG: GA.getLocalspace reading");
            GetLocalspace200Response resp = GA.getLocalspace();
            App.logger.debug("WS-LOG: GA.getLocalspace received response");
            List<ObjectTableLocalspace> dati = resp.getData();
            
            if ((dati==null)||(dati.isEmpty()))return null;
            
            res.add(new Localspaces_List_items("production","sit-rev-origin"));
            for (int i =0; i< dati.size(); i++){
                res.add(new Localspaces_List_items(dati.get(i).getEnvironment().getValue(),dati.get(i).getName()));
            }
            
            return res;
        } catch (Exception ex){
            return null;
        } finally {
            GA = null;
        }
    }
//------------------------------------------------------------------------------  
    private ArrayList<Versions_List_items> search_versions(){
         GetApi GA;
        try {
            ArrayList<Versions_List_items> res = new ArrayList<>();
            
            GA = new GetApi();
            App.logger.debug("WS-LOG: GA.getTypeOrigin reading");
            
            
            Logger.getLogger("org.ingv.sit").log(Level.INFO, "WS-LOG: GA.getTypeOrigin reading");
            
            
            GetTypeOrigin200Response resp = GA.getTypeOrigin();
            App.logger.debug("WS-LOG: GA.getTypeOrigin received response");
            
            Logger.getLogger("org.ingv.sit").log(Level.INFO, "WS-LOG: GA.getTypeOrigin received response");
            
            List<ObjectTableTypeOrigin> dati = resp.getData();
            
            if ((dati==null)||(dati.isEmpty()))return null;
            for (int i =0; i< dati.size(); i++){
                res.add(new Versions_List_items(dati.get(i).getVersionValue().toString(), dati.get(i).getDescription()));
            }
            
            return res;
        } catch (Exception ex){
            return null;
        } finally {
            GA = null;
        }
    }

    @FXML
    private void btnSelectStations_Click(ActionEvent event) {
        getMH().setACTION("SELECT");
    }

    @FXML
    private void btnMapResetBounds_Click(ActionEvent event) {
        getMH().SetMapBounds(App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
            App.G.options.get_box_minLat(),App.G.options.get_box_maxLat());
        getMH().RefreshMap();
                                    
    }

    @FXML
    private void mnuRight_SetPreferred_Click(ActionEvent event) {
        System.out.println("Setto a preferito... " + this.selected_group_id);
    }

    @FXML
    private void treetableview_Events_mouse_click(MouseEvent event) {
//        if (App.G.options.isBulletinMode()){
//            //------------------------
//            // BULLETIN MODE
//            //------------------------
//            mnuRight_ShowMembers.setVisible(false);
//            if (event.getButton()==MouseButton.PRIMARY) 
//                show_group_members(selected_treetable_item);
//        } else {
            //------------------------
            // REALTIME MODE
            //------------------------
            if ((event.getButton()==MouseButton.PRIMARY) && (event.getClickCount()==2))
                show_group_members(selected_treetable_item);
        
            if (event.getButton()==MouseButton.SECONDARY){
                switch (livello_click){
                    case 1:
                        this.mnuRight_ShowMembers.setVisible(true);
    //                    this.mnuRight_SetGroupLeader.setVisible(false);
    //                    this.mnuRight_RemoveFromGroup.setVisible(false);
                        this.mnuRight_SetPreferred.setVisible(false);
    //                    this.mnuRight_ChangeGroup.setVisible(false);
                        break;
                    case 2:
                        this.mnuRight_ShowMembers.setVisible(false);
    //                    this.mnuRight_SetGroupLeader.setVisible(true);
    //                    this.mnuRight_RemoveFromGroup.setVisible(true);
                        this.mnuRight_SetPreferred.setVisible(false);
    //                    this.mnuRight_ChangeGroup.setVisible(true);
                        break;
                    case 3:
                        this.mnuRight_ShowMembers.setVisible(false);
    //                    this.mnuRight_SetGroupLeader.setVisible(false);
    //                    this.mnuRight_RemoveFromGroup.setVisible(false);
                        this.mnuRight_SetPreferred.setVisible(true);
    //                    this.mnuRight_ChangeGroup.setVisible(false);
                        break;
                }

            }
        //}
                  
    }
//------------------------------------------------------------------------------

    @FXML
    private void btnZoonOnIpo_Clicked(ActionEvent event) {
        if (getMH().getLayerIndex("Hypocenter")!=-1){
            getMH().SetMapBounds(getMH().getLayerIndex("Hypocenter"));
            getMH().RefreshMap();
        }
    }

    @FXML
    private void btnMapZoomIn_Click(ActionEvent event) {
        getMH().setACTION("ZOOMIN");
    }

    @FXML
    private void btnMapZoomOut_Click(ActionEvent event) {
        getMH().ZoomMapOut(10d);
    }

    @FXML
    private void ckLimitSearch_Clicked(ActionEvent event) {
        if (ckLimitSearch.isSelected())
            limit_search=true;
        else
            limit_search=false;
        
        btnRefresh.fire();
    }

    @FXML
    private void cmbSearchRoute_Click(ActionEvent event) {
        search_route = cmbSearchRoute.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void mnuRight_ShowMembers_Click(ActionEvent event) {
        show_group_members(selected_treetable_item);
    }

    @FXML
    private void btnSaveSearchSettings_Click(ActionEvent event) {
        btnRefresh.fire(); 
        // Iserire validazione input!!!!!   
        App.G.options.setList_of_searched_environments(App.G.options.getList_of_searched_environments());
        App.G.options.store();
    }

    @FXML
    private void btnApplySearchSettings_Click(ActionEvent event) {
        btnRefresh.fire();
    }

    @FXML
    private void btnRepaintMap_Click(ActionEvent event) {
        drawMap();
    }
  
    private class PollingService extends ScheduledService<Void> { 
        String AlarmFieName, OriginUpdateFileName;
        OffsetDateTime last_modified_datetime_origin, last_modified_datetime_event, last_modified_datetime_magnitude;
        //  ------------------------------------------------------------------------------    
        public PollingService(String AlarmFile, String in_OriginUpdateFileName, OffsetDateTime in_last_modified_datetime_event,
                OffsetDateTime in_last_modified_datetime_origin,
                OffsetDateTime in_last_modified_datetime_magnitude
                ){ 
            AlarmFieName = AlarmFile;
            OriginUpdateFileName = in_OriginUpdateFileName;
            last_modified_datetime_event = in_last_modified_datetime_event;
            last_modified_datetime_origin = in_last_modified_datetime_origin;
            last_modified_datetime_magnitude = in_last_modified_datetime_magnitude;
         }
    //  ------------------------------------------------------------------------------
        @Override
        protected Task<Void> createTask(){

            return new Task<Void>(){

                @Override
                protected Void call(){
                  Platform.runLater(() -> {
                     if (calendar.getValue()!=LocalDate.now())
                         calendar.setValue(LocalDate.now());
                      
                    if (polling_on.equalsIgnoreCase("GROUPS"))
                       MakePoll_EventsSearch();
                    else
                       MakePoll_OriginsSearch();

                  });

                  return null;
                }
            };

        }
    //  ------------------------------------------------------------------------------
        public void MakePoll_EventsSearch(){
            try {
                ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> to_be_processed;
                // Esegue il check         
                //PlaySound("sounds/ping.wav");              
                to_be_processed = HaveToUpdateList_groups();
                if ((to_be_processed!=null) && !to_be_processed.isEmpty()){      
                    // Process the results of the optimized polling
                    //PlaySound(AlarmFieName);

                    Process_new_events(to_be_processed);        
                }
            } catch (Exception ex) {
                //
            }   
        }

        public void MakePoll_OriginsSearch(){
            try {
                List<ObjectMagnitudesOriginsEventsAndEventsGroup> to_be_processed;
                // Esegue il check         
               // PlaySound("sounds/ping.wav");

                to_be_processed = HaveToUpdateList_origins(); //start, end);
                if ((to_be_processed!=null) && !to_be_processed.isEmpty()){      
                    // Process the results of the optimized polling
                    //PlaySound(AlarmFieName);

                    Process_new_origins(to_be_processed);    
                }
            } catch (Exception ex) {
                //
            }   
        }

    //  ----------------------------------------------------------------------------    
        private void Process_new_origins(List<ObjectMagnitudesOriginsEventsAndEventsGroup> to_be_processed){ 
            try {
                if ((to_be_processed!=null) && !to_be_processed.isEmpty()) {    
                    //final ObservableList<ObjectMagnitudesOriginsEventsAndEventsGroup> dati = FXCollections.observableArrayList();

                    //---------------------------------------------------------
                    for (int i=0; i < to_be_processed.size(); i++) {

                        if (CaravelSearchOrder.toUpperCase().contains("DESC")){
                            tblOrigins.getItems().add(0,to_be_processed.get(i));
                            tblOrigins.getSelectionModel().select(0);
                            tblOrigins.getFocusModel().focus(0);        
                        } else {
                            tblOrigins.getItems().add(to_be_processed.get(i));
                            tblOrigins.getSelectionModel().select(tblOrigins.getItems().size()-1);
                            tblOrigins.getFocusModel().focus(tblOrigins.getItems().size()-1);   
                        }

                        //
                        if ((to_be_processed.get(i).getModified()!=null) && (to_be_processed.get(i).getModified().isAfter(last_modified_datetime_event)))   {     
                            last_modified_datetime_event = to_be_processed.get(i).getModified();
                            System.out.println("----------- AGGIORNAMENTO SU EVENT con tempo: " + last_modified_datetime_event);    
                        }

                        if ((to_be_processed.get(i).getOrigin().getModified()!=null) && (to_be_processed.get(i).getOrigin().getModified().isAfter(last_modified_datetime_origin))) {     
                            System.out.println("----------- AGGIORNAMENTO SU ORIGIN con tempo: " + last_modified_datetime_origin); 
                            last_modified_datetime_origin = to_be_processed.get(i).getOrigin().getModified();
                        }

                        if ((to_be_processed.get(i).getMagnitude().getModified()!=null) && (to_be_processed.get(i).getMagnitude().getModified().isAfter(last_modified_datetime_magnitude)))     {     
                            System.out.println("----------- AGGIORNAMENTO SU MAGNITUDE con tempo: " + last_modified_datetime_magnitude);    
                            last_modified_datetime_magnitude = to_be_processed.get(i).getMagnitude().getModified();
                        }

                    }
                }
            } catch (Exception  ex){
            }

        }
    //  ----------------------------------------------------------------------------    
        private void Process_new_events(ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> to_be_processed){  
            try {
                if ((to_be_processed!=null) && !to_be_processed.isEmpty()) {    
                    treetableview_Events.getRoot().setExpanded(false);

                    for (int i=0; i< treetableview_Events.getRoot().getChildren().size(); i++){
                        treetableview_Events.getRoot().getChildren().get(i).setExpanded(false);
                    }

                    int id;
                    
                    for (int i=0; i < to_be_processed.size(); i++){
                        final ObjectMagnitudesOriginsEventsAndEventsGroup appo = to_be_processed.get(i);
                        
                        System.out.println(
                                ">>>>>>>>>>>>>>>>>>>>>>>> PROCESSO EVENTO: " + 
                                        to_be_processed.get(i).getOrigin().getRegion() + 
                                        " at " + 
                                        to_be_processed.get(i).getOrigin().getOt());
                        id = find_row_id(to_be_processed.get(i).getId());


                        if (id==-1){
                            // New event
                            System.out.println("SI TRATTA DI NUOVA EVENTO/ORIGIN");
                            Platform.runLater(() -> {
                                Notifications.create()
                                        .darkStyle()
                                        .hideAfter(Duration.seconds(3.0))
                                        .position(Pos.TOP_RIGHT)
                                        .title("SIT information")
                                        .text("New origin in " + appo.getOrigin().getRegion())
                                        .showInformation();
                                
                                PlaySound(AlarmFieName);
                            });
                            

                            if (CaravelSearchOrder.toUpperCase().contains("DESC")){
                                treetableview_Events.getRoot().getChildren().add(0,new TreeItem(to_be_processed.get(i)));
                                SelectRow(0);
                                treetableview_Events.getRoot().getChildren().get(0).setExpanded(true);
                            } else {
                                treetableview_Events.getRoot().getChildren().add(new TreeItem(to_be_processed.get(i)));
                                SelectRow(treetableview_Events.getRoot().getChildren().size()-1);
                                treetableview_Events.getRoot().getChildren().get(treetableview_Events.getRoot().getChildren().size()-1).setExpanded(true);
                            }
                        } else {
                            Platform.runLater(() -> {
                                Notifications.create()
                                        .darkStyle()
                                        .hideAfter(Duration.seconds(3.0))
                                        .position(Pos.TOP_RIGHT)
                                        .title("SIT information")
                                        .text("Origin update")
                                        .showInformation();
                                
                                PlaySound(OriginUpdateFileName);
                            });
                            // Updated origin
                            System.out.println("SI TRATTA DI AGGIORNAMENTO EVENTO/ORIGIN");
                            treetableview_Events.getRoot().getChildren().remove(id);
                            treetableview_Events.getRoot().getChildren().add(id, new TreeItem(to_be_processed.get(i)));
                            treetableview_Events.getRoot().getChildren().get(id).setExpanded(true);

                            SelectRow(id);
                        }

                        if ((to_be_processed.get(i).getModified()!=null) && (to_be_processed.get(i).getModified().isAfter(last_modified_datetime_event)))   {     
                            last_modified_datetime_event = to_be_processed.get(i).getModified();
                            System.out.println("----------- AGGIORNAMENTO SU EVENT con tempo: " + last_modified_datetime_event);    
                        }

                        if ((to_be_processed.get(i).getOrigin().getModified()!=null) && (to_be_processed.get(i).getOrigin().getModified().isAfter(last_modified_datetime_origin))) {     
                            System.out.println("----------- AGGIORNAMENTO SU ORIGIN con tempo: " + last_modified_datetime_origin); 
                            last_modified_datetime_origin = to_be_processed.get(i).getOrigin().getModified();
                        }

                        if ((to_be_processed.get(i).getMagnitude()!=null)&&(to_be_processed.get(i).getMagnitude().getModified()!=null) && (to_be_processed.get(i).getMagnitude().getModified().isAfter(last_modified_datetime_magnitude)))     {     
                            System.out.println("----------- AGGIORNAMENTO SU MAGNITUDE con tempo: " + last_modified_datetime_magnitude);    
                            last_modified_datetime_magnitude = to_be_processed.get(i).getMagnitude().getModified();
                        }

                    }
                    treetableview_Events.getRoot().setExpanded(true);
                }
            } catch (Exception ex) {
                App.logger.error(ex.getMessage());
            }




        }
    //  ----------------------------------------------------------------------------
        private ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> HaveToUpdateList_groups(){
            GetApi ReadClient;
            try {
                ReadClient = new GetApi();
                OffsetDateTime start, end;
                //InlineResponse2002 request_output;
                GetEventsGroup200Response request_output;
                ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup> res;
    //      
                start = OffsetDateTime.of(OffsetDateTime.now().getYear(), OffsetDateTime.now().getMonthValue(), OffsetDateTime.now().getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
                end = OffsetDateTime.of(OffsetDateTime.now().getYear(), OffsetDateTime.now().getMonthValue(), OffsetDateTime.now().getDayOfMonth(), 23, 59, 59, 0, ZoneOffset.UTC);
    //                
                ReadClient.getApiClient().setReadTimeout(30000);
                              
                lblLastPoll.setText("Last poll: " + LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.of("UTC"))) + " (UTC)");

                App.logger.debug("WS-LOG: HaveToSearch()--> ReadClient.getEventsGroup reading");

                request_output = ReadClient.getEventsGroup(start, end,              // OffsetDateTime starttime, OffsetDateTime endtime,
                        null, null, null, null, null, null,                         // Double minlat, Double maxlat, Double minlon, Double maxlon, Double lat, Double lon,
                        null, null, null,                                           // Double minradius, Double maxradius, Double minradiuskm
                        null, null,null, null, null,                                // Double maxradiuskm, Double minmag, Double maxmag, Double mindepth, Double maxdepth,
                        null, null,null, 
                        null, null,null, null,
                        null, null,null, 
                        null, null,null, null, null, null,
                        App.G.options.getList_of_searched_environments(),
                        App.G.options.getList_of_searched_environments(), 
                        App.G.options.getList_of_searched_environments(),
                        null,                                  
                        null, null,
                        last_modified_datetime_event,
                        last_modified_datetime_origin,
                        last_modified_datetime_magnitude, // magnitude_modified
                        "or",null,
                        CaravelSearchOrder,null, null,null, null, null, null, null);                     // String orderby, Long eventGroupId, Long idLocalspace, Integer limit, Integer page

                App.logger.debug("WS-LOG: HaveToSearch()--> ReadClient.getEventsGroup received response"); 

            if ((request_output.getData()!=null) && !request_output.getData().isEmpty() ){
                // The web service returned a non empty list 
                res = (ArrayList<ObjectMagnitudesOriginsEventsAndEventsGroup>)request_output.getData();
                return res;               
            } else 
                return null;

           } catch (Exception ex) {
               return null;
           } finally {
                ReadClient=null;
            }

       }

        private List<ObjectMagnitudesOriginsEventsAndEventsGroup> HaveToUpdateList_origins(){ //(OffsetDateTime start, OffsetDateTime end){
            
            OffsetDateTime start, end;
            start = OffsetDateTime.of(OffsetDateTime.now().getYear(), OffsetDateTime.now().getMonthValue(), OffsetDateTime.now().getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
            end = OffsetDateTime.of(OffsetDateTime.now().getYear(), OffsetDateTime.now().getMonthValue(), OffsetDateTime.now().getDayOfMonth(), 23, 59, 59, 0, ZoneOffset.UTC);
            
            GetEventsGroup200Response request_output = read_origins_advanced(start, end, last_modified_datetime_event,
                        last_modified_datetime_origin,
                        last_modified_datetime_magnitude);

            if ((request_output.getData()!=null) && !request_output.getData().isEmpty() ){
                // The web service returned a non empty list 
                return (List<ObjectMagnitudesOriginsEventsAndEventsGroup>)request_output.getData();  
            } else 
                return null;

        }

    //  ------------------------------------------------------------------------------   
        private int find_row_id(Long event_id){
            try {
                int id=-1;
                for(Object treeItem:treetableview_Events.getRoot().getChildren()){
                    id+=1;
                    ObjectMagnitudesOriginsEventsAndEventsGroup item= treetableview_Events.getRoot().getChildren().get(id).getValue();

                    //this.lastSelectedId = item.getId().toString();
                        if(item.getId().equals(event_id)){
                            return id;
                        }    
                }
    //
                return -1;
            } catch (Exception ex) {
                return -1;
            }
        }
    //  ------------------------------------------------------------------------------
        private void PlaySound(String fileName){
            File file;
            Media media;
            MediaPlayer mediaPlayer;
            try{

                if (!App.G.FileExists(fileName)) return;

                file = new File(fileName);

                URL url = null;
                if (file.canRead()) {url = file.toURI().toURL();}

                if (url==null) return;
                media = new Media(url.toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                
            } catch (Exception ex) {
                // fileName could not exist...
                java.util.logging.Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
            } finally {
                file=null;
                media=null;
                mediaPlayer=null;
            }
        }
        }

//------------------------------------------------------------------------------
        private void AddFDSNEventsTab(int datasource_index, String title){
            Tab newTab = new Tab(title);
            newTab.setText(title);

            AnchorPane anc = new AnchorPane();
            TableView tv = new TableView();

            tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FDSN_Events_List_items>() {
                @Override
                public void changed(ObservableValue<? extends FDSN_Events_List_items> observable, FDSN_Events_List_items old_val, FDSN_Events_List_items new_val) {
                    ReadSelectedEvent_FDSN(new_val);
                }               
            });

            anc.getChildren().add(tv);
            AnchorPane.setBottomAnchor(tv, 0.0);
            AnchorPane.setTopAnchor(tv, 0.0);
            AnchorPane.setLeftAnchor(tv, 0.0);
            AnchorPane.setRightAnchor(tv, 0.0);

            newTab.setClosable(true);
            newTab.setContent(anc);

            tabs_available_FDSN.getTabs().add(newTab);

        }
//------------------------------------------------------------------------------        
        private void AddLOCALHOSTEventsTab(int datasource_index, String title){
            Tab newTab = new Tab(title);
            newTab.setText(title);
            AnchorPane anc = new AnchorPane();
            
            String startup_dir;
            if (Paths.get(App.G.options.getDatasources_LOCALHOST().get(datasource_index).getUrl()).toFile().exists())
                startup_dir = Paths.get(App.G.options.getDatasources_LOCALHOST().get(datasource_index).getUrl()).toAbsolutePath().toString();
            else
                startup_dir = System.getProperty("user.dir"); 
                       
            RootDirItem rootDirItem;
            rootDirItem = ResourceItem.createObservedPath(Paths.get(startup_dir));
            DirectoryTreeView tv = new DirectoryTreeView();
            tv.setIconSize(IconSize.MEDIUM);
            tv.setRootDirectories(FXCollections.observableArrayList(rootDirItem));

            DirectoryView v = new DirectoryView();
            v.setIconSize(IconSize.MEDIUM);

            tv.getSelectedItems().addListener( (Observable o) -> {
                if( ! tv.getSelectedItems().isEmpty() ) {
                    v.setDir(tv.getSelectedItems().get(0));
                    local_SAC_folder = tv.getSelectedItems().get(0).getUri().replace("file:", "");
                } else {
                    v.setDir(null);
                }
            });
            
            v.getSelectedItems().addListener((Observable o) -> {
                if( !v.getSelectedItems().isEmpty() ) {
                    setLocal_QUAKEML_file(v.getSelectedItems().get(0).getUri().replace("file:", ""));
                    if (getLocal_QUAKEML_file().endsWith(".xml")){
                        System.out.println("local_QUAKEML_file = " + getLocal_QUAKEML_file());
                        this.btnPreReadQML.setDisable(false);
                    } else {
                        setLocal_QUAKEML_file("");
                        this.btnPreReadQML.setDisable(true);
                    }
                } else {
                   setLocal_QUAKEML_file("");
                   this.btnPreReadQML.setDisable(true);
                }
            });

            SplitPane split_LocalFolders = new SplitPane(tv,v); 
            split_LocalFolders.setDividerPositions(0.3,0.8);
            
            anc.getChildren().add(split_LocalFolders);
            
            AnchorPane.setBottomAnchor(split_LocalFolders, 0.0);
            AnchorPane.setTopAnchor(split_LocalFolders, 0.0);
            AnchorPane.setLeftAnchor(split_LocalFolders, 0.0);
            AnchorPane.setRightAnchor(split_LocalFolders, 0.0); 
        

            newTab.setClosable(true);
            newTab.setContent(anc);

            tabs_available_LOCALHOST.getTabs().add(newTab);

        }
//------------------------------------------------------------------------------
        /**
         * @return the AnyDatasourcesAvailable
         */
        public boolean isAnyDatasourcesAvailable() {
            return AnyDatasourcesAvailable;
        }

        /**
         * @param AnyDatasourcesAvailable the AnyDatasourcesAvailable to set
         */
        public void setAnyDatasourcesAvailable(boolean AnyDatasourcesAvailable) {
            this.AnyDatasourcesAvailable = AnyDatasourcesAvailable;
        }

    /**
     * @return the Event_on_Map
     */
    public Event getEvent_on_Map() {
        return Event_on_Map;
    }

    /**
     * @param Event_on_Map the Event_on_Map to set
     */
    public void setEvent_on_Map(Event Event_on_Map) {
        this.Event_on_Map = Event_on_Map;
    }

    /**
     * @return the local_QUAKEML_file
     */
    public String getLocal_QUAKEML_file() {
        return local_QUAKEML_file;
    }

    /**
     * @param local_QUAKEML_file the local_QUAKEML_file to set
     */
    public void setLocal_QUAKEML_file(String local_QUAKEML_file) {
        this.local_QUAKEML_file = local_QUAKEML_file;
    }
}
