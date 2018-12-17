/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

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
    private Integer tempIndex;
    private Boolean isAuthed;
    
    public State() {
        this.setIsAuthed(null);
        this.setTempCustomer(new Customer());
        this.setTempIndex(null);
        state = new HashMap<>();
        state.put("tempCustomer", (T)tempCustomer);
        state.put("tempIndex", (T)tempIndex);
        state.put("isAuthed", (T)isAuthed);
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

    public Integer getTempIndex() {
        return tempIndex;
    }

    public void setTempIndex(Integer tempIndex) {
        this.tempIndex = tempIndex;
    }
    
    public void clearTempCustomer(){
        this.tempCustomer = null;
    }
    
    @Override
    public String toString() {
        return "State: "+state;
    }
    
}
