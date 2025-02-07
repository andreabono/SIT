
package org.ingv.sit.datamodel;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

/**
 *
 * @author andreabono
 */
public class PhaseMarker {
    private String marker_type;
    private Label label;
    private Line line;
    
    public PhaseMarker(Line inLine, Label inLabel){
        line = inLine; 
        label= inLabel; 
        
        line.setUserData(this);
        label.setUserData(this);
    }
    
    
    public void DrawToXY(double X, double Y){
        line.setStartX(X);
        line.setEndX(X);
        
        label.setLayoutX(X + 3);
        AnchorPane.setLeftAnchor(label, X+3);
        if (Y > -1)
            AnchorPane.setTopAnchor(label, Y);
    
    }
    
    public void setLabelAnchors(double leftAnchor, double topAnchor){
        AnchorPane.setLeftAnchor(label, leftAnchor);
        AnchorPane.setTopAnchor(label, topAnchor);    
        
    }
    

    /**
     * @return the label
     */
    public Label getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * @return the line
     */
    public Line getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * @return the marker_type
     */
    public String getMarker_type() {
        return marker_type;
    }

    /**
     * @param marker_type the marker_type to set
     */
    public void setMarker_type(String marker_type) {
        this.marker_type = marker_type;
    }

}
