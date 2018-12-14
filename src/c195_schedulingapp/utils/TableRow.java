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
    private ObservableValue<String> customerName;
    private ObservableValue<String> address;
    private ObservableValue<String> phone;
    
    public TableRow(ObservableValue<String> customerName, ObservableValue<String> address, ObservableValue<String> phone) {
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TableRow{" + "customerName=" + customerName + '}';
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
