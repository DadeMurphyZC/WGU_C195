/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import c195_schedulingapp.Model.Appointment;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import static c195_schedulingapp.utils.DB.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class AddAppointmentController implements Initializable {
    
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
    
    private HashMap contactOptions = new HashMap();
    private ArrayList customerOptions = new ArrayList();
    private ArrayList descriptionOptions = new ArrayList();
    private HashMap locationOptions = new HashMap();
    private ArrayList<LocalTime> times = new ArrayList<>();
    
    //populates available times
    private ArrayList generateHours(){
        ArrayList<LocalTime> times = new ArrayList<>();
        for(int i=8; i<=17; i++){
            times.add(LocalTime.of(i, 0, 0));
        }
        return times;
    }
    
    //populates the combobox options
    private void setComboBoxes() throws ClassNotFoundException, SQLException{
        descriptionOptions.add("First Consultation");
        descriptionOptions.add("First Meeting");
        descriptionOptions.add("Follow-up");
        contactOptions = getUsers();
        customerOptions = getCustomersArray();
        locationOptions = getCities();
        contactOptions.forEach((k,v)->contact.getItems().add(v));
        customerOptions.forEach((c)->customer.getItems().add(c));
        descriptionOptions.forEach((d)->description.getItems().add(d));
        locationOptions.forEach((k,v)->location.getItems().add(v));
        generateHours().forEach((t)->startTime.getItems().add(t));
        generateHours().forEach((t)->endTime.getItems().add(t));
    }
    
    @FXML void testAdd() throws ParseException{
        LocalDate dateValue = date.getValue();
        LocalDateTime ldtTest = LocalDateTime.of(dateValue, LocalTime.parse(startTime.getValue().toString()));
        LocalDateTime ldt = LocalDateTime.of(date.getValue(), LocalTime.of(Integer.parseInt(startTime.getValue().toString().substring(0, 2)),0));
        System.out.println("Start Time: "+ldtTest);
        System.out.println(contact.getSelectionModel().getSelectedItem());
        System.out.println(customer.getSelectionModel().getSelectedItem());
        System.out.println(description.getSelectionModel().getSelectedItem());
        System.out.println(location.getSelectionModel().getSelectedItem());
        System.out.println(startTime.getSelectionModel().getSelectedItem());
        System.out.println(endTime.getSelectionModel().getSelectedItem());
        System.out.println(title.getText());
        System.out.println(date.getValue());
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {setComboBoxes();} 
        catch (ClassNotFoundException | SQLException ex) {System.out.println("Ex: "+ex);}
    }    
}
