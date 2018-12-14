/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.CustomersController.customers;
import c195_schedulingapp.Model.Address;
import c195_schedulingapp.Model.Customer;
import c195_schedulingapp.utils.DB;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import c195_schedulingapp.utils.TableRow;
import javafx.beans.property.ReadOnlyStringWrapper;

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
    
    @FXML
    public synchronized void addCustomer() throws ClassNotFoundException, SQLException {
        //use TextField input to create a Customer object and add it to the db
        Customer c = new Customer();
        c.setCustomerName(name.getText());
        c.setAddress(new Address(address.getText(),address2.getText(),1,postalCode.getText(),phone.getText()));
        DB.addCustomerToDB(c);
        c.setAddress1(c.getAddress().getAddress());
        c.setPhone(c.getAddress().getPhone());
        TableRow tr = new TableRow(
                            new ReadOnlyStringWrapper(c.getCustomerName()),
                            new ReadOnlyStringWrapper(c.getAddress().getAddress()),
                            new ReadOnlyStringWrapper(c.getPhone())
                    );
        customers.add(tr);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
