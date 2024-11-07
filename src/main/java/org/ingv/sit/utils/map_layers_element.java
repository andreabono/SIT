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

import java.awt.Color;

/**
 *
 * @author andreabono
 */
public class map_layers_element {
    private boolean used;               // "true" to see the layer on the map
    private String type;                // S: Shapefile R: Raster Image
    private String filename;            // File name in the /shapefiles folder
    private String title;               // Layer title
    private String linecolor;           // Outline RGB color ex. "(0,0,0)" stands for BLACK  NO SPACES!!!!!!
    private String fillcolor;           // Fill RGB color ex. "(0,0,0)" stands for BLACK  NO SPACES!!!!!!
    private double opacity;             // Opacity value: 0.1 is almost transparent; 1 is fully opaque

    /**
     * @return the used
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * @param used the used to set
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the linecolor
     */
    public Color getLinecolor_asColor() {
        try {
            String[] lat_color = this.linecolor.replace("(","").replace(")","").split(","); 
            return new Color(Integer.parseInt(lat_color[0]), Integer.parseInt(lat_color[1]), Integer.parseInt(lat_color[2]));
        } catch (Exception ex) {
            return Color.BLACK;
        }
        
    }
    
    public String getLinecolor() {     
        return this.linecolor;   
    }

    /**
     * @param linecolor the linecolor to set
     */
    public void setLinecolor(String linecolor) {
        this.linecolor = linecolor;
    }

    /**
     * @return the fillcolor
     */
    public String getFillcolor() {
        return fillcolor;
    }
    public Color getFillcolor_asColor() {
        try {
            String[] lat_color = this.fillcolor.replace("(","").replace(")","").split(","); 
            return new Color(Integer.parseInt(lat_color[0]), Integer.parseInt(lat_color[1]), Integer.parseInt(lat_color[2]));
        } catch (Exception ex) {
            return Color.BLACK;
        }
        
    }

    /**
     * @param fillcolor the fillcolor to set
     */
    public void setFillcolor(String fillcolor) {
        this.fillcolor = fillcolor;
    }

    /**
     * @return the opacity
     */
    public double getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    
    
}

