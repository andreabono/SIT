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

//import com.sun.javafx.application.LauncherImpl;
import com.sun.tools.javac.Main;
import java.awt.RenderingHints;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ingv.sit.utils.Globals;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.geotools.util.factory.Hints;
import org.ingv.sit.datamodel.SeismicNetwork;
import org.ingv.sit.utils.sitDialog;
import org.slf4j.LoggerFactory;

//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class App extends Application { //implements WebApplicationInitializer {
    public static Globals G;
    private static Scene scene;
    public static SimpleDateFormat dateFormatGmt;
    
    public static org.slf4j.Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
     
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.setConfigLocation("com.example.config");
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
//        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/pfxauth/");
//    }
    
   

//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.setPort(8585);
//        factory.setContextPath("/pfxauth");
//        return factory;
//    }
//--------------------------------------------------------------------------------
    @Override
    public void init() throws Exception {
        System.setProperty("user.timezone", "GMT");
        
        System.setProperty("org.geotools.referencing.forceXY", "true");
//
        dateFormatGmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));  
        Hints.putSystemDefault(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);
//       
//      ------------------------------------
//      FILE SYSTEM CHECKS
//      ------------------------------------
        if (!this.FileSystemCheck()){
            return;         
        }
        
//       
//      ------------------------------------
//      READ OPTIONS AND SET-UP SOME GLOBALS
//      ------------------------------------
        G=new Globals();
        if (!G.options.load()) return;
        //Thread.sleep(100);
               
//      ------------------------------------
//      READ AND SET-UP THE SEISMIC NETWORK
//      ------------------------------------
        G.SeismicNet = new SeismicNetwork();        
        if (!G.SeismicNet.read(false)) {
            sitDialog.ShowInformationMessage("Error while reading seismic network!", null);
        }  
        
        while (App.G.StillReadingStations()) {
            Thread.sleep(1);
        }
       
        G.SeismicNet.RemoveDuplicates();  
    }
//------------------------------------------------------------------------------
    @Override
    public void start(Stage stage) throws IOException {   
        try {                 
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("SplashScreen.fxml"));
                       
            Parent root1 = (Parent) fxmlLoader1.load();
               
             
            Stage splashStage = new Stage();
            splashStage.initStyle(StageStyle.UNDECORATED); // Nasconde la barra del titolo
            Scene splashScene = new Scene(root1, 560, 319);
            splashStage.setScene(splashScene);
            splashStage.show();

            // Simula un caricamento lungo con un task in background
            Task<Void> longTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Thread.sleep(2000); // Simula 2 secondi di caricamento
                    return null;
                }
            };

            longTask.setOnSucceeded(event -> {
                // Chiudi lo splash screen e mostra la finestra principale
                splashStage.close();
                //showMainStage(primaryStage);
                showMainStage();
            });

            // Avvia il task
            new Thread(longTask).start();
            
            
            

        } catch (Exception ex) {
            Logger.getLogger("org.ingv.sit").log(java.util.logging.Level.SEVERE,  ex.getMessage());
        }  
    }
//------------------------------------------------------------------------------       
    private void showMainStage(){
        Image image = new Image("images/pfx_orange.png");
        Stage stage = new Stage();
        App.G.setMainFormLoader(new FXMLLoader(App.class.getResource("MapForm.fxml")));
        
        Parent root=null;
        try {
            root = (Parent) App.G.getMainFormLoader().load();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (root!=null){
            scene = new Scene(root); 
            stage.setScene(scene); 
            stage.setTitle("SIT");

            stage.setOnHidden(e -> Platform.exit());

            try {
                final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
                final URL imageResource = Main.class.getClassLoader().getResource("images/pfx_orange.png");
                final java.awt.Image image2 = defaultToolkit.getImage(imageResource);

                //this is new since JDK 9
                final Taskbar taskbar = Taskbar.getTaskbar();
                //set icon for mac os (and other systems which do support this method)
                taskbar.setIconImage(image2);
            } catch (final UnsupportedOperationException e) {
                System.out.println("The os does not support: 'taskbar.setIconImage'");
            } catch (final SecurityException e) {
                System.out.println("There was a security exception for: 'taskbar.setIconImage'");
            }

            stage.setMaximized(true);

            stage.getIcons().add(image); 

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
              @Override public void handle(WindowEvent ev) {    
                if (sitDialog.ShowConfirmationMessage("Do you really want to quit SIT?", "All unsaved data will be lost!!", stage)==ButtonType.OK) 
                    Platform.exit();
                else
                    ev.consume();
              }
            });

            stage.setOnShown(event -> {
                Platform.runLater(() -> {
                    //((MapFormController)App.G.getMainFormLoader().getController()).SearchAndShowLocations();
                    ((MapFormController)App.G.getMainFormLoader().getController()).FinalizeStartup();
                    if (((MapFormController)App.G.getMainFormLoader().getController()).isAnyDatasourcesAvailable())
                        ((MapFormController)App.G.getMainFormLoader().getController()).SearchAndShowLocations();
                    else
                        System.exit(0); // TODO: User should edit configuration via sit interface
                });
            });          

            ((MapFormController)App.G.getMainFormLoader().getController()).setPrimaryStage(stage);

            stage.show();
        }   
    }
//------------------------------------------------------------------------------
    public static void main(String[] args) {         
        launch(args);
    }
//------------------------------------------------------------------------------  
    private boolean FileSystemCheck(){
        File options_f, hypo_settings_f, hypo_MODEL_settings_f, waves_folder, 
                net_folder, logs_folder, waves_full_folder;
        try{
            // Settings file
            options_f=new File("configuration/sit_properties.xml");
            if (!options_f.exists()){
                sitDialog.ShowConfirmationMessage("A","B", null);
                sitDialog.ShowErrorMessage("Options file is missing:\n" + options_f.getPath(), null);
                return false;
            } 
                     
            // Hypoinverse settings file 1
            hypo_settings_f=new File("configuration/Hypo2000_configuration.txt");
            if (!hypo_settings_f.exists()){
                sitDialog.ShowErrorMessage("Hypoinverse configuration file is missing:\n" + hypo_settings_f.getPath(),null);
                return false;
            } 
            // Hypoinverse settings file 2
            hypo_MODEL_settings_f=new File("configuration/Hypo2000_MODEL_configuration.txt");
            if (!hypo_MODEL_settings_f.exists()){
                sitDialog.ShowErrorMessage("Hypoinverse MODEL configuration file is missing:\n" + hypo_MODEL_settings_f.getPath(), null);
                return false;
            } 
                     
            // Waves folder
            waves_folder=new File("waves");
            if (!waves_folder.exists()){
                waves_folder.mkdir();
            } 
            
            waves_full_folder=new File("waves" + File.separator + System.getProperty("user.name") + File.separator );
            if (!waves_full_folder.exists()){
                waves_full_folder.mkdir();
            } 
            
            // Network folder
            net_folder=new File("networks");
            if (!net_folder.exists()){
                net_folder.mkdir();
            } 
            
            // Logs folder
            logs_folder=new File("logs");
            if (!logs_folder.exists()){
                logs_folder.mkdir();
            } 
            
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            options_f=null;
            hypo_settings_f=null;
            hypo_MODEL_settings_f=null;
            waves_folder=null; 
            net_folder=null;
            logs_folder=null;
            waves_full_folder=null;
        }
    }
//------------------------------------------------------------------------------    
    @Override
  public void stop() { 
    logger.info("{} is closing" , MethodHandles.lookup().lookupClass());
    logger.info("{} is releasing global variables..." , MethodHandles.lookup().lookupClass());
        
    G=null;
    System.gc();
        
    logger.info("{} shutdown completed!!" , MethodHandles.lookup().lookupClass());
       
    System.exit(0);
  }
}