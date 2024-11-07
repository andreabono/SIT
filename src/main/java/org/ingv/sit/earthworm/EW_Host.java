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
package org.ingv.sit.earthworm;

//import edu.sc.seis.seisFile.SyncFileWriter;
//import edu.sc.seis.seisFile.syncFile.SyncLine;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.logging.Logger;
import edu.sc.seis.seisFile.earthworm.EarthwormEscapeOutputStream;
import edu.sc.seis.seisFile.earthworm.EarthwormHeartbeater;
import java.util.logging.Logger;

/**
 *
 * @author andreabono
 */
public class EW_Host {
    private String hostname;                        
    private ArrayList<EW_Wave_Server_Client> wave_server_client_list;
    //private List<MenuItem> ws_menu=null;
    
    private Socket s;
    int port =19000;
    String heartbeatMessage = "heartbeat";
    int heartbeatSeconds = 10;
    int institution = 2;
    int module = 99;
    
    BufferedInputStream in;
    EarthwormEscapeOutputStream outStream;
    EarthwormHeartbeater heartbeater;

//--------------------------------------------------------------------------------
    public EW_Host(String host, ArrayList<Integer> ports_list) {
        
       this.hostname = host;
       if (!this.Init_Wave_Servers(ports_list)) {  
           Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.SEVERE, "Error while reading wave servers menus");
       }
       
//       if (this.OpenConnection()) {
//           this.ListenToMessages();
//       }
       
    }
    
//--------------------------------------------------------------------------------         
    public boolean OpenConnection(){
        try {
//            Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.INFO, 
//                    "Opening connection with Earthworm host " + hostname + " on port " + port);
            
            s = new Socket(hostname, port);
            in = new BufferedInputStream(s.getInputStream());
            outStream = new EarthwormEscapeOutputStream(new BufferedOutputStream(s.getOutputStream()));
            EarthwormHeartbeater heartbeater = new EarthwormHeartbeater(null, heartbeatSeconds, heartbeatMessage, institution, module);

            heartbeater.setOutStream(outStream);
            heartbeater.heartbeat();
       
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            //Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.SEVERE, ex.getMessage());
            return false;
        }
    }
//--------------------------------------------------------------------------------        
    public boolean CloseConnection(){
        try {
            if (heartbeater != null)  heartbeater.cancel();
            
            if (s!=null) s.close();
          
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
//--------------------------------------------------------------------------------
public void ListenToMessages(){
    HashMap<String, Double> lastStartTimeMap = new HashMap<String, Double>();
    HashMap<String, Double> lastEndTimeMap = new HashMap<String, Double>();
    //
    String syncfile = "syncro.txt";
    
    //Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.INFO, "Listening to Earthworm messages...");
    try {     
//        SyncFileWriter syncWriter = null;
//        if (syncfile != null) {
//            syncWriter = new SyncFileWriter("ewimport", syncfile);
//        }
//            
//        EarthwormImport ewImport = new EarthwormImport(in);
//            while(true) {
//                EarthwormMessage message;
//                try {
//                    message = ewImport.nextMessage();
//                    if (message.getMessageType() == EarthwormMessage.MESSAGE_TYPE_TRACEBUF2) {
//                        TraceBuf2 traceBuf2 = new TraceBuf2(message.getData());
//                        String key = traceBuf2.formatNSLCCodes();
//                        if (lastEndTimeMap.containsKey(key)) {
//                            if (Math.abs(traceBuf2.getStartTime() - lastEndTimeMap.get(key)) > 1/traceBuf2.getSampleRate()) {
//                                //System.out.println("GAP: "+(traceBuf2.getStartTime() - lastEndTimeMap.get(key)));
//                                if (syncWriter != null) {
//                                    syncWriter.appendLine(new SyncLine(traceBuf2.getNetwork(), 
//                                       traceBuf2.getStation(),
//                                       traceBuf2.getLocId(),
//                                       traceBuf2.getChannel(),
//                                       new Date(Math.round(1000*lastStartTimeMap.get(key))),
//                                       new Date(Math.round(1000*lastEndTimeMap.get(key))),
//                                       0f, 0f));
//                                    lastStartTimeMap.put(key, traceBuf2.getStartTime());
//                                }
//                            }
//                        } else {
//                            lastStartTimeMap.put(key, traceBuf2.getStartTime());
//                        }
//                        lastEndTimeMap.put(key, traceBuf2.getPredictedNextStartTime());
//                        //System.out.println("TraceBuf: "+traceBuf2);
//                    } else if (message.getMessageType() == EarthwormMessage.MESSAGE_TYPE_HEARTBEAT) {
//                        Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.INFO, "Heartbeat received: " + new String(message.getData()));
//                    }
//                    Thread.sleep(1);
//                } catch(IOException e) {
//                    // remote closed connection?
//                    Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.INFO, "Remote conection closed");
//                    heartbeater.setOutStream(null);
//                    outStream.close();
//                    break;
//                }
//            }
//            if (syncWriter != null) {
//                for (String key : lastStartTimeMap.keySet()) {
//                    String[] chanCodes = key.split("\\.");
//                    syncWriter.appendLine(new SyncLine(chanCodes[0], 
//                                                       chanCodes[1],
//                                                       chanCodes[2],
//                                                       chanCodes[3],
//                                                       new Date(Math.round(1000*lastStartTimeMap.get(key))),
//                                                       new Date(Math.round(1000*lastEndTimeMap.get(key))),
//                                                       0f, 0f));
//                }
//                syncWriter.close();
//            }
    } catch (Exception ex) {
    }
}    
//--------------------------------------------------------------------------------     
    private boolean Init_Wave_Servers(ArrayList<Integer> ports_list) {
        try {
            setWave_server_client_list(new ArrayList<>());
            EW_Wave_Server_Client tmp;
            for (int i=0; i<ports_list.size(); i++) {
//                String[] params = {"-h", this.getHostname(), "-p", ports_list.get(i).toString(), "--menu"};
//                Logger.getLogger("org.ingv.sit ").log(java.util.logging.Level.INFO, 
//                        "Creating an EW_Wave_Server_Client with params -h=" + 
//                        this.getHostname() + " e -p = " + ports_list.get(i).toString() + " --menu");
                
                //
                //tmp = new EW_Wave_Server_Client(params, i); 
                tmp = new EW_Wave_Server_Client(this.getHostname(), ports_list.get(i), 12, true, true,  i);  
                              
                tmp.readData(); // this reads the menu
                wave_server_client_list.add(tmp);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
//--------------------------------------------------------------------------------    
    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }
//--------------------------------------------------------------------------------
    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
//--------------------------------------------------------------------------------
    /**
     * @return the wave_server_client_list
     */
    public ArrayList<EW_Wave_Server_Client> getWave_server_client_list() {
        return wave_server_client_list;
    }
//--------------------------------------------------------------------------------
    /**
     * @param wave_server_client_list the wave_server_client_list to set
     */
    public void setWave_server_client_list(ArrayList<EW_Wave_Server_Client> wave_server_client_list) {
        this.wave_server_client_list = wave_server_client_list;
    }
//--------------------------------------------------------------------------------    
}
