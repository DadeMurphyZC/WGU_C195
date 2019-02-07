/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import c195_schedulingapp.Model.Address;
import c195_schedulingapp.Model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cris
 */

//CREATES AND RETURNS A TEMP CUSTOMER OBJECT
public class TempDBCustomer {
    
    public static Customer tempCustomer(ResultSet rs) throws SQLException{
        Customer temp = new Customer();
                temp.setAddressId(rs.getInt("addressId"));
                temp.setCustomerName(rs.getString("customerName"));
                temp.setAddress(new Address(rs.getString("address"), 
                        rs.getString("address2"), rs.getString("postalCode"), 
                        rs.getString("phone")));
                temp.setAddress1(temp.getAddress().getAddress()
                        + " " + temp.getAddress().getAddress2() 
                        + " ," + temp.getAddress().getPostalCode());
                temp.setPhone(temp.getAddress().getPhone());
        return temp;
    }
    
    //TEMP DESTRUCTOR, SETS IT TO NULL FOR GARBAGE COLLECTION
    public static void closeTempCustomer(Customer c){
        c = null;
    }
    
}
