/**
 *
 *
 * Andrea Bono
 * I.N.G.V. Isituto Nazionale di Geofisica e Vulcanologia
 * C.N.T. Centro Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 */
package org.ingv.sit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import javafx.scene.control.Alert;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.ingv.sit.App;
import org.ingv.sit.datamodel.DataSource;
import org.ingv.sit.datamodel.DataSource.dsType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Options {
    private final Properties prop = new Properties();

    public ArrayList<map_layers_element> map_layers;
    
    private String map_settings_file="configuration/maplayers.xml";

    private final String datasources_FDSN_filename = "configuration/datasources_FDSN.xml";
    private final String datasources_EW_filename = "configuration/datasources_EW.xml";
    private final String datasources_SL_filename = "configuration/datasources_SL.xml";
    private final String datasources_CARAVEL_filename = "configuration/datasources_CARAVEL.xml";
    private final String datasources_LOCALHOST_filename = "configuration/datasources_LOCALHOST.xml";
    
    private ArrayList<DataSource> datasources_FDSN;
    private ArrayList<DataSource> datasources_EW;
    private ArrayList<DataSource> datasources_SL;
    private ArrayList<DataSource> datasources_CARAVEL;
    private ArrayList<DataSource> datasources_LOCALHOST;

    
    public ArrayList<String> list_of_searched_environments;
    public ArrayList<String> list_of_searched_localspaces;  
    public ArrayList<String> list_of_searched_versions;  
   
   public boolean isCaravelAvailable(){
       try {
           if (datasources_CARAVEL==null) return false;
           if (datasources_CARAVEL.isEmpty()) return false;
           
           for (int i=0; i<datasources_CARAVEL.size(); i++ ){
               if (datasources_CARAVEL.get(i).isUsed()) return true;
           }
           
           return false;
       } catch (Exception ex){
           return false;
       }
   }
//------------------------------------------------------------------------------    
    public final boolean load() throws InvalidPropertiesFormatException{
        FileInputStream fis=null;
        try {
            File options_f=new File("configuration/pfx_properties.xml");
            if (!options_f.exists()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Could not locate file");
                    alert.setContentText("Options file is missing:\n" + options_f.getPath());

                    alert.showAndWait();
                    return false;
            } else {
                fis = new FileInputStream("configuration/pfx_properties.xml");
                prop.loadFromXML(fis);
                
                if ((App.G.FileExists(datasources_FDSN_filename)) && !readFDSNDatasources(datasources_FDSN_filename)){
                    App.logger.info("Warning: NO FDSN DATASOURCES FILE AVAILABLE. Check configuration directory...");
                }
                if ((App.G.FileExists(datasources_EW_filename)) && !readEWDatasources(datasources_EW_filename)){
                    App.logger.info("Warning: NO EW DATASOURCES FILE AVAILABLE. Check configuration directory...");
                }
                if ((App.G.FileExists(datasources_SL_filename)) && !readSLDatasources(datasources_SL_filename)){
                    App.logger.info("Warning: NO SEEDLINK DATASOURCES AVAILABLE. Check configuration directory...");
                }
                if ((App.G.FileExists(datasources_CARAVEL_filename)) && !readCARAVELDatasources(datasources_CARAVEL_filename)){
                    App.logger.info("Warning: NO CARAVEL DATASOURCES AVAILABLE. Check configuration directory...");
                }
                if ((App.G.FileExists(datasources_LOCALHOST_filename)) && !readLOCALHOSTDatasources(datasources_LOCALHOST_filename)){
                    App.logger.info("Warning: NO LOCALHOST DATASOURCES AVAILABLE. Check configuration directory...");
                }
                
               if (App.G.FileExists(map_settings_file)){
                    map_layers = readMapSettingsFromFile(map_settings_file);
                }
                
                list_of_searched_environments = ReadList_of_searched_environments(); 
                list_of_searched_localspaces = ReadList_of_searched_localspaces(); 
                list_of_searched_versions = ReadList_of_searched_versions(); 
                
                return true;
            }
        } catch (IOException ex) {
            //Logger.getLogger(Options.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try { if (fis != null) fis.close(); } catch(IOException e) {}}
    } 
//------------------------------------------------------------------------------
    public ArrayList<map_layers_element> readMapSettingsFromFile(String fname) {
        try {
            boolean layer_used;
            String layer_type, layer_filename, layer_title, layer_linecolor, layer_fillcolor;
            double layer_opacity;
            ArrayList<map_layers_element> res = new ArrayList();
            
            File fXmlFile = new File(fname);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
           
            NodeList nodeList = doc.getElementsByTagName("node");

            for (int parameter = 0; parameter < nodeList.getLength(); parameter++) {
                map_layers_element inNode = new map_layers_element();
                Node node = nodeList.item(parameter);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    //
                    layer_used = Boolean.parseBoolean(eElement.getElementsByTagName("used").item(0).getTextContent());
                    layer_type = eElement.getElementsByTagName("type").item(0).getTextContent();
                    layer_filename = eElement.getElementsByTagName("filename").item(0).getTextContent();
                    layer_title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    layer_linecolor= eElement.getElementsByTagName("linecolor").item(0).getTextContent();
                    layer_fillcolor = eElement.getElementsByTagName("fillcolor").item(0).getTextContent();
                    if (eElement.getElementsByTagName("opacity").item(0).getTextContent().trim().length()!=0)
                        layer_opacity= Double.valueOf(eElement.getElementsByTagName("opacity").item(0).getTextContent());
                    else
                        layer_opacity = Double.valueOf("1");
                    //
                    inNode.setUsed(layer_used);
                    inNode.setType(layer_type);
                    inNode.setFilename(layer_filename);
                    inNode.setTitle(layer_title);
                    inNode.setLinecolor(layer_linecolor);
                    inNode.setFillcolor(layer_fillcolor);
                    inNode.setOpacity(layer_opacity);
                    //
                    res.add(inNode);          
                }
            }
         
            if (res.isEmpty()) 
                return null;
            else
                return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
//------------------------------------------------------------------------------
    public boolean readFDSNDatasources(String xml_file){
        try {
            if (datasources_FDSN==null){
                datasources_FDSN=new ArrayList<>();
            }else{
                datasources_FDSN.clear();
            }
            String datasourcetype;
                       
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("datasource");
            
            for (int parameter = 0; parameter < nodeList.getLength(); parameter++) {
                //datasourcetype="";      
                DataSource inDatasource = new DataSource();
                Node node = nodeList.item(parameter);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    datasourcetype = eElement.getElementsByTagName("datasourcetype").item(0).getTextContent();
                    inDatasource.setUsed(Boolean.valueOf(eElement.getElementsByTagName("used").item(0).getTextContent()));
                    inDatasource.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    inDatasource.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                    if ((eElement.getElementsByTagName("httpport").item(0).getTextContent()!=null) && 
                            (!eElement.getElementsByTagName("httpport").item(0).getTextContent().isEmpty()))
                        inDatasource.setHttpport(Integer.valueOf(eElement.getElementsByTagName("httpport").item(0).getTextContent()));
                    else
                        inDatasource.setHttpport(80);
                    inDatasource.setStill_trying_to_read(false);
                                      
                    switch (datasourcetype){
                        case "FDSN":
                            inDatasource.setDatasourcetype(dsType.FDSN);
                            inDatasource.setHostname(eElement.getElementsByTagName("hostname").item(0).getTextContent());
                            datasources_FDSN.add(inDatasource);
                            break;
                        default:
                            App.logger.warn("Warning: FOUND UNKNOWN FDSN DATASOURCE TYPE in " + xml_file);
                            break;
                    }
//
                    inDatasource=null;
                }
            }
//         
            return true;
        } catch (Exception ex) {
            return false;
        }  
    }
//------------------------------------------------------------------------------
    public boolean readEWDatasources(String xml_file){
        try {
            if (datasources_EW==null){
                datasources_EW=new ArrayList<>();
            }else{
                datasources_EW.clear();
            }
            String datasourcetype;
                       
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("datasource");
            
            for (int parameter = 0; parameter < nodeList.getLength(); parameter++) {
                //datasourcetype="";      
                DataSource inDatasource = new DataSource();
                Node node = nodeList.item(parameter);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    datasourcetype = eElement.getElementsByTagName("datasourcetype").item(0).getTextContent();
                    inDatasource.setUsed(Boolean.valueOf(eElement.getElementsByTagName("used").item(0).getTextContent()));
                    inDatasource.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    inDatasource.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                    if ((eElement.getElementsByTagName("httpport").item(0).getTextContent()!=null) && 
                            (!eElement.getElementsByTagName("httpport").item(0).getTextContent().isEmpty()))
                        inDatasource.setHttpport(Integer.valueOf(eElement.getElementsByTagName("httpport").item(0).getTextContent()));
                    else
                        inDatasource.setHttpport(80);
                    inDatasource.setStill_trying_to_read(false);
                                      
                    switch (datasourcetype){
                        case "EARTHWORMWS":                         
                            String ports;
                            inDatasource.setDatasourcetype(dsType.EARTHWORMWS);
                            inDatasource.setHostname(eElement.getElementsByTagName("hostname").item(0).getTextContent());
                            ports = eElement.getElementsByTagName("waveserver_ports_list").item(0).getTextContent();
                            inDatasource.setPortslist(handle_ports_list(ports));
                            
                            datasources_EW.add(inDatasource);
                            break;
                        default:
                            App.logger.warn("Warning: FOUND UNKNOWN EW DATASOURCE TYPE in " + xml_file);
                            break;
                    }
//
                    inDatasource=null;
                }
            }        
            return true;
        } catch (Exception ex) {
            return false;
        } 
    }     
//------------------------------------------------------------------------------
    public boolean readCARAVELDatasources(String xml_file){
        try {
            if (datasources_CARAVEL==null){
                datasources_CARAVEL=new ArrayList<>();
            }else{
                datasources_CARAVEL.clear();
            }
            String datasourcetype;
                       
           File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("datasource");
            
            for (int parameter = 0; parameter < nodeList.getLength(); parameter++) {
                datasourcetype="";
       
                DataSource inDatasource = new DataSource();
                Node node = nodeList.item(parameter);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    datasourcetype = eElement.getElementsByTagName("datasourcetype").item(0).getTextContent();
                    inDatasource.setUsed(Boolean.valueOf(eElement.getElementsByTagName("used").item(0).getTextContent()));
                    inDatasource.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    inDatasource.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                                      
                    switch (datasourcetype){
                        case "INGV":
                            inDatasource.setDatasourcetype(dsType.INGV);
                            datasources_CARAVEL.add(inDatasource);
                            break;    
                        default:
                            App.logger.warn("Warning: FOUND UNKNOWN INGV DATASOURCE TYPE in " + xml_file);
                            break;
                    }
//
                    inDatasource=null;
                }
            }
//         
            return true;
        } catch (Exception ex) {
            return false;
        } 
    }  
//------------------------------------------------------------------------------
    public boolean readLOCALHOSTDatasources(String xml_file){
        try {
            if (getDatasources_LOCALHOST()==null){
                setDatasources_LOCALHOST(new ArrayList<>());
            }else{
                getDatasources_LOCALHOST().clear();
            }
            String datasourcetype;
                       
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("datasource");
            
            for (int parameter = 0; parameter < nodeList.getLength(); parameter++) {
                datasourcetype="";
       
                DataSource inDatasource = new DataSource();
                Node node = nodeList.item(parameter);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    datasourcetype = eElement.getElementsByTagName("datasourcetype").item(0).getTextContent();
                    inDatasource.setUsed(Boolean.valueOf(eElement.getElementsByTagName("used").item(0).getTextContent()));
                    inDatasource.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    inDatasource.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                                      
                    switch (datasourcetype){
                        case "LOCALHOST_EVENTS":
                            inDatasource.setDatasourcetype(dsType.LOCALHOST_EVENTS);
                            getDatasources_LOCALHOST().add(inDatasource);
                            break;    
                        case "LOCALHOST_NETWORKS":
                            inDatasource.setDatasourcetype(dsType.LOCALHOST_NETWORKS);
                            getDatasources_LOCALHOST().add(inDatasource);
                            break;    
                        default:
                            App.logger.warn("Warning: FOUND UNKNOWN LOCALHOST DATASOURCE TYPE in " + xml_file);
                            break;
                    }
//
                    inDatasource=null;
                }
            }
//         
            return true;
        } catch (Exception ex) {
            return false;
        } 
    }      
//------------------------------------------------------------------------------
    public boolean readSLDatasources(String xml_file){
        try {
            if (datasources_SL==null){
                datasources_SL=new ArrayList<>();
            }else{
                datasources_SL.clear();
            }
            String datasourcetype;
                       
            File fXmlFile = new File(xml_file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("datasource");
            
            for (int parameter = 0; parameter < nodeList.getLength(); parameter++) {
                datasourcetype="";
       
                DataSource inDatasource = new DataSource();
                Node node = nodeList.item(parameter);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    datasourcetype = eElement.getElementsByTagName("datasourcetype").item(0).getTextContent();
                    inDatasource.setUsed(Boolean.valueOf(eElement.getElementsByTagName("used").item(0).getTextContent()));
                    inDatasource.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    inDatasource.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                                      
                    switch (datasourcetype){
                        case "SEEDLINK":
                            inDatasource.setDatasourcetype(dsType.SL);
                            datasources_SL.add(inDatasource);
                            break;    
                        default:
                            App.logger.warn("Warning: FOUND UNKNOWN SEEDLINK DATASOURCE TYPE in " + xml_file);
                            break;
                    }
//
                    inDatasource=null;
                }
            }
//         
            return true;
        } catch (Exception ex) {
            return false;
        } 
    }      
//------------------------------------------------------------------------------    
    public void SaveDataSources(){
        saveToXML(datasources_FDSN_filename, datasources_FDSN);
        saveToXML(datasources_CARAVEL_filename, datasources_CARAVEL);
        saveToXML(datasources_EW_filename, datasources_EW);
        saveToXML(datasources_SL_filename, datasources_SL);
    }
//------------------------------------------------------------------------------        
    public void saveToXML(String xml, ArrayList<DataSource> list) {
    Document dom;
    Element e = null;

    // instance of a DocumentBuilderFactory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
        if (list==null) return;
        if (list.isEmpty()) return;
        
        // use factory to get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
        // create instance of DOM
        dom = db.newDocument();

        // create the root element
        Element rootEle = dom.createElement("root");
        
        for (int i=0; i<list.size(); i++){
            // create data elements and place them under root
            e = dom.createElement("datasource");

            Element dstype = dom.createElement("datasourcetype");
            dstype.appendChild(dom.createTextNode(list.get(i).getDatasourcetype().toString()));      
            e.appendChild(dstype);

            Element dsdesc = dom.createElement("description");
            dsdesc.appendChild(dom.createTextNode(list.get(i).getDescription()));      
            e.appendChild(dsdesc);

            Element dsused = dom.createElement("used");
            if (list.get(i).isUsed())
                dsused.appendChild(dom.createTextNode("true"));      
            else
                dsused.appendChild(dom.createTextNode("false"));   
            e.appendChild(dsused);

            Element dshost = dom.createElement("hostname");
            if ((list.get(i).getHostname())!=null)
                dshost.appendChild(dom.createTextNode(list.get(i).getHostname()));    
            else
                dshost.appendChild(dom.createTextNode(""));
            e.appendChild(dshost);

            Element dsports = dom.createElement("waveserver_ports_list");
            dsports.appendChild(dom.createTextNode(handle_ports_list_reverse(list.get(i).getPortslist())));      
            e.appendChild(dsports);

            Element dsurl = dom.createElement("url");
            if (list.get(i).getUrl()!=null)
                dsurl.appendChild(dom.createTextNode(list.get(i).getUrl()));  
            else
                dsurl.appendChild(dom.createTextNode(""));  
            e.appendChild(dsurl);
            
            Element dshttpport = dom.createElement("httpport");
            if (list.get(i).getHttpport()!=0)
                dshttpport.appendChild(dom.createTextNode((String.valueOf(list.get(i).getHttpport()))));  
            else
                dshttpport.appendChild(dom.createTextNode(""));  
            e.appendChild(dshttpport);

            rootEle.appendChild(e);
        }

        dom.appendChild(rootEle);

        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "datasource.dtd");
//            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(dom), 
                                 new StreamResult(new FileOutputStream(xml)));

        } catch (TransformerException te) {
            App.logger.error(te.getMessage());
        } catch (IOException ioe) {
            App.logger.error(ioe.getMessage());
        }
    } catch (ParserConfigurationException pce) {
        App.logger.error("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
    }
}
//------------------------------------------------------------------------------    
    public int getMaxIndexOfUsedEwHosts(){
        int res=-1;
        try {
            if (datasources_EW==null) return -1;
            if (datasources_EW.isEmpty()) return -1;
            for (int i=0; i< this.datasources_EW.size(); i++){
                // We assume that an earthworm host ahas a non empty ports list
                if ((datasources_EW.get(i).getPortslist()!=null) && (!datasources_EW.get(i).getPortslist().isEmpty()) && (datasources_EW.get(i).isUsed()))
                    res=i;
            }
            
            return res;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    public int getFirstUsedEwHostIndex(){
        int res=-1;
        try {
            if (datasources_EW==null) return -1;
            if (datasources_EW.isEmpty()) return -1;
             
            for (int i=0; i< this.datasources_EW.size(); i++){
                // We assume that an earthworm host ahas a non empty ports list
                if ((datasources_EW.get(i).isUsed()) &&(datasources_EW.get(i).getPortslist()!=null) && (!datasources_EW.get(i).getPortslist().isEmpty()) )
                    return i;
            }
            
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    public int getNextUsedEwHostIndex(int starting_after){
        int res=-1;
        try {
            if (datasources_EW==null) return -1;
            if (datasources_EW.isEmpty()) return -1;
            if (starting_after+1 >= datasources_EW.size()) return -1;
            
            for (int i=starting_after+1; i< this.datasources_EW.size(); i++){
                // We assume that an earthworm host ahas a non empty ports list
                if ((datasources_EW.get(i).getPortslist()!=null) && (!datasources_EW.get(i).getPortslist().isEmpty()) && (datasources_EW.get(i).isUsed()))
                    return i;
            }
            
            return -1;
        } catch (Exception ex) {
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    private ArrayList<Integer> handle_ports_list(String in_ports){
        try {
            if (in_ports.isBlank()|| in_ports.isEmpty()) return null;
            ArrayList<Integer> res = new ArrayList();
            ArrayList<String> res_string = new ArrayList();
            res_string=  new ArrayList<String>(Arrays.asList(in_ports.split(",")));
            
            for (int i=0; i< res_string.size(); i++){
                res.add(Integer.valueOf(res_string.get(i).trim()));
            }
            
            return res;
        } catch (Exception ex){
            return null;
        }
    }
//------------------------------------------------------------------------------    
    private String handle_ports_list_reverse(ArrayList<Integer> in_ports){
        try {
            if (in_ports==null) return "";
            if (in_ports.isEmpty()) return "";
            
            String res=in_ports.get(0).toString();
  
            for (int i=1; i< in_ports.size(); i++){
                res+= "," + in_ports.get(i);
            }
            
            return res;
        } catch (Exception ex){
            return "";
        }
    }
//-------------------------------------------------------------------------------- 
    /**
     *
     * @return the startup date for events search
     */
    public String get_startup_date() {
        return prop.getProperty("startup_date");
    }  
    public void SetStartupDate(String in_date){
        prop.setProperty("startup_date", in_date);
    }
//-------------------------------------------------------------------------------- 
    /**
     *
     * @return the MIN LAT of the selected geographical box
     */
    public float get_box_minLat() {
        return Float.valueOf(prop.getProperty("box.min_lat"));
    }   
    public void SetBoxMinLat(String min_lat) {
        prop.setProperty("box.min_lat", min_lat);
    }
//-------------------------------------------------------------------------------- 
    public int get_polling_interval(){
        return Integer.parseInt(prop.getProperty("ws.polling_interval"));
    }
    
    public void setPollingInterval(String sec){
        prop.setProperty("ws.polling_interval", sec);
    }
//------------------------------------------------------------------------------    
    /**
     *
     * @return the MAX LAT of the selected geographical box
     */
    public float get_box_maxLat() {
        return Float.parseFloat(prop.getProperty("box.max_lat"));
    }
    public void SetBoxMaxLat(String max_lat) {
        prop.setProperty("box.max_lat", max_lat);
    }
    
    
    
    public int get_hours_before_now(){
        try {
            return Integer.parseInt(prop.getProperty("search.hours_before_now"));
        } catch (Exception ex){return 0;}
    }
//-------------------------------------------------------------------------------- 
    /**
     *
     * @return the MIN LON of the selected geographical box
     */
    public float get_box_minLon() {
        return Float.valueOf(prop.getProperty("box.min_lon"));
    } 
    public void SetBoxMinLon(String min_lon) {
        prop.setProperty("box.min_lon", min_lon);
    }
//-------------------------------------------------------------------------------- 
    /**
     *
     * @return the MIAX LON of the selected geographical box
     */
    public float get_box_maxLon() {
        return Float.valueOf(prop.getProperty("box.max_lon"));
    }   
    public void SetBoxMaxLon(String max_lon) {
        prop.setProperty("box.max_lon", max_lon);
    }
    //-------------------------------------------------------------------------------- 
    /**
     *
     * @return the hypo location routine web services base path
     */
    public String get_hypo2000_path() {
        return prop.getProperty("ws.path_for_hypo2000");
    } 
    public void SetHypo2000Path(String hypo_path){
        prop.setProperty("ws.path_for_hypo2000", hypo_path);
    }
    
    
    public String get_pyml_url() {
        return prop.getProperty("ws.pymlurl");
    } 
    public void set_pyml_url(String pyml_url){
        prop.setProperty("ws.pymlurl", pyml_url);
    }
//-------------------------------------------------------------------------------- 
//    /**
//     *
//     * @return the standard used for webservices in [FDSN, INGV_CUSTOM]
//     */
//    public String get_ws_standard() {
//        return prop.getProperty("ws.standard");
//    } 
//    public void SetWsStandard(String value){
//        prop.setProperty("ws.standard", value);
//    }
//-------------------------------------------------------------------------------- 
    /**
     *
     * @return true if we want to poll on the web services host
     */
    public boolean isWebServicePollingActive() {
        return Boolean.parseBoolean(prop.getProperty("ws.make_polling_on_host"));
    } 
//--------------------------------------------------------------------------------
    /**
     * @param active true if the polling must be activated
     */
    public void setWebServicePollingActive(boolean active) {
        prop.setProperty("ws.make_polling_on_host", Boolean.toString(active));
    }
//--------------------------------------------------------------------------------        
     /**
     *
     * @return if the debug mode is set
     */
        public boolean isDebugMode() {
        return Boolean.parseBoolean(prop.getProperty("debug_mode"));
    }
//--------------------------------------------------------------------------------
    /**
     * @param debug_mode true if in debug mode
     */
    public void setDebugMode(boolean debug_mode) {
        prop.setProperty("debug_mode", Boolean.toString(debug_mode));
    }   
//-------------------------------------------------------------------------------- 
    /**
     * @return the alarm_filename
     */
    public String getAlarm_filename() {
        return prop.getProperty("alarm_filename");
    }
//--------------------------------------------------------------------------------   
    /**
     * @param alarm_filename the alarm_filename to set
     */
    public void setAlarm_filename(String alarm_filename) {
        //this.alarm_filename = alarm_filename;
        prop.setProperty("alarm_filename", alarm_filename);
        
    }
//-------------------------------------------------------------------------------- 
    /**
     * @return the alarm_originupdate
     */
    public String getAlarm_originupdate_filename() {
        return prop.getProperty("alarm_originupdate");
    }
//--------------------------------------------------------------------------------   
    /**
     * @param alarm_originupdate_filename the alarm_filename to set
     */
    public void setAlarm_originupdate_filename(String alarm_originupdate_filename) {
        //this.alarm_filename = alarm_filename;
        prop.setProperty("alarm_originupdate", alarm_originupdate_filename);
        
    }
//--------------------------------------------------------------------------------       
    /**
     * @return the waveforms file format es. MSEED or SAC or SEISAN
     */
    public String getWaveFileFormat() {
        return prop.getProperty("waveform.format");
    }
    public void SetWaveFileFormat(String format){
        prop.setProperty("waveform.format", format);
    }
//--------------------------------------------------------------------------------  
     /**
     * @return true if we want to get use offline maps for the GIS
     */
    public boolean useWMS() {
        return Boolean.parseBoolean(prop.getProperty("mapping.gis.useWMS"));
    }
    public void setUseWMS(boolean value) {
        prop.setProperty("mapping.gis.useWMS", Boolean.toString(value));
    }

//--------------------------------------------------------------------------------    
    public String getWMSURL() {
        return prop.getProperty("mapping.gis.WMSURL");
    }
    
    public void setWMSURL(String URL) {
        prop.setProperty("mapping.gis.WMSURL", URL);
    }
//--------------------------------------------------------------------------------       
    /**
     * @return the NETWORK parameter for the "station" FDSN web service query
     */
    public String getFDSNSwsStationNetwork() {
        if (prop.getProperty("ws.station.network") != null) {
            return prop.getProperty("ws.station.network");
        } else return "";
    }
//--------------------------------------------------------------------------------  
    
    public boolean isMessagingActive() {
        return Boolean.parseBoolean(prop.getProperty("messaging.use_messaging"));
    } 
//--------------------------------------------------------------------------------
    /**
     * @param active true if the polling must be activated
     */
    public void setMessagingActive(boolean active) {
        prop.setProperty("messaging.use_messaging", Boolean.toString(active));
    }
    
    /**
     * @return the Messaging engine: can be NONE, RABBITMQ
     */
    public String getMessagingEngine() {
        if (prop.getProperty("messaging.engine") != null) {
            return prop.getProperty("messaging.engine");
        } else return "";
        
    }
    
    
    //--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Host Name in case of RABBIT engine
     */
    public String getMessaging_RABBIT_HostName() {
        if (prop.getProperty("messaging.rabbit.host") != null) {
            return prop.getProperty("messaging.rabbit.host");
        } else return "";
        
    }
    //--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Port Number in case of RABBIT engine
     */
    public int getMessaging_RABBIT_PortNumber() {
        if (prop.getProperty("messaging.rabbit.port") != null) {
            return Integer.valueOf(prop.getProperty("messaging.rabbit.port"));
        } else return -1;
        
    }
    //--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Queue Name in case of RABBIT engine
     */
    public String getMessaging_RABBIT_QueueName() {
        if (prop.getProperty("messaging.rabbit.queue") != null) {
            return prop.getProperty("messaging.rabbit.queue");
        } else return "";
        
    }
    //--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Queue Name in case of RABBIT engine
     */
    public String getMessaging_RABBIT_UserName() {
        if (prop.getProperty("messaging.rabbit.user") != null) {
            return prop.getProperty("messaging.rabbit.user");
        } else return "";
        
    }
    //--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Queue Name in case of RABBIT engine
     */
    public String getMessaging_RABBIT_pwd() {
        if (prop.getProperty("messaging.rabbit.pwd") != null) {
            return prop.getProperty("messaging.rabbit.pwd");
        } else return "";
        
    }
    //--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Queue Name in case of RABBIT engine
     */
    public String getMessaging_RABBIT_VirtualHost() {
        if (prop.getProperty("messaging.rabbit.virtual_host") != null) {
            return prop.getProperty("messaging.rabbit.virtual_host");
        } else return "";
        
    }
//--------------------------------------------------------------------------------       
    /**
     * @return the Messaging Exchange Name in case of RABBIT engine
     */
    public String getMessaging_RABBIT_ExchangeName() {
        if (prop.getProperty("messaging.rabbit.exchange") != null) {
            return prop.getProperty("messaging.rabbit.exchange");
        } else return "";
        
    }    
//--------------------------------------------------------------------------------     
    public boolean store() { //throws FileNotFoundException, IOException{
        try {
            FileOutputStream fos;
            fos = new FileOutputStream("configuration/pfx_properties.xml");
            
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());


            prop.storeToXML(fos, "PickFX properties file - last update " + formatter.format(date));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
       
    }


    /**
     *
     * @return a list of (threshold, R,G,B)
     */
    public String[] get_latency_colors(){
        
       // String [] list= "(-1,160,160,160) (5,0,204,0) (20,0,140,0) (60,242,135,38) (100000000,102,0,102)".split(" ");    
        String [] list= "(-1,255,153,51) (5,0,204,0) (20,0,140,0) (60,242,135,38) (100000000,102,0,102)".split(" ");    
        for (int i=0; i<list.length; i++) {
            list[i]=list[i].replace("(", "");
            list[i]=list[i].replace(")", "");
        }
        return list;
    }   

    /**
     * @return the map_layers
     */
    public ArrayList<map_layers_element> getMap_layers() {
        return map_layers;
    }

    /**
     * @param map_layers the map_layers to set
     */
    public void setMap_layers(ArrayList<map_layers_element> map_layers) {
        this.map_layers = map_layers;
    }

    /**
     * @return the map_settings_file
     */
    public String getMap_settings_file() {
        return map_settings_file;
    }

    /**
     * @param map_settings_file the map_settings_file to set
     */
    public void setMap_settings_file(String map_settings_file) {
        this.map_settings_file = map_settings_file;
    }

    
    public ArrayList<DataSource> getDatasources_FDSN() {
        return datasources_FDSN;
    }

    
    public void setDatasources_FDSN(ArrayList<DataSource> in_datasources_FDSN) {
        this.datasources_FDSN = in_datasources_FDSN;
    }

    
    public ArrayList<DataSource> getDatasources_CARAVEL() {
        return datasources_CARAVEL;
    }

    
    public void setDatasources_CARAVEL(ArrayList<DataSource> in_datasources_CARAVEL) {
        this.datasources_CARAVEL = in_datasources_CARAVEL;
    }

    
    public ArrayList<DataSource> getDatasources_EW() {
        return datasources_EW;
    }

    
    public void setDatasources_EW(ArrayList<DataSource> in_datasources_EW) {
        this.datasources_EW = in_datasources_EW;
    }
    
    public ArrayList<DataSource> getDatasources_SL() {
        return datasources_SL;
    }

    
    public void setDatasources_SL(ArrayList<DataSource> in_datasources_SL) {
        this.datasources_SL = in_datasources_SL;
    }
    
    public void moveUp(ArrayList<DataSource> dsArr, int id){
        
            DataSource swp;
            swp = dsArr.get(id-1);
            dsArr.set(id-1, dsArr.get(id));
            dsArr.set(id, swp);
         
    }
    
    public void moveDown(ArrayList<DataSource> dsArr, int id){
        
            DataSource swp;
            swp = dsArr.get(id+1);
            dsArr.set(id+1, dsArr.get(id));
            dsArr.set(id, swp);
         
    }
//------------------------------------------------------------------------------
    public int search_used_localspace_index(String localspace_name){
        try{
            return this.ReadList_of_searched_localspaces().indexOf(localspace_name);    
        } catch (Exception ex){
            return -1;
        }
    }
//------------------------------------------------------------------------------    
    public ArrayList<String> getsearched_localspaces_comma_separated(){    
        if ((prop.getProperty("search.localspaces")==null)||prop.getProperty("search.localspaces").trim().length()==0) return null;
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(prop.getProperty("search.localspaces").split(",")));    
        return arr;
    }    
    private ArrayList<String> ReadList_of_searched_localspaces(){    
        if ((prop.getProperty("search.localspaces")==null)||prop.getProperty("search.localspaces").trim().length()==0) return null;
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(prop.getProperty("search.localspaces").split(",")));    
        return arr;
    }
    
    private ArrayList<String> ReadList_of_searched_environments(){    
        if ((prop.getProperty("search.environments")==null)||prop.getProperty("search.environments").trim().length()==0) return null;
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(prop.getProperty("search.environments").split(",")));  
        if (arr!=null) {
            for (int i=0; i< arr.size();i++)
                arr.set(i, arr.get(i).toLowerCase());
        }
        return arr;
    }
//------------------------------------------------------------------------------    
    public String getList_of_searched_environments_toString(){
        String res="";
        if (list_of_searched_environments==null) return null;
        if (list_of_searched_environments.isEmpty()) return null;
        for (int i=0; i<this.list_of_searched_environments.size(); i++){
            res+=list_of_searched_environments.get(i).toLowerCase()+",";
        }
        if (res.endsWith(",")) res=res.substring(0,res.lastIndexOf(","));
        return res;
    }    
//------------------------------------------------------------------------------    
    public String getList_of_searched_localspaces_toString(){
        String res="";
        if (list_of_searched_localspaces==null) return null;
        if (list_of_searched_localspaces.isEmpty()) return null;
        for (int i=0; i<this.list_of_searched_localspaces.size(); i++){
            res+=list_of_searched_localspaces.get(i)+",";
        }
        if (res.endsWith(",")) res=res.substring(0,res.lastIndexOf(","));
        return res;
    }
//------------------------------------------------------------------------------
    public int search_used_version_index(String version_code){
        try{
            return this.ReadList_of_searched_versions().indexOf(version_code);    
        } catch (Exception ex){
            return -1;
        }
    }
//------------------------------------------------------------------------------        
    private ArrayList<String> ReadList_of_searched_versions(){    
        if ((prop.getProperty("search.versions")==null)||prop.getProperty("search.versions").trim().length()==0) return null;
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList(prop.getProperty("search.versions").split(",")));    
        return arr;
    }
//------------------------------------------------------------------------------    
    public String List_of_searched_versions_toString(){
        String res="";
        if (list_of_searched_versions==null) return null;
        if (list_of_searched_versions.isEmpty()) return null;
        for (int i=0; i<this.list_of_searched_versions.size(); i++){
            res+=list_of_searched_versions.get(i)+",";
        }
        if (res.endsWith(",")) res=res.substring(0,res.lastIndexOf(","));
        return res;
    }    

    /**
     * @return the list_of_searched_environments
     */
    public ArrayList<String> getList_of_searched_environments() {
        return list_of_searched_environments;
//        //return prop.getProperty("search.environments").split(","); // list_of_searched_environments;
//        try {
//            String[] arr = prop.getProperty("search.environments").split(",");
//            return new ArrayList<String>(Arrays.asList(arr));
//        } catch (Exception ex){
//            return null;
//        }
        
    }

    /**
     * @param list_of_searched_environments the list_of_searched_environments to set
     */
    public void setList_of_searched_environments(ArrayList<String> list_of_searched_environments) {
        this.list_of_searched_environments = list_of_searched_environments;
        
        prop.setProperty("search.environments", this.getList_of_searched_environments_toString());
   
    }

    /**
     * @return the list_of_searched_localspaces
     */
    public ArrayList<String> getList_of_searched_localspaces() {
        return list_of_searched_localspaces;
    }

    /**
     * @param list_of_searched_localspaces the list_of_searched_localspaces to set
     */
    public void setList_of_searched_localspaces(ArrayList<String> list_of_searched_localspaces) {
        this.list_of_searched_localspaces = list_of_searched_localspaces;
    }

    /**
     * @return the list_of_searched_versions
     */
    public ArrayList<String> getList_of_searched_versions() {
        return list_of_searched_versions;
    }

    /**
     * @param list_of_searched_versions the list_of_searched_versions to set
     */
    public void setList_of_searched_versions(ArrayList<String> list_of_searched_versions) {
        this.list_of_searched_versions = list_of_searched_versions;
    }

    /**
     * @return the datasources_LOCALHOST
     */
    public ArrayList<DataSource> getDatasources_LOCALHOST() {
        return datasources_LOCALHOST;
    }

    /**
     * @param datasources_LOCALHOST the datasources_LOCALHOST to set
     */
    public void setDatasources_LOCALHOST(ArrayList<DataSource> datasources_LOCALHOST) {
        this.datasources_LOCALHOST = datasources_LOCALHOST;
    }
    
    public String get_KeyCloak_configurl(){
        return prop.getProperty("keycloak.config_url");
    }
    
    
    public String getPreferredMagnitudeType(){
        return prop.getProperty("magntude.preferred");
    }
}
