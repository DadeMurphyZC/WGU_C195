/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author cfonseca
 */
public class Address {
    private String address;
    private String address2;
    private int cityId;
    private String postalCode;
    private String phone;

    
    public Address(){}

    public Address(String address, String address2, String postalCode, String phone) {
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    
    
    public Address(String address, String address2, int cityId, String postalCode, String phone) {
        this.address = address;
        this.address2 = address2;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }   
}
