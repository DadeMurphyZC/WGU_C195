/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import c195_schedulingapp.Model.Appointment;
import c195_schedulingapp.utils.AppointmentRow;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import static c195_schedulingapp.AppointmentsController.appointments;
import c195_schedulingapp.Model.Customer;
import c195_schedulingapp.utils.DB;
import static c195_schedulingapp.utils.DB.getApptStartTimes;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private ComboBox contact;
    @FXML
    private ComboBox customer;
    @FXML
    private ComboBox description;
    @FXML
    private ComboBox location;
    @FXML
    private ComboBox startTime;
    @FXML
    private ComboBox endTime;
    @FXML
    private TextField title;
    @FXML
    private DatePicker date;
    @FXML
    private Button save;
    @FXML
    private Button cancel;

    private HashMap contactOptions = new HashMap();
    private ArrayList customerOptions = new ArrayList();
    private ArrayList descriptionOptions = new ArrayList();
    private HashMap locationOptions = new HashMap();
    private ArrayList<LocalTime> times = new ArrayList<>();

    //populates available times
    private ArrayList generateHours() {
        ArrayList<LocalTime> times = new ArrayList<>();
        for (int i = 1; i < 24; i++) {
            times.add(LocalTime.of(i, 0, 0));
        }
        return times;
    }

    //populates the combobox options
    private void setComboBoxes() throws ClassNotFoundException, SQLException {
        descriptionOptions.add("First Consultation");
        descriptionOptions.add("First Meeting");
        descriptionOptions.add("Follow-up");
        contactOptions = getUsers();
        customerOptions = getCustomersArray();
        locationOptions = getCities();
        //LAMBDA EXPRESSION USED IN forEach AS A for loop ALTERNATIVE
        contactOptions.forEach((k, v) -> contact.getItems().add(v));
        customerOptions.forEach((c) -> customer.getItems().add(c));
        descriptionOptions.forEach((d) -> description.getItems().add(d));
        locationOptions.forEach((k, v) -> location.getItems().add(v));
        generateHours().forEach((t) -> startTime.getItems().add((LocalTime) t));
        generateHours().forEach((t) -> endTime.getItems().add(t));
    }

    @FXML
    void testAdd() throws ParseException, ClassNotFoundException, SQLException{
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
        Integer checkFormatStart = Integer.parseInt(DateTimeFormatter.ofPattern("H").toFormat().format(start));
        Integer checkFormatEnd = Integer.parseInt(DateTimeFormatter.ofPattern("H").toFormat().format(end));

        if (checkFormatStart < 8 || checkFormatEnd > 17) {
            Alert hoursCheck = new Alert(Alert.AlertType.ERROR);
            hoursCheck.setContentText("Start and End hours must fall between 08:00am and 05:00pm");
            hoursCheck.setTitle("Business hours error");
            hoursCheck.show();
            throw new IllegalArgumentException("Hour out of range!");
        };
        
        if(getApptStartTimes().contains(startFormatted+".0")){
            Alert startConflict = new Alert(Alert.AlertType.ERROR);
            startConflict.setContentText("An appointment with that date and start time already exists");
            startConflict.setTitle("Date/Time Conflict");
            startConflict.show();
            throw new IllegalArgumentException("Date/Start conflict - already exists");
        }
        Appointment a = new Appointment();
        a.setAppointmentId(DB.nextAppointmentId());
        a.setCustomerid(getCustomerId(customer.getSelectionModel().getSelectedItem().toString()));
        a.setTitle(title.getText());
        a.setDescription(description.getSelectionModel().getSelectedItem().toString());
        a.setLocation(location.getSelectionModel().getSelectedItem().toString());
        a.setContact(contact.getSelectionModel().getSelectedItem().toString());
        a.setUrl("test");
        a.setStart(startFormatted);
        a.setEnd(endFormatted);
        addAppointment(a);
        String startTime = DateTimeFormatter
                .ofPattern("HH:mm:ss")
                .toFormat()
                .format(start);
        String endTime = DateTimeFormatter
                .ofPattern("HH:mm:ss")
                .toFormat()
                .format(end);
        Customer c = getCustomerFullClass(customer.getSelectionModel().getSelectedItem().toString());
        Button _url = new Button();
                    _url.setText("Get Customer");
                    JOptionPane pane = new JOptionPane();
                    _url.setOnAction((ActionEvent e) -> {
                        pane.showMessageDialog(null, "Name: " + c.getCustomerName() //+getCustomerName(Integer.parseInt(customerId))
                                +"\nAddress: " + c.getAddress().getAddress()//+tempC.getAddress().getAddress()
                                +"\nPhone: " + c.getPhone()
                                +"\nCity: " + c.getCity()
                                +"\nZip: " + c.getPostalCode());//+tempC.getAddress().getPhone());
                        });
        AppointmentRow tr = new AppointmentRow(
                new SimpleObjectProperty(a.getAppointmentId()),
                new SimpleStringProperty(String.valueOf(a.getCustomerid())),
                new SimpleStringProperty(a.getTitle()),
                new SimpleStringProperty(a.getDescription()),
                new SimpleStringProperty(a.getLocation()),
                new SimpleStringProperty(a.getContact()),
                new SimpleObjectProperty(_url),
                new SimpleStringProperty(startTime),
                new SimpleStringProperty(endTime)
        );
        appointments.add(tr);
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML
    void testPrint() {
        LocalDateTime dt = LocalDateTime.of(date.getValue(), LocalTime.parse(startTime.getValue().toString()));
        String fdt = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .toFormat()
                .format(dt);
        System.out.println(fdt);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setComboBoxes();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }
}
