/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.Model;

import static c195_schedulingapp.utils.DB.getCityName;
import java.sql.SQLException;

public class Customer {
    private int addressId, active;
    private String customerName, address1, phone;
    private Address address;
    
    public Customer(){};

    public Customer(String customerName, int addressId, int active, Address address) {
        this.customerName = customerName;
        this.addressId = addressId;
        this.active = active;
        this.address = address;
        this.address1 = address.getAddress();
        this.phone = address.getPhone(); 
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return customerName;
    }
    
    public String getCity(int id) throws ClassNotFoundException, SQLException{
        String city = getCityName(id);
        return city;
    }

}
