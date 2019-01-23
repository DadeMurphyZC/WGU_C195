/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import java.net.URL;
import java.util.ResourceBundle;
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
public class CalendarController implements Initializable {
    
    @FXML private ComboBox months;
    @FXML private Button getMonthlyBtn;
    @FXML private Button getWeeklyBtn;
    @FXML private TableView calendarTable;
    @FXML private TableColumn calendarCol;
    
    private void setMonths(){
        months.getItems().clear();
        months.getItems().setAll
           ("January", 
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setMonths();
    }    
    
}
