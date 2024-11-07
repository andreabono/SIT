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

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200ResponseDataInner;
import org.ingv.dante.model.ObjectArrival;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectStationmagnitude;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.datamodel.Station;
import org.ingv.sit.location.Hypo2000Handler;
import org.ingv.sit.location.PymlHandler;
import org.ingv.sit.mapping.MapHandler;
import org.ingv.sit.tablemodels.Localmagnitude_list_items;
import org.ingv.sit.tablemodels.Phases_List_items;
import org.ingv.sit.tablemodels.Towns_List_items;
import org.ingv.sit.utils.duration_buffer_item;
import org.ingv.sit.utils.pfxDialog;
import org.ingv.sit.webservices.Hypo2000_configuration;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class LocationResult2Controller implements Initializable {

    @FXML
    private TabPane mytabPane;
    @FXML
    private Rating rating1;
    @FXML
    private Rating rating2;
    @FXML
    private Label lblQ1;
    @FXML
    private Label lblQ2;
    @FXML
    private AnchorPane map_anchor;
    @FXML
    private TableView<?> table_phases;
    @FXML
    private Label lblPhases;
    @FXML
    private ScatterChart<?, ?> resChart;
    @FXML
    private TabPane tabMags;
    @FXML
    private TextField txtRMS_s;
    @FXML
    private TextField txtERR;
    @FXML
    private TextField txtPOS;
    @FXML
    private TextField txtLET;
    @FXML
    private TextField txtH71;
    @FXML
    private TextField txtZTR;
    @FXML
    private CheckBox chkFixDepth;
    @FXML
    private TextField txtMAG;
    @FXML
    private TextField txtDUR;
    @FXML
    private TextField txtFC1;
    @FXML
    private Button btnCancel1;
    @FXML
    private TextField txtREP;
    @FXML
    private TextField txtJUN;
    @FXML
    private TextField txtMIN;
    @FXML
    private TextField txtNET;
    @FXML
    private TextField txtERF;
    @FXML
    private TextField txtTOP;
    @FXML
    private TextField txtLST;
    @FXML
    private TextField txtDIS;
    @FXML
    private TextField txtDAM;
    @FXML
    private TextField txtWET;
    @FXML
    private TextField txtMODEL_Name;
    @FXML
    private TextField txtMODEL_V0;
    @FXML
    private TextField txtMODEL_V1;
    @FXML
    private TextField txtMODEL_V2;
    @FXML
    private TextField txtMODEL_H0;
    @FXML
    private TextField txtMODEL_H1;
    @FXML
    private TextField txtMODEL_H2;
    @FXML
    private TextField txtKmLimit;
    
    public Stage PrimaryStage;  
    public long localOriginID;
    public Event LocationResultEvent;
    private Hypo2000_configuration Hypo2000_CONFIG;
    private MapHandler MH;
    private Event result;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnLocate;
    @FXML
    private Button btnReMag;
    @FXML
    private Button btnOnlyMd;
    @FXML
    private Label lblPhases1;
    @FXML
    private Label lblAmpsAndMags;
    @FXML
    private TableView<Localmagnitude_list_items> table_amplitudes;
    private PopOver popOver;
    @FXML
    private Label lblOT;
    @FXML
    private Label lblLAT;
    @FXML
    private Label lblLON;
    @FXML
    private Label lblERRH;
    @FXML
    private Label lblDEP;
    @FXML
    private Label lblERRDEP;
    @FXML
    private Label lblGAP;
    @FXML
    private Label lblRMS;
    @FXML
    private TableView<?> table_cities;
   

    @FXML
    private void table_phases_MouseClicked(MouseEvent event) {
        Phases_List_items row;
        String StationCode;
        row = (Phases_List_items)this.table_phases.getSelectionModel().getSelectedItem();
        if (row == null) return;
        StationCode = row.getStation_code();

        // 20220107
        //App.G.WavesController.LoadTerna(StationCode);
    }

    @FXML
    private void btnCancel_Click(ActionEvent event) {
        try {
            //this.setResult((Boolean) false);
            result=null;

            PrimaryStage.close();
            
            release_map();
            //App.G.LocationControllers.remove(this);
            System.gc();
        } catch (Exception ex) {
        }
    }
    
    /**
     * @param in_PrimaryStage the PrimaryStage to set
     */
    public void setPrimaryStage(Stage in_PrimaryStage) {
        PrimaryStage = in_PrimaryStage;       
    } 
    
    //------------------------------------------------------------------------------        
    public void FinalizeStartup(){
        mytabPane.selectionModelProperty().getValue().selectedIndexProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable,
                Number oldValue, Number newValue) {        
            if ((oldValue.intValue()==(int)1) && (newValue.intValue()==(int)0)){
                ApplySettings();
            }
            }
        });
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rating1.setMouseTransparent(true);
        rating2.setMouseTransparent(true);
        this.Hypo2000_CONFIG = new Hypo2000_configuration("configuration/Hypo2000_configuration.txt","configuration/Hypo2000_MODEL_configuration.txt");
        
        ShowDefaultHypo2000Configuration();
    }       
//------------------------------------------------------------------------------  
    
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
            if (LocationResultEvent==null) return true;
            LocationResultEvent.RefreshTownsInfo();
            if (LocationResultEvent.getTownsInfo()==null) return true;
            //---------------------------------------------------------
            for (int i=0; i < LocationResultEvent.getTownsInfo().size(); i++) {
                tmpTown = LocationResultEvent.getTownsInfo().get(i);
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
    public void ShowData() {
        
        if (!"??".equals(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getQuality())){    
            ShowQualityChart(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getQuality()); 
        }
       
        lblOT.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getOt().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"))));
        
        lblLAT.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getLat().toString());
        lblLON.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getLon().toString());
        lblDEP.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getDepth().toString());
        if (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getErrZ() != null)
            lblERRDEP.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getErrZ().toString() + " km");
        if (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getErrH() != null)
            lblERRH.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getErrH().toString() + " km");
        if (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getRms() != null)
            lblRMS.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getRms().toString());
        if (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getAzimGap() != null)
            lblGAP.setText(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getAzimGap().toString());
        //
        if (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes() != null) {
            if (tabMags.getScene()!=null)
                tabMags.getScene().setUserData(this);
      
            populate_magnitude_tabs();
            populate_amplitudes_grid();
        }
        //
        showMap();
        //
        populate_phases_list();
        //
        make_residuals_chart();  
        
        populate_towns_list();
    }
    
    //------------------------------------------------------------------------------
    private void ApplySettings() {
        if (this.Settings_Input_Validation()){
          
            this.Hypo2000_CONFIG.setFixDepth(this.chkFixDepth.isSelected());
                        
            this.Hypo2000_CONFIG.setRMS(lblRMS.getText());
            this.Hypo2000_CONFIG.setERR(this.txtERR.getText());
            this.Hypo2000_CONFIG.setPOS(this.txtPOS.getText());
            this.Hypo2000_CONFIG.setLET(this.txtLET.getText());
            this.Hypo2000_CONFIG.setH71(this.txtH71.getText());
            this.Hypo2000_CONFIG.setMAG(this.txtMAG.getText());
            this.Hypo2000_CONFIG.setDUR(this.txtDUR.getText());
            this.Hypo2000_CONFIG.setFC1(this.txtFC1.getText());
            this.Hypo2000_CONFIG.setREP(this.txtREP.getText());
            this.Hypo2000_CONFIG.setJUN(this.txtJUN.getText());
            this.Hypo2000_CONFIG.setMIN(this.txtMIN.getText());
            this.Hypo2000_CONFIG.setNET(this.txtNET.getText());
            
            this.Hypo2000_CONFIG.setZTR(this.txtZTR.getText());
            
            this.Hypo2000_CONFIG.setDIS(this.txtDIS.getText());
            this.Hypo2000_CONFIG.setDAM(this.txtDAM.getText());
            this.Hypo2000_CONFIG.setWET(this.txtWET.getText());
            this.Hypo2000_CONFIG.setERF(this.txtERF.getText());
            this.Hypo2000_CONFIG.setTOP(this.txtTOP.getText());
            this.Hypo2000_CONFIG.setLST(this.txtLST.getText());
            this.Hypo2000_CONFIG.setMODEL_Name(this.txtMODEL_Name.getText());
            this.Hypo2000_CONFIG.setMODEL_H0(Float.valueOf(this.txtMODEL_H0.getText()));
            this.Hypo2000_CONFIG.setMODEL_H1(Float.valueOf(this.txtMODEL_H1.getText()));
            this.Hypo2000_CONFIG.setMODEL_H2(Float.valueOf(this.txtMODEL_H2.getText()));
            this.Hypo2000_CONFIG.setMODEL_V0(Float.valueOf(this.txtMODEL_V0.getText()));
            this.Hypo2000_CONFIG.setMODEL_V1(Float.valueOf(this.txtMODEL_V1.getText()));
            this.Hypo2000_CONFIG.setMODEL_V2(Float.valueOf(this.txtMODEL_V2.getText()));
            
            if (App.G.IsNumeric(txtKmLimit.getText())){
                LocationResultEvent.ApplyRangeLimitation(Float.valueOf(txtKmLimit.getText()));
            }    else {
                LocationResultEvent.ApplyRangeLimitation(Float.valueOf("999999"));
            }
            
            mytabPane.getSelectionModel().selectFirst();
            populate_phases_list();
        } else {
            pfxDialog.ShowErrorMessage("Please, check settings parameters and format.", PrimaryStage);    
        }
            
            
        
    }
//------------------------------------------------------------------------------
    private boolean Settings_Input_Validation(){
        try {
            //------------------------------------------------------------------
            // La validazione dell'input al momento non è fatta perchè non so 
            // quali sono i parametri effettivamente necessari che andremo a
            // configurare... Quindi restituisce sempre TRUE.
            //------------------------------------------------------------------
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    private void ShowQualityChart(String quality_indexes){
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
            }
        }  
    }
//------------------------------------------------------------------------------ 
    private void showMap() {
        try {
           // final SwingNode swingNode = new SwingNode();
            if (MH!=null) MH.close();
            
            MH = new MapHandler(480,226);

            if ((LocationResultEvent!=null) && (LocationResultEvent.getInnerObjectEvent().getOrigins()!=null))
                MH.CreateMapWithCanvas(LocationResultEvent.ToStationArray(), 
                    LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getLat(), LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getLon(), 
                    0,0, 0, 0,
                    true, true);
            else
                MH.CreateMapWithCanvas(null, 0, 0, 
                        App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
                        App.G.options.get_box_minLat(),App.G.options.get_box_maxLat(), 
                        true, true);
              
            this.map_anchor.getChildren().clear();
             
            this.map_anchor.getChildren().add(MH.getCanvas());
            
            if (MH.getLayerIndex("Hypocenter")!=-1){
                MH.SetMapBounds(MH.getLayerIndex("Hypocenter"));
                MH.RefreshMap();
            }

            AnchorPane.setBottomAnchor(MH.getCanvas(), 0.0);
            AnchorPane.setTopAnchor(MH.getCanvas(), 0.0);
            AnchorPane.setLeftAnchor(MH.getCanvas(), 0.0);
            AnchorPane.setRightAnchor(MH.getCanvas(), 0.0);
            
            
        } catch (Exception ex){
            
        }
    }  
//------------------------------------------------------------------------------
    private void ShowDefaultHypo2000Configuration(){
        if (this.Hypo2000_CONFIG!=null){
            
            this.chkFixDepth.setSelected(this.Hypo2000_CONFIG.isFixDepth());
            
            
            this.txtRMS_s.setText(Hypo2000_CONFIG.getRMS());
            this.txtERR.setText(Hypo2000_CONFIG.getERR());
            this.txtLET.setText(Hypo2000_CONFIG.getLET());
            this.txtH71.setText(Hypo2000_CONFIG.getH71());
            this.txtMAG.setText(Hypo2000_CONFIG.getMAG());
            this.txtDUR.setText(Hypo2000_CONFIG.getDUR());
            this.txtFC1.setText(Hypo2000_CONFIG.getFC1());
            this.txtREP.setText(Hypo2000_CONFIG.getREP());
            this.txtJUN.setText(Hypo2000_CONFIG.getJUN());
            this.txtMIN.setText(Hypo2000_CONFIG.getMIN());
            this.txtNET.setText(Hypo2000_CONFIG.getNET());
            
            this.txtZTR.setText(Hypo2000_CONFIG.getZTR().replace("T", "").replace("F", ""));
            
            this.txtDIS.setText(Hypo2000_CONFIG.getDIS());
            this.txtDAM.setText(Hypo2000_CONFIG.getDAM());
            this.txtWET.setText(Hypo2000_CONFIG.getWET());
            this.txtERF.setText(Hypo2000_CONFIG.getERF());
            this.txtTOP.setText(Hypo2000_CONFIG.getTOP());
            this.txtLST.setText(Hypo2000_CONFIG.getLST());
            this.txtPOS.setText(Hypo2000_CONFIG.getPOS());
            
            this.txtMODEL_Name.setText(Hypo2000_CONFIG.getMODEL_Name());
            this.txtMODEL_H0.setText(Hypo2000_CONFIG.getMODEL_H0().toString());
            this.txtMODEL_V0.setText(Hypo2000_CONFIG.getMODEL_V0().toString());
            this.txtMODEL_H1.setText(Hypo2000_CONFIG.getMODEL_H1().toString());
            this.txtMODEL_V1.setText(Hypo2000_CONFIG.getMODEL_V1().toString());
            this.txtMODEL_H2.setText(Hypo2000_CONFIG.getMODEL_H2().toString());
            this.txtMODEL_V2.setText(Hypo2000_CONFIG.getMODEL_V2().toString());
        }
    }
    
    //------------------------------------------------------------------------------    
    private void release_map(){
        try {
            if (this.MH.getMap()!=null) {
                Logger.getLogger("org.ingv.pfx").log(java.util.logging.Level.INFO, "Releasing map and graphic resources...");
                //MH.getMap().dispose();
                MH.close();
            }
            MH=null;
        } catch (Exception ex) {
        }
    }
//------------------------------------------------------------------------------      
    private void populate_magnitude_tabs(){
        tabMags.getTabs().clear();
        if (LocationResultEvent==null) return;
        if ((LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()==null) || (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty())) {
            AddMAGTab(null);
        } else {
            // Show each magnitude in a tab
            String preferred_magnitude_type= App.G.options.getPreferredMagnitudeType();
            for (int i =0; i < LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); i++){
                AddMAGTab(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i)); 
                if (LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i).getTypeMagnitude().toUpperCase().contains(preferred_magnitude_type)){
                    tabMags.getSelectionModel().select(tabMags.getTabs().get(tabMags.getTabs().size()-1)); 
                    setTabAsPreferred(tabMags.getTabs().get(tabMags.getTabs().size()-1), LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(i));
                }
            }
        }    
    } 
    
    
    private void populate_amplitudes_grid(){
        if ((LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes()!=null) && !(LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().isEmpty())) {
            int mlId = LocationResultEvent.findMLid();
            if (mlId==-1) mlId=0;
            ObjectMagnitude MAG = LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(mlId);
            // Legge le ampiezze SOLO sulla prima magnitudo
            ObjectStationmagnitude sta_mag;
            Localmagnitude_list_items tmpItem;
            try {
                if (table_amplitudes.getColumns()!=null)
                    table_amplitudes.getColumns().clear();
                final ObservableList dati = FXCollections.observableArrayList();
                String  amp1, amp2, t01, t02, log, delta;
                Boolean usedmag;
                for (int i=0; i < MAG.getStationmagnitudes().size(); i++) {
                    amp1="0";
                    amp2="0";
                    t01="";
                    t02="";
                    log=null;

                    sta_mag = MAG.getStationmagnitudes().get(i);

                    if (sta_mag.getAmplitude().getSta().contains("*")) {
                        log = sta_mag.getAmplitude().getSta().substring(sta_mag.getAmplitude().getSta().indexOf("*")+1);
                        if (log.equalsIgnoreCase("null")) log=null;
                        sta_mag.getAmplitude().setSta(sta_mag.getAmplitude().getSta().substring(0, sta_mag.getAmplitude().getSta().indexOf("*")));

                    }
                    
                    if (sta_mag.getAmplitude().getAmp1()!=null) amp1 = sta_mag.getAmplitude().getAmp1().toString();
                    if (sta_mag.getAmplitude().getTime1()!=null) t01 = sta_mag.getAmplitude().getTime1().toString();
                    if (sta_mag.getAmplitude().getAmp2()!=null) amp2 = sta_mag.getAmplitude().getAmp2().toString();
                    if (sta_mag.getAmplitude().getTime2()!=null) t02 = sta_mag.getAmplitude().getTime2().toString();

                    if (sta_mag.getEpDistanceKm() != null) 
                        delta = sta_mag.getEpDistanceKm().toString();
                    else 
                        delta = "-9.9";

                    if (sta_mag.getIsUsed()==null) 
                        usedmag = true;
                    else 
                        usedmag = sta_mag.getIsUsed();

                    tmpItem= new Localmagnitude_list_items(usedmag.toString(),  sta_mag.getAmplitude().getSta(), 
                            sta_mag.getAmplitude().getCha(), 
                            Float.valueOf(delta),
                            null,
                            Float.valueOf(amp1),
                            t01, 
                            Float.valueOf(amp2),
                            t02, log);
                    dati.add(tmpItem);
                }
        //             

                TableColumn<Localmagnitude_list_items, Void> logCol = new TableColumn("Log");
                logCol.setStyle( "-fx-alignment: CENTER;");
                Callback<TableColumn<Localmagnitude_list_items, Void>, TableCell<Localmagnitude_list_items, Void>> custom_cellFactory = new Callback<TableColumn<Localmagnitude_list_items, Void>, TableCell<Localmagnitude_list_items, Void>>() {
                    @Override
                    public TableCell<Localmagnitude_list_items, Void> call(final TableColumn<Localmagnitude_list_items, Void> param) {
                        final TableCell<Localmagnitude_list_items, Void> cell = new TableCell<Localmagnitude_list_items, Void>() {

                            private final Button btn = new Button();
                            private final Button btn2 = new Button();

                            {
                                try {
                                    String separator = System.getProperty("file.separator");

                                    Image img = new Image("images"+separator+"warning3.png");
                                    Image img2 = new Image("images"+separator+"err.png");

                                    ImageView view = new ImageView(img);
                                    ImageView view2 = new ImageView(img2);
                                    view.setFitHeight(16);
                                    view.setPreserveRatio(true);
                                    view2.setFitHeight(16);
                                    view2.setPreserveRatio(true);

                                    btn.setGraphic(view);
                                    btn2.setGraphic(view2);

                                } catch (Exception ex){
                                    //
                                }
                                btn.setBorder(Border.EMPTY);
                                btn.setStyle("-fx-background-color: transparent;");
                                btn2.setBorder(Border.EMPTY);
                                btn2.setStyle("-fx-background-color: transparent;");

                                btn.setOnAction((ActionEvent event) -> {

                                    //Localmagnitude_list_items data = (Localmagnitude_list_items)(getTableView().getItems().get(getIndex()));
    //                                String data = (getTableView().getItems().get(getIndex())).getStation() + " " + (getTableView().getItems().get(getIndex())).getChannel();
    //                                System.out.println("selectedData: " + data);

                                    if (popOver != null && !popOver.isDetached()) {
                                        popOver.hide();
                                    }

                                    Label L = new Label(getTableView().getItems().get(getIndex()).getLog().getValue());
                                    L.setTextFill(Color.BLACK);
                                    L.setVisible(true);

                                    popOver = createPopOver(L);

                                    popOver.show((Button)event.getSource());


                                });
                                btn2.setOnAction(btn.getOnAction());

                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    if (getTableView().getItems().get(getIndex()).getLog().getValue()!=null){
                                        if (getTableView().getItems().get(getIndex()).getLog().getValue().toUpperCase().startsWith("CRITICAL")){
                                            // CRITICAL
                                            setGraphic(btn2);
                                        } else
                                            // WARNING
                                            setGraphic(btn);
                                    } else setGraphic(null);

                                }
                            }
                        };

                        return cell;
                    }
                };

                logCol.setCellFactory(custom_cellFactory);



                TableColumn usedCol = new TableColumn("Used");
                TableColumn staCol = new TableColumn("Sta");
                TableColumn chaCol = new TableColumn("Chan");

                TableColumn deltaCol= new TableColumn("Delta");
                deltaCol.setCellValueFactory(
                    new PropertyValueFactory<>("delta")
                );
                deltaCol.setStyle( "-fx-alignment: CENTER;");

                TableColumn a1Col;
                if (MAG.getTypeMagnitude().toUpperCase().contains("MD"))
                    a1Col= new TableColumn("Coda Duration");
                else
                    a1Col= new TableColumn("Amp 1");

                TableColumn t1Col;
                if (MAG.getTypeMagnitude().toUpperCase().contains("MD"))
                    t1Col= new TableColumn("P phase time");
                else
                    t1Col = new TableColumn("Time 1");

                TableColumn a2Col = new TableColumn("Amp 2");
                TableColumn t2Col = new TableColumn("Time 2");
        //       
                staCol.setCellValueFactory(
                    new PropertyValueFactory<>("station")
                );
                //nameCol.setStyle( "-fx-alignment: LEFT;");

                chaCol.setCellValueFactory(
                    new PropertyValueFactory<>("channel")
                );
                chaCol.setStyle( "-fx-alignment: CENTER;");

                a1Col.setCellValueFactory(
                    new PropertyValueFactory<>("a1")
                );
                a1Col.setStyle( "-fx-alignment: CENTER;");

                t1Col.setCellValueFactory(
                    new PropertyValueFactory<>("t1")
                );
                t1Col.setStyle( "-fx-alignment: CENTER;");

                a2Col.setCellValueFactory(
                    new PropertyValueFactory<>("a2")
                );
                a2Col.setStyle( "-fx-alignment: CENTER;");

                t2Col.setCellValueFactory(
                    new PropertyValueFactory<>("t2")
                );
                t2Col.setStyle( "-fx-alignment: CENTER;");

                usedCol.setMinWidth(20);
                usedCol.setStyle( "-fx-alignment: CENTER;");
                usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Localmagnitude_list_items, CheckBox>, ObservableValue<CheckBox>>() {
                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<Localmagnitude_list_items, CheckBox> arg0) {

                    Localmagnitude_list_items mag_item = arg0.getValue();
                    String Staz = mag_item.getStation();
                    String Chan = mag_item.getChannel();         
    //
                    CheckBox checkBox = new CheckBox();
                    if (mag_item.getUsed().getValue().equalsIgnoreCase("TRUE"))
                        checkBox.selectedProperty().setValue(Boolean.TRUE);
                    else
                        checkBox.selectedProperty().setValue(Boolean.FALSE);

                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {

                            if (!new_val) {
                                // Station must be excluded from ML calc
                                exclude_include_from_ml(true, Staz, Chan);
                            } else {
                                // Station can be included from ML calc
                                exclude_include_from_ml(false, Staz, Chan);
                            }

                        }
                    });

                    return new SimpleObjectProperty<CheckBox>(checkBox);

                }

            });

            table_amplitudes.setItems(dati);
            
            if (MAG.getTypeMagnitude().toUpperCase().contains("MD")) 
                table_amplitudes.getColumns().addAll(logCol, usedCol, staCol, chaCol,   deltaCol, a1Col, t1Col);
            else 
                table_amplitudes.getColumns().addAll(logCol,usedCol, staCol, chaCol,  deltaCol,a1Col, t1Col, a2Col, t2Col);
            
            } catch (Exception ex) {
               

            }
        }
    }
    
    private void exclude_include_from_ml(boolean exclude, String sta, String cha){
        ObjectMagnitude MAG;
        for (int idMag=0; idMag<LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().size(); idMag++){
            MAG = LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().get(idMag);
            if (MAG.getStationmagnitudes()==null) return;
            if (MAG.getStationmagnitudes().isEmpty()) return;
       
            for (int idAmplitude=0; idAmplitude<MAG.getStationmagnitudes().size(); idAmplitude++){
                 if (MAG.getStationmagnitudes().get(idAmplitude).getAmplitude().getSta().equalsIgnoreCase(sta) && 
                         MAG.getStationmagnitudes().get(idAmplitude).getAmplitude().getCha().equalsIgnoreCase(cha)){
                     if (exclude)
                         MAG.getStationmagnitudes().get(idAmplitude).setIsUsed(Boolean.FALSE);
                     else
                         MAG.getStationmagnitudes().get(idAmplitude).setIsUsed(Boolean.TRUE);
                }
            }
        }
       

    }
    //------------------------------------------------------------------------------ 
    private PopOver createPopOver(Node node) {
        
        DoubleProperty masterArrowSize;
        DoubleProperty masterArrowIndent;
        DoubleProperty masterCornerRadius;
        ObjectProperty<PopOver.ArrowLocation> masterArrowLocation;
        BooleanProperty masterHeaderAlwaysVisible;
        
        masterArrowSize = new SimpleDoubleProperty(12);
        masterArrowIndent = new SimpleDoubleProperty(12);
        masterCornerRadius = new SimpleDoubleProperty(6);
        masterArrowLocation = new SimpleObjectProperty<>(PopOver.ArrowLocation.LEFT_TOP);
        masterHeaderAlwaysVisible = new SimpleBooleanProperty(false);
        
        
        PopOver mypopOver = new PopOver(node);
        mypopOver.setDetachable(false);
        mypopOver.setDetached(false);
        mypopOver.arrowSizeProperty().bind(masterArrowSize);
        mypopOver.arrowIndentProperty().bind(masterArrowIndent);
        mypopOver.arrowLocationProperty().bind(masterArrowLocation);
        mypopOver.cornerRadiusProperty().bind(masterCornerRadius);
        mypopOver.headerAlwaysVisibleProperty().bind(masterHeaderAlwaysVisible);
        mypopOver.setAnimated(true);
        mypopOver.setCloseButtonEnabled(true);  
        return mypopOver;
    }
//------------------------------------------------------------------------------      
    private void AddMAGTab(ObjectMagnitude M)  {
        try {
            if (M==null) return;
            Tab tab = new Tab();
            FXMLLoader fXMLLoader = new FXMLLoader();
            Parent tab_root;
            
            String Magnitude_tab_fxml_name = "Magnitude.fxml"; 
 
            tab_root = (Parent) fXMLLoader.load(this.getClass().getResource(Magnitude_tab_fxml_name).openStream());
             
            MagnitudeController controller_ML = (MagnitudeController)fXMLLoader.getController();
            
            controller_ML.setMAG(M);
           
//            if (M.getTypeMagnitude().toUpperCase().contains("MD"))
//                controller_ML.ShowData(false);
//            else
            controller_ML.ShowData(false);
                           
            tab.setContent(tab_root);
                
            Label label = new Label(M.getTypeMagnitude()); 
            tab.setGraphic(label);

            tabMags.getTabs().add(tab);

            fXMLLoader=null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }  
        
    }
    
//    private void setTabAsPreferred(Tab tab, ObjectMagnitude M){
//        Image iconImage = new Image("images/yellowstar_small.png");
//
//        // Creazione di un'etichetta con un'immagine     
//        String testo = M.getTypeMagnitude();
//        
//        Label label = new Label(testo, new ImageView(iconImage));
//        tab.setGraphic(label);       
//    }
    
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

    /**
     * @return the localOriginID
     */
    public long getLocalOriginID() {
        return localOriginID;
    }

    /**
     * @param localOriginID the localOriginID to set
     */
    public void setLocalOriginID(long localOriginID) {
        this.localOriginID = localOriginID;
    }

    /**
     * @return the PrimaryStage
     */
    public Stage getPrimaryStage() {
        return PrimaryStage;
    }
    
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
        if (this.LocationResultEvent==null) return true;
        if (this.LocationResultEvent.getNStations()==0) return true;
        //---------------------------------------------
        for (i=0; i<this.LocationResultEvent.getNStations(); i++) {
            if (this.LocationResultEvent.getStation(i).getNPhases()>0) {
                for (j=0; j<this.LocationResultEvent.getStation(i).getNPhases(); j++) {
                    tmpPh = this.LocationResultEvent.getStation(i).getPhase(j);
                    
                    tmpItem= new Phases_List_items(this.LocationResultEvent.getStation(i).getCode(),
                            tmpPh.getPick().getCha(),tmpPh.getIscCode(),
                            tmpPh.getPick().getArrivalTime().format(DateTimeFormatter.ISO_TIME), 
                            tmpPh.getResidual().floatValue(),
                            tmpPh.getWeight().floatValue(),
                            tmpPh.getEpDistanceKm(),
                            tmpPh.getAzimut(),
                            tmpPh.getPick().getQualityClass());
                    dati.add(tmpItem); 
                    
                }
            }
        }
//
        table_phases.setOnMouseClicked((MouseEvent e) -> {
            e.consume();
            if (e.getButton() == MouseButton.PRIMARY) { 
                int indice = ((TableView)e.getSource()).getSelectionModel().getFocusedIndex();
                String StationCode, PhaseCode;
                //String Spegnere="";
                ArrayList<String> Stazioni_da_accendere = new ArrayList();
//                if (last_blinking_station_code.trim().length()>0){
//                    Spegnere = last_blinking_station_code;
//                    Map_TurnStationsOFF();
//                }
                StationCode = String.valueOf(table_phases.getColumns().get(1).getCellData(indice));
                PhaseCode = String.valueOf(table_phases.getColumns().get(3).getCellData(indice));
//                if (!(StationCode.equalsIgnoreCase(last_blinking_station_code))) {
//                    last_blinking_station_code = StationCode;
//                    Stazioni_da_accendere.add(StationCode);
//                    Map_TurnStationsON(Stazioni_da_accendere);
//                }

                if (e.getClickCount()==2){
                    // Mostra la terna della stazione selezionata (magari zoomando sulla fase)                        
                    
                    App.G.ActivateStationByResidual(Long.valueOf(LocationResultEvent.getWork_event_ID()), StationCode, PhaseCode);
                }                   
            }
        });
        //---------------------------------------------
        //
        TableColumn usedCol = new TableColumn("Used");
        TableColumn stationCol = new TableColumn("Sta");
        TableColumn channelCol = new TableColumn("Chan");
        TableColumn phaseCol = new TableColumn("Phase");
        TableColumn timeCol = new TableColumn("Time");
        TableColumn resCol = new TableColumn("Residual");
        TableColumn qcCol = new TableColumn("QC");
        TableColumn weightCol = new TableColumn("Weight");
        TableColumn deltaCol = new TableColumn("Delta");
        TableColumn azimutCol = new TableColumn("Azimut");
        
              
        stationCol.setCellValueFactory(
            new PropertyValueFactory<>("station_code")
        );
//
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
        
        qcCol.setCellValueFactory(
            new PropertyValueFactory<>("qualityclass_weight")
        );
        qcCol.setStyle( "-fx-alignment: CENTER;");
        
        weightCol.setCellValueFactory(
            new PropertyValueFactory<>("weight")
        );
        weightCol.setStyle( "-fx-alignment: CENTER;");
        
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
                            setTextFill(Color.GREEN);
                            setStyle("-fx-text-fill: rgb(1, 148, 1);");
                            //setStyle("-fx-background-color: yellow");
                        } else  if ((Math.abs(Double.valueOf(item)) >= 0.5) && (Math.abs(Double.valueOf(item)) < 1.0)) { 
                                setTextFill(Color.ORANGE); 
                                setStyle("-fx-text-fill: rgb(255, 165, 0);");
                        } else {
                            setTextFill(Color.RED);
                            setStyle("-fx-text-fill: rgb(200, 17, 17);");
                        }
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
//
                Phases_List_items phItem = arg0.getValue();
                String Staz = phItem.getStation_code();
                String PhaseRemark = phItem.getPick_name();
                
                int idStaz = LocationResultEvent.StationCode_to_StationId(Staz);
                // Trova la fase nella stazione
                int idPh = ((Station)LocationResultEvent.getStations().get(idStaz)).phase_index(PhaseRemark);
                if (idPh<0) {
                    Logger.getLogger("org.ingv.pfx").log(java.util.logging.Level.SEVERE, "RETURNING NULL with PhaseRemark = *" + PhaseRemark +"*");
                    return null;
                }
                
                CheckBox checkBox = new CheckBox();
                
                if (((ObjectArrival)((Station)LocationResultEvent.getStations().get(idStaz)).getPhases().get(idPh)).getArrTimeIsUsed()) {
                    checkBox.selectedProperty().setValue(Boolean.TRUE);}
                else checkBox.selectedProperty().setValue(Boolean.FALSE);

                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> ov,
                            Boolean old_val, Boolean new_val) {
                        if (new_val) {
                            phItem.setUsed(new SimpleStringProperty("TRUE")); 
                            ((ObjectArrival)((Station)LocationResultEvent.getStations().get(idStaz)).getPhases().get(idPh)).setArrTimeIsUsed(true);
                            if (txtKmLimit.getText().trim().length()!=0){
                                if (((ObjectArrival)((Station)LocationResultEvent.getStations().get(idStaz)).getPhases().get(idPh)).getEpDistanceKm()>=Float.valueOf(txtKmLimit.getText()))
                                    txtKmLimit.clear();
                            }
                        } else {
                            phItem.setUsed(new SimpleStringProperty("FALSE"));
                            ((ObjectArrival)((Station)LocationResultEvent.getStations().get(idStaz)).getPhases().get(idPh)).setArrTimeIsUsed(false);
                        }

                    }
                });
//
                return new SimpleObjectProperty<>(checkBox);
//
            }

        });
//          
        table_phases.setItems(dati);
//        
        table_phases.getColumns().addAll(usedCol, stationCol, channelCol, phaseCol, 
            timeCol, resCol, qcCol, weightCol, deltaCol, azimutCol);
//   
         return true;   
    } catch (Exception ex) {
        return false;
    }
}
//------------------------------------------------------------------------------        
    private void make_residuals_chart(){
        //final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//      
        resChart.getData().clear();
//
        resChart.setTitle("Residuals by distance");
//        xAxis.setLabel("Km");       
//        yAxis.setLabel("Sec.");
//               
        XYChart.Series seriesP_green = new XYChart.Series();
        seriesP_green.setName("Pg"); 
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
        for (int i = 0; i < this.LocationResultEvent.getNStations(); i++) {
            
            if ((LocationResultEvent.getStation(i).getNPhases()>0) && (LocationResultEvent.getStation(i).HasAnUsedPhase())) {
                for (int k=0; k < this.LocationResultEvent.getStation(i).getNPhases(); k++) {
                   
                    if ((!this.LocationResultEvent.getStation(i).getPhase(k).getIscCode().equalsIgnoreCase("A")) && (LocationResultEvent.getStation(i).phase_is_used(k))) {
                        res=this.LocationResultEvent.getStation(i).getPhase(k).getResidual().floatValue();
                        //
                        if (this.LocationResultEvent.getStation(i).getPhase(k).getIscCode().contains("P")) {
                            if ((0.0 <= Math.abs(res)) && (Math.abs(res) < 0.5)){
                                seriesP_green.getData().add(new XYChart.Data(this.LocationResultEvent.getStation(i).getPhase(k).getEpDistanceKm(), res));
                                } else if ((0.5 <= Math.abs(res)) && (Math.abs(res) < 1.0)){
                                    seriesP_orange.getData().add(new XYChart.Data(this.LocationResultEvent.getStation(i).getPhase(k).getEpDistanceKm(), res));
                                } else seriesP_red.getData().add(new XYChart.Data(this.LocationResultEvent.getStation(i).getPhase(k).getEpDistanceKm(), res));  
                        }else{
                            if (this.LocationResultEvent.getStation(i).getPhase(k).getIscCode().contains("S")) {
                                if ((0.0 <= Math.abs(res)) && (Math.abs(res) < 0.5)){
                                    seriesS_green.getData().add(new XYChart.Data(this.LocationResultEvent.getStation(i).getPhase(k).getEpDistanceKm(), res));
                                    } else if ((0.5 <= Math.abs(res)) && (Math.abs(res) < 1.0)){
                                        seriesS_orange.getData().add(new XYChart.Data(this.LocationResultEvent.getStation(i).getPhase(k).getEpDistanceKm(), res));
                                    } else seriesS_red.getData().add(new XYChart.Data(this.LocationResultEvent.getStation(i).getPhase(k).getEpDistanceKm(), res));  
                            }

                        }

                    }
                    
                }
            }       
        }
// 

        this.resChart.setLegendVisible(false);

        this.resChart.getData().addAll(seriesP_green, seriesP_orange, seriesP_red,
                seriesS_green, seriesS_orange, seriesS_red);
        Set<Node> nodesP_green = this.resChart.lookupAll(".series" + 0);                
        for (Node n : nodesP_green) {
            n.setStyle("-fx-background-color: green, white;\n"
                    + "    -fx-background-insets: 0, 2;\n" 
                    + "    -fx-shape: null;\n"
                    + "    -fx-background-radius: 3px;\n"
                    + "    -fx-padding: 3px;");
            this.resChart.getData().get(0).setNode(n);
        }
//        
        Set<Node> nodesP_orange = this.resChart.lookupAll(".series" + 1);                
        for (Node n : nodesP_orange) {
            n.setStyle("-fx-background-color: orange, white;\n"
                    + "    -fx-background-insets: 0, 2;\n"
                    + "    -fx-background-radius: 3px;\n"
                    + "    -fx-shape: null;\n"
                    + "    -fx-padding: 3px;");
            this.resChart.getData().get(1).setNode(n);
            
        }
//        
        Set<Node> nodesP_red = this.resChart.lookupAll(".series" + 2);                
        for (Node n : nodesP_red) {
            n.setStyle("-fx-background-color: red, white;\n"
                    + "    -fx-background-insets: 0, 2;\n"
                    + "    -fx-background-radius: 3px;\n"
                    + "    -fx-shape: null;\n"
                    + "    -fx-padding: 3px;");
            this.resChart.getData().get(2).setNode(n);
        }             
//        
        Set<Node> nodesS_green = this.resChart.lookupAll(".series" + 3);                
        for (Node n : nodesS_green) {
            n.setStyle("-fx-background-color: green, white;\n"
                    + "    -fx-background-insets: 0, 2;\n" 
                    + "    -fx-background-radius: 3px;\n"
                    + "    -fx-shape: null;\n"
                    + "    -fx-padding: 3px;");
            this.resChart.getData().get(0).setNode(n);
        }
//        
        Set<Node> nodesS_orange = this.resChart.lookupAll(".series" + 4);                
        for (Node n : nodesS_orange) {
            n.setStyle("-fx-background-color: orange, white;\n"
                    + "    -fx-background-insets: 0, 2;\n"
                    + "    -fx-background-radius: 3px;\n"
                    + "    -fx-shape: null;\n"
                    + "    -fx-padding: 3px;");
            this.resChart.getData().get(1).setNode(n);
            
        }
//        
        Set<Node> nodesS_red = this.resChart.lookupAll(".series" + 5);                
        for (Node n : nodesS_red) {
            n.setStyle("-fx-background-color: red, white;\n"
                    + "    -fx-background-insets: 0, 2;\n"
                    + "    -fx-background-radius: 3px;\n"
                    + "    -fx-shape: null;\n"
                    + "    -fx-padding: 3px;");
            this.resChart.getData().get(2).setNode(n);
        }  
//        
//        Set<Node> nodes_green = this.resChart.lookupAll(".series" + 0);                
//        for (Node n : nodes_green) {
//            n.setStyle("-fx-background-color: green, white;\n"
//                    + "    -fx-background-insets: 0, 2;\n" 
//                    + "    -fx-background-radius: 3px;\n"
//                    + "    -fx-shape: null;\n"
//                    + "    -fx-padding: 3px;");
//            resChart.getData().get(0).setNode(n);
//        }
////        
//        Set<Node> nodes_orange = this.resChart.lookupAll(".series" + 1);                
//        for (Node n : nodes_orange) {
//            n.setStyle("-fx-background-color: orange, white;\n"
//                    + "    -fx-background-insets: 0, 2;\n"
//                    + "    -fx-background-radius: 3px;\n"
//                    + "    -fx-shape: null;\n"
//                    + "    -fx-padding: 3px;");
//            resChart.getData().get(1).setNode(n);
//        }
////        
//        Set<Node> nodes_red = this.resChart.lookupAll(".series" + 2);                
//        for (Node n : nodes_red) {
//            n.setStyle("-fx-background-color: red, white;\n"
//                    + "    -fx-background-insets: 0, 2;\n"
//                    + "    -fx-background-radius: 3px;\n"
//                    + "    -fx-shape: null;\n"
//                    + "    -fx-padding: 3px;");
//            resChart.getData().get(2).setNode(n);
//        }   
    }    
    
    //------------------------------------------------------------------------------
    /**
     * @return the xmlLocationResult
     */
    public Event getLocationResultEvent() {
        return LocationResultEvent;
    }
//------------------------------------------------------------------------------    
    /**
     * @param xmlLocationResult the xmlLocationResult to set
     */
    public void setLocationResultEvent(Event xmlLocationResult) {
        this.LocationResultEvent = xmlLocationResult;
    }
//------------------------------------------------------------------------------    
    /**
     * @return the result
     */
    public Event getResult() {
        return result;
    }
//------------------------------------------------------------------------------    
    /**
     * @param result the result to set
     */
    public void setResult(Event result) {
        this.result = result;
    }

    @FXML
    private void btnOk_Click(ActionEvent event) {
        try {
            result = LocationResultEvent;
            
            PrimaryStage.close();  
            release_map();
           // App.G.LocationControllers.remove(this);
            System.gc();
        } catch (Exception ex) {
        }  
    }

    @FXML
    private void btnLocate_Click(ActionEvent event) {
        Hypo2000Handler HYPO2000 = new Hypo2000Handler(App.G.options.get_hypo2000_path());
        
        HYPO2000.setHypo2000_CONFIG(Hypo2000_CONFIG);
//
        PymlHandler PYML = new PymlHandler(App.G.options.get_pyml_url());        
//
        ArrayList<duration_buffer_item> buffered_codas=null;
        if (LocationResultEvent.HasCodas()){
            buffered_codas = LocationResultEvent.BackupCodas();
        }
        
        Event relocated_event = HYPO2000.PostRequest(LocationResultEvent);
//        
        if (relocated_event!=null) {
            // Mix dei due eventi e aggiornamento interfaccia  
            relocated_event = PYML.PostRequest(relocated_event);
            if (relocated_event==null) {
                pfxDialog.ShowErrorMessage("Error while using PYML", PrimaryStage);
            } else {         
                
                if (buffered_codas!=null){
                    relocated_event.RestoreCodas(buffered_codas);
                    relocated_event.CalculateMD();
                }
                
                LocationResultEvent.Update_with_location_results(relocated_event);
                // ...Poi aggiorna l'interfaccia:
                ShowData();
            }           
        } else {
            pfxDialog.ShowErrorMessage("Event location returned no results.", PrimaryStage);
        }
        //hypo_client=null;
        HYPO2000=null;
        PYML=null;
    }

    @FXML
    private void btnReMag_Click(ActionEvent event) {
        PymlHandler PYML = new PymlHandler(App.G.options.get_pyml_url()); 
        
        ArrayList<duration_buffer_item> buffered_codas=null;
        if (LocationResultEvent.HasCodas()){
            buffered_codas = LocationResultEvent.BackupCodas();
        }
        
        Event relocated_event = PYML.PostRequest(LocationResultEvent);
        if (relocated_event==null) {
            pfxDialog.ShowErrorMessage("Error while using PYML", PrimaryStage);
        } else {         

            if ((buffered_codas!=null) && (!relocated_event.HasMD())){ // ad not gia ha la MD !!!!!!!!!!!!!!!!!!!!!!
                relocated_event.RestoreCodas(buffered_codas);
                relocated_event.CalculateMD();
            }

            ShowData();
        }    
    }

    @FXML
    private void btnOnlyMd_click(ActionEvent event) {
        // Remove the Ml and amplitudes
LocationResultEvent.getInnerObjectEvent().getOrigins().get(0).getMagnitudes().clear();

        // Calculate only the Md
        if (LocationResultEvent.HasCodas()){
            LocationResultEvent.CalculateMD();
        } // else messaggio no codas
        
        ShowData();
    }

    @FXML
    private void table_amplitudes_MouseClicked(MouseEvent event) {
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
    
   
    
}
