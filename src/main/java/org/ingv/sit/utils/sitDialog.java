/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
package org.ingv.sit.utils;

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class sitDialog {
    //------------------------------------------------------------------------------
    /**
     *
     * @param msgText
     */
        public static void ShowErrorMessage(String msgText, Stage owner){
            Platform.runLater(new Runnable(){
                @Override public void run() 
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.initOwner(owner); 
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("SIT - Error");
                    alert.setHeaderText("An error occurred!!");
                    alert.setContentText(msgText);

                    Optional<ButtonType> option = alert.showAndWait();
                }
            });          
    }       
//------------------------------------------------------------------------------
    /**
     *
     * @param msgText
     */
        public static void ShowInformationMessage(String msgText, Stage owner){            
            Platform.runLater(new Runnable(){
                @Override public void run() {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.initOwner(owner); 
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("SIT - Information");
                    alert.setContentText(msgText);
                    alert.showAndWait();
                }
                
            });
    }
//------------------------------------------------------------------------------
    public static ButtonType ShowConfirmationMessage(String header_text, String msgText, Stage owner){
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(owner); 
            alert.setTitle("SIT - Confirmation");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setHeaderText(header_text);
            alert.setContentText(msgText);

            Optional<ButtonType> option = alert.showAndWait();
 
            if (option.get() == null  || option.get() == ButtonType.CANCEL) {
               return ButtonType.CANCEL;
            } else if (option.get() == ButtonType.OK) {
               return ButtonType.OK;
            } else return null;
        } catch (Exception ex){

            return null;
        }
    }
 //------------------------------------------------------------------------------
    /**
     *
     * @param msgText
     */
        public static void ShowMessage(String msgText, String title, AlertType alert_type, Stage owner){
            Platform.runLater(() -> {
                Alert alert = new Alert(alert_type);
                alert.initOwner(owner); 
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle(title);
                alert.setContentText(msgText);
                alert.showAndWait();
            });
    }
//------------------------------------------------------------------------------
}
