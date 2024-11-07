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

import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;

public class pfxPreloader extends Preloader {
    
    ProgressBar bar;
    private Stage PreloaderStage;
    private Scene scene;
//--------------------------------------------------------------------------------        
    @Override 
    public void init() throws Exception {
        try {
            Parent root1 = FXMLLoader.load(App.class.getResource("SplashScreen.fxml")); 
            scene = new Scene(root1); 
        } catch (Exception ex) {
            int k=0;
            k++;
        }
        
    }
//--------------------------------------------------------------------------------        
    @Override
    public void start(Stage stage) throws Exception {
        this.PreloaderStage = stage;      
        this.PreloaderStage.setScene(scene);

        this.PreloaderStage.initStyle(StageStyle.TRANSPARENT);               
        this.PreloaderStage.show();
    }
//--------------------------------------------------------------------------------        
    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        StateChangeNotification.Type type=scn.getType();
        
        switch (type) {
            case BEFORE_START:
                PreloaderStage.hide();
                break;
        }
        
    }
//--------------------------------------------------------------------------------        
    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
           SplashScreenController.statLabel.setText("Loading "+((ProgressNotification) info).getProgress() + "%");
           SplashScreenController.statProgressBar.setProgress(((ProgressNotification) info).getProgress()/100);
        }
    }
}
