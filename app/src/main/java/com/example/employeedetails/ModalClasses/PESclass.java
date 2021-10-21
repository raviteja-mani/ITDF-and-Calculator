package com.example.employeedetails.ModalClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "PES_table")
public class PESclass implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    public int gsalary;
    public int exUSs;
    public int profTax;
    public int provfund;
    public int incTax;

    public PESclass(int gsalary, int exUSs, int profTax, int provfund, int incTax) {
        this.gsalary = gsalary;
        this.exUSs = exUSs;
        this.profTax = profTax;
        this.provfund = provfund;
        this.incTax = incTax;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGsalary(int gsalary) {
        this.gsalary = gsalary;
    }

    public void setExUSs(int exUSs) {
        this.exUSs = exUSs;
    }

    public void setProfTax(int profTax) {
        this.profTax = profTax;
    }

    public void setProvfund(int provfund) {
        this.provfund = provfund;
    }

    public void setIncTax(int incTax) {
        this.incTax = incTax;
    }




    public int getGsalary() {
        return gsalary;
    }

    public int getExUSs() {
        return exUSs;
    }

    public int getProfTax() {
        return profTax;
    }

    public int getProvfund() {
        return provfund;
    }

    public int getIncTax() {
        return incTax;
    }

    public int calculated(){
        return gsalary+exUSs+profTax+provfund+incTax;
    }
}
