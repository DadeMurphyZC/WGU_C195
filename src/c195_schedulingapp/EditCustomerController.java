/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.state;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
