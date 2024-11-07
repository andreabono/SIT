
package org.ingv.sit;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.SwingWorker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart3d.Chart3D;
import org.jfree.chart3d.Chart3DFactory;
import org.jfree.chart3d.data.xyz.XYZDataset;
import org.jfree.chart3d.fx.Chart3DViewer;
import org.jfree.chart3d.graphics3d.Dimension3D;
import org.jfree.chart3d.plot.XYZPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class pmController implements Initializable {

    @FXML
    private AnchorPane anchor1;
    @FXML
    private AnchorPane anchor2;
    @FXML
    private AnchorPane anchor3;
    @FXML
    private AnchorPane anchor4;
    @FXML
    private AnchorPane anchor3d;
    

    private XYZDataset<String> dataset_3d_Pwave;
    private XYZDataset<String> dataset_3d_Swave;
    

    private String StationCode;
    @FXML
    private Button btnClose;
    
    private LocalDateTime PTime;
    private LocalDateTime StartTimeP, EndTimeP;
    private LocalDateTime StartTimeS, EndTimeS;
    
    @FXML
    private Button btnPlay;
    
    private  XYSeries testseries = new XYSeries("Result");
    @FXML
    private Button btnPlay1;
    @FXML
    private TextField txtStartP;
    @FXML
    private TextField txtEndP;
    @FXML
    private TextField txtStartS;
    @FXML
    private TextField txtEndS;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.btnPlay.setVisible(false);
        this.btnPlay1.setVisible(false);
    }    
    
    public  Node createNode3d() {
        Chart3D chart = createChart(dataset_3d_Pwave);
        return new Chart3DViewer(chart);
    }
    
    private Chart3D createChart(XYZDataset<String> dataset) {
        Chart3D chart = Chart3DFactory.createXYZLineChart("3D Particle motion", 
                "Station: " + StationCode, dataset, "NS [South(-) North (+)]", "Z [Down(-) Up (+)]", "EW [West(-) East (+)]");
        chart.setChartBoxColor(new Color(255, 255, 255, 128));
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10, 10, 10));
        return chart;    
    }
    
    public void disegna(){
        Node grafico = createNode3d();
        anchor3d.getChildren().add(grafico);
        
        AnchorPane.setBottomAnchor(grafico, 0.0);
        AnchorPane.setTopAnchor(grafico, 0.0);
        AnchorPane.setLeftAnchor(grafico, 0.0);
        AnchorPane.setRightAnchor(grafico, 0.0);
        
        txtStartP.setText(getStartTimeP().format(DateTimeFormatter.ISO_TIME));
        txtEndP.setText(getEndTimeP().format(DateTimeFormatter.ISO_TIME));
        
        if ((getStartTimeS()!=null) && (getEndTimeS()!=null)){
            txtStartS.setText(getStartTimeS().format(DateTimeFormatter.ISO_TIME));
            txtEndS.setText(getEndTimeS().format(DateTimeFormatter.ISO_TIME));
        }
        showGraph(1, anchor1);
        showGraph(2, anchor2);
        showGraph(3, anchor3);
        showGraph(4, anchor4);       
    }
    
    private void showGraph(int graphId, AnchorPane anchor){
        NumberAxis DomainAxis, RangeAxis;
        XYDataset dataset=null; 
        
        RangeAxis = new NumberAxis();
        RangeAxis.setAutoRange(true);
        RangeAxis.setTickLabelPaint(Color.BLACK);
        DomainAxis = new NumberAxis();
        DomainAxis.setAutoRange(true);
        DomainAxis.setTickLabelPaint(Color.BLACK);
        
        switch (graphId){
            
            case 1:
                dataset = createDataset_P_on_Z_NS();
                break;
            case 2:
                dataset = createDataset_P_on_EW_NS();
                break;
            case 3:
                dataset = createDataset_S_on_Z_NS();
                break;
            case 4:
                dataset = createDataset_nosamples();
                break;
        }
        
        if (dataset==null) return;
                
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
         "" , // "P Wave in Z-NS Plane"
         "" , // NS
         "" ,  // Z
         dataset ,
         PlotOrientation.VERTICAL,
         false , false , false);
        
        XYPlot plot = (XYPlot) xylineChart.getPlot();
        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setUpperMargin(0.0);
        plot.setBackgroundPaint(Color.WHITE);
        
        XYLineAndShapeRenderer r1 = new XYLineAndShapeRenderer();
        r1.setSeriesPaint(0, Color.RED); //new Color(0xff, 0xff, 0x00)); 
        r1.setDefaultShapesVisible(false);
        plot.setRenderer(r1);
        
        
        ChartViewer P = new ChartViewer(xylineChart);
        
        
        AnchorPane.setBottomAnchor(P, 0.0);
        AnchorPane.setTopAnchor(P, 0.0);
        AnchorPane.setLeftAnchor(P, 0.0);
        AnchorPane.setRightAnchor(P, 0.0);
        anchor.getChildren().add(P);
    }
  
    private XYDataset createDataset_P_on_Z_NS( ) {
        try {
            XYSeries wave = new XYSeries( "PWave", false);          
            
            for (int i=0; i< dataset_3d_Pwave.getItemCount(0); i++){
                wave.add(dataset_3d_Pwave.getX(0, i), dataset_3d_Pwave.getZ(0, i));
            }

            XYSeriesCollection dataset = new XYSeriesCollection( );          
            dataset.addSeries(wave );          

            return dataset;
        } catch (Exception ex) {
            return null;
        }
   }
  
    private XYDataset createDataset_P_on_EW_NS( ) {
        try {
            XYSeries wave = new XYSeries( "PWave", false);          
            
            for (int i=0; i< dataset_3d_Pwave.getItemCount(0); i++){
                wave.add(dataset_3d_Pwave.getY(0, i), dataset_3d_Pwave.getX(0, i));
            }

            XYSeriesCollection dataset = new XYSeriesCollection( );          
            dataset.addSeries(wave );          

            return dataset;
        } catch (Exception ex) {
            return null;
        }
   }
    
    
    private XYDataset createDataset_S_on_Z_NS( ) {
        try {
            XYSeries wave = new XYSeries( "SWave", false);          
            
            for (int i=0; i< dataset_3d_Swave.getItemCount(0); i++){
                wave.add(dataset_3d_Swave.getX(0, i), dataset_3d_Swave.getZ(0, i));
            }

            XYSeriesCollection dataset = new XYSeriesCollection( );          
            dataset.addSeries(wave );          

            return dataset;
        } catch (Exception ex) {
            return null;
        }
   }
    
    
    
    private XYDataset createDataset_nosamples( ) {
        try {
            testseries = new XYSeries( "PWave", false);          
            
            XYSeriesCollection dataset = new XYSeriesCollection( );          
            dataset.addSeries(testseries );          

            return dataset;
        } catch (Exception ex) {
            return null;
        }
   }

    /**
     * @return the StationCode
     */
    public String getStationCode() {
        return StationCode;
    }

    /**
     * @param StationCode the StationCode to set
     */
    public void setStationCode(String StationCode) {
        this.StationCode = StationCode;
    }

    @FXML
    private void btnClose_Click(ActionEvent event) {
        try {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();  
        } catch (Exception ex) {
        }
    }

    

    /**
     * @return the PTime
     */
    public LocalDateTime getPTime() {
        return PTime;
    }

    /**
     * @param PTime the PTime to set
     */
    public void setPTime(LocalDateTime PTime) {
        this.PTime = PTime;
    }

    /**
     * @return the dataset_3d_Pwave
     */
    public XYZDataset<String> getDataset_3d_Pwave() {
        return dataset_3d_Pwave;
    }

    /**
     * @param dataset_3d_Pwave the dataset_3d_Pwave to set
     */
    public void setDataset_3d_Pwave(XYZDataset<String> dataset_3d_Pwave) {
        this.dataset_3d_Pwave = dataset_3d_Pwave;
    }

    /**
     * @return the dataset_3d_Swave
     */
    public XYZDataset<String> getDataset_3d_Swave() {
        return dataset_3d_Swave;
    }

    /**
     * @param dataset_3d_Swave the dataset_3d_Swave to set
     */
    public void setDataset_3d_Swave(XYZDataset<String> dataset_3d_Swave) {
        this.dataset_3d_Swave = dataset_3d_Swave;
    }

    @FXML
    private void btnPlay_Click(ActionEvent event) {
        try {
            testseries.clear();
            
            Task<Void> task = new Task<Void>() {
                @Override
                    protected Void call(){
                        try {
                            XYDataset dataset = createDataset_P_on_Z_NS();
                            for (int i=0; i< dataset.getItemCount(0); i++) {
                                testseries.add(dataset.getX(0, i), dataset.getY(0, i));
                                Thread.sleep(100);
                            }
                           //task.cancel();

                        } catch (Exception ex){
                            int k=0;
                            k++;
                        }
                        return null;
                    }
            };
 
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    //System.out.println("Task succeded...");
                }
            });
       
            new Thread(task).start();
        } catch (Exception ex) {
            int k=0;
            k++;
        }
        
        
        
    }

    @FXML
    private void btnPlay1_Click(ActionEvent event) {
        try {
            testseries.clear();
            XYDataset dataset = createDataset_P_on_Z_NS();
            
            Task<Void> task = new Task<Void>() {
                @Override
                    protected Void call(){
                        try {
                            //XYDataset dataset = createDataset_P_on_Z_NS();
                            for (int i=0; i< dataset.getItemCount(0); i++) {
                                testseries.add(dataset.getX(0, i), dataset.getY(0, i));
                                Thread.sleep(100);
                            }
                           //task.cancel();

                        } catch (Exception ex){
                            int k=0;
                            k++;
                        }
                        return null;
                    }
            };
 
            task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    //System.out.println("Task succeded...");
                }
            });
       
            new Thread(task).start();
        } catch (Exception ex) {
            int k=0;
            k++;
        }
    }
    
    
    
    
    
    
    
    private class TwoWorker extends SwingWorker<Double, Double> {

        private static final int N = 5;
        //private final DecimalFormat df = new DecimalFormat(S);
        double x = 1;
        private int n;

        @Override
        protected Double doInBackground() throws Exception {
            for (int i = 1; i <= N; i++) {
                x = x - (((x * x - 2) / (2 * x)));
                setProgress(i * (100 / N));
                publish(x);
                Thread.sleep(1000); // simulate latency
            }
            return x;
        }

        @Override
        protected void process(List<Double> chunks) {
            for (double d : chunks) {
                //label.setText(df.format(d));
                testseries.add(++n, d);
            }
        }
    }

    /**
     * @return the StartTimeP
     */
    public LocalDateTime getStartTimeP() {
        return StartTimeP;
    }

    /**
     * @param StartTimeP the StartTimeP to set
     */
    public void setStartTimeP(LocalDateTime StartTimeP) {
        this.StartTimeP = StartTimeP;
    }

    /**
     * @return the EndTimeP
     */
    public LocalDateTime getEndTimeP() {
        return EndTimeP;
    }

    /**
     * @param EndTimeP the EndTimeP to set
     */
    public void setEndTimeP(LocalDateTime EndTimeP) {
        this.EndTimeP = EndTimeP;
    }

    /**
     * @return the StartTimeS
     */
    public LocalDateTime getStartTimeS() {
        return StartTimeS;
    }

    /**
     * @param StartTimeS the StartTimeS to set
     */
    public void setStartTimeS(LocalDateTime StartTimeS) {
        this.StartTimeS = StartTimeS;
    }

    /**
     * @return the EndTimeS
     */
    public LocalDateTime getEndTimeS() {
        return EndTimeS;
    }

    /**
     * @param EndTimeS the EndTimeS to set
     */
    public void setEndTimeS(LocalDateTime EndTimeS) {
        this.EndTimeS = EndTimeS;
    }

   
}
