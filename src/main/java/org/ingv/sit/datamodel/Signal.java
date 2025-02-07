package org.ingv.sit.datamodel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//----------------------------------------------

class bx {

    public double x1box, x2box, y1box, y2box;
}
//----------------------------------------------

public class Signal {

    float xmax, xmin, ymax, ymin;
    bx BoxRectangle = new bx();
    
    ArrayList<PhaseMarker> PhaseMarkers;
    
    public Signal(float[] xData, float[] yData){
        xmin = xData[0]; //findMax(xData);
        xmax = xData[xData.length-1]; //  findMin(xData);
        ymax = findMax(yData);
        ymin = findMin(yData);
    }
//------------------------------------------------------------------------------    

    public void Plot(Pane box, String Staz, String Channel,
            LocalDateTime Start_Time, boolean ShowGrid, boolean ShowAxes,
            boolean ShowTimeLabels,
            double[] x, double[] y, double samplinginterval,
            Color wave_color) {

        PlotAxes(box, Start_Time, ShowGrid, ShowAxes, ShowTimeLabels, x, y);

        Polyline polyline = new Polyline();

        PlotSignal(box, polyline, x, y, wave_color);

        addSignalNameLabel(box, Staz + " " + Channel);
    }
//------------------------------------------------------------------------------        

    private void PlotAxes(Pane box,
            LocalDateTime Start_Time, boolean ShowGrid, boolean ShowAxes,
            boolean ShowTimeLabels,
            double[] x, double[] y) {

        double xtickrange, ytickrange, fact;
        double xtick_start, ytick_start, it_incr;
        double xtickpos1, ytickpos1, deltatick;

        int itt;
        double luntick, xtickp, ytickp;

//        xmax = findMax(x);
//        xmin = findMin(x);
//        ymax = findMax(y);
//        ymin = findMin(y);

        BoxRectangle.x1box = 5;
        BoxRectangle.x2box = box.getWidth() - 5;
        BoxRectangle.y1box = 5;
        BoxRectangle.y2box = box.getHeight() - BoxRectangle.y1box;

        box.getChildren().clear();

        box.setStyle("-fx-background-color: rgb(66, 66, 66);");

        try {
            if (ShowGrid) {
                xtickrange = xmax - xmin;
                fact = 1;

                while (xtickrange < 1) {
                    fact = fact * 10;
                    xtickrange = xtickrange * 10;
                }

                while (xtickrange > 10) {
                    fact = fact * 0.1;
                    xtickrange = xtickrange * 0.1;
                }

                xtick_start = (double) Math.floor(xmin * fact);

                if (xtickrange < 5) {
                    it_incr = 1;
                } else {
                    it_incr = 2;
                }
                
                //--------------------------------------------------------------
                if (ShowTimeLabels){
                    double tick_text;
                    LocalDateTime time_for_this_label;

                    for (int it = 1; it <= 5; it++) {
                        tick_text = (xtick_start + (it - 1) * it_incr) / fact;
                       
                        double xtickpos = BoxRectangle.x1box + (tick_text - xmin) * (BoxRectangle.x2box - BoxRectangle.x1box) / (xmax - xmin);
                        if ((xtickpos < BoxRectangle.x1box)|| (xtickpos > BoxRectangle.x2box)) xtickpos=-1000;

                        time_for_this_label= Start_Time.plusSeconds((long)(tick_text));

                        Label label = new Label(time_for_this_label.format(DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.of("UTC"))));

                        label.setTextFill(Color.WHITE);
                        //label.setUserData(tipo);
                        box.getChildren().add(label);

                        double xPosition = xtickpos - label.getWidth() / 2; // Centra l'etichetta
                        label.setLayoutX(xPosition);
                        label.setLayoutY(this.BoxRectangle.y2box-15);
                        AnchorPane.setLeftAnchor(label, xPosition);
                        AnchorPane.setBottomAnchor(label, 5.0); // Posiziona vicino al bordo inferiore   
                    } 
                }
                //--------------------------------------------------------------
                xtickpos1 = BoxRectangle.x1box
                        + (xtick_start / fact - xmin)
                        * (BoxRectangle.x2box - BoxRectangle.x1box) / (xmax - xmin);
                deltatick = it_incr / (10 * fact)
                        * (BoxRectangle.x2box - BoxRectangle.x1box) / (xmax - xmin);

                for (itt = -9; itt < 50; itt++) {
                    if (itt / 5 == Math.floor(itt / 5)) {
                        luntick = 10;
                    } else {
                        luntick = 5;
                    }
                    xtickp = xtickpos1 + itt * deltatick;

                    Line L;
                    if ((xtickp > BoxRectangle.x1box) && (xtickp < BoxRectangle.x2box)) {
                        L = new Line(xtickp, BoxRectangle.y1box, xtickp, BoxRectangle.y2box);
                        L.setStroke(javafx.scene.paint.Color.rgb(97, 97, 97));

                        L.getStrokeDashArray().addAll(3.0, 3.0); // Pattern tratteggiato: 10 pixel linea, 5 pixel spazio
                        L.setStrokeWidth(1); // Imposta la larghezza della linea (opzionale)
                        box.getChildren().add(L);

                        L = new Line(xtickp, BoxRectangle.y2box, xtickp, BoxRectangle.y2box - luntick);
                        L.setStroke(javafx.scene.paint.Color.rgb(50, 50, 50)); //(Color.DARKGREY);
                        box.getChildren().add(L);

                        L = new Line(xtickp, BoxRectangle.y1box, xtickp, BoxRectangle.y1box + luntick);
                        L.setStroke(javafx.scene.paint.Color.rgb(50, 50, 50)); //(Color.DARKGREY);
                        box.getChildren().add(L);
                    }
                }

                ytickrange = ymax - ymin;
                fact = 1;

                while (ytickrange < 1) {
                    fact = fact * 10;
                    ytickrange = ytickrange * 10;
                }

                while (ytickrange > 10) {
                    fact = fact * 0.1;
                    ytickrange = ytickrange * 0.1;
                }

                ytick_start = (double) Math.floor(ymin * fact);

                if (ytickrange < 5) {
                    it_incr = 1;
                } else {
                    it_incr = 2;
                }

                ytickpos1 = BoxRectangle.y2box - (ytick_start / fact - ymin) * (BoxRectangle.y2box - BoxRectangle.y1box) / (ymax - ymin);
                deltatick = it_incr / (10 * fact) * (BoxRectangle.y2box - BoxRectangle.y1box) / (ymax - ymin);
                Line L;
                for (itt = -9; itt < 50; itt++) {
                    if (itt / 5 == Math.floor(itt / 5)) {
                        luntick = 10;
                    } else {
                        luntick = 5;
                    }
                    ytickp = ytickpos1 - itt * deltatick;
                    if ((ytickp > BoxRectangle.y1box) && (ytickp < BoxRectangle.y2box)) {
                        //myG.DrawLine(New Pen(Me.GraphicObj.ColorGrid), Me.BoxRectangle.x1box, ytickp, Me.BoxRectangle.x2box, ytickp)
                        L = new Line(BoxRectangle.x1box, ytickp, BoxRectangle.x2box, ytickp);
                        L.setStroke(javafx.scene.paint.Color.rgb(97, 97, 97));
                        L.getStrokeDashArray().addAll(3.0, 3.0); // Pattern tratteggiato: 10 pixel linea, 5 pixel spazio
                        L.setStrokeWidth(1); // Imposta la larghezza della linea (opzionale)
                        box.getChildren().add(L);

                        L = new Line(BoxRectangle.x1box, ytickp, BoxRectangle.x1box + luntick, ytickp);
                        L.setStroke(javafx.scene.paint.Color.rgb(50, 50, 50)); //(Color.DARKGREY);.setStroke(Color.DARKGREY);
                        box.getChildren().add(L);

                        L = new Line(BoxRectangle.x2box, ytickp, BoxRectangle.x2box - luntick, ytickp);
                        L.setStroke(javafx.scene.paint.Color.rgb(50, 50, 50)); //(Color.DARKGREY);.setStroke(Color.DARKGREY);
                        box.getChildren().add(L);
                    }
                }
            }

            if (ShowAxes) {
                Rectangle R = new Rectangle(BoxRectangle.x1box, BoxRectangle.y1box,
                        BoxRectangle.x2box - BoxRectangle.x1box,
                        BoxRectangle.y2box - BoxRectangle.y1box);
                R.setFill(Color.TRANSPARENT);
                R.setStroke(javafx.scene.paint.Color.rgb(50, 50, 50)); //(Color.DARKGREY);.setStroke(Color.DARKGREY);
                box.getChildren().add(R);

                AnchorPane.setBottomAnchor(R, BoxRectangle.y2box);
                AnchorPane.setTopAnchor(R, BoxRectangle.y1box);
                AnchorPane.setLeftAnchor(R, BoxRectangle.x1box);
                AnchorPane.setRightAnchor(R, BoxRectangle.y2box);
            }

        } catch (Exception ex) {
            //
        }
    }
//------------------------------------------------------------------------------
//    private long Decimillesimi(LocalDateTime tempo) {
//        Instant instant = tempo.toInstant(ZoneOffset.UTC);
//
//        // Ottieni i millisecondi dall'epoch (1970-01-01T00:00:00Z)
//        long millis = instant.toEpochMilli();
//
//        return millis * 10000;
//    }
//------------------------------------------------------------------------------    
    private void PlotSignal(Pane P, Polyline polyline, double[] xData, double[] yData, Color wave_color) {
        // Pulizia della polyline
        polyline.getPoints().clear();

        // Aggiungere i nuovi punti alla polyline
        for (int i = 0; i < xData.length; i++) {
            double x = (BoxRectangle.x1box + (xData[i] - xmin)
                    * (BoxRectangle.x2box - BoxRectangle.x1box)
                    / (xmax - xmin));
            double y = (BoxRectangle.y2box - (yData[i] - ymin)
                    * (BoxRectangle.y2box - BoxRectangle.y1box)
                    / (ymax - ymin));
            polyline.getPoints().addAll(x, y);
        }

        polyline.setStroke(wave_color);
        polyline.setStrokeWidth(1);

        // Aggiungi la polyline al pane (assicurati che non sia duplicata)
        if (!P.getChildren().contains(polyline)) {
            P.getChildren().add(polyline);
        }

    }
//------------------------------------------------------------------------------            
    public void ShowMarker(Pane P, LocalDateTime wave_start_time, LocalDateTime marker_time, 
            String marker_label_text, Color colore){
        try {
            // Converte entrambe le date in Instant (UTC)
            Instant waveInstant = wave_start_time.toInstant(ZoneOffset.UTC);
            Instant markerInstant = marker_time.toInstant(ZoneOffset.UTC);

            // Calcola la differenza in millisecondi
            long differenceMillis = markerInstant.toEpochMilli() - waveInstant.toEpochMilli();
            
            // Converte la differenza in decimillesimi di secondo
            double appo1 = differenceMillis * 10; //000;
            
            
            double phase_position_X = BoxRectangle.x1box + 
                        ((appo1 - xmin * 10000) * 
                        (BoxRectangle.x2box - BoxRectangle.x1box) / 
                        (xmax * 10000 - xmin * 10000));
            
            Line L;
            L = new Line(phase_position_X, BoxRectangle.y1box, phase_position_X, BoxRectangle.y2box);
            L.setStroke(colore);
            
            //L.setUserData("PICKLINE_" + marker_label_text);
            
            P.getChildren().add(L);
            
            Label label = new Label(marker_label_text);
            label.setTextFill(colore);
            if (marker_label_text.toUpperCase().startsWith("P"))
                label.setStyle("-fx-text-fill: red;");
            else
                label.setStyle("-fx-text-fill: magenta;");
            
            label.setFont(Font.font("Arial", FontWeight.BOLD, 11));
     
            //label.setUserData(tipo);
            P.getChildren().add(label);
            
            if (PhaseMarkers==null) PhaseMarkers = new ArrayList();
            PhaseMarker M = new PhaseMarker(L, label);
            PhaseMarkers.add(M);
            M.DrawToXY(phase_position_X, BoxRectangle.y1box + 5);
           // M.setLabelAnchors(phase_position_X+3, BoxRectangle.y1box + 5);           
        } catch (Exception ex){
        }
    }
    
    
    
    public void ShowPhaseUncertainty(Pane P, LocalDateTime wave_start_time, LocalDateTime PhaseTime,
            float LowerUncertainty, float UpperUncertainty){
        
        try{
        
            Instant waveInstant = wave_start_time.toInstant(ZoneOffset.UTC);
            Instant startInstant = PhaseTime.minusNanos((long)(LowerUncertainty * 1000000000)).toInstant(ZoneOffset.UTC);
            Instant endInstant = PhaseTime.plusNanos((long)(UpperUncertainty * 1000000000)).toInstant(ZoneOffset.UTC);
            // Calcola la differenza in millisecondi
            long differenceMillis_start = startInstant.toEpochMilli() - waveInstant.toEpochMilli();
            long differenceMillis_end = endInstant.toEpochMilli() - waveInstant.toEpochMilli();
            
            // Converte la differenza in decimillesimi di secondo
            double appo_start = differenceMillis_start * 10; //000;
            double appo_end = differenceMillis_end * 10; //000;
            
            
            double oldX_start = BoxRectangle.x1box + 
                        ((appo_start - xmin * 10000) * 
                        (BoxRectangle.x2box - BoxRectangle.x1box) / 
                        (xmax * 10000 - xmin * 10000));
            double oldX_end = BoxRectangle.x1box + 
                        ((appo_end - xmin * 10000) * 
                        (BoxRectangle.x2box - BoxRectangle.x1box) / 
                        (xmax * 10000 - xmin * 10000));
            
            
            Rectangle R = new Rectangle(oldX_start, BoxRectangle.y1box,
                        oldX_end - oldX_start,
                        BoxRectangle.y2box - BoxRectangle.y1box);
            
            //R.setFill(Color.gray(0.5, 0.5));
            R.setFill(Color.rgb(255, 255, 255, 0.5));
            R.setStroke(Color.TRANSPARENT);
            P.getChildren().add(R);

            AnchorPane.setBottomAnchor(R, BoxRectangle.y2box);
            AnchorPane.setTopAnchor(R, BoxRectangle.y1box);
            AnchorPane.setLeftAnchor(R, oldX_start);
            AnchorPane.setRightAnchor(R, oldX_end);
        } catch (Exception ex) {
            //
        }   
        
    }
//------------------------------------------------------------------------------        
    public void addSignalNameLabel(Pane pane, String signalName) {
        // Crea un rettangolo
        Rectangle rect = new Rectangle();
        rect.setWidth(80); // Imposta la larghezza del rettangolo
        rect.setHeight(20); // Imposta l'altezza del rettangolo
        rect.setFill(javafx.scene.paint.Color.rgb(66, 66, 66)); // Colore di riempimento del rettangolo
        rect.setStroke(Color.WHITE); // Colore del bordo del rettangolo

        // Crea la label con il nome del segnale
        Label label = new Label(signalName);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Imposta la font della label
        label.setTextFill(Color.WHITE); // Colore del testo

        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(rect.getWidth(), rect.getHeight());
        stackPane.getChildren().addAll(rect, label);

        // Posiziona lo StackPane nell'angolo in alto a destra
        AnchorPane.setTopAnchor(stackPane, 10.0); // Posiziona 10px dal bordo superiore
        AnchorPane.setRightAnchor(stackPane, 10.0); // Posiziona 10px dal bordo destro

        // Aggiungi lo StackPane al pane
        stackPane.setLayoutX(BoxRectangle.x2box - stackPane.getPrefWidth()-10);
        stackPane.setLayoutY(BoxRectangle.y2box - stackPane.getPrefHeight() - 5); //stackPane.getPrefHeight()-10);
        pane.getChildren().add(stackPane);

    }
//------------------------------------------------------------------------------    
    public float findMin(float[] data) {
        float min = Float.MAX_VALUE;
        for (float v : data) {
            if (v < min) {
                min = v;
            }
        } 
        return min;
    }
//------------------------------------------------------------------------------    
    public float findMax(float[] data) {
        float max = -Float.MAX_VALUE;
        for (float v : data) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

}
