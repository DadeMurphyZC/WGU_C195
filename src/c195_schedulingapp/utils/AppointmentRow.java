/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import java.sql.Time;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author cfonseca
 */
public class AppointmentRow {
    private ObservableValue<String> appointmentId;
    private ObservableValue<String> customerId;
    private ObservableValue<String> title;
    private ObservableValue<String> description;
    private ObservableValue<String> location;
    private ObservableValue<String> contact;
    private ObservableValue<String> url;
    private ObservableValue<Time> start;
    private ObservableValue<Time> end;

        public AppointmentRow(ObservableValue<String> appointmentId, ObservableValue<String> customerId, ObservableValue<String> title, ObservableValue<String> description, ObservableValue<String> location, ObservableValue<String> contact, ObservableValue<String> url, ObservableValue<Time> start, ObservableValue<Time> end) {
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

        public ObservableValue<String> getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(ObservableValue<String> appointmentId) {
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

        public ObservableValue<String> getUrl() {
            return url;
        }

        public void setUrl(ObservableValue<String> url) {
            this.url = url;
        }

        public ObservableValue<Time> getStart() {
            return start;
        }

        public void setStart(ObservableValue<Time> start) {
            this.start = start;
        }

        public ObservableValue<Time> getEnd() {
            return end;
        }

        public void setEnd(ObservableValue<Time> end) {
            this.end = end;
        }
}
