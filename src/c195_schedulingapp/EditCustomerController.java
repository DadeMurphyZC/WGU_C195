/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.state;
import static c195_schedulingapp.CustomersController.customers;
import c195_schedulingapp.Model.Address;
import static c195_schedulingapp.utils.DB.getCities;
import static c195_schedulingapp.utils.DB.updateCustomer;
import c195_schedulingapp.utils.TableRow;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML private ComboBox city;
    @FXML private TextField postalCode;
    @FXML private TextField phone;
    
    HashMap options = new HashMap();
    
    @FXML public synchronized void editCustomer() throws ClassNotFoundException, SQLException{
        state.getTempCustomer().setAddress(new Address(address.getText(),address2.getText(),1,postalCode.getText(),phone.getText()));    
        updateCustomer(state.getTempCustomer().getCustomerName(), name.getText(), address.getText(), postalCode.getText(), phone.getText(), state.getTempCustomer().getAddressId());
        TableRow tr = new TableRow(
                            new SimpleObjectProperty(state.getTempCustomer().getCustomerId()),
                            new SimpleStringProperty(name.getText()),
                            new SimpleStringProperty(address.getText()),
                            new SimpleStringProperty(phone.getText())
                    );
        customers.set(state.getTempIndex(), tr);
        state.clearTempCustomer();
        state.clearTempIndex();
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
        city.getItems().clear();
        city.getSelectionModel().select(state.getTempCustomer().getCity());
        try {options = getCities();}
        catch (ClassNotFoundException | SQLException ex) {System.out.println(ex);}
        options.forEach((k,v)->city.getItems().add(v));
    }    
    
}
