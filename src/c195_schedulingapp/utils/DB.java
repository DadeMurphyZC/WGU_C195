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
import static c195_schedulingapp.utils.TempDBAppointment.tempAppointment;
import static c195_schedulingapp.C195_SchedulingApp.state;
import c195_schedulingapp.Model.Appointment;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

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
                + "WHERE customer.customerName = ?"
                + "AND address.addressId = customer.addressId");
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();
        Customer temp = null;
        while (rs.next()) {
            temp = tempCustomer(rs);
        }
        return temp;
    }
    
    public static String getCustomerName(int id) throws SQLException, ClassNotFoundException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT customerName "
                        + "FROM customer "
                        + "WHERE customerId = ?"
        );
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        String name = null;
        while(rs.next()){
            name = rs.getString("customerName");
        }
        return name;
    }
    
    public static Appointment searchAppointment(int id) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement("SELECT * from appointment "
                + "WHERE appointment.appointmentid = ?");
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        Appointment temp = null;
        while (rs.next()) {
            temp = tempAppointment(rs);
        }
        return temp;
    }
    
    public static void deleteAppointment(int id) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "DELETE from appointment "
                        + "WHERE appointmentId = ?"
        );
        pstmt.setInt(1, id);
        pstmt.execute();
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
    
    public static ResultSet getApptTypesByMonth() throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "select concat(monthname(date(start)),\" - \",year(date(start))) as month, "
                + "appointmentid, " 
                + "customer.customerName, "
                + "title, "
                + "description, "
                + "location, "
                + "contact, "
                + "date(start) as date, "
                + "time(start) as start, "
                + "time(end) as end "
                + "from appointment "
                + "join customer "
                + "where appointment.customerId = customer.customerid;"
        );
        rs = pstmt.executeQuery();
        return rs;
    }
    
    public static HashMap getApptStartTimes() throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT hour(start) as start, hour(end) as end from appointment"
        );
        rs = pstmt.executeQuery();
        HashMap startTimes = new HashMap();
        while(rs.next()){startTimes.put(rs.getInt("start"), rs.getInt("end"));};
        return startTimes;
    }

    
    public static ResultSet getApptsByMonth(String month) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * from appointment "
                        + "WHERE monthname(start) = ?"
        );
        pstmt.setString(1, month);
        rs = pstmt.executeQuery();
        return rs;
    }
    
    public static ResultSet getApptsByWeekRange(int start, int end, String month) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * from appointment "
                        + "WHERE dayofmonth(start) between ? and ? "
                        + "AND monthname(start) = ? "
                        + "ORDER BY dayofmonth(start)"
        );
        pstmt.setInt(1, start);
        pstmt.setInt(2, end);
        pstmt.setString(3, month);
        rs = pstmt.executeQuery();
        return rs;
    }
    
    public static String getApptAlert() throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT * from appointment "
                        + "WHERE timediff(?, start) < 15"
        );
        String timeStamp = new Timestamp(System.currentTimeMillis()).toString();
        pstmt.setTimestamp(1, Timestamp.valueOf(timeStamp));
        rs = pstmt.executeQuery();
        String alertMessage = null;
        while(rs.next()){alertMessage=rs.getString("start");}
        return alertMessage;
    }
    
    public static ResultSet getApptsByUser() throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT contact, appointmentid, title, description, location, start, end "
                        + "FROM appointment "
                        + "ORDER BY appointment.contact"
        );
        rs = pstmt.executeQuery();
        return rs;
    }
    
    public static ResultSet getApptsByLocation() throws SQLException, ClassNotFoundException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT location, appointmentid, date(start), time(start) "
                        + "FROM appointment "
                        + "ORDER BY location;"
        );
        rs = pstmt.executeQuery();
        return rs;
    }
    
    public static ArrayList getCustomersArray() throws ClassNotFoundException, SQLException{
        ArrayList customers = new ArrayList();
        conn = dbConnect();
        rs = conn.prepareStatement("SELECT customerName FROM customer").executeQuery();
        while(rs.next()){customers.add(rs.getString("customerName"));}
        return customers;
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
            pstmt.setTimestamp(6, Timestamp.from(Instant.now()));
            pstmt.setString(7, "test");
            pstmt.setString(8, "test");
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
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            pstmt2.setString(1, c.getCustomerName());
            pstmt2.setInt(2, newId);
            pstmt2.setInt(3, c.getActive());
            pstmt2.setTimestamp(4, Timestamp.from(Instant.now()));
            pstmt2.setString(5, "test");
            pstmt2.setString(6, "test");
            pstmt2.executeUpdate();
        }
    }
    
    public static void updateCustomer(String name, String updated, String address, String postalCode, String phone, int id) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "UPDATE customer "
                        + "SET customer.customerName = ? "
                        + "WHERE customer.customerName = ?");
        pstmt.setString(1, updated);
        pstmt.setString(2, name);
        pstmt.executeUpdate();
        
        pstmt2 = conn.prepareStatement(
                "UPDATE address "
                        + "SET address = ?, "
                        + "postalCode = ?, "
                        + "phone = ? "
                        + "WHERE addressId = ?");
        pstmt2.setString(1, address);
        pstmt2.setString(2, postalCode);
        pstmt2.setString(3, phone);
        pstmt2.setInt(4, id);
        pstmt2.executeUpdate();
        
    }

        
    public static void deleteCustomer(String name) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "DELETE from customer "
                + "WHERE customer.customerName = ?");
        pstmt.setString(1, name);
        pstmt.execute();
    }
    
    public static HashMap getCities() throws ClassNotFoundException, SQLException{
        HashMap dbCities = new HashMap();
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT cityid, city "
                + "FROM city"
        );
        rs = pstmt.executeQuery();
        while(rs.next()){
            dbCities.put(rs.getString("cityid"), rs.getString("city"));
        }
        return dbCities;
    }
    
    public static String getCityName(int id) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT city from city "
                        + "WHERE city.cityId = ?"
        );
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        String city = null;
        while(rs.next()){
            city = rs.getString("city");
        }
        return city;
    }
    
    public static HashMap getUsers() throws ClassNotFoundException, SQLException{
        HashMap dbUsers = new HashMap();
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "SELECT userid, userName "
              + "FROM user"
        );
        rs = pstmt.executeQuery();
        while(rs.next()){
            dbUsers.put(rs.getInt("userid"), rs.getString("userName"));
        }
        return dbUsers;
    }
    
    public static void addAppointment(Appointment a) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdateBy)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        pstmt.setInt(1, a.getCustomerid());
        pstmt.setString(2, a.getTitle());
        pstmt.setString(3, a.getDescription());
        pstmt.setString(4, a.getLocation());
        pstmt.setString(5, a.getContact());
        pstmt.setString(6, a.getUrl());
        pstmt.setTimestamp(7, Timestamp.valueOf(a.getStart()));
        pstmt.setTimestamp(8, Timestamp.valueOf(a.getEnd()));
        pstmt.setTimestamp(9, Timestamp.from(Instant.now()));
        pstmt.setTimestamp(10, Timestamp.from(Instant.now()));
        pstmt.setString(11, "test");
        pstmt.executeUpdate();
    }
    
    public static void updateAppointment(int id, String title, String description, String location, String contact, Timestamp start, Timestamp end) throws ClassNotFoundException, SQLException{
        conn = dbConnect();
        pstmt = conn.prepareStatement(
                "UPDATE appointment "
                        + "SET title = ?, "
                        + "description = ?, "
                        + "location = ?, "
                        + "contact = ?, "
                        + "start = ?, "
                        + "end = ? "
                        + "WHERE appointmentId = ?"
        );
        pstmt.setString(1, title);
        pstmt.setString(2, description);
        pstmt.setString(3, location);
        pstmt.setString(4, contact);
        pstmt.setTimestamp(5, start);
        pstmt.setTimestamp(6, end);
        pstmt.setInt(7, id);
        pstmt.executeUpdate();
    }
}
