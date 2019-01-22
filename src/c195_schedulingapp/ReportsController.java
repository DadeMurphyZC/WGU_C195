/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.utils.DB.getApptTypesByMonth;
import static c195_schedulingapp.utils.DB.getApptsByLocation;
import static c195_schedulingapp.utils.DB.getApptsByUser;
import c195_schedulingapp.utils.ReportRow;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class ReportsController implements Initializable {
    
    @FXML private Button generateBtn;
    @FXML private ComboBox reportsCB;
    @FXML private TableView reportsTable;
    @FXML private TableColumn<ReportRow, String> reportCol;
    
    public static ObservableList<ReportRow> reportsList = FXCollections.observableArrayList();
    

    final HashMap reportshm = new HashMap();
    public static ReportRow selected = new ReportRow();
    
    public void populateReports(){
        reportshm.put(1, "Appointment types by month");
        reportshm.put(2, "Consultant schedules");
        reportshm.put(3, "Appointments by location");
        reportsCB.getItems().addAll(reportshm.values());
        reportsCB.getSelectionModel().selectFirst();
    }
    
    String report;
    public void getReport() throws SQLException, ClassNotFoundException{
        report = reportsCB.getSelectionModel().getSelectedItem().toString();
        switch(report){
            case "Appointment types by month": 
                //test
                reportsList.clear();
                getApptTypesByMonthReport();
                break;
            case "Consultant schedules": 
                getApptsByUserReport();
                break;
            case "Appointments by location":
                //test
                reportsList.clear();
                getApptsByLocationReport();
                break;
        }; 
    }
    
    public void getApptTypesByMonthReport() throws ClassNotFoundException, SQLException{
        reportsTable.getItems().clear();
        ResultSet rs = getApptTypesByMonth();
        reportsList.clear();
        while(rs.next()){
            String report = rs.getString(1)+"    -    "
                        + rs.getString(2)+"    ||     "
                        + rs.getString(3)+" || "
                        + rs.getString(4)+" || "
                        + rs.getString(5)+" || "
                        + rs.getString(6)+" || "
                        + rs.getString(7)+" || "
                        + rs.getString(8)+" || "
                        + rs.getString(9);
            ReportRow rr = new ReportRow(
                    new ReadOnlyStringWrapper(report)
            );
            reportsList.add(rr);
        }
        reportsTable.setItems(reportsList);
    }
    
    public void getApptsByUserReport() throws SQLException, ClassNotFoundException{
        reportsTable.getItems().clear();
            ResultSet rs = getApptsByUser();
            reportsList.clear();
            while(rs.next()){
                String report = rs.getString(1)+"    -    "
                        + rs.getString(2)+"    ||     "
                        + rs.getString(3)+" || "
                        + rs.getString(4)+" || "
                        + rs.getString(5)+" || "
                        + rs.getString(6)+" || "
                        + rs.getString(7);
                ReportRow rr = new ReportRow(
                        new ReadOnlyStringWrapper(report)
                );
                reportsList.add(rr);
            }
            reportsTable.setItems(reportsList);
    }
    
    public void getApptsByLocationReport() throws SQLException, ClassNotFoundException{
        reportsTable.getItems().clear();
            ResultSet rs = getApptsByLocation();
            reportsList.clear();
            while(rs.next()){
                String report = rs.getString(1)+"    ||    "
                        + rs.getString(2)+"    ||     "
                        + rs.getString(3)+"    ||    "
                        + rs.getString(4);
                ReportRow rr = new ReportRow(
                        new ReadOnlyStringWrapper(report)
                );
                reportsList.add(rr);
            }
            reportsTable.setItems(reportsList);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reportsList.clear();
        populateReports();
        reportCol.setCellValueFactory(c ->  c.getValue().getRow());
    }    
    
}