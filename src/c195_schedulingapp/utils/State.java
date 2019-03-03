/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import c195_schedulingapp.Model.Appointment;
import c195_schedulingapp.Model.Customer;
import java.util.HashMap;

/**
 *
 * @author cfonseca
 * @param <T>
 */
public class State<T> {
    private HashMap<String, T> state;
    private Customer tempCustomer;
    private Appointment tempAppointment;
    private Integer tempIndex;
    private Boolean isAuthed;
    private Boolean needsRefresh;
    private String location;

    
    public State() {
        this.setIsAuthed(null);
        this.setTempCustomer(new Customer());
        this.setTempAppointment(new Appointment());
        this.setTempIndex(null);
        this.setNeedsRefresh(false);
        this.setLocation(null);
        state = new HashMap<>();
        state.put("tempCustomer", (T)tempCustomer);
        state.put("tempAppointment", (T)tempAppointment);
        state.put("tempIndex", (T)tempIndex);
        state.put("isAuthed", (T)isAuthed);
        state.put("needsRefresh", (T)needsRefresh);
        state.put("location", (T)location);
    };
    
    public Boolean getIsAuthed() {
        return isAuthed;
    }

    public void setIsAuthed(Boolean isAuthed) {
        this.isAuthed = isAuthed;
        System.out.println("isAuthed set to: "+isAuthed);
    }

    public HashMap<String, T> getState() {
        return state;
    }

    public void setState(HashMap<String, T> state) {
        this.state = state;
    }
    
    public void addToState(String k, T v) {
        state.put(k, v);
        System.out.println("Key: "+k+ " and Value: "+v+" added to State.");
    }

    public Customer getTempCustomer() {
        return tempCustomer;
    }

    public void setTempCustomer(Customer tempCustomer) {
        this.tempCustomer = tempCustomer;
    }

    public Appointment getTempAppointment() {
        return tempAppointment;
    }

    public void setTempAppointment(Appointment tempAppointment) {
        this.tempAppointment = tempAppointment;
    }
    
    public Integer getTempIndex() {
        return tempIndex;
    }

    public void setTempIndex(Integer tempIndex) {
        this.tempIndex = tempIndex;
    }

    public Boolean getNeedsRefresh() {
        return needsRefresh;
    }

    public void setNeedsRefresh(Boolean needsRefresh) {
        this.needsRefresh = needsRefresh;
    }
    
    public void clearTempCustomer(){
        this.tempCustomer = new Customer();
    }
    
    public void clearTempAppointment(){
        this.tempAppointment = null;
    }
    
    public void clearTempIndex(){
        this.tempIndex = null;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "State: "+state;
    }
    
}
