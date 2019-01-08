/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.appStage;
import static c195_schedulingapp.C195_SchedulingApp.state;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import c195_schedulingapp.utils.AppointmentRow;
import static c195_schedulingapp.utils.DB.getAppointments;
import static c195_schedulingapp.utils.DB.searchAppointment;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class AppointmentsController implements Initializable {
    
    @FXML private TableColumn<AppointmentRow, String> idCol;
    @FXML private TableColumn<AppointmentRow, String> customerCol;
    @FXML private TableColumn<AppointmentRow, String> titleCol;
    @FXML private TableColumn<AppointmentRow, String> descriptionCol;
    @FXML private TableColumn<AppointmentRow, String> locationCol;
    @FXML private TableColumn<AppointmentRow, String> contactCol;
    @FXML private TableColumn<AppointmentRow, String> urlCol;
    @FXML private TableColumn<AppointmentRow, String> startCol;
    @FXML private TableColumn<AppointmentRow, String> endCol;
    @FXML private TableView appointmentsTable;
    @FXML private Button customersBtn;
    public static AppointmentRow selected = new AppointmentRow();
    
    public static ObservableList<AppointmentRow> appointments = FXCollections.observableArrayList();
    
    @FXML public void openCustomers() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
    
    @FXML public void addAppointment() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    private void loadAppointment() throws ClassNotFoundException, SQLException{
        state.clearTempAppointment();
        state.clearTempIndex();
        selected = (AppointmentRow) appointmentsTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected: "+selected.getAppointmentId());
        state.setTempAppointment(searchAppointment(Integer.parseInt(selected.getAppointmentId().getValue())));
    }
    
    @FXML public void editAppointment() throws IOException, ClassNotFoundException, SQLException{
        loadAppointment();
        state.setTempIndex(appointmentsTable.getSelectionModel().getSelectedIndex());
        Parent root = FXMLLoader.load(getClass().getResource("EditAppointment.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ResultSet rs = getAppointments();
            idCol.setCellValueFactory(cellData -> {return cellData.getValue().getCustomerId();});
            customerCol.setCellValueFactory(cellData -> {return cellData.getValue().getCustomerId();});
            titleCol.setCellValueFactory(cellData -> {return cellData.getValue().getTitle();});
            descriptionCol.setCellValueFactory(cellData -> {return cellData.getValue().getDescription();});
            locationCol.setCellValueFactory(cellData -> {return cellData.getValue().getLocation();});
            contactCol.setCellValueFactory(cellData -> {return cellData.getValue().getContact();});
            startCol.setCellValueFactory(cellData -> {return cellData.getValue().getStart();});
            endCol.setCellValueFactory(cellData -> {return cellData.getValue().getEnd();});
            try{
                while (rs.next()) {
                    String appointmentId = rs.getString("appointmentId");
                    String customerId = rs.getString("customerId");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    String location = rs.getString("location");
                    String contact = rs.getString("contact");
                    String _url = rs.getString("url");
                    Time start = rs.getTime("start");
                    Time end = rs.getTime("end");
                    AppointmentRow tr = new AppointmentRow(
                            new ReadOnlyStringWrapper(appointmentId),
                            new ReadOnlyStringWrapper(customerId),
                            new ReadOnlyStringWrapper(title),
                            new ReadOnlyStringWrapper(description),
                            new ReadOnlyStringWrapper(location),
                            new ReadOnlyStringWrapper(contact),
                            new ReadOnlyStringWrapper(_url),
                            new ReadOnlyObjectWrapper(start),
                            new ReadOnlyObjectWrapper(end)
                    );
                    appointments.add(tr);
                }
                appointmentsTable.setItems(appointments);
            } catch (SQLException ex) {
                Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
