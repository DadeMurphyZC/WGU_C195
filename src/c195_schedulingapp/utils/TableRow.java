/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import javafx.beans.value.ObservableValue;

/**
 *
 * @author cfonseca
 */
public class TableRow {
    private ObservableValue<String> customerId;
    private ObservableValue<String> customerName;
    private ObservableValue<String> address;
    private ObservableValue<String> phone;
    
    public TableRow(ObservableValue<String> customerId, ObservableValue<String> customerName, ObservableValue<String> address, ObservableValue<String> phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
    }

    public TableRow() {
    }

    @Override
    public String toString() {
        return "TableRow{" + "customerName=" + customerName + '}';
    }

    public ObservableValue<String> getCustomerId() {
        return customerId;
    }

    public void setCustomerId(ObservableValue<String> customerId) {
        this.customerId = customerId;
    }
    
    public ObservableValue<String> getcustomerName() {
        return customerName;
    }

    public void setcustomerName(ObservableValue<String> customerName) {
        this.customerName = customerName;
    }

    public ObservableValue<String> getAddress() {
        return address;
    }

    public void setAddress(ObservableValue<String> address) {
        this.address = address;
    }

    public ObservableValue<String> getPhone() {
        return phone;
    }

    public void setPhone(ObservableValue<String> phone) {
        this.phone = phone;
    }
}
