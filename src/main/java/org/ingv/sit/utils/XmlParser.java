
package org.ingv.sit.utils;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author andreabono
 */
public class XmlParser {
    
//------------------------------------------------------------------------------    
    public XmlParser() {
    
        
    }
//------------------------------------------------------------------------------        
    public Document loadXMLFromString(String xml) throws Exception{
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            return builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            return null;
        }      
    }
//------------------------------------------------------------------------------    
    
    public String parseStationXML(String xmlstream) {
    try {
        String STA, CHA, NET, LOC, LastCHA;
        boolean foundSTA, foundCHA, foudLOC;
        String RESULT="";
        
        // New factory to process data stream
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // We instantiate a new document
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        // New input source from the string (builder only parses files or input sources)
        InputSource is = new InputSource(new StringReader(xmlstream));
        
        // Parse the is
        Document doc = builder.parse(is); 
    
        // Get the root:
        Node root = doc.getFirstChild();

        // Iterate on sub-root nodes
        for (int i = 0; i < root.getChildNodes().getLength(); i++) {
            NET="";
        //  First node is a network_root... 
            Node network_root = root.getChildNodes().item(i);
            for (int aa=0; aa< network_root.getAttributes().getLength(); aa++) {
                if (network_root.getAttributes().item(aa).getNodeName().equalsIgnoreCase("Code")) {
                    NET= network_root.getAttributes().item(aa).getNodeValue();
                    break;
                } 
            }
            
        // ...so we iterate on stations:
            for (int j = 0; j < network_root.getChildNodes().getLength(); j++) {
                Node element = network_root.getChildNodes().item(j);
                //
                if (element.getNodeType() == Node.ELEMENT_NODE) {
                    
                    STA="";
                    if (element.hasAttributes()) {
                        for (int k = 0; k < element.getAttributes().getLength(); k++) {
                            Node attribute = element.getAttributes().item(k);
                            //
                            if (attribute.getNodeName().equalsIgnoreCase("Code")) {
                                // FOUND A NEW STATION:
                                STA = attribute.getNodeValue();
                                break;
                            }
                        }
                    }
                    if(element.hasChildNodes()) {
                        LastCHA="";
                        for (int k = 0; k < element.getChildNodes().getLength(); k++) { 
                            CHA = "";
                            
                            LOC="";
                            Node child = element.getChildNodes().item(k);
                            if (child.getNodeName().equalsIgnoreCase("Channel")) {
                                for (int cnt=0; cnt<child.getAttributes().getLength(); cnt++) {
                                    
                                    if (child.getAttributes().item(cnt).getNodeName().equalsIgnoreCase("Code")){                                        
                                        CHA = child.getAttributes().item(cnt).getNodeValue();
                                    }
                                    if (child.getAttributes().item(cnt).getNodeName().equalsIgnoreCase("LocationCode")){
                                        LOC = child.getAttributes().item(cnt).getNodeValue();
                                        break;
                                    }
                                    
                                }                                
                                if ((CHA.trim().length()>0) && (!CHA.equalsIgnoreCase(LastCHA))) {
                                    RESULT += NET + " " + STA + " -" + LOC + "- " + CHA + " <DATES_PLACEHOLDER>\n";
                                    LastCHA=CHA;                                   
                                }
                                
                            }    
                        }
                    }
                }
            }
        }
        //
        if (RESULT.trim().length()>0) {
            return RESULT;
        } else return null;
        
    } catch (Exception ex) {
        return null;
    }    
        
    }
}
