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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import fr.brouillard.oss.jgitver.GitVersionCalculator;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class AboutController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Label lblVersion;
    @FXML
    private TextArea txtArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File workDir=null;
        try {           
            workDir = new File(System.getProperty("user.dir"));
            try (GitVersionCalculator jgitver = GitVersionCalculator.location(workDir).setAutoIncrementPatch(true) ) { 
                this.lblVersion.setText("Version " + jgitver.getVersion());
            }
            
            txtArea.setStyle("-fx-text-fill: white ;") ;
            txtArea.setText("Copyright (C) 2023 - INGV Rome \n " +
                    "This program is free software: you can redistribute it and/or modify \n it under the terms of the GNU General Public License as published by \n the Free Software Foundation, either version 3 of the License, or \n"
                    + "(at your option) any later version. \n"
                    + "This program is distributed in the hope that it will be useful, \n"
                    + "but WITHOUT ANY WARRANTY; without even the implied warranty of \n"
                    + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the \n"
                    + "GNU General Public License for more details. \n\n"
                    + "You should have received a copy of the GNU General Public License \n"
                    + "along with this program.  If not, see <https://www.gnu.org/licenses/>.\n"
                    + "Copyright (C) 2023 - INGV Rome \n ");
        } catch (Exception ex) {
            App.logger.error(ex.getMessage());
        } finally {
            if (workDir!=null) workDir=null;
        }
        
    }    


    @FXML
    private void btnClose_Click(ActionEvent event) {
        try {       
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();  
        } catch (Exception ex) {
        }
    }
    
}
