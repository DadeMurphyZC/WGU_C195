/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import c195_schedulingapp.Model.Appointment;
import java.sql.Time;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;

/**
 *
 * @author cfonseca
 */
public class AppointmentRow {
    private ObservableValue<Integer> appointmentId;
    private ObservableValue<String> customerId;
    private ObservableValue<String> title;
    private ObservableValue<String> description;
    private ObservableValue<String> location;
    private ObservableValue<String> contact;
    private ObservableValue<Button> url;
    private ObservableValue<String> start;
    private ObservableValue<String> end;

        public AppointmentRow(ObservableValue<Integer> appointmentId, ObservableValue<String> customerId, ObservableValue<String> title, ObservableValue<String> description, ObservableValue<String> location, ObservableValue<String> contact, ObservableValue<Button> url, ObservableValue<String> start, ObservableValue<String> end) {
            this.appointmentId = appointmentId;
            this.customerId = customerId;
            this.title = title;
            this.description = description;
            this.location = location;
            this.contact = contact;
            this.url = url;
            this.start = start;
            this.end = end;
        }

    public AppointmentRow() {  
    }
        
        public ObservableValue<Integer> getAppointmentId() {
            return appointmentId;
        }
        
        public int getAppointmentIdInt() {
            return this.appointmentId.getValue();
        }

        public void setAppointmentId(ObservableValue<Integer> appointmentId) {
            this.appointmentId = appointmentId;
        }
        
        public ObservableValue<String> getCustomerId() {
            return customerId;
        }

        public void setCustomerId(ObservableValue<String> customerId) {
            this.customerId = customerId;
        }

        public ObservableValue<String> getTitle() {
            return title;
        }

        public void setTitle(ObservableValue<String> title) {
            this.title = title;
        }

        public ObservableValue<String> getDescription() {
            return description;
        }

        public void setDescription(ObservableValue<String> description) {
            this.description = description;
        }

        public ObservableValue<String> getLocation() {
            return location;
        }

        public void setLocation(ObservableValue<String> location) {
            this.location = location;
        }

        public ObservableValue<String> getContact() {
            return contact;
        }

        public void setContact(ObservableValue<String> contact) {
            this.contact = contact;
        }

        public ObservableValue<Button> getUrl() {
            return url;
        }

        public void setUrl(ObservableValue<Button> url) {
            this.url = url;
        }

        public ObservableValue<String> getStart() {
            return start;
        }

        public void setStart(ObservableValue<String> start) {
            this.start = start;
        }

        public ObservableValue<String> getEnd() {
            return end;
        }

        public void setEnd(ObservableValue<String> end) {
            this.end = end;
        }
}
