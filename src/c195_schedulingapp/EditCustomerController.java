/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.state;
import c195_schedulingapp.Model.Address;
import static c195_schedulingapp.utils.DB.updateCustomer;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cris
 */
public class EditCustomerController implements Initializable {
    
    @FXML Button saveBtn;
    @FXML private TextField name;
    @FXML private TextField address;
    @FXML private TextField address2;
    @FXML private TextField city;
    @FXML private TextField postalCode;
    @FXML private TextField phone;
    
    @FXML public synchronized void editCustomer() throws ClassNotFoundException, SQLException{
        state.getTempCustomer().setAddress(new Address(address.getText(),address2.getText(),1,postalCode.getText(),phone.getText()));
        updateCustomer(state.getTempCustomer().getCustomerName(), name.getText());
        state.clearTempCustomer();
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
    
    @FXML public void cancel(){
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(state.getTempCustomer().getCustomerName());
        address.setText(state.getTempCustomer().getAddress().getAddress());
        address2.setText(state.getTempCustomer().getAddress().getAddress2());
        postalCode.setText(state.getTempCustomer().getAddress().getPostalCode());
        phone.setText(state.getTempCustomer().getPhone());
    }    
    
}
