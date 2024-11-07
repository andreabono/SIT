/**
 *
 *
 * Andrea Bono 
 * I.N.G.V. Isituto Nazionale di Geofisica e Vulcanologia
 * C.N.T. Centro Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 * andrea.bono@ingv.it
 *
 */

package org.ingv.sit.utils;


//import org.ingv.spx.forms.frmMain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import org.apache.commons.io.FileUtils;
import org.ingv.sit.LocationResult2Controller;
import org.ingv.sit.WavesFormController;
import org.ingv.sit.datamodel.SeismicNetwork;
import org.ingv.sit.security.User;
//import org.ingv.sit.MapFormController;
//import org.ingv.sit.datamodel.SeismicNetwork;
//
public class Globals {    
    public SysProperties SystemProperties;
    public SeismicNetwork SeismicNet;
    public String WAVES_BASKET_PATH; 
    public Options options=new Options();   
    public ArrayList<WavesFormController> WavesControllers;
    public ArrayList<LocationResult2Controller> LocationControllers;
    private FXMLLoader mainFormLoader;
    public  String sitHostname, sitUsername;
    public boolean show_magnitudebyazimuth_chart;
    public User User;
    
//------------------------------------------------------------------------------ 
    public Globals() {
        User = new User();
        SystemProperties = new SysProperties();
        WAVES_BASKET_PATH  = "waves" + SystemProperties.file_Separator; 
        
        try {
            sitHostname=InetAddress.getLocalHost().getHostName();
        } catch (Exception ex){
            sitHostname="Unknown SIT host";
        }
        try {
            sitUsername=System.getProperty("user.name");
        } catch (Exception ex){
            sitUsername="Unknown SIT user";
        }
    }
//------------------------------------------------------------------------------
    public BigDecimal round(Double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
//------------------------------------------------------------------------------
    public boolean IsTime(String inStr) {
        try {
            if (inStr.trim().length()!=8) return false;

            if ((!IsNumeric(inStr.substring(0,2))) ||
            (!IsNumeric(inStr.substring(3,2))) ||
            (!IsNumeric(inStr.substring(6,2)))) return false;

            if ((!inStr.substring(2,1).equals(":")) ||
            (!inStr.substring(5,1).equals(":")))return false;
//
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------
    public boolean IsNumeric(String inStr) {
        try {
            if (inStr==null) return false;
            if (inStr.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
                return true;
            } else {
                return false;
            }  
        }
        catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------
    public boolean IsDate(String inStr) {
        try {
            if (inStr.trim().length()!=10) return false;
            if (!IsNumeric(inStr.substring(0,4))) return false;
            if (!IsNumeric(inStr.substring(5,7))) return false;
            if (!IsNumeric(inStr.substring(8,10))) return false;
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
//------------------------------------------------------------------------------
    public boolean FileExists(String filename) {
        File tmp = new File( filename );
        if( tmp.exists() ){
            return true;
        } else {
            return false;
        }
    }
//------------------------------------------------------------------------------
    public String ChangeLen(String inStr, int ToLen, String PadChar, boolean AddInHead) {
        try {
            inStr = inStr.trim();
            int nMissingChars= ToLen - inStr.length();
            int i;
            if (nMissingChars < 0) {
                return inStr.substring(0, ToLen);
            } else {
                for (i=0; i<nMissingChars; i++) {
                    if (AddInHead) {
                        inStr = PadChar + inStr;
                    } else {
                        inStr = inStr+PadChar;
                    }
                }
            }
//
            return inStr;
        } catch (Exception ex) {
            return "";
        }
    }

//------------------------------------------------------------------------------    
    public String zero_for_null (String s)
    {
        if (s==null) {
          return "0";  
        } 
        else  
        return s;
    }
//------------------------------------------------------------------------------     
    private static void renderSplashFrame(Graphics2D g, int frame) {
        final String[] comps = {"Classes", "Settings", "Environment",  "Options", "Seismic events"};
        g.setComposite(AlphaComposite.Clear);
        //g.fillRect(120,140,200,40);
        g.setPaintMode();
        g.setColor(Color.BLUE);
        g.drawString("Loading "+comps[frame]+"...", 10, 87);
    }
//------------------------------------------------------------------------------    
    /**
     *
     * @param i the level of the splash screen info
     */
    public void UpdateSplash(int i) {
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            //Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.SEVERE,"SplashScreen.getSplashScreen() returned null");
            return;
        }
        Graphics2D g = splash.createGraphics();
        if (g == null) {
            //Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.SEVERE,"g is null");
            return;
        }
            renderSplashFrame(g, i);
            splash.update();
    }  
//------------------------------------------------------------------------------
    public void MakeDirectory(String path) {
        try {
            File folder = new File(path);
            if (!folder.exists())
                new File(path).mkdir();
        } catch (Exception ex) {
            //
        } 
    }  
//------------------------------------------------------------------------------    
    public void ClearDirectory(String path) {
        try {
            File folder = new File(path);
            if (folder.exists())
                FileUtils.deleteDirectory(folder);
        } catch (Exception ex) {
            //
        } finally {
            new File(path).mkdir();
        }
    }  
//------------------------------------------------------------------------------
    public boolean IsDirEmpty(String path){  
        File file = new File(path);
        
        if(file.isDirectory() && file.list().length == 0) {
            return true;
        } else {
            return false;
        }
    }
//------------------------------------------------------------------------------       
    public boolean StillReadingStations(){
        try {          
            for (int i=0; i< options.getDatasources_FDSN().size(); i++){
                if (options.getDatasources_FDSN().get(i).isStill_trying_to_read()) return true;
            }    
            return false; 
        } catch (Exception ex){
            return false;
        }
    }

    /**
     * @return the mainFormLoader
     */
    public FXMLLoader getMainFormLoader() {
        return mainFormLoader;
    }

    /**
     * @param mainFormLoader the mainFormLoader to set
     */
    public void setMainFormLoader(FXMLLoader mainFormLoader) {
        this.mainFormLoader = mainFormLoader;
    }
    
    
    
    
    public void ActivateStationByResidual(Long eventId, String StationCode, String PhaseCode){
        
        try{
            WavesFormController frm = FindWavesControllerByEventId(eventId);
            if (frm!=null){
                frm.show_wave_by_station_click(StationCode);
            }
        } catch (Exception ex){
        }
    }
    
    private WavesFormController FindWavesControllerByEventId(Long eventId){
        //WavesFormController frm;
        try{
            if (WavesControllers==null) return null;
            if (WavesControllers.isEmpty()) return null;
            for (int i=0; i<this.WavesControllers.size(); i++){
                
                
                if (Objects.equals(WavesControllers.get(i).getMyEvent().getInnerObjectEvent().getId(), eventId)){
                    return WavesControllers.get(i);
                }
            }
            return null;
        } catch (Exception ex){
            return null;
        }
    }
    
    public LocationResult2Controller FindLocationControllerByOriginId(Long originId){
        //WavesFormController frm;
        try{
            if (LocationControllers==null) return null;
            if (LocationControllers.isEmpty()) return null;
            if (LocationControllers.size()==1) return LocationControllers.get(0);
            for (int i=0; i<this.LocationControllers.size(); i++){
                if (LocationControllers.get(i).getLocalOriginID()==originId){
                    return LocationControllers.get(i);
                }
            }
            return null;
        } catch (Exception ex){
            return null;
        }
    }
}
