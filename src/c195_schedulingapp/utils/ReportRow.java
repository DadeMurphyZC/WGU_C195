/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195_schedulingapp.utils;

import javafx.beans.value.ObservableValue;

/**
 *
 * @author cris
 */
public class ReportRow {
    private ObservableValue<String> row;
    private ObservableValue<String> week1row;
    private ObservableValue<String> week2row;
    private ObservableValue<String> week3row;
    private ObservableValue<String> week4row;
    private ObservableValue<String> week5row;

    public ReportRow() {
    }

    public ReportRow(ObservableValue<String> row) {
        this.row = row;
    }

    public ReportRow(ObservableValue<String> row, ObservableValue<String> week1row, ObservableValue<String> week2row, ObservableValue<String> week3row, ObservableValue<String> week4row, ObservableValue<String> week5row) {
        this.row = row;
        this.week1row = week1row;
        this.week2row = week2row;
        this.week3row = week3row;
        this.week4row = week4row;
        this.week5row = week5row;
    }

    public ObservableValue<String> getRow() {
        return row;
    }

    public void setRow(ObservableValue<String> row) {
        this.row = row;
    }

    public ObservableValue<String> getWeek1row() {
        return week1row;
    }

    public void setWeek1row(ObservableValue<String> week1row) {
        this.week1row = week1row;
    }

    public ObservableValue<String> getWeek2row() {
        return week2row;
    }

    public void setWeek2row(ObservableValue<String> week2row) {
        this.week2row = week2row;
    }

    public ObservableValue<String> getWeek3row() {
        return week3row;
    }

    public void setWeek3row(ObservableValue<String> week3row) {
        this.week3row = week3row;
    }

    public ObservableValue<String> getWeek4row() {
        return week4row;
    }

    public void setWeek4row(ObservableValue<String> week4row) {
        this.week4row = week4row;
    }

    public ObservableValue<String> getWeek5row() {
        return week5row;
    }

    public void setWeek5row(ObservableValue<String> week5row) {
        this.week5row = week5row;
    }

    
    
    
}
