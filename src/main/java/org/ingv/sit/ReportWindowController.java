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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.ingv.dante.model.GetMunicipiDistanceKmPopolazione200ResponseDataInner;
import org.ingv.dante.model.ObjectOrigin;
import org.ingv.sit.utils.pfxDialog;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class ReportWindowController implements Initializable {

    @FXML
    private AnchorPane anchor_main;
    @FXML
    private Button btnClose;
    @FXML
    private WebView ReportWebView;
    @FXML
    private Button btnPrint;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }  
    
    
    public void ShowData(ObjectOrigin O, List<GetMunicipiDistanceKmPopolazione200ResponseDataInner> Cities){
        File f = new File("configuration/event_report_template.html"); 
        
        FileReader fr=null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader br = new BufferedReader(fr);
        String line, htmltext="";
        try {
            while((line = br.readLine()) != null){
                htmltext+=line;
            }
        } catch (Exception ex) {
            Logger.getLogger(ReportWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        // Replace html file dummy tags with actual event data
        htmltext=htmltext.replace("mag_type",O.getMagnitudes().get(0).getTypeMagnitude());
        htmltext=htmltext.replace("mag_value",O.getMagnitudes().get(0).getMag().toString());
        htmltext=htmltext.replace("origintime", O.getOt().format(DateTimeFormatter.ISO_DATE_TIME).replace("T", " ").replace("Z", ""));
        htmltext=htmltext.replace("area_epi",O.getRegion());
        htmltext=htmltext.replace("Epi_lat",O.getLat().toString());
        htmltext=htmltext.replace("Epi_lon",O.getLon().toString());
        htmltext=htmltext.replace("Epi_dep",O.getDepth().toString());
        if (O.getErrH()!=null)
            htmltext=htmltext.replace("err_h",O.getErrH().toString());
        else
            htmltext=htmltext.replace("err_h","");
        if (O.getErrZ()!=null)
            htmltext=htmltext.replace("err_z",O.getErrZ().toString());
        else
            htmltext=htmltext.replace("err_z","");

        if ((Cities!=null)&&(!Cities.isEmpty())){
            for (int i=1; i<=6; i++){
                htmltext=htmltext.replace("com" + String.valueOf(i),Cities.get(i).getName());
                htmltext=htmltext.replace("prov" + String.valueOf(i),Cities.get(i).getSiglaPro());
                htmltext=htmltext.replace("delta" + String.valueOf(i),Cities.get(i).getDistKm().toString());
                htmltext=htmltext.replace("pop" + String.valueOf(i), Cities.get(i).getPopolazione().toString());
            }
        } else {
            for (int i=1; i<=6; i++){
                htmltext=htmltext.replace("com" + String.valueOf(i),"");
                htmltext=htmltext.replace("prov" + String.valueOf(i),"");
                htmltext=htmltext.replace("delta" + String.valueOf(i),"");
                htmltext=htmltext.replace("pop" + String.valueOf(i),"");
            }
        }
               
        ReportWebView.getEngine().loadContent(htmltext);
    }

    @FXML
    private void btnClose_Click(ActionEvent event) {
        try {
            // get a handle to the stage
            Stage stage = (Stage) btnClose.getScene().getWindow();
            // do what you have to do
            stage.close();  
        } catch (Exception ex) {
        }
    }

    @FXML
    private void btnPrint_Click(ActionEvent event) {
        try {
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null) {
                ReportWebView.getEngine().print(job);
                job.endJob();
            }
        } catch (Exception ex) {
            pfxDialog.ShowErrorMessage("Printer error", (Stage) anchor_main.getScene().getWindow());
        }
        
    }
    
}
