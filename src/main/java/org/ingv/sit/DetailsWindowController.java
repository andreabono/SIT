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
package org.ingv.sit;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.ingv.dante.model.ObjectOrigin;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private AnchorPane anchor_main;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClose_Click(ActionEvent event) {
        try {
            // get a handle to the stage
            Stage stage = (Stage) btnClose.getScene().getWindow();
            // do what you have to do
            stage.close();  
        } catch (Exception ex) {
        }
    }
    
    public void ShowData(ObjectOrigin origin) {
        
        if (origin==null) return;
        
        // sample data
        Map<String, String> map = new HashMap<>();

        map.put("origin", origin.toString());

        ShowMapInTable(map);
    }
    
    private void ShowMapInTable(Map map) {
        // use fully detailed type for Map.Entry<String, String> 
        TableColumn<Map.Entry<String, String>, String> column1 = new TableColumn<>("Key");
        column1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                // this callback returns property for just one cell, you can't use a loop here
                // for first column we use key
                return new SimpleStringProperty(p.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<String, String>, String> column2 = new TableColumn<>("Value");
        column2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> p) {
                // for second column we use value
                return new SimpleStringProperty(p.getValue().getValue());
            }
        });

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(map.entrySet());
        final TableView<Map.Entry<String,String>> table = new TableView<>(items);

        table.getColumns().setAll(column1, column2);
        table.getSortOrder().addAll(column1);
                
        this.anchor_main.getChildren().add(table);
        AnchorPane.setBottomAnchor(table, 50.0);
        AnchorPane.setTopAnchor(table, 7.0);
                   AnchorPane.setLeftAnchor(table, 7.0);
                   AnchorPane.setRightAnchor(table, 7.0);
    }
    
}
