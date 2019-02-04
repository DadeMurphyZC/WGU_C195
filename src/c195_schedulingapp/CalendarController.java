/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.appStage;
import static c195_schedulingapp.utils.DB.getApptsByMonth;
import static c195_schedulingapp.utils.DB.getApptsByWeekRange;
import c195_schedulingapp.utils.ReportRow;
import c195_schedulingapp.utils.Week1Row;
import c195_schedulingapp.utils.Week2Row;
import c195_schedulingapp.utils.Week3Row;
import c195_schedulingapp.utils.Week4Row;
import c195_schedulingapp.utils.Week5Row;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
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
    private TableColumn<ReportRow, String> calendarCol;
    @FXML
    private TableColumn<ReportRow, String> week1col;
    @FXML
    private TableColumn<ReportRow, String> week2col;
    @FXML
    private TableColumn<ReportRow, String> week3col;
    @FXML
    private TableColumn<ReportRow, String> week4col;
    @FXML
    private TableColumn<ReportRow, String> week5col;
    @FXML
    private VBox vbox;
    
    public static ObservableList appts = FXCollections.observableArrayList();
    
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
                "October",
                "November",
                "December"
        );
    }
    
    @FXML private void getMonth() throws ClassNotFoundException, SQLException{
        appts.clear();
        try{
            ResultSet rs = getApptsByMonth(months.getSelectionModel()
                    .getSelectedItem()
                    .toString());
            calendarCol.setText(months.getSelectionModel().getSelectedItem().toString());
            calendarCol.setCellValueFactory(cellData -> {
                return cellData.getValue().getRow();
            });
            calendarCol.setVisible(true);
            try {
                while (rs.next()) {
                    String id = "Appointment ID: "+rs.getString("appointmentId");
                    String customer = "Customer ID: "+rs.getString("customerId");
                    String start = "Date/Time: "+rs.getString("start");
                    ReportRow ar = new ReportRow(new ReadOnlyStringWrapper(id+" "+customer+" "+start));
                    appts.add(ar);
                }
                calendarTable.setItems(appts);
            } catch (SQLException ex) {
                System.out.println("Ex: " + ex);
            }
            
        }
        catch (ClassNotFoundException | SQLException ex) {System.out.println("Ex: "+ex);}
        
        week1col.setVisible(false);
        week2col.setVisible(false);
        week3col.setVisible(false);
        week4col.setVisible(false);
        week5col.setVisible(false);
        
        
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
    
    @FXML private void getWeek() throws ClassNotFoundException, SQLException{
        calendarCol.setVisible(false);
        week1col.setVisible(true);
        week2col.setVisible(true);
        week3col.setVisible(true);
        week4col.setVisible(true);
        week5col.setVisible(true);
        week1col.setCellValueFactory(cd->cd.getValue().getWeek1row());
        week2col.setCellValueFactory(cd->cd.getValue().getWeek2row());
        week3col.setCellValueFactory(cd->cd.getValue().getWeek3row());
        week4col.setCellValueFactory(cd->cd.getValue().getWeek4row());
        week5col.setCellValueFactory(cd->cd.getValue().getWeek5row());
        appts.clear();
        String m = months.getSelectionModel().getSelectedItem().toString();
        int max = Month.valueOf(m.toUpperCase()).maxLength();
        ResultSet week1rs = getApptsByWeekRange(1,7,m);
        ResultSet week2rs = getApptsByWeekRange(8,14,m);
        ResultSet week3rs = getApptsByWeekRange(15,21,m);
        ResultSet week4rs = getApptsByWeekRange(22,28,m);
        ResultSet week5rs = getApptsByWeekRange(29,31,m);
        try {
            while(week1rs.next()){
                ReportRow r = new ReportRow();
                r.setWeek1row(new ReadOnlyStringWrapper(week1rs.getString("start")));
                appts.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Ex: "+ex);
        }
        try {
            while(week2rs.next()){
                ReportRow r = new ReportRow();
                r.setWeek2row(new ReadOnlyStringWrapper(week2rs.getString("start")));
                appts.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Ex: "+ex);
        }
        try {
            while(week3rs.next()){
                ReportRow r = new ReportRow();
                r.setWeek3row(new ReadOnlyStringWrapper(week3rs.getString("start")));
                appts.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Ex: "+ex);
        }
        try {
            while(week4rs.next()){
                ReportRow r = new ReportRow();
                r.setWeek4row(new ReadOnlyStringWrapper(week4rs.getString("start")));
                appts.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Ex: "+ex);
        }
        try {
            while(week5rs.next()){
                ReportRow r = new ReportRow();
                r.setWeek5row(new ReadOnlyStringWrapper(week5rs.getString("start")));
                appts.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Ex: "+ex);
        }
        calendarTable.setItems(appts);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMonths();
    }

}
