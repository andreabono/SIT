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

package org.ingv.sit.utils;


import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import org.ingv.dante.model.ObjectArrival;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.TextAnchor;

public class PickedObject {
    private LocalDateTime pick_arrival_time;
    private XYPointerAnnotation pre_label, post_label;
    private XYShapeAnnotation shapeAnnotation;
    
    double x_start, x_end;
    private LocalDateTime time_start;
    private LocalDateTime time_end;
    
    //private XYPointerAnnotation LABEL_PRE, LABEL_POST;
//--------------------------------------------------------------------------------
    public PickedObject(double xstart, double xend){       
        if (xstart > xend) {
            double appo=xstart;
            xstart=xend;
            xend = appo;
        }   
//        
        x_start = xstart;
        x_end = xend;        
    }    
//--------------------------------------------------------------------------------
    public PickedObject(double xstart, double xend, LocalDateTime TimeStart, LocalDateTime TimeEnd){
        if (xstart > xend) {
            double appo=xstart;
            xstart=xend;
            xend = appo;
        }   
//        
        x_start = xstart;
        x_end = xend;
//        
        if (TimeStart.isAfter(TimeEnd)) {
            LocalDateTime dt_appo = TimeStart;
            TimeStart = TimeEnd;
            TimeEnd = dt_appo;
        }
//        
        time_start = TimeStart;
        time_end = TimeEnd;
    }
//--------------------------------------------------------------------------------
    private void add_or_edit_LabelAnnotation(XYPlot xyPlot, double label_value, 
            LocalDateTime label_time, String label_sign){
        DecimalFormat df = new DecimalFormat("0.00");
        ZonedDateTime zdt;
        double millis_Label;
        double amp_Label= (xyPlot.getRangeAxis().getUpperBound() + xyPlot.getRangeAxis().getLowerBound())/2;
        double label_angle = 0;
        
        zdt = label_time.atZone(ZoneId.of("UTC"));
        
        if (label_sign.equals("-")) label_angle = Math.PI ; 
//       
        millis_Label = zdt.toInstant().toEpochMilli();
                
        if (label_sign.equals("-")) {          
            if (pre_label!=null) xyPlot.getRenderer().removeAnnotation(pre_label);  
            
            pre_label = new XYPointerAnnotation(label_sign + df.format(label_value), millis_Label, amp_Label, label_angle); 
        
            pre_label.setBaseRadius(35.0);
            pre_label.setTipRadius(10.0);
            pre_label.setFont(new Font("SansSerif", Font.BOLD, 9));
            pre_label.setPaint(java.awt.Color.RED);
            pre_label.setArrowPaint(java.awt.Color.BLUE);
            pre_label.setTextAnchor(TextAnchor.BASELINE_CENTER); 
            
            xyPlot.getRenderer().addAnnotation(pre_label);
        } else {
            if (post_label!=null) xyPlot.getRenderer().removeAnnotation(post_label);
            
            post_label = new XYPointerAnnotation(label_sign + df.format(label_value), millis_Label, amp_Label, label_angle); 
        
            post_label.setBaseRadius(35.0);
            post_label.setTipRadius(10.0);
            post_label.setFont(new Font("SansSerif", Font.BOLD, 9));
            post_label.setPaint(java.awt.Color.RED);
            post_label.setArrowPaint(java.awt.Color.BLUE);
            post_label.setTextAnchor(TextAnchor.BASELINE_CENTER);
            xyPlot.getRenderer().addAnnotation(post_label);
        }       
    }
//--------------------------------------------------------------------------------    
    public void DrawTimeInterval(XYPlot xyPlot){     
        java.awt.Color col;
        col = java.awt.Color.LIGHT_GRAY;     
//   
        Shape rectangle = new Rectangle2D.Double(x_start, -10000000, x_end-x_start, 20000000);
//           
        shapeAnnotation = new XYShapeAnnotation(rectangle, new BasicStroke(1.f), col, col);        
        shapeAnnotation.setToolTipText("Pick your phase inside this time window");
        xyPlot.setBackgroundAlpha(0.2f);
//
        xyPlot.getRenderer().addAnnotation(shapeAnnotation, Layer.BACKGROUND);       
    }  
//--------------------------------------------------------------------------------    
    public void DrawTimeIntervalWhithLabels(XYPlot xyPlot) {    
//
        java.awt.Color col;
        col = java.awt.Color.GRAY;    
        double diff_pre, diff_post;
        ZonedDateTime zdt_pre, zdt_post, zdt_pick;
//        
        diff_pre=0.0;
        diff_post=0.0;
//        
        DrawTimeInterval(xyPlot); //, xstart, xend);
//        
        if ((getTime_start()==null) || (getTime_end()==null)) return;
//        
        if (this.getPick_arrival_time()!=null) {
            zdt_pre = getTime_start().atZone(ZoneId.of("UTC"));
            zdt_post = getTime_end().atZone(ZoneId.of("UTC"));
            zdt_pick = getPick_arrival_time().atZone(ZoneId.of("UTC"));
            
            diff_pre = (zdt_pick.toInstant().toEpochMilli() - zdt_pre.toInstant().toEpochMilli())/1000.0;
            diff_post = (zdt_post.toInstant().toEpochMilli() - zdt_pick.toInstant().toEpochMilli())/1000.0;
        }
                
        add_or_edit_LabelAnnotation(xyPlot, diff_pre, getTime_start(), "-");
        add_or_edit_LabelAnnotation(xyPlot, diff_post, getTime_end(), "+");
            
    }
//--------------------------------------------------------------------------------    
    public double get_diff_PRE(){
        try {
            double diff_pre=0; 
            ZonedDateTime zdt_pre, zdt_post, zdt_pick;
        
            if (this.getPick_arrival_time()!=null) {
                zdt_pre = getTime_start().atZone(ZoneId.of("UTC"));
                zdt_post = getTime_end().atZone(ZoneId.of("UTC"));
                zdt_pick = getPick_arrival_time().atZone(ZoneId.of("UTC"));

                diff_pre = (zdt_pick.toInstant().toEpochMilli() - zdt_pre.toInstant().toEpochMilli())/1000.0;
                
            }
        
        return diff_pre;
        } catch (Exception ex) {
            return 0.0;
        }
        
    }
//--------------------------------------------------------------------------------        
    public double get_diff_POST(){
        try {
            double diff_post=0; //, diff_post;
            ZonedDateTime zdt_pre, zdt_post, zdt_pick;
        
            if (this.getPick_arrival_time()!=null) {
                zdt_pre = getTime_start().atZone(ZoneId.of("UTC"));
                zdt_post = getTime_end().atZone(ZoneId.of("UTC"));
                zdt_pick = getPick_arrival_time().atZone(ZoneId.of("UTC"));

                diff_post = (zdt_post.toInstant().toEpochMilli() - zdt_pick.toInstant().toEpochMilli())/1000.0;
            }
        
        return diff_post;
        } catch (Exception ex) {
            return 0.0;
        }
        
    }
//--------------------------------------------------------------------------------
    public int Find_phase_in_time_interval(ArrayList<ObjectArrival> phases) {
        //------------------------------------------------------------------------
        // This counts how many phases in the input array (named "phases") are in 
        // the time window identified by [time_start, time_end]
        // 
        // If ONE AND ONLY ONE phase is in the interval, then phase_id is returned
        // 
        //------------------------------------------------------------------------
        try {
            int count = 0;
            int ph_id=-1;
            if (phases ==null) return 0;
            if (phases.size()==0) return 0;
            for (int i=0; i<phases.size(); i++){
                if (this.getTime_start().isBefore(phases.get(i).getPick().getArrivalTime().toLocalDateTime()) && 
                        this.getTime_end().isAfter(phases.get(i).getPick().getArrivalTime().toLocalDateTime())){
                    ph_id = i;
                    count+=1;
                }      
            }            
//            
            if (count == 0) return -1;       // This is OK: no phases
            if (count == 1) return ph_id;    // This is OK: one phase
            if (count > 1) return -9;
//         
            return -9;
        } catch (Exception ex) {
            return -9;
        }
    }
//-------------------------------------------------------------------------------- 
    public void Repaint(XYPlot p){
        if (this.pick_arrival_time == null) return;
        this.DrawTimeIntervalWhithLabels(p);  
    }
//--------------------------------------------------------------------------------

    /**
     * @return the pick_arrival_time
     */
    public LocalDateTime getPick_arrival_time() {
        return pick_arrival_time;
    }

    /**
     * @param pick_arrival_time the pick_arrival_time to set
     */
    public void setPick_arrival_time(LocalDateTime pick_arrival_time) {
        this.pick_arrival_time = pick_arrival_time;
    }

    /**
     * @return the time_start
     */
    public LocalDateTime getTime_start() {
        return time_start;
    }

    /**
     * @param time_start the time_start to set
     */
    public void setTime_start(LocalDateTime time_start) {
        this.time_start = time_start;
    }

    /**
     * @return the time_end
     */
    public LocalDateTime getTime_end() {
        return time_end;
    }

    /**
     * @param time_end the time_end to set
     */
    public void setTime_end(LocalDateTime time_end) {
        this.time_end = time_end;
    }
}
