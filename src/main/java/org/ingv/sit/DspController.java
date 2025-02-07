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

import com.github.psambit9791.jdsp.filter.FIRWin1;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ingv.sit.datamodel.Event;
import org.ingv.sit.utils.DSP;
import org.ingv.sit.utils.sitDialog;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.Range;

public class DspController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> IIR_cmbFilterType;
    @FXML
    private ComboBox<String> IIR_cmbFilterCut;
    @FXML
    private TextField IIR_txtF1;
    @FXML
    private Button IIR_btnOk;
    @FXML
    private TextField IIR_txtF2;  
    
    private DSP dsp_tool;
    
    private int ID_waves_controller;
    @FXML
    private Button IIR_btnCancel;
    
    private Event E;
    @FXML
    private TextField IIR_txtOrder;
    @FXML
    private ComboBox<String> FIR_cmbFilterType;
    @FXML
    private ComboBox<String> FIR_cmbFilterCut;
    @FXML
    private TextField FIR_txtF1;
    @FXML
    private TextField FIR_txtF2;
    @FXML
    private Button FIR_btnOk;
    @FXML
    private Button FIR_btnCancel;
   
    @FXML
    private TextField FIR_txtNTaps;
    @FXML
    private TextField FIR_txtRipple;
    @FXML
    private TextField FIR_txtWidth;
//------------------------------------------------------------------------------  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dsp_tool  = new DSP();
        
        E = App.G.WavesControllers.get(ID_waves_controller).getMyEvent();
        
        IIR_cmbFilterCut.setDisable(true);
        
        IIR_cmbFilterType.getItems().add("CHEBYSHEV");
        IIR_cmbFilterType.getItems().add("BUTTERWORTH");
        IIR_cmbFilterType.getItems().add("BESSEL");
        
        IIR_cmbFilterCut.getItems().add("High pass");
        IIR_cmbFilterCut.getItems().add("Low pass");
        IIR_cmbFilterCut.getItems().add("Band pass");
        IIR_cmbFilterCut.getItems().add("Band stop");
        
        
        FIR_cmbFilterType.getItems().add("WINDOWED");
        //FIR_cmbFilterType.getItems().add("WIDOWED WITH GAIN");
        //FIR_cmbFilterType.getItems().add("LEAST-SQUARES OPTIMISED");
        
        FIR_cmbFilterCut.getItems().add("High pass - Ripple");
        FIR_cmbFilterCut.getItems().add("High pass - N. Taps");
        FIR_cmbFilterCut.getItems().add("Low pass - Ripple");
        FIR_cmbFilterCut.getItems().add("Low pass - N. Taps");
        FIR_cmbFilterCut.getItems().add("Band pass - Ripple");
        FIR_cmbFilterCut.getItems().add("Band pass - N. Taps");
        FIR_cmbFilterCut.getItems().add("Band stop - Ripple");
        FIR_cmbFilterCut.getItems().add("Band stop - N. Taps");
        
    }    
//------------------------------------------------------------------------------
    @FXML
    private void btnCancel_Clicked(ActionEvent event) {
         try {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();  
        } catch (Exception ex) {
        }
    }
//------------------------------------------------------------------------------
    @FXML
    private void IIR_btnOk_Click(ActionEvent event) {
        try {
            if (IIR_InputValidation()){
                String fType=IIR_cmbFilterType.getValue();
                if (!E.getActiveTerna().getWaves().isEmpty()) {    
                    for (int idWave=0; idWave < E.getActiveTerna().getWaves().size(); idWave++) {
                        //
                        E.getActiveTerna().getWaves().get(idWave).Backup_Samples();
                        E.getActiveTerna().getWaves().get(idWave).setFilters(E.getActiveTerna().getWaves().get(idWave).getFilters()+1);
                        switch(IIR_cmbFilterCut.getValue()){
                            case "Low pass":
                                dsp_tool.IIR_LowPass_psambit(IIR_cmbFilterType.getValue(), E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        0, (int)E.getActiveTerna().getWaves().get(idWave).getnSamples(), 
                                         Float.valueOf(IIR_txtF2.getText()), 
                                        Integer.valueOf(IIR_txtOrder.getText()));
                                break;
                            case "High pass":
                                dsp_tool.IIR_HighPass_psambit(IIR_cmbFilterType.getValue(), E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        0, (int)E.getActiveTerna().getWaves().get(idWave).getnSamples(), 
                                         Float.valueOf(IIR_txtF1.getText()), 
                                        Integer.valueOf(IIR_txtOrder.getText()));
                                break;
                            case "Band pass":                       
                                dsp_tool.IIR_BandPass_psambit(IIR_cmbFilterType.getValue(), E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        0, (int)E.getActiveTerna().getWaves().get(idWave).getnSamples(), 
                                        Float.valueOf(IIR_txtF1.getText()), Float.valueOf(IIR_txtF2.getText()), 
                                        Integer.valueOf(IIR_txtOrder.getText()));
                                    
                                break;
                            case "Band stop":
                                dsp_tool.IIR_BandStop_psambit(IIR_cmbFilterType.getValue(), E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        0, (int)E.getActiveTerna().getWaves().get(idWave).getnSamples(), 
                                        Float.valueOf(IIR_txtF1.getText()), Float.valueOf(IIR_txtF2.getText()), 
                                        Integer.valueOf(IIR_txtOrder.getText()));
                                break;
                        }
                    }
                } 
                //
                Range xRange = ((XYPlot)App.G.WavesControllers.get(ID_waves_controller).getPlot_combinato_TERNA().getSubplots().get(0)).getDomainAxis().getRange();
                App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA(xRange, false, false);
                App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA_new(xRange, false, false);
          
                IIR_btnCancel.setDisable(false);
            } else {
                sitDialog.ShowErrorMessage("Please check your input", null); 
            }
        } catch (Exception ex){
        }
        
    }
//------------------------------------------------------------------------------    
    private boolean IIR_InputValidation(){
        try {
            if (IIR_txtOrder.getText().isBlank()|| IIR_txtOrder.getText().isEmpty()) return false;
            if (!App.G.IsNumeric(IIR_txtOrder.getText())) return false;
            
            switch(IIR_cmbFilterCut.getValue()){
                case "Low pass":
                    if (IIR_txtF2.getText().isBlank()) return false;

                    break;
                case "High pass":
                    if (IIR_txtF1.getText().isBlank()) return false;
                    break;
                case "Band pass":
                    if (IIR_txtF1.getText().isBlank() || IIR_txtF2.getText().isBlank()) return false;

                    break;
                case "Band stop":
                    if (IIR_txtF1.getText().isBlank() || IIR_txtF2.getText().isBlank()) return false;

                    break;
                default:
                    return false;
            }
            //
            return true;
        } catch (Exception ex){
            return false;
        }
    }
//------------------------------------------------------------------------------    
    private boolean FIR_InputValidation(){
        try {
            switch(FIR_cmbFilterCut.getValue()){
                case "Low pass - Ripple":
                    if (FIR_txtF2.getText().isBlank()) return false;
                    if (FIR_txtRipple.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "Low pass - N. Taps":
                    if (FIR_txtF2.getText().isBlank()) return false;
                    if (FIR_txtNTaps.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "High pass - Ripple":
                    if (FIR_txtF1.getText().isBlank()) return false;
                    if (FIR_txtRipple.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "High pass - N. Taps":
                    if (FIR_txtF1.getText().isBlank()) return false;
                    if (FIR_txtNTaps.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "Band pass - Ripple":
                    if (FIR_txtF1.getText().isBlank() || FIR_txtF2.getText().isBlank()) return false;
                    if (FIR_txtRipple.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "Band pass - N. Taps":
                    if (FIR_txtF1.getText().isBlank() || FIR_txtF2.getText().isBlank()) return false;
                    if (FIR_txtNTaps.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "Band stop - Ripple":
                    if (FIR_txtF1.getText().isBlank() || FIR_txtF2.getText().isBlank()) return false;
                    if (FIR_txtRipple.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
                case "Band stop - NTaps":
                    if (FIR_txtF1.getText().isBlank() || FIR_txtF2.getText().isBlank()) return false;
                    if (FIR_txtNTaps.getText().isBlank()) return false;
                    if (FIR_txtWidth.getText().isBlank()) return false;
                    break;
 
                default:
                    return false;
            }
            //
            return true;
        } catch (Exception ex){
            return false;
        }
    }    
//------------------------------------------------------------------------------
    @FXML
    private void IIR_cmbFilterType_change(ActionEvent event) {
        if (IIR_cmbFilterType.getValue()!=null)
            IIR_cmbFilterCut.setDisable(false);
        else
            IIR_cmbFilterCut.setDisable(true);
    }
//------------------------------------------------------------------------------
    @FXML
    private void IIR_cmbFilterCut_change(ActionEvent event) {
        switch(IIR_cmbFilterCut.getValue()){
            case "Low pass":
                IIR_txtF1.setDisable(true);
                IIR_txtF2.setDisable(false);
                IIR_btnOk.setDisable(false);
                break;
            case "High pass":
                IIR_txtF1.setDisable(false);
                IIR_txtF2.setDisable(true);
                IIR_btnOk.setDisable(false);
                break;
            case "Band pass":
                IIR_txtF1.setDisable(false);
                IIR_txtF2.setDisable(false);
                IIR_btnOk.setDisable(false);
                break;
            case "Band stop":
                IIR_txtF1.setDisable(false);
                IIR_txtF2.setDisable(false);
                IIR_btnOk.setDisable(false);
                break;    
            default:
                IIR_txtF1.setDisable(true);
                IIR_txtF2.setDisable(true);
                IIR_btnOk.setDisable(true);
                break;
        }
    }
//------------------------------------------------------------------------------    
    /**
     * @return the ID_waves_controller
     */
    public int getID_waves_controller() {
        return ID_waves_controller;
    }
//------------------------------------------------------------------------------    
    /**
     * @param ID_waves_controller the ID_waves_controller to set
     */
    public void setID_waves_controller(int ID_waves_controller) {
        this.ID_waves_controller = ID_waves_controller;
    }
//------------------------------------------------------------------------------    
    @FXML
    private void IIR_btnCancel_Click(ActionEvent event) {
        App.G.WavesControllers.get(ID_waves_controller).Restore_TERNA_UndoLastFilter();
        Range xRange = ((XYPlot)App.G.WavesControllers.get(ID_waves_controller).getPlot_combinato_TERNA().getSubplots().get(0)).getDomainAxis().getRange();
        
        App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA(xRange, false, false);
        App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA_new(xRange, false, false);
        
        IIR_btnCancel.setDisable(true);
    }
//------------------------------------------------------------------------------    
    @FXML
    private void FIR_cmbFilterType_change(ActionEvent event) {
        if (FIR_cmbFilterType.getValue()!=null)
            FIR_cmbFilterCut.setDisable(false);
        else
            FIR_cmbFilterCut.setDisable(true);
    }
//------------------------------------------------------------------------------    
    @FXML
    private void FIR_cmbFilterCut_change(ActionEvent event) {
        switch(FIR_cmbFilterCut.getValue()){
            case "Low pass - Ripple":
                FIR_txtF1.setDisable(true);
                FIR_txtF2.setDisable(false);
                FIR_txtRipple.setDisable(false);
                FIR_txtNTaps.setDisable(true);
                FIR_btnOk.setDisable(false);
                break;
            case "Low pass - N. Taps":
                FIR_txtF1.setDisable(true);
                FIR_txtF2.setDisable(false);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(true);
                FIR_txtNTaps.setDisable(false);
                break;
            case "High pass - Ripple":
                FIR_txtF1.setDisable(false);
                FIR_txtF2.setDisable(true);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(false);
                FIR_txtNTaps.setDisable(true);
                break;
            case "High pass - N. Taps":
                FIR_txtF1.setDisable(false);
                FIR_txtF2.setDisable(true);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(true);
                FIR_txtNTaps.setDisable(false);
                break;
            case "Band pass - Ripple":
                FIR_txtF1.setDisable(false);
                FIR_txtF2.setDisable(false);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(false);
                FIR_txtNTaps.setDisable(true);
                break;
            case "Band pass - N. Taps":
                FIR_txtF1.setDisable(false);
                FIR_txtF2.setDisable(false);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(true);
                FIR_txtNTaps.setDisable(false);
                break;    
            case "Band stop - Ripple":
                FIR_txtF1.setDisable(false);
                FIR_txtF2.setDisable(false);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(false);
                FIR_txtNTaps.setDisable(true);
                break; 
            case "Band stop - N. Taps":
                FIR_txtF1.setDisable(false);
                FIR_txtF2.setDisable(false);
                FIR_btnOk.setDisable(false);
                FIR_txtRipple.setDisable(true);
                FIR_txtNTaps.setDisable(false);
                break;
            default:
                FIR_txtF1.setDisable(true);
                FIR_txtF2.setDisable(true);
                FIR_btnOk.setDisable(true);
                FIR_txtRipple.setDisable(true);
                FIR_txtNTaps.setDisable(true);
                break;
        }
    }
//------------------------------------------------------------------------------    
    @FXML
    private void FIR_btnOk_Click(ActionEvent event) {
        try {         
            if (FIR_InputValidation()){
                String fType=FIR_cmbFilterType.getValue();
                if (!E.getActiveTerna().getWaves().isEmpty()) {         
                    for (int idWave=0; idWave < E.getActiveTerna().getWaves().size(); idWave++) {
                        E.getActiveTerna().getWaves().get(idWave).Backup_Samples();
                        E.getActiveTerna().getWaves().get(idWave).setFilters(E.getActiveTerna().getWaves().get(idWave).getFilters()+1);
                        switch(FIR_cmbFilterCut.getValue()){
                            case "Low pass - Ripple":
                                double[] cut = {Double.valueOf(FIR_txtF2.getText())};
                                dsp_tool.FIR_psambit_ripple(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.LOWPASS,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cut, 
                                        Float.valueOf(FIR_txtRipple.getText()));
                                break;
                            case "Low pass - N. Taps":
                                double[] cut2 = {Double.valueOf(FIR_txtF2.getText())};
                                dsp_tool.FIR_psambit_taps(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.LOWPASS,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cut2, 
                                        Integer.valueOf(FIR_txtNTaps.getText()));
                                break;
                            case "High pass - Ripple":
                                double[] cut3 = {Double.valueOf(FIR_txtF1.getText())};
                                dsp_tool.FIR_psambit_ripple(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.HIGHPASS,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cut3, 
                                        Float.valueOf(FIR_txtRipple.getText()));
                                break;
                            case "High pass - N. Taps":
                                double[] cut4 = {Double.valueOf(FIR_txtF1.getText())};
                                dsp_tool.FIR_psambit_taps(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.HIGHPASS,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cut4, 
                                        Integer.valueOf(FIR_txtNTaps.getText()));
                                break;
                            case "Band pass - Ripple":                       
                                double[] cutband = {Double.valueOf(FIR_txtF1.getText()), Double.valueOf(FIR_txtF2.getText())};
                                dsp_tool.FIR_psambit_ripple(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.BANDPASS,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cutband, 
                                        Float.valueOf(FIR_txtRipple.getText()));    
                                break;
                            case "Band pass - N. Taps":                       
                                double[] cutband2 = {Double.valueOf(FIR_txtF1.getText()), Double.valueOf(FIR_txtF2.getText())};
                                dsp_tool.FIR_psambit_taps(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.BANDPASS,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cutband2, 
                                        Integer.valueOf(FIR_txtNTaps.getText()));  
                                break;
                            case "Band stop - Ripple":                       
                                double[] cutbandstop = {Double.valueOf(FIR_txtF1.getText()), Double.valueOf(FIR_txtF2.getText())};
                                dsp_tool.FIR_psambit_ripple(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.BANDSTOP,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cutbandstop, 
                                        Float.valueOf(FIR_txtRipple.getText()));    
                                break;
                            case "Band stop - N. Taps":                       
                                double[] cutbandstop2 = {Double.valueOf(FIR_txtF1.getText()), Double.valueOf(FIR_txtF2.getText())};
                                dsp_tool.FIR_psambit_taps(FIR_cmbFilterType.getValue(), FIRWin1.FIRfilterType.BANDSTOP,E.getActiveTerna().getWaves().get(idWave).getY(), 
                                        E.getActiveTerna().getWaves().get(idWave).getSamplingRate(), 
                                        Double.valueOf(FIR_txtWidth.getText()),
                                         cutbandstop2, 
                                        Integer.valueOf(FIR_txtNTaps.getText()));  
                                break;
                        }
                    }
                } 
                //
                Range xRange = ((XYPlot)App.G.WavesControllers.get(ID_waves_controller).getPlot_combinato_TERNA().getSubplots().get(0)).getDomainAxis().getRange();
                App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA(xRange, false, false);
                App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA_new(xRange, false, false);
          
                FIR_btnCancel.setDisable(false);
            } else {
                sitDialog.ShowErrorMessage("Please check your input", null); 
            }
        } catch (Exception ex){
        }
    }
//------------------------------------------------------------------------------    
    @FXML
    private void FIR_btnCancel_Click(ActionEvent event) {
        App.G.WavesControllers.get(ID_waves_controller).Restore_TERNA_UndoLastFilter();
        Range xRange = ((XYPlot)App.G.WavesControllers.get(ID_waves_controller).getPlot_combinato_TERNA().getSubplots().get(0)).getDomainAxis().getRange();
        
        App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA(xRange, false, false);
        App.G.WavesControllers.get(ID_waves_controller).DisplayWaves_TERNA_new(xRange, false, false);
        
        FIR_btnCancel.setDisable(true);
    }
}
