/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class ReportsController implements Initializable {
    
    @FXML private Button generateBtn;
    @FXML private ComboBox reportsCB;
    
    final ArrayList reports = new ArrayList();
    
    public void populateReports(){
        reports.add("Appointment types by month");
        reports.add("Consultant schedules");
        reports.add("Test");
        reportsCB.getItems().addAll(reports);
    }
    
    public void reportsTest(){
        System.out.println("Running report: "+reportsCB.getSelectionModel().getSelectedItem().toString());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(reportsCB.getItems().isEmpty()){System.out.println("No reports found.");} else {
            reportsCB.getItems().clear();
        }
        populateReports();
    }    
    
}