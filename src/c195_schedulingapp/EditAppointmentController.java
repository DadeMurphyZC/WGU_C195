/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.AppointmentsController.appointments;
import c195_schedulingapp.Model.Appointment;
import static c195_schedulingapp.C195_SchedulingApp.state;
import c195_schedulingapp.utils.AppointmentRow;
import static c195_schedulingapp.utils.DB.getCities;
import static c195_schedulingapp.utils.DB.getCustomersArray;
import static c195_schedulingapp.utils.DB.getUsers;
import static c195_schedulingapp.utils.DB.updateAppointment;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

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
    @FXML private ComboBox title;
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
    
    public void populateForm(){
        Appointment appt = state.getTempAppointment();
        contact.getSelectionModel().select(appt.getContact());
        title.getSelectionModel().select(appt.getTitle());
        customer.getSelectionModel().select(appt.getCustomerid());
        description.getSelectionModel().select(appt.getDescription());
        location.getSelectionModel().select(appt.getLocation());
        date.setValue(LocalDate.parse(state.getTempAppointment().getStart().substring(0, 10)));
        startTime.getSelectionModel().select(appt.getStart().substring(11, appt.getStart().length()-3));
        endTime.getSelectionModel().select(appt.getEnd().substring(11, appt.getEnd().length()-3));
    }
    
    @FXML private void updateAppt() throws ClassNotFoundException, SQLException{
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
        updateAppointment(
                state.getTempAppointment().getAppointmentId(),
                title.getSelectionModel().getSelectedItem().toString(),
                description.getSelectionModel().getSelectedItem().toString(),
                location.getSelectionModel().getSelectedItem().toString(),
                contact.getSelectionModel().getSelectedItem().toString(),
                Timestamp.valueOf(startFormatted),
                Timestamp.valueOf(endFormatted)
                );
        AppointmentRow ar = new AppointmentRow();
        ar.setAppointmentId(new SimpleObjectProperty(String.valueOf(state.getTempAppointment().getAppointmentId())));
        ar.setCustomerId(new SimpleStringProperty(String.valueOf(state.getTempAppointment().getCustomerid())));
        ar.setContact(new SimpleStringProperty(contact.getSelectionModel().getSelectedItem().toString()));
        ar.setDescription(new SimpleStringProperty(description.getSelectionModel().getSelectedItem().toString()));
        ar.setEnd(new SimpleStringProperty(Timestamp.valueOf(end).toString()));
        ar.setLocation(new SimpleStringProperty(location.getSelectionModel().getSelectedItem().toString()));
        ar.setStart(new SimpleStringProperty(Timestamp.valueOf(start).toString()));
        ar.setTitle(new SimpleStringProperty(title.getSelectionModel().getSelectedItem().toString()));
        appointments.set(state.getTempIndex(), ar);
        state.clearTempCustomer();
        state.clearTempIndex();
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
    
    @FXML public void cancel(){
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateForm();
            setComboBoxes();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }    
    
}
