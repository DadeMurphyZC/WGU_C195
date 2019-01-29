/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.appStage;
import c195_schedulingapp.utils.AppointmentRow;
import static c195_schedulingapp.utils.DB.getApptsByMonth;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class CalendarController implements Initializable {

    @FXML
    private ComboBox months;
    @FXML
    private Button getMonthlyBtn;
    @FXML
    private Button getWeeklyBtn;
    @FXML
    private TableView calendarTable;
    @FXML
    private TableColumn<AppointmentRow, String> calendarCol;
    @FXML
    private TableColumn week1col;
    @FXML
    private TableColumn week2col;
    @FXML
    private TableColumn week3col;
    @FXML
    private TableColumn week4col;
    @FXML
    private TableColumn week5col;
    @FXML
    private VBox vbox;
    
    public static ObservableList<AppointmentRow> appts = FXCollections.observableArrayList();
    
    @FXML
    public void openAppointments() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Appointments.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    public void openReports() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reports.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
        
    @FXML
    public void openCustomers() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    private void setMonths() {
        months.getItems().clear();
        months.getItems().setAll("January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "Obtober",
                "November",
                "December"
        );
    }
    
    @FXML private void getMonth() throws ClassNotFoundException, SQLException{
        calendarCol.setText(months.getSelectionModel().getSelectedItem().toString());
        calendarCol.setCellValueFactory(cellData -> {return cellData.getValue().getContact();});
        calendarCol.setVisible(true);
        week1col.setVisible(false);
        week2col.setVisible(false);
        week3col.setVisible(false);
        week4col.setVisible(false);
        week5col.setVisible(false);
        calendarTable.getItems().clear();
        ResultSet rs = getApptsByMonth(months.getSelectionModel()
                .getSelectedItem()
                .toString());
        System.out.println("MONTH: "+months.getSelectionModel().getSelectedItem().toString());
        try{
            while(rs.next()){
                String id = String.valueOf(rs.getInt("appointmentid"));
                AppointmentRow ar = new AppointmentRow();
                ar.setAppointmentId(new ReadOnlyStringWrapper(id));
                appts.add(ar);
            }
            calendarTable.setItems(appts);
        }
        catch (SQLException ex) {
                System.out.println("Ex: "+ex);
            }
    }
    
    private void printWeeks(int max){
        String m = months.getSelectionModel().getSelectedItem().toString();
        week1col.setText(m+" "+1+" - "+m+" "+7);
        week2col.setText(m+" "+8+" - "+m+" "+14);
        week3col.setText(m+" "+15+" - "+m+" "+21);
        if(max<29){
            week4col.setText(m+" "+22+" - "+m+" "+max);
        } else {
            week4col.setText(m+" "+22+" - "+m+" "+28);
            week5col.setText(m+" "+29+" - "+m+" "+max);
        }
        
        System.out.println("Week of "+m+" "+1+" - "+m+" "+7);
        System.out.println("Week of "+m+" "+8+" - "+m+" "+14);
        System.out.println("Week of "+m+" "+15+" - "+m+" "+21);
        if(max<29){
            System.out.println("Week of "+m+" "+22+" - "+m+" "+max);
        } else {
            System.out.println("Week of "+m+" "+22+" - "+m+" "+28);
            System.out.println("Week of "+m+" "+29+" - "+m+" "+max);
        }
    }
    
    @FXML private void getWeek(){
        calendarCol.setVisible(false);
        week1col.setVisible(true);
        week2col.setVisible(true);
        week3col.setVisible(true);
        week4col.setVisible(true);
        week5col.setVisible(true);
        String m = months.getSelectionModel().getSelectedItem().toString();
        int max = Month.valueOf(m.toUpperCase()).maxLength();
        printWeeks(max);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMonths();
    }

}
