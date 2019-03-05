/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import c195_schedulingapp.Model.Appointment;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author cfonseca
 */
public class TempDBAppointment {
    public static Appointment tempAppointment(ResultSet rs) throws SQLException{
        Appointment temp = new Appointment();
                temp.setAppointmentId(rs.getInt("appointmentid"));
                temp.setCustomerid(rs.getInt("customerId"));
                temp.setTitle(rs.getString("title"));
                temp.setDescription(rs.getString("description"));
                temp.setLocation(rs.getString("location"));
                temp.setContact(rs.getString("contact"));
                temp.setUrl(rs.getString("url"));
                temp.setStart(rs.getTimestamp("start").toString());
                temp.setEnd(rs.getTimestamp("end").toString());
        return temp;
    }
    
    //TEMP DESTRUCTOR, SETS IT TO NULL FOR GARBAGE COLLECTION
    public static void closeAppointment(Appointment a){
        a = null;
    }
}
