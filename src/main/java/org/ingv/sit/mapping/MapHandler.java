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
package org.ingv.sit.mapping;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javax.swing.UIManager;
import org.geotools.coverage.GridSampleDimension;
import static org.geotools.coverage.GridSampleDimension.LOGGER;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.geometry.Envelope2D;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.GridReaderLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.ows.wms.CRSEnvelope;
import org.geotools.ows.wms.StyleImpl;
import org.geotools.ows.wms.WMSCapabilities;
import org.geotools.ows.wms.WMSUtils;
import org.geotools.ows.wms.WebMapServer;
import org.geotools.ows.wms.map.WMSLayer;
import org.geotools.referencing.CRS;
import org.geotools.referencing.GeodeticCalculator;
import org.geotools.referencing.ReferencingFactoryFinder;
import org.geotools.referencing.operation.transform.AffineTransform2D;
import org.geotools.renderer.lite.StreamingRenderer;
import org.geotools.styling.ChannelSelection;
import org.geotools.styling.ContrastEnhancement;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Fill;
import org.geotools.styling.Graphic;
import org.geotools.styling.Mark;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.RasterSymbolizer;
import org.geotools.styling.Rule;
import org.geotools.styling.SLD;
import org.geotools.styling.SelectedChannelType;
import org.geotools.styling.Stroke;
import org.geotools.styling.Style;
import org.geotools.styling.StyleBuilder;
import org.geotools.util.factory.FactoryRegistryException;
import org.ingv.sit.App;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.style.ContrastMethod;
import org.geotools.styling.StyleFactory;
import org.geotools.styling.Symbolizer;
import org.geotools.styling.TextSymbolizer;
import org.geotools.swing.wms.WMSLayerChooser;
import org.geotools.util.factory.Hints;
import org.ingv.sit.utils.pfxDialog;
import org.jfree.fx.FXGraphics2D;
import org.locationtech.jts.geom.Geometry;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.expression.Expression;
import org.opengis.filter.identity.FeatureId;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

public class MapHandler implements AutoCloseable {
    private MapContent map = new MapContent();
    private GridCoverage2DReader reader;
    static StyleFactory styleFactory = CommonFactoryFinder.getStyleFactory(null);
    static FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory(null);
    static FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2(null);

    //private myResizableCanvas canvas; 
    private Canvas canvas; 
    
    private GraphicsContext gc;
    private boolean repaint = true;
    private static final double PAINT_HZ = 50.0;
    
    final Rectangle selection = new Rectangle();
    private double baseDragX;
    private double baseDragY;
    final Light.Point anchor = new Light.Point();
    private String ACTION="NONE";
    
    DirectPosition2D clickPos, releasePos;
    private final FilterFactory2 ff =  CommonFactoryFinder.getFilterFactory2(); 
    private ArrayList Selected_Stations;
    private FeatureSource featureSource ;
    
    public int idWavesController;
    
    //private Coordinate ruler_start, ruler_end;
    private DirectPosition2D ruler_start, ruler_end;
    private ScheduledService<Boolean> svc;
    
//------------------------------------------------------------------------------  
    public MapHandler(int canvas_width, int canvas_height){
        canvas = new Canvas(canvas_width, canvas_height); 
    }
    
    public void ridisegna(){
    
        map.getViewport().setScreenArea(new java.awt.Rectangle((int) getCanvas().getWidth(), (int) getCanvas().getHeight()));
    
        drawMap(gc);
        
        doSetDisplayArea(map.getViewport().getBounds());    
    }
  
    @Override
    public void close()  {
        if (svc!=null && svc.isRunning()) svc.cancel();
                
        //Logger.getLogger("org.ingv.pfx").log(java.util.logging.Level.INFO, ">>>>>>>>>>>>>>>> Releasing map and graphic resources...");
        if ((map!=null) && (map.layers()!=null)){
            for (int k=0; k < map.layers().size(); k++){
                map.layers().get(k).dispose();
            }
        }
        
        gc=null;
        if (Selected_Stations!=null) Selected_Stations.clear();
        setCanvas(null);
        if (map!=null) map.dispose();
        map=null;
        featureSource=null;
        System.gc();
    }
    
    public void CreateMapWithCanvas(ArrayList staz, 
            double epi_lat, double epi_lon, 
            double x1, double x2, double y1, double y2,
            boolean basic, boolean showLabels){
        
        //canvas = new Canvas(800, 600);
        //canvas = new Canvas((int)(Screen.getPrimary().getBounds().getWidth()*0.6), (int)(Screen.getPrimary().getBounds().getHeight()/2));
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        if (map==null)
            map=new MapContent();
                
        gc = getCanvas().getGraphicsContext2D();

        ShowMap(basic, staz, epi_lat, epi_lon, x1, x2, y1, y2, showLabels);
        map.getViewport().setScreenArea(new java.awt.Rectangle((int) getCanvas().getWidth(), (int) getCanvas().getHeight()));
    
        drawMap(gc);
               
        initEvents(); 
   
        initPaintThread();     
    }
//------------------------------------------------------------------------------
    private void initPaintThread() {
        svc = new ScheduledService<Boolean>() {
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {
                    protected Boolean call() {
                        Platform.runLater(() -> {
                            drawMap(gc);
                        });
                        return true;
                    }
                };
            }
        };
        svc.setPeriod(Duration.millis(1000.0 / PAINT_HZ));
        svc.start();
    }
//------------------------------------------------------------------------------
    protected void doSetDisplayArea(ReferencedEnvelope envelope) {
            map.getViewport().setBounds(envelope);
            repaint = true;
    }    
//------------------------------------------------------------------------------    
    private void drawMap(GraphicsContext gc) {
	if (!repaint) {
            return;
        }
        
        try {
            repaint = false;
            StreamingRenderer draw = new StreamingRenderer();
            draw.setMapContent(map);
            FXGraphics2D graphics = new FXGraphics2D(gc);
            graphics.setBackground(java.awt.Color.DARK_GRAY);
            graphics.clearRect(0, 0, (int) getCanvas().getWidth(), (int) getCanvas().getHeight());
            java.awt.Rectangle rectangle = new java.awt.Rectangle((int) getCanvas().getWidth(), (int) getCanvas().getHeight());
            draw.paint(graphics, rectangle, map.getViewport().getBounds());
        } catch (Exception ex){
        }
        
    }   
//------------------------------------------------------------------------------
    private int find_layer_index(String layer_title){
        if (map==null) return -1;
        if (map.layers()==null) return -1;
        if (map.layers().isEmpty()) return -1;
        
        for (int i=0; i< map.layers().size(); i++){
            if (map.layers().get(i).getTitle().equalsIgnoreCase(layer_title)) return i;
        }
        return -1;
    }
//------------------------------------------------------------------------------    
    public void ShowMap(boolean basic, ArrayList staz, double epi_lat, double epi_lon, 
            double x1, double x2, double y1, double y2, boolean showLabels){
        try {
            if ((Math.abs(x2-x1) > 0.0) && ((Math.abs(y2-y1) > 0.0))) 
                ShowBaseMap(basic, x1, x2, y1, y2);
            else
                ShowBaseMap(basic, epi_lon-1, epi_lon+1, epi_lat-1, epi_lat+1);
            
            showLocationOnMap(staz, epi_lat, epi_lon, showLabels);
            
  
        } catch (Exception ex) {
        } 
    }
//------------------------------------------------------------------------------       
    public void showLocationOnMap(ArrayList staz, double epi_lat, double epi_lon, boolean showLabels){
        ClearMap();
        if ((App.G!=null) && (App.G.SeismicNet!=null))
            ShowSeismicNetwork(App.G.SeismicNet.ToStationArray_Geotools());
        ShowTriggeredStations(staz, showLabels);
        
        ShowHypocenter(epi_lat, epi_lon);  
        SetMapBounds(App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
            App.G.options.get_box_minLat(),App.G.options.get_box_maxLat());
        repaint = true;
       
    }
//------------------------------------------------------------------------------    
    private void ClearMap(){
        if (map==null) return;
  
        Iterator<Layer> itr = map.layers().iterator();
        if(!map.layers().isEmpty() ) {
            try {            
                while(itr.hasNext()){  
                    Layer next=itr.next();   
                    String next_title=next.getTitle();
                    if (next_title!=null &&  (next_title.equalsIgnoreCase("TriggeredStations") || next_title.equalsIgnoreCase("Hypocenter") || next_title.equalsIgnoreCase("Network") || next_title.equalsIgnoreCase("SelectedStations")) )
                    {
                        map.removeLayer(next);
                    }
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            } 
        }
    }
//------------------------------------------------------------------------------   
    public void ShowBaseMap(boolean basic,
        double x1, double x2, double y1, double y2) throws Exception {
        /* 
           ---------------------------------------------------------------------
        // This shows the base DEM layer as a geotiff raster file IF AVAILABLE
        // and shows also world borders and local/regional boundaries
        //
        // "basic" mode does not show the raster level
           ---------------------------------------------------------------------
        */
        try {
            Hints.putSystemDefault(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);

            if (App.G.options.useWMS() && !basic)
                CreateWMS();
     
            if ((App.G.options.map_layers!=null) &&(!App.G.options.map_layers.isEmpty())) {
                for (int i=0; i<App.G.options.map_layers.size(); i++){
                    switch (App.G.options.map_layers.get(i).getType()){
                        case "L":
                            // Lines shapefile Layer
                            float LW = 0.1f;
                            if ((basic) && (App.G.options.map_layers.get(i).getFilename().toUpperCase().contains("REGIONI"))) {
                                LW=1f;
                            }
                            AddShapefileLINELayer(App.G.options.map_layers.get(i).getFilename(),
                                App.G.options.map_layers.get(i).getTitle(),
                                App.G.options.map_layers.get(i).getLinecolor_asColor(),LW);
                            break;
                        case "P":
                            // Polygon shapefile layer 
                            AddShapefilePOLYGONLayer(App.G.options.map_layers.get(i).getFilename(),
                                App.G.options.map_layers.get(i).getTitle(),
                                App.G.options.map_layers.get(i).getLinecolor_asColor(),
                                App.G.options.map_layers.get(i).getFillcolor_asColor(),
                                (float)App.G.options.map_layers.get(i).getOpacity()
                                );
                            break;
                        case "R":
                            // Local file raster Layers are not displayed when a WMS is selected
                            // and also when we wannt a basic map
                            if (!basic && !App.G.options.useWMS()) {
                                //if (App.G.FileExists("shapefiles/" + App.G.options.map_layers.get(i).getFilename())) {
                                    addRasterLayer(App.G.options.map_layers.get(i).getFilename(), App.G.options.map_layers.get(i).getTitle());
                                //} else App.G.options.map_layers.get(i).setUsed(false);
                            }
                            break;
                    }    
                }
            }
            
            SetMapBounds(x1, x2, y1, y2);           
        } catch (Exception ex) {
            App.logger.error("ERROR showing basemap: " + ex.getMessage());
        }
    }
//------------------------------------------------------------------------------  
    private void addRasterLayer(String rasterFileName, String title) {
        if (!rasterFileName.startsWith("shapefiles/"))
            rasterFileName = "shapefiles/" + rasterFileName;
        
        if (App.G.FileExists(rasterFileName)) {
                
            File rasterFile = new File(rasterFileName);
            AbstractGridFormat format = GridFormatFinder.findFormat(rasterFile);
            if (format==null) return;

            Hints hints = new Hints();
            if (format instanceof GeoTiffFormat) {
                hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
            }
            reader = format.getReader(rasterFile, hints);

            // Initially display the raster in greyscale using the
            // data from the first image band
            Style rasterStyle =   createRGBStyle(); // createGreyscaleStyle(2);

            Layer rasterLayer = new GridReaderLayer(reader, rasterStyle);

            rasterLayer.setTitle(title);

            map.addLayer(rasterLayer);
        }
    }
//------------------------------------------------------------------------------  
    private void AddShapefileLINELayer(String fName, String layer_name, java.awt.Color line_color, float line_width) {
        try {
            if (layer_name==null) return;
            
            
            File file = new File("shapefiles/" + fName);
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            
            //Style shpStyle = SLD.createLineStyle(line_color, 0.1f); 
            Style shpStyle = SLD.createLineStyle(line_color, line_width); 
            Layer layer_shape = new FeatureLayer(featureSource, shpStyle, layer_name);
            
            map.addLayer(layer_shape);
        } catch (Exception ex) {
        }  
    }
//------------------------------------------------------------------------------    
    private void AddShapefilePOLYGONLayer(String fName, String layer_name, java.awt.Color line_color, java.awt.Color background_color, float opacity) {
        try {
            File file = new File("shapefiles/" + fName);
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            Style shpStyle = SLD.createPolygonStyle(line_color, background_color, opacity); 
            Layer layer_shape = new FeatureLayer(featureSource, shpStyle, layer_name);
            
            map.addLayer(layer_shape);
        } catch (Exception ex) {
        }  
    }
//------------------------------------------------------------------------------           
    public void ShowSeismicNetwork(ArrayList network_stations) {
        try {
            if (network_stations==null) return;
            if (network_stations.isEmpty()) return;
            if (map==null) return;
            if (map.layers()==null) return;
//       
            java.awt.Color colore_stazione= java.awt.Color.GRAY;

            final SimpleFeatureType TYPE = DataUtilities.createType("location_net",
                    "location_net:Point:srid=4326,"
                    +                    
                    "name:String," // <- a String attribute
                    + // <- station data latency
                    "latency:double"
            );
           
            // Prima rimuove il vecchio layer della rete (Se c'è)
            for (int i = 0; i < map.layers().size(); i++) {
                if ((map.layers().get(i).getTitle()!=null) && (map.layers().get(i).getTitle().equalsIgnoreCase("Network"))) {
                    map.removeLayer(map.layers().get(i));
                }

            }

            //Uso la DefaultFeatureCollection
            DefaultFeatureCollection net;
            net = new DefaultFeatureCollection();

            //serve un featurebuilder per creare le features
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

            //Serve una geometryfactory per creare i point
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

            double lon, lat;
            String name;
//            SimpleFeature featuremirror;
//
//            org.locationtech.jts.geom.Point pointmirror;
            org.locationtech.jts.geom.Point point;
            SimpleFeature feature = featureBuilder.buildFeature("001");
            //Manage new featurebuilder
            featureBuilder.init(feature);

            for (int i = 0; i < network_stations.size(); i++) {
                lon = Double.parseDouble(((String[]) (network_stations.get(i)))[1]);
                lat = Double.parseDouble(((String[]) (network_stations.get(i)))[2]);
                
                double latency = Double.valueOf(((String[])network_stations.get(i))[3]);

                /* Longitude (= x coord) first ! */
                point = geometryFactory.createPoint(new Coordinate(lat, lon));
                name = ((String[]) (network_stations.get(i)))[0];
                try {
                    featureBuilder.add(point);
                    featureBuilder.add(name);
                    featureBuilder.add(latency);
                                     
                    feature = featureBuilder.buildFeature("feature-0-" + String.valueOf(i));
                    //Aggiungo la feature alla collection
                    net.add(feature);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                
            }
            //Creo lo stile dei punti da mostrare sul layer  
            Style stationStyle = createLatencyPointStyle();

            //Creo il layer indicando la collection e lostile
            Layer feautureLayer = new FeatureLayer(net, stationStyle, "Network");

            map.addLayer(feautureLayer);
            
            //map.moveLayer(map.layers().size() - 1, network_layer_index);
//
        } catch (SchemaException | FactoryRegistryException | NumberFormatException ex) {
            App.logger.error(ex.getMessage());
        }
    }   
//------------------------------------------------------------------------------ 
    private void ShowTriggeredStations(ArrayList stations_in_event, boolean showLabels){
        if (stations_in_event==null) return;
        if (stations_in_event.isEmpty()) return;

         try {
            //java.awt.Color colore_trigger = java.awt.Color.YELLOW;

            final SimpleFeatureType TYPE = DataUtilities.createType("location_trg",
                    "location_trg:Point:srid=4326,"
                    + // <- the geometry attribute: Point type
                    //                "location:Point:srid=9601:180," + // <- the geometry attribute: Point type                        
                    "name:String," // <- a String attribute 
                    + "used:String"
            );
           
            //Uso la DefaultFeatureCollection
            DefaultFeatureCollection triggers;
            triggers = new DefaultFeatureCollection();

            //serve un featurebuilder per creare le features
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

            //Serve una geometryfactory per creare i point
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

            double lon, lat;
            String name;
            String used;
        
            org.locationtech.jts.geom.Point point;
            SimpleFeature feature = featureBuilder.buildFeature("001");
            //Manage new featurebuilder
            featureBuilder.init(feature);

            for (int i = 0; i < stations_in_event.size(); i++) {
                
                lon = Double.parseDouble(((String[]) (stations_in_event.get(i)))[1]);
                lat = Double.parseDouble(((String[]) (stations_in_event.get(i)))[2]);
                used = String.valueOf(((String[]) (stations_in_event.get(i)))[3]);

                /* Longitude (= x coord) first ! */
                point = geometryFactory.createPoint(new Coordinate(lat, lon));
                name = ((String[]) (stations_in_event.get(i)))[0];
                featureBuilder.add(point);
                featureBuilder.add(name);
                featureBuilder.add(used);
                feature = featureBuilder.buildFeature("feature-0-" + String.valueOf(i));
                //Aggiungo la feature alla collection
                triggers.add(feature); 
            }
            //Creo lo stile dei punti da mostrare sul layer       
           Style stationStyle = createUsedStationStyle(showLabels); 

            //Creo il layer indicando la collection e lostile
            Layer feautureLayer = new FeatureLayer(triggers, stationStyle, "TriggeredStations");

            map.addLayer(feautureLayer); 
           // map.getViewport().setBounds(feautureLayer.getBounds());

        } catch (SchemaException | FactoryRegistryException | NumberFormatException ex) {
            App.logger.error(ex.getMessage());
        }
    } 
    
//------------------------------------------------------------------------------    
    private void ShowSelectedStations(ArrayList stations_in_event, boolean showLabels){
        if (stations_in_event==null) return;
        if (stations_in_event.isEmpty()) return;

         try {
            if (Selected_Stations==null)
                Selected_Stations =new ArrayList();
            else
                Selected_Stations.clear();
            //java.awt.Color colore_trigger = java.awt.Color.YELLOW;

            final SimpleFeatureType TYPE = DataUtilities.createType("location_sel",
                    "location_trg:Point:srid=4326,"
                    + // <- the geometry attribute: Point type
                    //                "location:Point:srid=9601:180," + // <- the geometry attribute: Point type                        
                    "name:String," // <- a String attribute 
                    + "selected:String"
            );
           
            //Uso la DefaultFeatureCollection
            DefaultFeatureCollection triggers;
            triggers = new DefaultFeatureCollection();

            //serve un featurebuilder per creare le features
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

            //Serve una geometryfactory per creare i point
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

            double lon, lat;
            String name;
            String used;
        
            org.locationtech.jts.geom.Point point;
            SimpleFeature feature = featureBuilder.buildFeature("099");
            //Manage new featurebuilder
            featureBuilder.init(feature);

            for (int i = 0; i < stations_in_event.size(); i++) {
                
                lon = Double.parseDouble(((String[]) (stations_in_event.get(i)))[1]);
                lat = Double.parseDouble(((String[]) (stations_in_event.get(i)))[2]);
                used = String.valueOf(((String[]) (stations_in_event.get(i)))[3]);

                /* Longitude (= x coord) first ! */
                point = geometryFactory.createPoint(new Coordinate(lat, lon));
                name = ((String[]) (stations_in_event.get(i)))[0];
                
                Selected_Stations.add(name);
                
                featureBuilder.add(point);
                featureBuilder.add(name);
                featureBuilder.add(used);
                feature = featureBuilder.buildFeature("feature-0-" + String.valueOf(i));
                //Aggiungo la feature alla collection
                triggers.add(feature); 
            }
            //Creo lo stile dei punti da mostrare sul layer       
           Style stationStyle = createSelectedStationStyle(showLabels); //SLD.createPointStyle("Triangle", java.awt.Color.RED, colore_trigger, 1, 14); 
          // Style stationStyle = SLD.createPointStyle("Triangle", java.awt.Color.RED, colore_trigger, 1, 14); 

            //Creo il layer indicando la collection e lostile
            Layer feautureLayer = new FeatureLayer(triggers, stationStyle, "SelectedStations");

            map.addLayer(feautureLayer); 
        } catch (SchemaException | FactoryRegistryException | NumberFormatException ex) {
            App.logger.error(ex.getMessage());
        }
    } 
//------------------------------------------------------------------------------    
    public void ShowCurrentStation(String[] Code_lon_lat){ //, boolean showLabels){

         try {
             int li = find_layer_index("CurrentStation");
             if (li!=-1) map.removeLayer(map.layers().get(li));
             
             
            java.awt.Color colore_stazione = java.awt.Color.RED;
            
            
            final SimpleFeatureType TYPE = DataUtilities.createType("cur_station",
                    "cur_station:Point:srid=4326,"
                    + // <- the geometry attribute: Point type
                    //                "location:Point:srid=9601:180," + // <- the geometry attribute: Point type                        
                    "name:String" // <- a String attribute 
            );
           
            //Uso la DefaultFeatureCollection
            DefaultFeatureCollection stazFC;
            stazFC = new DefaultFeatureCollection();

            //serve un featurebuilder per creare le features
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);

            //Serve una geometryfactory per creare i point
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

            double lon, lat;
            String name;
                  
            org.locationtech.jts.geom.Point point;
            SimpleFeature feature = featureBuilder.buildFeature("017");
            //Manage new featurebuilder
            featureBuilder.init(feature);

                
                
                lon = Double.parseDouble(Code_lon_lat[1]);
                lat = Double.parseDouble(Code_lon_lat[2]);
                

                /* Longitude (= x coord) first ! */
                point = geometryFactory.createPoint(new Coordinate(lat, lon));
                name = Code_lon_lat[0];
                featureBuilder.add(point);
                featureBuilder.add(name);
                
                feature = featureBuilder.buildFeature("feature-0-999");
                //Aggiungo la feature alla collection
                stazFC.add(feature); 
            
            //Creo lo stile dei punti da mostrare sul layer       
           //Style stationStyle = createUsedStationStyle(showLabels); //SLD.createPointStyle("Triangle", java.awt.Color.RED, colore_trigger, 1, 14); 
           Style stationStyle = SLD.createPointStyle("Triangle", java.awt.Color.YELLOW, colore_stazione, 1, 24); 

            //Creo il layer indicando la collection e lostile
            Layer feautureLayer = new FeatureLayer(stazFC, stationStyle, "CurrentStation");

            map.addLayer(feautureLayer); 
           
           doSetDisplayArea(map.getViewport().getBounds());

        } catch (SchemaException | FactoryRegistryException | NumberFormatException ex) {
            App.logger.error(ex.getMessage());
        }
    } 
//------------------------------------------------------------------------------    
    private Style createPointStyle(Mark mark, Color color_stroke, Color color_fill, int point_size, boolean ShowLabels) {
        Graphic gr = styleFactory.createDefaultGraphic();
        mark.setStroke(styleFactory.createStroke(
                filterFactory.literal(color_stroke), filterFactory.literal(1)));

        mark.setFill(styleFactory.createFill(filterFactory.literal(color_fill)));

        gr.graphicalSymbols().clear();

        gr.graphicalSymbols().add(mark);
        gr.setSize(filterFactory.literal(point_size));

        /*
         * Setting the geometryPropertyName arg to null signals that we want to
         * draw the default geometry of features
         */
        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);

        StyleBuilder sb = new StyleBuilder(styleFactory);
        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(sym);
        if (ShowLabels) {
            TextSymbolizer textSym = sb.createTextSymbolizer(Color.YELLOW, sb.createFont("Arial", false, true, 15), filterFactory.property("name").toString()); 

            Fill fill = styleFactory.createFill(
                    filterFactory.literal(color_stroke),
                    filterFactory.literal(0.5));

            textSym.setHalo(styleFactory.createHalo(fill, Expression.NIL));
            rule.symbolizers().add(textSym);
        }

        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(new Rule[]{rule});

        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }
    
    
    private Style createLatencyPointStyle() { 

        //based on Options.get_latency_colors create rules graphics and marks
        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<Graphic> grs = new ArrayList<>();
        ArrayList<Mark> marks = new ArrayList<>();
        //ArrayList<PointSymbolizer> symbs = new ArrayList<>();
        Mark mark1;

        String[] latency_colors = App.G.options.get_latency_colors();
        Integer lower_bound = -10000000;
        Integer upper_bound = 10000000;

        for (String latency_color : latency_colors) {  //Nel GT ho saltato la prima tupla
            String[] lat_color = latency_color.split(","); //0 lat, 1 R,2 G,3 B

            upper_bound = Integer.parseInt(lat_color[0]);
            Mark mark = styleFactory.getTriangleMark(); //  .getCircleMark();
            mark.setStroke(styleFactory.createStroke(
                    filterFactory.literal(Color.BLACK), filterFactory.literal(1)));
            mark.setFill(styleFactory.createFill(filterFactory.literal(
                    new Color(Integer.parseInt(lat_color[1]), Integer.parseInt(lat_color[2]), Integer.parseInt(lat_color[3])))));
            marks.add(mark);

            Graphic gr = styleFactory.createDefaultGraphic();
            gr.graphicalSymbols().clear();
            gr.graphicalSymbols().add(mark);
            gr.setSize(filterFactory.literal(10));
            grs.add(gr);

            Rule rule = styleFactory.createRule();
            rules.add(rule);

            PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);
            //symbs.add(sym);
            Filter filter;

            filter = filterFactory.between(filterFactory.property("latency"), filterFactory.literal(lower_bound), filterFactory.literal(upper_bound));
            
            rule.setFilter(filter);
            rule.symbolizers().add(sym);
            //move bound for next check
            lower_bound = upper_bound;

        }

        Rule[] rule_array = rules.toArray(new Rule[rules.size()]);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(rule_array);
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }
    
    private Style createUsedStationStyle(boolean showLabels) { 

        ArrayList<Rule> rules = new ArrayList<>();
        
        Mark mark = styleFactory.getSquareMark();
        mark.setStroke(styleFactory.createStroke(filterFactory.literal(Color.RED), filterFactory.literal(1)));
        mark.setFill(styleFactory.createFill(filterFactory.literal(Color.YELLOW)));

        Graphic gr = styleFactory.createDefaultGraphic();
        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);

        gr.setSize(filterFactory.literal(12));
      
        Rule rule = styleFactory.createRule();
        
        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);
        
        Filter filter;
        filter = filterFactory.equals(filterFactory.property("used"), filterFactory.literal("T"));

        rule.setFilter(filter);
        rule.symbolizers().add(sym);
        if (showLabels) {
            StyleBuilder sb = new StyleBuilder(styleFactory);
            TextSymbolizer textSym = sb.createTextSymbolizer(Color.YELLOW, sb.createFont("Arial", false, true, 11), filterFactory.property("name").toString()); 

                Fill fill = styleFactory.createFill(
                        filterFactory.literal(Color.black),
                        filterFactory.literal(0.5));

                textSym.setHalo(styleFactory.createHalo(fill, filterFactory.literal(4)));
                rule.symbolizers().add(textSym);
        }
        rules.add(rule);

        Mark mark2 = styleFactory.getSquareMark();
        mark2.setStroke(styleFactory.createStroke(
                filterFactory.literal(Color.BLACK), filterFactory.literal(1)));
        mark2.setFill(styleFactory.createFill(filterFactory.literal(Color.LIGHT_GRAY))); //255.220.120
        Graphic gr2 = styleFactory.createDefaultGraphic();
        gr2.graphicalSymbols().clear();
        gr2.graphicalSymbols().add(mark2);
        gr2.setSize(filterFactory.literal(9));
      
        Rule rule2 = styleFactory.createRule();
        if (showLabels) {
            StyleBuilder sb = new StyleBuilder(styleFactory);
            TextSymbolizer textSym = sb.createTextSymbolizer(Color.WHITE, sb.createFont("Arial", false, true, 10), filterFactory.property("name").toString()); 

                Fill fill = styleFactory.createFill(
                        filterFactory.literal(Color.black),
                        filterFactory.literal(0.5));

                textSym.setHalo(styleFactory.createHalo(fill, filterFactory.literal(4)));
                rule2.symbolizers().add(textSym);
        }
        rules.add(rule2);

        PointSymbolizer sym2 = styleFactory.createPointSymbolizer(gr2, null);
        
        Filter filter2;
        filter2 = filterFactory.equals(filterFactory.property("used"), filterFactory.literal("F"));

        rule2.setFilter(filter2);
        rule2.symbolizers().add(sym2);

        Rule[] rule_array = rules.toArray(new Rule[rules.size()]);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(rule_array);
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }
    
    private Style createSelectedStationStyle(boolean showLabels) { 

        ArrayList<Rule> rules = new ArrayList<>();
        
        Mark mark = styleFactory.getSquareMark();
        mark.setStroke(styleFactory.createStroke(filterFactory.literal(Color.RED), filterFactory.literal(1)));
        mark.setFill(styleFactory.createFill(filterFactory.literal(Color.RED)));

        Graphic gr = styleFactory.createDefaultGraphic();
        gr.graphicalSymbols().clear();
        gr.graphicalSymbols().add(mark);

        gr.setSize(filterFactory.literal(10));
      
        Rule rule = styleFactory.createRule();
        

        PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);
        
        Filter filter;
        filter = filterFactory.equals(filterFactory.property("selected"), filterFactory.literal("T"));

        rule.setFilter(filter);
        rule.symbolizers().add(sym);
        if (showLabels) {
            StyleBuilder sb = new StyleBuilder(styleFactory);
            TextSymbolizer textSym = sb.createTextSymbolizer(Color.RED, sb.createFont("Arial", false, true, 11), filterFactory.property("name").toString()); 

                Fill fill = styleFactory.createFill(
                        filterFactory.literal(Color.black),
                        filterFactory.literal(0.5));

                textSym.setHalo(styleFactory.createHalo(fill, filterFactory.literal(4)));
                rule.symbolizers().add(textSym);
        }
        rules.add(rule);

        Mark mark2 = styleFactory.getSquareMark();
        mark2.setStroke(styleFactory.createStroke(
                filterFactory.literal(Color.BLACK), filterFactory.literal(1)));
        mark2.setFill(styleFactory.createFill(filterFactory.literal(Color.LIGHT_GRAY))); //255.220.120
        Graphic gr2 = styleFactory.createDefaultGraphic();
        gr2.graphicalSymbols().clear();
        gr2.graphicalSymbols().add(mark2);
        gr2.setSize(filterFactory.literal(9));
      
        Rule rule2 = styleFactory.createRule();
        rules.add(rule2);

        PointSymbolizer sym2 = styleFactory.createPointSymbolizer(gr2, null);
        
        Filter filter2;
        filter2 = filterFactory.equals(filterFactory.property("selected"), filterFactory.literal("F"));

        rule2.setFilter(filter2);
        rule2.symbolizers().add(sym2);

        Rule[] rule_array = rules.toArray(new Rule[rules.size()]);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(rule_array);
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }
    
    private void ShowHypocenter(Double lat, Double lon){
        try {
            if ((lat==0) && (lon==0)) return;

            SimpleFeatureType TYPE = DataUtilities.createType("Location_hypo",
                    "location:Point:srid=4326,"
                    + // <- the geometry attribute: Point type
                    //                "location:Point:srid=9601:180," + // <- the geometry attribute: Point type                        
                    "name:String" // <- a String attribute
            );
        
            SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
            SimpleFeature feature; // = featureBuilder.buildFeature("001");

            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
            org.locationtech.jts.geom.Point point = geometryFactory.createPoint(new Coordinate(lon, lat));

            Style hypocenterStyle = SLD.createPointStyle("Star", java.awt.Color.YELLOW, java.awt.Color.RED, 1, 20);

            DefaultFeatureCollection epi = new DefaultFeatureCollection();

            featureBuilder.add(point);
            featureBuilder.add("HYPOCENTER");
            feature = featureBuilder.buildFeature("feature-0-" + String.valueOf(0));

            epi.add(feature);
            
            Layer feautureLayer = new FeatureLayer(epi, hypocenterStyle, "Hypocenter");

            map.addLayer(feautureLayer);       
        } catch (Exception ex) {
            //
        } 
    }
    
    private Style createRGBStyle() {
        GridCoverage2D cov = null;
        try {
            cov = reader.read(null);
        } catch (IOException giveUp) {
            throw new RuntimeException(giveUp);
        }
        // We need at least three bands to create an RGB style
        int numBands = cov.getNumSampleDimensions();
        if (numBands < 3) {
            return null;
        }
        // Get the names of the bands
        String[] sampleDimensionNames = new String[numBands];
        for (int i = 0; i < numBands; i++) {
            GridSampleDimension dim = cov.getSampleDimension(i);
            sampleDimensionNames[i] = dim.getDescription().toString();
        }
        final int RED = 0, GREEN = 1, BLUE = 2;
        int[] channelNum = {-1, -1, -1};
        // We examine the band names looking for "red...", "green...", "blue...".
        // Note that the channel numbers we record are indexed from 1, not 0.
        for (int i = 0; i < numBands; i++) {
            String name = sampleDimensionNames[i].toLowerCase();
            if (name != null) {
                if (name.matches("red.*")) {
                    channelNum[RED] = i + 1;
                } else if (name.matches("green.*")) {
                    channelNum[GREEN] = i + 1;
                } else if (name.matches("blue.*")) {
                    channelNum[BLUE] = i + 1;
                }
            }
        }
        // If we didn't find named bands "red...", "green...", "blue..."
        // we fall back to using the first three bands in order
        if (channelNum[RED] < 0 || channelNum[GREEN] < 0 || channelNum[BLUE] < 0) {
            channelNum[RED] = 1;
            channelNum[GREEN] = 2;
            channelNum[BLUE] = 3;
        }
        // Now we create a RasterSymbolizer using the selected channels
        SelectedChannelType[] sct = new SelectedChannelType[cov.getNumSampleDimensions()];
        ContrastEnhancement ce = styleFactory.contrastEnhancement(filterFactory2.literal(1.0), ContrastMethod.NORMALIZE);
        for (int i = 0; i < 3; i++) {
            sct[i] = styleFactory.createSelectedChannelType(String.valueOf(channelNum[i]), ce);
        }
        RasterSymbolizer sym = styleFactory.getDefaultRasterSymbolizer();
        ChannelSelection sel = styleFactory.channelSelection(sct[RED], sct[GREEN], sct[BLUE]);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);
    }
    
    private Style createGreyscaleStyle(int band) {
        ContrastEnhancement ce = styleFactory.contrastEnhancement(filterFactory2.literal(1.0), ContrastMethod.NORMALIZE);
        SelectedChannelType sct = styleFactory.createSelectedChannelType(String.valueOf(band), ce);

        RasterSymbolizer sym = styleFactory.getDefaultRasterSymbolizer();
        ChannelSelection sel = styleFactory.channelSelection(sct);
        sym.setChannelSelection(sel);

        return SLD.wrapSymbolizers(sym);

    } 
//------------------------------------------------------------------------------
    /**
     * @return the map
     */
    public MapContent getMap() {
        return map;
    }
//------------------------------------------------------------------------------
    /**
     * @param map the map to set
     */
    public void setMap(MapContent map) {
        map = map;
    }
//------------------------------------------------------------------------------
    public void SetMapBounds(double x1, double x2, double y1, double y2){
        try {
            Hints hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, true);
//            
            CRSAuthorityFactory factory = ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", hints);  
            CoordinateReferenceSystem crs = factory.createCoordinateReferenceSystem("EPSG:63266405");
//
            map.getViewport().setCoordinateReferenceSystem(crs);
//            
            map.getViewport().setBounds(new ReferencedEnvelope(x1,x2, y1, y2, crs));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
//------------------------------------------------------------------------------    
    public void SetMapBounds(int idLayer){
        try {
            if (map==null) return;
            Hints hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, true);
//            
            CRSAuthorityFactory factory = ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", hints);  
            CoordinateReferenceSystem crs = factory.createCoordinateReferenceSystem("EPSG:63266405");
//
            map.getViewport().setCoordinateReferenceSystem(crs);
            
            ReferencedEnvelope env = map.layers().get(idLayer).getBounds();
            if (env.getMinX()==env.getMaxX()){
                // Envelope puntiforme
                env.setBounds(new ReferencedEnvelope(env.getMinX()-2.0, env.getMaxX()+2, env.getMinY()-1, env.getMaxY()+1, crs));
            }
            
            map.getViewport().setBounds(env);
//          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
//------------------------------------------------------------------------------    
    public void CreateWMS(){
        URL url;
        WebMapServer wms;
        try {
            url = new URL(App.G.options.getWMSURL());
            
            wms = new WebMapServer(url);
            WMSCapabilities capabilities = wms.getCapabilities();
//
//            String serverName = capabilities.getService().getName();
//            String serverTitle = capabilities.getService().getTitle();
//            App.logger.debug("Capabilities retrieved from server: " + serverName + " (" + serverTitle + ")");
//
//            if (capabilities.getRequest().getGetFeatureInfo() != null) {
//              //This server supports GetFeatureInfo requests!
//              // We could make one if we wanted to.
//            }
//            
//            //Get formats for GetMap operation
//            List<String> formats = wms.getCapabilities().getRequest().getGetMap().getFormats();
//            
//            //gets the top most layer, which will contain all the others
//            org.geotools.ows.wms.Layer rootLayer = capabilities.getLayer();
//
//            //gets all the layers in a flat list, in the order they appear in
//            //the capabilities document (so the rootLayer is at index 0)
//            List layers = capabilities.getLayerList();
//
//            // WMSUtils
//            org.geotools.ows.wms.Layer[] layers2 = WMSUtils.getNamedLayers(capabilities);
//
//            // Styles
//            for(int i=0; i< layers2.length; i++){
//                // Print layer info
//                System.out.println("Layer: ("+i+")"+layers2[i].getName());
//                System.out.println("       "+layers2[i].getTitle());
//                System.out.println("       "+layers2[i].getChildren().length);
//                System.out.println("       "+layers2[i].getBoundingBoxes());
//                CRSEnvelope env = layers2[i].getLatLonBoundingBox();
//                System.out.println("       "+env.getLowerCorner()+" x "+env.getUpperCorner());
//
//                // Get layer styles
//                List styles = layers2[i].getStyles();
//                for (Iterator it = styles.iterator(); it.hasNext();) {
//                   StyleImpl elem = (StyleImpl) it.next();
//
//                    // Print style info
//                    System.out.println("Style:");
//                    System.out.println("  Name:"+elem.getName());
//                    System.out.println("  Title:"+elem.getName()); //  .getTitle());
//                }
//              }

            
////            // Making a GetMap request is more interesting than looking at WMSCapabilities.
////            GetMapRequest request = wms.createGetMapRequest();
////            request.setFormat("image/png");
////            request.setDimensions("600", "400"); //sets the dimensions of the image to be returned from the server
////            request.setTransparent(true);
////            request.setSRS("EPSG:4326");
////           
////            // -5,30, 34, 50
////            //request.setBBox("-131.13151509433965,46.60532747661736,-117.61620566037737,56.34191403281659");
////            request.setBBox("-4,34,30,49");
////            //Note: you can look at the layer metadata to determine a layer's bounding box for a SRS
////            
//            for ( org.geotools.ows.wms.Layer layer : WMSUtils.getNamedLayers(capabilities) ) {
//                request.addLayer(layer);
//  
//            }
            
            map.addLayer(new WMSLayer(wms, WMSUtils.getNamedLayers(capabilities)[1]));
            
//            GetMapResponse response = (GetMapResponse) wms.issueRequest(request);
//            BufferedImage image = ImageIO.read(response.getInputStream());
//            //map.addLayer(image);
            
            
//            List<org.geotools.ows.wms.Layer> wmsLayers = WMSLayerChooser.showSelectLayer(wms);
//            int j=0;
//            for (org.geotools.ows.wms.Layer wmsLayer : WMSUtils.getNamedLayers(capabilities)) {
//                j++;
//                System.out.println("adding " + wmsLayer.getTitle());
//                WMSLayer displayLayer = new WMSLayer(wms, wmsLayer);
//                displayLayer.setTitle("Titolo " + j);
//                map.addLayer(displayLayer);
//            }
            

////////////////            WMSLayer displayLayer3 = new WMSLayer(wms, layers2[3]);
////////////////            //displayLayer3.setStyle(createGreyscaleStyle(3));
////////////////            displayLayer3.setTitle("SHADED");
////////////////            map.addLayer(displayLayer3);
            
//            for (int i =0; i < layers2.length; i++) {
//                WMSLayer displayLayer = new WMSLayer(wms, layers2[i]);
//                displayLayer.setTitle("");
//                map.addLayer(displayLayer);
//            }
            
 
          //  
            
                       
            /*
            If you want to request the map with some of its available styles use the appropriate addLayer method:

            Layer[] layers = WMSUtils.getNamedLayers(capabilities);
            List styles = layers[2].getStyles();
            Style style = styles.get(2);

            request.addLayer(layers[2]);
            request.addLayer(layers[2], style);
            request.addLayer(layers[2], style.getName());
            request.addLayer(layers[2].getName(), style);


            Retrieving valid formats for the GetMap operation:

            URL serverURL = new URL(...);

            WebMapServer wms = new WebMapServer(serverURL);

            WMSCapabilities capabilities = wms.getCapabilities();

            //Get formats for GetMap operation
            String[] formats = wms.getCapabilities().getRequest().getGetMap().getFormatStrings()


            */
          
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
          
        } 
    }
    public void CreateWMS_old(){
        URL url = null;
        try {
            // https://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv
          //url = new URL("https://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&Request=GetCapabilities&Service=WMS");
          url = new URL(App.G.options.getWMSURL() + "?&Request=GetCapabilities&Service=WMS");
        } catch (MalformedURLException e) {
          //will not happen
        }

        WebMapServer wms = null;
        try {
            wms = new WebMapServer(url);
            WMSCapabilities capabilities = wms.getCapabilities();

            String serverName = capabilities.getService().getName();
            String serverTitle = capabilities.getService().getTitle();
            App.logger.debug("Capabilities retrieved from server: " + serverName + " (" + serverTitle + ")");

            if (capabilities.getRequest().getGetFeatureInfo() != null) {
              //This server supports GetFeatureInfo requests!
              // We could make one if we wanted to.
            }
            
            //Get formats for GetMap operation
            List<String> formats = wms.getCapabilities().getRequest().getGetMap().getFormats();
            
            //gets the top most layer, which will contain all the others
            org.geotools.ows.wms.Layer rootLayer = capabilities.getLayer();

            //gets all the layers in a flat list, in the order they appear in
            //the capabilities document (so the rootLayer is at index 0)
            List layers = capabilities.getLayerList();

            // WMSUtils
            org.geotools.ows.wms.Layer[] layers2 = WMSUtils.getNamedLayers(capabilities);

            // Styles
            for(int i=0; i< layers2.length; i++){
                // Print layer info
                System.out.println("Layer: ("+i+")"+layers2[i].getName());
                System.out.println("       "+layers2[i].getTitle());
                System.out.println("       "+layers2[i].getChildren().length);
                System.out.println("       "+layers2[i].getBoundingBoxes());
                CRSEnvelope env = layers2[i].getLatLonBoundingBox();
                System.out.println("       "+env.getLowerCorner()+" x "+env.getUpperCorner());

                // Get layer styles
                List styles = layers2[i].getStyles();
                for (Iterator it = styles.iterator(); it.hasNext();) {
                   StyleImpl elem = (StyleImpl) it.next();

                    // Print style info
                    System.out.println("Style:");
                    System.out.println("  Name:"+elem.getName());
                    System.out.println("  Title:"+elem.getName()); //  .getTitle());
                }
              }

            
//            // Making a GetMap request is more interesting than looking at WMSCapabilities.
//            GetMapRequest request = wms.createGetMapRequest();
//            request.setFormat("image/png");
//            request.setDimensions("600", "400"); //sets the dimensions of the image to be returned from the server
//            request.setTransparent(true);
//            request.setSRS("EPSG:4326");
//           
//            // -5,30, 34, 50
//            //request.setBBox("-131.13151509433965,46.60532747661736,-117.61620566037737,56.34191403281659");
//            request.setBBox("-5,34,30,50");
//            //Note: you can look at the layer metadata to determine a layer's bounding box for a SRS
//            
//            for ( org.geotools.ows.wms.Layer layer : WMSUtils.getNamedLayers(capabilities) ) {
//                request.addLayer(layer);
//            }
//            
//            GetMapResponse response = (GetMapResponse) wms.issueRequest(request);
//            BufferedImage image = ImageIO.read(response.getInputStream());
            
            
            List<org.geotools.ows.wms.Layer> wmsLayers = WMSLayerChooser.showSelectLayer(wms);
            int j=0;
            for (org.geotools.ows.wms.Layer wmsLayer : wmsLayers) {
                j++;
                System.out.println("adding " + wmsLayer.getTitle());
                WMSLayer displayLayer = new WMSLayer(wms, wmsLayer);
                displayLayer.setTitle("Titolo " + j);
                map.addLayer(displayLayer);
            }
            

////////////////            WMSLayer displayLayer3 = new WMSLayer(wms, layers2[3]);
////////////////            //displayLayer3.setStyle(createGreyscaleStyle(3));
////////////////            displayLayer3.setTitle("SHADED");
////////////////            map.addLayer(displayLayer3);
            
//            for (int i =0; i < layers2.length; i++) {
//                WMSLayer displayLayer = new WMSLayer(wms, layers2[i]);
//                displayLayer.setTitle("");
//                map.addLayer(displayLayer);
//            }
            
 
          //  
            
                       
            /*
            If you want to request the map with some of its available styles use the appropriate addLayer method:

            Layer[] layers = WMSUtils.getNamedLayers(capabilities);
            List styles = layers[2].getStyles();
            Style style = styles.get(2);

            request.addLayer(layers[2]);
            request.addLayer(layers[2], style);
            request.addLayer(layers[2], style.getName());
            request.addLayer(layers[2].getName(), style);


            Retrieving valid formats for the GetMap operation:

            URL serverURL = new URL(...);

            WebMapServer wms = new WebMapServer(serverURL);

            WMSCapabilities capabilities = wms.getCapabilities();

            //Get formats for GetMap operation
            String[] formats = wms.getCapabilities().getRequest().getGetMap().getFormatStrings()


            */
          
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
          
        } 
    }
//------------------------------------------------------------------------------
    public void BlinkStationsSet(ArrayList<String[]> Selected_Stations){
        try {
            //ShowCurrentStation((String[])Selected_Stations.get(0)); 
            //ShowSelectedStations(Selected_Stations, true);
            
            this.ShowCurrentStation(Selected_Stations.get(0));
            repaint = true;
        }catch (Exception ex){}
    }
//------------------------------------------------------------------------------    
    public void TurnStationsOFF(){
        
    }
//------------------------------------------------------------------------------
    /**
     * @return the canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
//------------------------------------------------------------------------------
    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(Canvas in_canvas) {
        this.canvas = in_canvas;
    }
//------------------------------------------------------------------------------       
    /*
     *initial for mouse event 
     */
    private void initEvents() {
            /*
             * setting the original coordinate
             */
            getCanvas().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {  
                        DirectPosition2D screen_clickPos;
                        baseDragX = e.getSceneX(); 
                        baseDragY = e.getSceneY();

                        switch (getACTION()){
                            case "SELECT": case "ZOOMIN":

                                anchor.setX(e.getX());
                                anchor.setY(e.getY()+45);
                                selection.setX(e.getX());
                                selection.setY(e.getY()+45);
                                selection.setFill(null); // transparent 
                                if (ACTION.equalsIgnoreCase("SELECT"))
                                    selection.setStroke(javafx.scene.paint.Color.RED); 
                                else
                                    selection.setStroke(javafx.scene.paint.Color.YELLOW); 
                                
                                selection.getStrokeDashArray().add(5.0);
//                                
                                if (((Pane)getCanvas().getParent()).getChildren().contains(selection)) 
                                    ((Pane)getCanvas().getParent()).getChildren().remove(selection);
                                ((Pane)getCanvas().getParent()).getChildren().add(selection);
                                
                                screen_clickPos = new DirectPosition2D(e.getX(), e.getY());
                                clickPos = new DirectPosition2D();
                                
                                map.getViewport().getScreenToWorld().transform(screen_clickPos, clickPos);

                                break;
                            case "IDENTIFY":
                                try {
                                    screen_clickPos = new DirectPosition2D(e.getX(), e.getY());
                                    clickPos = new DirectPosition2D();
                                    map.getViewport().getScreenToWorld().transform(screen_clickPos, clickPos);
                                    String STAZ = selectFeaturesByClick();  
                                    if (STAZ!=null)
                                        App.G.WavesControllers.get(idWavesController).ActivateStation(STAZ);
                                } catch (Exception ex) {
                                    App.logger.error("Error while identifying station:\n" + ex.getMessage());
                                }
                                break;
                            default:
                                
                                break;
                        }
                        e.consume();

                    }
            });
            /*
             * translate according to the mouse drag
             */
            getCanvas().addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {

                        double difX = e.getSceneX() - baseDragX;
                        double difY = e.getSceneY() - baseDragY;

                        baseDragX = e.getSceneX();
                        baseDragY = e.getSceneY();

                        switch (getACTION()){
                            case "SELECT": case "ZOOMIN":
                                if (ACTION.equalsIgnoreCase("SELECT")){
                                    gc.setFill(javafx.scene.paint.Color.BLUE);
                                    gc.setStroke(javafx.scene.paint.Color.RED);
                                } else {
                                    gc.setFill(javafx.scene.paint.Color.BLUE);
                                    gc.setStroke(javafx.scene.paint.Color.YELLOW);
                                }

                                gc.setLineWidth(2);

                                selection.setWidth(Math.abs(e.getX() - anchor.getX()));
                                selection.setHeight(Math.abs(e.getY() - anchor.getY()+45));
                                selection.setX(Math.min(anchor.getX(), e.getX()));
                                selection.setY(Math.min(anchor.getY(), e.getY()+45));

                                break;
                            default:
                                DirectPosition2D newPos = new DirectPosition2D(difX, difY);
                                DirectPosition2D result = new DirectPosition2D();
                                map.getViewport().getScreenToWorld().transform(newPos, result);
                                ReferencedEnvelope env = new ReferencedEnvelope(map.getViewport().getBounds());
                                env.translate(env.getMinimum(0) - result.x, env.getMaximum(1) - result.y);
                                doSetDisplayArea(env);
                        }

                        e.consume();

                    }
            });

            getCanvas().addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent e) {

                        switch (getACTION()){
                            case "SELECT": case "ZOOMIN":

                                
                                double difX = e.getSceneX() - baseDragX;
                                double difY = e.getSceneY() - baseDragY;

                                ((Pane)getCanvas().getParent()).getChildren().remove(selection);
                                selection.setWidth(0);
                                selection.setHeight(0);

                                DirectPosition2D newPos = new DirectPosition2D(difX, difY);
                                DirectPosition2D result = new DirectPosition2D();
                                map.getViewport().getScreenToWorld().transform(newPos, result);
                                ReferencedEnvelope env = new ReferencedEnvelope(map.getViewport().getBounds());
                                env.translate(env.getMinimum(0) - result.x, env.getMaximum(1) - result.y);
                                
                                DirectPosition2D screen_relPos = new DirectPosition2D(e.getX(), e.getY());
                                releasePos = new DirectPosition2D();
                                
                                map.getViewport().getScreenToWorld().transform(screen_relPos, releasePos);
                                                                        
                                if (ACTION.equalsIgnoreCase("SELECT"))
                                    selectFeatures();
                                else {
                                    if (clickPos.x!=releasePos.x && clickPos.y!=releasePos.y)
                                        env = new ReferencedEnvelope(clickPos.x, releasePos.x, clickPos.y, releasePos.y, map.getViewport().getCoordinateReferenceSystem());
                                }
                                setACTION("NONE");
                                doSetDisplayArea(env);
                                
                                
//                                if (((AnchorPane)getCanvas().getParent()).getId().equalsIgnoreCase("anchor_top_right") && Selected_Stations!=null && !Selected_Stations.isEmpty()){
//                                    // We are in the waveforms window
//                                    for (int i=0; i< Selected_Stations.size(); i++){
//                                        System.out.println(Selected_Stations.get(i));
//                                    }
//                                }

                                break;
                            default:
                                

                        }
                    }
            });


            /*
             * double clicks to restore to original map
             */
            getCanvas().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        switch(ACTION){
                            case "RULER_1_pending":

                                ruler_start = new DirectPosition2D();
                                map.getViewport().getScreenToWorld().transform(new DirectPosition2D(t.getX(), t.getY()), ruler_start);

                                ACTION = "RULER_2_pending";
                                break;
                            case "RULER_2_pending":
                                ruler_end = new DirectPosition2D();
                                map.getViewport().getScreenToWorld().transform(new DirectPosition2D(t.getX(), t.getY()), ruler_end);
                                righello(ruler_start, ruler_end);
                                ACTION ="";
                                break;   
                            default:
                                if (t.getClickCount() > 1) {
                                    //doSetDisplayArea(map.getMaxBounds());
                                    SetMapBounds(App.G.options.get_box_minLon(),App.G.options.get_box_maxLon(), 
                                        App.G.options.get_box_minLat(),App.G.options.get_box_maxLat());
                                    repaint = true;
                                }
                                break;
                        }
                            
                            t.consume();
                    }
            });          
            /*
             * scroll for zoom in and out
             */
            getCanvas().addEventHandler(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {

                    @Override
                    public void handle(ScrollEvent e) {
                            ReferencedEnvelope envelope = map.getViewport().getBounds();
                            double percent = e.getDeltaY() / getCanvas().getWidth();
                            double width = envelope.getWidth();
                            double height = envelope.getHeight();
                            double deltaW = width * percent;
                            double deltaH = height * percent;
                            envelope.expandBy(deltaW, deltaH);
                            doSetDisplayArea(envelope);
                            e.consume();
                    }
            });
	}
    
    
    
    private void righello(DirectPosition2D start, DirectPosition2D end){
            // the following code is based on JTS.orthodromicDistance( start, end, crs )
            try {
                GeodeticCalculator geoCalc = new GeodeticCalculator(map.getCoordinateReferenceSystem());
                start.setCoordinateReferenceSystem(map.getCoordinateReferenceSystem());
                end.setCoordinateReferenceSystem(map.getCoordinateReferenceSystem());
                //gc.setStartingPosition(JTS.toDirectPosition(start, map.getCoordinateReferenceSystem()));
                //gc.setDestinationPosition(JTS.toDirectPosition(end, map.getCoordinateReferenceSystem()));
                geoCalc.setStartingPosition(start);
                geoCalc.setDestinationPosition(end);

                double distance = geoCalc.getOrthodromicDistance();

                int totalmeters = (int) distance;
                int km = totalmeters / 1000;
                int meters = totalmeters - (km * 1000);
                float remaining_cm = (float) (distance - totalmeters) * 10000;
                remaining_cm = Math.round(remaining_cm);
                float cm = remaining_cm / 100;

                //System.out.println("Distance = " + km + "km " + meters + "m " + cm + "cm");
                pfxDialog.ShowInformationMessage("DISTANCE: " + km + "km " + meters + "m " , null);
                
            } catch (Exception ex) {
                App.logger.error(ex.getMessage());
            }
        
    }
//------------------------------------------------------------------------------
    /**
     * @return the ACTION
     */
    public String getACTION() {
        return ACTION;
    }
//------------------------------------------------------------------------------
    /**
     * @param ACTION the ACTION to set
     */
    public void setACTION(String ACTION) {
        this.ACTION = ACTION;
    }
//------------------------------------------------------------------------------       
    private void selectFeatures() { 
        Envelope2D env = new Envelope2D();
        if (getLayerIndex("Network")<0) return;
        
        featureSource = map.layers().get(getLayerIndex("Network")).getFeatureSource();
        
        env.setFrameFromDiagonal(clickPos, releasePos);
         
        Set<FeatureId> IDs;
        SimpleFeatureCollection selectedFeatures;
        SimpleFeatureIterator iter;
        
        ReferencedEnvelope bbox = new ReferencedEnvelope(
                env.getFrame(), 
                map.getCoordinateReferenceSystem());
        /*
         * Create a Filter to select features that intersect with
         * the bounding box
         */
        Filter filter = filterFactory2.intersects(filterFactory2.property("location_net"), filterFactory2.literal(bbox));

        if (Selected_Stations==null)
                Selected_Stations =new ArrayList();
            else
                Selected_Stations.clear();
        
        /*
         * Use the filter to identify the selected features 
         */
        
        try {
            selectedFeatures = (SimpleFeatureCollection)featureSource.getFeatures(filter);
            iter = selectedFeatures.features();
            IDs = new HashSet<>();
            try {
                while (iter.hasNext()) {
                    SimpleFeature feature = iter.next();
                    IDs.add(feature.getIdentifier()); //Find IDs filtered
                    
                    String s = feature.getValue().toArray()[1].toString();
                    s = s.substring(s.lastIndexOf("=")+1);
                    Selected_Stations.add(s);
                }

            } finally {
                iter.close();
            }

            displaySelectedFeatures(IDs, map.layers().get(getLayerIndex("Network")), ff); 

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
//------------------------------------------------------------------------------    
    private String selectFeaturesByClick() { 
        Envelope2D env;
        String s=null;

        featureSource = map.layers().get(getLayerIndex("Network")).getFeatureSource();
        
        env = new Envelope2D(map.getCoordinateReferenceSystem(),
                        clickPos.x,
                        clickPos.y,
                        0.1,
                        0.1
                        );
  
        Set<FeatureId> IDs;
        SimpleFeatureCollection selectedFeatures;
        SimpleFeatureIterator iter;
        
        ReferencedEnvelope bbox = new ReferencedEnvelope(
                env.getFrame(), 
                map.getCoordinateReferenceSystem());
        /*
         * Create a Filter to select features that intersect with
         * the bounding box
         */
        Filter filter = filterFactory2.intersects(filterFactory2.property("location_net"), filterFactory2.literal(bbox));
//
        try {
            selectedFeatures = (SimpleFeatureCollection)featureSource.getFeatures(filter);
            iter = selectedFeatures.features();
            IDs = new HashSet<>();
            try {
                int cont=0; // Serve per estrarre solo la prima staz risultante dal click
                while ((iter.hasNext()) && cont==0) {
                    SimpleFeature feature = iter.next();
                    IDs.add(feature.getIdentifier()); //Find IDs filtered
                    
                    s = feature.getValue().toArray()[1].toString();
                    s = s.substring(s.lastIndexOf("=")+1);                   
                    cont++;
                }

            } finally {
                iter.close();
            }
            return s; 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
//------------------------------------------------------------------------------    
    public void displaySelectedFeatures(Set<FeatureId> IDs, Layer layer, FilterFactory2 ff) {
        Style style=null;
        if (IDs.isEmpty()) {
            style = createDefaultStyle();
        } else {
            //style = createSelectedStyle(IDs, ff);
            style = createSelectedAndLatenciesStyle(IDs);
        }
        
        //Layer layer = getMapPane().getMapContent().layers().get(3); //3 il layer di FCP_ALL
        ((FeatureLayer) layer).setStyle(style);
        
    }
//------------------------------------------------------------------------------    
public Style createSelectedAndLatenciesStyle(Set<FeatureId> IDs) { 

        //based on Options.get_latency_colors create rules graphics and marks
        ArrayList<Rule> rules = new ArrayList<>();
        ArrayList<Graphic> grs = new ArrayList<>();
        ArrayList<Mark> marks = new ArrayList<>();
        ArrayList<PointSymbolizer> symbs = new ArrayList<>();
        Mark mark1;

        String[] latency_colors = App.G.options.get_latency_colors();
        Integer lower_bound = -10000000;
        Integer upper_bound = 10000000;

        Rule selectedRule = createRule(ff, Color.WHITE, Color.MAGENTA, 2, 18, 1);
        selectedRule.setFilter(filterFactory2.id(IDs));
        rules.add(selectedRule);
            
        for (String latency_color : latency_colors) {              
            String[] lat_color = latency_color.split(","); //0 lat, 1 R,2 G,3 B

            upper_bound = Integer.parseInt(lat_color[0]);
            Mark mark = styleFactory.getTriangleMark(); //   .getCircleMark();
            mark.setStroke(styleFactory.createStroke(
                    filterFactory.literal(Color.BLACK), filterFactory.literal(1)));
            mark.setFill(styleFactory.createFill(filterFactory.literal(
                    new Color(Integer.parseInt(lat_color[1]), Integer.parseInt(lat_color[2]), Integer.parseInt(lat_color[3])))));
            marks.add(mark);

            Graphic gr = styleFactory.createDefaultGraphic();
            gr.graphicalSymbols().clear();
            gr.graphicalSymbols().add(mark);
            gr.setSize(filterFactory.literal(10));
            grs.add(gr);

            Rule rule = styleFactory.createRule();
            rules.add(rule);

            PointSymbolizer sym = styleFactory.createPointSymbolizer(gr, null);
            symbs.add(sym);
            Filter filter;

            filter = filterFactory.between(filterFactory.property("latency"), filterFactory.literal(lower_bound), filterFactory.literal(upper_bound));
            rule.setFilter(filter);
            rule.symbolizers().add(sym);
            //move bound for next check
            lower_bound = upper_bound;

        }

        Rule[] rule_array = rules.toArray(new Rule[rules.size()]);
        FeatureTypeStyle fts = styleFactory.createFeatureTypeStyle(rule_array);
        Style style = styleFactory.createStyle();
        style.featureTypeStyles().add(fts);

        return style;
    }
//------------------------------------------------------------------------------    
    private Rule createRule( FilterFactory2 ff, Color outlineColor, Color fillColor, float line_width, float point_size, float opacity ) {
        Symbolizer symbolizer = null;
        Fill fill = null;
        Stroke stroke = styleFactory.createStroke(filterFactory2.literal(outlineColor), filterFactory2.literal(line_width));
        fill = styleFactory.createFill(filterFactory2.literal(fillColor), filterFactory2.literal(opacity));

        Mark mark = styleFactory.getTriangleMark();
        mark.setFill(fill);
        mark.setStroke(stroke);

        Graphic graphic = styleFactory.createDefaultGraphic();
        graphic.graphicalSymbols().clear();
        graphic.graphicalSymbols().add(mark);
        graphic.setSize(filterFactory2.literal(point_size));

        String nome = this.featureSource.getSchema().getGeometryDescriptor().getLocalName();
        symbolizer = styleFactory.createPointSymbolizer(graphic, nome);

        Rule rule = styleFactory.createRule();
        rule.symbolizers().add(symbolizer);
        return rule;
    }
//------------------------------------------------------------------------------    
    private static Style createDefaultStyle() {
         Style stationStyle = SLD.createPointStyle("Triangle", Color.BLACK, Color.ORANGE, 1, 7);       
         return stationStyle;
    }
//------------------------------------------------------------------------------    
    public void RefreshMap(){
        repaint = true;
    }

    /**
     * @return the Selected_Stations
     */
    public ArrayList<String> getSelected_Stations() {
        return Selected_Stations;
    }

    /**
     * @param Selected_Stations the Selected_Stations to set
     */
    public void setSelected_Stations(ArrayList Selected_Stations) {
        this.Selected_Stations = Selected_Stations;
    }
    
    
    public void changeNetPointsSize() {
   
        try {
            StyleBuilder styleBuilder = new StyleBuilder();
            Style style = map.layers().get(getLayerIndex("Network")).getStyle();

            // Creare un nuovo Symbolizer con la dimensione aggiornata
            Graphic gr = styleBuilder.createGraphic();
            gr.setSize(filterFactory.literal(20)); // Imposta la nuova dimensione

            //PointSymbolizer pointSymbolizer = styleBuilder.createPointSymbolizer(gr, "ps");
            for (int idRule=0; idRule<style.featureTypeStyles().get(0).rules().size(); idRule++){
                Symbolizer[] arr = style.featureTypeStyles().get(0).rules().get(idRule).getSymbolizers();
                PointSymbolizer pointSymbolizer=(PointSymbolizer)arr[0];

                pointSymbolizer.getGraphic().setSize(styleBuilder.literalExpression(20));
            }
            
            // Imposta il nuovo stile sul layer
            ((FeatureLayer)(map.layers().get(getLayerIndex("Network")))).setStyle(style);
            
            repaint = true;
        } catch (Exception ex){
            int k=0;
            k++;
        }
    }
    
    
    
//    public int getInfo(DirectPosition2D in_pos) throws Exception {
//        int result=0;
//        String attrName="location_net";
//        Geometries geomType = Geometries.POINT; // Geometries.POLYGON; //
//        SimpleFeatureIterator iter;
//        Set<FeatureId> IDs;
//        SimpleFeatureCollection selectedFeatures;
//        
//        
//        featureSource = map.layers().get(getLayerIndex("Network")).getFeatureSource();
//        
//            Filter filter = null;
////            if (geomType == Geometries.POLYGON || geomType == Geometries.MULTIPOLYGON) {
////                Geometry posGeom = createSearchPoint(pos);
////                filter = filterFactory.intersects(filterFactory.property(attrName).getPropertyName(), (org.opengis.geometry.Geometry)filterFactory.literal(posGeom).getValue());
////
////            } else {
////                ReferencedEnvelope env = createSearchEnv(pos);
////                //filter = filterFactory.bbox(filterFactory.property(attrName).getPropertyName(), env);
////                filter = filterFactory.bbox(filterFactory.property(attrName).getPropertyName(), env.getMinX(), env.getMinY(), env.getMaxX(), env.getMaxY(), map.getCoordinateReferenceSystem().toString());
////            }
//            
//DirectPosition2D pos=new DirectPosition2D();
//map.getViewport().getScreenToWorld().transform(in_pos, pos);
//
//            ReferencedEnvelope bbox; // = createSearchEnv(pos);
//            
//            bbox =
//                new ReferencedEnvelope(
//                        pos.x - 3.5,
//                        pos.x + 3.5,
//                        pos.y - 3.5,
//                        pos.y + 3.5,
//                        map.getCoordinateReferenceSystem());
//            
//            
//            
////            ReferencedEnvelope bbox = new ReferencedEnvelope(
////                env.getFrame(), 
////                map.getCoordinateReferenceSystem());
//        /*
//         * Create a Filter to select features that intersect with
//         * the bounding box
//         */
//            filter = filterFactory2.intersects(filterFactory2.property("location_net"), filterFactory2.literal(bbox));
//
////            Query query = new Query(null, filter);  
////            query.setCoordinateSystemReproject(map.getCoordinateReferenceSystem());
////            
////            Collection<PropertyDescriptor> descriptors = map.layers().get(network_layer_index).getFeatureSource().getSchema().getDescriptors();
////
////            FeatureCollection queryResult = map.layers().get(network_layer_index).getFeatureSource().getFeatures(query);
//
//        try {
//            selectedFeatures = (SimpleFeatureCollection)featureSource.getFeatures(filter);
//            iter = selectedFeatures.features();
//            IDs = new HashSet<>();
//            try {
//                while (iter.hasNext()) {
//                    SimpleFeature feature = iter.next();
//                    IDs.add(feature.getIdentifier()); //Find IDs filtered
//                    
//                    String s = feature.getValue().toArray()[1].toString();
//                    s = s.substring(s.lastIndexOf("=")+1);
//                    Selected_Stations.add(s);
//                    
//                    //System.out.println(s);
//                }
//
//            } finally {
//                iter.close();
//            }
//
//            //displaySelectedFeatures(IDs, map.layers().get(network_layer_index), ff); 
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            
//        }
//        return result;
//    }
    
    /**
     * Converts the query position, in map content coordinates, to a position in layer coordinates
     * and returns it as a JTS {@code Point}.
     *
     * @param pos query position in map content coordaintes
     * @return point in layer coordinates
     */
    private Geometry createSearchPoint(DirectPosition2D pos) {
        try {
            
            GeometryFactory geometryFactory =
            JTSFactoryFinder.getGeometryFactory(null);
            
            DirectPosition2D trPos = new DirectPosition2D();
            getContentToLayerTransform().transform(pos, trPos);
            Geometry point = geometryFactory.createPoint(new Coordinate(trPos.x, trPos.y));
            return point;

        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    private ReferencedEnvelope createSearchEnv(DirectPosition2D pos) {
        double DEFAULT_DISTANCE_FRACTION = 0.3d; //0.01d;
        Layer layer = map.layers().get(getLayerIndex("Network"));
        
        ReferencedEnvelope mapBounds = map.getViewport().getBounds();
        if (mapBounds == null || mapBounds.isEmpty()) {
            // fall back to layer bounds
            
            if (layer == null) {
                // this should never happen
                throw new IllegalStateException("Target layer has been lost");
            }
            mapBounds = map.layers().get(getLayerIndex("Network")).getBounds();
        }

        double halfWidth =
                0.5 * DEFAULT_DISTANCE_FRACTION * (mapBounds.getWidth() + mapBounds.getHeight());

        CoordinateReferenceSystem contentCRS = map.getCoordinateReferenceSystem();
        ReferencedEnvelope env =
                new ReferencedEnvelope(
                        pos.x - halfWidth,
                        pos.x + halfWidth,
                        pos.y - halfWidth,
                        pos.y + halfWidth,
                        contentCRS);

        if (isTransformRequired()) {
            CoordinateReferenceSystem layerCRS =
                    layer.getFeatureSource().getSchema().getCoordinateReferenceSystem();

            try {
                env = env.transform(layerCRS, true);
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }

        return env;
    }
    
    protected MathTransform getContentToLayerTransform() {
        MathTransform transform = null;
        

            Layer layer = map.layers().get(getLayerIndex("Network"));

            if (map != null && layer != null) {
                CoordinateReferenceSystem contentCRS = map.getCoordinateReferenceSystem();
                CoordinateReferenceSystem layerCRS =
                        layer.getFeatureSource().getSchema().getCoordinateReferenceSystem();

                if (contentCRS != null && layerCRS != null) {
                    if (CRS.equalsIgnoreMetadata(contentCRS, layerCRS)) {
                        transform = new AffineTransform2D(new AffineTransform());

                    } else {
                        try {
                            transform = CRS.findMathTransform(contentCRS, layerCRS, true);
                        } catch (Exception ex) {
                            LOGGER.warning("Can't transform map content CRS to layer CRS");
                            //transformFailed = true;
                        }
                    }
                }

            } else {
                // one or both of content and layer CRS is null
                transform = new AffineTransform2D(new AffineTransform());
            }
       

        return transform;
    }
    
    protected boolean isTransformRequired() {
        return !getContentToLayerTransform().isIdentity();
    }

    /**
     * @return the idWavesController
     */
    public int getIdWavesController() {
        return idWavesController;
    }

    /**
     * @param idWavesController the idWavesController to set
     */
    public void setIdWavesController(int idWavesController) {
        this.idWavesController = idWavesController;
    }
//------------------------------------------------------------------------------    
    public int getLayerIndex(String LayerName){
        try{
            if (map==null) return -1;
            if (map.layers()==null || map.layers().isEmpty()) return -1;
            for (int i=0; i<map.layers().size(); i++){
                if (map.layers().get(i).getTitle().equalsIgnoreCase(LayerName)) 
                    return i;
            }
            
            return -1;
        } catch (Exception ex){
            return -1;
        }
    }
//------------------------------------------------------------------------------
        public void ZoomMapOut(double percent){
            ReferencedEnvelope env; // = new ReferencedEnvelope(map.getViewport().getBounds());
            double xratio = (map.getViewport().getBounds().getMaxX()-  map.getViewport().getBounds().getMinX())*percent/100d;
            double yratio = (map.getViewport().getBounds().getMaxY()-  map.getViewport().getBounds().getMinY())*percent/100d;
            
            
            env = new ReferencedEnvelope(map.getViewport().getBounds().getMinX()-xratio, 
                map.getViewport().getBounds().getMaxX()+xratio, 
                    map.getViewport().getBounds().getMinY() - yratio, 
                    map.getViewport().getBounds().getMaxY() +yratio, 
                    map.getViewport().getCoordinateReferenceSystem());
                        
            doSetDisplayArea(env);
        }
//------------------------------------------------------------------------------        
}
