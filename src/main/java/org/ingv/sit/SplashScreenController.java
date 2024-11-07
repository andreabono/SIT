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

import fr.brouillard.oss.jgitver.GitVersionCalculator;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class SplashScreenController implements Initializable {

    @FXML
    private Label lblProgress;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label lblVersion;
    
    public static ProgressBar statProgressBar;
    public static Label statLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statLabel = this.lblProgress;
        statProgressBar = this.progressBar;  
        File workDir;
        try {        
            workDir = new File(System.getProperty("user.dir"));
            try (GitVersionCalculator jgitver = GitVersionCalculator.location(workDir).setAutoIncrementPatch(true) ) { //  .setMavenLike(true)) {
                this.lblVersion.setText("Version " + jgitver.getVersion());
            }
        } catch (Exception ex) {
            App.logger.error(ex.getMessage());
        } finally {
            workDir=null;
        }
    }       
    
}
