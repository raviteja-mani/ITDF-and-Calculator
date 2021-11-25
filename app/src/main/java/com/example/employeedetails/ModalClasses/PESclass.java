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

    public long gsalary;
    public long exUSs;
    public long profTax;
    public long provfund;
    public long incTax;

    public PESclass(long gsalary, long exUSs, long profTax, long provfund, long incTax) {
        this.gsalary = gsalary;
        this.exUSs = exUSs;
        this.profTax = profTax;
        this.provfund = provfund;
        this.incTax = incTax;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGsalary(long gsalary) {
        this.gsalary = gsalary;
    }

    public void setExUSs(long exUSs) {
        this.exUSs = exUSs;
    }

    public void setProfTax(long profTax) {
        this.profTax = profTax;
    }

    public void setProvfund(long provfund) {
        this.provfund = provfund;
    }

    public void setIncTax(long incTax) {
        this.incTax = incTax;
    }

    public long getGsalary() {
        return gsalary;
    }

    public long getExUSs() {
        return exUSs;
    }

    public long getProfTax() {
        return profTax;
    }

    public long getProvfund() {
        return provfund;
    }

    public long getIncTax() {
        return incTax;
    }

    public long calculated(){
        return gsalary+exUSs+profTax+provfund+incTax;
    }

    public long getTotalPES() {
        return getGsalary()-getProvfund()-getProfTax()-getExUSs();
    }
}
