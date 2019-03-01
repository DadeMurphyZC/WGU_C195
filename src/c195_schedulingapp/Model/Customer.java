/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.Model;

import static c195_schedulingapp.utils.DB.getCityName;
import java.sql.SQLException;

public class Customer {
    private int addressId, active, customerId, cityId;
    private String customerName, address1, phone, postalCode, city;
    private Address address;
    
    public Customer(){};

    public Customer(String customerName, int customerId, int addressId, int active, Address address) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.addressId = addressId;
        this.active = active;
        this.address = address;
        this.address1 = address.getAddress();
        this.phone = address.getPhone(); 
    }

    public Customer(int addressId, int customerId, int cityId, String customerName, String address1, String phone, String postalCode, String city, Address address) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.cityId = cityId;
        this.customerName = customerName;
        this.address = address;
        this.address1 = address1;
        this.phone = phone;
        this.postalCode = postalCode;
        this.city = city;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
