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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ingv.sit.datamodel.DataSource;
import org.ingv.sit.datamodel.DataSource.dsType;
import org.ingv.sit.utils.sitDialog;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class NewDatasourceController implements Initializable {

    @FXML
    private Label lblHost;
    @FXML
    private Label lblPorts;
    @FXML
    private Label lblUrl;
    @FXML
    private Button btnOK;
    @FXML
    private Button btnCancel;
    
    private DataSource myNewDataSource = new DataSource();
    @FXML
    private TextField txtHost;
    @FXML
    private TextField txtPorts;
    @FXML
    private TextField txtUrl;
    @FXML
    private TextField txtDescription;
    @FXML
    private Label lblDataSourrceType;
    @FXML
    private ComboBox<String> cmbDataSourceType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
//////////////////    /**
//////////////////     * Sets the data source type for the local resource.
//////////////////     */
//////////////////    public void setDSType(dsType type) {
//////////////////        myNewDataSource.setDatasourcetype(type);
//////////////////        cmbDataSourceType.setVisible(false);
//////////////////        lblDataSourrceType.setVisible(false);
//////////////////        switch (type) {
//////////////////            case FDSN_STATION:
//////////////////                txtUrl.setDisable(false);
//////////////////                txtHost.setDisable(true);
//////////////////                txtPorts.setDisable(true);
//////////////////                break;
//////////////////            case FDSN_EVENT:
//////////////////                txtUrl.setDisable(false);
//////////////////                txtHost.setDisable(true);
//////////////////                txtPorts.setDisable(true);
//////////////////                break;
//////////////////         case FDSN_DATASELECT:
//////////////////                txtUrl.setDisable(false);
//////////////////                txtHost.setDisable(true);
//////////////////                txtPorts.setDisable(true);
//////////////////             break;
//////////////////
//////////////////         default:
//////////////////                cmbDataSourceType.setVisible(true);
//////////////////                cmbDataSourceType.getItems().clear();
//////////////////                cmbDataSourceType.getItems().addAll("EARTHWORMWS", "FDSN_DATASELECT");
//////////////////                lblDataSourrceType.setVisible(true);
//////////////////                txtUrl.setDisable(true);
//////////////////                txtHost.setDisable(true);
//////////////////                txtPorts.setDisable(true);
//////////////////                break;
//////////////////        }
//////////////////    }
//////////////////
//////////////////    @FXML
//////////////////    private void btnCancel_Click(ActionEvent event) {
//////////////////        try {
//////////////////            // get a handle to the stage
//////////////////            Stage stage = (Stage) btnCancel.getScene().getWindow();
//////////////////            // do what you have to do
//////////////////            stage.close();  
//////////////////        } catch (Exception ex) {
//////////////////        }
//////////////////    }
////////////////////------------------------------------------------------------------------------
//////////////////    @FXML
//////////////////    private void btnOK_Click(ActionEvent event) {
//////////////////        try {
//////////////////            int res = InputValidation();
//////////////////        
//////////////////            switch (res){
//////////////////                case -1:
//////////////////                    break;
//////////////////                case 0:
//////////////////                    // Everything is ok
//////////////////                    AddDataSource();
//////////////////                    myNewDataSource=null;
//////////////////                    btnCancel.fire();
//////////////////                    break;
//////////////////                case 1:
//////////////////                    // Missing datasource description    
//////////////////                    sitDialog.ShowErrorMessage("Missing description");
//////////////////                    break;
//////////////////                case 2:
//////////////////                    // Missing or malformed URL
//////////////////                    sitDialog.ShowErrorMessage("Missing or malformed url");
//////////////////                    break;
//////////////////                case 3:
//////////////////                    // Missing wave server host
//////////////////                    sitDialog.ShowErrorMessage("Missing host name");
//////////////////                    break; 
//////////////////                case 4:
//////////////////                    // Missing wave server host
//////////////////                    sitDialog.ShowErrorMessage("Ports list must be like 123,456,789");
//////////////////                    break;     
//////////////////                case 100:
//////////////////                    break;
//////////////////            }
//////////////////        } catch (Exception ex){
//////////////////            
//////////////////        } 
//////////////////        
//////////////////        
//////////////////    }
////////////////////------------------------------------------------------------------------------    
//////////////////    private int InputValidation(){
//////////////////        // A description is always needed
//////////////////        if (txtDescription.getText().isBlank()) 
//////////////////            return 1;
//////////////////        else
//////////////////            myNewDataSource.setDescription(txtDescription.getText());
//////////////////        
//////////////////        // check the other fields
//////////////////        try {
//////////////////            switch (myNewDataSource.getDatasourcetype()){
//////////////////                case FDSN_STATION:
//////////////////                    if ((txtUrl.getText().isBlank())||(!txtUrl.getText().contains("http://"))||(txtUrl.getText().equalsIgnoreCase("http://"))) 
//////////////////                        return 2;
//////////////////                    else {
//////////////////                        myNewDataSource.setUrl(txtUrl.getText());
//////////////////                    }                                        
//////////////////                    break;
//////////////////                case FDSN_EVENT:
//////////////////                    if ((txtUrl.getText().isBlank())||(!txtUrl.getText().contains("http://"))||(txtUrl.getText().equalsIgnoreCase("http://"))) 
//////////////////                        return 2;
//////////////////                    else {
//////////////////                        myNewDataSource.setUrl(txtUrl.getText());
//////////////////                    }                          
//////////////////                    break;
//////////////////                case FDSN_DATASELECT:
//////////////////                    if ((txtUrl.getText().isBlank())||(!txtUrl.getText().contains("http://"))||(txtUrl.getText().equalsIgnoreCase("http://"))) 
//////////////////                        return 2;
//////////////////                    else {
//////////////////                        myNewDataSource.setUrl(txtUrl.getText());
//////////////////                    }       
//////////////////                    break;
//////////////////                case CARAVEL_EVENT: 
//////////////////                    if ((txtUrl.getText().isBlank())||(!txtUrl.getText().contains("http://"))||(txtUrl.getText().equalsIgnoreCase("http://"))) return 2;
//////////////////                    else {
//////////////////                        myNewDataSource.setUrl(txtUrl.getText());
//////////////////                    }                           
//////////////////                    break;
//////////////////                case EARTHWORMWS: 
//////////////////                    if (txtHost.getText().isBlank()) return 3;  
//////////////////                    if (txtPorts.getText().isBlank()) return 4; 
//////////////////                    
//////////////////                    myNewDataSource.setHostname(txtHost.getText());
//////////////////                    myNewDataSource.setPortslist(handle_ports_list(txtPorts.getText()));
//////////////////                    
//////////////////                    break;
//////////////////                default:
//////////////////                  break;
//////////////////            }          
////////////////////            
//////////////////            return 0;
//////////////////        } catch (Exception ex) {
//////////////////            return -1;
//////////////////        }
//////////////////    }
////////////////////------------------------------------------------------------------------------    
//////////////////    private ArrayList<Integer> handle_ports_list(String in_ports){
//////////////////        try {
//////////////////            if (in_ports.isBlank()|| in_ports.isEmpty()) return null;
//////////////////            ArrayList<Integer> res = new ArrayList();
//////////////////            ArrayList<String> res_string = new ArrayList();
//////////////////            res_string=  new ArrayList<String>(Arrays.asList(in_ports.split(",")));
//////////////////            
//////////////////            for (int i=0; i< res_string.size(); i++){
//////////////////                res.add(Integer.valueOf(res_string.get(i).trim()));
//////////////////            }
//////////////////            
//////////////////            return res;
//////////////////        } catch (Exception ex){
//////////////////            return null;
//////////////////        }
//////////////////    }
////////////////////------------------------------------------------------------------------------
//////////////////    @FXML
//////////////////    private void cmbDataSourceType_Changed(ActionEvent event) {
//////////////////        switch (cmbDataSourceType.getSelectionModel().getSelectedItem().toString()){
//////////////////            case "FDSN_DATASELECT":
//////////////////                myNewDataSource.setDatasourcetype(dsType.FDSN_DATASELECT);
//////////////////                txtUrl.setDisable(false);
//////////////////                txtHost.setDisable(true);
//////////////////                txtPorts.setDisable(true);
//////////////////                break;
//////////////////            case "EARTHWORMWS":
//////////////////                myNewDataSource.setDatasourcetype(dsType.EARTHWORMWS);
//////////////////                txtUrl.setDisable(true);
//////////////////                txtHost.setDisable(false);
//////////////////                txtPorts.setDisable(false);
//////////////////                break;
//////////////////        }
//////////////////       
//////////////////    }
////////////////////------------------------------------------------------------------------------
//////////////////    private void AddDataSource(){
//////////////////        switch (myNewDataSource.getDatasourcetype()){
//////////////////            case FDSN_STATION:
//////////////////                App.G.options.getDatasources_stations().add(myNewDataSource);
//////////////////                break;
//////////////////            case FDSN_EVENT:
//////////////////                App.G.options.getDatasources_events().add(myNewDataSource);
//////////////////                break;
//////////////////            case FDSN_DATASELECT:
//////////////////                App.G.options.getDatasources_waves().add(myNewDataSource);
//////////////////                break;
//////////////////            case CARAVEL_EVENT:
//////////////////                App.G.options.getDatasources_events().add(myNewDataSource);
//////////////////                break;
//////////////////            case EARTHWORMWS:
//////////////////                App.G.options.getDatasources_waves().add(myNewDataSource);
//////////////////                break;
//////////////////            case UNKNOWN:
//////////////////                break; 
//////////////////        }
//////////////////    }
}
