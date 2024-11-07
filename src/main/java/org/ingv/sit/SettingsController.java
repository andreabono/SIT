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

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.ingv.sit.datamodel.DataSource;
import org.ingv.sit.mapping.MapHandler;
import org.ingv.sit.tablemodels.DataSource_List_items;
import org.ingv.sit.tablemodels.MAPLayers_list_items;
import org.ingv.sit.utils.pfxDialog;
import org.ingv.sit.utils.map_layers_element;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class SettingsController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtAlarmFileName;
    @FXML
    private CheckBox ckDebugMode;
    @FXML
    private TextField txtStartupDate;
    @FXML
    private CheckBox ckPolling;
    @FXML
    private TextField txtPollingInterval;
    @FXML
    private ComboBox<String> cmbWsStandard;
    @FXML
    private TextField txtMapBox_MinLon;
    @FXML
    private TextField txtMapBox_MaxLat;
    @FXML
    private TextField txtMapBox_MinLat;
    @FXML
    private TextField txtMapBox_MaxLon;
    @FXML
    private CheckBox ckUseMessaging;
    @FXML
    private TextField txtMessaging_Host;
    @FXML
    private TextField txtMessaging_Port;
    @FXML
    private TextField txtMessaging_User;
    @FXML
    private TextField txtMessaging_Password;
    @FXML
    private TextField txtMessaging_Exchange;
    @FXML
    private TextField txtMessaging_Virtualhost;
    @FXML
    private TextField txtMessaging_Queue;
    @FXML
    private ComboBox<String> cmbMessagingTool;
    @FXML
    private TextField txtWsHypo2000Path;

    
    @FXML
    private CheckBox ckUseWMS;
    @FXML
    private TextField txtWMSURL;
    @FXML
    private TableView<?> tblMapLayers;
    @FXML
    private TextField txtWsPYMLPath;
    
    MapHandler MH;
    @FXML
    private AnchorPane anchor_mappa;
       
    boolean mapBoxModified = false;
    @FXML
    private Button btnOk;
    @FXML
    private Button mapBoundBtn;
    @FXML
    private Button btnEditMapLayers;
    @FXML
    private Button btnReloadMapLayers;
    @FXML
    private TableView<?> tblDataSources_EW;
    @FXML
    private TableView<?> tblDataSources_FDSN;
    @FXML
    private TableView<?> tblDataSources_SL;
    @FXML
    private TableView<?> tblDataSources_INGV;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {    // ../../../images/
        try {   
            // Load the options
            load_options();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
  
    }    
    
    private void load_options(){
        try{
            this.cmbWsStandard.getItems().clear();
            this.cmbWsStandard.getItems().addAll("INGV_CUSTOM", "FDSN");
            //this.cmbWsStandard.getSelectionModel().select(App.G.options.get_ws_standard());
            
            this.txtAlarmFileName.setText(App.G.options.getAlarm_filename());
            //this.txtEWHostname.setText(App.G.options.getEarthwomServerName());
            this.txtMapBox_MaxLat.setText( String.valueOf(App.G.options.get_box_maxLat()));
            this.txtMapBox_MaxLon.setText( String.valueOf(App.G.options.get_box_maxLon()));
            this.txtMapBox_MinLat.setText( String.valueOf(App.G.options.get_box_minLat()));
            this.txtMapBox_MinLon.setText( String.valueOf(App.G.options.get_box_minLon()));
            
//            this.cmbMessagingTool.getItems().clear();
//            this.cmbMessagingTool.getItems().addAll("RABBITMQ");
//            this.cmbMessagingTool.getSelectionModel().select(App.G.options.getMessagingEngine());
//            
//            this.txtMessaging_Exchange.setText(App.G.options.getMessaging_RABBIT_ExchangeName());
//            this.txtMessaging_Host.setText(App.G.options.getMessaging_RABBIT_HostName());
//            this.txtMessaging_Password.setText(App.G.options.getMessaging_RABBIT_pwd());
//            this.txtMessaging_Port.setText(String.valueOf(App.G.options.getMessaging_RABBIT_PortNumber()));
//            this.txtMessaging_Queue.setText(App.G.options.getMessaging_RABBIT_QueueName());
//            this.txtMessaging_User.setText(App.G.options.getMessaging_RABBIT_UserName());
//            this.txtMessaging_Virtualhost.setText(App.G.options.getMessaging_RABBIT_VirtualHost());
            
            this.txtWMSURL.setText(App.G.options.getWMSURL());
                       
            this.txtPollingInterval.setText(String.valueOf(App.G.options.get_polling_interval()));
            
            this.txtStartupDate.setText(App.G.options.get_startup_date());
           
            //this.txtWsBasepathINGV.setText(App.G.options.get_ws_basepath_ingv());
            this.txtWsHypo2000Path.setText(App.G.options.get_hypo2000_path());
            this.txtWsPYMLPath.setText(App.G.options.get_pyml_url());
            
            //this.ckBulletinMode.setSelected(App.G.options.isBulletinMode());
            this.ckDebugMode.setSelected(App.G.options.isDebugMode());
            this.ckPolling.setSelected(App.G.options.isWebServicePollingActive());
            this.ckUseMessaging.setSelected(App.G.options.isMessagingActive());
            this.ckUseWMS.setSelected(App.G.options.useWMS());
            
            this.txtPollingInterval.setDisable(!ckPolling.isSelected());
            enable_disable_messaging_fields(!this.ckUseMessaging.isSelected());
            
//            populate_datasources_stations_list();
//            populate_datasources_waves_list();
//            populate_datasources_events_list();
            
populate_datasources_FDSN_list();
populate_datasources_EW_list();
populate_datasources_SL_list();
populate_datasources_INGV_list();


            populate_maplayers_list();
            
            MH = new MapHandler(318,121);
            MH.CreateMapWithCanvas(null, 0, 0, 
                    App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
                    App.G.options.get_box_minLat(),App.G.options.get_box_maxLat(),
                    true, false);
            
            anchor_mappa.getChildren().add(MH.getCanvas());
            AnchorPane.setBottomAnchor(MH.getCanvas(), (double)0);
            AnchorPane.setTopAnchor(MH.getCanvas(), (double)0);
            AnchorPane.setLeftAnchor(MH.getCanvas(), (double)0);
            AnchorPane.setRightAnchor(MH.getCanvas(), (double)0);                  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void enable_disable_messaging_fields(boolean disabled){
        this.cmbMessagingTool.setDisable(disabled);
        this.txtMessaging_Exchange.setDisable(disabled);
        this.txtMessaging_Host.setDisable(disabled);
        this.txtMessaging_Password.setDisable(disabled);
        this.txtMessaging_Port.setDisable(disabled);
        this.txtMessaging_Queue.setDisable(disabled);
        this.txtMessaging_User.setDisable(disabled);
        this.txtMessaging_Virtualhost.setDisable(disabled);
    }

    @FXML
    private void btnOk_Clicked(ActionEvent event) {
        // To do: INPUT VALIDATION
        // checks on input data... existing files, etc...
        // ...........
        
        // This writes the options   
        if (mapBoxModified) {
            ChangeMapBounds();
            mapBoxModified=false; 
        }
         
        if (!change_options() || !App.G.options.store()){
            pfxDialog.ShowErrorMessage("Error while saving options.", (Stage) btnCancel.getScene().getWindow());
        }else {
            // Polling options change            
            App.G.options.SaveDataSources();
            //
            if (App.G.options.isWebServicePollingActive()){
                ((MapFormController)App.G.getMainFormLoader().getController()).StartPolling();
            }
            //
            btnCancel.fire();
        }  
    }
    
    private boolean change_options(){
        try {
            App.G.options.setAlarm_filename(this.txtAlarmFileName.getText());
           // App.G.options.setBulletinMode(this.ckBulletinMode.isSelected());
            App.G.options.setDebugMode(this.ckDebugMode.isSelected());
            App.G.options.setMessagingActive(this.ckUseMessaging.isSelected());
            App.G.options.setWebServicePollingActive(this.ckPolling.isSelected());
            
            App.G.options.setPollingInterval(this.txtPollingInterval.getText());
            App.G.options.SetStartupDate(this.txtStartupDate.getText());
            
            App.G.options.SetBoxMaxLat(this.txtMapBox_MaxLat.getText());
            App.G.options.SetBoxMaxLon(this.txtMapBox_MaxLon.getText());
            App.G.options.SetBoxMinLat(this.txtMapBox_MinLat.getText());
            App.G.options.SetBoxMinLon(this.txtMapBox_MinLon.getText());
            
            App.G.options.SetHypo2000Path(this.txtWsHypo2000Path.getText());
            App.G.options.set_pyml_url(this.txtWsPYMLPath.getText());
            
       //     App.G.options.SetWsStandard(this.cmbWsStandard.getSelectionModel().getSelectedItem());
         
            App.G.options.setUseWMS(this.ckUseWMS.isSelected());
            App.G.options.setWMSURL(this.txtWMSURL.getText());
                       
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    

    @FXML
    private void btnCancel_Clicked(ActionEvent event) {
        try {    
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            
            stage.close();  
        } catch (Exception ex) {
        }
    }


    @FXML
    private void ckPolling_Checked(ActionEvent event) {
        this.txtPollingInterval.setDisable(!ckPolling.isSelected());
    }



    @FXML
    private void ckUseMessaging_Checked(ActionEvent event) {
        enable_disable_messaging_fields(!this.ckUseMessaging.isSelected());
    }

    
    
    
//------------------------------------------------------------------------------    
    private boolean populate_datasources_EW_list(){
        try { 

            int i, j;
            DataSource_List_items tmpItem;
            //earthworm_hostslist_element tmpHost;
            //float r,w,a, d;
            tblDataSources_EW.getColumns().clear();
           //
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (App.G.options.getDatasources_EW()==null) return true;
            if (App.G.options.getDatasources_EW().isEmpty()) return true;
            String p;
            //---------------------------------------------
            for (i=0; i<App.G.options.getDatasources_EW().size(); i++) { 
                if (App.G.options.getDatasources_EW().get(i).getPortslist()!=null)
                    p = App.G.options.getDatasources_EW().get(i).getPortslist().toString();
                else
                    p="";
                tmpItem= new DataSource_List_items(App.G.options.getDatasources_EW().get(i).isUsed(), 
                        App.G.options.getDatasources_EW().get(i).getDatasourcetype().toString(), 
                        App.G.options.getDatasources_EW().get(i).getDescription(), 
                        App.G.options.getDatasources_EW().get(i).getHostname(),
                        p,
                        App.G.options.getDatasources_EW().get(i).getUrl(),
                        App.G.options.getDatasources_EW().get(i).getHttpport()
                        );
                dati.add(tmpItem);
           }

            //---------------------------------------------
            //
            TableColumn usedCol = new TableColumn("Used");
            usedCol.setMinWidth(50);
            usedCol.setStyle( "-fx-alignment: CENTER;");
            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DataSource_List_items, CheckBox>, ObservableValue<CheckBox>>() {
                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<DataSource_List_items, CheckBox> arg0) {

                    DataSource_List_items item = arg0.getValue();
                    int idSource;
                    if (item.getUrl() == null)
                        idSource = find_source_index_by_hostname(App.G.options.getDatasources_EW(), item.getHost()+"#"+item.getPorts());
                    else
                        idSource = find_source_index_by_url(App.G.options.getDatasources_EW(), item.getUrl());
//               
//
                    CheckBox checkBox = new CheckBox();
                
//                
                    if (idSource != -1) {
                        if (App.G.options.getDatasources_EW().get(idSource).isUsed()) {
                            checkBox.selectedProperty().setValue(Boolean.TRUE);}
                        else checkBox.selectedProperty().setValue(Boolean.FALSE);
                    } else checkBox.selectedProperty().setValue(Boolean.TRUE);
                    

                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                            if (new_val) {
                                item.setUsed(true); 
                                if ((DataSource)(App.G.options.getDatasources_EW().get(idSource))!=null)
                                    ((DataSource)(App.G.options.getDatasources_EW().get(idSource))).setUsed(true);
                            } else {
                                item.setUsed(false);
                                if ((DataSource)(App.G.options.getDatasources_EW().get(idSource))!=null)
                                    ((DataSource)(App.G.options.getDatasources_EW().get(idSource))).setUsed(false);
                            }
                        }
                    });

                    return new SimpleObjectProperty<CheckBox>(checkBox);

                }
            });
                 
            TableColumn typeCol = new TableColumn("Type");
            TableColumn descCol = new TableColumn("Description");
            TableColumn hostCol = new TableColumn("Host");
            TableColumn portsCol = new TableColumn("Ports list");
            TableColumn httpportCol = new TableColumn("HTTP port");
            TableColumn urlCol = new TableColumn("URL");

            

            typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
            typeCol.setStyle( "-fx-alignment: CENTER;");
           
            descCol.setCellValueFactory(
                new PropertyValueFactory<>("description")
            );
            
            
            hostCol.setCellValueFactory(
                new PropertyValueFactory<>("host")
            );
            portsCol.setCellValueFactory(
                new PropertyValueFactory<>("ports")
            );
            httpportCol.setCellValueFactory(
                new PropertyValueFactory<>("httpport")
            );
            urlCol.setCellValueFactory(
                new PropertyValueFactory<>("url")
            );
            

            tblDataSources_EW.setItems(dati);

            tblDataSources_EW.getColumns().addAll(usedCol, typeCol, descCol, hostCol, portsCol, httpportCol, urlCol);

             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
    
    private boolean populate_datasources_SL_list(){
        return true;
    }
//    private boolean populate_datasources_waves_list(){
//        try { 
//
//            int i, j;
//            DataSource_List_items tmpItem;
//            //earthworm_hostslist_element tmpHost;
//            //float r,w,a, d;
//            tblDataSources_waves.getColumns().clear();
//           //
//            final ObservableList dati = FXCollections.observableArrayList();
//            //---------------------------------------------
//            // Check per sicurezza
//            //---------------------------------------------
//            if (App.G.options.getDatasources_waves()==null) return true;
//            if (App.G.options.getDatasources_waves().isEmpty()) return true;
//            String p;
//            //---------------------------------------------
//            for (i=0; i<App.G.options.getDatasources_waves().size(); i++) { 
//                if (App.G.options.getDatasources_waves().get(i).getPortslist()!=null)
//                    p = App.G.options.getDatasources_waves().get(i).getPortslist().toString();
//                else
//                    p="";
//                tmpItem= new DataSource_List_items(App.G.options.getDatasources_waves().get(i).isUsed(), 
//                        App.G.options.getDatasources_waves().get(i).getDatasourcetype().toString(), 
//                        App.G.options.getDatasources_waves().get(i).getDescription(), 
//                        App.G.options.getDatasources_waves().get(i).getHostname(),
//                        p,
//                        App.G.options.getDatasources_waves().get(i).getUrl(),
//                        App.G.options.getDatasources_waves().get(i).getHttpport()
//                        );
//                dati.add(tmpItem);
//           }
//
//            //---------------------------------------------
//            //
//            TableColumn usedCol = new TableColumn("Used");
//            usedCol.setMinWidth(50);
//            usedCol.setStyle( "-fx-alignment: CENTER;");
//            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DataSource_List_items, CheckBox>, ObservableValue<CheckBox>>() {
//                @Override
//                public ObservableValue<CheckBox> call(
//                        TableColumn.CellDataFeatures<DataSource_List_items, CheckBox> arg0) {
//
//                    DataSource_List_items item = arg0.getValue();
//                    int idSource;
//                    if (item.getUrl() == null)
//                        idSource = find_source_index_by_hostname(App.G.options.getDatasources_waves(), item.getHost()+"#"+item.getPorts());
//                    else
//                        idSource = find_source_index_by_url(App.G.options.getDatasources_waves(), item.getUrl());
////               
////
//                    CheckBox checkBox = new CheckBox();
//                
////                
//                    if (idSource != -1) {
//                        if (App.G.options.getDatasources_waves().get(idSource).isUsed()) {
//                            checkBox.selectedProperty().setValue(Boolean.TRUE);}
//                        else checkBox.selectedProperty().setValue(Boolean.FALSE);
//                    } else checkBox.selectedProperty().setValue(Boolean.TRUE);
//                    
//
//                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                        public void changed(ObservableValue<? extends Boolean> ov,
//                                Boolean old_val, Boolean new_val) {
//                            if (new_val) {
//                                item.setUsed(true); 
//                                if ((DataSource)(App.G.options.getDatasources_waves().get(idSource))!=null)
//                                    ((DataSource)(App.G.options.getDatasources_waves().get(idSource))).setUsed(true);
//                            } else {
//                                item.setUsed(false);
//                                if ((DataSource)(App.G.options.getDatasources_waves().get(idSource))!=null)
//                                    ((DataSource)(App.G.options.getDatasources_waves().get(idSource))).setUsed(false);
//                            }
//                        }
//                    });
//
//                    return new SimpleObjectProperty<CheckBox>(checkBox);
//
//                }
//            });
//                 
//            TableColumn typeCol = new TableColumn("Type");
//            TableColumn descCol = new TableColumn("Description");
//            TableColumn hostCol = new TableColumn("Host");
//            TableColumn portsCol = new TableColumn("Ports list");
//            TableColumn httpportCol = new TableColumn("HTTP port");
//            TableColumn urlCol = new TableColumn("URL");
//
//            
//
//            typeCol.setCellValueFactory(
//                new PropertyValueFactory<>("type")
//            );
//            typeCol.setStyle( "-fx-alignment: CENTER;");
//           
//            descCol.setCellValueFactory(
//                new PropertyValueFactory<>("description")
//            );
//            
//            
//            hostCol.setCellValueFactory(
//                new PropertyValueFactory<>("host")
//            );
//            portsCol.setCellValueFactory(
//                new PropertyValueFactory<>("ports")
//            );
//            httpportCol.setCellValueFactory(
//                new PropertyValueFactory<>("httpport")
//            );
//            urlCol.setCellValueFactory(
//                new PropertyValueFactory<>("url")
//            );
//            
//
//            tblDataSources_waves.setItems(dati);
//
//            tblDataSources_waves.getColumns().addAll(usedCol, typeCol, descCol, hostCol, portsCol, httpportCol, urlCol);
//
//             return true;   
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//------------------------------------------------------------------------------    
    private boolean populate_datasources_FDSN_list(){
        try { 

            int i, j;
            DataSource_List_items tmpItem;
            //earthworm_hostslist_element tmpHost;
            //float r,w,a, d;
            tblDataSources_FDSN.getColumns().clear();
           //
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (App.G.options.getDatasources_FDSN()==null) return true;
            if (App.G.options.getDatasources_FDSN().isEmpty()) return true;
           
            //---------------------------------------------
            for (i=0; i<App.G.options.getDatasources_FDSN().size(); i++) { 
               
                tmpItem= new DataSource_List_items(App.G.options.getDatasources_FDSN().get(i).isUsed(), 
                        App.G.options.getDatasources_FDSN().get(i).getDatasourcetype().toString(), 
                        App.G.options.getDatasources_FDSN().get(i).getDescription(), 
                        null,
                        null,
                        App.G.options.getDatasources_FDSN().get(i).getUrl(),
                        0
                        );
                dati.add(tmpItem);
           }

            //---------------------------------------------
            //
            TableColumn usedCol = new TableColumn("Used");
            usedCol.setMinWidth(50);
            usedCol.setStyle( "-fx-alignment: CENTER;");
            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DataSource_List_items, CheckBox>, ObservableValue<CheckBox>>() {
                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<DataSource_List_items, CheckBox> arg0) {

                    DataSource_List_items item = arg0.getValue();
                    int idSource = find_source_index_by_url(App.G.options.getDatasources_FDSN(), item.getUrl());
                    CheckBox checkBox = new CheckBox();                
                    if (idSource != -1) {
                        if (App.G.options.getDatasources_FDSN().get(idSource).isUsed()) {
                            checkBox.selectedProperty().setValue(Boolean.TRUE);}
                        else checkBox.selectedProperty().setValue(Boolean.FALSE);
                    } else checkBox.selectedProperty().setValue(Boolean.TRUE);
                    

                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                            if (new_val) {
                                item.setUsed(true); 
                                if ((DataSource)(App.G.options.getDatasources_FDSN().get(idSource))!=null)
                                    ((DataSource)(App.G.options.getDatasources_FDSN().get(idSource))).setUsed(true);
                            } else {
                                item.setUsed(false);
                                if ((DataSource)(App.G.options.getDatasources_FDSN().get(idSource))!=null)
                                    ((DataSource)(App.G.options.getDatasources_FDSN().get(idSource))).setUsed(false);
                            }
                        }
                    });

                    return new SimpleObjectProperty<CheckBox>(checkBox);

                }
            });
            TableColumn typeCol = new TableColumn("Type");
            TableColumn descCol = new TableColumn("Description");
//            TableColumn hostCol = new TableColumn("Host");
//            TableColumn portsCol = new TableColumn("Ports list");
            TableColumn urlCol = new TableColumn("URL");

            typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
            typeCol.setStyle( "-fx-alignment: CENTER;");
           
            descCol.setCellValueFactory(
                new PropertyValueFactory<>("description")
            );
            urlCol.setCellValueFactory(
                new PropertyValueFactory<>("url")
            );
            
            tblDataSources_FDSN.setItems(dati);

            tblDataSources_FDSN.getColumns().addAll(usedCol, typeCol, descCol, urlCol);

             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
    
//    private boolean populate_datasources_stations_list(){
//        try { 
//
//            int i, j;
//            DataSource_List_items tmpItem;
//            //earthworm_hostslist_element tmpHost;
//            //float r,w,a, d;
//            tblDataSources_stations.getColumns().clear();
//           //
//            final ObservableList dati = FXCollections.observableArrayList();
//            //---------------------------------------------
//            // Check per sicurezza
//            //---------------------------------------------
//            if (App.G.options.getDatasources_stations()==null) return true;
//            if (App.G.options.getDatasources_stations().isEmpty()) return true;
//           
//            //---------------------------------------------
//            for (i=0; i<App.G.options.getDatasources_stations().size(); i++) { 
//               
//                tmpItem= new DataSource_List_items(App.G.options.getDatasources_stations().get(i).isUsed(), 
//                        App.G.options.getDatasources_stations().get(i).getDatasourcetype().toString(), 
//                        App.G.options.getDatasources_stations().get(i).getDescription(), 
//                        null,
//                        null,
//                        App.G.options.getDatasources_stations().get(i).getUrl(),
//                        0
//                        );
//                dati.add(tmpItem);
//           }
//
//            //---------------------------------------------
//            //
//            TableColumn usedCol = new TableColumn("Used");
//            usedCol.setMinWidth(50);
//            usedCol.setStyle( "-fx-alignment: CENTER;");
//            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DataSource_List_items, CheckBox>, ObservableValue<CheckBox>>() {
//                @Override
//                public ObservableValue<CheckBox> call(
//                        TableColumn.CellDataFeatures<DataSource_List_items, CheckBox> arg0) {
//
//                    DataSource_List_items item = arg0.getValue();
//
//                    int idSource = find_source_index_by_url(App.G.options.getDatasources_stations(), item.getUrl());
////               
////
//                    CheckBox checkBox = new CheckBox();
//                
////                
//                    if (idSource != -1) {
//                        if (App.G.options.getDatasources_stations().get(idSource).isUsed()) {
//                            checkBox.selectedProperty().setValue(Boolean.TRUE);}
//                        else checkBox.selectedProperty().setValue(Boolean.FALSE);
//                    } else checkBox.selectedProperty().setValue(Boolean.TRUE);
//                    
//
//                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                        public void changed(ObservableValue<? extends Boolean> ov,
//                                Boolean old_val, Boolean new_val) {
//                            if (new_val) {
//                                item.setUsed(true); 
//                                if ((DataSource)(App.G.options.getDatasources_stations().get(idSource))!=null)
//                                    ((DataSource)(App.G.options.getDatasources_stations().get(idSource))).setUsed(true);
//                            } else {
//                                item.setUsed(false);
//                                if ((DataSource)(App.G.options.getDatasources_stations().get(idSource))!=null)
//                                    ((DataSource)(App.G.options.getDatasources_stations().get(idSource))).setUsed(false);
//                            }
//                        }
//                    });
//
//                    return new SimpleObjectProperty<CheckBox>(checkBox);
//
//                }
//            });
//            TableColumn typeCol = new TableColumn("Type");
//            TableColumn descCol = new TableColumn("Description");
////            TableColumn hostCol = new TableColumn("Host");
////            TableColumn portsCol = new TableColumn("Ports list");
//            TableColumn urlCol = new TableColumn("URL");
//
//            typeCol.setCellValueFactory(
//                new PropertyValueFactory<>("type")
//            );
//            typeCol.setStyle( "-fx-alignment: CENTER;");
//           
//            descCol.setCellValueFactory(
//                new PropertyValueFactory<>("description")
//            );
//            urlCol.setCellValueFactory(
//                new PropertyValueFactory<>("url")
//            );
//            
//            tblDataSources_stations.setItems(dati);
//
//            tblDataSources_stations.getColumns().addAll(usedCol, typeCol, descCol, urlCol);
//
//             return true;   
//        } catch (Exception ex) {
//            return false;
//        }
//    }
//------------------------------------------------------------------------------    
//    private boolean populate_datasources_events_list(){
//        try { 
//
//            int i, j;
//            DataSource_List_items tmpItem;
//            //earthworm_hostslist_element tmpHost;
//            //float r,w,a, d;
//            tblDataSources_events.getColumns().clear();
//           //
//            final ObservableList dati = FXCollections.observableArrayList();
//            //---------------------------------------------
//            // Check per sicurezza
//            //---------------------------------------------
//            if (App.G.options.getDatasources_events()==null) return true;
//            if (App.G.options.getDatasources_events().isEmpty()) return true;
//            String p;
//            //---------------------------------------------
//            for (i=0; i<App.G.options.getDatasources_events().size(); i++) {     
//                if (App.G.options.getDatasources_events().get(i).getPortslist()!=null)
//                    p = App.G.options.getDatasources_events().get(i).getPortslist().toString();
//                else
//                    p="";
//                tmpItem= new DataSource_List_items(App.G.options.getDatasources_events().get(i).isUsed(), 
//                        App.G.options.getDatasources_events().get(i).getDatasourcetype().toString(), 
//                        App.G.options.getDatasources_events().get(i).getDescription(), 
//                        App.G.options.getDatasources_events().get(i).getHostname(),
//                        p,
//                        App.G.options.getDatasources_events().get(i).getUrl(),
//                        0
//                        );
//                dati.add(tmpItem);
//           }
//
//            //---------------------------------------------
//            //
//            TableColumn usedCol = new TableColumn("Used");
//            usedCol.setMinWidth(50);
//            usedCol.setStyle( "-fx-alignment: CENTER;");
//            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DataSource_List_items, CheckBox>, ObservableValue<CheckBox>>() {
//                @Override
//                public ObservableValue<CheckBox> call(
//                        TableColumn.CellDataFeatures<DataSource_List_items, CheckBox> arg0) {
//
//                    DataSource_List_items item = arg0.getValue();
//
//                    int idSource = find_source_index_by_url(App.G.options.getDatasources_events(), item.getUrl());               
////
//                    CheckBox checkBox = new CheckBox();              
////                
//                    if (idSource != -1) {
//                        if (App.G.options.getDatasources_events().get(idSource).isUsed()) {
//                            checkBox.selectedProperty().setValue(Boolean.TRUE);}
//                        else checkBox.selectedProperty().setValue(Boolean.FALSE);
//                    } else checkBox.selectedProperty().setValue(Boolean.TRUE);
//                    
//
//                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                        public void changed(ObservableValue<? extends Boolean> ov,
//                                Boolean old_val, Boolean new_val) {
//                            if (new_val) {
//                                item.setUsed(true); 
//                                if ((DataSource)(App.G.options.getDatasources_events().get(idSource))!=null)
//                                    ((DataSource)(App.G.options.getDatasources_events().get(idSource))).setUsed(true);
//                            } else {
//                                item.setUsed(false);
//                                if ((DataSource)(App.G.options.getDatasources_events().get(idSource))!=null)
//                                    ((DataSource)(App.G.options.getDatasources_events().get(idSource))).setUsed(false);
//                            }
//                        }
//                    });
//
//                    return new SimpleObjectProperty<CheckBox>(checkBox);
//                }
//            });
//            TableColumn typeCol = new TableColumn("Type");
//            TableColumn descCol = new TableColumn("Description");
//            TableColumn hostCol = new TableColumn("Host");
//            TableColumn portsCol = new TableColumn("Ports list");
//            TableColumn urlCol = new TableColumn("URL");
//
//            typeCol.setCellValueFactory(
//                new PropertyValueFactory<>("type")
//            );
//            typeCol.setStyle( "-fx-alignment: CENTER;");
//           
//            descCol.setCellValueFactory(
//                new PropertyValueFactory<>("description")
//            );
//            
//            
//            hostCol.setCellValueFactory(
//                new PropertyValueFactory<>("host")
//            );
//            portsCol.setCellValueFactory(
//                new PropertyValueFactory<>("ports")
//            );
//            urlCol.setCellValueFactory(
//                new PropertyValueFactory<>("url")
//            );
//            
//
//            tblDataSources_events.setItems(dati);
//
//            tblDataSources_events.getColumns().addAll(usedCol, typeCol, descCol, hostCol, portsCol, urlCol);
//
//             return true;   
//        } catch (Exception ex) {
//            return false;
//        }
//    }
    private boolean populate_datasources_INGV_list(){
        try { 

            int i, j;
            DataSource_List_items tmpItem;
            //earthworm_hostslist_element tmpHost;
            //float r,w,a, d;
            tblDataSources_INGV.getColumns().clear();
           //
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (App.G.options.getDatasources_CARAVEL()==null) return true;
            if (App.G.options.getDatasources_CARAVEL().isEmpty()) return true;
            String p;
            //---------------------------------------------
            for (i=0; i<App.G.options.getDatasources_CARAVEL().size(); i++) {     
                if (App.G.options.getDatasources_CARAVEL().get(i).getPortslist()!=null)
                    p = App.G.options.getDatasources_CARAVEL().get(i).getPortslist().toString();
                else
                    p="";
                tmpItem= new DataSource_List_items(App.G.options.getDatasources_CARAVEL().get(i).isUsed(), 
                        App.G.options.getDatasources_CARAVEL().get(i).getDatasourcetype().toString(), 
                        App.G.options.getDatasources_CARAVEL().get(i).getDescription(), 
                        App.G.options.getDatasources_CARAVEL().get(i).getHostname(),
                        p,
                        App.G.options.getDatasources_CARAVEL().get(i).getUrl(),
                        0
                        );
                dati.add(tmpItem);
           }

            //---------------------------------------------
            //
            TableColumn usedCol = new TableColumn("Used");
            usedCol.setMinWidth(50);
            usedCol.setStyle( "-fx-alignment: CENTER;");
            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DataSource_List_items, CheckBox>, ObservableValue<CheckBox>>() {
                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<DataSource_List_items, CheckBox> arg0) {

                    DataSource_List_items item = arg0.getValue();

                    int idSource = find_source_index_by_url(App.G.options.getDatasources_CARAVEL(), item.getUrl());               
//
                    CheckBox checkBox = new CheckBox();              
//                
                    if (idSource != -1) {
                        if (App.G.options.getDatasources_CARAVEL().get(idSource).isUsed()) {
                            checkBox.selectedProperty().setValue(Boolean.TRUE);}
                        else checkBox.selectedProperty().setValue(Boolean.FALSE);
                    } else checkBox.selectedProperty().setValue(Boolean.TRUE);
                    

                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                            if (new_val) {
                                item.setUsed(true); 
                                if ((DataSource)(App.G.options.getDatasources_CARAVEL().get(idSource))!=null)
                                    ((DataSource)(App.G.options.getDatasources_CARAVEL().get(idSource))).setUsed(true);
                            } else {
                                item.setUsed(false);
                                if ((DataSource)(App.G.options.getDatasources_CARAVEL().get(idSource))!=null)
                                    ((DataSource)(App.G.options.getDatasources_CARAVEL().get(idSource))).setUsed(false);
                            }
                        }
                    });

                    return new SimpleObjectProperty<CheckBox>(checkBox);
                }
            });
            TableColumn typeCol = new TableColumn("Type");
            TableColumn descCol = new TableColumn("Description");
            TableColumn hostCol = new TableColumn("Host");
            TableColumn portCol = new TableColumn("Port");
            TableColumn urlCol = new TableColumn("URL");

            typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
            typeCol.setStyle( "-fx-alignment: CENTER;");
           
            descCol.setCellValueFactory(
                new PropertyValueFactory<>("description")
            );
            
            
            hostCol.setCellValueFactory(
                new PropertyValueFactory<>("host")
            );
            portCol.setCellValueFactory(
                new PropertyValueFactory<>("httpport")
            );
            urlCol.setCellValueFactory(
                new PropertyValueFactory<>("url")
            );
            

            tblDataSources_INGV.setItems(dati);

            tblDataSources_INGV.getColumns().addAll(usedCol, typeCol, descCol, hostCol, portCol, urlCol);

             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------ 
    private int find_source_index_by_url(ArrayList<DataSource> arr, String url){
        try {
           
            boolean fnd=false;
            int id=0;
            while ((!fnd) && (id < arr.size())){
                if ((arr.get(id).getUrl()!=null) && (arr.get(id).getUrl().equalsIgnoreCase(url)))
                    fnd=true;
                else
                    id++;
            }
            
            if (fnd) 
                return id;
            else return -1;
 
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    private int find_source_index_by_hostname(ArrayList<DataSource> arr, String hostname_and_ports){
        try {
           
            boolean fnd=false;
            int id=0;
            String host, ports;
            String[] a = hostname_and_ports.split("#");
            host = a[0];
            ports = a[1].replace("[", "").replace("]", "");
            while ((!fnd) && (id < arr.size())){
                if ((arr.get(id).getHostname()!=null) && (arr.get(id).getPortslist()!=null) && (arr.get(id).getHostname().equalsIgnoreCase(host))&& (this.make_a_string(arr.get(id).getPortslist()).equalsIgnoreCase(ports)))
                    fnd=true;
                else
                    id++;
            }
            
            if (fnd) 
                return id;
            else return -1;
 
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    private boolean populate_maplayers_list(){
        try { 

            int i, j;
            MAPLayers_list_items tmpItem;
            map_layers_element tmpLayer;
            float r,w,a, d;
            this.tblMapLayers.getColumns().clear();
            final ObservableList dati = FXCollections.observableArrayList();
            //---------------------------------------------
            // Check per sicurezza
            //---------------------------------------------
            if (App.G.options.getMap_layers()==null) return true;
            if (App.G.options.getMap_layers().isEmpty()) return true;
            //---------------------------------------------
            for (i=0; i<App.G.options.getMap_layers().size(); i++) {      
                tmpItem= new MAPLayers_list_items(App.G.options.getMap_layers().get(i).isUsed(), 
                        App.G.options.getMap_layers().get(i).getType(), 
                        App.G.options.getMap_layers().get(i).getFilename(), 
                        App.G.options.getMap_layers().get(i).getTitle(),
                        App.G.options.getMap_layers().get(i).getLinecolor(),
                        App.G.options.getMap_layers().get(i).getFillcolor(), 
                        App.G.options.getMap_layers().get(i).getOpacity()
                        );
                dati.add(tmpItem);
           }

            //---------------------------------------------
            //
            TableColumn usedCol = new TableColumn("Used");
            TableColumn typeCol = new TableColumn("Type");
            TableColumn fileCol = new TableColumn("File name");
            TableColumn titleCol = new TableColumn("Layer title");
            TableColumn linecolorCol = new TableColumn("Line color (R,G,B)");
            TableColumn fillcolorCol = new TableColumn("Fill color (R,G,B)");
            TableColumn opacityCol = new TableColumn("Opacity");

            usedCol.setCellValueFactory(
                new PropertyValueFactory<>("used")
            );
            usedCol.setStyle( "-fx-alignment: CENTER;");

            typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type")
            );
            typeCol.setStyle( "-fx-alignment: CENTER;");
           
            fileCol.setCellValueFactory(
                new PropertyValueFactory<>("filename")
            );
            
            
            titleCol.setCellValueFactory(
                new PropertyValueFactory<>("title")
            );
            
            
            linecolorCol.setCellValueFactory(
                new PropertyValueFactory<>("linecolor")
            );
            linecolorCol.setStyle( "-fx-alignment: CENTER;");
            
            fillcolorCol.setCellValueFactory(
                new PropertyValueFactory<>("fillcolor")
            );
            fillcolorCol.setStyle( "-fx-alignment: CENTER;");
            
            opacityCol.setCellValueFactory(
                new PropertyValueFactory<>("opacity")
            );
            opacityCol.setStyle( "-fx-alignment: CENTER;");


            this.tblMapLayers.setItems(dati);

            this.tblMapLayers.getColumns().addAll(usedCol, typeCol, fileCol, titleCol, linecolorCol, fillcolorCol, opacityCol);

             return true;   
        } catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------    
    private String make_a_string(ArrayList<Integer> values){
        if (values==null) return "";
        if (values.isEmpty()) return "";
        if (values.size()==1) 
            return String.valueOf(values.get(0));
        else {
            String res= String.valueOf(values.get(0));
            for (int i=1; i< values.size(); i++) {
                res+=", " + String.valueOf(values.get(i));
            }
            return res;
        }
        
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnEditMapLayers_Click(ActionEvent event) {
         File tmp;
        try {
            tmp = new File(App.G.options.getMap_settings_file());
            if (tmp.exists())
                Desktop.getDesktop().open(tmp);  
        } catch (Exception ex) {
        } finally {
            tmp=null;
        }
    }
//------------------------------------------------------------------------------
    @FXML
    private void btnReloadMapLayers_Click(ActionEvent event) {
        
        App.G.options.setMap_layers(App.G.options.readMapSettingsFromFile(App.G.options.getMap_settings_file()));   
        populate_maplayers_list();
    }
//------------------------------------------------------------------------------
    private void btnDataSources_stations_minus_Click(ActionEvent event) {
        if (pfxDialog.ShowConfirmationMessage("Data source will be permanently removed", "Are you sure?", (Stage) btnCancel.getScene().getWindow())==ButtonType.OK){
            System.out.println("To do:   Elimino");
        } else System.out.println("To do:   NON Elimino");
    }
//------------------------------------------------------------------------------
    @FXML
    private void mapBoundBtn_Click(ActionEvent event) {
        
        this.txtMapBox_MaxLat.setText( Double.toString(round(MH.getMap().getViewport().getBounds().getMaxY(),2)));
        this.txtMapBox_MaxLon.setText( Double.toString(round(MH.getMap().getViewport().getBounds().getMaxX(),2)));
        this.txtMapBox_MinLat.setText( Double.toString(round(MH.getMap().getViewport().getBounds().getMinY(),2)));
        this.txtMapBox_MinLon.setText( Double.toString(round(MH.getMap().getViewport().getBounds().getMinX(),2)));
         
        mapBoxModified = true;
       
    }
//------------------------------------------------------------------------------    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }    
//------------------------------------------------------------------------------    
    private void ChangeMapBounds(){
        App.G.options.SetBoxMinLon(Double.toString(round(MH.getMap().getViewport().getBounds().getMinX(),2)));
        App.G.options.SetBoxMaxLon(Double.toString(round(MH.getMap().getViewport().getBounds().getMaxX(),2)));
        App.G.options.SetBoxMinLat(Double.toString(round(MH.getMap().getViewport().getBounds().getMinY(),2)));
        App.G.options.SetBoxMaxLat(Double.toString(round(MH.getMap().getViewport().getBounds().getMaxY(),2)));
    }

    @FXML
    private void ckDebugMode_Checked(ActionEvent event) {
    }



    @FXML
    private void cmbWsStandard_Clicked(ActionEvent event) {
    }

    @FXML
    private void ckUseWMS_Checked(ActionEvent event) {
    }

    @FXML
    private void cmbMessagingTool_Checked(ActionEvent event) {
    }
}