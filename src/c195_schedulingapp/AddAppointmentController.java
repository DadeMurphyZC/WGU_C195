/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
