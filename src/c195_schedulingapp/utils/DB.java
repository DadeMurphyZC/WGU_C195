/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import c195_schedulingapp.Model.Customer;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.stage.Stage;
import static c195_schedulingapp.utils.TempDBCustomer.tempCustomer;
import static c195_schedulingapp.C195_SchedulingApp.state;

/**
 *
 * @author cfonseca
 */
public class DB {
    
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://52.206.157.109/U005XW";
    static String user = "U005XW";
    static String pass = "53687115318";
    static Stage stage;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static PreparedStatement pstmt;
    static PreparedStatement pstmt2;

    public static Connection dbConnect() throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        return conn;
    }

    public static void dbExecute(String query) throws ClassNotFoundException, SQLException {
        conn = DriverManager.getConnection(url, user, pass);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(query);
    }

    public static Boolean loginAuth(String u, String p) throws ClassNotFoundException, SQLException, IOException {
        conn = dbConnect();
        pstmt = conn.prepareStatement("SELECT * FROM user WHERE userName = ?");
        pstmt.setString(1, u);
        rs = pstmt.executeQuery();
        state.getState().put("isAuthed", (rs.next()) ? (rs.getString("password").equals(p)) : false);
        System.out.println(state);
        return (Boolean) state.getState().get("isAuthed");
    };

    public static Customer searchCustomer(String name) throws ClassNotFoundException, SQLException {
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * FROM customer "
                + "JOIN address "
                + "ON customer.customerId "
                + "WHERE customer.customerName = ?");
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();
        Customer temp = null;
        while (rs.next()) {
            temp = tempCustomer(rs);
        }
        return temp;
    }

    public static ResultSet getCustomers() throws ClassNotFoundException, SQLException {
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * from customer "
                + "JOIN address "
                + "ON customer.customerId "
                + "WHERE address.addressId = customer.addressId");
        rs = pstmt.executeQuery();
        return rs;
    }
    
    public static ResultSet getAppointments() throws SQLException, ClassNotFoundException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * from appointment");
        rs = pstmt.executeQuery();
        return rs;
    }

    public static void addCustomerToDB(Customer c) throws ClassNotFoundException, SQLException {
        conn = dbConnect();
        String check = "SELECT * FROM customer WHERE customerName = ?";
        pstmt = conn.prepareStatement(check);
        pstmt.setString(1, c.getCustomerName());
        rs = pstmt.executeQuery();
        if (rs.next()) {

        } else {
            pstmt = conn.prepareStatement(
                    "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdateBy)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                            , Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, c.getAddress().getAddress());
            pstmt.setString(2, c.getAddress().getAddress2());
            pstmt.setInt(3, 3);
            pstmt.setString(4, c.getAddress().getPostalCode());
            pstmt.setString(5, c.getAddress().getPhone());
            pstmt.setDate(6, null);
            pstmt.setString(7, "");
            pstmt.setString(8, "");
            pstmt.executeUpdate();
            int newId = -1;
            ResultSet rsKeys = pstmt.getGeneratedKeys();
            System.out.println(rsKeys);

            if (rsKeys.next()) {
                newId = rsKeys.getInt(1);
            }

            pstmt2 = conn.prepareStatement(
                    "INSERT INTO customer "
                    + "(customerName, addressId, active, createDate, createdBy, lastUpdateBy)"
                    + "VALUES (?, ?, ?, ?, ?, ?)"
                        );

            pstmt2.setString(1, c.getCustomerName());
            pstmt2.setInt(2, newId);
            pstmt2.setInt(3, c.getActive());
            pstmt2.setDate(4, null);
            pstmt2.setString(5, "");
            pstmt2.setString(6, "");
            pstmt2.executeUpdate();
        }
    }
    
    public static void updateCustomer(String name, String updatedName) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * FROM customer "
                + "WHERE customer.customerName = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();
        while(rs.next()){
            rs.updateString("customerName", updatedName);
            rs.updateRow();
        }
        if(pstmt!=null){pstmt.close();}
    }
}
