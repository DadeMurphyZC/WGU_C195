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

    public ReportRow(ObservableValue<String> row) {
        this.row = row;
    }
    
    public ReportRow(){}

    public ObservableValue<String> getRow() {
        return row;
    }

    public void setRow(ObservableValue<String> row) {
        this.row = row;
    }
    
    
}
