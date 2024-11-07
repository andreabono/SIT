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
package org.ingv.sit.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import org.ingv.dante.model.ObjectMagnitude;
import org.ingv.dante.model.ObjectStationmagnitude;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;


public class MagnitudeChartMaker {
    
    private ObjectMagnitude MAG;
    
    private double mMin, mMax;
    private double mStart, mEnd;
    private List<ObjectStationmagnitude> myStationMagnitudes;
    double azimuth_max_range=0;
    
    public MagnitudeChartMaker(ObjectMagnitude inMag){
        MAG = inMag;
        if ((MAG.getStationmagnitudes()!=null) && !MAG.getStationmagnitudes().isEmpty()){
            myStationMagnitudes = new ArrayList<ObjectStationmagnitude>();
            for (int k=0; k< MAG.getStationmagnitudes().size(); k++){
                if (MAG.getStationmagnitudes().get(k).getMag()!=null)
                    myStationMagnitudes.add(MAG.getStationmagnitudes().get(k));
            }
        }
    }
    
    public JFreeChart createChart() {
        //if ((MAG==null) || (myStationMagnitudes==null) || myStationMagnitudes.isEmpty())return null;
        if ((myStationMagnitudes==null) || (myStationMagnitudes.isEmpty())) return null;
        //    
        try {
            JFreeChart chart = ChartFactory.createBarChart(
                "",          // chart title
                "Magnitudes",               // domain axis label
                "Number of records",        // range axis label
                createDataset1(),           // data
                PlotOrientation.VERTICAL,
                false,                    // include legend
                true,                     // tooltips?
                false                     // URL generator?  Not required...
            );

            // get a reference to the plot for further customisation...
            CategoryPlot plot = (CategoryPlot) chart.getPlot();

            plot.getDomainAxis().setVisible(true);
            plot.getDomainAxis().setLabelPaint(java.awt.Color.white);
            
            plot.getDomainAxis().setTickLabelsVisible(true);

            CategoryDataset dataset2 = createDataset2();
            if (dataset2!=null) {
                plot.setDataset(1, dataset2);
                plot.mapDatasetToRangeAxis(1, 1);

            }

            CategoryAxis domainAxis = plot.getDomainAxis();
    
            domainAxis.setVisible(true);
            
            plot.getDomainAxis().setVisible(false);

            ValueAxis axis2 = new NumberAxis("");
            axis2.setTickLabelPaint(java.awt.Color.yellow);
            //axis2.setVisible(false);
            plot.setRangeAxis(1, axis2);

            LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
            renderer2.setDefaultShapesVisible(false);
            renderer2.setSeriesPaint(0, java.awt.Color.YELLOW);

            plot.setRenderer(1, renderer2);
            plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
            plot.setBackgroundPaint(new java.awt.Color(82,90,96));
            plot.getRangeAxis().setTickLabelPaint(java.awt.Color.white);
            plot.getRangeAxis().setTickLabelsVisible(true);
            plot.getRangeAxis().setLabelPaint(java.awt.Color.white);

   
            chart.setBackgroundPaint(new java.awt.Color(82,90,96));
            chart.getTitle().setPaint(java.awt.Color.RED);


            return chart;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public JFreeChart createScatterDeltaAzimuthChart() {
        if ((myStationMagnitudes==null) || (myStationMagnitudes.isEmpty())) return null;
        //    
        try {

            JFreeChart chart = ChartFactory.createScatterPlot("", 
                    "Azimuth (deg.)", 
                    "MStaz-M0",
                    createDatasetAzimuth(), 
                    PlotOrientation.VERTICAL, 
                    false, true, false);
                   
            ValueMarker max = new ValueMarker(MAG.getLowerUncertainty());
            max.setPaint(java.awt.Color.orange);
            max.setStroke(new BasicStroke(1.5f));
            //max.setLabel("");
            //max.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
            chart.getXYPlot().addRangeMarker(max,Layer.BACKGROUND);
            
            ValueMarker min = new ValueMarker(- MAG.getLowerUncertainty());
            min.setPaint(java.awt.Color.orange);
            min.setStroke(new BasicStroke(1.5f));
            
            chart.getXYPlot().addRangeMarker(min,Layer.BACKGROUND);
            
            NumberAxis range = (NumberAxis) chart.getXYPlot().getRangeAxis();
            range.setRange(-(azimuth_max_range+0.5), azimuth_max_range+0.5);

            chart.setBackgroundPaint(new java.awt.Color(82,90,96));
            chart.getXYPlot().setBackgroundPaint(new java.awt.Color(82,90,96));
                        
            XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
            renderer.setDefaultItemLabelGenerator(new LabelGenerator());
            renderer.setDefaultItemLabelPaint(Color.YELLOW);  
            renderer.setDefaultNegativeItemLabelPosition(
                new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
            renderer.setDefaultItemLabelFont(
                renderer.getItemLabelFont(0, 0).deriveFont(10f));
            renderer.setDefaultItemLabelsVisible(true);
            chart.getXYPlot().setRenderer(renderer);
                        
            Font smallfont = new Font("Dialog", Font.BOLD, 10); 
            Font smallfont2 = new Font("Dialog", Font.PLAIN, 8);
            
            chart.getXYPlot().getDomainAxis().setLabelPaint(java.awt.Color.white);
            chart.getXYPlot().getDomainAxis().setLabelFont(smallfont);
            chart.getXYPlot().getRangeAxis().setLabelPaint(java.awt.Color.white);
            chart.getXYPlot().getRangeAxis().setLabelFont(smallfont);
            
            chart.getXYPlot().getDomainAxis().setTickLabelPaint(java.awt.Color.white);
            chart.getXYPlot().getDomainAxis().setTickLabelFont(smallfont2);
            chart.getXYPlot().getRangeAxis().setTickLabelPaint(java.awt.Color.white);
            chart.getXYPlot().getRangeAxis().setTickLabelFont(smallfont2);
            
            chart.getTitle().setPaint(java.awt.Color.RED);
            return chart;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    
    
    private CategoryDataset createDataset1() {
        String series1 = "Magnitudes";

        ArrayList<Double> categorie = make_intervals();
        if (categorie==null) return null;
        ArrayList<Double> valori_per_categroria = make_somme_per_categoria(categorie);
        
        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i=0; i< categorie.size(); i++){
            dataset.addValue(valori_per_categroria.get(i), series1, categorie.get(i).toString());
        }

        return dataset;

    }
    
    private CategoryDataset createDataset2() {
        try {
            String series1 = "Normal dist.";
            Double alpha, sigma;
            alpha = MAG.getMag();
            sigma = MAG.getUpperUncertainty();

            if (sigma==null) sigma = MAG.getLowerUncertainty();

            if (sigma==null) return null;

            if (sigma==0d) return null;
            // create the dataset...
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            Function2D n4 = new NormalDistributionFunction2D(alpha,  Math.abs(sigma));
            XYSeries s4 = DatasetUtils.sampleFunction2DToSeries(n4, mMin, mMax, 121, "gauss");

            for (int i=0; i<121; i++){
                //dataset.addValue(s4.getY(i), series1, String.valueOf(i)); 
                dataset.addValue(s4.getY(i), series1, s4.getX(i).toString()); 
            }

            return dataset;
        } catch (Exception ex) {
            return null;
        }
        

    }
    
    private double FindClosestTick(double mVal, boolean upper) {
        double n1=0, n2;
                  
        for (int i=-12; i<40; i++){
            n1 = i *0.25;
            n2 = n1+0.25;
            if ((mVal>=n1) && (mVal<n2)){
                if (upper) 
                    return n2;
                else
                    return n1;
            }
           
        }

        return -99;
    }
    
    private ArrayList<Double> make_somme_per_categoria(ArrayList<Double> categorie){
        try{
            
            // trova quanti ce ne sono tra 0.25 e 0.50         es. 3
            //                         tra 0.50 e 0.75         es. 2
            //                             ...........         es. 0
            
            ArrayList<Double>  res = new  ArrayList<>();
            double Sk=0;
            
            for (int k=0; k<categorie.size(); k++){
                Sk = make_somma(categorie.get(k));
                res.add(Sk);
            }
            
           
             return res;
        } catch (Exception ex) {
            return null;
        }
    }
    
    private double make_somma(double cat_limit){
        try {
            double S=0;
            for (int i=0; i<myStationMagnitudes.size(); i++){
                if ((myStationMagnitudes.get(i).getMag()<cat_limit) && (myStationMagnitudes.get(i).getMag()>cat_limit-0.25))
                    S+=1;
            } 
            return S;
        } catch (Exception ex){
            return 0.0;
        }
    }
    
    
    private ArrayList<Double> make_intervals(){
        ArrayList<Double> res = new ArrayList<>();
        mMin= myStationMagnitudes.get(0).getMag();
        mMax=mMin; //myStationMagnitudes.get(0).getMag();
        for (int i=0; i< myStationMagnitudes.size(); i++){
            if (myStationMagnitudes.get(i).getMag()<mMin) mMin = myStationMagnitudes.get(i).getMag();
            if (myStationMagnitudes.get(i).getMag()>mMax) mMax = myStationMagnitudes.get(i).getMag();
        }
        
        mStart = FindClosestTick(mMin, false) - 0.25;
        
        mEnd = FindClosestTick(mMax, true) + 0.25;
       
        if (mStart<mEnd) {
            
            for (int i=0; i <= (int)((mEnd - mStart)/0.25); i++){
                res.add(mStart + 0.25*i);
            }
            return res;
        } else 
            return null;
       
    }
    
    private XYDataset createDatasetAzimuth() {  
        if (myStationMagnitudes==null) return null;
        if (myStationMagnitudes.isEmpty()) return null;
        
        LabeledXYDataset ds = new LabeledXYDataset();
        double xval, diff;
        
        for (int i=0; i<myStationMagnitudes.size(); i++){
            if (myStationMagnitudes.get(i).getAzimut()!=null) {
                xval = myStationMagnitudes.get(i).getAzimut(); 
                diff = myStationMagnitudes.get(i).getMag()-MAG.getMag();

                ds.add(xval, diff, myStationMagnitudes.get(i).getAmplitude().getSta());
                if (Math.abs(diff) > azimuth_max_range) azimuth_max_range = Math.abs(diff);
            }
        }
        return ds;  
    }
    

    
    private static class LabeledXYDataset extends AbstractXYDataset {

        //private static final int N = 26;
        private List<Number> x = new ArrayList<Number>();
        private List<Number> y = new ArrayList<Number>();
        private List<String> label = new ArrayList<String>();

        public void add(double x, double y, String label){
            this.x.add(x);
            this.y.add(y);
            this.label.add(label);
        }

        public String getLabel(int series, int item) {
            return label.get(item);
        }

        @Override
        public int getSeriesCount() {
            return 1;
        }

        @Override
        public Comparable getSeriesKey(int series) {
            return "Unit";
        }

        @Override
        public int getItemCount(int series) {
            return label.size();
        }

        @Override
        public Number getX(int series, int item) {
            return x.get(item);
        }

        @Override
        public Number getY(int series, int item) {
            return y.get(item);
        }
    }
    
    private static class LabelGenerator implements XYItemLabelGenerator {

        @Override
        public String generateLabel(XYDataset dataset, int series, int item) {
            LabeledXYDataset labelSource = (LabeledXYDataset) dataset;
            return labelSource.getLabel(series, item);
        }

    }
    
    
}
