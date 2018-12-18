/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class AddAppointmentController implements Initializable {
    
    @FXML private ComboBox customer;
    @FXML private TextField title;
    @FXML private ComboBox description;
    @FXML private ComboBox location;
    @FXML private ComboBox contact;
    @FXML private DatePicker date;
    @FXML private ComboBox startTime;
    @FXML private ComboBox endTime;
    @FXML private Button save;
    @FXML private Button cancel;

    ObservableList<String> customerOptions = FXCollections.observableArrayList(
            "Customer 1",
            "Customer 2",
            "Customer 3"
    );
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customer.setItems(customerOptions);
        
    }    
    
}
