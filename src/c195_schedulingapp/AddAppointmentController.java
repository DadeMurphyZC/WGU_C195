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
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        generateHours().forEach((t)->startTime.getItems().add((LocalTime)t));
        generateHours().forEach((t)->endTime.getItems().add(t));
    }
    
    @FXML void testAdd() throws ParseException, ClassNotFoundException, SQLException{
        LocalDateTime start = LocalDateTime.of(date.getValue(), LocalTime.parse(startTime.getValue().toString()));
        String startFormatted = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")
                        .toFormat()
                        .format(start);
        
        LocalDateTime end = LocalDateTime.of(date.getValue(), LocalTime.parse(endTime.getValue().toString()));
        String endFormatted = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")
                        .toFormat()
                        .format(end);
        
        Appointment a = new Appointment();
        a.setCustomerid(1);
        a.setTitle(title.getText());
        a.setDescription(description.getSelectionModel().getSelectedItem().toString());
        a.setLocation(location.getSelectionModel().getSelectedItem().toString());
        a.setContact(contact.getSelectionModel().getSelectedItem().toString());
        a.setUrl("test");
        a.setStart(startFormatted);
        a.setEnd(endFormatted);
        addAppointment(a);
    }
    
    @FXML void testPrint(){
        LocalDateTime dt = LocalDateTime.of(date.getValue(), LocalTime.parse(startTime.getValue().toString()));
        String fdt = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")
                        .toFormat()
                        .format(dt);
        System.out.println(fdt);
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
