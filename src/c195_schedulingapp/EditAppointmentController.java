/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import c195_schedulingapp.Model.Appointment;
import static c195_schedulingapp.C195_SchedulingApp.state;
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
public class EditAppointmentController implements Initializable {
    
    @FXML private ComboBox contact;
    @FXML private ComboBox customer;
    @FXML private ComboBox description;
    @FXML private ComboBox location;
    @FXML private ComboBox startTime;
    @FXML private ComboBox endTime;
    @FXML private TextField title;
    @FXML private DatePicker date;
    @FXML private Button save;
    @FXML private Button cancel;
    
    public void populateForm(){
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Appointment appt = state.getTempAppointment();
        contact.getSelectionModel().select(appt.getContact());
        customer.getSelectionModel().select(appt.getCustomerid());
        description.getSelectionModel().select(appt.getDescription());
        location.getSelectionModel().select(appt.getLocation());
        startTime.getSelectionModel().select(appt.getStart());
        endTime.getSelectionModel().select(appt.getEnd());
        
    }    
    
}
