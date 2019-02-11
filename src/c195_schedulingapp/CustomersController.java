/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp;

import static c195_schedulingapp.C195_SchedulingApp.appStage;
import static c195_schedulingapp.C195_SchedulingApp.state;
import static c195_schedulingapp.CustomersController.customers;
import c195_schedulingapp.utils.DB;
import static c195_schedulingapp.utils.DB.getCustomers;
import c195_schedulingapp.utils.TableRow;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cfonseca
 */
public class CustomersController implements Initializable {

    @FXML
    private Button createCustomerBtn;
    @FXML
    private Button editCustomerBtn;
    @FXML
    private Button deleteCustomerBtn;
    @FXML
    private Button apptBtn;
    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn<TableRow, String> nameCol;
    @FXML
    private TableColumn<TableRow, String> addressCol;
    @FXML
    private TableColumn<TableRow, String> phoneCol;
    public static TableRow selected = new TableRow();

    public static ObservableList<TableRow> customers = FXCollections.observableArrayList();

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
    public void openCalendar() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    public void createCustomer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    protected void loadCustomer() throws ClassNotFoundException, SQLException {
        state.clearTempCustomer();
        state.clearTempIndex();
        selected = (TableRow) customerTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected: " + selected.getcustomerName());
        state.setTempCustomer(DB.searchCustomer(selected.getcustomerName().getValue()));
    }

    @FXML
    public void editCustomer() throws IOException, ClassNotFoundException, SQLException {
        loadCustomer();
        state.setTempIndex(customerTable.getSelectionModel().getSelectedIndex());
        Parent root = FXMLLoader.load(getClass().getResource("EditCustomer.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteCustomer() throws ClassNotFoundException, SQLException {
        state.clearTempCustomer();
        selected = (TableRow) customerTable.getSelectionModel().getSelectedItem();
        DB.deleteCustomer(selected.getcustomerName().getValue());
        state.setTempIndex(customerTable.getSelectionModel().getSelectedIndex());
        customers.remove(state.getTempIndex().intValue());
        state.clearTempCustomer();
        state.clearTempIndex();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TimeZone.setDefault(TimeZone.getTimeZone(state.getLocation()));
        customers.clear();
        try {
            ResultSet rs = getCustomers();
            nameCol.setCellValueFactory(cellData -> {
                return cellData.getValue().getcustomerName();
            });
            addressCol.setCellValueFactory(cellData -> {
                return cellData.getValue().getAddress();
            });
            phoneCol.setCellValueFactory(cellData -> {
                return cellData.getValue().getPhone();
            });
            try {
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
                System.out.println("Ex: " + ex);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }
}
