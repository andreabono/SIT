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
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;
import org.ingv.dante.api.UpdateApi;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectStationmagnitude;
import org.ingv.dante.model.UpdateEvent200Response;
import org.ingv.dante.model.UpdateEventRequest;
import org.ingv.dante.model.UpdateEventRequestData;
import org.ingv.dante.model.UpdateEventRequestDataEvent;
import org.ingv.sit.tablemodels.Localmagnitude_list_items;
import org.ingv.sit.utils.MagnitudeChartMaker;
import org.ingv.sit.utils.sitDialog;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;

public class MagnitudeController implements Initializable {
    protected ObjectMagnitude MAG;
    @FXML
    private Label lblMagvalue;
    @FXML
    private Label lblNSta;
    @FXML
    private Label lblNCha;

    @FXML
    private TableView<Localmagnitude_list_items> table_stationmagnitudes;
    @FXML
    private Label lblLowerUncert;
    @FXML
    private Label lblUpperUncert;
    @FXML
    private ChartViewer chartViewerGraph;
    
    private MagnitudeChartMaker MCM;
    @FXML
    private Label lblMagQuality;
    
    private PopOver popOver;
    @FXML
    private Tab tabMagsByAzimuth;
    @FXML
    private ChartViewer chartViewerGraphAzimuth;
    @FXML
    private TabPane tabMagMain;
    @FXML
    private Button btnSetMagAsPreferred;
    
    
    private Long EventId;
    private Long EventPreferredMagnitudeId;
    private String EventSource;
//------------------------------------------------------------------------------   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblMagvalue.setTextFill(new Color(0.8947, 0.5468, 0.0249, 1.0));
        
    }       
//------------------------------------------------------------------------------    
    public void ShowData(boolean show_checks){
        try {
            
            // FIRST CHART: MAGNITUDE DISTRIBUTION
            JFreeChart chart;
            if (MAG!=null) {
//                if (btnSetMagAsPreferred!=null) {
//                    if ((EventPreferredMagnitudeId!=null) && (EventPreferredMagnitudeId.equals(MAG.getId())))
//                        btnSetMagAsPreferred.setVisible(false);
//                    else
//                        btnSetMagAsPreferred.setVisible(true);
//                }

                btnSetMagAsPreferred.setVisible(false);
                
                MCM = new MagnitudeChartMaker(MAG);
                if (MCM!=null) {
                    chart = MCM.createChart();
                    if (chart!=null){
                        chartViewerGraph.setChart(chart);
                        chartViewerGraph.setVisible(true);
                    }
                    else
                        chartViewerGraph.setVisible(false);
                }               
                lblMagvalue.setText("Mag: " + String.format("%2.2f", MAG.getMag()).replace(",", ".")) ; 
                if (MAG.getLowerUncertainty()!=null)
                    lblLowerUncert.setText("Lower Uncertainty: " + String.format("%2.2f", MAG.getLowerUncertainty()).replace(",", "."));
                else
                    lblLowerUncert.setText("Lower Uncertainty: ");
                if (MAG.getUpperUncertainty()!=null)
                    lblUpperUncert.setText("Upper Uncertainty: " + String.format("%2.2f", MAG.getUpperUncertainty()).replace(",", "."));
                else
                    lblUpperUncert.setText("Upper Uncertainty: ");
                lblNSta.setText("N Stations: " + MAG.getNsta());
                lblNCha.setText("N Channels: " + MAG.getNcha());
                               
                if (MAG.getMagQuality()!=null)
                    lblMagQuality.setText("Mag. Quality: " + MAG.getMagQuality());
                else
                    lblMagQuality.setText("Mag. Quality: ??");
                
                if(MAG.getStationmagnitudes()!=null && !MAG.getStationmagnitudes().isEmpty()) populate_stationmagnitudes_grid(show_checks);
                
                //chartViewerGraph.setVisible(true);
            } else {
                lblMagvalue.setText("No magnitude");
                lblNSta.setText("N Stations: 0");
                lblNCha.setText("N Channels: 0");
                lblMagQuality.setText("Mag. Quality: ??");
                reset_table();
                
                chartViewerGraph.setVisible(false);
            }
            
            
            if (!App.G.show_magnitudebyazimuth_chart){
                if ((tabMagMain!=null) && (tabMagMain.getTabs()!=null))
                    tabMagMain.getTabs().remove(this.tabMagsByAzimuth);
            } else {
                // SECOND CHART: Magnitudes by azimuth
                JFreeChart chartAZIM=null;
                if (MCM==null) MCM = new MagnitudeChartMaker(MAG);
                
                if (chartAZIM == null) chartAZIM = MCM.createScatterDeltaAzimuthChart();
                if (chartAZIM!=null){
                    chartViewerGraphAzimuth.setChart(chartAZIM);
                    chartViewerGraphAzimuth.setVisible(true);
                }
                else
                    chartViewerGraphAzimuth.setVisible(false);   
            }
        } catch (Exception ex){
            App.logger.error(ex.getMessage());
        }
    }
//------------------------------------------------------------------------------
    private void reset_table(){
        table_stationmagnitudes.getColumns().clear();
        TableColumn staCol = new TableColumn("Sta");
        TableColumn chaCol = new TableColumn("Chan");
        TableColumn deltaCol = new TableColumn("Delta");
        TableColumn magCol = new TableColumn("Ml");
        TableColumn a1Col = new TableColumn("Amp 1");
        TableColumn t1Col = new TableColumn("Time 1");
        TableColumn a2Col = new TableColumn("Amp 2");
        TableColumn t2Col = new TableColumn("Time 2");

        table_stationmagnitudes.getColumns().addAll(staCol, chaCol, magCol, deltaCol ,a1Col, t1Col, a2Col, t2Col);
    }
//------------------------------------------------------------------------------    
    private void populate_stationmagnitudes_grid(boolean show_checks){
        ObjectStationmagnitude sta_mag;
        Localmagnitude_list_items tmpItem;
        try {
            table_stationmagnitudes.getColumns().clear();
            final ObservableList dati = FXCollections.observableArrayList();
            String ml, amp1, amp2, t01, t02, log, delta;
            Boolean usedmag;
            for (int i=0; i < MAG.getStationmagnitudes().size(); i++) {
                ml="0";
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
                if (sta_mag.getMag()!=null) ml = sta_mag.getMag().toString();
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
                        Float.valueOf(ml),
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

                     
            
            //TableColumn usedCol = new TableColumn("Used");
            TableColumn staCol = new TableColumn("Sta");
            TableColumn chaCol = new TableColumn("Chan");
            
            TableColumn deltaCol= new TableColumn("Delta");
            deltaCol.setCellValueFactory(
                new PropertyValueFactory<>("delta")
            );
            deltaCol.setStyle( "-fx-alignment: CENTER;");
            
            TableColumn magCol;
            if (MAG.getTypeMagnitude().toUpperCase().contains("MD"))
                 magCol = new TableColumn("Md");
            else
                magCol = new TableColumn("Ml");
            
            
                       
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
            
            magCol.setCellValueFactory(
                new PropertyValueFactory<>("ml")
            );
            magCol.setStyle( "-fx-alignment: CENTER;");
            

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

//            usedCol.setMinWidth(20);
//            usedCol.setStyle( "-fx-alignment: CENTER;");
//            usedCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Localmagnitude_list_items, CheckBox>, ObservableValue<CheckBox>>() {
//            @Override
//            public ObservableValue<CheckBox> call(
//                    TableColumn.CellDataFeatures<Localmagnitude_list_items, CheckBox> arg0) {
//
//                Localmagnitude_list_items mag_item = arg0.getValue();
//                String Staz = mag_item.getStation();
//                String Chan = mag_item.getChannel();         
////
//                CheckBox checkBox = new CheckBox();
//                if (mag_item.getUsed().getValue().equalsIgnoreCase("TRUE"))
//                    checkBox.selectedProperty().setValue(Boolean.TRUE);
//                else
//                    checkBox.selectedProperty().setValue(Boolean.FALSE);
// 
//                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
//                    public void changed(ObservableValue<? extends Boolean> ov,
//                            Boolean old_val, Boolean new_val) {
//                        
//                        if (!new_val) {
//                            // Station must be excluded from ML calc
//                            exclude_include_from_ml(true, Staz, Chan);
//                        } else {
//                            // Station can be included from ML calc
//                            exclude_include_from_ml(false, Staz, Chan);
//                        }
//                        
//                    }
//                });
//
//                return new SimpleObjectProperty<CheckBox>(checkBox);
//
//            }
//        });
         
        table_stationmagnitudes.setItems(dati);
//        if (show_checks){
//            if (MAG.getTypeMagnitude().toUpperCase().contains("MD")) 
//                table_stationmagnitudes.getColumns().addAll(logCol, usedCol, staCol, chaCol,  magCol, deltaCol, a1Col, t1Col);
//             else 
//                table_stationmagnitudes.getColumns().addAll(logCol,usedCol, staCol, chaCol, magCol, deltaCol,a1Col, t1Col, a2Col, t2Col);
//        } else
            if (MAG.getTypeMagnitude().toUpperCase().contains("MD")) 
                table_stationmagnitudes.getColumns().addAll(staCol, chaCol, magCol, deltaCol, a1Col, t1Col);
             else 
                table_stationmagnitudes.getColumns().addAll(staCol, chaCol, magCol, deltaCol, a1Col, t1Col, a2Col, t2Col);
        } catch (Exception ex) {
           

        }
    
    
    }
    
    
//------------------------------------------------------------------------------ 
    private PopOver createPopOver(Node node) {
        
        DoubleProperty masterArrowSize;
        DoubleProperty masterArrowIndent;
        DoubleProperty masterCornerRadius;
        ObjectProperty<ArrowLocation> masterArrowLocation;
        BooleanProperty masterHeaderAlwaysVisible;
        
        masterArrowSize = new SimpleDoubleProperty(12);
        masterArrowIndent = new SimpleDoubleProperty(12);
        masterCornerRadius = new SimpleDoubleProperty(6);
        masterArrowLocation = new SimpleObjectProperty<>(ArrowLocation.LEFT_TOP);
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
        mypopOver.setCloseButtonEnabled(true);  //.closeButtonEnabledProperty().bind(closeButtonEnabled.selectedProperty());
        return mypopOver;
    }
//------------------------------------------------------------------------------    
    /**
     * @return the MAG
     */
    public ObjectMagnitude getMAG() {
        return MAG;
    }

    /**
     * @param MAG the MAG to set
     */
    public void setMAG(ObjectMagnitude MAG) {
        this.MAG = MAG;
    }
    
    @FXML
    private void btnSetMagAsPreferred_click(ActionEvent event) {
        if (MAG!=null){
            UpdateApi magnitudeUpdater = new UpdateApi();         
            UpdateEventRequest updateEventRequest = new UpdateEventRequest();
            UpdateEventRequestDataEvent ev;
            UpdateEventRequestData data;

            try {  
                if ((App.G.User!=null) && (App.G.User.isLoggedIn()))
                    magnitudeUpdater.getApiClient().setBearerToken(App.G.User.getToken());
                else
                    magnitudeUpdater.getApiClient().setBearerToken("");

                ev = new UpdateEventRequestDataEvent();
                ev.setPreferredMagnitudeId(MAG.getId());

                data = new UpdateEventRequestData();
                data.setEvent(ev);

                updateEventRequest.setData(data);

                App.logger.debug("WS-LOG: ChangeEventType()--> eventUpdater.updateEvent posting");
                UpdateEvent200Response resp = magnitudeUpdater.updateEvent(EventId, updateEventRequest);
                App.logger.debug("WS-LOG: ChangeEventType()--> eventUpdater.updateEvent finished");
                
                
                switch (EventSource){
                    case "MAPFORM":
                        ((MapFormController)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).UpdateEventIterface_CARAVEL(null);
                        break;
                    case "WAVESFORM":
                        if (btnSetMagAsPreferred!=null)
                            btnSetMagAsPreferred.setVisible(false);
                        
                        AnchorPane anchorPane= (AnchorPane)btnSetMagAsPreferred.getParent();
                        Tab t= ((WavesFormController)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).findTabForAnchorPane(anchorPane);
                        
                        ((WavesFormController)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).ResetInactiveTabsStars(anchorPane);
                        ((WavesFormController)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).setTabAsPreferred(t, MAG);
                        
                        break;
                    case "LOCATIONFORM":
                        if (btnSetMagAsPreferred!=null)
                            btnSetMagAsPreferred.setVisible(false);
                        
                        AnchorPane anchorPane_L= (AnchorPane)btnSetMagAsPreferred.getParent();
                        Tab t_L= ((WavesFormController)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).findTabForAnchorPane(anchorPane_L);
                        
                        ((LocationResult2Controller)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).ResetInactiveTabsStars(anchorPane_L);
                        ((LocationResult2Controller)btnSetMagAsPreferred.getScene().getRoot().getScene().getRoot().getScene().getUserData()).setTabAsPreferred(t_L, MAG);
                        
                        break;
                }
                
            } catch (Exception ex) {
                sitDialog.ShowErrorMessage("Error:\n" + ex.getMessage() , null);
                //return false;
            } finally {
                magnitudeUpdater=null;

                ev=null;
                data=null;

            }
            
        }
    }

    /**
     * @return the EventId
     */
    public Long getEventId() {
        return EventId;
    }

    /**
     * @param EventId the EventId to set
     */
    public void setEventId(Long EventId) {
        this.EventId = EventId;
    }

    /**
     * @return the EventPreferredMagnitudeId
     */
    public Long getEventPreferredMagnitudeId() {
        return EventPreferredMagnitudeId;
    }

    /**
     * @param EventPreferredMagnitudeId the EventPreferredMagnitudeId to set
     */
    public void setEventPreferredMagnitudeId(Long EventPreferredMagnitudeId) {
        this.EventPreferredMagnitudeId = EventPreferredMagnitudeId;
    }

    /**
     * @return the EventSource
     */
    public String getEventSource() {
        return EventSource;
    }

    /**
     * @param EventSource the EventSource to set
     */
    public void setEventSource(String EventSource) {
        this.EventSource = EventSource;
    }
}
