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
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.geom.Point2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import okhttp3.Call;
import org.apache.commons.lang3.ArrayUtils;
import org.controlsfx.control.ToggleSwitch;
import org.controlsfx.dialog.ProgressDialog;
import org.ingv.dante.ApiClient;
import org.ingv.dante.ApiException;
import org.ingv.dante.ApiResponse;
import org.ingv.dante.api.StoreApi;
import org.ingv.dante.model.AddOrigin201Response;
import org.ingv.dante.model.AddOrigin201ResponseData;
import org.ingv.dante.model.AddOriginFlagRequest;
import org.ingv.dante.model.AddOriginFlagRequestData;
import org.ingv.dante.model.AddOriginRequest;
import org.ingv.dante.model.AddOriginRequestData;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200ResponseDataInner;
import org.ingv.dante.model.ObjectAmplitude;
import org.ingv.dante.model.ObjectAmplitudeTypeAmplitude;
import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectOrigin;
import org.ingv.dante.model.ObjectOriginFlag;
import org.ingv.dante.model.ObjectPick;
import org.ingv.dante.model.ObjectStationmagnitude;
import org.ingv.dante.model.ObjectTypeOrigin;
import org.ingv.dante.model.TypeOriginName;
import org.ingv.sit.datamodel.Event_type;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.datamodel.Station;
import org.ingv.sit.datamodel.Waveform;
//import org.ingv.sit.jL.jL;
import org.ingv.sit.location.Hypo2000Handler;
import org.ingv.sit.mapping.MapHandler;
import org.ingv.sit.sigpro.WoodAndersonHandler;
import org.ingv.sit.tablemodels.Phases_List_items;
import org.ingv.sit.tablemodels.Phases_List_items_terna;
import org.ingv.sit.utils.CircleDrawer;
import org.ingv.sit.utils.DSP;
import org.ingv.sit.utils.Globals;
import org.ingv.sit.utils.sitDialog;
import org.ingv.sit.utils.PickedObject;
import org.ingv.sit.location.PymlHandler;
import org.ingv.sit.quakeml.QuakeMLWriter;
import org.ingv.sit.tablemodels.Towns_List_items;
import org.ingv.sit.utils.duration_buffer_item;
import org.ingv.sit.utils.tauPObject;
import org.ingv.sit.webservices.Generic_client;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYTitleAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockFrame;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.fx.interaction.ChartMouseEventFX;
import org.jfree.chart.fx.interaction.ChartMouseListenerFX;
import org.jfree.chart.fx.interaction.ZoomHandlerFX;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.LengthAdjustmentType;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.Range;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.data.xyz.XYZSeries;
import org.jfree.chart3d.data.xyz.XYZSeriesCollection;

public class WavesFormController implements Initializable {
    @FXML
    private Button btnBack;
    @FXML
    private Button btnLocate_Hypo2000;
    @FXML
    private Button btnSave;
    @FXML
    private Button bntSaveQML;
    @FXML
    private ScrollPane waves_scrollbox_preview;
    @FXML
    private AnchorPane waves_anchor;
    
    @FXML
    private ScrollPane waves_scrollbox_TERNA;
    @FXML
    private TableView<?> table_phases;
    @FXML
    private SplitPane main_split_panel;
    @FXML
    private SplitPane waves_split;       
    @FXML
    private AnchorPane main_anchor_pane;
    @FXML
    private ChartViewer chart_viewer_single_WA;
    @FXML
    private Button btnStart;
    @FXML
    private SplitPane WAwaves_split;
    @FXML
    private ScrollPane waves_scrollbox_preview_WA;
    @FXML
    private VBox waves_vbox_preview_WA;
    @FXML
    private ChartViewer chart_viewer_preview_WA;
    @FXML
    private Button btnDelAllAmps;
    @FXML
    private Button btnRecalc_mode1;
    @FXML
    private Button btnRecalc_mode2;
    @FXML
    private Button btnDelCurrentAmp;
    @FXML
    private Button btnRecalc_mode1_single;
    @FXML
    private Button btnRecalc_mode2_single;
    @FXML
    private RadioButton radio_beforeA;
    @FXML
    private RadioButton radio_afterA;
    @FXML
    private Button btnRecalc_single;
    @FXML
    private Button btnSave_single_amplitude;  
    @FXML
    private ComboBox combo_TernaChannels;
    @FXML
    private Button btnPrevTerna;
    @FXML
    private Button btnNextTerna;
    @FXML
    private Button btnZoomOut;
    @FXML
    private Button btnZoomReset;
    @FXML
    private Button btnClearWeightBoxes; 
    @FXML
    private Button bntNOTAnEarthquake;
    @FXML
    private ComboBox<String> cmbTypeEvent;
    @FXML
    private Button btnApplyEventChange;
    @FXML
    private TableView<?> table_picks;
    @FXML
    private Button bntRepaintBigger;
    @FXML
    private ChartViewer chart_viewer_PREVIEW;
    @FXML
    private Button bntRepaintSmaller;
    @FXML
    private ChartViewer chart_viewer_TERNA;
    @FXML
    private Button btn_WA_refresh;
    @FXML
    private Button btnPaneRight;
    @FXML
    private Button btnPaneLeft;
    @FXML
    private Button btnZoomIn_Area;
    
    private Event myEvent;
    private WoodAndersonHandler myWA;
    private Stage PrimaryStage;  
    private boolean form_initializing=true;
    private CombinedDomainXYPlot plot_combinato;
    private CombinedDomainXYPlot plot_combinato_WA;
    private CombinedDomainXYPlot plot_combinato_TERNA;
    private int active_phase_id=-1;
    private int active_phasemarker_id= -1;
    private LocalDateTime time_mouse;
    private double weightbox_millis_xstart_TEMP;
    private LocalDateTime weightbox_time_start_TEMP;
    private ArrayList<PickedObject> WEIGHT_BOXES = new ArrayList<>();
    private double size_factor=4.0;
    private boolean LoadingTerna=false;
    private Range startupXRange;
  //  private Range startupYRange;
    private int MAX_N_WA_STATIONS = 10000;  // sarebbe meglio un max_delta
    private int current_WA_plotbox_index=-1;
    private float local_A1,  local_A2; 
    private LocalDateTime local_T1, local_T2;
    private ArrayList<Event_type> event_types;
    private LocalDateTime T_b, T_a;
    private String last_blinking_station_code="";
    private double scale_factor=0.5;
    private static int preview_subplot_index=-1;
    private int WA_subplot_index=-1;
    private ObjectArrival prospective_phase;
    private ObjectArrival prospective_phase_WA;
    //
    private MapHandler MH;
    @FXML
    private Tab tab_StationPicks;
    @FXML
    private ComboBox<String> cmbActiveStation;
    @FXML
    private Button btnPyml;
   

    public boolean m_stageShowing = false;
    @FXML
    private Button btnDiscardStation;
    
    public int idControllers;
    
    @FXML
    private ToggleSwitch tglFilterAll;
    @FXML
    private ToggleSwitch tglFilterTerna;
    @FXML
    private Button btnZoomIn;
    
    
    private double startup_plot_height;
    @FXML
    private RadioButton radioP;
    @FXML
    private RadioButton radioS;
    @FXML
    private TabPane tabMags;
    @FXML
    private SplitPane info_split;
    @FXML
    private SplitPane info_split_inner;
    @FXML
    private Button btnRuler;
    @FXML
    private RadioButton radioCoda;
    @FXML
    private Button btnWeight;
    @FXML
    private Button btnDropCoda;
    @FXML
    private Label lblSminusP;
    @FXML
    private Label lblCoda;
    @FXML
    private Button btnSelectStations;
    @FXML
    private AnchorPane anchor_top_right;
    @FXML
    private Button btnAddWaves;
    private VBox vboxWaves;

    private static final double PAINT_HZ = 2.0;
    private ScheduledService<Boolean> scheduled_phaseswap_service;
    @FXML
    private Button btnZoomResetPreview;
    @FXML
    private Button btnRapaintMap;
    @FXML
    private Button btnPM;
    @FXML
    private Button btnDSP;
    private Label lblName;
    @FXML
    private TableView<?> table_cities;
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
    private Label lblErrZ;
    @FXML
    private Label lblErrH;
    @FXML
    private Label lblOT_L;
    @FXML
    private Label lblOT;
    @FXML
    private TabPane main_waves_tab;
    
    public void resizemap(){
        this.btnRapaintMap.fire();
    }
      
    public void initPhasesSwapThread() {
        scheduled_phaseswap_service = new ScheduledService<Boolean>() {
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {
                    protected Boolean call() {
                        Platform.runLater(() -> {
                            swapPhasesInVerticalComponentsview();
                        });
                        return true;
                    }
                };
            }
        };
        scheduled_phaseswap_service.setPeriod(javafx.util.Duration.millis(1000.0 / PAINT_HZ));
        scheduled_phaseswap_service.start();
    }
    private void swapPhasesInVerticalComponentsview() {
	if (!phase_was_picked) {
            return;
        }
        
        try {
            phase_was_picked = false;
            show_phase_lines(myEvent.getActiveStationID());
        } catch (Exception ex){
        }
        
    }   

    @FXML
    private void btnRuler_Click(ActionEvent event) {        
        MH.setACTION("RULER_1_pending");
    }
    
    private boolean can_pick=false;
    private boolean phase_was_picked=false;

    @FXML
    private void chart_viewer_TERNA_MouseEntered(MouseEvent event) {
        can_pick=true;
    }

    @FXML
    private void radioCoda_clicked(ActionEvent event) {
        OPERATION_DETAIL_WAVE= OPERATIONS_WAVE.PICKING_PH;
    }

    @FXML
    private void btnWeight_Clicked(ActionEvent event) {
        if ((OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.CAN_GRAB)|| 
                           ((OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK))){
                        getPrimaryStage().getScene().setCursor(Cursor.H_RESIZE);
                        OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.WEIGHT_BOX_WAITING_DOWN;
                    }      
    }

    @FXML
    private void btnDropCoda_Click(ActionEvent event) {
        DropCoda();  
    }

    @FXML
    private void btnSelectStations_Click(ActionEvent event) {
        MH.setACTION("SELECT");
    }

    @FXML
    private void btnAddWaves_Clicked(ActionEvent event) {
        try {
            myEvent.setAddingStations(true);
            if (MH.getSelected_Stations()!=null && !MH.getSelected_Stations().isEmpty()){
                String msg = MH.getSelected_Stations().size() + " stations will be added to current event.\n Are you sure?" ;

                if (sitDialog.ShowConfirmationMessage("Adding stations...", msg,this.getPrimaryStage())== ButtonType.OK){
                    for (int i=0; i<MH.getSelected_Stations().size(); i++){
                        AddStationToMyEvent(MH.getSelected_Stations().get(i));
                    }
                }
                // Download waveforms:
                myEvent.ADDWAVES(MH.getSelected_Stations(), PrimaryStage);
                
                if (show_waves_PREVIEW(preview_subplot_index)) {     
                    //LoadAndShowTerna(0);
                    show_phase_lines(plot_combinato);
                    showMap();
                    //populate_magnitude_tabs();
                    populate_phases_list();             
                    ResetRadios();
                }
            }
        } catch (Exception ex){
                
        } 
    }
    
    private void AddStationToMyEvent(String STAZ){
        int idStazione = myEvent.StationCode_to_StationId(STAZ);   
        if (idStazione>-1) {
            myEvent.getStation(idStazione).Include_in_location();
        } else {          
            // La stazione non è ancora nell'evento
            Station s = App.G.SeismicNet.getStation(STAZ);
            s.Include_in_location();
            myEvent.getStations().add(s);  
        }
        
        
    }

    @FXML
    private void btnZoomResetPreview_Click(ActionEvent event) {
        ResetViewRange_PREVIEW();
    }

    @FXML
    private void btnRepaintMap_Click(ActionEvent event) {
        showMap();
    }

    @FXML
    private void btnDSP_Click(ActionEvent event) {
        try {                 
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("Dsp.fxml"));
                       
            Parent root1 = (Parent) fxmlLoader1.load();
           
            Stage stage = new Stage();
                        
            stage.setScene(new Scene(root1));  
            stage.setTitle("I.N.G.V. SIT - Information dialog");
            stage.initModality(Modality.APPLICATION_MODAL);
                    
            stage.setMaximized(false);
            DspController controller_DSP = (DspController)fxmlLoader1.getController();
            controller_DSP.setID_waves_controller(idControllers);
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
        }
    }

//------------------------------------------------------------------------------      
    public enum OPERATIONS_WAVE{CAN_GRAB, GRABPICK_WAITING_CLICK, GRABPICK_MOVE, 
        GRABPICK_RELEASE,PICKING_A, PICKING_T1, PICKING_T2, PICKING_PH, 
        ZOOM,
        PAN, PAN_DOWN, PAN_MOVE, PAN_MOVE_RIGHT, PAN_MOVE_LEFT, 
        WEIGHT_BOX_WAITING_DOWN, WEIGHT_BOX_WAITING_RELEASE, WEIGHT_BOX_RELEASE};    
    public OPERATIONS_WAVE OPERATION_PREVIEW_WAVE;
    public OPERATIONS_WAVE OPERATION_DETAIL_WAVE;
    public OPERATIONS_WAVE OPERATION_DETAIL_WAVE_WA;

//------------------------------------------------------------------------------   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        form_initializing=true;    
        btnSave_single_amplitude.setVisible(false);
        waves_scrollbox_TERNA.setFitToWidth(true);
        waves_scrollbox_TERNA.setFitToHeight(true);
        waves_scrollbox_preview.setFitToWidth(true);
        waves_scrollbox_preview.setFitToHeight(false);
        waves_scrollbox_preview_WA.setFitToWidth(true);
        waves_scrollbox_preview_WA.setFitToHeight(false);
        

        ToggleGroup group = new ToggleGroup();
        radioP.setToggleGroup(group);
        radioS.setToggleGroup(group);
        radioCoda.setToggleGroup(group);
               
        ToggleGroup groupWA = new ToggleGroup();
        radio_beforeA.setToggleGroup(groupWA);
        radio_afterA.setToggleGroup(groupWA);
        
//      ------------------------------------------------------------------------                      
        main_split_panel.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                if (!form_initializing) {
                    scale_factor = waves_split.getDividerPositions()[0];       
                    waves_scrollbox_TERNA.setPrefSize(waves_split.getWidth(), waves_split.getHeight()-(waves_split.getHeight()*scale_factor)-40);       
                    waves_scrollbox_preview.setMaxHeight(waves_split.getHeight()- waves_scrollbox_TERNA.getHeight());
                    waves_scrollbox_preview.setPrefHeight(waves_split.getHeight()- waves_scrollbox_TERNA.getHeight()); 
                    
                }
            }
        });
//      ------------------------------------------------------------------------        
        waves_split.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>(){           
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                if (!form_initializing) {      
                    scale_factor = waves_split.getDividerPositions()[0];    
                    waves_scrollbox_TERNA.setPrefSize(waves_split.getWidth(), waves_split.getHeight()-(waves_split.getHeight()*scale_factor)-40);
                                
                    waves_scrollbox_preview.setMaxHeight(waves_split.getHeight()- waves_scrollbox_TERNA.getHeight());
                    waves_scrollbox_preview.setPrefHeight(waves_split.getHeight()- waves_scrollbox_TERNA.getHeight());         
                }
            }
        });
//      --------------------------------------------------------------------------       
        WAwaves_split.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>(){
            
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                if (!form_initializing) {      
                    scale_factor = WAwaves_split.getDividerPositions()[0];
                    chart_viewer_single_WA.setPrefSize(WAwaves_split.getWidth(), WAwaves_split.getHeight()-(WAwaves_split.getHeight()*scale_factor)-40);                    
                    chart_viewer_preview_WA.setPrefSize(WAwaves_split.getWidth(), WAwaves_split.getHeight());
                    
                    //txtAreaLog.setPrefSize(WAwaves_split.getWidth(), WAwaves_split.getHeight());     
                }
            }
        });
//      -------------------------------------------------------------------------- 
        event_types = ReadEventTypes();
        if (event_types==null) event_types = ReadDefaultEventTypes();
        
        if (event_types!=null) {                        
            for (int i=0; i<event_types.size(); i++){
                cmbTypeEvent.getItems().add(event_types.get(i).getDescription());
            }    
        }             
//      ------------------------------------------------------------------------        
//        
        tglFilterAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                getPrimaryStage().getScene().setCursor(Cursor.WAIT);
                if (!form_initializing) {
                    try {
                        if (new_val) {
                           if (Filter_ALLWAVES()) {
                                DisplayOnlyWaves(preview_subplot_index);

                                //tglFilterTerna.setSelected(true);
                            }  
                        } else {
                            Restore_ALLWAVES_ResetOriginal();
                            DisplayOnlyWaves(preview_subplot_index);
                            //tglFilterTerna.setSelected(false);
                        }      
                    } catch (Exception ex) {
                    } finally {
                        getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
                    }
                }    
            }
        });
        
        tglFilterTerna.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                getPrimaryStage().getScene().setCursor(Cursor.WAIT);
                try {
                    if (LoadingTerna) return;
                    
                    if (new_val) {
                        Range xRange = ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(0)).getDomainAxis().getRange();
                        //Range yRange = ((XYPlot)plot_combinato_TERNA.getSubplots().get(0)).getRangeAxis().getRange();
                        if (Filter_TERNA()) DisplayWaves_TERNA(xRange); //, yRange);    
                    } else {
                        Restore_TERNA_UndoLastFilter();
                        Range xRange = ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(0)).getDomainAxis().getRange();
                        DisplayWaves_TERNA(xRange); //, yRange);  
                    }      
                } catch (Exception ex) {
                    App.logger.error(ex.getMessage());
                } finally {
                    getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
                } 
                
            }
        });
//      ------------------------------------------------------------------------  

        main_waves_tab.selectionModelProperty().getValue().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) {        
                if ((oldValue.intValue()==(int)0) && (newValue.intValue()==(int)1)){
                    // Refresh sui WA cosi mostra eventuali modifiche ai picking
                    btn_WA_refresh.fire();
                    show_current_wave_wa(current_WA_plotbox_index);

                }
            }
        });



        form_initializing=false;  
    }    
    //------------------------------------------------------------------------------      
    public void populate_magnitude_tabs(){
        tabMags.getTabs().clear();
        tabMags.getScene().setUserData(this);
        if (myEvent==null) return;
        if ((myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()==null) || (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty())) {
            AddMAGTab(null);
        } else {
            for (int i =0; i< myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); i++){
                AddMAGTab(myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i)); 
                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getId()!=null){
                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getId().equals(myEvent.getInnerObjectEvent().getPreferredMagnitudeId())) {
                        tabMags.getSelectionModel().select(tabMags.getTabs().get(tabMags.getTabs().size()-1));  
                        setTabAsPreferred(tabMags.getTabs().get(tabMags.getTabs().size()-1), myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i));
                    }
                } else {
                    String preferred_magnitude_type= App.G.options.getPreferredMagnitudeType();
                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains(preferred_magnitude_type)){
                        tabMags.getSelectionModel().select(tabMags.getTabs().get(tabMags.getTabs().size()-1));  
                        setTabAsPreferred(tabMags.getTabs().get(tabMags.getTabs().size()-1), myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i));
                    }
                }
            }
        }    
    }   
    
    
        public Tab findTabForAnchorPane(AnchorPane anchorPane) {
        // Scorrere tutti i tab nella TabPane
        for (Tab tab : tabMags.getTabs()) {
            // Per ogni tab, ottenere il contenuto (che dovrebbe essere un AnchorPane)
            AnchorPane content = (AnchorPane) tab.getContent();
            // Verificare se l'AnchorPane è quello cercato
            if (content == anchorPane) {
                // Stampa il testo del tab corrispondente
               return  tab;
                // Esci dal ciclo una volta trovato il tab
                
            }
        }
        return null;
    }
    public void ResetInactiveTabsStars(AnchorPane anchorPane_prefMag) {
        // Scorrere tutti i tab nella TabPane
        for (Tab tab : tabMags.getTabs()) {
            // Per ogni tab, ottenere il contenuto (che dovrebbe essere un AnchorPane)
            AnchorPane tmpAnchor = (AnchorPane) tab.getContent();
            // Verificare se l'AnchorPane è quello cercato
            if (tmpAnchor != anchorPane_prefMag) {     
                
                ((Label)(tab.getGraphic())).setGraphic(null);
                //tab.setGraphic(label);
                
                
                if (tmpAnchor.getChildren().isEmpty()) return;
                
                for (javafx.scene.Node node : tmpAnchor.getChildren()) {
                    if (node instanceof Button) {
                        node.setVisible(true);
                    }
                }
            }
        }
        
    }
//------------------------------------------------------------------------------      
    private void AddMAGTab(ObjectMagnitude M)  {
        try {
            if (M==null) return;
            Tab tab = new Tab();
//            if (M!=null) {
//                tab.setText(M.getTypeMagnitude());
//            } else tab.setText("NO Magnitude");
            FXMLLoader fXMLLoader = new FXMLLoader();
            Parent tab_root;
            
            String Magnitude_tab_fxml_name = "Magnitude.fxml"; 
 
            tab_root = (Parent) fXMLLoader.load(getClass().getResource(Magnitude_tab_fxml_name).openStream());
             
            MagnitudeController controller_ML = (MagnitudeController)fXMLLoader.getController();
            
            controller_ML.setMAG(M);
            
            controller_ML.setEventSource("WAVESFORM");
            controller_ML.setEventId(myEvent.getInnerObjectEvent().getId());
            controller_ML.setEventPreferredMagnitudeId(myEvent.getInnerObjectEvent().getPreferredMagnitudeId());
            
            if (M!=null) {
                if (M.getTypeMagnitude().toUpperCase().contains("MD"))
                    controller_ML.ShowData(false);
                else
                    controller_ML.ShowData(true);
            }
                                     
            tab.setContent(tab_root);

            Label label = new Label(M.getTypeMagnitude()); 
            tab.setGraphic(label);
            tabMags.getTabs().add(tab);
            
            if (M.getTypeMagnitude().toUpperCase().contains(App.G.options.getPreferredMagnitudeType()))
                tabMags.getSelectionModel().select(tab);
            fXMLLoader=null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }  
    }
    
    public void setTabAsPreferred(Tab tab, ObjectMagnitude M){
        Image iconImage = new Image("images/yellowstar_small.png");

        // Creazione di un'etichetta con un'immagine     
        String testo = M.getTypeMagnitude();
        
        Label label = new Label(testo, new ImageView(iconImage));
        tab.setGraphic(label);       
        
        AnchorPane a = (AnchorPane)tab.getContent();
                      
        if (a.getChildren().isEmpty()) return;

        for (javafx.scene.Node node : a.getChildren()) {
            if (node instanceof Button) {
                node.setVisible(false);
            }
        }
    }
//------------------------------------------------------------------------------    
     @FXML
    private void radioP_clicked(ActionEvent event) {
        OPERATION_DETAIL_WAVE= OPERATIONS_WAVE.PICKING_PH;
    }

    @FXML
    private void radioS_clicked(ActionEvent event) {
        OPERATION_DETAIL_WAVE= OPERATIONS_WAVE.PICKING_PH;
    }
//------------------------------------------------------------------------------    
    public void  populate_stations_combo(){
        try  {
            if (myEvent==null)  return;
            if (myEvent.getNStations()==0) return;
            cmbActiveStation.getItems().clear();
            for (int i=0; i<myEvent.getNStations(); i++){
                if (myEvent.getStation(i).HasAnUsedPhase())
                    cmbActiveStation.getItems().add(myEvent.getStation(i).getCode());
            }
        } catch (Exception  ex) {
        }
    }
    
    @FXML
    private void cmbActiveStation_change(ActionEvent event) {
        if ((cmbActiveStation.getValue()!=null) && (cmbActiveStation.getValue().trim().length()>0)) {
            LoadAndShowTerna(cmbActiveStation.getValue().toString());
        }
    }
//------------------------------------------------------------------------------
     @FXML
    private void btn_WA_refres_click(ActionEvent event) {
        if (myEvent.getNWavesWA() > 0) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Plotting Wood-Anderson waves...");
            if (!show_waves_WA()){
                sitDialog.ShowErrorMessage("Error while plotting waves.\nOperation aborted.", getPrimaryStage());   
            } 
        }          
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnPaneRight_Click(ActionEvent event) {
        Range vecchiorange, nuovorange;
        try {
            vecchiorange = ((CombinedDomainXYPlot)(chart_viewer_TERNA.getChart().getPlot())).getDomainAxis().getRange();
   
            nuovorange= new Range(vecchiorange.getLowerBound()+1000, vecchiorange.getUpperBound()+1000);
            ((CombinedDomainXYPlot)(chart_viewer_TERNA.getChart().getPlot())).getDomainAxis().setRange(nuovorange);
        } catch (Exception ex) {
        } finally {
            nuovorange=null;
        }     
    }
    
    @FXML
    private void btnPaneLeft_Click(ActionEvent event) { 
        Range vecchiorange, nuovorange;
        try {
            vecchiorange = ((CombinedDomainXYPlot)(chart_viewer_TERNA.getChart().getPlot())).getDomainAxis().getRange();
   
            nuovorange= new Range(vecchiorange.getLowerBound()-1000, vecchiorange.getUpperBound()-1000);
            ((CombinedDomainXYPlot)(chart_viewer_TERNA.getChart().getPlot())).getDomainAxis().setRange(nuovorange);
        } catch (Exception ex) {
        } finally {
            nuovorange=null;
        }     
    }
//------------------------------------------------------------------------------    
    @FXML
    private void btnBack_clicked(ActionEvent event) {
        //
        if (sitDialog.ShowConfirmationMessage("Do you really want to close this window?", 
                "All unsaved data will be lost!!", this.getPrimaryStage())==ButtonType.CANCEL) return;
        
        // 
        myEvent.SetSemaphoreOnOrigin("OFF");
        //       
        if (MH!=null) {
            MH.close();
        }
        WEIGHT_BOXES=null;
        MH=null;
        myWA=null;
        myEvent=null;

        ((MapFormController)App.G.getMainFormLoader().getController()).SearchAndShowLocations();
        
        if (scheduled_phaseswap_service!=null && scheduled_phaseswap_service.isRunning()) scheduled_phaseswap_service.cancel();
                
        PrimaryStage.close();   
        App.G.WavesControllers.remove(this);
        System.gc();
    }
//--------------------------------------------------------------------------------    
    @FXML
    private void chart_viewer_TERNA_MouseExited(MouseEvent event) {
        can_pick=false;
        if (getPrimaryStage().getScene().getCursor()!= Cursor.DEFAULT){
            //OPERATION_DETAIL_WAVE=null;
            event.consume();
            getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
        }
    }    
//--------------------------------------------------------------------------------
    @FXML
    private void btnLocate_Hypo2000_click(ActionEvent event) {        
        PymlHandler PYML = new PymlHandler(App.G.options.get_pyml_url());                    
        Hypo2000Handler HYPO2000 = new Hypo2000Handler(App.G.options.get_hypo2000_path());
        Event relocated_event, res;
//       
        try {
            //------------------------------------------------------------------
            // CALL A HYPO2000
            //------------------------------------------------------------------
//            if (!myEvent.HasAmplitudes()){
//                // Event was read before a magnitude was available: we try to read it again
//                App.logger.debug("Reading event again to find some amplitude...");
//                if (!myEvent.reReadMagnitudes()) {
//                    if (sitDialog.ShowConfirmationMessage("No amplitude available for this event", "Continue location anyway?",this.getPrimaryStage())==ButtonType.CANCEL)
//                        return;
//                    }
//            }

            ArrayList<duration_buffer_item> buffered_codas=null;
            if (myEvent.HasCodas()){
                buffered_codas = myEvent.BackupCodas();
            }       
            
            ArrayList<ObjectArrival> buffered_unused_arrivals=null;
            buffered_unused_arrivals = myEvent.BackupUnusedArrivals();           
    //     
            relocated_event = HYPO2000.PostRequest(myEvent);           
    //      
            if (relocated_event!=null) {
                //--------------------------------------------------------------
                // CALL A PYML
                //--------------------------------------------------------------

                // Mix dei due eventi e aggiornamento interfaccia  
                if (relocated_event.HasAmplitudes())
                    relocated_event = PYML.PostRequest(relocated_event);
                else
                    relocated_event.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().clear();

                if (relocated_event!=null) {
                    if (buffered_codas!=null){
                        relocated_event.RestoreCodas(buffered_codas);
                        relocated_event.CalculateMD();
                    }
                    
                    relocated_event.setWork_event_ID(myEvent.innerObjectEvent.getId());
                    relocated_event.setWork_origin_ID(myEvent.getInnerObjectEvent().getOrigins().get(0).getId());
                    //----------------------------------------------------------------------
                    // Serve per salvare gli eventi in SeisEV
                    relocated_event.innerObjectEvent.setIdLocalspace(myEvent.innerObjectEvent.getIdLocalspace());
                    //----------------------------------------------------------------------

                    res = ShowResultWindow(relocated_event);
                    
                    // Epurate null magnitudes
                    res = Epurate_null_magnintudes(res);
                    
                    if (res!=null) { // If location is accepted by user:
                        // Prima aggiorna l'evento......
                        myEvent.Update_with_location_results(res);
                        
                        myEvent.RestoreUnusedArrivals(buffered_unused_arrivals);
                        // ...Poi aggiorna l'interfaccia:
                        DisplayOnlyMetadata();
                    } 
                }           
            } else {
                sitDialog.ShowErrorMessage("Event location returned no results.", this.getPrimaryStage());
            }
            
            relocated_event=null;
            PYML=null;
            HYPO2000=null;
            buffered_codas=null;
            res=null;
        } finally {
//            relocated_event=null;
//            PYML=null;
//            HYPO2000=null;
        }      
    }
    
    
    
    private Event Epurate_null_magnintudes(Event inE){
        try {
            if (inE==null) return null;
            if (inE.getInnerObjectEvent()==null) return inE;
            if ((inE.getInnerObjectEvent().getOrigins()==null) || (inE.getInnerObjectEvent().getOrigins().isEmpty()) )return inE;
            if ((inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()==null) || (inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty())) return inE;
            
            for (int idMag=0; idMag < inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); idMag++){
                if ((inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag).getStationmagnitudes()!=null) && !(inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag).getStationmagnitudes().isEmpty())){
                    for (int idStaMag=0; idStaMag < inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag).getStationmagnitudes().size(); idStaMag++){
                        if (inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag).getStationmagnitudes().get(idStaMag).getMag()==null){
                            inE.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag).getStationmagnitudes().remove(idStaMag);
                        }
                    }
                }
            }
            return inE;
        } catch (Exception ex){
            return null;
        }
    }
//------------------------------------------------------------------------------
    private Event ShowResultWindow(Event event_to_be_shown) {       
        
        try {
            if (App.G.LocationControllers==null) App.G.LocationControllers = new ArrayList<>();
            
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("LocationResult2.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
      
            App.G.LocationControllers.add((LocationResult2Controller)fxmlLoader1.getController());
                  
            Stage stage = new Stage();
            stage.setResizable(true);
            stage.setOnCloseRequest((event) -> event.consume());
           
            //stage.setAlwaysOnTop(true);
            //stage.initModality(Modality.APPLICATION_MODAL);
                        
            stage.setScene(new Scene(root1));  
            stage.setMaximized(false);
                            
            App.G.LocationControllers.get(App.G.LocationControllers.size()-1).setLocationResultEvent(event_to_be_shown);
            App.G.LocationControllers.get(App.G.LocationControllers.size()-1).ShowData();
            
           // stage.initOwner(PrimaryStage);
            
            stage.setOnShown(event -> {
                App.G.LocationControllers.get(App.G.LocationControllers.size()-1).FinalizeStartup();
            }); 
            
            if (myEvent.getInnerObjectEvent().getOrigins().get(0).getId()!=null){
                stage.setTitle("I.N.G.V. SIT - Location result for origin " + 
                        myEvent.getInnerObjectEvent().getOrigins().get(0).getId());
                    //event_to_be_shown.getInnerObjectEvent().getOrigins().get(0).getId());
                App.G.LocationControllers.get(App.G.LocationControllers.size()-1).setLocalOriginID(myEvent.getInnerObjectEvent().getOrigins().get(0).getId());
            }else{
                if (myEvent.getWork_origin_ID()!=null) {
                    stage.setTitle("I.N.G.V. SIT - Location result for origin " + myEvent.getWork_origin_ID());
                        //event_to_be_shown.getWork_origin_ID());
                    App.G.LocationControllers.get(App.G.LocationControllers.size()-1).setLocalOriginID(myEvent.getWork_origin_ID());
                } else {
                    stage.setTitle("I.N.G.V. SIT - Location result for origin **Unknown ID**" );
                    App.G.LocationControllers.get(App.G.LocationControllers.size()-1).setLocalOriginID(App.G.LocationControllers.size()-1);
                }
            } 
                       
            App.G.LocationControllers.get(App.G.LocationControllers.size()-1).setPrimaryStage(stage);
            
            App.G.LocationControllers.get(App.G.LocationControllers.size()-1).getPrimaryStage().showAndWait();
           
            
            Event tmp = App.G.LocationControllers.get(App.G.LocationControllers.size()-1).getResult();
            if (myEvent.getInnerObjectEvent().getOrigins().get(0).getId()!=null)
                App.G.LocationControllers.remove(App.G.FindLocationControllerByOriginId(myEvent.getInnerObjectEvent().getOrigins().get(0).getId()));
            else if (myEvent.getWork_origin_ID()!=null)
                App.G.LocationControllers.remove(App.G.FindLocationControllerByOriginId(myEvent.getWork_origin_ID()));
            else
                App.G.LocationControllers.remove(App.G.FindLocationControllerByOriginId(Long.valueOf(App.G.LocationControllers.size()-1)));
           
            return tmp;
        }  catch (Exception ex) {
//            if (controller_added)
//                App.G.LocationControllers.remove(App.G.LocationControllers.size()-1);
            sitDialog.ShowErrorMessage(ex.getMessage(), this.getPrimaryStage());
            return null; 
        }
    }  
//------------------------------------------------------------------------------
    @FXML
    private void btnSave_Click(ActionEvent event) {      
        try{
            
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("ChooseHowToSaveOrigin.fxml"));
            Parent root1 = (Parent) fxmlLoader1.load();
            
            Stage stage = new Stage();
            stage.setResizable(true);
            stage.setOnCloseRequest((eventclose) -> eventclose.consume());
           
            //stage.setAlwaysOnTop(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(PrimaryStage);
                        
            stage.setScene(new Scene(root1));  
            stage.setTitle("SIT - Choose how to save ");                    
            stage.setMaximized(false);
            
            ((ChooseHowToSaveOriginController)fxmlLoader1.getController()).setPrimaryStage(stage);
            
            stage.showAndWait();
            
            String result = ((ChooseHowToSaveOriginController)fxmlLoader1.getController()).getResult();
            // Possible values for result:
            // "CANCEL", "SAVE", "SAVE_AND_NOTIFY", "DRAFT"
            switch (result){
                case "CANCEL":
                    break;
                case "SAVE":                    
                    SaveSimple();
                    break;    
                case "SAVE_AND_NOTIFY":                   
                    SaveAndNotify();
                    break;
                case "DRAFT":
                    SaveDraft();
                    break;    
            }
        } catch (Exception ex){
        
        }

    }
    
    
    private void SaveSimple(){ 
        
         if (sitDialog.ShowConfirmationMessage("Please confirm", "Location will be saved in the database.", this.getPrimaryStage())==ButtonType.CANCEL) return;
        
        Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Starting event save.");
  
        Long local_id_localspace = myEvent.getInnerObjectEvent().getIdLocalspace();
        ObjectLocalspace LS = new ObjectLocalspace();
        LS.setDescription(myEvent.getInnerObjectEvent().getLocalspace().getDescription());
        LS.setName(myEvent.getInnerObjectEvent().getLocalspace().getName());
        


        //-----------------------------------
//        try {
//            boolean saved=SaveInSeisEV(false);
//            if (saved)
//                sitDialog.ShowInformationMessage("Evento salvato anche in SeisEV!!", this.getPrimaryStage());
//            else
//                sitDialog.ShowErrorMessage("Non sono ruiscito a salvare l'evento su seisEV.", this.getPrimaryStage());  
//        } catch (Exception ex) {    
//            //
//        }
//        //-----------------------------------
        
        
        
        
        switch (post_data()){
            case -1:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Generic Exception: Event save not completed!");
                sitDialog.ShowErrorMessage("Generic Exception: Event save not completed!", this.getPrimaryStage());
                break;
            case -2:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Api Exception: Unauthorized user or user session expired");
                sitDialog.ShowErrorMessage("Api Exception: Unauthorized user or user session expired.\nPlease Login", this.getPrimaryStage());
                break;
            default:
                // Mix dei due eventi e aggiornamento interfaccia
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Location saved");
                sitDialog.ShowInformationMessage("Your location has been saved into the database!!", this.getPrimaryStage());
        }
    }
    
    
//------------------------------------------------------------------------------
   
    private void SaveDraft() {
        if (sitDialog.ShowConfirmationMessage("Please confirm", "Location will be saved as a DRAFT", this.getPrimaryStage())==ButtonType.CANCEL) return;
        
        Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Starting event save as draft.", this.getPrimaryStage());
        ObjectTypeOrigin to = new ObjectTypeOrigin();
        to.setName(TypeOriginName.HYPOCENTER);    
        to.setVersionName("draft");
//                
        myEvent.getInnerObjectEvent().getOrigins().get(0).setTypeOrigin(to);
        
        switch (post_data()){
            case -1:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Generic Exception: Event save not completed!");
                sitDialog.ShowErrorMessage("Generic Exception: Event save not completed!", this.getPrimaryStage());
                break;
            case -2:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Api Exception: Unauthorized user or user session expired");
                sitDialog.ShowErrorMessage("Api Exception: Unauthorized user or user session expired.\nPlease Login", this.getPrimaryStage());
                break;
            default:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Location saved");
                sitDialog.ShowInformationMessage("Your location has been saved as a draft.", this.getPrimaryStage());  
            break;
        }  
    }
//------------------------------------------------------------------------------    
    
    private void SaveAndNotify() {
        if (sitDialog.ShowConfirmationMessage("Please confirm", 
                "Location will be saved and sent to DPC", this.getPrimaryStage())==ButtonType.CANCEL) return;
//        
        int neworiginid=post_data();
               
        switch (neworiginid){
            case -1:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Generic Exception: Event save not completed!");
                sitDialog.ShowErrorMessage("Generic Exception: Event save not completed!", this.getPrimaryStage());
                break;
            case -2:
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Api Exception: Unauthorized user or user session expired");
                sitDialog.ShowErrorMessage("Api Exception: Unauthorized user or user session expired.\nPlease Login", this.getPrimaryStage());
                break;
            default:
                Long local_id_localspace = myEvent.getInnerObjectEvent().getIdLocalspace();
                ObjectLocalspace LS = new ObjectLocalspace();
                LS.setDescription(myEvent.getInnerObjectEvent().getLocalspace().getDescription());
                LS.setName(myEvent.getInnerObjectEvent().getLocalspace().getName());

                // Mix dei due eventi e aggiornamento interfaccia
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Location saved");
                sitDialog.ShowInformationMessage("Your location has been saved into the database!!", this.getPrimaryStage());

                // Chiama la rotta per settare l'evento a comunicato:
                StoreApi writer=new StoreApi();
                writer.getApiClient().setReadTimeout(30000);
                AddOriginFlagRequest addOriginFlagRequest= new AddOriginFlagRequest();
                AddOriginFlagRequestData data = new AddOriginFlagRequestData();

                ObjectOriginFlag originFlagItem = new ObjectOriginFlag();
                originFlagItem.setName("DPC");
                originFlagItem.setValue(Float.valueOf("1"));
                originFlagItem.setNote("Evento comunicato a DPC - Event notified to CP");
                data.setOriginid(Long.valueOf(neworiginid)); 
                data.addOriginFlagItem(originFlagItem);

                addOriginFlagRequest.setData(data);

                try {
                    App.logger.debug("WS-LOG: SaveAndNotify()--> writer.addOriginFlag posting");

                    writer.addOriginFlag(addOriginFlagRequest);

                    App.logger.debug("WS-LOG: SaveAndNotify()--> writer.addOriginFlag finished");

                    sitDialog.ShowInformationMessage("Location was also marked for DPC notifications!!", this.getPrimaryStage());
                } catch (ApiException aex){
                     sitDialog.ShowErrorMessage("Event save not completed: error while marking origin flags!!", this.getPrimaryStage());
                }
                break;
        }   
    }
//------------------------------------------------------------------------------    
    private int post_data(){
        try{
            StoreApi writer=new StoreApi(); 
            writer.getApiClient().setReadTimeout(120000);
            writer.getApiClient().setWriteTimeout(120000);
//
            ObjectOrigin originsItem; 
           
            AddOriginRequestData data = new AddOriginRequestData();
            
            AddOriginRequest addOriginRequest=new AddOriginRequest();
            
            // Annulla tutti gli id_localspace
            myEvent.ResetLocalspacesAndProvenances();
            
            if ((App.G.User!=null) && (App.G.User.isLoggedIn()))
                writer.getApiClient().setBearerToken(App.G.User.getToken());
            else
                writer.getApiClient().setBearerToken("");
           
            originsItem = myEvent.getInnerObjectEvent().getOrigins().get(0); 
                              
            data.addOriginsItem(originsItem);
            data.setEventid(Long.valueOf(myEvent.getWork_event_ID()));
            
            addOriginRequest.setData(data);
            
            App.logger.debug("WS-LOG: post_data()--> writer.addOrigin posting");
            App.logger.debug("WS-LOG: post_data() Request body \n" + data.toJson());
            AddOrigin201Response resp = writer.addOrigin(addOriginRequest);
            App.logger.debug("WS-LOG: post_data()--> writer.addOrigin finished");
            
            if ((resp!=null) && (resp.getData()!=null)){
                AddOrigin201ResponseData respdata = resp.getData();
                if ((respdata.getOrigins()!=null) && (!respdata.getOrigins().isEmpty())){
                    return respdata.getOrigins().get(0).getId().intValue();
                } else return -1;
            } else return -1;
        
        
            
        } catch (ApiException aex){
            
            return -2;  // ApiException: unauthorized user
        } catch (Exception ex) {
            
            return -1; // Generic exception
        }
    }
//------------------------------------------------------------------------------    
    
    
    @FXML
    private void btnSaveQML_Click(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
 
        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("QuakeML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
 
        Stage stage = (Stage) main_anchor_pane.getScene().getWindow();
        
        //Show save file dialog       
        File file = fileChooser.showSaveDialog(stage);
 
        if (file != null) {
                //saveTextToFile(sampleText, file);
                QuakeMLWriter w = new QuakeMLWriter();
//        
                w.setSource_event(myEvent);
                w.saveEventDataToQMLFile_1_2(file);
        }
    }
//--------------------------------------------------------------------------------    
    private void SetCustomCursor(String fName){
        URL url;
        url = getClass().getResource(fName);
        try {
            Image img = new Image(url.openStream());
            getPrimaryStage().getScene().setCursor(new ImageCursor(img));
        } catch (IOException e) {
            //logger.warn("Failed to load cursor icon from {}", url);
        }
    }
//--------------------------------------------------------------------------------
    /**
     * @param in_myEvent the in_myEvent to set
     */
    public void setMyEvent(Event in_myEvent) {       
        myEvent = in_myEvent;  
    }  
//--------------------------------------------------------------------------------    
      /**
     * @return myEvent
     */
    public Event getMyEvent() {
        return myEvent;
    }
    
//--------------------------------------------------------------------------------    
    /**
     * @return the PrimaryStage
     */
    public Stage getPrimaryStage() {
        return PrimaryStage;
    }
//--------------------------------------------------------------------------------
    /**
     * @param in_PrimaryStage the PrimaryStage to set
     */
    public void setPrimaryStage(Stage in_PrimaryStage) {
        PrimaryStage = in_PrimaryStage;       
    }
//------------------------------------------------------------------------------    
    public void DisplayAll() {
        if ( !form_initializing) {
            if (show_waves_PREVIEW(-1)) {
                waves_split.setDividerPosition(0, 0.6);
                info_split.setDividerPosition(0, 0.5);
                info_split_inner.setDividerPosition(0, 0.5);
                LoadAndShowTerna(0);
                show_phase_lines(plot_combinato);
                showMap();
                populate_magnitude_tabs();
                populate_phases_list();  
                populate_hypoinfo_tab();
                ResetRadios();
            }
        }
    }  
    //------------------------------------------------------------------------------
    public void DisplayOnlyMetadata() { 
        showMap();
        populate_magnitude_tabs();
        populate_phases_list();  
        populate_picks_list();
        populate_hypoinfo_tab();
    }  
    //------------------------------------------------------------------------------
    public void DisplayOnlyWaves(int new_current_wave_id) {
        if (show_waves_PREVIEW(new_current_wave_id)) {
            show_phase_lines(plot_combinato);
        }
    } 
//------------------------------------------------------------------------------ 
    private void showMap() {
            try {
                if (MH!=null){
                MH.close();
                MH=null;
            }

            MH = new MapHandler((int)anchor_top_right.getWidth(), (int)anchor_top_right.getHeight()-45);
            //MH = new MapHandler(500,400);
            MH.setIdWavesController(idControllers);
            MH.setACTION("IDENTIFY");
           
            if ((myEvent!=null) && (myEvent.getInnerObjectEvent().getOrigins()!=null))
                MH.CreateMapWithCanvas(myEvent.ToStationArray(), 
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getLat(), myEvent.getInnerObjectEvent().getOrigins().get(0).getLon(), 
                    0,0, 0, 0,
                    true, true);
            else
                MH.CreateMapWithCanvas(null, 0, 0, 
                        App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
                        App.G.options.get_box_minLat(),App.G.options.get_box_maxLat(), 
                        true, true);
             
            if (MH.getLayerIndex("Hypocenter")!=-1){
                MH.SetMapBounds(MH.getLayerIndex("Hypocenter"));
                MH.RefreshMap();
            }


            anchor_top_right.getChildren().add(MH.getCanvas()); 
        
            AnchorPane.setBottomAnchor(MH.getCanvas(), 0.0);
            AnchorPane.setTopAnchor(MH.getCanvas(), 45.0);
            AnchorPane.setLeftAnchor(MH.getCanvas(), 0.0);
            AnchorPane.setRightAnchor(MH.getCanvas(), 0.0);     
          
        } catch (Exception ex){
            
        }
    }
//------------------------------------------------------------------------------   
    private void Map_ShowTriggeredStations(){
        // This buils the v_staz_triggered array for Map.displayTriggeredStations

        if (myEvent==null) return;
        if (myEvent.getNStations()==0) return;
        try {
            ArrayList staz = myEvent.ToStationArray();
            if (staz !=null) {
                //map.displayTriggeredStations(staz);
            }      
        } catch (Exception ex) {
            // Log something....
        }    
    }
//------------------------------------------------------------------------------   
    private void Map_TurnStationsON(ArrayList<String> codici){
        // This buils the v_staz_triggered array for Map.displayTriggeredStations

        if (myEvent==null) return;
        if (myEvent.getNStations()==0) return;
        try {
            ArrayList<String[]> staz_to_be_turnedON = make_array_with_coords(codici);

            if (staz_to_be_turnedON !=null) {
                MH.BlinkStationsSet(staz_to_be_turnedON);

               // map.HighlightStations(staz_to_be_turnedON, Boolean.TRUE);
            }      
        } catch (Exception ex) {
            // Log something....
            int k=0;
            k++;
        }    
    }
//------------------------------------------------------------------------------   
    private void Map_TurnStationsOFF(){
//        // This clears the highlighted sations layer
//        try {
//            //map.HighlightStations(null, Boolean.FALSE);
//            MH.clear_blinks();
//        } catch (Exception ex) {}
    }
//------------------------------------------------------------------------------
    private void Restore_ALLWAVES_UndoLastFilter(){
        try {
            if (myEvent.getNStations()>0) {
                for (int i = 0; i < myEvent.getNStations(); i++){
                        myEvent.getStations().get(i).getWave(0).Restore_Samples();
                        if (myEvent.getStations().get(i).getWave(0).getFilters()>0)
                            myEvent.getStations().get(i).getWave(0).setFilters(myEvent.getStations().get(i).getWave(0).getFilters()-1);
                }  
            }
        } catch (Exception ex) {
            //
        }     
    }    
//------------------------------------------------------------------------------    
    public void Restore_TERNA_UndoLastFilter(){
        try {
            if (myEvent.getActiveTerna().getWaves()!=null) {
                for (int i = 0; i < myEvent.getActiveTerna().getWaves().size(); i++){
                        myEvent.getActiveTerna().getWaves().get(i).Restore_Samples();
                        if (myEvent.getActiveTerna().getWaves().get(i).getFilters()>0)
                            myEvent.getActiveTerna().getWaves().get(i).setFilters(myEvent.getActiveTerna().getWaves().get(i).getFilters()-1);
                }  
            }
        } catch (Exception ex) {
            //
        }     
    }    
   
//------------------------------------------------------------------------------    
    private void Restore_ALLWAVES_ResetOriginal(){
        try {
            if (myEvent.getNStations()>0) {
                for (int i = 0; i < myEvent.getNStations(); i++){
                        myEvent.getStations().get(i).getWave(0).Restore_Original_Samples();
                        if (myEvent.getStations().get(i).getWave(0).getFilters()>0)
                            myEvent.getStations().get(i).getWave(0).setFilters(myEvent.getStations().get(i).getWave(0).getFilters()-1);
                }  
            }
        } catch (Exception ex) {
            //
        }     
    }    
//------------------------------------------------------------------------------
    private ArrayList make_array_with_coords(ArrayList<String> codici){
        try{
            //
            // In realtà produce sempre un array di un solo elelemnto
            //
            ArrayList out_vector = new ArrayList();
            boolean fnd=false;
            int i=0;
            String[] element; // = new String[3];
            String srcCode="";
            for (int j=0; j<codici.size(); j++){
                srcCode =codici.get(j);
                while ((!fnd)&&(i<App.G.SeismicNet.getStations().size())){
                    Station tmpSta = (Station)App.G.SeismicNet.getStations().get(i);
                    if (srcCode.equalsIgnoreCase(tmpSta.getCode())){
                        // 
                        element = new String[3];
                        element[0] = tmpSta.getCode();
                        element[1] = String.valueOf(tmpSta.getLat());
                        element[2] = String.valueOf(tmpSta.getLon());
                        //
                        out_vector.add((String[])element); 
                        //
                        fnd = true;
                    } else i+=1;
                }
            }
            return out_vector;
        } catch (Exception ex){
            return null;
        }
    }
    
//------------------------------------------------------------------------------    
 @FXML
    private void btnRepaintBigger_Click(ActionEvent event) {
        //volte+=1;  
        //paint_PREVIEW_ONLY(true);
        chart_viewer_PREVIEW.setPrefHeight(chart_viewer_PREVIEW.getHeight()* 2);
    }  
//------------------------------------------------------------------------------        
    @FXML
    private void btnRepaintSmaller_Click(ActionEvent event) {
        
        chart_viewer_PREVIEW.setPrefHeight(chart_viewer_PREVIEW.getHeight()/2);       
//        if (size_factor >=2) {
//            size_factor-=1;
//            paint_PREVIEW_ONLY(true);
//        }
    }
//------------------------------------------------------------------------------    
    public boolean show_waves_PREVIEW(int new_preview_subplot_index){        
        Waveform tmpWave;
        int idStation;
        TimeSeries series;
        TimeSeriesCollection dataset;

     //   
        float time;
        LocalDateTime new_date;
        LocalDateTime dateStart;
        long timestamp;
        long current_time;
        Millisecond ms;
        TimeSeriesDataItem item;
        NumberAxis rangeAxis1;
        XYPlot subplot;
        //XYItemRenderer renderer;
        LegendTitle legend_title;
        java.awt.Color wave_color, back_color, legend_color;
        BlockFrame frame_legend;

        //
        Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Plotting waves");
    //    
        try{
            if (myEvent == null) return true;
            if (!myEvent.Event_has_waves()) return false;
    //  
            back_color =  Color.DARK_GRAY; 
            legend_color= Color.DARK_GRAY;  
            frame_legend=new BlockBorder(Color.DARK_GRAY); 

            Font legend_font=new Font("Dialog", Font.BOLD, 9);
    //    
            if (plot_combinato==null)
                plot_combinato = new CombinedDomainXYPlot(new DateAxis("Time"));
            plot_combinato.getDomainAxis().setTickLabelPaint(Color.white);
            //plot_combinato.setRangeAxis(rangeAxis1);
            
            plot_combinato.setOrientation(PlotOrientation.VERTICAL);
            
            plot_combinato.getDomainAxis().setLabelPaint(Color.white);
//         
            ZoneId zoneId = ZoneId.of("UTC");
            int wCounter=-1;
            float ymin, ymax;
            for (idStation=0; idStation < myEvent.getNStations(); idStation++) { 
                if (myEvent.getStation(idStation).getNWaves()>0) {
                    
                    tmpWave = myEvent.getStation(idStation).getWave(0); // should search for the vertical one  
                    wCounter+=1;
                    if (tmpWave.getPlot_box_id()==-1) {
                        List b = Arrays.asList(ArrayUtils.toObject(tmpWave.getY()));
                    
                        ymin = (Float)Collections.min(b);
                        ymax = (Float)Collections.max(b);

                        if (ymin!=ymax){
                            series = new TimeSeries(tmpWave.getStationCode() + " " +tmpWave.getChannelCode());
                            dateStart = tmpWave.getStartTime();            
                            timestamp = dateStart.atZone(zoneId).toInstant().toEpochMilli();
                            for (Integer i =0; i< tmpWave.getnSamples(); i++) {  
                                //------------------------------------------------------------------
                                time = i.floatValue()/tmpWave.getSamplingRate(); //the time value of the sample
                                current_time= timestamp + Float.valueOf(time*1000).intValue();
                                new_date = millsToLocalDateTime(current_time);
                                ms= new Millisecond(Date.from(new_date.atZone(zoneId).toInstant()));          
                                item = new TimeSeriesDataItem(ms, tmpWave.getY(i)); 
                                //------------------------------------------------------------------
                                series.addOrUpdate(item);
                            }     

                            //wCounter+=1;
                            myEvent.setnOpenWaves(wCounter);
                            myEvent.getStation(idStation).getWave(0).setPlot_box_id(wCounter);

                            dataset = new TimeSeriesCollection();
                            dataset.addSeries(series);
            //        
                            rangeAxis1 = new NumberAxis();
                            rangeAxis1.setAutoRange(false);  // 2023-02-06
                            rangeAxis1.setRange(ymin, ymax);

                            SamplingXYLineRenderer renderer2 = new SamplingXYLineRenderer(); 

                            if (tmpWave.getDataProvider().equalsIgnoreCase("FDSN"))
                                wave_color = Color.ORANGE;
                            else
                                wave_color= new Color(90,222,50);

                            renderer2.setSeriesPaint(0, wave_color);
            //         

                            subplot = new XYPlot(dataset, null, rangeAxis1, renderer2);
                            subplot.setBackgroundPaint(back_color);           // Colore dello sfondo
                            subplot.getRangeAxis().setTickLabelsVisible(false);

                            // Mostra le linee crosshair per pickare tempo-ampiezza
                            subplot.setDomainCrosshairVisible(false);
                            subplot.setRangeCrosshairVisible(false);          
    //           
                            legend_title = new LegendTitle(subplot);
                            legend_title.setItemFont(legend_font);

                            legend_title.setBackgroundPaint(legend_color); 
                            legend_title.setItemPaint(java.awt.Color.WHITE);                              
                            legend_title.setFrame(frame_legend);
                            legend_title.setPosition(RectangleEdge.BOTTOM);
                            XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, legend_title, RectangleAnchor.BOTTOM_RIGHT);                   
                            ta.setMaxWidth(0.48);
                            subplot.addAnnotation(ta);   
    //
                            plot_combinato.add(subplot); 
                        } else {
                            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Wave is flat: " + tmpWave.getStationCode() + " " + tmpWave.getChannelCode());
                        }
                    }
                    
                      
                }           
            }          
    //      -----------------------------------------        
            plot_combinato.setDomainPannable(true);            
    //      -----------------------------------------  
            chart_viewer_PREVIEW.addChartMouseListener(new ChartMouseListenerFX() {
                @Override
                public void chartMouseClicked(ChartMouseEventFX event) {
                    //event.getTrigger().consume();
                    if (event.getTrigger().getButton()==MouseButton.PRIMARY) {                   
                        chartPreview_MouseClicked((int)event.getTrigger().getX(), (int)event.getTrigger().getY());
                    } else {
                        // Right button click
                        OPERATION_PREVIEW_WAVE=null;
                        getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
                    }
                    
                    event.getTrigger().consume();
                }

                @Override
                public void chartMouseMoved(ChartMouseEventFX event) { 
                    event.getTrigger().consume();
                    //chartPreview_MouseMoved(event.getTrigger().getX(), event.getTrigger().getY());
                }

            }); 

            if (new_preview_subplot_index>-1)
                preview_subplot_index=new_preview_subplot_index;
            else
                preview_subplot_index=0;    
            
            waves_scrollbox_preview.setFitToHeight(false);
            waves_scrollbox_preview.setFitToWidth(true);

            if (myEvent.getNStations()>30) size_factor=10;
            //
            paint_PREVIEW_ONLY();
            //
            return true;
    } catch (Exception ex) {
        
        Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        return false;
    }            
}
   
//------------------------------------------------------------------------------        
    private void paint_PREVIEW_ONLY(){     
        chart_viewer_PREVIEW.setPrefHeight(size_factor*waves_scrollbox_preview.getHeight());
        
        String panel_title;
        if (myEvent.getInnerObjectEvent().getOrigins().get(0).getOt()!=null){
            panel_title = "Origin Time: " + 
                myEvent.getInnerObjectEvent().getOrigins().get(0).getOt().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS").withZone(ZoneId.of("UTC")));
        } else {
            panel_title = "Origin Time: ** Unknown **";
        }
    
    
        chart_viewer_PREVIEW.setChart(new JFreeChart(panel_title, JFreeChart.DEFAULT_TITLE_FONT, plot_combinato, false));
        chart_viewer_PREVIEW.getChart().setBackgroundPaint(java.awt.Color.DARK_GRAY);
        chart_viewer_PREVIEW.getChart().getTitle().setPaint(java.awt.Color.LIGHT_GRAY);      
    }
//------------------------------------------------------------------------------    
    public static LocalDateTime millsToLocalDateTime(long millis) {
      Instant instant = Instant.ofEpochMilli(millis);
      LocalDateTime date = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
      return date;
  }    
//------------------------------------------------------------------------------    
    public void show_phase_lines(CombinedDomainXYPlot combinedplot) {
        //----------------------------------------------------------------------
        // ATTENZIONE: Una stazione potrebbe non avere WAVES!!!!!
        //----------------------------------------------------------------------
        if (combinedplot == null) return;
        if (combinedplot.getSubplots()==null) return;
        if (combinedplot.getSubplots().isEmpty()) return;
    // 
        int idStaz;
        ObjectArrival tmpPH;
        ValueMarker phase_marker;
        boolean isWA=true;
        java.awt.Color backPaint = Color.DARK_GRAY;
  
        if (!combinedplot.getChart().getTitle().getText().contains("Wood-Anderson")){
            isWA=false;
            //  Clear existing markers    
            for (int idSub=0; idSub < combinedplot.getSubplots().size(); idSub++) {
                ((XYPlot)(combinedplot.getSubplots().get(idSub))).clearDomainMarkers();
            } 
        }
              
        for (idStaz = 0; idStaz < myEvent.getNStations(); idStaz++){
            if ((myEvent.getStation(idStaz).getNWaves()>0) && (myEvent.getStation(idStaz).getNPhases()>0)) {
                for (int idPh=0; idPh<myEvent.getStation(idStaz).getPhases().size(); idPh++) {
                    tmpPH = myEvent.getStation(idStaz).getPhase(idPh);                   
                    if (tmpPH.getArrTimeIsUsed()==null) tmpPH.setArrTimeIsUsed(true);
                    phase_marker = Make_Marker(tmpPH.getPick().getArrivalTime().toLocalDateTime(), 
                            tmpPH.getIscCode(), 
                            backPaint,
                            tmpPH.getArrTimeIsUsed());
//     
                    if (phase_marker != null) {
                        if (!isWA)
                            ((XYPlot)(combinedplot.getSubplots().get(myEvent.getStation(idStaz).getWave(0).getPlot_box_id()))).addDomainMarker(phase_marker);          
                        else
                            ((XYPlot)(combinedplot.getSubplots().get(myEvent.getStation(idStaz).getWave_WA(0).getPlot_box_id_WA()))).addDomainMarker(phase_marker);          
                    }
                }                                 
                              
                if (myEvent.getStation(idStaz).getCoda_duration()>0){
                    int phase_id = myEvent.getStation(idStaz).FindFirstPhase("P");
                    if (phase_id >-1){
                        LocalDateTime tempoP =
                                ((ObjectArrival)myEvent.getStation(idStaz).getPhases().get(phase_id)).getPick().getArrivalTime().toLocalDateTime();
                        LocalDateTime tempo_coda = 
                                tempoP.plusNanos((long)(myEvent.getStation(idStaz).getCoda_duration()*1000000000));
                        
                        // Pick the coda duration
                        // Disegna la coda
                        ValueMarker coda_marker = Make_Marker(
                                tempo_coda, 
                                "Coda", 
                                backPaint,
                                true);
    //     
                        if (coda_marker != null) {
                            if (!isWA)
                                ((XYPlot)(combinedplot.getSubplots().get(myEvent.getStation(idStaz).getWave(0).getPlot_box_id()))).addDomainMarker(coda_marker);          
                            else
                                ((XYPlot)(combinedplot.getSubplots().get(myEvent.getStation(idStaz).getWave_WA(0).getPlot_box_id_WA()))).addDomainMarker(coda_marker);          
                        }
                    }               
                }
            }          
        }   
    }
    
    
    private void show_phase_lines(int StationIndex) {
        //--------------------------------------------------------------------------
        // ATTENZIONE: Una stazione potrebbe non avere WAVES!!!!!
        //--------------------------------------------------------------------------
        phase_was_picked=false;
        if (plot_combinato == null) return;
    // 
        ObjectArrival tmpPH;
        ValueMarker phase_marker;
        
        if ((myEvent.getStation(StationIndex).getNWaves()>0) && (myEvent.getStation(StationIndex).getNPhases()>0)) {
            //  Clear existing markers    
            ((XYPlot)(plot_combinato.getSubplots().get(myEvent.getStation(StationIndex).getWave(0).getPlot_box_id()))).clearDomainMarkers();

            for (int idPh=0; idPh<myEvent.getStation(StationIndex).getPhases().size(); idPh++) {
                tmpPH = myEvent.getStation(StationIndex).getPhase(idPh);
                if (tmpPH.getPick().getArrivalTime() != null) {
                    if (tmpPH.getArrTimeIsUsed()==null) tmpPH.setArrTimeIsUsed(true);
                    phase_marker = Make_Marker(tmpPH.getPick().getArrivalTime().toLocalDateTime(), 
                            tmpPH.getIscCode(), 
                            (java.awt.Color)((XYPlot)(plot_combinato.getSubplots().get(myEvent.getStation(StationIndex).getWave(0).getPlot_box_id()))).getBackgroundPaint(),
                            tmpPH.getArrTimeIsUsed());
//     
                    if (phase_marker != null) {
                        ((XYPlot)(plot_combinato.getSubplots().get(myEvent.getStation(StationIndex).getWave(0).getPlot_box_id()))).addDomainMarker(phase_marker);          
                    }
                } 
            }                                 
            // Paint the "coda" marker if a durastion was set
            if (myEvent.getStation(StationIndex).getCoda_duration()>0){
                int phase_id = myEvent.getStation(StationIndex).FindFirstPhase("P");
                if (phase_id >-1){
                    LocalDateTime tempoP =
                            ((ObjectArrival)myEvent.getStation(StationIndex).getPhases().get(phase_id)).getPick().getArrivalTime().toLocalDateTime();
                    LocalDateTime tempo_coda = 
                            tempoP.plusNanos((long)(myEvent.getStation(StationIndex).getCoda_duration()*1000000000));

                    // Pick the coda duration
                    // Disegna la coda
                    ValueMarker coda_marker = Make_Marker(
                            tempo_coda, 
                            "Coda", 
                            (java.awt.Color)((XYPlot)(plot_combinato.getSubplots().get(myEvent.getStation(StationIndex).getWave(0).getPlot_box_id()))).getBackgroundPaint(),
                            true);
//     
                    if (coda_marker != null) {
                        ((XYPlot)(plot_combinato.getSubplots().get(myEvent.getStation(StationIndex).getWave(0).getPlot_box_id()))).addDomainMarker(coda_marker);          
                    }
                }               
            }
        }          
           
    }
//------------------------------------------------------------------------------    
    private void show_phases_TERNA() {  

        ObjectArrival tmpPH;
        ValueMarker phase_marker;

        int idTernaStation = myEvent.getActiveTerna().getStationIndex();

    //  Clear existing markers    
        for (int idSub=0; idSub < getPlot_combinato_TERNA().getSubplots().size(); idSub++) {
            ((XYPlot)(getPlot_combinato_TERNA().getSubplots().get(idSub))).clearDomainMarkers();
        }  
    // 
    //      Paint new markers
        if (myEvent.getStation(idTernaStation).getPhases() != null){     
            for (int idPh=0; idPh<myEvent.getStation(idTernaStation).getNPhases(); idPh++) {
                tmpPH = myEvent.getStation(idTernaStation).getPhase(idPh);
                if (tmpPH.getPick().getArrivalTime() != null) {
                    Paint p = ((XYPlot)(getPlot_combinato_TERNA().getSubplots().get(0))).getBackgroundPaint();
                    if (tmpPH.getArrTimeIsUsed()==null) tmpPH.setArrTimeIsUsed(true);
                    phase_marker = Make_Marker(tmpPH.getPick().getArrivalTime().toLocalDateTime(), tmpPH.getIscCode(), (java.awt.Color)p, tmpPH.getArrTimeIsUsed());
    //     
                    if (phase_marker != null) {
                        for (int idSub=0; idSub < getPlot_combinato_TERNA().getSubplots().size(); idSub++) {
                            ((XYPlot)(getPlot_combinato_TERNA().getSubplots().get(idSub))).addDomainMarker(phase_marker);          
                        }
                    }

                    if ((tmpPH.getPick().getLowerUncertainty()!=null) && tmpPH.getPick().getUpperUncertainty()!=null) {
                        // Show the time-uncertainty range
                        LocalDateTime t_start, t_end;
                        t_start = tmpPH.getPick().getArrivalTime().toLocalDateTime().minusNanos((long)(tmpPH.getPick().getLowerUncertainty()*1000000000));
                        t_end = tmpPH.getPick().getArrivalTime().toLocalDateTime().plusNanos((long)(tmpPH.getPick().getUpperUncertainty()*1000000000));

                        Long millis_Xstart, millis_Xend;
                        ZonedDateTime zdt;
                        zdt = t_start.atZone(ZoneId.of("UTC"));
                        millis_Xstart = zdt.toInstant().toEpochMilli();
                        zdt = t_end.atZone(ZoneId.of("UTC"));
                        millis_Xend = zdt.toInstant().toEpochMilli();

                        PickedObject po = new PickedObject(millis_Xstart, millis_Xend,
                            t_start, t_end);
                        po.setPick_arrival_time(tmpPH.getPick().getArrivalTime().toLocalDateTime());

                        for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {
                            po.DrawTimeIntervalWhithLabels((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i));
                        }                    

                        WEIGHT_BOXES.add(po);
                    }                   
                }        
            }        
                    
            if (myEvent.getStation(idTernaStation).getCoda_duration()>0.0){
                int Pphase_id = myEvent.getStation(idTernaStation).FindFirstPhase("P");
                if (Pphase_id >-1){
                    LocalDateTime tempoP =
                            ((ObjectArrival)myEvent.getStation(idTernaStation).getPhases().get(Pphase_id)).getPick().getArrivalTime().toLocalDateTime();
                    LocalDateTime tempo_coda = 
                            tempoP.plusNanos((long)myEvent.getStation(idTernaStation).getCoda_duration()*1000000000);

                    // Pick the coda duration
                    // Disegna la coda
                    ValueMarker coda_marker = Make_Marker(
                            tempo_coda, 
                            "Coda", 
                            (java.awt.Color)((XYPlot)(plot_combinato.getSubplots().get(myEvent.getStation(idTernaStation).getWave(0).getPlot_box_id()))).getBackgroundPaint(),
                            true);
//     
                    if (coda_marker != null) {
                         
                        for (int idSub=0; idSub < getPlot_combinato_TERNA().getSubplots().size(); idSub++) {
                            ((XYPlot)(getPlot_combinato_TERNA().getSubplots().get(idSub))).addDomainMarker(coda_marker);    
                        }
                    }
                }   
            
            
            }
        }       
    }
    //------------------------------------------------------------------------------
    private void populate_hypoinfo_tab(){
        try {
            ShowEventSummary_short();
            populate_towns_list();
        } catch (Exception ex) {
        }
    
    }
    //------------------------------------------------------------------------------  
    private void ShowEventSummary_short(){
        try {       
            if (myEvent==null) {
                Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Hypocenter is null");
                lblLat.setText("00.00");
                lblLon.setText("00.00");
                lblDep.setText("00.00" + " km");
                lblErrH.setText("00.00");
                lblErrZ.setText("00.00");

                //lblArea.setText("Unknown area");

                lblOT.setText("HH:MM:SS");        
                return;
            }

            lblLat.setText(String.format("%5.2f", myEvent.getInnerObjectEvent().getOrigins().get(0).getLat()));
            lblLon.setText(String.format("%5.2f", myEvent.getInnerObjectEvent().getOrigins().get(0).getLon()));
            lblDep.setText(myEvent.getInnerObjectEvent().getOrigins().get(0).getDepth() + " km");

            if ((myEvent!=null) && (myEvent.getInnerObjectEvent().getOrigins()!=null) && (!myEvent.getInnerObjectEvent().getOrigins().isEmpty())){
                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getErrH()!=null){
                    lblErrH.setText(String.format("%5.2f",myEvent.getInnerObjectEvent().getOrigins().get(0).getErrH()) + " km");
                    //lblErrH.setVisible(true);
                } else {
                    lblErrH.setText("??.??");
                    //lblErrH.setVisible(false);
                }

                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getErrZ()!=null){
                    lblErrZ.setText(String.format("%5.2f",myEvent.getInnerObjectEvent().getOrigins().get(0).getErrZ()) + " km");

                } else {
                    if (myEvent.getInnerObjectEvent().getOrigins().get(0).getErrDepth()!=null){
                        lblErrZ.setText(String.format("%5.2f",myEvent.getInnerObjectEvent().getOrigins().get(0).getErrDepth()) + " km");
                    } else
                        lblErrZ.setText("??.??"); 
                }

                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getOt()!=null)
                    this.lblOT.setText(myEvent.getInnerObjectEvent().getOrigins().get(0).getOt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS").withZone(ZoneId.of("UTC"))));
                else
                    this.lblOT.setText("** Unknown **");
            }
        } catch (Exception ex){
            sitDialog.ShowErrorMessage("Error in event summary parameters display.\n" + ex.getMessage(), PrimaryStage);
        }
    }
    //------------------------------------------------------------------------------
    private boolean populate_towns_list(){
        try {
            Towns_List_items tmpItem;
            //Town tmpTown;
            GetMunicipiDistanceKmPopolazione200ResponseDataInner tmpTown;
            table_cities.getColumns().clear();
            final ObservableList dati = FXCollections.observableArrayList();
    //        
            //---------------------------------------------------------
            if (myEvent==null) return true;
            myEvent.RefreshTownsInfo();
            if (myEvent.getTownsInfo()==null) return true;
            //---------------------------------------------------------
            for (int i=0; i < myEvent.getTownsInfo().size(); i++) {
                tmpTown = myEvent.getTownsInfo().get(i);
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


            table_cities.setItems(dati);

            table_cities.getColumns().addAll(nameCol, provCol, deltaCol, populationCol);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }   
    
    
    
//------------------------------------------------------------------------------
    private boolean populate_phases_list(){
        try {       
            int i, j;
            Phases_List_items tmpItem;
            ObjectArrival tmpPh;
            table_phases.getColumns().clear();
            //
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (myEvent==null) return true;
            if (myEvent.getNStations()==0) return true;
            //---------------------------------------------
            // Event sort!!
            //myEvent.Sort();
            //---------------------------------------------
            float res=0, wg=0, azm=0, dist=0;
            int qc=0;
            for (i=0; i<myEvent.getNStations(); i++) {
                if (myEvent.getStation(i).getNPhases()>0) {
                    for (j=0; j<myEvent.getStation(i).getNPhases(); j++) {
                        tmpPh = myEvent.getStation(i).getPhase(j);
                        if (tmpPh.getResidual()!=null) res = tmpPh.getResidual().floatValue();
                        if (tmpPh.getWeight()!=null) wg = tmpPh.getWeight().floatValue();
                        if (tmpPh.getPick().getQualityClass()!=null) qc = tmpPh.getPick().getQualityClass();
                        if (tmpPh.getAzimut()!=null) azm = tmpPh.getAzimut().floatValue();
                        if (tmpPh.getEpDistanceKm()!=null) dist = tmpPh.getEpDistanceKm();       //getAzimut().floatValue();

                        
                        if (tmpPh.getPick().getArrivalTime() != null) {
                            tmpItem= new Phases_List_items(myEvent.getStation(i).getCode(),
                                    tmpPh.getPick().getCha(),tmpPh.getIscCode(),
                                    tmpPh.getPick().getArrivalTime().format(DateTimeFormatter.ISO_TIME), 
                                    res,
                                    wg,
                                    dist,
                                    azm,
                                    qc);
                            dati.add(tmpItem);
                        }
                    }
                }
            }
            //---------------------------------------------
            //
            TableColumn usedCol = new TableColumn("Used");
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
            //stationCol.setOnEditCommit(value);

            table_phases.setOnMouseClicked((MouseEvent e) -> {
                e.consume();
                
                if (e.getButton() == MouseButton.PRIMARY) { 
                    int indice = ((TableView)e.getSource()).getSelectionModel().getFocusedIndex();
                    String Accendere;
                    String Spegnere="";
                    ArrayList<String> Stazioni_da_accendere = new ArrayList();
                    if (last_blinking_station_code.trim().length()>0){
                        Spegnere = last_blinking_station_code;
                        Map_TurnStationsOFF();
                    }
                    Accendere = String.valueOf(table_phases.getColumns().get(1).getCellData(indice));
                    if (!(Accendere.equalsIgnoreCase(last_blinking_station_code))) {
                        last_blinking_station_code = Accendere;
                        Stazioni_da_accendere.add(Accendere);
                        Map_TurnStationsON(Stazioni_da_accendere);
                    }
                                       
                    if (e.getClickCount()==2){
                        // Mostra la terna della stazione selezionata (magari zoomando sulla fase)
//                        int idWaveBox = find_subplot_index(myEvent.StationCode_to_StationId(Accendere));
//                        if (idWaveBox!=-1) preview_subplot_index=idWaveBox;
//                        LoadAndShowTerna(Accendere);
                        
                        show_wave_by_station_click(Accendere);
                    }                   
                }
            });
            
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

            deltaCol.setCellValueFactory(
                new PropertyValueFactory<>("delta")
            );
            deltaCol.setStyle( "-fx-alignment: CENTER;");

            azimutCol.setCellValueFactory(
                new PropertyValueFactory<>("azimut")
            );
            azimutCol.setStyle( "-fx-alignment: CENTER;");

            resCol.setCellFactory(column -> {
            return new TableCell<ObjectArrival, Float>() {
                @Override
                protected void updateItem(Float item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        //setStyle("");
                    } else {
                        // Format the residual
                        setText(item.toString());
                        //
                        if ((Math.abs(Double.valueOf(item)) >= 0.0) && (Math.abs(Double.valueOf(item)) < 0.5)) {
                            setTextFill(javafx.scene.paint.Color.GREEN);
                            setStyle("-fx-text-fill: rgb(1, 148, 1);");
                        } else  if ((Math.abs(Double.valueOf(item)) >= 0.5) && (Math.abs(Double.valueOf(item)) < 1.0)) { 
                                setTextFill(javafx.scene.paint.Color.ORANGE);   
                                setStyle("-fx-text-fill: rgb(255, 165, 0);");
                                
                            } else {
                                setTextFill(javafx.scene.paint.Color.RED);
                                setStyle("-fx-text-fill: rgb(200, 17, 17);");
                            }
                        }
                    }
                };
            });

            stationCol.setCellFactory(column -> {
                return new TableCell<ObjectArrival, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setAlignment(Pos.CENTER);

                        if (item == null || empty) {
                            setText(null);
                            //setStyle("");
                        } else {
                            // Format the residual
                            setText(item.toString());
                            //
//                            if (myEvent.getStations().get(myEvent.StationCode_to_StationId(item)).getNWaves()==0){                           
//                                setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
//                            }    
                        }
                    }
                };
            });

            usedCol.setMinWidth(20);
            usedCol.setStyle( "-fx-alignment: CENTER;");
            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Phases_List_items, CheckBox>, ObservableValue<CheckBox>>() {
                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<Phases_List_items, CheckBox> arg0) {
                    try{
                        Phases_List_items phase_items = arg0.getValue();
                        String Staz = phase_items.getStation_code();
                        String PhaseRemark = phase_items.getPick_name();

                        int idStaz = myEvent.StationCode_to_StationId(Staz);

                        // Trova la fase nella stazione
                        int idPh = ((Station)myEvent.getStations().get(idStaz)).phase_index(PhaseRemark);

                        // Setta la fase a [NON]USATA 
                        CheckBox checkBox = new CheckBox();

                        if (((ObjectArrival)((Station)myEvent.getStations().get(idStaz)).getPhases().get(idPh)).getArrTimeIsUsed()!=null) {
                            if (((ObjectArrival)((Station)myEvent.getStations().get(idStaz)).getPhases().get(idPh)).getArrTimeIsUsed()) {
                                checkBox.selectedProperty().setValue(Boolean.TRUE);}
                            else checkBox.selectedProperty().setValue(Boolean.FALSE);
                        } else checkBox.selectedProperty().setValue(Boolean.TRUE);


                        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                            public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
                                if (new_val) {
                                    phase_items.setUsed(new SimpleStringProperty("TRUE")); 
                                    if (((ObjectArrival)((Station)myEvent.getStations().get(idStaz)).getPhases().get(idPh))!=null)
                                        ((ObjectArrival)((Station)myEvent.getStations().get(idStaz)).getPhases().get(idPh)).setArrTimeIsUsed(true);  
                                } else {
                                    phase_items.setUsed(new SimpleStringProperty("FALSE"));
                                    if (((ObjectArrival)((Station)myEvent.getStations().get(idStaz)).getPhases().get(idPh))!=null)
                                        ((ObjectArrival)((Station)myEvent.getStations().get(idStaz)).getPhases().get(idPh)).setArrTimeIsUsed(false);  
                                }
                            }
                        });

                        return new SimpleObjectProperty<CheckBox>(checkBox);
                    } catch (Exception ex){
                        return null;
                    }
                }

            });

            table_phases.setItems(dati);

            table_phases.getColumns().addAll(usedCol, stationCol, channelCol, phaseCol, 
                timeCol, resCol, weightCol, qcCol, deltaCol, azimutCol);
    //   
             return true;   
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }   
//------------------------------------------------------------------------------
    public void show_wave_by_station_click(String StationCode){
        int idWaveBox = find_subplot_index(myEvent.StationCode_to_StationId(StationCode));
        if (idWaveBox!=-1) preview_subplot_index=idWaveBox;
        LoadAndShowTerna(StationCode);
    }
//------------------------------------------------------------------------------    
    int find_subplot_index(int until_this_index){
        try{
            
            int cnt=-1;
            for (int i=0; i<=until_this_index; i++){
                if (myEvent.getStation(i).getNWaves()>0)
                    cnt++;
            }
            
            return cnt;
        } catch (Exception ex){
            return -1;
        }
    }
    
//------------------------------------------------------------------------------
    private boolean populate_picks_list(){   
        try { 
            int i, j;
            Phases_List_items_terna tmpItem;
            ObjectArrival tmpPh;
            table_picks.getColumns().clear();
            //
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (myEvent==null) return true;
            if (myEvent.getNStations()==0) return true;
            if (myEvent.getActiveTerna()==null) return true;
            if (myEvent.getActiveTerna().getStationIndex()<0) return true;
            //---------------------------------------------
            // Event sort!!
            //myEvent.Sort();
            //---------------------------------------------
            
            tab_StationPicks.setText("Station Picks: "+myEvent.getActiveTerna().getStationCode());
            
            float  w;
            String ui, li;
            int q;
            String fm, ie;
            
            if (myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getNPhases()>0) { 
                if (myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getNPhases()>1){
                    this.lblSminusP.setText("S-P: " + CalculateSminusP());
                } else lblSminusP.setText("S-P: ");
                if (myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getCoda_duration()>0){
                    lblCoda.setText("Coda duration: " + myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getCoda_duration());
                } else lblCoda.setText("Coda duration: not set");
                
                
                for (j=0; j<myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getNPhases(); j++) {
                    ui="--";
                    li="--";
                    w=0.0f;
                    q=0;
                    fm="";
                    ie="";

                    tmpPh = myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getPhase(j);
                    if (tmpPh.getPick().getArrivalTime() != null) {
                        //
                        if (tmpPh.getPick().getUpperUncertainty()!=null) ui=tmpPh.getPick().getUpperUncertainty().toString();
                        if (tmpPh.getPick().getLowerUncertainty()!=null) li=tmpPh.getPick().getLowerUncertainty().toString();
                        if (tmpPh.getWeight()!=null) w= tmpPh.getWeight().floatValue();

                        if (tmpPh.getPick().getQualityClass()!=null) q=(int)tmpPh.getPick().getQualityClass();

                        if (tmpPh.getPick().getFirstmotion()!=null) 
                            fm = tmpPh.getPick().getFirstmotion().getValue();

                        if (tmpPh.getPick().getEmersio()!=null) ie = tmpPh.getPick().getEmersio().getValue();
                        //
                        tmpItem= new Phases_List_items_terna(
                                fm + ie + tmpPh.getIscCode(),
                                tmpPh.getPick().getArrivalTime().format(DateTimeFormatter.ISO_TIME),
                                ui,
                                li,
                                w,
                                q);

                        dati.add(tmpItem);
                    }
                }
            } else {
                this.lblSminusP.setText("S-P: ");
                this.lblCoda.setText("Coda duration: not set");
            }
            
            //---------------------------------------------
            //
            TableColumn phaseCol = new TableColumn("Phase");
            TableColumn timeCol = new TableColumn("Time");
            TableColumn plusCol = new TableColumn("+ (sec.)");
            TableColumn minusCol = new TableColumn("- (sec.)");
            TableColumn qcCol = new TableColumn("QC");
            TableColumn weightHypo2000Col = new TableColumn("Hypo weight");


            phaseCol.setCellValueFactory(
                new PropertyValueFactory<>("pick_name")
            );
            phaseCol.setStyle( "-fx-alignment: CENTER;");

            timeCol.setCellValueFactory(
                new PropertyValueFactory<>("pick_time")
            );
            timeCol.setStyle( "-fx-alignment: CENTER;");

            plusCol.setCellValueFactory(
                new PropertyValueFactory<>("error_plus")
            );
            plusCol.setStyle( "-fx-alignment: CENTER;");

            minusCol.setCellValueFactory(
                new PropertyValueFactory<>("error_minus")
            );
            minusCol.setStyle( "-fx-alignment: CENTER;");

            qcCol.setCellValueFactory(
                new PropertyValueFactory<>("qualityclass_weight")
            );
            qcCol.setStyle( "-fx-alignment: CENTER;");
            
            weightHypo2000Col.setCellValueFactory(
                new PropertyValueFactory<>("hypo_weight")
            );
            weightHypo2000Col.setStyle( "-fx-alignment: CENTER;");

            table_picks.setItems(dati);

            table_picks.getColumns().addAll(phaseCol, 
                timeCol, plusCol, minusCol, qcCol, weightHypo2000Col);
    //   
             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    
    private String CalculateSminusP(){
        try {
            //myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).get
            ObjectArrival P = myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).FindFirstPhase_fase("P");
            ObjectArrival S = myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).FindFirstPhase_fase("S");
            
            if ((P!=null)&&(S!=null)){
                
                //LocalDateTime tempo =((ObjectArrival)myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getPhases().get(phase_id)).getPick().getArrivalTime().toLocalDateTime();
                    double tempoP = (P.getPick().getArrivalTime().toLocalDateTime().toEpochSecond(ZoneOffset.UTC)*1000.0);
                    double tempoS = (S.getPick().getArrivalTime().toLocalDateTime().toEpochSecond(ZoneOffset.UTC)*1000.0);
                    // Pick the coda duration
                    float res = (float)((tempoS-tempoP)/1000.0);
           
              //long num = S.getPick().getArrivalTime().toLocalDateTime().toEpochSecond(ZoneOffset.UTC)-P.getPick().getArrivalTime().toLocalDateTime().toEpochSecond(ZoneOffset.UTC);
              
                return String.valueOf(res);
            } else return "";
            
            
        } catch (Exception ex){
            return "Sorry, no picks";
        }
    }
//------------------------------------------------------------------------------    
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
       return LocalDateTime.ofInstant(dateToConvert.toInstant(), ZoneId.of("UTC"));
    }
//------------------------------------------------------------------------------    
    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.of("UTC")).toInstant());
}
//------------------------------------------------------------------------------
    public void ActivateStation(String CODE){
        try {
            int idSta = myEvent.StationCode_to_StationId(CODE);
            if (idSta!=-1){
                // CODE is already part of the event
                preview_subplot_index = myEvent.getStation(idSta).getWave(0).getPlot_box_id();
                LoadAndShowTerna(preview_subplot_index);
            }
        } catch (Exception ex) {
        }
    }
//------------------------------------------------------------------------------    
    private void LoadAndShowTerna(int subplotIndex) {
        try {           
            if (subplotIndex<0) return;
            LoadingTerna=true;
            
            tglFilterTerna.setSelected(false);
            
            WEIGHT_BOXES.clear();
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Loading terna");
            Waveform tmpW;       
            if (subplotIndex > -1) {  
                if (subplotIndex==0) {
                    btnPrevTerna.setDisable(true);
                } else btnPrevTerna.setDisable(false);
                if (subplotIndex==myEvent.getnOpenWaves()) {
                    btnNextTerna.setDisable(true);
                } else btnNextTerna.setDisable(false);
                
                // Inizializza la terna
                tmpW = myEvent.SubplotIndex_to_Waveform(subplotIndex);     
                
                if (tmpW==null) return;
                
                int idStaz = myEvent.StationCode_to_StationId(tmpW.getStationCode());
                
                myEvent.setActiveStationID(idStaz);
                                
                Station s = myEvent.getStations().get(idStaz);
                               
                if (myEvent.InitTerna(tmpW.getStationCode(), s.getName(),
                    tmpW.getChannelCode().substring(0, 2), 
                    tmpW.getNetworkCode(), tmpW.getLocationCode(), 
                    s.getEarthWorm_WaveServer_Client_INDEX(), s.getFDSN_Service_index())) {
                    //
                    // This shows the waves for the selected station
                    DisplayWaves_TERNA(null); //, null);
                    //tglFilterTerna.setSelected(false);
                }
            } 
            
            // Avviare un thread esterno per popolare il combo con id_origin canali disponibili per la stazione
            // .....
            ObservableList<?> channel_options = FXCollections.observableArrayList(
                    "EH",
                    "HH",
                    "HN"
                );
            combo_TernaChannels.getItems().clear();
            combo_TernaChannels.getItems().addAll(channel_options);  
            combo_TernaChannels.setPromptText("Pick a channel");
       
            populate_picks_list();
            
            //----------------------------------------------------------
            Map_TurnStationsOFF();
            ArrayList<String> blinks = new ArrayList<>();
            blinks.add(myEvent.getActiveTerna().getStationCode());
            Map_TurnStationsON(blinks);
            //----------------------------------------------------------

            App.logger.debug("Loaded terna");
        } catch (Exception ex) {
             App.logger.error(ex.getMessage());
        } finally {
            LoadingTerna=false;
        }
    }
//------------------------------------------------------------------------------    
    public void DisplayWaves_TERNA(Range xRange) { //, Range yRange) {
        if (show_waves_TERNA(xRange)) { //, yRange)) {
            show_phases_TERNA();
        }
    }  
//------------------------------------------------------------------------------        
    public void LoadAndShowTerna(String staCode) {
        try {
            WEIGHT_BOXES.clear();
                  
            Waveform tmpW;
            
            if (!staCode.isEmpty()) {
               int idStation = myEvent.StationCode_to_StationId(staCode);
               myEvent.setActiveStationID(idStation);
                // Inizializza la terna
                tmpW = myEvent.getStation(idStation).getWave(0);
                if (tmpW==null) return;
                
                //int idStaz = myEvent.StationCode_to_StationId(tmpW.getStationCode());
                Station s = myEvent.getStations().get(idStation);
                
                if (myEvent.InitTerna(s.getCode(), myEvent.getStation(idStation).getName(), 
                    tmpW.getChannelCode().substring(0, 2), 
                    tmpW.getNetworkCode(), tmpW.getLocationCode(), s.getEarthWorm_WaveServer_Client_INDEX(), s.getFDSN_Service_index())) {
                    // Mostra le tracce e le fasi della stazione selezionata
                    DisplayWaves_TERNA(null); //, null);
                    //tglFilterTerna.setSelected(false);
                }
            } 
            
            App.logger.debug("Loaded terna");
        } catch (Exception ex) {
             App.logger.error(ex.getMessage());
        }    
    }
//------------------------------------------------------------------------------        
    public void LoadAndShowTerna(String staCode, String chaCode) {
        try {
            if (!staCode.isEmpty() && (!chaCode.isEmpty())) {
               int idStation = myEvent.StationCode_to_StationId(staCode);
               myEvent.setActiveStationID(idStation);
                // Inizializza la terna
                Station s = myEvent.getStations().get(idStation);
                
                if (myEvent.InitTerna(staCode, myEvent.getStation(idStation).getName(),
                    chaCode, s.getNetwork(), s.getLocation(chaCode), s.getEarthWorm_WaveServer_Client_INDEX(),s.getFDSN_Service_index())) {
                    // Mostra le tracce e le fasi della stazione selezionata
                    DisplayWaves_TERNA(null); //, null);
                    //tglFilterTerna.setSelected(false);
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("SIT - Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No waves for " + staCode + " " + chaCode);
                    alert.showAndWait();

                    Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "No waves for " + staCode + " " + chaCode);
                }
            } 
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.INFO, "Loaded terna");
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        }    
    }
//------------------------------------------------------------------------------    
    private boolean show_waves_TERNA(Range xRange) {     
        Waveform tmpWave;
        int idWave=0;
        TimeSeries series;
        TimeSeriesCollection dataset;

        String CurrentComponent; // Z, N, E
        java.awt.Color WaveColor;
     //   
        float time;
        LocalDateTime new_date;
        LocalDateTime dateStart;
        long timestamp;
        long current_time;
        Millisecond ms;
        TimeSeriesDataItem item;
        NumberAxis rangeAxis1;        
        XYPlot subplot;
       // XYItemRenderer renderer;
        LegendTitle lt;       
    //    
        try{      
            if (myEvent == null) return true;                    // or false?
            if (myEvent.getActiveTerna() == null) return true;   // or false?
            if (myEvent.getActiveTerna().getWaves()==null) return false;
            if (myEvent.getActiveTerna().getWaves().isEmpty()) return false;      
    //
            //rangeAxis1 = new NumberAxis();   
    //                  
            setPlot_combinato_TERNA(new CombinedDomainXYPlot(new DateAxis("Time")));
            getPlot_combinato_TERNA().setOrientation(PlotOrientation.VERTICAL);          
            //plot_combinato_TERNA.setRangeAxis(rangeAxis1);
            
            getPlot_combinato_TERNA().setBackgroundPaint(java.awt.Color.darkGray);
            getPlot_combinato_TERNA().getDomainAxis().setTickLabelPaint(java.awt.Color.white);
            getPlot_combinato_TERNA().getDomainAxis().setLabelPaint(java.awt.Color.white);
    //    
            //SortTernaWaves();  
            myEvent.getActiveTerna().SortWaves();
            //         
            ZoneId zoneId = ZoneId.of("UTC");    
            float ymin, ymax;   
            for (idWave=0; idWave < myEvent.getActiveTerna().getWaves().size(); idWave++) { 
                tmpWave = myEvent.getActiveTerna().getWaves().get(idWave);     
                List b = Arrays.asList(ArrayUtils.toObject(tmpWave.getY()));                   
                ymin = (Float)Collections.min(b);
                ymax = (Float)Collections.max(b);
                if (ymin!=ymax){
                    series = new TimeSeries(tmpWave.getStationCode() + " " +tmpWave.getChannelCode());
                    dateStart = tmpWave.getStartTime();            
                    timestamp = dateStart.atZone(zoneId).toInstant().toEpochMilli();
                    
                    for (Integer i =0; i< tmpWave.getnSamples(); i++) {  
                        //------------------------------------------------------------------
                        time = i.floatValue()/tmpWave.getSamplingRate(); //the time value of the sample
                        current_time= timestamp + Float.valueOf(time*1000).intValue();
                        new_date = millsToLocalDateTime(current_time);
                        ms= new Millisecond(Date.from(new_date.atZone(zoneId).toInstant()));
                        item = new TimeSeriesDataItem(ms, tmpWave.getY(i)); 
                        //------------------------------------------------------------------
                        series.add(item);                       
                    }     
                    
        //  
                    dataset = new TimeSeriesCollection();
                    dataset.addSeries(series);
        //  
                    // Determina il colore della traccia in funzione della componente
                    CurrentComponent= tmpWave.getChannelCode().substring(2); 
                    switch (CurrentComponent.toUpperCase()) {
                        case "Z":
                            WaveColor = java.awt.Color.YELLOW;
                            break;
                        case "N":
                            WaveColor = java.awt.Color.CYAN;
                            break;
                        case "1":
                            WaveColor = java.awt.Color.CYAN;
                            break;
                        case "E":
                            WaveColor = java.awt.Color.MAGENTA;
                            break;  
                        case "2":
                            WaveColor = java.awt.Color.MAGENTA;
                            break; 
                        default: 
                            WaveColor = java.awt.Color.DARK_GRAY;
                            break;
                    }
        //            
                    rangeAxis1 = new NumberAxis();
                    rangeAxis1.setAutoRange(false); // 202230206   
                    rangeAxis1.setTickLabelsVisible(false);                
                    rangeAxis1.setRange(ymin, ymax);
    //
                    SamplingXYLineRenderer renderer2 = new SamplingXYLineRenderer(); 
                    renderer2.setSeriesPaint(0, WaveColor); 
        //         
                    subplot = new XYPlot(dataset, null, rangeAxis1, renderer2); 
                    subplot.setRangePannable(true);
        //       
                    //subplot.setBackgroundPaint(new java.awt.Color(242,242,242)); //new java.awt.Color(68,68,68));
                    subplot.setBackgroundPaint(java.awt.Color.DARK_GRAY);
        //         
 // 20240604                   subplot.setDomainCrosshairVisible(false);
                    subplot.setDomainCrosshairLockedOnData(false);
                    subplot.setRangeCrosshairVisible(false);
        //           
                    // Per customizzare: subplot.getLegendItems().get(idStation).set...        
                    lt = new LegendTitle(subplot);
                    lt.setItemFont(new Font("Arial", Font.BOLD, 9));
                    lt.setBackgroundPaint(java.awt.Color.LIGHT_GRAY); //(200, 200, 255, 100));
                    lt.setItemPaint(java.awt.Color.BLACK);
                    lt.setFrame(new BlockBorder(java.awt.Color.BLACK));
                    lt.setPosition(RectangleEdge.BOTTOM);
                    XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, 
                            lt,
                            RectangleAnchor.BOTTOM_RIGHT);
                    ta.setMaxWidth(0.48);
                    subplot.addAnnotation(ta);

                    getPlot_combinato_TERNA().add(subplot);
                
                }                           
            }         
            getPlot_combinato_TERNA().setDomainPannable(true);
    //
            if (xRange!=null) getPlot_combinato_TERNA().getDomainAxis().setRange(xRange);
            //if (yRange!=null) plot_combinato_TERNA.getRangeAxis().setRange(yRange);
            
            startupXRange = getPlot_combinato_TERNA().getDomainAxis().getRange();
            //startupYRange = plot_combinato_TERNA.getRangeAxis().getRange();
    //            
            chart_viewer_TERNA.addChartMouseListener(new ChartMouseListenerFX() {
                @Override
                public void chartMouseClicked(ChartMouseEventFX event) { 
                    event.getTrigger().consume();
                    if (event.getTrigger().getButton()==MouseButton.PRIMARY) {
                        //chartTerna_MouseClicked(event.getTrigger().getX(), event.getTrigger().getY());
                    } else {
                        // Right button click
                        OPERATION_DETAIL_WAVE=null;
                        getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
                    }
                }

                @Override
                public void chartMouseMoved(ChartMouseEventFX event) { 
                    event.getTrigger().consume();                    
                    chartTerna_MouseMoved((int)event.getTrigger().getX(), (int)event.getTrigger().getY());   
                }

            });            
            //--------------------------------------------------------------------            
            setChartZoomable(chart_viewer_TERNA, false);
            OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.CAN_GRAB;
            //--------------------------------------------------------------------
    
            waves_scrollbox_TERNA.setFitToHeight(true);
            waves_scrollbox_TERNA.setFitToWidth(true);

            String panel_title = myEvent.getActiveTerna().getNetworkcode() + " " +
                    myEvent.getActiveTerna().getStationCode() + " " +
                    myEvent.getActiveTerna().getStationLocation().trim() + " " +
                    myEvent.getActiveTerna().getChannelCode() + " ---- (Station " +
                    (myEvent.getStation(myEvent.StationCode_to_StationId(myEvent.getActiveTerna().getStationCode())).getWave(0).getPlot_box_id()+1) +
                    " of " + (myEvent.getnOpenWaves()+1) + ") ---- " + myEvent.getActiveTerna().getStationName() ;
            chart_viewer_TERNA.setChart(new JFreeChart(panel_title, JFreeChart.DEFAULT_TITLE_FONT, getPlot_combinato_TERNA(), false));
            chart_viewer_TERNA.getChart().setBackgroundPaint(java.awt.Color.DARK_GRAY);
            chart_viewer_TERNA.getChart().getTitle().setPaint(java.awt.Color.LIGHT_GRAY);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "ERROR in plot terna: " + ex.getMessage());
            return false;
        }            
    }  
    

//--------------------------------------------------------------------------------
    private void setChartZoomable(ChartViewer chart, boolean value){         
        if (!value) {
            if (chart.getCanvas().getMouseHandler("zoom") !=null) {
                chart.getCanvas().removeMouseHandler(chart.getCanvas().getMouseHandler("zoom")); 
            } 
            //
            getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.CAN_GRAB;
        } else {
            OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.ZOOM;
            if (chart.getCanvas().getMouseHandler("zoom") == null) {
                chart.getCanvas().addMouseHandler(new ZoomHandlerFX("zoom", chart));
            } 
        }
    }    
    
//--------------------------------------------------------------------------------    
    private int idClosestPhase(String StationCode, LocalDateTime time_mouse, double seconds_tolerance) {
        // Return the ID of the phase in the terna that is closest to time_mouse
        // close means in the seconds_tolerance time window
        try {
            LocalDateTime time_phase;
            Duration distance;

            int idStaz = myEvent.getActiveStationID(); //myEvent.StationCode_to_StationId(StationCode);
            if (myEvent.getStations().get(idStaz).getNPhases()<=0) return -1;

            for (int i=0; i<myEvent.getStations().get(idStaz).getNPhases(); i++){
                 time_phase = myEvent.getStations().get(idStaz).getPhase(i).getPick().getArrivalTime().toLocalDateTime();
                 distance = Duration.between(time_mouse, time_phase).abs();
                 if (distance.toMillis() <= (seconds_tolerance*1000)) return i;

            }

            return -1;
        } catch (Exception ex) {
            return -1;
        }
    } 
   
    private int idClosestPhase(Station S, LocalDateTime time_mouse, double seconds_tolerance) {
        // Return the ID of the phase in the terna that is closest to time_mouse
        // close means in the seconds_tolerance time window
        try {
            LocalDateTime time_phase;
            Duration distance;

            //int idStaz = myEvent.getActiveStationID(); //myEvent.StationCode_to_StationId(StationCode);
            if (S.getNPhases()<=0) return -1;

            for (int i=0; i<S.getNPhases(); i++){
                 time_phase = S.getPhase(i).getPick().getArrivalTime().toLocalDateTime();
                 distance = Duration.between(time_mouse, time_phase).abs();
                 if (distance.toMillis() <= (seconds_tolerance*1000)) return i;

            }

            return -1;
        } catch (Exception ex) {
            return -1;
        }
    } 
//------------------------------------------------------------------------------    
    @FXML 
    private void chart_viewer_TERNA_MousePressed(MouseEvent event) {   
        event.consume();
//        if (event.isSecondaryButtonDown()) {
//            setChartZoomable(chart_viewer_TERNA, false);
//            return;
//        }
        
        chartTerna_MousePressed((int)event.getX(), (int)event.getY());
    }
//------------------------------------------------------------------------------
    @FXML
    private void chart_viewer_TERNA_MouseReleased(MouseEvent event) {
        event.consume();
        chartTerna_MouseReleased((int)event.getX(), (int)event.getY());
    }
//------------------------------------------------------------------------------    
    private Date FindPhaseTime(String phRemark) {
        try {        
            Date res=null;
            boolean fnd=false;
            int idPh=0;
            Station s=myEvent.getStation(myEvent.getActiveStationID());
            while ((!fnd) && (idPh < s.getNPhases())) {
                if (phRemark.equals(s.getPhase(idPh).getIscCode())) {
                    res = Date.from(s.getPhase(idPh).getPick().getArrivalTime().toLocalDateTime().toInstant(ZoneOffset.UTC));    
                    
                    fnd=true;
                } else {
                    idPh++;
                }
            }
            return res;
        } catch (Exception ex) {
            return null;
        }
    }
//------------------------------------------------------------------------------    
    private boolean Filter_ALLWAVES(){
        try {         
            if (myEvent.getNStations()>0) {
                DSP dsp_tool = new DSP();
            
                for (int i = 0; i < myEvent.getNStations(); i++){
                    if (myEvent.getStation(i).getNWaves()>0 && myEvent.getStation(i).getWave(0).getPlot_box_id()!=-1){
                        myEvent.getStations().get(i).getWave(0).Backup_Samples();
                        dsp_tool.IIR_BandPass_psambit("BUTTERWORTH",myEvent.getStations().get(i).getWave(0).getY(), 
                            myEvent.getStations().get(i).getWave(0).getSamplingRate(), 
                            0, (int)myEvent.getStations().get(i).getWave(0).getnSamples(), 
                            (float)3.0, (float)7.0, 
                            2);
                        myEvent.getStations().get(i).getWave(0).setFilters(myEvent.getStations().get(i).getWave(0).getFilters()+1);
                    }
                }               
                return true;
            } else {
                return false;
            }
  
            } catch (Exception ex) {
                return false;
        }
        
    }
//--------------------------------------------------------------------------------
    private boolean Filter_TERNA(){
        try {
            int idWave;
            if (!myEvent.getActiveTerna().getWaves().isEmpty()) {    
                DSP dsp_tool = new DSP();
                for (idWave=0; idWave < myEvent.getActiveTerna().getWaves().size(); idWave++) {
                    //
                    myEvent.getActiveTerna().getWaves().get(idWave).Backup_Samples();
                    myEvent.getActiveTerna().getWaves().get(idWave).setFilters(myEvent.getActiveTerna().getWaves().get(idWave).getFilters()+1);
                    //                    
//                    dsp_tool.BandPass(myEvent.getActiveTerna().getWaves().get(idWave).getY(), 
//                            (float)1.0/myEvent.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
//                            0, (int)myEvent.getActiveTerna().getWaves().get(idWave).getnSamples(), 
//                            (float)3.0, (float)7.0, 
//                            2);
                    dsp_tool.IIR_BandPass_psambit("BUTTERWORTH", myEvent.getActiveTerna().getWaves().get(idWave).getY(), 
                            myEvent.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                            0, (int)myEvent.getActiveTerna().getWaves().get(idWave).getnSamples(), 
                            (float)3.0, (float)7.0, 
                            2);
                }
            return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
//--------------------------------------------------------------------------------
    private void chartPreview_MouseClicked(int X, int Y) {
        try {       
            Point p = new Point(X, Y);       

            preview_subplot_index  = chart_viewer_PREVIEW.getRenderingInfo().getPlotInfo().getSubplotIndex(p);    
            LoadAndShowTerna(preview_subplot_index);
        } catch (Exception ex) { 
             Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        }
    }
//--------------------------------------------------------------------------------
    @FXML
    private void ShortcutKeyPressed(KeyEvent event) {             
        if (event.getEventType().getName().contains("PRESS")){
            switch (event.getText().toUpperCase()) {
                case " ":
                    btnNextTerna.fire();
                case "1":
                    // Zoom on P phase only active on TERNA               
                    ZoomOnPhase("P");
                    break;
                case "2":
                    // Zoom on S phase only active on TERNA
                    ZoomOnPhase("S");
                    break;

                case "A":
                    if ((OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.CAN_GRAB)|| 
                           ((OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK))){
                        getPrimaryStage().getScene().setCursor(Cursor.H_RESIZE);
                        OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.WEIGHT_BOX_WAITING_DOWN;
                    }              
                    break;
                case "Z":
                    if (can_pick)
                        PickCoda();
                    break;
                case "E":
                    if (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK) {
                        myEvent.getStation(myEvent.getActiveStationID()).setPhaseQuality(active_phase_id, "E");
                        populate_picks_list();
                    } else {
                        // Undo last filter
                        tglFilterTerna.setSelected(false);
                    } 
                    break;
                case "F":
                    tglFilterTerna.setSelected(true);
                    break;
                case "G":
                    //btnFilterAll_click(null);
                    tglFilterAll.setSelected(true);
                    break;
                case "I":
                    if (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK) {
                        myEvent.getStation(myEvent.getActiveStationID()).setPhaseQuality(active_phase_id, "I");
                        populate_picks_list();
                    } else {
                        ResetViewRange_TERNA();
                    }
                    break;   
                case "K":
                    // Reload all
                    tglFilterAll.setSelected(false);
                    break;
                case "R":
                    // Reload terna
                    LoadAndShowTerna(preview_subplot_index);
                    break;
                case "P":
                    if (can_pick)
                        PickOnMousePosition("P");
                    break;
                case "S":
                    if (can_pick)
                        PickOnMousePosition("S");
                    break;
                case "C":
                    if ((can_pick)&&(OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK)) {
                        
                        DeletePhaseFromPreviewPlot(myEvent.getStation(myEvent.getActiveStationID()).getPhase(active_phase_id).getIscCode());
    
                        if ((App.G.LocationControllers!=null) && (!App.G.LocationControllers.isEmpty())){
                            LocationResult2Controller CONTROLLER = App.G.FindLocationControllerByOriginId(myEvent.getInnerObjectEvent().getOrigins().get(0).getId());                            
                            if (CONTROLLER!=null) {
                                int staId;
                                staId=CONTROLLER.getLocationResultEvent().StationCode_to_StationId(myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getCode());
                                if (staId!=-1){
                                    int phId = CONTROLLER.getLocationResultEvent().getStation(staId).FindFirstPhase(myEvent.getStation(myEvent.getActiveStationID()).getPhase(active_phase_id).getIscCode());
                                    if (phId!=-1)
                                        CONTROLLER.getLocationResultEvent().getStation(staId).deletePhase(phId);
                                }
                                
                            } else {
                                sitDialog.ShowErrorMessage("CONTROLLER is null", PrimaryStage);
                            }
                        }
                        
                        myEvent.getStation(myEvent.getActiveStationID()).deletePhase(active_phase_id);
                        
                        show_phases_TERNA();
                        populate_picks_list();
                    }
                    break;
                case "+":
                    if ((can_pick) && (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK)) {
                        myEvent.getStation(myEvent.getActiveStationID()).setPhasePolarity(active_phase_id, "U");
                        populate_picks_list();
                    }
                    break;
                case "U":
                    if ((can_pick) && (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK)) {
                        myEvent.getStation(myEvent.getActiveStationID()).setPhasePolarity(active_phase_id, "U");
                        populate_picks_list();
                    }
                    break;
                case "-":
                    if ((can_pick) && (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK)) {
                        myEvent.getStation(myEvent.getActiveStationID()).setPhasePolarity(active_phase_id, "D");
                        populate_picks_list();
                    }
                    break;
                case "D":
                    if ((can_pick) && (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK)) {
                        myEvent.getStation(myEvent.getActiveStationID()).setPhasePolarity(active_phase_id, "D");
                        populate_picks_list();
                    }
                    break;
                case "T":
                    ShowTraveltimes();
                    break;
            }       
        }      
    } 
//------------------------------------------------------------------------------    
    private void ShowTraveltimes(){
        try {     
            Generic_client taup_client = new Generic_client();
            taup_client.add_parameter("model", "iasp91");
           // taup_client.add_parameter("phases", "Pg,Pn,Sg,Sn");
            taup_client.add_parameter("evdepth", myEvent.getInnerObjectEvent().getOrigins().get(0).getDepth().toString());
            taup_client.add_parameter("evloc", "[" +myEvent.getInnerObjectEvent().getOrigins().get(0).getLat().toString() + "," +
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getLon().toString() +  "]");
            taup_client.add_parameter("staloc", "[" +App.G.SeismicNet.getStation(myEvent.getActiveTerna().getStationCode()).getLat() + "," +
                    App.G.SeismicNet.getStation(myEvent.getActiveTerna().getStationCode()).getLon() +  "]");
            taup_client.add_parameter("format", "json");
            
            ApiResponse RESP = taup_client.makeGET("https://service.iris.edu/irisws", "/traveltime/1/query");
            if (RESP != null) { 
                Logger.getLogger("org.ingv.pickfx").log(Level.INFO, 
                        RESP.getData().toString());
                    Gson g = new Gson();  
                      
                    tauPObject O = g.fromJson(RESP.getData().toString(), tauPObject.class);
                //
                
                if ((O!=null) &&  (O.getArrivals()!=null) && (!O.getArrivals().isEmpty())) {
                    // Costruisce le fasi teoriche per la stazione
                    ObjectArrival a;
                    ArrayList<ObjectArrival> theoretical_arrivals = new ArrayList<>();
                    for (int i=0; i<O.getArrivals().size(); i++){
                        a = new ObjectArrival();
                        a.setPick(new ObjectPick());
                        a.getPick().setSta(myEvent.getActiveTerna().getStationCode());
                        a.getPick().setNet(myEvent.getActiveTerna().getNetworkcode());
                        a.getPick().setLoc(myEvent.getActiveTerna().getStationLocation());
                        a.setArrTimeIsUsed(false);
                        a.getPick().setCha(myEvent.getActiveTerna().getChannelCode());
                        a.setIscCode(O.getArrivals().get(i).getPhase().toUpperCase()); // oppure getPuristName
                        //a.setEpDistanceDelta(((tauParrival)(O.getArrivals().get(i))).getDistdeg());
                        
                        a.getPick().setArrivalTime(myEvent.getInnerObjectEvent().getOrigins().get(0).getOt().plus((int)(O.getArrivals().get(i).getTime()*1000), ChronoUnit.MILLIS));
                        theoretical_arrivals.add(a);
                    }
                    
                    
                    int k=0;
                    k++;
                    
                    myEvent.getStation(myEvent.getActiveStationID()).setPhases(theoretical_arrivals);
                    this.show_phases_TERNA();
                    // ShowTheoreticalArrivals
                }
            } 
        } catch (Exception ex) {
            sitDialog.ShowErrorMessage("Could not load traveltimes.\n" + ex.getMessage() , PrimaryStage);
        }
        
    }
//--------------------------------------------------------------------------------    
    @FXML
    private void ShortcutKeyReleased(KeyEvent event) {
        if (event.getCode().toString().equalsIgnoreCase("A")) {
            OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.CAN_GRAB;     
            getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
            return;
        }
    }
//--------------------------------------------------------------------------------
    private void ZoomOnPhase(String phasePS){    
//private void ZoomOnPhase(int Pphase_id){

        int phase_id = -1;

        /* Zoom three component waves around the P wave*/   
        int idStation = myEvent.getActiveTerna().getStationIndex();
        
        
//    
        if (myEvent.getStation(idStation).getNPhases()>0){   
            phase_id = myEvent.getStation(idStation).FindFirstPhase(phasePS);
            if (phase_id >-1){
                LocalDateTime tempo =((ObjectArrival)myEvent.getStation(idStation).getPhases().get(phase_id)).getPick().getArrivalTime().toLocalDateTime();
                double from = (tempo.minusSeconds(3).toEpochSecond(ZoneOffset.UTC)*1000.0);
                double to = (tempo.plusSeconds(7).toEpochSecond(ZoneOffset.UTC)*1000.0);
                getPlot_combinato_TERNA().getDomainAxis().setRange( from, to);
            }
                
        }
    }
//--------------------------------------------------------------------------------
    private void ResetViewRange_TERNA(){      
        if (startupXRange!=null)
            ((CombinedDomainXYPlot)(chart_viewer_TERNA.getChart().getPlot())).getDomainAxis().setRange(startupXRange);
        
        // Per ora fa reset del range a ymin e ymax per ogni traccia della terna
        float minY, maxY;
        for (int i=0; i < myEvent.getActiveTerna().getWaves().size(); i++){
            minY = myEvent.getActiveTerna().getWaves().get(i).getY(0);
            maxY = myEvent.getActiveTerna().getWaves().get(i).getY(0);
            for (int j=0; j< myEvent.getActiveTerna().getWaves().get(i).getY().length; j++){
                if (myEvent.getActiveTerna().getWaves().get(i).getY(j) < minY) minY = myEvent.getActiveTerna().getWaves().get(i).getY(j);
                if (myEvent.getActiveTerna().getWaves().get(i).getY(j) > maxY) maxY = myEvent.getActiveTerna().getWaves().get(i).getY(j);
            }
            
            ((XYPlot)((CombinedDomainXYPlot)(chart_viewer_TERNA.getChart().getPlot())).getSubplots().get(i)).getRangeAxis().setRange(minY, maxY);
        }
        
    }
    private void ResetViewRange_PREVIEW(){      
        if (startupXRange!=null)
            ((CombinedDomainXYPlot)(chart_viewer_PREVIEW.getChart().getPlot())).getDomainAxis().setRange(startupXRange);
        
        // Per ora fa reset del range a ymin e ymax per ogni traccia della terna
//        float minY, maxY;
//        
//        
//        for (int idSta=0; idSta < myEvent.getNStations(); idSta++){
//            for (int i=0; i < myEvent.getStation(idSta).getWaves().size(); i++){
//                minY = myEvent.getStation(idSta).getWaves().get(i).getY(0);
//                maxY = myEvent.getStation(idSta).getWaves().get(i).getY(0);
//                for (int j=0; j< myEvent.getStation(idSta).getWaves().get(i).getY().length; j++){
//                    if (myEvent.getStation(idSta).getWaves().get(i).getY(j) < minY) minY = myEvent.getStation(idSta).getWaves().get(i).getY(j);
//                    if (myEvent.getStation(idSta).getWaves().get(i).getY(j) > maxY) maxY = myEvent.getStation(idSta).getWaves().get(i).getY(j);
//                }
//
//                ((XYPlot)((CombinedDomainXYPlot)(chart_viewer_PREVIEW.getChart().getPlot())).getSubplots().get(i)).getRangeAxis().setRange(minY, maxY);
//            }
//        }
        
        
    }
//--------------------------------------------------------------------------------    
    @FXML
    private void btnStart_Clicked(ActionEvent event) {  
        WAsingleTaskService();
    }
    
    private void WAsingleTaskService(){
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new WoodAndersonTask("wa-task");
            }
        };

        ProgressDialog progDiag = new ProgressDialog(service);
        progDiag.setTitle("Wood Anderson conversion");
        progDiag.initOwner(getPrimaryStage());
        progDiag.setHeaderText("Converting waves");
        progDiag.initModality(Modality.WINDOW_MODAL);
        progDiag.initStyle(StageStyle.UNDECORATED);
        //
        service.start();

    }
    
    private void ResetWAs(){
        if (myEvent.getNStations()<=0) return;
        for (int i=0; i< myEvent.getNStations(); i++){
            if (myEvent.getStation(i).getWaves_WA()!=null)
                myEvent.getStation(i).getWaves_WA().clear();
        }
    }
    
    class WoodAndersonTask extends Task<Void> {
        public WoodAndersonTask(String title) {
            updateTitle(title);  
        }

        @Override
        protected Void call() throws Exception {

            if (Math.random() < .3) {
                updateMessage("Starting conversion....");
                Thread.sleep(2500);
            }
            
            myWA = new WoodAndersonHandler();
            
            ResetWAs();
            
            updateMessage("Recovering horizontal components waves...");
            updateProgress(30, 100);
            ArrayList<String> stazioni= StationsInDelta();
            if (!myEvent.GETWAVES_WA(stazioni)){
                updateMessage("Error while recovering horizontal components. Aborting...");
                sitDialog.ShowMessage("Error while recovering horizontal components.\nOperation aborted.", "ERROR", Alert.AlertType.ERROR, null);
                 if (isCancelled()) {
                    return null;
                }
            }   
            
            updateMessage("Converting waves...");
            updateProgress(60, 100);
            if (!ConvertWaves(MAX_N_WA_STATIONS)){
                updateMessage("Error during conversion. Aborting...");
                sitDialog.ShowMessage("Error while converting waves to Wood-Anderson.\nOperation aborted.", "ERROR", Alert.AlertType.ERROR,null);
                return null;
            }  
                       
            // 4. Plots and components enabling
            if (myEvent.getNWavesWA() > 0) {
                updateProgress(90, 100);
                updateMessage("Plotting Wood-Anderson waves...");
                Platform.runLater(() -> {
                    if (!show_waves_WA()){
                        updateMessage("Error while plotting waves. Aborting...");
                        sitDialog.ShowMessage("Error while plotting waves.\nOperation aborted.", "ERROR", Alert.AlertType.ERROR,null);
                    }      
                });
                
                updateProgress(100, 100);
                updateMessage("Plotting current wave...");
                Platform.runLater(() -> {
                    if (!show_current_wave_wa(0)){
                        updateMessage("Could not plot current wave. Aborting...");
                        sitDialog.ShowMessage("Could not plot current wave.", "ERROR", Alert.AlertType.ERROR,null);
                     } else {
                        //txtAreaLog.setVisible(false);
                        Disable_Tools(false);
                    }  
                });
            } else {  
                Platform.runLater(() -> {
                    sitDialog.ShowMessage("Operation terminated.\nNo waves converted.", "WARNING", Alert.AlertType.WARNING,null);
                });
            }

            updateProgress(0, 0);
            done();
            return null;
        }
    } 
//--------------------------------------------------------------------------------
    private void Disable_Tools(boolean disable){
        btnDelAllAmps.setDisable(disable);
        btnDelCurrentAmp.setDisable(disable);
        btnRecalc_mode1.setDisable(disable);
        btnRecalc_mode1_single.setDisable(disable);
        btnRecalc_mode2.setDisable(disable);
        btnRecalc_mode2_single.setDisable(disable);
        radio_afterA.setDisable(disable);
        radio_beforeA.setDisable(disable);
        btnRecalc_single.setDisable(disable);
    }
//--------------------------------------------------------------------------------     
    private boolean show_waves_WA() {
        Waveform tmpWave;
        int idStation;
        TimeSeries series;
        TimeSeriesCollection dataset;
        //ChartPanel viewer;
     //   
        float time;
        LocalDateTime new_date;
        LocalDateTime dateStart;
        long timestamp;
        long current_time;
        Millisecond ms;
        TimeSeriesDataItem item;
        NumberAxis rangeAxis1;
        XYPlot subplot;
        LegendTitle lt;
    //    
        try{
            if (myEvent == null) return false;
            if (!myEvent.Event_has_waves_WA()) return false;  
    //         
            waves_vbox_preview_WA.getChildren().clear();
            waves_vbox_preview_WA.requestFocus();
            waves_vbox_preview_WA.layout();
    //
            rangeAxis1 = new NumberAxis();
    //    
            plot_combinato_WA = new CombinedDomainXYPlot(new DateAxis("Time"));
            plot_combinato_WA.setOrientation(PlotOrientation.VERTICAL);
            plot_combinato_WA.setRangeAxis(rangeAxis1);
            plot_combinato_WA.getDomainAxis().setTickLabelPaint(Color.white);
            plot_combinato_WA.getDomainAxis().setLabelPaint(Color.white);                  
    //         
            ZoneId zoneId = ZoneId.of("UTC");
            int wCounter_WA=-1;
                   
            for (idStation=0; idStation < myEvent.getNStations(); idStation++) { 
                if (myEvent.getStation(idStation).getNWaves_WA()>0) {   
                    for (int idWa=0; idWa < myEvent.getStation(idStation).getNWaves_WA(); idWa++){
                        wCounter_WA+=1;
                        
                        tmpWave = myEvent.getStation(idStation).getWave_WA(idWa);
                        tmpWave.setPlot_box_id_WA(wCounter_WA);
                        
                        series = new TimeSeries(tmpWave.getStationCode() + " " +tmpWave.getChannelCode());
                        dateStart = tmpWave.getStartTime();            
                        timestamp = dateStart.atZone(zoneId).toInstant().toEpochMilli();

                        for (Integer i =0; i< tmpWave.getnSamples(); i++) {  
                            //------------------------------------------------------------------
                            time = i.floatValue()/tmpWave.getSamplingRate(); //the time value of the sample
                            current_time= timestamp + Float.valueOf(time*1000).intValue();
                            new_date = millsToLocalDateTime(current_time);
                            ms= new Millisecond(Date.from(new_date.atZone(zoneId).toInstant()));
                            item = new TimeSeriesDataItem(ms, tmpWave.getY(i)); 
                            //------------------------------------------------------------------
                            series.add(item);
                            item=null;
                        }     
                        item=null;
        //  
                        dataset = new TimeSeriesCollection();
                        dataset.addSeries(series);
//        
                        rangeAxis1 = new NumberAxis();
                        rangeAxis1.setAutoRange(true);      
                        SamplingXYLineRenderer renderer2 = new SamplingXYLineRenderer();
//                             
                        renderer2.setSeriesPaint(0, new java.awt.Color(189,235,175)); 
//         
                        subplot = new XYPlot(dataset, null, rangeAxis1, renderer2);
                        subplot.setBackgroundPaint(Color.DARK_GRAY);  // Colore dello sfondo
                        subplot.getRangeAxis().setTickLabelPaint(Color.white);          

                        // Mostra le linee crosshair per pickare tempo-ampiezza
                        subplot.setDomainCrosshairVisible(false);
                        subplot.setRangeCrosshairVisible(false);     
//           
                        lt = new LegendTitle(subplot);
                        lt.setItemFont(new Font("Dialog", Font.BOLD, 9));
                        lt.setBackgroundPaint(Color.DARK_GRAY);
                        lt.setItemPaint(java.awt.Color.WHITE);

                        lt.setFrame(new BlockBorder(java.awt.Color.WHITE));
                        lt.setPosition(RectangleEdge.BOTTOM);
                        XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, lt,RectangleAnchor.BOTTOM_RIGHT);
//
                        ta.setMaxWidth(0.48);
                        subplot.addAnnotation(ta);
                        
                        // Display amplitude bars
                        org.ingv.dante.model.ObjectStationmagnitude tmpAmp = myEvent.FindAmplitude(tmpWave.getStationCode(), tmpWave.getChannelCode());
                        if (tmpAmp !=null) {
                            make_amplitude_annotations(tmpAmp, subplot);
                        }
                                                        
                        plot_combinato_WA.add(subplot); 
                    }                  
                }           
            }             
//         

            waves_scrollbox_preview_WA.setFitToHeight(false);
            chart_viewer_preview_WA.setChart(new JFreeChart("Wood-Anderson horizontal components", 
                    JFreeChart.DEFAULT_TITLE_FONT, plot_combinato_WA, false));
         
//           ********************************************************************************************
//            ATTENZIONE: BUG DI JAVAFX SULLA DIMENSIONE ECCESSIVA DEL BOX !!!! Crash RAM video
//           ********************************************************************************************
            int volte_wa=4;
            if (wCounter_WA>30) volte_wa=15;
            chart_viewer_preview_WA.setPrefHeight(volte_wa*waves_scrollbox_preview_WA.getHeight());
            
//           ********************************************************************************************
            
            waves_vbox_preview_WA.getChildren().add(chart_viewer_preview_WA); 
            chart_viewer_preview_WA.getChart().setBackgroundPaint(java.awt.Color.DARK_GRAY);
            chart_viewer_preview_WA.getChart().getTitle().setPaint(java.awt.Color.LIGHT_GRAY);  
//        
            WA_subplot_index=0;
            
            show_phase_lines(plot_combinato_WA);
          
            return true;
        } catch (Exception ex) {
            
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }                
    }    
//------------------------------------------------------------------------------    
    private boolean show_current_wave_wa(int plotId){
        
        if (plot_combinato_WA==null) return false;
        
        current_WA_plotbox_index=plotId;
        //-----------------------------------------
        // BISOGNA CAPIRE QUAL è LA CURRENT WAVE 
        // ALL'INIZIO è LA PRIMA
        // POI CAMBIA COL CLICK
        //-----------------------------------------    
        
        TimeSeriesCollection dataset;
     //   

        NumberAxis rangeAxis1;
        
        org.jfree.chart.axis.DateAxis asse_tempo;
        
        XYPlot plot;
        XYItemRenderer renderer;
        LegendTitle lt;
        ZoneId zoneId = ZoneId.of("UTC");
        try {
            dataset =(TimeSeriesCollection)((XYPlot)plot_combinato_WA.getSubplots().get(plotId)).getDataset();                          
    //                
            rangeAxis1 = new NumberAxis();
            rangeAxis1.setAutoRange(true);
            rangeAxis1.setTickLabelPaint(Color.WHITE);
            renderer = new StandardXYItemRenderer();   
            asse_tempo = new DateAxis();                        
            renderer.setSeriesPaint(0, new java.awt.Color(189,235,175)); // Colore del segnale
            plot = new XYPlot(dataset, asse_tempo, rangeAxis1, renderer);
            plot.setBackgroundPaint(Color.DARK_GRAY);   // Colore dello sfondo

            // Mostra le linee crosshair per pickare tempo-ampiezza
            plot.setDomainCrosshairVisible(false);
            plot.setDomainCrosshairLockedOnData(true); // Serve a true altrimenti non individua il campione pickato per l'ampiezza (T1 e T2)
            plot.setRangeCrosshairVisible(false);  
            plot.getDomainAxis().setTickLabelPaint(Color.white);
            plot.getDomainAxis().setLabelPaint(Color.white);
        //                        
            Waveform tmpW = myEvent.SubplotIndex_to_Waveform_WA(plotId);
//
            org.ingv.dante.model.ObjectStationmagnitude tmpAmp = myEvent.FindAmplitude(tmpW.getStationCode(), tmpW.getChannelCode());
            if (tmpAmp !=null) {
                make_amplitude_annotations(tmpAmp, plot);
            }
            int idStaz = myEvent.StationCode_to_StationId(tmpW.getStationCode());
            if (myEvent.getStation(idStaz).getNPhases()>0) {
                ObjectArrival tmpPH; // = myEvent.getStation(myEvent.StationCode_to_StationId(tmpW.getStationCode())).getPhase(0);
                
                ValueMarker phase_marker;
                
                for (int idPh=0; idPh<myEvent.getStation(idStaz).getPhases().size(); idPh++) {
                    tmpPH = myEvent.getStation(idStaz).getPhase(idPh);
                    if (tmpPH.getPick().getArrivalTime() != null) {
                        phase_marker = Make_Marker(tmpPH.getPick().getArrivalTime().toLocalDateTime(), 
                                tmpPH.getIscCode(), 
                                (java.awt.Color)((XYPlot)(plot_combinato_WA.getSubplots().get(0))).getBackgroundPaint(),
                                tmpPH.getArrTimeIsUsed());
    //     
                        if (phase_marker != null) {
                            plot.addDomainMarker(phase_marker);          
                        }
                    } 
                }      
            }
//--------------------------------------------------------------------------------                                    
            chart_viewer_single_WA.setChart(new JFreeChart(tmpW.getNetworkCode() + " " + tmpW.getStationCode() + " " + tmpW.getChannelCode(), JFreeChart.DEFAULT_TITLE_FONT, plot, false));
            chart_viewer_single_WA.getChart().setBackgroundPaint(java.awt.Color.DARK_GRAY);
            chart_viewer_single_WA.getChart().getTitle().setPaint(java.awt.Color.LIGHT_GRAY);                     
            chart_viewer_single_WA.addChartMouseListener(new ChartMouseListenerFX() {
                ValueMarker tmpMarker=null;
            
                @Override
                public void chartMouseClicked(ChartMouseEventFX event) {     
                    event.getTrigger().consume();
                    if (OPERATION_DETAIL_WAVE_WA!=null) {
                       switch (OPERATION_DETAIL_WAVE_WA) {
                        case PICKING_PH:
                            LocalDateTime time_mouse_wa;
                            OPERATION_DETAIL_WAVE_WA=null;
                            XYPlot wa_plot = (XYPlot)event.getChart().getPlot();
                                                
                            double domain =  wa_plot.getDomainAxis().java2DToValue(event.getTrigger().getX(), 
                                chart_viewer_single_WA.getRenderingInfo().getPlotInfo().getDataArea(), 
                                wa_plot.getDomainAxisEdge());   
                            
                            Instant instant = Instant.ofEpochMilli((long) domain);
                            time_mouse_wa = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"));                            
                            Date date_new_pick  = Date.from(time_mouse_wa.atZone(ZoneId.of("UTC")).toInstant());
                          
                            prospective_phase_WA.getPick().setArrivalTime(time_mouse_wa.atOffset(ZoneOffset.UTC)); 
                            
                            if (prospective_phase_WA.getIscCode().contains("b"))
                                T_b=time_mouse_wa; 
                            else if (prospective_phase_WA.getIscCode().contains("a"))
                                    T_a=time_mouse_wa; 

                            //                       
                            //ValueMarker new_phase_marker = Make_Phase_Marker(date_new_pick, prospective_phase_WA.getIscCode());
                            if (prospective_phase_WA.getArrTimeIsUsed()==null) prospective_phase_WA.setArrTimeIsUsed(true);
                            ValueMarker new_phase_marker = Make_Marker(date_new_pick, prospective_phase_WA.getIscCode(),
                                    (java.awt.Color)wa_plot.getBackgroundPaint(), prospective_phase_WA.getArrTimeIsUsed());
                            ValueMarker m;
                            ValueMarker old_phase_marker=null;
                            if (wa_plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND)!=null) {
                                for (Iterator iterator = wa_plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND).iterator(); iterator.hasNext();) {
                                 m = (ValueMarker)iterator.next();
                                 if (m.getLabel().contains(prospective_phase_WA.getIscCode())) {
                                    old_phase_marker=m; 
                                 }
                             }
                            }
    //                                           
                             if (old_phase_marker!=null) {
                                 wa_plot.removeDomainMarker(old_phase_marker);
                             } 
 //                          // Add new marker
                             wa_plot.addDomainMarker(new_phase_marker);  
                             
                             radio_beforeA.setSelected(false);
                             radio_afterA.setSelected(false);
                             
                            break;
                       case PICKING_T1:

                           break;
                       case PICKING_T2:

                           break;                       
                       case PICKING_A:                       

                           break;
                       case ZOOM:     
                        event.getTrigger().consume();
                           break;                    }
                    }                      
                }
                @Override
                public void chartMouseMoved(ChartMouseEventFX event) {
                    try {
                        event.getTrigger().consume();
                        if (OPERATION_DETAIL_WAVE_WA!=null) {
                            switch (OPERATION_DETAIL_WAVE_WA) {
                                case PICKING_PH:

                                    break;
                                case PICKING_T1:

                                    break;
                                case PICKING_T2:

                                    break;                       
                                case PICKING_A:                       

                                    break;
                                default:

                                    break;
        //                   
                            } 

                        }
                    } catch (Exception ex) {
                        //
                    }
                     
                }
            }); 
//            
        radio_beforeA.setSelected(false);
        radio_afterA.setSelected(false);
        
        return true;
        } catch (Exception ex)  {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Errore in show_current_wave_wa:\n" + ex.getMessage());
            return false;
        }
    }    
//--------------------------------------------------------------------------------    
    private boolean ConvertWaves(int max_n_staz){
       try {
           
           int lim=max_n_staz;
           if (myEvent.getNStations()<lim) lim = myEvent.getNStations();
           for (int i=0; i < lim; i++) {
               if (myEvent.getStation(i).getNWaves_WA()>0){
                   for (int k=0; k < myEvent.getStation(i).getNWaves_WA(); k++) {
                       myWA.Make_a_WoodAnderson(myEvent.getStation(i).getWave_WA(k));
                   }
               }
           }
      
           return true;
      } catch (Exception ex) {
           return false;
       }       
    }    
//--------------------------------------------------------------------------------
    private ArrayList<String> StationsInDelta(){
        /*
        Creates an array of station codes filled with stations that rely 
        in a certain delta from hypocenter
        */
        try{
            ArrayList<String> res = new ArrayList<>();
            Double deltamax_dg;
            try {
                deltamax_dg = myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(myEvent.findMLid()).getMag();
            } catch (Exception e) {
                deltamax_dg = 1.0;
            }
            
            if (deltamax_dg < 1.0) deltamax_dg = 1.0;
            
            double delta=0;
            int idStaz;
            for (int i=0; i<App.G.SeismicNet.getStations().size(); i++){
                idStaz = myEvent.StationCode_to_StationId(((Station)(App.G.SeismicNet.getStations().get(i))).getCode());
                //if ((idStaz!=-1) && (myEvent.getStation(idStaz).getNPhases()>0)){
                if (idStaz!=-1){
                    delta = (((Station)(App.G.SeismicNet.getStations().get(i))).distanceFrom(myEvent.getInnerObjectEvent().getOrigins().get(0).getLat(), myEvent.getInnerObjectEvent().getOrigins().get(0).getLon(), 0));
                
                    if ((delta <deltamax_dg*111.0) && ((Station)(App.G.SeismicNet.getStations().get(i))).getChannels()!=null)
                        res.add(((Station)(App.G.SeismicNet.getStations().get(i))).getCode());
                }
                
            }
           
            return res;
        } catch (Exception ex) {
            return null;
        }
    }
//--------------------------------------------------------------------------------
    @FXML
    private void PreviewChartWA_MouseClicked(MouseEvent event) {
        event.consume();
        WA_subplot_index = chart_viewer_preview_WA.getRenderingInfo().getPlotInfo().getSubplotIndex(new Point2D.Double(event.getX(), event.getY()));
        show_current_wave_wa(WA_subplot_index);
    }
//--------------------------------------------------------------------------------
    private void make_amplitude_annotations(org.ingv.dante.model.ObjectStationmagnitude inAmp, XYPlot plot){
        try{
            
            DecimalFormat df = new DecimalFormat("0.00");
            
            // http://www.java2s.com/Code/Java/Chart/JFreeChartMarkerDemo1.htm
            if (inAmp.getAmplitude().getTime1()!=null)
                setMarker(plot, inAmp.getAmplitude().getTime1().toLocalDateTime(), "", true); 
            if (inAmp.getAmplitude().getTime2()!=null)
                setMarker(plot, inAmp.getAmplitude().getTime2().toLocalDateTime(), "", true); 

            ZonedDateTime zdt;
            //ZonedDateTime zdt;
            double millis_A1, millis_A2;
            CircleDrawer cd = new CircleDrawer(java.awt.Color.ORANGE, new BasicStroke(1.0f), null);
            if (inAmp.getAmplitude().getTime1()!=null) {
                zdt = inAmp.getAmplitude().getTime1().toZonedDateTime(); //  .atZone(TimeZone.getTimeZone("UTC").toZoneId());
                millis_A1 = zdt.toInstant().toEpochMilli();

                
                final XYAnnotation a1 = new XYDrawableAnnotation(millis_A1, inAmp.getAmplitude().getAmp1(), 11, 11, cd);
                plot.addAnnotation(a1);

                XYPointerAnnotation pointer_A1 = new XYPointerAnnotation("A1: " + df.format(inAmp.getAmplitude().getAmp1()), millis_A1, inAmp.getAmplitude().getAmp1(), Math.PI); // 3.0 * Math.PI / 4.0);

                pointer_A1.setBaseRadius(35.0);
                pointer_A1.setTipRadius(10.0);
                pointer_A1.setFont(new Font("SansSerif", Font.PLAIN, 9));
                pointer_A1.setPaint(Color.WHITE);
                pointer_A1.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
                plot.addAnnotation(pointer_A1);
            }
            if (inAmp.getAmplitude().getTime2()!=null) {
                zdt = inAmp.getAmplitude().getTime2().toZonedDateTime();
                millis_A2 = zdt.toInstant().toEpochMilli();
                XYPointerAnnotation pointer_A2 = new XYPointerAnnotation("A2: " + df.format(inAmp.getAmplitude().getAmp2()), millis_A2, inAmp.getAmplitude().getAmp2(), Math.PI); // 3.0 * Math.PI / 4.0);

                final XYAnnotation a2 = new XYDrawableAnnotation(millis_A2, inAmp.getAmplitude().getAmp2(), 11, 11, cd);
                plot.addAnnotation(a2);

                pointer_A2.setBaseRadius(35.0);
                pointer_A2.setTipRadius(10.0);
                pointer_A2.setFont(new Font("SansSerif", Font.PLAIN, 9));
                pointer_A2.setPaint(java.awt.Color.WHITE);
                pointer_A2.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
                plot.addAnnotation(pointer_A2);
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
    
    private void make_amplitude_annotations(LocalDateTime time_t1, LocalDateTime time_t2,
            double amp_A1, double amp_A2, 
            XYPlot plot){
        try{
            
            if ((time_t1==null)||(time_t2==null)){
                sitDialog.ShowErrorMessage("Error showing amplitude blue bars:\nT1 or T2 is null", this.getPrimaryStage());
                return;
            }
            DecimalFormat df = new DecimalFormat("0.00");
            
            // http://www.java2s.com/Code/Java/Chart/JFreeChartMarkerDemo1.htm
            
            setMarker(plot, time_t1 , "", true); // String.valueOf(inAmp.getA1()));
            setMarker(plot, time_t2, "", true); //String.valueOf(inAmp.getA2()));

           
            ZonedDateTime zdt;
            double millis_A1, millis_A2;

            zdt = time_t1.atZone(ZoneId.of("UTC"));
            millis_A1 = zdt.toInstant().toEpochMilli();

            CircleDrawer cd = new CircleDrawer(java.awt.Color.ORANGE, new BasicStroke(1.0f), null);
            final XYAnnotation a1 = new XYDrawableAnnotation(millis_A1, amp_A1, 11, 11, cd);
            plot.addAnnotation(a1);

            XYPointerAnnotation pointer_A1 = new XYPointerAnnotation("A1: " + df.format(amp_A1), millis_A1, amp_A1, Math.PI); // 3.0 * Math.PI / 4.0);

            pointer_A1.setBaseRadius(35.0);
            pointer_A1.setTipRadius(10.0);
            pointer_A1.setFont(new Font("SansSerif", Font.PLAIN, 9));
            pointer_A1.setPaint(java.awt.Color.WHITE);
            pointer_A1.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
            plot.addAnnotation(pointer_A1);

            zdt =time_t2.atZone(ZoneId.of("UTC"));
            millis_A2 = zdt.toInstant().toEpochMilli();
            XYPointerAnnotation pointer_A2 = new XYPointerAnnotation("A2: " + df.format(amp_A2), millis_A2, amp_A2, Math.PI); // 3.0 * Math.PI / 4.0);

            final XYAnnotation a2 = new XYDrawableAnnotation(millis_A2, amp_A2, 11, 11, cd);
            plot.addAnnotation(a2);

            pointer_A2.setBaseRadius(35.0);
            pointer_A2.setTipRadius(10.0);
            pointer_A2.setFont(new Font("SansSerif", Font.PLAIN, 9));
            pointer_A2.setPaint(java.awt.Color.WHITE);
            pointer_A2.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
            plot.addAnnotation(pointer_A2);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }   
//------------------------------------------------------------------------------        
    private void setMarker(XYPlot plot, LocalDateTime marker_time, String marker_label, boolean used){
        ValueMarker amp_marker;
//     
        amp_marker = Make_Marker(marker_time, marker_label, (java.awt.Color)plot.getBackgroundPaint(), used);
//
        if (amp_marker != null) {
            plot.addDomainMarker(amp_marker);          
        }
    }
//------------------------------------------------------------------------------    
    private ValueMarker Make_Marker(LocalDateTime date_time, String phLabel, java.awt.Color backColor, boolean used){
        ValueMarker pmarker=null; 
        java.awt.Color colore = java.awt.Color.RED;
        try {
            if (date_time==null) return null;
            
            if (!used) colore = java.awt.Color.YELLOW;
            else {
                if (phLabel.toUpperCase().contains("S")) colore = java.awt.Color.MAGENTA;
                if (phLabel.toUpperCase().contains("CODA")) colore = java.awt.Color.ORANGE;
                if ((phLabel.trim().length()==0)||(phLabel.toUpperCase().contains("T"))) colore = java.awt.Color.ORANGE;
            }
            
            pmarker = new ValueMarker(Date.from((date_time.atZone(ZoneId.of("UTC"))).toInstant()).getTime());
         
            BasicStroke stroke1 = new BasicStroke(1.5f);
            pmarker.setStroke(stroke1);     
            pmarker.setPaint(colore);
            pmarker.setLabel(phLabel);
            pmarker.setLabelPaint(colore);
            pmarker.setLabelOffset(new RectangleInsets( 0, 0, 2, 2 ));
            pmarker.setLabelOffsetType(LengthAdjustmentType.NO_CHANGE);

            pmarker.setLabelBackgroundColor(backColor); 
            
            pmarker.setLabelAnchor(RectangleAnchor.TOP);
            pmarker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
            pmarker.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        } catch (Exception ex) {
            Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return pmarker;
    }
    
    private ValueMarker Make_Marker(Date date_time, String phLabel, java.awt.Color backColor, boolean used){
        ValueMarker pmarker=null; 
        java.awt.Color colore = java.awt.Color.RED;
        try {
            if (date_time==null) return null;
            if (!used) colore = java.awt.Color.YELLOW;
            else {              
                if (phLabel.toUpperCase().contains("S")) colore = java.awt.Color.MAGENTA;
                if (phLabel.toUpperCase().equalsIgnoreCase("Coda")) colore = java.awt.Color.ORANGE;
                if ((phLabel.trim().length()==0)||(phLabel.toUpperCase().contains("T"))) colore = java.awt.Color.ORANGE;
            }
            
            if (phLabel.toUpperCase().contains("B") ||(phLabel.toUpperCase().contains("A"))) colore = java.awt.Color.GREEN;
            
            pmarker = new ValueMarker(date_time.getTime());
            BasicStroke stroke1 = new BasicStroke(1.5f);
            pmarker.setStroke(stroke1);     
            pmarker.setPaint(colore);
            pmarker.setLabel(phLabel);
            pmarker.setLabelPaint(colore);
            
            pmarker.setLabelOffset(new RectangleInsets( 0, 0, 2, 2 ));
            pmarker.setLabelOffsetType(LengthAdjustmentType.NO_CHANGE);
           
            pmarker.setLabelBackgroundColor(backColor); 
            
            pmarker.setLabelAnchor(RectangleAnchor.TOP);
            pmarker.setLabelTextAnchor(TextAnchor.TOP_LEFT);
            pmarker.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        } catch (Exception ex) {
            Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return pmarker;
    }
    
    @FXML
    private void btnDelCurrentAmp_Click(ActionEvent event) {    
        /*
        Cancella l'ampiezza dal Wood-Anderson corrente
        */
        try {
            delete_amplitude_markers((XYPlot)chart_viewer_single_WA.getChart().getPlot());
            
            Waveform tmpW = myEvent.SubplotIndex_to_Waveform_WA(current_WA_plotbox_index);

            ObjectStationmagnitude tmpAmp = myEvent.FindAmplitude(tmpW.getStationCode(), tmpW.getChannelCode());
            if (tmpAmp !=null) {
                myEvent.AmplitudeDelete(tmpAmp); 
            }
            local_A1 = 0;
            local_A2 = 0;
            local_T1 = null;
            local_T2 = null;
            show_waves_WA();
        } catch (Exception ex) {
            //
        }         
    }  
 
    @FXML
    private void btnDelAllAmps_click(ActionEvent event) {
        /*
        Cancella l'ampiezza da tutti i segnali Wood-Anderson
        */
        if (!myEvent.HasAmplitudes()) return;
        
        delete_amplitude_markers(plot_combinato_WA);
        if (chart_viewer_single_WA.getChart().getXYPlot()!=null)
            delete_amplitude_markers(chart_viewer_single_WA.getChart().getXYPlot());
        
        if ((myEvent.getInnerObjectEvent().getOrigins()!=null) && (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()!=null)) {
            for (int idM=0; idM < myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); idM++){
                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idM).getTypeMagnitude().toUpperCase().contains("ML")){
                    myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idM).getStationmagnitudes().clear();
                }
            }
        }
                   
//        ObjectStationmagnitude tmpAmp;
//        String cha;   
//        for (int idSta = 0; idSta < myEvent.getNStations(); idSta++){
//            for (int idW=0; idW < myEvent.getStation(idSta).getNWaves_WA(); idW++){
//                cha = myEvent.getStation(idSta).getWave_WA(idW).getChannelCode();
//                tmpAmp = myEvent.FindAmplitude(myEvent.getStation(idSta).getCode(), cha);
//                if (tmpAmp !=null) myEvent.AmplitudeDelete(tmpAmp); 
//            }
//        }
    }

    @FXML
    private void btnRecalc_mode1_click(ActionEvent event) {
        WoodAndersonHandler wa;
        ObjectStationmagnitude newA;
                
        if (myEvent == null) return ;
        if (!myEvent.Event_has_waves_WA()) return;  
        
        int BOXID;
        delete_amplitude_markers(plot_combinato_WA);
        int n_new_amplitudes=0;      
        for (int idStation=0; idStation < myEvent.getNStations(); idStation++) { 
            if (myEvent.getStation(idStation).getNWaves_WA()>0) {
                for (int k=0; k < myEvent.getStation(idStation).getNWaves_WA(); k++){
                    BOXID =  myEvent.getStation(idStation).getWave_WA(k).getPlot_box_id_WA();
                    //wa = new WoodAndersonHandler(null, null);  
                    wa = new WoodAndersonHandler();  
                    newA =  wa.RecalcAmplitude(myEvent, BOXID, 1);
                    if (newA != null) {
                        n_new_amplitudes++;
                        myEvent.AmplitudeAdd(newA);
                                              
//                        local_A1 = newA.getAmplitude().getAmp1().floatValue();
//                        local_A2 = newA.getAmplitude().getAmp2().floatValue();
//                        local_T1 = newA.getAmplitude().getTime1().toLocalDateTime();
//                        local_T2 = newA.getAmplitude().getTime2().toLocalDateTime();
//
//                        //delete_amplitude_markers(plot_combinato_WA);
//                        make_amplitude_annotations(newA, (XYPlot)((CombinedDomainXYPlot)(chart_viewer_preview_WA.getChart().getPlot())).getSubplots().get(BOXID));

                    }  else Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.WARNING, "outA is null");
                }
            }
        }
        
        if (n_new_amplitudes>0)
            btn_WA_refresh.fire();
        
    }

    @FXML
    private void btnRecalc_mode2_click(ActionEvent event) {
        System.out.print("Not implemented...");
    }
//------------------------------------------------------------------------------  
    @FXML
    private void btnRecalc_mode1_sinigle_click(ActionEvent event) {
        //WoodAndersonHandler wa = new WoodAndersonHandler(null, null);
        WoodAndersonHandler wa = new WoodAndersonHandler();
        
        org.ingv.dante.model.ObjectStationmagnitude newA =  wa.RecalcAmplitude(myEvent, WA_subplot_index, 1);
        if (newA != null) {
            local_A1 = newA.getAmplitude().getAmp1().floatValue();
            local_A2 = newA.getAmplitude().getAmp2().floatValue();
            local_T1 = newA.getAmplitude().getTime1().toLocalDateTime();
            local_T2 = newA.getAmplitude().getTime2().toLocalDateTime();
            
            delete_amplitude_markers((XYPlot)chart_viewer_single_WA.getChart().getPlot());
            make_amplitude_annotations(newA, (XYPlot)chart_viewer_single_WA.getChart().getPlot());
            
            btnSave_single_amplitude.setVisible(true);
            
        }  else Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.WARNING, "outA is null");
    }

    @FXML
    private void btnRecalc_mode2_single_click(ActionEvent event) {
        System.out.print("Not implemented...");
    }

    @FXML
    private void btnRecalc_single_click(ActionEvent event) {
        if (T_a==null || T_b==null) {
            sitDialog.ShowInformationMessage("Please, set 'before' and 'after' indicators first.", PrimaryStage);
        }
        
        // Calcola la differenza assoluta in secondi
        long diffInSeconds = Math.abs(Duration.between(T_a, T_b).getSeconds());

        // Verifica se la differenza è maggiore di 1 secondo
        if (diffInSeconds > 1) {
            sitDialog.ShowInformationMessage("Warning: You selected a time window that is longer than a second!!", PrimaryStage);
        }
        
        
        try {
            ValueMarker m;
            XYPlot wa_plot = (XYPlot)chart_viewer_single_WA.getChart().getPlot();  
            int idStart=-1;
            int idEnd = -1;
            //
            XYDataset dataset = wa_plot.getDataset();
           
            RegularTimePeriod  p;
           // 
            ZoneId zoneId = ZoneId.of("UTC");
            if (wa_plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND)!=null) {
                for (Iterator iterator = wa_plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND).iterator(); iterator.hasNext();) {
                    m = (ValueMarker)iterator.next();

                    if (m.getLabel().contains("b")) {
                       // Find the start index
//                       Date data = Date.from( T_b.atZone(ZoneId.of("UTC")).toInstant());
//                       p=RegularTimePeriod.createInstance(org.jfree.data.time.Millisecond.class, 
//                               data, TimeZone.getTimeZone("UTC"), Locale.getDefault())  ;
//                                          
//                       idStart = ((TimeSeries)((TimeSeriesCollection) dataset).getSeries().get(0)).getIndex(p);
//                       
//                       System.out.println("idStart = " + idStart);

                        idStart = FindIndexInDataset(dataset, T_b);
                      
                    } else {
                        if (m.getLabel().contains("a")) {
//                            // Find the end index
//                            Date data = Date.from(T_a.atZone(ZoneId.of("UTC")).toInstant());
//                            p=RegularTimePeriod.createInstance(org.jfree.data.time.Millisecond.class, data, 
//                                     TimeZone.getTimeZone("UTC"), Locale.getDefault())  ;
//                            idEnd = ((TimeSeries)((TimeSeriesCollection) dataset).getSeries().get(0)).getIndex(p);
                            
                            idEnd = FindIndexInDataset(dataset, T_a);
                            
                        }
                    }
                }
                //
                if ((idStart > -1) && (idEnd > -1)) {
                    if (idStart > idEnd) {  // Switch in case of wrong id order
                        int a = idStart;
                        idStart = idEnd;
                        idEnd = a;
                    }
               }
                //
                Waveform tmpW = myEvent.SubplotIndex_to_Waveform_WA(current_WA_plotbox_index);
                LocalDateTime dateStart;
                long timestamp;
                long current_time;

                float time;
                double min, max;
                min = tmpW.getY(0);
                max = tmpW.getY(0);
                LocalDateTime t_max, t_min;
//                t_max=null;
//                t_min = null;
                
                dateStart = tmpW.getStartTime();   
                timestamp = dateStart.atZone(zoneId).toInstant().toEpochMilli();
                
                time = ((Integer)(idStart+1)).floatValue()/tmpW.getSamplingRate(); 
                current_time= timestamp + Float.valueOf(time*1000).intValue();
                t_min  =  millsToLocalDateTime(current_time);
                t_max = t_min;

                for (Integer i = idStart+1;  i < idEnd; i++){

                    if (tmpW.getY(i) > max) {
                        max = tmpW.getY(i);
                        time = i.floatValue()/tmpW.getSamplingRate(); 
                        current_time= timestamp + Float.valueOf(time*1000).intValue();
                        t_max  = millsToLocalDateTime(current_time);
                    }

                    if (tmpW.getY(i) < min) {
                        min = tmpW.getY(i);

                        time = i.floatValue()/tmpW.getSamplingRate(); 
                        current_time= timestamp + Float.valueOf(time*1000).intValue();
                        t_min  =  millsToLocalDateTime(current_time);
                    }

                }

                if (t_min != t_max) {
                    btnDelCurrentAmp.fire();
                    local_A1 = (float)min;
                    local_A2 = (float)max;
                    local_T1 = t_min;
                    local_T2 = t_max;
                    make_amplitude_annotations(t_min, t_max, min, max, wa_plot);
                    //
                    btnSave_single_amplitude.setVisible(true);
                }
                
            } 
        } catch (Exception ex) {
        }
              
    }   
    
    private int FindIndexInDataset(XYDataset D, LocalDateTime T){
        try{
            
            int itemCount = D.getItemCount(0);
            
            // Converto il tempo T_In in millisecondi dall'Epoch
            long targetTimeMillis = T.toInstant(ZoneOffset.UTC).toEpochMilli();

            // Variabili per tenere traccia del minimo errore
            long minDifference = Long.MAX_VALUE;
            int nearestIndex = -1;

            // Itera sui campioni
            for (int i = 0; i < itemCount; i++) {
                // Recupera il valore in ascissa (tempo) in millisecondi dall'Epoch
                double xValue = D.getXValue(0, i);
                long sampleTimeMillis = (long) xValue;

                // Calcola la differenza assoluta
                long difference = Math.abs(sampleTimeMillis - targetTimeMillis);

                // Aggiorna l'indice più vicino se troviamo un errore minore
                if (difference < minDifference) {
                    minDifference = difference;
                    nearestIndex = i;
                }
            }
            
            return nearestIndex;
        } catch (Exception ex) {
            return -1;
        }
    
    }
//--------------------------------------------------------------------------------    
    private void delete_amplitude_markers(CombinedDomainXYPlot combined_plot){
        if (combined_plot.getSubplots()!=null) {
            for (int k=0; k< combined_plot.getSubplots().size(); k++) {
                delete_amplitude_markers((XYPlot)combined_plot.getSubplots().get(k));
            }
        }
    }
//--------------------------------------------------------------------------------        
    private void delete_amplitude_markers(XYPlot current_plot){
        try {
            ValueMarker m;
      
            Object o[] = current_plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND).toArray();
            for (int i =0; i< o.length; i++){
                m = (ValueMarker)o[i];
                if (( ((m.getLabel().toUpperCase().contains("A")) || (m.getLabel().toUpperCase().contains("B")))&& !m.getLabel().toUpperCase().contains("CODA"))||(m.getLabel().isBlank()))
                     current_plot.removeDomainMarker(m);
            }

            int k=current_plot.getAnnotations().size();
            Object a[] =current_plot.getAnnotations().toArray();
            for (int i =0; i< a.length; i++){      
                if (!(a[i] instanceof XYTitleAnnotation)) {
                    current_plot.removeAnnotation((XYAnnotation)a[i]);
                }
            }
        } catch (Exception ex) {
        }
        
    }
//--------------------------------------------------------------------------------
    @FXML
    private void radio_beforeA_Released(MouseEvent event) {
        OPERATION_DETAIL_WAVE_WA=OPERATION_DETAIL_WAVE.PICKING_PH;       
        prospective_phase_WA = new ObjectArrival();
        prospective_phase_WA.setIscCode("b");
        prospective_phase_WA.setPick(new ObjectPick());
    }
//--------------------------------------------------------------------------------
    @FXML
    private void radio_afterA_Released(MouseEvent event) {
        OPERATION_DETAIL_WAVE_WA=OPERATION_DETAIL_WAVE.PICKING_PH;       
        prospective_phase_WA = new ObjectArrival();
        prospective_phase_WA.setIscCode("a");
        prospective_phase_WA.setPick(new ObjectPick());
    }
//--------------------------------------------------------------------------------
    
    @FXML
    private void btnSave_single_amplitude_click(ActionEvent event) {
        try {
            Waveform tmpW = myEvent.SubplotIndex_to_Waveform_WA(current_WA_plotbox_index);

            org.ingv.dante.model.ObjectStationmagnitude tmpAmp = myEvent.FindAmplitude(tmpW.getStationCode(), tmpW.getChannelCode());
            if (tmpAmp !=null) {
                // Aggiorna l'ampiezza
                tmpAmp.getAmplitude().setAmp1(Double.valueOf(local_A1));
                tmpAmp.getAmplitude().setAmp2(Double.valueOf(local_A2));
                tmpAmp.getAmplitude().setTime1(OffsetDateTime.of(local_T1, ZoneOffset.UTC));
                tmpAmp.getAmplitude().setTime2(OffsetDateTime.of(local_T2, ZoneOffset.UTC));

                myEvent.AmplitudeUpdate(tmpAmp);
            } else {
                // Aggiunge l'ampiezza
                ObjectAmplitudeTypeAmplitude ta = new ObjectAmplitudeTypeAmplitude();
                ta.setName("ML");
                ta.setUnit("mm");
                tmpAmp = new org.ingv.dante.model.ObjectStationmagnitude();
                tmpAmp.setTypeMagnitude("ML");
                tmpAmp.setAmplitude(new ObjectAmplitude());
                tmpAmp.getAmplitude().setTypeAmplitude(ta);
                tmpAmp.getAmplitude().setSta(tmpW.getStationCode());
                tmpAmp.getAmplitude().setCha(tmpW.getChannelCode());
                tmpAmp.getAmplitude().setNet(tmpW.getNetworkCode());
                tmpAmp.getAmplitude().setLoc(tmpW.getLocationCode());
                tmpAmp.setIsUsed(true);
                tmpAmp.getAmplitude().setAmp1(Double.valueOf(local_A1));
                tmpAmp.getAmplitude().setTime1(OffsetDateTime.of(local_T1, ZoneOffset.UTC));
                tmpAmp.getAmplitude().setAmp2(Double.valueOf(local_A2));
                tmpAmp.getAmplitude().setTime2(OffsetDateTime.of(local_T2, ZoneOffset.UTC));
                
                myEvent.AmplitudeAdd(tmpAmp);
            }

            local_A1 = 0;
            local_A2 = 0;
            local_T1 = null;
            local_T2 = null;

            btnSave_single_amplitude.setVisible(false);

            show_waves_WA();
        } catch (Exception ex) {
            //
            sitDialog.ShowErrorMessage(ex.getMessage(), PrimaryStage);
        }         
    }
//--------------------------------------------------------------------------------
    @FXML
    private void combo_TernaChannels_change(ActionEvent event) {
        if (combo_TernaChannels.getValue()!=null) {
            LoadAndShowTerna(myEvent.getStations().get(myEvent.getActiveStationID()).getCode(),combo_TernaChannels.getValue().toString());
        }
    }
//--------------------------------------------------------------------------------    
    @FXML
    private void btnPrevTerna_Click(ActionEvent event) {
        if ((preview_subplot_index-1) >=0) {
//            if (phase_was_picked)
//                show_phase_lines(myEvent.getActiveStationID());
                    
            preview_subplot_index-=1;
            LoadAndShowTerna(preview_subplot_index);
        }
    }
//--------------------------------------------------------------------------------
    @FXML
    private void btnNextTerna_Click(ActionEvent event) {      
        if ((preview_subplot_index+1) < ((CombinedDomainXYPlot)chart_viewer_PREVIEW.getChart().getPlot()).getSubplots().size()) {
//            if (phase_was_picked)
//                show_phase_lines(myEvent.getActiveStationID());
           
            preview_subplot_index+=1;
            LoadAndShowTerna(preview_subplot_index);
        }    
    }
//--------------------------------------------------------------------------------
   
//--------------------------------------------------------------------------------    
    private void ResetRadios(){     
          
        cmbTypeEvent.setPromptText(myEvent.getInnerObjectEvent().getTypeEvent());
    }
//--------------------------------------------------------------------------------
    private void chartTerna_MouseMoved(int X, int Y) { 
        int subplotid;            
        if ((OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.PICKING_PH)||(OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.CAN_GRAB)
                ||(OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK)||(OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.GRABPICK_MOVE))   {         
            Point p = new Point(X, Y);  
            subplotid = chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getSubplotIndex(p);
               p=null;
            if (subplotid > -1){
                double domain = ((XYPlot) getPlot_combinato_TERNA().getSubplots().get(subplotid)).getDomainAxis().java2DToValue(X, 
                            chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getDataArea(), 
                            ((XYPlot) getPlot_combinato_TERNA().getSubplots().get(subplotid)).getDomainAxisEdge());                
                domain = domain/1000.0; // in seconds
                
                double nanos = domain - (int)domain;
                nanos = nanos*1000000000;
            
                time_mouse = LocalDateTime.ofEpochSecond(Double.valueOf(domain).longValue(), (int)nanos, ZoneOffset.UTC);
                
                int idPh = idClosestPhase(myEvent.getStation(myEvent.getActiveStationID()), time_mouse, 1.0);   
//
                if (idPh >-1)  {      
                    //--------------------------------------------------------
                    // SWITCH PHASE MARKERS ON
                    //--------------------------------------------------------
                    active_phase_id = idPh;
                    if (OPERATION_DETAIL_WAVE==OPERATION_DETAIL_WAVE.CAN_GRAB) {
                        OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK;        
                        getPrimaryStage().getScene().setCursor(Cursor.OPEN_HAND);
                    }           
                } else {
                        
                        //--------------------------------------------------------
                        // SWITCH PHASE MARKERS OFF
                        //--------------------------------------------------------
                        if (OPERATION_DETAIL_WAVE == OPERATION_DETAIL_WAVE.GRABPICK_WAITING_CLICK) {
                            getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
                            OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.CAN_GRAB;
                            active_phase_id=-1;
                        }
                    }
                }
            }   
    }
//------------------------------------------------------------------------------    
    @FXML
    private void chart_viewer_TERNA_MouseDragged(MouseEvent event) {
        int subplotid=-1;
        if ( OPERATION_DETAIL_WAVE == OPERATION_DETAIL_WAVE.GRABPICK_MOVE){
            Point p = new Point((int)event.getX(), (int)event.getY());  
            subplotid = chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getSubplotIndex(p);
               p=null;
            if (subplotid > -1){
                double domain = ((XYPlot) getPlot_combinato_TERNA().getSubplots().get(subplotid)).getDomainAxis().java2DToValue((int)event.getX(), 
                            chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getDataArea(), 
                            ((XYPlot) getPlot_combinato_TERNA().getSubplots().get(subplotid)).getDomainAxisEdge());

                if (active_phasemarker_id!=-1) {
                    for (int idPlot=0; idPlot<  getPlot_combinato_TERNA().getSubplots().size(); idPlot++){
                        ((ValueMarker)(((XYPlot) getPlot_combinato_TERNA().getSubplots().get(idPlot)).getDomainMarkers(Layer.FOREGROUND).toArray()[active_phasemarker_id])).setValue(domain);
                    }
                }
            }
        }       
    }    
//------------------------------------------------------------------------------
    private void chartTerna_MousePressed(int X, int Y) {    
        if (OPERATION_DETAIL_WAVE==null) return;
        //
        switch (OPERATION_DETAIL_WAVE) {
            case PICKING_PH:
                if (radioP.isSelected())
                    PickOnMousePosition("P");
                else
                    if (radioS.isSelected())
                        PickOnMousePosition("S");
                    else if (radioCoda.isSelected())
                        PickCoda();
                     
                radioP.setSelected(false);
                radioS.setSelected(false);
                radioCoda.setSelected(false);
                break;
                
            case GRABPICK_WAITING_CLICK:     
                active_phasemarker_id = FindMarkerForPhase(active_phase_id);
                OPERATION_DETAIL_WAVE = OPERATION_DETAIL_WAVE.GRABPICK_MOVE;         
                getPrimaryStage().getScene().setCursor(Cursor.CLOSED_HAND);          
                prospective_phase = new ObjectArrival();      
                prospective_phase.setPick(new ObjectPick());
                prospective_phase.setIscCode(myEvent.getStation(myEvent.getActiveStationID()).getPhase(active_phase_id).getIscCode());
                break;
            case WEIGHT_BOX_WAITING_DOWN:
                OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.WEIGHT_BOX_WAITING_RELEASE;        
                Point pp = new Point(X, Y); 
                int subplotid2 = chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getSubplotIndex(pp);       
                if (subplotid2 > -1) {
                    XYPlot current_subplot = (XYPlot)getPlot_combinato_TERNA().getSubplots().get(0);
                    double domain = current_subplot.getDomainAxis().java2DToValue(X, 
                            chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getDataArea(), 
                            current_subplot.getDomainAxisEdge());
                    domain = domain/1000.0;
                    double nanos = domain - (int)domain;
                    nanos = nanos*1000000000;
            
                    time_mouse = LocalDateTime.ofEpochSecond(Double.valueOf(domain).longValue(), (int)nanos, ZoneOffset.UTC);                  
                    ZonedDateTime zdt;
                    zdt = time_mouse.atZone(ZoneId.of("UTC"));
                    
                    weightbox_millis_xstart_TEMP = zdt.toInstant().toEpochMilli();
                    weightbox_time_start_TEMP = time_mouse;
                }      
                
                pp=null;
                System.gc();
            default:
                break;
        }
    }
//--------------------------------------------------------------------------------
    private void chartTerna_MouseReleased(int X, int Y) {  
        if (OPERATION_DETAIL_WAVE==null) return;
//        
        int idSta = myEvent.getActiveTerna().getStationIndex();
        
        switch (OPERATION_DETAIL_WAVE) {
            
            case GRABPICK_MOVE:                  
                Point p = new Point(X, Y); 
                                 
                int subplotid = chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getSubplotIndex(p);
                
                if (subplotid > -1) {
                    
                    XYPlot current_subplot = (XYPlot)getPlot_combinato_TERNA().getSubplots().get(subplotid);
                    //double domain = current_subplot.getDomainAxis().java2DToValue(p.getX(), plotInfo.getDataArea(), current_subplot.getDomainAxisEdge());
                    
                    double domain = current_subplot.getDomainAxis().java2DToValue(X, 
                            chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getDataArea(), 
                            current_subplot.getDomainAxisEdge());
                    
                    domain = domain/1000.0;
                    double nanos = domain - (int)domain;
                    nanos = nanos*1000000000;
            
                    time_mouse = LocalDateTime.ofEpochSecond(Double.valueOf(domain).longValue(), (int)nanos, ZoneOffset.UTC);

                    prospective_phase.setArrTimeIsUsed(true);  //.setUsedForLocation(true);
                    prospective_phase.getPick().setArrivalTime(time_mouse.atOffset(ZoneOffset.UTC));
                    prospective_phase.getPick().setCha(myEvent.getActiveTerna().getChannelCode());
                    if (prospective_phase.getPick().getCha().trim().length()==2){
                        prospective_phase.getPick().setCha(prospective_phase.getPick().getCha()+"Z");
                    }
                            
                    
                    if (prospective_phase.getIscCode().contains("S"))
                        prospective_phase.getPick().setQualityClass(2);
                    
                    Update_uncertainties_before_picking();
                    myEvent.getStation(idSta).Pick_a_phase(prospective_phase);
                                    
                    populate_picks_list();
                    
                    phase_was_picked=true;
                    active_phasemarker_id=-1;
                                  
                    getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);
//    
                    Date date_new_phase  = Date.from(time_mouse.atZone(ZoneId.of("UTC")).toInstant());
                    ValueMarker new_phase_marker = Make_Marker(date_new_phase, 
                            prospective_phase.getIscCode(),
                            (java.awt.Color)current_subplot.getBackgroundPaint(), true);
                    ValueMarker m;
                    ArrayList<ValueMarker> old_phase_markers_TERNA= new ArrayList<ValueMarker>();
                    //ArrayList<ValueMarker> old_phase_markers_PREVIEW= new ArrayList<ValueMarker>();
                    //
                    // TERNA
                    if (current_subplot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND)!=null) {
                        for (Iterator iterator = current_subplot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND).iterator(); iterator.hasNext();) {
                            m = (ValueMarker)iterator.next();
                            if (m.getLabel().trim().equalsIgnoreCase(prospective_phase.getIscCode().trim())) {                              
                                old_phase_markers_TERNA.add(m);  
                            }
                        }
                        if (old_phase_markers_TERNA.size()>0) 
                            RemoveMarkerFromPlot(getPlot_combinato_TERNA(), old_phase_markers_TERNA);
                    }
                    
                    
                    if ((App.G.LocationControllers!=null) && (!App.G.LocationControllers.isEmpty())){
                        LocationResult2Controller CONTROLLER = App.G.FindLocationControllerByOriginId(myEvent.getInnerObjectEvent().getOrigins().get(0).getId());
                        if (CONTROLLER !=null) {
                        int staId = CONTROLLER.getLocationResultEvent().StationCode_to_StationId(myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getCode());
                            if (staId!=-1) {
                                CONTROLLER.getLocationResultEvent().getStation(staId).Pick_a_phase(prospective_phase);
                            }
                        }
                    }

//                  //------------------------------------------------------------    
                    // Add markers for the TERNA waves
                    //------------------------------------------------------------
                    for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {
                        ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).addDomainMarker(new_phase_marker);

                    }                
                }
                OPERATION_DETAIL_WAVE = OPERATION_DETAIL_WAVE.CAN_GRAB;
                p=null;
                System.gc();
                break;
            case WEIGHT_BOX_WAITING_RELEASE:
                double millis_Xend =0.0;
                               
                Point pp = new Point(X, Y); 
                //
                XYPlot current_subplot=null;
                
                int subplotid2 = chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getSubplotIndex(pp);
                
                if (subplotid2 > -1) {
                    current_subplot = (XYPlot)getPlot_combinato_TERNA().getSubplots().get(0);
                   
                    double domain = current_subplot.getDomainAxis().java2DToValue(X, 
                            chart_viewer_TERNA.getRenderingInfo().getPlotInfo().getDataArea(), 
                            current_subplot.getDomainAxisEdge());
                      
                    domain = domain/1000.0;
                    double nanos = domain - (int)domain;
                    nanos = nanos*1000000000;
            
                    time_mouse = LocalDateTime.ofEpochSecond(Double.valueOf(domain).longValue(), (int)nanos, ZoneOffset.UTC);                  
                    ZonedDateTime zdt;
                    zdt = time_mouse.atZone(ZoneId.of("UTC"));
                    millis_Xend = zdt.toInstant().toEpochMilli();
                }
//               
                PickedObject po = new PickedObject(weightbox_millis_xstart_TEMP, millis_Xend,
                        weightbox_time_start_TEMP, time_mouse);
//
                
                int idPhase =  po.Find_phase_in_time_interval(myEvent.getStations().get(idSta).getPhases());  
//
                switch(idPhase) {
                    case -1:
                        // No phases in []
                        for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {
                            po.DrawTimeInterval((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i));
                        } 
                        break;
                    case -9:
                        // Too many phases in []
                        // does nothing...
                        break;
                       
//                    default:
//                        // Only one phase in []
//                        break;
                    case 0: case 1:case 2: case 3:case 4: case 5:case 6: case 7:case 8: case 9:
                       
                        po.setPick_arrival_time(((ObjectArrival)myEvent.getStations().get(idSta).getPhases().get(idPhase)).getPick().getArrivalTime().toLocalDateTime());
                        
                        // set phase reading uncertainty values
                        ((ObjectArrival)myEvent.getStations().get(idSta).getPhases().get(idPhase)).getPick().setLowerUncertainty((float)po.get_diff_PRE());      
                        ((ObjectArrival)myEvent.getStations().get(idSta).getPhases().get(idPhase)).getPick().setUpperUncertainty((float)po.get_diff_POST());
                        ((ObjectArrival)myEvent.getStations().get(idSta).getPhases().get(idPhase)).getPick().setQualityClass(Calculate_Hypo2000_phase_qualityclass((float)po.get_diff_PRE(), (float)po.get_diff_POST()));
                                               
                        // paint the time interval with uncertainty labels
                        for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {
                            po.DrawTimeIntervalWhithLabels((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i));
                        }                       
                }
                //
                WEIGHT_BOXES.add(po);
                //
                getPrimaryStage().getScene().setCursor(Cursor.DEFAULT);            
                OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.CAN_GRAB;     
                populate_picks_list();               
                pp=null;               
                System.gc();           
                break;         
            case ZOOM:
                setChartZoomable(chart_viewer_TERNA, false);
                break;
            default:
                OPERATION_DETAIL_WAVE=OPERATION_DETAIL_WAVE.CAN_GRAB;     
                break;
        }
    }
    
//------------------------------------------------------------------------------
    private int FindMarkerForPhase(int idPhase){
        try {          
            if (((CombinedDomainXYPlot)chart_viewer_TERNA.getChart().getPlot()).getSubplots().isEmpty()) return -1;
            XYPlot P = (XYPlot)((CombinedDomainXYPlot)chart_viewer_TERNA.getChart().getPlot()).getSubplots().get(0);
            
            if (P.getDomainMarkers(Layer.FOREGROUND).isEmpty()) return -1;
            
            Collection<Marker> markers = P.getDomainMarkers(Layer.FOREGROUND);
            if (markers != null) {
                int index = 0;
                for (Marker marker : markers) {
                    if (marker instanceof ValueMarker) {
                        ValueMarker valueMarker = (ValueMarker) marker;
                        if (myEvent.getStation(myEvent.getActiveStationID()).getPhase(idPhase).getIscCode().equals(valueMarker.getLabel())) {
                            //System.out.println("Muovo la fase all'indice " + index);
                           return index;
                        }
                    }
                    index++;
                }
            }
       
            return -1;
        } catch (Exception ex){
            return -1;
        }
    }
//------------------------------------------------------------------------------
    private int Calculate_Hypo2000_phase_qualityclass(float uncert_pre_sec, float uncert_post_sec){
        try {
            float uncert_med = (Math.abs(uncert_pre_sec) + Math.abs(uncert_post_sec))/(float)2.0;
            
            if ((0.0 <= uncert_med) && (uncert_med < 0.1)) {
                return 0;
            } else if ((0.1 <= uncert_med) && (uncert_med < 0.3)) {
                return 1;
            } else if ((0.3 <= uncert_med) && (uncert_med < 0.6)) {
                return 2;
            } else if ((0.6 <= uncert_med) && (uncert_med < 1.0)) {
                return 3;
            } else return 4;
            
        } catch (Exception ex) {
            return 4;
        }
    }
//--------------------------------------------------------------------------------
    private void RemoveMarkerFromPlot(XYPlot plot, ValueMarker Marker){
        try {
            plot.removeDomainMarker(Marker);    
        } catch (Exception ex){
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Error removing marker from plot");
        }
    }
    private void RemoveMarkerFromPlot(XYPlot plot, ArrayList<ValueMarker> Markers){
        try {
            for (int m=0; m< Markers.size(); m++){
                plot.removeDomainMarker(Markers.get(m));
            }
        } catch (Exception ex){
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Error removing marker from plot");
        }
    }
    private void RemoveMarkerFromPlot(CombinedDomainXYPlot combinedplot, ValueMarker Marker){
        try {
            for (int i=0; i < combinedplot.getSubplots().size(); i++){
                ((XYPlot)combinedplot.getSubplots().get(i)).removeDomainMarker(Marker);
            }
        } catch (Exception ex){
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Error removing marker from combined plot");
        }
    }
    private void RemoveMarkerFromPlot(CombinedDomainXYPlot combinedplot, ArrayList<ValueMarker> Markers){
        try {
            for (int i=0; i < combinedplot.getSubplots().size(); i++){
                for (int j=0; j < Markers.size(); j++) {
                    ((XYPlot)combinedplot.getSubplots().get(i)).removeDomainMarker(Markers.get(j));
                }
            }
        } catch (Exception ex){
           Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, "Error removing marker from combined plot");
        }
    }
//--------------------------------------------------------------------------------    
    @FXML
    private void btnZoomIn_Click(ActionEvent event) {
        XYPlot plot = (CombinedDomainXYPlot) chart_viewer_TERNA.getChart().getPlot();
     
        ValueAxis domainAxis = plot.getDomainAxis(); 
        
        double lower = domainAxis.getLowerBound();
        double upper = domainAxis.getUpperBound();
        double dist = upper - lower;
        double ten_percent_dist = dist*10.0/100.0;
        
        if (lower < upper) {
            Range range = new Range(lower+ten_percent_dist, upper-ten_percent_dist);
           
            domainAxis.setRange(range);
        }
    }
//------------------------------------------------------------------------------    
    @FXML
    private void btnZoomOut_Click(ActionEvent event) {
        XYPlot plot = (CombinedDomainXYPlot) chart_viewer_TERNA.getChart().getPlot();
        ValueAxis domainAxis = plot.getDomainAxis(); 
        
        double lower = domainAxis.getLowerBound();
        double upper = domainAxis.getUpperBound();
        double dist = upper - lower;
        double ten_percent_dist = dist*10.0/100.0;
        
        if (lower < upper) {
            Range range = new Range(lower-ten_percent_dist, upper+ten_percent_dist);
           
            domainAxis.setRange(range);
          }
        
    } 
//------------------------------------------------------------------------------
    @FXML
    private void btnZoomInArea_Click(ActionEvent event) {
        setChartZoomable(chart_viewer_TERNA, true);
    }
//--------------------------------------------------------------------------------
    @FXML
    private void btnZoomReset_Click(ActionEvent event) {
        ResetViewRange_TERNA();  
    }
//--------------------------------------------------------------------------------    
    private void PickOnMousePosition(String phase_remark){
        if (time_mouse==null){
            System.out.println("time_mouse is NULL!!");
            return;
        }
        phase_was_picked=true;
        
        prospective_phase = new ObjectArrival();
        prospective_phase.setPick(new ObjectPick());
        prospective_phase.setIscCode(phase_remark);
        prospective_phase.setArrTimeIsUsed(true); 
        prospective_phase.getPick().setArrivalTime(time_mouse.atOffset(ZoneOffset.UTC));
        prospective_phase.getPick().setCha(myEvent.getActiveTerna().getChannelCode());  
        if (prospective_phase.getPick().getCha().trim().length()==2){
            prospective_phase.getPick().setCha(prospective_phase.getPick().getCha() + "Z");
        }   
        if (phase_remark.toUpperCase().contains("S"))
            prospective_phase.getPick().setQualityClass(2);
        else
            prospective_phase.getPick().setQualityClass(0);
        
//    
        DrawMarkers();

        //------------------------------------------------------------------------
        //2020-06-11
        //------------------------------------------------------------------------
        Platform.runLater(() -> {
            Update_uncertainties_before_picking(); // This updates the weight boxes (if existing)

            // Pick the new phase for the current Station
            myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).Pick_a_phase(prospective_phase);
            populate_picks_list();

            if ((App.G.LocationControllers!=null) && (!App.G.LocationControllers.isEmpty())){
                LocationResult2Controller CONTROLLER = App.G.FindLocationControllerByOriginId(myEvent.getInnerObjectEvent().getOrigins().get(0).getId());                
                if (CONTROLLER!=null) {
                    int staId = CONTROLLER.getLocationResultEvent().StationCode_to_StationId(myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getCode());
                    if (staId!=-1)
                        CONTROLLER.getLocationResultEvent().getStation(staId).Pick_a_phase(prospective_phase);
                } else sitDialog.ShowErrorMessage("CONTROLLER = null", PrimaryStage);
            }
            prospective_phase=null;
            System.gc();
        });
    }
    
    
    private void DrawMarkers(){
        
        Date a = new Date(time_mouse.atZone(ZoneId.of("UTC")).toEpochSecond());        
        if (time_mouse!=null) {
            XYPlot current_subplot = (XYPlot)getPlot_combinato_TERNA().getSubplots().get(0);
        
            Date date_new_phase  = Date.from(time_mouse.atZone(ZoneId.of("UTC")).toInstant());
            
            if (prospective_phase.getArrTimeIsUsed()==null) prospective_phase.setArrTimeIsUsed(true);
            
            final ValueMarker new_phase_marker = Make_Marker(date_new_phase, 
                    prospective_phase.getIscCode(),
                    (java.awt.Color)current_subplot.getBackgroundPaint(), prospective_phase.getArrTimeIsUsed());

            ValueMarker old_phase_marker=SearchPhaseMarker(current_subplot, prospective_phase.getIscCode());
            // Remove old marker
            Platform.runLater(() -> {
                if (old_phase_marker!=null) {
                    for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {
                         ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).removeDomainMarker(old_phase_marker, org.jfree.chart.ui.Layer.FOREGROUND);
                         //((XYPlot)plot_combinato_TERNA.getSubplots().get(i)).removeDomainMarker(old_phase_marker, org.jfree.chart.ui.Layer.BACKGROUND);
                    }
                } 
            });
            // Add new marker
            for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {                 
                ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).addDomainMarker(new_phase_marker);
            }      
            
            //*******************************************
            // BLOCCO LENTO:
            //*******************************************
            // Show the new phase in the main preview plot:
//            Platform.runLater(() -> {
//                final ValueMarker oPm = SearchPhaseMarker((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index), prospective_phase.getIscCode());        
//                if (oPm!=null) {
//                    ((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index)).removeDomainMarker(oPm);
//                }  
//                ((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index)).addDomainMarker(new_phase_marker);
//            });
            //*******************************************

            date_new_phase=null;   
           // new_phase_marker=null;  
        }
         
    }
    
    
    private void PickCoda(){
        
        phase_was_picked=true;
        
        prospective_phase = new ObjectArrival();
        prospective_phase.setPick(new ObjectPick());
        prospective_phase.setIscCode("Coda");
        prospective_phase.setArrTimeIsUsed(false); 
        prospective_phase.getPick().setArrivalTime(time_mouse.atOffset(ZoneOffset.UTC));
        prospective_phase.getPick().setCha(myEvent.getActiveTerna().getChannelCode());  
        if (prospective_phase.getPick().getCha().trim().length()==2){
            prospective_phase.getPick().setCha(prospective_phase.getPick().getCha() + "Z");
        }   
        
        prospective_phase.getPick().setQualityClass(0);
//    
        DrawCoda();
        

        Platform.runLater(() -> {
            if (myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getNPhases()>0){   
                int phase_id = myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).FindFirstPhase("P");
                if (phase_id >-1){
                    LocalDateTime tempo =((ObjectArrival)myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getPhases().get(phase_id)).getPick().getArrivalTime().toLocalDateTime();
                    double tempoP = (tempo.toEpochSecond(ZoneOffset.UTC)*1000.0);
                    double tempoCoda = (time_mouse.toEpochSecond(ZoneOffset.UTC)*1000.0);
                    // Pick the coda duration
                    myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).setCoda_duration((float)((tempoCoda-tempoP)/1000.0));
                    
                    lblCoda.setText("Coda duration: " + myEvent.getStation(myEvent.getActiveTerna().getStationIndex()).getCoda_duration());
                }
            }
            prospective_phase=null;
            System.gc();
        });
    }
//------------------------------------------------------------------------------    
    private void DrawCoda(){
        
        //Date a = new Date(time_mouse.atZone(ZoneId.of("UTC")).toEpochSecond());        
        if (time_mouse!=null) {
            XYPlot current_subplot = (XYPlot)getPlot_combinato_TERNA().getSubplots().get(0);
        
            Date date_coda  = Date.from(time_mouse.atZone(ZoneId.of("UTC")).toInstant());
            
            final ValueMarker new_phase_marker = Make_Marker(date_coda, 
                    prospective_phase.getIscCode(),
                    (java.awt.Color)current_subplot.getBackgroundPaint(), true);

            ValueMarker old_phase_marker = SearchPhaseMarker(current_subplot, prospective_phase.getIscCode());
            
            // Remove old marker
            if (old_phase_marker!=null) {
                for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {
                     ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).removeDomainMarker(old_phase_marker, org.jfree.chart.ui.Layer.FOREGROUND);
                     ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).removeDomainMarker(old_phase_marker, org.jfree.chart.ui.Layer.BACKGROUND);
                }
            } 
            // Add new marker
            for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {                 
                ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).addDomainMarker(new_phase_marker);
            }      
            
            //*******************************************
            // BLOCCO LENTO:
            //*******************************************
            // Show the new phase in the main preview plot: 
//            Platform.runLater(() -> {
//                final ValueMarker oPm = SearchPhaseMarker((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index), prospective_phase.getIscCode()); 
//                if (oPm!=null) {
//                    ((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index)).removeDomainMarker(oPm);
//                }  
//
//                ((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index)).addDomainMarker(new_phase_marker);
//            });
            //*******************************************

            date_coda=null;   
           // new_phase_marker=null;  
        }
         
    }
    
    private void DropCoda(){
        ValueMarker m;
        
        myEvent.getStation(myEvent.getActiveStationID()).setCoda_duration(0);
        
        for (int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++) {                 
            if (!((XYPlot)plot_combinato_TERNA.getSubplots().get(i)).getDomainMarkers(Layer.FOREGROUND).isEmpty()){
                for (int k=0; k< ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).getDomainMarkers(Layer.FOREGROUND).size(); k++){
                    m= (ValueMarker)(((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).getDomainMarkers(Layer.FOREGROUND).toArray()[k]);
                    if (m.getLabel().equalsIgnoreCase("CODA")){
                        ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).removeDomainMarker(m);
                        phase_was_picked=true;
                        break;
                    }
                }
            }
        } 
    }
       
    private ValueMarker SearchPhaseMarker(XYPlot plot, String marker_label){
        try{
            if (plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND)!=null) {
                ValueMarker m, res;
                for (Iterator iterator = plot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND).iterator(); iterator.hasNext();) {
                    m = (ValueMarker)iterator.next();
                    if (m.getLabel().contains(prospective_phase.getIscCode())) {
                        return m;
                    }
                }
            }
            return null;
        } catch (Exception ex){
            return null;
        }
    }
//--------------------------------------------------------------------------------
   private void DeletePhaseFromPreviewPlot(String phase_remark){
        XYPlot current_subplot = (XYPlot)getPlot_combinato_TERNA().getSubplots().get(0);
// 
        ValueMarker m;
        ValueMarker old_phase_marker=null;
               
        if (current_subplot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND)!=null) {
            for (Iterator iterator = current_subplot.getDomainMarkers(org.jfree.chart.ui.Layer.FOREGROUND).iterator(); iterator.hasNext();) {
             
             m = (ValueMarker)iterator.next();
             
             if (m.getLabel().contains(phase_remark)) {
                old_phase_marker=m; 
             }
         }
        }
//
        // Remove the phase from the main preview plot:
        if (old_phase_marker!=null) {
            ((XYPlot)plot_combinato.getSubplots().get(preview_subplot_index)).removeDomainMarker(old_phase_marker);
        }       
    }
//--------------------------------------------------------------------------------
    @FXML
    private void btnClearWeightBoxes_Click(ActionEvent event) {
        if (WEIGHT_BOXES!=null)
            WEIGHT_BOXES.clear();
        for(int i=0; i<getPlot_combinato_TERNA().getSubplots().size(); i++){
            ((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i)).getRenderer().removeAnnotations();
        }
       // 
       if (myEvent.getStation(myEvent.getActiveStationID()).getNPhases()>0){
           for (int k=0; k<myEvent.getStation(myEvent.getActiveStationID()).getNPhases(); k++){
               myEvent.getStation(myEvent.getActiveStationID()).getPhase(k).getPick().setUpperUncertainty(null);
               myEvent.getStation(myEvent.getActiveStationID()).getPhase(k).getPick().setLowerUncertainty(null);
               myEvent.getStation(myEvent.getActiveStationID()).getPhase(k).getPick().setQualityClass(0);
           }
           
           populate_picks_list();
       }      
    }
//--------------------------------------------------------------------------------
    private int Pick_is_in_a_weightbox(LocalDateTime pick_time){
        //-------------------------------------------------------
        // Return the weight box  index containing the pick_time
        //-------------------------------------------------------
        try {
            if (WEIGHT_BOXES==null) return -1;
            if (WEIGHT_BOXES.size()==0) return -1;
            //
            for (int i=0; i< WEIGHT_BOXES.size(); i++) {
                if ((WEIGHT_BOXES.get(i).getTime_start().isBefore(pick_time)) &&
                        (pick_time.isBefore(WEIGHT_BOXES.get(i).getTime_end()))){
                    return i;
                }
            }
            //
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//--------------------------------------------------------------------------------
    private void Update_uncertainties_before_picking(){
        if (prospective_phase==null) return;
        
        int wbId=Pick_is_in_a_weightbox(prospective_phase.getPick().getArrivalTime().toLocalDateTime());
        if (wbId!=-1){
            WEIGHT_BOXES.get(wbId).setPick_arrival_time(prospective_phase.getPick().getArrivalTime().toLocalDateTime());
            
            // set phase reading uncertainty values
            prospective_phase.getPick().setLowerUncertainty((float)WEIGHT_BOXES.get(wbId).get_diff_PRE());      
            prospective_phase.getPick().setUpperUncertainty((float)WEIGHT_BOXES.get(wbId).get_diff_POST());
            prospective_phase.getPick().setQualityClass(Calculate_Hypo2000_phase_qualityclass((float)WEIGHT_BOXES.get(wbId).get_diff_PRE(), (float)WEIGHT_BOXES.get(wbId).get_diff_POST()));
            
            for (int i=0; i < getPlot_combinato_TERNA().getSubplots().size(); i++) {
                WEIGHT_BOXES.get(wbId).Repaint((XYPlot)getPlot_combinato_TERNA().getSubplots().get(i));
            }
        }
    }
//--------------------------------------------------------------------------------
    private ArrayList<Event_type> ReadEventTypes(){
        try {
            ArrayList<Event_type> res = new ArrayList<Event_type>();
            ApiResponse response=null;
            Event_type e;
            // read the list
            try {
                ApiClient myClient = new ApiClient();
                myClient.setDebugging(App.G.options.isDebugMode());
                myClient.setBasePath("http://caravel.int.ingv.it/api/quakedb/_table/v1/type_event/");
                myClient.setConnectTimeout(20000);
                myClient.setReadTimeout(20000);
                //                
                Call myCall = myClient.buildCall("http://caravel.int.ingv.it/api/quakedb/_table/v1/type_event/", "/", "GET", null, null, 
                    null, null, null, null, null,null); 
                //
                if (myCall!=null) {
                    response = myClient.execute(myCall, String.class);                    
                } else return null;
            } catch (ApiException ex) {                
                return null;
            }
            
            if (response != null){  
                JsonParser parser = new JsonParser();
                JsonObject Lista_Tipi = (JsonObject) parser.parse(response.getData().toString());    
                if (Lista_Tipi==null) {
                       return null;
                } else {                   
                    JsonArray dati_tipi=  (JsonArray)Lista_Tipi.get("data");
                    //                  
                    if (dati_tipi.size()>0) {
                        //FileWriter myWriter = new FileWriter("configuration/default_event_types.txt");
                        JsonObject tipo;
                        Integer type_id;
                        String type_description;
                        for (int i=0; i< dati_tipi.size(); i++) {
                            tipo =  (JsonObject)dati_tipi.get(i);
                            type_id = tipo.get("id").getAsInt();
                            type_description = tipo.get("name").getAsString();
                            e = new Event_type(type_id, type_description);
                            //myWriter.write(type_id + "#" + type_description + "\n");
                            res.add(e);
                        }   
                        //myWriter.close();                        
                    } else return null;
                }
            }
//           
            return res;
        } catch (Exception ex) {
            return null;
        }
        
    }
    
    private ArrayList<Event_type> ReadDefaultEventTypes() {        
        ArrayList<Event_type> res = new ArrayList<>();
        String file ="configuration/default_event_types.txt";
        BufferedReader reader;
        FileReader freader;
        try {
            freader=new FileReader(file);
            reader = new BufferedReader(freader);
            String currentLine; // = reader.readLine();
            
//            
            String[] o;
            while ((currentLine = reader.readLine()) != null) {
                o = currentLine.split("#");
                res.add(new Event_type(Integer.valueOf(o[0]), o[1]));
            }
            
            reader.close();
            
            return res;
        } catch (Exception ex) {
            
            return null;
        } finally{
            reader=null;
            freader=null;
            res=null;
        }   
    }
//------------------------------------------------------------------------------   
    @FXML
    private void bntNOTAnEarthquake_Click(ActionEvent event) {
        try {
            if (myEvent.ChangeEventType("not existing", event_types)) {
                if (sitDialog.ShowConfirmationMessage("Event was marked as NOT EXISTING", "Close the waves window?", this.getPrimaryStage())==ButtonType.OK){
                    btnBack.fire();
                }
            }
        } catch (Exception ex) {
            // Log the error
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
        }
    }
//------------------------------------------------------------------------------       
    @FXML
    private void btnApplyEventChange_click(ActionEvent event) {        
        String newVal = cmbTypeEvent.getValue();
        if (newVal!=null){
            if (myEvent.ChangeEventType(newVal, event_types)) {
                if (sitDialog.ShowConfirmationMessage("Event was marked as " + 
                        newVal.toUpperCase(), "Close the waves window?", this.getPrimaryStage())==ButtonType.OK){
                    btnBack.fire();
                }
            }
        }
    }
//------------------------------------------------------------------------------ 
    private int FindHuttonBoore(){
        try {
            if (myEvent.getInnerObjectEvent().getOrigins()==null) return -1;
            if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()==null) return -1;
            if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty()) return -1;
            
            for (int i=0; i < myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); i++){
                if (myEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains("HUTTON"))
                    return i;
            }
         
            return -1;
        } catch (Exception ex){
            return -1;
        }
    }  
//------------------------------------------------------------------------------    
     @FXML
    private void btnPyml_click(ActionEvent event) {
        try {
            
            PymlHandler PYML = new PymlHandler(App.G.options.get_pyml_url());
            
            PYML.PostRequest(myEvent);
            
        } catch (Exception ex) {
            int j=0;
            j++;
        }         
    }  

    /**
     * @return the m_stageShowing
     */
    public boolean isM_stageShowing() {
        return m_stageShowing;
    }

    /**
     * @param in_m_stageShowing the m_stageShowing to set
     */
    public void setM_stageShowing(boolean in_m_stageShowing) {
        m_stageShowing = in_m_stageShowing;
    }
    
    
    @FXML
    private void btnDiscardStation_click(ActionEvent event) {       
        if (sitDialog.ShowConfirmationMessage("Station " + myEvent.getStation(myEvent.getActiveStationID()).getCode() +  " will be removed from event", "This operation is not reversible!!", this.getPrimaryStage())==ButtonType.CANCEL) 
            return;
        // 
        if (myEvent.RemoveStation(myEvent.getActiveStationID())){
            if ((preview_subplot_index>0)||(preview_subplot_index==plot_combinato.getSubplots().size()-1))
                DisplayOnlyWaves(preview_subplot_index-1);
            else
                DisplayOnlyWaves(preview_subplot_index);
            LoadAndShowTerna(preview_subplot_index);
        }
    }

    /**
     * @return the idControllers
     */
    public int getIdControllers() {
        return idControllers;
    }

    /**
     * @param in_idControllers the idControllers to set
     */
    public void setIdControllers(int in_idControllers) {
        idControllers = in_idControllers;
    }
    
    
    @FXML
    private void btnPM_Click(ActionEvent event) {
        try {
            if (myEvent.getStation(myEvent.getActiveStationID()).getPhases()==null) return ;
            if (myEvent.getStation(myEvent.getActiveStationID()).getPhases().isEmpty()) return ;
            
            if (myEvent.getActiveTerna()==null) return;
            if (myEvent.getActiveTerna().getWaves()==null) return;
            if (myEvent.getActiveTerna().getWaves().isEmpty()) return;
            if (myEvent.getActiveTerna().getWaves().size()<3) {
                sitDialog.ShowInformationMessage("Particle motion is implemented for three components stations only", PrimaryStage);
                return;
            }

            if (myEvent.getStation(myEvent.getActiveStationID()).FindFirstPhase_fase("P")==null) return ;
            LocalDateTime PTime=null, STime=null;
            try {
                PTime = myEvent.getStation(myEvent.getActiveStationID()).FindFirstPhase_fase("P").getPick().getArrivalTime().toLocalDateTime();
                if (myEvent.getStation(myEvent.getActiveStationID()).FindFirstPhase_fase("S")!=null)
                    STime = myEvent.getStation(myEvent.getActiveStationID()).FindFirstPhase_fase("S").getPick().getArrivalTime().toLocalDateTime();
            } catch (Exception ex) {
                return ;
            }
             //FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("Appoggio.fxml"));
             FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("pm.fxml"));
            
             Parent root1 = (Parent) fxmlLoader1.load();

             Stage stage = new Stage();

             stage.setScene(new Scene(root1));  
             stage.setTitle("SIT - Particle motion viewer");
             stage.initModality(Modality.APPLICATION_MODAL);
             float duration_sec = (float)1.5;
             
             ((pmController)fxmlLoader1.getController()).setPTime(PTime);
             ((pmController)fxmlLoader1.getController()).setStartTimeP(PTime.minus(500,ChronoUnit.MILLIS));
             ((pmController)fxmlLoader1.getController()).setEndTimeP(PTime.plus(1,ChronoUnit.SECONDS));
             ((pmController)fxmlLoader1.getController()).setDataset_3d_Pwave(PM_prepara_dataset_3d(PTime.minus(500, ChronoUnit.MILLIS), duration_sec));
             if (STime!=null){
                 ((pmController)fxmlLoader1.getController()).setDataset_3d_Swave(PM_prepara_dataset_3d(STime.minus(500, ChronoUnit.MILLIS),duration_sec));
                 ((pmController)fxmlLoader1.getController()).setStartTimeS(STime.minus(500,ChronoUnit.MILLIS));
                    ((pmController)fxmlLoader1.getController()).setEndTimeS(STime.plus(1,ChronoUnit.SECONDS));
             }
             ((pmController)fxmlLoader1.getController()).setStationCode(myEvent.getStations().get(myEvent.getActiveStationID()).getCode());
             ((pmController)fxmlLoader1.getController()).disegna();

             stage.setMaximized(false);

             stage.show();
         } catch (Exception ex) {
             Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE, ex.getMessage());
         }
    }

    
    private XYZDataset<String> PM_prepara_dataset_3d(LocalDateTime StartTime, float duration_sec){        
        XYZSeries<String> pWave = new XYZSeries<>("Pwave");
        XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
        
        float samprate = myEvent.getActiveTerna().getWaves().get(0).getSamplingRate();
        //float duration_sec = (float)1.5;
        
                                    
        int id_Z = calcola_i_al_tempo(myEvent.getActiveTerna().getWaves().get(0), StartTime);
        int id_N = calcola_i_al_tempo(myEvent.getActiveTerna().getWaves().get(1), StartTime);
        int id_E = calcola_i_al_tempo(myEvent.getActiveTerna().getWaves().get(2), StartTime);    

        float sumZ=0, sumE=0, sumN=0;
        
        int nsamp = (int)(samprate*duration_sec);
        float [] sampsN=new float[nsamp];
        float [] sampsE=new float[nsamp];
        float [] sampsZ=new float[nsamp];
       
        for (int sampleID=0; sampleID < nsamp; sampleID++){
////            sampsN[sampleID]=myEvent.getActiveTerna().getWaves().get(1).getY(id_N-(int)(samprate/2)+sampleID);
////            sampsE[sampleID]=myEvent.getActiveTerna().getWaves().get(1).getY(id_E-(int)(samprate/2)+sampleID);
////            sampsZ[sampleID]=myEvent.getActiveTerna().getWaves().get(1).getY(id_Z-(int)(samprate/2)+sampleID);
//            sampsZ[sampleID]=myEvent.getActiveTerna().getWaves().get(0).getY(id_Z-(int)(samprate/2)+sampleID);
//            sampsN[sampleID]=myEvent.getActiveTerna().getWaves().get(1).getY(id_N-(int)(samprate/2)+sampleID);
//            sampsE[sampleID]=myEvent.getActiveTerna().getWaves().get(2).getY(id_E-(int)(samprate/2)+sampleID);
            sampsZ[sampleID]=myEvent.getActiveTerna().getWaves().get(0).getY(id_Z+sampleID);
            sampsN[sampleID]=myEvent.getActiveTerna().getWaves().get(1).getY(id_N+sampleID);
            sampsE[sampleID]=myEvent.getActiveTerna().getWaves().get(2).getY(id_E+sampleID);
            
            sumZ+=sampsZ[sampleID];
            sumN+=sampsN[sampleID];
            sumE+=sampsE[sampleID];
        }
        
         // Pre-processing: mean removal
        float avgZ=sumZ/(samprate*duration_sec);
        float avgN=sumN/(samprate*duration_sec);
        float avgE=sumE/(samprate*duration_sec);

        for (int sID=0; sID < nsamp; sID++){
            sampsN[sID] = sampsN[sID]-avgN;
            sampsZ[sID] = sampsZ[sID]-avgZ;
            sampsE[sID] = sampsE[sID]-avgE;
        }
        
        // Pre-processing: bandpass filter (0.2Hz-3.0Hz)
        DSP dsp_tool = new DSP();
        dsp_tool.BandPass(sampsN, 
                            (float)1.0/samprate, 
                            0, nsamp, 
                            (float)0.2, (float)3.0, 
                            2);
        dsp_tool.BandPass(sampsE, 
                            (float)1.0/samprate, 
                            0, nsamp, 
                            (float)0.2, (float)3.0, 
                            2);
        dsp_tool.BandPass(sampsZ, 
                            (float)1.0/samprate, 
                            0, nsamp, 
                            (float)0.2, (float)3.0, 
                            2);
                
        // Swap samples in the dataset for plots
        for (int sID=0; sID < nsamp; sID++){
            pWave.add(sampsN[sID],
                    sampsZ[sID],
                    sampsE[sID] 
                    );
        }
        
        dataset.add(pWave);
        return dataset;
    }
    
    private int calcola_i_al_tempo(Waveform tmpWave, LocalDateTime pTime){
 
        LocalDateTime dateStart = tmpWave.getStartTime(); 
        LocalDateTime new_date;
        float time;
        ZoneId zoneId = ZoneId.of("UTC");
        long current_time;
        long  timestamp = dateStart.atZone(zoneId).toInstant().toEpochMilli();
        try {
            int id=-1;
            for (Integer i =0; i< tmpWave.getnSamples(); i++) {
                time = i.floatValue()/tmpWave.getSamplingRate(); 
                current_time= timestamp + Float.valueOf(time*1000).intValue();
                new_date = millsToLocalDateTime(current_time);
                if (new_date.isAfter(pTime))
                    return i-1;
            }
            
            return id;
        } catch (Exception ex){
            return -1;
        }
    }

    /**
     * @return the plot_combinato_TERNA
     */
    public CombinedDomainXYPlot getPlot_combinato_TERNA() {
        return plot_combinato_TERNA;
    }

    /**
     * @param plot_combinato_TERNA the plot_combinato_TERNA to set
     */
    public void setPlot_combinato_TERNA(CombinedDomainXYPlot plot_combinato_TERNA) {
        this.plot_combinato_TERNA = plot_combinato_TERNA;
    }
}   
//------------------------------------------------------------------------------
