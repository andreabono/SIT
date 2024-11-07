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
 * andrea.bono@ingv.itff
 * +39 0651860290
 * 
 */
package org.ingv.sit.tablemodels;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty; 
import javafx.beans.property.SimpleDoubleProperty;
        
public class MAPLayers_list_items {


    
    
    
    private SimpleBooleanProperty used;                  // "true" to see the layer on the map
    private SimpleStringProperty type;                  // S: Shapefile R: Raster Image
    private SimpleStringProperty filename;              // File name in the /shapefiles folder
    private SimpleStringProperty title;                 // Layer title
    private SimpleStringProperty linecolor;             // Outline RGB color ex. "(0,0,0)" stands for BLACK  NO SPACES!!!!!!
    private SimpleStringProperty fillcolor;             // Fill RGB color ex. "(0,0,0)" stands for BLACK  NO SPACES!!!!!!
    private SimpleDoubleProperty opacity;                     // Opacity value: 0.1 is almost transparent; 1 is fully opaque
    

//------------------------------------------------------------------------------
    public MAPLayers_list_items(Boolean in_used, String in_type, String in_filename, 
            String in_title, String in_linecolor, String in_fillcolor, Double in_opacity){
        used = new SimpleBooleanProperty(in_used);
        type = new SimpleStringProperty(in_type);
        filename = new SimpleStringProperty(in_filename);
        title = new SimpleStringProperty(in_title);
        linecolor = new SimpleStringProperty(in_linecolor); 
        fillcolor = new SimpleStringProperty(in_fillcolor);
        opacity = new SimpleDoubleProperty(in_opacity); 
    }  
//------------------------------------------------------------------------------ 
    public Boolean getUsed() {
        return used.get();
    }
//------------------------------------------------------------------------------     
    public void setUsed(Boolean in_used) {
        this.used.set(in_used);
    }    
//------------------------------------------------------------------------------ 
    public String getType() {
        return type.get();
    }
//------------------------------------------------------------------------------     
    public void setType(String in_type) {
        this.type.set(in_type);
    }
//------------------------------------------------------------------------------
    public String getFilename() {
        return filename.get();
    }
//------------------------------------------------------------------------------     
    public void setFilename(String in_filename) {
        this.filename.set(in_filename);
    }
//------------------------------------------------------------------------------
    public String getTitle() {
        return title.get();
    }
//------------------------------------------------------------------------------     
    public void setTitle(String in_title) {
        this.title.set(in_title);
    }
//------------------------------------------------------------------------------
    public String getLinecolor() {
        return linecolor.get();
    }
//------------------------------------------------------------------------------     
    public void setLinecolor(String in_linecolor) {
        this.linecolor.set(in_linecolor);
    }
//------------------------------------------------------------------------------
    public String getFillcolor() {
        return fillcolor.get();
    }
//------------------------------------------------------------------------------     
    public void setFillcolor(String in_fillcolor) {
        this.fillcolor.set(in_fillcolor);
    }
//------------------------------------------------------------------------------
    public Double getOpacity() {
        return opacity.get();
    }
//------------------------------------------------------------------------------     
    public void setOpacity(Double in_opacity) {
        this.opacity.set(in_opacity);
    }
//------------------------------------------------------------------------------
   
}


