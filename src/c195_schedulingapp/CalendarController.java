/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.appStage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableColumn calendarCol;

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
    
    @FXML private void getMonth(){
        System.out.println(months.getSelectionModel().getSelectedItem().toString());
    }
    
    private void printWeeks(int max){
        String m = months.getSelectionModel().getSelectedItem().toString();
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
