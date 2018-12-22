/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.state;
import static c195_schedulingapp.CustomersController.customers;
import c195_schedulingapp.Model.Address;
import c195_schedulingapp.Model.Customer;
import c195_schedulingapp.utils.DB;
import static c195_schedulingapp.utils.DB.getCities;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import c195_schedulingapp.utils.TableRow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class AddCustomerController implements Initializable {
    
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField address2;
    @FXML private TextField postalCode;
    @FXML private TextField phone;
    @FXML private ComboBox city;

    HashMap options = new HashMap();
    
    @FXML
    public synchronized void addCustomer() throws ClassNotFoundException, SQLException {
        //use TextField input to create a Customer object and add it to the db
        state.getTempCustomer().setCustomerName(name.getText());
        state.getTempCustomer().setAddress(new Address(address.getText(),address2.getText(),1,postalCode.getText(),phone.getText()));
        DB.addCustomerToDB(state.getTempCustomer());
        TableRow tr = new TableRow(
                            new SimpleStringProperty(state.getTempCustomer().getCustomerName()),
                            new SimpleStringProperty(state.getTempCustomer().getAddress().getAddress()),
                            new SimpleStringProperty(state.getTempCustomer().getAddress().getPhone())
                    );
        customers.add(tr);
        state.clearTempCustomer();
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void cancel(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        city.getItems().clear();
        try {options = getCities();} 
        catch (ClassNotFoundException | SQLException ex) {System.out.println(ex);}
        options.forEach((k,v)->city.getItems().add(v));
    }    
    
}
