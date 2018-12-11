/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import Model.Customer;
import static c195_schedulingapp.utils.DB.getCustomers;
import c195_schedulingapp.utils.TableRow;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class CustomersController implements Initializable {
    @FXML private Button createCustomerBtn;
    @FXML private Button editCustomerBtn;
    @FXML private Button apptBtn;
    @FXML private TableView customerTable;
    @FXML private TableColumn<TableRow, String> nameCol;
    @FXML private TableColumn<TableRow, String> addressCol;
    @FXML private TableColumn<TableRow, String> phoneCol;
    public static Customer selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ObservableList<TableRow> customers = FXCollections.observableArrayList();
            ResultSet rs = getCustomers();
            nameCol.setCellValueFactory(cellData -> {return cellData.getValue().getcustomerName();});
            addressCol.setCellValueFactory(cellData -> {return cellData.getValue().getAddress();});
            phoneCol.setCellValueFactory(cellData -> {return cellData.getValue().getPhone();});
            try{
                while (rs.next()) {
                    String customerName = rs.getString("customerName");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    TableRow tr = new TableRow(
                            new ReadOnlyStringWrapper(customerName),
                            new ReadOnlyStringWrapper(address),
                            new ReadOnlyStringWrapper(phone)
                    );
                    customers.add(tr);
                }
                customerTable.setItems(customers);
            } catch (SQLException ex) {
                Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
