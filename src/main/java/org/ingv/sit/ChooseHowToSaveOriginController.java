/**
 * FXML Controller class
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ChooseHowToSaveOriginController implements Initializable {
    private Stage PrimaryStage;  

    ToggleGroup groupHowTo = new ToggleGroup();
    @FXML
    private RadioButton radioSave;
    @FXML
    private RadioButton radioSaveAndNotify;
    @FXML
    private RadioButton radioSaveAsDraft;
    
    private String result;  // "CANCEL", "SAVE", "SAVE_AND_NOTIFY", "DRAFT"
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioSave.setToggleGroup(groupHowTo);
        radioSaveAndNotify.setToggleGroup(groupHowTo);
        radioSaveAsDraft.setToggleGroup(groupHowTo);
        
        radioSave.setSelected(true);
    }    

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    @FXML
    private void btnOk_Click(ActionEvent event) {
        if (radioSave.isSelected()){
            result = "SAVE";
        } else if (radioSaveAndNotify.isSelected()){
            result = "SAVE_AND_NOTIFY";
        } else if (radioSaveAsDraft.isSelected()){
            result = "DRAFT";
        }
        PrimaryStage.close();
    }

    @FXML
    private void btnCancel_Click(ActionEvent event) {
        result = "CANCEL";
        PrimaryStage.close();
    }

    /**
     * @return the PrimaryStage
     */
    public Stage getPrimaryStage() {
        return PrimaryStage;
    }

    /**
     * @param PrimaryStage the PrimaryStage to set
     */
    public void setPrimaryStage(Stage in_PrimaryStage) {
        PrimaryStage = in_PrimaryStage;
    }
    
}
